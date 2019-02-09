package com.silvaniastudios.roads.blocks.paint;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.enums.EnumConnectDiagonal;
import com.silvaniastudios.roads.blocks.enums.EnumConnectDirectional_FarSide;
import com.silvaniastudios.roads.blocks.enums.ILineConnectable;
import com.silvaniastudios.roads.blocks.enums.IMetaBlockName;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FarSideLinePaintBlock extends PaintBlockBase implements ILineConnectable, IMetaBlockName {
	
	public static final PropertyEnum<EnumConnectDiagonal> CONNECT_DIAGONAL = PropertyEnum.create("diagonal_connections", EnumConnectDiagonal.class);
	public static final PropertyEnum<EnumConnectDirectional_FarSide> CONNECT_DIRECTIONAL = PropertyEnum.create("directional_connections", EnumConnectDirectional_FarSide.class);
	public static final PropertyDirection FACING =  PropertyDirection.create("zz_facing", EnumFacing.Plane.HORIZONTAL);

	public FarSideLinePaintBlock(String name) {
		super(name);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.setCreativeTab(FurenikusRoads.tab_paint_lines);
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

	public EnumConnectDirectional_FarSide getFacingForConnectedBlock(IBlockAccess world, BlockPos pos) {
		int rootBlockMeta = getMetaFromState(world.getBlockState(pos));
		IBlockState offsetBlockNorth = world.getBlockState(pos.offset(EnumFacing.NORTH));
		IBlockState offsetBlockEast  = world.getBlockState(pos.offset(EnumFacing.EAST));
		IBlockState offsetBlockSouth = world.getBlockState(pos.offset(EnumFacing.SOUTH));
		IBlockState offsetBlockWest  = world.getBlockState(pos.offset(EnumFacing.WEST));
		int offsetMetaNorth = offsetBlockNorth.getBlock() instanceof ILineConnectable ? offsetBlockNorth.getBlock().getMetaFromState(offsetBlockNorth) : -1;
		int offsetMetaEast  = offsetBlockEast.getBlock()  instanceof ILineConnectable ? offsetBlockEast.getBlock().getMetaFromState(offsetBlockEast) : -1;
		int offsetMetaSouth = offsetBlockSouth.getBlock() instanceof ILineConnectable ? offsetBlockSouth.getBlock().getMetaFromState(offsetBlockSouth) : -1;
		int offsetMetaWest  = offsetBlockWest.getBlock()  instanceof ILineConnectable ? offsetBlockWest.getBlock().getMetaFromState(offsetBlockWest) : -1;
		
		//NORTH
		if (rootBlockMeta == 2) {
			if (offsetMetaSouth == 4) {
				return EnumConnectDirectional_FarSide.CONNECT_SOUTH_LEFT_DOWN;
			}
			if (offsetMetaSouth == 5) {
				return EnumConnectDirectional_FarSide.CONNECT_SOUTH_RIGHT_DOWN;
			}
		}	
		//SOUTH
		if (rootBlockMeta == 3) {
			if (offsetMetaNorth == 4) {
				return EnumConnectDirectional_FarSide.CONNECT_NORTH_RIGHT_DOWN;
			}
			if (offsetMetaNorth == 5) {
				return EnumConnectDirectional_FarSide.CONNECT_NORTH_LEFT_DOWN;
			}
		}		
		//WEST
		if (rootBlockMeta == 4) {
			if (offsetMetaEast == 2) {
				return EnumConnectDirectional_FarSide.CONNECT_EAST_RIGHT_DOWN;
			}
			if (offsetMetaEast == 3) {
				return EnumConnectDirectional_FarSide.CONNECT_EAST_LEFT_DOWN;
			}
		}
		//EAST
		if (rootBlockMeta == 5) {
			if (offsetMetaWest == 2) {
				return EnumConnectDirectional_FarSide.CONNECT_WEST_LEFT_DOWN;
			}
			if (offsetMetaWest == 3) {
				return EnumConnectDirectional_FarSide.CONNECT_WEST_RIGHT_DOWN;
			}
		}
		return EnumConnectDirectional_FarSide.CONNECT_NONE;
	}
	
	public EnumConnectDiagonal getInnerCorner(IBlockAccess world, BlockPos pos) {
		int rootBlockMeta = getMetaFromState(world.getBlockState(pos));
		IBlockState offsetBlockNorth = world.getBlockState(pos.offset(EnumFacing.NORTH));
		IBlockState offsetBlockEast  = world.getBlockState(pos.offset(EnumFacing.EAST));
		IBlockState offsetBlockSouth = world.getBlockState(pos.offset(EnumFacing.SOUTH));
		IBlockState offsetBlockWest  = world.getBlockState(pos.offset(EnumFacing.WEST));
		int offsetMetaNorth = offsetBlockNorth.getBlock() instanceof ILineConnectable ? offsetBlockNorth.getBlock().getMetaFromState(offsetBlockNorth) : -1;
		int offsetMetaEast  = offsetBlockEast.getBlock()  instanceof ILineConnectable ? offsetBlockEast.getBlock().getMetaFromState(offsetBlockEast) : -1;
		int offsetMetaSouth = offsetBlockSouth.getBlock() instanceof ILineConnectable ? offsetBlockSouth.getBlock().getMetaFromState(offsetBlockSouth) : -1;
		int offsetMetaWest  = offsetBlockWest.getBlock()  instanceof ILineConnectable ? offsetBlockWest.getBlock().getMetaFromState(offsetBlockWest) : -1;
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
	
	@SideOnly(Side.CLIENT)
	@Override
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
}
