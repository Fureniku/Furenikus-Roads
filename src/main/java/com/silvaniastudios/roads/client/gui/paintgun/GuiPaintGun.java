package com.silvaniastudios.roads.client.gui.paintgun;

import java.io.IOException;
import java.util.ArrayList;

import net.minecraft.util.text.TextFormatting;
import org.lwjgl.opengl.GL11;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;
import com.silvaniastudios.roads.blocks.tileentities.paintfiller.PaintFillerEntity;
import com.silvaniastudios.roads.client.gui.GuiIconButton;
import com.silvaniastudios.roads.items.PaintGun;
import com.silvaniastudios.roads.network.PaintGunUpdatePacket;
import com.silvaniastudios.roads.registries.PaintCategoryList;
import com.silvaniastudios.roads.registries.PaintGunItemRegistry;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;

public class GuiPaintGun extends GuiScreen {
	
	private final int guiWidth = 256;
	private final int guiHeight = 200;
	
	PaintGunCategoryList categoryList;
	ArrayList<PaintGunPaintList> paintLists = new ArrayList<PaintGunPaintList>();
	ArrayList<String> tooltipList = new ArrayList<String>();
	
	private GuiButton whitePaint;
	private GuiButton yellowPaint;
	private GuiButton redPaint;
	
	private GuiButton sizeSmall;
	private GuiButton sizeLarge;
	
	private int currentPageId = 0;
	private int selectedPageId = 0;
	private int selectedSlotId = 0;
	private String selectedColour = "white"; //0 = white, 1 = yellow, 2 = red
	private boolean isLarge = false;
	
	private int gun_white = 0;
	private int gun_yellow = 0;
	private int gun_red = 0;
	
	private static ResourceLocation texture = new ResourceLocation(FurenikusRoads.MODID + ":textures/gui/paint_gun_background.png");
	private static ResourceLocation texture_2 = new ResourceLocation(FurenikusRoads.MODID + ":textures/gui/paint_gun_background_2.png");
	
	public GuiPaintGun() {
	}
	
	public int getCategoryId() {
		return currentPageId;
	}
	
	public int getSelectedCategoryId() {
		return selectedPageId;
	}
	
	public int getSlotId() {
		return selectedSlotId;
	}
	
	public void setPageId(int id) {
		currentPageId = id;
	}
	
	public void setSelectedId(int id) {
		selectedPageId = currentPageId;
		selectedSlotId = id;
		FurenikusRoads.PACKET_CHANNEL.sendToServer(new PaintGunUpdatePacket(selectedSlotId, selectedColour, currentPageId, isLarge));
	}
	
	public void setCategoryId(int id) {
		paintLists.get(currentPageId).setEnabled(false);
		currentPageId = id;
		paintLists.get(currentPageId).setEnabled(true);
		
	}
	
