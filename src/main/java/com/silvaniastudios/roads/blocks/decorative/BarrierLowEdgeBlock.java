package com.silvaniastudios.roads.blocks.decorative;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BarrierLowEdgeBlock extends BarrierEdgeBlock {

	public BarrierLowEdgeBlock(String name, boolean double_sided) {
		super(name, double_sided);
	}
	
	@Override
	public void setBoxes(IBlockAccess world, BlockPos pos) {
		SOUTH_AABB = new AxisAlignedBB(0.0D, -1+getBlockBelowHeight(world, pos), 0.5D, 1.0D, -1+getBlockBelowHeight(world, pos)+0.625D, 1.0D);
		WEST_AABB  = new AxisAlignedBB(0.0D, -1+getBlockBelowHeight(world, pos), 0.0D, 0.5D, -1+getBlockBelowHeight(world, pos)+0.625D, 1.0D);
		NORTH_AABB = new AxisAlignedBB(0.0D, -1+getBlockBelowHeight(world, pos), 0.0D, 1.0D, -1+getBlockBelowHeight(world, pos)+0.625D, 0.5D);
		EAST_AABB  = new AxisAlignedBB(0.5D, -1+getBlockBelowHeight(world, pos), 0.0D, 1.0D, -1+getBlockBelowHeight(world, pos)+0.625D, 1.0D);
		FULL_AABB  = new AxisAlignedBB(0.0D, -1+getBlockBelowHeight(world, pos), 0.0D, 1.0D, -1+getBlockBelowHeight(world, pos)+0.625D, 1.0D);
	}
}
