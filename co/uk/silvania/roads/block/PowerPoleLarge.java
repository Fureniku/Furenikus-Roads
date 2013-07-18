package co.uk.silvania.roads.block;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import co.uk.silvania.roads.Roads;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Direction;
import net.minecraft.util.ReportedException;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class PowerPoleLarge extends Block {

	private Material material;
	
	private boolean wiresProvidePower = true;
    private Set blocksNeedingUpdate = new HashSet();
	
	public PowerPoleLarge(int id) {
		super(id, Material.iron);
		this.setCreativeTab(Roads.tabRoads);
        this.setBlockBounds(0.425F, 0.0F, 0.425F, 0.575F, 1.0F, 0.575F);
	}
	
    public int getRenderType() {
    	return 0;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }
    
	public boolean renderAsNormalBlock() {
		return false;
	}
	
    public void registerIcons(IconRegister iconRegister) {
    	blockIcon = iconRegister.registerIcon("Roads:PowerPole");
    }
}
	
	/*public void onBlockPlaced(Block block, World world, int x, int y, int z) {
		if block.set
		
	}
	
    public int isBlockProvidingPowerTo(int par1, int par2, int par3, int par4)
    {
        int i1 = this.getBlockId(par1, par2, par3);
        return i1 == 0 ? 0 : Block.blocksList[i1].isProvidingStrongPower(this, par1, par2, par3, par4);
    }
	
	private int getBlockId(int par1, int par2, int par3) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getBlockPowerInput(int par1, int par2, int par3)
    {
        byte b0 = 0;
        int l = Math.max(b0, this.isBlockProvidingPowerTo(par1, par2 - 1, par3, 0));

        if (l >= 15)
        {
            return l;
        }
        else
        {
            l = Math.max(l, this.isBlockProvidingPowerTo(par1, par2 + 1, par3, 1));

            if (l >= 15)
            {
                return l;
            }
            else
            {
                l = Math.max(l, this.isBlockProvidingPowerTo(par1, par2, par3 - 1, 2));

                if (l >= 15)
                {
                    return l;
                }
                else
                {
                    l = Math.max(l, this.isBlockProvidingPowerTo(par1, par2, par3 + 1, 3));

                    if (l >= 15)
                    {
                        return l;
                    }
                    else
                    {
                        l = Math.max(l, this.isBlockProvidingPowerTo(par1 - 1, par2, par3, 4));

                        if (l >= 15)
                        {
                            return l;
                        }
                        else
                        {
                            l = Math.max(l, this.isBlockProvidingPowerTo(par1 + 1, par2, par3, 5));
                            return l >= 15 ? l : l;
                        }
                    }
                }
            }
        }
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
    
    private void notifyWireNeighborsOfNeighborChange(World par1World, int par2, int par3, int par4)
    {
        if (par1World.getBlockId(par2, par3, par4) == this.blockID)
        {
            par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2 - 1, par3, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2 + 1, par3, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2, par3, par4 - 1, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2, par3, par4 + 1, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2, par3 + 1, par4, this.blockID);
        }
    }
    
    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);

        if (!par1World.isRemote)
        {
            this.updateAndPropagateCurrentStrength(par1World, par2, par3, par4);
            this.notifyWireNeighborsOfNeighborChange(par1World, par2 + 1, par3, par4);
            this.notifyWireNeighborsOfNeighborChange(par1World, par2 - 1, par3, par4);

        }
    }
    
    @Override
    public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return !this.wiresProvidePower ? 0 : this.isProvidingWeakPower(par1IBlockAccess, par2, par3, par4, par5);
    }
    
    private void updateAndPropagateCurrentStrength(World par1World, int par2, int par3, int par4)
    {
        this.calculateCurrentChanges(par1World, par2, par3, par4, par2, par3, par4);
        ArrayList arraylist = new ArrayList(this.blocksNeedingUpdate);
        this.blocksNeedingUpdate.clear();

        for (int l = 0; l < arraylist.size(); ++l)
        {
            ChunkPosition chunkposition = (ChunkPosition)arraylist.get(l);
            par1World.notifyBlocksOfNeighborChange(chunkposition.x, chunkposition.y, chunkposition.z, this.blockID);
        }
    }
    
    @Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        if (!par1World.isRemote)
        {
        	this.updateAndPropagateCurrentStrength(par1World, par2, par3, par4);

            super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
        }
    }
    
    @Override
    public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int x, int y, int z, int side)
    {
    	if (!this.wiresProvidePower)
        {
            return 0;
        }
        else
        {
        	int i1 = par1IBlockAccess.getBlockMetadata(x, y, z);
            if (i1 == 0)
            {
                return 0;
            }
            else
            {
            	boolean flag = isPoweredOrRepeater(par1IBlockAccess, x, y-1, z, -1);
          		boolean flag2 = par1IBlockAccess.isBlockNormalCube(x, y - 1, z);
                if(!flag)
                	return i1;
                else
                	return 0;
            }
        }
    }
    
    private void calculateCurrentChanges(World par1World, int par2, int par3, int par4, int par5, int par6, int par7)
    {
        int k1 = par1World.getBlockMetadata(par2, par3, par4);
        byte b0 = 0;
        int l1 = this.getMaxCurrentStrength(par1World, par5, par6, par7, b0);
        this.wiresProvidePower = false;
        int i2 = par1World.getStrongestIndirectPower(par2, par3, par4);
        this.wiresProvidePower = true;

        if (i2 > 0 && i2 > l1 - 3)
        {
            l1 = i2;
        }

        int j2 = 0;

        j2 = this.getMaxCurrentStrength(par1World, par2, par3 - 1, par4, j2);

        if (j2 > l1)
        {
            l1 = j2 - 3;
        }
        else if (l1 > 3)
        {
            l1 -= 3;
        }
        else
        {
            l1 = 0;
        }

        if (i2 > l1 - 3)
        {
            l1 = i2;
        }
        if(l1 < 0)
        	l1 = 0;

        if (k1 != l1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, l1, 2);
            this.blocksNeedingUpdate.add(new ChunkPosition(par2, par3, par4));
            this.blocksNeedingUpdate.add(new ChunkPosition(par2, par3 + 1, par4));
        }
    }
    
    private int getMaxCurrentStrength(World par1World, int par2, int par3, int par4, int par5)
    {
        if (par1World.getBlockId(par2, par3, par4) != this.blockID)
        {
            return par5;
        }
        else
        {
            int i1 = par1World.getBlockMetadata(par2, par3, par4);
            return i1 > par5 ? i1 : par5;
        }
    }

}
/**/