package co.uk.silvania.roads.block;

import co.uk.silvania.roads.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class HardenedTarBlock extends Block {

    public HardenedTarBlock (int id, int texture, Material material) {
            super(id, texture, material);
    }
    
    @Override
    public String getTextureFile () {
            return CommonProxy.BLOCK_PNG;
    }

}