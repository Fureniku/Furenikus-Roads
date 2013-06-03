package co.uk.silvania.roads.block;

import java.util.Random;

import co.uk.silvania.roads.CommonProxy;
import co.uk.silvania.roads.Roads;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class LimeStoneBlock extends Block {
	
	public LimeStoneBlock (int id) {
    super(id, Material.rock);
    	this.setHardness(0.7F);
    	this.setStepSound(Block.soundStoneFootstep);
    	this.setCreativeTab(Roads.tabRoads);
	}
    
	public void registerIcons(IconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon("Roads:LimeStone");
	}
    
    public int idDropped(int par1, Random random, int par2) {
    	return (Roads.limeStonePowderItem.itemID);
    	
    }
    
    public int quantityDropped(Random par1Random)
    {
        return this.blockID == Roads.limeStoneBlock.blockID ? 4 : 1;
    }

}