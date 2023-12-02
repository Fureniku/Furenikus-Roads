package com.fureniku.roads.datagen;

import com.fureniku.metropolis.datagen.MetroBlockStateProvider;
import com.fureniku.metropolis.utils.Debug;
import com.fureniku.roads.FurenikusRoads;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class DataGeneration {

    public static void generate(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper efh = event.getExistingFileHelper();
        generator.addProvider(event.includeClient(), new MetroBlockStateProvider(packOutput, FurenikusRoads.MODID, efh, FurenikusRoads.INSTANCE.registration));
    }
}
