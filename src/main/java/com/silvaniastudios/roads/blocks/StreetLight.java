package com.silvaniastudios.roads.blocks;

import com.silvaniastudios.roads.blocks.enums.EnumSimpleRotation;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class StreetLight extends MetalPost {
	
	public static final PropertyEnum<EnumSimpleRotation> ROTATION = PropertyEnum.create("rotation", EnumSimpleRotation.class);

	public StreetLight(String name) {
		super(name, true, 0.0);
		this.setLightLevel(1.0F);
	}
	
	@Override
	public void setDefaultState() {
		this.setDefaultState(this.blockState.getBaseState().withProperty(ROTATION, EnumSimpleRotation.NORTH));
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		//First try and place it against whatever we're placing it against
		if (facing == EnumFacing.NORTH) { return this.getDefaultState().withProperty(ROTATION, EnumSimpleRotation.NORTH); }
		if (facing == EnumFacing.EAST)  { return this.getDefaultState().withProperty(ROTATION, EnumSimpleRotation.EAST);  }
		if (facing == EnumFacing.SOUTH) { return this.getDefaultState().withProperty(ROTATION, EnumSimpleRotation.SOUTH); }
		if (facing == EnumFacing.WEST)  { return this.getDefaultState().withProperty(ROTATION, EnumSimpleRotation.WEST);  }
		//If that fails, just place it opposite us
		if (placer.getHorizontalFacing() == EnumFacing.NORTH) { return this.getDefaultState().withProperty(ROTATION, EnumSimpleRotation.SOUTH); }
		if (placer.getHorizontalFacing() == EnumFacing.EAST)  { return this.getDefaultState().withProperty(ROTATION, EnumSimpleRotation.WEST);  }
		if (placer.getHorizontalFacing() == EnumFacing.SOUTH) { return this.getDefaultState().withProperty(ROTATION, EnumSimpleRotation.NORTH); }
		if (placer.getHorizontalFacing() == EnumFacing.WEST)  { return this.getDefaultState().withProperty(ROTATION, EnumSimpleRotation.EAST);  }

		return this.getDefaultState();
	}

	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
    	placeLightBlockBelow(world, pos, placer);
    }
	
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
    	destroyLightBlockBelow(worldIn, pos);
    	super.breakBlock(worldIn, pos, state);
    }
	
	@SuppressWarnings("deprecation")
	public void placeLightBlockBelow(World world, BlockPos pos, EntityLivingBase placer) {
		BlockPos posOffset = pos.offset(EnumFacing.DOWN);
    	Block block = world.getBlockState(posOffset).getBlock();
    	
    	int count = 0;
    	
    	while (block == Blocks.AIR && count < 15) {
    		posOffset = posOffset.offset(EnumFacing.DOWN);
    		block = world.getBlockState(posOffset).getBlock();
    		count++;
    		if (block != Blocks.AIR) {
    			Block targetBlock = world.getBlockState(posOffset.offset(EnumFacing.UP)).getBlock();
    			if (targetBlock == Blocks.AIR) {
    				world.setBlockState(posOffset.offset(EnumFacing.UP), FRBlocks.fake_light_source.getStateForPlacement(world, posOffset, EnumFacing.UP, 0, 0, 0, 0, placer), 3);
    			}
    		}
    	}
	}
	
	public void destroyLightBlockBelow(World world, BlockPos pos) {
		BlockPos posOffset = pos.offset(EnumFacing.DOWN);
    	Block block = world.getBlockState(posOffset).getBlock();
    	
    	int count = 0;
    	
    	while (block != FRBlocks.fake_light_source && count < 15) {
    		posOffset = posOffset.offset(EnumFacing.DOWN);
    		block = world.getBlockState(posOffset).getBlock();
    		count++;
    		if (block == FRBlocks.fake_light_source) {
    			world.setBlockToAir(posOffset);
    		}
    	}
	}
	
	@Override
	public AxisAlignedBB getBoxFromState(IBlockState state, IBlockAccess world, BlockPos pos) {
		int meta = getMetaFromState(state);
		
		if (meta == 0) { return new AxisAlignedBB(0.40625,  0.46875, 0.359375, 0.59375,  0.578125, 1); }
		if (meta == 1) { return new AxisAlignedBB(0,        0.46875, 0.40625,  0.640625, 0.578125, 0.59375); }
		if (meta == 2) { return new AxisAlignedBB(0.40625,  0.46875, 0,        0.59375,  0.578125, 0.640625); }
		if (meta == 3) { return new AxisAlignedBB(0.359375, 0.46875, 0.40625,  1,        0.578125, 0.59375); }
		
		return new AxisAlignedBB(0, 0.4375, 0, 1, 1, 1);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {ROTATION});
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		if (meta == 0) { this.getDefaultState().withProperty(ROTATION, EnumSimpleRotation.NORTH); }
		if (meta == 1) { this.getDefaultState().withProperty(ROTATION, EnumSimpleRotation.EAST ); }
		if (meta == 2) { this.getDefaultState().withProperty(ROTATION, EnumSimpleRotation.SOUTH); }
		if (meta == 3) { this.getDefaultState().withProperty(ROTATION, EnumSimpleRotation.WEST ); }
        return this.getDefaultState().withProperty(ROTATION, EnumSimpleRotation.byMetadata(meta));
    }
	
	@Override
    public int getMetaFromState(IBlockState state) {
		 return ((EnumSimpleRotation)state.getValue(ROTATION)).getMetadata();
    }
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state;
	}

}
