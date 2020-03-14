package com.silvaniastudios.roads.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class WheelStopBlock extends FourWayRotDecorativeBlock {

	public WheelStopBlock(String name) {
		super(name);
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
    	if (meta == 0 || meta == 4 || meta == 8 || meta == 12) {
    		return new AxisAlignedBB(0*v, -1+belowHeight, 1*v, 16*v, -1+belowHeight+(3*v), 5*v);
		}
    	if (meta == 1 || meta == 5 || meta == 9 || meta == 13) {
    		return new AxisAlignedBB(11*v, -1+belowHeight, 0*v, 15*v, -1+belowHeight+(3*v), 16*v);
		}
    	if (meta == 2 || meta == 6 || meta == 10 || meta == 14) {
    		return new AxisAlignedBB(0*v, -1+belowHeight, 11*v, 16*v, -1+belowHeight+(3*v), 15*v);
		}
    	
    	return new AxisAlignedBB(1*v, -1+belowHeight, 0*v, 5*v, -1+belowHeight+(3*v), 16*v);
    }
}
