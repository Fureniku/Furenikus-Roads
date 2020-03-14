package com.silvaniastudios.roads.blocks.tileentities.paintoven;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.oredict.OreDictionary;

public class PaintOvenStackHandler extends ItemStackHandler {
	
	private final ItemStackHandler internalStackHandler;
	boolean electric;
	
	public PaintOvenStackHandler(ItemStackHandler ish, boolean electric) {
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
		if (slot == PaintOvenContainer.FUEL && TileEntityFurnace.getItemBurnTime(stack) > 0 && !electric) {
			return internalStackHandler.insertItem(slot, stack, simulate);
		}
		
		if (slot == PaintOvenContainer.DYE && (isDye(stack, "dyeWhite") || isDye(stack, "dyeYellow") || isDye(stack, "dyeRed"))) {
			return internalStackHandler.insertItem(slot, stack, simulate);
		}
		return stack;
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
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
