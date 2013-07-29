package co.uk.silvania.roads.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class BlockGag4 extends Block {

	public BlockGag4(int id) {
		super(id, Material.rock);
		this.setBlockUnbreakable();
		this.setBlockBounds(0.0F, 0.0F, 0.4F, 1.0F, 0.8F, 0.6F);
	}
	
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	public boolean isOpaqueCube() {
		return false;
	}
	
	public void registerIcons(IconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon("Roads:trans");
	}
}