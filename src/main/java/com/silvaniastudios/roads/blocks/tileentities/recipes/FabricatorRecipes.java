package com.silvaniastudios.roads.blocks.tileentities.recipes;

import com.silvaniastudios.roads.blocks.tileentities.fabricator.FabricatorContainer;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class FabricatorRecipes {
	
	private final int id;
	private final ItemStack is1;
	private final ItemStack is2;
	private final ItemStack is3;
	private final ItemStack is4;
	private final ItemStack is5;
	private final ItemStack is6;
	private final ItemStack result;
	
	public FabricatorRecipes(int id, ItemStack i1, ItemStack i2, ItemStack i3, ItemStack i4, ItemStack i5, ItemStack i6, ItemStack result) {
		this.is1 = i1;
		this.is2 = i2;
		this.is3 = i3;
		this.is4 = i4;
		this.is5 = i5;
		this.is6 = i6;
		
		this.id = id;
		
		this.result = result;
	}
	
	public boolean test(ItemStack ingredient, ItemStack input) {
		if (ingredient != ItemStack.EMPTY) {
			return ingredient.getItem() == input.getItem() && ingredient.getItemDamage() == input.getItemDamage() && input.getCount() >= ingredient.getCount();
		}
		return true;
	}
	
	public ItemStack getCraftingResult(ItemStackHandler inventory, int id) {
		if (id != this.id) {
			return ItemStack.EMPTY;
		}
		ItemStack slotIn1 = inventory.getStackInSlot(FabricatorContainer.IN_1);
		ItemStack slotIn2 = inventory.getStackInSlot(FabricatorContainer.IN_2);
		ItemStack slotIn3 = inventory.getStackInSlot(FabricatorContainer.IN_3);
		ItemStack slotIn4 = inventory.getStackInSlot(FabricatorContainer.IN_4);
		ItemStack slotIn5 = inventory.getStackInSlot(FabricatorContainer.IN_5);
		ItemStack slotIn6 = inventory.getStackInSlot(FabricatorContainer.IN_6);
		
		boolean slot1Valid = test(is1, slotIn1);
		boolean slot2Valid = test(is2, slotIn2);
		boolean slot3Valid = test(is3, slotIn3);
		boolean slot4Valid = test(is4, slotIn4);
		boolean slot5Valid = test(is5, slotIn5);
		boolean slot6Valid = test(is6, slotIn6);
		
		if (slot1Valid && slot2Valid && slot3Valid && slot4Valid && slot5Valid && slot6Valid) {
			return result.copy();
		}
		return ItemStack.EMPTY;
	}
	
	public void processRecipe(ItemStackHandler inventory, int id) {
		if (getCraftingResult(inventory, id) != ItemStack.EMPTY) {
			if (inventory.insertItem(FabricatorContainer.OUTPUT, result, true) == ItemStack.EMPTY) {
				if (is1 != ItemStack.EMPTY) { inventory.extractItem(FabricatorContainer.IN_1, is1.getCount(), false); }
				if (is2 != ItemStack.EMPTY) { inventory.extractItem(FabricatorContainer.IN_2, is2.getCount(), false); }
				if (is3 != ItemStack.EMPTY) { inventory.extractItem(FabricatorContainer.IN_3, is3.getCount(), false); }
				if (is4 != ItemStack.EMPTY) { inventory.extractItem(FabricatorContainer.IN_4, is4.getCount(), false); }
				if (is5 != ItemStack.EMPTY) { inventory.extractItem(FabricatorContainer.IN_5, is5.getCount(), false); }
				if (is6 != ItemStack.EMPTY) { inventory.extractItem(FabricatorContainer.IN_6, is6.getCount(), false); }
				
				inventory.insertItem(FabricatorContainer.OUTPUT, result.copy(), false);
			}
		}
	}

	public ItemStack getInput1() { return is1; }
	public ItemStack getInput2() { return is2; }
	public ItemStack getInput3() { return is3; }
	public ItemStack getInput4() { return is4; }
	public ItemStack getInput5() { return is5; }
	public ItemStack getInput6() { return is6; }
	
	public ItemStack getOutput() { return result; }
	public int getId() { return this.id; }
}
