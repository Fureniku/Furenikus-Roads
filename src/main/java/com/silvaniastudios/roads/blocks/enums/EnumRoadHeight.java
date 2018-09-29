package com.silvaniastudios.roads.blocks.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumRoadHeight implements IStringSerializable {
	id0(0, "1_16th"),
	id1(1, "1_8th"),
	id2(2, "3_16ths"),
	id3(3, "quarter_block"),
	id4(4, "5_16ths"),
	id5(5, "6_16ths"),
	id6(6, "7_16ths"),
	id7(7, "half_block"),
	id8(8, "9_16ths"),
	id9(9, "10_16ths"),
	id10(10, "11_16ths"),
	id11(11, "three_quarter_block"),
	id12(12, "13_16ths"),
	id13(13, "14_16ths"),
	id14(14, "15_16ths"),
	id15(15, "full_block");
	
	private static final EnumRoadHeight[] META_LOOKUP = new EnumRoadHeight[values().length];
	private final int meta;
	private final String name;
	
	private EnumRoadHeight(int meta, String name) {
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
	
	public static EnumRoadHeight byMetadata(int meta) {
        if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
        }
        
        return META_LOOKUP[meta];
    }
	
	static {
        for (EnumRoadHeight type: values()) {
            META_LOOKUP[type.getMetadata()] = type;
        }
    }
}