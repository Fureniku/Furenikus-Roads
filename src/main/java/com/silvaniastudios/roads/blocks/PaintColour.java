package com.silvaniastudios.roads.blocks;

public class PaintColour {
	
	private String name;
	private int colour;
	private int id;
	
	public PaintColour(String name, int col, int id) {
		this.name = name;
		this.colour = col;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getColourInt() {
		return colour;
	}
	
	public int getId() {
		return id;
	}
}
