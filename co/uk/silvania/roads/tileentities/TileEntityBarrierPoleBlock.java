package co.uk.silvania.roads.tileentities;

import co.uk.silvania.roads.Roads;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityBarrierPoleBlock extends BlockContainer {

	public TileEntityBarrierPoleBlock(int id) {
		super(id, Material.iron);
		this.setCreativeTab(Roads.tabRoads);
    	this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 0.125F);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityBarrierPoleEntity();
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
