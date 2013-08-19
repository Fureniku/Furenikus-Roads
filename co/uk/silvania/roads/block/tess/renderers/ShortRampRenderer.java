package co.uk.silvania.roads.block.tess.renderers;

import org.lwjgl.opengl.GL11;

import co.uk.silvania.roads.client.ClientProxy;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ShortRampRenderer implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		
	}

    @Override
    @SideOnly(Side.CLIENT)
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    	int meta = world.getBlockMetadata(x, y, z);
    	Icon c = block.getIcon(0, meta);
    	Icon b = block.getIcon(1, meta);
    	
    	float u = c.getMinU();
    	float v = c.getMinV();
    	float U = c.getMaxU();
    	float V = c.getMaxV();
    	
    	float u1 = b.getMinU();
    	float v1 = b.getMinV();
    	float U1 = b.getMaxU();
    	float V1 = b.getMaxV();
    	
		Tessellator tess = Tessellator.instance;
		tess.addTranslation(x, y, z);
		//Base
		tess.addVertexWithUV(0, -0.25, 1, u, v);
		tess.addVertexWithUV(0, -0.25, 0, u, V);
		tess.addVertexWithUV(1, -0.25, 0, U, V);
		tess.addVertexWithUV(1, -0.25, 1, U, v);
		//Top Slope
		tess.addVertexWithUV(0, -0.25, 1, u1, v1);
		tess.addVertexWithUV(1, 0.75, 1, u1, V1);
		tess.addVertexWithUV(1, 0.75, 0, U1, V1);
		tess.addVertexWithUV(0, -0.25, 0, U1, v1);
		
		tess.addVertexWithUV(1, -0.25, 0, u, v);
		tess.addVertexWithUV(1, 0.75, 0, u, V);
		tess.addVertexWithUV(1, 0.75, 1, U, V);
		tess.addVertexWithUV(1, -0.25, 1, U, v);
		
		tess.addVertexWithUV(1, 0.75, 0, u, v);
		tess.addVertexWithUV(1, -0.25, 0, u, V);
		tess.addVertexWithUV(0, -0.25, 0, U, V);
		tess.addVertexWithUV(1, 0.75, 0, U, v);
		
		tess.addVertexWithUV(1, 0.75, 1, u, v);
		tess.addVertexWithUV(0, -0.25, 1, u, V);
		tess.addVertexWithUV(1, -0.25, 1, U, V);
		tess.addVertexWithUV(1, 0.75, 1, U, v);
		
		tess.addTranslation(-x, -y, -z);
        return true;
    }

	@Override
	public boolean shouldRender3DInInventory() {
		return false;
	}

	@Override
	public int getRenderId() {
		return ClientProxy.RoadsRampShortRenderID;
	}
}