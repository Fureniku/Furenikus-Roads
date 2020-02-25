package com.silvaniastudios.roads.blocks.tileentities.paintfiller;

import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.tileentities.FREnergyStorage;
import com.silvaniastudios.roads.fluids.FRFluids;
import com.silvaniastudios.roads.items.PaintGun;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.CapabilityItemHandler;

public class PaintFillerElectricEntity extends PaintFillerEntity implements ITickable, ICapabilityProvider {
	
	public boolean isProcessing = false;
	int consumedEnergy = 0;
	
	public PaintFillerElectricEntity() {}
	
	public FREnergyStorage energy = new FREnergyStorage(RoadsConfig.machine.electricPaintFillerEnergyStorage, RoadsConfig.machine.electricPaintFillerEnergyTransferRate) {};
	
	public Container createContainer(EntityPlayer player) {
		return new PaintFillerContainer(player.inventory, this, true);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityEnergy.ENERGY) {
			return getCapability(capability, facing) != null;
		}
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
		return super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityEnergy.ENERGY) {
			if (facing != null) {
				return CapabilityEnergy.ENERGY.cast(energy);
			}
		}
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory);
		}
		
		return super.getCapability(capability, facing);
	}
	
	@Override
	public void update() {
		if (fuel_remaining > 0) {
			fuel_remaining--;
		} else if (fuel_remaining <= 0) {
			if (!inventory.getStackInSlot(4).isEmpty()) {
				fuel_remaining = TileEntityFurnace.getItemBurnTime(inventory.getStackInSlot(4));
				last_fuel_cap = fuel_remaining;
				if (inventory.getStackInSlot(4).getItem() == Items.LAVA_BUCKET) {
					inventory.setStackInSlot(4, new ItemStack(Items.BUCKET));
				} else {
					inventory.extractItem(4, 1, false);
				}
				sendUpdates();
			} else {
				timerCount = 0;
				return;
			}
		}
		
		if (timerCount < RoadsConfig.machine.fillerTickRate) {
			if (shouldTick()) {
				timerCount++;
			} else {
				timerCount = 0;
			}
		} else {
			if (!world.isRemote) {
				int paintPerDye = RoadsConfig.general.paintPerDye;
				boolean hasChanges = false;
				
				if ((!inventory.getStackInSlot(0).isEmpty() || !inventory.getStackInSlot(2).isEmpty() || !inventory.getStackInSlot(3).isEmpty()) && fuel_remaining > 0) {
					ItemStack white_dye = inventory.getStackInSlot(0);
					ItemStack yellow_dye = inventory.getStackInSlot(2);
					ItemStack red_dye = inventory.getStackInSlot(3);
					
					if (white_dye.getItem() instanceof ItemDye) {
						EnumDyeColor col = EnumDyeColor.byDyeDamage(white_dye.getMetadata());
						if (col == EnumDyeColor.WHITE && white_paint.getFluidAmount() <= white_paint.getCapacity() + paintPerDye) {
							inventory.extractItem(0, 1, false);
							white_paint.fill(new FluidStack(FRFluids.white_paint, paintPerDye), true);
							hasChanges = true;
						}
					}
					
					if (yellow_dye.getItem() instanceof ItemDye) {
						EnumDyeColor col = EnumDyeColor.byDyeDamage(yellow_dye.getMetadata());
						if (col == EnumDyeColor.YELLOW && yellow_paint.getFluidAmount() <= yellow_paint.getCapacity() + paintPerDye) {
							inventory.extractItem(2, 1, false);
							yellow_paint.fill(new FluidStack(FRFluids.yellow_paint, paintPerDye), true);
							hasChanges = true;
						}
					}
					
					if (red_dye.getItem() instanceof ItemDye) {
						EnumDyeColor col = EnumDyeColor.byDyeDamage(red_dye.getMetadata());
						if (col == EnumDyeColor.RED && red_paint.getFluidAmount() <= red_paint.getCapacity() + paintPerDye) {
							inventory.extractItem(3, 1, false);
							red_paint.fill(new FluidStack(FRFluids.red_paint, paintPerDye), true);
							hasChanges = true;
						}
					}
				}

				if (getGun().getItem() instanceof PaintGun) {
					if (!has_gun) { hasChanges = true; }
					has_gun = true;
					
					NBTTagCompound nbt;
					
					if (!getGun().hasTagCompound()) {
						nbt = new NBTTagCompound();
						nbt.setInteger("white_paint", 0);
						nbt.setInteger("yellow_paint", 0);
						nbt.setInteger("red_paint", 0);
						
						getGun().setTagCompound(nbt);
					} else {
						nbt = getGun().getTagCompound();
					}
					
					gun_white = nbt.getInteger("white_paint");
					gun_yellow = nbt.getInteger("yellow_paint");
					gun_red = nbt.getInteger("red_paint");
					
					fillGun(nbt);
				} else {
					if (has_gun) { hasChanges = true; }
					has_gun = false;
					
					gun_white = 0;
					gun_yellow = 0;
					gun_red = 0;
				}
				
				if (hasChanges) {
					sendUpdates();
				}
			}
			timerCount = 0;
		}
	}
	
	public boolean shouldTick() {
		int paintPerDye = RoadsConfig.general.paintPerDye;
		
		if ((!inventory.getStackInSlot(0).isEmpty() || !inventory.getStackInSlot(2).isEmpty() || !inventory.getStackInSlot(3).isEmpty()) && fuel_remaining > 0) {
			ItemStack white_dye = inventory.getStackInSlot(0);
			ItemStack yellow_dye = inventory.getStackInSlot(2);
			ItemStack red_dye = inventory.getStackInSlot(3);
			
			if (white_dye.getItem() instanceof ItemDye) {
				EnumDyeColor col = EnumDyeColor.byDyeDamage(white_dye.getMetadata());
				if (col == EnumDyeColor.WHITE && white_paint.getFluidAmount() <= white_paint.getCapacity() + paintPerDye) {
					return true;
				}
			}
			
			if (yellow_dye.getItem() instanceof ItemDye) {
				EnumDyeColor col = EnumDyeColor.byDyeDamage(yellow_dye.getMetadata());
				if (col == EnumDyeColor.YELLOW && yellow_paint.getFluidAmount() <= yellow_paint.getCapacity() + paintPerDye) {
					return true;
				}
			}
			
			if (red_dye.getItem() instanceof ItemDye) {
				EnumDyeColor col = EnumDyeColor.byDyeDamage(red_dye.getMetadata());
				if (col == EnumDyeColor.RED && red_paint.getFluidAmount() <= red_paint.getCapacity() + paintPerDye) {
					return true;
				}
			}
		}

		if (getGun().getItem() instanceof PaintGun) {
			NBTTagCompound nbt = getGun().getTagCompound();
			
			if (!getGun().hasTagCompound() && (white_paint.getFluidAmount() > 0 || white_paint.getFluidAmount() > 0 || white_paint.getFluidAmount() > 0)) {
				return true;
			}
			if (!getGun().hasTagCompound()) {
				nbt = new NBTTagCompound();
				nbt.setInteger("white_paint", 0);
				nbt.setInteger("yellow_paint", 0);
				nbt.setInteger("red_paint", 0);
				
				getGun().setTagCompound(nbt);
				if (!world.isRemote) { sendUpdates(); }
				return true;
			}
			
			if (white_paint.getFluidAmount()  > 0 && nbt.getInteger("white_paint")  < GUN_TANK_CAP) { return true; }
			if (yellow_paint.getFluidAmount() > 0 && nbt.getInteger("yellow_paint") < GUN_TANK_CAP) { return true; }
			if (red_paint.getFluidAmount()    > 0 && nbt.getInteger("red_paint")    < GUN_TANK_CAP) { return true; }
		}
		return false;
	}
	
	public void fillGun(NBTTagCompound nbt) {
		ItemStack stack = getGun();
		if (stack.getItem() instanceof PaintGun) {
			int fillRate = 1000;
			boolean hasChanges = false;
			
			if (white_paint.getFluidAmount() > 0) {
				FluidStack drained = null;
				if (white_paint.getFluidAmount() >= fillRate && gun_white <= GUN_TANK_CAP - fillRate) {
					drained = white_paint.drain(new FluidStack(FRFluids.white_paint, fillRate), true);
				} else {
					if (white_paint.getFluidAmount() <= (GUN_TANK_CAP - gun_white)) { //if {block tank} less than or equal to {gun max - gun current} - "Is there enough space?"
						drained = white_paint.drain(new FluidStack(FRFluids.white_paint, red_paint.getFluidAmount()), true);
					} else {
						drained = white_paint.drain(new FluidStack(FRFluids.white_paint, (GUN_TANK_CAP - gun_white)), true);
					}
				}
				if (drained != null) {
					hasChanges = true;
					gun_white = gun_white + drained.amount;
				}
			}
			
			if (yellow_paint.getFluidAmount() > 0) {
				FluidStack drained = null;
				if (yellow_paint.getFluidAmount() >= fillRate && gun_yellow <= GUN_TANK_CAP - fillRate) {
					drained = yellow_paint.drain(new FluidStack(FRFluids.yellow_paint, fillRate), true);
				} else {
					if (yellow_paint.getFluidAmount() <= (GUN_TANK_CAP - gun_yellow)) {
						drained = yellow_paint.drain(new FluidStack(FRFluids.yellow_paint, yellow_paint.getFluidAmount()), true);
					} else {
						drained = yellow_paint.drain(new FluidStack(FRFluids.yellow_paint, (GUN_TANK_CAP - gun_yellow)), true);
					}
				}
				if (drained != null) {
					hasChanges = true;
					gun_yellow = gun_yellow + drained.amount;
				}
			}
		
			if (red_paint.getFluidAmount() > 0) {
				FluidStack drained = null;
				if (red_paint.getFluidAmount() >= fillRate && gun_red <= GUN_TANK_CAP - fillRate) {
					drained = red_paint.drain(new FluidStack(FRFluids.red_paint, fillRate), true);
				} else {
					if (red_paint.getFluidAmount() <= (GUN_TANK_CAP - gun_red)) {
						drained = red_paint.drain(new FluidStack(FRFluids.red_paint, red_paint.getFluidAmount()), true);
					} else {
						drained = red_paint.drain(new FluidStack(FRFluids.red_paint, (GUN_TANK_CAP - gun_red)), true);
					}
				}
				if (drained != null) { 
					hasChanges = true;
					gun_red = gun_red + drained.amount;
				}
			}
			if (hasChanges) {
				nbt.setInteger("white_paint", gun_white);
				nbt.setInteger("yellow_paint", gun_yellow);
				nbt.setInteger("red_paint", gun_red);
				sendUpdates();
			}
		}
	}

	public ItemStack getGun() {
		return inventory.getStackInSlot(1);
	}
	
	@Override
	public void readNBT(NBTTagCompound nbt) {
		has_gun = nbt.getBoolean("has_gun");
		
		int wp = nbt.getInteger("white_paint");
		int yp = nbt.getInteger("yellow_paint");
		int rp = nbt.getInteger("red_paint");
		
		if (wp > FILLER_TANK_CAP) { wp = FILLER_TANK_CAP; }
		if (yp > FILLER_TANK_CAP) { yp = FILLER_TANK_CAP; }
		if (rp > FILLER_TANK_CAP) { rp = FILLER_TANK_CAP; }
		
		white_paint.setFluid(new FluidStack(FRFluids.white_paint, wp));
		yellow_paint.setFluid(new FluidStack(FRFluids.yellow_paint, yp));
		red_paint.setFluid(new FluidStack(FRFluids.red_paint, rp));
		
		gun_white = nbt.getInteger("gun_white");
		gun_yellow = nbt.getInteger("gun_yellow");
		gun_red = nbt.getInteger("gun_red");
		
		if (gun_white > GUN_TANK_CAP) { gun_white = GUN_TANK_CAP; }
		if (gun_yellow > GUN_TANK_CAP) { gun_yellow = GUN_TANK_CAP; }
		if (gun_red > GUN_TANK_CAP) { gun_red = GUN_TANK_CAP; }
		
		if (nbt.hasKey("items")) {
			inventory.deserializeNBT((NBTTagCompound) nbt.getTag("items"));
		}
		if (nbt.hasKey("energy")) {
			energy.deserializeNBT((NBTTagCompound) nbt.getTag("energy"));
		}
	}
	
	@Override
	public NBTTagCompound writeNBT(NBTTagCompound nbt) {
		nbt.setBoolean("has_gun", has_gun);
		
		nbt.setInteger("white_paint", white_paint.getFluidAmount());
		nbt.setInteger("yellow_paint", yellow_paint.getFluidAmount());
		nbt.setInteger("red_paint", red_paint.getFluidAmount());
		
		nbt.setInteger("gun_white", gun_white);
		nbt.setInteger("gun_yellow", gun_yellow);
		nbt.setInteger("gun_red", gun_red);
		
		nbt.setTag("items", inventory.serializeNBT());
		nbt.setTag("energy", energy.serializeNBT());
		return nbt;
	}
}
