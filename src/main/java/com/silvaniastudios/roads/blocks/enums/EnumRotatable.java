package com.silvaniastudios.roads.blocks.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumRotatable implements IStringSerializable {
	FLAT_NORTH(0, "flat_north"),
	FLAT_EAST(1, "flat_east"),
	FLAT_SOUTH(2, "flat_south"),
	FLAT_WEST(3, "flat_west"),
	FACE_NORTH(4, "face_north"),
	FACE_EAST(5, "face_east"),
	FACE_SOUTH(6, "face_south"),
	FACE_WEST(7, "face_west"),
	FLAT_NORTH_2(8, "flat_north_2"),
	FLAT_EAST_2(9, "flat_east_2"),
	FLAT_SOUTH_2(10, "flat_south_2"),
	FLAT_WEST_2(11, "flat_west_2"),
	FACE_NORTH_2(12, "face_north_2"),
	FACE_EAST_2(13, "face_east_2"),
	FACE_SOUTH_2(14, "face_south_2"),
	FACE_WEST_2(15, "face_west_2");
	
	private static final EnumRotatable[] ID_LOOKUP = new EnumRotatable[values().length];
	private final int meta;
	private final String name;
	
	private EnumRotatable(int meta, String name) {
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
	
	public static EnumRotatable byMetadata(int meta) {
        if (meta < 0 || meta >= ID_LOOKUP.length) {
            meta = 0;
        }
        
        return ID_LOOKUP[meta];
    }
	
	static {
        for (EnumRotatable type: values()) {
        	ID_LOOKUP[type.getMetadata()] = type;
        }
    }
}
