package com.silvaniastudios.roads.client.gui.paintgun;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.paint.LargeTextPaintBlock;
import com.silvaniastudios.roads.client.gui.GuiScrollingList_Mod;
import com.silvaniastudios.roads.registries.PaintIconObject;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class PaintGunPaintList extends GuiScrollingList_Mod {

	private FontRenderer fontRenderer;
	private Minecraft client;
	private GuiPaintGun gui;
	private ArrayList<PaintIconObject> paints;
	private boolean enabled = false;
	
	private static final ResourceLocation TEXTURE_WIDGET = new ResourceLocation(FurenikusRoads.MODID, "textures/gui/paint_list.png");

	public PaintGunPaintList(int width, int height, int top, int left, int entryHeight, int screenWidth, int screenHeight, FontRenderer font, Minecraft mc, GuiPaintGun gui, ArrayList<PaintIconObject> paints, ArrayList<String> tooltipList) {
		super(mc, width, height, top, height+top, left, entryHeight, screenWidth, screenHeight, tooltipList);
		this.fontRenderer = font;
		this.client = mc;
		this.gui = gui;
		this.paints = paints;
	}
	
	public void setEnabled(boolean b) {
		enabled = b;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	@Override
	protected int getSize() {
		return (int) Math.ceil(paints.size() / 5.0);
	}

	@Override
	protected void elementClicked(int index, boolean doubleClick) {
		int slot = (index*5) + ((mouseX - left)/25);
		if (slot < paints.size()) {
			gui.setSelectedId(slot);
		}
	}
	
	@Override protected boolean isSelected(int index) { return false; }
	@Override protected void drawBackground() {}
	
	@Override
	protected void drawSlot(int slotIdx, int entryRight, int slotTop, int slotBuffer, Tessellator tess) {
		GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_FOG);
        GL11.glColor4f(1,1,1,1);
        
		int left = entryRight - this.listWidth + 7;
		
		int slot = slotIdx * 5;
		int horizontal = 0;
		
		this.client.getTextureManager().bindTexture(TEXTURE_WIDGET);

		while (horizontal < 5) {
			if (slot+horizontal < paints.size()) {
				int yOffset = 0;
				
				//If it's the current selected slot, set a darker background
				if (gui.getCategoryId() == gui.getSelectedCategoryId() && gui.getSlotId() == slot+horizontal) {
					yOffset = 48;
				}
				
				//If we're hovering over it, put a lighter ring around it
				if (mouseY >= slotTop && mouseY <= (slotTop + this.slotHeight)) {
					if (mouseX >= left + (horizontal*25) && mouseX <= left + (horizontal*25) + 24) {
						yOffset += 24;
					}
				}
				
				PaintIconObject paint = paints.get(slot+horizontal);
				
				if (paint.getPaint().canConnect(paint.getIndex()) || paint.getPaint() instanceof LargeTextPaintBlock && paint.getMeta() != 0) {
					gui.drawTexturedModalRect(left + (horizontal*25), slotTop, 24, yOffset, 24, 24);
				} else {
					gui.drawTexturedModalRect(left + (horizontal*25), slotTop, 0, yOffset, 24, 24);
				}
			}
			horizontal++;
		}
		
		//Iterate the whole list twice because otherwise it breaks the rendering -_-
		horizontal = 0;
		while (horizontal < 5) {
			if (slot+horizontal < paints.size()) {
				drawItemStack(new ItemStack(paints.get(slot+horizontal).getPaint(), 1, paints.get(slot+horizontal).getMeta()), left + (horizontal*25) + 4, slotTop + 4, 1.0f, 0.0f, 0.0f);
			}
			horizontal++;
		}
	}
}
