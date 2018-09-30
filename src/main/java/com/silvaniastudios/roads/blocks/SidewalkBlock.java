package com.silvaniastudios.roads.blocks;

import javax.annotation.Nullable;

import com.silvaniastudios.roads.FlenixRoads;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;

public class SidewalkBlock extends BlockBase {
	
	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool NORTH_EAST = PropertyBool.create("north_east");
	public static final PropertyBool EAST = PropertyBool.create("east");
	public static final PropertyBool SOUTH_EAST = PropertyBool.create("south_east");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool SOUTH_WEST = PropertyBool.create("south_west");
	public static final PropertyBool WEST = PropertyBool.create("west");
	public static final PropertyBool NORTH_WEST = PropertyBool.create("north_west");
	
	boolean connectToOthers = false;

	public SidewalkBlock(String name, Material mat, boolean connect) {
		super(name, mat);
		this.setDefaultState(this.blockState.getBaseState().withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false)));
		this.connectToOthers = connect;
		this.setCreativeTab(FlenixRoads.tab_roads);
	}

	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos, EnumFacing facing) {
		IBlockState state = worldIn.getBlockState(pos);
		BlockFaceShape face = state.getBlockFaceShape(worldIn, pos, facing);
		Block block = state.getBlock();
		boolean flag = face == BlockFaceShape.MIDDLE_POLE && (state.getMaterial() == this.blockMaterial) || block instanceof SidewalkBlock;
		if (connectToOthers) {
			return !isExceptBlockForAttachWithPiston(block) && face == BlockFaceShape.SOLID || flag;
		}
		return block == this;
	}
	
	@Override
	public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing) {
		return canConnectTo(world, pos.offset(facing), facing.getOpposite());
	}

	private boolean canSidewalkConnectTo(IBlockAccess world, BlockPos pos, EnumFacing facing) {
		BlockPos offset = pos.offset(facing);
		Block block = world.getBlockState(offset).getBlock();
		return block.canBeConnectedTo(world, offset, facing.getOpposite()) || canConnectTo(world, offset, facing.getOpposite());
	}
	
	private boolean canSidewalkConnectDiagonal(IBlockAccess world, BlockPos pos, EnumFacing facing, EnumFacing facing2) {
		BlockPos ofst = pos.offset(facing);
		BlockPos offset = ofst.offset(facing2);
		Block block = world.getBlockState(offset).getBlock();
		return block.canBeConnectedTo(world, offset, facing.getOpposite()) || canConnectTo(world, offset, facing.getOpposite());
	}
	
	public int getMetaFromState(IBlockState state) {
		return 0;
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {NORTH, EAST, WEST, SOUTH, NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST});
	}

	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(NORTH, canSidewalkConnectTo(worldIn, pos, EnumFacing.NORTH))
			.withProperty(EAST,  canSidewalkConnectTo(worldIn, pos, EnumFacing.EAST))
			.withProperty(SOUTH, canSidewalkConnectTo(worldIn, pos, EnumFacing.SOUTH))
			.withProperty(WEST,  canSidewalkConnectTo(worldIn, pos, EnumFacing.WEST))
			.withProperty(NORTH_WEST,  canSidewalkConnectDiagonal(worldIn, pos, EnumFacing.NORTH, EnumFacing.WEST))
			.withProperty(NORTH_EAST,  canSidewalkConnectDiagonal(worldIn, pos, EnumFacing.NORTH, EnumFacing.EAST))
			.withProperty(SOUTH_WEST,  canSidewalkConnectDiagonal(worldIn, pos, EnumFacing.SOUTH, EnumFacing.WEST))
			.withProperty(SOUTH_EAST,  canSidewalkConnectDiagonal(worldIn, pos, EnumFacing.SOUTH, EnumFacing.EAST));
	}
	
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    	return new AxisAlignedBB(0.0D, -1+getBlockBelowHeight(source, pos), 0.0D, 1.0D, -1+getBlockBelowHeight(source, pos)+0.0625, 1.0D);
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return NULL_AABB;
    }
    
    @Override
    public Vec3d getOffset(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        double offset = 1.0 - getBlockBelowHeight(worldIn, pos);
        return new Vec3d(0, -offset, 0);
    }
    
    @SuppressWarnings("deprecation")
	private double getBlockBelowHeight(IBlockAccess worldIn, BlockPos pos) {
    	IBlockState underState = worldIn.getBlockState(pos.offset(EnumFacing.DOWN));
        Block underBlock = underState.getBlock();
        
        return underBlock.getBoundingBox(underState, worldIn, pos.offset(EnumFacing.DOWN)).maxY;
    }
}
