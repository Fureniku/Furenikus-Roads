package com.silvaniastudios.roads.jei;

import java.util.ArrayList;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class TarmacCutterWrapper implements IRecipeWrapper {

	private final ItemStack input;
	private final ItemStack output;
	private final ItemStack outputFragments;
	
	public TarmacCutterWrapper(ItemStack input, ItemStack output, ItemStack outputFragments) {
		this.input = input;
		this.output = output;
		this.outputFragments = outputFragments;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		ArrayList<ItemStack> input1 = new ArrayList<ItemStack>();
		input1.add(input);
		
		ArrayList<ItemStack> outputs = new ArrayList<ItemStack>();
		
		outputs.add(output);
		outputs.add(outputFragments);
		
		ingredients.setInput(VanillaTypes.ITEM, input);
		ingredients.setOutputs(VanillaTypes.ITEM, outputs);
	}
}