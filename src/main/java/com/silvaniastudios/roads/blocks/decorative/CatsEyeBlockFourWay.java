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

public class CatsEyeBlockFourWay extends NonPaintRoadTopBlock {
	
	public static final PropertyEnum<CatsEyeBlockFourWay.EnumCatsEye> ENUM_EYE = PropertyEnum.create("eye_type", CatsEyeBlockFourWay.EnumCatsEye.class);
	boolean dbl = false;

	public CatsEyeBlockFourWay(String name, boolean dbl) {
		super(name);
		this.dbl = dbl;
		this.setDefaultState(this.blockState.getBaseState().withProperty(ENUM_EYE, CatsEyeBlockFourWay.EnumCatsEye.floor_n));
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		if (facing == EnumFacing.UP) {
			if (placer.getHorizontalFacing() == EnumFacing.NORTH) return this.getDefaultState().withProperty(ENUM_EYE, CatsEyeBlockFourWay.EnumCatsEye.floor_n);
			if (placer.getHorizontalFacing() == EnumFacing.EAST)  return this.getDefaultState().withProperty(ENUM_EYE, CatsEyeBlockFourWay.EnumCatsEye.floor_e);
			if (placer.getHorizontalFacing() == EnumFacing.SOUTH) return this.getDefaultState().withProperty(ENUM_EYE, CatsEyeBlockFourWay.EnumCatsEye.floor_s);
			if (placer.getHorizontalFacing() == EnumFacing.WEST)  return this.getDefaultState().withProperty(ENUM_EYE, CatsEyeBlockFourWay.EnumCatsEye.floor_w);
		}
		if (facing == EnumFacing.DOWN) {
			if (placer.getHorizontalFacing() == EnumFacing.NORTH) return this.getDefaultState().withProperty(ENUM_EYE, CatsEyeBlockFourWay.EnumCatsEye.roof_n);
			if (placer.getHorizontalFacing() == EnumFacing.EAST)  return this.getDefaultState().withProperty(ENUM_EYE, CatsEyeBlockFourWay.EnumCatsEye.roof_e);
			if (placer.getHorizontalFacing() == EnumFacing.SOUTH) return this.getDefaultState().withProperty(ENUM_EYE, CatsEyeBlockFourWay.EnumCatsEye.roof_s);
			if (placer.getHorizontalFacing() == EnumFacing.WEST)  return this.getDefaultState().withProperty(ENUM_EYE, CatsEyeBlockFourWay.EnumCatsEye.roof_w);
		}
		
		if (facing == EnumFacing.NORTH) {
			if (placer.getHorizontalFacing() == EnumFacing.EAST) return this.getDefaultState().withProperty(ENUM_EYE, CatsEyeBlockFourWay.EnumCatsEye.wall_north_gr);
			if (placer.getHorizontalFacing() == EnumFacing.WEST) return this.getDefaultState().withProperty(ENUM_EYE, CatsEyeBlockFourWay.EnumCatsEye.wall_north_rg);
			return this.getDefaultState().withProperty(ENUM_EYE, CatsEyeBlockFourWay.EnumCatsEye.wall_north_gr);
		}
		if (facing == EnumFacing.EAST)  {
			if (placer.getHorizontalFacing() == EnumFacing.NORTH) return this.getDefaultState().withProperty(ENUM_EYE, CatsEyeBlockFourWay.EnumCatsEye.wall_east_gr);
			if (placer.getHorizontalFacing() == EnumFacing.SOUTH) return this.getDefaultState().withProperty(ENUM_EYE, CatsEyeBlockFourWay.EnumCatsEye.wall_east_rg);
			return this.getDefaultState().withProperty(ENUM_EYE, CatsEyeBlockFourWay.EnumCatsEye.wall_east_gr);
		}
		if (facing == EnumFacing.SOUTH) {
			if (placer.getHorizontalFacing() == EnumFacing.WEST) return this.getDefaultState().withProperty(ENUM_EYE, CatsEyeBlockFourWay.EnumCatsEye.wall_south_gr);
			if (placer.getHorizontalFacing() == EnumFacing.EAST) return this.getDefaultState().withProperty(ENUM_EYE, CatsEyeBlockFourWay.EnumCatsEye.wall_south_rg);
			return this.getDefaultState().withProperty(ENUM_EYE, CatsEyeBlockFourWay.EnumCatsEye.wall_south_gr);
		}
		if (facing == EnumFacing.WEST)  {
			if (placer.getHorizontalFacing() == EnumFacing.SOUTH) return this.getDefaultState().withProperty(ENUM_EYE, CatsEyeBlockFourWay.EnumCatsEye.wall_west_gr);
			if (placer.getHorizontalFacing() == EnumFacing.NORTH) return this.getDefaultState().withProperty(ENUM_EYE, CatsEyeBlockFourWay.EnumCatsEye.wall_west_rg);
			return this.getDefaultState().withProperty(ENUM_EYE, CatsEyeBlockFourWay.EnumCatsEye.wall_west_gr);
		}
		
		return this.getDefaultState().withProperty(ENUM_EYE, CatsEyeBlockFourWay.EnumCatsEye.floor_n);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {ENUM_EYE});
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(ENUM_EYE, CatsEyeBlockFourWay.EnumCatsEye.byMetadata(meta));
    }
	
	@Override
    public int getMetaFromState(IBlockState state) {
        return ((CatsEyeBlockFourWay.EnumCatsEye)state.getValue(ENUM_EYE)).getMetadata();
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
    		if (meta == 0 || meta == 2) { return new AxisAlignedBB(0.125,  -1+getBlockBelowHeight(worldIn, pos), 0.4375, 0.875, -1+getBlockBelowHeight(worldIn, pos)+0.0625, 0.5625); }
    		if (meta == 1 || meta == 3) { return new AxisAlignedBB(0.4375, -1+getBlockBelowHeight(worldIn, pos), 0.125, 0.5625, -1+getBlockBelowHeight(worldIn, pos)+0.0625,  0.875); }
	    	if (meta == 4 || meta == 8) { return new AxisAlignedBB(0.4375, 0.125,  0.9375, 0.5625, 0.875, 1     ); }
	    	if (meta == 5 || meta == 9) { return new AxisAlignedBB(0,      0.125,  0.4375, 0.0625, 0.875, 0.5625); }
	    	if (meta == 6 || meta == 10) { return new AxisAlignedBB(0.4375, 0.125,  0,      0.5625, 0.875, 0.0625); }
	    	if (meta == 7 || meta == 11) { return new AxisAlignedBB(0.9375, 0.125,  0.4375, 1,      0.875, 0.5625); }
	    	if (meta == 12 || meta == 14) { return new AxisAlignedBB(0.125,  0.9375, 0.4375, 0.875,  1.0,   0.5625); }
	    	if (meta == 13 || meta == 15) { return new AxisAlignedBB(0.4375, 0.9375, 0.125,  0.5625, 1.0,   0.875);  }
    	}
    	
    	if (meta >= 12) {
    		return new AxisAlignedBB(0.4375, 0.9375, 0.4375, 0.5625, 1.0, 0.5625);
    	}
    	
    	if (meta == 4 || meta == 8) { return new AxisAlignedBB(0.4375, 0.4375, 0.9375, 0.5625, 0.5625, 1     ); }
    	if (meta == 5 || meta == 9) { return new AxisAlignedBB(0,      0.4375, 0.4375, 0.0625, 0.5625, 0.5625); }
    	if (meta == 6 || meta == 10) { return new AxisAlignedBB(0.4375, 0.4375, 0,      0.5625, 0.5625, 0.0625); }
    	if (meta == 7 || meta == 11) { return new AxisAlignedBB(0.9375, 0.4375, 0.4375, 1,      0.5625, 0.5625); }
    	
    	return new AxisAlignedBB(0.4375, -1+getBlockBelowHeight(worldIn, pos), 0.4375, 0.5625, -1+getBlockBelowHeight(worldIn, pos)+0.0625, 0.5625);
    }
	
	@Override
    public Vec3d getOffset(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		int thisMeta = getMetaFromState(worldIn.getBlockState(pos));
		if (thisMeta <= 3) {
			double offset = 1.0 - getBlockBelowHeight(worldIn, pos);
	        return new Vec3d(0, -offset, 0);
		}
        return new Vec3d(0, 0, 0);
    }
    
	
	public static enum EnumCatsEye implements IStringSerializable {
		floor_n(0, "floor_n"),
		floor_e(1, "floor_e"),
		floor_s(2, "floor_s"),
		floor_w(3, "floor_w"),
		wall_north_rg(4, "wall_north_rg"),
		wall_east_rg (5, "wall_east_rg"),
		wall_south_rg(6, "wall_south_rg"),
		wall_west_rg (7, "wall_west_rg"),
		wall_north_gr(8, "wall_north_gr"),
		wall_east_gr (9, "wall_east_gr"),
		wall_south_gr(10, "wall_south_gr"),
		wall_west_gr (11, "wall_west_gr"),
		roof_n(12, "roof_n"),
		roof_e(13, "roof_e"),
		roof_s(14, "roof_s"),
		roof_w(15, "roof_w");
		
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
