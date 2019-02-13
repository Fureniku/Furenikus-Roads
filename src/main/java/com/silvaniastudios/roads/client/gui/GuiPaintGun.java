package com.silvaniastudios.roads.client.gui;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;
import com.silvaniastudios.roads.blocks.tileentities.paintfiller.PaintFillerEntity;
import com.silvaniastudios.roads.items.PaintGun;
import com.silvaniastudios.roads.items.PaintGunItemRegistry;
import com.silvaniastudios.roads.network.PaintGunUpdatePacket;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;

public class GuiPaintGun extends GuiScreen {
	
	private final int guiWidth = 256;
	private final int guiHeight = 200;

	private GuiButton cat1;
	private GuiButton cat2;
	private GuiButton cat3;
	private GuiButton cat4;
	private GuiButton cat5;
	
	private GuiIconButton icon1;
	private GuiIconButton icon2;
	private GuiIconButton icon3;
	private GuiIconButton icon4;
	private GuiIconButton icon5;
	private GuiIconButton icon6;
	private GuiIconButton icon7;
	private GuiIconButton icon8;
	private GuiIconButton icon9;
	private GuiIconButton icon10;
	private GuiIconButton icon11;
	private GuiIconButton icon12;
	private GuiIconButton icon13;
	private GuiIconButton icon14;
	private GuiIconButton icon15;
	private GuiIconButton icon16;
	private GuiIconButton icon17;
	private GuiIconButton icon18;
	private GuiIconButton icon19;
	private GuiIconButton icon20;
	private GuiIconButton icon21;
	private GuiIconButton icon22;
	private GuiIconButton icon23;
	private GuiIconButton icon24;
	private GuiIconButton icon25;
	private GuiIconButton icon26;
	private GuiIconButton icon27;
	private GuiIconButton icon28;
	private GuiIconButton icon29;
	private GuiIconButton icon30;
	private GuiIconButton icon31;
	private GuiIconButton icon32;
	private GuiIconButton icon33;
	private GuiIconButton icon34;
	private GuiIconButton icon35;
	private GuiIconButton icon36;
	private GuiIconButton icon37;
	private GuiIconButton icon38;
	private GuiIconButton icon39;
	private GuiIconButton icon40;
	private GuiIconButton icon41;
	private GuiIconButton icon42;
	
	private ArrayList<GuiIconButton> iconBtnList = new ArrayList<GuiIconButton>();
	
	private GuiButton whitePaint;
	private GuiButton yellowPaint;
	private GuiButton redPaint;
	
	private GuiButton sizeSmall;
	private GuiButton sizeLarge;
	
	private int pageId = 0;
	private PaintBlockBase selection = null;
	private int selMeta = 0;
	private int selectedColour = 0; //0 = white, 1 = yellow, 2 = red
	private boolean isLarge = false;
	
	private int gun_white = 0;
	private int gun_yellow = 0;
	private int gun_red = 0;
	
	private static ResourceLocation texture = new ResourceLocation(FurenikusRoads.MODID + ":textures/gui/paint_gun_background.png");
	private static ResourceLocation texture_2 = new ResourceLocation(FurenikusRoads.MODID + ":textures/gui/paint_gun_background_2.png");
	
	public GuiPaintGun() {
	}
	
