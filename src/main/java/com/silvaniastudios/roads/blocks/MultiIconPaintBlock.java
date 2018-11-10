package com.silvaniastudios.roads.blocks;

import com.silvaniastudios.roads.blocks.enums.EnumMeta;
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
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MultiIconPaintBlock extends PaintBlockBase implements IMetaBlockName {
	
	public static final PropertyEnum<EnumMeta> META_ID = PropertyEnum.create("meta", EnumMeta.class);
	//Meta 0-3 normal arrow, 4-7 side arrow, 8-11 vertical left, 12-15 vertical right.

	public MultiIconPaintBlock(String name) {
		super(name);
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
		
		return this.getDefaultState().withProperty(META_ID, EnumMeta.byMetadata(placedMeta));
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
	
    @SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
    	items.add(new ItemStack(this, 1, 0));
    	items.add(new ItemStack(this, 1, 4));
    	items.add(new ItemStack(this, 1, 8));
    	items.add(new ItemStack(this, 1, 12));
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