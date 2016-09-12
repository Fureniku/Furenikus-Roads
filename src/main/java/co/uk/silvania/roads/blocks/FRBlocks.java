package co.uk.silvania.roads.blocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class FRBlocks {
	
	//Tarmac variants
	public static Block roadBlockBase1;
	public static Block roadBlockConcrete;
	public static Block roadBlockConcrete2;
	public static Block roadBlockLight;
	public static Block roadBlockFine;
	public static Block roadBlockDark;
	public static Block roadBlockPale;
	public static Block roadBlockRed;
	public static Block roadBlockBlue;
	public static Block roadBlockWhite;
	public static Block roadBlockYellow;
	public static Block roadBlockGreen;
	public static Block roadBlockMuddy;
	public static Block roadBlockMuddyDried;
	
	public static Block roadBlockStone;
	public static Block roadBlockGrass;
	public static Block roadBlockDirt;
	public static Block roadBlockGravel;
	public static Block roadBlockSand;
	
	//Other "road blocks"
	public static Block sidewalkGrass;
	
	public static Block sidewalk1;
	public static Block sidewalk2;
	public static Block sidewalk3;
	public static Block sidewalk4;
	
	public static Block streetBlocks;
	
	public static Block lineBlock1;
	public static Block lineBlock2;
	public static Block lineBlock3;
	public static Block lineBlock4;
	public static Block lineBlock5;
	public static Block lineBlock6;
	
	public static Block doubleLineBlock1;
	public static Block doubleLineBlock2;
	public static Block doubleLineBlock3;
	
	public static Block sideDoubleYellow;
	public static Block sideSingleYellow;
	public static Block sideDoubleWhite;
	public static Block sideSingleWhite;
	public static Block sideDoubleRed;
	public static Block sideSingleRed;
	
	public static Block iconBlock1;
	public static Block iconBlock2;
	public static Block iconBlock3;
	public static Block iconBlock4;
	public static Block iconBlock5;
	public static Block iconBlock6;
	public static Block iconBlock7;
	
	public static Block iconLetters2345;
	public static Block iconLetters6789;
	public static Block iconLettersABCD;
	public static Block iconLettersEFGH;
	public static Block iconLettersIJKL;
	public static Block iconLettersMNOP;
	public static Block iconLettersQRST;
	public static Block iconLettersUVWX;
	public static Block iconLettersYZ01;
	public static Block iconLettersMisc;
	
	public static void init() {
		roadBlocks();
	}
	
	public static void roadBlocks() {
		roadBlockBase1 = new RoadBlock().setBlockName("roadBlockBase1");
		roadBlockConcrete = new RoadBlock().setBlockName("roadBlockConcrete");
		roadBlockConcrete2 = new RoadBlock().setBlockName("roadBlockConcrete2");
		roadBlockLight = new RoadBlock().setBlockName("roadBlockLight");
		roadBlockFine = new RoadBlock().setBlockName("roadBlockFine");
		roadBlockDark = new RoadBlock().setBlockName("roadBlockDark");
		roadBlockPale = new RoadBlock().setBlockName("roadBlockPale");
		roadBlockRed = new RoadBlock().setBlockName("roadBlockRed");
		roadBlockBlue = new RoadBlock().setBlockName("roadBlockBlue");
		roadBlockWhite = new RoadBlock().setBlockName("roadBlockWhite");
		roadBlockYellow = new RoadBlock().setBlockName("roadBlockYellow");
		roadBlockGreen = new RoadBlock().setBlockName("roadBlockGreen");
		roadBlockMuddy = new RoadBlock().setBlockName("roadBlockMuddy");
		roadBlockMuddyDried = new RoadBlock().setBlockName("roadBlockMuddyDried");
		
		roadBlockStone = new VanillaRoadBlock(Blocks.stone).setBlockName("roadBlockStone");
		roadBlockGrass = new GrassRoadBlock().setBlockName("roadBlockGrass");
		roadBlockDirt = new VanillaRoadBlock(Blocks.dirt).setBlockName("roadBlockDirt");
		roadBlockGravel = new VanillaRoadBlock(Blocks.gravel).setBlockName("roadBlockGravel");
		roadBlockSand = new VanillaRoadBlock(Blocks.sand).setBlockName("roadBlockSand");
		
		sidewalkGrass = new GrassKerb().setBlockName("sidewalkGrass");
		
		sidewalk1 = new NonRoadBlockCT().setBlockName("sidewalk1");
		sidewalk2 = new NonRoadBlockCT().setBlockName("sidewalk2");
		sidewalk3 = new NonRoadBlockCT().setBlockName("sidewalk3");
		sidewalk4 = new NonRoadBlockCT().setBlockName("sidewalk4");
		
		streetBlocks = new SimpleBlock().setBlockName("streetBlocks");
		
		lineBlock1 = new LineBlock().setBlockName("lineBlock1");
		lineBlock2 = new LineBlock().setBlockName("lineBlock2");
		lineBlock3 = new LineBlock().setBlockName("lineBlock3");
		lineBlock4 = new LineBlock().setBlockName("lineBlock4");
		lineBlock5 = new LineBlock().setBlockName("lineBlock5");
		lineBlock6 = new LineBlock().setBlockName("lineBlock6");
		
		doubleLineBlock1 = new LineBlock().setBlockName("doubleLineBlock1");
		doubleLineBlock2 = new LineBlock().setBlockName("doubleLineBlock2");
		doubleLineBlock3 = new LineBlock().setBlockName("doubleLineBlock3");
		
		sideDoubleYellow = new LineBlock4Way().setBlockName("sideDoubleYellow");
		sideSingleYellow = new LineBlock4Way().setBlockName("sideSingleYellow");
		sideDoubleWhite = new LineBlock4Way().setBlockName("sideDoubleWhite");
		sideSingleWhite = new LineBlock4Way().setBlockName("sideSingleWhite");
		sideDoubleRed = new LineBlock4Way().setBlockName("sideDoubleRed");
		sideSingleRed = new LineBlock4Way().setBlockName("sideSingleRed");
		
		iconBlock1 = new IconBlock().setBlockName("iconBlock1");
		iconBlock2 = new IconBlock().setBlockName("iconBlock2");
		iconBlock3 = new IconBlockWide().setBlockName("iconBlock3");
		iconBlock4 = new IconBlockWide().setBlockName("iconBlock4");
		iconBlock5 = new IconBlockWide().setBlockName("iconBlock5");
		iconBlock6 = new IconBlockWide().setBlockName("iconBlock6");
		iconBlock7 = new IconBlockWide().setBlockName("iconBlock7");
		iconLetters2345 = new IconBlock().setBlockName("iconLetters2345");
		iconLetters6789 = new IconBlock().setBlockName("iconLetters6789");
		iconLettersABCD = new IconBlock().setBlockName("iconLettersABCD");
		iconLettersEFGH = new IconBlock().setBlockName("iconLettersEFGH");
		iconLettersIJKL = new IconBlock().setBlockName("iconLettersIJKL");
		iconLettersMNOP = new IconBlock().setBlockName("iconLettersMNOP");
		iconLettersQRST = new IconBlock().setBlockName("iconLettersQRST");
		iconLettersUVWX = new IconBlock().setBlockName("iconLettersUVWX");
		iconLettersYZ01 = new IconBlock().setBlockName("iconLettersYZ01");
		iconLettersMisc = new IconBlock().setBlockName("iconLettersMisc");
	}

}
