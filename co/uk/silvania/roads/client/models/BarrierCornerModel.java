package co.uk.silvania.roads.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class BarrierCornerModel extends ModelBase
{
  //fields
    ModelRenderer Base;
    ModelRenderer Post;
    ModelRenderer BottomMid;
    ModelRenderer Mid;
    ModelRenderer Bottom;
    ModelRenderer TopMid;
    ModelRenderer Top;
    ModelRenderer Mid2;
    ModelRenderer BottomMid2;
    ModelRenderer Bottom2;
    ModelRenderer TopMid2;
    ModelRenderer Top2;
    ModelRenderer Post2;
    ModelRenderer Base2;
  
  public BarrierCornerModel()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Base = new ModelRenderer(this, 0, 14);
      Base.addBox(0F, 0F, 0F, 4, 1, 6);
      Base.setRotationPoint(-3F, 23F, -7F);
      Base.setTextureSize(64, 32);
      Base.mirror = true;
      setRotation(Base, 0F, 0F, 0F);
      Post = new ModelRenderer(this, 34, 0);
      Post.addBox(0F, 0F, 0F, 2, 11, 2);
      Post.setRotationPoint(-2F, 12F, -6F);
      Post.setTextureSize(64, 32);
      Post.mirror = true;
      setRotation(Post, 0F, 0F, 0F);
      BottomMid = new ModelRenderer(this, 0, 9);
      BottomMid.addBox(0F, 0F, 0F, 14, 2, 1);
      BottomMid.setRotationPoint(-8F, 17.3F, -6.75F);
      BottomMid.setTextureSize(64, 32);
      BottomMid.mirror = true;
      setRotation(BottomMid, -0.7853982F, 0F, 0F);
      Mid = new ModelRenderer(this, 0, 5);
      Mid.addBox(0F, 0F, 0F, 14, 3, 1);
      Mid.setRotationPoint(-8F, 15F, -7F);
      Mid.setTextureSize(64, 32);
      Mid.mirror = true;
      setRotation(Mid, 0F, 0F, 0F);
      Bottom = new ModelRenderer(this, 0, 12);
      Bottom.addBox(0F, 0F, 0F, 14, 1, 1);
      Bottom.setRotationPoint(-8F, 18.71F, -6.75F);
      Bottom.setTextureSize(64, 32);
      Bottom.mirror = true;
      setRotation(Bottom, -0.7853982F, 0F, 0F);
      TopMid = new ModelRenderer(this, 0, 2);
      TopMid.addBox(0F, 0F, 0F, 14, 2, 1);
      TopMid.setRotationPoint(-8F, 14.3F, -8.1F);
      TopMid.setTextureSize(64, 32);
      TopMid.mirror = true;
      setRotation(TopMid, 0.7853982F, 0F, 0F);
      Top = new ModelRenderer(this, 0, 0);
      Top.addBox(0F, 0F, 0F, 14, 1, 1);
      Top.setRotationPoint(-8F, 13.6F, -7.4F);
      Top.setTextureSize(64, 32);
      Top.mirror = true;
      setRotation(Top, 0.7853982F, 0F, 0F);
      Mid2 = new ModelRenderer(this, 0, 5);
      Mid2.addBox(0F, 0F, 0F, 14, 3, 1);
      Mid2.setRotationPoint(7F, 15F, -6F);
      Mid2.setTextureSize(64, 32);
      Mid2.mirror = true;
      setRotation(Mid2, 0F, -1.570796F, 0F);
      BottomMid2 = new ModelRenderer(this, 0, 9);
      BottomMid2.addBox(0F, 0F, 0F, 14, 2, 1);
      BottomMid2.setRotationPoint(6.7F, 17.3F, -6F);
      BottomMid2.setTextureSize(64, 32);
      BottomMid2.mirror = true;
      setRotation(BottomMid2, -0.7853982F, -1.570796F, 0F);
      Bottom2 = new ModelRenderer(this, 0, 12);
      Bottom2.addBox(0F, 0F, 0F, 14, 1, 1);
      Bottom2.setRotationPoint(6.7F, 18.7F, -6F);
      Bottom2.setTextureSize(64, 32);
      Bottom2.mirror = true;
      setRotation(Bottom2, -0.7853982F, -1.570796F, 0F);
      TopMid2 = new ModelRenderer(this, 0, 2);
      TopMid2.addBox(0F, 0F, 0F, 14, 2, 1);
      TopMid2.setRotationPoint(8.1F, 14.3F, -6F);
      TopMid2.setTextureSize(64, 32);
      TopMid2.mirror = true;
      setRotation(TopMid2, 0.7853982F, -1.570796F, 0F);
      Top2 = new ModelRenderer(this, 0, 0);
      Top2.addBox(0F, 0F, 0F, 14, 1, 1);
      Top2.setRotationPoint(7.4F, 13.6F, -6F);
      Top2.setTextureSize(64, 32);
      Top2.mirror = true;
      setRotation(Top2, 0.7853982F, -1.570796F, 0F);
      Post2 = new ModelRenderer(this, 34, 0);
      Post2.addBox(0F, 0F, 0F, 2, 11, 2);
      Post2.setRotationPoint(4F, 12F, 0F);
      Post2.setTextureSize(64, 32);
      Post2.mirror = true;
      setRotation(Post2, 0F, 0F, 0F);
      Base2 = new ModelRenderer(this, 0, 14);
      Base2.addBox(0F, 0F, 0F, 4, 1, 6);
      Base2.setRotationPoint(7F, 23F, -1F);
      Base2.setTextureSize(64, 32);
      Base2.mirror = true;
      setRotation(Base2, 0F, -1.570796F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Base.render(f5);
    Post.render(f5);
    BottomMid.render(f5);
    Mid.render(f5);
    Bottom.render(f5);
    TopMid.render(f5);
    Top.render(f5);
    Mid2.render(f5);
    BottomMid2.render(f5);
    Bottom2.render(f5);
    TopMid2.render(f5);
    Top2.render(f5);
    Post2.render(f5);
    Base2.render(f5);
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
