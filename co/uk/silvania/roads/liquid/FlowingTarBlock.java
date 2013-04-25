package co.uk.silvania.roads.liquid;

import co.uk.silvania.roads.Roads;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockFlowing;
import net.minecraft.block.material.Material;

public class FlowingTarBlock extends BlockFlowing {
	
	public FlowingTarBlock(int par1) {
		super(par1, Material.water);

		this.blockHardness = 100.0F;
		this.setLightOpacity(0);
		this.setCreativeTab(Roads.tabRoads);
		}
    @SideOnly(Side.CLIENT)
    public int getBlockColor() {
        return 00000000;
    }
    
	public String getTextureFile(){
		return "/co/uk/silvania/roads/blocks.png";
		}

}
