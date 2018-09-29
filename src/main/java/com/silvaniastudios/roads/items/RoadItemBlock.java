package com.silvaniastudios.roads.items;

import com.silvaniastudios.roads.blocks.enums.IMetaBlockName;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class RoadItemBlock extends ItemBlock {
	
	public RoadItemBlock(Block block) {
		super(block);
		this.setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata(int dmg) {
		return dmg;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		if (stack.getItem() instanceof ItemBlock) {
			ItemBlock ib = (ItemBlock) stack.getItem();
			Block block = ib.getBlock();
			if (block instanceof IMetaBlockName) {
				return super.getUnlocalizedName(stack) + "." + ((IMetaBlockName) this.block).getSpecialName(stack);
			}
		}
		return super.getUnlocalizedName(stack);
	}

}
