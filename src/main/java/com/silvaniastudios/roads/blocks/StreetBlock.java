package com.silvaniastudios.roads.blocks;

import com.silvaniastudios.roads.blocks.enums.EnumMeta;
import com.silvaniastudios.roads.blocks.enums.IMetaBlockName;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class StreetBlock extends BlockBase implements IMetaBlockName {
	
	public static final PropertyEnum<EnumMeta> META_ID = PropertyEnum.create("street_block", EnumMeta.class);

	public StreetBlock(String name) {
		super(name, Material.ROCK);
		setDefaultState(this.blockState.getBaseState().withProperty(META_ID, EnumMeta.id0));
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return stack.getItemDamage() + "";
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(CreativeTabs creativeTabs, NonNullList<ItemStack> list) {
		for (int meta = 0; meta < 16; ++meta) {
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
