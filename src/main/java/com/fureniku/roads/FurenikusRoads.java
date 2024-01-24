package com.fureniku.roads;

import com.fureniku.metropolis.utils.Debug;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

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
    }
}

