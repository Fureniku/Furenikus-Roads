package co.uk.silvania.roads.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import co.uk.silvania.roads.FlenixRoads;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class NonRoadBlockCT extends NonRoadBlock {
	
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		icons = new IIcon[24];
		for (int i = 0; i < icons.length; i++) {
			icons[i] = iconRegister.registerIcon(FlenixRoads.modid + ":" + (this.getUnlocalizedName().substring(5)) + "_" + i);
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		return icons[23];
	}
	
	@Override
	public IIcon getIcon(IBlockAccess block, int x, int y, int z, int side) {
		return getConnectedBlockTexture(block, x, y, z, side, icons);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs ctabs, List list) {
		for (int i = 0; i < 16; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	@Override
	public int damageDropped(int meta) {
		return meta;
	}
	
    public boolean shouldConnectToBlock (IBlockAccess block, int x, int y, int z, Block ctBlock, int ctMeta) {
        return ctBlock == this;
    }
	
    public IIcon getConnectedBlockTexture (IBlockAccess block, int x, int y, int z, int side, IIcon[] icons) {
        boolean connectUp = false, connectDown = false, connectLeft = false, connectRight = false, 
        		connectLeftUp = false, connectRightUp = false, connectLeftDown = false, connectRightDown = false,
        		connectLeftUpDown = false, connectRightUpDown = false, connectUpLeftRight = false, connectDownLeftRight = false;

        if (side == 1) {
            //Down
        	if (shouldConnectToBlock(block, x, y, z, block.getBlock(x - 1, y, z), block.getBlockMetadata(x - 1, y, z))) {
                connectDown = true;
            }
        	
        	//Up
            if (shouldConnectToBlock(block, x, y, z, block.getBlock(x + 1, y, z), block.getBlockMetadata(x + 1, y, z))) {
                connectUp = true;
            }
            
            //Left
            if (shouldConnectToBlock(block, x, y, z, block.getBlock(x, y, z - 1), block.getBlockMetadata(x, y, z - 1))) {
                connectLeft = true;
            }

            //Right
            if (shouldConnectToBlock(block, x, y, z, block.getBlock(x, y, z + 1), block.getBlockMetadata(x, y, z + 1))) {
                connectRight = true;
            }
            
            //Up-Left
            if ((shouldConnectToBlock(block, x, y, z, block.getBlock(x + 1, y, z), block.getBlockMetadata(x + 1, y, z))) 
            		&& (shouldConnectToBlock(block, x, y, z, block.getBlock(x, y, z - 1), block.getBlockMetadata(x, y, z - 1)))) {
            	connectLeftUp = true;
            }
            
            //Up-Right
            if ((shouldConnectToBlock(block, x, y, z, block.getBlock(x + 1, y, z), block.getBlockMetadata(x + 1, y, z))) 
            		&& (shouldConnectToBlock(block, x, y, z, block.getBlock(x, y, z + 1), block.getBlockMetadata(x, y, z + 1)))) {
            	connectRightUp = true;
            }
            
            //Down-Left
            if ((shouldConnectToBlock(block, x, y, z, block.getBlock(x - 1, y, z), block.getBlockMetadata(x - 1, y, z))) 
            		&& (shouldConnectToBlock(block, x, y, z, block.getBlock(x, y, z - 1), block.getBlockMetadata(x, y, z - 1)))) {
            	connectLeftDown = true;
            }
            
            //Down-Right
            if ((shouldConnectToBlock(block, x, y, z, block.getBlock(x + 1, y, z), block.getBlockMetadata(x + 1, y, z))) 
            		&& (shouldConnectToBlock(block, x, y, z, block.getBlock(x, y, z + 1), block.getBlockMetadata(x, y, z + 1)))) {
            	connectRightDown = true;
            }
            
            //Left Up/Down
            if ((shouldConnectToBlock(block, x, y, z, block.getBlock(x, y, z - 1), block.getBlockMetadata(x, y, z - 1))) 
            		&& (shouldConnectToBlock(block, x, y, z, block.getBlock(x + 1, y, z), block.getBlockMetadata(x + 1, y, z)))
            		&& (shouldConnectToBlock(block, x, y, z, block.getBlock(x - 1, y, z), block.getBlockMetadata(x - 1, y, z)))) {
            	connectLeftUpDown = true;
            }
            
            //Right Up/Down
            if ((shouldConnectToBlock(block, x, y, z, block.getBlock(x, y, z + 1), block.getBlockMetadata(x, y, z + 1))) 
            		&& (shouldConnectToBlock(block, x, y, z, block.getBlock(x + 1, y, z), block.getBlockMetadata(x + 1, y, z)))
            		&& (shouldConnectToBlock(block, x, y, z, block.getBlock(x - 1, y, z), block.getBlockMetadata(x - 1, y, z)))) {
            	connectRightUpDown = true;
            }
            
            //Upper Left/Right
            if ((shouldConnectToBlock(block, x, y, z, block.getBlock(x + 1, y, z), block.getBlockMetadata(x + 1, y, z))) 
            		&& (shouldConnectToBlock(block, x, y, z, block.getBlock(x, y, z - 1), block.getBlockMetadata(x, y, z - 1)))
            		&& (shouldConnectToBlock(block, x, y, z, block.getBlock(x, y, z + 1), block.getBlockMetadata(x, y, z + 1)))) {
            	connectUpLeftRight = true;
            }
            
            //Lower Left/Right
            if ((shouldConnectToBlock(block, x, y, z, block.getBlock(x - 1, y, z), block.getBlockMetadata(x - 1, y, z))) 
            		&& (shouldConnectToBlock(block, x, y, z, block.getBlock(x, y, z - 1), block.getBlockMetadata(x, y, z - 1)))
            		&& (shouldConnectToBlock(block, x, y, z, block.getBlock(x, y, z + 1), block.getBlockMetadata(x, y, z + 1)))) {
            	connectDownLeftRight = true;
            }
            
            
            //Failsafe
            boolean microConnection = false;
            if (connectLeftUp || connectRightUp || connectLeftDown || connectRightDown || connectLeftUpDown || connectRightUpDown || connectUpLeftRight || connectDownLeftRight) {
            	microConnection = true;
            }

            if (connectUp && connectDown && connectLeft && connectRight) {
                return icons[15];
            } else if (connectUp && connectDown && connectLeft) {
                return icons[11];
            } else if (connectUp && connectDown && connectRight) {
                return icons[12];
            } else if (connectUp && connectLeft && connectRight) {
                return icons[13];
            } else if (connectDown && connectLeft && connectRight) {
                return icons[14];
            } else if (connectDown && connectUp) {
                return icons[5];
            } else if (connectLeft && connectRight) {
                return icons[6];
            } else if (connectDown && connectLeft) {
                return icons[8];
            } else if (connectDown && connectRight) {
                return icons[10];
            } else if (connectUp && connectLeft) {
                return icons[7];
            } else if (connectUp && connectRight) {
                return icons[9];
            } else if (connectDown && !microConnection) {
                return icons[3]; //Points left, empty is right
            } else if (connectUp && !microConnection) {
                return icons[4]; //Points right, empty is left
            } else if (connectLeft && !microConnection) {
                return icons[2]; //Points up, empty is down
            } else if (connectRight && !microConnection) {
                return icons[1]; //Points down, empty is up
            } else if (connectLeftUpDown) {
            	return icons[22];
            } else if (connectRightUpDown) {
            	return icons[20];
            } else if (connectUpLeftRight) {
            	return icons[23];
            } else if (connectDownLeftRight) {
            	return icons[21];
            } else if (connectLeftUp) {
            	return icons[16];
            } else if (connectRightUp) {
            	return icons[17];
            } else if (connectLeftDown) {
            	return icons[19];
            } else if (connectRightDown) {
            	return icons[18];
            } else if (!connectUp || !connectDown || !connectLeft || !connectRight) {
            	return icons[0];
            }
        }
        return icons[15];
    }

}
