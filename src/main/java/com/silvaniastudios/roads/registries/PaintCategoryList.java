package com.silvaniastudios.roads.registries;

import java.util.ArrayList;

import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;

public class PaintCategoryList {

	private ArrayList<PaintBlockBase> paints = new ArrayList<PaintBlockBase>();
	private ArrayList<Integer> metas = new ArrayList<Integer>();
	String name;
	
	public PaintCategoryList(String name) {
		this.name = name;
	}
	
	public String getCategoryName() {
		return name;
	}
	
	public void add(PaintBlockBase paint, int meta) {
		paints.add(paint);
		metas.add(meta);
	}
	
	public void add(PaintBlockBase paint) {
		paints.add(paint);
		metas.add(0);
	}
	
	public int size() {
		return paints.size();
	}
	
	public PaintBlockBase getPaint(int i) {
		return paints.get(i);
	}

	public int getMeta(int i) {
		return metas.get(i);
	}
	
	public ArrayList<PaintIconObject> getWhitePaints() {
		ArrayList<PaintIconObject> whites = new ArrayList<PaintIconObject>(); 
		
		for (int i = 0; i < paints.size(); i++) {
			if (paints.get(i).getUnlocalizedName().contains("white")) {
				PaintIconObject pio = new PaintIconObject(paints.get(i), metas.get(i));
				
				whites.add(pio);
			}
		}
		
		return whites;
	}

}
