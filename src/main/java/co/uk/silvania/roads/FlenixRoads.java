package co.uk.silvania.roads;

import co.uk.silvania.roads.blocks.FRBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=FlenixRoads.modid, /*dependencies="requiredafter:flenixcities",*/ name="FlenixRoads", version="1.0.0")
public class FlenixRoads {
	
	public static final String modid = "flenixroads";
	
    @Instance(FlenixRoads.modid)
    public static FlenixRoads instance;
    
    @SidedProxy(clientSide="co.uk.silvania.roads.client.ClientProxy", serverSide="co.uk.silvania.roads.CommonProxy")
    public static CommonProxy proxy;
    
	public static CreativeTabs tabRoads = new CreativeTabs("tabRoads") {
		@Override
		public Item getTabIconItem() {
			return new ItemStack(Items.apple, 1, 0).getItem();
		}
	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		FRBlocks.init();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
}
