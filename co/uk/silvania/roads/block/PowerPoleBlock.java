package co.uk.silvania.roads.block;

import java.util.List;
import java.util.Random;

import co.uk.silvania.roads.Roads;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class PowerPoleBlock extends BlockRedstoneWire {

	private Material material;
	
	public PowerPoleBlock(int id) {
		super(id);
		this.setCreativeTab(Roads.tabRoads);
        //this.setBlockBounds(0.4F, 0.0F, 0.4F, 0.6F, 1.0F, 0.6F);
	}
	
    public void registerIcons(IconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon("Roads:CementBlock");
    }
    
    @Override
    public int getRenderType()
    {
        return 0;
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return AxisAlignedBB.getAABBPool().getAABB((double)par2 + this.minX, (double)par3 + this.minY, (double)par4 + this.minZ, (double)par2 + this.maxX, (double)par3 + this.maxY, (double)par4 + this.maxZ);
    }
    
    @Override
    public void addCollisionBoxesToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity) {
    	this.setBlockBounds(0.4F, 0.0F, 0.4F, 0.6F, 1.0F, 0.6F);
    	super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
    }
    @Override
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
		return true;
    }
    
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Roads.powerPole.blockID;
    }
    
    public static boolean isPowerProviderOrWire(IBlockAccess par0IBlockAccess, int par1, int par2, int par3, int par4)
    {
        int i1 = par0IBlockAccess.getBlockId(par1, par2, par3);

        if (i1 == Roads.powerPole.blockID)
        {
            return true;
        }
        else if (i1 == 0)
        {
            return false;
        }
        else
        {
            int j1 = par0IBlockAccess.getBlockMetadata(par1, par2, par3);
            return par4 == (j1 & 3) || par4 == Direction.rotateOpposite[j1 & 3];
        }
    }

    
    @Override
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
    	//Remove here to end of constructor - Only kept in for testing to see when the current is active.
        int l = par1World.getBlockMetadata(par2, par3, par4);

        if (l > 0)
        {
            double d0 = (double)par2 + 0.5D + ((double)par5Random.nextFloat() - 0.5D) * 0.2D;
            double d1 = (double)((float)par3 + 0.0625F);
            double d2 = (double)par4 + 0.5D + ((double)par5Random.nextFloat() - 0.5D) * 0.2D;
            float f = (float)l / 15.0F;
            float f1 = f * 0.6F + 0.4F;

            if (l == 0)
            {
                f1 = 0.0F;
            }

            float f2 = f * f * 0.7F - 0.5F;
            float f3 = f * f * 0.6F - 0.7F;

            if (f2 < 0.0F)
            {
                f2 = 0.0F;
            }

            if (f3 < 0.0F)
            {
                f3 = 0.0F;
            }

            par1World.spawnParticle("reddust", d0, d1, d2, (double)f1, (double)f2, (double)f3);
        }
    }
    
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return Roads.powerPole.blockID;
    }

}
