package com.silvaniastudios.roads.client.gui;

import java.util.ArrayList;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.tileentities.fabricator.FabricatorEntity;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class GuiFabricatorSelector extends GuiScreen {
	
	private static final ResourceLocation guiTextures = new ResourceLocation(FurenikusRoads.MODID + ":textures/gui/fabricator_list.png");
	
	private final int xSize = 356;
	private final int ySize = 228;
	
	FabricatorRecipeList recipeList;
	FabricatorEntity te;
	ArrayList<String> tooltipList = new ArrayList<String>();

	public GuiFabricatorSelector(FabricatorEntity te) {
		this.te = te;
	}
	
	public void initGui() {
		super.initGui();
		int guiLeft = (width - xSize) / 2;
	    int guiTop = (height - ySize) / 2;
	    
	    recipeList = new FabricatorRecipeList(340, 212, guiTop+8, guiLeft+8, 28, width, height, fontRenderer, mc, this, te, tooltipList);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        
        int left = (width - xSize) / 2;
		int top = (height - ySize) / 2;
        mc.getTextureManager().bindTexture(guiTextures);
        drawTexturedModalRect(left, top, 0, 28, 180, ySize);
        drawTexturedModalRect(left+180, top, 80, 28, 176, ySize);
        
        tooltipList.clear();
        
        recipeList.drawScreen(mouseX, mouseY, partialTicks);

        if (tooltipList.size() > 0) {
			drawHoveringText(tooltipList, mouseX, mouseY);
		}
    }
}
