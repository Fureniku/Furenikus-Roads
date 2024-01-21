package com.fureniku.roads.blockentities;

import com.fureniku.metropolis.blockentity.MetroBlockEntity;
import com.fureniku.roads.FurenikusRoads;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class CrusherBlockEntity extends MetroBlockEntity {

    public CrusherBlockEntity(BlockPos pos, BlockState state) {
        super(FurenikusRoads.INSTANCE.registration._registrationMachines.getCrusherEntity().get(), pos, state);
    }
}
