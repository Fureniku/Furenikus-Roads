package co.uk.silvania.roads.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class LightBollardModel extends ModelBase
{
  //fields
    ModelRenderer Base;
    ModelRenderer Body;
    ModelRenderer Top1;
    ModelRenderer Top2;
  
  public LightBollardModel()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Base = new ModelRenderer(this, 24, 21);
      Base.addBox(0F, 0F, 0F, 8, 1, 8);
      Base.setRotationPoint(-4F, 23F, -4F);
      Base.setTextureSize(64, 32);
      Base.mirror = true;
      setRotation(Base, 0F, 0F, 0F);
      Body = new ModelRenderer(this, 0, 0);
      Body.addBox(0F, 0F, 0F, 6, 12, 6);
      Body.setRotationPoint(-3F, 11F, -3F);
      Body.setTextureSize(64, 32);
      Body.mirror = true;
      setRotation(Body, 0F, 0F, 0F);
      Top1 = new ModelRenderer(this, 32, 12);
      Top1.addBox(0F, 0F, 0F, 5, 1, 5);
      Top1.setRotationPoint(-2.5F, 10F, -2.5F);
      Top1.setTextureSize(64, 32);
      Top1.mirror = true;
      setRotation(Top1, 0F, 0F, 0F);
      Top2 = new ModelRenderer(this, 32, 5);
      Top2.addBox(0F, 0F, 0F, 4, 1, 4);
      Top2.setRotationPoint(-2F, 9F, -2F);
      Top2.setTextureSize(64, 32);
      Top2.mirror = true;
      setRotation(Top2, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Base.render(f5);
    Body.render(f5);
    Top1.render(f5);
    Top2.render(f5);
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
