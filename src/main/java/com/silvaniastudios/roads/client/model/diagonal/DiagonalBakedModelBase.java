package com.silvaniastudios.roads.client.model.diagonal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.diagonal.HalfBlock;
import com.silvaniastudios.roads.blocks.diagonal.RoadBlockDiagonal;
import com.silvaniastudios.roads.blocks.diagonal.ShapeLibrary;
import com.silvaniastudios.roads.client.render.Quad;

import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
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
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

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

		RoadBlockDiagonal block = (RoadBlockDiagonal) state.getBlock();

		IExtendedBlockState extendedState = (IExtendedBlockState) state;
		EnumFacing facing = extendedState.getValue(RoadBlockDiagonal.FACING);
		IBlockState stateLeft = extendedState.getValue(RoadBlockDiagonal.LEFT);
		IBlockState stateRight = extendedState.getValue(RoadBlockDiagonal.RIGHT);
		BlockPos pos = extendedState.getValue(RoadBlockDiagonal.POS);
		BlockPos posLeft = pos.offset(facing.rotateYCCW());
		BlockPos posRight = pos.offset(facing.rotateY());
		BlockPos posFront = pos.offset(facing);
		BlockPos posBack = pos.offset(facing.getOpposite());
		
		HalfBlock blockLeft = new HalfBlock(extendedState, HalfBlock.HalfBlockSide.LEFT);
		HalfBlock blockRight = new HalfBlock(extendedState, HalfBlock.HalfBlockSide.RIGHT);

		System.out.println("Get quads called");
		

		/* 
		 *  Side culling
		 */


		/*//Near (back)
		leftShape[0] = leftHeight != rightHeight ? true : renderSide(pos, posBack, extendedState, true);
		rightShape[0] = leftHeight != rightHeight ? true : renderSide(pos, posBack, extendedState, false);

		//Far (front)
		leftShape[1] = leftHeight != rightHeight ? true : renderSide(pos, posFront, extendedState, true);
		rightShape[1] = leftHeight != rightHeight ? true : renderSide(pos, posFront, extendedState, false);

		//Left
		rightShape[2] = leftHeight != rightHeight;
		leftShape[2] = leftHeight != rightHeight;

		//Top
		if (leftHeight >= 1) {
			leftShape[3] = renderSide(pos, pos.offset(EnumFacing.UP), extendedState, true);
		}
		if (rightHeight >= 1) {
			rightShape[3] = renderSide(pos, pos.offset(EnumFacing.UP), extendedState, false);
		}

		//Bottom
		if (facingSolidBlock(pos.offset(EnumFacing.DOWN), extendedState)) {
			leftShape[4] = false;
			rightShape[4] = false;
		}*/
		
		blockLeft.setPartnerBlock(blockRight);
		blockRight.setPartnerBlock(blockLeft);

		return packQuads(facing, blockLeft, blockRight);
	}

	//Overriden in subclasses
	protected List<BakedQuad> packQuads(EnumFacing facing, HalfBlock blockLeft, HalfBlock blockRight) {
		List<BakedQuad> quads = new ArrayList<>();
		return quads;
	}

	/*public boolean facingSolidBlock(BlockPos other, IExtendedBlockState state) {
		IBlockState stateOther = mc.world.getBlockState(other);
		if (stateOther.getBlock().isOpaqueCube(stateOther) || stateOther.getBlock().isFullBlock(stateOther)) {
			return true;
		}
		return false;
	}

	public boolean renderSide(BlockPos pos, BlockPos posOther, IExtendedBlockState state, boolean checkingLeft) {
		IBlockState stateOther = mc.world.getBlockState(posOther);
		if (facingSolidBlock(posOther, state)) {
			return false;
		}

		if (stateOther.getBlock() instanceof RoadBlockDiagonal) {
			RoadBlockDiagonal rbd = (RoadBlockDiagonal) stateOther.getBlock();

			if (rbd.getLeftHeight(state, mc.world, pos) == rbd.getLeftHeight(stateOther, mc.world, pos) && checkingLeft) {
				return false;
			} else if (rbd.getRightHeight(state, mc.world, pos) == rbd.getRightHeight(stateOther, mc.world, pos) && !checkingLeft) {
				return false;
			}
		}

		return true;
	}*/

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

	protected List<BakedQuad> createTriangle(List<BakedQuad> quads, boolean left, HalfBlock block, double width) {
		List<Quad> rawQuads = new ArrayList<>();

		if (left) {
			rawQuads = ShapeLibrary.shapeTriangleLeft(block, width, format);
		} else {
			rawQuads = ShapeLibrary.shapeTriangleRight(block, width, format);
		}

		return shapeBuilder(rawQuads, quads, block);
	}


	protected List<BakedQuad> createTrapezium(List<BakedQuad> quads, boolean left, HalfBlock block, double widthN, double widthW) {
		List<Quad> rawQuads = new ArrayList<>();

		if (left) {
			rawQuads = ShapeLibrary.shapeTrapeziumLeft(block, widthN, widthW, format);
		} else {
			rawQuads = ShapeLibrary.shapeTrapeziumRight(block, widthN, widthW, format);
		}

		return shapeBuilder(rawQuads, quads, block);
	}

	//Works for most default shapes that assume 90 degree rotations.
	protected List<BakedQuad> shapeBuilder(List<Quad> rawQuads, List<BakedQuad> quads, HalfBlock block) {
		for (int i = 0; i < rawQuads.size(); i++) {
			if (rawQuads.get(i) != null) {
				rawQuads.set(i, Quad.rotateQuadY(rawQuads.get(i), block.getRotation()));
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
				BakedQuad baked = rawQuads.get(i).createQuad(block.getColour());
				
				if (i == 1 && block.isFluid()) {
					baked = rawQuads.get(i).createUnnormalizedQuad(block.getColour()); //Unnormalized? abnormal? idk I'm not good at 3d rendering math stuff 
				}
				
				quads.add(baked);
			}
		}

		return quads;
	}
}
