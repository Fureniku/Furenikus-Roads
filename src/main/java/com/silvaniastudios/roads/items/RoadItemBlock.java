package com.silvaniastudios.roads.items;

import com.silvaniastudios.roads.blocks.enums.IMetaBlockName;
import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

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
	
	public String getItemStackDisplayName(ItemStack stack) {
		String unloc = this.getUnlocalizedNameInefficiently(stack);
		
		if (Block.getBlockFromItem(stack.getItem()) instanceof PaintBlockBase) {
			if (unloc.contains("white")) { return TextFormatting.WHITE + super.getItemStackDisplayName(stack); }
			if (unloc.contains("yellow")) { return TextFormatting.YELLOW + super.getItemStackDisplayName(stack); }
			if (unloc.contains("red")) { return TextFormatting.RED + super.getItemStackDisplayName(stack); }
		}
		
        return super.getItemStackDisplayName(stack);
    }
}
