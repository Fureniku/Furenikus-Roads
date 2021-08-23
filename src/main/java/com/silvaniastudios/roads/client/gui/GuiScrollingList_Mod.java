/*
 * Minecraft Forge
 * Copyright (c) 2016-2018.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation version 2.1
 * of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

/*
 * Modified by Fureniku. Modifications made are as follows
 * - Removed the border so the first element can correctly top-align
 * - Removed check for height so the scrollbar will always render
 */

package com.silvaniastudios.roads.client.gui;

import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.config.GuiUtils;

public abstract class GuiScrollingList_Mod
{
    private final Minecraft client;
    protected final int listWidth;
    protected final int listHeight;
    protected final int screenWidth;
    protected final int screenHeight;
    protected final int top;
    protected final int bottom;
    protected final int right;
    protected final int left;
    protected final int slotHeight;

    protected int mouseX;
    protected int mouseY;
    private float initialMouseClickY = -2.0F;
    private float scrollFactor;
    private float scrollDistance;
    protected int selectedIndex = -1;
    private long lastClickTime = 0L;
    private boolean highlightSelected = true;
    private boolean hasHeader;
    private int headerHeight;
    protected boolean captureMouse = true;
    protected ArrayList<String> tooltipList;

    public GuiScrollingList_Mod(Minecraft client, int width, int height, int top, int bottom, int left, int entryHeight, int screenWidth, int screenHeight, ArrayList<String> tooltipList)
    {
        this.client = client;
        this.listWidth = width;
        this.listHeight = height;
        this.top = top;
        this.bottom = bottom;
        this.slotHeight = entryHeight;
        this.left = left;
        this.right = width + this.left;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
		this.tooltipList = tooltipList;
    }

    @Deprecated // Unused, remove in 1.9.3?
    public void func_27258_a(boolean p_27258_1_)
    {
        this.highlightSelected = p_27258_1_;
    }

    @Deprecated protected void func_27259_a(boolean hasFooter, int footerHeight){ setHeaderInfo(hasFooter, footerHeight); }
    protected void setHeaderInfo(boolean hasHeader, int headerHeight)
    {
        this.hasHeader = hasHeader;
        this.headerHeight = headerHeight;
        if (!hasHeader) this.headerHeight = 0;
    }

    protected abstract int getSize();

    protected abstract void elementClicked(int index, boolean doubleClick);

    protected abstract boolean isSelected(int index);

    protected int getContentHeight()
    {
        return this.getSize() * this.slotHeight + this.headerHeight;
    }

    protected abstract void drawBackground();

    /**
     * Draw anything special on the screen. GL_SCISSOR is enabled for anything that
     * is rendered outside of the view box. Do not mess with SCISSOR unless you support this.
     */
    protected abstract void drawSlot(int slotIdx, int entryRight, int slotTop, int slotBuffer, Tessellator tess);

    @Deprecated protected void func_27260_a(int entryRight, int relativeY, Tessellator tess) {}
    /**
     * Draw anything special on the screen. GL_SCISSOR is enabled for anything that
     * is rendered outside of the view box. Do not mess with SCISSOR unless you support this.
     */
    protected void drawHeader(int entryRight, int relativeY, Tessellator tess) { func_27260_a(entryRight, relativeY, tess); }

    @Deprecated protected void func_27255_a(int x, int y) {}
    protected void clickHeader(int x, int y) { func_27255_a(x, y); }

    @Deprecated protected void func_27257_b(int mouseX, int mouseY) {}
    /**
     * Draw anything special on the screen. GL_SCISSOR is enabled for anything that
     * is rendered outside of the view box. Do not mess with SCISSOR unless you support this.
     */
    protected void drawScreen(int mouseX, int mouseY) { func_27257_b(mouseX, mouseY); }

    private void applyScrollLimits()
    {
        int listHeight = this.getContentHeight() - (this.bottom - this.top - 4);

        if (listHeight < 0)
        {
            listHeight /= 2;
        }

        if (this.scrollDistance < 0.0F)
        {
            this.scrollDistance = 0.0F;
        }

        if (this.scrollDistance > (float)listHeight)
        {
            this.scrollDistance = (float)listHeight;
        }
    }


