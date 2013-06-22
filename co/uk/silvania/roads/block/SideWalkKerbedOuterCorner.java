package co.uk.silvania.roads.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import co.uk.silvania.roads.Roads;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SideWalkKerbedOuterCorner extends Block {

    public SideWalkKerbedOuterCorner (int id) {
        super(id, Material.rock);
        this.setHardness(1.0F);
        this.setStepSound(Block.soundStoneFootstep);
        this.setCreativeTab(Roads.tabRoads);
    }
    
	@SideOnly(Side.CLIENT)
	private Icon sides;
	@SideOnly(Side.CLIENT)
	private Icon sidesa;
	@SideOnly(Side.CLIENT)
	private Icon sidesb;
	@SideOnly(Side.CLIENT)
	private Icon sidesc;
	@SideOnly(Side.CLIENT)
	private Icon sidesd;
	@SideOnly(Side.CLIENT)
	private Icon sidese;
	@SideOnly(Side.CLIENT)
	private Icon base;
	@SideOnly(Side.CLIENT)
	private Icon top;
    
	public void registerIcons(IconRegister iconRegister) {
        this.top = iconRegister.registerIcon("Roads:SideWalkKerbedOuterCorner");
        this.sides = iconRegister.registerIcon("Roads:SideWalkKerbed");
        this.sidesa = iconRegister.registerIcon("Roads:SideWalkKerbeda");
        this.sidesb = iconRegister.registerIcon("Roads:SideWalkKerbedInnerCorner");
        this.sidesc = iconRegister.registerIcon("Roads:SideWalkKerbedInnerCornera");
        this.sidesd = iconRegister.registerIcon("Roads:SideWalkKerbedInnerCornerb");
        this.sidese = iconRegister.registerIcon("Roads:SideWalkKerbedb");
        this.base = iconRegister.registerIcon("Roads:sidewalkBlocks0");
	}
	
    public int getRenderType() {
        return 16;
        }

    public boolean isOpaqueCube() {
    	return false;
        }
        
    public boolean renderAsNormalBlock() {
    	return false;
        }

    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta) {
        int k = meta;
        if (k == 0 && (side == 1)) {
         	return top;
     	} else if (k == 1 && (side == 1)) {
           	return top;
       	} else if (k == 2 && (side == 1)) {
           	return top;
        } else if (k == 3 && (side == 1)) {
          	return top;
        } else if (k == 4 && (side == 1)) {
           	return top;
        } else if (k == 5 && (side == 1)) {
           	return top;
           	//Side textures for rotation 1
        } else if (k == 2 && side == 2) {
        	return sides;
        } else if (k == 2 && side == 4) {
        	return sidesb;
        } else if (k == 2 && side == 3) {
        	return sidesb;
        } else if (k == 2 && side == 5) {
        	return sidesa;
        	//Side textures for rotation 2
        } else if (k == 3 && side == 2) {
        	return sidesb;
        } else if (k == 3 && side == 4) {
        	return sidese;
        } else if (k == 3 && side == 3) {
        	return sides;
        } else if (k == 3 && side == 5) {
        	return sidesd;
        	//Side textures for rotation 3
        } else if (k == 4 && side == 2) {
        	return sidesa;
        } else if (k == 4 && side == 4) {
        	return sides;
        } else if (k == 4 && side == 3) {
        	return sidesb;
        } else if (k == 4 && side == 5) {
        	return sidesb;
        	//Side textures for rotation 4
        } else if (k == 5 && side == 2) {
        	return sidesd;
        } else if (k == 5 && side == 4) {
        	return sidesb;
        } else if (k == 5 && side == 3) {
        	return sidese;
        } else if (k == 5 && side == 5) {
        	return sides;
        } else
   		return base;
    }

    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving entity, ItemStack item) {
        int l = determineOrientation(par1World, par2, par3, par4, entity);
        par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
    }

    public static int determineOrientation(World world, int par1, int par2, int par3, EntityLiving entity) {
        int l = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        return l == 0 ? 3 : (l == 1 ? 4 : (l == 2 ? 2 : (l == 3 ? 5 : 0)));
    }
}
