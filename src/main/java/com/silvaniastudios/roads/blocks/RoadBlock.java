package com.silvaniastudios.roads.blocks;

import com.silvaniastudios.roads.FlenixRoads;
import com.silvaniastudios.roads.blocks.enums.EnumRoadHeight;
import com.silvaniastudios.roads.blocks.enums.IMetaBlockName;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RoadBlock extends BlockBase implements IMetaBlockName {
	
	public static final PropertyEnum<EnumRoadHeight> ENUM_HEIGHT = PropertyEnum.create("road_block", EnumRoadHeight.class);
	
	public static final AxisAlignedBB ROAD_1_16_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D);
	public static final AxisAlignedBB ROAD_2_16_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);
	public static final AxisAlignedBB ROAD_3_16_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D);
	public static final AxisAlignedBB ROAD_4_16_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D);
	public static final AxisAlignedBB ROAD_5_16_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D);
	public static final AxisAlignedBB ROAD_6_16_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D);
	public static final AxisAlignedBB ROAD_7_16_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.4375D, 1.0D);
	public static final AxisAlignedBB ROAD_8_16_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
	public static final AxisAlignedBB ROAD_9_16_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5625D, 1.0D);
	public static final AxisAlignedBB ROAD_10_16_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D);
	public static final AxisAlignedBB ROAD_11_16_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.6875D, 1.0D);
	public static final AxisAlignedBB ROAD_12_16_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);
	public static final AxisAlignedBB ROAD_13_16_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.8125D, 1.0D);
	public static final AxisAlignedBB ROAD_14_16_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D);
	public static final AxisAlignedBB ROAD_15_16_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.9375D, 1.0D);
	public static final AxisAlignedBB ROAD_16_16_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	
	public RoadBlock(String name, Material mat) {
		super(name, mat);
		setDefaultState(this.blockState.getBaseState().withProperty(ENUM_HEIGHT, EnumRoadHeight.id0));
		this.setCreativeTab(FlenixRoads.tab_roads);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
        for (EnumRoadHeight height: EnumRoadHeight.values()) {
            items.add(new ItemStack(this, 1, height.getMetadata()));
        }
    }
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(state.getBlock(), 1, this.getMetaFromState(state));
    }
	
	@Override
	public String getSpecialName(ItemStack stack) {
		return stack.getItemDamage() + "";
	}

    @Override
    public int damageDropped(IBlockState state) {
    	return this.getMetaFromState(state);
    }
    
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {ENUM_HEIGHT});
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(ENUM_HEIGHT, EnumRoadHeight.byMetadata(meta));
    }
	
	@Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumRoadHeight)state.getValue(ENUM_HEIGHT)).getMetadata();
    }
	
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    	return getBoxFromState(state);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
    	return getBoxFromState(state);
    }
    
    public AxisAlignedBB getBoxFromState(IBlockState state) {
    	int meta = getMetaFromState(state);
    	if (meta == 0) { return ROAD_1_16_AABB; }
    	if (meta == 1) { return ROAD_2_16_AABB; }
    	if (meta == 2) { return ROAD_3_16_AABB; }
    	if (meta == 3) { return ROAD_4_16_AABB; }
    	if (meta == 4) { return ROAD_5_16_AABB; }
    	if (meta == 5) { return ROAD_6_16_AABB; }
    	if (meta == 6) { return ROAD_7_16_AABB; }
    	if (meta == 7) { return ROAD_8_16_AABB; }
    	if (meta == 8) { return ROAD_9_16_AABB; }
    	if (meta == 9) { return ROAD_10_16_AABB; }
    	if (meta == 10) { return ROAD_11_16_AABB; }
    	if (meta == 11) { return ROAD_12_16_AABB; }
    	if (meta == 12) { return ROAD_13_16_AABB; }
    	if (meta == 13) { return ROAD_14_16_AABB; }
    	if (meta == 14) { return ROAD_15_16_AABB; }
    	if (meta == 15) { return ROAD_16_16_AABB; }
        return ROAD_12_16_AABB;
    }
	
    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
    
	@Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
	
	//onblockclicked
	//tarmac cutter = lower block size, give tarmac fragment
	
	//onblockactivated
	//impact wrench = raise block size, take tarmac fragment
}
