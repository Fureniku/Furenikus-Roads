package co.uk.silvania.city.tileentities;

import co.uk.silvania.city.RoadsCity;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class TileEntityATMBlock extends BlockContainer {
	

	public TileEntityATMBlock(int id) {
		super(id, Material.iron);
		this.setCreativeTab(RoadsCity.tabEcon);
		this.setHardness(1.0F);
		this.setLightValue(0.5F);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityATMEntity();
	}
	
	@Override
	public int getRenderType() {
		return -1;
	}
	
	
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
                    EntityPlayer player, int i, float j, float k, float l) {
            TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
            if (player.getHeldItem() != null) {
            	if (player.getHeldItem().getItem() == RoadsCity.debitCard) {
                    player.openGui(RoadsCity.instance, 0, world, x, y, z);
            	}
            }
			return true;
    }
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
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