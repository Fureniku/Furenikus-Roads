package co.uk.silvania.roads.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class RoadSlope2Model extends ModelBase
{
  //fields
    ModelRenderer Shape3;
    ModelRenderer Shape4;
  
  public RoadSlope2Model()
  {
    textureWidth = 32;
    textureHeight = 64;
    
      Shape3 = new ModelRenderer(this, 0, 0);
      Shape3.addBox(-8F, 0F, 0F, 16, 18, 1);
      Shape3.setRotationPoint(0F, 16F, 8F);
      Shape3.setTextureSize(32, 64);
      Shape3.mirror = true;
      setRotation(Shape3, -1.11352F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 0, 19);
      Shape4.addBox(-8F, 0F, 0F, 16, 18, 1);
      Shape4.setRotationPoint(0F, 8F, 24F);
      Shape4.setTextureSize(32, 64);
      Shape4.mirror = true;
      setRotation(Shape4, -1.11352F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Shape3.render(f5);
    Shape4.render(f5);
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
