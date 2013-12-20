package co.uk.silvania.roads.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import co.uk.silvania.roads.Roads;

public class YellowPaintBlob extends Item {
	
	public YellowPaintBlob(int id) {
		super(id);
		this.setMaxStackSize(64);
		this.setCreativeTab(Roads.tabRoads);
	}
	
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean bool) {
		list.add("Yellow");
	}
	
	public void registerIcons(IconRegister iconRegister) {
	    itemIcon = iconRegister.registerIcon(Roads.modid + ":YellowPaintBlob");
	}

}
