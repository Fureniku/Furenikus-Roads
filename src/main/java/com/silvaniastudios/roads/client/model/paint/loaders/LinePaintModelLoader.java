package com.silvaniastudios.roads.client.model.paint.loaders;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.client.model.diagonal.DiagonalTriangleModel;
import com.silvaniastudios.roads.client.model.paint.PaintLineModel;

import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;

public class LinePaintModelLoader implements ICustomModelLoader {
	
	public static final PaintLineModel MODEL = new PaintLineModel();
	
	@Override
	public boolean accepts(ResourceLocation modelLocation) {
		return modelLocation.getResourceDomain().equals(FurenikusRoads.MODID) && modelLocation.getResourcePath().equals("line_white_straight_full");
	}
	
	@Override
	public IModel loadModel(ResourceLocation modelLocation) throws Exception {
		return MODEL;
	}
	
	@Override
	public void onResourceManagerReload(IResourceManager resourceManager) {}

}
