package com.silvaniastudios.roads.blocks.paint;

import com.silvaniastudios.roads.FurenikusRoads;

public class LetterPaintBlock extends WallPaintBlock {
	
	public LetterPaintBlock(String name, String category, int[] coreMetas, boolean dynamic) {
		super(name, category, coreMetas, dynamic);
		this.setCreativeTab(FurenikusRoads.tab_paint_letters);
	}
}
