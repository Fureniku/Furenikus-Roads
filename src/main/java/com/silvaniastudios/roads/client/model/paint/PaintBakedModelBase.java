package com.silvaniastudios.roads.client.model.paint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.diagonal.ShapeLibrary;
import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;
import com.silvaniastudios.roads.blocks.paint.customs.CustomPaintBlock;
import com.silvaniastudios.roads.blocks.paint.customs.ICustomBlock;
import com.silvaniastudios.roads.client.render.Quad;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.model.pipeline.UnpackedBakedQuad;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.property.IExtendedBlockState;

public abstract class PaintBakedModelBase implements IBakedModel {

	private final PaintOverrideList overrideList;

	protected TextureAtlasSprite[] sprites;
	protected ItemStack stack;
	protected VertexFormat format;
	protected Minecraft mc;
	private List<BakedQuad> itemQuadsCache = null;

	public PaintBakedModelBase(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
		this.format = format;

		mc = Minecraft.getMinecraft();
		stack = null;

		overrideList = new PaintOverrideList(this);
	}

	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
		if (side != null) {
			return Collections.emptyList();
		}

		return packQuads(state);
	}

	//Overriden in subclasses
	protected List<BakedQuad> packQuads(IBlockState state) {
		List<BakedQuad> quads = new ArrayList<>();
		return quads;
	}

	@Override public ItemOverrideList getOverrides() {
		return this.overrideList;
	}
	@Override public boolean isAmbientOcclusion() { return true; }
	@Override public boolean isGui3d() { return false; }
	@Override public boolean isBuiltInRenderer() { return false; }
	@Override public TextureAtlasSprite getParticleTexture() { return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":blocks/road_block_standard"); }
	@Override public ItemCameraTransforms getItemCameraTransforms() { return ItemCameraTransforms.DEFAULT; }

	public List<BakedQuad> handleItemRendering() {
		List<BakedQuad> quads = new ArrayList<>();
		List<Quad> rawQuads = new ArrayList<>();

		CustomPaintBlock paintBlock = (CustomPaintBlock) ((ItemBlock) stack.getItem()).getBlock();
		if (paintBlock.isInternal()) {
			List<Quad> spriteQuads = getSpriteQuads();

			rawQuads.addAll(spriteQuads);
			quads = shapeBuilderItem(rawQuads, quads, paintBlock.getColour().getColourInt(), 0, 0);
			return quads;
		} else {
			if (itemQuadsCache == null) {
				int colId = paintBlock.getColour().getId();
				boolean[][]grid = ((ICustomBlock) paintBlock).getGrid(stack.getItemDamage() < 7 ? 0 : 1).getGrid();

				rawQuads = ShapeLibrary.shapeFromGrid(grid, 0.5f, sprites[colId], format, true);

				itemQuadsCache = shapeBuilderItem(rawQuads, quads, paintBlock.getColour().getColourInt(), 90, stack.isOnItemFrame() ? 180 : 0);
			}
			return itemQuadsCache;
		}
	}

	protected List<BakedQuad> shapeBuilderItem(List<Quad> rawQuads, List<BakedQuad> quads, int col, int xRot, int yRot) {
		for (int i = 0; i < rawQuads.size(); i++) {
			if (rawQuads.get(i) != null) {
				rawQuads.set(i, Quad.rotateQuadX(rawQuads.get(i), xRot).rotateQuadY(rawQuads.get(i), yRot));
				BakedQuad baked = rawQuads.get(i).createQuad(col);

				quads.add(baked);
			}
		}

		return quads;
	}

	public List<Quad> getSpriteQuads() {
		PaintBlockBase paintBlock = (PaintBlockBase) ((ItemBlock) stack.getItem()).getBlock();
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":paints/" + paintBlock.getIconName() + "_" + stack.getItemDamage());

		return getSpriteQuads(sprite);
	}

	public List<Quad> getSpriteQuads(TextureAtlasSprite sprite) {
		List<Quad> quads = new ArrayList<>();

		Quad front = new Quad(
				new Vec3d(0, 1, 0.5), 16f, 0f, //BL
				new Vec3d(1, 1, 0.5), 0f, 0f,//BR
				new Vec3d(1, 0, 0.5), 0f, 16f, //TR
				new Vec3d(0, 0, 0.5), 16f, 16f, //TL
				sprite, format);

		Quad back = new Quad(
				new Vec3d(1, 1, 0.5), 16f, 0f, //BL
				new Vec3d(0, 1, 0.5), 0f, 0f,//BR
				new Vec3d(0, 0, 0.5), 0f, 16f, //TR
				new Vec3d(1, 0, 0.5), 16f, 16f, //TL
				sprite, format);

		quads.add(front);
		quads.add(back);

		return quads;
	}
	
	public PaintBakedModelBase setCurrentItemStack(ItemStack stack) {
		this.stack = stack;
		return this;
	}

	protected List<BakedQuad> shapeBuilder(List<Quad> rawQuads, List<BakedQuad> quads, int rotX, int rotY) {
		for (int i = 0; i < rawQuads.size(); i++) {
			if (rawQuads.get(i) != null) {
				rawQuads.set(i, Quad.rotateQuadX(rawQuads.get(i), rotX).rotateQuadY(rawQuads.get(i), rotY));
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

	private static class PaintOverrideList extends ItemOverrideList {
		private PaintBakedModelBase model;

		public PaintOverrideList(PaintBakedModelBase model) {
			super(Collections.emptyList());
			this.model = model;
		}

		@Override
		public IBakedModel handleItemState(IBakedModel originalModel, ItemStack stack, World world, EntityLivingBase entity) {
			return this.model.setCurrentItemStack(stack);
		}
	}
}