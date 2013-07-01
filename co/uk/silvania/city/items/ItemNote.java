package co.uk.silvania.city.items;

import co.uk.silvania.city.RoadsCity;
import net.minecraft.item.Item;

public class ItemNote extends Item {

	public ItemNote(int id) {
		super(id);
		this.setCreativeTab(RoadsCity.tabEcon);
		this.setMaxStackSize(50);
	}
}
