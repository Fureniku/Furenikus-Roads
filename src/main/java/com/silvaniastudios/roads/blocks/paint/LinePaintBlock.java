package com.silvaniastudios.roads.blocks.paint;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.enums.ILineConnectable;
import com.silvaniastudios.roads.blocks.enums.IMetaBlockName;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class LinePaintBlock extends PaintBlockBase implements ILineConnectable, IMetaBlockName {
	
	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool EAST = PropertyBool.create("east");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool WEST = PropertyBool.create("west");
	//public static final PropertyBool SIDES = PropertyBool.create("sides");
	public static final PropertyBool DEFAULTS = PropertyBool.create("zz_default_stuff");
	public static final PropertyEnum<LinePaintBlock.EnumRotation> FACING = PropertyEnum.create("facing", LinePaintBlock.EnumRotation.class);

	public LinePaintBlock(String name) {
		super(name);
		this.setDefaultState(this.blockState.getBaseState()
				.withProperty(NORTH, false)
				.withProperty(EAST, false)
				.withProperty(SOUTH, false)
				.withProperty(WEST, false)
				.withProperty(DEFAULTS, true)
				.withProperty(FACING, LinePaintBlock.EnumRotation.ns));
		this.setCreativeTab(FurenikusRoads.tab_paint_lines);
	}
	
	@Override
	public String getSpecialName(ItemStack stack) {
		return stack.getItemDamage() + "";
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		if (meta < 2) {
			if (placer.getHorizontalFacing() == EnumFacing.EAST || placer.getHorizontalFacing() == EnumFacing.WEST) {
				return this.getDefaultState().withProperty(FACING, LinePaintBlock.EnumRotation.ew);
			}
			return this.getDefaultState().withProperty(FACING, LinePaintBlock.EnumRotation.ns);
		}
		return this.getDefaultState().withProperty(FACING, LinePaintBlock.EnumRotation.connect);
	}

	public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos, EnumFacing facing) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();
		return block instanceof ILineConnectable;
	}

	private boolean canLineConnectTo(IBlockAccess world, BlockPos pos, EnumFacing facing) {
		BlockPos offset = pos.offset(facing);
		return canConnectTo(world, offset, facing.getOpposite()) || canConnectTo(world, offset.offset(EnumFacing.DOWN), facing.getOpposite()) || canConnectTo(world, offset.offset(EnumFacing.UP), facing.getOpposite());
	}
	
	public int getMetaFromState(IBlockState state) {
		if (state.getValue(FACING).equals(LinePaintBlock.EnumRotation.ns)) {
			return 0;
		} else if (state.getValue(FACING).equals(LinePaintBlock.EnumRotation.ew)) {
			return 1;
		} else {
			return 2;
		}
	}
	
	public IBlockState getStateFromMeta(int meta) {
		if (meta == 0) { return this.getDefaultState().withProperty(FACING, LinePaintBlock.EnumRotation.ns); }
		if (meta == 1) { return this.getDefaultState().withProperty(FACING, LinePaintBlock.EnumRotation.ew); }
		return this.getDefaultState().withProperty(FACING, LinePaintBlock.EnumRotation.connect);
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {NORTH, EAST, WEST, SOUTH, DEFAULTS, FACING});
	}

	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(NORTH, canLineConnectTo(worldIn, pos, EnumFacing.NORTH))
			.withProperty(EAST,  canLineConnectTo(worldIn, pos, EnumFacing.EAST))
			.withProperty(SOUTH, canLineConnectTo(worldIn, pos, EnumFacing.SOUTH))
			.withProperty(WEST,  canLineConnectTo(worldIn, pos, EnumFacing.WEST));
	}
    
    @SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
    	items.add(new ItemStack(this, 1, 0));
    	items.add(new ItemStack(this, 1, 2));
    }
	
    @SideOnly(Side.CLIENT)
	@Override
	public void initModel() {
    	ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    	ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 2, new ModelResourceLocation(getRegistryName(), "inventory_2"));
    	ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 3, new ModelResourceLocation(getRegistryName(), "inventory_2"));
	}
    
    public static enum EnumRotation implements IStringSerializable {
    	ns("north_south"),
		ew("east_west"),
		connect("connect");
    	
		private final String name;
		
		private EnumRotation(String name) {
			this.name = name;
		}

		@Override
		public String getName() {
			return this.name;
		}
		
		public static EnumRotation byMetadata(int meta) {
	        if (meta == 0) { return EnumRotation.ns; }
	        if (meta == 1) { return EnumRotation.ew; }
	        return EnumRotation.connect;
	    }
    }
}
