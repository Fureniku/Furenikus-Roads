package com.silvaniastudios.roads.blocks.tileentities.recipes;

import com.silvaniastudios.roads.RoadsConfig;

import cofh.api.util.ThermalExpansionHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

public class ThermalExpansionRecipes {
	
	public static void registerThermalExpansionRecipes() {
		if (Loader.isModLoaded("thermalexpansion")) {
			for (int i = 0; i < RecipeRegistry.crusherRecipes.size(); i++) {
				CrusherRecipes cr = RecipeRegistry.crusherRecipes.get(i);
				ThermalExpansionHelper.addPulverizerRecipe(RoadsConfig.machine.electricCrusherEnergyConsumption, cr.getInputStack(), cr.getOutputStack());
			}
			
			for (int i = 0; i < RecipeRegistry.compactorRecipes.size(); i++) {
				CompactorRecipes cr = RecipeRegistry.compactorRecipes.get(i);
				ItemStack input = cr.getInputStack();
				ItemStack output = cr.getOutputStack();
				
				input.setCount(16);
				output.setItemDamage(15);
				
				ThermalExpansionHelper.addCompactorRecipe(RoadsConfig.machine.electricCompactorEnergyConsumption, input, output);
			}
		}
	}
}
