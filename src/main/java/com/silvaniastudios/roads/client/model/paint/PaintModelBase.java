package com.silvaniastudios.roads.client.model.paint;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Function;

import com.google.common.collect.ImmutableSet;
import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.client.model.diagonal.DiagonalTriangleTriangleBakedModel;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;

public class PaintModelBase implements IModel {

	@Override
	public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
		return new DiagonalTriangleTriangleBakedModel(state, format, bakedTextureGetter, 1.0, 1.0, true);
	}

	@Override
	public Collection<ResourceLocation> getDependencies() {
		return Collections.emptySet();
	}
	
	@Override
	public Collection<ResourceLocation> getTextures() {
		return ImmutableSet.of(new ResourceLocation(FurenikusRoads.MODID, "blocks/road_block_standard"));
	}
	
	@Override
	public IModelState getDefaultState() {
		return TRSRTransformation.identity();
	}
}
