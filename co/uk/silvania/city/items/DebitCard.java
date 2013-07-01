package co.uk.silvania.city.items;

import net.minecraft.item.Item;
import co.uk.silvania.city.RoadsCity;

public class DebitCard extends Item {

	public DebitCard(int id) {
		super(id);
		this.setMaxStackSize(1);
		this.setCreativeTab(RoadsCity.tabEcon);
	}
}