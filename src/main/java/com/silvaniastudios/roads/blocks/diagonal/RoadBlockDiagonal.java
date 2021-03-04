package com.silvaniastudios.roads.blocks.diagonal;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.BlockBase;
import com.silvaniastudios.roads.blocks.FRBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RoadBlockDiagonal extends BlockBase {

	public static final PropertyEnum<EnumFacing> FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	public static final UnlistedPropertySideBlock LEFT = new UnlistedPropertySideBlock("left");
	public static final UnlistedPropertySideBlock RIGHT = new UnlistedPropertySideBlock("right");
	public static final UnlistedPropertyBlockPos POS = new UnlistedPropertyBlockPos("pos");

	private String modelName;

	boolean mirror;

	public RoadBlockDiagonal(String name, boolean mirror, String modelName) {
		super(name, Material.ROCK);
		setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.mirror = mirror;
		this.setCreativeTab(FurenikusRoads.tab_diagonals);
		this.setHarvestLevel("pneumatic_drill", 0);
		this.setHardness(1.0F);
		this.modelName = modelName;
	}

	@SuppressWarnings("deprecation")
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		if (placer.getHorizontalFacing() == EnumFacing.NORTH) { return this.getDefaultState().withProperty(FACING, EnumFacing.NORTH); }
		if (placer.getHorizontalFacing() == EnumFacing.EAST)  { return this.getDefaultState().withProperty(FACING, EnumFacing.EAST); }
		if (placer.getHorizontalFacing() == EnumFacing.SOUTH) { return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH); }
		if (placer.getHorizontalFacing() == EnumFacing.WEST)  { return this.getDefaultState().withProperty(FACING, EnumFacing.WEST); }
		return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer);
	}

	public int getMetaFromState(IBlockState state) {
		if (state.getValue(FACING).equals(EnumFacing.NORTH)) {
			return 0;
		} else if (state.getValue(FACING).equals(EnumFacing.EAST)) {
			return 1;
		} else if (state.getValue(FACING).equals(EnumFacing.SOUTH)) {
			return 2;
		} else if (state.getValue(FACING).equals(EnumFacing.WEST)) {
			return 3;
		}

		return 0;
	}

	public IBlockState getStateFromMeta(int meta) {
		if (meta == 0) { return this.getDefaultState().withProperty(FACING, EnumFacing.NORTH); }
		if (meta == 1) { return this.getDefaultState().withProperty(FACING, EnumFacing.EAST);  }
		if (meta == 2) { return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH); }
		return this.getDefaultState().withProperty(FACING, EnumFacing.WEST); 	
	}

	protected BlockStateContainer createBlockState() {
		IProperty[] listedProperties = new IProperty[] {FACING};
		IUnlistedProperty[] unlistedProperties = new IUnlistedProperty[] {LEFT, RIGHT, POS};

		return new ExtendedBlockState(this, listedProperties, unlistedProperties);
	}

	@Override
	public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
		IExtendedBlockState extendedBlockState = (IExtendedBlockState) state;
		IBlockState l = getLeftBlock(state, world, pos);
		IBlockState r = getRightBlock(state, world, pos);

		return extendedBlockState.withProperty(LEFT, l).withProperty(RIGHT, r).withProperty(POS, pos);
	}

	public IBlockState getLeftBlock(IBlockState state, IBlockAccess world, BlockPos pos) {
		EnumFacing facingLeft;

		if (state.getValue(FACING).equals(EnumFacing.NORTH)) {
			facingLeft = EnumFacing.WEST;
		} else if (state.getValue(FACING).equals(EnumFacing.EAST)) {
			facingLeft = EnumFacing.NORTH;
		} else if (state.getValue(FACING).equals(EnumFacing.SOUTH)) {
			facingLeft = EnumFacing.EAST;
		} else {
			facingLeft = EnumFacing.SOUTH;
		}

		IBlockState stateLeft = world.getBlockState(pos.offset(facingLeft));

		return stateLeft;
	}

	public IBlockState getRightBlock(IBlockState state, IBlockAccess world, BlockPos pos) {
		EnumFacing facingRight;

		if (state.getValue(FACING).equals(EnumFacing.NORTH)) {
			facingRight = EnumFacing.EAST;
		} else if (state.getValue(FACING).equals(EnumFacing.EAST)) {
			facingRight = EnumFacing.SOUTH;
		} else if (state.getValue(FACING).equals(EnumFacing.SOUTH)) {
			facingRight = EnumFacing.WEST;
		} else {
			facingRight = EnumFacing.NORTH;
		}

		IBlockState stateRight = world.getBlockState(pos.offset(facingRight));

		return stateRight;
	}

	@SuppressWarnings("deprecation")
	public double getBlockHeight(IBlockAccess world, IBlockState state, BlockPos pos) {
		Block block = state.getBlock();
		return block.getBoundingBox(state, world, pos).maxY;
	}

	public IBlockState getRoad(IBlockAccess world, BlockPos pos) {
		return getBlockSide(world, pos, this.mirror);
	}

	public BlockPos getRoadPos(IBlockAccess world, BlockPos pos) {
		return getSidePos(pos, world.getBlockState(pos), this.mirror);
	}

	public IBlockState getSidewalk(IBlockAccess world, BlockPos pos, EnumFacing facing) {
		return getBlockSide(world, pos, !this.mirror);
	}

	public IBlockState getBlockSide(IBlockAccess world, BlockPos pos, boolean left) {
		IBlockState thisState = world.getBlockState(pos);
		return world.getBlockState(getSidePos(pos, thisState, left));
	}

	//pass true to get the pos on the left, false to get the pos on the right
	public BlockPos getSidePos(BlockPos pos, IBlockState state, boolean left) {
		EnumFacing facing = state.getValue(FACING);
		if (facing == EnumFacing.NORTH) {
			return left ? pos.offset(EnumFacing.WEST) : pos.offset(EnumFacing.EAST);
		} else if (facing == EnumFacing.EAST) {
			return left ? pos.offset(EnumFacing.SOUTH) : pos.offset(EnumFacing.NORTH);
		} else if (facing == EnumFacing.SOUTH) {
			return left ? pos.offset(EnumFacing.EAST) : pos.offset(EnumFacing.WEST);
		} else {
			return left ? pos.offset(EnumFacing.NORTH) : pos.offset(EnumFacing.SOUTH);
		}
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		StateMapperBase b = new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation(FurenikusRoads.MODID + ":" + modelName);
			}
		};

		ModelLoader.setCustomStateMapper(this, b);
	}

	@SideOnly(Side.CLIENT)
	public void initItemModel() {
		Item itemBlock = Item.REGISTRY.getObject(new ResourceLocation(FurenikusRoads.MODID, this.name));
		ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(getRegistryName(), "inventory");
		final int DEFAULT_ITEM_SUBTYPE = 0;
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemBlock, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		//if (this.getMetaFromState(state) < 15 && face != EnumFacing.DOWN && face != EnumFacing.UP) { //TODO checks for the sake of other blocks culling.
		return BlockFaceShape.UNDEFINED;
	}
}
