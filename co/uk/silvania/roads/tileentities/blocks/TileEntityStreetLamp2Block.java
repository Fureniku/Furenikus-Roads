package co.uk.silvania.roads.tileentities.blocks;

import java.util.List;

import co.uk.silvania.roads.Roads;
import co.uk.silvania.roads.tileentities.entities.TileEntityStreetLamp2Entity;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class TileEntityStreetLamp2Block extends BlockContainer {
	
    private final boolean powered;

	public TileEntityStreetLamp2Block(int id, boolean par2) {
		super(id, Material.iron);
		this.setCreativeTab(Roads.tabRoads);
		this.setHardness(1.0F);
	    this.powered = par2;
        this.setLightValue(1.0F);
        this.setBlockBounds(-0.4F, 0.0F, 0.4F, 1.4F, 0.2F, 0.6F);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityStreetLamp2Entity();
	}
	
	public void setBlockBoundsBasedOnState(IBlockAccess block, int x, int y, int z) {
		int meta = block.getBlockMetadata(x, y, z);
    	if (meta == 0) {
        	this.setBlockBounds(-0.4F, 0.0F, 0.4F, 1.4F, 0.2F, 0.6F);
    	} else if (meta == 1) {
        	this.setBlockBounds(0.4F, 0.0F, -0.4F, 0.6F, 0.2F, 1.4F);
    	} else if (meta == 2) {
        	this.setBlockBounds(-0.4F, 0.0F, 0.4F, 1.4F, 0.2F, 0.6F);
    	} else
        	this.setBlockBounds(0.4F, 0.0F, -0.4F, 0.6F, 0.2F, 1.4F);
	}
	
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axis, List list, Entity entity) {
       	super.addCollisionBoxesToList(world, x, y, z, axis, list, entity);
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
		this.blockIcon = icon.registerIcon("Roads:lightdouble");
	}
	
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        int l = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
    }
}
