package com.fureniku.roads.blocks.entityblock;

import com.fureniku.metropolis.blocks.decorative.MetroEntityBlockDecorative;
import com.fureniku.metropolis.datagen.TextureSet;
import com.fureniku.roads.blockentities.CrusherBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public abstract class CrusherEntityBlock extends MetroEntityBlockDecorative {

    public CrusherEntityBlock(Properties props, VoxelShape shape, String modelDir, String modelName, String tag, boolean dynamicShape, TextureSet... textures) {
        super(props, shape, modelDir, modelName, tag, dynamicShape, textures);
    }

    @Override
    @Nullable
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CrusherBlockEntity(pos, state);
    }
}
