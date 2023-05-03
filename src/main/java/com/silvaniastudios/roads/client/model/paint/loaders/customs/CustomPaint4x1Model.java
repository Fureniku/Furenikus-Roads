package com.silvaniastudios.roads.client.model.paint.loaders.customs;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.diagonal.ShapeLibrary;
import com.silvaniastudios.roads.blocks.enums.EnumFourLengthConnectable;
import com.silvaniastudios.roads.blocks.enums.EnumTwoLengthConnectable;
import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;
import com.silvaniastudios.roads.blocks.paint.customs.Custom1x2PaintBlock;
import com.silvaniastudios.roads.blocks.paint.customs.Custom1x4PaintBlock;
import com.silvaniastudios.roads.blocks.paint.customs.ICustomBlock;
import com.silvaniastudios.roads.client.model.paint.PaintBakedModelBase;
import com.silvaniastudios.roads.client.model.paint.PaintModelBase;
import com.silvaniastudios.roads.client.render.Quad;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.model.IModelState;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CustomPaint4x1Model extends PaintModelBase {

	@Override
	public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
		return new CustomPaint4x1BakedModel(state, format, bakedTextureGetter);
	}
}

class CustomPaint4x1BakedModel extends PaintBakedModelBase {

	public CustomPaint4x1BakedModel(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
		super(state, format, bakedTextureGetter);
		populateSprites();
	}

	@Override
	protected List<BakedQuad> packQuads(IBlockState state) {
		List<BakedQuad> quads;
		List<Quad> rawQuads;

		if (state != null) {
			int colId = ((PaintBlockBase) state.getBlock()).getColour().getId();

			TextureAtlasSprite tex = sprites[colId];
			EnumFourLengthConnectable rotState = state.getValue(Custom1x4PaintBlock.CONNECT);

			int xRot = 0;
			int yRot = 45;
			int gridId = 0;

			switch (rotState) {
				case NORTH_TOP:
					yRot = 0;
					gridId = 3;
					break;
				case NORTH_TOP_MID:
					yRot = 0;
					gridId = 2;
					break;
				case NORTH_BOTTOM_MID:
					yRot = 0;
					gridId = 1;
					break;
				case NORTH_BOTTOM:
					yRot = 0;
					gridId = 0;
					break;

				case EAST_TOP:
					yRot = 270;
					gridId = 3;
					break;
				case EAST_TOP_MID:
					yRot = 270;
					gridId = 2;
					break;
				case EAST_BOTTOM_MID:
					yRot = 270;
					gridId = 1;
					break;
				case EAST_BOTTOM:
					yRot = 270;
					gridId = 0;
					break;

				case SOUTH_TOP:
					yRot = 180;
					gridId = 3;
					break;
				case SOUTH_TOP_MID:
					yRot = 180;
					gridId = 2;
					break;
				case SOUTH_BOTTOM_MID:
					yRot = 180;
					gridId = 1;
					break;
				case SOUTH_BOTTOM:
					yRot = 180;
					gridId = 0;
					break;

				case WEST_TOP:
					yRot = 90;
					gridId = 3;
					break;
				case WEST_TOP_MID:
					yRot = 90;
					gridId = 2;
					break;
				case WEST_BOTTOM_MID:
					yRot = 90;
					gridId = 1;
					break;
				case WEST_BOTTOM:
					yRot = 90;
					gridId = 0;
					break;
			}

			boolean[][] grid = ((ICustomBlock) state.getBlock()).getGrid(gridId).getGrid();

			rawQuads = ShapeLibrary.shapeFromGrid(grid, 0.015625f, tex, format, false);
			quads = shapeBuilder(rawQuads, new ArrayList<>(), xRot, yRot);
		} else {
			return handleItemRendering();
		}

		return quads;
	}
}