package com.fureniku.roads.datagen;

import com.fureniku.metropolis.datagen.MetroItemModelProvider;
import com.fureniku.roads.FurenikusRoads;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

public class RoadsItemModelProvider extends MetroItemModelProvider {

    public RoadsItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, FurenikusRoads.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //simpleItem()
    }
}
