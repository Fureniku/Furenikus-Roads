package com.silvaniastudios.roads.client.gui;

import org.lwjgl.opengl.GL11;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.tileentities.paintfiller.PaintFillerContainer;
import com.silvaniastudios.roads.blocks.tileentities.paintfiller.PaintFillerElectricEntity;
import com.silvaniastudios.roads.blocks.tileentities.paintfiller.PaintFillerEntity;
import com.silvaniastudios.roads.fluids.FRFluids;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

public class GuiPaintFiller extends GuiContainer {
	
	private static final ResourceLocation guiTextures = new ResourceLocation(FurenikusRoads.MODID + ":textures/gui/paint_filler.png");
	private PaintFillerEntity tileEntity;
	private boolean electric;
	
	public GuiPaintFiller(PaintFillerEntity tileEntity, PaintFillerContainer container, boolean electric) {
		super(container);
		xSize = 196;
		ySize = 206;
		this.tileEntity = tileEntity;
		this.electric = electric;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		int left = (width - xSize) / 2;
		int top = (height - ySize) / 2;
        
        drawTooltip(2, left, top, mouseX, mouseY);
        if (electric) {
			fontRenderer.drawString(I18n.format("roads.gui.electric_paint_filler.name"), 6, 6, 4210752);
		} else {
			fontRenderer.drawString(I18n.format("roads.gui.paint_filler.name"), 6, 6, 4210752);
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
        	drawTexturedModalRect(left+xSize-23, top, 196, 0, 23, 56);
        }
        
        FluidTank gun_white = new FluidTank(PaintFillerEntity.GUN_TANK_CAP);
        FluidTank gun_yellow = new FluidTank(PaintFillerEntity.GUN_TANK_CAP);
        FluidTank gun_red = new FluidTank(PaintFillerEntity.GUN_TANK_CAP);
        
        gun_white.setFluid(new FluidStack(FRFluids.white_paint, tileEntity.gun_white));
        gun_yellow.setFluid(new FluidStack(FRFluids.yellow_paint, tileEntity.gun_yellow));
        gun_red.setFluid(new FluidStack(FRFluids.red_paint, tileEntity.gun_red));
        
        if (tileEntity.inventory.getStackInSlot(PaintFillerContainer.GUN) != ItemStack.EMPTY) {
        	drawFluidSmall(left +  8, top + 60, gun_white);
        	drawFluidSmall(left + 24, top + 60, gun_yellow);
        	drawFluidSmall(left + 40, top + 60, gun_red);
        }
        drawFluid(left +  96, top + 8, tileEntity.white_paint);
        drawFluid(left + 122, top + 8, tileEntity.yellow_paint);
        drawFluid(left + 148, top + 8, tileEntity.red_paint);
        
        drawFuel(left, top);
        drawProgress(left, top);
        
        
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
	
	private void drawFuel(int left, int top) {
		if (electric) {
			PaintFillerElectricEntity pfee = (PaintFillerElectricEntity) tileEntity;
			int p = getPercentage(pfee.energy.getEnergyStored(), pfee.energy.getMaxEnergyStored());
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
		drawTexturedModalRect(left + 8, top + 114, 0, 252, x, 4);
	}
	
	private void drawTooltip(int col, int left, int top, int mouseX, int mouseY) {
		if (mouseX >= (left + 96)  && mouseX <= (left + 116) && mouseY >= (top + 8) && mouseY <= (top + 108)) { this.drawHoveringText(tileEntity.white_paint.getFluidAmount()  + "/" + PaintFillerEntity.FILLER_TANK_CAP, mouseX - left, mouseY - top + 15); }
		if (mouseX >= (left + 122) && mouseX <= (left + 142) && mouseY >= (top + 8) && mouseY <= (top + 108)) { this.drawHoveringText(tileEntity.yellow_paint.getFluidAmount() + "/" + PaintFillerEntity.FILLER_TANK_CAP, mouseX - left, mouseY - top + 15); }
		if (mouseX >= (left + 148) && mouseX <= (left + 168) && mouseY >= (top + 8) && mouseY <= (top + 108)) { this.drawHoveringText(tileEntity.red_paint.getFluidAmount()    + "/" + PaintFillerEntity.FILLER_TANK_CAP, mouseX - left, mouseY - top + 15); }
		
		if (mouseX >= (left + 8)  && mouseX <= (left + 18) && mouseY >= (top + 58) && mouseY <= (top + 108)) { this.drawHoveringText(tileEntity.gun_white  + "/" + PaintFillerEntity.GUN_TANK_CAP, mouseX - left, mouseY - top + 15); }
		if (mouseX >= (left + 24) && mouseX <= (left + 34) && mouseY >= (top + 58) && mouseY <= (top + 108)) { this.drawHoveringText(tileEntity.gun_yellow + "/" + PaintFillerEntity.GUN_TANK_CAP, mouseX - left, mouseY - top + 15); }
		if (mouseX >= (left + 40) && mouseX <= (left + 50) && mouseY >= (top + 58) && mouseY <= (top + 108)) { this.drawHoveringText(tileEntity.gun_red    + "/" + PaintFillerEntity.GUN_TANK_CAP, mouseX - left, mouseY - top + 15); }
		
		if (electric) {
			PaintFillerElectricEntity pfee = (PaintFillerElectricEntity) tileEntity;
			if (mouseX >= (left + 175) && mouseX <= (left + 189) && mouseY >= (top +  7) && mouseY <= (top + 49)) { this.drawHoveringText(pfee.energy.getEnergyStored() + "/" + pfee.energy.getMaxEnergyStored(), mouseX - left, mouseY - top + 15); }
		} else {
			if (mouseX >= (left + 175) && mouseX <= (left + 189) && mouseY >= (top + 13) && mouseY <= (top + 27)) { this.drawHoveringText(tileEntity.fuel_remaining + "/" + tileEntity.last_fuel_cap, mouseX - left, mouseY - top + 15); }
		}
		
		if (RoadsConfig.general.guiGuide) {
			if (mouseX >= (left + 21)  && mouseX <= (left + 37) && mouseY >= (top + 36) && mouseY <= (top + 52) && !tileEntity.has_gun) { this.drawHoveringText(I18n.format("roads.gui.paint_filler.paint_gun_slot"), mouseX - left, mouseY - top + 15); }
			
			if (mouseX >= (left + 74)  && mouseX <= (left +  90) && mouseY >= (top + 36) && mouseY <= (top +  52) && tileEntity.inventory.getStackInSlot(PaintFillerContainer.WHITE_DYE).getCount()  == 0) { this.drawHoveringText(I18n.format("roads.gui.paint_filler.white_slot"), mouseX - left, mouseY - top + 15); }
			if (mouseX >= (left + 74)  && mouseX <= (left +  90) && mouseY >= (top + 64) && mouseY <= (top +  80) && tileEntity.inventory.getStackInSlot(PaintFillerContainer.YELLOW_DYE).getCount() == 0) { this.drawHoveringText(I18n.format("roads.gui.paint_filler.yellow_slot"), mouseX - left, mouseY - top + 15); }
			if (mouseX >= (left + 74)  && mouseX <= (left +  90) && mouseY >= (top + 92) && mouseY <= (top + 108) && tileEntity.inventory.getStackInSlot(PaintFillerContainer.RED_DYE).getCount()    == 0) { this.drawHoveringText(I18n.format("roads.gui.paint_filler.red_slot"), mouseX - left, mouseY - top + 15); }
			if (mouseX >= (left + 174) && mouseX <= (left + 190) && mouseY >= (top + 32) && mouseY <= (top +  48) && !electric  && tileEntity.inventory.getStackInSlot(PaintFillerContainer.FUEL).getCount() == 0) { this.drawHoveringText(I18n.format("roads.gui.fuelSlot"), mouseX - left, mouseY - top + 15); }
		}
	}
	
	private void drawFluidSmall(int left, int top, FluidTank fluid) {
		if (fluid.getFluidAmount() > 0) {
			int fillPercent = getPercentage(fluid.getFluidAmount(), PaintFillerEntity.GUN_TANK_CAP) / 2;
			if (fillPercent > 48) { fillPercent = 48; }
			mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			TextureAtlasSprite texture = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(fluid.getFluid().getFluid().getStill(fluid.getFluid()).toString());
	
			for (int i = 0; i < 3; i++) {
				drawFluidQuad(left, top+(i*16), 10, 16, texture);
			}
			
			mc.getTextureManager().bindTexture(guiTextures);
			drawTexturedModalRect(left, top, 8, 60, 10, 48-fillPercent);
		}
		drawTexturedModalRect(left, top, 246, 108, 10, 48);
	}
	
	private void drawFluid(int left, int top, FluidTank fluid) {
		if (fluid.getFluidAmount() > 0) {
			int fillPercent = getPercentage(fluid.getFluidAmount(), PaintFillerEntity.FILLER_TANK_CAP);
			mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			TextureAtlasSprite texture = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(fluid.getFluid().getFluid().getStill(fluid.getFluid()).toString());
	
			for (int i = 0; i < 6; i++) {
				drawFluidQuad(left,    top+(i*16), 16, 16, texture);
				drawFluidQuad(left+16, top+(i*16), 4, 16, texture);
			}
			
			drawFluidQuad(left,    top+96, 16, 4, texture);
			drawFluidQuad(left+16, top+96,  4, 4, texture);
			
			mc.getTextureManager().bindTexture(guiTextures);
			drawTexturedModalRect(left, top, 96, 8, 20, 100-fillPercent);
		}
		drawTexturedModalRect(left, top, 236, 156, 20, 100);
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
	
	private int getPercentage(int num, int max) {
		float x = (float) num / (float) max;
		int y = Math.round(x*100);
		if (y > 100) { return 100; }
		return y;
	}
}
