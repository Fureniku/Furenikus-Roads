package co.uk.silvania.roads.tileentities.blocks;

import java.util.List;

import co.uk.silvania.roads.Roads;
import co.uk.silvania.roads.tileentities.entities.TileEntityStreetSignEntity;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class TileEntityStreetSignBlock extends BlockContainer {

	public TileEntityStreetSignBlock(int id) {
		super(id, Material.iron);
		this.setCreativeTab(Roads.tabRoads);
		this.setHardness(1.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityStreetSignEntity();
	}
	
    public void addCollisionBoxesToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity) {
    	this.setBlockBounds(0.4F, 0.0F, 0.4F, 0.6F, 3.0F, 0.6F);
    	super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
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
		this.blockIcon = icon.registerIcon("Roads:StreetSignIcon");
	}
	
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4,
			EntityLiving par5EntityLiving, ItemStack par6ItemStack) {
		int var6 = MathHelper
				.floor_double(par5EntityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

		if (var6 == 0) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 0);
		}

		if (var6 == 1) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 0);
		}

		if (var6 == 2) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 0);
		}

		if (var6 == 3) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 0);
		}
	}	

}
