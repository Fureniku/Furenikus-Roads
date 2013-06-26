package co.uk.silvania.roads.client;

import net.minecraftforge.client.MinecraftForgeClient;
import co.uk.silvania.roads.CommonProxy;
import co.uk.silvania.roads.tileentities.*;
import co.uk.silvania.roads.tileentities.entities.*;
import co.uk.silvania.roads.tileentities.renderers.*;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
        
        @Override
        public void registerRenderThings() {
        	
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTrafficLightEntity.class, new TileEntityTrafficLightRenderer(false));
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTrafficHangingEntity.class, new TileEntityTrafficHangingRenderer(false));
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLightBollardEntity.class, new TileEntityLightBollardRenderer());
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStreetLamp1Entity.class, new TileEntityStreetLamp1Renderer());
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStreetLamp2Entity.class, new TileEntityStreetLamp2Renderer());
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStreetSignEntity.class, new TileEntityStreetSignRenderer());
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBarrierPoleEntity.class, new TileEntityBarrierPoleRenderer());
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBarrierEntity.class, new TileEntityBarrierRenderer());
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBarrierCornerEntity.class, new TileEntityBarrierCornerRenderer());
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHalfSlopeEntity.class, new TileEntityHalfSlopeRenderer());
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRoadSlopeEntity.class, new TileEntityRoadSlopeRenderer());
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRoadSlopeDYSEntity.class, new TileEntityRoadSlopeDYSRenderer());
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRoadSlopeSWCEntity.class, new TileEntityRoadSlopeSWCRenderer());
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRoadSlopeSWSEntity.class, new TileEntityRoadSlopeSWSRenderer());
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRoadSlopeSWSSEntity.class, new TileEntityRoadSlopeSWSSRenderer());
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRoadSlopeKerb1Entity.class, new TileEntityRoadSlopeKerb1Renderer());
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRoadSlopeKerb2Entity.class, new TileEntityRoadSlopeKerb2Renderer());
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRoadPainterEntity.class, new TileEntityRoadPainterRenderer());
        }
        
}