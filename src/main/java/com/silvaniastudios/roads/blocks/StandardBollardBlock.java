package com.silvaniastudios.roads.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class StandardBollardBlock extends GenericDecorativeBlock {

	public StandardBollardBlock(String name) {
		super(name);
	}
	
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		int meta = getMetaFromState(state);
        return new AxisAlignedBB(0.5-(getWidth(meta)/2), -1+getBlockBelowHeight(worldIn, pos), 0.5-(getWidth(meta)/2), 0.5+(getWidth(meta)/2), -1+getBlockBelowHeight(worldIn, pos)+getHeight(meta), 0.5+(getWidth(meta)/2));
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
    	int meta = getMetaFromState(state);
        return new AxisAlignedBB(0.5-(getWidth(meta)/2), -1+getBlockBelowHeight(worldIn, pos), 0.5-(getWidth(meta)/2), 0.5+(getWidth(meta)/2), -1+getBlockBelowHeight(worldIn, pos)+getHeight(meta), 0.5+(getWidth(meta)/2));
    }
    
    float v = 1.0F/16.0F;
    
    private double getWidth(int meta) {
    	if (meta == 0 || meta == 1 || meta == 10 || meta == 11 || meta == 14 || meta == 15) { return 4*v; }
    	if (meta == 2 || meta == 3 || meta == 4 || meta == 5 || meta == 12 || meta == 13) { return 3.5*v; }
    	if (meta == 6 || meta == 7 || meta == 8 || meta == 9) { return 2*v; }
    	return 1.0D;
    }
    
    private double getHeight(int meta) {
    	if (meta == 0 || meta == 1 || meta == 14 || meta == 15) { return 1.0D; }
    	if (meta == 12 || meta == 13) { return 18*v; }
    	return 14.5*v;
    }
    
    @Override
    public int getLightValue(IBlockState state) {
		if (getMetaFromState(state) == 12 || getMetaFromState(state) == 13) {
			return 15;
		}
        return this.lightValue;
    }
}
