package co.uk.silvania.roads;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class RBDoubleYellowStripe extends Block {

        public RBDoubleYellowStripe (int id, int texture, Material material) {
                super(id, texture, material);
        }
        
        public int getBlockTextureFromSide(int side){
        	if(side == 1){
        		return blockIndexInTexture;
        	}else{
        		return 1;
        		
        	}
        	
        	/*0 bottom
        	1 top
        	2
        	3
        	4
        	5*/
        }
        
        @Override
        public String getTextureFile () {
                return CommonProxy.BLOCK_PNG;
        }

}