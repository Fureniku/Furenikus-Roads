package co.uk.silvania.roads.tileentities.itemrenderers;

import org.lwjgl.opengl.GL11;

import co.uk.silvania.roads.client.models.TrafficLightModel;
import co.uk.silvania.roads.tileentities.entities.TileEntityTrafficLightEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;

public class TrafficLightItemRenderer implements IItemRenderer {
	
    private TrafficLightModel model;
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}
	
	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}
	
	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch(type) {
			case INVENTORY: {
				renderTrafficLight();
				return;
			}
			
			default: return;
		}
	}

	private void renderTrafficLight()
	{
		TileEntity te = new TileEntityTrafficLightEntity();
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, 0.0F, 0.0F);
        //GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(1.0F, -1F, -1F);
        //model.render((entity), 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
	}
}