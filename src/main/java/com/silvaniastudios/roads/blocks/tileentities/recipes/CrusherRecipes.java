package com.silvaniastudios.roads.blocks.tileentities.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.items.ItemStackHandler;

public class CrusherRecipes {

	private final Ingredient ingredient;
	private final ItemStack input;
	private final ItemStack result;
	
	public CrusherRecipes(ItemStack i, ItemStack r) {
		this.ingredient = CraftingHelper.getIngredient(i);
		this.input = i;
		this.result = r;
	}
	
	public boolean test(ItemStack input) {
		return ingredient.test(input);
	}

	public ItemStack getCraftingResult(ItemStackHandler inv) {
		return this.result.copy();
	}
	
	public ItemStack getInputStack() {
		return input;
	}
	
	public ItemStack getOutputStack() {
		return result;
	}
}
