package com.silvaniastudios.roads.blocks.tileentities.paintfiller;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.oredict.OreDictionary;

public class SlotDye extends SlotItemHandler {

	private String dyeName;
	
	public SlotDye(IItemHandler itemHandler, int index, int xPosition, int yPosition, String dyeName) {
		super(itemHandler, index, xPosition, yPosition);
		this.dyeName = dyeName;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		if (stack != ItemStack.EMPTY) {
			for (int id : OreDictionary.getOreIDs(stack)) {
				if (OreDictionary.getOreName(id).equals(dyeName)) {
	                return true;
				}
			}
		}
		return false;
	}
}
