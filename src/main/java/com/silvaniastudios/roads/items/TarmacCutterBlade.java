package com.silvaniastudios.roads.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TarmacCutterBlade extends RoadItemBase {

	public int size;
	
	public TarmacCutterBlade(String name, int maxDmg, int size) {
		super(name, 1);
		this.setMaxDamage(maxDmg);
		this.size = size;
	}
	
	@Override
    public boolean showDurabilityBar(ItemStack itemStack)  {
		if (itemStack.getItemDamage() > 0) {
			return true;
		}
		return false;
    }
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("Size: " + size);
	}
}
