package com.silvaniastudios.roads.client.model.paint.loaders.customs;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.diagonal.ShapeLibrary;
import com.silvaniastudios.roads.blocks.paint.customs.CustomPaintBlock;
import com.silvaniastudios.roads.blocks.paint.customs.ICustomBlock;
import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;
import com.silvaniastudios.roads.client.model.paint.PaintBakedModelBase;
import com.silvaniastudios.roads.client.model.paint.PaintModelBase;
import com.silvaniastudios.roads.client.render.Quad;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.model.IModelState;

public class CustomPaintModel extends PaintModelBase {

	@Override
	public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
		return new CustomPaintBakedModel(state, format, bakedTextureGetter);
	}
}

class CustomPaintBakedModel extends PaintBakedModelBase {

	public CustomPaintBakedModel(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
		super(state, format, bakedTextureGetter);
		populateSprites();
	}

	@Override
	protected List<BakedQuad> packQuads(IBlockState state) {
		List<BakedQuad> quads;
		List<Quad> rawQuads;

		if (state != null) {
			int colId = ((PaintBlockBase) state.getBlock()).getColour().getId();
			boolean[][] grid = ((ICustomBlock) state.getBlock()).getGrid(0).getGrid();

			TextureAtlasSprite tex = sprites[colId];
			EnumFacing rotState = state.getValue(CustomPaintBlock.FACING);

			int xRot = 0;
			int yRot = 45;

			switch (rotState) {
				case NORTH:
					yRot = 0;
					break;
				case EAST:
					yRot = 270;
					break;
				case SOUTH:
					yRot = 180;
					break;
				case WEST:
					yRot = 90;
					break;
			}

			rawQuads = ShapeLibrary.shapeFromGrid(grid, 0.015625f, tex, format, false);
			quads = shapeBuilder(rawQuads, new ArrayList<>(), xRot, yRot);
		} else {
			return handleItemRendering();
		}

		return quads;
	}


}