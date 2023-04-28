package com.silvaniastudios.roads.client.model.paint.loaders;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.FRBlocks;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;

public class PaintLoaderBase implements ICustomModelLoader {

    IModel model;
    String texturePre;
    String texturePost;

    public PaintLoaderBase(IModel model, String texturePre, String texturePost) {
        this.model = model;
        this.texturePre = texturePre;
        this.texturePost = texturePost;
    }

    @Override
    public boolean accepts(ResourceLocation modelLocation) {
        if (modelLocation.getResourceDomain().equals(FurenikusRoads.MODID)) {
            for (int i = 0; i < FRBlocks.col.length; i++) {
                if (modelLocation.getResourcePath().equals(texturePre + FRBlocks.col[i].getName() + texturePost)) {
                    return true;
                }
            }
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
