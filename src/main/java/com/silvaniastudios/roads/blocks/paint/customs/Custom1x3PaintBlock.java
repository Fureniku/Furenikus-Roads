package com.silvaniastudios.roads.blocks.paint.customs;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.PaintColour;
import com.silvaniastudios.roads.blocks.enums.EnumThreeLengthConnectable;
import com.silvaniastudios.roads.blocks.enums.EnumTwoLengthConnectable;
import com.silvaniastudios.roads.blocks.paint.properties.PaintGrid;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Custom1x3PaintBlock extends CustomPaintBlock {

	public static final PropertyEnum<EnumThreeLengthConnectable> CONNECT = PropertyEnum.create("position_rotation", EnumThreeLengthConnectable.class);

	private boolean horizontal = false;

	public Custom1x3PaintBlock(String name, String localName, PaintGrid[] grids, String category, PaintColour colour, boolean horizontal) {
		super(name, localName, EnumPaintType.MULTI_3x1, grids, category, new int[] {0}, colour);
		this.horizontal = horizontal;
		this.setDefaultState(this.blockState.getBaseState().withProperty(CONNECT, EnumThreeLengthConnectable.NORTH_BOTTOM));
	}

	public Custom1x3PaintBlock(String name, PaintGrid[] grids, String category, PaintColour colour) {
		super(name, EnumPaintType.MULTI_3x1, grids, category, new int[] {0}, colour, FurenikusRoads.tab_paint_icons);
		this.setDefaultState(this.blockState.getBaseState().withProperty(CONNECT, EnumThreeLengthConnectable.NORTH_BOTTOM));
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		EnumFacing placerFacing = placer.getHorizontalFacing();
		int returnMeta = meta;
		
		if (placerFacing.equals(EnumFacing.EAST))  { returnMeta = meta + 1; }
		if (placerFacing.equals(EnumFacing.SOUTH)) { returnMeta = meta + 2; }
		if (placerFacing.equals(EnumFacing.WEST))  { returnMeta = meta + 3; }

		IBlockState northBlock = world.getBlockState(pos.offset(EnumFacing.NORTH));
		IBlockState eastBlock =  world.getBlockState(pos.offset(EnumFacing.EAST));
		IBlockState southBlock = world.getBlockState(pos.offset(EnumFacing.SOUTH));
		IBlockState westBlock =  world.getBlockState(pos.offset(EnumFacing.WEST));

		if (horizontal) { //Change the checking directions for horizontal placement
			southBlock = westBlock;
			westBlock = northBlock;
			northBlock = eastBlock;
			eastBlock = world.getBlockState(pos.offset(EnumFacing.SOUTH));
		}
		
		if (placerFacing.equals(EnumFacing.NORTH)) {
			if (southBlock.getBlock() instanceof Custom1x3PaintBlock) {
				if (getMetaFromState(southBlock) == 0) { returnMeta = 4; }
				if (getMetaFromState(southBlock) == 4) { returnMeta = 8; }
			} else if (northBlock.getBlock() instanceof Custom1x3PaintBlock) {
				if (getMetaFromState(northBlock) == 8) { returnMeta = 4; }
				if (getMetaFromState(northBlock) == 4) { returnMeta = 0; }
			}
		}
		
		if (placerFacing.equals(EnumFacing.EAST)) {
			if (westBlock.getBlock() instanceof Custom1x3PaintBlock) {
				if (getMetaFromState(westBlock) == 1) { returnMeta = 5; }
				if (getMetaFromState(westBlock) == 5) { returnMeta = 9; }
			} else if (eastBlock.getBlock() instanceof Custom1x3PaintBlock) {
				if (getMetaFromState(eastBlock) == 9) { returnMeta = 5; }
				if (getMetaFromState(eastBlock) == 5) { returnMeta = 1; }
			}
		}
		
		if (placerFacing.equals(EnumFacing.SOUTH)) {
			if (northBlock.getBlock() instanceof Custom1x3PaintBlock) {
				if (getMetaFromState(northBlock) == 2) { returnMeta = 6; }
				if (getMetaFromState(northBlock) == 6) { returnMeta = 10; }
			} else if (southBlock.getBlock() instanceof Custom1x3PaintBlock) {
				if (getMetaFromState(southBlock) == 10) { returnMeta = 6; }
				if (getMetaFromState(southBlock) == 6) { returnMeta = 2; }
			}
		}
		
		if (placerFacing.equals(EnumFacing.WEST)) {
			if (eastBlock.getBlock() instanceof Custom1x3PaintBlock) {
				if (getMetaFromState(eastBlock) == 3) { returnMeta = 7; }
				if (getMetaFromState(eastBlock) == 7) { returnMeta = 11; }
			} else if (westBlock.getBlock() instanceof Custom1x3PaintBlock) {
				if (getMetaFromState(westBlock) == 11) { returnMeta = 7; }
				if (getMetaFromState(westBlock) == 7) { returnMeta = 3; }
			}
		}
		return this.getDefaultState().withProperty(CONNECT, EnumThreeLengthConnectable.byMetadata(returnMeta));
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(CONNECT).getIndex();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(CONNECT, EnumThreeLengthConnectable.byMetadata(meta));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {CONNECT});
	}
}
