package com.silvaniastudios.roads.client.model.paint;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.silvaniastudios.roads.blocks.diagonal.HalfBlock;
import com.silvaniastudios.roads.blocks.paint.LinePaintBlock;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
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
		
		if (facing == LinePaintBlock.EnumRotation.ns) {
			quads = createModelFromTexture(quads);
		} else if (facing == LinePaintBlock.EnumRotation.ew) {
			quads = createModelFromTexture(quads);
		} else if (facing == LinePaintBlock.EnumRotation.connect) {
			quads = createModelFromTexture(quads);
		} else {
			quads = createModelFromTexture(quads);
		}

		return quads;
	}
}
