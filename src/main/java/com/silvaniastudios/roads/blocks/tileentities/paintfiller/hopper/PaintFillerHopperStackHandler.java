package com.silvaniastudios.roads.blocks.tileentities.paintfiller.hopper;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.oredict.OreDictionary;

public class PaintFillerHopperStackHandler extends ItemStackHandler {
	
	private final ItemStackHandler internalStackHandler;
	boolean electric;
	
	public PaintFillerHopperStackHandler(ItemStackHandler ish, boolean electric) {
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
		if (slot == 0 && isDye(stack, "dyeWhite")) {
			return internalStackHandler.insertItem(slot, stack, simulate);
		}
		if (slot == 1 && isDye(stack, "dyeYellow")) {
			return internalStackHandler.insertItem(slot, stack, simulate);
		}
		if (slot == 2 && isDye(stack, "dyeRed")) {
			return internalStackHandler.insertItem(slot, stack, simulate);
		}
		
		if (slot == 3 && stack.getItem().getUnlocalizedName().compareTo("item.forge.bucketFilled") == 0) {
			return internalStackHandler.insertItem(slot, stack, simulate);
		}
		
		if (slot == 4 && stack.getItem() == Items.BUCKET) {
			return internalStackHandler.insertItem(slot, stack, simulate);
		}
		return stack;
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		if (slot != 3) {
			return internalStackHandler.extractItem(slot, amount, simulate);
		}
		return ItemStack.EMPTY;
	}
	
	public boolean isDye(ItemStack stack, String name) {
		if (!stack.isEmpty()) {
			for (int id : OreDictionary.getOreIDs(stack)) {
				if (OreDictionary.getOreName(id).equals(name)) {
	                return true;
				}
			}
		}
		return false;
	}
}