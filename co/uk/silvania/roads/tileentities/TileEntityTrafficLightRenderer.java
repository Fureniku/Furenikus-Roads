package co.uk.silvania.roads.tileentities;

import org.lwjgl.opengl.GL11;


import co.uk.silvania.roads.Roads;
import co.uk.silvania.roads.client.TrafficLightModel;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class TileEntityTrafficLightRenderer extends TileEntitySpecialRenderer {
	
	private final TrafficLightModel model;
	
	public TileEntityTrafficLightRenderer() {
		this.model = new TrafficLightModel();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
		GL11.glPushMatrix();
		//adjustLightFixture(te.getWorldObj(), x, y, z);
		GL11.glScalef(-1F, 1F, -1F);
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		bindTextureByName("/mods/roads/textures/blocks/TrafficLightPoleRed.png");
		this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}

	private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		GL11.glRotatef(meta * (90), 0.0F, 1.0F, 0.0F);
	}
	
	private void adjustLightFixture(World world, int i, int j, int k, Block block) {
		Tessellator tess = Tessellator.instance;
		float brightness = block.getBlockBrightness(world, i, j, k);
		int skyLight = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
		int modulousModifier = skyLight % 65536;
		int divModifier = skyLight / 65536;
		tess.setColorOpaque_F(brightness, brightness, brightness);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit,  (float) modulousModifier,  divModifier);
	}
}



/*public class TileEntityTrafficLightRenderer extends TileEntitySpecialRenderer {
	
	TrafficLightModel model;
	
	public TileEntityTrafficLightRenderer() {
		model = new TrafficLightModel();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f) {

		GL11.glPushMatrix();
		
		GL11.glTranslatef((float)d, (float)d1, (float)d2);
		TileEntity te = (TileEntityTrafficLightEntity)tileentity;
		
		renderBlock(te, tileentity.worldObj, tileentity.xCoord, tileentity.yCoord, tileentity.zCoord, Roads.trafficLight);
		//this.model.render(0.0625F);
		GL11.glPopMatrix();
	}
	
	public void renderBlock(TileEntity te, World world, int i, int j, int k, Block block) {
		
		Tessellator tessellator = Tessellator.instance;
		
		float f = block.getBlockBrightness(world, i, j, k);
		int l = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
		int l1 = l % 65536;
		int l2 = l / 65536;
		tessellator.setColorOpaque_F(f, f, f);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)l1, (float)l2);
		
		int dir = world.getBlockMetadata(i, j, k);
		
		GL11.glPushMatrix();
		GL11.glTranslatef(0.5F, 0F, 0.5F);
		GL11.glRotatef(dir * (-90F), 0F, 1F, 0F);
		GL11.glTranslatef(-0.5F, 0F, -0.5F);
		bindTextureByName("/mods/roads/textures/blocks/TrafficLightPoleRed.png");
		
		this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}


}*/
