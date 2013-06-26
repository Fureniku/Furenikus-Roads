package co.uk.silvania.roads.tileentities.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;

import co.uk.silvania.roads.Roads;
import co.uk.silvania.roads.tileentities.entities.TileEntityRoadSignEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class TileEntityRoadSignBlock extends BlockContainer
{
    private Class signEntityClass;

    /** Whether this is a freestanding sign or a wall-mounted sign */
    private boolean isFreestanding;

    public TileEntityRoadSignBlock(int roadSignID, Class par2Class, boolean par3) {
		super(roadSignID, Material.iron);
		this.isFreestanding = par3;
		this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
		this.setCreativeTab(Roads.tabRoads);
		this.setHardness(1.0F);
	}

	@SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
        return Block.planks.getBlockTextureFromSide(par1);
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        if (!this.isFreestanding)
        {
            int l = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
            float f = 0.28125F;
            float f1 = 0.78125F;
            float f2 = 0.0F;
            float f3 = 1.0F;
            float f4 = 0.125F;
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);

            if (l == 2)
            {
                this.setBlockBounds(f2, f, 1.0F - f4, f3, f1, 1.0F);
            }

            if (l == 3)
            {
                this.setBlockBounds(f2, f, 0.0F, f3, f1, f4);
            }

            if (l == 4)
            {
                this.setBlockBounds(1.0F - f4, f, f2, 1.0F, f1, f3);
            }

            if (l == 5)
            {
                this.setBlockBounds(0.0F, f, f2, f4, f1, f3);
            }
        }
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return -1;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public boolean getBlocksMovement(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return true;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World par1World)
    {
        try
        {
            return new TileEntityRoadSignEntity();
        }
        catch (Exception exception)
        {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Item.sign.itemID;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        boolean flag = false;

        if (this.isFreestanding)
        {
            if (!par1World.getBlockMaterial(par2, par3 - 1, par4).isSolid())
            {
                flag = true;
            }
        }
        else
        {
            int i1 = par1World.getBlockMetadata(par2, par3, par4);
            flag = true;

            if (i1 == 2 && par1World.getBlockMaterial(par2, par3, par4 + 1).isSolid())
            {
                flag = false;
            }

            if (i1 == 3 && par1World.getBlockMaterial(par2, par3, par4 - 1).isSolid())
            {
                flag = false;
            }

            if (i1 == 4 && par1World.getBlockMaterial(par2 + 1, par3, par4).isSolid())
            {
                flag = false;
            }

            if (i1 == 5 && par1World.getBlockMaterial(par2 - 1, par3, par4).isSolid())
            {
                flag = false;
            }
        }

        if (flag)
        {
            this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
            par1World.setBlockToAir(par2, par3, par4);
        }

        super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
    }

    @SideOnly(Side.CLIENT)

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return Item.sign.itemID;
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister) {}
}
