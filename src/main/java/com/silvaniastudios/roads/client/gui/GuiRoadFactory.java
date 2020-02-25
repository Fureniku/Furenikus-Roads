package com.silvaniastudios.roads.client.gui;

import java.util.Arrays;
import java.util.List;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.tileentities.crusher.CrusherElectricEntity;
import com.silvaniastudios.roads.blocks.tileentities.roadfactory.RoadFactoryBlock;
import com.silvaniastudios.roads.blocks.tileentities.roadfactory.RoadFactoryContainer;
import com.silvaniastudios.roads.blocks.tileentities.roadfactory.RoadFactoryElectricEntity;
import com.silvaniastudios.roads.blocks.tileentities.roadfactory.RoadFactoryEntity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class GuiRoadFactory extends GuiContainer {
	
	private static final ResourceLocation guiTextures = new ResourceLocation(FurenikusRoads.MODID + ":textures/gui/road_factory.png");
	private RoadFactoryEntity tileEntity;
	private boolean electric;
	
	public GuiRoadFactory(RoadFactoryEntity tileEntity, RoadFactoryContainer container, boolean electric) {
		super(container);
		xSize = 176;
		ySize = 206;
		this.tileEntity = tileEntity;
		this.electric = electric;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        int left = (width - xSize) / 2;
        int top = (height - ySize) / 2;
        
        fontRenderer.drawString(I18n.format("roads.gui.road_factory.link"), 116, 96, 4210752);
        drawTooltip(2, left, top, mouseX, mouseY);
        if (electric) {
			fontRenderer.drawString(I18n.format("roads.gui.electric_road_factory.name"), 32, 6, 4210752);
		} else {
			fontRenderer.drawString(I18n.format("roads.gui.road_factory.name"), 32, 6, 4210752);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(guiTextures);
        int left = (width - xSize) / 2;
        int top = (height - ySize) / 2;
        drawTexturedModalRect(left, top, 0, 0, xSize, ySize);
        drawTexturedModalRect(left, top, 0, 0, xSize, ySize);
        if (electric) {
        	drawTexturedModalRect(left+xSize-25, top, 176, 0, 25, 206);
        }
        
        drawTankFillBar(left, top, tileEntity.tarFluid.getFluidAmount());
        drawFuel(left, top);
        drawProgress(left, top);
        
        if (distillerFound()) {
        	drawTexturedModalRect(left + 157, top + 95, 232, 14, 10, 10);
        }
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
	
	private void drawTankFillBar(int left, int top, int fill) {
		int p = getPercentage(fill, RoadFactoryEntity.TANK_CAP);
		drawTexturedModalRect(left + 8, top + 108 - p, 236, 100 - p, 20, p);
	}
	
	private void drawFuel(int left, int top) {
		if (electric) {
			RoadFactoryElectricEntity rfee = (RoadFactoryElectricEntity) tileEntity;
			int p = getPercentage(rfee.energy.getEnergyStored(), rfee.energy.getMaxEnergyStored());
			int x = Math.round(p / 2.5F);
			if (p == 100) { x = 42; }
			drawTexturedModalRect(left + 153, top + 18 + (42-x), 242, 42-x, 14, x);
		} else {
			int p = getPercentage(tileEntity.fuel_remaining, tileEntity.last_fuel_cap);
			int x = Math.round(p / 7.0F);
			drawTexturedModalRect(left + 153, top + 23 + (14-x), 228, 14-x, 14, x);
		}
	}
	
	private void drawProgress(int left, int top) {
		int p = getPercentage(tileEntity.timerCount, RoadsConfig.machine.roadFactoryTickRate);
		int x = Math.round(p * 1.6F);
		drawTexturedModalRect(left + 8, top + 114, 0, 252, x, 4);
	}
	
	private void drawTooltip(int col, int left, int top, int mouseX, int mouseY) {
		if (mouseX >= (left + 8)  && mouseX <= (left + 28) && mouseY >= (top + 8) && mouseY <= (top + 108)) { this.drawHoveringText(tileEntity.tarFluid.getFluidAmount()  + "/" + RoadFactoryEntity.TANK_CAP, mouseX - left, mouseY - top + 15); }
		
		if (electric) {
			RoadFactoryElectricEntity rfee = (RoadFactoryElectricEntity) tileEntity;
			if (mouseX >= (left + 153) && mouseX <= (left + 167) && mouseY >= (top + 18) && mouseY <= (top + 60)) { this.drawHoveringText(rfee.energy.getEnergyStored() + "/" + rfee.energy.getMaxEnergyStored(), mouseX - left, mouseY - top + 15); }
		} else {
			if (mouseX >= (left + 153) && mouseX <= (left + 167) && mouseY >= (top + 23) && mouseY <= (top + 37)) { this.drawHoveringText(tileEntity.fuel_remaining + "/" + tileEntity.last_fuel_cap, mouseX - left, mouseY - top + 15); }
		}
		
		if (RoadsConfig.general.guiGuide) {
			String a = TextFormatting.RESET + " -> " + TextFormatting.GREEN;
			List<String> inputList = Arrays.asList(
					I18n.format("roads.gui.inputSlot"),
					"",
					TextFormatting.UNDERLINE + I18n.format("roads.gui.valid"),
					TextFormatting.AQUA + new ItemStack(FRBlocks.generic_blocks, 1, 0).getDisplayName() + a + FRBlocks.road_block_standard.getLocalizedName(),
					TextFormatting.AQUA + new ItemStack(Blocks.STONE, 1, 0).getDisplayName() + a + FRBlocks.road_block_stone.getLocalizedName(),
					TextFormatting.AQUA + new ItemStack(Blocks.STONE, 1, 1).getDisplayName() + a + FRBlocks.road_block_pale.getLocalizedName(),
					TextFormatting.AQUA + new ItemStack(Blocks.STONE, 1, 3).getDisplayName() + a + FRBlocks.road_block_light.getLocalizedName(),
					TextFormatting.AQUA + new ItemStack(Blocks.STONE, 1, 5).getDisplayName() + a + FRBlocks.road_block_dark.getLocalizedName(),
					TextFormatting.AQUA + new ItemStack(Blocks.STONE, 1, 6).getDisplayName() + a + FRBlocks.road_block_fine.getLocalizedName(),
					TextFormatting.AQUA + Blocks.GRASS.getLocalizedName() + a + FRBlocks.road_block_grass.getLocalizedName(),
					TextFormatting.AQUA + Blocks.DIRT.getLocalizedName() + a + FRBlocks.road_block_dirt.getLocalizedName(),
					TextFormatting.AQUA + Blocks.GRAVEL.getLocalizedName() + a + FRBlocks.road_block_gravel.getLocalizedName(),
					TextFormatting.AQUA + Blocks.SAND.getLocalizedName() + a + FRBlocks.road_block_sand.getLocalizedName());
			
			if (mouseX >= (left +  34) && mouseX <= (left +  73) && mouseY >= (top + 20) && mouseY <= (top +  59)) { this.drawHoveringText(inputList, mouseX - left, mouseY - top + 15); }
			if (mouseX >= (left +  34) && mouseX <= (left +  50) && mouseY >= (top + 70) && mouseY <= (top +  86)) { this.drawHoveringText(I18n.format("roads.gui.tarInputSlot"), mouseX - left, mouseY - top + 15); }
			if (mouseX >= (left +  34) && mouseX <= (left +  50) && mouseY >= (top + 92) && mouseY <= (top + 108)) { this.drawHoveringText(I18n.format("roads.gui.bucketOutputSlot"), mouseX - left, mouseY - top + 15); }
			if (mouseX >= (left + 152) && mouseX <= (left + 168) && mouseY >= (top + 43) && mouseY <= (top +  59) && !electric) { this.drawHoveringText(I18n.format("roads.gui.fuelSlot"), mouseX - left, mouseY - top + 15); }
			if (mouseX >= (left + 107) && mouseX <= (left + 146) && mouseY >= (top + 20) && mouseY <= (top +  59)) { this.drawHoveringText(I18n.format("roads.gui.outputSlot"), mouseX - left, mouseY - top + 15); }
			
			if (distillerFound()) {
				if (mouseX >= (left + 114) && mouseX <= (left + 169) && mouseY >= (top + 93) && mouseY <= (top + 107)) { this.drawHoveringText(TextFormatting.GREEN + I18n.format("roads.gui.road_factory.connect_pass"), mouseX - left, mouseY - top + 15); }
			} else {
				List<String> error = Arrays.asList(
						TextFormatting.RED + I18n.format("roads.gui.road_factory.connect_failed_1"),
						TextFormatting.RED + I18n.format("roads.gui.road_factory.connect_failed_2"),
						TextFormatting.RED + I18n.format("roads.gui.road_factory.connect_failed_3")
					);
				if (mouseX >= (left + 114) && mouseX <= (left + 169) && mouseY >= (top + 93) && mouseY <= (top + 107)) { this.drawHoveringText(error, mouseX - left, mouseY - top + 15); }
			}
		}
	}
	
	private int getPercentage(int num, int max) {
		float x = (float) num / (float) max;
		int y = Math.round(x*100);
		if (y > 100) { return 100; }
		return y;
	}

	private boolean distillerFound() {
		IBlockState state = tileEntity.getState();
		RoadFactoryBlock block = (RoadFactoryBlock) tileEntity.getBlockType();
		return block.connect(state, Minecraft.getMinecraft().world, tileEntity.getPos());
	}
}
