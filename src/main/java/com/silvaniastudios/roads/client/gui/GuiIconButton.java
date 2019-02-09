package com.silvaniastudios.roads.client.gui;

import java.util.Arrays;

import com.silvaniastudios.roads.FurenikusRoads;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiIconButton extends GuiButton {
	
	public String iconName = "empty";
	public int iconId = 0;
	public String hoverText = "";
	public int colourType = 0;
	
	public GuiIconButton(int buttonId, int x, int y) {
		super(buttonId, x, y, 20, 20, "");
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (this.visible) {
        	ResourceLocation buttonIcon = new ResourceLocation(FurenikusRoads.MODID + ":textures/gui/buttons/" + iconName + ".png");
            mc.getTextureManager().bindTexture(buttonIcon);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;

            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

            int colourOffset = 0;
            if (colourType == 1) { colourOffset = 80; }
            if (colourType == 2) { colourOffset = 160; }
            
            if (this.hovered) {
            	ResourceLocation hoverIcon = new ResourceLocation(FurenikusRoads.MODID + ":textures/gui/buttons/" + iconName + "_hovered.png");
            	mc.getTextureManager().bindTexture(hoverIcon);
            	//net.minecraftforge.fml.client.config.GuiUtils.drawHoveringText(Arrays.asList(hoverText), mouseX, mouseY, width, height, -1, mc.fontRenderer);
            }
            
            this.drawTexturedModalRect(this.x, this.y, findIconX(iconId), findIconY(iconId) + colourOffset, 20, 20);
                        
            this.mouseDragged(mc, mouseX, mouseY);
        }
    }
	
	public int findIconX(int id) {
		while (id > 11) {
			id = id - 12;
		}
		return id * 20;
	}
	
	public int findIconY(int id) {
		int i = 0;
		while (id > 11) {
			id = id - 12;
			i++;
		}
		return i * 20;
	}
	
	//Borrowed from GuiScreen
	protected void drawHoveringText(String text, int x, int y, FontRenderer font)  {
        net.minecraftforge.fml.client.config.GuiUtils.drawHoveringText(Arrays.asList(text), x, y, width, height, -1, font);
    }
}
