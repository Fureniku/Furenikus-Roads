package co.uk.silvania.roads.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import co.uk.silvania.roads.Roads;

public class YellowPaintCan extends Item {
	
	public YellowPaintCan(int id) {
		super(id);
		this.setMaxStackSize(1);
		this.setCreativeTab(Roads.tabRoads);
		this.setMaxDamage(64);
	}
	
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean bool) {
		list.add("Yellow");
	}
	
	public void registerIcons(IconRegister iconRegister) {
	    itemIcon = iconRegister.registerIcon("Roads:YellowPaintCan");
	}

}
