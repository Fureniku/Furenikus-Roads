package com.silvaniastudios.roads.blocks.paint;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.enums.EnumMeta;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ChevronMidPaintBlock extends PaintBlockBase {

	public static final PropertyEnum<EnumMeta> META_ID = PropertyEnum.create("meta", EnumMeta.class);
	public boolean fourType = false;
	
	public ChevronMidPaintBlock(String name, boolean fourType, String category, int[] coreMetas, boolean dynamic) {
		super(name, category, coreMetas, dynamic);
		this.fourType = fourType;
		this.setDefaultState(this.blockState.getBaseState().withProperty(META_ID, EnumMeta.id0));
		this.setCreativeTab(FurenikusRoads.tab_paint_junction);
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		if (meta > 7 && fourType) {
			if (placer.getHorizontalFacing().equals(EnumFacing.NORTH)) { return this.getDefaultState().withProperty(META_ID, EnumMeta.byMetadata(meta)); }
			if (placer.getHorizontalFacing().equals(EnumFacing.EAST))  { return this.getDefaultState().withProperty(META_ID, EnumMeta.byMetadata(meta + 1)); }
			if (placer.getHorizontalFacing().equals(EnumFacing.SOUTH)) { return this.getDefaultState().withProperty(META_ID, EnumMeta.byMetadata(meta + 2)); }
			if (placer.getHorizontalFacing().equals(EnumFacing.WEST))  { return this.getDefaultState().withProperty(META_ID, EnumMeta.byMetadata(meta + 3)); }
		}
		
		IBlockState stateNorth = world.getBlockState(pos.offset(EnumFacing.NORTH));		
		IBlockState stateEast = world.getBlockState(pos.offset(EnumFacing.EAST));
		IBlockState stateSouth = world.getBlockState(pos.offset(EnumFacing.SOUTH));
		IBlockState stateWest = world.getBlockState(pos.offset(EnumFacing.WEST));
		
		IBlockState stateLeft = null;
		IBlockState stateRight = null;
		IBlockState stateUp = null;
		IBlockState stateDown = null;
		
		int rot = 0;
		
		if (placer.getHorizontalFacing().equals(EnumFacing.NORTH)) {
			stateLeft = stateWest;
			stateRight = stateEast;
			stateUp = stateNorth;
			stateDown = stateSouth;
			rot = 0;
		}
		
		if (placer.getHorizontalFacing().equals(EnumFacing.EAST))  {
			stateLeft  = stateNorth;
			stateRight = stateSouth;
			stateUp = stateEast;
			stateDown = stateWest;
			rot = 1;
		}
		
		if (placer.getHorizontalFacing().equals(EnumFacing.SOUTH)) {
			stateLeft  = stateEast;
			stateRight = stateWest;
			stateUp = stateSouth;
			stateDown = stateNorth;
			rot = 2;
		}
		
		if (placer.getHorizontalFacing().equals(EnumFacing.WEST))  {
			stateLeft  = stateSouth;
			stateRight = stateNorth;
			stateUp = stateWest;
			stateDown = stateEast;
			rot = 3;
		}
		
		Block blockLeft  = stateLeft.getBlock();
		Block blockRight = stateRight.getBlock();
		Block blockUp = stateUp.getBlock();
		Block blockDown = stateDown.getBlock();
		
		if (blockUp.getRegistryName().equals(this.getRegistryName())) { 
			return this.getDefaultState().withProperty(META_ID, EnumMeta.byMetadata(blockUp.getMetaFromState(stateUp)));
		} else if (blockDown.getRegistryName().equals(this.getRegistryName())) {
			return this.getDefaultState().withProperty(META_ID, EnumMeta.byMetadata(blockDown.getMetaFromState(stateDown)));
		} else if (blockLeft instanceof Connected1x4PaintBlock || blockLeft instanceof ChevronSidePaintBlock) {
			return this.getDefaultState().withProperty(META_ID, EnumMeta.byMetadata(placeMeta(blockLeft, rot, stateLeft)));
		} else if (blockRight instanceof Connected1x4PaintBlock || blockRight instanceof ChevronSidePaintBlock) {
			return this.getDefaultState().withProperty(META_ID, EnumMeta.byMetadata(placeMeta(blockRight, rot, stateRight)));
		} else {
			return this.getDefaultState().withProperty(META_ID, EnumMeta.byMetadata(meta+rot));
		}
	}
	
	public int placeMeta(Block block, int meta, IBlockState state) {
		if (block instanceof Connected1x4PaintBlock) {
			return meta + 4;
		}
		
		if (block instanceof ChevronSidePaintBlock) {
			ChevronSidePaintBlock b = (ChevronSidePaintBlock) block;
			int bMeta = b.getMetaFromState(state);
			if (bMeta < 4 || (bMeta > 7 && bMeta < 12)) {
				return meta + 4;
			} else {
				return meta;
			}
		}
		return meta;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(CreativeTabs creativeTabs, NonNullList<ItemStack> list) {
		list.add(new ItemStack(this, 1, 0));
		list.add(new ItemStack(this, 1, 4));
		list.add(new ItemStack(this, 1, 8));
		list.add(new ItemStack(this, 1, 12));
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumMeta)state.getValue(META_ID)).getMetadata();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(META_ID, EnumMeta.byMetadata(meta));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {META_ID});
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 4, new ModelResourceLocation(getRegistryName(), "inventory_2"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 8, new ModelResourceLocation(getRegistryName(), "inventory_3"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 12, new ModelResourceLocation(getRegistryName(), "inventory_4"));
	}
}