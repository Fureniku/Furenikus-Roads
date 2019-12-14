package com.silvaniastudios.roads.blocks.tileentities;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.ItemStackHandler;

public class InteractableStackHandler extends ItemStackHandler {
	private final ItemStackHandler internalStackHandler;
	
	private int inputSlots[];
	private int outputSlots[];
	private int fuelSlot = 0;

	public InteractableStackHandler(ItemStackHandler ish, int[] inputSlots, int[] outputSlots, int fuelSlot) {
		super();
		internalStackHandler = ish;
		this.inputSlots = inputSlots;
		this.outputSlots = outputSlots;
		this.fuelSlot = fuelSlot;
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
		if (slot == fuelSlot && TileEntityFurnace.getItemBurnTime(stack) > 0) {
			return internalStackHandler.insertItem(slot, stack, simulate);
		}
		for (int i = 0; i < inputSlots.length; i++) {
			if (inputSlots[i] == slot) {
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