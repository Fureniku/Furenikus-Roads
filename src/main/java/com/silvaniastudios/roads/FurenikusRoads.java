package com.silvaniastudios.roads;

import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.items.FRItems;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid=FurenikusRoads.MODID, name="Fureniku's Roads", version=FurenikusRoads.VERSION)
public class FurenikusRoads {
	
	public static final String MODID = "furenikusroads";
	public static final String VERSION = "1.0.0";
    
    @SidedProxy(clientSide="com.silvaniastudios.roads.client.ClientProxy", serverSide="com.silvaniastudios.roads.CommonProxy")
    public static CommonProxy proxy;
    
    
	public static CreativeTabs tab_roads = new CreativeTabs("tab_roads") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(FRBlocks.road_block_standard, 1, 11);
		}
	};
	
	public static CreativeTabs tab_paint = new CreativeTabs("tab_paint") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(FRBlocks.line_white_straight_full, 1, 0);
		}
	};
	
	public static CreativeTabs tab_road_parts = new CreativeTabs("tab_road_parts") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(FRBlocks.tactile_crossing_bumps, 1, 0);
		}
	};
	
	public static CreativeTabs tab_tools = new CreativeTabs("tab_tools") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(FRItems.pneumatic_drill, 1, 0);
		}
	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
	}
	
	@EventHandler
    public void postInit(FMLPostInitializationEvent event) {}
    
    @Mod.EventBusSubscriber
	public static class RegistrationHandler {
		
		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			FRItems.register(event.getRegistry());
			FRBlocks.registerItemBlocks(event.getRegistry());
		}
				
		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			FRBlocks.register(event.getRegistry());
		}
		
		@SubscribeEvent
		public static void registerModels(ModelRegistryEvent event) {
			FRItems.registerModels();
			FRBlocks.registerModels();
		}
	}
}
