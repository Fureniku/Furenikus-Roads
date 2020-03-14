package com.silvaniastudios.roads.client.gui;

import org.lwjgl.opengl.GL11;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.tileentities.paintoven.PaintOvenContainer;
import com.silvaniastudios.roads.blocks.tileentities.paintoven.PaintOvenElectricEntity;
import com.silvaniastudios.roads.blocks.tileentities.paintoven.PaintOvenEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidTank;

public class GuiPaintOven extends GuiContainer {
	
	private static final ResourceLocation guiTextures = new ResourceLocation(FurenikusRoads.MODID + ":textures/gui/paint_oven.png");
	private PaintOvenEntity tileEntity;
	private boolean electric;
	
	public GuiPaintOven(PaintOvenEntity tileEntity, PaintOvenContainer container, boolean electric) {
		super(container);
		xSize = 196;
		ySize = 156;
		this.tileEntity = tileEntity;
		this.electric = electric;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		int left = (width - xSize) / 2;
		int top = (height - ySize) / 2;
        
        drawTooltip(2, left, top, mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(guiTextures);
        int left = (width - xSize) / 2;
        int top = (height - ySize) / 2;
        drawTexturedModalRect(left, top, 0, 0, xSize, ySize);
        if (electric) {
        	drawTexturedModalRect(left+xSize-23, top, 196, 0, 23, 56);
        }
        
        drawFluid(left +   8, top + 8, tileEntity.water);
        drawFluid(left + 108, top + 8, tileEntity.paint);
        
        drawFuel(left, top);
        drawProgress(left, top);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
	
	private void drawFluid(int left, int top, FluidTank fluid) {
		if (fluid.getFluidAmount() > 0) {
			int fillPercent = getPercentage(fluid.getFluidAmount(), PaintOvenEntity.FILLER_TANK_CAP)/2;
			mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			TextureAtlasSprite waterTexture = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(fluid.getFluid().getFluid().getStill(fluid.getFluid()).toString());
	
			for (int i = 0; i < 3; i++) {
				drawFluidQuad(left,    top+(i*16), 16, 16, waterTexture);
				drawFluidQuad(left+16, top+(i*16), 16, 16, waterTexture);
				drawFluidQuad(left+32, top+(i*16), 16, 16, waterTexture);
				drawFluidQuad(left+48, top+(i*16), 12, 16, waterTexture);
			}
			
			drawFluidQuad(left,    top+48, 16, 2, waterTexture);
			drawFluidQuad(left+16, top+48, 16, 2, waterTexture);
			drawFluidQuad(left+32, top+48, 16, 2, waterTexture);
			drawFluidQuad(left+48, top+48, 12, 2, waterTexture);
			
			mc.getTextureManager().bindTexture(guiTextures);
			drawTexturedModalRect(left, top, 8, 8, 60, 50-fillPercent);
		}
		drawTexturedModalRect(left, top, 196, 206, 60, 50);
	}
	
	private void drawFluidQuad(int x, int y, int width, int height, TextureAtlasSprite texture) {
		float minU = texture.getInterpolatedU(0);
		float minV = texture.getInterpolatedV(0);
		float maxU = texture.getInterpolatedU(width);
		float maxV = texture.getInterpolatedV(height);
		
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder worldrenderer = tessellator.getBuffer();
		worldrenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		worldrenderer.pos((double)x, (double)(y + height), (double)this.zLevel).tex(minU, maxV).endVertex();
		worldrenderer.pos((double)(x + width), (double)(y + height), (double)this.zLevel).tex(maxU, maxV).endVertex();
		worldrenderer.pos((double)(x + width), (double)y, (double)this.zLevel).tex(maxU, minV).endVertex();
		worldrenderer.pos((double)x, (double)y, (double)this.zLevel).tex(minU, minV).endVertex();
		tessellator.draw();
	}
	
	private void drawFuel(int left, int top) {
		if (electric) {
			PaintOvenElectricEntity poee = (PaintOvenElectricEntity) tileEntity;
			int p = getPercentage(poee.energy.getEnergyStored(), poee.energy.getMaxEnergyStored());
			int x = Math.round(p / 2.5F);
			if (p == 100) { x = 42; }
			drawTexturedModalRect(left + 175, top + 7 + (42-x), 242, 42-x, 14, x);
		} else {
			int p = getPercentage(tileEntity.fuel_remaining, tileEntity.last_fuel_cap);
			int x = Math.round(p / 7.0F);
			drawTexturedModalRect(left + 175, top + 13 + (14-x), 228, 14-x, 14, x);
		}
	}
	
	private void drawProgress(int left, int top) {
		int p = getPercentage(tileEntity.timerCount, electric ? RoadsConfig.machine.electricFillerTickRate : RoadsConfig.machine.fillerTickRate);
		int x = Math.round(p * 1.6F);
		drawTexturedModalRect(left + 8, top + 64, 0, 252, x, 4);
	}
	
	private void drawTooltip(int col, int left, int top, int mouseX, int mouseY) {
		if (mouseX >= (left +   8) && mouseX <= (left +  68) && mouseY >= (top + 8) && mouseY <= (top + 58)) { this.drawHoveringText(tileEntity.water.getFluidAmount() + "/" + PaintOvenEntity.FILLER_TANK_CAP, mouseX - left, mouseY - top + 15); }
		if (mouseX >= (left + 108) && mouseX <= (left + 168) && mouseY >= (top + 8) && mouseY <= (top + 58)) { this.drawHoveringText(tileEntity.paint.getFluidAmount() + "/" + PaintOvenEntity.FILLER_TANK_CAP, mouseX - left, mouseY - top + 15); }

		if (electric) {
			PaintOvenElectricEntity pfee = (PaintOvenElectricEntity) tileEntity;
			if (mouseX >= (left + 175) && mouseX <= (left + 189) && mouseY >= (top +  7) && mouseY <= (top + 49)) { this.drawHoveringText(pfee.energy.getEnergyStored() + "/" + pfee.energy.getMaxEnergyStored(), mouseX - left, mouseY - top + 15); }
		} else {
			if (mouseX >= (left + 175) && mouseX <= (left + 189) && mouseY >= (top + 13) && mouseY <= (top + 27)) { this.drawHoveringText(tileEntity.fuel_remaining + "/" + tileEntity.last_fuel_cap, mouseX - left, mouseY - top + 15); }
		}
		
		if (RoadsConfig.general.guiGuide) {
			if (mouseX >= (left + 80)  && mouseX <= (left + 96) && mouseY >= (top + 25) && mouseY <= (top + 41) && tileEntity.inventory.getStackInSlot(PaintOvenContainer.DYE).getCount() == 0) { this.drawHoveringText(I18n.format("roads.gui.paint_oven.dye_slot"), mouseX - left, mouseY - top + 15); }
			if (mouseX >= (left + 174) && mouseX <= (left + 190) && mouseY >= (top + 32) && mouseY <= (top +  48) && !electric && tileEntity.inventory.getStackInSlot(PaintOvenContainer.FUEL).getCount() == 0) { this.drawHoveringText(I18n.format("roads.gui.fuelSlot"), mouseX - left, mouseY - top + 15); }
		}
	}
	
	private int getPercentage(int num, int max) {
		float x = (float) num / (float) max;
		int y = Math.round(x*100);
		if (y > 100) { return 100; }
		return y;
	}
}
