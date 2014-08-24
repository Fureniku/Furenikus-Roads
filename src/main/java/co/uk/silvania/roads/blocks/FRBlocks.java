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
	
	public static void init() {
		roadBlocks();
	}
	
	public static void roadBlocks() {
		roadBlockBase1 = new RoadBlock().setBlockName("roadBlockBase1");
		
		sidewalk1 = new RoadBlockCT().setBlockName("sidewalk1");
		
		streetBlocks1 = new RoadBlock().setBlockName("streetBlocks1");
		streetBlocks2 = new RoadBlock().setBlockName("streetBlocks2");
		streetBlocks3 = new RoadBlock().setBlockName("streetBlocks3");
		streetBlocks4 = new RoadBlock().setBlockName("streetBlocks4");
		streetBlocks5 = new RoadBlock().setBlockName("streetBlocks5");
		streetBlocks6 = new RoadBlock().setBlockName("streetBlocks6");
		streetBlocks7 = new RoadBlock().setBlockName("streetBlocks7");
		streetBlocks8 = new RoadBlock().setBlockName("streetBlocks8");
		streetBlocks9 = new RoadBlock().setBlockName("streetBlocks9");
		streetBlocks10 = new RoadBlock().setBlockName("streetBlocks10");
		streetBlocks11 = new RoadBlock().setBlockName("streetBlocks11");
		streetBlocks12 = new RoadBlock().setBlockName("streetBlocks12");
		streetBlocks13 = new RoadBlock().setBlockName("streetBlocks13");
		streetBlocks14 = new RoadBlock().setBlockName("streetBlocks14");
		streetBlocks15 = new RoadBlock().setBlockName("streetBlocks15");
		
		lineBlock1 = new LineBlock().setBlockName("lineBlock1");
	}

}
