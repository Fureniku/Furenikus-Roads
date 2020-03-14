package com.silvaniastudios.roads.client.model;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.tileentities.paintoven.PaintOvenEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.client.model.animation.FastTESR;
import net.minecraftforge.fluids.FluidStack;

public class PaintOvenRenderer extends FastTESR<PaintOvenEntity> {

	final TextureAtlasSprite sprite_white_paint = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":fluids/white_paint_flowing");
	final TextureAtlasSprite sprite_yellow_paint = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":fluids/yellow_paint_flowing");

	@Override
	public void renderTileEntityFast(PaintOvenEntity te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {
		FluidStack paint = te.paint.getFluid();
		FluidStack water = te.water.getFluid();
		TextureAtlasSprite waterTexture = null;
		TextureAtlasSprite paintTexture = null;
		
		if (water != null) { waterTexture = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(water.getFluid().getStill(water).toString()); }
		if (paint != null) { paintTexture = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(paint.getFluid().getStill(paint).toString()); }
		
		float f = 0.0625f;
		int meta = te.getBlockType().getMetaFromState(te.getState());
		
		if (meta == 0) {
			if (water != null) { RenderHelper.renderTankFluid(buffer, x, y, z, 3F+f, 12F+f, 1.75F+f, 10F-(f*2), 4F-(f*2), 10F-(f*2), water.amount, PaintOvenEntity.FILLER_TANK_CAP, waterTexture); }
			if (paint != null) { RenderHelper.renderTankFluid(buffer, x, y, z, 3F, 1.25F, 9.25F, 10F, 8F, 3.5F, paint.amount, PaintOvenEntity.FILLER_TANK_CAP, paintTexture); }
		}
		if (meta == 1) {
			if (water != null) { RenderHelper.renderTankFluid(buffer, x, y, z, 4.25F+f, 12F+f, 3F+f, 10F-(f*2), 4F-(f*2), 10F-(f*2), water.amount, PaintOvenEntity.FILLER_TANK_CAP, waterTexture); }
			if (paint != null) { RenderHelper.renderTankFluid(buffer, x, y, z, 3.25F, 1.25F, 3F, 3.5F, 8F, 10F, paint.amount, PaintOvenEntity.FILLER_TANK_CAP, paintTexture); }
		}
		if (meta == 2) {
			if (water != null) { RenderHelper.renderTankFluid(buffer, x, y, z, 3F+f, 12F+f, 4.75F+f, 10F-(f*2), 4F-(f*2), 10F-(f*2), water.amount, PaintOvenEntity.FILLER_TANK_CAP, waterTexture); }
			if (paint != null) { RenderHelper.renderTankFluid(buffer, x, y, z, 3F, 1.25F, 3.25F, 10F, 8F, 3.5F, paint.amount, PaintOvenEntity.FILLER_TANK_CAP, paintTexture); }
		}
		if (meta == 3) {
			if (water != null) { RenderHelper.renderTankFluid(buffer, x, y, z, 1.75F+f, 12F+f, 3F+f, 10F-(f*2), 4F-(f*2), 10F-(f*2), water.amount, PaintOvenEntity.FILLER_TANK_CAP, waterTexture); }
			if (paint != null) { RenderHelper.renderTankFluid(buffer, x, y, z, 9.25F, 1.25F, 3F, 3.5F, 8F, 10F, paint.amount, PaintOvenEntity.FILLER_TANK_CAP, paintTexture); }
		}
	}
}
