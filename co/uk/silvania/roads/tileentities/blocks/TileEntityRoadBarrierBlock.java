package co.uk.silvania.roads.tileentities.blocks;

import java.util.Random;

import co.uk.silvania.roads.Roads;
import co.uk.silvania.roads.tileentities.entities.TileEntityLightBollardEntity;
import co.uk.silvania.roads.tileentities.entities.TileEntityRoadBarrierEntity;
import co.uk.silvania.roads.tileentities.entities.TileEntityRoadBarrierUpEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class TileEntityRoadBarrierBlock extends BlockContainer {
    public final boolean powered;
    String direction = "";
	
	public TileEntityRoadBarrierBlock(int id, boolean par2) {
		super(id, Material.iron);
        this.powered = par2;
		this.setCreativeTab(Roads.tabRoads);
    	this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.95F, 0.75F);
		this.setHardness(1.0F);
		
        if (par2)
        {
            this.setLightValue(1.0F);
        }
	}
	
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        if (!par1World.isRemote)
        {
            if (this.powered && !par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
            {
                par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, 4);
            }
            else if (!this.powered && par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
            {
                par1World.setBlock(par2, par3, par4, Roads.roadBarrier.blockID, 0, 2);
            }
        }
    }
    
    public void onNeighborBlockChange(World world, int x, int y, int z, int par5) {
    	int meta = world.getBlockMetadata(x, y, z);
        if (!world.isRemote) {
        	//UNPOWERED STATE
            if (this.powered && !world.isBlockIndirectlyGettingPowered(x, y, z)) {
    	    	System.out.println("This is point A; the block is NOT powered.");
            }
            
            //POWERED STATE
            else if (!this.powered && world.isBlockIndirectlyGettingPowered(x, y, z)) {

    	    	System.out.println("This is point B.");
        	    }
            }
        }
    /**/
    
    /*public void onNeighborBlockChange(World world, int x, int y, int z, int par5) {
    	int meta = world.getBlockMetadata(x, y, z);
        if (!world.isRemote)
        {
            if (this.powered && !world.isBlockIndirectlyGettingPowered(x, y, z))
            {
                world.scheduleBlockUpdate(x, y, z, this.blockID, 4);
                world.setBlockMetadataWithNotify(x, y, z, 0, meta);
    	    	world.setBlock(x, y + 1, z, Roads.blockGag5.blockID);
    	    	world.setBlock(x, y + 2, z, Roads.blockGag5.blockID);
    	    	world.setBlock(x, y + 3, z, Roads.blockGag5.blockID);
    	    	System.out.println("IDGAF what the meta is (it's " + meta + " by the way), I'm setting the three blocks above to gag!");
                
            	if (meta == 1) {
            		world.setBlockToAir(x, y, z + 1);
            		world.setBlockToAir(x, y, z + 2);
            		world.setBlockToAir(x, y, z + 3);
            		System.out.println("Meta is " + meta + " so I'll set Z+ to air.");
            	}
        	    if (meta == 3) {
        	    	world.setBlockToAir(x, y, z - 1);
        	    	world.setBlockToAir(x, y, z - 2);
        	    	world.setBlockToAir(x, y, z - 3);
        	    	System.out.println("Meta is " + meta + " so I'll set Z- to air.");
        	    }
        	    if (meta == 2) {
        	    	world.setBlockToAir(x - 1, y, z);
        	    	world.setBlockToAir(x - 2, y, z);
        	    	world.setBlockToAir(x - 3, y, z);
        	    	System.out.println("Meta is " + meta + " so I'll set X- to air.");
        	    }
        	    if (meta == 0) {
        	    	world.setBlockToAir(x + 1, y, z);
        	    	world.setBlockToAir(x + 2, y, z);
        	    	world.setBlockToAir(x + 3, y, z);
        	    	System.out.println("Meta is " + meta + " so I'll set X+ to air.");
        	    }
                
            }
            else if (!this.powered && world.isBlockIndirectlyGettingPowered(x, y, z))
            {
                world.setBlockMetadataWithNotify(x, y, z, 0, meta);
            	if (meta == 1) {
            		if (world.isAirBlock(x, y, z + 1)) {
	        	    	world.setBlock(x, y, z + 1, Roads.blockGag5.blockID);
	        	    	world.setBlock(x, y, z + 2, Roads.blockGag5.blockID);
	        	    	world.setBlock(x, y, z + 3, Roads.blockGag5.blockID);
            		}
            	} else if (meta == 3) {
        	    	world.setBlock(x, y, z - 1, Roads.blockGag5.blockID);
        	    	world.setBlock(x, y, z - 2, Roads.blockGag5.blockID);
        	    	world.setBlock(x, y, z - 3, Roads.blockGag5.blockID);
        	    } else if (meta == 2) {
        	    	world.setBlock(x - 1, y, z, Roads.blockGag4.blockID);
        	    	world.setBlock(x - 2, y, z, Roads.blockGag4.blockID);
        	    	world.setBlock(x - 3, y, z, Roads.blockGag4.blockID);
        	    } else if (meta == 0) {
        	    	world.setBlock(x + 1, y, z, Roads.blockGag4.blockID);
        	    	world.setBlock(x + 2, y, z, Roads.blockGag4.blockID);
        	    	world.setBlock(x + 3, y, z, Roads.blockGag4.blockID);
        	    }
            }
        }
    }/**/

    public void updateTick(World world, int x, int y, int z, Random random) {
    	int meta = world.getBlockMetadata(x, y, z);
        if (!world.isRemote && this.powered && !world.isBlockIndirectlyGettingPowered(x, y, z)) {
            world.setBlock(x, y, z, Roads.roadBarrier.blockID, 0, 2);
            world.setBlockMetadataWithNotify(x, y, z, 0, meta);
        }
    }

    public int idDropped(int par1, Random par2Random, int par3) {
        return Roads.roadBarrier.blockID;
    }

    @SideOnly(Side.CLIENT)
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return Roads.roadBarrier.blockID;
    }

	@Override
	public TileEntity createNewTileEntity(World world) {
		if (this.powered) {
			return new TileEntityRoadBarrierEntity();
		} else
		return new TileEntityRoadBarrierUpEntity();
	}
	
	@Override
	public int getRenderType() {
		return -1;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	public void registerIcons(IconRegister icon) {
		this.blockIcon = icon.registerIcon("Roads:barrierIcon");
	}
	
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
    }	
	
    /*public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        int l = par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 7;

        switch (l)
        {
            case 0:
            default:
                this.setBlockBounds(0.0F, 0.0F, 0.15F, 1.0F, 0.8F, 0.4F);
                break;
            case 1:
                this.setBlockBounds(0.6F, 0.0F, 0.0F, 0.85F, 0.8F, 1.0F);
                break;
            case 2:
                this.setBlockBounds(0.0F, 0.0F, 0.6F, 1.0F, 0.8F, 0.85F);
                break;
            case 3:
                this.setBlockBounds(0.15F, 0.0F, 0.0F, 0.4F, 0.8F, 1.0F);
        }
    }*/
    
	
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        int l = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        System.out.println("Angle is L");
        //To store rotation in the tile entity, call the TE here and save L as an int. Read that back instead of the metadata.
        //Simples!
        world.setBlockMetadataWithNotify(x, y, z, l, 2);
        int meta = world.getBlockMetadata(x, y, z);
        if (meta == 0) {
        	System.out.println("Meta is ZERO, so direction is EAST");
        	String direction = "east";
        	System.out.println("To confirm that, it's direction is now " + direction + " and the meta is " + meta + ".");
        } else if (meta == 1) {
        	System.out.println("Meta is ONE, so direction is SOUTH");
        	String direction = "south";
        	System.out.println("To confirm that, it's direction is now " + direction + " and the meta is " + meta + ".");
        } else if (meta == 2) {
        	System.out.println("Meta is TWO, so direction is WEST");
        	String direction = "west";
        	System.out.println("To confirm that, it's direction is now " + direction + " and the meta is " + meta + ".");
        } else if (meta == 3) {
        	System.out.println("Meta is THREE, so direction is NORTH");
        	String direction = "north";
        	System.out.println("To confirm that, it's direction is now " + direction + " and the meta is " + meta + ".");
        } else {
        	System.out.println("Meta is 4 or higher...");
        	String direction = "broken";
        }
        
    	/*if (!this.powered && meta == 1) {
	    	world.setBlock(x, y, z + 1, Roads.blockGag5.blockID);
	    	world.setBlock(x, y, z + 2, Roads.blockGag5.blockID);
	    	world.setBlock(x, y, z + 3, Roads.blockGag5.blockID);
    	} else if (!this.powered && meta == 3) {
	    	world.setBlock(x, y, z - 1, Roads.blockGag5.blockID);
	    	world.setBlock(x, y, z - 2, Roads.blockGag5.blockID);
	    	world.setBlock(x, y, z - 3, Roads.blockGag5.blockID);
	    } else if (!this.powered && meta == 2) {
	    	world.setBlock(x - 1, y, z, Roads.blockGag4.blockID);
	    	world.setBlock(x - 2, y, z, Roads.blockGag4.blockID);
	    	world.setBlock(x - 3, y, z, Roads.blockGag4.blockID);
	    } else if (!this.powered && meta == 0) {
	    	world.setBlock(x + 1, y, z, Roads.blockGag4.blockID);
	    	world.setBlock(x + 2, y, z, Roads.blockGag4.blockID);
	    	world.setBlock(x + 3, y, z, Roads.blockGag4.blockID);
	    }*/
    }
    
    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
    	int meta = world.getBlockMetadata(x, y, z);   
    	if (!this.powered && meta == 1) {
    		world.setBlockToAir(x, y, z + 1);
    		world.setBlockToAir(x, y, z + 2);
    		world.setBlockToAir(x, y, z + 3);
    	}
	    if (!this.powered && meta == 3) {
	    	world.setBlockToAir(x, y, z - 1);
	    	world.setBlockToAir(x, y, z - 2);
	    	world.setBlockToAir(x, y, z - 3);
	    }
	    if (!this.powered && meta == 2) {
	    	world.setBlockToAir(x - 1, y, z + 1);
	    	world.setBlockToAir(x - 2, y, z + 2);
	    	world.setBlockToAir(x - 3, y, z + 3);
	    }
	    if (!this.powered && meta == 0) {
	    	world.setBlockToAir(x + 1, y, z);
	    	world.setBlockToAir(x + 2, y, z);
	    	world.setBlockToAir(x + 3, y, z);
	    }
    }
}