package com.silvaniastudios.roads.blocks;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;
import com.silvaniastudios.roads.items.FRItems;
import com.silvaniastudios.roads.items.RoadItemBase;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RoadBlock extends BlockBase {
	
	public static final PropertyEnum<RoadBlock.EnumRoadHeight> ENUM_HEIGHT = PropertyEnum.create("road_block", RoadBlock.EnumRoadHeight.class);
	
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
	
	public RoadItemBase fragmentItem;
	
	public RoadBlock(String name, Material mat, RoadItemBase fragment) {
		super(name, mat);
		setDefaultState(this.blockState.getBaseState().withProperty(ENUM_HEIGHT, RoadBlock.EnumRoadHeight.id0));
		this.setCreativeTab(FurenikusRoads.tab_roads);
		this.fragmentItem = fragment;
		this.setHarvestLevel("pneumatic_drill", 0);
		this.setHardness(2.0F);
	}
	
	public RoadItemBase getFragmentItem(Block block) {
		if (block == FRBlocks.road_block_standard) { return FRItems.tarmac_fragment_standard; }
		if (block == FRBlocks.road_block_concrete_1) { return FRItems.tarmac_fragment_concrete_1; }
		if (block == FRBlocks.road_block_concrete_2) { return FRItems.tarmac_fragment_concrete_2; }
		if (block == FRBlocks.road_block_light) { return FRItems.tarmac_fragment_light; }
		if (block == FRBlocks.road_block_fine) { return FRItems.tarmac_fragment_fine; }
		if (block == FRBlocks.road_block_dark) { return FRItems.tarmac_fragment_dark; }
		if (block == FRBlocks.road_block_pale) { return FRItems.tarmac_fragment_pale; }
		if (block == FRBlocks.road_block_red) { return FRItems.tarmac_fragment_red; }
		if (block == FRBlocks.road_block_blue) { return FRItems.tarmac_fragment_blue; }
		if (block == FRBlocks.road_block_white) { return FRItems.tarmac_fragment_white; }
		if (block == FRBlocks.road_block_yellow) { return FRItems.tarmac_fragment_yellow; }
		if (block == FRBlocks.road_block_green) { return FRItems.tarmac_fragment_green; }
		if (block == FRBlocks.road_block_muddy) { return FRItems.tarmac_fragment_muddy; }
		if (block == FRBlocks.road_block_muddy_dried) { return FRItems.tarmac_fragment_muddy; }
		
		if (block == FRBlocks.road_block_stone) { return FRItems.tarmac_fragment_stone; }
		if (block == FRBlocks.road_block_grass) { return FRItems.tarmac_fragment_grass; }
		if (block == FRBlocks.road_block_dirt) { return FRItems.tarmac_fragment_dirt; }
		if (block == FRBlocks.road_block_gravel) { return FRItems.tarmac_fragment_gravel; }
		if (block == FRBlocks.road_block_sand) { return FRItems.tarmac_fragment_sand; }
		
		return FRItems.tarmac_fragment_standard;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
        for (RoadBlock.EnumRoadHeight height: RoadBlock.EnumRoadHeight.values()) {
            items.add(new ItemStack(this, 1, height.getMetadata()));
        }
    }
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		Item item = player.getHeldItem(hand).getItem();

		if (item.equals(FRItems.paint_scraper)) {
			IBlockState stateAbove = world.getBlockState(pos.offset(EnumFacing.UP));
			if (stateAbove.getBlock() instanceof PaintBlockBase) {
				world.setBlockToAir(pos.offset(EnumFacing.UP));
				return true;
			}
		}
		return super.onBlockActivated(world, pos, state, player, hand, facing, hitX, hitY, hitZ);
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(state.getBlock(), 1, this.getMetaFromState(state));
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
        return this.getDefaultState().withProperty(ENUM_HEIGHT, RoadBlock.EnumRoadHeight.byMetadata(meta));
    }
	
	@Override
    public int getMetaFromState(IBlockState state) {
        return ((RoadBlock.EnumRoadHeight)state.getValue(ENUM_HEIGHT)).getMetadata();
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
        return getMetaFromState(state) == 15;
    }
    
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return getMetaFromState(state) == 15;
    }
	
	public static enum EnumRoadHeight implements IStringSerializable {
		id0(0, "1_16th"),
		id1(1, "1_8th"),
		id2(2, "3_16ths"),
		id3(3, "quarter_block"),
		id4(4, "5_16ths"),
		id5(5, "6_16ths"),
		id6(6, "7_16ths"),
		id7(7, "half_block"),
		id8(8, "9_16ths"),
		id9(9, "10_16ths"),
		id10(10, "11_16ths"),
		id11(11, "three_quarter_block"),
		id12(12, "13_16ths"),
		id13(13, "14_16ths"),
		id14(14, "15_16ths"),
		id15(15, "full_block");
		
		private static final EnumRoadHeight[] META_LOOKUP = new EnumRoadHeight[values().length];
		private final int meta;
		private final String name;
		
		private EnumRoadHeight(int meta, String name) {
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
		
		public static EnumRoadHeight byMetadata(int meta) {
	        if (meta < 0 || meta >= META_LOOKUP.length) {
	            meta = 0;
	        }
	        
	        return META_LOOKUP[meta];
	    }
		
		static {
	        for (EnumRoadHeight type: values()) {
	            META_LOOKUP[type.getMetadata()] = type;
	        }
	    }
	}
}
