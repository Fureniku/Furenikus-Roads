package com.silvaniastudios.roads.client.model.diagonal;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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
	protected List<BakedQuad> packQuads(EnumFacing facing, TextureAtlasSprite spriteLeft, TextureAtlasSprite spriteRight, float leftHeight, float rightHeight) {
		List<BakedQuad> quads = new ArrayList<>();
		
		if (facing == EnumFacing.NORTH) {
			if (spriteLeft  != null) quads = createTriangle(quads, left, leftHeight, rightHeight, spriteLeft, widthN, 0);
			if (spriteRight != null) quads = createTriangle(quads, left, rightHeight, leftHeight, spriteRight, widthN, 180);
		} else if (facing == EnumFacing.EAST) {
			if (spriteLeft  != null) quads = createTriangle(quads, left, leftHeight, rightHeight, spriteLeft, widthN, 270);
			if (spriteRight != null) quads = createTriangle(quads, left, rightHeight, leftHeight, spriteRight, widthN, 90);
		} else if (facing == EnumFacing.SOUTH) {
			if (spriteLeft  != null) quads = createTriangle(quads, left, leftHeight, rightHeight, spriteLeft, widthN, 180);
			if (spriteRight != null) quads = createTriangle(quads, left, rightHeight, leftHeight, spriteRight, widthN, 0);
		} else {
			if (spriteLeft  != null) quads = createTriangle(quads, left, leftHeight, rightHeight, spriteLeft, widthN, 90);
			if (spriteRight != null) quads = createTriangle(quads, left, rightHeight, leftHeight, spriteRight, widthN, 270);
		}

		return quads;
	}
}
