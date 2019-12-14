package com.silvaniastudios.roads;

import org.apache.logging.log4j.Logger;

import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.fluids.FRFluids;
import com.silvaniastudios.roads.items.FRItems;
import com.silvaniastudios.roads.items.RoadsFuelHandler;
import com.silvaniastudios.roads.network.PaintGunUpdatePacket;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid=FurenikusRoads.MODID, name="Fureniku's Roads", version=FurenikusRoads.VERSION)
public class FurenikusRoads {
	
	public static final String MODID = "furenikusroads";
	public static final String VERSION = "1.1.0-beta";
	
	@Instance(MODID)
	public static FurenikusRoads instance;
	public static Logger logger;
    
    @SidedProxy(clientSide="com.silvaniastudios.roads.client.ClientProxy", serverSide="com.silvaniastudios.roads.CommonProxy")
    public static CommonProxy proxy;
    
    public static final SimpleNetworkWrapper PACKET_CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
    
    
    public static CreativeTabs tab_roads = new CreativeTabs("tab_roads") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(FRBlocks.road_block_standard, 1, 11);
		}
	};
	
	public static CreativeTabs tab_sidewalks = new CreativeTabs("tab_sidewalks") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(FRBlocks.street_block_a, 1, 3);
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
			return new ItemStack(FRItems.paint_gun, 1, 0);
		}
	};
	
	public static CreativeTabs tab_paint_lines = new CreativeTabs("tab_paint_lines") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(FRBlocks.line_white_straight_thick, 1, 0);
		}
	};
	
	public static CreativeTabs tab_paint_icons = new CreativeTabs("tab_paint_icons") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(FRBlocks.white_wheelchair_icon, 1, 0);
		}
	};
	
	public static CreativeTabs tab_paint_letters = new CreativeTabs("tab_paint_letters") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(FRBlocks.paint_letter_white_ab, 1, 0);
		}
	};
	
	public static CreativeTabs tab_paint_text = new CreativeTabs("tab_paint_text") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(FRBlocks.white_slow, 1, 0);
		}
	};
	
	public static CreativeTabs tab_paint_junction = new CreativeTabs("tab_paint_junction") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(FRBlocks.white_junction_fork_chevron_mid, 1, 0);
		}
	};
	
	static {
		FluidRegistry.enableUniversalBucket();
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit();
		logger = event.getModLog();
		FRBlocks.registerPaintGunEntries();
		GameRegistry.registerWorldGenerator(new WorldGen(), 3);
	}
	
	@EventHandler
	@SuppressWarnings("deprecation")
	public void init(FMLInitializationEvent event) {
		proxy.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(FurenikusRoads.instance, new GuiHandler());
		PACKET_CHANNEL.registerMessage(PaintGunUpdatePacket.Handler.class, PaintGunUpdatePacket.class, 0, Side.SERVER);
		FRFluids.registerFluids();
		MinecraftForge.EVENT_BUS.register(FRItems.class);
		
		GameRegistry.registerFuelHandler(new RoadsFuelHandler());
	}
	
	@EventHandler
    public void postInit(FMLPostInitializationEvent event) {
		
	}
    
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
			FRBlocks.registerTileEntities();
		}
		
		@SubscribeEvent
		public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
			
		}
		
		@SubscribeEvent
		public static void registerModels(ModelRegistryEvent event) {
			FRItems.registerModels();
			FRBlocks.registerModels();
		}
	}
}
