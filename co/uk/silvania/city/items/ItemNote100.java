package co.uk.silvania.city.items;

import co.uk.silvania.city.RoadsCity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemNote100 extends Item {

	public ItemNote100(int id) {
		super(id);
		this.setCreativeTab(RoadsCity.tabEcon);
		this.setMaxStackSize(50);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon("City:Note10000");
	}
}
