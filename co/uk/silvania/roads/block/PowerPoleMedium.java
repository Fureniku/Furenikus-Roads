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

public class PowerPoleMedium extends Block {
    private final boolean powered;
    public static int powerValue;

	public PowerPoleMedium(int id, boolean par2) {
		super(id, Material.iron);
		this.powered = par2;
		this.setCreativeTab(Roads.tabRoads);
        this.setBlockBounds(0.45F, 0.0F, 0.45F, 0.55F, 1.0F, 0.55F);
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
	
    public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
    	if (this.powered) {
    		return 15;
    	}
      	return 0;
    }
    
    public boolean canProvidePower() {
   		return true;
    }
    
    
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        if (!par1World.isRemote)
        {
            if (this.powered && !par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
            {
                par1World.setBlock(par2, par3, par4, Roads.powerPole.blockID, 0, 2);
            }
            else if (!this.powered && par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
            {
                par1World.setBlock(par2, par3, par4, Roads.powerPoleOn.blockID, 0, 2);
            }
        }
    }

    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        if (!par1World.isRemote)
        {
            if (this.powered && !par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
            {
                par1World.setBlock(par2, par3, par4, Roads.powerPole.blockID, 0, 2);
            }
            else if (!this.powered && par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
            {
                par1World.setBlock(par2, par3, par4, Roads.powerPoleOn.blockID, 0, 2);
            }
        }
    }

    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote && this.powered && !par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
        {
            par1World.setBlock(par2, par3, par4, Roads.powerPole.blockID, 0, 2);
        }
    }

    public void registerIcons(IconRegister iconRegister) {
    	blockIcon = iconRegister.registerIcon("Roads:PowerPole");
    }
}