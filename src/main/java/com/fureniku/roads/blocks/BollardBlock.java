package com.fureniku.roads.blocks;

import com.fureniku.metropolis.blocks.MetroBlockDecorative;
import com.fureniku.metropolis.datagen.TextureSet;
import net.minecraft.resources.ResourceLocation;

public class BollardBlock extends MetroBlockDecorative {

    public BollardBlock(Properties props, float height, float width, String modelName, ResourceLocation texture) {
        this(props, height, width, modelName, new TextureSet("texture", texture), new TextureSet("top", texture));
    }

    public BollardBlock(Properties props, float height, float width, String modelName, TextureSet... textures) {
        super(props, height, width, modelName, textures);
    }
}
