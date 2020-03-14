package com.silvaniastudios.roads.client.model;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.tileentities.paintfiller.hopper.PaintFillerHopperEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.animation.FastTESR;
import net.minecraftforge.fluids.FluidStack;

public class PaintFillerHopperRenderer extends FastTESR<PaintFillerHopperEntity> {
	
	private static float p = 1/16F; //one "pixel"

	final TextureAtlasSprite sprite_glass = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":blocks/hopper_tank");
	final TextureAtlasSprite sprite_glass_top = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":blocks/hopper_tank_top");
	
	final TextureAtlasSprite sprite_light_white = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":blocks/hopper_type_white");
	final TextureAtlasSprite sprite_light_yellow = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":blocks/hopper_type_yellow");
	final TextureAtlasSprite sprite_light_red = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":blocks/hopper_type_red");
	final TextureAtlasSprite sprite_light_item = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":blocks/hopper_type_item");
	final TextureAtlasSprite sprite_light_none = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":blocks/hopper_type_none");
	

	@Override
	public void renderTileEntityFast(PaintFillerHopperEntity te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {
		FluidStack paint_white = te.white_paint.getFluid();
		FluidStack paint_yellow = te.yellow_paint.getFluid();
		FluidStack paint_red = te.red_paint.getFluid();
		
		TextureAtlasSprite sprite_white_paint = null;
		TextureAtlasSprite sprite_yellow_paint = null;
		TextureAtlasSprite sprite_red_paint = null;
		
		if (paint_white != null) { sprite_white_paint = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(paint_white.getFluid().getStill(paint_white).toString()); }
		if (paint_yellow != null) { sprite_yellow_paint = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(paint_yellow.getFluid().getStill(paint_yellow).toString()); }
		if (paint_red != null) { sprite_red_paint = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(paint_red.getFluid().getStill(paint_red).toString()); }
		
		
		int up = te.getSideUp();
		int north = te.getSideNorth();
		int east = te.getSideEast();
		int south = te.getSideSouth();
		int west = te.getSideWest();
		
		if (up > 0) {
			if (up == 1) { renderSide(buffer, x, y, z, 3, 12, 3, 10, 3, 10, paint_white, te, up, sprite_white_paint, EnumFacing.UP); }
			if (up == 2) { renderSide(buffer, x, y, z, 3, 12, 3, 10, 3, 10, paint_yellow, te, up, sprite_yellow_paint, EnumFacing.UP); }
			if (up == 3) { renderSide(buffer, x, y, z, 3, 12, 3, 10, 3, 10, paint_red, te, up, sprite_red_paint, EnumFacing.UP); }
			if (up == 4) { renderSide(buffer, x, y, z, 3, 12, 3, 10, 3, 10, null, te, up, null, EnumFacing.UP); }
		}
		
		if (north > 0) {
			if (north == 1) { renderSide(buffer, x, y, z, 3, 4, 1, 10, 8, 2, paint_white, te, north, sprite_white_paint, EnumFacing.NORTH); }
			if (north == 2) { renderSide(buffer, x, y, z, 3, 4, 1, 10, 8, 2, paint_yellow, te, north, sprite_yellow_paint, EnumFacing.NORTH); }
			if (north == 3) { renderSide(buffer, x, y, z, 3, 4, 1, 10, 8, 2, paint_red, te, north, sprite_red_paint, EnumFacing.NORTH); }
			if (north == 4) { renderSide(buffer, x, y, z, 3, 4, 1, 10, 8, 2, null, te, north, null, EnumFacing.NORTH); }
		}
		
		if (east > 0) {
			if (east == 1) { renderSide(buffer, x, y, z, 13, 4, 3, 2, 8, 10, paint_white, te, east, sprite_white_paint, EnumFacing.EAST); }
			if (east == 2) { renderSide(buffer, x, y, z, 13, 4, 3, 2, 8, 10, paint_yellow, te, east, sprite_yellow_paint, EnumFacing.EAST); }
			if (east == 3) { renderSide(buffer, x, y, z, 13, 4, 3, 2, 8, 10, paint_red, te, east, sprite_red_paint, EnumFacing.EAST); }
			if (east == 4) { renderSide(buffer, x, y, z, 13, 4, 3, 2, 8, 10, null, te, east, null, EnumFacing.EAST); }
		}
		
		if (south > 0) {
			if (south == 1) { renderSide(buffer, x, y, z, 3, 4, 13, 10, 8, 2, paint_white, te, south, sprite_white_paint, EnumFacing.SOUTH); }
			if (south == 2) { renderSide(buffer, x, y, z, 3, 4, 13, 10, 8, 2, paint_yellow, te, south, sprite_yellow_paint, EnumFacing.SOUTH); }
			if (south == 3) { renderSide(buffer, x, y, z, 3, 4, 13, 10, 8, 2, paint_red, te, south, sprite_red_paint, EnumFacing.SOUTH); }
			if (south == 4) { renderSide(buffer, x, y, z, 3, 4, 13, 10, 8, 2, null, te, south, null, EnumFacing.SOUTH); }
		}
		
		if (west > 0) {
			if (west == 1) { renderSide(buffer, x, y, z, 1, 4, 3, 2, 8, 10, paint_white, te, west, sprite_white_paint, EnumFacing.WEST); }
			if (west == 2) { renderSide(buffer, x, y, z, 1, 4, 3, 2, 8, 10, paint_yellow, te, west, sprite_yellow_paint, EnumFacing.WEST); }
			if (west == 3) { renderSide(buffer, x, y, z, 1, 4, 3, 2, 8, 10, paint_red, te, west, sprite_red_paint, EnumFacing.WEST); }
			if (west == 4) { renderSide(buffer, x, y, z, 1, 4, 3, 2, 8, 10, null, te, west, null, EnumFacing.WEST); }
		}
		
	}
	
	private void renderSide(final BufferBuilder buffer, double x, double y, double z,
			float posX, float posY, float posZ, float sizeX, float sizeY, float sizeZ,
			FluidStack fluid, PaintFillerHopperEntity te, int type, TextureAtlasSprite sprite, EnumFacing rot) {
		
		
		TextureAtlasSprite sprite_type = sprite_light_none;
		
		if (type == 1) { sprite_type = sprite_light_white; }
		if (type == 2) { sprite_type = sprite_light_yellow; }
		if (type == 3) { sprite_type = sprite_light_red; }
		if (type == 4) { sprite_type = sprite_light_item; }
		
		renderLight(buffer, x, y, z, rot, te, sprite_type);
		
		if (type < 4) {
			if (rot == EnumFacing.UP) {
				renderTank(buffer, x, y, z, posX, posY, posZ, sizeX, sizeY, sizeZ, te, sprite_glass_top, rot);
			} else {
				renderTank(buffer, x, y, z, posX, posY, posZ, sizeX, sizeY, sizeZ, te, sprite_glass, rot);
			}
			
			if (fluid != null && fluid.amount > 0) {
				float f = 0.0625f; //Tiny inset amount for fluids to avoid z-fighting
				RenderHelper.renderTankFluid(buffer, x, y, z, posX+f, posY+f, posZ+f, sizeX-(f*2), sizeY-(f*2), sizeZ-(f*2),
						fluid.amount, PaintFillerHopperEntity.FILLER_TANK_CAP, sprite);
			}
		}
	}
	
	private static void renderLight(final BufferBuilder buffer, double x, double y, double z, EnumFacing rot, PaintFillerHopperEntity te, TextureAtlasSprite sprite) {
		if (rot == EnumFacing.NORTH) {
			RenderHelper.renderCube(buffer, x, y, z, 7f*p,    11.5f*p, 0.5f*p,    2f*p, 0.25f*p, 0.5f*p, sprite);
			RenderHelper.renderCube(buffer, x, y, z, 11.5f*p,    7f*p, 0.5f*p, 0.25f*p,    2f*p, 0.5f*p, sprite);
			RenderHelper.renderCube(buffer, x, y, z, 4.25f*p,    7f*p, 0.5f*p, 0.25f*p,    2f*p, 0.5f*p, sprite);
			RenderHelper.renderCube(buffer, x, y, z, 7f*p,    4.25f*p, 0.5f*p,    2f*p, 0.25f*p, 0.5f*p, sprite);
			
			RenderHelper.renderCube(buffer, x, y, z, 7.5f*p, 7.5f*p, 0.25f*p, 1f*p, 1f*p, 0.25f*p, sprite);
		}
		if (rot == EnumFacing.EAST) {
			RenderHelper.renderCube(buffer, x, y, z, 15f*p, 11.5f*p,    7f*p, 0.5f*p, 0.25f*p,    2f*p, sprite);
			RenderHelper.renderCube(buffer, x, y, z, 15f*p,    7f*p, 4.25f*p, 0.5f*p,    2f*p, 0.25f*p, sprite);
			RenderHelper.renderCube(buffer, x, y, z, 15f*p,    7f*p, 11.5f*p, 0.5f*p,    2f*p, 0.25f*p, sprite);
			RenderHelper.renderCube(buffer, x, y, z, 15f*p, 4.25f*p,    7f*p, 0.5f*p, 0.25f*p,    2f*p, sprite);
			
			RenderHelper.renderCube(buffer, x, y, z, 15.5f*p, 7.5f*p, 7.5f*p, 0.25f*p, 1f*p, 1f*p, sprite);
		}
		if (rot == EnumFacing.SOUTH) {
			RenderHelper.renderCube(buffer, x, y, z, 7f*p,    11.5f*p, 15f*p,    2f*p, 0.25f*p, 0.5f*p, sprite);
			RenderHelper.renderCube(buffer, x, y, z, 11.5f*p,    7f*p, 15f*p, 0.25f*p,    2f*p, 0.5f*p, sprite);
			RenderHelper.renderCube(buffer, x, y, z, 4.25f*p,    7f*p, 15f*p, 0.25f*p,    2f*p, 0.5f*p, sprite);
			RenderHelper.renderCube(buffer, x, y, z, 7f*p,    4.25f*p, 15f*p,    2f*p, 0.25f*p, 0.5f*p, sprite);
			
			RenderHelper.renderCube(buffer, x, y, z, 7.5f*p, 7.5f*p, 15.5f*p, 1f*p, 1f*p, 0.25f*p, sprite);
		}
		if (rot == EnumFacing.WEST) {
			RenderHelper.renderCube(buffer, x, y, z, 0.5f*p, 11.5f*p,    7f*p, 0.5f*p, 0.25f*p,    2f*p, sprite);
			RenderHelper.renderCube(buffer, x, y, z, 0.5f*p,    7f*p, 4.25f*p, 0.5f*p,    2f*p, 0.25f*p, sprite);
			RenderHelper.renderCube(buffer, x, y, z, 0.5f*p,    7f*p, 11.5f*p, 0.5f*p,    2f*p, 0.25f*p, sprite);
			RenderHelper.renderCube(buffer, x, y, z, 0.5f*p, 4.25f*p,    7f*p, 0.5f*p, 0.25f*p,    2f*p, sprite);
			
			RenderHelper.renderCube(buffer, x, y, z, 0.25f*p, 7.5f*p, 7.5f*p, 0.25f*p, 1f*p, 1f*p, sprite);
		}
		
		if (rot == EnumFacing.UP) {
			RenderHelper.renderCube(buffer, x, y, z,    7f*p, 15f*p, 4.25f*p,    2f*p, 0.5f*p, 0.25f*p, sprite);
			RenderHelper.renderCube(buffer, x, y, z, 11.5f*p, 15f*p,    7f*p, 0.25f*p, 0.5f*p,    2f*p, sprite);
			RenderHelper.renderCube(buffer, x, y, z,    7f*p, 15f*p, 11.5f*p,    2f*p, 0.5f*p, 0.25f*p, sprite);
			RenderHelper.renderCube(buffer, x, y, z, 4.25f*p, 15f*p,    7f*p, 0.25f*p, 0.5f*p,    2f*p, sprite);
			
			RenderHelper.renderCube(buffer, x, y, z, 7.5f*p, 15.25f*p, 7.5f*p, 1f*p, 0.5f*p, 1f*p, sprite);
		}
	}
	
	private static void renderTank(final BufferBuilder buffer, double x, double y, double z, 
			float posX, float posY, float posZ, float sizeX, float sizeY, float sizeZ,
			PaintFillerHopperEntity te, TextureAtlasSprite sprite, EnumFacing facing) {
		renderGlassTankCube(buffer, x, y, z, posX*p, posY*p, posZ*p, sizeX*p, sizeY*p, sizeZ*p, sprite, facing);
	}
	
	public static void renderGlassTankCube(final BufferBuilder buffer, double x, double y, double z, float xLow, float yLow, float zLow, float xSize, float ySize, float zSize, TextureAtlasSprite texture, EnumFacing facing) {
		final int red = (int) (0xFF);
		final int green = (int) (0xFF);
		final int blue = (int) (0xFF);
		final int alpha = 0xFF;
		
		final double u = texture.getMinU();
		final double v = texture.getMinV();

		double minU = texture.getMinU(); //3
		double maxU = texture.getMaxU(); //13
		double minV = texture.getMinV(); //2
		double maxV = texture.getMaxV(); //4
		
		final double uSize = maxU - minU;
		final double vSize = maxV - minV;

		float xHigh = xLow + xSize;
		float yHigh = yLow + ySize;
		float zHigh = zLow + zSize;
		
		if (facing == EnumFacing.NORTH || facing == EnumFacing.SOUTH) {
			//Up
			minU = u + ((uSize/16.0)*3.0);
			maxU = u + ((uSize/16.0)*13.0);
			minV = v + ((vSize/16.0)*2.0);
			maxV = v + ((vSize/16.0)*4.0);
			buffer.pos(x + xHigh, y + yHigh, z + zHigh).color(red, green, blue, alpha).tex(minU, maxV).lightmap(240, 0).endVertex(); //SE
			buffer.pos(x + xHigh, y + yHigh, z +  zLow).color(red, green, blue, alpha).tex(minU, minV).lightmap(240, 0).endVertex(); //NE
			buffer.pos(x +  xLow, y + yHigh, z +  zLow).color(red, green, blue, alpha).tex(maxU, minV).lightmap(240, 0).endVertex(); //NW
			buffer.pos(x +  xLow, y + yHigh, z + zHigh).color(red, green, blue, alpha).tex(maxU, maxV).lightmap(240, 0).endVertex(); //SW

			//Down
			minU = u + ((uSize/16.0)*3.0);
			maxU = u + ((uSize/16.0)*13.0);
			minV = v + ((vSize/16.0)*12.0);
			maxV = v + ((vSize/16.0)*14.0);
			buffer.pos(x + xHigh, y +  yLow, z +  zLow).color(red, green, blue, alpha).tex(minU, maxV).lightmap(168, 0).endVertex(); //SE
			buffer.pos(x + xHigh, y +  yLow, z + zHigh).color(red, green, blue, alpha).tex(minU, minV).lightmap(168, 0).endVertex(); //NE
			buffer.pos(x +  xLow, y +  yLow, z + zHigh).color(red, green, blue, alpha).tex(maxU, minV).lightmap(168, 0).endVertex(); //NW
			buffer.pos(x +  xLow, y +  yLow, z +  zLow).color(red, green, blue, alpha).tex(maxU, maxV).lightmap(168, 0).endVertex(); //SW
	
			if (facing == EnumFacing.NORTH) {
				//North
				minU = u + ((uSize/16.0)*3.0);
				maxU = u + ((uSize/16.0)*13.0);
				minV = v + ((vSize/16.0)*4.0);
				maxV = v + ((vSize/16.0)*12.0);
				buffer.pos(x +  xLow, y +  yLow, z +  zLow).color(red, green, blue, alpha).tex(minU, maxV).lightmap(216, 0).endVertex(); //SE
				buffer.pos(x +  xLow, y + yHigh, z +  zLow).color(red, green, blue, alpha).tex(minU, minV).lightmap(216, 0).endVertex(); //NE
				buffer.pos(x + xHigh, y + yHigh, z +  zLow).color(red, green, blue, alpha).tex(maxU, minV).lightmap(216, 0).endVertex(); //NW
				buffer.pos(x + xHigh, y +  yLow, z +  zLow).color(red, green, blue, alpha).tex(maxU, maxV).lightmap(216, 0).endVertex(); //SW
			}
			
			if (facing == EnumFacing.SOUTH) {
				//South
				minU = u + ((uSize/16.0)*3.0);
				maxU = u + ((uSize/16.0)*13.0);
				minV = v + ((vSize/16.0)*4.0);
				maxV = v + ((vSize/16.0)*12.0);
				buffer.pos(x + xHigh, y +  yLow, z + zHigh).color(red, green, blue, alpha).tex(minU, maxV).lightmap(216, 0).endVertex(); //SE
				buffer.pos(x + xHigh, y + yHigh, z + zHigh).color(red, green, blue, alpha).tex(minU, minV).lightmap(216, 0).endVertex(); //NE
				buffer.pos(x +  xLow, y + yHigh, z + zHigh).color(red, green, blue, alpha).tex(maxU, minV).lightmap(216, 0).endVertex(); //NW
				buffer.pos(x +  xLow, y +  yLow, z + zHigh).color(red, green, blue, alpha).tex(maxU, maxV).lightmap(216, 0).endVertex(); //SW
			}
			//West
			minU = u + ((uSize/16.0)*1.0);
			maxU = u + ((uSize/16.0)*3.0);
			minV = v + ((vSize/16.0)*4.0);
			maxV = v + ((vSize/16.0)*12.0);
			buffer.pos(x +  xLow, y +  yLow, z +  zLow).color(red, green, blue, alpha).tex(minU, maxV).lightmap(192, 0).endVertex(); //SE
			buffer.pos(x +  xLow, y + yHigh, z +  zLow).color(red, green, blue, alpha).tex(minU, minV).lightmap(192, 0).endVertex(); //NE
			buffer.pos(x +  xLow, y + yHigh, z + zHigh).color(red, green, blue, alpha).tex(maxU, minV).lightmap(192, 0).endVertex(); //NW
			buffer.pos(x +  xLow, y +  yLow, z + zHigh).color(red, green, blue, alpha).tex(maxU, maxV).lightmap(192, 0).endVertex(); //SW

			//East
			minU = u + ((uSize/16.0)*13.0);
			maxU = u + ((uSize/16.0)*15.0);
			minV = v + ((vSize/16.0)*4.0);
			maxV = v + ((vSize/16.0)*12.0);
			buffer.pos(x + xHigh, y +  yLow, z + zHigh).color(red, green, blue, alpha).tex(minU, maxV).lightmap(192, 0).endVertex(); //SE
			buffer.pos(x + xHigh, y + yHigh, z + zHigh).color(red, green, blue, alpha).tex(minU, minV).lightmap(192, 0).endVertex(); //NE
			buffer.pos(x + xHigh, y + yHigh, z +  zLow).color(red, green, blue, alpha).tex(maxU, minV).lightmap(192, 0).endVertex(); //NW
			buffer.pos(x + xHigh, y +  yLow, z +  zLow).color(red, green, blue, alpha).tex(maxU, maxV).lightmap(192, 0).endVertex(); //SW
				
		} else if (facing == EnumFacing.EAST || facing == EnumFacing.WEST) {
			//Up
			minU = u + ((uSize/16.0)*3.0);
			maxU = u + ((uSize/16.0)*13.0);
			minV = v + ((vSize/16.0)*2.0);
			maxV = v + ((vSize/16.0)*4.0);
			buffer.pos(x + xHigh, y + yHigh, z + zHigh).color(red, green, blue, alpha).tex(minU, minV).lightmap(240, 0).endVertex(); //SE
			buffer.pos(x + xHigh, y + yHigh, z +  zLow).color(red, green, blue, alpha).tex(maxU, minV).lightmap(240, 0).endVertex(); //NE
			buffer.pos(x +  xLow, y + yHigh, z +  zLow).color(red, green, blue, alpha).tex(maxU, maxV).lightmap(240, 0).endVertex(); //NW
			buffer.pos(x +  xLow, y + yHigh, z + zHigh).color(red, green, blue, alpha).tex(minU, maxV).lightmap(240, 0).endVertex(); //SW
			
			//Down
			minU = u + ((uSize/16.0)*3.0);
			maxU = u + ((uSize/16.0)*13.0);
			minV = v + ((vSize/16.0)*12.0);
			maxV = v + ((vSize/16.0)*14.0);
			buffer.pos(x + xHigh, y +  yLow, z +  zLow).color(red, green, blue, alpha).tex(minU, minV).lightmap(168, 0).endVertex(); //SE
			buffer.pos(x + xHigh, y +  yLow, z + zHigh).color(red, green, blue, alpha).tex(maxU, minV).lightmap(168, 0).endVertex(); //NE
			buffer.pos(x +  xLow, y +  yLow, z + zHigh).color(red, green, blue, alpha).tex(maxU, maxV).lightmap(168, 0).endVertex(); //NW
			buffer.pos(x +  xLow, y +  yLow, z +  zLow).color(red, green, blue, alpha).tex(minU, maxV).lightmap(168, 0).endVertex(); //SW
			
			//North
			minU = u + ((uSize/16.0)*1.0);
			maxU = u + ((uSize/16.0)*3.0);
			minV = v + ((vSize/16.0)*4.0);
			maxV = v + ((vSize/16.0)*12.0);
			buffer.pos(x +  xLow, y +  yLow, z +  zLow).color(red, green, blue, alpha).tex(minU, maxV).lightmap(216, 0).endVertex(); //SE
			buffer.pos(x +  xLow, y + yHigh, z +  zLow).color(red, green, blue, alpha).tex(minU, minV).lightmap(216, 0).endVertex(); //NE
			buffer.pos(x + xHigh, y + yHigh, z +  zLow).color(red, green, blue, alpha).tex(maxU, minV).lightmap(216, 0).endVertex(); //NW
			buffer.pos(x + xHigh, y +  yLow, z +  zLow).color(red, green, blue, alpha).tex(maxU, maxV).lightmap(216, 0).endVertex(); //SW
			
			//South
			minU = u + ((uSize/16.0)*13.0);
			maxU = u + ((uSize/16.0)*15.0);
			minV = v + ((vSize/16.0)*4.0);
			maxV = v + ((vSize/16.0)*12.0);
			buffer.pos(x + xHigh, y +  yLow, z + zHigh).color(red, green, blue, alpha).tex(minU, maxV).lightmap(216, 0).endVertex(); //SE
			buffer.pos(x + xHigh, y + yHigh, z + zHigh).color(red, green, blue, alpha).tex(minU, minV).lightmap(216, 0).endVertex(); //NE
			buffer.pos(x +  xLow, y + yHigh, z + zHigh).color(red, green, blue, alpha).tex(maxU, minV).lightmap(216, 0).endVertex(); //NW
			buffer.pos(x +  xLow, y +  yLow, z + zHigh).color(red, green, blue, alpha).tex(maxU, maxV).lightmap(216, 0).endVertex(); //SW
			
			if (facing == EnumFacing.WEST) {
				//West
				minU = u + ((uSize/16.0)*3.0);
				maxU = u + ((uSize/16.0)*13.0);
				minV = v + ((vSize/16.0)*4.0);
				maxV = v + ((vSize/16.0)*12.0);
				buffer.pos(x +  xLow, y +  yLow, z +  zLow).color(red, green, blue, alpha).tex(minU, maxV).lightmap(192, 0).endVertex(); //SE
				buffer.pos(x +  xLow, y + yHigh, z +  zLow).color(red, green, blue, alpha).tex(minU, minV).lightmap(192, 0).endVertex(); //NE
				buffer.pos(x +  xLow, y + yHigh, z + zHigh).color(red, green, blue, alpha).tex(maxU, minV).lightmap(192, 0).endVertex(); //NW
				buffer.pos(x +  xLow, y +  yLow, z + zHigh).color(red, green, blue, alpha).tex(maxU, maxV).lightmap(192, 0).endVertex(); //SW
			}
			if (facing == EnumFacing.EAST) {
				//East
				minU = u + ((uSize/16.0)*3.0);
				maxU = u + ((uSize/16.0)*13.0);
				minV = v + ((vSize/16.0)*4.0);
				maxV = v + ((vSize/16.0)*12.0);
				buffer.pos(x + xHigh, y +  yLow, z + zHigh).color(red, green, blue, alpha).tex(minU, maxV).lightmap(192, 0).endVertex(); //SE
				buffer.pos(x + xHigh, y + yHigh, z + zHigh).color(red, green, blue, alpha).tex(minU, minV).lightmap(192, 0).endVertex(); //NE
				buffer.pos(x + xHigh, y + yHigh, z +  zLow).color(red, green, blue, alpha).tex(maxU, minV).lightmap(192, 0).endVertex(); //NW
				buffer.pos(x + xHigh, y +  yLow, z +  zLow).color(red, green, blue, alpha).tex(maxU, maxV).lightmap(192, 0).endVertex(); //SW
			}
		} else {
			//Up
			minU = u + ((uSize/16.0)*3.0);
			maxU = u + ((uSize/16.0)*13.0);
			minV = v + ((vSize/16.0)*3.0);
			maxV = v + ((vSize/16.0)*13.0);
			buffer.pos(x + xHigh, y + yHigh, z + zHigh).color(red, green, blue, alpha).tex(minU, maxV).lightmap(240, 0).endVertex(); //SE
			buffer.pos(x + xHigh, y + yHigh, z +  zLow).color(red, green, blue, alpha).tex(minU, minV).lightmap(240, 0).endVertex(); //NE
			buffer.pos(x +  xLow, y + yHigh, z +  zLow).color(red, green, blue, alpha).tex(maxU, minV).lightmap(240, 0).endVertex(); //NW
			buffer.pos(x +  xLow, y + yHigh, z + zHigh).color(red, green, blue, alpha).tex(maxU, maxV).lightmap(240, 0).endVertex(); //SW
			
			//North
			minU = u + ((uSize/16.0)*3.0);
			maxU = u + ((uSize/16.0)*13.0);
			minV = v + ((vSize/16.0)*0.0);
			maxV = v + ((vSize/16.0)*3.0);
			buffer.pos(x +  xLow, y +  yLow, z +  zLow).color(red, green, blue, alpha).tex(minU, maxV).lightmap(216, 0).endVertex(); //SE
			buffer.pos(x +  xLow, y + yHigh, z +  zLow).color(red, green, blue, alpha).tex(minU, minV).lightmap(216, 0).endVertex(); //NE
			buffer.pos(x + xHigh, y + yHigh, z +  zLow).color(red, green, blue, alpha).tex(maxU, minV).lightmap(216, 0).endVertex(); //NW
			buffer.pos(x + xHigh, y +  yLow, z +  zLow).color(red, green, blue, alpha).tex(maxU, maxV).lightmap(216, 0).endVertex(); //SW
			
			//South
			minU = u + ((uSize/16.0)*3.0);
			maxU = u + ((uSize/16.0)*13.0);
			minV = v + ((vSize/16.0)*13.0);
			maxV = v + ((vSize/16.0)*16.0);
			buffer.pos(x + xHigh, y +  yLow, z + zHigh).color(red, green, blue, alpha).tex(minU, maxV).lightmap(216, 0).endVertex(); //SE
			buffer.pos(x + xHigh, y + yHigh, z + zHigh).color(red, green, blue, alpha).tex(minU, minV).lightmap(216, 0).endVertex(); //NE
			buffer.pos(x +  xLow, y + yHigh, z + zHigh).color(red, green, blue, alpha).tex(maxU, minV).lightmap(216, 0).endVertex(); //NW
			buffer.pos(x +  xLow, y +  yLow, z + zHigh).color(red, green, blue, alpha).tex(maxU, maxV).lightmap(216, 0).endVertex(); //SW

			//West
			minU = u + ((uSize/16.0)*0.0);
			maxU = u + ((uSize/16.0)*3.0);
			minV = v + ((vSize/16.0)*3.0);
			maxV = v + ((vSize/16.0)*13.0);
			buffer.pos(x +  xLow, y +  yLow, z +  zLow).color(red, green, blue, alpha).tex(minU, minV).lightmap(192, 0).endVertex(); //SE
			buffer.pos(x +  xLow, y + yHigh, z +  zLow).color(red, green, blue, alpha).tex(maxU, minV).lightmap(192, 0).endVertex(); //NE
			buffer.pos(x +  xLow, y + yHigh, z + zHigh).color(red, green, blue, alpha).tex(maxU, maxV).lightmap(192, 0).endVertex(); //NW
			buffer.pos(x +  xLow, y +  yLow, z + zHigh).color(red, green, blue, alpha).tex(minU, maxV).lightmap(192, 0).endVertex(); //SW

			//East
			minU = u + ((uSize/16.0)*13.0);
			maxU = u + ((uSize/16.0)*16.0);
			minV = v + ((vSize/16.0)*3.0);
			maxV = v + ((vSize/16.0)*13.0);
			buffer.pos(x + xHigh, y +  yLow, z + zHigh).color(red, green, blue, alpha).tex(minU, minV).lightmap(192, 0).endVertex(); //SE
			buffer.pos(x + xHigh, y + yHigh, z + zHigh).color(red, green, blue, alpha).tex(maxU, minV).lightmap(192, 0).endVertex(); //NE
			buffer.pos(x + xHigh, y + yHigh, z +  zLow).color(red, green, blue, alpha).tex(maxU, maxV).lightmap(192, 0).endVertex(); //NW
			buffer.pos(x + xHigh, y +  yLow, z +  zLow).color(red, green, blue, alpha).tex(minU, maxV).lightmap(192, 0).endVertex(); //SW
		}
	}
}
