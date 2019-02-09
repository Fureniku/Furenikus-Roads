package com.silvaniastudios.roads.client.model;

import com.silvaniastudios.roads.blocks.tileentities.crusher.CrusherEntity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.animation.AnimationTESR;

public class CrusherRenderer extends AnimationTESR<CrusherEntity> {
	
	private static float p = 1/16F; //one "pixel"

	@Override
	public void renderTileEntityFast(CrusherEntity te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {
		int meta = te.getBlockType().getMetaFromState(te.getState());
		if (!te.inventory.getStackInSlot(0).isEmpty()) { renderBlock(buffer, x, y, z, meta, te.inventory.getStackInSlot(0), 0); }
	}
	
	@SuppressWarnings("deprecation")
	private static void renderBlock(final BufferBuilder buffer, double x, double y, double z, int meta, ItemStack stack, int slot) {
		if (stack.getItem() instanceof ItemBlock) {
			ItemBlock ib = (ItemBlock) stack.getItem();
			IBlockState state = ib.getBlock().getStateFromMeta(stack.getItemDamage());
			IBakedModel model = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getModelForState(state);
			TextureAtlasSprite sprite = model.getParticleTexture();
			
			if (meta == 0) { RenderHelper.renderCube(buffer, x, y, z, 6F*p, 4.5F*p, 7F*p, 4F*p, 4F*p, 4F*p, sprite); }
			if (meta == 1) { RenderHelper.renderCube(buffer, x, y, z, 7F*p, 4.5F*p, 6F*p, 4F*p, 4F*p, 4F*p, sprite); }
			if (meta == 2) { RenderHelper.renderCube(buffer, x, y, z, 6F*p, 4.5F*p, 5F*p, 4F*p, 4F*p, 4F*p, sprite); }
			if (meta == 3) { RenderHelper.renderCube(buffer, x, y, z, 5F*p, 4.5F*p, 6F*p, 4F*p, 4F*p, 4F*p, sprite); }
		}
	}

}
