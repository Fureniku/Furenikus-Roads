package co.uk.silvania.roads.block.tess;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import co.uk.silvania.roads.Roads;
import co.uk.silvania.roads.client.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RoadRamp5 extends Block {

	public RoadRamp5(int i) {
		super(i, Material.rock);
		this.setCreativeTab(Roads.tabRoads);
		this.setHardness(1.0F);
	}

	public boolean isOpaqueCube() {
		return false;  
	}

	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public int getRenderType() {
		return ClientProxy.RoadsRampShortRenderID;
	}
	
    @SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon("roads:roadBlockArrows0");
	}
    
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return blockIcon;	
	}
}