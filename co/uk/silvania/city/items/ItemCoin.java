package co.uk.silvania.city.items;

import co.uk.silvania.city.RoadsCity;
import net.minecraft.item.Item;

public class ItemCoin extends Item {

	public ItemCoin(int id) {
		super(id);
		this.setMaxStackSize(50);
		this.setCreativeTab(RoadsCity.tabEcon);
	}
}
