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
		this.setCreativeTab(FlenixRoads.tabPaints);
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
	@Override
	public void registerBlockIcons(IIconRegister icon) {
		icons = new IIcon[17];
		
		for (int i = 0; i < icons.length; i++) {
			icons[i] = icon.registerIcon(FlenixRoads.modid + ":" + (this.getUnlocalizedName().substring(5)) + "_" + i);
		}
	}
	
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(IBlockAccess block, int x, int y, int z, int side){
    	//MUST BE Y+1! This is actually only ever called from the road block which is always below the line.
    	//The line can't exist without the road, which is why the road is where the texture is actually rendered.
        return getConnectedBlockTexture(block, x, y+1, z, side, icons);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
    	if (meta <= 1) {
    		return icons[0];
    	} else {
    		return icons[11];
    	}
    }
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack item) {
		int meta = world.getBlockMetadata(x, y, z);
		int direction = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360F) + 2.5D) & 3;

		if (meta <= 1) {
			if (direction == 2) {
				direction = 0;
			} else if (direction == 3) {
				direction = 1;
			}
		}
		
		int newMeta = meta + direction;
		world.setBlockMetadataWithNotify(x, y, z, newMeta, 0);
	}
	
    public boolean shouldConnectToBlock(IBlockAccess blockAccess, int x, int y, int z, Block block, int compMeta) {
    	String unloc = blockAccess.getBlock(x, y, z).getUnlocalizedName();
    	int meta = blockAccess.getBlockMetadata(x, y, z);
    	if (unloc.equals("lineBlock3") || unloc.equals("lineBlock3")) {
    		if (block.getUnlocalizedName().equals("iconBlock1")) {
    			if (meta <= 1) {
    				if (compMeta <= 7) {
    					return true;
    				}
    			}
    		}
    	}
        return block == (Block) this;
    }
	
	public IIcon getConnectedBlockTexture(IBlockAccess block, int x, int y, int z, int side, IIcon[] icons) {
		//Icon IDs: 0: Meta 0, North/south. 1: Meta 1, East/west
		//2: North/east 3: South/east 4: South/west 5: North/west
		//6: North/east/south 7: east/south/west 8: south/west/north 9:west/north/east
		//10: All four
		
    	int meta = block.getBlockMetadata(x, y, z);
        boolean connectNorth = false, connectSouth = false, connectWest = false, connectEast = false;
        if (shouldConnectToBlock(block, x, y, z, block.getBlock(x - 1, y, z), block.getBlockMetadata(x - 1, y, z))) {
            connectWest = true;
        }

        if (shouldConnectToBlock(block, x, y, z, block.getBlock(x + 1, y, z), block.getBlockMetadata(x + 1, y, z))) {
            connectEast = true;
        }

        if (shouldConnectToBlock(block, x, y, z, block.getBlock(x, y, z - 1), block.getBlockMetadata(x, y, z - 1))) {
            connectNorth = true;
        }

        if (shouldConnectToBlock(block, x, y, z, block.getBlock(x, y, z + 1), block.getBlockMetadata(x, y, z + 1))) {
            connectSouth = true;
        }
        if (meta == 0 || meta == 1) {
            if (connectNorth && connectSouth && connectWest && connectEast) {
                return icons[10];
            } else if (connectNorth && connectSouth && connectWest) {
                return icons[8];
            } else if (connectNorth && connectSouth && connectEast) {
                return icons[6];
            } else if (connectNorth && connectWest && connectEast) {
                return icons[9];
            } else if (connectSouth && connectWest && connectEast) {
                return icons[7];
            } else if (connectSouth && connectWest) {
                return icons[4];
            } else if (connectSouth && connectEast) {
                return icons[3];
            } else if (connectNorth && connectWest) {
                return icons[5];
            } else if (connectNorth && connectEast) {
                return icons[2];
            } else if (connectNorth && connectSouth) {
            	return icons[0];
            } else if (connectWest && connectEast) {
            	return icons[1];
	        } else if (meta == 0 && connectWest) {
	        	return icons[8];
	        } else if (meta == 0 && connectEast) {
	        	return icons[6];
	        } else if (meta == 1 && connectNorth) {
	        	return icons[9];
	        } else if (meta == 1 && connectSouth) {
	        	return icons[7];
	        } else {
	        	return icons[meta];
	        }
            //DIAGONALS
    		//11: north/east 12: south/east 13: south/west 14: north/west
    		//15: NW/SE 16: NE/SW
        } else if (meta <= 5) {
        	if (meta == 2 || meta == 4) {
         		if (connectNorth && connectEast && connectSouth && connectWest) {
         			return icons[15];
         		} else {
         			if (meta == 2) {
         				return icons[11];
         			} else {
         				return icons[13];
         			}
         		}
        	}
         	if (meta == 3 || meta == 5) {
         		if (connectNorth && connectEast && connectSouth && connectWest) {
         			return icons[16];
         		} else {
         			if (meta == 3) {
         				return icons[12];
         			} else {
         				return icons[14];
         			}
         		}
         	}
         	if (connectNorth && connectWest) {
        		return icons[14];
        	} else if (connectNorth && connectEast) {
        		return icons[11];
        	} else if (connectSouth&& connectEast) {
         		return icons[13];
         	} else if (connectSouth && connectEast) {
         		return icons[12];
         	}
        }
        return icons[0];
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 2));
	}
}
