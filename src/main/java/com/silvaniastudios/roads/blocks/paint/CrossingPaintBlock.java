package com.silvaniastudios.roads.blocks.paint;

import com.silvaniastudios.roads.FurenikusRoads;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CrossingPaintBlock extends PaintBlockBase {
	
	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool EAST = PropertyBool.create("east");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool WEST = PropertyBool.create("west");

	public CrossingPaintBlock(String name, String category, int[] coreMetas, boolean dynamic, String colour) {
		super(name, category, coreMetas, dynamic, colour);
		this.setDefaultState(this.blockState.getBaseState()
				.withProperty(NORTH, false)
				.withProperty(EAST, false)
				.withProperty(SOUTH, false)
				.withProperty(WEST, false));
		this.setCreativeTab(FurenikusRoads.tab_paint_icons);
	}

	private boolean isEdgeAndOpposite(IBlockAccess world, BlockPos pos, EnumFacing facing, EnumFacing oppositeSide) {
		BlockPos offset = pos.offset(facing);
		BlockPos offsetOpposite = pos.offset(oppositeSide);
		IBlockState state = world.getBlockState(offset);
		IBlockState stateOpposite = world.getBlockState(offsetOpposite);
		Block block = state.getBlock();
		Block blockOpposite = stateOpposite.getBlock();
		boolean b1 = block instanceof CrossingPaintBlock;
		boolean b2 = blockOpposite instanceof CrossingPaintBlock;
		if (b1 && !b2) {
			return true;
		}
		return false;
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
		return state.withProperty(NORTH, isEdgeAndOpposite(worldIn, pos, EnumFacing.NORTH, EnumFacing.SOUTH))
					.withProperty(SOUTH, isEdgeAndOpposite(worldIn, pos, EnumFacing.SOUTH, EnumFacing.NORTH))
					.withProperty(EAST, isEdgeAndOpposite(worldIn, pos, EnumFacing.EAST, EnumFacing.WEST))
					.withProperty(WEST, isEdgeAndOpposite(worldIn, pos, EnumFacing.WEST, EnumFacing.EAST));

	}
    
    @SideOnly(Side.CLIENT)
	@Override
	public void initModel() {
    	ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
}
