package co.uk.silvania.roads.block;

import co.uk.silvania.roads.Roads;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public class CatsEye extends Block {

	public CatsEye(int id) {
		super(id, Material.glass);
		this.setStepSound(Block.soundStoneFootstep);
		this.setCreativeTab(Roads.tabRoads);
        this.setBlockBounds(0.4F, -0.175F, 0.4F, 0.6F, -0.125F, 0.6F);
        this.setLightValue(0.5F);
	}
	
	@SideOnly(Side.CLIENT)
	private Icon side1;
	@SideOnly(Side.CLIENT)
	private Icon top;
	
    public boolean renderAsNormalBlock() {
        return false;
    }
    
    public boolean isOpaqueCube() {
    	return false;
    }
    
    public void registerIcons(IconRegister iconRegister) {
    	this.top = iconRegister.registerIcon("Roads:CatsEyeTop");
    	this.side1 = iconRegister.registerIcon("Roads:CatsEyeSide1");
    }
    
    public Icon getIcon(int side, int meta) {
    	int k = meta;
        if (side == 1) {
         	return top;
        } else
        	return side1;
    }

}
