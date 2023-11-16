package com.fureniku.roads.enums;

import net.minecraft.util.StringRepresentable;

public enum EnumRoadSize implements StringRepresentable {
    height_01(1, "1_16"),
    height_02(2, "2_16"),
    height_03(3, "3_16"),
    height_04(4, "4_16"),
    height_05(5, "5_16"),
    height_06(6, "6_16"),
    height_07(7, "7_16"),
    height_08(8, "8_16"),
    height_09(9, "9_16"),
    height_10(10, "10_16"),
    height_11(11, "11_16"),
    height_12(12, "12_16"),
    height_13(13, "13_16"),
    height_14(14, "14_16"),
    height_15(15, "15_16"),
    height_16(16, "16_16");

    private static final EnumRoadSize[] LOOKUP = new EnumRoadSize[values().length];
    private final int height;
    private final String name;

    EnumRoadSize(int height, String name) {
        this.height = height;
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    @Override
    public String toString() {
        return getSerializedName();
    }

    public int getHeight() {
        return this.height-1;
    }

    static {
        for (EnumRoadSize type: values()) {
            LOOKUP[type.getHeight()] = type;
        }
    }
}
