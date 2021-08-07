package com.silvaniastudios.roads.client.model.paint;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.silvaniastudios.roads.blocks.paint.LinePaintBlock;
import com.silvaniastudios.roads.client.render.Quad;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
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
		TextureAtlasSprite sprite = mc.getBlockRendererDispatcher().getModelForState(state).getParticleTexture();
		System.out.println("################################ PACK QUADS ############################");
		
		List<Quad> rawQuads = new ArrayList<>();
		float v = 1.0f / 16.0f;
		if (sprite != null) {
			if (facing == LinePaintBlock.EnumRotation.ns) {
				//rawQuads.addAll(shapeLine(format, sprite, v*7, v*9, 0.0f, v*7, 1.0f, 1.0f, 1.0f,  1.0f));
				//rawQuads.addAll(shapeLine(format, sprite, v*7, v*9, v*9, 1.0f, 1.0f, 1.0f, 1.0f,  1.0f));
				//quads = shapeBuilder(rawQuads, quads);
			} else if (facing == LinePaintBlock.EnumRotation.ew) {
				//quads = createModelFromTexture(quads);
			} else if (facing == LinePaintBlock.EnumRotation.connect) {
				//quads = createModelFromTexture(quads);
			}
		}
		System.out.println("Returning quads to pack");
		return quads;
	}
	
	//Works for most default shapes that assume 90 degree rotations.
	protected List<BakedQuad> shapeBuilder(List<Quad> rawQuads, List<BakedQuad> quads) {
		for (int i = 0; i < rawQuads.size(); i++) {
			if (rawQuads.get(i) != null) {
				rawQuads.set(i, Quad.rotateQuadY(rawQuads.get(i), 0));
			}
		}

		if (rawQuads.get(0) != null) {
			rawQuads.get(0).updateUVs(); //Prevent UV rotation on top face
		}
		
		if (rawQuads.get(1) != null) {
			rawQuads.get(1).setFlipV(true); //Flip UVs for bottom face
			rawQuads.get(1).updateUVs(); //Prevent UV rotation on bottom face
		}

		for (int i = 0; i < rawQuads.size(); i++) {
			if (rawQuads.get(i) != null) {
				BakedQuad baked = rawQuads.get(i).createQuad(0);
				
				quads.add(baked);
			}
		}

		return quads;
	}
	
	public static List<Quad> shapeLine(VertexFormat format, TextureAtlasSprite sprite, float x1, float z1, float x2, float z2, float heightSW, float heightSE, float heightNE, float heightNW) {
		List<Quad> quads = new ArrayList<>();
		
		Quad north = null;
		Quad east = null;
		Quad south = null;
		Quad west = null;

		Quad top = new Quad( //Top
				new Vec3d(x1, heightSW, z2),   //BL
				new Vec3d(x2, heightSE, z2),     //BR
				new Vec3d(x2, heightNE, z1), //TR
				new Vec3d(x1, heightNW, z1),     //TL
				sprite, format);
		
		quads.add(top);
		return quads;
	}
}
