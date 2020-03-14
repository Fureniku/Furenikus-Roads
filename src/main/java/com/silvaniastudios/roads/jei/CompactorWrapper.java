package com.silvaniastudios.roads.jei;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class CompactorWrapper implements IRecipeWrapper {

	private final ItemStack input;
	private final ItemStack output;
	
	public CompactorWrapper(ItemStack input, ItemStack output) {
		this.input = input;
		this.output = output;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInput(VanillaTypes.ITEM, input);
		ingredients.setOutput(VanillaTypes.ITEM, output);
	}

}
