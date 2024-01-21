package com.fureniku.roads.blockentities;

import com.fureniku.metropolis.blockentity.MetroBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class RoadMachineBlockEntity extends MetroBlockEntity {

    public RoadMachineBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
}
