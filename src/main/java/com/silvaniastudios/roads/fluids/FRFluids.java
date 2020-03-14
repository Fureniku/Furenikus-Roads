package com.silvaniastudios.roads.fluids;

import net.minecraftforge.fluids.FluidRegistry;

public class FRFluids {
	
	public static ModFluid tar = new ModFluid("tar");
	public static ModFluid white_paint = new ModFluid("white_paint");
	public static ModFluid yellow_paint = new ModFluid("yellow_paint");
	public static ModFluid red_paint = new ModFluid("red_paint");
	
	public static void registerFluids() {
		FluidRegistry.registerFluid(tar);
		FluidRegistry.registerFluid(white_paint);
		FluidRegistry.registerFluid(yellow_paint);
		FluidRegistry.registerFluid(red_paint);
	}
}
