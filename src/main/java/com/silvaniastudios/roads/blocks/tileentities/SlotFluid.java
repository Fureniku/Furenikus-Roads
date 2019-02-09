package com.silvaniastudios.roads.blocks.tileentities;

import javax.annotation.Nullable;

import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotFluid extends SlotItemHandler {

	public SlotFluid(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(@Nullable ItemStack stack) {
		if (stack.getUnlocalizedName().compareTo("item.forge.bucketFilled") == 0) {
			return true;
		}
		return stack.getItem() instanceof ItemBucket;
	}

}
