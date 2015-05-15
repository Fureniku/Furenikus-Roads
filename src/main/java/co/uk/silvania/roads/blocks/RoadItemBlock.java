package co.uk.silvania.roads.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class RoadItemBlock extends ItemBlock {

	public RoadItemBlock(Block block) {
		super(block);
		this.setHasSubtypes(true);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean bool) {
		list.add("Height: " + (this.getDamage(item) - 1) + " / 16");
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

}
