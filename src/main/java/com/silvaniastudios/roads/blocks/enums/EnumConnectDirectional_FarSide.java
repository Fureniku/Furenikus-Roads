package com.silvaniastudios.roads.blocks.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumConnectDirectional_FarSide implements IStringSerializable {
	CONNECT_NONE(0, "none"),
	CONNECT_NORTH_LEFT_DOWN(1, "connect_north_left_down"),
	CONNECT_NORTH_RIGHT_DOWN(2, "connect_north_right_down"),
	
	CONNECT_EAST_LEFT_DOWN(3, "connect_east_left_down"),
	CONNECT_EAST_RIGHT_DOWN(4, "connect_east_right_down"),
	
	CONNECT_SOUTH_LEFT_DOWN(5, "connect_south_left_down"),
	CONNECT_SOUTH_RIGHT_DOWN(6, "connect_south_right_down"),
	
	CONNECT_WEST_LEFT_DOWN(7, "connect_west_left_down"),
	CONNECT_WEST_RIGHT_DOWN(8, "connect_west_right_down");
	
	private static final EnumConnectDirectional_FarSide[] META_LOOKUP = new EnumConnectDirectional_FarSide[values().length];
	private final int meta;
	private final String name;
	
	private EnumConnectDirectional_FarSide(int meta, String name) {
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
	
	public static EnumConnectDirectional_FarSide byMetadata(int meta) {
        if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
        }
        
        return META_LOOKUP[meta];
    }
	
	static {
        for (EnumConnectDirectional_FarSide type: values()) {
            META_LOOKUP[type.getMetadata()] = type;
        }
    }
}
