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
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRoadSlope1Entity.class, new TileEntityRoadSlope1Renderer());
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRoadSlope2Entity.class, new TileEntityRoadSlope2Renderer());
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRoadSlope3Entity.class, new TileEntityRoadSlope3Renderer());
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRoadSlope4Entity.class, new TileEntityRoadSlope4Renderer());
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRoadSlope5Entity.class, new TileEntityRoadSlope5Renderer());
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRoadSlope6Entity.class, new TileEntityRoadSlope6Renderer());
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRoadSlope7Entity.class, new TileEntityRoadSlope7Renderer());
        }
        
}