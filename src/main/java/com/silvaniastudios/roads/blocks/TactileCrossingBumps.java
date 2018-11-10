package com.silvaniastudios.roads.blocks;

import javax.annotation.Nullable;

import com.silvaniastudios.roads.FurenikusRoads;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;

public class TactileCrossingBumps extends BlockBase {
	
	public TactileCrossingBumps(String name) {
		super(name, Material.ROCK);
		this.setCreativeTab(FurenikusRoads.tab_road_parts);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
    	return new AxisAlignedBB(0.0D, -1+getBlockBelowHeight(worldIn, pos), 0.0D, 1.0D, -1+getBlockBelowHeight(worldIn, pos)+0.0625, 1.0D);
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return new AxisAlignedBB(0.0D, -1+getBlockBelowHeight(worldIn, pos), 0.0D, 1.0D, -1+getBlockBelowHeight(worldIn, pos)+0.0625, 1.0D);
    }
    
    @Override
    public Block.EnumOffsetType getOffsetType()
    {
        return Block.EnumOffsetType.XYZ;
    }
    
    @Override
    public Vec3d getOffset(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        double offset = 1.0 - getBlockBelowHeight(worldIn, pos);
        return new Vec3d(0, -offset, 0);
    }
    
    @SuppressWarnings("deprecation")
	public double getBlockBelowHeight(IBlockAccess worldIn, BlockPos pos) {
    	IBlockState underState = worldIn.getBlockState(pos.offset(EnumFacing.DOWN));
        Block underBlock = underState.getBlock();
        double extraOffset = 0.0;
        
        if (underBlock instanceof PaintBlockBase || underBlock instanceof TactileCrossingBumps || underBlock instanceof SidewalkBlock) {
        	extraOffset = 0.062;
        }
        
        return underBlock.getBoundingBox(underState, worldIn, pos.offset(EnumFacing.DOWN)).maxY - extraOffset;
    }
}