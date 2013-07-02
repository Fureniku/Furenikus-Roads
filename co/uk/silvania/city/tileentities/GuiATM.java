package co.uk.silvania.city.tileentities;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import co.uk.silvania.roads.tileentities.entities.TileEntityRoadPainterEntity;

public class GuiATM extends GuiContainer {

        public GuiATM (InventoryPlayer inventoryPlayer, TileEntityATMEntity tileEntity, World world, int x, int y, int z) {
                                super(new ContainerATM(inventoryPlayer, tileEntity));
        }
        
        /** The X size of the inventory window in pixels. */
        protected int xSize = 176;

        /** The Y size of the inventory window in pixels. */
        protected int ySize = 182;

        @Override
        protected void drawGuiContainerForegroundLayer(int param1, int param2) {
                fontRenderer.drawString("ATM", 8, 0, 4210752);
                fontRenderer.drawString("Balance", 8, 17, 4210752);
                fontRenderer.drawString("Safe", 132, -3, 4210752);
                fontRenderer.drawString("Deposit", 124, 5, 4210752);
                fontRenderer.drawString("Withdraw", 8, 37, 4210752);
                fontRenderer.drawString("Deposit", 8, 57, 4210752);
                fontRenderer.drawString("1234.56", 58, 17, 1237000);
                fontRenderer.drawString("345.67", 58, 37, 1237000);
                fontRenderer.drawString("789.01", 58, 57, 1237000);
                fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 103, 4210752);
        }

        @Override
        protected void drawGuiContainerBackgroundLayer(float par1, int par2,
                        int par3) {
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                this.mc.renderEngine.bindTexture("/mods/City/textures/gui/atm.png");
                int x = (width - xSize) / 2;
                int y = (height - ySize) / 2;
                this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        }
}