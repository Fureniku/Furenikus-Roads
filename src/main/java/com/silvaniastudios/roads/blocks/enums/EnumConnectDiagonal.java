package com.silvaniastudios.roads.blocks.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumConnectDiagonal implements IStringSerializable {
	NONE(0, "none"),
	NORTH_EAST(1, "north_east"),
	NORTH_WEST(2, "north_west"),
	SOUTH_EAST(3, "south_east"),
	SOUTH_WEST(4, "south_west");
	
	private static final EnumConnectDiagonal[] META_LOOKUP = new EnumConnectDiagonal[values().length];
	private final int meta;
	private final String name;
	
	private EnumConnectDiagonal(int meta, String name) {
		this.meta = meta;
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
	
	public int getMetadata() {
        return this.meta;
    }
	
	public static EnumConnectDiagonal byMetadata(int meta) {
        if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
        }
        
        return META_LOOKUP[meta];
    }
	
	static {
        for (EnumConnectDiagonal type: values()) {
            META_LOOKUP[type.getMetadata()] = type;
        }
    }
}
