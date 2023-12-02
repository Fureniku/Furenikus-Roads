package com.fureniku.roads;

import com.fureniku.metropolis.utils.Debug;
import com.fureniku.roads.datagen.DataGeneration;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FurenikusRoads.MODID)
public class FurenikusRoads {

    public static final String MODID = "furenikusroads";

    public static FurenikusRoads INSTANCE;
    public RegistrationRoads registration;

    public FurenikusRoads() {
        INSTANCE = this;
        Debug.registerMod("Fureniku's Roads");

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        registration = new RegistrationRoads(MODID, modEventBus);
        registration.init(modEventBus);

        modEventBus.addListener(DataGeneration::generate);
    }
}

