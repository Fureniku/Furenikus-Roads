package co.uk.silvania.roads;

import co.uk.silvania.roads.blocks.FRBlocks;
import co.uk.silvania.roads.items.FRItems;
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

@Mod(modid=FlenixRoads.modid, /*dependencies="requiredafter:flenixcities",*/ name="FlenixRoads", version="0.8.0")
public class FlenixRoads {
	
	public static final String modid = "flenixroads";
	
    @Instance(FlenixRoads.modid)
    public static FlenixRoads instance;
    
    @SidedProxy(clientSide="co.uk.silvania.roads.client.ClientProxy", serverSide="co.uk.silvania.roads.CommonProxy")
    public static CommonProxy proxy;
    
	public static CreativeTabs tabRoads = new CreativeTabs("tabRoads") {
		@Override
		public Item getTabIconItem() {
			return new ItemStack(FRBlocks.roadBlockBase1, 1, 11).getItem();
		}
		@Override
		public int func_151243_f() {
			return 11;
		}
	};
	
	public static CreativeTabs tabPaints = new CreativeTabs("tabPaints") {
		@Override
		public Item getTabIconItem() {
			return new ItemStack(FRBlocks.lineBlock1, 1, 0).getItem();
		}
	};
	
	public static CreativeTabs tabSidewalks = new CreativeTabs("tabSidewalks") {
		@Override
		public Item getTabIconItem() {
			return new ItemStack(FRBlocks.sidewalk1, 1, 15).getItem();
		}
		@Override
		public int func_151243_f() {
			return 15;
		}
	};
	
	public static CreativeTabs tabTools = new CreativeTabs("tabTools") {
		@Override
		public Item getTabIconItem() {
			return new ItemStack(FRItems.pneumaticDrill, 1, 0).getItem();
		}
	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		FRBlocks.init();
		FRItems.init();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
}
