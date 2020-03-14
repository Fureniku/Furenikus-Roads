package com.silvaniastudios.roads.blocks.tileentities.roadfactory;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.items.ItemStackHandler;

public class RoadFactoryStackHandler extends ItemStackHandler {
	
	private final ItemStackHandler internalStackHandler;
	boolean electric;
	
	public int[] inputSlots = {
			RoadFactoryContainer.INPUT_1, 
			RoadFactoryContainer.INPUT_2,
			RoadFactoryContainer.INPUT_3,
			RoadFactoryContainer.INPUT_4
		};
	public int[] outputSlots = {
			RoadFactoryContainer.OUTPUT_1,
			RoadFactoryContainer.OUTPUT_2,
			RoadFactoryContainer.OUTPUT_3,
			RoadFactoryContainer.OUTPUT_4,
			RoadFactoryContainer.FLUID_IN_BUCKET
	};
	
	private int fuelSlot = RoadFactoryContainer.FUEL;

	public RoadFactoryStackHandler(ItemStackHandler ish, boolean electric) {
		super();
		this.electric = electric;
		internalStackHandler = ish;
	}

	@Override
	public void setSize(int size) {
		stacks = NonNullList.<ItemStack>withSize(size, ItemStack.EMPTY);
	}

	@Override
	public void setStackInSlot(int slot, ItemStack stack) {
		internalStackHandler.setStackInSlot(slot, stack);
	}

	@Override
	public int getSlots() {
		return internalStackHandler.getSlots();
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return internalStackHandler.getStackInSlot(slot);
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
		if (slot == fuelSlot && TileEntityFurnace.getItemBurnTime(stack) > 0 && !electric) {
			return internalStackHandler.insertItem(slot, stack, simulate);
		}
		if (slot == RoadFactoryContainer.FLUID_IN && stack.getItem() instanceof UniversalBucket) {
			return internalStackHandler.insertItem(slot, stack, simulate);
		}
		for (int i = 0; i < inputSlots.length; i++) {
			//Road factory only ever has blocks as an input.
			//This is a bit lazier than a proper validation list. Players are responsible for their own filtering beyond this though!
			//This only makes sure that tar buckets and coal don't accidentally go to the wrong slot.
			if (inputSlots[i] == slot && stack.getItem() instanceof ItemBlock) {
				return internalStackHandler.insertItem(slot, stack, simulate);
			}
		}
		
		return stack;
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		for (int i = 0; i < outputSlots.length; i++) {
			if (outputSlots[i] == slot) {
				return internalStackHandler.extractItem(slot, amount, simulate);
			}
		}
		return ItemStack.EMPTY;
	}
}