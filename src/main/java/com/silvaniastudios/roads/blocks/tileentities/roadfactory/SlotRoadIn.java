package com.silvaniastudios.roads.blocks.tileentities.roadfactory;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotRoadIn extends SlotItemHandler {

	public SlotRoadIn(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(@Nullable ItemStack stack) {
		if (stack.getItem() instanceof ItemBlock) {
			ItemBlock ib = (ItemBlock) stack.getItem();
			Block block = ib.getBlock();
			int meta = stack.getItemDamage();
			if (block == Blocks.GRAVEL || block == Blocks.COBBLESTONE || block == Blocks.DIRT || block == Blocks.GRASS || block == Blocks.SAND) {
				return true;
			}
			if (block == Blocks.STONE) {
				if (meta == 0 || meta == 1 || meta == 3 || meta == 5 || meta == 6) { return true; }
			}
		}
		return false;
	}
}
