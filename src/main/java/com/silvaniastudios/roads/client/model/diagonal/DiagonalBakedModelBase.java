package com.silvaniastudios.roads.client.model.diagonal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.diagonal.RoadBlockDiagonal;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.model.pipeline.UnpackedBakedQuad;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.property.IExtendedBlockState;

public class DiagonalBakedModelBase implements IBakedModel {

	protected VertexFormat format;

	public DiagonalBakedModelBase(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
		this.format = format;
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

	@SuppressWarnings("deprecation")
	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {

		if (side != null) {
			return Collections.emptyList();
		}

		IExtendedBlockState extendedState = (IExtendedBlockState) state;
		EnumFacing facing = extendedState.getValue(RoadBlockDiagonal.FACING);
		IBlockState stateLeft = extendedState.getValue(RoadBlockDiagonal.LEFT);
		IBlockState stateRight = extendedState.getValue(RoadBlockDiagonal.RIGHT);
		BlockPos pos = extendedState.getValue(RoadBlockDiagonal.POS);

		float leftHeight = (float) stateLeft.getBlock().getBoundingBox(stateLeft, Minecraft.getMinecraft().world, getLeftPos(facing, pos)).maxY;
		float rightHeight = (float) stateRight.getBlock().getBoundingBox(stateRight, Minecraft.getMinecraft().world, getLeftPos(facing.getOpposite(), pos)).maxY;

		List<BakedQuad> quadsLeft = Minecraft.getMinecraft().getBlockRendererDispatcher().getModelForState(stateLeft).getQuads(stateLeft, EnumFacing.UP, rand);
		List<BakedQuad> quadsRight = Minecraft.getMinecraft().getBlockRendererDispatcher().getModelForState(stateRight).getQuads(stateRight, EnumFacing.UP, rand);

		TextureAtlasSprite spriteLeft = null;
		TextureAtlasSprite spriteRight = null;

		if (quadsLeft.size() > 0) {
			spriteLeft = quadsLeft.get(0).getSprite();
		} else {
			leftHeight = 0;
		}

		if (quadsRight.size() > 0) {
			spriteRight = quadsRight.get(0).getSprite();
		} else {
			rightHeight = 0;
		}

		if (stateLeft.getBlock().isAir(stateLeft, Minecraft.getMinecraft().world, this.getLeftPos(facing, pos))) { 
			spriteLeft = null;
			leftHeight = 0;
		}

		if (stateRight.getBlock().isAir(stateRight, Minecraft.getMinecraft().world, this.getLeftPos(facing.getOpposite(), pos))) { 
			spriteRight = null;
			rightHeight = 0;
		}

		return packQuads(facing, spriteLeft, spriteRight, leftHeight, rightHeight);
	}

	protected List<BakedQuad> packQuads(EnumFacing facing, TextureAtlasSprite spriteLeft, TextureAtlasSprite spriteRight, float leftHeight, float rightHeight) {
		List<BakedQuad> quads = new ArrayList<>();
		return quads;
	}

	protected BakedQuad createTriangle(Vec3d vec1, Vec3d vec2, Vec3d vec3, Vec3d vec4, TextureAtlasSprite sprite) {
		Vec3d normal = vec3.subtract(vec2).crossProduct(vec1.subtract(vec2)).normalize();

		UnpackedBakedQuad.Builder builder = new UnpackedBakedQuad.Builder(format);
		builder.setTexture(sprite);
		float f = 16.0f;

		putVertex(builder, normal, vec1.x, vec1.y, vec1.z, (float) vec1.x*f, (float) vec1.z*f, sprite);
		putVertex(builder, normal, vec2.x, vec2.y, vec2.z, (float) vec2.x*f, (float) vec2.z*f, sprite);
		putVertex(builder, normal, vec3.x, vec3.y, vec3.z, (float) vec3.x*f, (float) vec3.z*f, sprite);
		putVertex(builder, normal, vec4.x, vec4.y, vec4.z, (float) vec4.x*f, (float) vec4.z*f, sprite);
		return builder.build();
	}

	//UVs are reversed when the triangle is drawn upside down, hence 16f-
	protected BakedQuad createTriangleUpsideDown(Vec3d vec1, Vec3d vec2, Vec3d vec3, Vec3d vec4,TextureAtlasSprite sprite) {
		Vec3d normal = vec3.subtract(vec2).crossProduct(vec1.subtract(vec2)).normalize();

		UnpackedBakedQuad.Builder builder = new UnpackedBakedQuad.Builder(format);
		builder.setTexture(sprite);
		float f = 16.0f;

		putVertex(builder, normal, vec1.x, vec1.y, vec1.z, (float) vec1.x*f, 16.f - (float) vec1.z*f, sprite);
		putVertex(builder, normal, vec2.x, vec2.y, vec2.z, (float) vec2.x*f, 16.f - (float) vec2.z*f, sprite);
		putVertex(builder, normal, vec3.x, vec3.y, vec3.z, (float) vec3.x*f, 16.f - (float) vec3.z*f, sprite);
		putVertex(builder, normal, vec4.x, vec4.y, vec4.z, (float) vec4.x*f, 16.f - (float) vec4.z*f, sprite);
		return builder.build();
	}

	protected BakedQuad createQuadUV(Vec3d vec1, float u1, float v1, Vec3d vec2, float u2, float v2, Vec3d vec3, float u3, float v3, Vec3d vec4, float u4, float v4, TextureAtlasSprite sprite) {
		Vec3d normal = vec3.subtract(vec2).crossProduct(vec1.subtract(vec2)).normalize();

		UnpackedBakedQuad.Builder builder = new UnpackedBakedQuad.Builder(format);
		builder.setTexture(sprite);

		putVertex(builder, normal, vec1.x, vec1.y, vec1.z, u1, v1, sprite);
		putVertex(builder, normal, vec2.x, vec2.y, vec2.z, u2, v2, sprite);
		putVertex(builder, normal, vec3.x, vec3.y, vec3.z, u3, v3, sprite);
		putVertex(builder, normal, vec4.x, vec4.y, vec4.z, u4, v4, sprite);
		return builder.build();
	}

	protected BlockPos getLeftPos(EnumFacing facing, BlockPos pos) {
		if (facing == EnumFacing.NORTH) {
			return pos.offset(EnumFacing.WEST);
		} else if (facing == EnumFacing.EAST) {
			return pos.offset(EnumFacing.NORTH);
		} else if (facing == EnumFacing.SOUTH) {
			return pos.offset(EnumFacing.EAST);
		} else {
			return pos.offset(EnumFacing.WEST);
		}
	}

	@Override public ItemOverrideList getOverrides() { return null; }
	@Override public boolean isAmbientOcclusion() { return true; }
	@Override public boolean isGui3d() { return false; }
	@Override public boolean isBuiltInRenderer() { return false; }
	@Override public TextureAtlasSprite getParticleTexture() { return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":blocks/road_block_standard"); }
	@Override public ItemCameraTransforms getItemCameraTransforms() { return ItemCameraTransforms.DEFAULT; }

	
	/*
	 *    ###   #   #    #    ####          ####     #    #   #  #####  ####   #   #
	 *   #   #  #   #   # #   #   #         #   #   # #   #  #   #      #   #  #   #
	 *   #   #  #   #  #   #  #   #         #   #  #   #  # #    #      #   #  #   #
	 *   #   #  #   #  #####  #   #         ####   #####  ##     ####   ####    # #
	 *   # # #  #   #  #   #  #   #         #   #  #   #  # #    #      # #      # 
	 *   #  ##  #   #  #   #  #   #         #   #  #   #  #  #   #      #  #     #
	 *    ####   ###   #   #  ####          ####   #   #  #   #  #####  #   #    # 
	 */   
	
