package co.uk.silvania.roads.client.vehicles;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBasicCar extends ModelBase
{
  //fields
    ModelRenderer Front;
    ModelRenderer Windscreen;
    ModelRenderer Wheel_1;
    ModelRenderer Wheel_2;
    ModelRenderer Wheel_3;
    ModelRenderer Wheel_4;
    ModelRenderer Right;
    ModelRenderer Back;
    ModelRenderer Left;
    ModelRenderer Floor;
    ModelRenderer Steering_Wheel;
    ModelRenderer Steering_Column;
    ModelRenderer Chair_Base;
    ModelRenderer Chair_Top;
    ModelRenderer Wiper_Right;
    ModelRenderer Wiper_Left;
  
  public ModelBasicCar()
  {
    textureWidth = 512;
    textureHeight = 512;
    
      Front = new ModelRenderer(this, 0, 0);
      Front.addBox(0F, 0F, 0F, 48, 16, 20);
      Front.setRotationPoint(-24F, 2F, -32F);
      Front.setTextureSize(512, 512);
      Front.mirror = true;
      setRotation(Front, 0F, 0F, 0F);
      Windscreen = new ModelRenderer(this, 0, 53);
      Windscreen.addBox(0F, 0F, 0F, 48, 16, 1);
      Windscreen.setRotationPoint(-24F, -14F, -13F);
      Windscreen.setTextureSize(512, 512);
      Windscreen.mirror = true;
      setRotation(Windscreen, 0F, 0F, 0F);
      Wheel_1 = new ModelRenderer(this, 0, 0);
      Wheel_1.addBox(0F, -4F, -4F, 1, 8, 8);
      Wheel_1.setRotationPoint(-25F, 20F, -21F);
      Wheel_1.setTextureSize(512, 512);
      Wheel_1.mirror = true;
      setRotation(Wheel_1, 0F, 0F, 0F);
      Wheel_2 = new ModelRenderer(this, 0, 0);
      Wheel_2.addBox(-1F, -4F, -4F, 1, 8, 8);
      Wheel_2.setRotationPoint(25F, 20F, -21F);
      Wheel_2.setTextureSize(512, 512);
      Wheel_2.mirror = true;
      setRotation(Wheel_2, 0F, 0F, 0F);
      Wheel_3 = new ModelRenderer(this, 0, 0);
      Wheel_3.addBox(0F, -4F, -4F, 1, 8, 8);
      Wheel_3.setRotationPoint(-25F, 20F, 35F);
      Wheel_3.setTextureSize(512, 512);
      Wheel_3.mirror = true;
      setRotation(Wheel_3, 0F, 0F, 0F);
      Wheel_4 = new ModelRenderer(this, 0, 0);
      Wheel_4.addBox(-1F, -4F, -4F, 1, 8, 8);
      Wheel_4.setRotationPoint(25F, 20F, 35F);
      Wheel_4.setTextureSize(512, 512);
      Wheel_4.mirror = true;
      setRotation(Wheel_4, 0F, 0F, 0F);
      Right = new ModelRenderer(this, 0, 0);
      Right.addBox(0F, 0F, 0F, 1, 16, 35);
      Right.setRotationPoint(-24F, 2F, -12F);
      Right.setTextureSize(512, 512);
      Right.mirror = true;
      setRotation(Right, 0F, 0F, 0F);
      Back = new ModelRenderer(this, 0, 0);
      Back.addBox(0F, 0F, 0F, 48, 16, 20);
      Back.setRotationPoint(-24F, 2F, 23F);
      Back.setTextureSize(512, 512);
      Back.mirror = true;
      setRotation(Back, 0F, 0F, 0F);
      Left = new ModelRenderer(this, 0, 0);
      Left.addBox(0F, 0F, 0F, 1, 16, 35);
      Left.setRotationPoint(23F, 2F, -12F);
      Left.setTextureSize(512, 512);
      Left.mirror = true;
      setRotation(Left, 0F, 0F, 0F);
      Floor = new ModelRenderer(this, 105, 5);
      Floor.addBox(0F, 0F, 0F, 46, 1, 35);
      Floor.setRotationPoint(-23F, 17F, -12F);
      Floor.setTextureSize(512, 512);
      Floor.mirror = true;
      setRotation(Floor, 0F, 0F, 0F);
      Steering_Wheel = new ModelRenderer(this, 75, 40);
      Steering_Wheel.addBox(-4F, -4F, -1F, 8, 8, 1);
      Steering_Wheel.setRotationPoint(14F, 0F, -5.8F);
      Steering_Wheel.setTextureSize(512, 512);
      Steering_Wheel.mirror = true;
      setRotation(Steering_Wheel, 0F, 0F, 0F);
      Steering_Column = new ModelRenderer(this, 97, 43);
      Steering_Column.addBox(-1F, -1F, -8F, 2, 2, 8);
      Steering_Column.setRotationPoint(14F, 0F, -6.5F);
      Steering_Column.setTextureSize(512, 512);
      Steering_Column.mirror = true;
      setRotation(Steering_Column, 0.6108652F, 0F, 0F);
      Chair_Base = new ModelRenderer(this, 0, 80);
      Chair_Base.addBox(0F, 0F, 0F, 12, 2, 14);
      Chair_Base.setRotationPoint(8F, 11F, -2F);
      Chair_Base.setTextureSize(512, 512);
      Chair_Base.mirror = true;
      setRotation(Chair_Base, -0.122173F, 0F, 0F);
      Chair_Top = new ModelRenderer(this, 80, 80);
      Chair_Top.addBox(0F, -6F, 0F, 12, 20, 2);
      Chair_Top.setRotationPoint(8F, 0F, 13F);
      Chair_Top.setTextureSize(512, 512);
      Chair_Top.mirror = true;
      setRotation(Chair_Top, -0.2617994F, 0F, 0F);
      Wiper_Right = new ModelRenderer(this, 0, 100);
      Wiper_Right.addBox(-20F, 0F, 0F, 20, 1, 1);
      Wiper_Right.setRotationPoint(-1F, 1F, -13.5F);
      Wiper_Right.setTextureSize(512, 512);
      Wiper_Right.mirror = true;
      setRotation(Wiper_Right, 0F, 0F, 0F);
      Wiper_Left = new ModelRenderer(this, 0, 97);
      Wiper_Left.addBox(-20F, 0F, 0F, 20, 1, 1);
      Wiper_Left.setRotationPoint(20F, 1F, -13.5F);
      Wiper_Left.setTextureSize(512, 512);
      Wiper_Left.mirror = true;
      setRotation(Wiper_Left, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Front.render(f5);
    Windscreen.render(f5);
    Wheel_1.render(f5);
    Wheel_2.render(f5);
    Wheel_3.render(f5);
    Wheel_4.render(f5);
    Right.render(f5);
    Back.render(f5);
    Left.render(f5);
    Floor.render(f5);
    Steering_Wheel.render(f5);
    Steering_Column.render(f5);
    Chair_Base.render(f5);
    Chair_Top.render(f5);
    Wiper_Right.render(f5);
    Wiper_Left.render(f5);
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
