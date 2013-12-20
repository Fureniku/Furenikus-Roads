package co.uk.silvania.roads.roadblocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import co.uk.silvania.roads.Roads;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SidewalkBlockTri extends Block {
    public SidewalkBlockTri(int par1) {
        super(par1, Material.rock);
        this.setStepSound(soundStoneFootstep);
        this.setHardness(1.5F);
        this.setCreativeTab(Roads.tabRoads);
    }
    
	@SideOnly(Side.CLIENT)
	private Icon[] icons;
	@SideOnly(Side.CLIENT)
	private Icon side1;
	@SideOnly(Side.CLIENT)
	private Icon sidekerbed1;
	@SideOnly(Side.CLIENT)
	private Icon side2;
	@SideOnly(Side.CLIENT)
	private Icon sidekerbed2;
	@SideOnly(Side.CLIENT)
	private Icon side3;
	@SideOnly(Side.CLIENT)
	private Icon sidekerbed3;
	@SideOnly(Side.CLIENT)
	private Icon sidesmall1;
	@SideOnly(Side.CLIENT)
	private Icon sidesmall2;
	@SideOnly(Side.CLIENT)
	private Icon sidesmall3;
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack itemStack)
	{
	    int blockSet = world.getBlockMetadata(x, y, z) / 4;
	    int direction = MathHelper.floor_double((double)(entityliving.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
	    int newMeta = (blockSet * 4) + direction;
	    world.setBlockMetadataWithNotify(x, y, z, newMeta, 0);
	}
    	
    @SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		icons = new Icon[16];
		
		for(int i = 0; i < icons.length; i++) {
			icons[i] = iconRegister.registerIcon(Roads.modid + ":" + (this.getUnlocalizedName().substring(5)) + i);
			side1 = iconRegister.registerIcon(Roads.modid + ":sidewalkBlockGrey0");
			sidekerbed1 = iconRegister.registerIcon(Roads.modid + ":sidewalkBlockGrey4");
			sidesmall1 = iconRegister.registerIcon(Roads.modid + ":sidewalkBlockSides8");
			side2 = iconRegister.registerIcon(Roads.modid + ":sidewalkBlockLight0");
			sidekerbed2 = iconRegister.registerIcon(Roads.modid + ":sidewalkBlockLight4");
			sidesmall2 = iconRegister.registerIcon(Roads.modid + ":sidewalkBlockSides12");
			side3 = iconRegister.registerIcon(Roads.modid + ":sidewalkBlockTile0");
			sidekerbed3 = iconRegister.registerIcon(Roads.modid + ":sidewalkBlockTile4");
			sidesmall3 = iconRegister.registerIcon(Roads.modid + ":sidewalkBlockSides16");
		}
	}
    
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		if (side == 1) {
			return icons[meta];	
			
        	//Sidewalk Straight
        } else if (meta == 0 && side == 2) {
        	return sidekerbed1;//
        } else if (meta == 0 && side == 3) {
        	return sidesmall1;//
        } else if (meta == 0 && side == 4) {
        	return sidekerbed1;//
        } else if (meta == 0 && side == 5) {
        	return sidekerbed1;//
        	
        } else if (meta == 1 && side == 2) {
        	return sidekerbed1;//
        } else if (meta == 1 && side == 3) {
        	return sidekerbed1;//
        } else if (meta == 1 && side == 4) {
        	return sidesmall1;//
        } else if (meta == 1 && side == 5) {
        	return sidekerbed1;//
        	
        } else if (meta == 2 && side == 2) {
        	return sidesmall1;//
        } else if (meta == 2 && side == 3) {
        	return sidekerbed1;//
        } else if (meta == 2 && side == 4) {
        	return sidekerbed1;//
        } else if (meta == 2 && side == 5) {
        	return sidekerbed1;//
        	
        } else if (meta == 3 && side == 2) {
        	return sidekerbed1;//
        } else if (meta == 3 && side == 3) {
        	return sidekerbed1;//
        } else if (meta == 3 && side == 4) {
        	return sidekerbed1;//
        } else if (meta == 3 && side == 5) {
        	return sidesmall1;//
        	
        	
        	
			
        } else if (meta == 4 && side == 2) {
        	return sidekerbed2;//
        } else if (meta == 4 && side == 3) {
        	return sidesmall2;//
        } else if (meta == 4 && side == 4) {
        	return sidekerbed2;//
        } else if (meta == 4 && side == 5) {
        	return sidekerbed2;//
        	
        } else if (meta == 5 && side == 2) {
        	return sidekerbed2;//
        } else if (meta == 5 && side == 3) {
        	return sidekerbed2;//
        } else if (meta == 5 && side == 4) {
        	return sidesmall2;//
        } else if (meta == 5 && side == 5) {
        	return sidekerbed2;//
        	
        } else if (meta == 6 && side == 2) {
        	return sidesmall2;//
        } else if (meta == 6 && side == 3) {
        	return sidekerbed2;//
        } else if (meta == 6 && side == 4) {
        	return sidekerbed2;//
        } else if (meta == 6 && side == 5) {
        	return sidekerbed2;//
        	
        } else if (meta == 7 && side == 2) {
        	return sidekerbed2;//
        } else if (meta == 7 && side == 3) {
        	return sidekerbed2;//
        } else if (meta == 7 && side == 4) {
        	return sidekerbed2;//
        } else if (meta == 7 && side == 5) {
        	return sidesmall2;//
        	
        	
        	
        	
        } else if (meta == 8 && side == 2) {
        	return sidekerbed3;//
        } else if (meta == 8 && side == 3) {
        	return sidesmall3;//
        } else if (meta == 8 && side == 4) {
        	return sidekerbed3;//
        } else if (meta == 8 && side == 5) {
        	return sidekerbed3;//
        	
        } else if (meta == 9 && side == 2) {
        	return sidekerbed3;//
        } else if (meta == 9 && side == 3) {
        	return sidekerbed3;//
        } else if (meta == 9 && side == 4) {
        	return sidesmall3;//
        } else if (meta == 9 && side == 5) {
        	return sidekerbed3;//
        	
        } else if (meta == 10 && side == 2) {
        	return sidesmall3;//
        } else if (meta == 10 && side == 3) {
        	return sidekerbed3;//
        } else if (meta == 10 && side == 4) {
        	return sidekerbed3;//
        } else if (meta == 10 && side == 5) {
        	return sidekerbed3;//
        	
        } else if (meta == 11 && side == 2) {
        	return sidekerbed3;//
        } else if (meta == 11 && side == 3) {
        	return sidekerbed3;//
        } else if (meta == 11 && side == 4) {
        	return sidekerbed3;//
        } else if (meta == 11 && side == 5) {
        	return sidesmall3;//
        	
        	
        } else if (meta == 0 && side == 0) {
        	return side1;//
        } else if (meta == 1 && side == 0) {
        	return side1;//
        } else if (meta == 2 && side == 0) {
        	return side1;//
        } else if (meta == 3 && side == 0) {
        	return side1;//
        } else if (meta == 8 && side == 0) {
        	return side2;//
        } else if (meta == 9 && side == 0) {
        	return side2;//
        } else if (meta == 10 && side == 0) {
        	return side2;//
        } else if (meta == 11 && side == 0) {
        	return side2;//
        } else if (meta == 4 && side == 0) {
        	return side3;//
        } else if (meta == 5 && side == 0) {
        	return side3;//
        } else if (meta == 6 && side == 0) {
        	return side3;//
        } else if (meta == 7 && side == 0) {
        	return side3;//
        }
		return icons[0];
	}
    
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs creativeTabs, List list) {
		list.add(new ItemStack(par1, 1, 0));
		list.add(new ItemStack(par1, 1, 4));
		list.add(new ItemStack(par1, 1, 8));
	}

    @Override
    public int damageDropped(int meta) {
    	if (meta == 0 || meta == 1 || meta == 2 || meta == 3) {
    		return 0;
    	}
    	if (meta == 4 || meta == 5 || meta == 6 || meta == 7) {
    		return 4;
    	}
    	if (meta == 8 || meta == 9 || meta == 10 || meta == 11) {
    		return 8;
    	} else
    		return 12;
    }
}