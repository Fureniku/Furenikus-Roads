package com.silvaniastudios.roads.blocks;

import java.util.Random;

import com.silvaniastudios.roads.blocks.enums.EnumMeta;
import com.silvaniastudios.roads.blocks.enums.IMetaBlockName;
import com.silvaniastudios.roads.items.FRItems;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class StreetBlock extends BlockBase implements IMetaBlockName {
	
	public static final PropertyEnum<EnumMeta> META_ID = PropertyEnum.create("street_block", EnumMeta.class);
	int max_meta = 0;
	
	public StreetBlock(String name, int max_meta) {
		super(name, Material.ROCK);
		this.setHardness(2.0F);
		this.max_meta = max_meta;
		setDefaultState(this.blockState.getBaseState().withProperty(META_ID, EnumMeta.id0));
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return stack.getItemDamage() + "";
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		int meta = getMetaFromState(state);
		if (this == FRBlocks.generic_blocks && meta == 3) { return FRItems.limestone_dust; }
		
		return super.getItemDropped(state, rand, fortune);
	}
	
	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) {
		int meta = getMetaFromState(state);
		if (this == FRBlocks.generic_blocks && meta == 3) { return 4; }
        return quantityDroppedWithBonus(fortune, random);
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(CreativeTabs creativeTabs, NonNullList<ItemStack> list) {
		for (int meta = 0; meta < max_meta; ++meta) {
			list.add(new ItemStack(this, 1, meta));
		}
	}
	
    @Override
    public int damageDropped(IBlockState state) {
    	return this.getMetaFromState(state);
    }
    
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {META_ID});
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(META_ID, EnumMeta.byMetadata(meta));
    }
	
	@Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumMeta)state.getValue(META_ID)).getMetadata();
    }

}
