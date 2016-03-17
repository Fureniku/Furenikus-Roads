package co.uk.silvania.roads.blocks;

import java.util.List;

import co.uk.silvania.roads.FlenixRoads;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class SimpleBlock extends Block {
	//For simple blocks which don't need custom renderers etc.

	public SimpleBlock() {
		super(Material.rock);
		this.setHardness(1.5F);
		this.setCreativeTab(FlenixRoads.tabSidewalks);
	}
	
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister icon) {
		icons = new IIcon[16];
		
		for (int i = 0; i < icons.length; i++) {
			icons[i] = icon.registerIcon(FlenixRoads.modid + ":" + (this.getUnlocalizedName().substring(5))+i);
		}
	}
	
	public IIcon getIcon(int side, int meta) {
		return icons[meta];
	}
	
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int meta) {
		return icons[meta];
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < 16; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	@Override
	public int damageDropped(int meta) {
		return meta;
	}

}
