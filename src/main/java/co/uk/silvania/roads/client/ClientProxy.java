package co.uk.silvania.roads.client;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import co.uk.silvania.roads.CommonProxy;
import co.uk.silvania.roads.blocks.FRBlocks;
import co.uk.silvania.roads.client.render.NonRoadBlockRenderingHandler;
import co.uk.silvania.roads.client.render.RoadBlockItemRenderingHandler;
import co.uk.silvania.roads.client.render.RoadBlockRenderingHandler;
import co.uk.silvania.roads.client.render.RoadPaintRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	
	public static int roadBlockRenderID;
	public static int nonRoadBlockRenderID;
	public static int roadPaintRenderID;
	
	@Override
	public void registerRenderers() {
		roadBlockRenderID = RenderingRegistry.getNextAvailableRenderId();
		nonRoadBlockRenderID = RenderingRegistry.getNextAvailableRenderId();
		roadPaintRenderID = RenderingRegistry.getNextAvailableRenderId();
		
		RenderingRegistry.registerBlockHandler(roadBlockRenderID, new RoadBlockRenderingHandler());
		RenderingRegistry.registerBlockHandler(nonRoadBlockRenderID, new NonRoadBlockRenderingHandler());
		RenderingRegistry.registerBlockHandler(roadPaintRenderID, new RoadPaintRenderingHandler());
		MinecraftForgeClient.registerItemRenderer(new ItemStack(FRBlocks.roadBlockBase1).getItem(), new RoadBlockItemRenderingHandler());
	}

}
