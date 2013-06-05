package co.uk.silvania.roads;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class RoadsItemBlock extends ItemBlock {

	public RoadsItemBlock(int par1) {
		super(par1);
	}
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {

		if(itemStack.itemID == Roads.roadBlockAUS.blockID) list.add("Arrow, Straight");
		if(itemStack.itemID == Roads.roadBlockAUR.blockID) list.add("Arrow, Straight");
		
		
	}
	
	private void registerBlock(Block block, String name, String gameName) {
		GameRegistry.registerBlock(block, RoadsItemBlock.class, name);
		LanguageRegistry.addName(block, gameName);
	}

}
