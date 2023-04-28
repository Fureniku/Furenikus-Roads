package com.silvaniastudios.roads.client.model.paint;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import com.google.common.collect.ImmutableSet;
import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.diagonal.ShapeLibrary;
import com.silvaniastudios.roads.blocks.paint.customs.CustomPaintBlock;
import com.silvaniastudios.roads.blocks.paint.customs.ICustomBlock;
import com.silvaniastudios.roads.client.model.diagonal.DiagonalTriangleTriangleBakedModel;

import com.silvaniastudios.roads.client.render.Quad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;

public abstract class PaintModelBase implements IModel {

	@Override
	public abstract IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter);

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
