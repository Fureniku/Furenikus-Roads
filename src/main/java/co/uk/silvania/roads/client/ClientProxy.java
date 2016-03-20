package co.uk.silvania.roads.client;

import co.uk.silvania.roads.CommonProxy;
import co.uk.silvania.roads.FlenixRoads;
import co.uk.silvania.roads.client.render.GrassKerbRenderingHandler;
import co.uk.silvania.roads.client.render.GrassRoadRenderingHandler;
import co.uk.silvania.roads.client.render.NonRoadBlockRenderingHandler;
import co.uk.silvania.roads.client.render.RoadBlockRenderingHandler;
import co.uk.silvania.roads.client.render.RoadPaintRenderingHandler;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	
	public static int roadBlockRenderID;
	public static int nonRoadBlockRenderID;
	public static int roadPaintRenderID;
	public static int grassKerbRenderID;
	public static int grassRoadRenderID;
	
	@Override
	public void optifineCheck() {
		if (!FMLClientHandler.instance().hasOptifine()) {
			System.out.println("[FlenixRoads] OptiFine not installed. Everything should work fine :)");
		} else {
			FlenixRoads.over = 0.01;
			System.out.println("###### WARNING: OPTIFINE DETECTED ######");
			System.out.println("###### WARNING: OPTIFINE DETECTED ######");
			System.out.println("[FlenixRoads] Please navigate your options to Video Settings -> Performance Settings,");
			System.out.println("[FlenixRoads] and DISABLE Fast Render. It causes graphical issues with FlenixRoads.");
			System.out.println("###### WARNING: OPTIFINE DETECTED ######");
			System.out.println("###### WARNING: OPTIFINE DETECTED ######");
		}
	}
	
	@Override
	public void registerRenderers() {
		roadBlockRenderID = RenderingRegistry.getNextAvailableRenderId();
		nonRoadBlockRenderID = RenderingRegistry.getNextAvailableRenderId();
		roadPaintRenderID = RenderingRegistry.getNextAvailableRenderId();
		grassKerbRenderID = RenderingRegistry.getNextAvailableRenderId();
		grassRoadRenderID = RenderingRegistry.getNextAvailableRenderId();
		
		RenderingRegistry.registerBlockHandler(roadBlockRenderID, new RoadBlockRenderingHandler());
		RenderingRegistry.registerBlockHandler(nonRoadBlockRenderID, new NonRoadBlockRenderingHandler());
		RenderingRegistry.registerBlockHandler(roadPaintRenderID, new RoadPaintRenderingHandler());
		RenderingRegistry.registerBlockHandler(grassKerbRenderID, new GrassKerbRenderingHandler());
		RenderingRegistry.registerBlockHandler(grassRoadRenderID, new GrassRoadRenderingHandler());
	}

}
