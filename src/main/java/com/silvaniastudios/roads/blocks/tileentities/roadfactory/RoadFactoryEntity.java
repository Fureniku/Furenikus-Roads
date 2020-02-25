package com.silvaniastudios.roads.blocks.tileentities.roadfactory;

import javax.annotation.Nonnull;

import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.tileentities.RoadTileEntity;
import com.silvaniastudios.roads.fluids.FluidTar;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
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
	
	public ItemStackHandler inventory = new ItemStackHandler(11) {
		@Override
		public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
			System.out.println("[INTERNAL INV] Checking if " + stack.getDisplayName() + " is valid for slot " + slot);
			return true;
		}
		
		@Override
		protected void onContentsChanged(int slot) {
			RoadFactoryEntity.this.markDirty();
		}
	};
	
	public RoadFactoryStackHandler interactable_inv = new RoadFactoryStackHandler(inventory);
	
	public FluidTank tarFluid = new FluidTank(TANK_CAP) {
		@Override
		public boolean canFillFluidType(FluidStack fluid) {
	        return fluid.getFluid() instanceof FluidTar;
	    }
	};
	
	@Override
	public void update() {
		//Used for rendering. Only do it on client.
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
			
			if (!world.isRemote) {
				boolean hasChanges = false;
				
				ItemStack input_1 = inventory.getStackInSlot(RoadFactoryContainer.INPUT_1);
				ItemStack input_2 = inventory.getStackInSlot(RoadFactoryContainer.INPUT_2);
				ItemStack input_3 = inventory.getStackInSlot(RoadFactoryContainer.INPUT_3);
				ItemStack input_4 = inventory.getStackInSlot(RoadFactoryContainer.INPUT_4);
				
				if (tarFluid.getFluidAmount() >= 1000) {
					boolean processed = false;
					
					if (input_1.getCount() >=8 && putItemsInSlot(getRecipeResult(input_1), 1, false)) {
						inventory.extractItem(RoadFactoryContainer.INPUT_1, 8, false);
						processed = true;
					}
					
					if (!processed) {
						if (input_2.getCount() >=8 && putItemsInSlot(getRecipeResult(input_2), 2, false)) {
							inventory.extractItem(RoadFactoryContainer.INPUT_2, 8, false);
							processed = true;
						}
					}
					
					if (!processed) {
						if (input_3.getCount() >=8 && putItemsInSlot(getRecipeResult(input_3), 3, false)) {
							inventory.extractItem(RoadFactoryContainer.INPUT_3, 8, false);
							processed = true;
						}
					}
					
					if (!processed) {
						if (input_4.getCount() >=8 && putItemsInSlot(getRecipeResult(input_4), 4, false)) {
							inventory.extractItem(RoadFactoryContainer.INPUT_4, 8, false);
							processed = true;
						}
					}
				
					if (processed) {
						hasChanges = true;
						tarFluid.drain(new FluidStack(tarFluid.getFluid().getFluid(), 1000), true);
					}
				}
				
				ItemStack fluid_in = inventory.getStackInSlot(RoadFactoryContainer.FLUID_IN);
				ItemStack bucket_out = inventory.getStackInSlot(RoadFactoryContainer.FLUID_IN_BUCKET);

				if (fluid_in.getUnlocalizedName().compareTo("item.forge.bucketFilled") == 0) {
					FluidStack fluidStack = FluidUtil.getFluidContained(fluid_in);
					if (tarFluid.getFluidAmount() <= TANK_CAP - 1000 && (bucket_out.isEmpty() || bucket_out.getCount() < bucket_out.getMaxStackSize())) {
						tarFluid.fill(fluidStack, true);
						inventory.setStackInSlot(RoadFactoryContainer.FLUID_IN, ItemStack.EMPTY);
						if (bucket_out.isEmpty()) {
							inventory.setStackInSlot(RoadFactoryContainer.FLUID_IN_BUCKET, new ItemStack(Items.BUCKET));
						} else {
							bucket_out.setCount(bucket_out.getCount() + 1);
						}
						hasChanges = true;
					}
				}
				if (hasChanges) { sendUpdates(); }
			}
			timerCount = 0;
		}
	}
	
	public boolean shouldTick() {		
		ItemStack input_1 = inventory.getStackInSlot(RoadFactoryContainer.INPUT_1);
		ItemStack input_2 = inventory.getStackInSlot(RoadFactoryContainer.INPUT_2);
		ItemStack input_3 = inventory.getStackInSlot(RoadFactoryContainer.INPUT_3);
		ItemStack input_4 = inventory.getStackInSlot(RoadFactoryContainer.INPUT_4);
		
		if (tarFluid.getFluidAmount() >= 1000) {
			if (input_1.getCount() >=8 && putItemsInSlot(getRecipeResult(input_1), 1, true)) { return true; }
			if (input_2.getCount() >=8 && putItemsInSlot(getRecipeResult(input_2), 2, true)) { return true; }
			if (input_3.getCount() >=8 && putItemsInSlot(getRecipeResult(input_3), 3, true)) { return true; }
			if (input_4.getCount() >=8 && putItemsInSlot(getRecipeResult(input_4), 4, true)) { return true; }
		}
		
		ItemStack fluid_in = inventory.getStackInSlot(RoadFactoryContainer.FLUID_IN);
		ItemStack bucket_out = inventory.getStackInSlot(RoadFactoryContainer.FLUID_IN_BUCKET);

		if (fluid_in.getUnlocalizedName().compareTo("item.forge.bucketFilled") == 0) {
			if (tarFluid.getFluidAmount() <= TANK_CAP - 1000 && (bucket_out.isEmpty() || bucket_out.getCount() < bucket_out.getMaxStackSize())) {
				return true;
			}
		}
		return false;
	}
	
	public ItemStack getRecipeResult(ItemStack stack) {
		if (stack.isEmpty() || stack.getCount() < 8) {
			return stack;
		}
		
		if (stack.getItem() instanceof ItemBlock) {
			ItemBlock ib = (ItemBlock) stack.getItem();
			Block block = ib.getBlock();
			int meta = stack.getItemDamage();
			
			if (block == Blocks.STONE) {
				if (meta == 0) { return new ItemStack(FRBlocks.road_block_stone, 8, 15); }
				if (meta == 1) { return new ItemStack(FRBlocks.road_block_pale, 8, 15); } //granite
				if (meta == 3) { return new ItemStack(FRBlocks.road_block_light, 8, 15); } //diorite
				if (meta == 5) { return new ItemStack(FRBlocks.road_block_dark, 8, 15); } //andesite
				if (meta == 6) { return new ItemStack(FRBlocks.road_block_fine, 8, 15); } //polished andesite
			}
			
			if (block == FRBlocks.generic_blocks && meta == 0) { return new ItemStack(FRBlocks.road_block_standard, 8, 15); }
			
			//if (block == Blocks.COBBLESTONE) { return new ItemStack(FRBlocks.road_block_standard, 8, 15); }
			if (block == Blocks.GRASS) { return new ItemStack(FRBlocks.road_block_grass, 8, 15); }
			if (block == Blocks.DIRT) { return new ItemStack(FRBlocks.road_block_dirt, 8, 15); }
			if (block == Blocks.GRAVEL) { return new ItemStack(FRBlocks.road_block_gravel, 8, 15); }
			if (block == Blocks.SAND) { return new ItemStack(FRBlocks.road_block_sand, 8, 15); }
		}
		
		return ItemStack.EMPTY;
	}
	
	public boolean putItemsInSlot(ItemStack stack, int id, boolean simulate) {
		int remain = stack.getCount();
		if (stack.isEmpty()) {
			return false;
		}
		
		ItemStack o1 = inventory.getStackInSlot(RoadFactoryContainer.OUTPUT_1);
		ItemStack o2 = inventory.getStackInSlot(RoadFactoryContainer.OUTPUT_2);
		ItemStack o3 = inventory.getStackInSlot(RoadFactoryContainer.OUTPUT_3);
		ItemStack o4 = inventory.getStackInSlot(RoadFactoryContainer.OUTPUT_4);
		
		if (o1.getItem() == stack.getItem()) {
			if (o1.getCount() <= o1.getMaxStackSize() - remain) {
				if (!simulate) { o1.setCount(o1.getCount() + remain); }
				return true;
			}
		}
		
		if (o1.isEmpty()) {
			if (!simulate) { inventory.setStackInSlot(RoadFactoryContainer.OUTPUT_1, stack); }
			return true;
		}
		
		if (o2.getItem() == stack.getItem()) {
			if (o2.getCount() <= o2.getMaxStackSize() - remain) {
				if (!simulate) { o2.setCount(o2.getCount() + remain); }
				return true;
			}
		}
		
		if (o2.isEmpty()) {
			if (!simulate) { inventory.setStackInSlot(RoadFactoryContainer.OUTPUT_2, stack); }
			return true;
		}
		
		if (o3.getItem() == stack.getItem()) {
			if (o3.getCount() <= o3.getMaxStackSize() - remain) {
				if (!simulate) { o3.setCount(o3.getCount() + remain); }
				return true;
			}
		}
		if (o3.isEmpty()) {
			if (!simulate) { inventory.setStackInSlot(RoadFactoryContainer.OUTPUT_3, stack); }
			return true;
		}
		
		if (o4.getItem() == stack.getItem()) {
			if (o4.getCount() <= o4.getMaxStackSize() - remain) {
				if (!simulate) { o4.setCount(o4.getCount() + remain); }
				return true;
			}
		}
		
		if (o4.isEmpty()) {
			if (!simulate) { inventory.setStackInSlot(RoadFactoryContainer.OUTPUT_4, stack); }
			return true;
		}
		return false;
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return getCapability(capability, facing) != null;
        }
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			return true;
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
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && facing == EnumFacing.UP) {
			CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(tarFluid);
		}
		return super.getCapability(capability, facing);
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
