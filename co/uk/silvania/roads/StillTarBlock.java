package co.uk.silvania.roads;

import net.minecraft.block.BlockFlowing;
import net.minecraft.block.BlockStationary;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraftforge.liquids.ILiquid;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class StillTarBlock extends BlockStationary {
	
	private int blockIndextInTexture;

	protected StillTarBlock(int par1) {
		super(par1, Material.lava);
	this.blockHardness = 100F;
	this.setLightOpacity(3);
	this.setRequiresSelfNotify();
	this.blockIndextInTexture = 16;
	
	}
	
	public String getTextureFile() {
		return "/co/uk/silvania/roads/blocks.png";
	}

}
