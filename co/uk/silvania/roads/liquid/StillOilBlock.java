package co.uk.silvania.roads.liquid;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import co.uk.silvania.roads.Roads;

public class StillOilBlock extends StillLiquidBlock {

	public StillOilBlock(int id) {
		super(id);
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister) {
		this.theIcon = new Icon[] {iconRegister.registerIcon(Roads.modid + ":OilStill"), iconRegister.registerIcon(Roads.modid + ":OilFlowing")};
	}
	
    @Override
    public boolean isFlammable(IBlockAccess world, int x, int y, int z, int meta, ForgeDirection face) {
    	return true;
    }
    
    @Override
    public int getFlammability(IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face) {
            return 10;
    }
    
    @Override
    public int getFireSpreadSpeed(World world, int x, int y, int z, int metadata, ForgeDirection face) {
            return 250000000;
    }
}
