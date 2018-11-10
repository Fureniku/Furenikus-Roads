package com.silvaniastudios.roads.blocks.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumConnectSideLine implements IStringSerializable {
	NONE(0, "none"),
	UP(1, "up"),
	SIDE(2, "side"),
	DOWN(3, "down"),
	CORNER(4, "corner"),
	EMPTY(5, "empty");
	
	private static final EnumConnectSideLine[] META_LOOKUP = new EnumConnectSideLine[values().length];
	private final int meta;
	private final String name;
	
	private EnumConnectSideLine(int meta, String name) {
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
	
	public static EnumConnectSideLine byMetadata(int meta) {
        if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
        }
        
        return META_LOOKUP[meta];
    }
	
	static {
        for (EnumConnectSideLine type: values()) {
            META_LOOKUP[type.getMetadata()] = type;
        }
    }
}
