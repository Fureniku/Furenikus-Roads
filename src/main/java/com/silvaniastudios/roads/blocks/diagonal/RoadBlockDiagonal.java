package com.silvaniastudios.roads.blocks.diagonal;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.BlockBase;
import com.silvaniastudios.roads.blocks.diagonal.HalfBlock.HalfBlockSide;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RoadBlockDiagonal extends BlockBase {

	public static final PropertyEnum<EnumFacing> FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool TRANSPARENCY = PropertyBool.create("transparency");
			
	public static final UnlistedPropertySideBlock LEFT = new UnlistedPropertySideBlock("left");
	public static final UnlistedPropertySideBlock RIGHT = new UnlistedPropertySideBlock("right");
	public static final UnlistedPropertyBlockPos POS = new UnlistedPropertyBlockPos("pos");

	private String modelName;

	boolean mirror;
	float minWidth;
	float maxWidth;

	public RoadBlockDiagonal(String name, boolean mirror, String modelName, float minWidth, float maxWidth) {
		super(name, Material.ROCK);
		setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(TRANSPARENCY, false));
		this.mirror = mirror;
		this.setCreativeTab(FurenikusRoads.tab_diagonals);
		this.setHarvestLevel("pneumatic_drill", 0);
		this.setHardness(1.0F);
		this.modelName = modelName;
		this.minWidth = minWidth;
		this.maxWidth = maxWidth;
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		BlockPos left = pos.offset(placer.getHorizontalFacing().rotateYCCW());
		BlockPos right = pos.offset(placer.getHorizontalFacing().rotateY());
		
		IBlockState stateLeft = world.getBlockState(left);
		IBlockState stateRight = world.getBlockState(right);
		
		boolean trans = false;
		
		//TODO if (stateLeft.getBlock().getBlockLayer() == BlockRenderLayer.TRANSLUCENT || stateRight.getBlock().getBlockLayer() == BlockRenderLayer.TRANSLUCENT) {
			//trans = true;
		//}
		
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing()).withProperty(TRANSPARENCY, trans);
	}

	public int getMetaFromState(IBlockState state) {
		int add = state.getValue(TRANSPARENCY) ? 4 : 0;
		
		if (state.getValue(FACING).equals(EnumFacing.NORTH)) {
			return 0 + add;
		} else if (state.getValue(FACING).equals(EnumFacing.EAST)) {
			return 1 + add;
		} else if (state.getValue(FACING).equals(EnumFacing.SOUTH)) {
			return 2 + add;
		} else if (state.getValue(FACING).equals(EnumFacing.WEST)) {
			return 3 + add;
		}

		return 0 + add;
	}
	
	public IBlockState getStateFromMeta(int meta) {
		int m = meta;
		boolean t = false;
		
		if (meta > 3) {
			m = meta-4;
			t = true;
		}
		
		if (m == 0) { return this.getDefaultState().withProperty(FACING, EnumFacing.NORTH).withProperty(TRANSPARENCY, t); }
		if (m == 1) { return this.getDefaultState().withProperty(FACING, EnumFacing.EAST).withProperty(TRANSPARENCY, t);  }
		if (m == 2) { return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH).withProperty(TRANSPARENCY, t); }
		return this.getDefaultState().withProperty(FACING, EnumFacing.WEST).withProperty(TRANSPARENCY, t);
	}
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		BlockPos left = pos.offset(state.getValue(FACING).rotateYCCW());
		BlockPos right = pos.offset(state.getValue(FACING).rotateY());
		
		IBlockState stateLeft = worldIn.getBlockState(left);
		IBlockState stateRight = worldIn.getBlockState(right);
		
		boolean trans = false;
		
		//TODO if (stateLeft.getBlock().getBlockLayer() == BlockRenderLayer.TRANSLUCENT || stateRight.getBlock().getBlockLayer() == BlockRenderLayer.TRANSLUCENT) {
			//trans = true;
		//}
		
		worldIn.setBlockState(pos, state.withProperty(TRANSPARENCY, trans));
	}

	@SuppressWarnings("rawtypes")
	protected BlockStateContainer createBlockState() {
		IProperty[] listedProperties = new IProperty[] {FACING, TRANSPARENCY};
		IUnlistedProperty[] unlistedProperties = new IUnlistedProperty[] {LEFT, RIGHT, POS};

		return new ExtendedBlockState(this, listedProperties, unlistedProperties);
	}

	@Override
	public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
		IExtendedBlockState extendedBlockState = (IExtendedBlockState) state;
		IBlockState l = getLeftBlock(state, world, pos);
		IBlockState r = getRightBlock(state, world, pos);

		return extendedBlockState.withProperty(LEFT, l).withProperty(RIGHT, r).withProperty(POS, pos);
	}

	public RayTraceResult collisionRayTrace(IBlockState blockState, World worldIn, BlockPos pos, Vec3d start, Vec3d end) {
        List<RayTraceResult> list = Lists.<RayTraceResult>newArrayList();

        for (AxisAlignedBB axisalignedbb : getBoxList(pos, worldIn, blockState)) {
            list.add(this.rayTrace(pos, start, end, axisalignedbb));
        }

        RayTraceResult raytraceresult1 = null;
        double d1 = 0.0D;

        for (RayTraceResult raytraceresult : list) {
            if (raytraceresult != null) {
                double d0 = raytraceresult.hitVec.squareDistanceTo(end);

                if (d0 > d1) {
                    raytraceresult1 = raytraceresult;
                    d1 = d0;
                }
            }
        }

        return raytraceresult1;
    }

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		return new AxisAlignedBB(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
	}
	
	public ArrayList<AxisAlignedBB> getBoxList(BlockPos pos, World world, IBlockState state) {
		ArrayList<AxisAlignedBB> list = new ArrayList<>();
		
		EnumFacing facing = state.getValue(FACING);
		BlockPos posLeft = pos.offset(facing.rotateYCCW());
		BlockPos posRight = pos.offset(facing.rotateY());
		
		IBlockState leftState = getLeftBlock(state, world, pos);
		IBlockState rightState = getRightBlock(state, world, pos);
		
		double leftHeight = getBlockHeight(world, leftState, posLeft);
		double rightHeight = getBlockHeight(world, rightState, posRight);
		
		boolean leftFluid = leftState != null ? leftState.getBlock() instanceof BlockFluidBase || leftState.getBlock() instanceof BlockLiquid : false;
		boolean rightFluid = rightState != null ? rightState.getBlock() instanceof BlockFluidBase || rightState.getBlock() instanceof BlockLiquid : false;

		if (isAir(leftState, world, posLeft) && isAir(rightState, world, posRight)) {
			list.add(new AxisAlignedBB(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
			return list;
		}

		//air is basically fluid right?
		if (isAir(leftState, world, posLeft)) { leftFluid = true; }
		if (isAir(rightState, world, posRight)) { rightFluid = true; }
		
		if (leftHeight == rightHeight && !leftFluid && !rightFluid) {
			list.add(new AxisAlignedBB(0.0f, 0.0f, 0.0f, 1.0f, leftHeight, 1.0f));
			return list;
		}
		
		float min = minWidth;
		float max = maxWidth;
		
		float width = max - min;
		float eighth = width/8f;
		
		float step1 = 1.00f;
		float step2 = 0.75f;
		float step3 = 0.50f;
		float step4 = 0.25f;
		
		float step = 0.25f;
		
		if (mirror) {
			step1 = 0.25f;
			step2 = 0.50f;
			step3 = 0.75f;
			step4 = 1.00f;
		}

		if (facing.equals(EnumFacing.NORTH)) {
			if (!leftFluid) {
				list.add(new AxisAlignedBB(min, 0.0f, step1 - step, min + (width*0.25) - eighth, leftHeight, step1));
				list.add(new AxisAlignedBB(min, 0.0f, step2 - step, min + (width*0.50) - eighth, leftHeight, step2));
				list.add(new AxisAlignedBB(min, 0.0f, step3 - step, min + (width*0.75) - eighth, leftHeight, step3));
				list.add(new AxisAlignedBB(min, 0.0f, step4 - step, max                - eighth, leftHeight, step4));
			}
			
			if (!rightFluid) {
				list.add(new AxisAlignedBB(min                + eighth, 0.0f, step1 - step, 1.0f, rightHeight, step1));
				list.add(new AxisAlignedBB(min + (width*0.25) + eighth, 0.0f, step2 - step, 1.0f, rightHeight, step2));
				list.add(new AxisAlignedBB(min + (width*0.50) + eighth, 0.0f, step3 - step, 1.0f, rightHeight, step3));
				list.add(new AxisAlignedBB(min + (width*0.75) + eighth, 0.0f, step4 - step, 1.0f, rightHeight, step4));
			}
		}
		
		if (facing.equals(EnumFacing.EAST)) {
			if (!leftFluid) {
				list.add(new AxisAlignedBB(step4 - step, 0.0f, min, step4, leftHeight, min + (width*0.25) - eighth));
				list.add(new AxisAlignedBB(step3 - step, 0.0f, min, step3, leftHeight, min + (width*0.50) - eighth));
				list.add(new AxisAlignedBB(step2 - step, 0.0f, min, step2, leftHeight, min + (width*0.75) - eighth));
				list.add(new AxisAlignedBB(step1 - step, 0.0f, min, step1, leftHeight, max                - eighth));
			}
			
			if (!rightFluid) {
				list.add(new AxisAlignedBB(step4 - step, 0.0f, min +                eighth, step4, rightHeight, 1.0f));
				list.add(new AxisAlignedBB(step3 - step, 0.0f, min + (width*0.25) + eighth, step3, rightHeight, 1.0f));
				list.add(new AxisAlignedBB(step2 - step, 0.0f, min + (width*0.50) + eighth, step2, rightHeight, 1.0f));
				list.add(new AxisAlignedBB(step1 - step, 0.0f, min + (width*0.75) + eighth, step1, rightHeight, 1.0f));
			}
		}

		if (facing.equals(EnumFacing.SOUTH)) {
			if (!leftFluid) {
				list.add(new AxisAlignedBB(1.0 - (min                + eighth), 0.0f, step4 - step, 1, leftHeight, step4));
				list.add(new AxisAlignedBB(1.0 - (min + (width*0.25) + eighth), 0.0f, step3 - step, 1, leftHeight, step3));
				list.add(new AxisAlignedBB(1.0 - (min + (width*0.50) + eighth), 0.0f, step2 - step, 1, leftHeight, step2));
				list.add(new AxisAlignedBB(1.0 - (min + (width*0.75) + eighth), 0.0f, step1 - step, 1, leftHeight, step1));
			}

			if (!rightFluid) {
				list.add(new AxisAlignedBB(min, 0.0, step4 - step, 1.0 - (min + (width*0.25) - eighth), rightHeight, step4));
				list.add(new AxisAlignedBB(min, 0.0, step3 - step, 1.0 - (min + (width*0.50) - eighth), rightHeight, step3));
				list.add(new AxisAlignedBB(min, 0.0, step2 - step, 1.0 - (min + (width*0.75) - eighth), rightHeight, step2));
				list.add(new AxisAlignedBB(min, 0.0, step1 - step, 1.0 - (max                - eighth), rightHeight, step1));
			}
		}
		
		if (facing.equals(EnumFacing.WEST)) {
			if (!leftFluid) {
				list.add(new AxisAlignedBB(step1 - step, 0.0, 1.0 - (min +                eighth), step1, leftHeight, 1));
				list.add(new AxisAlignedBB(step2 - step, 0.0, 1.0 - (min + (width*0.25) + eighth), step2, leftHeight, 1));
				list.add(new AxisAlignedBB(step3 - step, 0.0, 1.0 - (min + (width*0.50) + eighth), step3, leftHeight, 1));
				list.add(new AxisAlignedBB(step4 - step, 0.0, 1.0 - (min + (width*0.75) + eighth), step4, leftHeight, 1));
			}

			if (!rightFluid) {
				list.add(new AxisAlignedBB(step1 - step, 0.0, min, step1, rightHeight, 1.0 - (min + (width*0.25) - eighth)));
				list.add(new AxisAlignedBB(step2 - step, 0.0, min, step2, rightHeight, 1.0 - (min + (width*0.50) - eighth)));
				list.add(new AxisAlignedBB(step3 - step, 0.0, min, step3, rightHeight, 1.0 - (min + (width*0.75) - eighth)));
				list.add(new AxisAlignedBB(step4 - step, 0.0, min, step4, rightHeight, 1.0 - (max                - eighth)));
			}
		}
		
		return list;
	}
	
	public Vec3d[] getLeftVecs(World world, IBlockState state, BlockPos pos) {
		Vec3d vecs[] = new Vec3d[16];
		EnumFacing facing = state.getValue(FACING);

		BlockPos posLeft = pos.offset(facing.rotateYCCW());
		BlockPos posRight = pos.offset(facing.rotateY());

		boolean airLeft = world.getBlockState(posLeft).getBlock().isAir(world.getBlockState(posLeft), world, posLeft);
		boolean airRight = world.getBlockState(posRight).getBlock().isAir(world.getBlockState(posRight), world, posRight);

		double height = getBlockHeight(world, getLeftBlock(state, world, pos), posLeft);

		if (airLeft) {
			height = airRight ? 1 : 0;
		}

		if (getHalfBlock(state, HalfBlockSide.LEFT, posLeft, facing, world).isFluid()) {
			for (int i = 0; i < vecs.length; i++) {
				vecs[i] = new Vec3d(0,0,0);
			}
			return vecs;
		}
		
		double g = 0.0020000000949949026D; //The amount a vanilla drawn box is bigger than the actual collision box
		
		if (mirror) {
			vecs[0] = new Vec3d(0-g, 			0-g, 		0-g);
			vecs[1] = new Vec3d(minWidth+g, 	0-g, 		0-g);
			vecs[2] = new Vec3d(maxWidth+g, 	0-g, 		1+g);
			vecs[3] = new Vec3d(0-g, 			0-g, 		1+g);
			
			vecs[4] = new Vec3d(0-g, 			0-g, 		0-g);
			
			vecs[5] = new Vec3d(0-g, 			height+g, 	0-g);
			vecs[6] = new Vec3d(minWidth+g, 	height+g, 	0-g);
			vecs[7] = new Vec3d(maxWidth+g, 	height+g, 	1+g);
			vecs[8] = new Vec3d(0-g, 			height+g, 	1+g);
			
			vecs[9] = new Vec3d(0-g, 			height+g, 	0-g);
			
			vecs[10] = new Vec3d(0-g, 			height+g, 	1+g);
			
			vecs[11] = new Vec3d(0-g, 			0-g, 		1+g);
			vecs[12] = new Vec3d(maxWidth+g, 	0-g, 		1+g);
			
			vecs[13] = new Vec3d(maxWidth+g, 	height+g, 	1+g);
			vecs[14] = new Vec3d(minWidth+g, 	height+g, 	0-g);
			vecs[15] = new Vec3d(minWidth+g, 	0-g, 		0-g);
		} else {
			vecs[0] = new Vec3d(0-g, 			0-g, 		0-g);
			vecs[1] = new Vec3d(maxWidth+g, 	0-g, 		0-g);
			vecs[2] = new Vec3d(minWidth+g, 	0-g, 		1+g);
			vecs[3] = new Vec3d(0-g, 			0-g, 		1+g);
			
			vecs[4] = new Vec3d(0-g, 			0-g, 		0-g);
			
			vecs[5] = new Vec3d(0-g, 			height+g, 	0-g);
			vecs[6] = new Vec3d(maxWidth+g, 	height+g, 	0-g);
			vecs[7] = new Vec3d(minWidth+g, 	height+g, 	1+g);
			vecs[8] = new Vec3d(0-g, 			height+g, 	1+g);
			
			vecs[9] = new Vec3d(0-g, 			height+g, 	0-g);
			
			vecs[10] = new Vec3d(0-g, 			height+g, 	1+g);
			
			vecs[11] = new Vec3d(0-g, 			0-g, 		1+g);
			vecs[12] = new Vec3d(minWidth+g, 	0-g, 		1+g);
			
			vecs[13] = new Vec3d(minWidth+g, 	height+g, 	1+g);
			vecs[14] = new Vec3d(maxWidth+g, 	height+g, 	0-g);
			vecs[15] = new Vec3d(maxWidth+g, 	0-g, 		0-g);
		}
		
		
		if (facing.equals(EnumFacing.EAST)) {
			vecs = getRotatedVecs(vecs, 270, new Vec3d(2,0,0));
		}
		
		if (facing.equals(EnumFacing.SOUTH)) {
			vecs = getRotatedVecs(vecs, 180, new Vec3d(2,0,2));
		}
		
		if (facing.equals(EnumFacing.WEST)) {
			vecs = getRotatedVecs(vecs, 90, new Vec3d(0,0,2));
		}
		
		return vecs;
	}
	
	public Vec3d[] getRightVecs(World world, IBlockState state, BlockPos pos) {
		Vec3d vecs[] = new Vec3d[16];
		EnumFacing facing = state.getValue(FACING);

		BlockPos posLeft = pos.offset(facing.rotateYCCW());
		BlockPos posRight = pos.offset(facing.rotateY());

		boolean airLeft = world.getBlockState(posLeft).getBlock().isAir(world.getBlockState(posLeft), world, posLeft);
		boolean airRight = world.getBlockState(posRight).getBlock().isAir(world.getBlockState(posRight), world, posRight);

		double height = getBlockHeight(world, getRightBlock(state, world, pos), posRight);

		if (airRight) {
			height = airLeft ? 1 : 0;
		}
		
		if (getHalfBlock(state, HalfBlockSide.RIGHT, posRight, facing, world).isFluid()) {
			for (int i = 0; i < vecs.length; i++) {
				vecs[i] = new Vec3d(0,0,0);
			}
			return vecs;
		}
		
		double g = 0.0020000000949949026D; //The amount a vanilla drawn box is bigger than the actual collision box

		if (mirror) {
			vecs[0] = new Vec3d(minWidth-g, 	0-g, 		0-g);
			vecs[1] = new Vec3d(1+g, 			0-g, 		0-g);
			vecs[2] = new Vec3d(1+g, 			0-g, 		1+g);
			vecs[3] = new Vec3d(maxWidth-g, 	0-g, 		1+g);
			
			vecs[4] = new Vec3d(minWidth-g, 	0-g, 		0-g);
			
			vecs[5] = new Vec3d(minWidth-g, 	height+g, 	0-g);
			vecs[6] = new Vec3d(1+g, 			height+g, 	0-g);
			vecs[7] = new Vec3d(1+g, 			height+g, 	1+g);
			vecs[8] = new Vec3d(maxWidth-g, 	height+g, 	1+g);
			
			vecs[9] = new Vec3d(minWidth-g, 	height+g, 	0-g);
			
			vecs[10] = new Vec3d(maxWidth-g, 	height+g, 	1+g);
			
			vecs[11] = new Vec3d(maxWidth-g, 	0-g, 		1+g);
			vecs[12] = new Vec3d(1+g, 			0-g, 		1+g);
			
			vecs[13] = new Vec3d(1+g, 			height+g, 	1+g);
			vecs[14] = new Vec3d(1+g, 			height+g, 	0-g);
			vecs[15] = new Vec3d(1+g, 			0-g, 		0-g);
		} else {
			vecs[0] = new Vec3d(maxWidth-g, 	0-g, 		0-g);
			vecs[1] = new Vec3d(1+g, 			0-g, 		0-g);
			vecs[2] = new Vec3d(1+g, 			0-g, 		1+g);
			vecs[3] = new Vec3d(minWidth-g, 	0-g, 		1+g);
			
			vecs[4] = new Vec3d(maxWidth-g, 	0-g, 		0-g);
			
			vecs[5] = new Vec3d(maxWidth-g, 	height+g, 	0-g);
			vecs[6] = new Vec3d(1+g, 			height+g, 	0-g);
			vecs[7] = new Vec3d(1+g, 			height+g, 	1+g);
			vecs[8] = new Vec3d(minWidth-g, 	height+g, 	1+g);
			
			vecs[9] = new Vec3d(maxWidth-g, 	height+g, 	0-g);
			
			vecs[10] = new Vec3d(minWidth-g, 	height+g, 	1+g);
			
			vecs[11] = new Vec3d(minWidth-g, 	0-g, 		1+g);
			vecs[12] = new Vec3d(1+g, 			0-g, 		1+g);
			
			vecs[13] = new Vec3d(1+g, 			height+g, 	1+g);
			vecs[14] = new Vec3d(1+g, 			height+g, 	0-g);
			vecs[15] = new Vec3d(1+g, 			0-g, 		0-g);
		}
		

		if (facing.equals(EnumFacing.EAST)) {
			vecs = getRotatedVecs(vecs, 270, new Vec3d(2,0,0));
		}
		
		if (facing.equals(EnumFacing.SOUTH)) {
			vecs = getRotatedVecs(vecs, 180, new Vec3d(2,0,2));
		}
		
		if (facing.equals(EnumFacing.WEST)) {
			vecs = getRotatedVecs(vecs, 90, new Vec3d(0,0,2));
		}
		
		return vecs;
	}
	
	//idk why its being dumb and needing different offsets depending on the rotation but oh well
	public Vec3d[] getRotatedVecs(Vec3d[] vecsIn, int rot, Vec3d offset) {
		Vec3d vecs[] = new Vec3d[vecsIn.length];
		
		for (int i = 0; i < vecsIn.length; i++) {
			Vec3d offsetVec = new Vec3d(vecsIn[i].x + 0.5, vecsIn[i].y + 0.5, vecsIn[i].z + 0.5).rotateYaw((float) Math.toRadians(rot));
			vecs[i] = new Vec3d(offsetVec.x - 0.5 + offset.x, offsetVec.y - 0.5, offsetVec.z - 0.5 + offset.z);
		}
		
		return vecs;
	}


	@Override
	public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
		ArrayList<AxisAlignedBB> list = getBoxList(pos, world, state);
		for (int i = 0; i < list.size(); i++) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, list.get(i));
		}
	}	


	public IBlockState getLeftBlock(IBlockState state, IBlockAccess world, BlockPos pos) {
		EnumFacing facingLeft;

		if (state.getValue(FACING).equals(EnumFacing.NORTH)) {
			facingLeft = EnumFacing.WEST;
		} else if (state.getValue(FACING).equals(EnumFacing.EAST)) {
			facingLeft = EnumFacing.NORTH;
		} else if (state.getValue(FACING).equals(EnumFacing.SOUTH)) {
			facingLeft = EnumFacing.EAST;
		} else {
			facingLeft = EnumFacing.SOUTH;
		}

		IBlockState stateLeft = world.getBlockState(pos.offset(facingLeft));

		return stateLeft;
	}

	public IBlockState getRightBlock(IBlockState state, IBlockAccess world, BlockPos pos) {
		EnumFacing facingRight;

		if (state.getValue(FACING).equals(EnumFacing.NORTH)) {
			facingRight = EnumFacing.EAST;
		} else if (state.getValue(FACING).equals(EnumFacing.EAST)) {
			facingRight = EnumFacing.SOUTH;
		} else if (state.getValue(FACING).equals(EnumFacing.SOUTH)) {
			facingRight = EnumFacing.WEST;
		} else {
			facingRight = EnumFacing.NORTH;
		}

		IBlockState stateRight = world.getBlockState(pos.offset(facingRight));

		return stateRight;
	}

	@SuppressWarnings("deprecation")
	public double getBlockHeight(IBlockAccess world, IBlockState state, BlockPos pos) {
		Block block = state.getBlock();
		if (block instanceof RoadBlockDiagonal || !block.isCollidable()) {
			return 0.0d;
		}
		return block.getBoundingBox(state, world, pos).maxY;
	}

	public IBlockState getRoad(IBlockAccess world, BlockPos pos) {
		return getBlockSide(world, pos, this.mirror);
	}

	public BlockPos getRoadPos(IBlockAccess world, BlockPos pos) {
		return getSidePos(pos, world.getBlockState(pos), this.mirror);
	}

	public IBlockState getSidewalk(IBlockAccess world, BlockPos pos, EnumFacing facing) {
		return getBlockSide(world, pos, !this.mirror);
	}

	public IBlockState getBlockSide(IBlockAccess world, BlockPos pos, boolean left) {
		IBlockState thisState = world.getBlockState(pos);
		return world.getBlockState(getSidePos(pos, thisState, left));
	}

	//pass true to get the pos on the left, false to get the pos on the right
	public BlockPos getSidePos(BlockPos pos, IBlockState state, boolean left) {
		EnumFacing facing = state.getValue(FACING);
		if (facing == EnumFacing.NORTH) {
			return left ? pos.offset(EnumFacing.WEST) : pos.offset(EnumFacing.EAST);
		} else if (facing == EnumFacing.EAST) {
			return left ? pos.offset(EnumFacing.SOUTH) : pos.offset(EnumFacing.NORTH);
		} else if (facing == EnumFacing.SOUTH) {
			return left ? pos.offset(EnumFacing.EAST) : pos.offset(EnumFacing.WEST);
		} else {
			return left ? pos.offset(EnumFacing.NORTH) : pos.offset(EnumFacing.SOUTH);
		}
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		StateMapperBase b = new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation(FurenikusRoads.MODID + ":" + modelName);
			}
		};

		ModelLoader.setCustomStateMapper(this, b);
	}

	@SideOnly(Side.CLIENT)
	public void initItemModel() {
		Item itemBlock = Item.REGISTRY.getObject(new ResourceLocation(FurenikusRoads.MODID, this.name));
		ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(getRegistryName(), "inventory");
		final int DEFAULT_ITEM_SUBTYPE = 0;
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemBlock, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
		if (state != null) {
			if (state.getValue(TRANSPARENCY)) {
				return BlockRenderLayer.TRANSLUCENT == layer;
			}
		}
		
		return getBlockLayer() == layer;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		//if (this.getMetaFromState(state) < 15 && face != EnumFacing.DOWN && face != EnumFacing.UP) { //TODO checks for the sake of other blocks culling.
		return BlockFaceShape.UNDEFINED;
	}

	//Prevent water rendering when up against this
	@SuppressWarnings("deprecation")
	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		if (world.getBlockState(pos.offset(face)).getBlock().getMaterial(world.getBlockState(pos.offset(face))) == Material.WATER) {
			return true;
		}
		return super.doesSideBlockRendering(state, world, pos, face);
	}

	@SuppressWarnings("deprecation")
	public float getSideHeight(IBlockState state, World world, BlockPos pos, HalfBlock.HalfBlockSide side) {
		EnumFacing facing = state.getValue(RoadBlockDiagonal.FACING);
		BlockPos posSide = (side == HalfBlock.HalfBlockSide.LEFT) ? pos.offset(facing.rotateYCCW()) : pos.offset(facing.rotateY());
		IBlockState stateSide = world.getBlockState(posSide);
		if (stateSide.getBlock().isAir(stateSide, world, posSide)) {
			return 0;
		}
		return (float) stateSide.getBlock().getBoundingBox(stateSide, Minecraft.getMinecraft().world, posSide).maxY;
	}

	public HalfBlock getHalfBlock(IBlockState state, HalfBlock.HalfBlockSide side, BlockPos pos, EnumFacing facing, World world) {
		IExtendedBlockState extendedState = (IExtendedBlockState) state;
		return new HalfBlock(extendedState, side, pos, facing, world);
	}
}