	@Override
	public void initGui() {
		ItemStack item = mc.player.getHeldItem(EnumHand.MAIN_HAND);
		
		if (item.getItem() instanceof PaintGun) {
			if (item.hasTagCompound()) {
				NBTTagCompound nbt = item.getTagCompound();
				selectedColour = nbt.getInteger("colour");
				pageId = nbt.getInteger("pageId");
				isLarge = nbt.getBoolean("isLarge");
				
				selection = getSelectionFromId(nbt.getInteger("selectedId"));
				selMeta = nbt.getInteger("selMeta");
				
				gun_white = nbt.getInteger("white_paint");
				gun_yellow = nbt.getInteger("yellow_paint");
				gun_red = nbt.getInteger("red_paint");
			}
		}
		
		int guiLeft = (width - guiWidth) / 2;
	    int guiTop = (height - guiHeight) / 2;
	    
		cat1 = new GuiButton(0, guiLeft + 9, guiTop + 9, 98, 20, "Lines");
		cat2 = new GuiButton(1, guiLeft + 9, guiTop + 31, 98, 20, "Icons");
		cat3 = new GuiButton(2, guiLeft + 9, guiTop + 53, 98, 20, "Letters");
		cat4 = new GuiButton(3, guiLeft + 9, guiTop + 75, 98, 20, "Text");
		cat5 = new GuiButton(4, guiLeft + 9, guiTop + 97, 98, 20, "Junction/Filter");
		
		whitePaint = new GuiButton(47, guiLeft + 113, guiTop + 171, 42, 20, "White");
		yellowPaint = new GuiButton(48, guiLeft + 158, guiTop + 171, 43, 20, "Yellow");
		redPaint = new GuiButton(49, guiLeft + 204, guiTop + 171, 42, 20, "Red");
		
		sizeSmall = new GuiButton(50, guiLeft + 118, guiTop + 140, 60, 20, "Small");
		sizeLarge = new GuiButton(51, guiLeft + 182, guiTop + 140, 60, 20, "Large");
		
		icon1 = new GuiIconButton(5,  guiLeft + 115, guiTop + 11);
		icon2 = new GuiIconButton(6,  guiLeft + 137, guiTop + 11);
		icon3 = new GuiIconButton(7,  guiLeft + 159, guiTop + 11);
		icon4 = new GuiIconButton(8,  guiLeft + 181, guiTop + 11);
		icon5 = new GuiIconButton(9,  guiLeft + 203, guiTop + 11);
		icon6 = new GuiIconButton(10, guiLeft + 225, guiTop + 11);
		
		icon7  = new GuiIconButton(11, guiLeft + 115, guiTop + 33);
		icon8  = new GuiIconButton(12, guiLeft + 137, guiTop + 33);
		icon9  = new GuiIconButton(13, guiLeft + 159, guiTop + 33);
		icon10 = new GuiIconButton(14, guiLeft + 181, guiTop + 33);
		icon11 = new GuiIconButton(15, guiLeft + 203, guiTop + 33);
		icon12 = new GuiIconButton(16, guiLeft + 225, guiTop + 33);
		
		icon13 = new GuiIconButton(17, guiLeft + 115, guiTop + 55);
		icon14 = new GuiIconButton(18, guiLeft + 137, guiTop + 55);
		icon15 = new GuiIconButton(19, guiLeft + 159, guiTop + 55);
		icon16 = new GuiIconButton(20, guiLeft + 181, guiTop + 55);
		icon17 = new GuiIconButton(21, guiLeft + 203, guiTop + 55);
		icon18 = new GuiIconButton(22, guiLeft + 225, guiTop + 55);
		
		icon19 = new GuiIconButton(23, guiLeft + 115, guiTop + 77);
		icon20 = new GuiIconButton(24, guiLeft + 137, guiTop + 77);
		icon21 = new GuiIconButton(25, guiLeft + 159, guiTop + 77);
		icon22 = new GuiIconButton(26, guiLeft + 181, guiTop + 77);
		icon23 = new GuiIconButton(27, guiLeft + 203, guiTop + 77);
		icon24 = new GuiIconButton(28, guiLeft + 225, guiTop + 77);
		
		icon25 = new GuiIconButton(29, guiLeft + 115, guiTop + 99);
		icon26 = new GuiIconButton(30, guiLeft + 137, guiTop + 99);
		icon27 = new GuiIconButton(31, guiLeft + 159, guiTop + 99);
		icon28 = new GuiIconButton(32, guiLeft + 181, guiTop + 99);
		icon29 = new GuiIconButton(33, guiLeft + 203, guiTop + 99);
		icon30 = new GuiIconButton(34, guiLeft + 225, guiTop + 99);
		
		icon31 = new GuiIconButton(35, guiLeft + 115, guiTop + 121);
		icon32 = new GuiIconButton(36, guiLeft + 137, guiTop + 121);
		icon33 = new GuiIconButton(37, guiLeft + 159, guiTop + 121);
		icon34 = new GuiIconButton(38, guiLeft + 181, guiTop + 121);
		icon35 = new GuiIconButton(39, guiLeft + 203, guiTop + 121);
		icon36 = new GuiIconButton(40, guiLeft + 225, guiTop + 121);
		
		icon37 = new GuiIconButton(41, guiLeft + 115, guiTop + 143);
		icon38 = new GuiIconButton(42, guiLeft + 137, guiTop + 143);
		icon39 = new GuiIconButton(43, guiLeft + 159, guiTop + 143);
		icon40 = new GuiIconButton(44, guiLeft + 181, guiTop + 143);
		icon41 = new GuiIconButton(45, guiLeft + 203, guiTop + 143);
		icon42 = new GuiIconButton(46, guiLeft + 225, guiTop + 143);
		
		buttonList.add(cat1);
		buttonList.add(cat2);
		buttonList.add(cat3);
		buttonList.add(cat4);
		buttonList.add(cat5);
		
		buttonList.add(whitePaint);
		buttonList.add(yellowPaint);
		buttonList.add(redPaint);
		
		buttonList.add(sizeSmall);
		buttonList.add(sizeLarge);
		
		buttonList.add(icon1);  iconBtnList.add(icon1);
		buttonList.add(icon2);  iconBtnList.add(icon2);
		buttonList.add(icon3);  iconBtnList.add(icon3);
		buttonList.add(icon4);  iconBtnList.add(icon4);
		buttonList.add(icon5);  iconBtnList.add(icon5);
		buttonList.add(icon6);  iconBtnList.add(icon6);
		buttonList.add(icon7);  iconBtnList.add(icon7);
		buttonList.add(icon8);  iconBtnList.add(icon8);
		buttonList.add(icon9);  iconBtnList.add(icon9);
		buttonList.add(icon10); iconBtnList.add(icon10);
		buttonList.add(icon11); iconBtnList.add(icon11);
		buttonList.add(icon12); iconBtnList.add(icon12);
		buttonList.add(icon13); iconBtnList.add(icon13);
		buttonList.add(icon14); iconBtnList.add(icon14);
		buttonList.add(icon15); iconBtnList.add(icon15);
		buttonList.add(icon16); iconBtnList.add(icon16);
		buttonList.add(icon17); iconBtnList.add(icon17);
		buttonList.add(icon18); iconBtnList.add(icon18);
		buttonList.add(icon19); iconBtnList.add(icon19);
		buttonList.add(icon20); iconBtnList.add(icon20);
		buttonList.add(icon21); iconBtnList.add(icon21);
		buttonList.add(icon22); iconBtnList.add(icon22);
		buttonList.add(icon23); iconBtnList.add(icon23);
		buttonList.add(icon24); iconBtnList.add(icon24);
		buttonList.add(icon25); iconBtnList.add(icon25);
		buttonList.add(icon26); iconBtnList.add(icon26);
		buttonList.add(icon27); iconBtnList.add(icon27);
		buttonList.add(icon28); iconBtnList.add(icon28);
		buttonList.add(icon29); iconBtnList.add(icon29);
		buttonList.add(icon30); iconBtnList.add(icon30);
		buttonList.add(icon31); iconBtnList.add(icon31);
		buttonList.add(icon32); iconBtnList.add(icon32);
		buttonList.add(icon33); iconBtnList.add(icon33);
		buttonList.add(icon34); iconBtnList.add(icon34);
		buttonList.add(icon35); iconBtnList.add(icon35);
		buttonList.add(icon36); iconBtnList.add(icon36);
		buttonList.add(icon37); iconBtnList.add(icon37);
		buttonList.add(icon38); iconBtnList.add(icon38);
		buttonList.add(icon39);	iconBtnList.add(icon39);
		buttonList.add(icon40); iconBtnList.add(icon40);
		buttonList.add(icon41); iconBtnList.add(icon41);
		buttonList.add(icon42); iconBtnList.add(icon42);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	@Override
	public void updateScreen() {
		if (selectedColour == 0) { whitePaint.enabled = false; yellowPaint.enabled = true; redPaint.enabled = true; }
		if (selectedColour == 1) { whitePaint.enabled = true; yellowPaint.enabled = false; redPaint.enabled = true; }
		if (selectedColour == 2) { whitePaint.enabled = true; yellowPaint.enabled = true; redPaint.enabled = false; }
		
		if (pageId == 1) { cat1.enabled = false; cat2.enabled = true; cat3.enabled = true; cat4.enabled = true; cat5.enabled = true; }
		if (pageId == 2) { cat1.enabled = true; cat2.enabled = false; cat3.enabled = true; cat4.enabled = true; cat5.enabled = true; }
		if (pageId == 3) { cat1.enabled = true; cat2.enabled = true; cat3.enabled = false; cat4.enabled = true; cat5.enabled = true; }
		if (pageId == 4) { cat1.enabled = true; cat2.enabled = true; cat3.enabled = true; cat4.enabled = false; cat5.enabled = true; }
		if (pageId == 5) { cat1.enabled = true; cat2.enabled = true; cat3.enabled = true; cat4.enabled = true; cat5.enabled = false; }
		
		sizeSmall.visible = false;
		sizeLarge.visible = false;
		
		if (pageId == 0) {
			for (int i = 0; i < iconBtnList.size(); i++) {
				GuiIconButton icnBtn = iconBtnList.get(i);
				icnBtn.visible = false;
			}
		}
		
		if (pageId == 1) {
			for (int i = 0; i < iconBtnList.size(); i++) {
				GuiIconButton icnBtn = iconBtnList.get(i);
				if (i < PaintGunItemRegistry.lines.size()) {
					icnBtn.visible = true;
					icnBtn.iconName = "1";
					icnBtn.iconId = i;
					icnBtn.colourType = selectedColour;
				} else {
					icnBtn.visible = false;
				}
			}
		}
		
		if (pageId == 2) {
			for (int i = 0; i < iconBtnList.size(); i++) {
				GuiIconButton icnBtn = iconBtnList.get(i);
				if (i < PaintGunItemRegistry.icons.size()) {
					icnBtn.visible = true;
					icnBtn.iconName = "2";
					icnBtn.iconId = i;
					icnBtn.colourType = selectedColour;
				} else {
					icnBtn.visible = false;
				}
			}
		}
		
		if (pageId == 3) {
			for (int i = 0; i < iconBtnList.size(); i++) {
				GuiIconButton icnBtn = iconBtnList.get(i);
				if (i < PaintGunItemRegistry.letters.size()) {
					icnBtn.visible = true;
					icnBtn.iconName = "3";
					icnBtn.iconId = i;
					icnBtn.colourType = selectedColour;
					icnBtn.hoverText = "Letter " + i;
				} else {
					icnBtn.visible = false;
				}
			}
		}
		
		if (pageId == 4) {
			sizeSmall.visible = true;
			sizeLarge.visible = true;
			
			if (isLarge) {
				sizeSmall.enabled = true; sizeLarge.enabled = false;
			} else {
				sizeSmall.enabled = false; sizeLarge.enabled = true;
			}
			
			for (int i = 0; i < iconBtnList.size(); i++) {
				GuiIconButton icnBtn = iconBtnList.get(i);
				if (i < PaintGunItemRegistry.text.size() / 2) {
					icnBtn.visible = true;
					icnBtn.iconName = "4";
					icnBtn.iconId = i;
					icnBtn.colourType = selectedColour;
				} else {
					icnBtn.visible = false;
				}
			}
		}
		
		if (pageId == 5) {
			for (int i = 0; i < iconBtnList.size(); i++) {
				GuiIconButton icnBtn = iconBtnList.get(i);
				if (i < PaintGunItemRegistry.junction.size()) {
					icnBtn.visible = true;
					icnBtn.iconName = "5";
					icnBtn.iconId = i;
					icnBtn.colourType = selectedColour;
				} else {
					icnBtn.visible = false;
				}
			}
		}
	}

	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(texture);
        
        int guiLeft = (width - guiWidth) / 2;
        int guiTop = (height - guiHeight) / 2;
        
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, guiWidth, guiHeight);
        
