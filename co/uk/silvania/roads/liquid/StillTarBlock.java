package co.uk.silvania.roads.liquid;

import co.uk.silvania.roads.Roads;
import net.minecraft.block.BlockStationary;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraftforge.liquids.ILiquid;

public class StillTarBlock extends BlockStationary implements ILiquid {
	
	public StillTarBlock(int id) {
		super(id, Material.water);
		this.setHardness(100.0F);
		this.setLightOpacity(0);
		this.setTickRandomly(true);
	}
	
	@Override
	public int getRenderType() {
		return 4;
	}

	@Override
	public int stillLiquidId() {
		return this.blockID;
	}

	@Override
	public boolean isMetaSensitive() {
		return false;
	}

	@Override
	public int stillLiquidMeta() {
		return 0;
	}
	
	public void registerIcons(IconRegister iconRegister) {
		this.theIcon = new Icon[] {iconRegister.registerIcon("Roads:TarStill"), iconRegister.registerIcon("Roads:TarFlowing")};
	}
}