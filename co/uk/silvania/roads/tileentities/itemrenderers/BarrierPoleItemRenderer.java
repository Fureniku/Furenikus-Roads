package co.uk.silvania.roads.tileentities.itemrenderers;

import org.lwjgl.opengl.GL11;

import co.uk.silvania.roads.Roads;
import co.uk.silvania.roads.client.models.BarrierPoleModel;
import co.uk.silvania.roads.tileentities.entities.TileEntityBarrierPoleEntity;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;

public class BarrierPoleItemRenderer implements IItemRenderer {

    private BarrierPoleModel model;

    public BarrierPoleItemRenderer() {

        model = new BarrierPoleModel();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
    	//1st - Left/Right   2nd - Up/Down   3rd - Near/Far   4th - Size
        switch (type) {
            case ENTITY: {
                renderBarrierPole(-0.5F, 0.0F, 0.5F, 2.0F);
                return;
            }
            case EQUIPPED: {
                renderBarrierPole(-1.0F, -1.0F, 1.0F, 2.0F);
                return;
            }
            case EQUIPPED_FIRST_PERSON: {
                renderBarrierPole(2.0F, -1.0F, 1.0F, 2.0F);
                return;
                }
            case INVENTORY: {
                renderBarrierPole(0.6F, -0.2F, 2.0F, 1.4F);
                return;
            }
            default:
                return;
        }
    }

    private void renderBarrierPole(float x, float y, float z, float scale) {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glTranslatef(x + 1, y + 3, z - 1);
        GL11.glRotatef(0F, 0.0F, 0.0F, 0.0F);
        GL11.glScalef(scale, -scale, -scale);
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("flenixroads", "textures/entities/Barrier.png"));
        model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
    
    private int getBlockMetadata(World world, int x, int y, int z) {
    	int meta = world.getBlockMetadata (x, y, z);
		return meta;
    }
}