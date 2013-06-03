package co.uk.silvania.roads.liquid;

import co.uk.silvania.roads.Roads;
import net.minecraft.block.BlockStationary;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class StillTarBlock extends BlockStationary{
	
	public StillTarBlock(int par1) {
		super(par1, Material.water);

		this.blockHardness = 100.0F;
		this.setLightOpacity(3);
		this.disableStats();
		//this.setRequiresSelfNotify();
		}
	
	public void registerIcons(IconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon("Roads:LiquidTarStill");
	}
}