package com.silvaniastudios.roads.blocks;

import com.silvaniastudios.roads.blocks.enums.ILineConnectable;
import com.silvaniastudios.roads.blocks.enums.IMetaBlockName;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class LinePaintBlock extends PaintBlockBase implements ILineConnectable, IMetaBlockName {
	
	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool EAST = PropertyBool.create("east");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool WEST = PropertyBool.create("west");
	public static final PropertyBool DEFAULTS = PropertyBool.create("zz_default_stuff");
	public static final PropertyBool FACING_NORTH_SOUTH = PropertyBool.create("facing_ns");
	public static final PropertyBool FACING_EAST_WEST = PropertyBool.create("facing_ew");

	public LinePaintBlock(String name) {
		super(name);
		this.setDefaultState(this.blockState.getBaseState()
				.withProperty(NORTH, false)
				.withProperty(EAST, false)
				.withProperty(SOUTH, false)
				.withProperty(WEST, false)
				.withProperty(DEFAULTS, true)
				.withProperty(FACING_NORTH_SOUTH, false)
				.withProperty(FACING_EAST_WEST, false));
	}
	
	@Override
	public String getSpecialName(ItemStack stack) {
		return stack.getItemDamage() + "";
	}
	

	/*@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (isOnRoadBlock(world, pos)) {
			world.setBlockToAir(pos);
		}
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		return isOnRoadBlock(world, pos);
	}
	
	public boolean isOnRoadBlock(World world, BlockPos pos) {
		if (world.getBlockState(new BlockPos(pos.getX(), pos.getY()-1, pos.getZ())).getBlock() instanceof RoadBlock) {
			return true;
		}
		return false;
	}*/
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		if (meta < 2) {
			if (placer.getHorizontalFacing() == EnumFacing.EAST || placer.getHorizontalFacing() == EnumFacing.WEST) {
				return this.getDefaultState().withProperty(FACING_EAST_WEST, true).withProperty(FACING_NORTH_SOUTH, false);
			}
			return this.getDefaultState().withProperty(FACING_NORTH_SOUTH, true).withProperty(FACING_EAST_WEST, false);
		}
		return this.getDefaultState().withProperty(FACING_NORTH_SOUTH, false).withProperty(FACING_EAST_WEST, false);
	}

	public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos, EnumFacing facing) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();
		return block instanceof ILineConnectable;
	}

	private boolean canLineConnectTo(IBlockAccess world, BlockPos pos, EnumFacing facing) {
		BlockPos offset = pos.offset(facing);
		return canConnectTo(world, offset, facing.getOpposite()) || canConnectTo(world, offset.offset(EnumFacing.DOWN), facing.getOpposite()) || canConnectTo(world, offset.offset(EnumFacing.UP), facing.getOpposite());
	}
	
	public int getMetaFromState(IBlockState state) {
		if (state.getValue(FACING_NORTH_SOUTH) && !state.getValue(FACING_EAST_WEST)) {
			return 0;
		} else if (state.getValue(FACING_EAST_WEST) && !state.getValue(FACING_NORTH_SOUTH)) {
			return 1;
		} else {
			return 2;
		}
	}
	
	public IBlockState getStateFromMeta(int meta) {
		if (meta == 0) { return this.getDefaultState().withProperty(FACING_NORTH_SOUTH, true); }
		if (meta == 1) { return this.getDefaultState().withProperty(FACING_EAST_WEST, true); }
		if (meta == 2) { return this.getDefaultState().withProperty(FACING_NORTH_SOUTH, false).withProperty(FACING_EAST_WEST, false); }
		return this.getDefaultState().withProperty(FACING_NORTH_SOUTH, false).withProperty(FACING_EAST_WEST, false);
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {NORTH, EAST, WEST, SOUTH, DEFAULTS, FACING_NORTH_SOUTH, FACING_EAST_WEST});
	}

	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(NORTH, canLineConnectTo(worldIn, pos, EnumFacing.NORTH))
			.withProperty(EAST,  canLineConnectTo(worldIn, pos, EnumFacing.EAST))
			.withProperty(SOUTH, canLineConnectTo(worldIn, pos, EnumFacing.SOUTH))
			.withProperty(WEST,  canLineConnectTo(worldIn, pos, EnumFacing.WEST));
	}
    
    @SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
    	items.add(new ItemStack(this, 1, 0));
    	items.add(new ItemStack(this, 1, 2));
    }
}
