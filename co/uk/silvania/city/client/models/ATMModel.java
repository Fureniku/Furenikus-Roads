package co.uk.silvania.city.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ATMModel extends ModelBase
{
  //fields
    ModelRenderer Base;
    ModelRenderer FrameBottom;
    ModelRenderer Top;
    ModelRenderer Right;
    ModelRenderer Left;
    ModelRenderer FrameTop;
    ModelRenderer FrameLeft;
    ModelRenderer FrameRight;
    ModelRenderer Keypad;
    ModelRenderer Panel;
    ModelRenderer Shape6;
    ModelRenderer CashBackPanel;
    ModelRenderer CashSlot;
    ModelRenderer Screen;
    ModelRenderer Panel2;
    ModelRenderer Panel3;
    ModelRenderer Shape1;
  
  public ATMModel()
  {
    textureWidth = 128;
    textureHeight = 64;
    
      Base = new ModelRenderer(this, 64, 18);
      Base.addBox(0F, 0F, 0F, 16, 2, 16);
      Base.setRotationPoint(-8F, 22F, -8F);
      Base.setTextureSize(128, 64);
      Base.mirror = true;
      setRotation(Base, 0F, 0F, 0F);
      FrameBottom = new ModelRenderer(this, 0, 61);
      FrameBottom.addBox(0F, 0F, 0F, 14, 2, 1);
      FrameBottom.setRotationPoint(-7F, 21F, -8.5F);
      FrameBottom.setTextureSize(128, 64);
      FrameBottom.mirror = true;
      setRotation(FrameBottom, 0F, 0F, 0F);
      Top = new ModelRenderer(this, 64, 0);
      Top.addBox(0F, 0F, 0F, 16, 2, 16);
      Top.setRotationPoint(-8F, 8F, -8F);
      Top.setTextureSize(128, 64);
      Top.mirror = true;
      setRotation(Top, 0F, 0F, 0F);
      Right = new ModelRenderer(this, 92, 36);
      Right.addBox(0F, 0F, 0F, 2, 12, 16);
      Right.setRotationPoint(6F, 10F, -8F);
      Right.setTextureSize(128, 64);
      Right.mirror = true;
      setRotation(Right, 0F, 0F, 0F);
      Left = new ModelRenderer(this, 56, 36);
      Left.addBox(0F, 0F, 0F, 2, 12, 16);
      Left.setRotationPoint(-8F, 10F, -8F);
      Left.setTextureSize(128, 64);
      Left.mirror = true;
      setRotation(Left, 0F, 0F, 0F);
      FrameTop = new ModelRenderer(this, 0, 58);
      FrameTop.addBox(0F, 0F, 0F, 14, 2, 1);
      FrameTop.setRotationPoint(-7F, 9F, -8.5F);
      FrameTop.setTextureSize(128, 64);
      FrameTop.mirror = true;
      setRotation(FrameTop, 0F, 0F, 0F);
      FrameLeft = new ModelRenderer(this, 0, 40);
      FrameLeft.addBox(0F, 0F, 0F, 2, 10, 8);
      FrameLeft.setRotationPoint(-7F, 11F, -8.5F);
      FrameLeft.setTextureSize(128, 64);
      FrameLeft.mirror = true;
      setRotation(FrameLeft, 0F, 0F, 0F);
      FrameRight = new ModelRenderer(this, 36, 46);
      FrameRight.addBox(0F, 0F, 0F, 2, 10, 8);
      FrameRight.setRotationPoint(5F, 11F, -8.5F);
      FrameRight.setTextureSize(128, 64);
      FrameRight.mirror = true;
      setRotation(FrameRight, 0F, 0F, 0F);
      Keypad = new ModelRenderer(this, 0, 35);
      Keypad.addBox(-7F, 0F, 0F, 13, 4, 1);
      Keypad.setRotationPoint(0.5F, 20F, -4.5F);
      Keypad.setTextureSize(128, 64);
      Keypad.mirror = true;
      setRotation(Keypad, -1.308997F, 0F, 0F);
      Panel = new ModelRenderer(this, 0, 3);
      Panel.addBox(0F, 0F, 0F, 6, 10, 1);
      Panel.setRotationPoint(0F, 11.5F, -3.2F);
      Panel.setTextureSize(128, 64);
      Panel.mirror = true;
      setRotation(Panel, -0.2617994F, 0F, 0F);
      Shape6 = new ModelRenderer(this, 0, 26);
      Shape6.addBox(-7F, 0F, 0F, 13, 8, 1);
      Shape6.setRotationPoint(0.8F, 11F, -8.4F);
      Shape6.setTextureSize(128, 64);
      Shape6.mirror = true;
      setRotation(Shape6, 1.22173F, 0F, 0F);
      CashBackPanel = new ModelRenderer(this, 20, 53);
      CashBackPanel.addBox(0F, 0F, 0F, 5, 2, 1);
      CashBackPanel.setRotationPoint(-5F, 18F, -4.5F);
      CashBackPanel.setTextureSize(128, 64);
      CashBackPanel.mirror = true;
      setRotation(CashBackPanel, 0F, 0F, 0F);
      CashSlot = new ModelRenderer(this, 20, 56);
      CashSlot.addBox(0F, 0F, 0F, 4, 1, 1);
      CashSlot.setRotationPoint(-4.5F, 18.5F, -4.8F);
      CashSlot.setTextureSize(128, 64);
      CashSlot.mirror = true;
      setRotation(CashSlot, 0F, 0F, 0F);
      Screen = new ModelRenderer(this, 0, 19);
      Screen.addBox(0F, 0F, 0F, 5, 6, 1);
      Screen.setRotationPoint(-5F, 12.3F, -3F);
      Screen.setTextureSize(128, 64);
      Screen.mirror = true;
      setRotation(Screen, -0.2617994F, 0F, 0F);
      Panel2 = new ModelRenderer(this, 0, 0);
      Panel2.addBox(0F, 0F, 0F, 6, 2, 1);
      Panel2.setRotationPoint(-6F, 11.5F, -3.2F);
      Panel2.setTextureSize(128, 64);
      Panel2.mirror = true;
      setRotation(Panel2, -0.2617994F, 0F, 0F);
      Panel3 = new ModelRenderer(this, 20, 40);
      Panel3.addBox(0F, 0F, 0F, 1, 10, 1);
      Panel3.setRotationPoint(-5.7F, 11.5F, -3.2F);
      Panel3.setTextureSize(128, 64);
      Panel3.mirror = true;
      setRotation(Panel3, -0.2617994F, 0F, 0F);
      Shape1 = new ModelRenderer(this, 14, 0);
      Shape1.addBox(0F, 0F, 0F, 12, 12, 1);
      Shape1.setRotationPoint(-6F, 10F, 7F);
      Shape1.setTextureSize(128, 64);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Base.render(f5);
    FrameBottom.render(f5);
    Top.render(f5);
    Right.render(f5);
    Left.render(f5);
    FrameTop.render(f5);
    FrameLeft.render(f5);
    FrameRight.render(f5);
    Keypad.render(f5);
    Panel.render(f5);
    Shape6.render(f5);
    CashBackPanel.render(f5);
    CashSlot.render(f5);
    Screen.render(f5);
    Panel2.render(f5);
    Panel3.render(f5);
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
