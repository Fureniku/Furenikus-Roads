package co.uk.silvania.city.items;

import co.uk.silvania.city.RoadsCity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;

public class ItemCoin10 extends Item {

	public ItemCoin10(int id) {
		super(id);
		this.setMaxStackSize(50);
		this.setCreativeTab(RoadsCity.tabEcon);
	}
	

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon("City:Coin10");
	}
}
