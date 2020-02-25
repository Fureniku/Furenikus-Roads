package com.silvaniastudios.roads.jei;

import java.util.ArrayList;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class TarDistillerWrapper implements IRecipeWrapper {

	private final ItemStack itemIn;
	private final ItemStack itemOut1;
	private final ItemStack itemOut2;
	
	private final FluidStack fluidIn;
	private final FluidStack fluidOut1;
	private final FluidStack fluidOut2;
	
	public TarDistillerWrapper(ItemStack itemIn, FluidStack fluidIn, ItemStack itemOut1, ItemStack itemOut2, FluidStack fluidOut1, FluidStack fluidOut2) {
		this.itemIn = itemIn;
		this.fluidIn = fluidIn;
		this.itemOut1 = itemOut1;
		this.itemOut2 = itemOut2;
		this.fluidOut1 = fluidOut1;
		this.fluidOut2 = fluidOut2;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInput(VanillaTypes.ITEM, itemIn);
		if (fluidIn != null) { ingredients.setInput(VanillaTypes.FLUID, fluidIn); }
		
		ArrayList<ItemStack> output = new ArrayList<ItemStack>(2);
		ArrayList<FluidStack> fluidOut = new ArrayList<FluidStack>(2);
		
		output.add(itemOut1);
		output.add(itemOut2);
		if (fluidOut1 != null) { fluidOut.add(fluidOut1); }
		if (fluidOut2 != null) { fluidOut.add(fluidOut2); }
		
		ingredients.setOutputs(VanillaTypes.ITEM, output);
		ingredients.setOutputs(VanillaTypes.FLUID, fluidOut);
	}
}