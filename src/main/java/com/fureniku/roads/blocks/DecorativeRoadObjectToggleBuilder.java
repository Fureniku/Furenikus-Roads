package com.fureniku.roads.blocks;

import com.fureniku.metropolis.blocks.decorative.builders.MetroBlockDecorativeToggleBuilder;
import com.fureniku.metropolis.enums.BlockOffsetDirection;
import com.fureniku.metropolis.enums.DecorativeBuilderType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class DecorativeRoadObjectToggleBuilder extends MetroBlockDecorativeToggleBuilder {

    public DecorativeRoadObjectToggleBuilder(BlockBehaviour.Properties props) {
        this(props, DecorativeBuilderType.DECORATIVE);
    }

    public DecorativeRoadObjectToggleBuilder(BlockBehaviour.Properties props, DecorativeBuilderType type) {
        super(props, type);
        setOffsetDirection(BlockOffsetDirection.DOWN);
    }
}
