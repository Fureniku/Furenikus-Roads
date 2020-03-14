package com.silvaniastudios.roads.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class SpeedBumpBlock extends TwoWayRotDecorativeBlock {
	
	public static final PropertyBool LEFT = PropertyBool.create("left");
	public static final PropertyBool RIGHT = PropertyBool.create("right");

	public SpeedBumpBlock(String name) {
		super(name);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {META_ID, LEFT, RIGHT});
	}
	
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		EnumFacing left = EnumFacing.NORTH;
		EnumFacing right = EnumFacing.SOUTH;
		if (getMetaFromState(state) % 2 == 0) {
			left = EnumFacing.WEST;
			right = EnumFacing.EAST;
		}
		
		
		return state.withProperty(LEFT, connect(worldIn, pos, left))
			.withProperty(RIGHT,  connect(worldIn, pos, right));
	}
	
	public boolean connect(IBlockAccess world, BlockPos pos, EnumFacing dir) {
		return world.getBlockState(pos.offset(dir)).getBlock() instanceof SpeedBumpBlock;
	}
	
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		int meta = getMetaFromState(state);
		return getBox(meta, getBlockBelowHeight(worldIn, pos));
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
    	int meta = getMetaFromState(state);
        return getBox(meta, getBlockBelowHeight(worldIn, pos));
    }
    
    
    public AxisAlignedBB getBox(int meta, double belowHeight) {
    	float v = 1.0F/16.0F;
    	if (meta % 2 == 0) {
    		return new AxisAlignedBB(0*v, -1+belowHeight, 4*v, 16*v, -1+belowHeight+(3*v), 12*v);
		}
    	return new AxisAlignedBB(4*v, -1+belowHeight, 0*v, 12*v, -1+belowHeight+(3*v), 16*v);
    	
    }
}
