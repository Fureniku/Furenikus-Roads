package com.silvaniastudios.roads.client.render;

import com.silvaniastudios.roads.FurenikusRoads;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DynamicTextureProvider {

    private final ResourceLocation textureLocation;
    private final DynamicTexture dynamicTexture;

    public DynamicTextureProvider() {
        this.textureLocation = new ResourceLocation(FurenikusRoads.MODID, "textures/blocks/my_dynamic_texture.png");

        // Generate your dynamic texture
        BufferedImage image = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.RED);
        g2d.fillRect(0, 0, 16, 16);
        g2d.dispose();

        // Convert the BufferedImage to a Minecraft-compatible texture
        this.dynamicTexture = new DynamicTexture(image);
    }

    public void registerTexture(TextureMap textureMap) {
        // Get the ResourceLocation for the dynamic texture
        ResourceLocation dynamicTextureLocation = new ResourceLocation(FurenikusRoads.MODID, "blocks/my_dynamic_texture");

        // Register the dynamic texture with Minecraft's texture atlas
        textureMap.registerSprite(dynamicTextureLocation);

        // Get the TextureAtlasSprite for the dynamic texture from Minecraft's texture atlas
        //this.textureAtlasSprite = textureMap.getAtlasSprite(dynamicTextureLocation.toString());
    }
}