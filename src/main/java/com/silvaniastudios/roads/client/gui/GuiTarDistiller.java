package com.silvaniastudios.roads.client.gui;

import java.util.Arrays;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.tileentities.distiller.TarDistillerContainer;
import com.silvaniastudios.roads.blocks.tileentities.distiller.TarDistillerElectricEntity;
import com.silvaniastudios.roads.blocks.tileentities.distiller.TarDistillerEntity;
import com.silvaniastudios.roads.items.FRItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fluids.FluidTank;

public class GuiTarDistiller extends GuiContainer {
	
	private static final ResourceLocation guiTextures = new ResourceLocation(FurenikusRoads.MODID + ":textures/gui/tar_distiller.png");
	private TarDistillerEntity tileEntity;
	private boolean electric;
	
	public GuiTarDistiller(TarDistillerEntity tileEntity, TarDistillerContainer container, boolean electric) {
		super(container);
		xSize = 196;
		ySize = 208;
		this.tileEntity = tileEntity;
		this.electric = electric;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        int left = (width - xSize) / 2;
        int top = (height - ySize) / 2;
        
        drawTooltip(2, left, top, mouseX, mouseY);
        if (electric) {
			fontRenderer.drawString(I18n.format("roads.gui.electric_tar_distiller.name"), 32, 6, 4210752);
		} else {
			fontRenderer.drawString(I18n.format("roads.gui.tar_distiller.name"), 32, 6, 4210752);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(guiTextures);
        int left = (width - xSize) / 2;
        int top = (height - ySize) / 2;
        drawTexturedModalRect(left, top, 0, 0, xSize, ySize);
        drawTexturedModalRect(left+176, top+62, 176, 62, 20, 54);
        if (electric) {
        	drawTexturedModalRect(left+xSize-23, top, 196, 0, 23, 116);
        }
        drawFluid(left +   8, top + 8, tileEntity.fluidInput);
        drawFluid(left + 122, top + 8, tileEntity.fluidOutput1);
        drawFluid(left + 148, top + 8, tileEntity.fluidOutput2);
        
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
			TarDistillerElectricEntity tdee = (TarDistillerElectricEntity) tileEntity;
			int p = getPercentage(tdee.energy.getEnergyStored(), tdee.energy.getMaxEnergyStored());
			int x = Math.round(p / 2.5F);
			if (p == 100) { x = 42; }
			drawTexturedModalRect(left + 175, top + 7 + (42-x), 242, 42-x, 14, x);
		} else {
			int p = getPercentage(tileEntity.fuel_remaining, tileEntity.last_fuel_cap);
			int x = Math.round(p / 7.0F);
			drawTexturedModalRect(left + 175, top + 11 + (14-x), 228, 14-x, 14, x);
		}
	}
	
	private void drawProgress(int left, int top) {
		int p = getPercentage(tileEntity.timerCount, RoadsConfig.machine.tarDistillerTickRate);
		int x = Math.round(p * 1.6F);
		drawTexturedModalRect(left + 8, top + 116, 0, 252, x, 4);
	}
	
