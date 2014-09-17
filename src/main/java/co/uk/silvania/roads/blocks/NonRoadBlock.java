package co.uk.silvania.roads.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import co.uk.silvania.roads.FlenixRoads;
import co.uk.silvania.roads.client.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class NonRoadBlock extends Block {
	
	//This is exactly the same as a road block, but specifically DOESN'T extend road block. This stops it connecting to road blocks (eg, for sidewalks)
	public NonRoadBlock() {
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
	
	/*@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        int meta = world.getBlockMetadata(x, y, z);
		if (meta > 0) {
			System.out.println("Meta: " + meta);
        	if (player.getHeldItem().getItem() == FRItems.impactWrench) {
        		System.out.println("Using impact wrench");
        		world.setBlockMetadataWithNotify(x, y, z, meta - 1, 3);
        	}
        }
		if (meta < 16) {
			System.out.println("Meta: " + meta);
			if (player.getHeldItem().getItem() == FRItems.tarmacCutter) {
        		System.out.println("Using tarmac cutter");
				world.setBlockMetadataWithNotify(x, y, z, meta + 1, 3);
			}
		}
		
		return false;
    }*/
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        return AxisAlignedBB.getBoundingBox(x + this.minX, y + this.minY, z + this.minZ, x + this.maxX, y + (((float)meta + 1.0F) / 16.0F), z + this.maxZ);
    }
	
	@Override
	public int getRenderType() {
		return ClientProxy.nonRoadBlockRenderID;
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
		blockIcon = icon.registerIcon(FlenixRoads.modid + ":" + (this.getUnlocalizedName().substring(5)) + "_0");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < 16; i++) {
			list.add(new ItemStack(item, 1, i));
		}
		/*list.add(new ItemStack(item, 1, 2)); //0=0.25, 1=0.5, 2=0.75, 3=1.0 height
		list.add(new ItemStack(item, 1, 6));
		list.add(new ItemStack(item, 1, 10));
		list.add(new ItemStack(item, 1, 14));*/
	}
	
	@Override
	public int damageDropped(int meta) {
		return meta;
	}
	
	public float height(int meta) {
		return (meta + 1) / 16;
	}
}
