package co.uk.silvania.roads.client;

import net.minecraftforge.client.MinecraftForgeClient;
import co.uk.silvania.roads.CommonProxy;
import co.uk.silvania.roads.Roads;
import co.uk.silvania.roads.tileentities.*;
import co.uk.silvania.roads.tileentities.blocks.TileEntityRamp1;
import co.uk.silvania.roads.tileentities.entities.*;
import co.uk.silvania.roads.tileentities.renderers.*;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
        
        @Override
        public void registerRenderThings() {
        	
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTrafficLightEntity.class, new TileEntityTrafficLightRenderer(false));
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLightBollardEntity.class, new TileEntityLightBollardRenderer());
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStreetLamp1Entity.class, new TileEntityStreetLamp1Renderer());
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStreetLamp2Entity.class, new TileEntityStreetLamp2Renderer());
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStreetSignEntity.class, new TileEntityStreetSignRenderer());
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBarrierPoleEntity.class, new TileEntityBarrierPoleRenderer());
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBarrierEntity.class, new TileEntityBarrierRenderer());
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBarrierCornerEntity.class, new TileEntityBarrierCornerRenderer());
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRoadSlope1Entity.class, new TileEntityRoadSlopeRenderer());
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRoadSlope2Entity.class, new TileEntityRoadSlope2Renderer());
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRoadPainterEntity.class, new TileEntityRoadPainterRenderer());
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRoadBarrierEntity.class, new TileEntityRoadBarrierRenderer());
        }
        
}