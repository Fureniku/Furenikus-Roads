package com.silvaniastudios.roads.blocks.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumConnectArrows implements IStringSerializable {
	CONNECT_NONE(0, "none"),
	CONNECT_LEFT(1, "connect_left"),
	CONNECT_RIGHT (2, "connect_right"),
	CONNECT_UP(3, "connect_up"),
	CONNECT_LEFT_RIGHT (4, "connect_left_right"),
	CONNECT_LEFT_UP (5, "connect_left_up"),
	CONNECT_RIGHT_UP (6, "connect_right_up"),
	CONNECT_LEFT_RIGHT_UP (7, "connect_left_right_up"),
	CONNECT_LEFT_DIAGONAL (8, "connect_left_diagonal"),
	CONNECT_RIGHT_DIAGONAL (9, "connect_right_diagonal");
	
	
	private static final EnumConnectArrows[] META_LOOKUP = new EnumConnectArrows[values().length];
	private final int meta;
	private final String name;
	
	private EnumConnectArrows(int meta, String name) {
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
	
	public static EnumConnectArrows byMetadata(int meta) {
        if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
        }
        
        return META_LOOKUP[meta];
    }
	
	static {
        for (EnumConnectArrows type: values()) {
            META_LOOKUP[type.getMetadata()] = type;
        }
    }
}
