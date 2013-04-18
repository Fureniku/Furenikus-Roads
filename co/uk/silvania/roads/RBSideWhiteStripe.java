package co.uk.silvania.roads;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class RBSideWhiteStripe extends BlockDirectional
{
    /** Boolean used to seperate different states of blocks */
    private boolean blockType;

    protected RBSideWhiteStripe(int par1, int par2)
    {
        super(par1, Material.pumpkin);
        this.blockIndexInTexture = par2;
        this.setTickRandomly(true);
        //this.blockType = par3;
        this.setCreativeTab(Roads.tabRoads);
    }
    
    @Override
    public String getTextureFile () {
            return CommonProxy.BLOCK_PNG;
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public int getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
        if (par1 == 1)
        {
            return this.blockIndexInTexture + 14;
        }
        else if (par1 == 0)
        {
            return this.blockIndexInTexture + 1;
        }
        else
        {
            int var3 = this.blockIndexInTexture + 1;

            if (this.blockType)
            {
                ++var3;
            }

            return par2 == 2 && par1 == 2 ? var3 : (par2 == 3 && par1 == 5 ? var3 : (par2 == 0 && par1 == 3 ? var3 : (par2 == 1 && par1 == 4 ? var3 : this.blockIndexInTexture + 16)));
        }
    }

    /**
     * Returns the block texture based on the side being looked at.  Args: side
     */
    public int getBlockTextureFromSide(int par1)
    {
        return par1 == 1 ? this.blockIndexInTexture : (par1 == 0 ? this.blockIndexInTexture : (par1 == 3 ? this.blockIndexInTexture + 1 + 16 : this.blockIndexInTexture + 16));
    }


    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving)
    {
        int var6 = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        par1World.setBlockMetadataWithNotify(par2, par3, par4, var6);
    }
}
