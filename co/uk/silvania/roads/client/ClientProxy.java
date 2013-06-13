package co.uk.silvania.roads.client;

import net.minecraftforge.client.MinecraftForgeClient;
import co.uk.silvania.roads.CommonProxy;
import co.uk.silvania.roads.tileentities.TileEntityTrafficLightBlock;
import co.uk.silvania.roads.tileentities.TileEntityTrafficLightEntity;
import co.uk.silvania.roads.tileentities.TileEntityTrafficLightRenderer;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
        
        @Override
        public void registerRenderThings() {
        	
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTrafficLightEntity.class, new TileEntityTrafficLightRenderer(false));
        }
        
}