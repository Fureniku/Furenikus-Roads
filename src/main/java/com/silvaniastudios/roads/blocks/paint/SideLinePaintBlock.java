package com.silvaniastudios.roads.blocks.paint;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.enums.EnumConnectSideLine;
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

public class SideLinePaintBlock extends PaintBlockBase implements IMetaBlockName {
	
	public static final PropertyEnum<EnumConnectSideLine> CONNECT_LEFT = PropertyEnum.create("connect_left", EnumConnectSideLine.class);
	public static final PropertyEnum<EnumConnectSideLine> CONNECT_RIGHT = PropertyEnum.create("connect_right", EnumConnectSideLine.class);
	public static final PropertyDirection FACING =  PropertyDirection.create("zz_facing", EnumFacing.Plane.HORIZONTAL);

	public SideLinePaintBlock(String name, String category, int[] coreMetas, boolean dynamic, String colour) {
		super(name, category, coreMetas, dynamic, colour);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(CONNECT_LEFT, EnumConnectSideLine.NONE).withProperty(CONNECT_RIGHT, EnumConnectSideLine.NONE));
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
		if (enumfacing == EnumFacing.DOWN || enumfacing == EnumFacing.UP) {
			return this.getDefaultState().withProperty(FACING, EnumFacing.NORTH);
		}
		return this.getDefaultState().withProperty(FACING, enumfacing);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {FACING, CONNECT_LEFT, CONNECT_RIGHT});
	}

	
	private EnumConnectSideLine canLineConnectTo(IBlockAccess world, BlockPos pos, boolean leftSide) {
		IBlockState state = world.getBlockState(pos);
		int meta = getMetaFromState(state);
		IBlockState stateNorth = world.getBlockState(pos);
		IBlockState stateEast  = world.getBlockState(pos);
		IBlockState stateSouth = world.getBlockState(pos);
		IBlockState stateWest  = world.getBlockState(pos);
		
		boolean blockNorth = world.getBlockState(pos.offset(EnumFacing.NORTH)).getBlock() instanceof SideLinePaintBlock;
		boolean blockEast  = world.getBlockState(pos.offset(EnumFacing.EAST)) .getBlock() instanceof SideLinePaintBlock;
		boolean blockSouth = world.getBlockState(pos.offset(EnumFacing.SOUTH)).getBlock() instanceof SideLinePaintBlock;
		boolean blockWest  = world.getBlockState(pos.offset(EnumFacing.WEST)) .getBlock() instanceof SideLinePaintBlock;
		
		if (blockNorth) { stateNorth = world.getBlockState(pos.offset(EnumFacing.NORTH)); }
		if (blockEast)  { stateEast  = world.getBlockState(pos.offset(EnumFacing.EAST));  }
		if (blockSouth) { stateSouth = world.getBlockState(pos.offset(EnumFacing.SOUTH)); }
		if (blockWest)  { stateWest  = world.getBlockState(pos.offset(EnumFacing.WEST));  }
		
		BlockPos posDown = pos.offset(EnumFacing.DOWN);
		BlockPos posUp = pos.offset(EnumFacing.UP);
		
		if (!blockNorth) {
			if (world.getBlockState(posDown.offset(EnumFacing.NORTH)).getBlock() instanceof SideLinePaintBlock) {
				blockNorth = true;
				stateNorth = world.getBlockState(posDown.offset(EnumFacing.NORTH));
			} else if (world.getBlockState(posUp.offset(EnumFacing.NORTH)).getBlock() instanceof SideLinePaintBlock) {
				blockNorth = true;
				stateNorth = world.getBlockState(posUp.offset(EnumFacing.NORTH));
			}
		}
		
		if (!blockEast) {
			if (world.getBlockState(posDown.offset(EnumFacing.EAST)).getBlock() instanceof SideLinePaintBlock) {
				blockEast = true;
				stateEast = world.getBlockState(posDown.offset(EnumFacing.EAST));
			} else if (world.getBlockState(posUp.offset(EnumFacing.EAST)).getBlock() instanceof SideLinePaintBlock) {
				blockEast = true;
				stateEast = world.getBlockState(posUp.offset(EnumFacing.EAST));
			}
		}
		
		if (!blockSouth) {
			if (world.getBlockState(posDown.offset(EnumFacing.SOUTH)).getBlock() instanceof SideLinePaintBlock) {
				blockSouth = true;
				stateSouth = world.getBlockState(posDown.offset(EnumFacing.SOUTH));
			} else if (world.getBlockState(posUp.offset(EnumFacing.SOUTH)).getBlock() instanceof SideLinePaintBlock) {
				blockSouth = true;
				stateSouth = world.getBlockState(posUp.offset(EnumFacing.SOUTH));
			}
		}
		
		if (!blockWest) {
			if (world.getBlockState(posDown.offset(EnumFacing.WEST)).getBlock() instanceof SideLinePaintBlock) {
				blockWest = true;
				stateWest = world.getBlockState(posDown.offset(EnumFacing.WEST));
			} else if (world.getBlockState(posUp.offset(EnumFacing.WEST)).getBlock() instanceof SideLinePaintBlock) {
				blockWest = true;
				stateWest = world.getBlockState(posUp.offset(EnumFacing.WEST));
			}
		}
		
		IBlockState stateUp = null;
		IBlockState stateDown = null;
		
		boolean blockUp = false;
		boolean blockLeft = false;
		boolean blockRight = false;
		boolean blockDown = false;
		
		//north
		if (getMetaFromState(state) == 2) {
			blockUp    = blockNorth;
			blockLeft  = blockWest;
			blockRight = blockEast;
			blockDown  = blockSouth;
			if (blockUp)   { stateUp   = stateNorth; }
			if (blockDown) { stateDown = stateSouth; }
		}
		
		//east
		if (getMetaFromState(state) == 5) {
			blockUp    = blockEast;
			blockLeft  = blockNorth;
			blockRight = blockSouth;
			blockDown  = blockWest;
			if (blockUp)   { stateUp   = stateEast; }
			if (blockDown) { stateDown = stateWest; }
		}
		
		//south
		if (getMetaFromState(state) == 3) {
			blockUp    = blockSouth;
			blockLeft  = blockEast;
			blockRight = blockWest;
			blockDown  = blockNorth;
			if (blockUp)   { stateUp   = stateSouth; }
			if (blockDown) { stateDown = stateNorth; }
		}
		
		//west
		if (getMetaFromState(state) == 4) {
			blockUp    = blockWest;
			blockLeft  = blockSouth;
			blockRight = blockNorth;
			blockDown  = blockEast;
			if (blockUp)   { stateUp   = stateWest; }
			if (blockDown) { stateDown = stateEast; }
		}
		boolean blockSide = false;
		int metaUp = -1;
		int metaDown = -1;
		
		if (blockUp && stateUp.getBlock() instanceof SideLinePaintBlock) { metaUp = stateUp.getBlock().getMetaFromState(stateUp); }
		if (blockDown && stateDown.getBlock() instanceof SideLinePaintBlock) { metaDown = stateDown.getBlock().getMetaFromState(stateDown); }

		if (leftSide) {
			if (blockRight && blockUp) {
				if ((meta == 2 && metaUp != 4) ||
					(meta == 3 && metaUp != 5) ||
					(meta == 4 && metaUp != 3) ||
					(meta == 5 && metaUp != 2)) {
						return EnumConnectSideLine.EMPTY;
				}
			}
			blockSide = blockLeft;
			blockRight = false;
		} else {
			if (blockLeft && blockUp) { 
				if ((meta == 2 && metaUp != 5) ||
					(meta == 3 && metaUp != 4) ||
					(meta == 4 && metaUp != 2) ||
					(meta == 5 && metaUp != 3)) {
						return EnumConnectSideLine.EMPTY;
				}
			}
			blockSide = blockRight;
			blockLeft = false;
		}
		if (!blockUp &&  blockSide && !blockDown) { return EnumConnectSideLine.SIDE; }
		if ( blockUp &&  blockSide &&  blockDown) { return EnumConnectSideLine.SIDE; }
		if (!blockUp &&  blockSide &&  blockDown) { return EnumConnectSideLine.SIDE; }

		if ((meta == 2 && metaUp == 4) ||
			(meta == 3 && metaUp == 5) ||
			(meta == 4 && metaUp == 3) ||
			(meta == 5 && metaUp == 2)) {
			if (leftSide) {
				if ( blockUp && !blockSide && !blockDown) { return EnumConnectSideLine.UP; }
				if ( blockUp && !blockSide &&  blockDown) { return EnumConnectSideLine.UP; }
				if ( blockUp &&  blockSide && !blockDown) { return EnumConnectSideLine.CORNER; }
			}
		}
		if ((meta == 2 && metaUp == 5) ||
			(meta == 3 && metaUp == 4) ||
			(meta == 4 && metaUp == 2) ||
			(meta == 5 && metaUp == 3)) {
			if (!leftSide) {
				if ( blockUp && !blockSide && !blockDown) { return EnumConnectSideLine.UP; }
				if ( blockUp && !blockSide &&  blockDown) { return EnumConnectSideLine.UP; }
				if ( blockUp &&  blockSide && !blockDown) { return EnumConnectSideLine.CORNER; }
			}
		}
		
		if ((meta == 2 && metaDown == 4) ||
			(meta == 3 && metaDown == 5) ||
			(meta == 4 && metaDown == 3) ||
			(meta == 5 && metaDown == 2)) {
			if (leftSide) {
				if (!blockUp && !blockSide &&  blockDown) { return EnumConnectSideLine.DOWN; }
			}
		}
		if ((meta == 2 && metaDown == 5) ||
			(meta == 3 && metaDown == 4) ||
			(meta == 4 && metaDown == 2) ||
			(meta == 5 && metaDown == 3)) {
			if (!leftSide) {
				if (!blockUp && !blockSide &&  blockDown) { return EnumConnectSideLine.DOWN; }
			}
		}
		
		if (blockSide) { return EnumConnectSideLine.SIDE; }
		
		return EnumConnectSideLine.NONE;
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(CONNECT_LEFT, canLineConnectTo(worldIn, pos, true))
			.withProperty(CONNECT_RIGHT, canLineConnectTo(worldIn, pos, false));
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
}
