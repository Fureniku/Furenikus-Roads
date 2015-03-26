package co.uk.silvania.roads.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import co.uk.silvania.roads.FlenixRoads;
import co.uk.silvania.roads.client.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LineBlock extends Block {

	public LineBlock() {
		super(Material.rock);
		this.setCreativeTab(FlenixRoads.tabRoads);
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess block, int x, int y, int z) {
		this.setLightOpacity(0);
		int mountBlockMeta = block.getBlockMetadata(x, y-1, z);
		float height = (((float)mountBlockMeta + 1.0F) / 16.0F) - 1.0F;
		this.setBlockBounds(0.0F, height + 0.01F, 0.0F, 1.0F, height + 0.02F, 1.0F);
		this.setBlockBoundsForItemRender();
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		if (!(world.getBlock(x, y-1, z) instanceof RoadBlock)) {
			//System.out.println("Should break");
			world.setBlockToAir(x, y, z);
		}
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		int mountBlockMeta = world.getBlockMetadata(x, y-1, z);
		float height = (((float)mountBlockMeta + 1.0F) / 16.0F) - 1.0F;
        return AxisAlignedBB.getBoundingBox(x + this.minX, y + height + 0.01F, z + this.minZ, x + this.maxX, y + height + 0.02F, z + this.maxZ);
    }
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		if (world.getBlock(x, y-1, z) instanceof RoadBlock) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		if (world.getBlock(x, y-1, z) instanceof RoadBlock) {
			return true;
		}
		return false;
	}
	
	@Override
	public int getRenderType() {
		return ClientProxy.roadPaintRenderID;
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
	private IIcon[] icons;
	@SideOnly(Side.CLIENT)
	private IIcon[] ctIcons0;
	@SideOnly(Side.CLIENT)
	private IIcon sideIcon;
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister icon) {
		icons = new IIcon[16];
		ctIcons0 = new IIcon[72];
		for (int meta = 0; meta < 15; meta++) {
			icons[meta] = icon.registerIcon(FlenixRoads.modid + ":" + (this.getUnlocalizedName().substring(5)) + "_" + meta);
			for (int c = 0; c < 9; c++) {
				if (meta == 0) {
					int v = c + meta;
					ctIcons0[v] = icon.registerIcon(FlenixRoads.modid + ":" + (this.getUnlocalizedName().substring(5)) + "_" + meta + "_" + c);
				}
			}
		}

		sideIcon = icon.registerIcon(FlenixRoads.modid + ":" + "blank");
	}
	


	
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(IBlockAccess p_149673_1_, int p_149673_2_, int p_149673_3_, int p_149673_4_, int p_149673_5_)
    {
        return getConnectedBlockTexture(p_149673_1_, p_149673_2_, p_149673_3_, p_149673_4_, p_149673_5_, icons);
    }
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack item) {
		int blockSet = world.getBlockMetadata(x, y, z) / 4;
		int direction = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360F) + 2.5D) & 3;
		if (direction == 2) {
			direction = 0;
		} else if (direction == 3) {
			direction = 1;
		}
		//System.out.println("direction: " + direction);
		int newMeta = (blockSet * 4) + direction;
		//System.out.println("Meta: " + newMeta);
		world.setBlockMetadataWithNotify(x, y, z, newMeta, 0);
	}
	
    public boolean shouldConnectToBlock (IBlockAccess blockAccess, int x, int y, int z, Block block, int meta) {
        return block == (Block) this;
    }
	
	public IIcon getConnectedBlockTexture (IBlockAccess block, int x, int y, int z, int side, IIcon[] icons) {
    	int meta = block.getBlockMetadata(x, y, z);
        boolean connectUp = false, connectDown = false, connectLeft = false, connectRight = false;

        if (side == 1) {
            if (shouldConnectToBlock(block, x, y, z, block.getBlock(x - 1, y, z), block.getBlockMetadata(x - 1, y, z))) {
                connectDown = true;
            }

            if (shouldConnectToBlock(block, x, y, z, block.getBlock(x + 1, y, z), block.getBlockMetadata(x + 1, y, z))) {
                connectUp = true;
            }

            if (shouldConnectToBlock(block, x, y, z, block.getBlock(x, y, z - 1), block.getBlockMetadata(x, y, z - 1))) {
                connectLeft = true;
            }

            if (shouldConnectToBlock(block, x, y, z, block.getBlock(x, y, z + 1), block.getBlockMetadata(x, y, z + 1))) {
                connectRight = true;
            }
            
            int smeta = 0;
            int lmeta = 0;
            
            if (meta == 0 || (meta & 1) == 0) {
            	smeta = meta;
            	if (meta > 0) {
            		lmeta = meta + 1;
            	}
            } else {
            	smeta = meta - 1;
            	lmeta = meta;
            }

            //System.out.println("Current Connections: Up: " + connectUp + ", Down: " + connectDown + ", Left: " + connectLeft + ", Right: " + connectRight);
            
            if (connectUp && connectDown && connectLeft && connectRight) {
                return ctIcons0[8 + smeta];
            } else if (connectUp && connectDown && connectLeft) {
                return ctIcons0[7 + smeta];
            } else if (connectUp && connectDown && connectRight) {
                return ctIcons0[5 + smeta];
            } else if (connectUp && connectLeft && connectRight) {
                return ctIcons0[4 + smeta];
            } else if (connectDown && connectLeft && connectRight) {
                return ctIcons0[6 + smeta];
            } else if (connectDown && connectLeft) {
                return ctIcons0[3 + smeta];
            } else if (connectDown && connectRight) {
                return ctIcons0[2 + smeta];
            } else if (connectUp && connectLeft) {
                return ctIcons0[0 + smeta];
            } else if (connectUp && connectRight) {
                return ctIcons0[1 + smeta];
            } else if (connectUp && connectDown || connectUp || connectDown) {
            	return icons[lmeta];
            } else if (connectLeft && connectRight || connectLeft || connectRight) {
            	return icons[smeta];
            }
        }
        return icons[meta];
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < 15; i++) {
			list.add(new ItemStack(item, 1, i));
		}
		/*list.add(new ItemStack(item, 1, 2)); //0=0.25, 1=0.5, 2=0.75, 3=1.0 height
		list.add(new ItemStack(item, 1, 6));
		list.add(new ItemStack(item, 1, 10));
		list.add(new ItemStack(item, 1, 14));*/
	}
}
