package co.uk.silvania.roads.roadblocks.itemblocks;

import java.util.List;

import cpw.mods.fml.common.Mod.Metadata;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemRoadBlockStripes extends ItemBlock {

	public ItemRoadBlockStripes(int par1) {
		super(par1);
		this.setHasSubtypes(true);
	}
	
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean bool) {
		switch (item.getItemDamage()) {
			case 0: {
				list.add("White Side");
				break;
			}
			case 4: {
				list.add("White Far Side");
				break;
			}
			case 8: {
				list.add("Yellow Side");
				break;
			}
			case 12: {
				list.add("Yellow Far Side");
				break;
			}
			default: list.add("");
		}
	}

	public String getUnlocalizedName(ItemStack itemstack) {

		String name = "";
		switch(itemstack.getItemDamage()) {
			case 0: {
				name = "0";
				break;
			}
			case 1: {
				name = "1";
				break;
			}
			case 2: {
				name = "2";
				break;
			}
			case 3: {
				name = "3";
				break;
			}
			case 4: {
				name = "4";
				break;
			}
			case 5: {
				name = "5";
				break;
			}
			case 6: {
				name = "6";
				break;
			}
			case 7: {
				name = "7";
				break;
			}
			case 8: {
				name = "8";
				break;
			}
			case 9: {
				name = "9";
				break;
			}
			case 10: {
				name = "10";
				break;
			}
			case 11: {
				name = "11";
				break;
			}
			case 12: {
				name = "12";
				break;
			}
			default: name = "broken";
		}
		return getUnlocalizedName() + "." + name;
	}

	public int getMetadata(int par1) {
		return par1;
	}

}