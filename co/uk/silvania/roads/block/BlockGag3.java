package co.uk.silvania.roads.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class BlockGag3 extends Block {

	public BlockGag3(int id) {
		super(id, Material.rock);
		this.setBlockUnbreakable();
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
	}
	
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	public boolean isOpaqueCube() {
		return false;
	}
	
	public void registerIcons(IconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon("Roads:generalBlocks0");
	}

}