	//Creation of a shape and converting it into Minecraft quads.
	//Create the shape first (shapeTrapeziumLeft in this case)
	//Apply rotation to all quads. Rotation can be any int 1-360 (or more I guess but why?). Pretty rotated blocks!
	//Can rotate on X/Y/Z axis by calling Quad.rotateQuad[Axis]

	//After rotation, any post processing. If you want the shape to sit flush, the bottom face needs V flipping and both top and bottom need UVs updating.
	//THIS ONLY WORKS ON MINECRAFT LOCKED ROTATIONS (0 90 180 270). If we're doing weird rotations we can't do this. F in chat.
	//Finally convert to a baked quad and add to the list to return (Can do this in the first loop if we're not doing UV things)
	
	protected List<BakedQuad> createTriangle(List<BakedQuad> quads, boolean left, float heightL, float heightR, TextureAtlasSprite sprite, double width, int rotation) {
		List<Quad> rawQuads = new ArrayList<>();
		
		if (left) {
			rawQuads = shapeTriangleLeft(heightL, heightR, sprite, width);
		} else {
			rawQuads = shapeTriangleRight(heightL, heightR, sprite, width);
		}
		
		return shapeBuilder(rawQuads, quads, rotation);
	}
	
	
	protected List<BakedQuad> createTrapezium(List<BakedQuad> quads, boolean left, float heightL, float heightR, TextureAtlasSprite sprite, double widthN, double widthW, int rotation) {
		List<Quad> rawQuads = new ArrayList<>();

		if (left) {
			rawQuads = shapeTrapeziumLeft(heightL, heightR, sprite, widthN, widthW);
		} else {
			rawQuads = shapeTrapeziumRight(heightL, heightR, sprite, widthN, widthW);
		}

		return shapeBuilder(rawQuads, quads, rotation);
	}
	
