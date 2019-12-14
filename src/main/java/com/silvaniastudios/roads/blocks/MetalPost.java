package com.silvaniastudios.roads.blocks;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.enums.IPostConnectable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class MetalPost extends BlockBase implements IPostConnectable {
	
	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool EAST = PropertyBool.create("east");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool WEST = PropertyBool.create("west");
	public static final PropertyBool UP = PropertyBool.create("up");
	public static final PropertyBool DOWN = PropertyBool.create("down");
	
	boolean horizontal = false;
	double thickness = 0;

	public MetalPost(String name, boolean horizontal, double thickness) {
		super(name, Material.IRON);
		this.horizontal = horizontal;
		this.thickness = thickness;
		this.setCreativeTab(FurenikusRoads.tab_road_parts);
		this.setHardness(2.0F);
		setDefaultState();
		this.useNeighborBrightness = true;
	}
	
	public void setDefaultState() {
		this.setDefaultState(this.blockState.getBaseState()
				.withProperty(NORTH, false)
				.withProperty(EAST,  false)
				.withProperty(SOUTH, false)
				.withProperty(WEST,  false)
				.withProperty(UP,  false)
				.withProperty(DOWN, false));
	}
	
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(NORTH, canPostConnectTo(worldIn, pos, EnumFacing.NORTH))
			.withProperty(EAST,  canPostConnectTo(worldIn, pos, EnumFacing.EAST))
			.withProperty(SOUTH, canPostConnectTo(worldIn, pos, EnumFacing.SOUTH))
			.withProperty(WEST,  canPostConnectTo(worldIn, pos, EnumFacing.WEST))
			.withProperty(UP,    canPostConnectTo(worldIn, pos, EnumFacing.UP))
			.withProperty(DOWN,  getBlockBelowOffset(worldIn, pos));
	}
	
	@SuppressWarnings("deprecation")
	public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos, EnumFacing facing) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();
		if (facing == EnumFacing.DOWN && !block.equals(Blocks.AIR)) {
			return true;
		}
		
		if (this.horizontal && block.getBlockFaceShape(worldIn, state, pos, facing.getOpposite()) == BlockFaceShape.SOLID) {
			return true;
		}
		if (block instanceof MetalPost) {
			MetalPost post = (MetalPost) block;
			return post.horizontal;
		}
		return false; 
	}

	private boolean canPostConnectTo(IBlockAccess world, BlockPos pos, EnumFacing facing) {
		BlockPos offset = pos.offset(facing);
		return canConnectTo(world, offset, facing.getOpposite());
	}
	
	@SuppressWarnings("deprecation")
	public boolean getBlockBelowOffset(IBlockAccess worldIn, BlockPos pos) {
    	IBlockState underState = worldIn.getBlockState(pos.offset(EnumFacing.DOWN));
        Block underBlock = underState.getBlock();
        return underBlock.getBoundingBox(underState, worldIn, pos.offset(EnumFacing.DOWN)).maxY != 1.0;
    }
    
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {NORTH, EAST, SOUTH, WEST, UP, DOWN});
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState();
    }
	
	@Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }
	
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
    	return getBoxFromState(state, world, pos);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
    	return getBoxFromState(state, world, pos);
    }
    
    public AxisAlignedBB getBoxFromState(IBlockState state, IBlockAccess world, BlockPos pos) {
    	double widthOffset = thickness / 2;
    	
    	boolean north = canPostConnectTo(world, pos, EnumFacing.NORTH);
    	boolean east  = canPostConnectTo(world, pos, EnumFacing.EAST);
    	boolean south = canPostConnectTo(world, pos, EnumFacing.SOUTH);
    	boolean west  = canPostConnectTo(world, pos, EnumFacing.WEST);
    	boolean up    = canPostConnectTo(world, pos, EnumFacing.UP);
    	
    	double xLow = 0;
    	double yLow = 0;
    	double zLow = 0;
    	double xHigh = 1;
    	double yHigh = 1;
    	double zHigh = 1;
    	
    	if (north) { zLow  = 0; } else { zLow  = 0.5 - widthOffset; }
    	if (east)  { xHigh = 1; } else { xHigh = 0.5 + widthOffset; }
    	if (south) { zHigh = 1; } else { zHigh = 0.5 + widthOffset; }
    	if (west)  { xLow  = 0; } else { xLow  = 0.5 - widthOffset; }
    	
    	if (horizontal) {
    		yLow = 0.5  - widthOffset;
    		yHigh = 0.5 + widthOffset;
    	} else {
    		if ((north || east || south || west) && !up) {
    			yHigh = 0.5 + widthOffset;
    		}
    		if ((!north && !east && !south && !west) || up) {
    			yHigh = 1;
    		}
    	}
    	
    	if (getBlockBelowOffset(world, pos)) {
    		yLow = -0.935;
    	}
    	
        return new AxisAlignedBB(xLow, yLow, zLow, xHigh, yHigh, zHigh);
    }
	
	@Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
    
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
}
