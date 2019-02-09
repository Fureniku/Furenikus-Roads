package com.silvaniastudios.roads.client.model;

import java.awt.Color;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.primitives.Ints;
import com.silvaniastudios.roads.FurenikusRoads;

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

public class PaintFillerBakedModel implements IBakedModel {
	
	private IBakedModel model;
	float p = 1/16F;
	private CatsEyeOverrideList overrideList;
	String rot;
	boolean hasGun = false;
	
	public PaintFillerBakedModel(IBakedModel model, String rot, boolean hasGun) {
		this.model = model;
		this.rot = rot;
		this.hasGun = hasGun;
		this.overrideList = new CatsEyeOverrideList(this);
	}

	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
		List<BakedQuad> quadList = new LinkedList<BakedQuad>();
		quadList.addAll(model.getQuads(state, side, rand));
		
		if (side == null) {
			return model.getQuads(state, side, rand);
		}
		
		TextureAtlasSprite tex = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":blocks/machine_vent_back_on");
		/*TextureAtlasSprite tex_display = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":blocks/paint_filler_machine_display");
		
		TextureAtlasSprite tex_display_white  = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":items/paint_white");
		TextureAtlasSprite tex_display_yellow = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":items/paint_yellow");
		TextureAtlasSprite tex_display_red    = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":items/paint_red");
		
		float fw = 2.25F;
		float fy = 2.25F;
		float fr = 2.25F;*/
		
		if (rot.equalsIgnoreCase("north")) {
			quadList.add(RenderHelper.setBrightTexture(light(tex,
					//SW SE NE NW
					0.24F*p, 1.25F*p, 1F   *p, 
					0.24F*p, 1.25F*p, 7.25F*p,
					0.24F*p, 6.25F*p, 7.25F*p,
					0.24F*p, 6.25F*p, 1F   *p), 0.007F));
			quadList.add(RenderHelper.setBrightTexture(light(tex,
					//SW SE NE NW
					7.25F*p, 1.25F*p, 0.24F*p, 
					1F   *p, 1.25F*p, 0.24F*p,
					1F   *p, 6.25F*p, 0.24F*p,
					7.25F*p, 6.25F*p, 0.24F*p), 0.007F));
			/*quadList.add(RenderHelper.setBrightTexture(light(tex_display,
					//SW SE NE NW
					 0F*p,  0F*p, -0.01F*p,
					 0F*p, 16F*p, -0.01F*p,
					16F*p, 16F*p, -0.01F*p,
					16F*p,  0F*p, -0.01F*p), 0.007F));
			
			quadList.add(RenderHelper.setBrightTexture(light(tex_display_white,
					//SW SE NE NW
					6.5F*p,  10F    *p, -0.02F*p,
					5.5F*p,  10F    *p, -0.02F*p,
					5.5F*p, (10F+fw)*p, -0.02F*p,
					6.5F*p, (10F+fw)*p, -0.02F*p), 0.007F));
			quadList.add(RenderHelper.setBrightTexture(light(tex_display_yellow,
					//SW SE NE NW
					4.5F*p,  10F    *p, -0.02F*p,
					3.5F*p,  10F    *p, -0.02F*p,
					3.5F*p, (10F+fy)*p, -0.02F*p,
					4.5F*p, (10F+fy)*p, -0.02F*p), 0.007F));
			quadList.add(RenderHelper.setBrightTexture(light(tex_display_red,
					//SW SE NE NW
					2.5F*p,  10F    *p, -0.02F*p,
					1.5F*p,  10F    *p, -0.02F*p,
					1.5F*p, (10F+fr)*p, -0.02F*p,
					2.5F*p, (10F+fr)*p, -0.02F*p), 0.007F));*/
		}
		
		if (rot.equalsIgnoreCase("east")) {
			quadList.add(RenderHelper.setBrightTexture(light(tex,
					//SW SE NE NW
					15F  *p, 1.25F*p, 0.24F*p, 
					8.75F*p, 1.25F*p, 0.24F*p,
					8.75F*p, 6.25F*p, 0.24F*p,
					15F  *p, 6.25F*p, 0.24F*p), 0.007F));
			quadList.add(RenderHelper.setBrightTexture(light(tex,
					//SW SE NE NW
					15.76F*p, 1.25F*p, 7.25F*p, 
					15.76F*p, 1.25F*p, 1F   *p,
					15.76F*p, 6.25F*p, 1F   *p,
					15.76F*p, 6.25F*p, 7.25F*p), 0.007F));
			/*quadList.add(RenderHelper.setBrightTexture(light(tex_display,
					//SW SE NE NW
					16.01F*p,  0F*p,  0F*p,
					16.01F*p, 16F*p,  0F*p,
					16.01F*p, 16F*p, 16F*p,
					16.01F*p,  0F*p, 16F*p), 0.007F));
			
			quadList.add(RenderHelper.setBrightTexture(light(tex_display_white,
					//SW SE NE NW
					16.02F*p,  10F    *p, 6.5F*p,
					16.02F*p,  10F    *p, 5.5F*p,
					16.02F*p, (10F+fw)*p, 5.5F*p,
					16.02F*p, (10F+fw)*p, 6.5F*p), 0.007F));
			quadList.add(RenderHelper.setBrightTexture(light(tex_display_yellow,
					//SW SE NE NW
					16.02F*p,  10F    *p, 4.5F*p,
					16.02F*p,  10F    *p, 3.5F*p,
					16.02F*p, (10F+fy)*p, 3.5F*p,
					16.02F*p, (10F+fy)*p, 4.5F*p), 0.007F));
			quadList.add(RenderHelper.setBrightTexture(light(tex_display_red,
					//SW SE NE NW
					16.02F*p,  10F    *p, 2.5F*p,
					16.02F*p,  10F    *p, 1.5F*p,
					16.02F*p, (10F+fr)*p, 1.5F*p,
					16.02F*p, (10F+fr)*p, 2.5F*p), 0.007F));*/
		}
		
		if (rot.equalsIgnoreCase("south")) {
			quadList.add(RenderHelper.setBrightTexture(light(tex,
					//SW SE NE NW
					15.76F*p, 1.25F*p, 15F  *p, 
					15.76F*p, 1.25F*p, 8.75F*p,
					15.76F*p, 6.25F*p, 8.75F*p,
					15.76F*p, 6.25F*p, 15F  *p), 0.007F));
			quadList.add(RenderHelper.setBrightTexture(light(tex,
					//SW SE NE NW
					8.75F*p, 1.25F*p, 15.76F*p, 
					15F  *p, 1.25F*p, 15.76F*p,
					15F  *p, 6.25F*p, 15.76F*p,
					8.75F*p, 6.25F*p, 15.76F*p), 0.007F));
			/*quadList.add(RenderHelper.setBrightTexture(light(tex_display,
					//SW SE NE NW
					16F*p,  0F*p, 16.01F*p,
					16F*p, 16F*p, 16.01F*p,
					 0F*p, 16F*p, 16.01F*p,
					 0F*p,  0F*p, 16.01F*p), 0.007F));
			
			quadList.add(RenderHelper.setBrightTexture(light(tex_display_white,
					//SW SE NE NW
					 9.5F*p,  10F    *p, 16.02F*p,
					10.5F*p,  10F    *p, 16.02F*p,
					10.5F*p, (10F+fw)*p, 16.02F*p,
					 9.5F*p, (10F+fw)*p, 16.02F*p), 0.007F));
			quadList.add(RenderHelper.setBrightTexture(light(tex_display_yellow,
					//SW SE NE NW
					11.5F*p,  10F    *p, 16.02F*p,
					12.5F*p,  10F    *p, 16.02F*p,
					12.5F*p, (10F+fy)*p, 16.02F*p,
					11.5F*p, (10F+fy)*p, 16.02F*p), 0.007F));
			quadList.add(RenderHelper.setBrightTexture(light(tex_display_red,
					//SW SE NE NW
					13.5F*p,  10F    *p, 16.02F*p,
					14.5F*p,  10F    *p, 16.02F*p,
					14.5F*p, (10F+fr)*p, 16.02F*p,
					13.5F*p, (10F+fr)*p, 16.02F*p), 0.007F));*/
		}
		
		if (rot.equalsIgnoreCase("west")) {
			quadList.add(RenderHelper.setBrightTexture(light(tex,
					//SW SE NE NW
					1F   *p, 1.25F*p, 15.76F*p, 
					7.25F*p, 1.25F*p, 15.76F*p,
					7.25F*p, 6.25F*p, 15.76F*p,
					1F   *p, 6.25F*p, 15.76F*p), 0.007F));
			quadList.add(RenderHelper.setBrightTexture(light(tex,
					//SW SE NE NW
					0.24F*p, 1.25F*p, 8.75F*p, 
					0.24F*p, 1.25F*p, 15F  *p,
					0.24F*p, 6.25F*p, 15F  *p,
					0.24F*p, 6.25F*p, 8.75F*p), 0.007F));
			/*quadList.add(RenderHelper.setBrightTexture(light(tex_display,
					//SW SE NE NW
					-0.01F*p,  0F*p, 16F*p,
					-0.01F*p, 16F*p, 16F*p,
					-0.01F*p, 16F*p,  0F*p,
					-0.01F*p,  0F*p,  0F*p), 0.007F));
			
			quadList.add(RenderHelper.setBrightTexture(light(tex_display_white,
					//SW SE NE NW
					-0.02F*p,  10F    *p,  9.5F*p,
					-0.02F*p,  10F    *p, 10.5F*p,
					-0.02F*p, (10F+fw)*p, 10.5F*p,
					-0.02F*p, (10F+fw)*p,  9.5F*p), 0.007F));
			quadList.add(RenderHelper.setBrightTexture(light(tex_display_yellow,
					//SW SE NE NW
					-0.02F*p,  10F    *p, 11.5F*p,
					-0.02F*p,  10F    *p, 12.5F*p,
					-0.02F*p, (10F+fy)*p, 12.5F*p,
					-0.02F*p, (10F+fy)*p, 11.5F*p), 0.007F));
			quadList.add(RenderHelper.setBrightTexture(light(tex_display_red,
					//SW SE NE NW
					-0.02F*p,  10F    *p, 13.5F*p,
					-0.02F*p,  10F    *p, 14.5F*p,
					-0.02F*p, (10F+fr)*p, 14.5F*p,
					-0.02F*p, (10F+fr)*p, 13.5F*p), 0.007F));*/
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
		return null;
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
	
	public PaintFillerBakedModel setCurrentItemStack(ItemStack stack) {
		return this;
	}
	
	private static class CatsEyeOverrideList extends ItemOverrideList {
		private PaintFillerBakedModel model;
		
		public CatsEyeOverrideList(PaintFillerBakedModel model) {
			super(Collections.emptyList());
			this.model = model;
		}
		
		@Override
		public IBakedModel handleItemState(IBakedModel originalModel, ItemStack stack, World world, EntityLivingBase entity) {
			return this.model.setCurrentItemStack(stack);
		}
	}

}
