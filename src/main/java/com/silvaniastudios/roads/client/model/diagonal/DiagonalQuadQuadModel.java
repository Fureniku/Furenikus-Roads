package com.silvaniastudios.roads.client.model.diagonal;

import java.util.function.Function;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.model.IModelState;

public class DiagonalQuadQuadModel extends DiagonalModelBase {
	
	double narrow;
	double wide;
	boolean left;
	
	public DiagonalQuadQuadModel(double narrow, double wide, boolean left) {
		this.narrow = narrow;
		this.wide = wide;
		this.left = left;
	}
	
	@Override
	public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
		return new DiagonalQuadQuadBakedModel(state, format, bakedTextureGetter, narrow, wide, left);
	}

}
