package co.uk.silvania.roads.blocks;

import net.minecraft.block.Block;

public class FRBlocks {
	
	//Tarmac variants
	public static Block roadBlockBase1;
	
	//Other "road blocks"
	public static Block sidewalk1;
	public static Block sidewalk2;
	public static Block sidewalk3;
	public static Block sidewalk4;
	
	public static Block streetBlocks1;
	public static Block streetBlocks2;
	public static Block streetBlocks3;
	public static Block streetBlocks4;
	public static Block streetBlocks5;
	public static Block streetBlocks6;
	public static Block streetBlocks7;
	public static Block streetBlocks8;
	public static Block streetBlocks9;
	public static Block streetBlocks10;
	public static Block streetBlocks11;
	public static Block streetBlocks12;
	public static Block streetBlocks13;
	public static Block streetBlocks14;
	public static Block streetBlocks15;
	
	public static Block lineBlock1;
	public static Block lineBlock2;
	public static Block lineBlock3;
	
	public static void init() {
		roadBlocks();
	}
	
	public static void roadBlocks() {
		roadBlockBase1 = new RoadBlock().setBlockName("roadBlockBase1");
		
		sidewalk1 = new NonRoadBlockCT().setBlockName("sidewalk1");
		
		streetBlocks1 = new SimpleBlock().setBlockName("streetBlocks1");
		streetBlocks2 = new SimpleBlock().setBlockName("streetBlocks2");
		streetBlocks3 = new SimpleBlock().setBlockName("streetBlocks3");
		streetBlocks4 = new SimpleBlock().setBlockName("streetBlocks4");
		streetBlocks5 = new SimpleBlock().setBlockName("streetBlocks5");
		streetBlocks6 = new SimpleBlock().setBlockName("streetBlocks6");
		streetBlocks7 = new SimpleBlock().setBlockName("streetBlocks7");
		streetBlocks8 = new SimpleBlock().setBlockName("streetBlocks8");
		streetBlocks9 = new SimpleBlock().setBlockName("streetBlocks9");
		streetBlocks10 = new SimpleBlock().setBlockName("streetBlocks10");
		streetBlocks11 = new SimpleBlock().setBlockName("streetBlocks11");
		streetBlocks12 = new SimpleBlock().setBlockName("streetBlocks12");
		streetBlocks13 = new SimpleBlock().setBlockName("streetBlocks13");
		streetBlocks14 = new SimpleBlock().setBlockName("streetBlocks14");
		streetBlocks15 = new SimpleBlock().setBlockName("streetBlocks15");
		
		lineBlock1 = new LineBlock().setBlockName("lineBlock1");
		lineBlock2 = new LineBlock().setBlockName("lineBlock2");
		lineBlock3 = new LineBlock().setBlockName("lineBlock3");
	}

}
