package com.silvaniastudios.roads.client.gui;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.tileentities.paintfiller.PaintFillerContainer;
import com.silvaniastudios.roads.blocks.tileentities.paintfiller.PaintFillerEntity;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GuiPaintFiller extends GuiContainer {
	
	private static final ResourceLocation guiTextures = new ResourceLocation(FurenikusRoads.MODID + ":textures/gui/paint_filler.png");
	private PaintFillerEntity tileEntity;
	
	public GuiPaintFiller(PaintFillerEntity tileEntity, PaintFillerContainer container) {
		super(container);
		this.tileEntity = tileEntity;
		xSize = 176;
		ySize = 206;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		int left = (width - xSize) / 2;
		int top = (height - ySize) / 2;
        
        drawTooltip(2, left, top, mouseX, mouseY);
        fontRenderer.drawString(I18n.format("roads.gui.paint_filler.name"), 6, 6, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(guiTextures);
        int left = (width - xSize) / 2;
        int top = (height - ySize) / 2;
        drawTexturedModalRect(left, top, 0, 0, xSize, ySize);
        drawGunFillBar(0, left, top, tileEntity.gun_white);
        drawGunFillBar(1, left, top, tileEntity.gun_yellow);
        drawGunFillBar(2, left, top, tileEntity.gun_red);
        drawFillerFillBar(0, left, top, tileEntity.white_paint.getFluidAmount());
        drawFillerFillBar(1, left, top, tileEntity.yellow_paint.getFluidAmount());
        drawFillerFillBar(2, left, top, tileEntity.red_paint.getFluidAmount());
        
        drawFuel(left, top);
        drawProgress(left, top);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
	
	private void drawGunFillBar(int col, int left, int top, int fill) {
		int p = Math.round(getPercentage(fill, PaintFillerEntity.GUN_TANK_CAP)/2.0F);
		drawTexturedModalRect(left + 8 + (col*16), top + 108 - p, 226 + (col*10), 100 + (50 - p), 10, p);
	}
	
	private void drawFillerFillBar(int col, int left, int top, int fill) {
		int p = getPercentage(fill, PaintFillerEntity.FILLER_TANK_CAP);
		drawTexturedModalRect(left + 96 + (col*26), top + 108 - p, 196 + (col*20), 100 - p, 20, p);
	}
	
	private void drawFuel(int left, int top) {
		int p = getPercentage(tileEntity.fuel_remaining, tileEntity.last_fuel_cap);
		int x = Math.round(p / 7.0F);
		drawTexturedModalRect(left + 75, top + 75 + (14-x), 176, 14-x, 14, x);
	}
	
	private void drawProgress(int left, int top) {
		int p = getPercentage(tileEntity.timerCount, RoadsConfig.general.fillerTickRate);
		int x = Math.round(p * 1.6F);
		drawTexturedModalRect(left + 8, top + 114, 0, 252, x, 4);
	}
	
	private void drawTooltip(int col, int left, int top, int mouseX, int mouseY) {
		if (mouseX >= (left + 96)  && mouseX <= (left + 116) && mouseY >= (top + 8) && mouseY <= (top + 108)) { this.drawHoveringText(tileEntity.white_paint.getFluidAmount()  + "/" + PaintFillerEntity.FILLER_TANK_CAP, mouseX - left, mouseY - top + 15); }
		if (mouseX >= (left + 122) && mouseX <= (left + 142) && mouseY >= (top + 8) && mouseY <= (top + 108)) { this.drawHoveringText(tileEntity.yellow_paint.getFluidAmount() + "/" + PaintFillerEntity.FILLER_TANK_CAP, mouseX - left, mouseY - top + 15); }
		if (mouseX >= (left + 148) && mouseX <= (left + 168) && mouseY >= (top + 8) && mouseY <= (top + 108)) { this.drawHoveringText(tileEntity.red_paint.getFluidAmount()    + "/" + PaintFillerEntity.FILLER_TANK_CAP, mouseX - left, mouseY - top + 15); }
		
		if (mouseX >= (left + 8)  && mouseX <= (left + 18) && mouseY >= (top + 58) && mouseY <= (top + 108)) { this.drawHoveringText(tileEntity.gun_white  + "/" + PaintFillerEntity.GUN_TANK_CAP, mouseX - left, mouseY - top + 15); }
		if (mouseX >= (left + 24) && mouseX <= (left + 34) && mouseY >= (top + 58) && mouseY <= (top + 108)) { this.drawHoveringText(tileEntity.gun_yellow + "/" + PaintFillerEntity.GUN_TANK_CAP, mouseX - left, mouseY - top + 15); }
		if (mouseX >= (left + 40) && mouseX <= (left + 50) && mouseY >= (top + 58) && mouseY <= (top + 108)) { this.drawHoveringText(tileEntity.gun_red    + "/" + PaintFillerEntity.GUN_TANK_CAP, mouseX - left, mouseY - top + 15); }
		
		if (mouseX >= (left + 75) && mouseX <= (left + 89) && mouseY >= (top + 75) && mouseY <= (top + 89)) { this.drawHoveringText(tileEntity.fuel_remaining + "/" + tileEntity.last_fuel_cap, mouseX - left, mouseY - top + 15); }
		
		if (RoadsConfig.general.guiGuide) {
			if (mouseX >= (left + 21)  && mouseX <= (left + 37) && mouseY >= (top + 36) && mouseY <= (top + 52)) { this.drawHoveringText(I18n.format("roads.gui.paint_filler.paint_gun_slot"), mouseX - left, mouseY - top + 15); }
			if (mouseX >= (left + 74)  && mouseX <= (left + 90) && mouseY >= (top +  8) && mouseY <= (top + 24)) { this.drawHoveringText(I18n.format("roads.gui.paint_filler.white_slot"), mouseX - left, mouseY - top + 15); }
			if (mouseX >= (left + 74)  && mouseX <= (left + 90) && mouseY >= (top + 30) && mouseY <= (top + 46)) { this.drawHoveringText(I18n.format("roads.gui.paint_filler.yellow_slot"), mouseX - left, mouseY - top + 15); }
			if (mouseX >= (left + 74)  && mouseX <= (left + 90) && mouseY >= (top + 52) && mouseY <= (top + 68)) { this.drawHoveringText(I18n.format("roads.gui.paint_filler.red_slot"), mouseX - left, mouseY - top + 15); }
			if (mouseX >= (left + 74)  && mouseX <= (left + 90) && mouseY >= (top + 92) && mouseY <= (top + 108)) { this.drawHoveringText(I18n.format("roads.gui.fuelSlot"), mouseX - left, mouseY - top + 15); }
		}
	}
	
	private int getPercentage(int num, int max) {
		float x = (float) num / (float) max;
		int y = Math.round(x*100);
		if (y > 100) { return 100; }
		return y;
	}
}
