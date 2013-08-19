package co.uk.silvania.roads.client;

import net.minecraftforge.client.MinecraftForgeClient;
import co.uk.silvania.roads.CommonProxy;
import co.uk.silvania.roads.Roads;
import co.uk.silvania.roads.RoadsConfig;
import co.uk.silvania.roads.block.tess.renderers.ShortRampRenderer;
import co.uk.silvania.roads.tileentities.*;
import co.uk.silvania.roads.tileentities.blocks.TileEntityRamp1;
import co.uk.silvania.roads.tileentities.entities.*;
import co.uk.silvania.roads.tileentities.itemrenderers.*;
import co.uk.silvania.roads.tileentities.renderers.*;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ClientProxy extends CommonProxy {
	
	public static int RoadsRampShortRenderID;
        
	@Override
	public void registerRenderThings() {
        	
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTrafficLightEntity.class, new TileEntityTrafficLightRenderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLightBollardEntity.class, new TileEntityLightBollardRenderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStreetLamp1Entity.class, new TileEntityStreetLamp1Renderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStreetLamp2Entity.class, new TileEntityStreetLamp2Renderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStreetSignEntity.class, new TileEntityStreetSignRenderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBarrierPoleEntity.class, new TileEntityBarrierPoleRenderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBarrierEntity.class, new TileEntityBarrierRenderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBarrierCornerEntity.class, new TileEntityBarrierCornerRenderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRoadSlope1Entity.class, new TileEntityRoadSlopeRenderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRoadSlope2Entity.class, new TileEntityRoadSlope2Renderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRoadSlope3Entity.class, new TileEntityRoadSlope3Renderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRoadSlope4Entity.class, new TileEntityRoadSlope4Renderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRoadSlope5Entity.class, new TileEntityRoadSlope5Renderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRoadSlope6Entity.class, new TileEntityRoadSlope6Renderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRoadPainterEntity.class, new TileEntityRoadPainterRenderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRoadBarrierEntity.class, new TileEntityRoadBarrierRenderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRoadBarrierUpEntity.class, new TileEntityRoadBarrierUpRenderer());
		MinecraftForgeClient.registerItemRenderer(Roads.trafficLight.blockID, new TrafficLightItemRenderer());
		MinecraftForgeClient.registerItemRenderer(Roads.barrierCorner.blockID, new BarrierCornerItemRenderer());
		MinecraftForgeClient.registerItemRenderer(Roads.barrierPole.blockID, new BarrierPoleItemRenderer());
		MinecraftForgeClient.registerItemRenderer(Roads.barrierBlock.blockID, new BarrierItemRenderer());
		MinecraftForgeClient.registerItemRenderer(Roads.lightBollard.blockID, new LightBollardItemRenderer());
		MinecraftForgeClient.registerItemRenderer(Roads.roadPainter.blockID, new RoadPainterItemRenderer());
		MinecraftForgeClient.registerItemRenderer(Roads.streetSign.blockID, new StreetSignItemRenderer());
    }
	
	public void registerRenderers() {
		RoadsRampShortRenderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(RoadsRampShortRenderID, new ShortRampRenderer());
	}
}