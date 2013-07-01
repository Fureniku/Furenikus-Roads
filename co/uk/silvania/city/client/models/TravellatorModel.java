package co.uk.silvania.city.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class TravellatorModel extends ModelBase
{
  //fields
    ModelRenderer Step1;
    ModelRenderer Step2;
    ModelRenderer Step3;
    ModelRenderer Step4;
    ModelRenderer Banister1;
    ModelRenderer Base;
    ModelRenderer Side1;
    ModelRenderer Banister2;
    ModelRenderer MetalSide2;
    ModelRenderer GlassRight;
    ModelRenderer SideLeft;
    ModelRenderer GlassLeft;
    ModelRenderer MetalSide1;
  
  public TravellatorModel()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      Step1 = new ModelRenderer(this, 0, 24);
      Step1.addBox(0F, 0F, 0F, 18, 4, 4);
      Step1.setRotationPoint(-6F, 23.9F, -8F);
      Step1.setTextureSize(64, 64);
      Step1.mirror = true;
      setRotation(Step1, 0F, 0F, 0F);
      Step2 = new ModelRenderer(this, 0, 24);
      Step2.addBox(0F, 0F, 0F, 18, 4, 4);
      Step2.setRotationPoint(-6F, 23.9F, -4F);
      Step2.setTextureSize(64, 64);
      Step2.mirror = true;
      setRotation(Step2, 0F, 0F, 0F);
      Step3 = new ModelRenderer(this, 0, 24);
      Step3.addBox(0F, 0F, 0F, 18, 4, 4);
      Step3.setRotationPoint(-6F, 23.9F, 0F);
      Step3.setTextureSize(64, 64);
      Step3.mirror = true;
      setRotation(Step3, 0F, 0F, 0F);
      Step4 = new ModelRenderer(this, 0, 24);
      Step4.addBox(0F, 0F, 0F, 18, 4, 4);
      Step4.setRotationPoint(-6F, 23.9F, 4F);
      Step4.setTextureSize(64, 64);
      Step4.mirror = true;
      setRotation(Step4, 0F, 0F, 0F);
      Banister1 = new ModelRenderer(this, 52, 0);
      Banister1.addBox(-1F, -16F, -0.5F, 2, 16, 1);
      Banister1.setRotationPoint(13F, 5F, -8F);
      Banister1.setTextureSize(64, 64);
      Banister1.mirror = true;
      setRotation(Banister1, -1.570796F, 0F, 0F);
      Base = new ModelRenderer(this, 0, 0);
      Base.addBox(-8F, 0F, 0F, 22, 16, 1);
      Base.setRotationPoint(0F, 27.8F, 8F);
      Base.setTextureSize(64, 64);
      Base.mirror = true;
      setRotation(Base, -1.570796F, 0F, 0F);
      Side1 = new ModelRenderer(this, 0, 37);
      Side1.addBox(0F, 0F, 0F, 1, 7, 16);
      Side1.setRotationPoint(12.1F, 21.8F, -8F);
      Side1.setTextureSize(64, 64);
      Side1.mirror = true;
      setRotation(Side1, 0F, 0F, 0F);
      Banister2 = new ModelRenderer(this, 52, 0);
      Banister2.addBox(-1F, -16F, -0.5F, 2, 16, 1);
      Banister2.setRotationPoint(-7F, 5F, -8F);
      Banister2.setTextureSize(64, 64);
      Banister2.mirror = true;
      setRotation(Banister2, -1.570796F, 0F, 0F);
      MetalSide2 = new ModelRenderer(this, 58, 0);
      MetalSide2.addBox(-1F, -16F, -0.5F, 2, 16, 1);
      MetalSide2.setRotationPoint(-7F, 21.5F, -8F);
      MetalSide2.setTextureSize(64, 64);
      MetalSide2.mirror = true;
      setRotation(MetalSide2, -1.570796F, 0F, 0F);
      GlassRight = new ModelRenderer(this, 30, 21);
      GlassRight.addBox(0F, 0F, 0F, 1, 16, 16);
      GlassRight.setRotationPoint(12.5F, 5F, -8F);
      GlassRight.setTextureSize(64, 64);
      GlassRight.mirror = true;
      setRotation(GlassRight, 0F, 0F, 0F);
      SideLeft = new ModelRenderer(this, 0, 37);
      SideLeft.addBox(0F, 0F, 0F, 1, 7, 16);
      SideLeft.setRotationPoint(-7.1F, 21.8F, -8F);
      SideLeft.setTextureSize(64, 64);
      SideLeft.mirror = true;
      setRotation(SideLeft, 0F, 0F, 0F);
      GlassLeft = new ModelRenderer(this, 30, 21);
      GlassLeft.addBox(0F, 0F, 0F, 1, 16, 16);
      GlassLeft.setRotationPoint(-7.5F, 5F, -8F);
      GlassLeft.setTextureSize(64, 64);
      GlassLeft.mirror = true;
      setRotation(GlassLeft, 0F, 0F, 0F);
      MetalSide1 = new ModelRenderer(this, 58, 0);
      MetalSide1.addBox(-1F, -16F, -0.5F, 2, 16, 1);
      MetalSide1.setRotationPoint(13F, 21.5F, -8F);
      MetalSide1.setTextureSize(64, 64);
      MetalSide1.mirror = true;
      setRotation(MetalSide1, -1.570796F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Step1.render(f5);
    Step2.render(f5);
    Step3.render(f5);
    Step4.render(f5);
    Banister1.render(f5);
    Base.render(f5);
    Side1.render(f5);
    Banister2.render(f5);
    MetalSide2.render(f5);
    GlassRight.render(f5);
    SideLeft.render(f5);
    GlassLeft.render(f5);
    MetalSide1.render(f5);
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
