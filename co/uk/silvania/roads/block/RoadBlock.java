package co.uk.silvania.roads.block;

import co.uk.silvania.roads.CommonProxy;
import co.uk.silvania.roads.Roads;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public class RoadBlock extends Block {

        public RoadBlock (int id) {
            super(id, Material.rock);
            this.setHardness(1.0F);
    		this.setStepSound(Block.soundStoneFootstep);
    		this.setCreativeTab(Roads.tabRoads);
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.825F, 1.0F);
        }

    	public void registerIcons(IconRegister iconRegister) {
            blockIcon = iconRegister.registerIcon("Roads:TarmacPlain");
    	}
    	
        public boolean renderAsNormalBlock() {
            return false;
        }
        
        public boolean isOpaqueCube() {
        	return false;
        }
}