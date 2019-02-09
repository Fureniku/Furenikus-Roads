package com.silvaniastudios.roads.blocks.tileentities.tarmaccutter;

import javax.annotation.Nullable;

import com.silvaniastudios.roads.items.TarmacCutterBlade;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotBlade extends SlotItemHandler {

	public SlotBlade(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(@Nullable ItemStack stack) {
		return stack.getItem() instanceof TarmacCutterBlade;
	}
}
