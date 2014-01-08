package co.uk.silvania.roads.liquid;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import co.uk.silvania.roads.Roads;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTar extends BlockFluidClassic {

	public BlockTar(int id, Fluid fluid) {
		super(id, fluid, Material.water);
		this.setDensity(2025);
		this.setTemperature(425);
		//this.setViscosity(1);
	}
	
	@SideOnly(Side.CLIENT)
	protected Icon stillIcon;
	@SideOnly(Side.CLIENT)
	protected Icon flowingIcon;
	
	@Override
	public Icon getIcon(int side, int meta) {
		return (side == 0 || side == 1) ? stillIcon : flowingIcon;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister icon) {
		stillIcon = icon.registerIcon(Roads.modid + ":TarStill");
		flowingIcon = icon.registerIcon(Roads.modid + ":TarFlowing");
	}
	
	@Override
	public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
		if (world.getBlockMaterial(x, y, z).isLiquid()) return false;
		return super.canDisplace(world, x, y, z);
	}
	
    @Override
    public boolean displaceIfPossible(World world, int x, int y, int z) {
            if (world.getBlockMaterial(x,  y,  z).isLiquid()) return false;
            return super.displaceIfPossible(world, x, y, z);
    }
	

}
