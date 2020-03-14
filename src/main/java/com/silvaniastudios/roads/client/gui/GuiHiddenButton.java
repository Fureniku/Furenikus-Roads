package com.silvaniastudios.roads.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class GuiHiddenButton extends GuiButton {
	
	public GuiHiddenButton(int buttonId, int x, int y, int width, int height) {
		super(buttonId, x, y, width, height, "");
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        return; //It's invisible, why would we draw it o.o
    }
}
