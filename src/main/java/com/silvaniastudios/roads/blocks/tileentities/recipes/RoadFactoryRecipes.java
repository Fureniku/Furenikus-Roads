package com.silvaniastudios.roads.blocks.tileentities.recipes;

import com.silvaniastudios.roads.blocks.tileentities.roadfactory.RoadFactoryContainer;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.ItemStackHandler;

public class RoadFactoryRecipes {
	
	private final Ingredient ingredient;
	private final Ingredient ingredientModifier;
	private final FluidStack fluidInput;
	private final ItemStack input;
	private final ItemStack modifier;
	private final ItemStack result;
	
	public RoadFactoryRecipes(FluidStack fluidInput, ItemStack i, ItemStack m, ItemStack r) {
		this.ingredient = CraftingHelper.getIngredient(i);
		this.ingredientModifier = CraftingHelper.getIngredient(m);
		this.fluidInput = fluidInput;
		this.input = i;
		this.modifier = m;
		this.result = r;
	}
	
	public boolean matches(InventoryCrafting inv, World worldIn) {
		return this.ingredient.test(inv.getStackInSlot(0));
	}
	
	public boolean test(Ingredient ing, ItemStack input) {
		if (input == ItemStack.EMPTY) {
			return ing.test(input);
		}
		return ing.test(input) && this.input.getCount() <= input.getCount();
	}

	public ItemStack getCraftingResult(ItemStackHandler inventory) {
		ItemStack in1 = inventory.getStackInSlot(RoadFactoryContainer.INPUT_1);
		ItemStack in2 = inventory.getStackInSlot(RoadFactoryContainer.INPUT_2);
		ItemStack in3 = inventory.getStackInSlot(RoadFactoryContainer.INPUT_3);
		ItemStack in4 = inventory.getStackInSlot(RoadFactoryContainer.INPUT_4);
		
		int i1 = test(ingredient, in1) ? 1 : 0;
		int i2 = test(ingredient, in2) ? 1 : 0;
		int i3 = test(ingredient, in3) ? 1 : 0;
		int i4 = test(ingredient, in4) ? 1 : 0;
		
		int sum = i1+i2+i3+i4;
		
		//Support for old machines which don't have the modifier slot.
		ItemStack modifier = inventory.getSlots() >= 12 ? inventory.getStackInSlot(RoadFactoryContainer.MODIFIER) : ItemStack.EMPTY;
		
		if ((sum > 0) && test(ingredientModifier, modifier)) {
			ItemStack out = this.result.copy();
			out.setCount(8 * (sum));
			return out;
		}
		return ItemStack.EMPTY;
	}
	
	public FluidStack getFluidInputStack() {
		return fluidInput;
	}
	
	public ItemStack getModifier() {
		return modifier;
	}
	
	public ItemStack getInputStack() {
		return input;
	}
	
	public ItemStack getOutputStack() {
		return result;
	}

}
