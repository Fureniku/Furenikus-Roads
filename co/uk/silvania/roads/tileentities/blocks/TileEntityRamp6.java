package co.uk.silvania.roads.tileentities.blocks;

import java.util.List;

import net.minecraft.block.Block;
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
import co.uk.silvania.roads.client.ClientProxy;
import co.uk.silvania.roads.tileentities.entities.TileEntityRoadSlope6Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityRamp6 extends Block {

    public TileEntityRamp6(int id) {
        super(id, Material.rock);
        this.setHardness(1.0F);
        this.setCreativeTab(Roads.tabRoads);
        this.setLightOpacity(0);
        this.setBlockBounds(0.0F, -0.25F, 0.0F, 1.0F, 0.75F, 1.0F);
    }
    
	@SideOnly(Side.CLIENT)
	private Icon top;

	@Override
	public int getRenderType() {
		return ClientProxy.RoadsRampShortRenderID;
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
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon("Roads:roadBlockMiscSingles0");
    	this.top = iconRegister.registerIcon("Roads:roadBlockDoubleYellow0");
    }
    
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		if (side == 1) {
			return top;	
		} else
			return blockIcon;

	}

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs creativeTabs, List list) {
        list.add(new ItemStack(par1, 1, 0));
        list.add(new ItemStack(par1, 1, 4));
        list.add(new ItemStack(par1, 1, 8));
        list.add(new ItemStack(par1, 1, 12));
    }
}