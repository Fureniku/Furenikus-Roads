package com.silvaniastudios.roads.blocks.tileentities;

import javax.annotation.Nullable;

import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotBucket extends SlotItemHandler {

	public SlotBucket(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}

	@Override
	public boolean isItemValid(@Nullable ItemStack stack) {
		return stack.getItem() instanceof ItemBucket;
	}
}
