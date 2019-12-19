package com.silvaniastudios.roads.blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;
import com.silvaniastudios.roads.items.FRItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BarrierEdgeBlock extends BlockBase {
	
	public static final PropertyEnum<EnumBarrierRotation> ROTATION = PropertyEnum.create("rotation", EnumBarrierRotation.class);
	public static final PropertyEnum<EnumBarrierSide> LEFT = PropertyEnum.create("left", EnumBarrierSide.class);
	public static final PropertyEnum<EnumBarrierSide> RIGHT = PropertyEnum.create("right", EnumBarrierSide.class);
	public static final PropertyBool POSTS = PropertyBool.create("zpost");
    
    boolean double_sided;
    
    AxisAlignedBB SOUTH_AABB = FULL_BLOCK_AABB;
	AxisAlignedBB WEST_AABB  = FULL_BLOCK_AABB;
	AxisAlignedBB NORTH_AABB = FULL_BLOCK_AABB;
	AxisAlignedBB EAST_AABB  = FULL_BLOCK_AABB;
	AxisAlignedBB FULL_AABB  = FULL_BLOCK_AABB;

	public BarrierEdgeBlock(String name, boolean double_sided) {
		super(name, Material.IRON);
		this.double_sided = double_sided;
		this.setCreativeTab(FurenikusRoads.tab_road_parts);
		this.setHardness(2.0F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(ROTATION, EnumBarrierRotation.NORTH)
				.withProperty(LEFT, EnumBarrierSide.NORMAL)
				.withProperty(RIGHT, EnumBarrierSide.NORMAL)
				.withProperty(POSTS, false));
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
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (playerIn.getHeldItem(hand).getItem() == FRItems.wrench) {
			if (getMetaFromState(state) > 3) {
				worldIn.setBlockState(pos, state.withProperty(POSTS, false));
			} else {
				worldIn.setBlockState(pos, state.withProperty(POSTS, true));
			}
			return true;
		}
        return false;
    }
    
    @SuppressWarnings("deprecation")
	@Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
    	int rot = 0;
    	if (placer.getHorizontalFacing().equals(EnumFacing.EAST))  { rot = 1; }
    	if (placer.getHorizontalFacing().equals(EnumFacing.SOUTH)) { rot = 2; }
    	if (placer.getHorizontalFacing().equals(EnumFacing.WEST))  { rot = 3; }
    	
        return getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta+rot, placer);
    }
    
	@Override
	public IBlockState getStateFromMeta(int meta) {
		boolean post = false;
		if (meta > 3) { 
			post = true;
			meta -= 4;
		} 
		return this.getDefaultState().withProperty(ROTATION, EnumBarrierRotation.byMetadata(meta)).withProperty(POSTS, post);
    }
	
	@Override
    public int getMetaFromState(IBlockState state) {
		EnumBarrierRotation rot = state.getValue(ROTATION);
		int post = state.getValue(POSTS) ? 4 : 0;
        return rot.getMetadata() + post;
    }
    
    @Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
	
	@Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }
    
	private EnumBarrierSide canBarrierConnectTo(IBlockState state, IBlockAccess world, BlockPos pos, boolean checkingLeft) {
		EnumBarrierRotation thisRot = state.getValue(ROTATION);
		
		EnumFacing up = EnumFacing.NORTH;
		EnumFacing left = EnumFacing.WEST;
		EnumFacing right = EnumFacing.EAST;
		EnumFacing down = EnumFacing.SOUTH;
		
		EnumBarrierRotation wrLeft = EnumBarrierRotation.WEST;
		EnumBarrierRotation wrRight = EnumBarrierRotation.EAST;
		
		if (thisRot.equals(EnumBarrierRotation.NORTH)) {
			up = EnumFacing.NORTH;
			left = EnumFacing.WEST;
			right = EnumFacing.EAST;
			down = EnumFacing.SOUTH;
			
			wrLeft = EnumBarrierRotation.WEST;
			wrRight = EnumBarrierRotation.EAST;
		}
		
		if (thisRot.equals(EnumBarrierRotation.EAST)) {
			up = EnumFacing.EAST;
			left = EnumFacing.NORTH;
			right = EnumFacing.SOUTH;
			down = EnumFacing.WEST;
			
			wrLeft = EnumBarrierRotation.NORTH;
			wrRight = EnumBarrierRotation.SOUTH;
		}
		
		if (thisRot.equals(EnumBarrierRotation.SOUTH)) {
			up = EnumFacing.SOUTH;
			left = EnumFacing.EAST;
			right = EnumFacing.WEST;
			down = EnumFacing.NORTH;
			
			wrLeft = EnumBarrierRotation.EAST;
			wrRight = EnumBarrierRotation.WEST;
		}
		
		if (thisRot.equals(EnumBarrierRotation.WEST)) {
			up = EnumFacing.WEST;
			left = EnumFacing.SOUTH;
			right = EnumFacing.NORTH;
			down = EnumFacing.EAST;
			
			wrLeft = EnumBarrierRotation.SOUTH;
			wrRight = EnumBarrierRotation.NORTH;
		}
		
		IBlockState stateUp    = world.getBlockState(pos.offset(up));
		IBlockState stateLeft  = world.getBlockState(pos.offset(left));
		IBlockState stateRight = world.getBlockState(pos.offset(right));
		IBlockState stateDown  = world.getBlockState(pos.offset(down));
		
		boolean upBlock    = stateUp.getBlock() instanceof BarrierEdgeBlock;
		boolean leftBlock  = stateLeft.getBlock() instanceof BarrierEdgeBlock;
		boolean rightBlock = stateRight.getBlock() instanceof BarrierEdgeBlock;
		boolean downBlock  = stateDown.getBlock() instanceof BarrierEdgeBlock;
		
		if (upBlock && leftBlock) {
			if (stateUp.getValue(ROTATION).equals(wrLeft) && stateLeft.getValue(ROTATION).equals(thisRot)) {
				if (checkingLeft) {
					return EnumBarrierSide.CORNER;
				} else {
					return EnumBarrierSide.NONE;
				}
			}
		}
		
		if (upBlock && rightBlock) {
			if (stateUp.getValue(ROTATION).equals(wrRight) && stateRight.getValue(ROTATION).equals(thisRot)) {
				if (checkingLeft) {
					return EnumBarrierSide.NONE;
				} else {
					return EnumBarrierSide.CORNER;
				}
			}
		}
		
		if (downBlock && !leftBlock && !upBlock && checkingLeft) {
			if (stateDown.getValue(ROTATION).equals(wrLeft)) {
				return EnumBarrierSide.DOWN;
			}
		}
		
		if (downBlock && !rightBlock && !upBlock && !checkingLeft) {
			if (stateDown.getValue(ROTATION).equals(wrRight)) {
				return EnumBarrierSide.DOWN;
			}
		}
        
		return EnumBarrierSide.NORMAL;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		EnumBarrierSide left  = canBarrierConnectTo(state, world, pos, true);
		EnumBarrierSide right = canBarrierConnectTo(state, world, pos, false);
		if (left != EnumBarrierSide.NORMAL || right != EnumBarrierSide.NORMAL) {
			double_sided = false;
		}
		
		return state.withProperty(LEFT, left).withProperty(RIGHT,  right);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, ROTATION, LEFT, RIGHT, POSTS);
	}
	
	public void setBoxes(IBlockAccess world, BlockPos pos) {
		SOUTH_AABB = new AxisAlignedBB(0.0D,   -1+getBlockBelowHeight(world, pos), 0.875D, 1.0D,   -1+getBlockBelowHeight(world, pos)+1.25D,   1.0D);
		WEST_AABB  = new AxisAlignedBB(0.0D,   -1+getBlockBelowHeight(world, pos),   0.0D, 0.125D, -1+getBlockBelowHeight(world, pos)+1.25D,   1.0D);
		NORTH_AABB = new AxisAlignedBB(0.0D,   -1+getBlockBelowHeight(world, pos),   0.0D, 1.0D,   -1+getBlockBelowHeight(world, pos)+1.25D, 0.125D);
		EAST_AABB  = new AxisAlignedBB(0.875D, -1+getBlockBelowHeight(world, pos),   0.0D, 1.0D,   -1+getBlockBelowHeight(world, pos)+1.25D,   1.0D);
		FULL_AABB  = new AxisAlignedBB(0.0D,   -1+getBlockBelowHeight(world, pos),   0.0D, 1.0D,   -1+getBlockBelowHeight(world, pos)+1.25D,   1.0D);
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
		setBoxes(world, pos);
		if (!isActualState) {
			state = state.getActualState(world, pos);
		}
		
		int meta = getMetaFromState(state);
		
		boolean n = (meta == 0 || meta == 4);
		boolean e = (meta == 1 || meta == 5);
		boolean s = (meta == 2 || meta == 6);
		boolean w = (meta == 3 || meta == 7);

		EnumBarrierSide left  = state.getValue(LEFT);
		EnumBarrierSide right = state.getValue(RIGHT);
		
		if (n) {
			if (left .equals(EnumBarrierSide.DOWN)) { w = true; }
			if (right.equals(EnumBarrierSide.DOWN)) { e = true; }
		} else if (e) { 
			if (left .equals(EnumBarrierSide.DOWN)) { n = true; }
			if (right.equals(EnumBarrierSide.DOWN)) { s = true; }
		} else if (s) {
			if (left .equals(EnumBarrierSide.DOWN)) { e = true; }
			if (right.equals(EnumBarrierSide.DOWN)) { w = true; }
		} else if (w) {
			if (left .equals(EnumBarrierSide.DOWN)) { s = true; }
			if (right.equals(EnumBarrierSide.DOWN)) { n = true; }
		}
		
		if (n && (left.equals(EnumBarrierSide.CORNER) || right.equals(EnumBarrierSide.CORNER))) { n = false; }
		if (e && (left.equals(EnumBarrierSide.CORNER) || right.equals(EnumBarrierSide.CORNER))) { e = false; }
		if (s && (left.equals(EnumBarrierSide.CORNER) || right.equals(EnumBarrierSide.CORNER))) { s = false; }
		if (w && (left.equals(EnumBarrierSide.CORNER) || right.equals(EnumBarrierSide.CORNER))) { w = false; }
		
		if ((n && e && s) || (n && w && s) || (n && e && w) || (n && e && s && w)) { 
			System.out.println("Broken! NESW: " + n + e + s + w);
		}
		
		if (n) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_AABB);
			if (double_sided) {
				addCollisionBoxToList(pos, entityBox, collidingBoxes, SOUTH_AABB);
			}
		}
		
		if (e) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, EAST_AABB);
			if (double_sided) {
				addCollisionBoxToList(pos, entityBox, collidingBoxes, WEST_AABB);
			}
		}
		
		if (s) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, SOUTH_AABB);
			if (double_sided) {
				addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_AABB);
			}
		}
		
		if (w) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, WEST_AABB);
			if (double_sided) {
				addCollisionBoxToList(pos, entityBox, collidingBoxes, EAST_AABB);
			}
		}
	}
    
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		setBoxes(world, pos);
		int meta = getMetaFromState(state);
		
		boolean n = (meta == 0 || meta == 4);
		boolean e = (meta == 1 || meta == 5);
		boolean s = (meta == 2 || meta == 6);
		boolean w = (meta == 3 || meta == 7);
		
		state = state.getActualState(world, pos);
		EnumBarrierSide left  = state.getValue(LEFT);
		EnumBarrierSide right = state.getValue(RIGHT);
		
		if (double_sided) { return FULL_AABB; }
		
		if (n && left .equals(EnumBarrierSide.DOWN)) { return FULL_AABB; }
		if (n && right.equals(EnumBarrierSide.DOWN)) { return FULL_AABB; }
		
		if (e && left .equals(EnumBarrierSide.DOWN)) { return FULL_AABB; }
		if (e && right.equals(EnumBarrierSide.DOWN)) { return FULL_AABB; }
		
		if (s && left .equals(EnumBarrierSide.DOWN)) { return FULL_AABB; }
		if (s && right.equals(EnumBarrierSide.DOWN)) { return FULL_AABB; }
		
		if (w && left .equals(EnumBarrierSide.DOWN)) { return FULL_AABB; }
		if (w && right.equals(EnumBarrierSide.DOWN)) { return FULL_AABB; }
		
		if (n &&  left.equals(EnumBarrierSide.CORNER)) { return new AxisAlignedBB(0.0D,   -1+getBlockBelowHeight(world, pos), 0.0D,   0.125D, -1+getBlockBelowHeight(world, pos)+1.0D, 0.125D); }
		if (n && right.equals(EnumBarrierSide.CORNER)) { return new AxisAlignedBB(0.875D, -1+getBlockBelowHeight(world, pos), 0.0D,     1.0D, -1+getBlockBelowHeight(world, pos)+1.0D, 0.125D); }
		
		if (e &&  left.equals(EnumBarrierSide.CORNER)) { return new AxisAlignedBB(0.875D, -1+getBlockBelowHeight(world, pos), 0.0D,     1.0D, -1+getBlockBelowHeight(world, pos)+1.0D, 0.125D); }
		if (e && right.equals(EnumBarrierSide.CORNER)) { return new AxisAlignedBB(0.875D, -1+getBlockBelowHeight(world, pos), 0.875D,   1.0D, -1+getBlockBelowHeight(world, pos)+1.0D,   1.0D); }
		
		if (s &&  left.equals(EnumBarrierSide.CORNER)) { return new AxisAlignedBB(0.875D, -1+getBlockBelowHeight(world, pos), 0.875D,   1.0D, -1+getBlockBelowHeight(world, pos)+1.0D,   1.0D); }
		if (s && right.equals(EnumBarrierSide.CORNER)) { return new AxisAlignedBB(  0.0D, -1+getBlockBelowHeight(world, pos), 0.875D, 0.125D, -1+getBlockBelowHeight(world, pos)+1.0D,   1.0D); }
		
		if (w &&  left.equals(EnumBarrierSide.CORNER)) { return new AxisAlignedBB(  0.0D, -1+getBlockBelowHeight(world, pos), 0.875D, 0.125D, -1+getBlockBelowHeight(world, pos)+1.0D,   1.0D); }
		if (w && right.equals(EnumBarrierSide.CORNER)) { return new AxisAlignedBB(  0.0D, -1+getBlockBelowHeight(world, pos),   0.0D, 0.125D, -1+getBlockBelowHeight(world, pos)+1.0D, 0.125D); }
		
		if (n) { return NORTH_AABB; }
		if (e) { return EAST_AABB;  }
		if (s) { return SOUTH_AABB; }
		if (w) { return WEST_AABB;  }
		
        return FULL_AABB;
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
	
	@SideOnly(Side.CLIENT)
	@Override
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0,  new ModelResourceLocation(getRegistryName(), "inventory"));
	}
	
	public enum EnumBarrierSide implements IStringSerializable {
		NORMAL(0, "normal"),
		DOWN(1, "down"),
		CORNER(2, "corner"),
		NONE(3, "none");
		
		private static final EnumBarrierSide[] META_LOOKUP = new EnumBarrierSide[values().length];
		private final int meta;
		private final String name;
		
		private EnumBarrierSide(int meta, String name) {
			this.meta = meta;
			this.name = name;
		}

		@Override
		public String getName() {
			return this.name;
		}
		
		@Override
		public String toString() {
			return getName();
		}
		
		public int getMetadata() {
	        return this.meta;
	    }
		
		public static EnumBarrierSide byMetadata(int meta) {
	        if (meta < 0 || meta >= META_LOOKUP.length) {
	            meta = 0;
	        }
	        
	        return META_LOOKUP[meta];
	    }
		
		static {
	        for (EnumBarrierSide type: values()) {
	            META_LOOKUP[type.getMetadata()] = type;
	        }
	    }
	}
	
	public enum EnumBarrierRotation implements IStringSerializable {
		NORTH(0, "north"),
		EAST (1, "east"),
		SOUTH(2, "south"),
		WEST (3, "west");
		
		private static final EnumBarrierRotation[] META_LOOKUP = new EnumBarrierRotation[values().length];
		private final int meta;
		private final String name;
		
		private EnumBarrierRotation(int meta, String name) {
			this.meta = meta;
			this.name = name;
		}

		@Override
		public String getName() {
			return this.name;
		}
		
		@Override
		public String toString() {
			return getName();
		}
		
		public int getMetadata() {
	        return this.meta;
	    }
		
		public static EnumBarrierRotation byMetadata(int meta) {
	        if (meta < 0 || meta >= META_LOOKUP.length) {
	            meta = 0;
	        }
	        
	        return META_LOOKUP[meta];
	    }
		
		static {
	        for (EnumBarrierRotation type: values()) {
	            META_LOOKUP[type.getMetadata()] = type;
	        }
	    }
	}
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}
}