package com.silvaniastudios.roads.blocks.tileentities.distiller;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotDistillerInput extends SlotItemHandler {
	
	TarDistillerEntity te;

	public SlotDistillerInput(IItemHandler itemHandler, int index, int xPosition, int yPosition, TarDistillerEntity te) {
		super(itemHandler, index, xPosition, yPosition);
		this.te = te;
	}
	
	@Override
	public boolean isItemValid(@Nullable ItemStack stack) {
		return te.validInput(stack, null);
	}

}
