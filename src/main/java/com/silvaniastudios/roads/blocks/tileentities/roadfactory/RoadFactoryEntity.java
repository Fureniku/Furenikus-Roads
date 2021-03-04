package com.silvaniastudios.roads.blocks.tileentities.roadfactory;

import javax.annotation.Nonnull;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.tileentities.RoadTileEntity;
import com.silvaniastudios.roads.blocks.tileentities.recipes.RecipeRegistry;
import com.silvaniastudios.roads.blocks.tileentities.recipes.RoadFactoryRecipes;
import com.silvaniastudios.roads.fluids.FRFluids;

import net.minecraft.block.state.IBlockState;
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
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class RoadFactoryEntity extends RoadTileEntity implements ITickable, ICapabilityProvider {
	
	public int timerCount = 0;
	public int fillCheckTick = 0;
	
	public int previousFill = 0;
	public boolean isFilling = false;
	
	public static final int TANK_CAP = 320000;

	public RoadFactoryEntity() {}
	
	public Container createContainer(EntityPlayer player) {
		return new RoadFactoryContainer(player.inventory, this, false);
	}
	
	public ItemStackHandler inventory = new ItemStackHandler(12) {
		@Override
		public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
			return true;
		}
		
		@Override
		protected void onContentsChanged(int slot) {
			RoadFactoryEntity.this.markDirty();
		}
	};
	
	public RoadFactoryStackHandler interactable_inv = new RoadFactoryStackHandler(inventory, hasCapability(CapabilityEnergy.ENERGY, null));
	
	public FluidTank tarFluid = new FluidTank(TANK_CAP) {
		@Override
		public boolean canFillFluidType(FluidStack fluid) {
			for (int i = 0; i < RoadsConfig.general.tarAlternatives.length; i++) {
				if (RoadsConfig.general.tarAlternatives[i].equalsIgnoreCase(fluid.getFluid().getName())) {
					return true;
				}
			}
			
	        return fluid.getFluid() == FRFluids.tar;
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
			
			IBlockState state = world.getBlockState(pos);
			if (state.getBlock() instanceof RoadFactoryBlock) {
				RoadFactoryBlock block = (RoadFactoryBlock) state.getBlock();
				int meta = block.getMetaFromState(state);
				
				if (meta == 1) { sideLeft = EnumFacing.NORTH; }
				if (meta == 2) { sideLeft = EnumFacing.EAST;  }
				if (meta == 3) { sideLeft = EnumFacing.SOUTH; }
				
				if (facing == sideLeft || facing == EnumFacing.UP) {
					return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(tarFluid);
				}
			}
		}
		return super.getCapability(capability, facing);
	}
	
	int lastTar = 0;
	
	@Override
	public void update() {
		renderUpdate();
		
		if (fuel_remaining > 0) {
			fuel_remaining--;
		} else if (fuel_remaining <= 0) {
			ItemStack fuel = inventory.getStackInSlot(RoadFactoryContainer.FUEL);
			if (!fuel.isEmpty()) {
				fuel_remaining = TileEntityFurnace.getItemBurnTime(fuel);
				last_fuel_cap = fuel_remaining;
				if (inventory.getStackInSlot(RoadFactoryContainer.FUEL).getItem() == Items.LAVA_BUCKET) {
					inventory.setStackInSlot(RoadFactoryContainer.FUEL, new ItemStack(Items.BUCKET));
				} else {
					inventory.extractItem(RoadFactoryContainer.FUEL, 1, false);
				}
				sendUpdates();
			} else {
				timerCount = 0;
				return;
			}
		}
		if (timerCount < RoadsConfig.machine.roadFactoryTickRate) {
			if (shouldTick()) {
				timerCount++;
				fillCheckTick = timerCount;
			} else {
				timerCount = 0;
			}
		} else {
			process();
			timerCount = 0;
		}
		
		if (!world.isRemote) {
			if (lastTar != tarFluid.getFluidAmount()) {
				lastTar = tarFluid.getFluidAmount();
				sendUpdates();
			}
		}
	}
	
	public void extract(int iterations) {
		for (int i = 0; i < iterations; i++) {
			if (inventory.extractItem(RoadFactoryContainer.INPUT_1, 8, false) == ItemStack.EMPTY) {
				if (inventory.extractItem(RoadFactoryContainer.INPUT_2, 8, false) == ItemStack.EMPTY) {
					if (inventory.extractItem(RoadFactoryContainer.INPUT_3, 8, false) == ItemStack.EMPTY) {
						if (inventory.extractItem(RoadFactoryContainer.INPUT_4, 8, false) == ItemStack.EMPTY) {
							FurenikusRoads.debug(0, "WARNING! A road factory is creating free resources. Please report to Fureniku https://discord.gg/BPzpQk2");
						}
					}
				}
			}
		}
	}
	
	public void process() {
		FurenikusRoads.debug(2, "Road Factory at" + formatPosition(pos) + "processing");
		if (!world.isRemote) {
			boolean hasChanges = false;
			ItemStack result = getRecipeResult();
			if (result != ItemStack.EMPTY && tarFluid.getFluidAmount() >= 100 * result.getCount()/8) {
				if (result.getCount() == 8) {
					extract(1);
				} else if (result.getCount() == 16) {
					extract(2);
				} else if (result.getCount() == 24) {
					extract(3);
				} else if (result.getCount() == 32) {
					extract(4);
				}
				
				putItemsInSlot(result, false);

				hasChanges = true;
				tarFluid.drain(new FluidStack(tarFluid.getFluid().getFluid(), 100 * result.getCount()/8), true);
			}
			
			ItemStack fluid_in = inventory.getStackInSlot(RoadFactoryContainer.FLUID_IN);
			ItemStack bucket_out = inventory.getStackInSlot(RoadFactoryContainer.FLUID_IN_BUCKET);

			if (fluid_in.getUnlocalizedName().compareTo("item.forge.bucketFilled") == 0) {
				FluidStack fluidStack = FluidUtil.getFluidContained(fluid_in);
				for (int i = 0; i < RecipeRegistry.tar.size(); i++) {
					if (RecipeRegistry.tar.get(i).getFluid() == fluidStack.getFluid() && tarFluid.fill(fluidStack, false) == fluidStack.amount) {
						if (tarFluid.getFluidAmount() <= TANK_CAP - fluidStack.amount && (bucket_out.isEmpty() || bucket_out.getCount() < bucket_out.getMaxStackSize())) {
							tarFluid.fill(fluidStack, true);
							inventory.setStackInSlot(RoadFactoryContainer.FLUID_IN, ItemStack.EMPTY);
							if (bucket_out.isEmpty()) {//TODO this is bad. Or is it? We only support buckets rn. Change if we support other fluid containers.
								inventory.setStackInSlot(RoadFactoryContainer.FLUID_IN_BUCKET, new ItemStack(Items.BUCKET));
							} else {
								bucket_out.setCount(bucket_out.getCount() + 1);
							}
							hasChanges = true;
						}
						break;
					}
				}
			}
			if (hasChanges) { sendUpdates(); }
		}
	}
	
	public void renderUpdate() {
		if (world.isRemote) {
			if (fillCheckTick < RoadsConfig.machine.roadFactoryTickRate) {
				fillCheckTick++;
			} else {
				if (previousFill < tarFluid.getFluidAmount()) {
					previousFill = tarFluid.getFluidAmount();
					isFilling = true;
				} else {
					isFilling = false;
				}
				fillCheckTick = 0;
			}
		}
	}
	
	public boolean shouldTick() {
		if (tarFluid.getFluidAmount() >= 100 && getRecipeResult() != ItemStack.EMPTY) {
			return true;
		}
		
		ItemStack fluid_in = inventory.getStackInSlot(RoadFactoryContainer.FLUID_IN);
		ItemStack bucket_out = inventory.getStackInSlot(RoadFactoryContainer.FLUID_IN_BUCKET);

		if (fluid_in.getUnlocalizedName().compareTo("item.forge.bucketFilled") == 0) {
			if (tarFluid.getFluidAmount() <= TANK_CAP - 100 && (bucket_out.isEmpty() || bucket_out.getCount() < bucket_out.getMaxStackSize())) {
				return true;
			}
		}
		return false;
	}
	
	public ItemStack getRecipeResult() {
		for (int i = 0; i < RecipeRegistry.roadFactoryRecipes.size(); i++) {
			RoadFactoryRecipes recipe = RecipeRegistry.roadFactoryRecipes.get(i);
			ItemStack out = recipe.getCraftingResult(inventory);
			
			ItemStack sim = out.copy();
			
			if (out != ItemStack.EMPTY) {
				sim = inventory.insertItem(RoadFactoryContainer.OUTPUT_1, sim, true);
				if (sim != ItemStack.EMPTY) { sim = inventory.insertItem(RoadFactoryContainer.OUTPUT_2, sim, true); }
				if (sim != ItemStack.EMPTY) { sim = inventory.insertItem(RoadFactoryContainer.OUTPUT_3, sim, true); }
				if (sim != ItemStack.EMPTY) { sim = inventory.insertItem(RoadFactoryContainer.OUTPUT_4, sim, true); }
				if (sim == ItemStack.EMPTY) {
					return out;
				}
			}
		}
		return ItemStack.EMPTY;
	}
	
	public void putItemsInSlot(ItemStack stack, boolean simulate) {
		if (stack != ItemStack.EMPTY) {
			stack = inventory.insertItem(RoadFactoryContainer.OUTPUT_1, stack, false);
		}
		
		if (stack != ItemStack.EMPTY) {
			stack = inventory.insertItem(RoadFactoryContainer.OUTPUT_2, stack, false);
		}
		
		if (stack != ItemStack.EMPTY) {
			stack = inventory.insertItem(RoadFactoryContainer.OUTPUT_3, stack, false);
		}
		
		if (stack != ItemStack.EMPTY) {
			stack = inventory.insertItem(RoadFactoryContainer.OUTPUT_4, stack, false);
		}
	}
	
	@Override
	public void readNBT(NBTTagCompound nbt) {
		if (nbt.hasKey("items")) {
			inventory.deserializeNBT((NBTTagCompound) nbt.getTag("items"));
		}
		
		fuel_remaining = nbt.getInteger("fuel");
		last_fuel_cap = nbt.getInteger("fuel_last_used");
		
		tarFluid.readFromNBT(nbt);
	}
	
	@Override
	public NBTTagCompound writeNBT(NBTTagCompound nbt) {
		nbt.setTag("items", inventory.serializeNBT());
		nbt.setInteger("fuel", fuel_remaining);
		nbt.setInteger("fuel_last_used", last_fuel_cap);
		
		tarFluid.writeToNBT(nbt);
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
