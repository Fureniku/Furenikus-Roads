package com.silvaniastudios.roads.blocks.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumThreeLengthConnectable implements IStringSerializable {
	NORTH_BOTTOM(0, "north_bottom"),
	EAST_BOTTOM(1, "east_bottom"),
	SOUTH_BOTTOM(2, "south_bottom"),
	WEST_BOTTOM(3, "west_bottom"),

	NORTH_MID(4, "north_mid"),
	EAST_MID(5, "east_mid"),
	SOUTH_MID(6, "south_mid"),
	WEST_MID(7, "west_mid"),

	NORTH_TOP(8, "north_top"),
	EAST_TOP(9, "east_top"),
	SOUTH_TOP(10, "south_top"),
	WEST_TOP(11, "west_top");

	private static final EnumThreeLengthConnectable[] INDEX_LOOKUP = new EnumThreeLengthConnectable[values().length];
	private final int index;
	private final String name;

	private EnumThreeLengthConnectable(int index, String name) {
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
	
	public static EnumThreeLengthConnectable byMetadata(int meta) {
        if (meta < 0 || meta >= INDEX_LOOKUP.length) {
        	meta = 0;
        }
        
        return INDEX_LOOKUP[meta];
    }
	
	static {
        for (EnumThreeLengthConnectable type: values()) {
        	INDEX_LOOKUP[type.getIndex()] = type;
        }
    }
}