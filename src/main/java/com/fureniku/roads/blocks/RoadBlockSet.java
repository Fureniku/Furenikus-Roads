package com.fureniku.roads.blocks;

import com.fureniku.metropolis.blocks.BlockSet;
import com.fureniku.roads.FurenikusRoads;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class RoadBlockSet extends BlockSet {

    public RoadBlockSet(String name, ResourceLocation texture) {
        super(name, texture, 16, FurenikusRoads.INSTANCE.registration);
    }

    public RoadBlockSet(String name) {
        this(name, null);
    }

    public RoadBlock getHigherBlock(int currentHeight) {
        return currentHeight < _blockCount ? getAtHeight(currentHeight+1) : getAtHeight(0);
    }

    public RoadBlock getLowerBlock(int currentHeight) {
        return currentHeight > 0 ? getAtHeight(currentHeight-1) : getAtHeight(_blockCount-1);
    }

    private RoadBlock getAtHeight(int height) {
        return (RoadBlock) getFromId(height);
    }

    @Override
    protected Supplier<Block> getClassSupplier(int id) {
        return _resourceLocation == null ? () -> new RoadBlock(id, this) : () -> new RoadBlock(id, this, _resourceLocation);
    }
}
