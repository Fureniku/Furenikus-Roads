package com.silvaniastudios.roads;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class FRSounds {
	
	public static SoundEvent pneumatic_drill = new SoundEvent(new ResourceLocation(FurenikusRoads.MODID, "pneumatic_drill"));
	
	public static void register(IForgeRegistry<SoundEvent> event) {
		event.register(pneumatic_drill);
	}

}
