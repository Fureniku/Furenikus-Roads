package com.silvaniastudios.roads.client.model.diagonal.loaders;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.client.model.diagonal.DiagonalQuadQuadModel;

import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;

public class Diagonal24ModelLoader implements ICustomModelLoader {
	
	public static final DiagonalQuadQuadModel MODEL = new DiagonalQuadQuadModel(0.25, 0.5, true);
	
	@Override
	public boolean accepts(ResourceLocation modelLocation) {
		return modelLocation.getNamespace().equals(FurenikusRoads.MODID) && "diagonal_24".equals(modelLocation.getPath());
	}
	
	@Override
	public IModel loadModel(ResourceLocation modelLocation) throws Exception {
		return MODEL;
	}
	
	@Override
	public void onResourceManagerReload(IResourceManager resourceManager) {}

}
