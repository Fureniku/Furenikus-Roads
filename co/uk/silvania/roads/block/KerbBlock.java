package co.uk.silvania.roads.block;

import co.uk.silvania.roads.Roads;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class KerbBlock extends Block {

	public KerbBlock(int id) {
		super(id, Material.rock);
		this.setCreativeTab(Roads.tabRoads);
		this.setHardness(1.2F);
	}
	
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        int l = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
    }
    
    public boolean renderAsNormalBlock() {
    	return false;
    }
    
    public boolean isOpaqueCube() {
    	return false;
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
    }	
	
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        int l = par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 7;

        switch (l)
        {
            case 0:
            default:
                this.setBlockBounds(0.0F, -0.25F, 0.15F, 1.0F, -0.125F, 0.4F);
                break;
            case 1:
                this.setBlockBounds(0.6F, -0.25F, 0.0F, 0.85F, -0.125F, 1.0F);
                break;
            case 2:
                this.setBlockBounds(0.0F, -0.25F, 0.6F, 1.0F, -0.125F, 0.85F);
                break;
            case 3:
                this.setBlockBounds(0.15F, -0.25F, 0.0F, 0.4F, -0.125F, 1.0F);
        }
    }
    
    public void registerIcons(IconRegister iconRegister) {
    	blockIcon = iconRegister.registerIcon("Roads:sidewalkBlockLight0");
    }
}
