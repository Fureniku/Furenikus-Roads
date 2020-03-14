package com.silvaniastudios.roads.blocks.tileentities.recipes;

import com.silvaniastudios.roads.blocks.tileentities.compactor.CompactorContainer;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.items.ItemStackHandler;

public class CompactorRecipes {
	
	private final Ingredient ingredient;
	private final ItemStack input;
	private final ItemStack result;
	
	public CompactorRecipes(ItemStack i, ItemStack r) {
		this.ingredient = CraftingHelper.getIngredient(i);
		this.input = i;
		this.result = r;
	}
	
	public boolean test(Ingredient ing, ItemStack input, int amt) {
		if (input == ItemStack.EMPTY) {
			return ing.test(input);
		}
		return ing.test(input) && input.getCount() >= amt;
	}

	public ItemStack getCraftingResult(ItemStackHandler inventory, int sizeSetting) {
		ItemStack in = inventory.getStackInSlot(CompactorContainer.FRAGMENTS);
		
		if (test(ingredient, in, sizeSetting+1)) {
			ItemStack out = this.result.copy();
			out.setItemDamage(sizeSetting);
			return out;
		}
		return ItemStack.EMPTY;
	}
	
	public ItemStack getInputStack() {
		return input;
	}
	
	public ItemStack getOutputStack() {
		return result;
	}

}
