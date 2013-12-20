package co.uk.silvania.roads.liquid;

import co.uk.silvania.roads.Roads;
import net.minecraft.block.BlockStationary;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;

public class StillTarBlock extends StillLiquidBlock {
	
	public StillTarBlock(int id) {
		super(id);
	}

	@Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        entity.motionX *= 0.5D;
    	entity.motionY *= 0.1D;
        entity.motionZ *= 0.5D;
        entity.attackEntityFrom(DamageSource.generic, 1.0F);
    }
	
	@Override
	public void registerIcons(IconRegister iconRegister) {
		this.theIcon = new Icon[] {iconRegister.registerIcon(Roads.modid + ":TarStill"), iconRegister.registerIcon(Roads.modid + ":TarFlowing")};
	}
}