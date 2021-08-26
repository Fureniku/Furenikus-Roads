package com.silvaniastudios.roads.blocks.paint;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.PaintColour;
import com.silvaniastudios.roads.blocks.enums.IMetaBlockName;
import com.silvaniastudios.roads.blocks.paint.properties.UnlistedPropertyConnection;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SideLinePaintBlock extends PaintBlockBase implements IMetaBlockName {
	
	public static final UnlistedPropertyConnection LEFT_UP = new UnlistedPropertyConnection("left_up");
	public static final UnlistedPropertyConnection LEFT_MID = new UnlistedPropertyConnection("left_mid");
	public static final UnlistedPropertyConnection LEFT_DOWN = new UnlistedPropertyConnection("left_down");
	public static final UnlistedPropertyConnection CENTRAL = new UnlistedPropertyConnection("central");
	public static final UnlistedPropertyConnection RIGHT_UP = new UnlistedPropertyConnection("right_up");
	public static final UnlistedPropertyConnection RIGHT_MID = new UnlistedPropertyConnection("right_mid");
	public static final UnlistedPropertyConnection RIGHT_DOWN = new UnlistedPropertyConnection("right_down");
	
	public static final PropertyDirection FACING =  PropertyDirection.create("zz_facing", EnumFacing.Plane.HORIZONTAL);

	public SideLinePaintBlock(String name, String category, int[] coreMetas, boolean dynamic, PaintColour colour) {
		super(name, category, coreMetas, dynamic, colour);
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
		if (enumfacing == EnumFacing.DOWN || enumfacing == EnumFacing.UP) {
			return this.getDefaultState().withProperty(FACING, EnumFacing.NORTH);
		}
		return this.getDefaultState().withProperty(FACING, enumfacing);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BlockStateContainer createBlockState() {
		IProperty[] listedProperties = new IProperty[] { FACING };
		IUnlistedProperty[] unlistedProperties = new IUnlistedProperty[] { LEFT_UP, LEFT_MID, LEFT_DOWN, CENTRAL, RIGHT_UP, RIGHT_MID, RIGHT_DOWN };
		
		return new ExtendedBlockState(this, listedProperties, unlistedProperties);
	}
	
	//Checks if the block at the position, or directly above/below, is relevant.
	private IBlockState getOffsetState(IBlockAccess world, BlockPos pos) {
		if (world.getBlockState(pos).getBlock() instanceof SideLinePaintBlock) {
			return world.getBlockState(pos);
		} else if (world.getBlockState(pos.offset(EnumFacing.DOWN)).getBlock() instanceof SideLinePaintBlock) {
			return world.getBlockState(pos.offset(EnumFacing.DOWN));
		} else if (world.getBlockState(pos.offset(EnumFacing.UP)).getBlock() instanceof SideLinePaintBlock) {
			return world.getBlockState(pos.offset(EnumFacing.UP));
		}
		return null;
	}

	@Override
	public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
		IExtendedBlockState extendedBlockState = (IExtendedBlockState) state;
		
		BlockPos posUp = pos.offset(EnumFacing.NORTH);
		BlockPos posLeft = pos.offset(EnumFacing.WEST);
		BlockPos posRight = pos.offset(EnumFacing.EAST);
		BlockPos posDown = pos.offset(EnumFacing.SOUTH);
		
		if (state.getValue(FACING) == EnumFacing.EAST) {
			posUp = pos.offset(EnumFacing.EAST);
			posLeft = pos.offset(EnumFacing.NORTH);
			posRight = pos.offset(EnumFacing.SOUTH);
			posDown = pos.offset(EnumFacing.WEST);
		}
		
		if (state.getValue(FACING) == EnumFacing.SOUTH) {
			posUp = pos.offset(EnumFacing.SOUTH);
			posLeft = pos.offset(EnumFacing.EAST);
			posRight = pos.offset(EnumFacing.WEST);
			posDown = pos.offset(EnumFacing.NORTH);
		}
		
		if (state.getValue(FACING) == EnumFacing.WEST) {
			posUp = pos.offset(EnumFacing.WEST);
			posLeft = pos.offset(EnumFacing.SOUTH);
			posRight = pos.offset(EnumFacing.NORTH);
			posDown = pos.offset(EnumFacing.EAST);
		}
		
		IBlockState upState = getOffsetState(world, posUp);
		IBlockState downState = getOffsetState(world, posDown);
		IBlockState leftState = getOffsetState(world, posLeft);
		IBlockState rightState = getOffsetState(world, posRight);
		
		boolean lu = upState != null ? upState.getValue(FACING) == state.getValue(FACING).rotateYCCW() : false;
		boolean lm = leftState != null ? leftState.getValue(FACING) == state.getValue(FACING) : false;
		boolean ld = downState != null ? downState.getValue(FACING) == state.getValue(FACING).rotateYCCW() : false;
		boolean ru = upState != null ? upState.getValue(FACING) == state.getValue(FACING).rotateY() : false;
		boolean rm = rightState != null ? rightState.getValue(FACING) == state.getValue(FACING) : false;
		boolean rd = downState != null ? downState.getValue(FACING) == state.getValue(FACING).rotateY() : false;
		
		boolean central = (lu || lm || ld) && (ru || rm || rd);

		return extendedBlockState.withProperty(LEFT_UP, lu).withProperty(LEFT_MID, lm).withProperty(LEFT_DOWN, ld)
				.withProperty(CENTRAL, central).withProperty(RIGHT_UP, ru).withProperty(RIGHT_MID, rm).withProperty(RIGHT_DOWN, rd);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void initModel() {
		StateMapperBase b = new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				StateMapperBase b = new DefaultStateMapper();
				return new ModelResourceLocation(state.getBlock().getRegistryName(), b.getPropertyString(state.getProperties()));
			}
		};

		ModelLoader.setCustomStateMapper(this, b);
	}
}
