package com.silvaniastudios.roads.registries;

import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;

public class PaintIconObject {
	
	PaintBlockBase paint;
	int meta;
	int index;
	
	public PaintIconObject(PaintBlockBase paint, int meta, int index) {
		this.paint = paint;
		this.meta = meta;
		this.index = index;
	}
	
	public PaintBlockBase getPaint() {
		return paint;
	}
	
	public int getMeta() {
		return meta;
	}
	
	public int getIndex() {
		return index;
	}

}
