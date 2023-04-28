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

public class PaintLineSideDoubleModel extends PaintModelBase {
	
	@Override
	public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
		return new PaintLineSideDoubleBakedModel(state, format, bakedTextureGetter);
	}
}

class PaintLineSideDoubleBakedModel extends PaintLineBakedModelBase {

	public PaintLineSideDoubleBakedModel(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
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
				//central line
				List<Quad> central_a = shapeLine(format, sprite, v*6, v*3, v*10, v*4, 0f, 0f, 0f, 0f);
				List<Quad> central_b = shapeLine(format, sprite, v*6, v*5, v*10, v*6, 0f, 0f, 0f, 0f);
				
				//connectors left
				List<Quad> connect_left_tl = shapeLine(format, sprite, v*3, v*3, v*4, v*4, 0f, 0f, 0f, 0f);
				List<Quad> connect_left_tr = shapeLine(format, sprite, v*5, v*3, v*6, v*4, 0f, 0f, 0f, 0f);
				List<Quad> connect_left_bl = shapeLine(format, sprite, v*3, v*5, v*4, v*6, 0f, 0f, 0f, 0f);
				List<Quad> connect_left_br = shapeLine(format, sprite, v*5, v*5, v*6, v*6, 0f, 0f, 0f, 0f);
				
				List<Quad> connect_left_widget_t = shapeLine(format, sprite, v*4, v*3, v*5, v*4, 0f, 0f, 0f, 0f);
				List<Quad> connect_left_widget_b = shapeLine(format, sprite, v*4, v*5, v*5, v*6, 0f, 0f, 0f, 0f);
				List<Quad> connect_left_widget_l = shapeLine(format, sprite, v*3, v*4, v*4, v*5, 0f, 0f, 0f, 0f);
				List<Quad> connect_left_widget_r = shapeLine(format, sprite, v*5, v*4, v*6, v*5, 0f, 0f, 0f, 0f);
				
				//lines left
				List<Quad> left_up_a = shapeLine(format, sprite, v*3, v*0, v*4, v*3, 0f, 0f, 0f, 0f);
				List<Quad> left_up_b = shapeLine(format, sprite, v*5, v*0, v*6, v*3, 0f, 0f, 0f, 0f);
				
				List<Quad> left_mid_a = shapeLine(format, sprite, v*0, v*3, v*3, v*4, 0f, 0f, 0f, 0f);
				List<Quad> left_mid_b = shapeLine(format, sprite, v*0, v*5, v*3, v*6, 0f, 0f, 0f, 0f);
				
				List<Quad> left_down_a = shapeLine(format, sprite, v*3, v*6, v*4, v*16, 0f, 0f, 0f, 0f);
				List<Quad> left_down_b = shapeLine(format, sprite, v*5, v*6, v*6, v*16, 0f, 0f, 0f, 0f);
				
				//connectors right
				List<Quad> connect_right_tl = shapeLine(format, sprite, v*10, v*3, v*11, v*4, 0f, 0f, 0f, 0f);
				List<Quad> connect_right_tr = shapeLine(format, sprite, v*12, v*3, v*13, v*4, 0f, 0f, 0f, 0f);
				List<Quad> connect_right_bl = shapeLine(format, sprite, v*10, v*5, v*11, v*6, 0f, 0f, 0f, 0f);
				List<Quad> connect_right_br = shapeLine(format, sprite, v*12, v*5, v*13, v*6, 0f, 0f, 0f, 0f);
				
				List<Quad> connect_right_widget_t = shapeLine(format, sprite, v*11, v*3, v*12, v*4, 0f, 0f, 0f, 0f);
				List<Quad> connect_right_widget_b = shapeLine(format, sprite, v*11, v*5, v*12, v*6, 0f, 0f, 0f, 0f);
				List<Quad> connect_right_widget_l = shapeLine(format, sprite, v*10, v*4, v*11, v*5, 0f, 0f, 0f, 0f);
				List<Quad> connect_right_widget_r = shapeLine(format, sprite, v*12, v*4, v*13, v*5, 0f, 0f, 0f, 0f);
				
				//lines right
				List<Quad> right_up_a = shapeLine(format, sprite, v*10, v*0, v*11, v*3, 0f, 0f, 0f, 0f);
				List<Quad> right_up_b = shapeLine(format, sprite, v*12, v*0, v*13, v*3, 0f, 0f, 0f, 0f);
				
				List<Quad> right_mid_a = shapeLine(format, sprite, v*13, v*3, v*16, v*4, 0f, 0f, 0f, 0f);
				List<Quad> right_mid_b = shapeLine(format, sprite, v*13, v*5, v*16, v*6, 0f, 0f, 0f, 0f);
				
				List<Quad> right_down_a = shapeLine(format, sprite, v*10, v*6, v*11, v*16, 0f, 0f, 0f, 0f);
				List<Quad> right_down_b = shapeLine(format, sprite, v*12, v*6, v*13, v*16, 0f, 0f, 0f, 0f);
				
				boolean lu = state.getValue(SideLinePaintBlock.LEFT_UP);
				boolean lm = state.getValue(SideLinePaintBlock.LEFT_MID);
				boolean ld = state.getValue(SideLinePaintBlock.LEFT_DOWN);
				
				boolean ru = state.getValue(SideLinePaintBlock.RIGHT_UP);
				boolean rm = state.getValue(SideLinePaintBlock.RIGHT_MID);
				boolean rd = state.getValue(SideLinePaintBlock.RIGHT_DOWN);
				
				boolean central = state.getValue(SideLinePaintBlock.CENTRAL);
				
				if (central) {
					rawQuads.addAll(central_a);
					rawQuads.addAll(central_b);
				}
				
				//Some kind of left connection
				if (lu || lm || ld) {
					if (!central) {
						rawQuads.addAll(connect_left_widget_r);
					}
					//add the frame for left side connections
					rawQuads.addAll(connect_left_tl);
					rawQuads.addAll(connect_left_tr);
					rawQuads.addAll(connect_left_bl);
					rawQuads.addAll(connect_left_br);
					//If up, connect up, else block it off
					if (lu) {
						rawQuads.addAll(left_up_a);
						rawQuads.addAll(left_up_b);
					} else {
						rawQuads.addAll(connect_left_widget_t);
					}
					//same for mid
					if (lm) {
						rawQuads.addAll(left_mid_a);
						rawQuads.addAll(left_mid_b);
					} else {
						rawQuads.addAll(connect_left_widget_l);
					}
					//same for down
					if (ld) {
						rawQuads.addAll(left_down_a);
						rawQuads.addAll(left_down_b);
					} else {
						rawQuads.addAll(connect_left_widget_b);
					}
				}
				
				//Some kind of right connection
				if (ru || rm || rd) {
					if (!central) {
						rawQuads.addAll(connect_right_widget_l);
					}
					//add the frame for left side connections
					rawQuads.addAll(connect_right_tl);
					rawQuads.addAll(connect_right_tr);
					rawQuads.addAll(connect_right_bl);
					rawQuads.addAll(connect_right_br);
					//If up, connect up, else block it off
					if (ru) {
						rawQuads.addAll(right_up_a);
						rawQuads.addAll(right_up_b);
					} else {
						rawQuads.addAll(connect_right_widget_t);
					}
					//same for mid
					if (rm) {
						rawQuads.addAll(right_mid_a);
						rawQuads.addAll(right_mid_b);
					} else {
						rawQuads.addAll(connect_right_widget_r);
					}
					//same for down
					if (rd) {
						rawQuads.addAll(right_down_a);
						rawQuads.addAll(right_down_b);
					} else {
						rawQuads.addAll(connect_right_widget_b);
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

