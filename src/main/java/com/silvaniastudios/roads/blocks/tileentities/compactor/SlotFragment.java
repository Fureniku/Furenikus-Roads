package com.silvaniastudios.roads.blocks.tileentities.compactor;

import javax.annotation.Nullable;

import com.silvaniastudios.roads.items.ItemFragment;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotFragment extends SlotItemHandler {

	public SlotFragment(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(@Nullable ItemStack stack) {
		return stack.getItem() instanceof ItemFragment;
	}
}
