package co.uk.silvania.roads.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;

import co.uk.silvania.roads.Roads;

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

public class RoadBlock extends Block
{

    @SideOnly(Side.CLIENT)
    private Icon sides;
    
    @SideOnly(Side.CLIENT)
    private Icon top;

    public RoadBlock(int par1) {
        super(par1, Material.rock);
        this.setStepSound(soundStoneFootstep);
        this.setHardness(1.5F);
        this.setCreativeTab(CreativeTabs.tabRedstone);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.825F, 1.0F);
    }
    
    public void roadBlockBounds(float par1, float par2, float par3, float par4, float par5, float par6)
    {
        this.setBlockBounds(par1, par2, par3, par4, par5, par6);
    }


    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta) {
        int k = meta;
        if (k == 0 && (side == 1)) {
         	return top;
     	} else if (k == 1 && (side == 1)) {
           	return top;
       	} else if (k == 2 && (side == 1)) {
           	return top;
        } else if (k == 3 && (side == 1)) {
          	return top;
        } else if (k == 4 && (side == 1)) {
           	return top;
        } else if (k == 5 && (side == 1)) {
           	return top;
        }
   		return sides;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.top = par1IconRegister.registerIcon("Roads:TarmacSideDoubleYellowStripe");
        this.sides = par1IconRegister.registerIcon("Roads:TarmacPlain");
    }

    //When this is uncommented and the method below is removed, I just get an empty frame, nothing renders.
    //I think this is because the getRenderType is disabled, but the getRoadsRenderer isn't working for some reason.
    //Therefore, I need to find that reason!
    
    public int getRoadsRenderer() {
        return 0;
    }
    
    public int getRenderType() {
    	return -1;
    }
    /**/
    
    //When it's set like this, it works, but this is the Piston one so I can't modify it to suit my needs. It works like this, but it's not ideal.
    /*public int getRenderType() {
    	return 16;
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



/*package co.uk.silvania.roads.block;

import co.uk.silvania.roads.CommonProxy;
import co.uk.silvania.roads.Roads;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class RoadBlock extends Block {
	
    @SideOnly(Side.CLIENT)
    private Icon top;
    
    @SideOnly(Side.CLIENT)
    private Icon sides;

        public RoadBlock (int id) {
            super(id, Material.rock);
            this.setHardness(1.0F);
    		this.setStepSound(Block.soundStoneFootstep);
    		this.setCreativeTab(Roads.tabRoads);
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.825F, 1.0F);
        }

    	public void registerIcons(IconRegister iconRegister) {
            this. = iconRegister.registerIcon("Roads:TarmacSideDoubleYellowStripe");
            this.sides = iconRegister.registerIcon("Roads:TarmacPlain");
    	}
    	
        public boolean renderAsNormalBlock() {
            return false;
        }
        
        public boolean isOpaqueCube() {
        	return false;
        }
        
        public static int getOrientation(int par0)
        {
            return par0 & 7;
        }
        
        public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving entity, ItemStack item) {
            int l = determineOrientation(par1World, par2, par3, par4, entity);
            par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
        }

        public static int determineOrientation(World world, int par1, int par2, int par3, EntityLiving entity) {
            int l = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
            return l == 0 ? 3 : (l == 1 ? 4 : (l == 2 ? 2 : (l == 3 ? 5 : 0)));
        }
        
        @SideOnly(Side.CLIENT)
        public Icon getIcon(int side, int meta) {
            int k = meta;
            if (k == 0 && (side == 1)) {
             	return top;
         	} else if (k == 1 && (side == 1)) {
               	return top;
           	} else if (k == 2 && (side == 1)) {
               	return top;
            } else if (k == 3 && (side == 1)) {
              	return top;
            } else if (k == 4 && (side == 1)) {
               	return top;
            } else if (k == 5 && (side == 1)) {
               	return top;
            }
       		return sides;
        }
        
        public void roadBlockBounds(float par1, float par2, float par3, float par4, float par5, float par6)
        {
            this.setBlockBounds(par1, par2, par3, par4, par5, par6);
        }

		public int getRoadsRenderer() {
			return 0;
		}
}*/