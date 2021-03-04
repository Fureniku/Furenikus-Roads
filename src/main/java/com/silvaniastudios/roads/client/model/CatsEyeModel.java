package com.silvaniastudios.roads.client.model;

import java.awt.Color;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.primitives.Ints;
import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.decorative.CatsEyeBlock;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class CatsEyeModel implements IBakedModel {
	
	private IBakedModel model;
	String col;
	CatsEyeBlock.EnumCatsEye dir;
	float p = 1/16F;
	private CatsEyeOverrideList overrideList;
	
	public CatsEyeModel(IBakedModel model_ns, String col, CatsEyeBlock.EnumCatsEye dir) {
		this.model = model_ns;
		this.col = col;
		this.dir = dir;
		this.overrideList = new CatsEyeOverrideList(this);
	}

	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
		List<BakedQuad> quadList = new LinkedList<BakedQuad>();
		quadList.addAll(model.getQuads(state, side, rand));
		
		if (side == null || dir == null || state == null) {
			return model.getQuads(state, side, rand);
		}
		
		TextureAtlasSprite tex = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":blocks/cats_eye_" + col);
		
		if (dir == CatsEyeBlock.EnumCatsEye.floor_ew) {
			quadList.add(RenderHelper.setBrightTexture(light(tex,
					//SW SE NE NW
					7.53F*p, 0.125F*p, 7.0617F*p, 
					7.53F*p, 0.125F*p, 8.9367F*p,
					7.75F*p, 0.625F*p, 8.9367F*p,
					7.75F*p, 0.625F*p, 7.0617F*p), 0.007F));
			quadList.add(RenderHelper.setBrightTexture(light(tex,
					//SW SE NE NW
					8.47F*p, 0.125F*p, 8.9367F*p, 
					8.47F*p, 0.125F*p, 7.0617F*p,
					8.25F*p, 0.625F*p, 7.0617F*p,
					8.25F*p, 0.625F*p, 8.9367F*p), 0.007F));
		}
		
		if (dir == CatsEyeBlock.EnumCatsEye.floor_ns) {
			quadList.add(RenderHelper.setBrightTexture(light(tex,
					//SW SE NE NW
					7.0617F*p, 0.125F*p, 8.47F*p, 
					8.9367F*p, 0.125F*p, 8.47F*p,
					8.9367F*p, 0.625F*p, 8.25F*p,
					7.0617F*p, 0.625F*p, 8.25F*p), 0.007F));
			quadList.add(RenderHelper.setBrightTexture(light(tex,
					//SW SE NE NW
					8.9367F*p, 0.125F*p, 7.53F*p, 
					7.0617F*p, 0.125F*p, 7.53F*p,
					7.0617F*p, 0.625F*p, 7.75F*p,
					8.9367F*p, 0.625F*p, 7.75F*p), 0.007F));
		}
		
		if (dir == CatsEyeBlock.EnumCatsEye.wall_north) {
			quadList.add(RenderHelper.setBrightTexture(light(tex,
					//SW SE NE NW
					8.47F*p, 7.0617F*p, (16-0.125F)*p, 
					8.25F*p, 7.0617F*p, (16-0.625F)*p,
					8.25F*p, 8.9367F*p, (16-0.625F)*p,
					8.47F*p, 8.9367F*p, (16-0.125F)*p), 0.007F));
			quadList.add(RenderHelper.setBrightTexture(light(tex,
					//SW SE NE NW
					7.75F*p, 7.0617F*p, (16-0.625F)*p, 
					7.53F*p, 7.0617F*p, (16-0.125F)*p,
					7.53F*p, 8.9367F*p, (16-0.125F)*p,
					7.75F*p, 8.9367F*p, (16-0.625F)*p), 0.007F));
		}
		
		if (dir == CatsEyeBlock.EnumCatsEye.wall_south) {
			quadList.add(RenderHelper.setBrightTexture(light(tex,
					//SW SE NE NW
					7.53F*p, 7.0617F*p, 0.125F*p, 
					7.75F*p, 7.0617F*p, 0.625F*p,
					7.75F*p, 8.9367F*p, 0.625F*p,
					7.53F*p, 8.9367F*p, 0.125F*p), 0.007F));
			quadList.add(RenderHelper.setBrightTexture(light(tex,
					//SW SE NE NW
					8.25F*p, 7.0617F*p, 0.625F*p, 
					8.47F*p, 7.0617F*p, 0.125F*p,
					8.47F*p, 8.9367F*p, 0.125F*p,
					8.25F*p, 8.9367F*p, 0.625F*p), 0.007F));
		}
		
		if (dir == CatsEyeBlock.EnumCatsEye.wall_west) {
			quadList.add(RenderHelper.setBrightTexture(light(tex,
					//SW SE NE NW
					(16-0.625F)*p, 7.0617F*p, 8.25F*p, 
					(16-0.125F)*p, 7.0617F*p, 8.47F*p,
					(16-0.125F)*p, 8.9367F*p, 8.47F*p,
					(16-0.625F)*p, 8.9367F*p, 8.25F*p), 0.007F));
			quadList.add(RenderHelper.setBrightTexture(light(tex,
					//SW SE NE NW
					(16-0.125F)*p, 7.0617F*p, 7.53F*p, 
					(16-0.625F)*p, 7.0617F*p, 7.75F*p,
					(16-0.625F)*p, 8.9367F*p, 7.75F*p,
					(16-0.125F)*p, 8.9367F*p, 7.53F*p), 0.007F));
		}
		
		if (dir == CatsEyeBlock.EnumCatsEye.wall_east) {
			quadList.add(RenderHelper.setBrightTexture(light(tex,
					//SW SE NE NW
					0.625F*p, 7.0617F*p, 7.75F*p, 
					0.125F*p, 7.0617F*p, 7.53F*p,
					0.125F*p, 8.9367F*p, 7.53F*p,
					0.625F*p, 8.9367F*p, 7.75F*p), 0.007F));
			quadList.add(RenderHelper.setBrightTexture(light(tex,
					//SW SE NE NW
					0.125F*p, 7.0617F*p, 8.47F*p, 
					0.625F*p, 7.0617F*p, 8.25F*p,
					0.625F*p, 8.9367F*p, 8.25F*p,
					0.125F*p, 8.9367F*p, 8.47F*p), 0.007F));
		}
		
		if (dir == CatsEyeBlock.EnumCatsEye.roof_ew) {
			quadList.add(RenderHelper.setBrightTexture(light(tex,
					//SW SE NE NW
					8.47F*p, (16-0.125F)*p, 7.0617F*p, 
					8.47F*p, (16-0.125F)*p, 8.9367F*p,
					8.25F*p, (16-0.625F)*p, 8.9367F*p,
					8.25F*p, (16-0.625F)*p, 7.0617F*p), 0.007F));
			quadList.add(RenderHelper.setBrightTexture(light(tex,
					//SW SE NE NW
					7.53F*p, (16-0.125F)*p, 8.9367F*p, 
					7.53F*p, (16-0.125F)*p, 7.0617F*p,
					7.75F*p, (16-0.625F)*p, 7.0617F*p,
					7.75F*p, (16-0.625F)*p, 8.9367F*p), 0.007F));
		}
		
		if (dir == CatsEyeBlock.EnumCatsEye.roof_ns) {
			quadList.add(RenderHelper.setBrightTexture(light(tex,
					//SW SE NE NW
					7.0617F*p, (16-0.125F)*p, 7.53F*p, 
					8.9367F*p, (16-0.125F)*p, 7.53F*p,
					8.9367F*p, (16-0.625F)*p, 7.75F*p,
					7.0617F*p, (16-0.625F)*p, 7.75F*p), 0.007F));
			quadList.add(RenderHelper.setBrightTexture(light(tex,
					//SW SE NE NW
					8.9367F*p, (16-0.125F)*p, 8.47F*p, 
					7.0617F*p, (16-0.125F)*p, 8.47F*p,
					7.0617F*p, (16-0.625F)*p, 8.25F*p,
					8.9367F*p, (16-0.625F)*p, 8.25F*p), 0.007F));
		}
		return quadList;
	}
	
	private BakedQuad light(TextureAtlasSprite texture, float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float x4, float y4, float z4) {
		int packednormal;
		packednormal = RenderHelper.calculatePackedNormal(x1, y1, z1,  x2, y2, z2,  x3, y3, z3,  x4, y4, z4);
		return new BakedQuad(Ints.concat(
				RenderHelper.vertexToInts(x1, y1, z1, Color.WHITE.getRGB(), texture, 16, 16, packednormal),
				RenderHelper.vertexToInts(x2, y2, z2, Color.WHITE.getRGB(), texture, 16, 0, packednormal),
				RenderHelper.vertexToInts(x3, y3, z3, Color.WHITE.getRGB(), texture, 0, 0, packednormal),
				RenderHelper.vertexToInts(x4, y4, z4, Color.WHITE.getRGB(), texture, 0, 16, packednormal)),
				0, EnumFacing.SOUTH, texture, true, net.minecraft.client.renderer.vertex.DefaultVertexFormats.ITEM);
	}

	@Override
	public boolean isAmbientOcclusion() {
		return model.isAmbientOcclusion();
	}

	@Override
	public boolean isGui3d() {
		return model.isGui3d();
	}

	@Override
	public boolean isBuiltInRenderer() {
		return false;
	}

	@Override
	public TextureAtlasSprite getParticleTexture() {
		return model.getParticleTexture();
	}

	@Override
	public ItemOverrideList getOverrides() {
		return this.overrideList;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public ItemCameraTransforms getItemCameraTransforms() {
		return model.getItemCameraTransforms();
	}
	
	@Override
	public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) {
		Matrix4f matrix4f = model.handlePerspective(cameraTransformType).getRight();
		return Pair.of(this, matrix4f);
	}
	
	public CatsEyeModel setCurrentItemStack(ItemStack stack) {
		return this;
	}
	
	private static class CatsEyeOverrideList extends ItemOverrideList {
		private CatsEyeModel model;
		
		public CatsEyeOverrideList(CatsEyeModel model) {
			super(Collections.emptyList());
			this.model = model;
		}
		
		@Override
		public IBakedModel handleItemState(IBakedModel originalModel, ItemStack stack, World world, EntityLivingBase entity) {
			return this.model.setCurrentItemStack(stack);
		}
	}

}
