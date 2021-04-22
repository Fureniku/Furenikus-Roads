package com.silvaniastudios.roads.blocks.diagonal;

import java.util.List;

import com.silvaniastudios.roads.blocks.FRBlocks;

import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class HalfBlock {
	
	RoadBlockDiagonal block;
	private World world;
	
	private IBlockState stateSide;
	private List<BakedQuad> quadsSide;
	private BlockPos posSide;
	private HalfBlock partnerBlock;
	
	private float height = 0;
	
	private BlockPos pos;
	private TextureAtlasSprite sprite;
	private int blockColour;
	private HalfBlockSide side;
	private EnumFacing facing;
	
	private boolean isFluid = false;
	
	public HalfBlock(IExtendedBlockState sharedState, HalfBlockSide side) {
		this(sharedState, side, sharedState.getValue(RoadBlockDiagonal.POS), sharedState.getValue(RoadBlockDiagonal.FACING));
	}
	
	public HalfBlock(IExtendedBlockState sharedState, HalfBlockSide side, BlockPos pos, EnumFacing facing, World world) {
		block = (RoadBlockDiagonal) sharedState.getBlock();
		
		sharedState = (IExtendedBlockState) block.getExtendedState(sharedState, world, pos);
		
		this.side = side;
		this.pos = pos;
		this.facing = facing;
		this.world = world;
		
		if (side == HalfBlockSide.LEFT) {
			this.posSide = pos.offset(facing.rotateYCCW());
			this.stateSide = sharedState.getValue(RoadBlockDiagonal.LEFT);
		} else if (side == HalfBlockSide.RIGHT) {
			this.posSide = pos.offset(facing.rotateY());
			this.stateSide = sharedState.getValue(RoadBlockDiagonal.RIGHT);
		}
		
		this.height = block.getSideHeight(sharedState, world, pos, side);
		
		if (stateSide.getBlock() instanceof BlockFluidBase) {
			isFluid = true;
		}
		
		if (stateSide.getBlock() instanceof BlockLiquid) {

			height = BlockLiquid.getBlockLiquidHeight(stateSide, world, posSide);
		}
	}

	public HalfBlock(IExtendedBlockState sharedState, HalfBlockSide side, BlockPos pos, EnumFacing facing) {
		block = (RoadBlockDiagonal) sharedState.getBlock();
		this.world = Minecraft.getMinecraft().world;
		Minecraft mc = Minecraft.getMinecraft();
		
		sharedState = (IExtendedBlockState) block.getExtendedState(sharedState, world, pos);
		
		this.side = side;
		this.pos = pos;
		this.facing = facing;
		
		
		if (side == HalfBlockSide.LEFT) {
			this.posSide = pos.offset(
					facing.rotateYCCW());
			this.stateSide = sharedState.getValue(RoadBlockDiagonal.LEFT);
		} else if (side == HalfBlockSide.RIGHT) {
			this.posSide = pos.offset(
					facing.rotateY());
			this.stateSide = sharedState.getValue(RoadBlockDiagonal.RIGHT);
		}
		
		this.height = block.getSideHeight(sharedState, world, pos, side);
		this.quadsSide = mc.getBlockRendererDispatcher().getModelForState(stateSide).getQuads(stateSide, EnumFacing.UP, 0);
		
		
		//Sprite handling - no sprite for no height, default sprite of particle texture
		sprite = height == 0 ? null : mc.getBlockRendererDispatcher().getModelForState(stateSide).getParticleTexture();
		//If it's a normal cube, go for top texture instead.
		if (quadsSide.size() > 0 && height > 0) {
			sprite = quadsSide.get(0).getSprite();
		}
		
		//May revisit this to make it more robust later for other mods, but for now it works with roads/vanilla and maybe some mods.
		if (stateSide.getBlock() instanceof BlockGrass || stateSide.getBlock() instanceof BlockLeaves || stateSide.getBlock() == FRBlocks.road_block_grass) {
			//blockColour = mc.getBlockColors().getColor(stateSide, mc.world, posSide);
			blockColour = world.getBiome(posSide).getGrassColorAtPos(posSide);
		}
		
		if (stateSide.getBlock() instanceof BlockFluidBase) {
			isFluid = true;
		}
		
		if (stateSide.getBlock() instanceof BlockLiquid) {
			Fluid fluid = null;
			if (stateSide.getMaterial() == Material.WATER) {
				fluid = FluidRegistry.WATER;
			} else if (stateSide.getMaterial() == Material.LAVA) {
				fluid = FluidRegistry.LAVA;
			}

			height = BlockLiquid.getBlockLiquidHeight(stateSide, world, posSide);

			if (fluid != null) {
				sprite = mc.getTextureMapBlocks().getAtlasSprite(fluid.getStill(new FluidStack(fluid, 1000)).toString());
				isFluid = true;
			}
		}
	}
	
	public int getRotation() {
		if (this.facing == EnumFacing.NORTH) {
			return this.side == HalfBlockSide.LEFT ? 0 : 180;
		} else if (this.facing == EnumFacing.EAST) {
			return this.side == HalfBlockSide.LEFT ? 270 : 90;
		} else if (this.facing == EnumFacing.SOUTH) {
			return this.side == HalfBlockSide.LEFT ? 180 : 0;
		} else {
			return this.side == HalfBlockSide.LEFT ? 90 : 270;
		}
	}
	
	public float getHeight() {
		return height;
	}
	
	public TextureAtlasSprite getSprite() {
		return sprite;
	}
	
	public int getColour() {
		return blockColour;
	}
	
	public void setPartnerBlock(HalfBlock block) {
		this.partnerBlock = block;
	}
	
	public HalfBlock getPartnerBlock() {
		return this.partnerBlock;
	}
	
	public boolean renderNear() {
		if (isFluid) {
			if (this.side == HalfBlockSide.LEFT) {
				return renderFar();
			}
			IBlockState near = world.getBlockState(pos.offset(facing.getOpposite()));
			return !checkStateForFluid(near, pos.offset(facing.getOpposite()));
		}
		return true;
	}
	
	public boolean renderNearMirrored() {
		if (isFluid) {
			if (this.side == HalfBlockSide.RIGHT) {
				return renderFarMirrored();
			}
			IBlockState near = world.getBlockState(pos.offset(facing.getOpposite()));
			return !checkStateForFluid(near, pos.offset(facing.getOpposite()));
		}
		return true;
	}
	
	public boolean renderFar() {
		if (isFluid) {
			if (this.side == HalfBlockSide.RIGHT) {
				return renderNear();
			}
			IBlockState far = world.getBlockState(pos.offset(facing));
			return !checkStateForFluid(far, pos.offset(facing));
		}
		return true;
	}
	
	public boolean renderFarMirrored() {
		if (isFluid) {
			if (this.side == HalfBlockSide.LEFT) {
				return renderNearMirrored();
			}
			IBlockState far = world.getBlockState(pos.offset(facing));
			return !checkStateForFluid(far, pos.offset(facing));
		}
		return true;
	}
	
	public boolean renderTop() {
		if (isFluid) {
			IBlockState above = world.getBlockState(pos.offset(EnumFacing.UP));
			return !checkStateForFluid(above, pos.offset(EnumFacing.UP));
		}
		return true;
	}
	
	public boolean renderBottom() {
		return !isFluid;
	}
	
	public boolean renderDivision() {
		if (isFluid && this.partnerBlock.isFluid) {
			return false;
		}
		if (this.height == this.partnerBlock.height && !this.partnerBlock.isFluid) {
			return false;
		}
		return true;
	}
	
	public boolean checkStateForFluid(IBlockState state, BlockPos pos) {
		if (state.getBlock() instanceof BlockLiquid || state.getBlock() instanceof BlockFluidBase) {
			return true;
		}
		
		if (state.getBlock() instanceof RoadBlockDiagonal) {
			HalfBlock hbSide = getCardinalHalfBlock(state, pos, state.getValue(RoadBlockDiagonal.FACING), HalfBlockSide.getFacingFromDirection(this.side, this.facing));
			if (hbSide.isFluid()) {
				return true;
			}
		}
		return false;
	}
	
	public float heightNW() {
		return this.height;
	}
	
	public float heightNE() {
		return this.height;
	}
	
	public float heightSE() {
		return this.height;
	}
	
	public float heightSW() {
		return this.height;
	}
	
	public boolean isFluid() {
		return isFluid;
	}
	
	public HalfBlock getCardinalHalfBlock(IBlockState state, BlockPos pos, EnumFacing blockFacing, EnumFacing blockSide) {
		if (state instanceof IExtendedBlockState) {
			IExtendedBlockState extendedState = (IExtendedBlockState) state;
			return new HalfBlock(extendedState, HalfBlockSide.getSide(blockFacing, blockSide), pos, facing);
		}
		return null;
	}
	
	public static HalfBlock getHalfBlock(IBlockState state, HalfBlockSide side, BlockPos pos, EnumFacing facing) {
		if (state instanceof IExtendedBlockState) {
			IExtendedBlockState extendedState = (IExtendedBlockState) state;
			return new HalfBlock(extendedState, side, pos, facing);
		}
		
		return null;
	}
	
	
	public static enum HalfBlockSide {
		LEFT,
		RIGHT;
		
		
		public static EnumFacing getFacingFromDirection(HalfBlockSide side, EnumFacing blockFacing) {
			if (blockFacing == EnumFacing.NORTH) {
				return side == LEFT ? EnumFacing.WEST : EnumFacing.EAST;
			}
			
			if (blockFacing == EnumFacing.EAST) {
				return side == LEFT ? EnumFacing.NORTH : EnumFacing.SOUTH;
			}
			
			if (blockFacing == EnumFacing.SOUTH) {
				return side == LEFT ? EnumFacing.EAST : EnumFacing.WEST;
			}
			
			if (blockFacing == EnumFacing.WEST) {
				return side == LEFT ? EnumFacing.SOUTH : EnumFacing.NORTH;
			}
			
			return null;
		}
		
		public static HalfBlockSide getSide(EnumFacing blockFacing, EnumFacing sideCheck) {
			if ((blockFacing == EnumFacing.NORTH && sideCheck == EnumFacing.WEST) ||
					(blockFacing == EnumFacing.EAST && sideCheck == EnumFacing.NORTH) ||
					(blockFacing == EnumFacing.SOUTH && sideCheck == EnumFacing.EAST) ||
					(blockFacing == EnumFacing.WEST && sideCheck == EnumFacing.SOUTH)) {
				return LEFT;
			}
			
			if ((blockFacing == EnumFacing.NORTH && sideCheck == EnumFacing.EAST) ||
					(blockFacing == EnumFacing.EAST && sideCheck == EnumFacing.SOUTH) ||
					(blockFacing == EnumFacing.SOUTH && sideCheck == EnumFacing.WEST) ||
					(blockFacing == EnumFacing.WEST && sideCheck == EnumFacing.NORTH)) {
				return RIGHT;
			}
			
			return null;
		}
	}

}
