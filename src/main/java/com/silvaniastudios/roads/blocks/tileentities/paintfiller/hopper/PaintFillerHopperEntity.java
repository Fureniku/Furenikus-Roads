package com.silvaniastudios.roads.blocks.tileentities.paintfiller.hopper;

import javax.annotation.Nonnull;

import com.silvaniastudios.roads.blocks.tileentities.RoadTileEntity;
import com.silvaniastudios.roads.blocks.tileentities.paintfiller.PaintFillerContainer;
import com.silvaniastudios.roads.blocks.tileentities.paintfiller.PaintFillerEntity;
import com.silvaniastudios.roads.fluids.FRFluids;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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

public class PaintFillerHopperEntity extends RoadTileEntity implements ITickable, ICapabilityProvider {
	
	private int up;
	private int north;
	private int east;
	private int south;
	private int west;
	
	private boolean is_output;
	
	public static final int FILLER_TANK_CAP = 32000;
	
	public ItemStackHandler inventory = new ItemStackHandler(5) {
		
		@Override
		public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
			if ((slot == 0 && isDye(stack, "dyeWhite")) ||
					(slot == 1 && isDye(stack, "dyeYellow")) ||
					(slot == 2 && isDye(stack, "dyeRed"))) {
				return true;
			}
			return false;
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
	
	public PaintFillerHopperStackHandler interactable_inv = new PaintFillerHopperStackHandler(inventory, hasCapability(CapabilityEnergy.ENERGY, null));
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (getIOFromSide(facing) == PaintFillerHopperBlock.EnumIO.item) {
				return true;
			}
		}
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			if (getIOFromSide(facing) == PaintFillerHopperBlock.EnumIO.white
					|| getIOFromSide(facing) == PaintFillerHopperBlock.EnumIO.yellow
					|| getIOFromSide(facing) == PaintFillerHopperBlock.EnumIO.red) {
				return true;
			}
		}
		return super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (getIOFromSide(facing) == PaintFillerHopperBlock.EnumIO.item) {
				return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(interactable_inv);
			}
		}
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			if (getIOFromSide(facing) == PaintFillerHopperBlock.EnumIO.white) {
				return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(white_paint);
			}
			if (getIOFromSide(facing) == PaintFillerHopperBlock.EnumIO.yellow) {
				return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(yellow_paint);
			}
			if (getIOFromSide(facing) == PaintFillerHopperBlock.EnumIO.red) {
				return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(red_paint);
			}
		}
		
		return super.getCapability(capability, facing);
	}
	
	public void updateSide(EnumFacing facing) {
		if (facing == EnumFacing.UP) {
			if (up < 4) {
				up++;
			} else {
				up = 0;
			}
		}
		if (facing == EnumFacing.NORTH) {
			if (north < 4) {
				north++;
			} else {
				north = 0;
			}
		}
		if (facing == EnumFacing.EAST) {
			if (east < 4) {
				east++;
			} else {
				east = 0;
			}
		}
		if (facing == EnumFacing.SOUTH) {
			if (south < 4) {
				south++;
			} else {
				south = 0;
			}
		}
		if (facing == EnumFacing.WEST) {
			if (west < 4) {
				west++;
			} else {
				west = 0;
			}
		}
		sendUpdates();
	}

	int lastWhite = 0;
	int lastYellow = 0;
	int lastRed = 0;
	
	@Override
	public void update() {
		if (!world.isRemote) {
			if (world.getTileEntity(pos.offset(EnumFacing.DOWN)) instanceof PaintFillerEntity) {
				PaintFillerEntity te = (PaintFillerEntity) world.getTileEntity(pos.offset(EnumFacing.DOWN));
				int amt = 50;
				
				if (is_output) {
					if (te.white_paint.getFluidAmount() + amt <= PaintFillerEntity.FILLER_TANK_CAP && white_paint.getFluidAmount() >= amt) {
						te.white_paint.fill(white_paint.drain(50, true), true);
					}
					
					if (te.yellow_paint.getFluidAmount() + amt <= PaintFillerEntity.FILLER_TANK_CAP && yellow_paint.getFluidAmount() >= amt) {
						te.yellow_paint.fill(yellow_paint.drain(50, true), true);
					}
					
					if (te.red_paint.getFluidAmount() + amt <= PaintFillerEntity.FILLER_TANK_CAP && red_paint.getFluidAmount() >= amt) {
						te.red_paint.fill(red_paint.drain(50, true), true);
					}
					
					inventory.setStackInSlot(0, te.inventory.insertItem(PaintFillerContainer.WHITE_DYE, inventory.getStackInSlot(0), false));
					inventory.setStackInSlot(1, te.inventory.insertItem(PaintFillerContainer.YELLOW_DYE, inventory.getStackInSlot(1), false));
					inventory.setStackInSlot(2, te.inventory.insertItem(PaintFillerContainer.RED_DYE, inventory.getStackInSlot(2), false));
				} else {
					if (white_paint.getFluidAmount() + amt <= FILLER_TANK_CAP && te.white_paint.getFluidAmount() >= amt) {
						white_paint.fill(te.white_paint.drain(50, true), true);
					}
					
					if (yellow_paint.getFluidAmount() + amt <= FILLER_TANK_CAP && te.yellow_paint.getFluidAmount() >= amt) {
						yellow_paint.fill(te.yellow_paint.drain(50, true), true);
					}
					
					if (red_paint.getFluidAmount() + amt <= FILLER_TANK_CAP && te.red_paint.getFluidAmount() >= amt) {
						red_paint.fill(te.red_paint.drain(50, true), true);
					}
					
					te.inventory.setStackInSlot(PaintFillerContainer.WHITE_DYE, te.inventory.insertItem(0, te.inventory.getStackInSlot(PaintFillerContainer.WHITE_DYE), false));
					te.inventory.setStackInSlot(PaintFillerContainer.YELLOW_DYE, te.inventory.insertItem(1, te.inventory.getStackInSlot(PaintFillerContainer.YELLOW_DYE), false));
					te.inventory.setStackInSlot(PaintFillerContainer.RED_DYE, te.inventory.insertItem(2, te.inventory.getStackInSlot(PaintFillerContainer.RED_DYE), false));
				}
			}
			
			
			//Sync updates when it fills
			if (lastWhite != white_paint.getFluidAmount() || lastYellow != yellow_paint.getFluidAmount() || lastRed != red_paint.getFluidAmount()) {
				lastWhite = white_paint.getFluidAmount();
				lastYellow = yellow_paint.getFluidAmount();
				lastRed = red_paint.getFluidAmount();
				
				sendUpdates();
			}
		}
	}
	
	public int getSideUp() {
		return up;
	}
	
	public int getSideNorth() {
		return north;
	}
	
	public int getSideEast() {
		return east;
	}
	
	public int getSideSouth() {
		return south;
	}
	
	public int getSideWest() {
		return west;
	}
	
	public boolean getOutput() {
		return is_output;
	}
	
	public void toggleOutputMode() {
		is_output = !is_output;
	}
	
	public PaintFillerHopperBlock.EnumIO getIOFromSide(EnumFacing facing) {
		int dir = 4;
		if (facing == EnumFacing.UP)    { dir = up;    }
		if (facing == EnumFacing.NORTH) { dir = north; }
		if (facing == EnumFacing.EAST)  { dir = east;  }
		if (facing == EnumFacing.SOUTH) { dir = south; }
		if (facing == EnumFacing.WEST)  { dir = west;  }
		
		return PaintFillerHopperBlock.EnumIO.byId(dir);
	}
	
	@Override
	public void readNBT(NBTTagCompound nbt) {
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
		
		up = nbt.getInteger("up");
		north = nbt.getInteger("north");
		east = nbt.getInteger("east");
		south = nbt.getInteger("south");
		west = nbt.getInteger("west");
		
		is_output = nbt.getBoolean("is_output");
	}
	
	@Override
	public NBTTagCompound writeNBT(NBTTagCompound nbt) {
		nbt.setTag("paint_white", white_paint.writeToNBT(new NBTTagCompound()));
		nbt.setTag("paint_yellow", yellow_paint.writeToNBT(new NBTTagCompound()));
		nbt.setTag("paint_red", red_paint.writeToNBT(new NBTTagCompound()));
		
		nbt.setTag("items", inventory.serializeNBT());
		
		nbt.setInteger("up", up);
		nbt.setInteger("north", north);
		nbt.setInteger("east", east);
		nbt.setInteger("south", south);
		nbt.setInteger("west", west);
		
		nbt.setBoolean("is_output", is_output);
		return nbt;
	}
}
