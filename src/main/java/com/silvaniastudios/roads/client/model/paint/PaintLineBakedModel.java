package com.silvaniastudios.roads.client.model.paint;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.paint.LinePaintBlock;
import com.silvaniastudios.roads.client.render.Quad;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.property.IExtendedBlockState;

public class PaintLineBakedModel extends PaintBakedModelBase {

	public PaintLineBakedModel(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
		super(state, format, bakedTextureGetter);
	}
	
	@Override
	protected List<BakedQuad> packQuads(IExtendedBlockState state) {
		List<BakedQuad> quads = new ArrayList<>();
		LinePaintBlock.EnumRotation facing = state.getValue(LinePaintBlock.FACING);
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":blocks/paint_white");
		
		List<Quad> rawQuads = new ArrayList<>();
		float v = 1.0f / 16.0f;
		if (sprite != null) {
			rawQuads.addAll(shapeLine(format, sprite, v*7, v*7, v*9, v*9, 1.0f, 1.0f, 1.0f, 1.0f)); //centre
			
			List<Quad> north = shapeLine(format, sprite, v*7, v*0, v*9,  v*7,  1.0f, 1.0f, 1.0f, 1.0f);
			List<Quad> east  = shapeLine(format, sprite, v*9, v*7, v*16, v*9,  1.0f, 1.0f, 1.0f, 1.0f);
			List<Quad> south = shapeLine(format, sprite, v*7, v*9, v*9,  v*16, 1.0f, 1.0f, 1.0f, 1.0f);
			List<Quad> west  = shapeLine(format, sprite, v*7, v*9, v*0,  v*7,  1.0f, 1.0f, 1.0f, 1.0f);

			if (facing == LinePaintBlock.EnumRotation.ns) {
				rawQuads.addAll(north);
				rawQuads.addAll(south);
			} else if (facing == LinePaintBlock.EnumRotation.ew) {
				rawQuads.addAll(east);
				rawQuads.addAll(west);
			} else if (facing == LinePaintBlock.EnumRotation.connect) {
				if (state.getValue(LinePaintBlock.NORTH)) { rawQuads.addAll(north); }
				if (state.getValue(LinePaintBlock.EAST))  { rawQuads.addAll(east); }
				if (state.getValue(LinePaintBlock.SOUTH)) { rawQuads.addAll(south); }
				if (state.getValue(LinePaintBlock.WEST))  { rawQuads.addAll(west); }
			}
			
			quads = shapeBuilder(rawQuads, quads);
		}
		return quads;
	}
	
	//Works for most default shapes that assume 90 degree rotations.
	protected List<BakedQuad> shapeBuilder(List<Quad> rawQuads, List<BakedQuad> quads) {
		for (int i = 0; i < rawQuads.size(); i++) {
			if (rawQuads.get(i) != null) {
				rawQuads.set(i, Quad.rotateQuadY(rawQuads.get(i), 0));
			}
		}

		for (int i = 0; i < rawQuads.size(); i++) {
			if (rawQuads.get(i) != null) {
				BakedQuad baked = rawQuads.get(i).createQuad(0);
				
				quads.add(baked);
			}
		}

		return quads;
	}
	
	public static List<Quad> shapeLine(VertexFormat format, TextureAtlasSprite sprite, float x1, float z1, float x2, float z2, float heightSW, float heightSE, float heightNE, float heightNW) {
		List<Quad> quads = new ArrayList<>();
		
		float xMin = Math.min(x1, x2);
		float xMax = Math.max(x1, x2);
		float zMin = Math.min(z1, z2);
		float zMax = Math.max(z1, z2);
		
		Quad north = new Quad(
				new Vec3d(xMax, 0, zMin),                   xMin*16f, 16f, //BR
				new Vec3d(xMin, 0, zMin),                   xMax*16f, 16f, //TR
				new Vec3d(xMin, 1-heightNE+0.015625, zMin), xMax*16f, 15.75f,//TL
				new Vec3d(xMax, 1-heightNE+0.015625, zMin), xMin*16f, 15.75f,//BL
				sprite, format);
		
		Quad east = null;

		
		Quad south = new Quad(
				new Vec3d(xMin, 0, zMax), xMax*16f, 15.75f, //BL
				new Vec3d(xMax, 0, zMax), xMin*16f, 15.75f,//BR
				new Vec3d(xMax, 1-heightNE+0.015625, zMax), xMin*16f, 16f, //TR
				new Vec3d(xMin, 1-heightNW+0.015625, zMax), xMax*16f, 16f, //TL
				sprite, format);
		
		Quad west = null;

		Quad top = new Quad( //Top
				new Vec3d(x1, 1-heightSW+0.015625, z2), //BL
				new Vec3d(x2, 1-heightSE+0.015625, z2), //BR
				new Vec3d(x2, 1-heightNE+0.015625, z1), //TR
				new Vec3d(x1, 1-heightNW+0.015625, z1), //TL
				sprite, format);
		
		quads.add(top);
		quads.add(north);
		quads.add(south);
		return quads;
	}
}