    public void handleMouseInput(int mouseX, int mouseY) throws IOException
    {
        boolean isHovering = mouseX >= this.left && mouseX <= this.left + this.listWidth &&
                             mouseY >= this.top && mouseY <= this.bottom;
        if (!isHovering)
            return;

        int scroll = Mouse.getEventDWheel();
        if (scroll != 0)
        {
            this.scrollDistance += (float)((-1 * scroll / 120.0F) * this.slotHeight / 2);
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.drawBackground();

        boolean isHovering = mouseX >= this.left && mouseX <= this.left + this.listWidth &&
                             mouseY >= this.top && mouseY <= this.bottom;
        int listLength     = this.getSize();
        int scrollBarWidth = 6;
        int scrollBarRight = this.left + this.listWidth;
        int scrollBarLeft  = scrollBarRight - scrollBarWidth;
        int entryLeft      = this.left;
        int entryRight     = scrollBarLeft - 1;
        int viewHeight     = this.bottom - this.top;
        int border         = 0;
        
        if (Mouse.isButtonDown(0))
        {
            if (this.initialMouseClickY == -1.0F)
            {
                if (isHovering)
                {
                    int mouseListY = mouseY - this.top - this.headerHeight + (int)this.scrollDistance - border;
                    int slotIndex = mouseListY / this.slotHeight;

                    if (mouseX >= entryLeft && mouseX <= entryRight && slotIndex >= 0 && mouseListY >= 0 && slotIndex < listLength)
                    {
                        this.elementClicked(slotIndex, slotIndex == this.selectedIndex && System.currentTimeMillis() - this.lastClickTime < 250L);
                        this.selectedIndex = slotIndex;
                        this.lastClickTime = System.currentTimeMillis();
                    }
                    else if (mouseX >= entryLeft && mouseX <= entryRight && mouseListY < 0)
                    {
                        this.clickHeader(mouseX - entryLeft, mouseY - this.top + (int)this.scrollDistance - border);
                    }

                    if (mouseX >= scrollBarLeft && mouseX <= scrollBarRight)
                    {
                    	resetScrollbar(viewHeight, border);
                    }

                    this.initialMouseClickY = mouseY;
                }
                else
                {
                    this.initialMouseClickY = -2.0F;
                }
            }
            else if (this.initialMouseClickY >= 0.0F)
            {
                this.scrollDistance -= ((float)mouseY - this.initialMouseClickY) * this.scrollFactor;
                this.initialMouseClickY = (float)mouseY;
            }
        }
        else
        {
            this.initialMouseClickY = -1.0F;
        }

        this.applyScrollLimits();
        Tessellator tess = Tessellator.getInstance();
        BufferBuilder worldr = tess.getBuffer();

        ScaledResolution res = new ScaledResolution(client);
        double scaleW = client.displayWidth / res.getScaledWidth_double();
        double scaleH = client.displayHeight / res.getScaledHeight_double();
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor((int)(left      * scaleW), (int)(client.displayHeight - (bottom * scaleH)),
                       (int)(listWidth * scaleW), (int)(viewHeight * scaleH));

        if (this.client.world != null)
        {
            this.drawGradientRect(this.left, this.top, this.right, this.bottom, 0xC0101010, 0xD0101010);
        }

        int baseY = this.top + border - (int)this.scrollDistance;

        if (this.hasHeader) {
            this.drawHeader(entryRight, baseY, tess);
        }

        for (int slotIdx = 0; slotIdx < listLength; ++slotIdx)
        {
            int slotTop = baseY + slotIdx * this.slotHeight + this.headerHeight;
            int slotBuffer = this.slotHeight - border;

            if (slotTop <= this.bottom && slotTop + slotBuffer >= this.top)
            {
                if (this.highlightSelected && this.isSelected(slotIdx))
                {
                    int min = this.left;
                    int max = entryRight;
                    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                    GlStateManager.disableTexture2D();
                    worldr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
                    worldr.pos(min,     slotTop + slotBuffer + 2, 0).tex(0, 1).color(0x80, 0x80, 0x80, 0xFF).endVertex();
                    worldr.pos(max,     slotTop + slotBuffer + 2, 0).tex(1, 1).color(0x80, 0x80, 0x80, 0xFF).endVertex();
                    worldr.pos(max,     slotTop              - 2, 0).tex(1, 0).color(0x80, 0x80, 0x80, 0xFF).endVertex();
                    worldr.pos(min,     slotTop              - 2, 0).tex(0, 0).color(0x80, 0x80, 0x80, 0xFF).endVertex();
                    worldr.pos(min + 1, slotTop + slotBuffer + 1, 0).tex(0, 1).color(0x00, 0x00, 0x00, 0xFF).endVertex();
                    worldr.pos(max - 1, slotTop + slotBuffer + 1, 0).tex(1, 1).color(0x00, 0x00, 0x00, 0xFF).endVertex();
                    worldr.pos(max - 1, slotTop              - 1, 0).tex(1, 0).color(0x00, 0x00, 0x00, 0xFF).endVertex();
                    worldr.pos(min + 1, slotTop              - 1, 0).tex(0, 0).color(0x00, 0x00, 0x00, 0xFF).endVertex();
                    tess.draw();
                    GlStateManager.enableTexture2D();
                }

                this.drawSlot(slotIdx, entryRight, slotTop, slotBuffer, tess);
            }
        }

        GlStateManager.disableDepth();

        int extraHeight = (this.getContentHeight() + border) - viewHeight;

        int ch = this.getContentHeight();
        
        if (ch == 0) { ch = 1; }
        int height = (viewHeight * viewHeight) / ch;

        if (height < 32) height = 32;

        if (height > viewHeight - border*2)
            height = viewHeight - border*2;

        int barTop = (int)this.scrollDistance * (viewHeight - height) / extraHeight + this.top;
        if (barTop < this.top)
        {
            barTop = this.top;
        }

        GlStateManager.disableTexture2D();
        worldr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
        worldr.pos(scrollBarLeft,  this.bottom, 0.0D).tex(0.0D, 1.0D).color(0x00, 0x00, 0x00, 0xFF).endVertex();
        worldr.pos(scrollBarRight, this.bottom, 0.0D).tex(1.0D, 1.0D).color(0x00, 0x00, 0x00, 0xFF).endVertex();
        worldr.pos(scrollBarRight, this.top,    0.0D).tex(1.0D, 0.0D).color(0x00, 0x00, 0x00, 0xFF).endVertex();
        worldr.pos(scrollBarLeft,  this.top,    0.0D).tex(0.0D, 0.0D).color(0x00, 0x00, 0x00, 0xFF).endVertex();
        tess.draw();
        worldr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
        worldr.pos(scrollBarLeft,  barTop + height, 0.0D).tex(0.0D, 1.0D).color(0x80, 0x80, 0x80, 0xFF).endVertex();
        worldr.pos(scrollBarRight, barTop + height, 0.0D).tex(1.0D, 1.0D).color(0x80, 0x80, 0x80, 0xFF).endVertex();
        worldr.pos(scrollBarRight, barTop,          0.0D).tex(1.0D, 0.0D).color(0x80, 0x80, 0x80, 0xFF).endVertex();
        worldr.pos(scrollBarLeft,  barTop,          0.0D).tex(0.0D, 0.0D).color(0x80, 0x80, 0x80, 0xFF).endVertex();
        tess.draw();
        worldr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
        worldr.pos(scrollBarLeft,      barTop + height - 1, 0.0D).tex(0.0D, 1.0D).color(0xC0, 0xC0, 0xC0, 0xFF).endVertex();
        worldr.pos(scrollBarRight - 1, barTop + height - 1, 0.0D).tex(1.0D, 1.0D).color(0xC0, 0xC0, 0xC0, 0xFF).endVertex();
        worldr.pos(scrollBarRight - 1, barTop,              0.0D).tex(1.0D, 0.0D).color(0xC0, 0xC0, 0xC0, 0xFF).endVertex();
        worldr.pos(scrollBarLeft,      barTop,              0.0D).tex(0.0D, 0.0D).color(0xC0, 0xC0, 0xC0, 0xFF).endVertex();
        tess.draw();

        this.drawScreen(mouseX, mouseY);
        GlStateManager.enableTexture2D();
        GlStateManager.shadeModel(GL11.GL_FLAT);
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }
    
    private void resetScrollbar(int viewHeight, int border) {
        this.scrollFactor = -1.0F;
        int scrollHeight = this.getContentHeight() - viewHeight - border;
        if (scrollHeight < 1) scrollHeight = 1;

        int var13 = (int)((float)(viewHeight * viewHeight) / (float)this.getContentHeight());

        if (var13 < 32) var13 = 32;
        if (var13 > viewHeight - border*2)
            var13 = viewHeight - border*2;

        this.scrollFactor /= (float)(viewHeight - var13) / (float)scrollHeight;
    }

    protected void drawGradientRect(int left, int top, int right, int bottom, int color1, int color2)
    {
        GuiUtils.drawGradientRect(0, left, top, right, bottom, color1, color2);
    }
    
    protected void drawItemStack(FontRenderer fontRenderer, ItemStack stack, int x, int y) {
		RenderHelper.disableStandardItemLighting();
		RenderHelper.enableGUIStandardItemLighting();
		
		GlStateManager.translate(0.0F, 0.0F, 32.0F);
        client.getRenderItem().renderItemAndEffectIntoGUI(stack, x, y);
        
        GlStateManager.scale(0.5, 0.5, 0.5);
        fontRenderer.drawString("" + stack.getCount(), (x+12)*2, (y+12)*2, 0xFFFFFF);
        GlStateManager.scale(2.0, 2.0, 2.0);
        GlStateManager.translate(0.0F, 0.0F, -32.0F);
        
        RenderHelper.enableStandardItemLighting();
    }
    
    protected void drawItemStack(ItemStack stack, int x, int y, float r, float g, float b) {
		RenderHelper.disableStandardItemLighting();
		RenderHelper.enableGUIStandardItemLighting();
		
		GlStateManager.translate(0.0F, 0.0F, 32.0F);
		GlStateManager.color(0.0f, 0.0f, 0.0f);
        client.getRenderItem().renderItemAndEffectIntoGUI(stack, x, y);
        GlStateManager.color(1.0f, 1.0f, 1.0f);

        GlStateManager.translate(0.0F, 0.0F, -32.0F);
        
        RenderHelper.enableStandardItemLighting();
    }
}