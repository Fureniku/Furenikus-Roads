package co.uk.silvania.roads.client.render;

import org.lwjgl.opengl.GL11;

import co.uk.silvania.roads.blocks.FRBlocks;
import co.uk.silvania.roads.blocks.LineBlock;
import co.uk.silvania.roads.blocks.RoadBlock;
import co.uk.silvania.roads.client.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RoadPaintRenderingHandler implements ISimpleBlockRenderingHandler {
	Tessellator tess;
	
	@Override
	public void renderInventoryBlock(Block block, int meta, int modelId, RenderBlocks renderer) {
		Tessellator tess = Tessellator.instance;

		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        //tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
        IIcon icon = FRBlocks.roadBlockBase1.getIcon(0, meta);

        double u0 = (double)icon.getMinU();
        double u1 = (double)icon.getMaxU();

        double v0 = (double)icon.getMinV();
        double v1 = (double)icon.getMaxV();
        
        final float FACE_XZ_NORMAL = 0.8944F;
        final float FACE_Y_NORMAL  = 0.4472F;
        
        IIcon iconAbove = block.getIcon(1, meta);

        double u0_1 = (double)iconAbove.getMinU();
        double u1_1 = (double)iconAbove.getMaxU();
        double v0_1 = (double)iconAbove.getMinV();
        double v1_1 = (double)iconAbove.getMaxV();

        //Because I'm too lazy to go change all the "A"'s from my code copied from the block renderer.
        double a = 0.5;
        
        //Now, we actually render each face.
        //Each face needs the colour setting, and then four vertex.
        //Colour is required as it's reduced for sides and bottom, to give a false effect of "shading" which is surprisingly very important.
        
        //Line (The part you actually place from this block.)
        tess.startDrawingQuads();
        tess.setNormal(0.0F, 1.0F, 0.0F);
        tess.addVertexWithUV(0, a+0.001, 0, u0_1, v0_1); //NW
        tess.addVertexWithUV(0, a+0.001, 1, u0_1, v1_1); //SW
        tess.addVertexWithUV(1, a+0.001, 1, u1_1, v1_1); //SE
        tess.addVertexWithUV(1, a+0.001, 0, u1_1, v0_1); //NE
        tess.draw();

        //ROAD BLOCK (Just for graphical effect)
        //Top Side
        tess.startDrawingQuads();
        tess.setNormal(0.0F, 1.0F, 0.0F);
        tess.addVertexWithUV(0, a, 0, u1, v1); //NW
        tess.addVertexWithUV(0, a, 1, u1, v0); //SW
        tess.addVertexWithUV(1, a, 1, u0, v0); //SE
        tess.addVertexWithUV(1, a, 0, u0, v1); //NE
        tess.draw();
        
        //North Side
        tess.startDrawingQuads();
        tess.setNormal(0.0F, FACE_Y_NORMAL, -FACE_XZ_NORMAL);
        tess.addVertexWithUV(1, a, 0, u1, v1);
        tess.addVertexWithUV(1, 0, 0, u1, v0);
        tess.addVertexWithUV(0, 0, 0, u0, v0);
        tess.addVertexWithUV(0, a, 0, u0, v1);
        tess.draw();
        
        //East Side
        tess.startDrawingQuads();
        tess.setNormal(FACE_XZ_NORMAL, FACE_Y_NORMAL, 0.0F);
        tess.addVertexWithUV(1, a, 1, u1, v1);
        tess.addVertexWithUV(1, 0, 1, u1, v0);
        tess.addVertexWithUV(1, 0, 0, u0, v0);
        tess.addVertexWithUV(1, a, 0, u0, v1);
        tess.draw();
        
        //South Side
        tess.startDrawingQuads();
        tess.setNormal(0.0F, FACE_Y_NORMAL, FACE_XZ_NORMAL);
        tess.addVertexWithUV(0, a, 1, u1, v1);
        tess.addVertexWithUV(0, 0, 1, u1, v0);
        tess.addVertexWithUV(1, 0, 1, u0, v0);
        tess.addVertexWithUV(1, a, 1, u0, v1);
        tess.draw();

        //West Side
        tess.startDrawingQuads();
        tess.setNormal(-FACE_XZ_NORMAL, FACE_Y_NORMAL, 0.0F);
        tess.addVertexWithUV(0, a, 0, u1, v1);
        tess.addVertexWithUV(0, 0, 0, u1, v0);
        tess.addVertexWithUV(0, 0, 1, u0, v0);
        tess.addVertexWithUV(0, a, 1, u0, v1);
        tess.draw();

        //Bottom Side
        tess.startDrawingQuads();
        tess.setNormal(0.0F, -1.0F, 0.0F);
        tess.addVertexWithUV(0, 0, 1, u1, v1);
        tess.addVertexWithUV(0, 0, 0, u1, v0);
        tess.addVertexWithUV(1, 0, 0, u0, v0);
        tess.addVertexWithUV(1, 0, 1, u0, v1);
        tess.draw();
        
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return ClientProxy.roadBlockRenderID;
	}
	
	public double quadHeight(int meta) {
		double m = meta + 1.0;
		return ((1.0 / 16.0) * m) + 0.01;
	}
}