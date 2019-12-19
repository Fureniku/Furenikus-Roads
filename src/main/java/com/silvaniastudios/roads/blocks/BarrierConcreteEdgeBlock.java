package com.silvaniastudios.roads.blocks;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BarrierConcreteEdgeBlock extends BarrierEdgeBlock {

	public BarrierConcreteEdgeBlock(String name) {
		super(name, false);
	}
	
	@Override
	public void setBoxes(IBlockAccess world, BlockPos pos) {
		SOUTH_AABB = new AxisAlignedBB(0.0D, -1+getBlockBelowHeight(world, pos), 0.5D, 1.0D, -1+getBlockBelowHeight(world, pos)+1.0D, 1.0D);
		WEST_AABB  = new AxisAlignedBB(0.0D, -1+getBlockBelowHeight(world, pos), 0.0D, 0.5D, -1+getBlockBelowHeight(world, pos)+1.0D, 1.0D);
		NORTH_AABB = new AxisAlignedBB(0.0D, -1+getBlockBelowHeight(world, pos), 0.0D, 1.0D, -1+getBlockBelowHeight(world, pos)+1.0D, 0.5D);
		EAST_AABB  = new AxisAlignedBB(0.5D, -1+getBlockBelowHeight(world, pos), 0.0D, 1.0D, -1+getBlockBelowHeight(world, pos)+1.0D, 1.0D);
		FULL_AABB  = new AxisAlignedBB(0.0D, -1+getBlockBelowHeight(world, pos), 0.0D, 1.0D, -1+getBlockBelowHeight(world, pos)+1.0D, 1.0D);
	}
}
