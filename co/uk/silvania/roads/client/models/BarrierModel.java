package co.uk.silvania.roads.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class BarrierModel extends ModelBase
{
  //fields
    ModelRenderer BottomMid;
    ModelRenderer Mid;
    ModelRenderer Bottom;
    ModelRenderer TopMid;
    ModelRenderer Top;
  
  public BarrierModel()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      BottomMid = new ModelRenderer(this, 0, 9);
      BottomMid.addBox(0F, 0F, 0F, 16, 2, 1);
      BottomMid.setRotationPoint(-8F, 17.3F, -6.75F);
      BottomMid.setTextureSize(64, 32);
      BottomMid.mirror = true;
      setRotation(BottomMid, -0.7853982F, 0F, 0F);
      Mid = new ModelRenderer(this, 0, 5);
      Mid.addBox(0F, 0F, 0F, 16, 3, 1);
      Mid.setRotationPoint(-8F, 15F, -7F);
      Mid.setTextureSize(64, 32);
      Mid.mirror = true;
      setRotation(Mid, 0F, 0F, 0F);
      Bottom = new ModelRenderer(this, 0, 12);
      Bottom.addBox(0F, 0F, 0F, 16, 1, 1);
      Bottom.setRotationPoint(-8F, 18.71F, -6.75F);
      Bottom.setTextureSize(64, 32);
      Bottom.mirror = true;
      setRotation(Bottom, -0.7853982F, 0F, 0F);
      TopMid = new ModelRenderer(this, 0, 2);
      TopMid.addBox(0F, 0F, 0F, 16, 2, 1);
      TopMid.setRotationPoint(-8F, 14.3F, -8.1F);
      TopMid.setTextureSize(64, 32);
      TopMid.mirror = true;
      setRotation(TopMid, 0.7853982F, 0F, 0F);
      Top = new ModelRenderer(this, 0, 0);
      Top.addBox(0F, 0F, 0F, 16, 1, 1);
      Top.setRotationPoint(-8F, 13.6F, -7.4F);
      Top.setTextureSize(64, 32);
      Top.mirror = true;
      setRotation(Top, 0.7853982F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    BottomMid.render(f5);
    Mid.render(f5);
    Bottom.render(f5);
    TopMid.render(f5);
    Top.render(f5);
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
