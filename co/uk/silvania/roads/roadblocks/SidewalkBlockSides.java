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

public class SidewalkBlockSides extends Block {
    public SidewalkBlockSides(int par1) {
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
	private Icon sidesmall1;
	@SideOnly(Side.CLIENT)
	private Icon sidesmall2;
	@SideOnly(Side.CLIENT)
	private Icon sidesmall3;
	@SideOnly(Side.CLIENT)
	private Icon sidesmall4;
	
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
			icons[i] = iconRegister.registerIcon("Roads:" + (this.getUnlocalizedName().substring(5)) + i);
			side1 = iconRegister.registerIcon("Roads:sidewalkBlockGrey0");
			sidekerbed1 = iconRegister.registerIcon("Roads:sidewalkBlockGrey4");
			side2 = iconRegister.registerIcon("Roads:sidewalkBlockLight0");
			sidekerbed2 = iconRegister.registerIcon("Roads:sidewalkBlockLight4");
			sidesmall1 = iconRegister.registerIcon("Roads:sidewalkBlockGrey8");
			sidesmall2 = iconRegister.registerIcon("Roads:sidewalkBlockGrey11");
			sidesmall3 = iconRegister.registerIcon("Roads:sidewalkBlockLight8");
			sidesmall4 = iconRegister.registerIcon("Roads:sidewalkBlockLight11");
		}
	}
    
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		if (side == 1) {
			return icons[meta];	
			
        	//Sidewalk Straight
        } else if (meta == 0 && side == 2) {
        	return icons[8];//
        } else if (meta == 0 && side == 3) {
        	return icons[8];//
        } else if (meta == 0 && side == 4) {
        	return sidekerbed1;//
        } else if (meta == 0 && side == 5) {
        	return sidekerbed1;//
        	
        } else if (meta == 1 && side == 2) {
        	return sidekerbed1;//
        } else if (meta == 1 && side == 3) {
        	return sidekerbed1;//
        } else if (meta == 1 && side == 4) {
        	return icons[8];//
        } else if (meta == 1 && side == 5) {
        	return icons[8];//
        	
        } else if (meta == 2 && side == 2) {
        	return icons[8];//
        } else if (meta == 2 && side == 3) {
        	return icons[8];//
        } else if (meta == 2 && side == 4) {
        	return sidekerbed1;//
        } else if (meta == 2 && side == 5) {
        	return sidekerbed1;//
        	
        } else if (meta == 3 && side == 2) {
        	return sidekerbed1;//
        } else if (meta == 3 && side == 3) {
        	return sidekerbed1;//
        } else if (meta == 3 && side == 4) {
        	return icons[8];//
        } else if (meta == 3 && side == 5) {
        	return icons[8];//
			
        	//Sidewalk Straight
        } else if (meta == 4 && side == 2) {
        	return icons[12];//
        } else if (meta == 4 && side == 3) {
        	return icons[12];//
        } else if (meta == 4 && side == 4) {
        	return sidekerbed2;//
        } else if (meta == 4 && side == 5) {
        	return sidekerbed2;//
        	
        } else if (meta == 5 && side == 2) {
        	return sidekerbed2;//
        } else if (meta == 5 && side == 3) {
        	return sidekerbed2;//
        } else if (meta == 5 && side == 4) {
        	return icons[12];//
        } else if (meta == 5 && side == 5) {
        	return icons[12];//
        	
        } else if (meta == 6 && side == 2) {
        	return icons[12];//
        } else if (meta == 6 && side == 3) {
        	return icons[12];//
        } else if (meta == 6 && side == 4) {
        	return sidekerbed2;//
        } else if (meta == 6 && side == 5) {
        	return sidekerbed2;//
        	
        } else if (meta == 7 && side == 2) {
        	return sidekerbed2;//
        } else if (meta == 7 && side == 3) {
        	return sidekerbed2;//
        } else if (meta == 7 && side == 4) {
        	return icons[12];//
        } else if (meta == 7 && side == 5) {
        	return icons[12];//
        	
        	
        	
        	
        	//Sidewalk Inner Corner
        } else if (meta == 8 && side == 2) {
        	return icons[8];//
        } else if (meta == 8 && side == 3) {
        	return side1;//
        } else if (meta == 8 && side == 4) {
        	return sidesmall2;//
        } else if (meta == 8 && side == 5) {
        	return sidesmall1;//
        	
        } else if (meta == 9 && side == 2) {
        	return sidesmall2;//
        } else if (meta == 9 && side == 3) {
        	return sidesmall1;//
        } else if (meta == 9 && side == 4) {
        	return side1;//
        } else if (meta == 9 && side == 5) {
        	return icons[8];//
        	
        } else if (meta == 10 && side == 2) {
        	return side1;//
        } else if (meta == 10 && side == 3) {
        	return icons[8];//
        } else if (meta == 10 && side == 4) {
        	return sidesmall1;//
        } else if (meta == 10 && side == 5) {
        	return sidesmall2;//
        	
        } else if (meta == 11 && side == 2) {
        	return sidesmall1;//
        } else if (meta == 11 && side == 3) {
        	return sidesmall2;//
        } else if (meta == 11 && side == 4) {
        	return icons[8];//
        } else if (meta == 11 && side == 5) {
        	return side1;//
        	
        	
        	
        	//Sidewalk Outer Corner
        } else if (meta == 12 && side == 2) {
        	return icons[12];//
        } else if (meta == 12 && side == 3) {
        	return side2;//
        } else if (meta == 12 && side == 4) {
        	return sidesmall2;//
        } else if (meta == 12 && side == 5) {
        	return sidesmall1;//
        	
        } else if (meta == 13 && side == 2) {
        	return sidesmall2;//
        } else if (meta == 13 && side == 3) {
        	return sidesmall1;//
        } else if (meta == 13 && side == 4) {
        	return side2;//
        } else if (meta == 13 && side == 5) {
        	return icons[12];//
        	
        } else if (meta == 14 && side == 2) {
        	return side2;//
        } else if (meta == 14 && side == 3) {
        	return icons[12];//
        } else if (meta == 14 && side == 4) {
        	return sidesmall1;//
        } else if (meta == 14 && side == 5) {
        	return sidesmall2;//
        	
        } else if (meta == 15 && side == 2) {
        	return sidesmall1;//
        } else if (meta == 15 && side == 3) {
        	return sidesmall2;//
        } else if (meta == 15 && side == 4) {
        	return icons[12];//
        } else if (meta == 15 && side == 5) {
        	return side2;//
        	
        	
        } else if (meta == 0 && side == 0) {
        	return side1;//
        } else if (meta == 1 && side == 0) {
        	return side1;//
        } else if (meta == 2 && side == 0) {
        	return side1;//
        } else if (meta == 3 && side == 0) {
        	return side1;//
        } else if (meta == 8 && side == 0) {
        	return side1;//
        } else if (meta == 9 && side == 0) {
        	return side1;//
        } else if (meta == 10 && side == 0) {
        	return side1;//
        } else if (meta == 11 && side == 0) {
        	return side1;//
        	
        } else if (meta == 4 && side == 0) {
        	return side2;//
        } else if (meta == 5 && side == 0) {
        	return side2;//
        } else if (meta == 6 && side == 0) {
        	return side2;//
        } else if (meta == 7 && side == 0) {
        	return side2;//
        } else if (meta == 12 && side == 0) {
        	return side2;//
        } else if (meta == 13 && side == 0) {
        	return side2;//
        } else if (meta == 14 && side == 0) {
        	return side2;//
        } else if (meta == 15 && side == 0) {
        	return side2;//
        }
		return icons[0];
	}

    public int getRenderType() {
    	return 0;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }
    
	public boolean renderAsNormalBlock() {
		return false;
	}
    
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs creativeTabs, List list) {
		list.add(new ItemStack(par1, 1, 0));
		list.add(new ItemStack(par1, 1, 4));
		list.add(new ItemStack(par1, 1, 8));
	}
}