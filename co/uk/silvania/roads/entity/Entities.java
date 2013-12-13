package co.uk.silvania.roads.entity;

import co.uk.silvania.roads.Roads;
import cpw.mods.fml.common.registry.EntityRegistry;

public class Entities {
	
	public static void init() {
		EntityRegistry.registerModEntity(EntityBasicCar.class, "EntityBasicCar", 0, Roads.instance, 80, 3, true);
	}

}
