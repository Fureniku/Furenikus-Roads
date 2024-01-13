package com.silvaniastudios.roads.client.model.paint.loaders;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.client.model.paint.PaintModelBase;

import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;

public class CustomPaintModelLoader implements ICustomModelLoader {

	PaintModelBase model;
	String name;

	public CustomPaintModelLoader(String name, PaintModelBase model) {
		this.name = name;
		this.model = model;
	}
	
	@Override
	public boolean accepts(ResourceLocation modelLocation) {
		if (modelLocation.getNamespace().equals(FurenikusRoads.MODID)) {
			return modelLocation.getPath().equals(name);
		}
		return false;
	}
	
	@Override
	public IModel loadModel(ResourceLocation modelLocation) throws Exception {
		return model;
	}
	
	@Override
	public void onResourceManagerReload(IResourceManager resourceManager) {}

}