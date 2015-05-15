package co.uk.silvania.roads.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import co.uk.silvania.roads.FlenixRoads;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PneumaticDrill extends Item {
	
	public PneumaticDrill() {
		this.setCreativeTab(FlenixRoads.tabRoads);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(FlenixRoads.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}
}
