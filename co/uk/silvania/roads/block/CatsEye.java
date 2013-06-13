package co.uk.silvania.roads.block;

import co.uk.silvania.roads.Roads;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class CatsEye extends Block {

	public CatsEye(int id) {
		super(id, Material.glass);
		this.setStepSound(Block.soundStoneFootstep);
		this.setCreativeTab(Roads.tabRoads);
        this.setBlockBounds(0.4F, -0.175F, 0.4F, 0.6F, -0.125F, 0.6F);
        this.setLightValue(1.0F);
	}
	
    public boolean renderAsNormalBlock() {
        return false;
    }
    
    public boolean isOpaqueCube() {
    	return false;
    }
    
    public void registerIcons(IconRegister iconRegister) {
    	blockIcon = iconRegister.registerIcon("Roads:CatsEye");
    }

}
