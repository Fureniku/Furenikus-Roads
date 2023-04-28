package com.silvaniastudios.roads.client.model.paint.loaders.lines;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.paint.LinePaintBlock;
import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;
import com.silvaniastudios.roads.blocks.paint.SimpleLinePaintBlock;
import com.silvaniastudios.roads.client.model.paint.PaintBakedModelBase;
import com.silvaniastudios.roads.client.model.paint.PaintLineBakedModelBase;
import com.silvaniastudios.roads.client.model.paint.PaintModelBase;
import com.silvaniastudios.roads.client.model.paint.loaders.PaintLoaderBase;
import com.silvaniastudios.roads.client.render.Quad;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.property.IExtendedBlockState;

public class PaintLineMiddleShortModel extends PaintModelBase {

    @Override
    public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        return new PaintLineMiddleShortBakedModel(state, format, bakedTextureGetter);
    }
}

class PaintLineMiddleShortBakedModel extends PaintLineBakedModelBase {

    public PaintLineMiddleShortBakedModel(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        super(state, format, bakedTextureGetter);
    }

    @Override
    protected List<BakedQuad> packQuads(IBlockState state) {
        List<BakedQuad> quads = new ArrayList<>();
        List<Quad> rawQuads = new ArrayList<>();

        if (state != null) {
            EnumFacing facing = state.getValue(SimpleLinePaintBlock.FACING);
            PaintBlockBase paintBlock = (PaintBlockBase) state.getBlock();
            TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":blocks/paint_" + paintBlock.getColour().getName());
            float v = 1.0f / 16.0f;

            if (sprite != null) {
                switch (facing) {
                    case NORTH:
                    case SOUTH:
                        rawQuads.addAll(shapeLine(format, sprite, v*7f, v*4, v*9f, v*12, 0f, 0f, 0f, 0f));
                        break;
                    case EAST:
                    case WEST:
                        rawQuads.addAll(shapeLine(format, sprite, v*4f, v*7f, v*12f, v*9f, 0f, 0f, 0f, 0f));
                        break;
                }

                quads = shapeBuilder(rawQuads, quads, 0);
            }
        } else if (stack != null) {
            List<Quad> spriteQuads = getSpriteQuads();
            PaintBlockBase paintBlock = (PaintBlockBase) ((ItemBlock) stack.getItem()).getBlock();
            rawQuads.addAll(spriteQuads);
            quads = shapeBuilder(rawQuads, quads, paintBlock.getColour().getColourInt());
        }

        return quads;
    }
}