package com.silvaniastudios.roads.client.model.paint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.diagonal.HalfBlock;
import com.silvaniastudios.roads.blocks.diagonal.RoadBlockDiagonal;
import com.silvaniastudios.roads.blocks.diagonal.ShapeLibrary;
import com.silvaniastudios.roads.client.render.Quad;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.model.pipeline.UnpackedBakedQuad;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.property.IExtendedBlockState;

public class PaintBakedModelBase implements IBakedModel {

	protected VertexFormat format;
	Minecraft mc;

	public PaintBakedModelBase(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
		this.format = format;
		mc = Minecraft.getMinecraft();
	}

	//Direct from mcjty's tutorial on IModel usage https://wiki.mcjty.eu/modding/index.php?title=Render_Block_Baked_Model-1.12
	protected void putVertex(UnpackedBakedQuad.Builder builder, Vec3d normal, double x, double y, double z, float u, float v, TextureAtlasSprite sprite) {
		for (int e = 0; e < format.getElementCount(); e++) {
			switch (format.getElement(e).getUsage()) {
			case POSITION:
				builder.put(e, (float)x, (float)y, (float)z, 1.0f);
				break;
			case COLOR:
				builder.put(e, 1.0f, 1.0f, 1.0f, 1.0f);
				break;
			case UV:
				if (format.getElement(e).getIndex() == 0) {
					u = sprite.getInterpolatedU(u);
					v = sprite.getInterpolatedV(v);
					builder.put(e, u, v, 0f, 1f);
					break;
				}
			case NORMAL:
				builder.put(e, (float) normal.x, (float) normal.y, (float) normal.z, 0f);
				break;
			default:
				builder.put(e);
				break;
			}
		}
	}

	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
		if (side != null) {
			return Collections.emptyList();
		}

		IExtendedBlockState extendedState = (IExtendedBlockState) state;

		return packQuads(extendedState);
	}

	//Overriden in subclasses
	protected List<BakedQuad> packQuads(IExtendedBlockState state) {
		List<BakedQuad> quads = new ArrayList<>();
		return quads;
	}

	@Override public ItemOverrideList getOverrides() { return null; }
	@Override public boolean isAmbientOcclusion() { return true; }
	@Override public boolean isGui3d() { return false; }
	@Override public boolean isBuiltInRenderer() { return false; }
	@Override public TextureAtlasSprite getParticleTexture() { return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":blocks/road_block_standard"); }
	@Override public ItemCameraTransforms getItemCameraTransforms() { return ItemCameraTransforms.DEFAULT; }


	protected List<BakedQuad> createModelFromTexture(List<BakedQuad> quads) {
		List<Quad> rawQuads = ShapeLibrary.shapeFromTexture(null);
		int rot = 0;
		return shapeBuilder(rawQuads, quads, rot);
	}

	//Works for most default shapes that assume 90 degree rotations.
	protected List<BakedQuad> shapeBuilder(List<Quad> rawQuads, List<BakedQuad> quads, int rotation) {
		for (int i = 0; i < rawQuads.size(); i++) {
			if (rawQuads.get(i) != null) {
				rawQuads.set(i, Quad.rotateQuadY(rawQuads.get(i), rotation));
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
}