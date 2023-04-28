package com.silvaniastudios.roads.blocks.paint.customs;

import com.silvaniastudios.roads.blocks.PaintColour;
import com.silvaniastudios.roads.blocks.enums.EnumRotatable;
import com.silvaniastudios.roads.blocks.paint.properties.PaintGrid;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CustomPaintWallBlock extends CustomPaintBlock {

    public static final PropertyEnum<EnumRotatable> ROTATE_ID = PropertyEnum.create("wall_paint", EnumRotatable.class);

    public CustomPaintWallBlock(String name, String localName, PaintGrid[] grids, String category, int[] subMetas, PaintColour colour) {
        super(name, localName, EnumPaintType.WALL_ICON_1x1, grids, category, subMetas, colour);
    }

    public CustomPaintWallBlock(String name, PaintGrid[] grids, String category, int[] subMetas, PaintColour colour, CreativeTabs tab) {
        super(name, EnumPaintType.WALL_ICON_1x1, grids, category, subMetas, colour, tab);
        this.setCreativeTab(tab);
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        if (meta < 8) {
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
        } else {
            if (facing == EnumFacing.UP) {
                if (placer.getHorizontalFacing() == EnumFacing.NORTH) { return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.FLAT_NORTH_2); }
                if (placer.getHorizontalFacing() == EnumFacing.EAST) { return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.FLAT_EAST_2); }
                if (placer.getHorizontalFacing() == EnumFacing.SOUTH) { return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.FLAT_SOUTH_2); }
                if (placer.getHorizontalFacing() == EnumFacing.WEST) { return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.FLAT_WEST_2); }
            }
            if (placer.getHorizontalFacing() == EnumFacing.NORTH) { return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.FACE_NORTH_2); }
            if (placer.getHorizontalFacing() == EnumFacing.EAST) { return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.FACE_EAST_2); }
            if (placer.getHorizontalFacing() == EnumFacing.SOUTH) { return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.FACE_SOUTH_2); }
            if (placer.getHorizontalFacing() == EnumFacing.WEST) { return this.getDefaultState().withProperty(ROTATE_ID, EnumRotatable.FACE_WEST_2); }
        }

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

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        int meta = getMetaFromState(state);
        if (meta < 4 || (meta > 7 && meta < 12)) {
            return new AxisAlignedBB(0.0D, -1+getBlockBelowHeight(source, pos), 0.0D, 1.0D, -1+getBlockBelowHeight(source, pos)+0.0625, 1.0D);
        }
        if (meta == 4 || meta == 12) { return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.0625D); }
        if (meta == 5 || meta == 13) { return new AxisAlignedBB(0.9375D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D); }
        if (meta == 6 || meta == 14) { return new AxisAlignedBB(0.0D, 0.0D, 0.9375D, 1.0D, 1.0D, 1.0D); }
        if (meta == 7 || meta == 15) { return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0625D, 1.0D, 1.0D); }

        return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0625, 1.0D);
    }

    @Override
    public Vec3d getOffset(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        int meta = getMetaFromState(state);
        double offset = 0.0;
        if (meta < 4 || (meta > 7 && meta < 12)) {
            offset = 1.0 - getBlockBelowHeight(worldIn, pos);
        }
        return new Vec3d(0, -offset, 0);
    }
}