	@Override
	public void initGui() {
		ItemStack item = mc.player.getHeldItem(EnumHand.MAIN_HAND);
		
		if (item.getItem() instanceof PaintGun) {
			if (item.hasTagCompound()) {
				NBTTagCompound nbt = item.getTagCompound();
				selectedColour = nbt.getString("colour");
				currentPageId = nbt.getInteger("pageId");
				selectedPageId = currentPageId;
				isLarge = nbt.getBoolean("isLarge");
				
				selectedSlotId = nbt.getInteger("selectedId");
				
				gun_white = nbt.getInteger("white_paint");
				gun_yellow = nbt.getInteger("yellow_paint");
				gun_red = nbt.getInteger("red_paint");
			}
		}
		
		int guiLeft = (width - guiWidth) / 2;
	    int guiTop = (height - guiHeight) / 2;
	    
	    categoryList = new PaintGunCategoryList(100, 182, guiTop+10, guiLeft+8, 20, width, height, fontRenderer, mc, this);
	    
	    for (int i = 0; i < PaintGunItemRegistry.categoryList.size(); i++) {
	    	PaintCategoryList category = PaintGunItemRegistry.categoryList.get(i);
	    	PaintGunPaintList list = new PaintGunPaintList(132, 154, guiTop+10, guiLeft+114, 25, width, height, fontRenderer, mc, this, category.getWhitePaints(), tooltipList);
	    	if (i == currentPageId) {
	    		list.setEnabled(true);
	    	}
	    	paintLists.add(list);
	    }
		
		whitePaint = new GuiButton(47, guiLeft + 113, guiTop + 171, 42, 20, "White");
		yellowPaint = new GuiButton(48, guiLeft + 158, guiTop + 171, 43, 20, TextFormatting.YELLOW + "Yellow");
		redPaint = new GuiButton(49, guiLeft + 204, guiTop + 171, 42, 20, TextFormatting.RED + "Red");
		
		sizeSmall = new GuiButton(50, guiLeft + 118, guiTop + 140, 60, 20, "Small");
		sizeLarge = new GuiButton(51, guiLeft + 182, guiTop + 140, 60, 20, "Large");
		
		buttonList.add(whitePaint);
		buttonList.add(yellowPaint);
		buttonList.add(redPaint);
		
		buttonList.add(sizeSmall);
		buttonList.add(sizeLarge);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	@Override
	public void updateScreen() {
		if (selectedColour.equals("white")) { whitePaint.enabled = false; yellowPaint.enabled = true; redPaint.enabled = true; }
		if (selectedColour.equals("yellow")) { whitePaint.enabled = true; yellowPaint.enabled = false; redPaint.enabled = true; }
		if (selectedColour.equals("red")) { whitePaint.enabled = true; yellowPaint.enabled = true; redPaint.enabled = false; }
		
		sizeSmall.visible = false;
		sizeLarge.visible = false;
	}

	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(texture);
        
        try {
			paintLists.get(currentPageId).handleMouseInput(mouseX, mouseY);
			categoryList.handleMouseInput(mouseX, mouseY);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        int guiLeft = (width - guiWidth) / 2;
        int guiTop = (height - guiHeight) / 2;
        
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, guiWidth, guiHeight);
		
		mc.getTextureManager().bindTexture(texture_2);
		drawTexturedModalRect(guiLeft+256, guiTop, 0, 0, 50, 70);
		
		drawGunFillBar(0, guiLeft, guiTop, gun_white);
		drawGunFillBar(1, guiLeft, guiTop, gun_yellow);
		drawGunFillBar(2, guiLeft, guiTop, gun_red);
		
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		categoryList.drawScreen(mouseX, mouseY, partialTicks);
		
		for (int i = 0; i < paintLists.size(); i++) {
			if (paintLists.get(i).isEnabled()) {
				tooltipList.clear();
				paintLists.get(i).drawScreen(mouseX, mouseY, partialTicks);
				if (tooltipList.size() > 0) {
					drawHoveringText(tooltipList, mouseX, mouseY);
				}
			}
		}
        
        drawTooltip(guiLeft+256, guiTop, mouseX, mouseY);
    }

	public String getSelectedColour() {
		return selectedColour;
	}
	
	private void drawTooltip(int left, int top, int mouseX, int mouseY) {
		if (mouseX >= (left)      && mouseX <= (left + 10) && mouseY >= (top + 10) && mouseY <= (top + 60)) { this.drawHoveringText(gun_white  + "/" + PaintFillerEntity.GUN_TANK_CAP, mouseX, mouseY); }
		if (mouseX >= (left + 16) && mouseX <= (left + 26) && mouseY >= (top + 10) && mouseY <= (top + 60)) { this.drawHoveringText(gun_yellow + "/" + PaintFillerEntity.GUN_TANK_CAP, mouseX, mouseY); }
		if (mouseX >= (left + 32) && mouseX <= (left + 42) && mouseY >= (top + 10) && mouseY <= (top + 60)) { this.drawHoveringText(gun_red    + "/" + PaintFillerEntity.GUN_TANK_CAP, mouseX, mouseY); }
	}
	
	private void drawGunFillBar(int col, int left, int top, int fill) {
		int p = Math.round(getPercentage(fill, PaintFillerEntity.GUN_TANK_CAP)/2.0F);
		drawTexturedModalRect(left + 256 + (col*16), top + 60 - p, 50 + (col*10), 0 + (50 - p), 10, p);
	}
	
	private int getPercentage(int num, int max) {
		float x = (float) num / (float) max;
		int y = Math.round(x*100);
		if (y > 100) { return 100; }
		return y;
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		if (button == whitePaint) { selectedColour = "white"; }
		if (button == yellowPaint) { selectedColour = "yellow"; }
		if (button == redPaint) { selectedColour = "red"; }
		
		if (button == sizeSmall) { isLarge = false; }
		if (button == sizeLarge) { isLarge = true; }
		
		FurenikusRoads.PACKET_CHANNEL.sendToServer(new PaintGunUpdatePacket(selectedSlotId, selectedColour, selectedPageId, isLarge));
	}
	
	public PaintBlockBase getSelectionFromId(int sel) {
		if (PaintGunItemRegistry.categoryList.size() >= selectedPageId) {
			PaintCategoryList cat = PaintGunItemRegistry.categoryList.get(selectedPageId);
			if (cat.size() >= sel) {
				return cat.getPaint(sel);
			} else {
				return cat.getPaint(0);
			}
		}
		return PaintGunItemRegistry.categoryList.get(0).getPaint(0);
	}
}
