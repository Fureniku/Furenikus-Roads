package co.uk.silvania.roads;

import net.minecraft.block.Block;
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
		GameRegistry.registerBlock(FRBlocks.lineBlock2, RoadItemBlock.class, "lineBlock2");
		GameRegistry.registerBlock(FRBlocks.lineBlock3, RoadItemBlock.class, "lineBlock3");
		
		GameRegistry.registerBlock(FRBlocks.sidewalk1, RoadItemBlock.class, "sidewalk1");

		GameRegistry.registerBlock(FRBlocks.streetBlocks1, RoadItemBlock.class, "streetBlocks1");
		GameRegistry.registerBlock(FRBlocks.streetBlocks2, RoadItemBlock.class, "streetBlocks2");
		GameRegistry.registerBlock(FRBlocks.streetBlocks3, RoadItemBlock.class, "streetBlocks3");
		GameRegistry.registerBlock(FRBlocks.streetBlocks4, RoadItemBlock.class, "streetBlocks4");
		GameRegistry.registerBlock(FRBlocks.streetBlocks5, RoadItemBlock.class, "streetBlocks5");
		GameRegistry.registerBlock(FRBlocks.streetBlocks6, RoadItemBlock.class, "streetBlocks6");
		GameRegistry.registerBlock(FRBlocks.streetBlocks7, RoadItemBlock.class, "streetBlocks7");
		GameRegistry.registerBlock(FRBlocks.streetBlocks8, RoadItemBlock.class, "streetBlocks8");
		GameRegistry.registerBlock(FRBlocks.streetBlocks9, RoadItemBlock.class, "streetBlocks9");
		GameRegistry.registerBlock(FRBlocks.streetBlocks10, RoadItemBlock.class, "streetBlocks10");
		GameRegistry.registerBlock(FRBlocks.streetBlocks11, RoadItemBlock.class, "streetBlocks11");
		GameRegistry.registerBlock(FRBlocks.streetBlocks12, RoadItemBlock.class, "streetBlocks12");
		GameRegistry.registerBlock(FRBlocks.streetBlocks13, RoadItemBlock.class, "streetBlocks13");
		GameRegistry.registerBlock(FRBlocks.streetBlocks14, RoadItemBlock.class, "streetBlocks14");
		GameRegistry.registerBlock(FRBlocks.streetBlocks15, RoadItemBlock.class, "streetBlocks15");
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
