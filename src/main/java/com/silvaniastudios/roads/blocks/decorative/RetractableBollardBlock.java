package com.silvaniastudios.roads.blocks.decorative;

import javax.annotation.Nullable;

import com.silvaniastudios.roads.blocks.enums.EnumMeta;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RetractableBollardBlock extends GenericDecorativeBlock {

	public RetractableBollardBlock(String name) {
		super(name);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
    	for (int i = 0; i < EnumMeta.values().length; i++) {
    		if (i % 2 == 0) { 
    			items.add(new ItemStack(this, 1, i));
    		}
    	}
    }

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(state.getBlock(), 1, getDroppedMeta(state));
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getDroppedMeta(state);
	}

	private int getDroppedMeta(IBlockState state) {
		int meta = getMetaFromState(state);

		if (meta % 2 == 1) { return meta - 1; }
		return meta;
	}
	
	@Override
	 public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
		int meta = getMetaFromState(state);
		if (world.isBlockPowered(pos) && (meta % 2 == 0)) {
			world.setBlockState(pos, getDefaultState().withProperty(META_ID, EnumMeta.byMetadata(meta+1)));
		} else if (!world.isBlockPowered(pos) && (meta % 2 != 0)) {
			world.setBlockState(pos, getDefaultState().withProperty(META_ID, EnumMeta.byMetadata(meta-1)));
		}
	}

	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		int meta = getMetaFromState(state);
        return new AxisAlignedBB(0.5-(getWidth(meta)/2), -1+getBlockBelowHeight(worldIn, pos), 0.5-(getWidth(meta)/2), 0.5+(getWidth(meta)/2), -1+getBlockBelowHeight(worldIn, pos)+getHeight(meta), 0.5+(getWidth(meta)/2));
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
    	int meta = getMetaFromState(state);
        return new AxisAlignedBB(0.5-(getWidth(meta)/2), -1+getBlockBelowHeight(worldIn, pos), 0.5-(getWidth(meta)/2), 0.5+(getWidth(meta)/2), -1+getBlockBelowHeight(worldIn, pos)+getCollisionHeight(meta), 0.5+(getWidth(meta)/2));
    }
    float v = 1.0F/16.0F;
    
    private double getWidth(int meta) {
    	if (meta < 8) { return 8*v; }
    	return 5*v;
    }
    
    private double getHeight(int meta) {
    	if (meta == 0 || meta == 2) { return 12*v; }
    	if (meta == 4 || meta == 6) { return 1.0D; }
    	if (meta == 8 || meta == 10 || meta == 12 || meta == 14) { return 1.0D; }
    	return meta < 8 ? 0.5*v : 1*v;
    }
    
    private double getCollisionHeight(int meta) {
    	if (meta == 0 || meta == 2 || meta == 4 || meta == 6 || meta == 8 || meta == 10 || meta == 12 || meta == 14) { return 1.5; }
    	return meta < 8 ? 0.5*v : 1*v;
    }
}
