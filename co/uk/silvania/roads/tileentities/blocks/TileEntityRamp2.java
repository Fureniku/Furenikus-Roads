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
import co.uk.silvania.roads.tileentities.entities.TileEntityRoadSlope2Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityRamp2 extends BlockContainer {

    public TileEntityRamp2(int id) {
        super(id, Material.rock);
        this.setHardness(1.0F);
        this.setCreativeTab(Roads.tabRoads);
        this.setLightOpacity(0);
        this.setBlockBounds(0.0F, -0.2F, 0.0F, 1.0F, -0.175F, 1.0F);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityRoadSlope2Entity();
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