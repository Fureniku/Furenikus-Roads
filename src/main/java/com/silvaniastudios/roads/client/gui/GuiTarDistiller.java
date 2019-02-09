package com.silvaniastudios.roads.client.gui;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.tileentities.distiller.TarDistillerContainer;
import com.silvaniastudios.roads.blocks.tileentities.distiller.TarDistillerEntity;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiTarDistiller extends GuiContainer {
	
	private static final ResourceLocation guiTextures = new ResourceLocation(FurenikusRoads.MODID + ":textures/gui/tar_distiller.png");
	private TarDistillerEntity tileEntity;
	
	public GuiTarDistiller(TarDistillerEntity tileEntity, TarDistillerContainer container) {
		super(container);
		this.tileEntity = tileEntity;
		xSize = 176;
		ySize = 208;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        int left = (width - xSize) / 2;
        int top = (height - ySize) / 2;
        
        drawTooltip(2, left, top, mouseX, mouseY);
        fontRenderer.drawString("Tar Distiller", 32, 6, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(guiTextures);
        int left = (width - xSize) / 2;
        int top = (height - ySize) / 2;
        drawTexturedModalRect(left, top, 0, 0, xSize, ySize);
        drawTexturedModalRect(left+176, top+62, 176, 62, 20, 54);
        drawTankFillBar(left +   8, top, tileEntity.fluidInput.getFluidAmount(),   0);
        drawTankFillBar(left + 122, top, tileEntity.fluidOutput1.getFluidAmount(), 1);
        drawTankFillBar(left + 148, top, tileEntity.fluidOutput2.getFluidAmount(), 2);
        
        drawFuel(left, top);
        drawProgress(left, top);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
	
	private void drawTankFillBar(int left, int top, int fill, int id) {
		int p = getPercentage(fill, TarDistillerEntity.TANK_CAP);
		drawTexturedModalRect(left, top + 108 - p, 196 + (id*20), 100 - p, 20, p);
	}
	
	private void drawFuel(int left, int top) {
		int p = getPercentage(tileEntity.fuel_remaining, tileEntity.last_fuel_cap);
		int x = Math.round(p / 7.0F);
		drawTexturedModalRect(left + 68, top + 75 + (14-x), 176, 14-x, 14, x);
	}
	
	private void drawProgress(int left, int top) {
		int p = getPercentage(tileEntity.timerCount, RoadsConfig.general.tarDistillerTickRate);
		int x = Math.round(p * 1.6F);
		drawTexturedModalRect(left + 8, top + 116, 0, 252, x, 4);
	}
	
	private void drawTooltip(int col, int left, int top, int mouseX, int mouseY) {
		if (mouseX >= (left + 8)   && mouseX <= (left +  28) && mouseY >= (top + 8) && mouseY <= (top + 108)) { this.drawHoveringText(tileEntity.fluidInput.getFluidAmount()   + "/" + TarDistillerEntity.TANK_CAP, mouseX - left, mouseY - top + 15); }
		if (mouseX >= (left + 122) && mouseX <= (left + 142) && mouseY >= (top + 8) && mouseY <= (top + 108)) { this.drawHoveringText(tileEntity.fluidOutput1.getFluidAmount() + "/" + TarDistillerEntity.TANK_CAP, mouseX - left, mouseY - top + 15); }
		if (mouseX >= (left + 148) && mouseX <= (left + 168) && mouseY >= (top + 8) && mouseY <= (top + 108)) { this.drawHoveringText(tileEntity.fluidOutput2.getFluidAmount() + "/" + TarDistillerEntity.TANK_CAP, mouseX - left, mouseY - top + 15); }
		
		if (mouseX >= (left + 68) && mouseX <= (left + 82) && mouseY >= (top + 75) && mouseY <= (top + 89)) { this.drawHoveringText(tileEntity.fuel_remaining + "/" + tileEntity.last_fuel_cap, mouseX - left, mouseY - top + 15); }
	}
	
	private int getPercentage(int num, int max) {
		float x = (float) num / (float) max;
		int y = Math.round(x*100);
		if (y > 100) { return 100; }
		return y;
	}

}
