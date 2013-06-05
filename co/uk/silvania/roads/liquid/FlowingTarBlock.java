package co.uk.silvania.roads.liquid;

import co.uk.silvania.roads.Roads;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockFlowing;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.liquids.ILiquid;

public class FlowingTarBlock extends BlockFlowing implements ILiquid {
    
    public FlowingTarBlock(int id) {
		super(id, Material.water);
		this.setCreativeTab(Roads.tabRoads);
		this.setHardness(100.0F);
		this.setLightOpacity(1);
		this.setTickRandomly(true);
	}

	
	@Override
	public int getRenderType() {
		return 4;
	}

	@Override
	public int stillLiquidId() {
		return this.blockID + 1;
	}

	@Override
	public boolean isMetaSensitive() {
		return false;
	}

	@Override
	public int stillLiquidMeta() {
		return 0;
	}
	
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        entity.motionX *= 0.8D;
    	entity.motionY *= 0.8D;
        entity.motionZ *= 0.8D;
    }
	
	public void registerIcons(IconRegister iconRegister) {
		this.theIcon = new Icon[] {iconRegister.registerIcon("Roads:TarFlowing"), iconRegister.registerIcon("Roads:TarFlowing")};
	}

}
