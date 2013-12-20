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

public class SidewalkBlockGrey extends Block {
    public SidewalkBlockGrey(int par1) {
        super(par1, Material.rock);
        this.setStepSound(soundStoneFootstep);
        this.setHardness(1.5F);
        this.setCreativeTab(Roads.tabRoads);
    }
    
	@SideOnly(Side.CLIENT)
	private Icon[] icons;
	@SideOnly(Side.CLIENT)
	private Icon sides;
	
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
		}
	}
    
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		if (side == 1) {
			return icons[meta];	
			
        	//Sidewalk Straight
        } else if (meta == 4 && side == 2) {
        	return icons[4];//
        } else if (meta == 4 && side == 4) {
        	return icons[11];//
        } else if (meta == 4 && side == 5) {
        	return icons[8];//
        	
        } else if (meta == 5 && side == 5) {
        	return icons[4];
        } else if (meta == 5 && side == 2) {
        	return icons[11];
        } else if (meta == 5 && side == 3) {
        	return icons[8];
        	
        } else if (meta == 6 && side == 3) {
        	return icons[4];
        } else if (meta == 6 && side == 4) {
        	return icons[8];
        } else if (meta == 6 && side == 5) {
        	return icons[11];
        	
        } else if (meta == 7 && side == 4) {
        	return icons[4];
        } else if (meta == 7 && side == 2) {
        	return icons[8];
        } else if (meta == 7 && side == 3) {
        	return icons[11];
        	
        	
        	
        	
        	//Sidewalk Inner Corner
        } else if (meta == 8 && side == 2) {
        	return icons[11];//
        } else if (meta == 8 && side == 5) {
        	return icons[8];//
        	
        } else if (meta == 9 && side == 5) {
        	return icons[11];
        } else if (meta == 9 && side == 3) {
        	return icons[8];
        	
        } else if (meta == 10 && side == 3) {
        	return icons[11];
        } else if (meta == 10 && side == 4) {
        	return icons[8];
        	
        } else if (meta == 11 && side == 4) {
        	return icons[11];
        } else if (meta == 11 && side == 2) {
        	return icons[8];
        	
        	
        	
        	//Sidewalk Outer Corner
        } else if (meta == 12 && side == 2) {
        	return icons[4];
        } else if (meta == 12 && side == 3) {
        	return icons[11];
        } else if (meta == 12 && side == 4) {
        	return icons[4];
        } else if (meta == 12 && side == 5) {
        	return icons[8];
        	
        } else if (meta == 13 && side == 2) {
        	return icons[4];
        } else if (meta == 13 && side == 3) {
        	return icons[8];
        } else if (meta == 13 && side == 4) {
        	return icons[11];
        } else if (meta == 13 && side == 5) {
        	return icons[4];
        	
        } else if (meta == 14 && side == 2) {
        	return icons[11];
        } else if (meta == 14 && side == 3) {
        	return icons[4];
        } else if (meta == 14 && side == 4) {
        	return icons[8];
        } else if (meta == 14 && side == 5) {
        	return icons[4];
        	
        } else if (meta == 15 && side == 2) {
        	return icons[8];
        } else if (meta == 15 && side == 3) {
        	return icons[4];
        } else if (meta == 15 && side == 4) {
        	return icons[4];
        } else if (meta == 15 && side == 5) {
        	return icons[11];
        } else 
			return icons[0];

	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs creativeTabs, List list) {
		list.add(new ItemStack(par1, 1, 0));
		list.add(new ItemStack(par1, 1, 4));
		list.add(new ItemStack(par1, 1, 8));
		list.add(new ItemStack(par1, 1, 12));
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