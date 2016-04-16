package co.uk.silvania.roads.blocks;

import java.util.List;

import co.uk.silvania.roads.FlenixRoads;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class IconBlock extends LineBlock {
	
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister icon) {
		icons = new IIcon[16];
		
		for (int i = 0; i < icons.length; i++) {
			icons[i] = icon.registerIcon(FlenixRoads.modid + ":" + (this.getUnlocalizedName().substring(5)) + "_" + i);
		}
	}
	
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side){
    	int meta = world.getBlockMetadata(x, y+1, z);
        return icons[meta];
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
    	return icons[meta];
    }
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack item) {
		int meta = world.getBlockMetadata(x, y, z);
		int direction = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360F) + 2.5D) & 3;
		world.setBlockMetadataWithNotify(x, y, z, meta + direction, 3);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 4));
		list.add(new ItemStack(item, 1, 8));
		list.add(new ItemStack(item, 1, 12));
	}

}
