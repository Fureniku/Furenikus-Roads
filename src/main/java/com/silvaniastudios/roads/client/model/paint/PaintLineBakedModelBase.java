package com.silvaniastudios.roads.client.model.paint;

import com.silvaniastudios.roads.client.render.Quad;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.model.IModelState;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class PaintLineBakedModelBase extends PaintBakedModelBase {

    public PaintLineBakedModelBase(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        super(state, format, bakedTextureGetter);

    }

    protected List<BakedQuad> shapeBuilder(List<Quad> rawQuads, List<BakedQuad> quads, int col) {
        return shapeBuilder(rawQuads, quads, col, 0);
    }

    protected List<BakedQuad> shapeBuilder(List<Quad> rawQuads, List<BakedQuad> quads, int col, int rotation) {
        if (rotation != 0) {
            for (int i = 0; i < rawQuads.size(); i++) {
                if (rawQuads.get(i) != null) {
                    rawQuads.set(i, Quad.rotateQuadY(rawQuads.get(i), rotation));
                }
            }
        }

        for (int i = 0; i < rawQuads.size(); i++) {
            if (rawQuads.get(i) != null) {
                BakedQuad baked = rawQuads.get(i).createQuad(col);

                quads.add(baked);
            }
        }

        return quads;
    }

    public static List<Quad> shapeLine(VertexFormat format, TextureAtlasSprite sprite, float x1, float z1, float x2, float z2, float hSW, float hSE, float hNE, float hNW) {
        List<Quad> quads = new ArrayList<>();

        float xMin = Math.min(x1, x2);
        float xMax = Math.max(x1, x2);
        float zMin = Math.min(z1, z2);
        float zMax = Math.max(z1, z2);

        float deltaHeightNWS = Math.abs(hNW - hSW); //north-south along west side // 1.0  - 0.75 = 0.25
        float deltaHeightNES = Math.abs(hNE - hSE); //north-south along east side // 0.5  - 0.25 = 0.25
        float deltaHeightENW = Math.abs(hNW - hNE); //east-west along north side //  1.0  - 0.5  = 0.5
        float deltaHeightESW = Math.abs(hSW - hSE); //east-west along south side //  0.75 - 0.25 = 0.5

        float heightSW = 0;
        float heightSE = 0;
        float heightNW = 0;
        float heightNE = 0;

        if (x1 > x2) {
            heightSW = Math.max(hSW, hSE) - (x1 * deltaHeightESW) + ((1-zMax) * deltaHeightNWS);
            heightSE = Math.max(hSW, hSE) - (x2 * deltaHeightESW) + ((1-zMax) * deltaHeightNES);
            heightNW = Math.max(hNW, hNE) - (x1 * deltaHeightENW) - (zMin * deltaHeightNWS);
            heightNE = Math.max(hNW, hNE) - (x2 * deltaHeightENW) - (zMin * deltaHeightNES);
        } else {
            heightSW = Math.max(hSW, hSE) - (x2 * deltaHeightESW) + ((1-zMax) * deltaHeightNWS);
            heightSE = Math.max(hSW, hSE) - (x1 * deltaHeightESW) + ((1-zMax) * deltaHeightNES);
            heightNW = Math.max(hNW, hNE) - (x2 * deltaHeightENW) - (zMin * deltaHeightNWS);
            heightNE = Math.max(hNW, hNE) - (x1 * deltaHeightENW) - (zMin * deltaHeightNES);
        }

        Quad north = new Quad(
                new Vec3d(xMax, heightNE,          zMin), xMin*16f, 16f, //BR
                new Vec3d(xMin, heightNW,          zMin), xMax*16f, 16f, //TR
                new Vec3d(xMin, heightNW+0.015625, zMin), xMax*16f, 15.75f,//TL
                new Vec3d(xMax, heightNE+0.015625, zMin), xMin*16f, 15.75f,//BL
                sprite, format);

        Quad east = new Quad(
                new Vec3d(xMax, heightSE,          zMax), zMin*16f, 16f, //BR
                new Vec3d(xMax, heightNE,          zMin), zMax*16f, 16f, //TR
                new Vec3d(xMax, heightNE+0.015625, zMin), zMax*16f, 15.75f,//TL
                new Vec3d(xMax, heightSE+0.015625, zMax), zMin*16f, 15.75f,//BL
                sprite, format);

        Quad south = new Quad(
                new Vec3d(xMin, heightSW,          zMax), xMax*16f, 15.75f, //BL
                new Vec3d(xMax, heightSE,          zMax), xMin*16f, 15.75f,//BR
                new Vec3d(xMax, heightSE+0.015625, zMax), xMin*16f, 16f, //TR
                new Vec3d(xMin, heightSW+0.015625, zMax), xMax*16f, 16f, //TL
                sprite, format);

        Quad west = new Quad(
                new Vec3d(xMin, heightNW,          zMin), zMax*16f, 15.75f, //BL
                new Vec3d(xMin, heightSW,          zMax), zMin*16f, 15.75f,//BR
                new Vec3d(xMin, heightSW+0.015625, zMax), zMin*16f, 16f, //TR
                new Vec3d(xMin, heightNW+0.015625, zMin), zMax*16f, 16f, //TL
                sprite, format);

        Quad top = new Quad( //Top
                new Vec3d(x1, heightSW+0.015625, z2), //BL
                new Vec3d(x2, heightSE+0.015625, z2), //BR
                new Vec3d(x2, heightNE+0.015625, z1), //TR
                new Vec3d(x1, heightNW+0.015625, z1), //TL
                sprite, format);

		/*TextureAtlasSprite sprite2 = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":blocks/road_block_standard");

		Quad base = new Quad( //Top
				new Vec3d(0, hSW, 1), //BL
				new Vec3d(1, hSE, 1), //BR
				new Vec3d(1, hNE, 0), //TR
				new Vec3d(0, hNW, 0), //TL
				sprite2, format);

		quads.add(base);*/
        quads.add(top);
        quads.add(north);
        quads.add(east);
        quads.add(south);
        quads.add(west);
        return quads;
    }


}
