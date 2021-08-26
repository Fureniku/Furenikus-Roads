package com.silvaniastudios.roads.blocks.paint;

import com.silvaniastudios.roads.blocks.PaintColour;
import com.silvaniastudios.roads.blocks.enums.EnumRotatable;
import com.silvaniastudios.roads.blocks.enums.IMetaBlockName;

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
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WallPaintBlock extends PaintBlockBase implements IMetaBlockName {
	
	public static final PropertyEnum<EnumRotatable> ROTATE_ID = PropertyEnum.create("wall_paint", EnumRotatable.class);
	
	public WallPaintBlock(String name, String category, int[] coreMetas, boolean dynamic, PaintColour colour) {
		super(name, category, coreMetas, dynamic, colour);
		setDefaultState(this.blockState.getBaseState().withProperty(ROTATE_ID, EnumRotatable.FLAT_NORTH));
	}
	
	@Override
	public String getSpecialName(ItemStack stack) {
		return stack.getItemDamage() + "";
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		if (meta < 8) {
			if (facing == EnumFacing.UP) {
				if (placer.getHorizontalFacing() == EnumFacing.NORTH) { return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.FLAT_NORTH); }
				if (placer.getHorizontalFacing() == EnumFacing.EAST) { return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.FLAT_EAST); }
				if (placer.getHorizontalFacing() == EnumFacing.SOUTH) { return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.FLAT_SOUTH); }
				if (placer.getHorizontalFacing() == EnumFacing.WEST) { return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.FLAT_WEST); }
			}
			if (placer.getHorizontalFacing() == EnumFacing.NORTH) { return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.FACE_NORTH); }
			if (placer.getHorizontalFacing() == EnumFacing.EAST) { return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.FACE_EAST); }
			if (placer.getHorizontalFacing() == EnumFacing.SOUTH) { return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.FACE_SOUTH); }
			if (placer.getHorizontalFacing() == EnumFacing.WEST) { return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.FACE_WEST); }
		} else {
			if (facing == EnumFacing.UP) {
				if (placer.getHorizontalFacing() == EnumFacing.NORTH) { return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.FLAT_NORTH_2); }
				if (placer.getHorizontalFacing() == EnumFacing.EAST) { return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.FLAT_EAST_2); }
				if (placer.getHorizontalFacing() == EnumFacing.SOUTH) { return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.FLAT_SOUTH_2); }
				if (placer.getHorizontalFacing() == EnumFacing.WEST) { return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.FLAT_WEST_2); }
			}
			if (placer.getHorizontalFacing() == EnumFacing.NORTH) { return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.FACE_NORTH_2); }
			if (placer.getHorizontalFacing() == EnumFacing.EAST) { return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.FACE_EAST_2); }
			if (placer.getHorizontalFacing() == EnumFacing.SOUTH) { return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.FACE_SOUTH_2); }
			if (placer.getHorizontalFacing() == EnumFacing.WEST) { return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.FACE_WEST_2); }
		}
		
		return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
	}
    
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {ROTATE_ID});
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.byMetadata(meta));
    }
	
	@Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumRotatable)state.getValue(ROTATE_ID)).getMetadata();
    }
	
    @SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
    	items.add(new ItemStack(this, 1, 0));
    	items.add(new ItemStack(this, 1, 8));
    }
    
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    	int meta = getMetaFromState(state);
    	
    	if (meta < 8) { return new ItemStack(this, 1, 0); }
    	return new ItemStack(this, 1, 8);
    }
	
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		int meta = getMetaFromState(state);
		if (meta < 4 || (meta > 7 && meta < 12)) {
			return new AxisAlignedBB(0.0D, -1+getBlockBelowHeight(source, pos), 0.0D, 1.0D, -1+getBlockBelowHeight(source, pos)+0.0625, 1.0D);
		}
		if (meta == 4 || meta == 12) { return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.0625D); }
		if (meta == 5 || meta == 13) { return new AxisAlignedBB(0.9375D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D); }
		if (meta == 6 || meta == 14) { return new AxisAlignedBB(0.0D, 0.0D, 0.9375D, 1.0D, 1.0D, 1.0D); }
		if (meta == 7 || meta == 15) { return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0625D, 1.0D, 1.0D); }
		
		return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0625, 1.0D);
    }

    @Override
    public Vec3d getOffset(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
    	int meta = getMetaFromState(state);
    	double offset = 0.0;
    	if (meta < 4 || (meta > 7 && meta < 12)) {
    		offset = 1.0 - getBlockBelowHeight(worldIn, pos);
    	}
        return new Vec3d(0, -offset, 0);
    }
    
    @SideOnly(Side.CLIENT)
	@Override
	public void initModel() {
    	ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    	ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 8, new ModelResourceLocation(getRegistryName(), "inventory_2"));
	}
}
