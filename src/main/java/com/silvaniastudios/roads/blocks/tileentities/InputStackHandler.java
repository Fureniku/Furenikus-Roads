package com.silvaniastudios.roads.blocks.tileentities;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.ItemStackHandler;

public class InputStackHandler extends ItemStackHandler {
	private final ItemStackHandler internalStackHandler;

	public InputStackHandler(ItemStackHandler ish) {
		super();
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
		return internalStackHandler.insertItem(slot, stack, simulate);
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		return ItemStack.EMPTY;
	}
}