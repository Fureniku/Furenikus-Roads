package com.silvaniastudios.roads.blocks.decorative;

import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FourWayRotDecorativeBlock extends GenericDecorativeBlock {

	public FourWayRotDecorativeBlock(String name) {
		super(name);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
		items.add(new ItemStack(this, 1, 0));
		items.add(new ItemStack(this, 1, 4));
		items.add(new ItemStack(this, 1, 8));
		items.add(new ItemStack(this, 1, 12));
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
}
