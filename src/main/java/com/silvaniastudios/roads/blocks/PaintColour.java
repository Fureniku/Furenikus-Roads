package com.silvaniastudios.roads.blocks;

import net.minecraft.util.text.TextFormatting;

public class PaintColour {
	
	private String name;
	private int colour;
	private int id;
	private TextFormatting formatting;

	/**
	 *
	 * @param name The name used on internal textures etc
	 * @param col The colour, as an int; java.awt.Color.X.getRGB()
	 * @param id The ID. Must be unique.
	 * @param formatting Minecraft TextFormatting string, applied before display names
	 */
	public PaintColour(String name, int col, int id, TextFormatting formatting) {
		this.name = name;
		this.colour = col;
		this.id = id;
		this.formatting = formatting;
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

	public static PaintColour getFromName(String name) {
		for (int i = 0; i < FRBlocks.col.size(); i++) {
			if (FRBlocks.col.get(i).name.equalsIgnoreCase(name)) {
				return FRBlocks.col.get(i);
			}
		}
		return FRBlocks.col.get(0);
	}

	public TextFormatting getFormat() {
		return formatting;
	}
}
