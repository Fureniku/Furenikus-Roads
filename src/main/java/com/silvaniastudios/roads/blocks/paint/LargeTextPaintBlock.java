package com.silvaniastudios.roads.blocks.paint;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.PaintColour;
import com.silvaniastudios.roads.blocks.enums.EnumMeta;

import com.silvaniastudios.roads.blocks.paint.customs.CustomMetaPaintBlock;
import com.silvaniastudios.roads.blocks.paint.customs.CustomPaintBlock;
import com.silvaniastudios.roads.blocks.paint.properties.PaintGrid;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class LargeTextPaintBlock extends CustomMetaPaintBlock {

	public LargeTextPaintBlock(String name, String localName, PaintGrid[] grids, String category, PaintColour colour) {
		super(name, localName, EnumPaintType.LARGE_TEXT, grids, category, new int[] {0, 4}, colour);
		this.dynamic = new boolean[] {false, true};
		this.setDefaultState(this.blockState.getBaseState().withProperty(META_ID, EnumMeta.id0));
	}

	public LargeTextPaintBlock(String name, PaintGrid[] grids, String category, PaintColour colour) {
		super(name, EnumPaintType.LARGE_TEXT, grids, category, new int[] {0, 4}, colour, FurenikusRoads.tab_paint_text);
		this.dynamic = new boolean[] {false, true};
		this.setDefaultState(this.blockState.getBaseState().withProperty(META_ID, EnumMeta.id0));
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
		
		if (meta > 3) {
			if (placerFacing.equals(EnumFacing.NORTH)) {
				if (westBlock.getBlock() instanceof LargeTextPaintBlock) {
					if (getMetaFromState(westBlock) < 12 && getMetaFromState(westBlock) > 3) {
						returnMeta = getMetaFromState(westBlock) + 4;
					}
				}
			}
			
			if (placerFacing.equals(EnumFacing.EAST)) {
				if (northBlock.getBlock() instanceof LargeTextPaintBlock) {
					if (getMetaFromState(northBlock) < 12 && getMetaFromState(northBlock) > 3) {
						returnMeta = getMetaFromState(northBlock) + 4;
					}
				}
			}
			
			if (placerFacing.equals(EnumFacing.SOUTH)) {
				if (eastBlock.getBlock() instanceof LargeTextPaintBlock) {
					if (getMetaFromState(eastBlock) < 12 && getMetaFromState(eastBlock) > 3) {
						returnMeta = getMetaFromState(eastBlock) + 4;
					}
				}
			}
			
			if (placerFacing.equals(EnumFacing.WEST)) {
				if (southBlock.getBlock() instanceof LargeTextPaintBlock) {
					if (getMetaFromState(southBlock) < 12 && getMetaFromState(southBlock) > 3) {
						returnMeta = getMetaFromState(southBlock) + 4;
					}
				}
			}
		}
		return this.getDefaultState().withProperty(META_ID, EnumMeta.byMetadata(returnMeta));
	}
}
