package com.silvaniastudios.roads.items;

import com.silvaniastudios.roads.FlenixRoads;

import net.minecraft.item.Item;

public class RoadItemBase extends Item {
	
	protected String name;
	
	public RoadItemBase(String name, int stackSize) {
		this.name = name;
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.maxStackSize = stackSize;
		this.setCreativeTab(FlenixRoads.tab_tools);
	}

	public void registerItemModel() {
		FlenixRoads.proxy.registerItemRenderer(this, 0, name);
	}

}
