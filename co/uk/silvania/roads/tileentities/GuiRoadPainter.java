package co.uk.silvania.roads.tileentities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import co.uk.silvania.roads.tileentities.entities.TileEntityRoadPainterEntity;

public class GuiRoadPainter extends GuiContainer {

        public GuiRoadPainter (InventoryPlayer inventoryPlayer, World world, int x, int y, int z) {
        	super(new ContainerRoadPainter(inventoryPlayer, world, x, y, z));
        }
        
        protected int xSize = 176;
        protected int ySize = 204;

        @Override
        protected void drawGuiContainerForegroundLayer(int param1, int param2) {
                fontRenderer.drawString("Road Painter", 8, -12, 4210752);
                fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 113, 4210752);
        }

        @Override
        protected void drawGuiContainerBackgroundLayer(float par1, int par2,
                        int par3) {
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("flenixroads", "textures/gui/roadpaintergui.png"));
                int x = (width - xSize) / 2;
                int y = (height - ySize) / 2;
                this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        }
}