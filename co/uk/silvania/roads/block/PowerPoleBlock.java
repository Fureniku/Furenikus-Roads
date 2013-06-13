/*package co.uk.silvania.roads.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import co.uk.silvania.roads.Roads;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Direction;
import net.minecraft.util.Icon;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class PowerPoleBlock extends Block
{

    private boolean wiresProvidePower = true;
    private Set blocksNeedingUpdate = new HashSet();
    @SideOnly(Side.CLIENT)
    public Icon rs_cross;
    @SideOnly(Side.CLIENT)
    public Icon field_94410_cO;
    @SideOnly(Side.CLIENT)
    public Icon field_94411_cP;
    @SideOnly(Side.CLIENT)
    public Icon field_94412_cQ;

    public PowerPoleBlock(int par1)
    {
        super(par1, Material.iron);
        this.setBlockBounds(0.4F, 0.0F, 0.4F, 0.6F, 1.0F, 0.6F);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }


    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public int getRenderType()
    {
        return 0;
    }

    @SideOnly(Side.CLIENT)

    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return 8388608;
    }

    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4) || par1World.getBlockId(par2, par3 - 1, par4) == Block.glowStone.blockID;
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

    private void calculateCurrentChanges(World par1World, int par2, int par3, int par4, int par5, int par6, int par7)
    {
        int k1 = par1World.getBlockMetadata(par2, par3, par4);
        byte b0 = 0;
        int l1 = this.getMaxCurrentStrength(par1World, par5, par6, par7, b0);
        this.wiresProvidePower = false;
        int i2 = par1World.getStrongestIndirectPower(par2, par3, par4);
        this.wiresProvidePower = true;

        if (i2 > 0 && i2 > l1 - 1)
        {
            l1 = i2;
        }

        int j2 = 0;

        for (int k2 = 0; k2 < 4; ++k2)
        {
            int l2 = par2;
            int i3 = par4;

            if (k2 == 0)
            {
                l2 = par2 - 1;
            }

            if (k2 == 1)
            {
                ++l2;
            }

            if (k2 == 2)
            {
                i3 = par4 - 1;
            }

            if (k2 == 3)
            {
                ++i3;
            }

            if (l2 != par5 || i3 != par7)
            {
                j2 = this.getMaxCurrentStrength(par1World, l2, par3, i3, j2);
            }

            if (par1World.isBlockNormalCube(l2, par3, i3) && !par1World.isBlockNormalCube(par2, par3 + 1, par4))
            {
                if ((l2 != par5 || i3 != par7) && par3 >= par6)
                {
                    j2 = this.getMaxCurrentStrength(par1World, l2, par3 + 1, i3, j2);
                }
            }
            else if (!par1World.isBlockNormalCube(l2, par3, i3) && (l2 != par5 || i3 != par7) && par3 <= par6)
            {
                j2 = this.getMaxCurrentStrength(par1World, l2, par3 - 1, i3, j2);
            }
        }

        if (j2 > l1)
        {
            l1 = j2 - 1;
        }
        else if (l1 > 0)
        {
            --l1;
        }
        else
        {
            l1 = 0;
        }

        if (i2 > l1 - 1)
        {
            l1 = i2;
        }

        if (k1 != l1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, l1, 2);
            this.blocksNeedingUpdate.add(new ChunkPosition(par2, par3, par4));
            this.blocksNeedingUpdate.add(new ChunkPosition(par2 - 1, par3, par4));
            this.blocksNeedingUpdate.add(new ChunkPosition(par2 + 1, par3, par4));
            this.blocksNeedingUpdate.add(new ChunkPosition(par2, par3 - 1, par4));
            this.blocksNeedingUpdate.add(new ChunkPosition(par2, par3 + 1, par4));
            this.blocksNeedingUpdate.add(new ChunkPosition(par2, par3, par4 - 1));
            this.blocksNeedingUpdate.add(new ChunkPosition(par2, par3, par4 + 1));
        }
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

    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);

        if (!par1World.isRemote)
        {
            this.updateAndPropagateCurrentStrength(par1World, par2, par3, par4);
            par1World.notifyBlocksOfNeighborChange(par2, par3 + 1, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, this.blockID);
            this.notifyWireNeighborsOfNeighborChange(par1World, par2 - 1, par3, par4);
            this.notifyWireNeighborsOfNeighborChange(par1World, par2 + 1, par3, par4);
            this.notifyWireNeighborsOfNeighborChange(par1World, par2, par3, par4 - 1);
            this.notifyWireNeighborsOfNeighborChange(par1World, par2, par3, par4 + 1);

            if (par1World.isBlockNormalCube(par2 - 1, par3, par4))
            {
                this.notifyWireNeighborsOfNeighborChange(par1World, par2 - 1, par3 + 1, par4);
            }
            else
            {
                this.notifyWireNeighborsOfNeighborChange(par1World, par2 - 1, par3 - 1, par4);
            }

            if (par1World.isBlockNormalCube(par2 + 1, par3, par4))
            {
                this.notifyWireNeighborsOfNeighborChange(par1World, par2 + 1, par3 + 1, par4);
            }
            else
            {
                this.notifyWireNeighborsOfNeighborChange(par1World, par2 + 1, par3 - 1, par4);
            }

            if (par1World.isBlockNormalCube(par2, par3, par4 - 1))
            {
                this.notifyWireNeighborsOfNeighborChange(par1World, par2, par3 + 1, par4 - 1);
            }
            else
            {
                this.notifyWireNeighborsOfNeighborChange(par1World, par2, par3 - 1, par4 - 1);
            }

            if (par1World.isBlockNormalCube(par2, par3, par4 + 1))
            {
                this.notifyWireNeighborsOfNeighborChange(par1World, par2, par3 + 1, par4 + 1);
            }
            else
            {
                this.notifyWireNeighborsOfNeighborChange(par1World, par2, par3 - 1, par4 + 1);
            }
        }
    }

    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        super.breakBlock(par1World, par2, par3, par4, par5, par6);

        if (!par1World.isRemote)
        {
            par1World.notifyBlocksOfNeighborChange(par2, par3 + 1, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2 + 1, par3, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2 - 1, par3, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2, par3, par4 + 1, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2, par3, par4 - 1, this.blockID);
            this.updateAndPropagateCurrentStrength(par1World, par2, par3, par4);
            this.notifyWireNeighborsOfNeighborChange(par1World, par2 - 1, par3, par4);
            this.notifyWireNeighborsOfNeighborChange(par1World, par2 + 1, par3, par4);
            this.notifyWireNeighborsOfNeighborChange(par1World, par2, par3, par4 - 1);
            this.notifyWireNeighborsOfNeighborChange(par1World, par2, par3, par4 + 1);

            if (par1World.isBlockNormalCube(par2 - 1, par3, par4))
            {
                this.notifyWireNeighborsOfNeighborChange(par1World, par2 - 1, par3 + 1, par4);
            }
            else
            {
                this.notifyWireNeighborsOfNeighborChange(par1World, par2 - 1, par3 - 1, par4);
            }

            if (par1World.isBlockNormalCube(par2 + 1, par3, par4))
            {
                this.notifyWireNeighborsOfNeighborChange(par1World, par2 + 1, par3 + 1, par4);
            }
            else
            {
                this.notifyWireNeighborsOfNeighborChange(par1World, par2 + 1, par3 - 1, par4);
            }

            if (par1World.isBlockNormalCube(par2, par3, par4 - 1))
            {
                this.notifyWireNeighborsOfNeighborChange(par1World, par2, par3 + 1, par4 - 1);
            }
            else
            {
                this.notifyWireNeighborsOfNeighborChange(par1World, par2, par3 - 1, par4 - 1);
            }

            if (par1World.isBlockNormalCube(par2, par3, par4 + 1))
            {
                this.notifyWireNeighborsOfNeighborChange(par1World, par2, par3 + 1, par4 + 1);
            }
            else
            {
                this.notifyWireNeighborsOfNeighborChange(par1World, par2, par3 - 1, par4 + 1);
            }
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

    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        if (!par1World.isRemote)
        {
            boolean flag = this.canPlaceBlockAt(par1World, par2, par3, par4);

            if (flag)
            {
                this.updateAndPropagateCurrentStrength(par1World, par2, par3, par4);
            }
            else
            {
                this.dropBlockAsItem(par1World, par2, par3, par4, 0, 0);
                par1World.setBlockToAir(par2, par3, par4);
            }

            super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
        }
    }

    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Item.redstone.itemID;
    }

    public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return !this.wiresProvidePower ? 0 : this.isProvidingWeakPower(par1IBlockAccess, par2, par3, par4, par5);
    }

    public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        if (!this.wiresProvidePower)
        {
            return 0;
        }
        else
        {
            int i1 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);

            if (i1 == 0)
            {
                return 0;
            }
            else if (par5 == 1)
            {
                return i1;
            }
            else
            {
                boolean flag = isPoweredOrRepeater(par1IBlockAccess, par2 - 1, par3, par4, 1) || !par1IBlockAccess.isBlockNormalCube(par2 - 1, par3, par4) && isPoweredOrRepeater(par1IBlockAccess, par2 - 1, par3 - 1, par4, -1);
                boolean flag1 = isPoweredOrRepeater(par1IBlockAccess, par2 + 1, par3, par4, 3) || !par1IBlockAccess.isBlockNormalCube(par2 + 1, par3, par4) && isPoweredOrRepeater(par1IBlockAccess, par2 + 1, par3 - 1, par4, -1);
                boolean flag2 = isPoweredOrRepeater(par1IBlockAccess, par2, par3, par4 - 1, 2) || !par1IBlockAccess.isBlockNormalCube(par2, par3, par4 - 1) && isPoweredOrRepeater(par1IBlockAccess, par2, par3 - 1, par4 - 1, -1);
                boolean flag3 = isPoweredOrRepeater(par1IBlockAccess, par2, par3, par4 + 1, 0) || !par1IBlockAccess.isBlockNormalCube(par2, par3, par4 + 1) && isPoweredOrRepeater(par1IBlockAccess, par2, par3 - 1, par4 + 1, -1);

                if (!par1IBlockAccess.isBlockNormalCube(par2, par3 + 1, par4))
                {
                    if (par1IBlockAccess.isBlockNormalCube(par2 - 1, par3, par4) && isPoweredOrRepeater(par1IBlockAccess, par2 - 1, par3 + 1, par4, -1))
                    {
                        flag = true;
                    }

                    if (par1IBlockAccess.isBlockNormalCube(par2 + 1, par3, par4) && isPoweredOrRepeater(par1IBlockAccess, par2 + 1, par3 + 1, par4, -1))
                    {
                        flag1 = true;
                    }

                    if (par1IBlockAccess.isBlockNormalCube(par2, par3, par4 - 1) && isPoweredOrRepeater(par1IBlockAccess, par2, par3 + 1, par4 - 1, -1))
                    {
                        flag2 = true;
                    }

                    if (par1IBlockAccess.isBlockNormalCube(par2, par3, par4 + 1) && isPoweredOrRepeater(par1IBlockAccess, par2, par3 + 1, par4 + 1, -1))
                    {
                        flag3 = true;
                    }
                }

                return !flag2 && !flag1 && !flag && !flag3 && par5 >= 2 && par5 <= 5 ? i1 : (par5 == 2 && flag2 && !flag && !flag1 ? i1 : (par5 == 3 && flag3 && !flag && !flag1 ? i1 : (par5 == 4 && flag && !flag2 && !flag3 ? i1 : (par5 == 5 && flag1 && !flag2 && !flag3 ? i1 : 0))));
            }
        }
    }

    public boolean canProvidePower()
    {
        return this.wiresProvidePower;
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
        else if (!Block.redstoneRepeaterIdle.func_94487_f(i1))
        {
            return (Block.blocksList[i1] != null && Block.blocksList[i1].canConnectRedstone(par0IBlockAccess, par1, par2, par3, par4));
        }
        else
        {
            int j1 = par0IBlockAccess.getBlockMetadata(par1, par2, par3);
            return par4 == (j1 & 3) || par4 == Direction.rotateOpposite[j1 & 3];
        }
    }

    @SideOnly(Side.CLIENT)

    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
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

    public static boolean isPoweredOrRepeater(IBlockAccess par0IBlockAccess, int par1, int par2, int par3, int par4)
    {
        if (isPowerProviderOrWire(par0IBlockAccess, par1, par2, par3, par4))
        {
            return true;
        }
        else
        {
            int i1 = par0IBlockAccess.getBlockId(par1, par2, par3);

            if (i1 == Block.redstoneRepeaterActive.blockID)
            {
                int j1 = par0IBlockAccess.getBlockMetadata(par1, par2, par3);
                return par4 == (j1 & 3);
            }
            else
            {
                return false;
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return Item.redstone.itemID;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.rs_cross = par1IconRegister.registerIcon("redstoneDust_cross");
        this.field_94410_cO = par1IconRegister.registerIcon("redstoneDust_line");
        this.field_94411_cP = par1IconRegister.registerIcon("redstoneDust_cross_overlay");
        this.field_94412_cQ = par1IconRegister.registerIcon("redstoneDust_line_overlay");
        this.blockIcon = this.rs_cross;
    }

    @SideOnly(Side.CLIENT)
    public static Icon func_94409_b(String par0Str)
    {
        return par0Str == "redstoneDust_cross" ? Roads.powerPole.rs_cross : (par0Str == "redstoneDust_line" ? Roads.powerPole.field_94410_cO : (par0Str == "redstoneDust_cross_overlay" ? Roads.powerPole.field_94411_cP : (par0Str == "redstoneDust_line_overlay" ? Roads.powerPole.field_94412_cQ : null)));
    }
}*/



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
