package com.silvaniastudios.roads.blocks.paint.customs;

import com.silvaniastudios.roads.blocks.PaintColour;
import com.silvaniastudios.roads.blocks.enums.EnumMeta;
import com.silvaniastudios.roads.blocks.paint.properties.PaintGrid;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CustomMetaPaintBlock extends CustomPaintBlock {

    public static final PropertyEnum<EnumMeta> META_ID = PropertyEnum.create("meta", EnumMeta.class);

    public CustomMetaPaintBlock(String name, EnumPaintType paintType, PaintGrid[] grids, String category, int[] subBlocks, PaintColour colour, CreativeTabs tab) {
        super(name, paintType, grids, category, subBlocks, colour, tab);
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        int placedMeta = 0;

        if (placer.getHorizontalFacing().equals(EnumFacing.NORTH)) { placedMeta = meta; }
        if (placer.getHorizontalFacing().equals(EnumFacing.EAST)) {  placedMeta = meta + 1; }
        if (placer.getHorizontalFacing().equals(EnumFacing.SOUTH)) { placedMeta = meta + 2; }
        if (placer.getHorizontalFacing().equals(EnumFacing.WEST)) {  placedMeta = meta + 3; }

        return this.getDefaultState().withProperty(META_ID, EnumMeta.byMetadata(placedMeta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return (state.getValue(META_ID)).getMetadata();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(META_ID, EnumMeta.byMetadata(meta));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {META_ID});
    }
}
