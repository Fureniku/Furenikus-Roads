package co.uk.silvania.roads;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class RBST extends Block {

    public RBST (int id, int texture, Material material) {
            super(id, texture, material);
    }
    
    public int getBlockTextureFromSide(int side){
    	if(side == 1){
    		return 36;
    	}else{
    		return 1;
    		}
    	}
    
    @Override
    public String getTextureFile () {
            return CommonProxy.BLOCK_PNG;
    }

}