	private void drawTooltip(int col, int left, int top, int mouseX, int mouseY) {
		if (mouseX >= (left + 8)   && mouseX <= (left +  28) && mouseY >= (top + 8) && mouseY <= (top + 108)) { this.drawHoveringText(tileEntity.fluidInput.getFluidAmount()   + "/" + TarDistillerEntity.TANK_CAP, mouseX - left, mouseY - top + 15); }
		if (mouseX >= (left + 122) && mouseX <= (left + 142) && mouseY >= (top + 8) && mouseY <= (top + 108)) { this.drawHoveringText(tileEntity.fluidOutput1.getFluidAmount() + "/" + TarDistillerEntity.TANK_CAP, mouseX - left, mouseY - top + 15); }
		if (mouseX >= (left + 148) && mouseX <= (left + 168) && mouseY >= (top + 8) && mouseY <= (top + 108)) { this.drawHoveringText(tileEntity.fluidOutput2.getFluidAmount() + "/" + TarDistillerEntity.TANK_CAP, mouseX - left, mouseY - top + 15); }
		
		if (electric) {
			TarDistillerElectricEntity tdee = (TarDistillerElectricEntity) tileEntity;
			if (mouseX >= (left + 175) && mouseX <= (left + 189) && mouseY >= (top +  7) && mouseY <= (top + 49)) { this.drawHoveringText(tdee.energy.getEnergyStored() + "/" + tdee.energy.getMaxEnergyStored(), mouseX - left, mouseY - top + 15); }
		} else {
			if (mouseX >= (left + 175) && mouseX <= (left + 189) && mouseY >= (top + 11) && mouseY <= (top + 25)) { this.drawHoveringText(tileEntity.fuel_remaining + "/" + tileEntity.last_fuel_cap, mouseX - left, mouseY - top + 15); }
		}
		
		if (RoadsConfig.general.guiGuide) {
			String a = TextFormatting.RESET + " -> " + TextFormatting.GREEN;
			List<String> inputList = Arrays.asList(
					I18n.format("roads.gui.inputSlot"),
					"",
					TextFormatting.UNDERLINE + I18n.format("roads.gui.valid"),
					TextFormatting.AQUA + new ItemStack(Items.COAL, 1, 0).getDisplayName() + a + new ItemStack(FRItems.coal_coke, 1, 0).getDisplayName());
			
			if (mouseX >= (left +  34) && mouseX <= (left +  50) && mouseY >= (top + 30) && mouseY <= (top +  46) && tileEntity.inventory.getStackInSlot(TarDistillerContainer.INPUT).getCount() == 0) { this.drawHoveringText(inputList, mouseX - left, mouseY - top + 15); }
			if (mouseX >= (left +  34) && mouseX <= (left +  50) && mouseY >= (top + 70) && mouseY <= (top +  86) && tileEntity.inventory.getStackInSlot(TarDistillerContainer.FLUID_IN).getCount() == 0) { this.drawHoveringText(I18n.format("roads.gui.fluidInputSlot"), mouseX - left, mouseY - top + 15); }
			if (mouseX >= (left +  34) && mouseX <= (left +  50) && mouseY >= (top + 92) && mouseY <= (top + 108) && tileEntity.inventory.getStackInSlot(TarDistillerContainer.FLUID_IN_BUCKET).getCount() == 0) { this.drawHoveringText(I18n.format("roads.gui.bucketOutputSlot"), mouseX - left, mouseY - top + 15); }
			if (mouseX >= (left + 174) && mouseX <= (left + 190) && mouseY >= (top + 30) && mouseY <= (top +  46) && !electric && tileEntity.inventory.getStackInSlot(TarDistillerContainer.FUEL).getCount() == 0) { this.drawHoveringText(I18n.format("roads.gui.fuelSlot"), mouseX - left, mouseY - top + 15); }
			
			if (mouseX >= (left + 100) && mouseX <= (left + 116) && mouseY >= (top + 70) && mouseY <= (top +  86) && tileEntity.inventory.getStackInSlot(TarDistillerContainer.FLUID_OUT_1).getCount() == 0) { this.drawHoveringText(I18n.format("roads.gui.bucketInputSlot"), mouseX - left, mouseY - top + 15); }
			if (mouseX >= (left + 100) && mouseX <= (left + 116) && mouseY >= (top + 92) && mouseY <= (top + 108) && tileEntity.inventory.getStackInSlot(TarDistillerContainer.FLUID_OUT_1_BUCKET).getCount() == 0) { this.drawHoveringText(I18n.format("roads.gui.fluidOutputSlot"), mouseX - left, mouseY - top + 15); }
			
			if (mouseX >= (left + 174) && mouseX <= (left + 190) && mouseY >= (top + 70) && mouseY <= (top +  86) && tileEntity.inventory.getStackInSlot(TarDistillerContainer.FLUID_OUT_2).getCount() == 0) { this.drawHoveringText(I18n.format("roads.gui.bucketInputSlot"), mouseX - left, mouseY - top + 15); }
			if (mouseX >= (left + 174) && mouseX <= (left + 190) && mouseY >= (top + 92) && mouseY <= (top + 108) && tileEntity.inventory.getStackInSlot(TarDistillerContainer.FLUID_OUT_2_BUCKET).getCount() == 0) { this.drawHoveringText(I18n.format("roads.gui.fluidOutputSlot"), mouseX - left, mouseY - top + 15); }
			
			if (mouseX >= (left + 100) && mouseX <= (left + 116) && mouseY >= (top +  8) && mouseY <= (top +  24) && tileEntity.inventory.getStackInSlot(TarDistillerContainer.OUTPUT_1).getCount() == 0) { this.drawHoveringText(I18n.format("roads.gui.outputSlot"), mouseX - left, mouseY - top + 15); }
			if (mouseX >= (left + 100) && mouseX <= (left + 116) && mouseY >= (top + 30) && mouseY <= (top +  46) && tileEntity.inventory.getStackInSlot(TarDistillerContainer.OUTPUT_2).getCount() == 0) { this.drawHoveringText(I18n.format("roads.gui.outputSlot"), mouseX - left, mouseY - top + 15); }
		}
	}
	
	private void drawFluid(int left, int top, FluidTank fluid) {
		if (fluid.getFluidAmount() > 0) {
			int fillPercent = getPercentage(fluid.getFluidAmount(), TarDistillerEntity.TANK_CAP);
			mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			TextureAtlasSprite texture = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(fluid.getFluid().getFluid().getStill(fluid.getFluid()).toString());
	
			for (int i = 0; i < 6; i++) {
				drawFluidQuad(left,    top+(i*16), 16, 16, texture);
				drawFluidQuad(left+16, top+(i*16), 4, 16, texture);
			}
			
			drawFluidQuad(left,    top+96, 16, 4, texture);
			drawFluidQuad(left+16, top+96,  4, 4, texture);
			
			mc.getTextureManager().bindTexture(guiTextures);
			drawTexturedModalRect(left, top, 8, 8, 20, 100-fillPercent);
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
		if (y == 0 && num > 0) {
			return 1;
		}
		return y;
	}

}
