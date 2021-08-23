package com.silvaniastudios.roads.blocks.paint;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.enums.EnumConnectArrows;
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

public class ArrowLinePaintBlock extends PaintBlockBase implements IMetaBlockName {
	
	//Direction is rotation of the block, not the actual direction to connect in.
	public static final PropertyEnum<EnumConnectArrows> CONNECT = PropertyEnum.create("connect", EnumConnectArrows.class);
	public static final PropertyDirection FACING =  PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	public ArrowLinePaintBlock(String name, String category, int[] coreMetas, boolean dynamic, String colour) {
		super(name, category, coreMetas, dynamic, colour);
		this.setCreativeTab(FurenikusRoads.tab_paint_icons);
		this.setDefaultState(this.blockState.getBaseState()
				.withProperty(CONNECT, EnumConnectArrows.CONNECT_NONE)
				.withProperty(FACING, EnumFacing.NORTH));
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

	private EnumConnectArrows canLineConnectTo(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		
		IBlockState stateNorth = world.getBlockState(pos.offset(EnumFacing.NORTH));
		IBlockState stateEast  = world.getBlockState(pos.offset(EnumFacing.EAST));
		IBlockState stateSouth = world.getBlockState(pos.offset(EnumFacing.SOUTH));
		IBlockState stateWest  = world.getBlockState(pos.offset(EnumFacing.WEST));
		
		BlockPos posNorth = pos.offset(EnumFacing.NORTH);
		BlockPos posSouth = pos.offset(EnumFacing.SOUTH);
		
		boolean blockNorth = stateNorth.getBlock() instanceof ArrowPaintBlock || stateNorth.getBlock() instanceof ArrowLinePaintBlock;
		boolean blockEast  =  stateEast.getBlock() instanceof ArrowPaintBlock ||  stateEast.getBlock() instanceof ArrowLinePaintBlock;
		boolean blockSouth = stateSouth.getBlock() instanceof ArrowPaintBlock || stateSouth.getBlock() instanceof ArrowLinePaintBlock;
		boolean blockWest  =  stateWest.getBlock() instanceof ArrowPaintBlock ||  stateWest.getBlock() instanceof ArrowLinePaintBlock;
		
		boolean blockNorthEast = world.getBlockState(posNorth.offset(EnumFacing.EAST)).getBlock() instanceof ArrowDiagonalPaintBlock;
		boolean blockNorthWest = world.getBlockState(posNorth.offset(EnumFacing.WEST)).getBlock() instanceof ArrowDiagonalPaintBlock;
		boolean blockSouthEast = world.getBlockState(posSouth.offset(EnumFacing.EAST)).getBlock() instanceof ArrowDiagonalPaintBlock;
		boolean blockSouthWest = world.getBlockState(posSouth.offset(EnumFacing.WEST)).getBlock() instanceof ArrowDiagonalPaintBlock;
		
		boolean blockUp = false;
		boolean blockLeft = false;
		boolean blockRight = false;
		
		boolean blockUpLeft = false;
		boolean blockUpRight = false;
		
		if (getMetaFromState(state) == 2) {
			blockUp = blockNorth;
			blockLeft = blockWest;
			blockRight = blockEast;
			
			blockUpLeft = blockNorthWest;
			blockUpRight = blockNorthEast;
		}
		
		if (getMetaFromState(state) == 5) {
			blockUp = blockEast;
			blockLeft = blockNorth;
			blockRight = blockSouth;
			
			blockUpLeft = blockNorthEast;
			blockUpRight = blockSouthEast;
		}
		
		if (getMetaFromState(state) == 3) {
			blockUp = blockSouth;
			blockLeft = blockEast;
			blockRight = blockWest;
			
			blockUpLeft = blockSouthEast;
			blockUpRight = blockSouthWest;
		}
		
		if (getMetaFromState(state) == 4) {
			blockUp = blockWest;
			blockLeft = blockSouth;
			blockRight = blockNorth;
			
			blockUpLeft = blockSouthWest;
			blockUpRight = blockNorthWest;
		}
		
		if (blockUpLeft) { return EnumConnectArrows.CONNECT_LEFT_DIAGONAL; }
		if (blockUpRight) { return EnumConnectArrows.CONNECT_RIGHT_DIAGONAL; }
		
		if (blockUp && blockRight && blockLeft)   { return EnumConnectArrows.CONNECT_LEFT_RIGHT_UP; }
		if (blockUp && blockRight && !blockLeft)  { return EnumConnectArrows.CONNECT_RIGHT_UP; }
		if (blockUp && !blockRight && blockLeft)  { return EnumConnectArrows.CONNECT_LEFT_UP; }
		if (!blockUp && blockRight && blockLeft)  { return EnumConnectArrows.CONNECT_LEFT_RIGHT; }
		if (!blockUp && !blockRight && blockLeft) { return EnumConnectArrows.CONNECT_LEFT; }
		if (!blockUp && blockRight && !blockLeft) { return EnumConnectArrows.CONNECT_RIGHT; }
		if (blockUp && !blockRight && !blockLeft) { return EnumConnectArrows.CONNECT_UP; }

		return EnumConnectArrows.CONNECT_NONE;
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {CONNECT, FACING});
	}

	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(CONNECT, canLineConnectTo(worldIn, pos));
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
}