package com.silvaniastudios.roads.client;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class ModBlockColours implements IBlockColor {

	@Override
	public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
		return worldIn.getBiome(pos).getGrassColorAtPos(pos);
	}
}
