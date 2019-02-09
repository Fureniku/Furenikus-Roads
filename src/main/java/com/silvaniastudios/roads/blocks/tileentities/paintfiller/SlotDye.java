package com.silvaniastudios.roads.blocks.tileentities.paintfiller;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotDye extends SlotItemHandler {

	private EnumDyeColor c;
	
	public SlotDye(IItemHandler itemHandler, int index, int xPosition, int yPosition, EnumDyeColor validColour) {
		super(itemHandler, index, xPosition, yPosition);
		this.c = validColour;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		if (stack.getItem() instanceof ItemDye) {
			EnumDyeColor col = EnumDyeColor.byDyeDamage(stack.getMetadata());
			
			if (col == c) {
				return true;
			}
		}
		return false;
	}
}
