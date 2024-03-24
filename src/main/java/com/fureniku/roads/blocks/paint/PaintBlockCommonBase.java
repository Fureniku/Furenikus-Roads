package com.fureniku.roads.blocks.paint;

import com.fureniku.metropolis.blocks.decorative.MetroBlockDecorativeBase;
import com.fureniku.metropolis.datagen.TextureSet;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class PaintBlockCommonBase extends MetroBlockDecorativeBase {

    public PaintBlockCommonBase(Properties props, VoxelShape shape, String modelDir, String modelName, String tag, boolean dynamicShape, TextureSet... textures) {
        super(props, shape, modelDir, modelName, tag, dynamicShape, textures);
    }
}
