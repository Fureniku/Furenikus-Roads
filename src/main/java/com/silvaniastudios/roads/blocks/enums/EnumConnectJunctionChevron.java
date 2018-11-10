package com.silvaniastudios.roads.blocks.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumConnectJunctionChevron implements IStringSerializable {
	NONE(0, "none"),
	N_1(1, "n1"),
	E_1(2, "e1"),
	S_1(3, "s1"),
	W_1(4, "w1"),
	N_2(5, "n2"),
	E_2(6, "e2"),
	S_2(7, "s2"),
	W_2(8, "w2");
	
	private static final EnumConnectJunctionChevron[] META_LOOKUP = new EnumConnectJunctionChevron[values().length];
	private final int meta;
	private final String name;
	
	private EnumConnectJunctionChevron(int meta, String name) {
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
	
	public static EnumConnectJunctionChevron byMetadata(int meta) {
        if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
        }
        
        return META_LOOKUP[meta];
    }
	
	static {
        for (EnumConnectJunctionChevron type: values()) {
            META_LOOKUP[type.getMetadata()] = type;
        }
    }
}
