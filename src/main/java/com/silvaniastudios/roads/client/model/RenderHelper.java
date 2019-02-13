package com.silvaniastudios.roads.client.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraftforge.client.model.pipeline.UnpackedBakedQuad;
import net.minecraftforge.client.model.pipeline.VertexLighterFlat;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fml.client.FMLClientHandler;

public class RenderHelper {
	
	public static void renderCube(final BufferBuilder buffer, double x, double y, double z, float xLow, float yLow, float zLow, float xSize, float ySize, float zSize, TextureAtlasSprite texture) {
		final int red = (int) (0xFF);
		final int green = (int) (0xFF);
		final int blue = (int) (0xFF);
		final int alpha = 0xFF;

		final double minU = texture.getMinU();
		final double maxU = texture.getMaxU();
		final double minV = texture.getMinV();
		final double maxV = texture.getMaxV();
		
		float xHigh = xLow + xSize;
		float yHigh = yLow + ySize;
		float zHigh = zLow + zSize;
		
		//Up
		buffer.pos(x + xHigh, y + yHigh, z + zHigh).color(red, green, blue, alpha).tex(minU, maxV).lightmap(240, 0).endVertex(); //SE
		buffer.pos(x + xHigh, y + yHigh, z +  zLow).color(red, green, blue, alpha).tex(minU, minV).lightmap(240, 0).endVertex(); //NE
		buffer.pos(x +  xLow, y + yHigh, z +  zLow).color(red, green, blue, alpha).tex(maxU, minV).lightmap(240, 0).endVertex(); //NW
		buffer.pos(x +  xLow, y + yHigh, z + zHigh).color(red, green, blue, alpha).tex(maxU, maxV).lightmap(240, 0).endVertex(); //SW
		//Down
		buffer.pos(x + xHigh, y +  yLow, z +  zLow).color(red, green, blue, alpha).tex(minU, maxV).lightmap(168, 0).endVertex(); //SE
		buffer.pos(x + xHigh, y +  yLow, z + zHigh).color(red, green, blue, alpha).tex(minU, minV).lightmap(168, 0).endVertex(); //NE
		buffer.pos(x +  xLow, y +  yLow, z + zHigh).color(red, green, blue, alpha).tex(maxU, minV).lightmap(168, 0).endVertex(); //NW
		buffer.pos(x +  xLow, y +  yLow, z +  zLow).color(red, green, blue, alpha).tex(maxU, maxV).lightmap(168, 0).endVertex(); //SW
		//North
		buffer.pos(x +  xLow, y +  yLow, z +  zLow).color(red, green, blue, alpha).tex(minU, maxV).lightmap(216, 0).endVertex(); //SE
		buffer.pos(x +  xLow, y + yHigh, z +  zLow).color(red, green, blue, alpha).tex(minU, minV).lightmap(216, 0).endVertex(); //NE
		buffer.pos(x + xHigh, y + yHigh, z +  zLow).color(red, green, blue, alpha).tex(maxU, minV).lightmap(216, 0).endVertex(); //NW
		buffer.pos(x + xHigh, y +  yLow, z +  zLow).color(red, green, blue, alpha).tex(maxU, maxV).lightmap(216, 0).endVertex(); //SW
		//South
		buffer.pos(x + xHigh, y +  yLow, z + zHigh).color(red, green, blue, alpha).tex(minU, maxV).lightmap(216, 0).endVertex(); //SE
		buffer.pos(x + xHigh, y + yHigh, z + zHigh).color(red, green, blue, alpha).tex(minU, minV).lightmap(216, 0).endVertex(); //NE
		buffer.pos(x +  xLow, y + yHigh, z + zHigh).color(red, green, blue, alpha).tex(maxU, minV).lightmap(216, 0).endVertex(); //NW
		buffer.pos(x +  xLow, y +  yLow, z + zHigh).color(red, green, blue, alpha).tex(maxU, maxV).lightmap(216, 0).endVertex(); //SW
		//West
		buffer.pos(x +  xLow, y +  yLow, z +  zLow).color(red, green, blue, alpha).tex(minU, maxV).lightmap(192, 0).endVertex(); //SE
		buffer.pos(x +  xLow, y + yHigh, z +  zLow).color(red, green, blue, alpha).tex(minU, minV).lightmap(192, 0).endVertex(); //NE
		buffer.pos(x +  xLow, y + yHigh, z + zHigh).color(red, green, blue, alpha).tex(maxU, minV).lightmap(192, 0).endVertex(); //NW
		buffer.pos(x +  xLow, y +  yLow, z + zHigh).color(red, green, blue, alpha).tex(maxU, maxV).lightmap(192, 0).endVertex(); //SW
		//East
		buffer.pos(x + xHigh, y +  yLow, z + zHigh).color(red, green, blue, alpha).tex(minU, maxV).lightmap(192, 0).endVertex(); //SE
		buffer.pos(x + xHigh, y + yHigh, z + zHigh).color(red, green, blue, alpha).tex(minU, minV).lightmap(192, 0).endVertex(); //NE
		buffer.pos(x + xHigh, y + yHigh, z +  zLow).color(red, green, blue, alpha).tex(maxU, minV).lightmap(192, 0).endVertex(); //NW
		buffer.pos(x + xHigh, y +  yLow, z +  zLow).color(red, green, blue, alpha).tex(maxU, maxV).lightmap(192, 0).endVertex(); //SW
	}
	
