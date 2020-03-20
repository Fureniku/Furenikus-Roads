package com.silvaniastudios.roads.blocks.tileentities.distiller;

import javax.annotation.Nonnull;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.tileentities.RoadTileEntity;
import com.silvaniastudios.roads.blocks.tileentities.recipes.RecipeRegistry;
import com.silvaniastudios.roads.blocks.tileentities.recipes.TarDistillerRecipes;
import com.silvaniastudios.roads.blocks.tileentities.roadfactory.RoadFactoryBlock;
import com.silvaniastudios.roads.blocks.tileentities.roadfactory.RoadFactoryEntity;
import com.silvaniastudios.roads.fluids.FRFluids;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TarDistillerEntity extends RoadTileEntity implements ITickable, ICapabilityProvider {
	
	public int last_fuel_cap = 20000;
	public int timerCount = 0;
	
	public static final int TANK_CAP = 320000;

	public TarDistillerEntity() {}
	
	public Container createContainer(EntityPlayer player) {
		return new TarDistillerContainer(player.inventory, this, false);
	}
	
	public ItemStackHandler inventory = new ItemStackHandler(10) {
		
		@Override
		public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
			return true;
		}
		
		@Override
		protected void onContentsChanged(int slot) {
			TarDistillerEntity.this.markDirty();
		}
	};
	
	public TarDistillerStackHandler interactable_inv = new TarDistillerStackHandler(inventory, hasCapability(CapabilityEnergy.ENERGY, null));
	
	public FluidTank fluidInput = new FluidTank(TANK_CAP) {
		@Override
		public boolean canFillFluidType(FluidStack fluid) {
	        return true;
	    }
	};
	
	public FluidTank fluidOutput1 = new FluidTank(TANK_CAP) {
		@Override
		public boolean canFillFluidType(FluidStack fluid) {
	        return fluid.getFluid() == FRFluids.tar;
	    }
	};
	
	public FluidTank fluidOutput2 = new FluidTank(TANK_CAP) {
		@Override
		public boolean canFillFluidType(FluidStack fluid) {
	        return canFill();
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
			if (facing != null) {
				return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(interactable_inv);
			} else {
				return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory);
			}
		}
		
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			EnumFacing sideLeft = EnumFacing.WEST;
			EnumFacing sideRight = EnumFacing.EAST;
			EnumFacing sideUp = EnumFacing.UP;
			EnumFacing sideDown = EnumFacing.DOWN;
			
			IBlockState state = world.getBlockState(pos);
			if (state.getBlock() instanceof TarDistillerBlock) {
				TarDistillerBlock block = (TarDistillerBlock) state.getBlock();
				int meta = block.getMetaFromState(state);
				
				if (meta == 1) {
					sideLeft = EnumFacing.NORTH;
					sideRight = EnumFacing.SOUTH;
				}
				if (meta == 2) {
					sideLeft = EnumFacing.EAST;
					sideRight = EnumFacing.WEST;
				}
				if (meta == 3) {
					sideLeft = EnumFacing.SOUTH;
					sideRight = EnumFacing.NORTH;
				}
				
				if (fluidOutput1.getFluidAmount() > 0) {
					if (facing == sideRight || facing == sideDown)  { return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(fluidOutput1); }
				}
				if (facing == sideRight || facing == sideDown) { return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(fluidOutput2); }
				
				if (facing == sideLeft || facing == sideUp)  { return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(fluidInput); }
			}
		}
		
		return super.getCapability(capability, facing);
	}
	
	int lastFluidInput = 0;
	int lastFluidOutput1 = 0;
	int lastFluidOutput2 = 0;
	
	@Override
	public void update() {
		if (fuel_remaining > 0) {
			fuel_remaining--;
		} else if (fuel_remaining <= 0) {
			if (inventory.getStackInSlot(TarDistillerContainer.FUEL).getItem() == Items.LAVA_BUCKET) {
				fuel_remaining = 20000;
				inventory.setStackInSlot(TarDistillerContainer.FUEL, new ItemStack(Items.BUCKET));
				sendUpdates();
			} else {
				timerCount = 0;
				return;
			}
		}
		
		if (fuel_remaining > 0) {
			transferTar();
		}
		
		if (timerCount < RoadsConfig.machine.tarDistillerTickRate) {
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
			if (lastFluidInput != fluidInput.getFluidAmount() || lastFluidOutput1 != fluidOutput1.getFluidAmount() || lastFluidOutput2 != fluidOutput2.getFluidAmount()) {
				lastFluidInput = fluidInput.getFluidAmount();
				lastFluidOutput1 = fluidOutput1.getFluidAmount();
				lastFluidOutput2 = fluidOutput2.getFluidAmount();
				sendUpdates();
			}
		}
	}
	
	public void process() {
		FurenikusRoads.debug(2, "Tar Distiller at" + formatPosition(pos) + "processing");
		if (!world.isRemote) {
			boolean hasChanges = false;
			
			ItemStack fluid_in = inventory.getStackInSlot(TarDistillerContainer.FLUID_IN);
			FluidStack fluidStack = null;
			
			
			if (fluid_in.getUnlocalizedName().compareTo("item.forge.bucketFilled") == 0) {
				fluidStack = FluidUtil.getFluidContained(fluid_in);
			} else if (fluid_in.getItem() == Items.WATER_BUCKET) {
				fluidStack = new FluidStack(FluidRegistry.WATER, 1000);
			}
			if (fluidStack != null) {	
				if (fluidInput.getFluid() != null) {
					if (fluidInput.getFluid().getFluid() == fluidStack.getFluid()) {
						if (fluidStack.amount <= fluidInput.getFluidAmount() + fluidStack.amount) {
							fluidInput.fill(fluidStack, true);
						}
					}
				} else {
					fluidInput.fill(fluidStack, true);
				}
			}
			
			ItemStack fluid_out_1 = inventory.getStackInSlot(TarDistillerContainer.FLUID_OUT_1);
			ItemStack fluid_out_1_bucket = inventory.getStackInSlot(TarDistillerContainer.FLUID_OUT_1_BUCKET);
			//Should always be tar. Should.
			if (fluid_out_1_bucket.getItem() == Items.BUCKET && fluidOutput1 != null && fluid_out_1.isEmpty()) {
				if (fluidOutput1.getFluidAmount() >= 1000) {
					Fluid fluid = fluidOutput1.getFluid().getFluid();
					ItemStack bucket = FluidUtil.getFilledBucket(fluidOutput1.getFluid());

					fluidOutput1.drain(new FluidStack(fluid, 1000), true);
					inventory.setStackInSlot(TarDistillerContainer.FLUID_OUT_1, bucket);
					fluid_out_1_bucket.setCount(fluid_out_1_bucket.getCount() - 1);
					hasChanges = true;
				}
			}
			
			ItemStack fluid_out_2 = inventory.getStackInSlot(TarDistillerContainer.FLUID_OUT_2);
			ItemStack fluid_out_2_bucket = inventory.getStackInSlot(TarDistillerContainer.FLUID_OUT_1_BUCKET);
			//Could be anything tbh (although currently its always nothing).
			if (fluid_out_2_bucket.getItem() == Items.BUCKET && fluidOutput2 != null && fluid_out_2.isEmpty()) {
				if (fluidOutput2.getFluidAmount() >= 1000) {
					Fluid fluid = fluidOutput2.getFluid().getFluid();
					ItemStack bucket = FluidUtil.getFilledBucket(fluidOutput2.getFluid());
				
					fluidOutput2.drain(new FluidStack(fluid, 1000), true);
					inventory.setStackInSlot(TarDistillerContainer.FLUID_OUT_2, bucket);
					fluid_out_2_bucket.setCount(fluid_out_2_bucket.getCount() - 1);
					hasChanges = true;
				}
			}
			if (hasChanges) { sendUpdates(); }
			
			for (int i = 0; i < RecipeRegistry.tarDistillerRecipes.size(); i++) {
				TarDistillerRecipes recipe = RecipeRegistry.tarDistillerRecipes.get(i);
				if (recipe.canFullyCraft(inventory, fluidInput, fluidOutput1, fluidOutput2)) {
					recipe.processCrafting(inventory, fluidInput, fluidOutput1, fluidOutput2);
					sendUpdates();
					return;
				}
			}
			
			
			/*boolean hasChanges = false;
			ItemStack input = inventory.getStackInSlot(TarDistillerContainer.INPUT);
			ItemStack output_1 = inventory.getStackInSlot(TarDistillerContainer.OUTPUT_1);
			
			if (validInput(input, null)) {
				ItemStack itemOut1 = output1(input, null);
				FluidStack fluidOut1 = fluidOut1(input, null);
				
				if (output_1.isEmpty() || output_1.getCount() + itemOut1.getCount() <= inventory.getSlotLimit(TarDistillerContainer.OUTPUT_1)) {
					boolean process = false;
					if (fluidOutput1.getFluid() == null) {
						fluidOutput1.setFluid(fluidOut1);
						process = true;
					} else if ((fluidOutput1.getFluidAmount() <= TANK_CAP - fluidOut1.amount && (fluidOutput1.getFluid().getFluid().equals(FRFluids.tar)) || fluidOutput1.getFluidAmount() <= 0)) {
						fluidOutput1.fill(fluidOut1, true);
						process = true;
					}
					
					if (process) {
						inventory.extractItem(TarDistillerContainer.INPUT, 1, false);
						
						if (output_1.isEmpty()) { 
							inventory.setStackInSlot(TarDistillerContainer.OUTPUT_1, itemOut1);
						} else {
							output_1.setCount(output_1.getCount()+itemOut1.getCount());
						}
						hasChanges = true;
					}
				}
			}
			
			ItemStack fluid_in = inventory.getStackInSlot(TarDistillerContainer.FLUID_IN);
			
			if (fluid_in.getUnlocalizedName().compareTo("item.forge.bucketFilled") == 0) {

				//When we support other fluids, this is where we'll import tham.
			}
			
				*/
		}
	}
	
	public boolean transferTar() {
		Block blockHere = world.getBlockState(pos).getBlock();
		EnumFacing getRight = EnumFacing.EAST;
		if (blockHere.getMetaFromState(getState()) == 0) { getRight = EnumFacing.EAST;  }
		if (blockHere.getMetaFromState(getState()) == 1) { getRight = EnumFacing.SOUTH; }
		if (blockHere.getMetaFromState(getState()) == 2) { getRight = EnumFacing.WEST;  }
		if (blockHere.getMetaFromState(getState()) == 3) { getRight = EnumFacing.NORTH; }
		
		IBlockState blockRight = world.getBlockState(pos.offset(getRight));
		if (blockRight.getBlock() instanceof RoadFactoryBlock) {
			if (world.getTileEntity(pos.offset(getRight)) != null) {
				RoadFactoryEntity rfEntity = (RoadFactoryEntity) world.getTileEntity(pos.offset(getRight));
				int transfer = RoadsConfig.machine.tarTransferRate;
				if (fluidOutput1.getFluidAmount() >= transfer) {
					if ((rfEntity.tarFluid.getFluidAmount() + transfer <= rfEntity.tarFluid.getCapacity())) {
						FluidStack fluid = fluidOutput1.drain(new FluidStack(FRFluids.tar, transfer), true);
						rfEntity.tarFluid.fill(fluid, true);
						rfEntity.sendUpdates();
						return true;
					}
				} else if (fluidOutput1.getFluidAmount() > 0) {
					if ((rfEntity.tarFluid.getFluidAmount() + fluidOutput1.getFluidAmount() <= rfEntity.tarFluid.getCapacity())) {
						FluidStack fluid = fluidOutput1.drain(new FluidStack(FRFluids.tar, fluidOutput1.getFluidAmount()), true);
						rfEntity.tarFluid.fill(fluid, true);
						rfEntity.sendUpdates();
						return true;
					}
				}
			}
		}
		return false;
	}

	//Simulate everything but dont actually process. This is to check if it "can" do stuff.
	public boolean shouldTick() {
		for (int i = 0; i < RecipeRegistry.tarDistillerRecipes.size(); i++) {
			TarDistillerRecipes recipe = RecipeRegistry.tarDistillerRecipes.get(i);
			if (recipe.canFullyCraft(inventory, fluidInput, fluidOutput1, fluidOutput2)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void readNBT(NBTTagCompound nbt) {
		if (nbt.hasKey("items")) {
			inventory.deserializeNBT((NBTTagCompound) nbt.getTag("items"));
		}
		if (nbt.hasKey("fluid_in_1")) {
			fluidInput.readFromNBT((NBTTagCompound) nbt.getTag("fluid_in_1"));
		}
		if (nbt.hasKey("fluid_out_1")) {
			fluidOutput1.readFromNBT((NBTTagCompound) nbt.getTag("fluid_out_1"));
		}
		if (nbt.hasKey("fluid_out_2")) {
			fluidOutput2.readFromNBT((NBTTagCompound) nbt.getTag("fluid_out_2"));
		}
		
		fuel_remaining = nbt.getInteger("fuel");
	}
	
	@Override
	public NBTTagCompound writeNBT(NBTTagCompound nbt) {
		nbt.setTag("items", inventory.serializeNBT());

		nbt.setTag("fluid_in_1", fluidInput.writeToNBT(new NBTTagCompound()));
		nbt.setTag("fluid_out_1", fluidOutput1.writeToNBT(new NBTTagCompound()));
		nbt.setTag("fluid_out_2", fluidOutput2.writeToNBT(new NBTTagCompound()));
		
		nbt.setInteger("fuel", fuel_remaining);
		return nbt;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		return writeNBT(nbt);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		readNBT(nbt);
	}
}
