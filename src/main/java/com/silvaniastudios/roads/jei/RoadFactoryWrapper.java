package com.silvaniastudios.roads.jei;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class RoadFactoryWrapper implements IRecipeWrapper {

	private final ItemStack input;
	private final FluidStack fluidInput;
	private final ItemStack output;
	
	public RoadFactoryWrapper(ItemStack input, FluidStack fluidInput, ItemStack output) {
		this.input = input;
		this.fluidInput = fluidInput;
		this.output = output;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInput(VanillaTypes.ITEM, input);
		ingredients.setInput(VanillaTypes.FLUID, fluidInput);
		ingredients.setOutput(VanillaTypes.ITEM, output);
	}
}
