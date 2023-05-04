package com.silvaniastudios.roads.blocks.tileentities.paintoven;

import javax.annotation.Nonnull;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.tileentities.RoadTileEntity;
import com.silvaniastudios.roads.fluids.FRFluids;

import net.minecraft.init.Items;
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
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class PaintOvenEntity extends RoadTileEntity implements ITickable, ICapabilityProvider {
	
	public static final int FILLER_TANK_CAP = 64000;
	public int timerCount = 0;
	
	public ItemStackHandler inventory = new ItemStackHandler(2) {
		@Override
		public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
			if (isDye(stack, "dyeWhite") || isDye(stack, "dyeYellow") || isDye(stack, "dyeRed")) {
				return true;
			}
			return false;
		}
	};
	
	public FluidTank water = new FluidTank(FILLER_TANK_CAP) {
		@Override
		public boolean canFillFluidType(FluidStack fluid) {
			return fluid.getFluid().getName().equalsIgnoreCase("water");
	    }
	};
	
	public FluidTank paint = new FluidTank(FILLER_TANK_CAP) {
		@Override
		public boolean canFillFluidType(FluidStack fluid) {
			return fluid.getFluid() == FRFluids.white_paint || fluid.getFluid() == FRFluids.yellow_paint || fluid.getFluid() == FRFluids.red_paint;
	    }
	};
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return getCapability(capability, facing) != null;
        }
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			return getCapability(capability, facing) != null;
		}
		return super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory);
		}
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			if (facing == EnumFacing.UP || facing == EnumFacing.DOWN) { 
				return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(water);
			} else {
				return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(paint);
			}
		}
		
		return super.getCapability(capability, facing);
	}
	
	int lastPaint = 0;
	int lastWater = 0;
	
	@Override
	public void update() {
		if (fuel_remaining > 0) {
			fuel_remaining--;
		} else if (fuel_remaining <= 0) {
			if (!inventory.getStackInSlot(PaintOvenContainer.FUEL).isEmpty()) {
				fuel_remaining = TileEntityFurnace.getItemBurnTime(inventory.getStackInSlot(PaintOvenContainer.FUEL));
				last_fuel_cap = fuel_remaining;
				if (inventory.getStackInSlot(PaintOvenContainer.FUEL).getItem() == Items.LAVA_BUCKET) {
					inventory.setStackInSlot(PaintOvenContainer.FUEL, new ItemStack(Items.BUCKET));
				} else {
					inventory.extractItem(PaintOvenContainer.FUEL, 1, false);
				}
				sendUpdates();
			} else {
				timerCount = 0;
			}
		}
		
		if (timerCount < RoadsConfig.machine.paintOvenTickRate && fuel_remaining > 0) {
			if (shouldTick()) {
				timerCount++;
			} else {
				timerCount = 0;
			}
		} else {
			if (fuel_remaining > 0) {
				process();
			}
			timerCount = 0;
		}
		
		if (!world.isRemote) {
			if (lastPaint != paint.getFluidAmount() || lastWater != water.getFluidAmount()) {
				lastPaint = paint.getFluidAmount();
				lastWater = water.getFluidAmount();
				
				sendUpdates();
			}
		}
	}
	
	public void process() {
		FurenikusRoads.debug(2, "Paint Oven at" + formatPosition(pos) + "processing");
		if (!world.isRemote) {
			if (water.getFluidAmount() >= 1000 && paint.getFluidAmount() + 1000 <= paint.getCapacity()) {
				int dyeCol = -1;
				boolean filled = false;
				ItemStack dye = inventory.getStackInSlot(PaintOvenContainer.DYE);
				
				if (isDye(dye, "dyeWhite")) { dyeCol = 0; }
				if (isDye(dye, "dyeYellow")) { dyeCol = 1; }
				if (isDye(dye, "dyeRed")) { dyeCol = 2; }
				
				if (paint.getFluidAmount() > 0) {
					if (dyeCol == 0 && paint.getFluid().getFluid() == FRFluids.white_paint) {
						paint.fill(new FluidStack(FRFluids.white_paint, 1000), true);
						filled = true;
					} else if (dyeCol == 1 && paint.getFluid().getFluid() == FRFluids.yellow_paint) {
						paint.fill(new FluidStack(FRFluids.yellow_paint, 1000), true);
						filled = true;
					} else if (dyeCol == 2 && paint.getFluid().getFluid() == FRFluids.red_paint) {
						paint.fill(new FluidStack(FRFluids.red_paint, 1000), true);
						filled = true;
					}
				} else {
					if (dyeCol == 0) {
						paint.fill(new FluidStack(FRFluids.white_paint, 1000), true);
						filled = true;
					}
					if (dyeCol == 1) {
						paint.fill(new FluidStack(FRFluids.yellow_paint, 1000), true);
						filled = true;
					}
					if (dyeCol == 2) {
						paint.fill(new FluidStack(FRFluids.red_paint, 1000), true);
						filled = true;
					}
				}
				if (filled) {
					water.drain(1000, true);
					inventory.extractItem(PaintOvenContainer.DYE, 1, false);
				}
			}
			sendUpdates();
		}
	}
	
	public boolean shouldTick() {
		if (water.getFluidAmount() >= 1000 &&
				(fuel_remaining > 0 || hasCapability(CapabilityEnergy.ENERGY, null)) &&
				inventory.getStackInSlot(PaintOvenContainer.DYE).getCount() >= 1 &&
				paint.getFluidAmount() + 1000 <= paint.getCapacity()) {
			return true;
		}
		return false;
	}
	
	@Override
	public void readNBT(NBTTagCompound nbt) {
		fuel_remaining = nbt.getInteger("fuel");
		last_fuel_cap = nbt.getInteger("fuel_last_used");
		
		if (nbt.hasKey("items")) {
			inventory.deserializeNBT((NBTTagCompound) nbt.getTag("items"));
		}
		if (nbt.hasKey("water")) {
			water.readFromNBT((NBTTagCompound) nbt.getTag("water"));
		}
		if (nbt.hasKey("paint")) {
			paint.readFromNBT((NBTTagCompound) nbt.getTag("paint"));
		}
	}
	
	@Override
	public NBTTagCompound writeNBT(NBTTagCompound nbt) {
		nbt.setInteger("fuel", fuel_remaining);
		nbt.setInteger("fuel_last_used", last_fuel_cap);

		nbt.setTag("items", inventory.serializeNBT());
		nbt.setTag("water", water.writeToNBT(new NBTTagCompound()));
		nbt.setTag("paint", paint.writeToNBT(new NBTTagCompound()));

		return nbt;
	}

}
