package co.uk.silvania.roads.tileentities;

import co.uk.silvania.roads.Roads;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityLightBollardBlock extends BlockContainer {

	public TileEntityLightBollardBlock(int id) {
		super(id, Material.iron);
		this.setCreativeTab(Roads.tabRoads);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityLightBollardEntity();
	}
	
	@Override
	public int getRenderType() {
		return -1;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	public void registerIcons(IconRegister icon) {
		this.blockIcon = icon.registerIcon("Roads:CementBlock");
	}

}
