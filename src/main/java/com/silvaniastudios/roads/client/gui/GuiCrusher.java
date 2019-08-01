package com.silvaniastudios.roads.client.gui;

import java.util.Arrays;
import java.util.List;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.tileentities.crusher.CrusherContainer;
import com.silvaniastudios.roads.blocks.tileentities.crusher.CrusherEntity;
import com.silvaniastudios.roads.items.FRItems;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class GuiCrusher extends GuiContainer {
	
	private static final ResourceLocation guiTextures = new ResourceLocation(FurenikusRoads.MODID + ":textures/gui/crusher.png");
	private CrusherEntity tileEntity;
	
	public GuiCrusher(CrusherEntity tileEntity, CrusherContainer container) {
		super(container);
		xSize = 176;
		ySize = 134;
		this.tileEntity = tileEntity;
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
        fontRenderer.drawString(I18n.format("roads.gui.crusher.name"), 6, 6, 4210752);
	}
	
	private void drawTooltip(int col, int left, int top, int mouseX, int mouseY) {
		if (mouseX >= (left + 153) && mouseX <= (left + 167) && mouseY >= (top + 3) && mouseY <= (top + 17)) { this.drawHoveringText(tileEntity.fuel_remaining + "/" + tileEntity.last_fuel_cap, mouseX - left, mouseY - top + 15); }
		
		if (RoadsConfig.general.guiGuide) {
			String a = TextFormatting.RESET + " -> " + TextFormatting.GREEN;
			List<String> list = Arrays.asList(
					I18n.format("roads.gui.inputSlot"),
					"",
					TextFormatting.UNDERLINE + I18n.format("roads.gui.valid"),
					TextFormatting.AQUA + Blocks.STONE.getLocalizedName() + a + Blocks.COBBLESTONE.getLocalizedName(),
					TextFormatting.AQUA + Blocks.COBBLESTONE.getLocalizedName() + a + Blocks.GRAVEL.getLocalizedName(),
					TextFormatting.AQUA + Blocks.GRAVEL.getLocalizedName() + a + new ItemStack(FRBlocks.generic_blocks, 1, 0).getDisplayName(),
					TextFormatting.AQUA + new ItemStack(FRBlocks.generic_blocks, 1, 0).getDisplayName() + a + Blocks.SAND.getLocalizedName(),
					TextFormatting.AQUA + new ItemStack(FRBlocks.generic_blocks, 1, 1).getDisplayName() + a + new ItemStack(FRItems.clinker_mix).getDisplayName(),
					TextFormatting.AQUA + new ItemStack(FRBlocks.generic_blocks, 1, 2).getDisplayName() + a + new ItemStack(FRItems.cement_dust).getDisplayName(),
					TextFormatting.AQUA + new ItemStack(FRBlocks.generic_blocks, 1, 3).getDisplayName() + a + new ItemStack(FRItems.limestone_dust).getDisplayName(),
					TextFormatting.AQUA + I18n.format("roads.gui.road_generic_name") + a + I18n.format("roads.gui.fragment_generic_name"));
			if (mouseX >= (left +   8) && mouseX <= (left +  24) && mouseY >= (top + 20) && mouseY <= (top + 36)) { this.drawHoveringText(list, mouseX - left, mouseY - top + 15); }
			if (mouseX >= (left +  98) && mouseX <= (left + 114) && mouseY >= (top + 20) && mouseY <= (top + 36)) { this.drawHoveringText(I18n.format("roads.gui.outputSlot"), mouseX - left, mouseY - top + 15); }
			if (mouseX >= (left + 152) && mouseX <= (left + 168) && mouseY >= (top + 20) && mouseY <= (top + 36)) { this.drawHoveringText(I18n.format("roads.gui.fuelSlot"), mouseX - left, mouseY - top + 15); }
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(guiTextures);
        int left = (width - xSize) / 2;
        int top = (height - ySize) / 2;
        
        drawTexturedModalRect(left, top, 0, 0, xSize, ySize);
        
        drawFuel(left, top);
        drawProgress(left, top);
	}
	
	private void drawFuel(int left, int top) {
		int p = getPercentage(tileEntity.fuel_remaining, tileEntity.last_fuel_cap);
		int x = Math.round(p / 7.0F);
		drawTexturedModalRect(left + 153, top + 3 + (14-x), 176, 14-x, 14, x);
	}
	
	private void drawProgress(int left, int top) {
		int p = getPercentage(tileEntity.timerCount, RoadsConfig.general.crusherTickRate);
		int x = Math.round(p * 1.6F);
		drawTexturedModalRect(left + 8, top + 42, 0, 252, x, 4);
	}
	
	private int getPercentage(int num, int max) {
		float x = (float) num / (float) max;
		int y = Math.round(x*100);
		if (y > 100) { return 100; }
		return y;
	}
}
