package com.silvaniastudios.roads.client.model.diagonal;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.silvaniastudios.roads.blocks.diagonal.HalfBlock;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.model.IModelState;

public class DiagonalTriangleTriangleBakedModel extends DiagonalBakedModelBase {
	
	double widthN = 0.5;
	double widthW = 0.75;
	boolean left;
	
	public DiagonalTriangleTriangleBakedModel(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter, double widthN, double widthW, boolean left) {
		super(state, format, bakedTextureGetter);
		this.widthN = widthN;
		this.widthW = widthW;
		this.left = left;
	}
	
	@Override
	protected List<BakedQuad> packQuads(EnumFacing facing, HalfBlock blockLeft, HalfBlock blockRight) {
		List<BakedQuad> quads = new ArrayList<>();
		
		if (facing == EnumFacing.NORTH) {
			if (blockLeft.getSprite()  != null) quads = createTriangle(quads, left, blockLeft, widthN);
			if (blockRight.getSprite() != null) quads = createTriangle(quads, left, blockRight, widthN);
		} else if (facing == EnumFacing.EAST) {
			if (blockLeft.getSprite()  != null) quads = createTriangle(quads, left, blockLeft, widthN);
			if (blockRight.getSprite() != null) quads = createTriangle(quads, left, blockRight, widthN);
		} else if (facing == EnumFacing.SOUTH) {
			if (blockLeft.getSprite()  != null) quads = createTriangle(quads, left, blockLeft, widthN);
			if (blockRight.getSprite() != null) quads = createTriangle(quads, left, blockRight, widthN);
		} else {
			if (blockLeft.getSprite()  != null) quads = createTriangle(quads, left, blockLeft, widthN);
			if (blockRight.getSprite() != null) quads = createTriangle(quads, left, blockRight, widthN);
		}

		return quads;
	}
}
