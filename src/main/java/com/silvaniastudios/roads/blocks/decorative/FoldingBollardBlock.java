package com.silvaniastudios.roads.blocks.decorative;

import javax.annotation.Nullable;

import com.silvaniastudios.roads.blocks.enums.EnumMeta;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FoldingBollardBlock extends GenericDecorativeBlock {

	public FoldingBollardBlock(String name) {
		super(name);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
		items.add(new ItemStack(this, 1, 0));
		items.add(new ItemStack(this, 1, 8));
    }
	
	@SuppressWarnings("deprecation")
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		int rot = 0;
		if (placer.getHorizontalFacing() == EnumFacing.EAST)  { rot = 1; }
		if (placer.getHorizontalFacing() == EnumFacing.SOUTH) { rot = 2; }
		if (placer.getHorizontalFacing() == EnumFacing.WEST)  { rot = 3; }
		return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta+rot, placer);
	}
	
	@Override
	 public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
		int meta = getMetaFromState(state);
		if (world.isBlockPowered(pos) && (meta < 4 || (meta > 7 && meta < 12))) {
			world.setBlockState(pos, getDefaultState().withProperty(META_ID, EnumMeta.byMetadata(meta+4)));
		} else if (!world.isBlockPowered(pos) && ((meta > 3 && meta < 8)|| meta > 11)) {
			world.setBlockState(pos, getDefaultState().withProperty(META_ID, EnumMeta.byMetadata(meta-4)));
		}
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
    float v = 1.0F/16.0F;
    
    public AxisAlignedBB getBox(int meta, double belowHeight) {
    	if (meta == 0 || meta == 1 || meta == 2 || meta == 3) {
    		return new AxisAlignedBB(6*v, -1+belowHeight, 6*v, 10*v, -1+belowHeight+1, 10*v);
		}
    	
    	if (meta == 4) { return new AxisAlignedBB(6*v, -1+belowHeight, 6*v, 10*v, -1+belowHeight+(2*v), 25*v); }
    	if (meta == 5) { return new AxisAlignedBB(-9*v, -1+belowHeight, 6*v, 10*v, -1+belowHeight+(2*v), 10*v); }
    	if (meta == 6) { return new AxisAlignedBB(6*v, -1+belowHeight, -9*v, 10*v, -1+belowHeight+(2*v), 10*v); }
    	if (meta == 7) { return new AxisAlignedBB(6*v, -1+belowHeight, 6*v, 25*v, -1+belowHeight+(2*v), 10*v); }
    	
    	if (meta == 8 || meta == 10) { return new AxisAlignedBB(6*v, -1+belowHeight, 7*v, 10*v, -1+belowHeight+1, 9*v); }
    	if (meta == 9 || meta == 11) { return new AxisAlignedBB(7*v, -1+belowHeight, 6*v, 9*v, -1+belowHeight+1, 10*v); }
    	
    	if (meta == 12) { return new AxisAlignedBB(6*v, -1+belowHeight, 6*v, 10*v,  -1+belowHeight+(2*v), 25*v); }
    	if (meta == 13) { return new AxisAlignedBB(-9*v, -1+belowHeight, 6*v, 10*v, -1+belowHeight+(2*v),  10*v); }
    	if (meta == 14) { return new AxisAlignedBB(6*v, -1+belowHeight, -9*v, 10*v,  -1+belowHeight+(2*v), 10*v); }
    	if (meta == 15) { return new AxisAlignedBB(6*v, -1+belowHeight, 6*v, 25*v, -1+belowHeight+(2*v),  10*v); }
    	
    	return FULL_BLOCK_AABB;
    }
}
