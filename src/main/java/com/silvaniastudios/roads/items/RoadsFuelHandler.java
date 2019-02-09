package com.silvaniastudios.roads.items;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;

@SuppressWarnings("deprecation")
public class RoadsFuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		if (fuel.getItem() == FRItems.coal_coke) {
			return 2000;
		}
		return 0;
	}

}
