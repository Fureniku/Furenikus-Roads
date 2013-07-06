package co.uk.silvania.roads.block;

import co.uk.silvania.roads.CommonProxy;
import co.uk.silvania.roads.Roads;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class HardenedTarBlock extends Block {

    public HardenedTarBlock (int id) {
        super(id, Material.rock);
        this.setHardness(3.5F);
        this.setStepSound(Block.soundStoneFootstep);
        this.setCreativeTab(Roads.tabRoads);
    }
        
    public void registerIcons(IconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon("roads:HardenedTar");
    }

}