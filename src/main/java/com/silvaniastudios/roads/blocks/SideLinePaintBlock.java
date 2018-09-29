package com.silvaniastudios.roads.blocks;

import com.silvaniastudios.roads.blocks.enums.EnumConnectDiagonal;
import com.silvaniastudios.roads.blocks.enums.EnumConnectDirectional;
import com.silvaniastudios.roads.blocks.enums.ILineConnectable;
import com.silvaniastudios.roads.blocks.enums.IMetaBlockName;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class SideLinePaintBlock extends PaintBlockBase implements ILineConnectable, IMetaBlockName {
	
	public static final PropertyEnum<EnumConnectDiagonal> CONNECT_DIAGONAL = PropertyEnum.create("diagonal_connections", EnumConnectDiagonal.class);
	public static final PropertyEnum<EnumConnectDirectional> CONNECT_DIRECTIONAL = PropertyEnum.create("directional_connections", EnumConnectDirectional.class);
	public static final PropertyDirection FACING =  PropertyDirection.create("zz_facing", EnumFacing.Plane.HORIZONTAL);

	public SideLinePaintBlock(String name) {
		super(name);
		this.setDefaultState(this.blockState.getBaseState()
				.withProperty(FACING, EnumFacing.NORTH));
	}
	
	@Override
	public String getSpecialName(ItemStack stack) {
		return stack.getItemDamage() + "";
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing)state.getValue(FACING)).getIndex();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing enumfacing = EnumFacing.getFront(meta);
		return this.getDefaultState().withProperty(FACING, enumfacing);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {FACING, CONNECT_DIRECTIONAL, CONNECT_DIAGONAL});
	}

	public EnumConnectDirectional getFacingForConnectedBlock(IBlockAccess world, BlockPos pos) {
		int rootBlockMeta = getMetaFromState(world.getBlockState(pos));
		IBlockState offsetBlockNorth = world.getBlockState(pos.offset(EnumFacing.NORTH));
		IBlockState offsetBlockEast  = world.getBlockState(pos.offset(EnumFacing.EAST));
		IBlockState offsetBlockSouth = world.getBlockState(pos.offset(EnumFacing.SOUTH));
		IBlockState offsetBlockWest  = world.getBlockState(pos.offset(EnumFacing.WEST));
		int offsetMetaNorth = offsetBlockNorth.getBlock().getMetaFromState(offsetBlockNorth);
		int offsetMetaEast  = offsetBlockEast.getBlock().getMetaFromState(offsetBlockEast);
		int offsetMetaSouth = offsetBlockSouth.getBlock().getMetaFromState(offsetBlockSouth);
		int offsetMetaWest  = offsetBlockWest.getBlock().getMetaFromState(offsetBlockWest);
		
		//NORTH
		if (rootBlockMeta == 2) {
			if (offsetMetaNorth == 4) {
				return EnumConnectDirectional.CONNECT_NORTH_LEFT_UP;
			}
			if (offsetMetaNorth == 5) {
				return EnumConnectDirectional.CONNECT_NORTH_RIGHT_UP;
			}
			if (offsetMetaSouth == 4) {
				return EnumConnectDirectional.CONNECT_SOUTH_LEFT_DOWN;
			}
			if (offsetMetaSouth == 5) {
				return EnumConnectDirectional.CONNECT_SOUTH_RIGHT_DOWN;
			}
		}	
		//SOUTH
		if (rootBlockMeta == 3) {
			if (offsetMetaNorth == 4) {
				return EnumConnectDirectional.CONNECT_NORTH_RIGHT_DOWN;
			}
			if (offsetMetaNorth == 5) {
				return EnumConnectDirectional.CONNECT_NORTH_LEFT_DOWN;
			}
			if (offsetMetaSouth == 4) {
				return EnumConnectDirectional.CONNECT_SOUTH_RIGHT_UP;
			}
			if (offsetMetaSouth == 5) {
				return EnumConnectDirectional.CONNECT_SOUTH_LEFT_UP;
			}
		}		
		//WEST
		if (rootBlockMeta == 4) {
			if (offsetMetaEast == 2) {
				return EnumConnectDirectional.CONNECT_EAST_RIGHT_DOWN;
			}
			if (offsetMetaEast == 3) {
				return EnumConnectDirectional.CONNECT_EAST_LEFT_DOWN;
			}
			
			if (offsetMetaWest == 2) {
				return EnumConnectDirectional.CONNECT_WEST_RIGHT_UP;
			}
			if (offsetMetaWest == 3) {
				return EnumConnectDirectional.CONNECT_WEST_LEFT_UP;
			}
		}
		//EAST
		if (rootBlockMeta == 5) {
			if (offsetMetaEast == 2) {
				return EnumConnectDirectional.CONNECT_EAST_LEFT_UP;
			}
			if (offsetMetaEast == 3) {
				return EnumConnectDirectional.CONNECT_EAST_RIGHT_UP;
			}
			if (offsetMetaWest == 2) {
				return EnumConnectDirectional.CONNECT_WEST_LEFT_DOWN;
			}
			if (offsetMetaWest == 3) {
				return EnumConnectDirectional.CONNECT_WEST_RIGHT_DOWN;
			}
		}
		return EnumConnectDirectional.CONNECT_NONE;
	}
	
	public EnumConnectDiagonal getInnerCorner(IBlockAccess world, BlockPos pos) {
		int rootBlockMeta = getMetaFromState(world.getBlockState(pos));
		IBlockState offsetBlockNorth = world.getBlockState(pos.offset(EnumFacing.NORTH));
		IBlockState offsetBlockEast  = world.getBlockState(pos.offset(EnumFacing.EAST));
		IBlockState offsetBlockSouth = world.getBlockState(pos.offset(EnumFacing.SOUTH));
		IBlockState offsetBlockWest  = world.getBlockState(pos.offset(EnumFacing.WEST));
		int offsetMetaNorth = offsetBlockNorth.getBlock().getMetaFromState(offsetBlockNorth);
		int offsetMetaEast  = offsetBlockEast.getBlock().getMetaFromState(offsetBlockEast);
		int offsetMetaSouth = offsetBlockSouth.getBlock().getMetaFromState(offsetBlockSouth);
		int offsetMetaWest  = offsetBlockWest.getBlock().getMetaFromState(offsetBlockWest);
		if (rootBlockMeta == 2 || rootBlockMeta == 5) {
			if (offsetMetaNorth == 5 && offsetMetaEast == 2) {
				return EnumConnectDiagonal.NORTH_EAST;
			}
		}
		
		if (rootBlockMeta == 2 || rootBlockMeta == 4) {
			if (offsetMetaNorth == 4 && offsetMetaWest == 2) {
				return EnumConnectDiagonal.NORTH_WEST;
			}
		}
		
		if (rootBlockMeta == 3 || rootBlockMeta == 5) {
			if (offsetMetaSouth == 5 && offsetMetaEast == 3) {
				return EnumConnectDiagonal.SOUTH_EAST;
			}
		}
		
		if (rootBlockMeta == 3 || rootBlockMeta == 4) {
			if (offsetMetaSouth == 4 && offsetMetaWest == 3) {
				return EnumConnectDiagonal.SOUTH_WEST;
			}
		}
		return EnumConnectDiagonal.NONE;
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(CONNECT_DIAGONAL, getInnerCorner(worldIn, pos))
			.withProperty(CONNECT_DIRECTIONAL, getFacingForConnectedBlock(worldIn, pos));
	}
}
