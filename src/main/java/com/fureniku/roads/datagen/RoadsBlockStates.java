package com.fureniku.roads.datagen;

import com.fureniku.metropolis.datagen.MetroBlockStateProvider;
import com.fureniku.roads.FurenikusRoads;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.RegistryObject;

import java.util.Collection;

public class RoadsBlockStates extends MetroBlockStateProvider {

    public RoadsBlockStates(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, FurenikusRoads.MODID, fileHelper);
    }

    @Override
    protected Collection<RegistryObject<Block>> getBlocks() {
        return FurenikusRoads.INSTANCE.registration.getBlockArray().values();
    }
}