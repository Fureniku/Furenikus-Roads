package com.silvaniastudios.roads.blocks.decorative;

import javax.annotation.Nullable;

import com.silvaniastudios.roads.blocks.NonPaintRoadTopBlock;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CatsEyeBlock extends NonPaintRoadTopBlock {
	
	public static final PropertyEnum<CatsEyeBlock.EnumCatsEye> ENUM_EYE = PropertyEnum.create("eye_type", CatsEyeBlock.EnumCatsEye.class);
	boolean dbl = false;

	public CatsEyeBlock(String name, boolean dbl) {
		super(name);
		this.dbl = dbl;
		this.setDefaultState(this.blockState.getBaseState().withProperty(ENUM_EYE, CatsEyeBlock.EnumCatsEye.floor_ns));
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		if (facing == EnumFacing.UP) {
			if (placer.getHorizontalFacing() == EnumFacing.EAST || placer.getHorizontalFacing() == EnumFacing.WEST) {
				return this.getDefaultState().withProperty(ENUM_EYE, CatsEyeBlock.EnumCatsEye.floor_ew);
			}
			return this.getDefaultState().withProperty(ENUM_EYE, CatsEyeBlock.EnumCatsEye.floor_ns);
		}
		if (facing == EnumFacing.DOWN) {
			if (placer.getHorizontalFacing() == EnumFacing.EAST || placer.getHorizontalFacing() == EnumFacing.WEST) {
				return this.getDefaultState().withProperty(ENUM_EYE, CatsEyeBlock.EnumCatsEye.roof_ew);
			}
			return this.getDefaultState().withProperty(ENUM_EYE, CatsEyeBlock.EnumCatsEye.roof_ns);
		}
		
		if (facing == EnumFacing.NORTH) { return this.getDefaultState().withProperty(ENUM_EYE, CatsEyeBlock.EnumCatsEye.wall_north); }
		if (facing == EnumFacing.EAST)  { return this.getDefaultState().withProperty(ENUM_EYE, CatsEyeBlock.EnumCatsEye.wall_east); }
		if (facing == EnumFacing.SOUTH) { return this.getDefaultState().withProperty(ENUM_EYE, CatsEyeBlock.EnumCatsEye.wall_south); }
		if (facing == EnumFacing.WEST)  { return this.getDefaultState().withProperty(ENUM_EYE, CatsEyeBlock.EnumCatsEye.wall_west); }
		
		
		return this.getDefaultState().withProperty(ENUM_EYE, CatsEyeBlock.EnumCatsEye.floor_ns);
	}
    
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {ENUM_EYE});
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(ENUM_EYE, CatsEyeBlock.EnumCatsEye.byMetadata(meta));
    }
	
	@Override
    public int getMetaFromState(IBlockState state) {
        return ((CatsEyeBlock.EnumCatsEye)state.getValue(ENUM_EYE)).getMetadata();
    }

	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
    	return getBox(state, worldIn, pos);
    }

	@Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return NULL_AABB;
    }
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState iBlockState) {
		return EnumBlockRenderType.MODEL;
	}
    
    public AxisAlignedBB getBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
    	int meta = getMetaFromState(state);
    	if (dbl) {
    		if (meta == 0) { return new AxisAlignedBB(0.125,  -1+getBlockBelowHeight(worldIn, pos), 0.4375, 0.875, -1+getBlockBelowHeight(worldIn, pos)+0.0625, 0.5625); }
    		if (meta == 1) { return new AxisAlignedBB(0.4375, -1+getBlockBelowHeight(worldIn, pos), 0.125, 0.5625, -1+getBlockBelowHeight(worldIn, pos)+0.0625,  0.875); }
	    	if (meta == 2) { return new AxisAlignedBB(0.4375, 0.125,  0.9375, 0.5625, 0.875, 1     ); }
	    	if (meta == 3) { return new AxisAlignedBB(0,      0.125,  0.4375, 0.0625, 0.875, 0.5625); }
	    	if (meta == 4) { return new AxisAlignedBB(0.4375, 0.125,  0,      0.5625, 0.875, 0.0625); }
	    	if (meta == 5) { return new AxisAlignedBB(0.9375, 0.125,  0.4375, 1,      0.875, 0.5625); }
	    	if (meta == 6) { return new AxisAlignedBB(0.125,  0.9375, 0.4375, 0.875,  1.0,   0.5625); }
	    	if (meta == 7) { return new AxisAlignedBB(0.4375, 0.9375, 0.125,  0.5625, 1.0,   0.875);  }
    	}
    	
    	if (meta == 6 || meta == 7) {
    		return new AxisAlignedBB(0.4375, 0.9375, 0.4375, 0.5625, 1.0, 0.5625);
    	}
    	
    	if (meta == 2) { return new AxisAlignedBB(0.4375, 0.4375, 0.9375, 0.5625, 0.5625, 1     ); }
    	if (meta == 3) { return new AxisAlignedBB(0,      0.4375, 0.4375, 0.0625, 0.5625, 0.5625); }
    	if (meta == 4) { return new AxisAlignedBB(0.4375, 0.4375, 0,      0.5625, 0.5625, 0.0625); }
    	if (meta == 5) { return new AxisAlignedBB(0.9375, 0.4375, 0.4375, 1,      0.5625, 0.5625); }
    	
    	return new AxisAlignedBB(0.4375, -1+getBlockBelowHeight(worldIn, pos), 0.4375, 0.5625, -1+getBlockBelowHeight(worldIn, pos)+0.0625, 0.5625);
    }
	
	@Override
    public Vec3d getOffset(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		int thisMeta = getMetaFromState(worldIn.getBlockState(pos));
		if (thisMeta == 0 || thisMeta == 1) {
			double offset = 1.0 - getBlockBelowHeight(worldIn, pos);
	        return new Vec3d(0, -offset, 0);
		}
        return new Vec3d(0, 0, 0);
    }
    
	
	public static enum EnumCatsEye implements IStringSerializable {
		floor_ns(0, "floor_ns"),
		floor_ew(1, "floor_ew"),
		wall_north(2, "wall_north"),
		wall_east(3, "wall_east"),
		wall_south(4, "wall_south"),
		wall_west(5, "wall_west"),
		roof_ns(6, "roof_ns"),
		roof_ew(7, "roof_ew");
		
		private static final EnumCatsEye[] META_LOOKUP = new EnumCatsEye[values().length];
		private final int meta;
		private final String name;
		
		private EnumCatsEye(int meta, String name) {
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
		
		public static EnumCatsEye byMetadata(int meta) {
	        if (meta < 0 || meta >= META_LOOKUP.length) {
	            meta = 0;
	        }
	        
	        return META_LOOKUP[meta];
	    }
		
		static {
	        for (EnumCatsEye type: values()) {
	            META_LOOKUP[type.getMetadata()] = type;
	        }
	    }
	}

}
