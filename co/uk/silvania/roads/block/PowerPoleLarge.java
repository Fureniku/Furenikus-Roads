package co.uk.silvania.roads.block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import co.uk.silvania.roads.Roads;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneWire;
import co.uk.silvania.roads.block.RoadsRedstoneUpdate;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.creativetab.CreativeTabs;
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

    private Set blocksNeedingUpdate = new HashSet();
    private boolean torchActive;
    /** Map of ArrayLists of RoadsRedstoneUpdate. Key of map is World. */
    private static Map RoadsRedstoneUpdateCache = new HashMap();
	
	public PowerPoleLarge(int id, boolean par2) {
		super(id, Material.iron);
		this.setCreativeTab(Roads.tabRoads);
        this.setBlockBounds(0.425F, 0.0F, 0.425F, 0.575F, 1.0F, 0.575F);
        this.torchActive = par2;
        this.setTickRandomly(true);
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
    	blockIcon = iconRegister.registerIcon(Roads.modid + ":PowerPole");
    }
    
    /** Whether the redstone torch is currently active or not. */

    private boolean checkForBurnout(World par1World, int par2, int par3, int par4, boolean par5)
    {
        if (!RoadsRedstoneUpdateCache.containsKey(par1World))
        {
            RoadsRedstoneUpdateCache.put(par1World, new ArrayList());
        }

        List list = (List)RoadsRedstoneUpdateCache.get(par1World);

        if (par5)
        {
            list.add(new RoadsRedstoneUpdate(par2, par3, par4, par1World.getTotalWorldTime()));
        }

        int l = 0;

        for (int i1 = 0; i1 < list.size(); ++i1)
        {
            RoadsRedstoneUpdate RoadsRedstoneUpdate = (RoadsRedstoneUpdate)list.get(i1);

            if (RoadsRedstoneUpdate.x == par2 && RoadsRedstoneUpdate.y == par3 && RoadsRedstoneUpdate.z == par4)
            {
                ++l;

                if (l >= 8)
                {
                    return true;
                }
            }
        }

        return false;
    }


    /**
     * How many world ticks before ticking
     */
    public int tickRate(World par1World)
    {
        return 2;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        if (par1World.getBlockMetadata(par2, par3, par4) == 0)
        {
            super.onBlockAdded(par1World, par2, par3, par4);
        }

        if (this.torchActive)
        {
            par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2, par3 + 1, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2 - 1, par3, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2 + 1, par3, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2, par3, par4 - 1, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2, par3, par4 + 1, this.blockID);
        }
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        if (this.torchActive)
        {
            par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2, par3 + 1, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2 - 1, par3, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2 + 1, par3, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2, par3, par4 - 1, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2, par3, par4 + 1, this.blockID);
        }
    }

    /**
     * Returns true if the block is emitting indirect/weak redstone power on the specified side. If isBlockNormalCube
     * returns true, standard redstone propagation rules will apply instead and this will not be called. Args: World, X,
     * Y, Z, side. Note that the side is reversed - eg it is 1 (up) when checking the bottom of the block.
     */
    public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        if (!this.torchActive)
        {
            return 0;
        }
        else
        {
            int i1 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
            return i1 == 5 && par5 == 1 ? 0 : (i1 == 3 && par5 == 3 ? 0 : (i1 == 4 && par5 == 2 ? 0 : (i1 == 1 && par5 == 5 ? 0 : (i1 == 2 && par5 == 4 ? 0 : 15))));
        }
    }

    /**
     * Returns true or false based on whether the block the torch is attached to is providing indirect power.
     */
    private boolean isIndirectlyPowered(World par1World, int x, int y, int z) {
        if (par1World.getIndirectPowerOutput(x, y - 1, z, 0) == true) {// : (par1World.getIndirectPowerOutput(x, y, z - 1, 2) ? true : (par1World.getIndirectPowerOutput(x, y, z + 1, 3) ? true : (l == 1 && par1World.getIndirectPowerOutput(x - 1, y, z, 4) ? true : l == 2 && par1World.getIndirectPowerOutput(x + 1, y, z, 5))));
        	par1World.setBlock(x + 1, y, z, Roads.blockGag1.blockID);
        }
		return torchActive;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        boolean flag = this.isIndirectlyPowered(par1World, par2, par3, par4);
        List list = (List)RoadsRedstoneUpdateCache.get(par1World);

        while (list != null && !list.isEmpty() && par1World.getTotalWorldTime() - ((RoadsRedstoneUpdate)list.get(0)).updateTime > 60L)
        {
            list.remove(0);
        }

        if (this.torchActive)
        {
            if (flag)
            {

                if (this.checkForBurnout(par1World, par2, par3, par4, true))
                {
                    par1World.playSoundEffect((double)((float)par2 + 0.5F), (double)((float)par3 + 0.5F), (double)((float)par4 + 0.5F), "random.fizz", 0.5F, 2.6F + (par1World.rand.nextFloat() - par1World.rand.nextFloat()) * 0.8F);

                    for (int l = 0; l < 5; ++l)
                    {
                        double d0 = (double)par2 + par5Random.nextDouble() * 0.6D + 0.2D;
                        double d1 = (double)par3 + par5Random.nextDouble() * 0.6D + 0.2D;
                        double d2 = (double)par4 + par5Random.nextDouble() * 0.6D + 0.2D;
                        par1World.spawnParticle("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
                    }
                }
            }
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
    	boolean flag = this.isIndirectlyPowered(par1World, par2, par3, par4);

    	if (this.torchActive && flag || !this.torchActive && !flag) {
    		par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate(par1World));
    	}
    }

    /**
     * Returns true if the block is emitting direct/strong redstone power on the specified side. Args: World, X, Y, Z,
     * side. Note that the side is reversed - eg it is 1 (up) when checking the bottom of the block.
     */
    public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return par5 == 0 ? this.isProvidingWeakPower(par1IBlockAccess, par2, par3, par4, par5) : 0;
    }

    /**
     * Can this block provide power. Only wire currently seems to have this change based on its state.
     */
    public boolean canProvidePower()
    {
    	if (this.torchActive) {
    		return true;
    	} else 
    	return false;
    }

    @SideOnly(Side.CLIENT)

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (this.torchActive)
        {
            int l = par1World.getBlockMetadata(par2, par3, par4);
            double d0 = (double)((float)par2 + 0.5F) + (double)(par5Random.nextFloat() - 0.5F) * 0.2D;
            double d1 = (double)((float)par3 + 0.7F) + (double)(par5Random.nextFloat() - 0.5F) * 0.2D;
            double d2 = (double)((float)par4 + 0.5F) + (double)(par5Random.nextFloat() - 0.5F) * 0.2D;
            double d3 = 0.2199999988079071D;
            double d4 = 0.27000001072883606D;

            if (l == 1)
            {
                par1World.spawnParticle("reddust", d0 - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
            }
            else if (l == 2)
            {
                par1World.spawnParticle("reddust", d0 + d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
            }
            else if (l == 3)
            {
                par1World.spawnParticle("reddust", d0, d1 + d3, d2 - d4, 0.0D, 0.0D, 0.0D);
            }
            else if (l == 4)
            {
                par1World.spawnParticle("reddust", d0, d1 + d3, d2 + d4, 0.0D, 0.0D, 0.0D);
            }
            else
            {
                par1World.spawnParticle("reddust", d0, d1, d2, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return Roads.powerPoleLarge.blockID;
    }

    /**
     * Returns true if the given block ID is equivalent to this one. Example: redstoneTorchOn matches itself and
     * redstoneTorchOff, and vice versa. Most blocks only match themselves.
     */
    public boolean isAssociatedBlockID(int par1) {
        return par1 == Roads.powerPoleLarge.blockID || par1 == Roads.powerPoleLargeOn.blockID;
    }
}
/**/