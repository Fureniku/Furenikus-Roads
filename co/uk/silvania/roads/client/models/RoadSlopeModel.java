package co.uk.silvania.roads.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class RoadSlopeModel extends ModelBase
{
  //fields
    ModelRenderer Section1;
    ModelRenderer Section2;
    ModelRenderer Section3;
    ModelRenderer Section4;
  
  public RoadSlopeModel()
  {
    textureWidth = 16;
    textureHeight = 128;
    
      Section1 = new ModelRenderer(this, -17, 0);
      Section1.addBox(-8F, 0F, -17F, 16, 1, 17);
      Section1.setRotationPoint(0F, 20F, 8F);
      Section1.setTextureSize(16, 128);
      Section1.mirror = true;
      setRotation(Section1, 0.2460914F, 0F, 0F);
      Section2 = new ModelRenderer(this, -17, 18);
      Section2.addBox(-8F, 0F, -17F, 16, 1, 17);
      Section2.setRotationPoint(0F, 16F, 24F);
      Section2.setTextureSize(16, 128);
      Section2.mirror = true;
      setRotation(Section2, 0.2460914F, 0F, 0F);
      Section3 = new ModelRenderer(this, -17, 36);
      Section3.addBox(-8F, 0F, -17F, 16, 1, 17);
      Section3.setRotationPoint(0F, 12F, 40F);
      Section3.setTextureSize(16, 128);
      Section3.mirror = true;
      setRotation(Section3, 0.2460914F, 0F, 0F);
      Section4 = new ModelRenderer(this, -17, 54);
      Section4.addBox(-8F, 0F, -17F, 16, 1, 17);
      Section4.setRotationPoint(0F, 8F, 56F);
      Section4.setTextureSize(16, 128);
      Section4.mirror = true;
      setRotation(Section4, 0.2460914F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Section1.render(f5);
    Section2.render(f5);
    Section3.render(f5);
    Section4.render(f5);
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
