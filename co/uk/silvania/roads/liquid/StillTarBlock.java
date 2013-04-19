package co.uk.silvania.roads.liquid;

import net.minecraft.block.BlockStationary;
import net.minecraft.block.material.Material;

public class StillTarBlock extends BlockStationary{
	
	public StillTarBlock(int par1) {
		super(par1, Material.water);

		this.blockHardness = 100.0F;
		this.setLightOpacity(3);
		this.disableStats();
		this.setRequiresSelfNotify();
		}
	
	public String getTextureFile(){
		return "/co/uk/silvania/roads/blocks.png";
		}

}