	public static void renderFlatQuad(final BufferBuilder buffer, TextureAtlasSprite texture,
			float x1, float y1, float z1,
			float x2, float y2, float z2,
			float x3, float y3, float z3,
			float x4, float y4, float z4) {
		
		
		final int red = (int) (0xFF);
		final int green = (int) (0xFF);
		final int blue = (int) (0xFF);
		final int alpha = 0xFF;

		final double minU = texture.getMinU();
		final double maxU = texture.getMaxU();
		final double minV = texture.getMinV();
		final double maxV = texture.getMaxV();

		final int lightmap1 = 240;
		final int lightmap2 = 0;
		
		buffer.pos(x1, y1, z1).color(red, green, blue, alpha).tex(minU, maxV).lightmap(lightmap1, lightmap2).endVertex(); //SE
		buffer.pos(x2, y2, z2).color(red, green, blue, alpha).tex(minU, minV).lightmap(lightmap1, lightmap2).endVertex(); //NE
		buffer.pos(x3, y3, z3).color(red, green, blue, alpha).tex(maxU, minV).lightmap(lightmap1, lightmap2).endVertex(); //NW
		buffer.pos(x4, y4, z4).color(red, green, blue, alpha).tex(maxU, maxV).lightmap(lightmap1, lightmap2).endVertex(); //SW
	}
	
	public static float getPercentage(float num, float max) {
		float x = (float) num / (float) max;
		float y = x*100;
		if (y > 100) { return 100; }
		return y;
	}
	
	public static BakedQuad setBrightTexture(BakedQuad quad, float light) {
		if (FMLClientHandler.instance().hasOptifine()) {
			return quad;
		}
		
		if (!ForgeModContainer.forgeLightPipelineEnabled) {
			return quad;
		}
		
		VertexFormat newFormat = getFormatWithLightMap(quad.getFormat());
	  
		UnpackedBakedQuad.Builder builder = new UnpackedBakedQuad.Builder(newFormat);
	  
		VertexLighterFlat trans = new VertexLighterFlat(Minecraft.getMinecraft().getBlockColors()) {
			@Override
			protected void updateLightmap(float[] normal, float[] lightmap, float x, float y, float z) {
				lightmap[0] = light;
				lightmap[1] = light;
			}
		
			@Override
			public void setQuadTint(int tint) {
			}
		};
	
		trans.setParent(builder);
	
		quad.pipe(trans);
	
		builder.setQuadTint(quad.getTintIndex());
		builder.setQuadOrientation(quad.getFace());
		builder.setTexture(quad.getSprite());
		builder.setApplyDiffuseLighting(false);
	
		return builder.build();
	}

	private static final VertexFormat ITEM_FORMAT_WITH_LIGHTMAP = new VertexFormat(DefaultVertexFormats.ITEM).addElement(DefaultVertexFormats.TEX_2S);

	public static VertexFormat getFormatWithLightMap(VertexFormat format) {
		if (FMLClientHandler.instance().hasOptifine() || !ForgeModContainer.forgeLightPipelineEnabled) {
			return format;
		}
		
		if (format == DefaultVertexFormats.BLOCK) {
			return DefaultVertexFormats.BLOCK;
		} else if (format == DefaultVertexFormats.ITEM) {
			return ITEM_FORMAT_WITH_LIGHTMAP;
		} else if (!format.hasUvOffset(1)) {
			VertexFormat result = new VertexFormat(format);
			result.addElement(DefaultVertexFormats.TEX_2S);
			return result;
		}
		return format;
	}

	public static int[] vertexToInts(float x, float y, float z, int color, TextureAtlasSprite texture, float u, float v, int normal) {
		return new int[] {
				Float.floatToRawIntBits(x),
				Float.floatToRawIntBits(y),
				Float.floatToRawIntBits(z),
				color,
				Float.floatToRawIntBits(texture.getInterpolatedU(u)),
				Float.floatToRawIntBits(texture.getInterpolatedV(v)),
				normal
		};
	}
	
	public static int calculatePackedNormal(float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float x4, float y4, float z4) {
		float xp = x4-x2;
		float yp = y4-y2;
		float zp = z4-z2;

		float xq = x3-x1;
		float yq = y3-y1;
		float zq = z3-z1;

		//Cross Product
		float xn = yq*zp - zq*yp;
		float yn = zq*xp - xq*zp;
		float zn = xq*yp - yq*xp;

		//Normalize
		float norm = (float)Math.sqrt(xn*xn + yn*yn + zn*zn);
		final float SMALL_LENGTH =  1.0E-4F;  //Vec3d.normalise() uses this
		if (norm < SMALL_LENGTH) norm = 1.0F;  // protect against degenerate quad

		norm = 1.0F / norm;
		xn *= norm;
		yn *= norm;
		zn *= norm;

		int x = ((byte)(xn * 127)) & 0xFF;
		int y = ((byte)(yn * 127)) & 0xFF;
		int z = ((byte)(zn * 127)) & 0xFF;
		return x | (y << 0x08) | (z << 0x10);
	}
}