        int x = -1;
        int y = -1;
        
        if (pageId == 1) {
        	if (PaintGunItemRegistry.lines.contains(selection)) {
        		x = findGridX();
        		y = findGridY();
        	}
        }
        
		if (pageId == 2) {
			if (PaintGunItemRegistry.icons.contains(selection)) {
				x = findGridX();
        		y = findGridY();
        	}
		}
		
		if (pageId == 3) {
			if (PaintGunItemRegistry.letters.contains(selection)) {
				x = findGridX();
        		y = findGridY();
        	}
		}
		
		if (pageId == 4) {
			if (PaintGunItemRegistry.text.contains(selection)) {
				x = findGridX();
        		y = findGridY();
        	}
		}
		
		if (pageId == 5) {
			if (PaintGunItemRegistry.junction.contains(selection)) {
				x = findGridX();
        		y = findGridY();
        	}
		}
		
		if (x > 0 && y > 0) {
			drawTexturedModalRect(guiLeft + 91 + (x*22), guiTop - 13 + (y*22), 0, 234, 22, 22);
		}
		
		mc.getTextureManager().bindTexture(texture_2);
		drawTexturedModalRect(guiLeft+256, guiTop, 0, 0, 50, 70);
		
		drawGunFillBar(0, guiLeft, guiTop, gun_white);
		drawGunFillBar(1, guiLeft, guiTop, gun_yellow);
		drawGunFillBar(2, guiLeft, guiTop, gun_red);
        super.drawScreen(mouseX, mouseY, partialTicks);
        
