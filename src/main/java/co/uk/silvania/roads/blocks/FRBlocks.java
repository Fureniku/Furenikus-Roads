package co.uk.silvania.roads.blocks;

import net.minecraft.block.Block;

public class FRBlocks {
	
	public static Block roadBlockBase1;
	public static Block lineBlock1;
	
	public static void init() {
		roadBlocks();
	}
	
	public static void roadBlocks() {
		roadBlockBase1 = new RoadBlock().setBlockName("roadBlockBase1");
		lineBlock1 = new LineBlock().setBlockName("lineBlock1");
	}

}
