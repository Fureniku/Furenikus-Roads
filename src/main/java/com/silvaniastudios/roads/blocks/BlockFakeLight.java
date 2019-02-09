package com.silvaniastudios.roads.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFakeLight extends BlockBase {

	public BlockFakeLight(String name) {
		super(name, Material.AIR);
		this.setCreativeTab(null);
		this.setTickRandomly(true);
		this.setLightLevel(1.0F);
	}
	
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
    	return new AxisAlignedBB(0.5, 0.5, 0.5, 0.5, 0.5, 0.5);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
    	return NULL_AABB;
    }
    
    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
    
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
    
    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rnd) {
    	if (!findLightBlockAbove(world, pos)) {
    		world.setBlockToAir(pos);
    	}
    }
    
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
    	if (!findLightBlockAbove(world, pos)) {
    		world.setBlockToAir(pos);
    	}
}
    
    public boolean findLightBlockAbove(World world, BlockPos pos) {
    	BlockPos posOffset = pos.offset(EnumFacing.UP);
    	Block block = world.getBlockState(posOffset).getBlock();
    	
    	int count = 0;
    	
    	while (block == Blocks.AIR && count < 15) {
    		posOffset = posOffset.offset(EnumFacing.UP);
    		block = world.getBlockState(posOffset).getBlock();
    		count++;
    		if (block != Blocks.AIR) {
    			if (block.getLightValue(world.getBlockState(posOffset), world, posOffset) == 15) {
    				return true;
    			}
    			return false;
    		}
    	}
    	return false;
    }
}
