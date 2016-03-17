package co.uk.silvania.roads.client;

import co.uk.silvania.roads.CommonProxy;
import co.uk.silvania.roads.client.render.GrassKerbRenderingHandler;
import co.uk.silvania.roads.client.render.NonRoadBlockRenderingHandler;
import co.uk.silvania.roads.client.render.RoadBlockRenderingHandler;
import co.uk.silvania.roads.client.render.RoadPaintRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	
	public static int roadBlockRenderID;
	public static int nonRoadBlockRenderID;
	public static int roadPaintRenderID;
	public static int grassKerbRenderID;
	
	@Override
	public void registerRenderers() {
		roadBlockRenderID = RenderingRegistry.getNextAvailableRenderId();
		nonRoadBlockRenderID = RenderingRegistry.getNextAvailableRenderId();
		roadPaintRenderID = RenderingRegistry.getNextAvailableRenderId();
		grassKerbRenderID = RenderingRegistry.getNextAvailableRenderId();
		
		RenderingRegistry.registerBlockHandler(roadBlockRenderID, new RoadBlockRenderingHandler());
		RenderingRegistry.registerBlockHandler(nonRoadBlockRenderID, new NonRoadBlockRenderingHandler());
		RenderingRegistry.registerBlockHandler(roadPaintRenderID, new RoadPaintRenderingHandler());
		RenderingRegistry.registerBlockHandler(grassKerbRenderID, new GrassKerbRenderingHandler());
	}

}
