package com.silvaniastudios.roads.items;

import java.util.List;

import javax.annotation.Nullable;

import com.silvaniastudios.roads.FurenikusRoads;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemWrench extends RoadItemBase {

	public ItemWrench(String name) {
		super(name, 1);
		this.setCreativeTab(FurenikusRoads.tab_tools);
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("Used to make minor adjustments to certain blocks");
	}
}
