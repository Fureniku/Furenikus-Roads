package co.uk.silvania.roads.tileentities;

import java.util.List;

import co.uk.silvania.roads.Roads;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class TileEntityTrafficLightBlock extends BlockContainer {

	public TileEntityTrafficLightBlock(int id) {
		super(id, Material.iron);
		this.setCreativeTab(Roads.tabRoads);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityTrafficLightEntity();
	}
	
    public void addCollisionBoxesToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity) {
    	this.setBlockBounds(0.4F, 0.0F, 0.4F, 0.6F, 3.0F, 0.6F);
    	super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
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
		this.blockIcon = icon.registerIcon("Roads:TrafficLightIcon");
	}

}
