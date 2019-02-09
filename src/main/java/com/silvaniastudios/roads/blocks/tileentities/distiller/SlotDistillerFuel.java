package com.silvaniastudios.roads.blocks.tileentities.distiller;

import javax.annotation.Nullable;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotDistillerFuel extends SlotItemHandler {

	public SlotDistillerFuel(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(@Nullable ItemStack stack) {
		return stack.getItem().equals(Items.LAVA_BUCKET);
	}
}
