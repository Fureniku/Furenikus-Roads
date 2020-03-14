package com.silvaniastudios.roads.jei;

import java.util.ArrayList;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class RoadFactoryWrapper implements IRecipeWrapper {

	private final ItemStack input;
	private final ItemStack modifier;
	private final FluidStack fluidInput;
	private final ItemStack output;
	
	public RoadFactoryWrapper(ItemStack input, ItemStack modifier, FluidStack fluidInput, ItemStack output) {
		this.input = input;
		this.modifier = modifier;
		this.fluidInput = fluidInput;
		this.output = output;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		list.add(input);
		list.add(modifier);
		ingredients.setInputs(VanillaTypes.ITEM, list);
		ingredients.setInput(VanillaTypes.FLUID, fluidInput);
		ingredients.setOutput(VanillaTypes.ITEM, output);
	}
}
