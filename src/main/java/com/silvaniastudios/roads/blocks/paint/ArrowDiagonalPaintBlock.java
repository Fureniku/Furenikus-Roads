package com.silvaniastudios.roads.blocks.paint;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.enums.IMetaBlockName;

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
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ArrowDiagonalPaintBlock extends PaintBlockBase implements IMetaBlockName {
	
	public static final PropertyEnum<ArrowDiagonalPaintBlock.EnumDiagonalArrow> META_ID = PropertyEnum.create("meta", ArrowDiagonalPaintBlock.EnumDiagonalArrow.class);

	public ArrowDiagonalPaintBlock(String name, String category, int[] coreMetas, boolean dynamic) {
		super(name, category, coreMetas, dynamic);
		setDefaultState(this.blockState.getBaseState().withProperty(META_ID, ArrowDiagonalPaintBlock.EnumDiagonalArrow.id0));
		this.setCreativeTab(FurenikusRoads.tab_paint_icons);
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return stack.getItemDamage() + "";
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		if (placer.getHorizontalFacing() == EnumFacing.NORTH) { return this.getDefaultState().withProperty(META_ID, ArrowDiagonalPaintBlock.EnumDiagonalArrow.byMetadata(meta)); }
		if (placer.getHorizontalFacing() == EnumFacing.EAST)  { return this.getDefaultState().withProperty(META_ID, ArrowDiagonalPaintBlock.EnumDiagonalArrow.byMetadata(meta + 1)); }
		if (placer.getHorizontalFacing() == EnumFacing.SOUTH) { return this.getDefaultState().withProperty(META_ID, ArrowDiagonalPaintBlock.EnumDiagonalArrow.byMetadata(meta + 2)); }
		if (placer.getHorizontalFacing() == EnumFacing.WEST)  { return this.getDefaultState().withProperty(META_ID, ArrowDiagonalPaintBlock.EnumDiagonalArrow.byMetadata(meta + 3)); }
		
		return this.getDefaultState().withProperty(META_ID, ArrowDiagonalPaintBlock.EnumDiagonalArrow.byMetadata(meta));
	}
    
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {META_ID});
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(META_ID, ArrowDiagonalPaintBlock.EnumDiagonalArrow.byMetadata(meta));
    }
	
	@Override
    public int getMetaFromState(IBlockState state) {
        return ((ArrowDiagonalPaintBlock.EnumDiagonalArrow)state.getValue(META_ID)).getMetadata();
    }
	
    @SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
    	items.add(new ItemStack(this, 1, 0));
    	items.add(new ItemStack(this, 1, 4));
    }
    
    @SideOnly(Side.CLIENT)
	@Override
	public void initModel() {
    	ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    	ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 4, new ModelResourceLocation(getRegistryName(), "inventory_2"));
	}
    
    public static enum EnumDiagonalArrow implements IStringSerializable {
		id0(0, "0"),
		id1(1, "1"),
		id2(2, "2"),
		id3(3, "3"),
		id4(4, "4"),
		id5(5, "5"),
		id6(6, "6"),
		id7(7, "7");
		
		private static final EnumDiagonalArrow[] META_LOOKUP = new EnumDiagonalArrow[values().length];
		private final int meta;
		private final String name;
		
		private EnumDiagonalArrow(int meta, String name) {
			this.meta = meta;
			this.name = name;
		}

		@Override
		public String getName() {
			return this.name;
		}
		
		public int getMetadata() {
	        return this.meta;
	    }
		
		public static EnumDiagonalArrow byMetadata(int meta) {
	        if (meta < 0 || meta >= META_LOOKUP.length) {
	            meta = 0;
	        }
	        
	        return META_LOOKUP[meta];
	    }
		
		static {
	        for (EnumDiagonalArrow type: values()) {
	            META_LOOKUP[type.getMetadata()] = type;
	        }
	    }
	}
}