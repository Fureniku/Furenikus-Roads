package com.silvaniastudios.roads.client;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.ColorizerGrass;

public class ModItemColours implements IItemColor {

	@Override
	public int colorMultiplier(ItemStack stack, int tintIndex) {
		return ColorizerGrass.getGrassColor(0.5, 0.5);
	}

}
