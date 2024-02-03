package com.fureniku.roads.client.screens;

import com.fureniku.roads.FurenikusRoads;
import com.fureniku.roads.blockentities.menus.CrusherMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CrusherScreen extends AbstractContainerScreen<CrusherMenu> {

    private static final ResourceLocation BACKGROUND_LOCATION = new ResourceLocation(FurenikusRoads.MODID, "textures/gui/machine/crusher.png");

    public CrusherScreen(CrusherMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);

        this.titleLabelX = 10;
        this.inventoryLabelX = 10;
    }

    //TODO move to metro
    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTick);

        /*
         * This method is added by the container screen to render
         * the tooltip of the hovered slot.
         */
        this.renderTooltip(graphics, mouseX, mouseY);
    }

    //TODO move to metro
    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, BACKGROUND_LOCATION);

        graphics.blit(BACKGROUND_LOCATION, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }

    //TODO move to metro
    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
        super.renderLabels(graphics, mouseX, mouseY);

        // Assume we have some Component 'label'
        // 'label' is drawn at 'labelX' and 'labelY'
        //graphics.drawString(this.font, this.label, this.labelX, this.labelY, 0x404040);
    }
}
