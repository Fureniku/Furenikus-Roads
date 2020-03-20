package com.silvaniastudios.roads.client.gui;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.tileentities.compactor.CompactorContainer;
import com.silvaniastudios.roads.blocks.tileentities.compactor.CompactorElectricEntity;
import com.silvaniastudios.roads.blocks.tileentities.compactor.CompactorEntity;
import com.silvaniastudios.roads.network.CompactorUpdatePacket;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GuiCompactor extends GuiContainer {
	
	private static final ResourceLocation guiTextures = new ResourceLocation(FurenikusRoads.MODID + ":textures/gui/compactor.png");
	private CompactorEntity tileEntity;
	private boolean electric;
	
	GuiButton increase;
	GuiButton decrease;
	
	public GuiCompactor(CompactorEntity tileEntity, CompactorContainer container, boolean electric) {
		super(container);
		xSize = 176;
		ySize = 146;
		this.tileEntity = tileEntity;
		this.electric = electric;
	}
	
	public void initGui() {
		super.initGui();
		int guiLeft = (width - xSize) / 2;
	    int guiTop = (height - ySize) / 2;
	    
		increase = new GuiButton(0, guiLeft + 91, guiTop + 30, 20, 20, "+");
		decrease = new GuiButton(1, guiLeft + 43, guiTop + 30, 20, 20, "-");

		buttonList.add(increase);
		buttonList.add(decrease);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		int left = (width - xSize) / 2;
		int top = (height - ySize) / 2;
		
		drawTooltip(2, left, top, mouseX, mouseY);
		if (electric) {
			fontRenderer.drawString(I18n.format("roads.gui.compactor_electric.name"), 6, 6, 4210752);
		} else {
			fontRenderer.drawString(I18n.format("roads.gui.compactor.name"), 6, 6, 4210752);
		}
		
		String str = "" + (tileEntity.road_size+1);
		
		fontRenderer.drawString(str, 77-(fontRenderer.getStringWidth(str)/2), 36, 4210752);
	}
	
	private void drawTooltip(int col, int left, int top, int mouseX, int mouseY) {
		if (electric) {
			CompactorElectricEntity cee = (CompactorElectricEntity) tileEntity;
			if (mouseX >= (left + 155) && mouseX <= (left + 169) && mouseY >= (top + 7) && mouseY <= (top + 49)) { this.drawHoveringText(cee.energy.getEnergyStored() + "/" + cee.energy.getMaxEnergyStored(), mouseX - left, mouseY - top + 15); }
		} else {
			if (mouseX >= (left + 153) && mouseX <= (left + 167) && mouseY >= (top + 15) && mouseY <= (top + 29)) { this.drawHoveringText(tileEntity.fuel_remaining + "/" + tileEntity.last_fuel_cap, mouseX - left, mouseY - top + 15); }
		}
		
		if (RoadsConfig.general.guiGuide) {
			if (mouseX >= (left +   8) && mouseX <= (left +  24) && mouseY >= (top + 32) && mouseY <= (top + 48) && tileEntity.inventory.getStackInSlot(CompactorContainer.FRAGMENTS).getCount()  == 0) { this.drawHoveringText(I18n.format("roads.gui.compactor.fragmentSlot"), mouseX - left, mouseY - top + 15); }
			if (mouseX >= (left + 130) && mouseX <= (left + 146) && mouseY >= (top + 32) && mouseY <= (top + 48) && tileEntity.inventory.getStackInSlot(CompactorContainer.ROADS).getCount() == 0) { this.drawHoveringText(I18n.format("roads.gui.outputSlot"), mouseX - left, mouseY - top + 15); }
			if (mouseX >= (left + 152) && mouseX <= (left + 168) && mouseY >= (top + 32) && mouseY <= (top + 48) && tileEntity.inventory.getStackInSlot(CompactorContainer.FUEL).getCount() == 0 && !electric) { this.drawHoveringText(I18n.format("roads.gui.fuelSlot"), mouseX - left, mouseY - top + 15); }
			if (mouseX >= (left + 63) && mouseX <= (left + 90) && mouseY >= (top + 30) && mouseY <= (top + 59)) { this.drawHoveringText(I18n.format("roads.gui.compactor.size"), mouseX - left, mouseY - top + 15); }
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(guiTextures);
        int left = (width - xSize) / 2;
        int top = (height - ySize) / 2;
        
        drawTexturedModalRect(left, top, 0, 0, xSize, ySize);
        if (electric) {
        	drawTexturedModalRect(left+xSize-25, top, 176, 0, 25, ySize);
        }
        
        drawFuel(left, top);
        drawProgress(left, top);
	}
	
	private void drawFuel(int left, int top) {
		if (electric) {
			CompactorElectricEntity cee = (CompactorElectricEntity) tileEntity;
			int p = getPercentage(cee.energy.getEnergyStored(), cee.energy.getMaxEnergyStored());
			int x = Math.round(p / 2.5F);
			if (p == 100) { x = 42; }
			drawTexturedModalRect(left + 155, top + 7 + (42-x), 242, 42-x, 14, x);
		} else {
			int p = getPercentage(tileEntity.fuel_remaining, tileEntity.last_fuel_cap);
			int x = Math.round(p / 7.0F);
			drawTexturedModalRect(left + 153, top + 15 + (14-x), 229, 14-x, 13, x);
		}
	}
	
	private void drawProgress(int left, int top) {
		int p = getPercentage(tileEntity.timerCount, electric ? RoadsConfig.machine.electricCompactorTickRate : RoadsConfig.machine.compactorTickRate);
		int x = Math.round(p * 1.6F);
		drawTexturedModalRect(left + 8, top + 54, 0, 252, x, 4);
	}
	
	private int getPercentage(int num, int max) {
		float x = (float) num / (float) max;
		int y = Math.round(x*100);
		if (y > 100) { return 100; }
		return y;
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		if (button == increase) {
			FurenikusRoads.PACKET_CHANNEL.sendToServer(new CompactorUpdatePacket(1));
		}
		if (button == decrease) {
			FurenikusRoads.PACKET_CHANNEL.sendToServer(new CompactorUpdatePacket(-1));
		}
	}
}
