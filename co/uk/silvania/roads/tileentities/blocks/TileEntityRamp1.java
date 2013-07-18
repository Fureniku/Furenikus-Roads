package co.uk.silvania.roads.tileentities.blocks;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import co.uk.silvania.roads.Roads;
import co.uk.silvania.roads.tileentities.entities.TileEntityRoadSlope1Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityRamp1 extends BlockContainer {

    public TileEntityRamp1(int id) {
        super(id, Material.rock);
        this.setHardness(1.0F);
        this.setCreativeTab(Roads.tabRoads);
        this.setLightOpacity(0);
        this.setBlockBounds(0.0F, -0.2F, 0.0F, 1.0F, -0.175F, 1.0F);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityRoadSlope1Entity();
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
    	int meta = world.getBlockMetadata(x, y, z);
	    int blockSet = meta / 4;
	    int direction = MathHelper.floor_double((double)(entityliving.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
	    int newMeta = (blockSet * 4) + direction;
	    world.setBlockMetadataWithNotify(x, y, z, newMeta, 0);
	    if (meta == 0) {
	    	world.setBlock(x + 1, y, z, Roads.blockGag1.blockID);
	    	world.setBlock(x + 2, y, z, Roads.blockGag2.blockID);
	    	world.setBlock(x + 3, y, z, Roads.blockGag3.blockID);
    	} else if (meta == 1) {
	    	world.setBlock(x - 1, y, z, Roads.blockGag1.blockID);
	    	world.setBlock(x - 2, y, z, Roads.blockGag2.blockID);
	    	world.setBlock(x - 3, y, z, Roads.blockGag3.blockID);
	    } else if (meta == 2) {
	    	world.setBlock(x, y, z + 1, Roads.blockGag1.blockID);
	    	world.setBlock(x, y, z + 2, Roads.blockGag2.blockID);
	    	world.setBlock(x, y, z + 3, Roads.blockGag3.blockID);
	    } else if (meta == 3) {
	    	world.setBlock(x, y, z - 1, Roads.blockGag1.blockID);
	    	world.setBlock(x, y, z - 2, Roads.blockGag2.blockID);
	    	world.setBlock(x, y, z - 3, Roads.blockGag3.blockID);
	    }
    }
    
    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
    	int meta = world.getBlockMetadata(x, y, z);   

	    world.setBlockToAir(x + 1, y, z);
	    world.setBlockToAir(x + 2, y, z);
	    world.setBlockToAir(x + 3, y, z);
	    /*if (meta == 1) {
	    	world.setBlockToAir(x - 1, y, z);
	    	world.setBlockToAir(x - 2, y, z);
	    	world.setBlockToAir(x - 3, y, z);
	    }
	    if (meta == 2) {
	    	world.setBlockToAir(x, y, z + 1);
	    	world.setBlockToAir(x, y, z + 2);
	    	world.setBlockToAir(x, y, z + 3);
	    }
	    if (meta == 3) {
	    	world.setBlockToAir(x, y, z - 1);
	    	world.setBlockToAir(x, y, z - 2);
	    	world.setBlockToAir(x, y, z - 3);
	    }*/
    }

    @SideOnly(Side.CLIENT)
    private Icon[] icons;

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        icons = new Icon[16];

        for (int i = 0; i < icons.length; i++) {
            icons[i] = iconRegister.registerIcon("Roads:" + this.getUnlocalizedName().substring(5) + i);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2) {
        return icons[par2];
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs creativeTabs, List list) {
        list.add(new ItemStack(par1, 1, 0));
        list.add(new ItemStack(par1, 1, 4));
        list.add(new ItemStack(par1, 1, 8));
        list.add(new ItemStack(par1, 1, 12));
    }
}