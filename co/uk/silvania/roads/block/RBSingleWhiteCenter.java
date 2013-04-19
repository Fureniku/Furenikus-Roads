package co.uk.silvania.roads.block;

import co.uk.silvania.roads.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class RBSingleWhiteCenter extends Block {

        public RBSingleWhiteCenter (int id, int texture, Material material) {
                super(id, texture, material);
        }
        public int getBlockTextureFromSide(int side){
        	if(side == 1){
        		return 7;
        	}else{
        		return 1;
        		}
        	}
        
        @Override
        public String getTextureFile () {
                return CommonProxy.BLOCK_PNG;
        }

}