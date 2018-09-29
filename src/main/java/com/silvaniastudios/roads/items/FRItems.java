package com.silvaniastudios.roads.items;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class FRItems {
	
	public static RoadItemBase pneumatic_drill = new RoadItemBase("pneumatic_drill", 1);
	public static RoadItemBase tarmac_cutter = new RoadItemBase("tarmac_cutter", 1);
	public static RoadItemBase impact_wrench = new RoadItemBase("impact_wrench", 1);
	public static RoadItemBase tarmac_fragment_standard = new RoadItemBase("tarmac_fragment_standard", 64);
	
	public static void register(IForgeRegistry<Item> registry) {
		registry.registerAll(
				pneumatic_drill,
				tarmac_cutter,
				impact_wrench,
				tarmac_fragment_standard
		);
	}
	
	public static void registerModels() {
		pneumatic_drill.registerItemModel();
		tarmac_cutter.registerItemModel();
		impact_wrench.registerItemModel();
		tarmac_fragment_standard.registerItemModel();
	}
}
