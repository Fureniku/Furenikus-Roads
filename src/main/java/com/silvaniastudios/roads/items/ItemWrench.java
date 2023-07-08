package com.silvaniastudios.roads.items;

import java.util.List;

import javax.annotation.Nullable;

import com.silvaniastudios.roads.FurenikusRoads;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
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
		tooltip.add(I18n.format("item.tooltip.wrench_1"));
		tooltip.add(I18n.format("item.tooltip.wrench_2"));
		if (stack.hasTagCompound()) {
			tooltip.add(I18n.format("item.tooltip.wrench_3" + " " + + stack.getTagCompound().getInteger("mode")));
		}
	}
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
		RayTraceResult rtr = this.rayTrace(worldIn, player, true);
		ItemStack stack = player.getHeldItem(handIn);
		NBTTagCompound nbt;
		
		if (rtr == null) {
			if (!stack.hasTagCompound()) {
				nbt = new NBTTagCompound();
				nbt.setInteger("mode", 0);
			} else {
				nbt = stack.getTagCompound();
			}
			
			if (nbt.getInteger("mode") == 0) {
				nbt.setInteger("mode", 1);
			} else {
				nbt.setInteger("mode", 0);
			}
			
			stack.setTagCompound(nbt);
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
	}
	
	public int getMode(ItemStack stack) {
		NBTTagCompound nbt;
		
		if (!stack.hasTagCompound()) {
			nbt = new NBTTagCompound();
			nbt.setInteger("mode", 0);
			stack.setTagCompound(nbt);
		}
		
		nbt = stack.getTagCompound();
		if (!nbt.hasKey("mode")) {
			nbt.setInteger("mode", 0);
		}
		return nbt.getInteger("mode");
	}
}
