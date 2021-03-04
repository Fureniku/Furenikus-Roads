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

public class DiagonalQuadQuadBakedModel extends DiagonalBakedModelBase {
	
	double widthN = 0.5;
	double widthW = 1.0;
	boolean left;
	
	public DiagonalQuadQuadBakedModel(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter, double widthN, double widthW, boolean left) {
		super(state, format, bakedTextureGetter);
		this.widthN = widthN;
		this.widthW = widthW;
		this.left = left;
	}
	
	@Override
	protected List<BakedQuad> packQuads(EnumFacing facing, TextureAtlasSprite spriteLeft, TextureAtlasSprite spriteRight, int colLeft, int colRight, float leftHeight, float rightHeight) {
		List<BakedQuad> quads = new ArrayList<>();
		
		if (facing == EnumFacing.NORTH) {
			if (spriteLeft  != null) quads = createTrapezium(quads, left, rightHeight, leftHeight, spriteLeft, widthN, widthW, 0, colLeft); //0.25 0.5
			if (spriteRight != null) quads = createTrapezium(quads, left, leftHeight, rightHeight, spriteRight, 1-widthW, 1-widthN, 180, colRight); //1-0.5, 1-0.25 = 0.5, 0.75, inverted because upside down.
		} else if (facing == EnumFacing.EAST) {
			if (spriteLeft  != null) quads = createTrapezium(quads, left, rightHeight, leftHeight, spriteLeft, widthN, widthW, 270, colLeft);
			if (spriteRight != null) quads = createTrapezium(quads, left, leftHeight, rightHeight, spriteRight, 1-widthW, 1-widthN, 90, colRight);
		} else if (facing == EnumFacing.SOUTH) {
			if (spriteLeft  != null) quads = createTrapezium(quads, left, rightHeight, leftHeight, spriteLeft, widthN, widthW, 180, colLeft);
			if (spriteRight != null) quads = createTrapezium(quads, left, leftHeight, rightHeight, spriteRight, 1-widthW, 1-widthN, 0, colRight);
		} else {
			if (spriteLeft  != null) quads = createTrapezium(quads, left, rightHeight, leftHeight, spriteLeft, widthN, widthW, 90, colLeft);
			if (spriteRight != null) quads = createTrapezium(quads, left, leftHeight, rightHeight, spriteRight, 1-widthW, 1-widthN, 270, colRight);
		}

		return quads;
	}
}
