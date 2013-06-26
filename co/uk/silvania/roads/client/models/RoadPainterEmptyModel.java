package co.uk.silvania.roads.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class RoadPainterEmptyModel extends ModelBase
{
  //fields
    ModelRenderer Base;
    ModelRenderer BackLeft;
    ModelRenderer Cover;
    ModelRenderer BackCenter;
    ModelRenderer PainterTop;
    ModelRenderer PainterMid;
    ModelRenderer PainterCenter;
    ModelRenderer Tank;
    ModelRenderer Monitor;
    ModelRenderer BackRight;
  
  public RoadPainterEmptyModel()
  {
    textureWidth = 128;
    textureHeight = 64;
    
      Base = new ModelRenderer(this, 0, 47);
      Base.addBox(0F, 0F, 0F, 16, 1, 16);
      Base.setRotationPoint(-8F, 23F, -8F);
      Base.setTextureSize(128, 64);
      Base.mirror = true;
      setRotation(Base, 0F, 0F, 0F);
      BackLeft = new ModelRenderer(this, 0, 0);
      BackLeft.addBox(0F, 0F, 0F, 2, 21, 2);
      BackLeft.setRotationPoint(-7F, 3F, 8F);
      BackLeft.setTextureSize(128, 64);
      BackLeft.mirror = true;
      setRotation(BackLeft, 0F, 0F, 0F);
      Cover = new ModelRenderer(this, 0, 32);
      Cover.addBox(0F, 0F, 0F, 14, 1, 14);
      Cover.setRotationPoint(-7F, 2F, -4F);
      Cover.setTextureSize(128, 64);
      Cover.mirror = true;
      setRotation(Cover, 0F, 0F, 0F);
      BackCenter = new ModelRenderer(this, 16, 0);
      BackCenter.addBox(0F, 0F, 0F, 2, 21, 1);
      BackCenter.setRotationPoint(-1F, 3F, 9F);
      BackCenter.setTextureSize(128, 64);
      BackCenter.mirror = true;
      setRotation(BackCenter, 0F, 0F, 0F);
      PainterTop = new ModelRenderer(this, 22, 0);
      PainterTop.addBox(0F, 0F, 0F, 5, 1, 5);
      PainterTop.setRotationPoint(-2.5F, 3F, -0.5F);
      PainterTop.setTextureSize(128, 64);
      PainterTop.mirror = true;
      setRotation(PainterTop, 0F, 0F, 0F);
      PainterMid = new ModelRenderer(this, 22, 6);
      PainterMid.addBox(0F, 0F, 0F, 3, 1, 3);
      PainterMid.setRotationPoint(-1.5F, 4F, 0.5F);
      PainterMid.setTextureSize(128, 64);
      PainterMid.mirror = true;
      setRotation(PainterMid, 0F, 0F, 0F);
      PainterCenter = new ModelRenderer(this, 22, 10);
      PainterCenter.addBox(-0.5F, 0F, 1.5F, 1, 3, 1);
      PainterCenter.setRotationPoint(0F, 5F, 0F);
      PainterCenter.setTextureSize(128, 64);
      PainterCenter.mirror = true;
      setRotation(PainterCenter, 0F, 0F, 0F);
      Tank = new ModelRenderer(this, 22, 14);
      Tank.addBox(0F, 0F, 0F, 8, 4, 8);
      Tank.setRotationPoint(-4F, -2F, -2F);
      Tank.setTextureSize(128, 64);
      Tank.mirror = true;
      setRotation(Tank, 0F, 0F, 0F);
      Monitor = new ModelRenderer(this, 27, 6);
      Monitor.addBox(0F, 0F, 0F, 7, 1, 7);
      Monitor.setRotationPoint(-3.5F, -2.3F, -1.5F);
      Monitor.setTextureSize(128, 64);
      Monitor.mirror = true;
      setRotation(Monitor, 0F, 0F, 0F);
      BackRight = new ModelRenderer(this, 0, 0);
      BackRight.addBox(0F, 0F, 0F, 2, 21, 2);
      BackRight.setRotationPoint(5F, 3F, 8F);
      BackRight.setTextureSize(128, 64);
      BackRight.mirror = true;
      setRotation(BackRight, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Base.render(f5);
    BackLeft.render(f5);
    Cover.render(f5);
    BackCenter.render(f5);
    PainterTop.render(f5);
    PainterMid.render(f5);
    PainterCenter.render(f5);
    Tank.render(f5);
    Monitor.render(f5);
    BackRight.render(f5);
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
