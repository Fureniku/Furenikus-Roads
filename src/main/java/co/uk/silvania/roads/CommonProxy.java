package co.uk.silvania.roads;

import co.uk.silvania.roads.blocks.FRBlocks;
import co.uk.silvania.roads.blocks.RoadItemBlock;
import co.uk.silvania.roads.items.FRItems;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {
	
	public void init() {
		registerBlocks();
		registerItems();
		registerRenderers();
		registerTileEntities();
	}
	
	public void registerBlocks() {
		GameRegistry.registerBlock(FRBlocks.roadBlockBase1, RoadItemBlock.class, "roadblockBase1");
		GameRegistry.registerBlock(FRBlocks.lineBlock1, RoadItemBlock.class, "lineBlock1");
	}
	
	public void registerItems() {
		GameRegistry.registerItem(FRItems.tarmacCutter, "tarmacCutter");
		GameRegistry.registerItem(FRItems.impactWrench, "impactWrench");
	}
	
	public void registerRenderers() {
		
	}
	
	public void registerTileEntities() {
		
	}

}
