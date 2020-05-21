package com.silvaniastudios.roads.blocks.tileentities.paintfiller;

import javax.annotation.Nonnull;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.tileentities.RoadTileEntity;
import com.silvaniastudios.roads.fluids.FRFluids;
import com.silvaniastudios.roads.items.PaintGun;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class PaintFillerEntity extends RoadTileEntity implements ITickable, ICapabilityProvider {
	
	public boolean has_gun = false;
	
	public int gun_white = 0;
	public int gun_yellow = 0;
	public int gun_red = 0;
	
	public int timerCount = 0;
	
	public static final int FILLER_TANK_CAP = 32000;
	public static final int GUN_TANK_CAP = 32000;
	
	public PaintFillerEntity() {}
	
	@Override
	public boolean hasFastRenderer() {
		return true;
	}
	
	public ItemStackHandler inventory = new ItemStackHandler(5) {
		
		@Override
		public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
			return true;
		}
		
		@Override
		protected void onContentsChanged(int slot) {
			PaintFillerEntity.this.markDirty();
		}
	};
	
	public FluidTank white_paint = new FluidTank(FILLER_TANK_CAP) {
		@Override
		public boolean canFillFluidType(FluidStack fluid) {
			return fluid.getFluid() == FRFluids.white_paint;
	    }
	};
	
	public FluidTank yellow_paint = new FluidTank(FILLER_TANK_CAP) {
		@Override
		public boolean canFillFluidType(FluidStack fluid) {
			return fluid.getFluid() == FRFluids.yellow_paint; 
	    }
	};
	
	public FluidTank red_paint = new FluidTank(FILLER_TANK_CAP) {
		@Override
		public boolean canFillFluidType(FluidStack fluid) {
			return fluid.getFluid() == FRFluids.red_paint;
	    }
	};
	
	public PaintFillerStackHandler interactable_inv = new PaintFillerStackHandler(inventory, hasCapability(CapabilityEnergy.ENERGY, null));
	
	public Container createContainer(EntityPlayer player) {
		return new PaintFillerContainer(player.inventory, this, false);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return getCapability(capability, facing) != null;
        }
		return super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(interactable_inv);
		}
		
		return super.getCapability(capability, facing);
	}
	
	int lastWhite = 0;
	int lastYellow = 0;
	int lastRed = 0;
	
	@Override
	public void update() {
		if (fuel_remaining > 0) {
			fuel_remaining--;
		} else if (fuel_remaining <= 0) {
			if (!inventory.getStackInSlot(PaintFillerContainer.FUEL).isEmpty()) {
				fuel_remaining = TileEntityFurnace.getItemBurnTime(inventory.getStackInSlot(PaintFillerContainer.FUEL));
				last_fuel_cap = fuel_remaining;
				if (inventory.getStackInSlot(PaintFillerContainer.FUEL).getItem() == Items.LAVA_BUCKET) {
					inventory.setStackInSlot(PaintFillerContainer.FUEL, new ItemStack(Items.BUCKET));
				} else {
					inventory.extractItem(PaintFillerContainer.FUEL, 1, false);
				}
				sendUpdates();
			} else {
				timerCount = 0;
			}
		}
		
		if (timerCount < RoadsConfig.machine.fillerTickRate && fuel_remaining > 0) {
			if (shouldTick()) {
				timerCount++;
			} else {
				timerCount = 0;
			}
		} else {
			process();
			timerCount = 0;
		}
		
		if (!world.isRemote) {
			if (lastWhite != white_paint.getFluidAmount() || lastYellow != yellow_paint.getFluidAmount() || lastRed != red_paint.getFluidAmount()) {
				lastWhite = white_paint.getFluidAmount();
				lastYellow = yellow_paint.getFluidAmount();
				lastRed = red_paint.getFluidAmount();
				
				sendUpdates();
			}
		}
	}
	
	public void process() {
		FurenikusRoads.debug(2, "Paint Filler at" + formatPosition(pos) + "processing");
		if (!world.isRemote) {
			int paintPerDye = RoadsConfig.machine.fillerPaintPerDye;
			boolean hasChanges = false;
			
			if ((!inventory.getStackInSlot(PaintFillerContainer.WHITE_DYE).isEmpty() || !inventory.getStackInSlot(PaintFillerContainer.YELLOW_DYE).isEmpty() || !inventory.getStackInSlot(PaintFillerContainer.RED_DYE).isEmpty())) {
				if (isDye(inventory.getStackInSlot(PaintFillerContainer.WHITE_DYE), "dyeWhite") && white_paint.getFluidAmount() + paintPerDye <= white_paint.getCapacity()) {
					FurenikusRoads.debug(2, "Paint Filler: Create white paint");
					inventory.extractItem(PaintFillerContainer.WHITE_DYE, 1, false);
					white_paint.fill(new FluidStack(FRFluids.white_paint, paintPerDye), true);
					hasChanges = true;
				}

				if (isDye(inventory.getStackInSlot(PaintFillerContainer.YELLOW_DYE), "dyeYellow") && yellow_paint.getFluidAmount() + paintPerDye <= yellow_paint.getCapacity()) {
					FurenikusRoads.debug(2, "Paint Filler: Create yellow paint");
					inventory.extractItem(PaintFillerContainer.YELLOW_DYE, 1, false);
					yellow_paint.fill(new FluidStack(FRFluids.yellow_paint, paintPerDye), true);
					hasChanges = true;
				}
			
				if (isDye(inventory.getStackInSlot(PaintFillerContainer.RED_DYE), "dyeRed") && red_paint.getFluidAmount() + paintPerDye <= red_paint.getCapacity()) {
					FurenikusRoads.debug(2, "Paint Filler: Create red paint");
					inventory.extractItem(PaintFillerContainer.RED_DYE, 1, false);
					red_paint.fill(new FluidStack(FRFluids.red_paint, paintPerDye), true);
					hasChanges = true;
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
	}
	
	public boolean shouldTick() {
		int paintPerDye = RoadsConfig.machine.fillerPaintPerDye;
		
		if ((!inventory.getStackInSlot(PaintFillerContainer.WHITE_DYE).isEmpty() || !inventory.getStackInSlot(PaintFillerContainer.YELLOW_DYE).isEmpty() || !inventory.getStackInSlot(PaintFillerContainer.RED_DYE).isEmpty()) && (fuel_remaining > 0 || hasCapability(CapabilityEnergy.ENERGY, null))) {
			if (isDye(inventory.getStackInSlot(PaintFillerContainer.WHITE_DYE), "dyeWhite") && white_paint.getFluidAmount() + paintPerDye <= white_paint.getCapacity()) {
				return true;
			}

			if (isDye(inventory.getStackInSlot(PaintFillerContainer.YELLOW_DYE), "dyeYellow") && yellow_paint.getFluidAmount() + paintPerDye <= yellow_paint.getCapacity()) {
				return true;
			}

			if (isDye(inventory.getStackInSlot(PaintFillerContainer.RED_DYE), "dyeRed") && red_paint.getFluidAmount() + paintPerDye <= red_paint.getCapacity()) {
				return true;
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
		fuel_remaining = nbt.getInteger("fuel");
		last_fuel_cap = nbt.getInteger("fuel_last_used");
		
		gun_white = nbt.getInteger("gun_white");
		gun_yellow = nbt.getInteger("gun_yellow");
		gun_red = nbt.getInteger("gun_red");
		
		if (gun_white > GUN_TANK_CAP) { gun_white = GUN_TANK_CAP; }
		if (gun_yellow > GUN_TANK_CAP) { gun_yellow = GUN_TANK_CAP; }
		if (gun_red > GUN_TANK_CAP) { gun_red = GUN_TANK_CAP; }
		
		if (nbt.hasKey("items")) {
			inventory.deserializeNBT((NBTTagCompound) nbt.getTag("items"));
		}
		if (nbt.hasKey("paint_white")) {
			white_paint.readFromNBT((NBTTagCompound) nbt.getTag("paint_white"));
		}
		if (nbt.hasKey("paint_yellow")) {
			yellow_paint.readFromNBT((NBTTagCompound) nbt.getTag("paint_yellow"));
		}
		if (nbt.hasKey("paint_red")) {
			red_paint.readFromNBT((NBTTagCompound) nbt.getTag("paint_red"));
		}
	}
	
	@Override
	public NBTTagCompound writeNBT(NBTTagCompound nbt) {
		nbt.setBoolean("has_gun", has_gun);
		nbt.setInteger("fuel", fuel_remaining);
		nbt.setInteger("fuel_last_used", last_fuel_cap);
		
		nbt.setTag("paint_white", white_paint.writeToNBT(new NBTTagCompound()));
		nbt.setTag("paint_yellow", yellow_paint.writeToNBT(new NBTTagCompound()));
		nbt.setTag("paint_red", red_paint.writeToNBT(new NBTTagCompound()));
		
		nbt.setInteger("gun_white", gun_white);
		nbt.setInteger("gun_yellow", gun_yellow);
		nbt.setInteger("gun_red", gun_red);
		
		nbt.setTag("items", inventory.serializeNBT());
		return nbt;
	}
}
