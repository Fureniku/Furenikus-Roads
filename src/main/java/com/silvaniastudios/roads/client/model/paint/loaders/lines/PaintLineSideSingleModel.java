package com.silvaniastudios.roads.client.model.paint.loaders.lines;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;
import com.silvaniastudios.roads.blocks.paint.SideLinePaintBlock;
import com.silvaniastudios.roads.client.model.paint.PaintBakedModelBase;
import com.silvaniastudios.roads.client.model.paint.PaintLineBakedModelBase;
import com.silvaniastudios.roads.client.model.paint.PaintModelBase;
import com.silvaniastudios.roads.client.model.paint.loaders.PaintLoaderBase;
import com.silvaniastudios.roads.client.render.Quad;

import net.minecraft.block.state.IBlockState;
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

public class PaintLineSideSingleModel extends PaintModelBase {
	
	@Override
	public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
		return new PaintLineSideSingleBakedModel(state, format, bakedTextureGetter);
	}
}

class PaintLineSideSingleBakedModel extends PaintLineBakedModelBase {

	public PaintLineSideSingleBakedModel(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
		super(state, format, bakedTextureGetter);
	}
	
	@Override
	protected List<BakedQuad> packQuads(IBlockState stateIn) {
		IExtendedBlockState state = (IExtendedBlockState) stateIn;
		List<BakedQuad> quads = new ArrayList<>();
		List<Quad> rawQuads = new ArrayList<>();
		
		if (state != null) {
			EnumFacing facing = state.getValue(SideLinePaintBlock.FACING);
			PaintBlockBase paintBlock = (PaintBlockBase) state.getBlock();
			TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":blocks/paint_" + paintBlock.getColour().getName());
			float v = 1.0f / 16.0f;
			
			if (sprite != null) {
				List<Quad> central_a = shapeLine(format, sprite, v*4, v*2, v*12, v*4, 0f, 0f, 0f, 0f);
				
				//connectors left
				List<Quad> connect_left = shapeLine(format, sprite, v*2, v*2, v*4, v*4, 0f, 0f, 0f, 0f);
				
				//lines left
				List<Quad> left_up_a = shapeLine(format, sprite, v*2, v*0, v*4, v*2, 0f, 0f, 0f, 0f);
				List<Quad> left_mid_a = shapeLine(format, sprite, v*0, v*2, v*2, v*4, 0f, 0f, 0f, 0f);
				List<Quad> left_down_a = shapeLine(format, sprite, v*2, v*4, v*4, v*16, 0f, 0f, 0f, 0f);
				
				//connectors right
				List<Quad> connect_right = shapeLine(format, sprite, v*12, v*2, v*14, v*4, 0f, 0f, 0f, 0f);
				
				//lines right
				List<Quad> right_up_a = shapeLine(format, sprite, v*12, v*0, v*14, v*2, 0f, 0f, 0f, 0f);
				List<Quad> right_mid_a = shapeLine(format, sprite, v*14, v*2, v*16, v*4, 0f, 0f, 0f, 0f);
				List<Quad> right_down_a = shapeLine(format, sprite, v*12, v*4, v*14, v*16, 0f, 0f, 0f, 0f);
				
				boolean lu = state.getValue(SideLinePaintBlock.LEFT_UP);
				boolean lm = state.getValue(SideLinePaintBlock.LEFT_MID);
				boolean ld = state.getValue(SideLinePaintBlock.LEFT_DOWN);
				
				boolean ru = state.getValue(SideLinePaintBlock.RIGHT_UP);
				boolean rm = state.getValue(SideLinePaintBlock.RIGHT_MID);
				boolean rd = state.getValue(SideLinePaintBlock.RIGHT_DOWN);
				
				boolean central = state.getValue(SideLinePaintBlock.CENTRAL);
				
				if (central) {
					rawQuads.addAll(central_a);
				}
				
				//Some kind of left connection
				if (lu || lm || ld) {
					//add the frame for left side connections
					rawQuads.addAll(connect_left);
					
					//If up, connect up, else block it off
					if (lu) {
						rawQuads.addAll(left_up_a);
					}
					//same for mid
					if (lm) {
						rawQuads.addAll(left_mid_a);
					}
					//same for down
					if (ld) {
						rawQuads.addAll(left_down_a);
					}
				}
				
				//Some kind of right connection
				if (ru || rm || rd) {
					//add the frame for left side connections
					rawQuads.addAll(connect_right);
					//If up, connect up, else block it off
					if (ru) {
						rawQuads.addAll(right_up_a);
					}
					//same for mid
					if (rm) {
						rawQuads.addAll(right_mid_a);
					}
					//same for down
					if (rd) {
						rawQuads.addAll(right_down_a);
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

