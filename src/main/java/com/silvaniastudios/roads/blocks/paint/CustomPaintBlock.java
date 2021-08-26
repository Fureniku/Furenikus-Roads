package com.silvaniastudios.roads.blocks.paint;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.PaintColour;
import com.silvaniastudios.roads.blocks.enums.EnumRotatable;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CustomPaintBlock extends PaintBlockBase {
	
	public static final PropertyEnum<EnumRotatable> ROTATE_ID = PropertyEnum.create("wall_paint", EnumRotatable.class);
	
	private boolean[][] paintGrid;
	private String localizedName;

	public CustomPaintBlock(String name, String localName, boolean[][] grid, String category, PaintColour colour) {
		super(name, category, new int[] {0}, false, colour);
		this.paintGrid = grid;
		this.localizedName = localName;
		this.setCreativeTab(FurenikusRoads.tab_paint_customs);
	}
	
	@SideOnly(Side.CLIENT)
	public boolean[][] getGrid() {
		return paintGrid;
	}
	
	public String getLocalName() {
		return localizedName;
	}
	
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		if (facing == EnumFacing.UP) {
			if (placer.getHorizontalFacing() == EnumFacing.NORTH) { return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.FLAT_NORTH); }
			if (placer.getHorizontalFacing() == EnumFacing.EAST) { return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.FLAT_EAST); }
			if (placer.getHorizontalFacing() == EnumFacing.SOUTH) { return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.FLAT_SOUTH); }
			if (placer.getHorizontalFacing() == EnumFacing.WEST) { return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.FLAT_WEST); }
		}
		if (placer.getHorizontalFacing() == EnumFacing.NORTH) { return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.FACE_NORTH); }
		if (placer.getHorizontalFacing() == EnumFacing.EAST) { return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.FACE_EAST); }
		if (placer.getHorizontalFacing() == EnumFacing.SOUTH) { return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.FACE_SOUTH); }
		if (placer.getHorizontalFacing() == EnumFacing.WEST) { return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.FACE_WEST); }

		return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
	}
    
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {ROTATE_ID});
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.byMetadata(meta));
    }
	
	@Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumRotatable)state.getValue(ROTATE_ID)).getMetadata();
    }
	
    
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    	return new ItemStack(this, 1, 0);
    }
	
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		int meta = getMetaFromState(state);
		if (meta == 4) { return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.0625D); }
		if (meta == 5) { return new AxisAlignedBB(0.9375D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D); }
		if (meta == 6) { return new AxisAlignedBB(0.0D, 0.0D, 0.9375D, 1.0D, 1.0D, 1.0D); }
		if (meta == 7) { return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0625D, 1.0D, 1.0D); }
		
		return new AxisAlignedBB(0.0D, -1+getBlockBelowHeight(source, pos), 0.0D, 1.0D, -1+getBlockBelowHeight(source, pos)+0.0625, 1.0D);
    }

    @Override
    public Vec3d getOffset(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
    	int meta = getMetaFromState(state);
    	double offset = 0.0;
    	if (meta < 4) {
    		offset = 1.0 - getBlockBelowHeight(worldIn, pos);
    	}
        return new Vec3d(0, -offset, 0);
    }
    
    @SideOnly(Side.CLIENT)
	public void initModel() {
		StateMapperBase b = new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation(FurenikusRoads.MODID + ":custom_paint");
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
}
