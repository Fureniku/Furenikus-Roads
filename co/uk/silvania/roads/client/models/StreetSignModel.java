package co.uk.silvania.roads.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class StreetSignModel extends ModelBase
{
  //fields
    ModelRenderer Pole;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
  
  public StreetSignModel()
  {
    textureWidth = 32;
    textureHeight = 32;
    
      Pole = new ModelRenderer(this, 0, 0);
      Pole.addBox(0F, 0F, 0F, 1, 8, 1);
      Pole.setRotationPoint(-0.5F, 16F, -0.5F);
      Pole.setTextureSize(32, 32);
      Pole.mirror = true;
      setRotation(Pole, 0F, 0F, 0F);
      Shape1 = new ModelRenderer(this, 4, 21);
      Shape1.addBox(0F, 0F, 0F, 4, 1, 1);
      Shape1.setRotationPoint(-2F, 15.9F, -1F);
      Shape1.setTextureSize(32, 32);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 4, 23);
      Shape2.addBox(0F, 0F, 0F, 4, 1, 1);
      Shape2.setRotationPoint(-2F, 22.9F, -1F);
      Shape2.setTextureSize(32, 32);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 14, 25);
      Shape3.addBox(0F, 0F, 0F, 6, 1, 1);
      Shape3.setRotationPoint(-3F, 21.9F, -1F);
      Shape3.setTextureSize(32, 32);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 0, 25);
      Shape4.addBox(0F, 0F, 0F, 6, 1, 1);
      Shape4.setRotationPoint(-3F, 16.9F, -1F);
      Shape4.setTextureSize(32, 32);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 0, 27);
      Shape5.addBox(0F, 0F, 0F, 8, 4, 1);
      Shape5.setRotationPoint(-4F, 17.9F, -1F);
      Shape5.setTextureSize(32, 32);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Pole.render(f5);
    Shape1.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
    Shape4.render(f5);
    Shape5.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
