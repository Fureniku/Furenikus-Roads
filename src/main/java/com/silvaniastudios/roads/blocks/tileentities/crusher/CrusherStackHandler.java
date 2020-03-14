package com.silvaniastudios.roads.blocks.tileentities.crusher;

import java.util.ArrayList;

import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.RoadBlock;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.ItemStackHandler;

public class CrusherStackHandler extends ItemStackHandler {
	
	private final ItemStackHandler internalStackHandler;
	boolean electric;
	public ArrayList<ItemStack> validInputs = new ArrayList<ItemStack>();

	public CrusherStackHandler(ItemStackHandler ish, boolean electric) {
		super();
		this.electric = electric;
		internalStackHandler = ish;
		
		validInputs.add(new ItemStack(Blocks.STONE));
		validInputs.add(new ItemStack(Blocks.COBBLESTONE));
		validInputs.add(new ItemStack(Blocks.GRAVEL));
		validInputs.add(new ItemStack(FRBlocks.generic_blocks, 1, 0));
		validInputs.add(new ItemStack(FRBlocks.generic_blocks, 1, 1));
		validInputs.add(new ItemStack(FRBlocks.generic_blocks, 1, 2));
		validInputs.add(new ItemStack(FRBlocks.generic_blocks, 1, 3));
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
		if (slot == CrusherContainer.FUEL && TileEntityFurnace.getItemBurnTime(stack) > 0 && !electric) {
			return internalStackHandler.insertItem(slot, stack, simulate);
		}

		if (slot == CrusherContainer.INPUT_1) {
			//Right now everything is a block, so we can just check this here for slight performance gainz.
			//If we add any items later, then move the valid input for loop out of the scope of this itemblock check.
			if (stack.getItem() instanceof ItemBlock) {
				ItemBlock ib = (ItemBlock) stack.getItem();
				if (ib.getBlock() instanceof RoadBlock) {
					return internalStackHandler.insertItem(slot, stack, simulate);
				}
				
				for (int i = 0; i < validInputs.size(); i++) {
					if (validInputs.get(i).getItem() == stack.getItem()) {
						return internalStackHandler.insertItem(slot, stack, simulate);
					}
				}
			}
		}
		
		return stack;
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		if (slot == CrusherContainer.OUTPUT_1) {
			return internalStackHandler.extractItem(slot, amount, simulate);
		}
		return ItemStack.EMPTY;
	}
}