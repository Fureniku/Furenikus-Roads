package com.silvaniastudios.roads.client.model.paint.loaders.customs;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.diagonal.ShapeLibrary;
import com.silvaniastudios.roads.blocks.enums.EnumMeta;
import com.silvaniastudios.roads.blocks.paint.customs.CustomMetaPaintBlock;
import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;
import com.silvaniastudios.roads.blocks.paint.properties.PaintGrid;
import com.silvaniastudios.roads.client.model.paint.PaintBakedModelBase;
import com.silvaniastudios.roads.client.model.paint.PaintModelBase;
import com.silvaniastudios.roads.client.render.Quad;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.model.IModelState;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CustomMetaPaintModel extends PaintModelBase {

    @Override
    public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        return new CustomMetaPaintBakedModel(state, format, bakedTextureGetter);
    }
}

class CustomMetaPaintBakedModel extends PaintBakedModelBase {

    public CustomMetaPaintBakedModel(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        super(state, format, bakedTextureGetter);
        populateSprites();
    }

    @Override
    protected List<BakedQuad> packQuads(IBlockState state) {
        List<BakedQuad> quads;
        List<Quad> rawQuads;

        if (state != null) {
            CustomMetaPaintBlock block = (CustomMetaPaintBlock) state.getBlock();
            int colId = block.getColour().getId();
            EnumMeta meta = state.getValue(CustomMetaPaintBlock.META_ID);
            TextureAtlasSprite tex = sprites[colId];

            if (getGrid(meta.getMetadata(), block) == null) {
                FurenikusRoads.debug(0, "Failed to load paint grid for " + block.getLocalName() + " and meta " + meta.getMetadata());
                rawQuads = ShapeLibrary.shapeFromGrid(PaintGrid.EMPTY.getGrid(), 0.015625f, tex, format, false);
                quads = shapeBuilder(rawQuads, new ArrayList<>(), 0, 0);
                return quads;
            }

            boolean[][] grid = getGrid(meta.getMetadata(), block).getGrid();

            int xRot = 0;
            int yRot = meta.getRotation();

            rawQuads = ShapeLibrary.shapeFromGrid(grid, 0.015625f, tex, format, false);
            quads = shapeBuilder(rawQuads, new ArrayList<>(), xRot, yRot);
        } else {
            return handleItemRendering();
        }

        return quads;
    }

    private PaintGrid getGrid(int meta, CustomMetaPaintBlock block) {
        int gridId = 0;
        int[] metas = new int[] {0, 4, 8, 12};

        for (int i = 0; i < metas.length; i++) {
            if (meta >= metas[i]) {
                gridId = i;
            }
        }

        return block.getGrid(gridId);
    }

    protected List<BakedQuad> shapeBuilderItem(List<Quad> rawQuads, List<BakedQuad> quads, int col, int yRot) {
        for (int i = 0; i < rawQuads.size(); i++) {
            if (rawQuads.get(i) != null) {
                rawQuads.set(i, Quad.rotateQuadX(rawQuads.get(i), 90).rotateQuadY(rawQuads.get(i), yRot));
                BakedQuad baked = rawQuads.get(i).createQuad(col);

                quads.add(baked);
            }
        }

        return quads;
    }
}