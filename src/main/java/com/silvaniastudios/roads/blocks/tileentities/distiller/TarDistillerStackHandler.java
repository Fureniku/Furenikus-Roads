package com.silvaniastudios.roads.blocks.tileentities.distiller;

import com.silvaniastudios.roads.blocks.tileentities.recipes.RecipeRegistry;
import com.silvaniastudios.roads.blocks.tileentities.recipes.TarDistillerRecipes;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.ItemStackHandler;

public class TarDistillerStackHandler extends ItemStackHandler {
	
	private final ItemStackHandler internalStackHandler;
	boolean electric;
	
	public TarDistillerStackHandler(ItemStackHandler ish, boolean electric) {
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
		//Only lava is a valid fuel for this, coal doesnt burn hot enough.
		if (slot == TarDistillerContainer.FUEL && stack.getItem() == Items.LAVA_BUCKET && !electric) {
			return internalStackHandler.insertItem(slot, stack, simulate);
		}

		for (int i = 0; i < RecipeRegistry.tarDistillerRecipes.size(); i++) {
			TarDistillerRecipes tdr = RecipeRegistry.tarDistillerRecipes.get(i);
			if (tdr.getInputStack().getItem() == stack.getItem() && tdr.getInputStack().getItemDamage() == stack.getItemDamage()) {
				return internalStackHandler.insertItem(slot, stack, simulate);
			}
		}
		
		if ((slot == TarDistillerContainer.FLUID_OUT_1_BUCKET || slot == TarDistillerContainer.FLUID_OUT_1_BUCKET) && stack.getItem() == Items.BUCKET) {
			return internalStackHandler.insertItem(slot, stack, simulate);
		}
		
		return stack;
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		//Only take out the empty bucket from the lava, not a filled one.
		if (slot == TarDistillerContainer.FUEL && getStackInSlot(slot).getItem() == Items.BUCKET) {
			return internalStackHandler.extractItem(slot, amount, simulate);
		}
		if (slot == TarDistillerContainer.OUTPUT_1 
				|| slot == TarDistillerContainer.OUTPUT_2 
				|| slot == TarDistillerContainer.FLUID_IN_BUCKET 
				|| slot == TarDistillerContainer.FLUID_OUT_1 
				|| slot == TarDistillerContainer.FLUID_OUT_2) {
			return internalStackHandler.extractItem(slot, amount, simulate);
		}

		return ItemStack.EMPTY;
	}
}