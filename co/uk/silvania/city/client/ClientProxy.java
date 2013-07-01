package co.uk.silvania.city.client;

import co.uk.silvania.city.CommonProxy;
import co.uk.silvania.city.tileentities.TileEntityEscalatorEntity;
import co.uk.silvania.city.tileentities.TileEntityEscalatorRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
        
        @Override
        public void registerRenderThings() {
        	
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEscalatorEntity.class, new TileEntityEscalatorRenderer());
        }
        
}