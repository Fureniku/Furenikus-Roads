package co.uk.silvania.roads.block;

import co.uk.silvania.roads.CommonProxy;
import co.uk.silvania.roads.Roads;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class RBDoubleYellowStripe extends BlockDirectional
{
    public RBDoubleYellowStripe(int par1, int par2)
    {
        super(par1, Material.rock);
        this.blockIndexInTexture = par2;
        this.setCreativeTab(Roads.tabRoads);
        this.setHardness(1.0F);
        this.setStepSound(Block.soundStoneFootstep);
    }
    
    @Override
    public String getTextureFile () {
            return CommonProxy.BLOCK_PNG;
    }
    
    @SideOnly(Side.CLIENT)
    public int getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
        int var3 = getOrientation(par2);
        return var3 > 5 ? this.blockIndexInTexture : (par1 == var3 ? 
        		(this.blockIndexInTexture + 1 * 1) : (par1 == Facing.faceToSide[var3] ? 1 : 0));
    }

    public int getRenderType()
    {
        return 16;
    }
    
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving)
    {
        int var6 = determineOrientation(par1World, par2, par3, par4, (EntityPlayer)par5EntityLiving);
        par1World.setBlockMetadataWithNotify(par2, par3, par4, var6);
    }

    public static int getOrientation(int par0)
    {
        return par0 & 7;
    }

    public static int determineOrientation(World par0World, int par1, int par2, int par3, EntityPlayer par4EntityPlayer)
    {
        int var7 = MathHelper.floor_double((double)(par4EntityPlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        return var7 == 0 ? 2 : (var7 == 1 ? 5 : (var7 == 2 ? 3 : (var7 == 3 ? 4 : 0)));
    }
}