package co.uk.silvania.roads.block;

import co.uk.silvania.roads.CommonProxy;
import co.uk.silvania.roads.Roads;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.src.FMLRenderAccessLibrary;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class RoadBlockRenderer implements ISimpleBlockRenderingHandler {
	
    public int uvRotateEast;
    public int uvRotateWest;
    public int uvRotateSouth;
    public int uvRotateNorth;
    public int uvRotateTop;
    public int uvRotateBottom;
    
    public IBlockAccess blockAccess;
    
    public boolean lockBlockBounds;
    public boolean partialRenderBounds;
    
    public double renderMinX;
    public double renderMaxX;
    public double renderMinY;
    public double renderMaxY;
    public double renderMinZ;
    public double renderMaxZ;
    
    public boolean renderStandardBlock(Block par1Block, int par2, int par3, int par4)
    {
        int l = par1Block.colorMultiplier(this.blockAccess, par2, par3, par4);
        float f = (float)(l >> 16 & 255) / 255.0F;
        float f1 = (float)(l >> 8 & 255) / 255.0F;
        float f2 = (float)(l & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable)
        {
            float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
            float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
            float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
            f = f3;
            f1 = f4;
            f2 = f5;
        }
		return lockBlockBounds;

        //return Minecraft.isAmbientOcclusionEnabled() && Block.lightValue[par1Block.blockID] == 0 ? (this.partialRenderBounds ? RenderBlocks.func_102027_b(par1Block, par2, par3, par4, f, f1, f2) : this.renderStandardBlockWithAmbientOcclusion(par1Block, par2, par3, par4, f, f1, f2)) : this.renderStandardBlockWithColorMultiplier(par1Block, par2, par3, par4, f, f1, f2);
    }
    
    public void setRenderBoundsFromBlock(Block par1Block)
    {
        if (!this.lockBlockBounds)
        {
            this.renderMinX = par1Block.getBlockBoundsMinX();
            this.renderMaxX = par1Block.getBlockBoundsMaxX();
            this.renderMinY = par1Block.getBlockBoundsMinY();
            this.renderMaxY = par1Block.getBlockBoundsMaxY();
            this.renderMinZ = par1Block.getBlockBoundsMinZ();
            this.renderMaxZ = par1Block.getBlockBoundsMaxZ();
            this.partialRenderBounds = (this.renderMinX > 0.0D || this.renderMaxX < 1.0D || this.renderMinY > 0.0D || this.renderMaxY < 1.0D || this.renderMinZ > 0.0D || this.renderMaxZ < 1.0D);
        }
    }
    
    
    public boolean renderBlockByRenderType(RoadBlock par1Block, int par2, int par3, int par4)
    {
        int l = par1Block.getRoadsRenderer();

        if (l == -1)
        {
            return false;
        }
        else
        {
            par1Block.setBlockBoundsBasedOnState(this.blockAccess, par2, par3, par4);
            this.setRenderBoundsFromBlock(par1Block);

            switch (l)
            {
                case 0: return this.renderRoadBlock(par1Block, par2, par3, par4, false);
                default: return this.renderRoadBlock(par1Block, par2, par3, par4, false);
            }
        }
    }
    
    
    
    public void setRenderBounds(double par1, double par3, double par5, double par7, double par9, double par11)
    {
        if (!this.lockBlockBounds)
        {
            this.renderMinX = par1;
            this.renderMaxX = par7;
            this.renderMinY = par3;
            this.renderMaxY = par9;
            this.renderMinZ = par5;
            this.renderMaxZ = par11;
            this.partialRenderBounds = (this.renderMinX > 0.0D || this.renderMaxX < 1.0D || this.renderMinY > 0.0D || this.renderMaxY < 1.0D || this.renderMinZ > 0.0D || this.renderMaxZ < 1.0D);
        }
    }
    
    
    public boolean renderRoadBlock(Block par1Block, int par2, int par3, int par4, boolean par5)
    {
        int l = this.blockAccess.getBlockMetadata(par2, par3, par4);
        boolean flag1 = par5 || (l & 8) != 0;
        int i1 = RoadBlock.getOrientation(l);
        float f = 0.25F;

        if (flag1)
        {
            switch (i1)
            {
                case 0:
                    this.uvRotateEast = 3;
                    this.uvRotateWest = 3;
                    this.uvRotateSouth = 3;
                    this.uvRotateNorth = 3;
                    this.setRenderBounds(0.0D, 0.25D, 0.0D, 1.0D, 1.0D, 1.0D);
                    break;
                case 1:
                    this.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);
                    break;
                case 2:
                    this.uvRotateSouth = 1;
                    this.uvRotateNorth = 2;
                    this.setRenderBounds(0.0D, 0.0D, 0.25D, 1.0D, 1.0D, 1.0D);
                    break;
                case 3:
                    this.uvRotateSouth = 2;
                    this.uvRotateNorth = 1;
                    this.uvRotateTop = 3;
                    this.uvRotateBottom = 3;
                    this.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.75D);
                    break;
                case 4:
                    this.uvRotateEast = 1;
                    this.uvRotateWest = 2;
                    this.uvRotateTop = 2;
                    this.uvRotateBottom = 1;
                    this.setRenderBounds(0.25D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
                    break;
                case 5:
                    this.uvRotateEast = 2;
                    this.uvRotateWest = 1;
                    this.uvRotateTop = 1;
                    this.uvRotateBottom = 2;
                    this.setRenderBounds(0.0D, 0.0D, 0.0D, 0.75D, 1.0D, 1.0D);
            }

            ((RoadBlock)par1Block).roadBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.825F, 1.0F);
            this.renderStandardBlock(par1Block, par2, par3, par4);
            this.uvRotateEast = 0;
            this.uvRotateWest = 0;
            this.uvRotateSouth = 0;
            this.uvRotateNorth = 0;
            this.uvRotateTop = 0;
            this.uvRotateBottom = 0;
            this.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
            ((RoadBlock)par1Block).roadBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.825F, 1.0F);
        }
        else
        {
            switch (i1)
            {
                case 0:
                    this.uvRotateEast = 3;
                    this.uvRotateWest = 3;
                    this.uvRotateSouth = 3;
                    this.uvRotateNorth = 3;
                case 1:
                default:
                    break;
                case 2:
                    this.uvRotateSouth = 1;
                    this.uvRotateNorth = 2;
                    break;
                case 3:
                    this.uvRotateSouth = 2;
                    this.uvRotateNorth = 1;
                    this.uvRotateTop = 3;
                    this.uvRotateBottom = 3;
                    break;
                case 4:
                    this.uvRotateEast = 1;
                    this.uvRotateWest = 2;
                    this.uvRotateTop = 2;
                    this.uvRotateBottom = 1;
                    break;
                case 5:
                    this.uvRotateEast = 2;
                    this.uvRotateWest = 1;
                    this.uvRotateTop = 1;
                    this.uvRotateBottom = 2;
            }

            this.renderStandardBlock(par1Block, par2, par3, par4);
            this.uvRotateEast = 0;
            this.uvRotateWest = 0;
            this.uvRotateSouth = 0;
            this.uvRotateNorth = 0;
            this.uvRotateTop = 0;
            this.uvRotateBottom = 0;
        }

        return true;
    }

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	@Override
	public int getRenderId() {
		return RenderingRegistry.getNextAvailableRenderId();
	}
   
}
/**/