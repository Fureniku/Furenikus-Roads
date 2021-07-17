package com.silvaniastudios.roads.client.gui.paintgun;

import org.lwjgl.opengl.GL11;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.client.gui.GuiScrollingList_Mod;
import com.silvaniastudios.roads.registries.PaintCategoryList;
import com.silvaniastudios.roads.registries.PaintGunItemRegistry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

public class PaintGunCategoryList extends GuiScrollingList_Mod {

	private FontRenderer fontRenderer;
	private Minecraft client;
	private GuiPaintGun gui;
	
	private static final ResourceLocation TEXTURE_WIDGET = new ResourceLocation(FurenikusRoads.MODID, "textures/gui/category_list.png");

	public PaintGunCategoryList(int width, int height, int top, int left, int entryHeight, int screenWidth, int screenHeight, FontRenderer font, Minecraft mc, GuiPaintGun gui) {
		super(mc, width, height, top, height+top, left, entryHeight, screenWidth, screenHeight, null);
		this.fontRenderer = font;
		this.client = mc;
		this.gui = gui;
	}
	
	@Override
	protected int getSize() {
		return PaintGunItemRegistry.categoryList.size();
	}

	@Override
	protected void elementClicked(int index, boolean doubleClick) {
		gui.setCategoryId(index);
	}
	
	@Override protected boolean isSelected(int index) { return false; }
	@Override protected void drawBackground() {}
	
	@Override
	protected void drawSlot(int slotIdx, int entryRight, int slotTop, int slotBuffer, Tessellator tess) {
		GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_FOG);
        GL11.glColor4f(1,1,1,1);
        
		int left = entryRight - this.listWidth + 7;
		PaintCategoryList cat = PaintGunItemRegistry.categoryList.get(slotIdx);

		this.client.getTextureManager().bindTexture(TEXTURE_WIDGET);
		
		if (slotIdx == gui.getCategoryId()) {
			gui.drawTexturedModalRect(left, slotTop, 0, 22, 96, 20);
		} else if (this.mouseX >= left && this.mouseX <= entryRight && this.mouseY >= slotTop && this.mouseY <= slotTop + this.slotHeight) {
			gui.drawTexturedModalRect(left, slotTop, 0, 44, 96, 20);
		} else {
			gui.drawTexturedModalRect(left, slotTop, 0, 0, 96, 20);
		}
		
		String n = cat.getCategoryName().substring(0, 1).toUpperCase() + cat.getCategoryName().substring(1);
			
		fontRenderer.drawString(n, left + (this.listWidth/2) - 3 - (fontRenderer.getStringWidth(n)/2), slotTop + 6, 0xFFFFFF);
	}
}
