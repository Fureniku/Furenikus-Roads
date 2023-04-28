package com.silvaniastudios.roads.client;

import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.MetadataSerializer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

public class CustomResourcePack implements IResourcePack {

    private final File resourceDirectory;

    public CustomResourcePack(File resourceDirectory) {
        this.resourceDirectory = resourceDirectory;
    }

    @Override
    public InputStream getInputStream(ResourceLocation location) throws IOException {
        // construct a file path relative to your custom resource directory
        String filePath = location.getResourcePath();
        File file = new File(resourceDirectory, filePath);

        if (file.exists()) {
            return new FileInputStream(file);
        }

        // return null if the resource doesn't exist in your custom resource directory
        return null;
    }

    @Override
    public boolean resourceExists(ResourceLocation location) {
        return false;
    }

    @Override
    public Set<String> getResourceDomains() {
        return null;
    }

    @Nullable
    @Override
    public <T extends IMetadataSection> T getPackMetadata(MetadataSerializer metadataSerializer, String metadataSectionName) throws IOException {
        return null;
    }

    @Override
    public BufferedImage getPackImage() throws IOException {
        return null;
    }

    @Override
    public String getPackName() {
        return null;
    }
}
