package co.uk.silvania.roads.blocks;

import java.util.List;

import co.uk.silvania.roads.FlenixRoads;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class PaintItemBlock extends ItemBlock {

	public PaintItemBlock(Block block) {
		super(block);
		this.setCreativeTab(FlenixRoads.tabRoads);
		this.setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		int meta = itemStack.getItemDamage();
		return super.getUnlocalizedName() + "." + meta;
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}
	
	@Override
	public IIcon getIconFromDamage(int damage) {
		return FRBlocks.roadBlockBase1.getIcon(0, damage);
	}

}
