package com.silvaniastudios.roads.client.model.diagonal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.diagonal.RoadBlockDiagonal;
import com.silvaniastudios.roads.client.render.Quad;

import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLeaves;
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
	Minecraft mc;

	public DiagonalBakedModelBase(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
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

		//Fallback to sprite particle
		TextureAtlasSprite spriteLeft = Minecraft.getMinecraft().getBlockRendererDispatcher().getModelForState(stateLeft).getParticleTexture();
		TextureAtlasSprite spriteRight = Minecraft.getMinecraft().getBlockRendererDispatcher().getModelForState(stateRight).getParticleTexture();
		
		int colLeft = 0;
		int colRight = 0;
		
		BlockPos posLeft = pos.offset(facing.rotateYCCW());
		BlockPos posRight = pos.offset(facing.rotateY());
		
		//May revisit this to make it more robust later for other mods, but for now it works with roads/vanilla and maybe some mods.
		if (stateLeft.getBlock() instanceof BlockGrass || stateLeft.getBlock() instanceof BlockLeaves || stateLeft.getBlock() == FRBlocks.road_block_grass) {
			//colLeft = mc.getBlockColors().getColor(stateLeft, mc.world, posLeft);
			colLeft = Minecraft.getMinecraft().world.getBiome(pos).getGrassColorAtPos(pos);
		}
		
		if (stateRight.getBlock() instanceof BlockGrass || stateRight.getBlock() instanceof BlockLeaves || stateRight.getBlock() == FRBlocks.road_block_grass) {
			//colRight = mc.getBlockColors().getColor(stateRight, mc.world, posRight);
			colRight = Minecraft.getMinecraft().world.getBiome(pos).getGrassColorAtPos(pos);
		}

		if (quadsLeft.size() > 0) {
			spriteLeft = quadsLeft.get(0).getSprite();
		}

		if (quadsRight.size() > 0) {
			spriteRight = quadsRight.get(0).getSprite();
		}

		if (stateLeft.getBlock().isAir(stateLeft, Minecraft.getMinecraft().world, this.getLeftPos(facing, pos))) { 
			spriteLeft = null;
			leftHeight = 0;
		}

		if (stateRight.getBlock().isAir(stateRight, Minecraft.getMinecraft().world, this.getLeftPos(facing.getOpposite(), pos))) { 
			spriteRight = null;
			rightHeight = 0;
		}

		return packQuads(facing, spriteLeft, spriteRight, colLeft, colRight, leftHeight, rightHeight);
	}

	//Overriden in subclasses
	protected List<BakedQuad> packQuads(EnumFacing facing, TextureAtlasSprite spriteLeft, TextureAtlasSprite spriteRight, int colLeft, int colRight, float leftHeight, float rightHeight) {
		List<BakedQuad> quads = new ArrayList<>();
		return quads;
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
	
	protected List<BakedQuad> createTriangle(List<BakedQuad> quads, boolean left, float heightL, float heightR, TextureAtlasSprite sprite, double width, int rotation, int colour) {
		List<Quad> rawQuads = new ArrayList<>();
		
		if (left) {
			rawQuads = shapeTriangleLeft(heightL, heightR, sprite, width);
		} else {
			rawQuads = shapeTriangleRight(heightL, heightR, sprite, width);
		}
		
		return shapeBuilder(rawQuads, quads, rotation, colour);
	}
	
	
	protected List<BakedQuad> createTrapezium(List<BakedQuad> quads, boolean left, float heightL, float heightR, TextureAtlasSprite sprite, double widthN, double widthW, int rotation, int colour) {
		List<Quad> rawQuads = new ArrayList<>();

		if (left) {
			rawQuads = shapeTrapeziumLeft(heightL, heightR, sprite, widthN, widthW);
		} else {
			rawQuads = shapeTrapeziumRight(heightL, heightR, sprite, widthN, widthW);
		}

		return shapeBuilder(rawQuads, quads, rotation, colour);
	}
	
	//Works for most default shapes that assume 90 degree rotations.
	protected List<BakedQuad> shapeBuilder(List<Quad> rawQuads, List<BakedQuad> quads, int rotation, int colour) {
		for (int i = 0; i < rawQuads.size(); i++) {
			rawQuads.set(i, Quad.rotateQuadY(rawQuads.get(i), rotation));
		}

		rawQuads.get(0).updateUVs(); //Prevent UV rotation on top face
		rawQuads.get(1).setFlipV(true); //Flip UVs for bottom face
		rawQuads.get(1).updateUVs(); //Prevent UV rotation on bottom face

		for (int i = 0; i < rawQuads.size(); i++) {
			quads.add(rawQuads.get(i).createQuad(colour));
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
		
		Quad north = new Quad( 
				new Vec3d(1-width, heightL, 0), 	(float) width*16f, 	16-heightL*16f,
				new Vec3d(1-width, 0, 0), 			(float) width*16f,	16,
				new Vec3d(0, 0, 0),					16, 				16,
				new Vec3d(0, heightL, 0), 			16, 				16-heightL*16f,
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
		} else {
			Quad east = new Quad( //East (long) side
					new Vec3d(0, heightL, 1), 0, 16-heightL*16f, //TL
					new Vec3d(0, 0, 1), 0, 16, //BL
					new Vec3d(1-width, 0, 0), 16, 16, //BR
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
				new Vec3d(0, heightL, 1), 		0, 					16-heightL*16f,
				new Vec3d(0, 0, 1), 			0, 					16,
				new Vec3d(1-width, 0, 1), 		(float) width*16f, 	16,
				new Vec3d(1-width, heightL, 1), (float) width*16f, 	16-heightL*16f,
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
		} else {
			Quad east = new Quad( //East (long) side
					new Vec3d(1-width, heightL, 1), 0, 16-heightL*16f, //TL
					new Vec3d(1-width, 0, 1), 0, 16, //BL
					new Vec3d(0, 0, 0), 16, 16, //BR
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
				new Vec3d(widthN, heightR, 1.0), 		(float) (16f*widthN), 	16 - (float) (16f * heightR),//south west
				new Vec3d(0, heightR, 1.0), 			0, 						16 - (float) (16f * heightR),//south east
				new Vec3d(0, 0.0, 1.0), 				0, 						16,//north east
				new Vec3d(widthN, 0.0, 1.0), 			(float) (16f*widthN), 	16, //north west
				sprite, format);
		Quad north = new Quad(
				new Vec3d(widthW, 0, 0), 0, 16,//south west
				new Vec3d(0, 0, 0), (float) (16f*widthW), 16,//south east
				new Vec3d(0, heightR, 0), (float) (16f*widthW), 16 - (float) (16f * heightR),//north east
				new Vec3d(widthW, heightR, 0), 0, 16 - (float) (16f * heightR), //north west
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
		} else {
			Quad front = new Quad( //Front (long side)
					new Vec3d(widthN, 0, 1.0), 			0, 	16,//south west
					new Vec3d(widthW, 0, 0.0), 			16, 16,//south east
					new Vec3d(widthW, heightR, 0.0), 	16, 16-heightR * 16f,//north east
					new Vec3d(widthN, heightR, 1.0), 	0, 	16-heightR * 16f,//north west
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
				new Vec3d(widthW, heightR, 1.0), 	(float) (16f*widthW), 			16 - (float) (16f * heightR),//south west
				new Vec3d(0, heightR, 1.0), 		0, 								16 - (float) (16f * heightR),//south east
				new Vec3d(0, 0.0, 1.0), 			0, 								16,//north east
				new Vec3d(widthW, 0.0, 1.0), 		(float) (16f*widthW), 			16, //north west
				sprite, format);
		Quad north = new Quad(
				new Vec3d(widthN, 0, 0), 			16 - (float) (16f*widthN), 		16,//south west
				new Vec3d(0, 0, 0), 				16, 			16,//south east
				new Vec3d(0, heightR, 0), 			16, 			16 - (float) (16f * heightR),//north east
				new Vec3d(widthN, heightR, 0), 		16 - (float) (16f*widthN), 		16 - (float) (16f * heightR), //north west
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
		} else {
			Quad front = new Quad( //Front (long side)
					new Vec3d(widthW, 0, 1.0), 			0, 	16,//south west
					new Vec3d(widthN, 0, 0.0), 			16, 16,//south east
					new Vec3d(widthN, heightR, 0.0), 	16, 16-heightR * 16f,//north east
					new Vec3d(widthW, heightR, 1.0), 	0, 	16-heightR * 16f,//north west
					sprite, format);
			quads.add(front);
		}
		return quads;
	}
}
