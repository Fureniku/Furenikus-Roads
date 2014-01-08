package co.uk.silvania.roads.client.vehicles;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import co.uk.silvania.roads.Roads;
import co.uk.silvania.roads.entity.EntityBasicCar;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBasicCar extends Render {
    private static final ResourceLocation texture = new ResourceLocation("flenixroads", "textures/vehicles/BasicCar.png");
	private static final ResourceLocation chargedTexture = new ResourceLocation("flenixroads", "textures/vehicles/BasicCar_red.png");

    protected ModelBasicCar model;

    public RenderBasicCar() {
        shadowSize = 0.5F;
        model = new ModelBasicCar();
    }

    public void renderBasicCar(EntityBasicCar car, double x, double y, double z, float yaw, float partialTickTime) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x - 0.85F, (float)y + 1.5F, (float)z + 0.7F);
        GL11.glRotatef(180.0F - yaw, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(-1.0F, -1.0F, 1.0F);

        
        bindEntityTexture(car);
        
        model.render(car, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        
        GL11.glPopMatrix();
    }


    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTickTime) {
        this.renderBasicCar((EntityBasicCar)entity, x, y, z, yaw, partialTickTime);
    }
    
    protected ResourceLocation getCarTextures(EntityBasicCar entity) {
    	return ((EntityBasicCar)entity).isRed() ? chargedTexture : texture;
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return (this.getCarTextures((EntityBasicCar)entity));//.isRed() ? chargedTexture : texture;
	}
}
