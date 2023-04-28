package com.silvaniastudios.roads.blocks.paint;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.PaintColour;
import com.silvaniastudios.roads.blocks.paint.properties.UnlistedPropertyConnection;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;

public class LinePaintBlock extends PaintBlockCustomRenderBase {
	
	public static final UnlistedPropertyConnection NORTH = new UnlistedPropertyConnection("north");
	public static final UnlistedPropertyConnection EAST = new UnlistedPropertyConnection("east");
	public static final UnlistedPropertyConnection SOUTH = new UnlistedPropertyConnection("south");
	public static final UnlistedPropertyConnection WEST = new UnlistedPropertyConnection("west");
	public static final PropertyEnum<LinePaintBlock.EnumRotation> FACING = PropertyEnum.create("facing", LinePaintBlock.EnumRotation.class);

	public LinePaintBlock(String name, String category, PaintColour colour) {
		super(name, category, new int[] { 0, 2 }, true, colour);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, LinePaintBlock.EnumRotation.ns));
		this.setCreativeTab(FurenikusRoads.tab_paint_lines);
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		if (meta < 2) {
			if (placer.getHorizontalFacing() == EnumFacing.EAST || placer.getHorizontalFacing() == EnumFacing.WEST) {
				return this.getDefaultState().withProperty(FACING, LinePaintBlock.EnumRotation.ew);
			}
			return this.getDefaultState().withProperty(FACING, LinePaintBlock.EnumRotation.ns);
		}
		return this.getDefaultState().withProperty(FACING, LinePaintBlock.EnumRotation.connect);
	}

	public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos, EnumFacing facing) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();
		return block instanceof LinePaintBlock || block instanceof ArrowLinePaintBlock;
	}

	private boolean canLineConnectTo(IBlockAccess world, BlockPos pos, EnumFacing facing) {
		BlockPos offset = pos.offset(facing);
		return canConnectTo(world, offset, facing.getOpposite()) || canConnectTo(world, offset.offset(EnumFacing.DOWN), facing.getOpposite()) || canConnectTo(world, offset.offset(EnumFacing.UP), facing.getOpposite());
	}
	
	public int getMetaFromState(IBlockState state) {
		if (state.getValue(FACING).equals(LinePaintBlock.EnumRotation.ns)) {
			return 0;
		} else if (state.getValue(FACING).equals(LinePaintBlock.EnumRotation.ew)) {
			return 1;
		} else {
			return 2;
		}
	}
	
	public IBlockState getStateFromMeta(int meta) {
		if (meta == 0) { return this.getDefaultState().withProperty(FACING, LinePaintBlock.EnumRotation.ns); }
		if (meta == 1) { return this.getDefaultState().withProperty(FACING, LinePaintBlock.EnumRotation.ew); }
		return this.getDefaultState().withProperty(FACING, LinePaintBlock.EnumRotation.connect);
	}
	
	@SuppressWarnings("rawtypes")
	protected BlockStateContainer createBlockState() {
		IProperty[] listedProperties = new IProperty[] { FACING };
		IUnlistedProperty[] unlistedProperties = new IUnlistedProperty[] { NORTH, EAST, WEST, SOUTH };

		return new ExtendedBlockState(this, listedProperties, unlistedProperties);
	}
	
	@Override
	public IBlockState getExtendedState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		IExtendedBlockState extendedBlockState = (IExtendedBlockState) state;
		boolean n = canLineConnectTo(worldIn, pos, EnumFacing.NORTH);
		boolean e = canLineConnectTo(worldIn, pos, EnumFacing.EAST);
		boolean s = canLineConnectTo(worldIn, pos, EnumFacing.SOUTH);
		boolean w = canLineConnectTo(worldIn, pos, EnumFacing.WEST);
		
		if (state.getValue(FACING) == EnumRotation.ns) {
			n = true;
			s = true;
		}
		
		if (state.getValue(FACING) == EnumRotation.ew) {
			e = true;
			w = true;
		}

		return extendedBlockState.withProperty(NORTH, n).withProperty(EAST, e).withProperty(SOUTH, s).withProperty(WEST, w);
	}
    
    public enum EnumRotation implements IStringSerializable {
    	ns("north_south"),
		ew("east_west"),
		connect("connect");
    	
		private final String name;
		
		EnumRotation(String name) {
			this.name = name;
		}

		@Override
		public String getName() {
			return this.name;
		}
		
		public static EnumRotation byMetadata(int meta) {
	        if (meta == 0) { return EnumRotation.ns; }
	        if (meta == 1) { return EnumRotation.ew; }
	        return EnumRotation.connect;
	    }
    }
}
