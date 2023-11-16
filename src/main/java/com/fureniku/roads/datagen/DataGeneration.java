package com.fureniku.roads.datagen;

import com.fureniku.metropolis.utils.Debug;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class DataGeneration {

    public static void generate(GatherDataEvent event) {
        Debug.Log("Generating");
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper efh = event.getExistingFileHelper();
        generator.addProvider(event.includeClient(), new RoadsBlockStates(packOutput, efh));
    }
}
