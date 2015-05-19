package co.uk.silvania.roads.client.render;

import co.uk.silvania.roads.blocks.FRBlocks;
import co.uk.silvania.roads.blocks.RoadBlock;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;

public class RoadBlockItemRenderingHandler implements IItemRenderer {

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        
		
	}
	
	public double quadHeight(int meta) {
		double m = meta + 1.0;
		return ((1.0 / 16.0) * m);
	}

}
