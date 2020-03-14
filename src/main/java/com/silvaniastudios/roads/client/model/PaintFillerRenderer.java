package com.silvaniastudios.roads.client.model;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.tileentities.paintfiller.PaintFillerEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraftforge.client.model.animation.FastTESR;
import net.minecraftforge.fluids.FluidStack;

public class PaintFillerRenderer extends FastTESR<PaintFillerEntity> {

	private static float p = 1/16F; //one "pixel"
	private int lastWhite = 0;
	private int lastYellow = 0;
	private int lastRed = 0;
	private long lastTick = 0;
	
	@Override
	public void renderTileEntityFast(PaintFillerEntity te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {
		FluidStack white_paint = te.white_paint.getFluid();
		FluidStack yellow_paint = te.yellow_paint.getFluid();
		FluidStack red_paint = te.red_paint.getFluid();

		bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		
		int meta = te.getBlockType().getMetaFromState(te.getState());
		
		boolean wp = white_paint != null;
		boolean yp = yellow_paint != null;
		boolean rp = red_paint != null;
		
		if (wp && white_paint.amount  > 0) { renderTankFluid(buffer, x, y, z, 0, white_paint, meta); }
		if (yp && yellow_paint.amount > 0) { renderTankFluid(buffer, x, y, z, 1, yellow_paint, meta); }
		if (rp && red_paint.amount    > 0) { renderTankFluid(buffer, x, y, z, 2, red_paint, meta); }
		
		if (wp && white_paint.amount  > lastWhite)  { renderFillingFluid(buffer, x, y, z, 0, meta); }
		if (yp && yellow_paint.amount > lastYellow) { renderFillingFluid(buffer, x, y, z, 1, meta); }
		if (rp && red_paint.amount    > lastRed)    { renderFillingFluid(buffer, x, y, z, 2, meta); }
		
		//why isn't there an easier way to get a tick
		//-5 to allow 5 ticks buffer for network desyncs etc, at the cost of maybe showing it filling a quarter second longer than it should oh noooooo
		if (getWorld().getTotalWorldTime()-5 > lastTick) {
			if (wp) { lastWhite = white_paint.amount; }
			if (yp) { lastYellow = yellow_paint.amount; }
			if (rp) { lastRed = red_paint.amount; }
			lastTick = getWorld().getTotalWorldTime();
		}
		
		if (te.has_gun) {
			if (te.gun_white  > 0) { renderGunTankFluid(buffer, x, y, z, 0, te.gun_white, meta);  }
			if (te.gun_yellow > 0) { renderGunTankFluid(buffer, x, y, z, 1, te.gun_yellow, meta); }
			if (te.gun_red    > 0) { renderGunTankFluid(buffer, x, y, z, 2, te.gun_red, meta);    }
			
			if (te.fuel_remaining > 0) {
				if (wp) { if (te.gun_white  < PaintFillerEntity.GUN_TANK_CAP &&  white_paint.amount > 1000) { renderGunFillingFluid(buffer, x, y, z, 0, meta); }}
				if (yp) { if (te.gun_yellow < PaintFillerEntity.GUN_TANK_CAP && yellow_paint.amount > 1000) { renderGunFillingFluid(buffer, x, y, z, 1, meta); }}
				if (rp) { if (te.gun_red    < PaintFillerEntity.GUN_TANK_CAP &&    red_paint.amount > 1000) { renderGunFillingFluid(buffer, x, y, z, 2, meta); }}
			}
		}
	}
	
	private static void renderTankFluid(final BufferBuilder buffer, double x, double y, double z, int col, FluidStack fluid, int meta) {
		float colourOffset = col * 5.25F;
		if (fluid != null) {
			TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(fluid.getFluid().getStill(fluid).toString());
			
			if (meta == 0) { RenderHelper.renderTankFluid(buffer, x, y, z, 1.5F + colourOffset, 0.25F,  12F,                2.5F, 11.75F, 2.5F, fluid.amount, PaintFillerEntity.FILLER_TANK_CAP, sprite); }
			if (meta == 1) { RenderHelper.renderTankFluid(buffer, x, y, z, 1.5F,                0.25F, 1.5F + colourOffset, 2.5F, 11.75F, 2.5F, fluid.amount, PaintFillerEntity.FILLER_TANK_CAP, sprite); }
			if (meta == 2) { RenderHelper.renderTankFluid(buffer, x, y, z,  12F - colourOffset, 0.25F, 1.5F,                2.5F, 11.75F, 2.5F, fluid.amount, PaintFillerEntity.FILLER_TANK_CAP, sprite); }
			if (meta == 3) { RenderHelper.renderTankFluid(buffer, x, y, z,  12F,                0.25F,  12F - colourOffset, 2.5F, 11.75F, 2.5F, fluid.amount, PaintFillerEntity.FILLER_TANK_CAP, sprite); }
		}
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
