package com.silvaniastudios.roads.blocks.tileentities.paintfiller;

import com.silvaniastudios.roads.items.FRItems;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.oredict.OreDictionary;

public class PaintFillerStackHandler extends ItemStackHandler {
	
	private final ItemStackHandler internalStackHandler;
	boolean electric;
	
	public PaintFillerStackHandler(ItemStackHandler ish, boolean electric) {
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
		if (slot == PaintFillerContainer.FUEL && TileEntityFurnace.getItemBurnTime(stack) > 0 && !electric) {
			return internalStackHandler.insertItem(slot, stack, simulate);
		}
		
		if (slot == PaintFillerContainer.WHITE_DYE && isDye(stack, "dyeWhite")) {
			return internalStackHandler.insertItem(slot, stack, simulate);
		}
		if (slot == PaintFillerContainer.YELLOW_DYE && isDye(stack, "dyeYellow")) {
			return internalStackHandler.insertItem(slot, stack, simulate);
		}
		if (slot == PaintFillerContainer.RED_DYE && isDye(stack, "dyeRed")) {
			return internalStackHandler.insertItem(slot, stack, simulate);
		}
		
		if (slot == PaintFillerContainer.GUN && stack.getItem() == FRItems.paint_gun) {
			return internalStackHandler.insertItem(slot, stack, simulate);
		}
		return super.insertItem(slot, stack, simulate);
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		return internalStackHandler.extractItem(slot, amount, simulate);
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