package com.silvaniastudios.roads.blocks.paint.customs;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.PaintColour;

import com.silvaniastudios.roads.blocks.enums.IMetaBlockName;
import com.silvaniastudios.roads.blocks.paint.PaintBlockCustomRenderBase;
import com.silvaniastudios.roads.blocks.paint.properties.PaintGrid;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CustomPaintBlock extends PaintBlockCustomRenderBase implements IMetaBlockName, ICustomBlock {

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	private PaintGrid[] paintGrids;
	private String localizedName;
	private boolean isInternal = false;
	private EnumPaintType paintType;

	public CustomPaintBlock(String name, String localName, EnumPaintType paintType, PaintGrid[] grids, String category, int[] subBlocks, PaintColour colour) {
		super(name, category, subBlocks, false, colour);
		this.paintGrids = grids;
		this.localizedName = localName;
		this.paintType = paintType;
		this.setCreativeTab(FurenikusRoads.tab_paint_customs);
	}

	public CustomPaintBlock(String name, EnumPaintType paintType, PaintGrid[] grids, String category, int[] subBlocks, PaintColour colour, CreativeTabs tab) {
		super(name, category, subBlocks, false, colour);
		this.paintGrids = grids;
		this.paintType = paintType;
		this.setCreativeTab(tab);
		this.isInternal = true;
	}

	public String getInternalName() {
		return name;
	}

	@Override
	public PaintGrid getGrid(int id) {
		return paintGrids[id];
	}
	
	public String getLocalName() {
		return localizedName;
	}
	public boolean isInternal() { return isInternal; }
	public EnumPaintType getPaintType() { return paintType; }

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return (state.getValue(FACING)).getIndex();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing enumfacing = EnumFacing.byIndex(meta);
		if (enumfacing == EnumFacing.DOWN || enumfacing == EnumFacing.UP) {
			return this.getDefaultState().withProperty(FACING, EnumFacing.NORTH);
		}
		return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {FACING});
	}

	public enum EnumPaintType implements IStringSerializable {
		ICON_1x1("icon_1x1"),
		WALL_ICON_1x1("wall_icon_1x1"),
		MULTI_2x1("multi_2x1"),
		MULTI_3x1("multi_3x1"),
		MULTI_4x1("multi_4x1"),
		LARGE_TEXT("large_text");

		private final String name;

		EnumPaintType(String name) {
			this.name = name;
		}

		@Override
		public String getName() {
			return this.name;
		}

		public static EnumPaintType getFromString(String in) {
			if (in.equalsIgnoreCase(ICON_1x1.getName()) || in.equalsIgnoreCase("1x1")) {	return ICON_1x1; }
			if (in.equalsIgnoreCase(WALL_ICON_1x1.getName())) {	return WALL_ICON_1x1; }
			if (in.equalsIgnoreCase(MULTI_2x1.getName())) {	return MULTI_2x1; }
			if (in.equalsIgnoreCase(MULTI_3x1.getName())) {	return MULTI_3x1; }
			if (in.equalsIgnoreCase(MULTI_4x1.getName())) {	return MULTI_4x1; }
			if (in.equalsIgnoreCase(LARGE_TEXT.getName())) { return LARGE_TEXT; }

			return ICON_1x1;
		}
	}
}
