package com.silvaniastudios.roads.blocks.paint;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.enums.IMetaBlockName;
import com.silvaniastudios.roads.blocks.paint.properties.UnlistedPropertyConnection;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class LinePaintBlock extends PaintBlockBase implements IMetaBlockName {
	
	public static final UnlistedPropertyConnection NORTH = new UnlistedPropertyConnection("north");
	public static final UnlistedPropertyConnection EAST = new UnlistedPropertyConnection("east");
	public static final UnlistedPropertyConnection SOUTH = new UnlistedPropertyConnection("south");
	public static final UnlistedPropertyConnection WEST = new UnlistedPropertyConnection("west");
	public static final PropertyEnum<LinePaintBlock.EnumRotation> FACING = PropertyEnum.create("facing", LinePaintBlock.EnumRotation.class);

	public LinePaintBlock(String name, String category, int[] coreMetas, boolean dynamic, String colour) {
		super(name, category, coreMetas, dynamic, colour);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, LinePaintBlock.EnumRotation.ns));
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
		return block instanceof LinePaintBlock || block instanceof ArrowLinePaintBlock;
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
	
	@SuppressWarnings("rawtypes")
	protected BlockStateContainer createBlockState() {
		IProperty[] listedProperties = new IProperty[] {FACING};
		IUnlistedProperty[] unlistedProperties = new IUnlistedProperty[] {NORTH, EAST, WEST, SOUTH};

		return new ExtendedBlockState(this, listedProperties, unlistedProperties);
	}
	
	@Override
	public IBlockState getExtendedState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		IExtendedBlockState extendedBlockState = (IExtendedBlockState) state;
		boolean n = canLineConnectTo(worldIn, pos, EnumFacing.NORTH);
		boolean e = canLineConnectTo(worldIn, pos, EnumFacing.EAST);
		boolean s = canLineConnectTo(worldIn, pos, EnumFacing.SOUTH);
		boolean w = canLineConnectTo(worldIn, pos, EnumFacing.WEST);
		
		if (state.getValue(FACING) == EnumRotation.ns) {
			n = true;
			s = true;
		}
		
		if (state.getValue(FACING) == EnumRotation.ew) {
			e = true;
			w = true;
		}
		
		System.out.printf("Block at %s %s %s has states N: %s E: %s S: %s W: %s \n", pos.getX(), pos.getY(), pos.getZ(), n, e, s, w);

		return extendedBlockState.withProperty(NORTH, n).withProperty(EAST, e).withProperty(SOUTH, s).withProperty(WEST, w);
	}
	
    @SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
    	items.add(new ItemStack(this, 1, 0));
    	items.add(new ItemStack(this, 1, 2));
    }
    
    @SideOnly(Side.CLIENT)
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

	@SideOnly(Side.CLIENT)
	public void initItemModel() {
		Item itemBlock = Item.REGISTRY.getObject(new ResourceLocation(FurenikusRoads.MODID, this.name));
		ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(getRegistryName(), "inventory");
		final int DEFAULT_ITEM_SUBTYPE = 0;
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemBlock, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);
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
