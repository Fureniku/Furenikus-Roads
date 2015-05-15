package co.uk.silvania.roads.items;

import co.uk.silvania.roads.FlenixRoads;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class PneumaticDrill extends Item {
	
	public PneumaticDrill() {
		this.setCreativeTab(FlenixRoads.tabRoads);
	}
	
	@Override
	public void registerIcons(IIconRegister icon) {
		icon.registerIcon(FlenixRoads.modid + ":" + this.getUnlocalizedName().substring(5));
	}
}
