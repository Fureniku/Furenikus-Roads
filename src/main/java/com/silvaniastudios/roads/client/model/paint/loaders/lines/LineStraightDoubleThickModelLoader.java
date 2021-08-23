package com.silvaniastudios.roads.client.model.paint.loaders.lines;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.paint.LinePaintBlock;
import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;
import com.silvaniastudios.roads.client.model.paint.PaintBakedModelBase;
import com.silvaniastudios.roads.client.model.paint.PaintModelBase;
import com.silvaniastudios.roads.client.render.Quad;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.property.IExtendedBlockState;

public class LineStraightDoubleThickModelLoader implements ICustomModelLoader {
	
	public static final PaintLineDoubleThickModel MODEL = new PaintLineDoubleThickModel();
	
	@Override
	public boolean accepts(ResourceLocation modelLocation) {
		if (modelLocation.getResourceDomain().equals(FurenikusRoads.MODID)) {
			for (int i = 0; i < FRBlocks.col.length; i++) {
				if (modelLocation.getResourcePath().equals("line_" + FRBlocks.col[i] + "_straight_double_thick")) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	@Override
	public IModel loadModel(ResourceLocation modelLocation) throws Exception {
		return MODEL;
	}
	
	@Override
	public void onResourceManagerReload(IResourceManager resourceManager) {}
}

class PaintLineDoubleThickModel extends PaintModelBase {
	
	@Override
	public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
		return new PaintLineDoubleThickBakedModel(state, format, bakedTextureGetter);
	}
}

class PaintLineDoubleThickBakedModel extends PaintBakedModelBase {

	public PaintLineDoubleThickBakedModel(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
		super(state, format, bakedTextureGetter);
	}
	
	@Override
	protected List<BakedQuad> packQuads(IExtendedBlockState state) {
		List<BakedQuad> quads = new ArrayList<>();
		LinePaintBlock.EnumRotation facing = state.getValue(LinePaintBlock.FACING);
		PaintBlockBase paintBlock = (PaintBlockBase) state.getBlock();
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":blocks/paint_" + paintBlock.getColour());
		
		List<Quad> rawQuads = new ArrayList<>();
		float v = 1.0f / 16.0f;
		if (sprite != null) {
			rawQuads.addAll(shapeLine(format, sprite, v*3, v*3, v*7,  v*7, 0f, 0f, 0f, 0f)); //northwest
			rawQuads.addAll(shapeLine(format, sprite, v*9, v*3, v*13, v*7, 0f, 0f, 0f, 0f)); //northeast
			rawQuads.addAll(shapeLine(format, sprite, v*3, v*9, v*7,  v*13, 0f, 0f, 0f, 0f)); //southwest
			rawQuads.addAll(shapeLine(format, sprite, v*9, v*9, v*13, v*13, 0f, 0f, 0f, 0f)); //southeast
			
			List<Quad> northL = shapeLine(format, sprite, v*3, v*0, v*7,  v*3,  0f, 0f, 0f, 0f);
			List<Quad> northR = shapeLine(format, sprite, v*9, v*0, v*13, v*3,  0f, 0f, 0f, 0f);
			List<Quad> northWidget = shapeLine(format, sprite, v*7, v*3, v*9, v*7,  0f, 0f, 0f, 0f);
			
			List<Quad> eastL  = shapeLine(format, sprite, v*13, v*3, v*16, v*7,  0f, 0f, 0f, 0f);
			List<Quad> eastR  = shapeLine(format, sprite, v*13, v*9, v*16, v*13, 0f, 0f, 0f, 0f);
			List<Quad> eastWidget = shapeLine(format, sprite, v*9, v*7, v*13, v*9, 0f, 0f, 0f, 0f);
			
			List<Quad> southL = shapeLine(format, sprite, v*3, v*9, v*7,  v*16, 0f, 0f, 0f, 0f);
			List<Quad> southR = shapeLine(format, sprite, v*9, v*9, v*13, v*16, 0f, 0f, 0f, 0f);
			List<Quad> southWidget = shapeLine(format, sprite, v*7, v*9, v*9, v*13,  0f, 0f, 0f, 0f);
			
			List<Quad> westL  = shapeLine(format, sprite, v*0, v*3, v*3, v*7,  0f, 0f, 0f, 0f);
			List<Quad> westR  = shapeLine(format, sprite, v*0, v*9, v*3, v*13, 0f, 0f, 0f, 0f);
			List<Quad> westWidget = shapeLine(format, sprite, v*3, v*7, v*7, v*9, 0f, 0f, 0f, 0f);

			if (facing == LinePaintBlock.EnumRotation.ns) {
				rawQuads.addAll(northL);
				rawQuads.addAll(northR);
				rawQuads.addAll(eastWidget);
				rawQuads.addAll(westWidget);
				rawQuads.addAll(southL);
				rawQuads.addAll(southR);
			} else if (facing == LinePaintBlock.EnumRotation.ew) {
				rawQuads.addAll(eastL);
				rawQuads.addAll(eastR);
				rawQuads.addAll(northWidget);
				rawQuads.addAll(southWidget);
				rawQuads.addAll(westL);
				rawQuads.addAll(westR);
			} else if (facing == LinePaintBlock.EnumRotation.connect) {
				if (state.getValue(LinePaintBlock.NORTH)) {
					rawQuads.addAll(northL);
					rawQuads.addAll(northR);
				} else {
					rawQuads.addAll(northWidget);
				}
				if (state.getValue(LinePaintBlock.EAST))  {
					rawQuads.addAll(eastL);
					rawQuads.addAll(eastR);
				} else {
					rawQuads.addAll(eastWidget);
				}
				if (state.getValue(LinePaintBlock.SOUTH)) {
					rawQuads.addAll(southL);
					rawQuads.addAll(southR);
				} else {
					rawQuads.addAll(southWidget);
				}
				if (state.getValue(LinePaintBlock.WEST))  {
					rawQuads.addAll(westL);
					rawQuads.addAll(westR);
				} else {
					rawQuads.addAll(westWidget);
				}
			}
			
			quads = shapeBuilder(rawQuads, quads);
		}
		return quads;
	}
}