	//Works for most default shapes that assume 90 degree rotations.
	protected List<BakedQuad> shapeBuilder(List<Quad> rawQuads, List<BakedQuad> quads, int rotation) {
		for (int i = 0; i < rawQuads.size(); i++) {
			rawQuads.set(i, Quad.rotateQuadY(rawQuads.get(i), rotation));
		}

		rawQuads.get(0).updateUVs(); //Prevent UV rotation on top face
		rawQuads.get(1).setFlipV(true); //Flip UVs for bottom face
		rawQuads.get(1).updateUVs(); //Prevent UV rotation on bottom face

		for (int i = 0; i < rawQuads.size(); i++) {
			quads.add(rawQuads.get(i).createQuad());
		}

		return quads;
	}
	
	/*
	 *   ####  #   #    #    ####   #####   ####
	 *  #      #   #   # #   #   #  #      #
	 *  #      #   #  #   #  #   #  #      #
	 *   ###   #####  #####  ####   ####    ###
	 *      #  #   #  #   #  #      #          #
	 *      #  #   #  #   #  #      #          #
	 *  ####   #   #  #   #  #      #####  #### 
	 */

	
	
	
	public List<Quad> shapeTriangleLeft(float heightL, float heightR, TextureAtlasSprite sprite, double width) {
		List<Quad> quads = new ArrayList<>();

		Quad top = new Quad( //Top
				new Vec3d(0, heightL, 0.5),   //BL
				new Vec3d(0, heightL, 1),     //BR
				new Vec3d(1-width, heightL, 0), //TR
				new Vec3d(0, heightL, 0),     //TL
				sprite, format);
		
		Quad bottom = new Quad( //bottom
				new Vec3d(0, 0, 0),
				new Vec3d(1-width, 0, 0),
				new Vec3d(0, 0, 1),
				new Vec3d(0, 0, 0.5),				
				sprite, format);
		
		Quad north = new Quad(  //North side
				new Vec3d(1-width, heightL, 0), (float) width*16f, 0,
				new Vec3d(1-width, 0, 0), (float) width*16f, heightL*16f,
				new Vec3d(0, 0, 0), 16, heightL*16f,
				new Vec3d(0, heightL, 0), 16, 0,
				sprite, format);
		
		quads.add(top);
		quads.add(bottom);
		quads.add(north);

		if (heightL > heightR) {
			Quad east = new Quad( //East (long) side
					new Vec3d(0, heightL, 1), 0, 16-heightL*16f, //TL
					new Vec3d(0, heightR, 1), 0, 16-heightR*16f, //BL
					new Vec3d(1-width, heightR, 0), 16, 16-heightR*16f, //BR
					new Vec3d(1-width, heightL, 0), 16, 16-heightL*16f,//TL
					sprite, format);
			quads.add(east);
		}
		return quads;
	}
	
	
	public List<Quad> shapeTriangleRight(float heightL, float heightR, TextureAtlasSprite sprite, double width) {
		List<Quad> quads = new ArrayList<>();

		Quad top = new Quad( //Top
				new Vec3d(0, heightL, 0.5),   //BL
				new Vec3d(0, heightL, 1),     //BR
				new Vec3d(1-width, heightL, 1), //TR
				new Vec3d(0, heightL, 0),     //TL
				sprite, format);
		
		Quad bottom = new Quad( //bottom
				new Vec3d(1-width, 0, 1),
				new Vec3d(0, 0, 1),	
				new Vec3d(0, 0, 0.5),
				new Vec3d(0, 0, 0),			
				sprite, format);

		Quad north = new Quad(  //North side
				new Vec3d(0, heightL, 1), (float) width*16f, 0,
				new Vec3d(0, 0, 1), (float) width*16f, heightL*16f,
				new Vec3d(1-width, 0, 1), 16, heightL*16f,
				new Vec3d(1-width, heightL, 1), 16, 0,
				sprite, format);
		
		quads.add(top);
		quads.add(bottom);
		quads.add(north);

		if (heightL > heightR) {
			Quad east = new Quad( //East (long) side
					new Vec3d(1-width, heightL, 1), 0, 16-heightL*16f, //TL
					new Vec3d(1-width, heightR, 1), 0, 16-heightR*16f, //BL
					new Vec3d(0, heightR, 0), 16, 16-heightR*16f, //BR
					new Vec3d(0, heightL, 0), 16, 16-heightL*16f,//TL
					sprite, format);
			quads.add(east);
		}
		return quads;
	}


