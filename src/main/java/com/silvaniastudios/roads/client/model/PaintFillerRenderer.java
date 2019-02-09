package com.silvaniastudios.roads.client.model;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.tileentities.paintfiller.PaintFillerEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemDye;
import net.minecraftforge.client.model.animation.FastTESR;
import net.minecraftforge.fluids.FluidStack;

public class PaintFillerRenderer extends FastTESR<PaintFillerEntity> {

	private static float p = 1/16F; //one "pixel"
	
	@Override
	public void renderTileEntityFast(PaintFillerEntity te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {
		FluidStack white_paint = te.white_paint.getFluid();
		FluidStack yellow_paint = te.yellow_paint.getFluid();
		FluidStack red_paint = te.red_paint.getFluid();

		bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		
		int meta = te.getBlockType().getMetaFromState(te.getState());
		
		if (white_paint  != null && white_paint.amount  > 0) { renderTankFluid(buffer, x, y, z, 0, white_paint.amount, meta); }
		if (yellow_paint != null && yellow_paint.amount > 0) { renderTankFluid(buffer, x, y, z, 1, yellow_paint.amount, meta); }
		if (red_paint    != null && red_paint.amount    > 0) { renderTankFluid(buffer, x, y, z, 2, red_paint.amount, meta); }
		
		if (te.fuel_remaining > 0) {
			if (white_paint.amount  < PaintFillerEntity.FILLER_TANK_CAP-1000 && te.inventory.getStackInSlot(0).getItem() instanceof ItemDye) { renderFillingFluid(buffer, x, y, z, 0, meta); }
			if (yellow_paint.amount < PaintFillerEntity.FILLER_TANK_CAP-1000 && te.inventory.getStackInSlot(2).getItem() instanceof ItemDye) { renderFillingFluid(buffer, x, y, z, 1, meta); }
			if (red_paint.amount    < PaintFillerEntity.FILLER_TANK_CAP-1000 && te.inventory.getStackInSlot(3).getItem() instanceof ItemDye) { renderFillingFluid(buffer, x, y, z, 2, meta); }
		}
		
		if (te.has_gun) {
			if (te.gun_white  > 0) { renderGunTankFluid(buffer, x, y, z, 0, te.gun_white, meta);  }
			if (te.gun_yellow > 0) { renderGunTankFluid(buffer, x, y, z, 1, te.gun_yellow, meta); }
			if (te.gun_red    > 0) { renderGunTankFluid(buffer, x, y, z, 2, te.gun_red, meta);    }
			
			if (te.fuel_remaining > 0) {
				if (te.gun_white  < PaintFillerEntity.GUN_TANK_CAP &&  white_paint.amount > 1000) { renderGunFillingFluid(buffer, x, y, z, 0, meta); }
				if (te.gun_yellow < PaintFillerEntity.GUN_TANK_CAP && yellow_paint.amount > 1000) { renderGunFillingFluid(buffer, x, y, z, 1, meta); }
				if (te.gun_red    < PaintFillerEntity.GUN_TANK_CAP &&    red_paint.amount > 1000) { renderGunFillingFluid(buffer, x, y, z, 2, meta); }
			}
		}
	}
	
	private static void renderTankFluid(final BufferBuilder buffer, double x, double y, double z, int col, int tankFill, int meta) {
		float colourOffset = col * 0.328125F;
		
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":items/paint_white");
		if (col == 1) { sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":items/paint_yellow"); }
		if (col == 2) { sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":items/paint_red"); }

		float fill = 0.00734375F * RenderHelper.getPercentage(tankFill, PaintFillerEntity.FILLER_TANK_CAP);
		
		if (meta == 0) { RenderHelper.renderCube(buffer, x, y, z, 1.5F*p + colourOffset, 0.25F*p,  12F*p,                2.5F*p, fill, 2.5F*p, sprite); }
		if (meta == 1) { RenderHelper.renderCube(buffer, x, y, z, 1.5F*p,                0.25F*p, 1.5F*p + colourOffset, 2.5F*p, fill, 2.5F*p, sprite); }
		if (meta == 2) { RenderHelper.renderCube(buffer, x, y, z,  12F*p - colourOffset, 0.25F*p, 1.5F*p,                2.5F*p, fill, 2.5F*p, sprite); }
		if (meta == 3) { RenderHelper.renderCube(buffer, x, y, z,  12F*p,                0.25F*p,  12F*p - colourOffset, 2.5F*p, fill, 2.5F*p, sprite); }
	}
	
	private static void renderFillingFluid(final BufferBuilder buffer, double x, double y, double z, int col, int meta) {
		float colourOffset = col * 0.328125F;
		
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":items/paint_white");
		if (col == 1) { sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":items/paint_yellow"); }
		if (col == 2) { sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":items/paint_red"); }
		
		if (meta == 0) { RenderHelper.renderCube(buffer, x, y, z, 2.5F*p + colourOffset, 0.25F*p,  13F*p,                0.5F*p, 12F*p, 0.5F*p, sprite); }
		if (meta == 1) { RenderHelper.renderCube(buffer, x, y, z, 2.5F*p,                0.25F*p, 2.5F*p + colourOffset, 0.5F*p, 12F*p, 0.5F*p, sprite); }
		if (meta == 2) { RenderHelper.renderCube(buffer, x, y, z,  13F*p - colourOffset, 0.25F*p, 2.5F*p,                0.5F*p, 12F*p, 0.5F*p, sprite); }
		if (meta == 3) { RenderHelper.renderCube(buffer, x, y, z,  13F*p,                0.25F*p,  13F*p - colourOffset, 0.5F*p, 12F*p, 0.5F*p, sprite); }
	}
	
	private static void renderGunTankFluid(final BufferBuilder buffer, double x, double y, double z, int col, int tankFill, int meta) {
		float colourOffset = col * (2F/16F);
		float fill = ((0.85F/16F)/100F) * RenderHelper.getPercentage(tankFill, PaintFillerEntity.GUN_TANK_CAP);
		
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":items/paint_white");
		if (col == 1) { sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":items/paint_yellow"); }
		if (col == 2) { sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":items/paint_red"); }
		
		if (meta == 0) { RenderHelper.renderCube(buffer, x, y, z, 11.5625F*p, 8.8F*p + colourOffset,  4.4375F*p,  0.875F*p, fill, 1.9375F*p, sprite); }
		if (meta == 1) { RenderHelper.renderCube(buffer, x, y, z,  9.5625F*p, 8.8F*p + colourOffset, 11.5625F*p, 1.9375F*p, fill,  0.875F*p, sprite); }
		if (meta == 2) { RenderHelper.renderCube(buffer, x, y, z,  3.5625F*p, 8.8F*p + colourOffset,  9.5625F*p,  0.875F*p, fill, 1.9375F*p, sprite); }
		if (meta == 3) { RenderHelper.renderCube(buffer, x, y, z,  4.4375F*p, 8.8F*p + colourOffset,  3.5625F*p, 1.9375F*p, fill,  0.875F*p, sprite); }
	}
	
	private static void renderGunFillingFluid(final BufferBuilder buffer, double x, double y, double z, int col, int meta) {
		float colourOffset = col * (2F/16F);
		
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":items/paint_white");
		if (col == 1) { sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":items/paint_yellow"); }
		if (col == 2) { sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":items/paint_red"); }
		
		if (meta == 0) { RenderHelper.renderCube(buffer, x, y, z,  11.75F*p, 8.8F*p + colourOffset, 6.125F*p,  0.5F*p, 0.7F*p, 0.25F*p, sprite); }
		if (meta == 1) { RenderHelper.renderCube(buffer, x, y, z, 9.5625F*p, 8.8F*p + colourOffset,  11.75F*p, 0.25F*p, 0.7F*p,  0.5F*p, sprite); }
		if (meta == 2) { RenderHelper.renderCube(buffer, x, y, z,   3.75F*p, 8.8F*p + colourOffset, 9.5625F*p,  0.5F*p, 0.7F*p, 0.25F*p, sprite); }
		if (meta == 3) { RenderHelper.renderCube(buffer, x, y, z,  6.125F*p, 8.8F*p + colourOffset,   3.75F*p, 0.25F*p, 0.7F*p,  0.5F*p, sprite); }
		
	}
}
