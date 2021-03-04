package com.silvaniastudios.roads.client.model.diagonal;

import java.util.function.Function;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.model.IModelState;

public class DiagonalTriangleModel extends DiagonalModelBase {
	
	boolean left;
	
	public DiagonalTriangleModel(boolean left) {
		this.left = left;
	}
	
	@Override
	public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
		return new DiagonalTriangleTriangleBakedModel(state, format, bakedTextureGetter, 0.0, 1.0, left);
	}
}
