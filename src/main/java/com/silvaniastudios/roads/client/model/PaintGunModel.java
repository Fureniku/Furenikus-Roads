package com.silvaniastudios.roads.client.model;

import java.awt.Color;
import java.util.*;

import javax.vecmath.Matrix4f;

import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.PaintColour;
import com.silvaniastudios.roads.blocks.diagonal.ShapeLibrary;
import com.silvaniastudios.roads.blocks.paint.customs.CustomPaintBlock;
import com.silvaniastudios.roads.blocks.paint.customs.ICustomBlock;
import com.silvaniastudios.roads.client.render.Quad;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.Vec3d;
import org.apache.commons.lang3.tuple.Pair;

import com.google.common.primitives.Ints;
import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;
import com.silvaniastudios.roads.blocks.tileentities.paintfiller.PaintFillerEntity;
import com.silvaniastudios.roads.registries.PaintGunItemRegistry;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class PaintGunModel implements IBakedModel {
	
	private IBakedModel mainModel;
	protected TextureAtlasSprite[] sprites;
	private final PaintGunOverrideList overrideList;
	private ItemStack stack;
	private List<BakedQuad> itemQuadsCache = null;

	//Used to detect selection changes
	private int selOld;
	private int pageOld;
	
	public static final ModelResourceLocation modelResourceLocation = new ModelResourceLocation(FurenikusRoads.MODID + ":paint_gun", "inventory");
	
	public PaintGunModel(IBakedModel mainModel) {
		this.mainModel = mainModel;
		this.overrideList = new PaintGunOverrideList(this);
		this.stack = null;
		sprites = new TextureAtlasSprite[FRBlocks.col.length];
		for (int i = 0; i < FRBlocks.col.length; i++) {
			sprites[i] = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":blocks/paint_" + FRBlocks.col[i].getName());
		}
	}

	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
		if (!stack.hasTagCompound()) {
			return mainModel.getQuads(state, side, rand);
		}

		NBTTagCompound nbt = stack.getTagCompound();
		int white_paint = nbt.getInteger("white_paint");
		int yellow_paint = nbt.getInteger("yellow_paint");
		int red_paint = nbt.getInteger("red_paint");
		
		List<BakedQuad> combinedQuadList = new ArrayList<BakedQuad>(mainModel.getQuads(state, side, rand));
		//Colour number IDs are backwards here. I made everything the wrong way around and I'm too lazy to re-do it correctly. This fixes it fine.
		combinedQuadList.addAll(getTankFluid(2, white_paint));
		combinedQuadList.addAll(getTankFluid(1, yellow_paint));
		combinedQuadList.addAll(getTankFluid(0, red_paint));
		
		int selId = nbt.getInteger("selectedId");
		int pageId = nbt.getInteger("pageId");

		if (selId != selOld || pageId != pageOld) {
			itemQuadsCache = null; //Clear cache if selections changed
			selOld = selId;
			pageOld = pageId;
		}

		PaintColour col = PaintColour.getFromName(nbt.getString("colour"));
		PaintBlockBase block = PaintGunItemRegistry.getSelectedPaint(pageId, selId);
		if (block instanceof CustomPaintBlock && !((CustomPaintBlock) block).isInternal()) {
			combinedQuadList.addAll(createCustomDisplay((CustomPaintBlock) block));
		} else {
			combinedQuadList.addAll(createDisplay(getDisplayTexture(block, PaintGunItemRegistry.getSelectedPaintMeta(pageId, selId)), col.getColourInt()));
		}

		
		return combinedQuadList;
	}
	
	public PaintGunModel setCurrentItemStack(ItemStack stack) {
		this.stack = stack;
		return this;
	}
	
	private float getPercentage(int num) {
		int max = PaintFillerEntity.GUN_TANK_CAP;
		float x = (float) num / (float) max;
		float y = x*100;
		if (y > 100) { return 100; }
		return y;
	}

	private List<BakedQuad> getTankFluid(int col, int paintLevel) {
		//As above, the col IDs are reversed here because lazy.
		TextureAtlasSprite texture = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":items/paint_red");
		if (col == 1) { texture = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":items/paint_yellow"); }
		if (col == 2) { texture = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":items/paint_white"); }
		
		List<BakedQuad> list = new ArrayList<BakedQuad>();
		
		float fluidWidth = 0.0546875F;
		float backTankMiddle = 0.75390625F+(fluidWidth/2);
		float baseHeight = 0.246875F;
		float fluidHeight = 0.0F;
		
		fluidHeight = getPercentage(paintLevel) * 0.00125F;
		
		list.add(createBakedQuadForFace(0.5F, fluidWidth, baseHeight + fluidHeight, 0.11875F, backTankMiddle - (col*0.125F) - (fluidWidth/2), 0, texture, EnumFacing.NORTH));
		list.add(createBakedQuadForFace(0.5F, fluidWidth, baseHeight + fluidHeight, 0.11875F, backTankMiddle - (col*0.125F) + (fluidWidth/2), 0, texture, EnumFacing.SOUTH));
		list.add(createBakedQuadForFace(backTankMiddle - (col*0.125F), fluidWidth, baseHeight + fluidHeight, 0.11875F, 0.5F + (fluidWidth/2), 0, texture, EnumFacing.EAST));
		list.add(createBakedQuadForFace(backTankMiddle - (col*0.125F), fluidWidth, baseHeight + fluidHeight, 0.11875F, 0.5F - (fluidWidth/2), 0, texture, EnumFacing.WEST));
		list.add(createBakedQuadForFace(0.5F, fluidWidth, 0.75F - (col*0.125F) + (fluidWidth/2), fluidWidth, baseHeight + fluidHeight + 0.0585F, 0, texture, EnumFacing.UP));
		return list;
	}
	
	private TextureAtlasSprite getDisplayTexture(PaintBlockBase block, int meta) {
		return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":paints/" + block.getIconName() + "_" + meta);
	}

	public Quad getSpriteQuad(TextureAtlasSprite sprite) {
		Quad quad = new Quad(
				new Vec3d(0.3671875F, 0.271875F, 0.621875F), 16f, 0f, //BL
				new Vec3d(0.2578125F, 0.271875F, 0.621875F), 0f, 0f,//BR
				new Vec3d(0.2578125F, 0.171875F, 0.66375F), 0f, 16f, //TR
				new Vec3d(0.3671875F, 0.171875F, 0.66375F), 16f, 16f, //TL
				sprite, DefaultVertexFormats.ITEM);

		return quad;
	}
	
	private List<BakedQuad> createDisplay(TextureAtlasSprite texture, int col) {
		List<BakedQuad> list = new ArrayList<>();
		list.add(getSpriteQuad(texture).createQuad(col));
		return list;
	}

	private List<BakedQuad> createCustomDisplay(CustomPaintBlock paintBlock) {
		List<Quad> rawQuads;
		if (itemQuadsCache == null) {
			int colId = PaintColour.getFromName(stack.getTagCompound().getString("colour")).getId();
			boolean[][]grid = ((ICustomBlock) paintBlock).getGrid(stack.getItemDamage() < 7 ? 0 : 1).getGrid();

			rawQuads = ShapeLibrary.shapeFromGridFlat(grid, 0.2578125F, 0.3671875F, 0.171875F, 0.271875F, 0.621875F, 0.66375F, sprites[colId]);

			itemQuadsCache = shapeBuilderItem(rawQuads, paintBlock.getColour().getColourInt(), 0, 0);
		}
		return itemQuadsCache;
	}

	protected List<BakedQuad> shapeBuilderItem(List<Quad> rawQuads, int col, int xRot, int yRot) {
		List<BakedQuad> bakedQuads = new ArrayList<>();
		for (int i = 0; i < rawQuads.size(); i++) {
			if (rawQuads.get(i) != null) {
				rawQuads.set(i, Quad.rotateQuadX(rawQuads.get(i), xRot).rotateQuadY(rawQuads.get(i), yRot));
				BakedQuad baked = rawQuads.get(i).createQuad(col);

				bakedQuads.add(baked);
			}
		}

		return bakedQuads;
	}
	
	private BakedQuad createBakedQuadForFace(float centreLR, float width, float centreUD, float height, float forwardDisplacement, int itemRenderLayer, TextureAtlasSprite texture, EnumFacing face) {
		float x1, x2, x3, x4;
		float y1, y2, y3, y4;
		float z1, z2, z3, z4;
		int packednormal;

		switch (face) {
			case UP: {
				x1 = x2 = centreLR + width/2.0F;
				x3 = x4 = centreLR - width/2.0F;
				z1 = z4 = centreUD + height/2.0F;
				z2 = z3 = centreUD - height/2.0F;
				y1 = y2 = y3 = y4 = forwardDisplacement;
				break;
			}
			case DOWN: {
				x1 = x2 = centreLR + width/2.0F;
				x3 = x4 = centreLR - width/2.0F;
				z1 = z4 = centreUD - height/2.0F;
				z2 = z3 = centreUD + height/2.0F;
				y1 = y2 = y3 = y4 = forwardDisplacement;
				break;
			}
		 	case WEST: {
				z1 = z2 = centreLR + width/2.0F;
				z3 = z4 = centreLR - width/2.0F;
				y1 = y4 = centreUD - height/2.0F;
				y2 = y3 = centreUD + height/2.0F;
				x1 = x2 = x3 = x4 = forwardDisplacement;
				break;
		 	}
		 	case EAST: {
				z1 = z2 = centreLR - width/2.0F;
				z3 = z4 = centreLR + width/2.0F;
				y1 = y4 = centreUD - height/2.0F;
				y2 = y3 = centreUD + height/2.0F;
				x1 = x2 = x3 = x4 = forwardDisplacement;
				break;
			}
			case NORTH: {
				x1 = x2 = centreLR - width/2.0F;
				x3 = x4 = centreLR + width/2.0F;
				y1 = y4 = centreUD - height/2.0F;
				y2 = y3 = centreUD + height/2.0F;
				z1 = z2 = z3 = z4 = forwardDisplacement;
				break;
			}
			case SOUTH: {
				x1 = x2 = centreLR + width/2.0F;
				x3 = x4 = centreLR - width/2.0F;
				y1 = y4 = centreUD - height/2.0F;
				y2 = y3 = centreUD + height/2.0F;
				z1 = z2 = z3 = z4 = forwardDisplacement;
				break;
			}
			default: {
				assert false : "Unexpected facing in createBakedQuadForFace:" + face;
				return null;
			}
		}
		
		packednormal = calculatePackedNormal(x1, y1, z1,  x2, y2, z2,  x3, y3, z3,  x4, y4, z4);
		return new BakedQuad(Ints.concat(
				vertexToInts(x1, y1, z1, Color.WHITE.getRGB(), texture, 16, 16, packednormal),
				vertexToInts(x2, y2, z2, Color.WHITE.getRGB(), texture, 16, 0, packednormal),
				vertexToInts(x3, y3, z3, Color.WHITE.getRGB(), texture, 0, 0, packednormal),
				vertexToInts(x4, y4, z4, Color.WHITE.getRGB(), texture, 0, 16, packednormal)),
				itemRenderLayer, face, texture, true, net.minecraft.client.renderer.vertex.DefaultVertexFormats.ITEM);
	}
	
	private int[] vertexToInts(float x, float y, float z, int color, TextureAtlasSprite texture, float u, float v, int normal) {
		return new int[] {
				Float.floatToRawIntBits(x),
				Float.floatToRawIntBits(y),
				Float.floatToRawIntBits(z),
				color,
				Float.floatToRawIntBits(texture.getInterpolatedU(u)),
				Float.floatToRawIntBits(texture.getInterpolatedV(v)),
				normal
		};
	}
	
	private int calculatePackedNormal(float x1, float y1, float z1, float x2, float y2, float z2,
			float x3, float y3, float z3, float x4, float y4, float z4) {

		float xp = x4-x2;
		float yp = y4-y2;
		float zp = z4-z2;

		float xq = x3-x1;
		float yq = y3-y1;
		float zq = z3-z1;

		//Cross Product
		float xn = yq*zp - zq*yp;
		float yn = zq*xp - xq*zp;
		float zn = xq*yp - yq*xp;

		//Normalize
		float norm = (float)Math.sqrt(xn*xn + yn*yn + zn*zn);
		final float SMALL_LENGTH =  1.0E-4F;  //Vec3d.normalise() uses this
		if (norm < SMALL_LENGTH) norm = 1.0F;  // protect against degenerate quad

		norm = 1.0F / norm;
		xn *= norm;
		yn *= norm;
		zn *= norm;

		int x = ((byte)(xn * 127)) & 0xFF;
		int y = ((byte)(yn * 127)) & 0xFF;
		int z = ((byte)(zn * 127)) & 0xFF;
		return x | (y << 0x08) | (z << 0x10);
	}
	
	@Override
	public boolean isAmbientOcclusion() {
		return this.mainModel.isAmbientOcclusion();
	}

	@Override
	public boolean isGui3d() {
		return true;
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
		return mainModel.getItemCameraTransforms();
	}
	
	@Override
	public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) {
		Matrix4f matrix4f = mainModel.handlePerspective(cameraTransformType).getRight();
		return Pair.of(this, matrix4f);
	}
	
	private static class PaintGunOverrideList extends ItemOverrideList {
		private PaintGunModel model;
		
		public PaintGunOverrideList(PaintGunModel model) {
			super(Collections.emptyList());
			this.model = model;
		}
		
		@Override
		public IBakedModel handleItemState(IBakedModel originalModel, ItemStack stack, World world, EntityLivingBase entity) {
			return this.model.setCurrentItemStack(stack);
		}
	}
}
