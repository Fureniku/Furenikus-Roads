package co.uk.silvania.roads.block;

import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.WEST;
import co.uk.silvania.roads.Roads;
import co.uk.silvania.roads.roadblocks.RoadBlock;
import co.uk.silvania.roads.roadblocks.RoadBlockMiscSingles;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class CatsEye extends Block {

	public CatsEye(int id) {
		super(id, Material.glass);
		this.setStepSound(Block.soundStoneFootstep);
		this.setCreativeTab(Roads.tabRoads);
        this.setBlockBounds(0.4F, -0.25F, 0.4F, 0.6F, -0.2F, 0.6F);
	}
	
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
    	int block = world.getBlockId(x, y - 1, z);
    	if (Block.blocksList[block] instanceof RoadBlock || Block.blocksList[block] instanceof RoadBlockMiscSingles) {
    		return true;
    	} else
    		return false;
    }
	
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        return null;
    }
	
	@SideOnly(Side.CLIENT)
	private Icon side1;
	@SideOnly(Side.CLIENT)
	private Icon top;
	
    public boolean renderAsNormalBlock() {
        return false;
    }
    
    public boolean isOpaqueCube() {
    	return false;
    }
    
    public void registerIcons(IconRegister iconRegister) {
    	this.top = iconRegister.registerIcon(Roads.modid + ":CatsEyeTop");
    	this.side1 = iconRegister.registerIcon(Roads.modid + ":CatsEyeSide1");
    }
    
    public Icon getIcon(int side, int meta) {
    	int k = meta;
        if (side == 1) {
         	return top;
        } else
        	return side1;
    }

}
