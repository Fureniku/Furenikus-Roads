package co.uk.silvania.roads.block;

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

public class GeneralBlocks extends Block {
    public GeneralBlocks(int par1) {
        super(par1, Material.rock);
        this.setStepSound(soundStoneFootstep);
        this.setHardness(1.5F);
        this.setCreativeTab(Roads.tabRoads);
    }
    
    public void roadBlockBounds(float par1, float par2, float par3, float par4, float par5, float par6)
    {
        this.setBlockBounds(par1, par2, par3, par4, par5, par6);
    }
    
	@SideOnly(Side.CLIENT)
	private Icon[] icons;
    	
    @SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		icons = new Icon[8];
		for(int i = 0; i < icons.length; i++) {
			icons[i] = iconRegister.registerIcon("Roads:" + (this.getUnlocalizedName().substring(5)) + i);
		}
	}
    
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return icons[meta];	

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
		for (int var4 = 0; var4 < 8; ++var4) {
			list.add(new ItemStack(par1, 1, var4));
		}
	}

    @Override
    public int damageDropped(int meta) {
    	return meta;
    }
}