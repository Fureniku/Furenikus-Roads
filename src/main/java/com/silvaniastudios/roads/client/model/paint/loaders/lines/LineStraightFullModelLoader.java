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

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.property.IExtendedBlockState;

public class LineStraightFullModelLoader implements ICustomModelLoader {
	
	public static final PaintLineModel MODEL = new PaintLineModel();
	
	@Override
	public boolean accepts(ResourceLocation modelLocation) {
		if (modelLocation.getResourceDomain().equals(FurenikusRoads.MODID)) {
			for (int i = 0; i < FRBlocks.col.length; i++) {
				if (modelLocation.getResourcePath().equals("line_" + FRBlocks.col[i] + "_straight_full")) {
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

class PaintLineModel extends PaintModelBase {
	
	@Override
	public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
		return new PaintLineBakedModel(state, format, bakedTextureGetter);
	}
}

class PaintLineBakedModel extends PaintBakedModelBase {

	public PaintLineBakedModel(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
		super(state, format, bakedTextureGetter);
	}
	
	@Override
	protected List<BakedQuad> packQuads(IExtendedBlockState state) {
		List<BakedQuad> quads = new ArrayList<>();
		List<Quad> rawQuads = new ArrayList<>();
		
		if (state != null) {
			LinePaintBlock.EnumRotation facing = state.getValue(LinePaintBlock.FACING);
			PaintBlockBase paintBlock = (PaintBlockBase) state.getBlock();
			TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":blocks/paint_" + paintBlock.getColour());
			float v = 1.0f / 16.0f;
			
			if (sprite != null) {
				rawQuads.addAll(shapeLine(format, sprite, v*7, v*7, v*9, v*9, 0f, 0f, 0f, 0f)); //centre
				
				List<Quad> north = shapeLine(format, sprite, v*7, v*0, v*9,  v*7,  0f, 0f, 0f, 0f);
				List<Quad> east  = shapeLine(format, sprite, v*9, v*7, v*16, v*9,  0f, 0f, 0f, 0f);
				List<Quad> south = shapeLine(format, sprite, v*7, v*9, v*9,  v*16, 0f, 0f, 0f, 0f);
				List<Quad> west  = shapeLine(format, sprite, v*0, v*7, v*7,  v*9,  0f, 0f, 0f, 0f);
	
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
				quads = shapeBuilder(rawQuads, quads, 0);
			}
		} else if (stack != null) {
			List<Quad> spriteQuads = getSpriteQuads();
			PaintBlockBase paintBlock = (PaintBlockBase) ((ItemBlock) stack.getItem()).getBlock();
			rawQuads.addAll(spriteQuads);
			quads = shapeBuilder(rawQuads, quads, getColIntFromName(paintBlock.getColour()));
		}
		
		
		return quads;
	}
}

