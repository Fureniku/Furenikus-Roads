package com.silvaniastudios.roads.blocks.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumTwoLengthConnectable implements IStringSerializable {
    n1(0, "n1"),
    e1(1, "e1"),
    s1(2, "s1"),
    w1(3, "w1"),
    n2(4, "n2"),
    e2(5, "e2"),
    s2(6, "s2"),
    w2(7, "w2");

    private static final EnumTwoLengthConnectable[] META_LOOKUP = new EnumTwoLengthConnectable[values().length];
    private final int meta;
    private final String name;

    EnumTwoLengthConnectable(int meta, String name) {
        this.meta = meta;
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public int getMetadata() {
        return this.meta;
    }

    public static EnumTwoLengthConnectable byMetadata(int meta) {
        if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    static {
        for (EnumTwoLengthConnectable type: values()) {
            META_LOOKUP[type.getMetadata()] = type;
        }
    }
}