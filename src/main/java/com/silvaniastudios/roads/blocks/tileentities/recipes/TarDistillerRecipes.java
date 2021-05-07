package com.silvaniastudios.roads.blocks.tileentities.recipes;

import com.silvaniastudios.roads.blocks.tileentities.distiller.TarDistillerContainer;
import com.silvaniastudios.roads.blocks.tileentities.distiller.TarDistillerEntity;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.items.ItemStackHandler;

public class TarDistillerRecipes {
		
	private final Ingredient ingredient;
	private final FluidStack fluidInput;
	private final FluidStack fluidOutput1;
	private final FluidStack fluidOutput2;
	private final ItemStack input;
	private final ItemStack output1;
	private final ItemStack output2;
	
	public TarDistillerRecipes(FluidStack fluidInput, ItemStack input, FluidStack fluidOutput1, FluidStack fluidOutput2, ItemStack output1, ItemStack output2) {
		this.ingredient = CraftingHelper.getIngredient(input);
		this.fluidInput = fluidInput;
		this.input = input;
		this.fluidOutput1 = fluidOutput1;
		this.fluidOutput2 = fluidOutput2;
		this.output1 = output1;
		this.output2 = output2;
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
	
	public boolean testFluid(FluidStack fluidIn) {
		if (fluidInput == null) {
			return true;
		}
		
		//If we get here, we SHOULD have a fluid (coz input wasn't null). So if the main tank is empty/null, then it fails.
		if (fluidIn == null) {
			return false;
		}
		
		if (fluidIn.getFluid() == fluidInput.getFluid() && fluidIn.amount >= fluidInput.amount) {
			return true;
		}
		
		return false;
	}
	
	public boolean testFluidOutput(FluidStack tank, FluidStack recipeOut) {
		if (tank == null || recipeOut == null) {
			return true;
		}
		if (tank.getFluid() == recipeOut.getFluid() && tank.amount + recipeOut.amount <= TarDistillerEntity.TANK_CAP) {
			return true;
		}
		return false;
	}
	
	public boolean canFullyCraft(ItemStackHandler inventory, FluidTank fluidIn, FluidTank fluidOut1, FluidTank fluidOut2) {
		ItemStack in = inventory.getStackInSlot(TarDistillerContainer.INPUT);
		if (test(ingredient, in)) {
			if (testFluid(fluidIn.getFluid())) {
				//Our inputs are all good. What can we make?
				boolean out1 = output1.getCount() > 0 ? inventory.insertItem(TarDistillerContainer.OUTPUT_1, output1, true).getCount() == 0 : true;
				boolean out2 = output2.getCount() > 0 ? inventory.insertItem(TarDistillerContainer.OUTPUT_2, output2, true).getCount() == 0 : true;
				
				boolean fOut1 = testFluidOutput(fluidOut1.getFluid(), fluidOutput1);
				boolean fOut2 = testFluidOutput(fluidOut2.getFluid(), fluidOutput2);
				
				if (out1 && out2 && fOut1 && fOut2) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public void processCrafting(ItemStackHandler inventory, FluidTank fluidIn, FluidTank fluidOut1, FluidTank fluidOut2) {
		if (canFullyCraft(inventory, fluidIn, fluidOut1, fluidOut2)) {
			if (input != ItemStack.EMPTY) { inventory.extractItem(TarDistillerContainer.INPUT, input.getCount(), false); }
			if (fluidInput != null) { fluidIn.drain(fluidInput.amount, true); }
			
			if (output1 != ItemStack.EMPTY) { inventory.insertItem(TarDistillerContainer.OUTPUT_1, output1.copy(), false); }
			if (output2 != ItemStack.EMPTY) { inventory.insertItem(TarDistillerContainer.OUTPUT_2, output2.copy(), false); }
			
			if (fluidOutput1 != null) { fluidOut1.fill(fluidOutput1, true); }
			if (fluidOutput2 != null) { fluidOut2.fill(fluidOutput2, true); }
		}
	}
	
	public FluidStack getFluidInputStack() {
		return fluidInput;
	}
	
	public ItemStack getInputStack() {
		return input;
	}
	
	public ItemStack getOutput1Stack() {
		return output1;
	}
	
	public ItemStack getOutput2Stack() {
		return output2;
	}
	
	public FluidStack getFluidOutput1Stack() {
		return fluidOutput1;
	}
	
	public FluidStack getFluidOutput2Stack() {
		return fluidOutput2;
	}

}
