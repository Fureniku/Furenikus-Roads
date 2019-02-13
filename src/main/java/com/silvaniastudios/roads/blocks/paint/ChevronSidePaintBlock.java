package com.silvaniastudios.roads.blocks.paint;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.enums.EnumConnectJunctionChevron;
import com.silvaniastudios.roads.blocks.enums.EnumMeta;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ChevronSidePaintBlock extends ChevronMidPaintBlock {

	public static final PropertyEnum<EnumMeta> META_ID = PropertyEnum.create("meta", EnumMeta.class);
	public static final PropertyEnum<EnumConnectJunctionChevron> EDGE = PropertyEnum.create("edge", EnumConnectJunctionChevron.class);
	
	public ChevronSidePaintBlock(String name) {
		super(name, false);
		this.setDefaultState(this.blockState.getBaseState().withProperty(META_ID, EnumMeta.id0).withProperty(EDGE, EnumConnectJunctionChevron.NONE));
		this.setCreativeTab(FurenikusRoads.tab_paint_junction);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(CreativeTabs creativeTabs, NonNullList<ItemStack> list) {
		list.add(new ItemStack(this, 1, 0));
		list.add(new ItemStack(this, 1, 8));
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
		return new BlockStateContainer(this, new IProperty[] {META_ID, EDGE});
	}
	
	public EnumConnectJunctionChevron getEdge(IBlockAccess world, BlockPos pos) {
		int rootBlockMeta = getMetaFromState(world.getBlockState(pos));
		IBlockState offsetBlockNorth = world.getBlockState(pos.offset(EnumFacing.NORTH));
		IBlockState offsetBlockEast  = world.getBlockState(pos.offset(EnumFacing.EAST));
		IBlockState offsetBlockSouth = world.getBlockState(pos.offset(EnumFacing.SOUTH));
		IBlockState offsetBlockWest  = world.getBlockState(pos.offset(EnumFacing.WEST));
		int offsetMetaNorth = offsetBlockNorth.getBlock() instanceof Connected1x4PaintBlock ? offsetBlockNorth.getBlock().getMetaFromState(offsetBlockNorth) : -1;
		int offsetMetaEast  = offsetBlockEast.getBlock()  instanceof Connected1x4PaintBlock ? offsetBlockEast.getBlock().getMetaFromState(offsetBlockEast) : -1;
		int offsetMetaSouth = offsetBlockSouth.getBlock() instanceof Connected1x4PaintBlock ? offsetBlockSouth.getBlock().getMetaFromState(offsetBlockSouth) : -1;
		int offsetMetaWest  = offsetBlockWest.getBlock()  instanceof Connected1x4PaintBlock ? offsetBlockWest.getBlock().getMetaFromState(offsetBlockWest) : -1;
		
		
		if (rootBlockMeta == 4  && offsetMetaWest  == 3  && offsetMetaSouth == 0)  { return EnumConnectJunctionChevron.N_1; }
		if (rootBlockMeta == 5  && offsetMetaNorth == 7  && offsetMetaWest  == 4)  { return EnumConnectJunctionChevron.E_1; }
		if (rootBlockMeta == 6  && offsetMetaEast  == 11 && offsetMetaNorth == 8)  { return EnumConnectJunctionChevron.S_1; }
		if (rootBlockMeta == 7  && offsetMetaNorth == 15 && offsetMetaEast  == 12) { return EnumConnectJunctionChevron.W_1; }
		
		if (rootBlockMeta == 12 && offsetMetaEast  == 3 && offsetMetaSouth  == 0)  { return EnumConnectJunctionChevron.N_2; }
		if (rootBlockMeta == 13 && offsetMetaSouth == 7 && offsetMetaWest   == 4)  { return EnumConnectJunctionChevron.E_2; }
		if (rootBlockMeta == 14 && offsetMetaWest  == 11 && offsetMetaNorth == 8)  { return EnumConnectJunctionChevron.S_2; }
		if (rootBlockMeta == 15 && offsetMetaSouth == 15 && offsetMetaEast  == 12) { return EnumConnectJunctionChevron.W_2; }
		
		if (rootBlockMeta == 4) {}
		
		return EnumConnectJunctionChevron.NONE;
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(EDGE, getEdge(worldIn, pos));
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 8, new ModelResourceLocation(getRegistryName(), "inventory_2"));
	}
}