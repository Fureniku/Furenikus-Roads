package com.silvaniastudios.roads.client.model.paint.loaders.customs;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.diagonal.ShapeLibrary;
import com.silvaniastudios.roads.blocks.enums.EnumRotatable;
import com.silvaniastudios.roads.blocks.paint.customs.CustomPaintWallBlock;
import com.silvaniastudios.roads.blocks.paint.customs.ICustomBlock;
import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;
import com.silvaniastudios.roads.client.model.paint.PaintBakedModelBase;
import com.silvaniastudios.roads.client.model.paint.PaintModelBase;
import com.silvaniastudios.roads.client.render.Quad;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.model.IModelState;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CustomWallPaintModel extends PaintModelBase {

    @Override
    public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        return new CustomWallBakedModel(state, format, bakedTextureGetter);
    }
}

class CustomWallBakedModel extends PaintBakedModelBase {

    public CustomWallBakedModel(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        super(state, format, bakedTextureGetter);
        populateSprites();
    }

    @Override
    protected List<BakedQuad> packQuads(IBlockState state) {
        List<BakedQuad> quads;
        List<Quad> rawQuads;

        if (state != null) {
            int colId = ((PaintBlockBase) state.getBlock()).getColour().getId();
            boolean[][] grid;

            TextureAtlasSprite tex = sprites[colId];
            EnumRotatable rotState = state.getValue(CustomPaintWallBlock.ROTATE_ID);
            int meta = state.getBlock().getMetaFromState(state);

            if (meta <= 7) {
                grid = ((ICustomBlock) state.getBlock()).getGrid(0).getGrid();
            } else {
                grid = ((ICustomBlock) state.getBlock()).getGrid(1).getGrid();
            }

            int xRot = 0;
            int yRot = 45;

            switch(rotState) {
                case FLAT_NORTH:
                case FLAT_NORTH_2:
                    yRot = 0;
                    break;
                case FLAT_EAST:
                case FLAT_EAST_2:
                    yRot = 270;
                    break;
                case FLAT_SOUTH:
                case FLAT_SOUTH_2:
                    yRot = 180;
                    break;
                case FLAT_WEST:
                case FLAT_WEST_2:
                    yRot = 90;
                    break;
                case FACE_NORTH:
                case FACE_NORTH_2:
                    xRot = 90;
                    yRot = 0;
                    break;
                case FACE_EAST:
                case FACE_EAST_2:
                    xRot = 90;
                    yRot = 270;
                    break;
                case FACE_SOUTH:
                case FACE_SOUTH_2:
                    xRot = 90;
                    yRot = 180;
                    break;
                case FACE_WEST:
                case FACE_WEST_2:
                    xRot = 90;
                    yRot = 90;
                    break;
            }

            rawQuads = ShapeLibrary.shapeFromGrid(grid, 0.015625f, tex, format, false);
            quads = shapeBuilder(rawQuads, new ArrayList<>(), xRot, yRot);
        } else {
            return handleItemRendering();
        }

        return quads;
    }

    protected List<BakedQuad> shapeBuilder(List<Quad> rawQuads, List<BakedQuad> quads, int rotX, int rotY) {
        for (int i = 0; i < rawQuads.size(); i++) {
            if (rawQuads.get(i) != null) {
                rawQuads.set(i, Quad.rotateQuadX(rawQuads.get(i), rotX).rotateQuadY(rawQuads.get(i), rotY));
            }
        }

        if (rawQuads.get(0) != null) {
            rawQuads.get(0).updateUVs(); //Prevent UV rotation on top face
        }

        if (rawQuads.get(1) != null) {
            rawQuads.get(1).setFlipV(true); //Flip UVs for bottom face
            rawQuads.get(1).updateUVs(); //Prevent UV rotation on bottom face
        }

        for (int i = 0; i < rawQuads.size(); i++) {
            if (rawQuads.get(i) != null) {
                BakedQuad baked = rawQuads.get(i).createQuad(0);

                quads.add(baked);
            }
        }

        return quads;
    }
}