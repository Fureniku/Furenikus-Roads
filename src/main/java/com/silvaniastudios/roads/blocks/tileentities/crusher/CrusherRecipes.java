package com.silvaniastudios.roads.blocks.tileentities.crusher;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.world.World;

public class CrusherRecipes {

	final Ingredient ingredient;
	final ItemStack result;
	
	public CrusherRecipes(Ingredient i, ItemStack r) {
		this.ingredient = i;
		this.result = r;
	}
	
	public boolean matches(InventoryCrafting inv, World worldIn) {
		return this.ingredient.test(inv.getStackInSlot(0));
	}

	public ItemStack getCraftingResult(InventoryCrafting inv) {
		return this.result.copy();
	}
}
