package com.fureniku.roads.blocks;

import com.fureniku.metropolis.blocks.decorative.builders.MetroBlockDecorativeBuilder;
import com.fureniku.metropolis.blocks.decorative.builders.MetroBlockDecorativeToggleBuilder;
import com.fureniku.metropolis.enums.BlockOffsetDirection;
import com.fureniku.metropolis.enums.DecorativeBuilderType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class DecorativeRoadObjectBuilder extends MetroBlockDecorativeBuilder {

    public DecorativeRoadObjectBuilder(BlockBehaviour.Properties props) {
        this(props, DecorativeBuilderType.DECORATIVE);
    }

    public DecorativeRoadObjectBuilder(BlockBehaviour.Properties props, DecorativeBuilderType type) {
        super(props, type);
        setOffsetDirection(BlockOffsetDirection.DOWN);
    }

    public DecorativeRoadObjectBuilder(BlockBehaviour.Properties props, float width, float height) {
        super(props, DecorativeBuilderType.DECORATIVE);
        setOffsetDirection(BlockOffsetDirection.DOWN);
        setShape(width, height);
    }

    public DecorativeRoadObjectBuilder(BlockBehaviour.Properties props, DecorativeBuilderType type, float width, float height) {
        super(props, type);
        setOffsetDirection(BlockOffsetDirection.DOWN);
        setShape(width, height);
    }
}

