package com.silvaniastudios.roads.items;

import java.util.List;

import javax.annotation.Nullable;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.BarrierBlock;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemWrench extends RoadItemBase {

	public ItemWrench(String name) {
		super(name, 1);
		this.setCreativeTab(FurenikusRoads.tab_tools);
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("Used to make minor adjustments to certain blocks");
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);
		
		if (stack.getItem() != this) {
			return EnumActionResult.FAIL;
		}
		
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		
		if (player.isSneaking()) {
			if (block instanceof BarrierBlock) {
				int meta = block.getMetaFromState(state);
				
				if (meta == 0) { world.setBlockState(pos, block.getDefaultState().withProperty(BarrierBlock.POSTS, BarrierBlock.EnumPost.NS)); }
				if (meta == 1) { world.setBlockState(pos, block.getDefaultState().withProperty(BarrierBlock.POSTS, BarrierBlock.EnumPost.NONE)); }
			}
		}
		
		return EnumActionResult.PASS;
	}
}
