package com.silvaniastudios.roads.items;

import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.PaintColour;
import com.silvaniastudios.roads.blocks.enums.IMetaBlockName;
import com.silvaniastudios.roads.blocks.paint.customs.CustomPaintBlock;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

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
	public String getTranslationKey(ItemStack stack) {
		if (stack.getItem() instanceof ItemBlock) {
			ItemBlock ib = (ItemBlock) stack.getItem();
			Block block = ib.getBlock();
			if (block instanceof IMetaBlockName) {
				return super.getTranslationKey(stack) + "." + ((IMetaBlockName) this.block).getSpecialName(stack);
			}
		}
		return super.getTranslationKey(stack);
	}
	
	public String getItemStackDisplayName(ItemStack stack) {
		String unloc = this.getUnlocalizedNameInefficiently(stack);

		if (Block.getBlockFromItem(stack.getItem()) instanceof CustomPaintBlock) {
			CustomPaintBlock paint = (CustomPaintBlock) Block.getBlockFromItem(stack.getItem());
			if (!paint.isInternal()) {
				for (int i = 0; i < FRBlocks.col.size(); i++) {
					PaintColour paintCol = FRBlocks.col.get(i);
					if (unloc.contains(paintCol.getName())) {
						return paintCol.getFormat() + paint.getLocalName();
					}
				}
			}
		}

		if (unloc.contains("hatch_box")) {

		}

		if (Block.getBlockFromItem(stack.getItem()) instanceof IMetaBlockName) {
			if (unloc.contains("white")) { return TextFormatting.WHITE + I18n.translateToLocal(unloc + ".name").trim(); }

			for (int i = 0; i < FRBlocks.col.size(); i++) {
				PaintColour paintCol = FRBlocks.col.get(i);
				if (unloc.contains(paintCol.getName())) {
					return paintCol.getFormat() + I18n.translateToLocal(unloc.replace(paintCol.getName(), "white") + ".name").trim();
				}
			}
		}
		
        return super.getItemStackDisplayName(stack);
    }

	public String getUnformattedDisplayName(ItemStack stack) {
		String unloc = this.getUnlocalizedNameInefficiently(stack);

		if (Block.getBlockFromItem(stack.getItem()) instanceof CustomPaintBlock) {
			CustomPaintBlock paint = (CustomPaintBlock) Block.getBlockFromItem(stack.getItem());
			if (!paint.isInternal()) {
				return paint.getLocalName();
			}
		}

		if (Block.getBlockFromItem(stack.getItem()) instanceof IMetaBlockName) {
			if (unloc.contains("white")) { I18n.translateToLocal(unloc + ".name").trim(); }
			if (unloc.contains("yellow")) { I18n.translateToLocal(unloc.replace("yellow", "white") + ".name").trim(); }
			if (unloc.contains("red")) { I18n.translateToLocal(unloc.replace("red", "white") + ".name").trim(); }
		}

		return super.getItemStackDisplayName(stack);
	}
}
