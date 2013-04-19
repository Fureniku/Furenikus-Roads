package co.uk.silvania.roads.block;

import co.uk.silvania.roads.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class RBWhiteCorner extends Block {

        public RBWhiteCorner (int id, int texture, Material material) {
                super(id, texture, material);
        }
        
        public int getBlockTextureFromSide(int side){
        	if(side == 1){
        		return 15;
        	}else{
        		return 1;
        		}
        	}
        
        @Override
        public String getTextureFile () {
                return CommonProxy.BLOCK_PNG;
        }

}