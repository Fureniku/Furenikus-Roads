package co.uk.silvania.roads;

import co.uk.silvania.roads.blocks.FRBlocks;
import co.uk.silvania.roads.blocks.IconBlock;
import co.uk.silvania.roads.blocks.PaintItemBlock;
import co.uk.silvania.roads.blocks.RoadItemBlock;
import co.uk.silvania.roads.items.FRItems;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {
	
	public void init() {
		registerBlocks();
		registerItems();
		registerRenderers();
		registerTileEntities();
		optifineCheck();
	}
	
	public void optifineCheck() {}
	
	public void registerBlocks() {
		GameRegistry.registerBlock(FRBlocks.roadBlockBase1, RoadItemBlock.class, "roadblockBase1");
		GameRegistry.registerBlock(FRBlocks.roadBlockConcrete, RoadItemBlock.class, "roadBlockConcrete");
		GameRegistry.registerBlock(FRBlocks.roadBlockConcrete2, RoadItemBlock.class, "roadBlockConcrete2");
		GameRegistry.registerBlock(FRBlocks.roadBlockLight, RoadItemBlock.class, "roadBlockLight");
		GameRegistry.registerBlock(FRBlocks.roadBlockFine, RoadItemBlock.class, "roadBlockFine");
		GameRegistry.registerBlock(FRBlocks.roadBlockDark, RoadItemBlock.class, "roadBlockDark");
		GameRegistry.registerBlock(FRBlocks.roadBlockPale, RoadItemBlock.class, "roadBlockPale");
		GameRegistry.registerBlock(FRBlocks.roadBlockRed, RoadItemBlock.class, "roadBlockRed");
		GameRegistry.registerBlock(FRBlocks.roadBlockBlue, RoadItemBlock.class, "roadBlockBlue");
		GameRegistry.registerBlock(FRBlocks.roadBlockWhite, RoadItemBlock.class, "roadBlockWhite");
		GameRegistry.registerBlock(FRBlocks.roadBlockYellow, RoadItemBlock.class, "roadBlockYellow");
		GameRegistry.registerBlock(FRBlocks.roadBlockMuddy, RoadItemBlock.class, "roadBlockMuddy");
		GameRegistry.registerBlock(FRBlocks.roadBlockMuddyDried, RoadItemBlock.class, "roadBlockMuddyDried");
		
		GameRegistry.registerBlock(FRBlocks.roadBlockStone, RoadItemBlock.class, "roadBlockStone");
		GameRegistry.registerBlock(FRBlocks.roadBlockGrass, RoadItemBlock.class, "roadBlockGrass");
		GameRegistry.registerBlock(FRBlocks.roadBlockDirt, RoadItemBlock.class, "roadBlockDirt");
		GameRegistry.registerBlock(FRBlocks.roadBlockGravel, RoadItemBlock.class, "roadBlockGravel");
		GameRegistry.registerBlock(FRBlocks.roadBlockSand, RoadItemBlock.class, "roadBlockSand");
		
		GameRegistry.registerBlock(FRBlocks.lineBlock1, PaintItemBlock.class, "lineBlock1");
		GameRegistry.registerBlock(FRBlocks.lineBlock2, PaintItemBlock.class, "lineBlock2");
		GameRegistry.registerBlock(FRBlocks.lineBlock3, PaintItemBlock.class, "lineBlock3");
		GameRegistry.registerBlock(FRBlocks.lineBlock4, PaintItemBlock.class, "lineBlock4");
		
		GameRegistry.registerBlock(FRBlocks.iconBlock1, PaintItemBlock.class, "iconBlock1");
		
		GameRegistry.registerBlock(FRBlocks.iconLetters2345, PaintItemBlock.class, "iconLetters2345");
		GameRegistry.registerBlock(FRBlocks.iconLetters6789, PaintItemBlock.class, "iconLetters6789");
		GameRegistry.registerBlock(FRBlocks.iconLettersABCD, PaintItemBlock.class, "iconLettersABCD");
		GameRegistry.registerBlock(FRBlocks.iconLettersEFGH, PaintItemBlock.class, "iconLettersEFGH");
		GameRegistry.registerBlock(FRBlocks.iconLettersIJKL, PaintItemBlock.class, "iconLettersIJKL");
		GameRegistry.registerBlock(FRBlocks.iconLettersMNOP, PaintItemBlock.class, "iconLettersMNOP");
		GameRegistry.registerBlock(FRBlocks.iconLettersQRST, PaintItemBlock.class, "iconLettersQRST");
		GameRegistry.registerBlock(FRBlocks.iconLettersUVWX, PaintItemBlock.class, "iconLettersUVWX");
		GameRegistry.registerBlock(FRBlocks.iconLettersYZ01, PaintItemBlock.class, "iconLettersYZ01");
		GameRegistry.registerBlock(FRBlocks.iconLettersMisc, PaintItemBlock.class, "iconLettersMisc");
		
		GameRegistry.registerBlock(FRBlocks.doubleLineBlock1, PaintItemBlock.class, "doubleLineBlock1");
		GameRegistry.registerBlock(FRBlocks.doubleLineBlock2, PaintItemBlock.class, "doubleLineBlock2");
		
		GameRegistry.registerBlock(FRBlocks.sidewalkGrass, RoadItemBlock.class, "sidewalkGrass");
		
		GameRegistry.registerBlock(FRBlocks.sidewalk1, RoadItemBlock.class, "sidewalk1");
		GameRegistry.registerBlock(FRBlocks.sidewalk2, RoadItemBlock.class, "sidewalk2");
		GameRegistry.registerBlock(FRBlocks.sidewalk3, RoadItemBlock.class, "sidewalk3");
		GameRegistry.registerBlock(FRBlocks.sidewalk4, RoadItemBlock.class, "sidewalk4");

		GameRegistry.registerBlock(FRBlocks.streetBlocks, PaintItemBlock.class, "streetBlocks");
	}
	
	public void registerItems() {
		GameRegistry.registerItem(FRItems.pneumaticDrill, "pneumaticDrill");
		GameRegistry.registerItem(FRItems.tarmacCutter, "tarmacCutter");
		GameRegistry.registerItem(FRItems.impactWrench, "impactWrench");
		GameRegistry.registerItem(FRItems.tarmacFragments, "tarmacFragments");
	}
	
	public void registerRenderers() {
		
	}
	
	public void registerTileEntities() {
		
	}

}
