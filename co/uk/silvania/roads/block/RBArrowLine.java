package co.uk.silvania.roads.block;

import co.uk.silvania.roads.CommonProxy;
import co.uk.silvania.roads.Roads;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class RBArrowLine extends Block {

    public RBArrowLine (int id) {
        super(id, Material.rock);
        this.setHardness(1.0F);
		this.setStepSound(Block.soundStoneFootstep);
		this.setCreativeTab(Roads.tabRoads);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.825F, 1.0F);
    }
       
	@SideOnly(Side.CLIENT)
	private Icon sides;
	@SideOnly(Side.CLIENT)
	private Icon top;

	public void registerIcons(IconRegister iconRegister) {
        this.top = iconRegister.registerIcon("Roads:TarmacArrowLine");
        this.sides = iconRegister.registerIcon("Roads:TarmacPlain");
	}
	
    public boolean renderAsNormalBlock() {
        return false;
    }
    
    public boolean isOpaqueCube() {
    	return false;
    }
   
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
    {
        int j1 = meta & 3;
        byte b0 = 0;

        switch (side)
        {
            case 0:
            case 1:
                b0 = 0;
                break;
            case 2:
            case 3:
                b0 = 8;
                break;
            case 4:
            case 5:
                b0 = 4;
        }

        return j1 | b0;
    }

    //Rotate the top-facing texture.
    //They told me it couldn't be done... THEY LIED. #MinecratForgeTuts
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        int k = meta & 12;
        if (k == 0 && (side == 1)) {
        	return top;
    	} else if (k == 4 && (side == 1)) {
        	return top;
        } else if (k == 8 && (side == 1)) {
        	return top;
        }
		return sides;
    }
}