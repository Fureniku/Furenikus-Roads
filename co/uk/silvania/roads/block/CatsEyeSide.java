package co.uk.silvania.roads.block;

import co.uk.silvania.roads.Roads;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CatsEyeSide extends Block {

	public CatsEyeSide(int id) {
		super(id, Material.glass);
		this.setStepSound(Block.soundStoneFootstep);
		this.setCreativeTab(Roads.tabRoads);
        this.setLightValue(1.0F);
	}
	
    public boolean renderAsNormalBlock() {
        return false;
    }
    
    public boolean isOpaqueCube() {
    	return false;
    }
    
    public void registerIcons(IconRegister iconRegister) {
    	blockIcon = iconRegister.registerIcon("Roads:CatsEye");
    }
    
    /*
     *         public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
                int metadata = world.getBlockMetadata(x, y, z);
                float f = 0.44F;
                if ((metadata & 1) == 0)
                  setBlockBounds(0.5F - f, 0.0F, 0.0F, 0.5F + f, 0.5F, 1.0F);
                else
                  setBlockBounds(0.0F, 0.0F, 0.5F - f, 1.0F, 0.5F, 0.5F + f);
        }(non-Javadoc)
     * @see net.minecraft.block.Block#onBlockPlacedBy(net.minecraft.world.World, int, int, int, net.minecraft.entity.EntityLiving, net.minecraft.item.ItemStack)
     */
    
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving, ItemStack par6ItemStack)
    {
        int l = determineOrientation(par1World, par2, par3, par4, par5EntityLiving);
        par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
    }
    
    public static int determineOrientation(World par0World, int par1, int par2, int par3, EntityLiving par4EntityLiving)
    {

        int l = MathHelper.floor_double((double)(par4EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        return l == 0 ? 2 : (l == 1 ? 5 : (l == 2 ? 3 : (l == 3 ? 4 : 0)));
    }
    
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        int l = par1IBlockAccess.getBlockMetadata(par2, par3, par4);

        switch (l) {
            case 0:
                this.setBlockBounds(0.9F, -0.175F, 0.4F, 1.1F, -0.125F, 0.6F);
                break;
            case 1:
                this.setBlockBounds(0.4F, -0.175F, 0.9F, 0.6F, -0.125F, 1.1F);
                break;
            case 2:
                this.setBlockBounds(0.9F, -0.175F, 0.9F, 1.1F, -0.125F, 1.1F);
                break;
            case 3:
                this.setBlockBounds(0.4F, -0.175F, 0.4F, 0.6F, -0.125F, 0.6F);
                break;
            /*case 4:
                this.setBlockBounds(0.25F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                break;
            case 5:
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.75F, 1.0F, 1.0F);*/
        }
    }
}
