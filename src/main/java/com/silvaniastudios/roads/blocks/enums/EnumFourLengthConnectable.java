package com.silvaniastudios.roads.blocks.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumFourLengthConnectable implements IStringSerializable {
	NORTH_TOP(0, "north_top"),
	NORTH_TOP_MID(1, "north_top_mid"),
	NORTH_BOTTOM_MID(2, "north_bottom_mid"),
	NORTH_BOTTOM(3, "north_bottom"),

	EAST_TOP(4, "east_top"),
	EAST_TOP_MID(5, "east_top_mid"),
	EAST_BOTTOM_MID(6, "east_bottom_mid"),
	EAST_BOTTOM(7, "east_bottom"),
	
	SOUTH_TOP(8, "south_top"),
	SOUTH_TOP_MID(9, "south_top_mid"),
	SOUTH_BOTTOM_MID(10, "south_bottom_mid"),
	SOUTH_BOTTOM(11, "south_bottom"),
	
	WEST_TOP(12, "west_top"),
	WEST_TOP_MID(13, "west_top_mid"),
	WEST_BOTTOM_MID(14, "west_bottom_mid"),
	WEST_BOTTOM(15, "west_bottom");
	
	private static final EnumFourLengthConnectable[] INDEX_LOOKUP = new EnumFourLengthConnectable[values().length];
	private final int index;
	private final String name;
	
	private EnumFourLengthConnectable(int index, String name) {
		this.index = index;
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	public int getIndex() {
        return this.index;
    }
	
	public static EnumFourLengthConnectable byMetadata(int meta) {
        if (meta < 0 || meta >= INDEX_LOOKUP.length) {
        	meta = 0;
        }
        
        return INDEX_LOOKUP[meta];
    }
	
	static {
        for (EnumFourLengthConnectable type: values()) {
        	INDEX_LOOKUP[type.getIndex()] = type;
        }
    }
}