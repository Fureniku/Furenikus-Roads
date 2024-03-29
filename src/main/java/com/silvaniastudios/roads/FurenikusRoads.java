package com.silvaniastudios.roads;

import com.silvaniastudios.roads.registries.CustomPaintModelRegistry;
import com.silvaniastudios.roads.client.TextureRegistryHandler;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.Loader;
import org.apache.logging.log4j.Logger;

import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.tileentities.recipes.RecipeRegistry;
import com.silvaniastudios.roads.fluids.FRFluids;
import com.silvaniastudios.roads.items.FRItems;
import com.silvaniastudios.roads.items.RoadsFuelHandler;
import com.silvaniastudios.roads.network.ClientGuiUpdatePacket;
import com.silvaniastudios.roads.network.CompactorUpdatePacket;
import com.silvaniastudios.roads.network.FabricatorUpdatePacket;
import com.silvaniastudios.roads.network.PaintGunUpdatePacket;
import com.silvaniastudios.roads.registries.DynamicBlockRegistry;
import com.silvaniastudios.roads.registries.PaintGunItemRegistry;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
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
import scala.Array;
import scala.Dynamic;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Mod(modid=FurenikusRoads.MODID, name="Fureniku's Roads", version=FurenikusRoads.VERSION)
public class FurenikusRoads {
	
	public static final String MODID = "furenikusroads";
	public static final String VERSION = "1.2.6";
	
	@Instance(MODID)
	public static FurenikusRoads instance;
	public static Logger logger;
    
    @SidedProxy(clientSide="com.silvaniastudios.roads.client.ClientProxy", serverSide="com.silvaniastudios.roads.CommonProxy")
    public static CommonProxy proxy;
    
    public static final SimpleNetworkWrapper PACKET_CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);

	public static final boolean genInternalTextures = false; //Set to true to generate a texture set from internal json paint files.
	public static final boolean genJsonFromTextures = false; //Set to true to generate a json file from a texture. Create a folder called "conversions" in root dir, put PNGs in there and magic happens.

    public static CreativeTabs tab_roads = new CreativeTabs("tab_roads") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(FRBlocks.road_block_standard, 1, 11);
		}
	};
	
	public static CreativeTabs tab_sidewalks = new CreativeTabs("tab_sidewalks") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(FRBlocks.street_block_a, 1, 3);
		}
	};
	
	public static CreativeTabs tab_road_parts = new CreativeTabs("tab_road_parts") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(FRBlocks.tactile_crossing_bumps, 1, 0);
		}
	};
	
	public static CreativeTabs tab_tools = new CreativeTabs("tab_tools") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(FRItems.paint_gun, 1, 0);
		}
	};
	
	public static CreativeTabs tab_diagonals = new CreativeTabs("tab_diagonals") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(FRBlocks.road_block_diagonal_1_2, 1, 0);
		}
	};
	
	public static CreativeTabs tab_paint_lines = new CreativeTabs("tab_paint_lines") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(Block.REGISTRY.getObject(new ResourceLocation(MODID, "line_white_straight_thick")), 1, 0);
		}
	};
	
	public static CreativeTabs tab_paint_icons = new CreativeTabs("tab_paint_icons") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(Block.REGISTRY.getObject(new ResourceLocation(MODID, "white_wheelchair_icon")), 1, 0);
		}
	};
	
	public static CreativeTabs tab_paint_letters = new CreativeTabs("tab_paint_letters") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(Block.REGISTRY.getObject(new ResourceLocation(MODID, "paint_letter_white_ab")), 1, 0);
		}
	};
	
	public static CreativeTabs tab_paint_text = new CreativeTabs("tab_paint_text") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(Block.REGISTRY.getObject(new ResourceLocation(MODID, "white_slow")), 1, 0);
		}
	};
	
	public static CreativeTabs tab_paint_junction = new CreativeTabs("tab_paint_junction") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(Block.REGISTRY.getObject(new ResourceLocation(MODID, "white_junction_fork_chevron_mid")), 1, 0);
		}
	};
	
	public static CreativeTabs tab_paint_customs = new CreativeTabs("tab_paint_customs") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(Block.REGISTRY.getObject(new ResourceLocation(MODID, "white_junction_fork_chevron_mid")), 1, 0);
		}
	};

	static {
		FluidRegistry.enableUniversalBucket();
	}

	public static ArrayList<String> plugins = new ArrayList<>();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		for (int i = 0; i < plugins.size(); i++) {
			if (Loader.isModLoaded(plugins.get(i))) {
				Object modMainClass = Loader.instance().getIndexedModList().get(plugins.get(i)).getMod();
				if (modMainClass instanceof IColour) {
					IColour col = (IColour) modMainClass;
					FRBlocks.addNewColour(col.getColourName(), col.getColour().getRGB(), col.getTextFormat());
				}
			}
		}

		if (genJsonFromTextures) {
			TextureRegistryHandler.generateJSONFromTextures();
		}

		CustomPaintModelRegistry.register(event);
		DynamicBlockRegistry.register();
		proxy.preInit();
		logger = event.getModLog();
		if (RoadsConfig.general.genLimestone) { 
			GameRegistry.registerWorldGenerator(new WorldGen(), 3);
		}
	}
	
	@EventHandler
	@SuppressWarnings("deprecation")
	public void init(FMLInitializationEvent event) {
		proxy.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(FurenikusRoads.instance, new GuiHandler());
		PACKET_CHANNEL.registerMessage(PaintGunUpdatePacket.Handler.class, PaintGunUpdatePacket.class, 0, Side.SERVER);
		PACKET_CHANNEL.registerMessage(CompactorUpdatePacket.Handler.class, CompactorUpdatePacket.class, 1, Side.SERVER);
		PACKET_CHANNEL.registerMessage(FabricatorUpdatePacket.Handler.class, FabricatorUpdatePacket.class, 2, Side.SERVER);
		PACKET_CHANNEL.registerMessage(ClientGuiUpdatePacket.Handler.class, ClientGuiUpdatePacket.class, 4, Side.CLIENT);
		FRFluids.registerFluids();
		MinecraftForge.EVENT_BUS.register(FRItems.class);
		
		GameRegistry.registerFuelHandler(new RoadsFuelHandler());
	}
	
	@EventHandler
    public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit();
		PaintGunItemRegistry.init();
		FRBlocks.registerPaintGunEntries();
		RecipeRegistry.init();
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
			System.out.println("register blocks!!");

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
    
    public static void debug(int level, String str) {
		if (RoadsConfig.general.debugLevel >= level) {
			System.out.println("[Fureniku's Roads] " + str);
		}
	}
}
