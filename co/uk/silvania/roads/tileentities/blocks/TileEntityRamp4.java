package co.uk.silvania.roads.tileentities.blocks;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import co.uk.silvania.roads.Roads;
import co.uk.silvania.roads.tileentities.entities.TileEntityRoadSlope4Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityRamp4 extends BlockContainer {

    public TileEntityRamp4(int id) {
        super(id, Material.rock);
        this.setHardness(1.0F);
        this.setCreativeTab(Roads.tabRoads);
        this.setLightOpacity(0);
        this.setBlockBounds(0.0F, -0.25F, 0.0F, 1.0F, -0.24F, 1.0F);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityRoadSlope4Entity();
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

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack itemStack) {
    	int meta = world.getBlockMetadata(x, y, z);
	    int blockSet = meta / 4;
	    int direction = MathHelper.floor_double((double)(entityliving.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
	    int newMeta = (blockSet * 4) + direction;
	    world.setBlockMetadataWithNotify(x, y, z, newMeta, 0);
	    if (newMeta == 1 || newMeta == 5 || newMeta == 9 || newMeta == 13) {
	    	world.setBlock(x + 1, y, z, Roads.blockGag2.blockID);
    	} else if (newMeta == 3 || newMeta == 7 || newMeta == 11 || newMeta == 15) {
	    	world.setBlock(x - 1, y, z, Roads.blockGag2.blockID);
	    } else if (newMeta == 2 || newMeta == 6 || newMeta == 10 || newMeta == 14) {
	    	world.setBlock(x, y, z + 1, Roads.blockGag2.blockID);
	    } else if (newMeta == 0 || newMeta == 4 || newMeta == 8 || newMeta == 12) {
	    	world.setBlock(x, y, z - 1, Roads.blockGag2.blockID);
	    }
    }
    
    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
    	int meta = world.getBlockMetadata(x, y, z);   
    	if (meta == 1 || meta == 5 || meta == 9 || meta == 13) {
    		world.setBlockToAir(x + 1, y, z);
    	}
	    if (meta == 3 || meta == 7 || meta == 11 || meta == 15) {
	    	world.setBlockToAir(x - 1, y, z);
	    }
	    if (meta == 2|| meta == 6 || meta == 10 || meta == 14) {
	    	world.setBlockToAir(x, y, z + 1);
	    }
	    if (meta == 0 || meta == 4 || meta == 8 || meta == 12) {
	    	world.setBlockToAir(x, y, z - 1);
	    }
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