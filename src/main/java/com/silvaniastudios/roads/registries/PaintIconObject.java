package com.silvaniastudios.roads.registries;

import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;

public class PaintIconObject {
	
	PaintBlockBase paint;
	int meta;

	public PaintIconObject(PaintBlockBase paint, int meta) {
		this.paint = paint;
		this.meta = meta;
	}
	
	public PaintBlockBase getPaint() {
		return paint;
	}
	
	public int getMeta() {
		return meta;
	}

}
