package com.silvaniastudios.roads.fluids;

import com.silvaniastudios.roads.FurenikusRoads;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidPaint extends Fluid {
	
	public int colour = 0;
	
	public FluidPaint(String fluidName, int col) {
		super(fluidName, new ResourceLocation(FurenikusRoads.MODID, "fluids/" + fluidName + "_still"), new ResourceLocation(FurenikusRoads.MODID, "fluids/" + fluidName + "_flowing"));
		this.colour = col;
		FluidRegistry.addBucketForFluid(this);
	}

}
