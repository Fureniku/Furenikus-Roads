package co.uk.silvania.roads.client;

import co.uk.silvania.roads.CommonProxy;
import co.uk.silvania.roads.client.render.RoadBlockRenderingHandler;
import co.uk.silvania.roads.client.render.RoadPaintRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	
	public static int roadBlockRenderID;
	public static int roadPaintRenderID;
	
	@Override
	public void registerRenderers() {
		roadBlockRenderID = RenderingRegistry.getNextAvailableRenderId();
		roadPaintRenderID = RenderingRegistry.getNextAvailableRenderId();
		
		RenderingRegistry.registerBlockHandler(roadBlockRenderID, new RoadBlockRenderingHandler());
		RenderingRegistry.registerBlockHandler(roadPaintRenderID, new RoadPaintRenderingHandler());
	}

}
