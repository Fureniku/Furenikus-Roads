package co.uk.silvania.roads.blocks;

import co.uk.silvania.roads.FlenixRoads;
import co.uk.silvania.roads.client.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class GrassRoadBlock extends RoadBlock {
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType() {
		return ClientProxy.grassRoadRenderID;
	}
	
	@SideOnly(Side.CLIENT) private IIcon iconGrassTop;
	@SideOnly(Side.CLIENT) private IIcon iconGrassSide;
	@SideOnly(Side.CLIENT) private IIcon iconDirt;
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister icon) {
		iconGrassTop = 	icon.registerIcon("minecraft:grass_top");
		iconGrassSide = icon.registerIcon("minecraft:grass_side_overlay");
		iconDirt = 		icon.registerIcon("minecraft:dirt");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		if (side == 1) {
			return iconGrassSide;
		}
		if (side == 2) {
			return iconDirt;
		}
		return iconGrassTop;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(IBlockAccess block, int x, int y, int z, int side) {
		if (side == 1) {
			return iconGrassSide;
		} else if (side == 2) {
			return iconDirt;
		}
		return iconGrassTop;
	}

}
