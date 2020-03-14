package com.silvaniastudios.roads.blocks.tileentities.distiller;

import javax.annotation.Nullable;

import com.silvaniastudios.roads.blocks.tileentities.recipes.RecipeRegistry;
import com.silvaniastudios.roads.blocks.tileentities.recipes.TarDistillerRecipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotDistillerInput extends SlotItemHandler {
	
	TarDistillerEntity te;

	public SlotDistillerInput(IItemHandler itemHandler, int index, int xPosition, int yPosition, TarDistillerEntity te) {
		super(itemHandler, index, xPosition, yPosition);
		this.te = te;
	}
	
	@Override
	public boolean isItemValid(@Nullable ItemStack stack) {
		for (int i = 0; i < RecipeRegistry.tarDistillerRecipes.size(); i++) {
			TarDistillerRecipes tdr = RecipeRegistry.tarDistillerRecipes.get(i);
			if (tdr.getInputStack().getItem() == stack.getItem() && tdr.getInputStack().getItemDamage() == stack.getItemDamage()) {
				return true;
			}
		}
		return false;
	}

}
