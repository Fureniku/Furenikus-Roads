package com.silvaniastudios.roads.blocks.paint;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.enums.EnumMeta;
import com.silvaniastudios.roads.blocks.enums.IMetaBlockName;

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

public class MultiIconPaintBlock extends PaintBlockBase implements IMetaBlockName {
	
	public static final PropertyEnum<EnumMeta> META_ID = PropertyEnum.create("meta", EnumMeta.class);
	//Meta 0-3 normal arrow, 4-7 side arrow, 8-11 vertical left, 12-15 vertical right.

	boolean multiType = true;
	
	public MultiIconPaintBlock(String name, boolean multiType, String category, int[] coreMetas, boolean dynamic, String colour) {
		super(name, category, coreMetas, dynamic, colour);
		this.multiType = multiType;
		setDefaultState(this.blockState.getBaseState().withProperty(META_ID, EnumMeta.id0));
		this.setCreativeTab(FurenikusRoads.tab_paint_junction);
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
		
		if (multiType) {
			IBlockState northBlock = world.getBlockState(pos.offset(EnumFacing.NORTH));
			IBlockState eastBlock =  world.getBlockState(pos.offset(EnumFacing.EAST));
			IBlockState southBlock = world.getBlockState(pos.offset(EnumFacing.SOUTH));
			IBlockState westBlock =  world.getBlockState(pos.offset(EnumFacing.WEST));

			Block leftBlock = westBlock.getBlock();
			Block rightBlock = eastBlock.getBlock();
			int rot = 0;
			
			boolean checkLeft = false;
			boolean checkRight = false;
			
			if (this.getUnlocalizedName().contains("_chevron_mid")) { checkLeft = true; checkRight = true; }
			if (this.getUnlocalizedName().contains("_chevron_mid_left")) { checkLeft = true; }
			if (this.getUnlocalizedName().contains("_chevron_mid_right")) { checkRight = true; }
			
			if (placer.getHorizontalFacing().equals(EnumFacing.EAST)) {
				leftBlock = northBlock.getBlock();
				rightBlock = southBlock.getBlock();
				rot = 1;
			}
			
			if (placer.getHorizontalFacing().equals(EnumFacing.SOUTH)) {
				leftBlock = eastBlock.getBlock();
				rightBlock = westBlock.getBlock();
				rot = 2;
			}
			
			if (placer.getHorizontalFacing().equals(EnumFacing.WEST)) {
				leftBlock = southBlock.getBlock();
				rightBlock = northBlock.getBlock();
				rot = 3;
			}
			
			if (leftBlock.getUnlocalizedName().contains("chevron_left_a") && checkLeft) { placedMeta = 0 + rot; }
			if (leftBlock.getUnlocalizedName().contains("chevron_left_b") && checkLeft) { placedMeta = 4 + rot; }
			if (leftBlock.getUnlocalizedName().contains("chevron_left_a_thin") && checkLeft) { placedMeta = 8 + rot; }
			if (leftBlock.getUnlocalizedName().contains("chevron_left_b_thin") && checkLeft) { placedMeta = 12 + rot; }
			
			if (rightBlock.getUnlocalizedName().contains("chevron_right_a") && checkRight) { placedMeta = 0 + rot; }
			if (rightBlock.getUnlocalizedName().contains("chevron_right_b") && checkRight) { placedMeta = 4 + rot; }
			if (rightBlock.getUnlocalizedName().contains("chevron_right_a_thin") && checkRight) { placedMeta = 8 + rot; }
			if (rightBlock.getUnlocalizedName().contains("chevron_right_b_thin") && checkRight) { placedMeta = 12 + rot; }
		}
		
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
    	if (multiType) {
    		items.add(new ItemStack(this, 1, 4));
    		items.add(new ItemStack(this, 1, 8));
    		items.add(new ItemStack(this, 1, 12));
    	}
    }
    
    @SideOnly(Side.CLIENT)
	@Override
	public void initModel() {
    	ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    	if (multiType) {
	    	ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 4, new ModelResourceLocation(getRegistryName(), "inventory_2"));
	    	ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 8, new ModelResourceLocation(getRegistryName(), "inventory_3"));
	    	ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 12, new ModelResourceLocation(getRegistryName(), "inventory_4"));
    	}
	}
}