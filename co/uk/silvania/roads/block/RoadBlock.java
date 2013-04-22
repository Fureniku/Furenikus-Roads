package co.uk.silvania.roads.block;

import co.uk.silvania.roads.CommonProxy;
import co.uk.silvania.roads.Roads;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class RoadBlock extends Block {

        public RoadBlock (int texture, Material material) {
                super(texture, material);
                this.blockIndexInTexture = 1;
                this.setCreativeTab(Roads.tabRoads);
                this.setHardness(1.0F);
                this.setStepSound(Block.soundStoneFootstep);
        }
        
        @Override
        public String getTextureFile () {
                return CommonProxy.BLOCK_PNG;
        }

}