package com.silvaniastudios.roads.client.model.paint.loaders.lines;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.paint.FarSideLinePaintBlock;
import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;
import com.silvaniastudios.roads.blocks.paint.SideLinePaintBlock;
import com.silvaniastudios.roads.client.model.paint.PaintBakedModelBase;
import com.silvaniastudios.roads.client.model.paint.PaintModelBase;
import com.silvaniastudios.roads.client.render.Quad;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.property.IExtendedBlockState;

public class LineFarSideThickModelLoader implements ICustomModelLoader {
	
	public static final PaintLineFarSideThickModel MODEL = new PaintLineFarSideThickModel();
	
	@Override
	public boolean accepts(ResourceLocation modelLocation) {
		if (modelLocation.getResourceDomain().equals(FurenikusRoads.MODID)) {
			for (int i = 0; i < FRBlocks.col.length; i++) {
				if (modelLocation.getResourcePath().equals("line_" + FRBlocks.col[i].getName() + "_far_side_thick")) {
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

class PaintLineFarSideThickModel extends PaintModelBase {
	
	@Override
	public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
		return new PaintLineFarSideThickBakedModel(state, format, bakedTextureGetter);
	}
}

class PaintLineFarSideThickBakedModel extends PaintBakedModelBase {

	public PaintLineFarSideThickBakedModel(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
		super(state, format, bakedTextureGetter);
	}
	
	@Override
	protected List<BakedQuad> packQuads(IExtendedBlockState state) {
		List<BakedQuad> quads = new ArrayList<>();
		List<Quad> rawQuads = new ArrayList<>();
		
		if (state != null) {
			EnumFacing facing = state.getValue(SideLinePaintBlock.FACING);
			PaintBlockBase paintBlock = (PaintBlockBase) state.getBlock();
			TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":blocks/paint_" + paintBlock.getColour().getName());
			float v = 1.0f / 16.0f;
			
			if (sprite != null) {
				List<Quad> central_a = shapeLine(format, sprite, v*4, v*0, v*12, v*4, 0f, 0f, 0f, 0f);
				
				List<Quad> connect_left = shapeLine(format, sprite, v*0, v*0, v*4, v*4, 0f, 0f, 0f, 0f);
				List<Quad> left_down = shapeLine(format, sprite, v*0, v*4, v*4, v*16, 0f, 0f, 0f, 0f);
				
				List<Quad> connect_right = shapeLine(format, sprite, v*12, v*0, v*16, v*4, 0f, 0f, 0f, 0f);
				List<Quad> right_down = shapeLine(format, sprite, v*12, v*4, v*16, v*16, 0f, 0f, 0f, 0f);
				
				boolean lm = state.getValue(FarSideLinePaintBlock.LEFT_MID);
				boolean ld = state.getValue(FarSideLinePaintBlock.LEFT_DOWN);
				
				boolean rm = state.getValue(FarSideLinePaintBlock.RIGHT_MID);
				boolean rd = state.getValue(FarSideLinePaintBlock.RIGHT_DOWN);
				
				boolean central = state.getValue(FarSideLinePaintBlock.CENTRAL);
				
				if (central) {
					rawQuads.addAll(central_a);
				}
				
				if (lm || ld) {
					rawQuads.addAll(connect_left);

					if (ld) {
						rawQuads.addAll(left_down);
					}
				}
				
				if (rm || rd) {
					rawQuads.addAll(connect_right);

					if (rd) {
						rawQuads.addAll(right_down);
					}
				}
				
				int rot = 0;
				
				if (facing == EnumFacing.EAST) { rot = 270; }
				if (facing == EnumFacing.SOUTH) { rot = 180; }
				if (facing == EnumFacing.WEST) { rot = 90; }
				
				quads = shapeBuilder(rawQuads, quads, 0, rot);
			}
		} else if (stack != null) {
			List<Quad> spriteQuads = getSpriteQuads();
			PaintBlockBase paintBlock = (PaintBlockBase) ((ItemBlock) stack.getItem()).getBlock();
			rawQuads.addAll(spriteQuads);
			quads = shapeBuilder(rawQuads, quads, paintBlock.getColour().getColourInt());
		}
		
		
		return quads;
	}
}

