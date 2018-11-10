package com.silvaniastudios.roads;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;

@Mod.EventBusSubscriber
public class CommonProxy {
	
	@Instance
	public static FurenikusRoads instance;
	
	public void registerItemRenderer(Item item, int meta, String id) {}
	
	public void openGui(int guiId) {}
	
	public void init() {}
}