        drawTooltip(guiLeft+256, guiTop, mouseX, mouseY);
    }
	
	private void drawTooltip(int left, int top, int mouseX, int mouseY) {
		if (mouseX >= (left)      && mouseX <= (left + 10) && mouseY >= (top + 10) && mouseY <= (top + 60)) { this.drawHoveringText(gun_white  + "/" + PaintFillerEntity.GUN_TANK_CAP, mouseX, mouseY); }
		if (mouseX >= (left + 16) && mouseX <= (left + 26) && mouseY >= (top + 10) && mouseY <= (top + 60)) { this.drawHoveringText(gun_yellow + "/" + PaintFillerEntity.GUN_TANK_CAP, mouseX, mouseY); }
		if (mouseX >= (left + 32) && mouseX <= (left + 42) && mouseY >= (top + 10) && mouseY <= (top + 60)) { this.drawHoveringText(gun_red    + "/" + PaintFillerEntity.GUN_TANK_CAP, mouseX, mouseY); }
		
		if (mouseX >= left-256 + 115 && mouseX <= left-256 + 245 && mouseY >= top + 11 && mouseY <= top + 163) {
			int buttonId = getHoveredButtonId(left-256, top, mouseX, mouseY)-1;
			
			if (buttonId >= 0) {
				PaintBlockBase original = PaintGunItemRegistry.getBlockFromIdAndPage(buttonId, pageId);
				ItemStack stack = ItemStack.EMPTY;
				
				if (original != null) {
					if (selectedColour == 0) { stack = new ItemStack(original, 1, getTooltipMeta(buttonId)); }
					if (selectedColour == 1) { stack = new ItemStack(PaintGunItemRegistry.getYellow(original), 1, getTooltipMeta(buttonId)); }
					if (selectedColour == 2) { stack = new ItemStack(PaintGunItemRegistry.getRed(original), 1, getTooltipMeta(buttonId)); }
					
					if (stack != null && stack != ItemStack.EMPTY) {
						this.drawHoveringText(stack.getDisplayName(), mouseX, mouseY);
					}
				}
			}
		}
	}
	
	private int getHoveredButtonId(int left, int top, int mouseX, int mouseY) {
		int gridX = 0;
		int gridY = 0;
		if (mouseX >= left + 115 && mouseX <= left + 135) { gridX = 1; }
		if (mouseX >= left + 137 && mouseX <= left + 157) { gridX = 2; }
		if (mouseX >= left + 159 && mouseX <= left + 179) { gridX = 3; }
		if (mouseX >= left + 181 && mouseX <= left + 201) { gridX = 4; }
		if (mouseX >= left + 203 && mouseX <= left + 223) { gridX = 5; }
		if (mouseX >= left + 225 && mouseX <= left + 245) { gridX = 6; }
	
		if (mouseY >= top + 11  && mouseY <= top + 31)  { gridY = 1; }
		if (mouseY >= top + 33  && mouseY <= top + 53)  { gridY = 2; }
		if (mouseY >= top + 55  && mouseY <= top + 75)  { gridY = 3; }
		if (mouseY >= top + 77  && mouseY <= top + 97)  { gridY = 4; }
		if (mouseY >= top + 99  && mouseY <= top + 119) { gridY = 5; }
		if (mouseY >= top + 121 && mouseY <= top + 141) { gridY = 6; }
		if (mouseY >= top + 143 && mouseY <= top + 163) { gridY = 7; }
		
		if (gridX == 0 || gridY == 0) { return -1; } 
		return (gridX + ((gridY-1) * 6));
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
	
	public int getTooltipMeta(int id) {
		if (pageId == 1) {
			if (id == 1 || id == 3 || id == 5 || id == 7) {
				return 2;
			}
		}
		
		if (pageId == 2) {
			if (id == 7 || id == 8 || id == 12 || id == 17)  { return 4; }
			if (id == 13 || id == 16) { return 8; }
		}
		
		if (pageId == 3) {
			if (id % 2 != 0) { return 8; }
		}
		
		if (pageId == 5) {
			if (id == 4  || id == 14 || id == 15 || id == 23 || id == 27) { return 8; }
			if (id == 22 || id == 26) { return 4; }
			if (id == 24 || id == 28) { return 12; }
		}
		
		return 0;
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		if (button == whitePaint) { selectedColour = 0; }
		if (button == yellowPaint) { selectedColour = 1; }
		if (button == redPaint) { selectedColour = 2; }
		
		if (button == sizeSmall) { isLarge = false; }
		if (button == sizeLarge) { isLarge = true; }
		
		if (button == cat1) { pageId = 1; }
		if (button == cat2) { pageId = 2; }
		if (button == cat3) { pageId = 3; }
		if (button == cat4) { pageId = 4; }
		if (button == cat5) { pageId = 5; }
		
		if (button.id >= 5 && button.id <= 46) { makeSelection(button); }
		
		FurenikusRoads.PACKET_CHANNEL.sendToServer(new PaintGunUpdatePacket(getListIndex(), selMeta, selectedColour, pageId, isLarge));
	}
	
	public void makeSelection(GuiButton button) {
		int x = button.id - 5;
		if (pageId == 1) {
			selection = PaintGunItemRegistry.lines.get(x);
			selMeta = PaintGunItemRegistry.linesMeta.get(x);
		}
		if (pageId == 2) {
			selection = PaintGunItemRegistry.icons.get(x);
			selMeta = PaintGunItemRegistry.iconsMeta.get(x);
		}
		if (pageId == 3) {
			selection = PaintGunItemRegistry.letters.get(x);
			selMeta = PaintGunItemRegistry.lettersMeta.get(x);
		}
		if (pageId == 4) {
			selection = PaintGunItemRegistry.text.get(x);
			selMeta = PaintGunItemRegistry.textMeta.get(x);
		}
		if (pageId == 5) {
			selection = PaintGunItemRegistry.junction.get(x);
			selMeta = PaintGunItemRegistry.junctionMeta.get(x);
		}
	}
	
	public int getListIndex() {
		if (pageId == 1) {
			for (int i = 0; i < PaintGunItemRegistry.lines.size(); i++) {
				if (PaintGunItemRegistry.lines.get(i).equals(selection)) {
					return i;
				}
			}
		}
		if (pageId == 2) { 
			for (int i = 0; i < PaintGunItemRegistry.icons.size(); i++) {
				if (PaintGunItemRegistry.icons.get(i).equals(selection)) {
					return i;
				}
			}
		}
		if (pageId == 3) { 
			for (int i = 0; i < PaintGunItemRegistry.letters.size(); i++) {
				if (PaintGunItemRegistry.letters.get(i).equals(selection)) {
					return i;
				}
			}
		}
		if (pageId == 4) { 
			for (int i = 0; i < PaintGunItemRegistry.text.size() / 2; i++) {
				if (PaintGunItemRegistry.text.get(i).equals(selection)) {
					return i;
				}
			}
		}
		if (pageId == 5) { 
			for (int i = 0; i < PaintGunItemRegistry.junction.size(); i++) {
				if (PaintGunItemRegistry.junction.get(i).equals(selection)) {
					return i;
				}
			}
		}
		return 0;
	}
	
	public PaintBlockBase getSelectionFromId(int sel) {
		if (pageId == 1 && PaintGunItemRegistry.lines.size() > sel) { return PaintGunItemRegistry.lines.get(sel); }
		if (pageId == 2 && PaintGunItemRegistry.icons.size() > sel) { return PaintGunItemRegistry.icons.get(sel); }
		if (pageId == 3 && PaintGunItemRegistry.letters.size() > sel) { return PaintGunItemRegistry.letters.get(sel); }
		if (pageId == 4 && PaintGunItemRegistry.text.size()/2 > sel) { return PaintGunItemRegistry.text.get(sel); }
		if (pageId == 5 && PaintGunItemRegistry.junction.size() > sel) { return PaintGunItemRegistry.junction.get(sel); }
		return PaintGunItemRegistry.lines.get(0);
	}
	
	public int getMetaOffset() {
		int sel = getListIndex();
		
		if (pageId == 1 || pageId == 3) {
			if (selMeta > 0) {
				return 1;
			}
		}
		
		if (pageId == 2) {
			if (selection == FRBlocks.white_junction_a) {
				if (selMeta == 4) { return 0; }
				if (selMeta == 8) { return 1; }
				if (selMeta == 0) { return 2; }
			}
			if (selection == FRBlocks.white_junction_b) {
				if (selMeta == 0) { return 0; }
				if (selMeta == 8) { return 1; }
				if (selMeta == 4) { return 2; }
			}
			
			if (sel == 8 && selMeta == 0) { return 1; }
		}
		
		if (pageId == 5) {
			if (selection == FRBlocks.white_junction_side_line_connection) {
				if (selMeta == 12)  { return 0; } //0 -> 12
				if (selMeta == 8)  { return 1; } //4 -> 8
				if (selMeta == 0) { return 2; } //8 -> 0
				if (selMeta == 4)  { return 3; } //12 -> 4
			}
			if (selection == FRBlocks.white_junction_side_line_connection_thin) {//4 12 0 8
				if (selMeta == 4)  { return 0; }
				if (selMeta == 12) { return 1; }
				if (selMeta == 0)  { return 2; }
				if (selMeta == 8)  { return 3; }
			}
			
			if (selection == FRBlocks.white_junction_side_line_connection_thick_thick) {
				if (selMeta == 0)  { return 0; }
				if (selMeta == 4)  { return 1; }
				if (selMeta == 8)  { return 2; }
				if (selMeta == 12) { return 3; }
			}
			
			if (selection == FRBlocks.white_junction_mid_line_connection) {
				if (selMeta == 0)  { return 0; }
				if (selMeta == 4)  { return 1; }
				if (selMeta == 8)  { return 2; }
				if (selMeta == 12) { return 3; }
			}
		}
		
		return 0;
	}
	
	public int findGridX() {
		int x = getListIndex()+1+getMetaOffset();
		
		if (x == 1 || x == 7  || x == 13 || x == 19 || x == 25 || x == 31 || x == 37) { return 1; }
		if (x == 2 || x == 8  || x == 14 || x == 20 || x == 26 || x == 32 || x == 38) { return 2; }
		if (x == 3 || x == 9  || x == 15 || x == 21 || x == 27 || x == 33 || x == 39) { return 3; }
		if (x == 4 || x == 10 || x == 16 || x == 22 || x == 28 || x == 34 || x == 40) { return 4; }
		if (x == 5 || x == 11 || x == 17 || x == 23 || x == 29 || x == 35 || x == 41) { return 5; }
		if (x == 6 || x == 12 || x == 18 || x == 24 || x == 30 || x == 36 || x == 42) { return 6; }
		
		return 1;
	}
	
	public int findGridY() {
		int y = getListIndex()+1+getMetaOffset();
		
		if (y > 0 && y < 7)   { return 1; }
		if (y > 6 && y < 13)  { return 2; }
		if (y > 12 && y < 19) { return 3; }
		if (y > 18 && y < 25) { return 4; }
		if (y > 24 && y < 31) { return 5; }
		if (y > 30 && y < 37) { return 6; }
		if (y > 36 && y < 43) { return 7; }
		
		return 1;
	}
}
