package com.silvaniastudios.roads.fluids;

import net.minecraftforge.fluids.FluidRegistry;

public class FRFluids {
	
	public static FluidTar tar = new FluidTar("tar");
	public static FluidPaint white_paint = new FluidPaint("white_paint", 0);
	public static FluidPaint yellow_paint = new FluidPaint("yellow_paint", 1);
	public static FluidPaint red_paint = new FluidPaint("red_paint", 2);
	
	public static void registerFluids() {
		FluidRegistry.registerFluid(tar);
		FluidRegistry.registerFluid(white_paint);
		FluidRegistry.registerFluid(yellow_paint);
		FluidRegistry.registerFluid(red_paint);
	}

}
