package co.uk.silvania.roads.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import co.uk.silvania.roads.FlenixRoads;
import co.uk.silvania.roads.client.ClientProxy;
import co.uk.silvania.roads.items.FRItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RoadBlock extends Block {
	
	public RoadBlock() {
		super(Material.rock);
		this.setHardness(1.5F);
		this.setCreativeTab(FlenixRoads.tabRoads);
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess block, int x, int y, int z) {
		this.setLightOpacity(0);
		int meta = block.getBlockMetadata(x, y, z);
		float height = ((float)meta + 1.0F) / 16.0F;
		
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, height, 1.0F);
		this.setBlockBoundsForItemRender();
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        return AxisAlignedBB.getBoundingBox(x + this.minX, y + this.minY, z + this.minZ, x + this.maxX, y + (((float)meta + 1.0F) / 16.0F), z + this.maxZ);
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType() {
		return ClientProxy.roadBlockRenderID;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister icon) {
		blockIcon = icon.registerIcon(FlenixRoads.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < 16; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	@Override
	public int damageDropped(int meta) {
		return meta;
	}
	
	public float height(int meta) {
		return (meta + 1) / 16;
	}
}
