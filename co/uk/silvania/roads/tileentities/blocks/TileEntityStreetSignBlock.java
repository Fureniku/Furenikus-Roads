package co.uk.silvania.roads.tileentities.blocks;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import co.uk.silvania.roads.Roads;
import co.uk.silvania.roads.tileentities.entities.TileEntityStreetSignEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityStreetSignBlock extends BlockContainer {

    public TileEntityStreetSignBlock(int id) {
        super(id, Material.iron);
        this.setHardness(1.0F);
        this.setCreativeTab(Roads.tabRoads);
        this.setLightOpacity(0);
        this.setBlockBounds(0.25F, 0F, 0.25F, 0.75F, 0.5F, 0.75F);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityStreetSignEntity();
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack itemStack) {
	    int blockSet = world.getBlockMetadata(x, y, z) / 4;
	    int direction = MathHelper.floor_double((double)(entityliving.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
	    int newMeta = (blockSet * 4) + direction;
	    world.setBlockMetadataWithNotify(x, y, z, newMeta, 0);
    }
    
	public void setBlockBoundsBasedOnState(IBlockAccess block, int x, int y, int z) {
		int meta = block.getBlockMetadata(x, y, z);
    	if (meta == 0) {
        	this.setBlockBounds(0.25F, 0.0F, 0.45F, 0.75F, 0.5F, 0.6F);
    	} else if (meta == 1) {
        	this.setBlockBounds(0.4F, 0.0F, 0.25F, 0.55F, 0.5F, 0.75F);
    	} else if (meta == 2) {
        	this.setBlockBounds(0.25F, 0.0F, 0.4F, 0.75F, 0.5F, 0.55F);
    	} else
        	this.setBlockBounds(0.45F, 0.0F, 0.25F, 0.6F, 0.5F, 0.75F);
	}
	
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axis, List list, Entity entity) {
       	super.addCollisionBoxesToList(world, x, y, z, axis, list, entity);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
    	blockIcon = iconRegister.registerIcon("Roads:streetSign30");
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs creativeTabs, List list) {
        list.add(new ItemStack(par1, 1, 0));
        list.add(new ItemStack(par1, 1, 4));
        list.add(new ItemStack(par1, 1, 8));
        list.add(new ItemStack(par1, 1, 12));
    }
}