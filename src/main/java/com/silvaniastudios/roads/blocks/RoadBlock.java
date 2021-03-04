package com.silvaniastudios.roads.blocks;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.enums.EnumMeta;
import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;
import com.silvaniastudios.roads.items.FRItems;
import com.silvaniastudios.roads.items.RoadItemBase;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
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
	
	public static final PropertyEnum<EnumMeta> ENUM_HEIGHT = PropertyEnum.create("road_block", EnumMeta.class);
	
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
	
	private RoadItemBase fragmentItem;
	
	public RoadBlock(String name, Material mat, RoadItemBase fragment) {
		super(name, mat);
		setDefaultState(this.blockState.getBaseState().withProperty(ENUM_HEIGHT, EnumMeta.id0));
		this.setCreativeTab(FurenikusRoads.tab_roads);
		this.fragmentItem = fragment;
		this.setHarvestLevel("pneumatic_drill", 0);
		this.setHardness(1.0F);
	}
	
	public RoadItemBase getFragmentItem() {
		return this.fragmentItem;
	}
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		IBlockState stateAbove = worldIn.getBlockState(pos.offset(EnumFacing.UP));
		
		if (stateAbove.getBlock() instanceof BlockSnow && !(stateAbove.getBlock() instanceof BlockRoadSnow)) {
			if (RoadsConfig.general.snowOnRoads) {
				worldIn.setBlockState(pos.offset(EnumFacing.UP), FRBlocks.road_snow.getDefaultState(), 3);
			} else {
				worldIn.setBlockToAir(pos.offset(EnumFacing.UP));
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
        for (EnumMeta height : EnumMeta.values()) {
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
        return this.getDefaultState().withProperty(ENUM_HEIGHT, EnumMeta.byMetadata(meta));
    }
	
	@Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumMeta)state.getValue(ENUM_HEIGHT)).getMetadata();
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
        return this.getMetaFromState(state) == 15;
    }
    
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return this.getMetaFromState(state) == 15;
    }
    
    //Checks on full-size blocks
    public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
    	return checkSideRendering(state, world, pos, face);
    }
    
    //Checks on non-full-size blocks
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		return checkSideRendering(state, world, pos, face);
    }
    
    private boolean checkSideRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
    	IBlockState offsetState = world.getBlockState(pos.offset(face));
    	
    	if (face == EnumFacing.UP && this.getMetaFromState(state) < 15) {
    		return true; //There's no reason for non-full height road blocks to ever cull.
    	}
    	
    	//I have to check this because apparently other mods aren't setting their block face shape properly, annoying and worse for performance but
    	//nothing more I can do. Go shout at other mods to set their block face shapes (if it's not a full, solid face, it shouldn't be set to solid!!).
    	//I am only doing this on the top face. If you're getting culling issues with other sides either tell me and I'll shout at the dev, or go show them this comment.
    	// :)
    	if (face == EnumFacing.UP && (!offsetState.isFullCube() || !offsetState.isOpaqueCube()) && !offsetState.getBlock().isAir(offsetState, world, pos.offset(face))) {
    		return false;
    	}
    	
    	
    	if (face == EnumFacing.DOWN && offsetState.getBlock() instanceof RoadBlock && offsetState.getBlock().getMetaFromState(offsetState) < 15) {
    		return true;
    	}

		if (offsetState.getBlock() instanceof RoadBlock && offsetState.getBlock().getMetaFromState(offsetState) >= this.getMetaFromState(state)) {
			return false;
		}
		
		if (offsetState.getBlockFaceShape(world, pos.offset(face), face.getOpposite()) == BlockFaceShape.SOLID) {
			return false;
		}
		
		return true;
	}
    
    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
    	if (this.getMetaFromState(state) < 15 && face != EnumFacing.DOWN && face != EnumFacing.UP) {
    		return BlockFaceShape.UNDEFINED;
    	}
    	
        return BlockFaceShape.SOLID;
    }
}
