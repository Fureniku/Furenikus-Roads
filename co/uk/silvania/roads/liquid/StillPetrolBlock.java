package co.uk.silvania.roads.liquid;

import co.uk.silvania.roads.Roads;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class StillPetrolBlock extends StillLiquidBlock {

	public StillPetrolBlock(int id) {
		super(id);
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister) {
		this.theIcon = new Icon[] {iconRegister.registerIcon(Roads.modid + ":PetrolStill"), iconRegister.registerIcon(Roads.modid + ":PetrolFlowing")};
	}
	
    @Override
    public boolean isFlammable(IBlockAccess world, int x, int y, int z, int meta, ForgeDirection face) {
    	return true;
    }
    
    @Override
    public int getFlammability(IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face) {
    	return 300;
    }
    
    @Override
    //TURN IT UP TO 11 BITCHES
    public int getFireSpreadSpeed(World world, int x, int y, int z, int metadata, ForgeDirection face) {
    	return 250000000;
    }
}
