package co.uk.silvania.roads.tileentities;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import co.uk.silvania.roads.tileentities.entities.TileEntityRoadPainterEntity;

public class GuiRoadPainter extends GuiContainer {

        public GuiRoadPainter (InventoryPlayer inventoryPlayer, TileEntityRoadPainterEntity tileEntity, World world, int x, int y, int z) {
                                super(new ContainerRoadPainter(inventoryPlayer, tileEntity, world, x, y, z));
        }
        
        /** The X size of the inventory window in pixels. */
        protected int xSize = 176;

        /** The Y size of the inventory window in pixels. */
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
                this.mc.renderEngine.bindTexture("/mods/Roads/textures/gui/RoadPainterGui.png");
                int x = (width - xSize) / 2;
                int y = (height - ySize) / 2;
                this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        }
}