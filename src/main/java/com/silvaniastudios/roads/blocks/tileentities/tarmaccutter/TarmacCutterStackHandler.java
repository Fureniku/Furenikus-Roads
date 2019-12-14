package com.silvaniastudios.roads.blocks.tileentities.tarmaccutter;

import com.silvaniastudios.roads.blocks.RoadBlock;
import com.silvaniastudios.roads.items.TarmacCutterBlade;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.ItemStackHandler;

public class TarmacCutterStackHandler extends ItemStackHandler {
	
	private final ItemStackHandler internalStackHandler;

	public TarmacCutterStackHandler(ItemStackHandler ish) {
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
		if (slot == TarmacCutterContainer.FUEL && TileEntityFurnace.getItemBurnTime(stack) > 0) {
			return internalStackHandler.insertItem(slot, stack, simulate);
		}
		
		if (slot == TarmacCutterContainer.BLADE && stack.getItem() instanceof TarmacCutterBlade) {
			return internalStackHandler.insertItem(slot, stack, simulate);
		}

		if (slot == TarmacCutterContainer.INPUT) {
			if (stack.getItem() instanceof ItemBlock) {
				ItemBlock ib = (ItemBlock) stack.getItem();
				if (ib.getBlock() instanceof RoadBlock) {
					return internalStackHandler.insertItem(slot, stack, simulate);
				}
			}
		}
		
		return stack;
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		if (slot == TarmacCutterContainer.OUTPUT_1 || slot == TarmacCutterContainer.OUTPUT_2) {
			return internalStackHandler.extractItem(slot, amount, simulate);
		}
		return ItemStack.EMPTY;
	}
}