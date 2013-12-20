package co.uk.silvania.roads.item;

import co.uk.silvania.roads.CommonProxy;
import co.uk.silvania.roads.Roads;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class CementItem extends Item {

	    public CementItem(int id) {
			super(id);
			this.setMaxStackSize(64);
			this.setCreativeTab(Roads.tabRoads);
		}
		public void registerIcons(IconRegister iconRegister) {
		    itemIcon = iconRegister.registerIcon(Roads.modid + ":CementItem");
		}
	}
