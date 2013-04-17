package co.uk.silvania.roads;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class RoadBlock extends Block {

        public RoadBlock (int id, int texture, Material material) {
                super(id, texture, material);
        }
        
        public int getBlockTextureFromSide(int side){
        	if(side == 0){
        		return blockIndexInTexture;
        	}else{
        		return 1;
        		
        	}
        	
        	
        	/*0 top
        	1 bottom
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