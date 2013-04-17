package co.uk.silvania.roads;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class MacadamBlock extends Block {

        public MacadamBlock (int id, int texture, Material material) {
                super(id, texture, material);
        }
        
        public String getTextureFile () {
                return CommonProxy.BLOCK_PNG;
        }

}