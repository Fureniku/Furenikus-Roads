package com.silvaniastudios.roads.blocks.decorative;

import javax.annotation.Nullable;

import com.silvaniastudios.roads.blocks.NonPaintRoadTopBlock;
import com.silvaniastudios.roads.blocks.enums.IMetaBlockName;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BollardBlock extends NonPaintRoadTopBlock implements IMetaBlockName {
	
	public static final PropertyBool ROTATED = PropertyBool.create("rotated");
	public static final PropertyEnum<EnumBollard> BOLLARD_TYPE = PropertyEnum.create("type", EnumBollard.class);

	public BollardBlock(String name) {
		super(name);
		this.setDefaultState(this.blockState.getBaseState().withProperty(ROTATED, false).withProperty(BOLLARD_TYPE, EnumBollard.ID_0));
		this.setHarvestLevel("pickaxe", 1);
		this.setLightLevel(12);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		int rot = 0;
		if (placer.getHorizontalFacing() == EnumFacing.EAST || placer.getHorizontalFacing() == EnumFacing.WEST)  { rot = 1; }
		return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta+rot, placer);
	}
	
	public int getMetaFromState(IBlockState state) {
		((EnumBollard)state.getValue(BOLLARD_TYPE)).getMeta();
		int rot = 0;
		if (state.getValue(ROTATED).equals(true)) {
			rot = 1;
		}
		return rot + ((EnumBollard)state.getValue(BOLLARD_TYPE)).getMeta();
	}
	
	@Override
	public String getSpecialName(ItemStack stack) {
		return stack.getItemDamage() + "";
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(CreativeTabs creativeTabs, NonNullList<ItemStack> list) {
		list.add(new ItemStack(this, 1, 0));
		list.add(new ItemStack(this, 1, 2));
		list.add(new ItemStack(this, 1, 4));
		list.add(new ItemStack(this, 1, 6));
	}
	
	@Override
    public int damageDropped(IBlockState state) {
    	int meta = getMetaFromState(state);
    	
    	
    	if (meta % 2 == 1) { return meta - 1; }
    	return meta;
    }
	
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return new AxisAlignedBB(0.25, -1+getBlockBelowHeight(worldIn, pos), 0.25, 0.75, -1+getBlockBelowHeight(worldIn, pos)+1, 0.75);
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
    	return new AxisAlignedBB(0.25, -1+getBlockBelowHeight(worldIn, pos), 0.25, 0.75, -1+getBlockBelowHeight(worldIn, pos)+1.5, 0.75);
    }
	
	public IBlockState getStateFromMeta(int meta) {
		boolean rot = false;
		if (meta % 2 == 1) {
			rot = true;
			meta--;
		}
		return this.getDefaultState().withProperty(ROTATED, rot).withProperty(BOLLARD_TYPE, EnumBollard.byId(meta/2));
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {ROTATED, BOLLARD_TYPE});
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void initModel() {
		for (int i = 0; i < 8; i++) {
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), i*2,  new ModelResourceLocation(getRegistryName(), "inventory_" + i));
		}
	}
	
	public static enum EnumBollard implements IStringSerializable {
    	ID_0(0, "0"),
		ID_1(2, "1"),
		ID_2(4, "2"),
		ID_3(6, "3"),
		ID_4(8, "4"),
		ID_5(10,"5"),
		ID_6(12,"6"),
		ID_7(14,"7");
    	
		private final String name;
		private final int meta;
		
		private EnumBollard(int meta, String name) {
			this.name = name;
			this.meta = meta;
		}
		
		public int getMeta() {
			return this.meta;
		}

		@Override
		public String getName() {
			return this.name;
		}
		
		public static EnumBollard byId(int id) {
	        if (id == 1) { return EnumBollard.ID_1; }
	        if (id == 2) { return EnumBollard.ID_2; }
	        if (id == 3) { return EnumBollard.ID_3; }
	        if (id == 4) { return EnumBollard.ID_4; }
	        if (id == 5) { return EnumBollard.ID_5; }
	        if (id == 6) { return EnumBollard.ID_6; }
	        if (id == 7) { return EnumBollard.ID_7; }
	        return EnumBollard.ID_0;
	    }
    }

}
