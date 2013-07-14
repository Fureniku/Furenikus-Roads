package co.uk.silvania.roads.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class TrafficLightModel extends ModelBase
{
  //fields
    ModelRenderer LightBox;
    ModelRenderer Trim;
    ModelRenderer FrontTop;
    ModelRenderer FrontTopRight;
    ModelRenderer FrontTopLeft;
    ModelRenderer FrontMidLeft;
    ModelRenderer FrontMid;
    ModelRenderer FrontBottom;
    ModelRenderer FrontBottomLeft;
    ModelRenderer FrontMidRight;
    ModelRenderer FrontBottomRight;
    ModelRenderer Shape1;
  
  public TrafficLightModel()
  {
    textureWidth = 32;
    textureHeight = 64;
    
      LightBox = new ModelRenderer(this, 12, 1);
      LightBox.addBox(0F, 0F, 0F, 4, 12, 3);
      LightBox.setRotationPoint(-2F, 10.5F, -2F);
      LightBox.setTextureSize(32, 64);
      LightBox.mirror = true;
      setRotation(LightBox, 0F, 0F, 0F);
      Trim = new ModelRenderer(this, 12, 16);
      Trim.addBox(0F, 0F, 0F, 5, 13, 1);
      Trim.setRotationPoint(-2.5F, 10F, -1.5F);
      Trim.setTextureSize(32, 64);
      Trim.mirror = true;
      setRotation(Trim, 0F, 0F, 0F);
      FrontTop = new ModelRenderer(this, 17, 4);
      FrontTop.addBox(0F, 0F, 0F, 1, 0, 2);
      FrontTop.setRotationPoint(-0.5F, 11.3F, -4F);
      FrontTop.setTextureSize(32, 64);
      FrontTop.mirror = true;
      setRotation(FrontTop, 0F, 0F, 0F);
      FrontTopRight = new ModelRenderer(this, 17, 4);
      FrontTopRight.addBox(0F, 0F, 0F, 1, 0, 2);
      FrontTopRight.setRotationPoint(0.5F, 11.3F, -4F);
      FrontTopRight.setTextureSize(32, 64);
      FrontTopRight.mirror = true;
      setRotation(FrontTopRight, 0F, 0F, 0.7853982F);
      FrontTopLeft = new ModelRenderer(this, 17, 4);
      FrontTopLeft.addBox(0F, 0F, 0F, 1, 0, 2);
      FrontTopLeft.setRotationPoint(-1.2F, 12F, -4F);
      FrontTopLeft.setTextureSize(32, 64);
      FrontTopLeft.mirror = true;
      setRotation(FrontTopLeft, 0F, 0F, -0.7853982F);
      FrontMidLeft = new ModelRenderer(this, 17, 4);
      FrontMidLeft.addBox(0F, 0F, 0F, 1, 0, 2);
      FrontMidLeft.setRotationPoint(-1.2F, 16.2F, -4F);
      FrontMidLeft.setTextureSize(32, 64);
      FrontMidLeft.mirror = true;
      setRotation(FrontMidLeft, 0F, 0F, -0.7853982F);
      FrontMid = new ModelRenderer(this, 17, 4);
      FrontMid.addBox(0F, 0F, 0F, 1, 0, 2);
      FrontMid.setRotationPoint(-0.5F, 15.5F, -4F);
      FrontMid.setTextureSize(32, 64);
      FrontMid.mirror = true;
      setRotation(FrontMid, 0F, 0F, 0F);
      FrontBottom = new ModelRenderer(this, 17, 4);
      FrontBottom.addBox(0F, 0F, 0F, 1, 0, 2);
      FrontBottom.setRotationPoint(-0.5F, 19.5F, -4F);
      FrontBottom.setTextureSize(32, 64);
      FrontBottom.mirror = true;
      setRotation(FrontBottom, 0F, 0F, 0F);
      FrontBottomLeft = new ModelRenderer(this, 17, 4);
      FrontBottomLeft.addBox(0F, 0F, 0F, 1, 0, 2);
      FrontBottomLeft.setRotationPoint(-1.2F, 20.2F, -4F);
      FrontBottomLeft.setTextureSize(32, 64);
      FrontBottomLeft.mirror = true;
      setRotation(FrontBottomLeft, 0F, 0F, -0.7853982F);
      FrontMidRight = new ModelRenderer(this, 17, 4);
      FrontMidRight.addBox(0F, 0F, 0F, 1, 0, 2);
      FrontMidRight.setRotationPoint(0.5F, 15.5F, -4F);
      FrontMidRight.setTextureSize(32, 64);
      FrontMidRight.mirror = true;
      setRotation(FrontMidRight, 0F, 0F, 0.7853982F);
      FrontBottomRight = new ModelRenderer(this, 17, 4);
      FrontBottomRight.addBox(0F, 0F, 0F, 1, 0, 2);
      FrontBottomRight.setRotationPoint(0.5F, 19.5F, -4F);
      FrontBottomRight.setTextureSize(32, 64);
      FrontBottomRight.mirror = true;
      setRotation(FrontBottomRight, 0F, 0F, 0.7853982F);
      Shape1 = new ModelRenderer(this, 0, 0);
      Shape1.addBox(0F, 0F, 0F, 1, 3, 1);
      Shape1.setRotationPoint(-0.5F, 21F, -0.5F);
      Shape1.setTextureSize(32, 64);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    LightBox.render(f5);
    Trim.render(f5);
    FrontTop.render(f5);
    FrontTopRight.render(f5);
    FrontTopLeft.render(f5);
    FrontMidLeft.render(f5);
    FrontMid.render(f5);
    FrontBottom.render(f5);
    FrontBottomLeft.render(f5);
    FrontMidRight.render(f5);
    FrontBottomRight.render(f5);
    Shape1.render(f5);
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
