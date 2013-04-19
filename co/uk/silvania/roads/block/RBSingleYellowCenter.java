package co.uk.silvania.roads.block;

import co.uk.silvania.roads.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class RBSingleYellowCenter extends Block {

        public RBSingleYellowCenter (int id, int texture, Material material) {
                super(id, texture, material);
        }
        
        public int getBlockTextureFromSide(int side){
        	if(side == 1){
        		return 9;
        	}else{
        		return 1;
        		}
        	}
        
        @Override
        public String getTextureFile () {
                return CommonProxy.BLOCK_PNG;
        }

}