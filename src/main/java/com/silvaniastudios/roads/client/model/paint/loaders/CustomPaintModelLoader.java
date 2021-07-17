package com.silvaniastudios.roads.client.model.paint.loaders;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.client.model.paint.CustomPaintModel;

import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;

public class CustomPaintModelLoader implements ICustomModelLoader {
	
	public static final CustomPaintModel MODEL = new CustomPaintModel();
	
	@Override
	public boolean accepts(ResourceLocation modelLocation) {
		return modelLocation.getResourceDomain().equals(FurenikusRoads.MODID) && modelLocation.getResourcePath().equals("custom_paint");
	}
	
	@Override
	public IModel loadModel(ResourceLocation modelLocation) throws Exception {
		return MODEL;
	}
	
	@Override
	public void onResourceManagerReload(IResourceManager resourceManager) {}

}