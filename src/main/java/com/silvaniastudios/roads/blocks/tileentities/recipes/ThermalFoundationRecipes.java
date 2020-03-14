package com.silvaniastudios.roads.blocks.tileentities.recipes;

import com.silvaniastudios.roads.fluids.FRFluids;

import cofh.thermalfoundation.init.TFFluids;
import cofh.thermalfoundation.init.TFItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class ThermalFoundationRecipes {
	
	public static void tfRecipes() {
		RecipeRegistry.tarDistillerRecipes.add(new TarDistillerRecipes(new FluidStack(FluidRegistry.WATER, 2000), new ItemStack(TFItems.itemMaterial, 1, 833), new FluidStack(FRFluids.tar, 2000), null, ItemStack.EMPTY, ItemStack.EMPTY));
		RecipeRegistry.tarDistillerRecipes.add(new TarDistillerRecipes(new FluidStack(FluidRegistry.WATER, 3000), new ItemStack(TFItems.itemMaterial, 1, 892), new FluidStack(FRFluids.tar, 3000), null, ItemStack.EMPTY, ItemStack.EMPTY));
		
		RecipeRegistry.tarDistillerRecipes.add(new TarDistillerRecipes(new FluidStack(TFFluids.fluidCrudeOil, 250), ItemStack.EMPTY, new FluidStack(FRFluids.tar, 250), null, ItemStack.EMPTY, ItemStack.EMPTY));
		RecipeRegistry.tarDistillerRecipes.add(new TarDistillerRecipes(new FluidStack(TFFluids.fluidBiocrude, 250), ItemStack.EMPTY, new FluidStack(FRFluids.tar, 250), null, ItemStack.EMPTY, ItemStack.EMPTY));
	}

}
