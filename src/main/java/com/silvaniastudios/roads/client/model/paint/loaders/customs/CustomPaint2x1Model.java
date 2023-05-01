package com.silvaniastudios.roads.client.model.paint.loaders.customs;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.diagonal.ShapeLibrary;
import com.silvaniastudios.roads.blocks.paint.customs.Custom1x2PaintBlock;
import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;
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

public class CustomPaint2x1Model extends PaintModelBase {

	@Override
	public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
		return new CustomPaint2x1BakedModel(state, format, bakedTextureGetter);
	}
}

class CustomPaint2x1BakedModel extends PaintBakedModelBase {

	public CustomPaint2x1BakedModel(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
		super(state, format, bakedTextureGetter);
		sprites = new TextureAtlasSprite[FRBlocks.col.length];

		for (int i = 0; i < FRBlocks.col.length; i++) {
			sprites[i] = mc.getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":blocks/paint_" + FRBlocks.col[i].getName());
		}
	}

	@Override
	protected List<BakedQuad> packQuads(IBlockState state) {
		List<BakedQuad> quads;
		List<Quad> rawQuads;

		if (state != null) {
			int colId = ((PaintBlockBase) state.getBlock()).getColour().getId();

			TextureAtlasSprite tex = sprites[colId];
			Custom1x2PaintBlock.Enum1x2Block rotState = state.getValue(Custom1x2PaintBlock.CONNECT);

			int xRot = 0;
			int yRot = 45;
			int gridId = 0;

			switch (rotState) {
				case n1:
					yRot = 0;
					break;
				case e1:
					yRot = 270;
					break;
				case s1:
					yRot = 180;
					break;
				case w1:
					yRot = 90;
					break;
				case n2:
					yRot = 0;
					gridId = 1;
					break;
				case e2:
					yRot = 270;
					gridId = 1;
					break;
				case s2:
					yRot = 180;
					gridId = 1;
					break;
				case w2:
					yRot = 90;
					gridId = 1;
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