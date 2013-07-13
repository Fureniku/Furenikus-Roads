package co.uk.silvania.roads.block;

import co.uk.silvania.roads.Roads;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CatsEyeSide extends Block {

	public CatsEyeSide(int id) {
		super(id, Material.glass);
		this.setStepSound(Block.soundStoneFootstep);
		this.setCreativeTab(Roads.tabRoads);
        this.setLightValue(0.5F);
        this.setBlockBounds(0.4F, -0.175F, 0.4F, 0.6F, -0.125F, 0.6F);
	}
	
    public boolean renderAsNormalBlock() {
        return false;
    }
    
    public boolean isOpaqueCube() {
    	return false;
    }
    
    public void registerIcons(IconRegister iconRegister) {
    	blockIcon = iconRegister.registerIcon("roads:CatsEyeTop");
    }
    
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity, ItemStack itemStack) {
        int l = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        world.setBlockMetadataWithNotify(x, y, z, l, 2);
    }
    
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
    	int meta = world.getBlockMetadata(x, y, z);
    	if ((meta) == 0)
    		setBlockBounds(0.9F, -0.175F, 0.4F, 1.1F, -0.125F, 0.6F);
    	else if ((meta) == 1)
            setBlockBounds(0.4F, -0.175F, 0.9F, 0.6F, -0.125F, 1.1F);
    	else if ((meta) == 2)
    		setBlockBounds(-0.1F, -0.175F, 0.4F, 0.1F, -0.125F, 0.6F);
    	else
    		setBlockBounds(0.4F, -0.175F, -0.1F, 0.6F, -0.125F, 0.1F);
    }
}
