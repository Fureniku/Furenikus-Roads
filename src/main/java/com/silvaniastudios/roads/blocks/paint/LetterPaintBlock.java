package com.silvaniastudios.roads.blocks.paint;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.PaintColour;

public class LetterPaintBlock extends WallPaintBlock {
	
	public LetterPaintBlock(String name, String category, int[] coreMetas, boolean dynamic, PaintColour colour) {
		super(name, category, coreMetas, dynamic, colour);
		this.setCreativeTab(FurenikusRoads.tab_paint_letters);
	}
}
