package co.uk.silvania.roads.block;

import java.util.Random;

import co.uk.silvania.roads.CommonProxy;
import co.uk.silvania.roads.Roads;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class LimeStoneBlock extends Block {

    public LimeStoneBlock (int id, int texture, Material material) {
            super(id, texture, material);
    }
    
    @Override
    public String getTextureFile () {
            return CommonProxy.BLOCK_PNG;
    }
    
    public int idDropped(int par1, Random random, int par2) {
    	return Roads.limeStonePowderItem.itemID;
    }

}