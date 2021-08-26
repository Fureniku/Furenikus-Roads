package com.silvaniastudios.roads.blocks.paint;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.PaintColour;
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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ArrowPaintBlock extends PaintBlockBase implements IMetaBlockName {
	
	public static final PropertyEnum<EnumMeta> META_ID = PropertyEnum.create("meta", EnumMeta.class);
	//Meta 0-3 normal arrow, 4-7 side arrow, 8-11 vertical left, 12-15 vertical right.

	public ArrowPaintBlock(String name, String category, int[] coreMetas, boolean dynamic, PaintColour colour) {
		super(name, category, coreMetas, dynamic, colour);
		setDefaultState(this.blockState.getBaseState().withProperty(META_ID, EnumMeta.id0));
		this.setCreativeTab(FurenikusRoads.tab_paint_icons);
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return stack.getItemDamage() + "";
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		if (meta < 4) {
			if (placer.getHorizontalFacing() == EnumFacing.NORTH) { return this.getDefaultState().withProperty(META_ID, EnumMeta.id0); }
			if (placer.getHorizontalFacing() == EnumFacing.EAST)  { return this.getDefaultState().withProperty(META_ID, EnumMeta.id1); }
			if (placer.getHorizontalFacing() == EnumFacing.SOUTH) { return this.getDefaultState().withProperty(META_ID, EnumMeta.id2); }
			if (placer.getHorizontalFacing() == EnumFacing.WEST)  { return this.getDefaultState().withProperty(META_ID, EnumMeta.id3); }
		} else if (facing == EnumFacing.UP) {
			if (placer.getHorizontalFacing() == EnumFacing.NORTH) { return this.getDefaultState().withProperty(META_ID, EnumMeta.id4); }
			if (placer.getHorizontalFacing() == EnumFacing.EAST)  { return this.getDefaultState().withProperty(META_ID, EnumMeta.id5); }
			if (placer.getHorizontalFacing() == EnumFacing.SOUTH) { return this.getDefaultState().withProperty(META_ID, EnumMeta.id6); }
			if (placer.getHorizontalFacing() == EnumFacing.WEST)  { return this.getDefaultState().withProperty(META_ID, EnumMeta.id7); }
		} else {
			if (placer.getHorizontalFacing() == EnumFacing.NORTH) { 
				if (facing == EnumFacing.EAST) { return this.getDefaultState().withProperty(META_ID, EnumMeta.id13); }
				if (facing == EnumFacing.WEST) { return this.getDefaultState().withProperty(META_ID, EnumMeta.id11); }
			}
			if (placer.getHorizontalFacing() == EnumFacing.EAST) {
				if (facing == EnumFacing.NORTH) { return this.getDefaultState().withProperty(META_ID, EnumMeta.id9);  }
				if (facing == EnumFacing.SOUTH) { return this.getDefaultState().withProperty(META_ID, EnumMeta.id14); }
			}
			if (placer.getHorizontalFacing() == EnumFacing.SOUTH) {
				if (facing == EnumFacing.EAST) { return this.getDefaultState().withProperty(META_ID, EnumMeta.id10); }
				if (facing == EnumFacing.WEST) { return this.getDefaultState().withProperty(META_ID, EnumMeta.id12); }
			}
			if (placer.getHorizontalFacing() == EnumFacing.WEST) {
				if (facing == EnumFacing.NORTH) { return this.getDefaultState().withProperty(META_ID, EnumMeta.id8);  }
				if (facing == EnumFacing.SOUTH) { return this.getDefaultState().withProperty(META_ID, EnumMeta.id15); }
			}
		}
		
		return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
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
    }
	
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		int meta = getMetaFromState(state);
		if (meta < 8) {
			return new AxisAlignedBB(0.0D, -1+getBlockBelowHeight(source, pos), 0.0D, 1.0D, -1+getBlockBelowHeight(source, pos)+0.0625, 1.0D);
		}
		if (meta == 8  || meta == 12) { return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.0625D); }
		if (meta == 9  || meta == 13) { return new AxisAlignedBB(0.9375D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D); }
		if (meta == 10 || meta == 14) { return new AxisAlignedBB(0.0D, 0.0D, 0.9375D, 1.0D, 1.0D, 1.0D); }
		if (meta == 11 || meta == 15) { return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0625D, 1.0D, 1.0D); }
		
		return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0625, 1.0D);
    }

    @Override
    public Vec3d getOffset(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
    	int meta = getMetaFromState(state);
    	double offset = 0.0;
    	if (meta < 8) {
    		offset = 1.0 - getBlockBelowHeight(worldIn, pos);
    	}
        return new Vec3d(0, -offset, 0);
    }
    
    @SideOnly(Side.CLIENT)
	@Override
	public void initModel() {
    	ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    	ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 4, new ModelResourceLocation(getRegistryName(), "inventory_2"));
	}
}