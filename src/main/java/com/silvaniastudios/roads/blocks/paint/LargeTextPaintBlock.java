package com.silvaniastudios.roads.blocks.paint;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.enums.EnumMeta;

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

public class LargeTextPaintBlock extends PaintBlockBase {
	
	public static final PropertyEnum<EnumMeta> META_ID = PropertyEnum.create("meta", EnumMeta.class);

	public LargeTextPaintBlock(String name, String category, int[] coreMetas, boolean dynamic, String colour) {
		super(name, category, coreMetas, dynamic, colour);
		this.setDefaultState(this.blockState.getBaseState().withProperty(META_ID, EnumMeta.id0));
		this.setCreativeTab(FurenikusRoads.tab_paint_text);
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
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(CreativeTabs creativeTabs, NonNullList<ItemStack> list) {
		list.add(new ItemStack(this, 1, 0));
		list.add(new ItemStack(this, 1, 4));
	}
	
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    	int meta = getMetaFromState(state);
    	
    	if (meta < 4) { return new ItemStack(this, 1, 0); }
    	return new ItemStack(this, 1, 4);
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
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 4, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}
