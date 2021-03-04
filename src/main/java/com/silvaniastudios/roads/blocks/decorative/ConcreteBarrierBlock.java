package com.silvaniastudios.roads.blocks.decorative;

import javax.annotation.Nullable;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.BlockBase;
import com.silvaniastudios.roads.blocks.NonPaintRoadTopBlock;
import com.silvaniastudios.roads.blocks.enums.IConnectable;
import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;

public class ConcreteBarrierBlock extends BlockBase implements IConnectable {
	
	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool EAST = PropertyBool.create("east");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool WEST = PropertyBool.create("west");

	public ConcreteBarrierBlock(String name) {
		super(name, Material.IRON);
		this.setDefaultState(this.blockState.getBaseState()
				.withProperty(NORTH, true)
				.withProperty(EAST, false)
				.withProperty(SOUTH, true)
				.withProperty(WEST, false));
		this.setCreativeTab(FurenikusRoads.tab_road_parts);
		this.setHardness(1.5F);
		this.setHarvestLevel("pickaxe", 1);
	}

	@SuppressWarnings("deprecation")
	public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos, boolean checkingLevel) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();
		if (checkingLevel) {
			if (block instanceof IConnectable || block.isOpaqueCube(state)) {
				return true;
			}
		} 
		
		return block instanceof IConnectable;
	}

	private boolean canBarrierConnectTo(IBlockAccess world, BlockPos pos, EnumFacing facing) {
		BlockPos offset = pos.offset(facing);
		return canConnectTo(world, offset, true) || canConnectTo(world, offset.offset(EnumFacing.DOWN), false) || canConnectTo(world, offset.offset(EnumFacing.UP), false);
	}
	
	public int getMetaFromState(IBlockState state) {
		return 0;
	}
	
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState();
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {NORTH, EAST, WEST, SOUTH});
	}

	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(NORTH, canBarrierConnectTo(worldIn, pos, EnumFacing.NORTH))
			.withProperty(EAST,  canBarrierConnectTo(worldIn, pos, EnumFacing.EAST))
			.withProperty(SOUTH, canBarrierConnectTo(worldIn, pos, EnumFacing.SOUTH))
			.withProperty(WEST,  canBarrierConnectTo(worldIn, pos, EnumFacing.WEST));
	}

    @Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
    	return getBox(state, world, pos);
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
        return getBox(state, world, pos);
    }
    
    private AxisAlignedBB getBox(IBlockState state, IBlockAccess world, BlockPos pos) {
    	boolean north = canBarrierConnectTo(world, pos, EnumFacing.NORTH);
    	boolean east  = canBarrierConnectTo(world, pos, EnumFacing.EAST);
    	boolean south = canBarrierConnectTo(world, pos, EnumFacing.SOUTH);
    	boolean west  = canBarrierConnectTo(world, pos, EnumFacing.WEST);
    	
    	double xLow = 0.0;
    	double zLow = 0.0;
    	double xHigh = 1.0;
    	double zHigh = 1.0;
    	
    	if (north) { zLow  = 0.0; } else { zLow  = (1.0/16.0)*5.375; }
    	if (east)  { xHigh = 1.0; } else { xHigh = 1.0-((1.0/16.0)*5.375); }
    	if (south) { zHigh = 1.0; } else { zHigh = 1.0-((1.0/16.0)*5.375); }
    	if (west)  { xLow  = 0.0; } else { xLow  = (1.0/16.0)*5.375; }
    	return new AxisAlignedBB(xLow, -1+getBlockBelowHeight(world, pos), zLow, xHigh, -1+getBlockBelowHeight(world, pos)+1.0, zHigh);
    }
    
    @Override
    public Block.EnumOffsetType getOffsetType()
    {
        return Block.EnumOffsetType.XYZ;
    }
    
    @Override
    public Vec3d getOffset(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        double offset = 1.0 - getBlockBelowHeight(worldIn, pos);
        return new Vec3d(0, -offset, 0);
    }
    
    @SuppressWarnings("deprecation")
	public double getBlockBelowHeight(IBlockAccess worldIn, BlockPos pos) {
    	IBlockState underState = worldIn.getBlockState(pos.offset(EnumFacing.DOWN));
        Block underBlock = underState.getBlock();
        double extraOffset = 0.0;
        
        if (underBlock instanceof PaintBlockBase || underBlock instanceof NonPaintRoadTopBlock || underBlock instanceof CurbBlock) {
        	extraOffset = 0.062;
        }
        
        return underBlock.getBoundingBox(underState, worldIn, pos.offset(EnumFacing.DOWN)).maxY - extraOffset;
    }
    
    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
    	return BlockFaceShape.UNDEFINED;
    }
}
