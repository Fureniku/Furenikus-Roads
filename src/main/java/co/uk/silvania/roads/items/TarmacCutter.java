package co.uk.silvania.roads.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import co.uk.silvania.roads.FlenixRoads;

public class TarmacCutter extends Item {
	
	public TarmacCutter() {
		this.setCreativeTab(FlenixRoads.tabRoads);
	}
	
	@Override
	public void registerIcons(IIconRegister icon) {
		icon.registerIcon(FlenixRoads.modid + ":" + this.getUnlocalizedName().substring(5));
	}

}
