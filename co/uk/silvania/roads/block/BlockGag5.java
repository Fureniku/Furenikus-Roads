package co.uk.silvania.roads.block;

import co.uk.silvania.roads.Roads;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class BlockGag5 extends Block {

	public BlockGag5(int id) {
		super(id, Material.rock);
		this.setBlockUnbreakable();
		this.setBlockBounds(0.4F, 0.0F, 0.0F, 0.6F, 0.8F, 1.0F);
	}
	
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	public boolean isOpaqueCube() {
		return false;
	}
	
	public void registerIcons(IconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(Roads.modid + ":trans");
	}

}