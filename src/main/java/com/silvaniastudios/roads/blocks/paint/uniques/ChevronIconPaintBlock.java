package com.silvaniastudios.roads.blocks.paint.uniques;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.PaintColour;
import com.silvaniastudios.roads.blocks.enums.EnumMeta;
import com.silvaniastudios.roads.blocks.enums.IMetaBlockName;

import com.silvaniastudios.roads.blocks.paint.customs.CustomMetaPaintBlock;
import com.silvaniastudios.roads.blocks.paint.properties.PaintGrid;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ChevronIconPaintBlock extends CustomMetaPaintBlock implements IMetaBlockName {
	
	public ChevronIconPaintBlock(String name, PaintGrid[] grids, String category, PaintColour colour) {
		super(name, EnumPaintType.ICON_1x1, grids, category, new int[] {0, 8}, colour, FurenikusRoads.tab_paint_junction);
		setDefaultState(this.blockState.getBaseState().withProperty(META_ID, EnumMeta.id0));
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return stack.getItemDamage() + "";
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		int placedMeta = 0;
		
		if (placer.getHorizontalFacing().equals(EnumFacing.NORTH)) { placedMeta = meta; }
		if (placer.getHorizontalFacing().equals(EnumFacing.EAST)) {  placedMeta = meta + 1; }
		if (placer.getHorizontalFacing().equals(EnumFacing.SOUTH)) { placedMeta = meta + 2; }
		if (placer.getHorizontalFacing().equals(EnumFacing.WEST)) {  placedMeta = meta + 3; }

		IBlockState northBlock = world.getBlockState(pos.offset(EnumFacing.NORTH));
		IBlockState eastBlock =  world.getBlockState(pos.offset(EnumFacing.EAST));
		IBlockState southBlock = world.getBlockState(pos.offset(EnumFacing.SOUTH));
		IBlockState westBlock =  world.getBlockState(pos.offset(EnumFacing.WEST));

		Block leftBlock = westBlock.getBlock();
		Block rightBlock = eastBlock.getBlock();
		int rot = 0;

		boolean checkLeft = false;
		boolean checkRight = false;

		if (this.getUnlocalizedName().contains("_chevron_mid")) { checkLeft = true; checkRight = true; }
		if (this.getUnlocalizedName().contains("_chevron_mid_left")) { checkLeft = true; }
		if (this.getUnlocalizedName().contains("_chevron_mid_right")) { checkRight = true; }

		if (placer.getHorizontalFacing().equals(EnumFacing.EAST)) {
			leftBlock = northBlock.getBlock();
			rightBlock = southBlock.getBlock();
			rot = 1;
		}

		if (placer.getHorizontalFacing().equals(EnumFacing.SOUTH)) {
			leftBlock = eastBlock.getBlock();
			rightBlock = westBlock.getBlock();
			rot = 2;
		}

		if (placer.getHorizontalFacing().equals(EnumFacing.WEST)) {
			leftBlock = southBlock.getBlock();
			rightBlock = northBlock.getBlock();
			rot = 3;
		}

		if (leftBlock.getUnlocalizedName().contains("chevron_left_a") && checkLeft) { placedMeta = 0 + rot; }
		if (leftBlock.getUnlocalizedName().contains("chevron_left_b") && checkLeft) { placedMeta = 4 + rot; }
		if (leftBlock.getUnlocalizedName().contains("chevron_left_a_thin") && checkLeft) { placedMeta = 8 + rot; }
		if (leftBlock.getUnlocalizedName().contains("chevron_left_b_thin") && checkLeft) { placedMeta = 12 + rot; }

		if (rightBlock.getUnlocalizedName().contains("chevron_right_a") && checkRight) { placedMeta = 0 + rot; }
		if (rightBlock.getUnlocalizedName().contains("chevron_right_b") && checkRight) { placedMeta = 4 + rot; }
		if (rightBlock.getUnlocalizedName().contains("chevron_right_a_thin") && checkRight) { placedMeta = 8 + rot; }
		if (rightBlock.getUnlocalizedName().contains("chevron_right_b_thin") && checkRight) { placedMeta = 12 + rot; }

		return this.getDefaultState().withProperty(META_ID, EnumMeta.byMetadata(placedMeta));
	}
}