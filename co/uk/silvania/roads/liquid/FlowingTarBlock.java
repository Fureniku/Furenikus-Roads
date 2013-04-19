package co.uk.silvania.roads.liquid;

import co.uk.silvania.roads.Roads;
import net.minecraft.block.BlockFlowing;
import net.minecraft.block.material.Material;

public class FlowingTarBlock extends BlockFlowing {
	
	public FlowingTarBlock(int par1) {
		super(par1, Material.water);

		this.blockHardness = 100.0F;
		this.setLightOpacity(3);
		this.setCreativeTab(Roads.tabRoads);
		}
	
	public String getTextureFile(){
		return "/co/uk/silvania/roads/blocks.png";
		}

}