	//Omits the left side face
	public List<Quad> shapeTrapeziumLeft(float heightL, float heightR, TextureAtlasSprite sprite, double widthN, double widthW) {
		List<Quad> quads = new ArrayList<>();
		Quad top = new Quad( //Top
				new Vec3d(0.0, heightR, 1.0),//south west
				new Vec3d(widthN, heightR, 1.0), //south east
				new Vec3d(widthW, heightR, 0.0), //north east
				new Vec3d(0, heightR, 0.0), //north west
				sprite, format);
		Quad bottom = new Quad( //Bottom
				new Vec3d(widthN, 0, 1),  //Bottom left
				new Vec3d(0,      0, 1),
				new Vec3d(0,      0, 0), 
				new Vec3d(widthW, 0, 0), 
				sprite, format);
		Quad south = new Quad(
				new Vec3d(widthN, heightR, 1.0), (float) (16f*widthN), 0,//south west
				new Vec3d(0, heightR, 1.0), 0, 0,//south east
				new Vec3d(0, 0.0, 1.0), 0, 16,//north east
				new Vec3d(widthN, 0.0, 1.0), (float) (16f*widthN), 16, //north west
				sprite, format);
		Quad north = new Quad(
				new Vec3d(widthW, 0, 0), 0, 16,//south west
				new Vec3d(0, 0, 0), (float) (16f*widthW), 16,//south east
				new Vec3d(0, heightR, 0), (float) (16f*widthW), 0,//north east
				new Vec3d(widthW, heightR, 0), 0, 0, //north west
				sprite, format);

		quads.add(top);
		quads.add(bottom);
		quads.add(south);
		quads.add(north);

		if (heightR > heightL) {
			Quad front = new Quad( //Front (long side)
					new Vec3d(widthN, heightL, 1.0), 0, 16-heightL * 16f,//south west
					new Vec3d(widthW, heightL, 0.0), 16, 16-heightL * 16f,//south east
					new Vec3d(widthW, heightR, 0.0), 16, 16-heightR * 16f,//north east
					new Vec3d(widthN, heightR, 1.0), 0, 16-heightR * 16f,//north west
					sprite, format);
			quads.add(front);
		}
		return quads;
	}

	//Omits the right side face
	public List<Quad> shapeTrapeziumRight(float heightL, float heightR, TextureAtlasSprite sprite, double widthN, double widthW) {
		List<Quad> quads = new ArrayList<>();
		Quad top = new Quad( //Top
				new Vec3d(0.0, heightR, 1.0),//south west
				new Vec3d(widthW, heightR, 1.0), //south east
				new Vec3d(widthN, heightR, 0.0), //north east
				new Vec3d(0, heightR, 0.0), //north west
				sprite, format);
		Quad bottom = new Quad( //Bottom
				new Vec3d(widthW, 0, 1),  //Bottom left
				new Vec3d(0,      0, 1),
				new Vec3d(0,      0, 0), 
				new Vec3d(widthN, 0, 0), 
				sprite, format);
		Quad south = new Quad(
				new Vec3d(widthW, heightR, 1.0), (float) (16f*widthW), 0,//south west
				new Vec3d(0, heightR, 1.0), 0, 0,//south east
				new Vec3d(0, 0.0, 1.0), 0, 16,//north east
				new Vec3d(widthW, 0.0, 1.0), (float) (16f*widthW), 16, //north west
				sprite, format);
		Quad north = new Quad(
				new Vec3d(widthN, 0, 0), 0, 16,//south west
				new Vec3d(0, 0, 0), (float) (16f*widthN), 16,//south east
				new Vec3d(0, heightR, 0), (float) (16f*widthN), 0,//north east
				new Vec3d(widthN, heightR, 0), 0, 0, //north west
				sprite, format);

		quads.add(top);
		quads.add(bottom);
		quads.add(south);
		quads.add(north);

		if (heightR > heightL) {
			Quad front = new Quad( //Front (long side)
					new Vec3d(widthW, heightL, 1.0), 0, 16-heightL * 16f,//south west
					new Vec3d(widthN, heightL, 0.0), 16, 16-heightL * 16f,//south east
					new Vec3d(widthN, heightR, 0.0), 16, 16-heightR * 16f,//north east
					new Vec3d(widthW, heightR, 1.0), 0, 16-heightR * 16f,//north west
					sprite, format);
			quads.add(front);
		}
		return quads;
	}
}
