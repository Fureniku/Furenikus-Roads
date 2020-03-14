package com.silvaniastudios.roads.blocks.tileentities.compactor;

import com.silvaniastudios.roads.items.ItemFragment;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.ItemStackHandler;

public class CompactorStackHandler extends ItemStackHandler {
	
	private final ItemStackHandler internalStackHandler;
	boolean electric;

	public CompactorStackHandler(ItemStackHandler ish, boolean electric) {
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
		if (slot == CompactorContainer.FUEL && TileEntityFurnace.getItemBurnTime(stack) > 0 && !electric) {
			return internalStackHandler.insertItem(slot, stack, simulate);
		}

		if (slot == CompactorContainer.FRAGMENTS) {
			if (stack.getItem() instanceof ItemFragment) {
				return internalStackHandler.insertItem(slot, stack, simulate);
			}
		}
		
		return stack;
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		if (slot == CompactorContainer.ROADS) {
			return internalStackHandler.extractItem(slot, amount, simulate);
		}
		return ItemStack.EMPTY;
	}
}