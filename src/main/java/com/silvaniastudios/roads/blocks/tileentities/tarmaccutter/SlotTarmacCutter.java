package com.silvaniastudios.roads.blocks.tileentities.tarmaccutter;

import javax.annotation.Nullable;

import com.silvaniastudios.roads.blocks.RoadBlock;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotTarmacCutter extends SlotItemHandler {

	public SlotTarmacCutter(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(@Nullable ItemStack stack) {
		if (stack.getItem() instanceof ItemBlock) {
			ItemBlock ib = (ItemBlock) stack.getItem();
			
			return ib.getBlock() instanceof RoadBlock;
		}
		return false;
	}

}
