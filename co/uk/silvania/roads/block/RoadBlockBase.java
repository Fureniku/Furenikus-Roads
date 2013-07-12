package co.uk.silvania.roads.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;

import co.uk.silvania.roads.Roads;
import co.uk.silvania.roads.client.ClientProxy;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityPiston;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class RoadBlockBase extends Block {
    public RoadBlockBase(int par1) {
        super(par1, Material.rock);
        this.setStepSound(soundStoneFootstep);
        this.setHardness(1.5F);
        this.setCreativeTab(Roads.tabRoads);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.825F, 1.0F);
    }
    
    public void roadBlockBounds(float par1, float par2, float par3, float par4, float par5, float par6)
    {
        this.setBlockBounds(par1, par2, par3, par4, par5, par6);
    }
    
    @SideOnly(Side.CLIENT)
    private Icon sides;
    @SideOnly(Side.CLIENT)
    private Icon top1;
    @SideOnly(Side.CLIENT)
    private Icon top2;
    @SideOnly(Side.CLIENT)
    private Icon top3;
    @SideOnly(Side.CLIENT)
    private Icon top4;
    	
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.top1 = par1IconRegister.registerIcon("Roads:" + (this.getUnlocalizedName().substring(5)));
        this.sides = par1IconRegister.registerIcon("Roads:TarmacPlain");
    }
    
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta) {
        int k = meta;
        if (side == 1) {
         	return top1;
        } else {
        	return sides;
        }
    }

    public int getRenderType() {
    	return 1;
    }
    /**/

    public boolean isOpaqueCube()
    {
        return false;
    }


    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        int l = determineOrientation(par1World, par2, par3, par4, par5EntityLivingBase);
        par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
    }

    public void addCollisionBoxesToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity)
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.825F, 1.0F);
        super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public static int getOrientation(int par0)
    {
        return par0 & 7;
    }


    public static int determineOrientation(World par0World, int par1, int par2, int par3, EntityLivingBase par4EntityLivingBase)
    {
        int l = MathHelper.floor_double((double)(par4EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        return l == 0 ? 2 : (l == 1 ? 5 : (l == 2 ? 3 : (l == 3 ? 4 : 0)));
    }
}