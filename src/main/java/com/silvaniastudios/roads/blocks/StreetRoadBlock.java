package com.silvaniastudios.roads.blocks;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.items.RoadItemBase;

import net.minecraft.block.material.Material;

public class StreetRoadBlock extends RoadBlock {

	public StreetRoadBlock(String name, Material mat, RoadItemBase fragment) {
		super(name, mat, fragment);
		this.setCreativeTab(FurenikusRoads.tab_sidewalks);
	}
}
