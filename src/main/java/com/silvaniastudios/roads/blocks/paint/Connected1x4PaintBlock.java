package com.silvaniastudios.roads.blocks.paint;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.enums.EnumFourLengthConnectable;

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

public class Connected1x4PaintBlock extends PaintBlockBase {
	
	public static final PropertyEnum<EnumFourLengthConnectable> CONNECT = PropertyEnum.create("position_rotation", EnumFourLengthConnectable.class);

	public Connected1x4PaintBlock(String name, boolean icon, String category, int[] coreMetas, boolean dynamic) {
		super(name, category, coreMetas, dynamic);
		if (icon) { this.setCreativeTab(FurenikusRoads.tab_paint_icons); } else { this.setCreativeTab(FurenikusRoads.tab_paint_junction); }
		this.setDefaultState(this.blockState.getBaseState().withProperty(CONNECT, EnumFourLengthConnectable.NORTH_TOP));
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		EnumFacing placerFacing = placer.getHorizontalFacing();
		int placeMeta = 0;
		
		IBlockState northBlock = world.getBlockState(pos.offset(EnumFacing.NORTH));
		IBlockState eastBlock =  world.getBlockState(pos.offset(EnumFacing.EAST));
		IBlockState southBlock = world.getBlockState(pos.offset(EnumFacing.SOUTH));
		IBlockState westBlock =  world.getBlockState(pos.offset(EnumFacing.WEST));
		
		if (placerFacing.equals(EnumFacing.NORTH)) { placeMeta = 3; }
		if (placerFacing.equals(EnumFacing.EAST))  { placeMeta = 7; }
		if (placerFacing.equals(EnumFacing.SOUTH)) { placeMeta = 11; }
		if (placerFacing.equals(EnumFacing.WEST))  { placeMeta = 15; }
		
		if (placerFacing.equals(EnumFacing.NORTH) || placerFacing.equals(EnumFacing.SOUTH)) {
			if (southBlock.getBlock().getRegistryName().equals(this.getRegistryName())) {
				int southMeta = getMetaFromState(southBlock);
				if (southMeta < 11 && southMeta > 7) { placeMeta = southMeta + 1; }
				if (southMeta < 4 && southMeta > 0) { placeMeta = southMeta - 1; }

			}
			if (northBlock.getBlock().getRegistryName().equals(this.getRegistryName())) {
				int northMeta = getMetaFromState(northBlock);
				if (northMeta < 3) { placeMeta = northMeta + 1; }
				if (northMeta < 12 && northMeta > 8) { placeMeta = northMeta - 1; }

			}
		}
		
		if (placerFacing.equals(EnumFacing.EAST) || placerFacing.equals(EnumFacing.WEST)) {
			if (westBlock.getBlock().getRegistryName().equals(this.getRegistryName())) {
				int westMeta = getMetaFromState(westBlock);
				if (westMeta < 15 && westMeta > 11) { placeMeta = westMeta + 1; }
				if (westMeta < 8 && westMeta > 4) { placeMeta = westMeta - 1; }

			}
			if (eastBlock.getBlock().getRegistryName().equals(this.getRegistryName())) {
				int eastMeta = getMetaFromState(eastBlock);
				if (eastMeta < 7 && eastMeta > 3) { placeMeta = eastMeta + 1; }
				if (eastMeta < 16 && eastMeta > 12) { placeMeta = eastMeta - 1; }

			}
		}
		return this.getDefaultState().withProperty(CONNECT, EnumFourLengthConnectable.byMetadata(placeMeta)); //TODO
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFourLengthConnectable)state.getValue(CONNECT)).getIndex();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(CONNECT, EnumFourLengthConnectable.byMetadata(meta));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {CONNECT});
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
}
