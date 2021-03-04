package com.silvaniastudios.roads.client.model.diagonal.loaders;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.client.model.diagonal.DiagonalTriangleQuadModel;

import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;

public class Diagonal12MirrorModelLoader implements ICustomModelLoader {
	
	public static final DiagonalTriangleQuadModel MODEL = new DiagonalTriangleQuadModel(0.5, 1.0, false);
	
	@Override
	public boolean accepts(ResourceLocation modelLocation) {
		return modelLocation.getResourceDomain().equals(FurenikusRoads.MODID) && "diagonal_12_mirror".equals(modelLocation.getResourcePath());
	}
	
	@Override
	public IModel loadModel(ResourceLocation modelLocation) throws Exception {
		return MODEL;
	}
	
	@Override
	public void onResourceManagerReload(IResourceManager resourceManager) {}

}
