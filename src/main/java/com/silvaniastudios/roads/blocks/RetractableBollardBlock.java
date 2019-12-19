package com.silvaniastudios.roads.blocks;

import com.silvaniastudios.roads.blocks.enums.EnumMeta;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
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
	 public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
		int meta = getMetaFromState(state);
		System.out.println("Neighbour change! meta is " + meta);
		if (world.isBlockPowered(pos) && (meta % 2 == 0)) {
			System.out.println("Power switch, increase meta!");
			world.setBlockState(pos, getDefaultState().withProperty(META_ID, EnumMeta.byMetadata(meta+1)));
		} else if (!world.isBlockPowered(pos) && (meta % 2 != 0)) {
			System.out.println("Power switch, decrease meta!");
			world.setBlockState(pos, getDefaultState().withProperty(META_ID, EnumMeta.byMetadata(meta-1)));
		}
	}

}
