package co.uk.silvania.roads.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class RoadItemBlock extends ItemBlock {

	public RoadItemBlock(Block block) {
		super(block);
		this.setHasSubtypes(true);
	}
	
	public int getMetadata(int par1) {
		return par1;
	}

}
