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
		icons = new IIcon[45];
		blockIcon = iconRegister.registerIcon(FlenixRoads.modid + ":" + (this.getUnlocalizedName().substring(5)));
		for (int i = 0; i < icons.length; i++) {
			icons[i] = iconRegister.registerIcon(FlenixRoads.modid + ":kerbOverlay_" + i);
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		return icons[23];
	}
	
	@Override
	public IIcon getIcon(IBlockAccess block, int x, int y, int z, int side) {
		if (side == 1) {
			return getConnectedBlockTexture(block, x, y, z, side, icons);
		} else if (side == 2) {
			return icons[14];
		}
		return blockIcon;
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
	
    public boolean shouldConnectToBlock (IBlockAccess block, int x, int y, int z, Block ctBlock, Block aboveBlock, Block belowBlock) {
    	if (aboveBlock == this) {
    		return true;
    	} else if (belowBlock == this) {
    		return true;
    	}
        return ctBlock == this;
    }
	
    public IIcon getConnectedBlockTexture (IBlockAccess block, int x, int y, int z, int side, IIcon[] icons) {
        boolean connectUp = false, connectDown = false, connectLeft = false, connectRight = false, 
        		connectLeftUp = false, connectRightUp = false, connectLeftDown = false, connectRightDown = false;

        if (side == 1) {
            //Down
        	if (shouldConnectToBlock(block, x, y, z, block.getBlock(x - 1, y, z), block.getBlock(x - 1, y + 1, z), block.getBlock(x - 1, y - 1, z))) {
                connectDown = true;
            }
        	
        	//Up
            if (shouldConnectToBlock(block, x, y, z, block.getBlock(x + 1, y, z), block.getBlock(x + 1, y + 1, z), block.getBlock(x + 1, y - 1, z))) {
                connectUp = true;
            }
            
            //Left
            if (shouldConnectToBlock(block, x, y, z, block.getBlock(x, y, z - 1), block.getBlock(x, y + 1, z - 1), block.getBlock(x, y - 1, z - 1))) {
                connectLeft = true;
            }

            //Right
            if (shouldConnectToBlock(block, x, y, z, block.getBlock(x, y, z + 1), block.getBlock(x, y + 1, z + 1), block.getBlock(x, y - 1, z + 1))) {
                connectRight = true;
            }
            
            //Up-Left
            if (shouldConnectToBlock(block, x, y, z, block.getBlock(x + 1, y, z - 1), block.getBlock(x + 1, y + 1, z - 1), block.getBlock(x + 1, y - 1, z - 1))) {
            	connectLeftUp = true;
            }
            
            //Up-Right
            if (shouldConnectToBlock(block, x, y, z, block.getBlock(x + 1, y, z + 1), block.getBlock(x + 1, y + 1, z + 1), block.getBlock(x + 1, y - 1, z + 1))) {
            	connectRightUp = true;
            }
            
            //Down-Left
            if (shouldConnectToBlock(block, x, y, z, block.getBlock(x - 1, y, z - 1), block.getBlock(x - 1, y + 1, z - 1), block.getBlock(x - 1, y - 1, z - 1))) {
            	connectLeftDown = true;
            }
            
            //Down-Right
            if (shouldConnectToBlock(block, x, y, z, block.getBlock(x - 1, y, z + 1), block.getBlock(x - 1, y + 1, z + 1), block.getBlock(x - 1, y - 1, z + 1))) {
            	connectRightDown = true;
            }

            if (connectUp && connectDown && connectLeft && connectRight && connectLeftUp && connectLeftDown && connectRightUp && connectRightDown) {
                return icons[15];
            } else if (connectUp && !connectDown && connectLeft && connectRight && !connectLeftUp && !connectRightUp) {
            	return icons[32];
            } else if (connectUp && connectDown && connectLeft && !connectRight && !connectLeftUp && !connectLeftDown) {
            	return icons[31];
            } else if (!connectUp && connectDown && connectLeft && connectRight && !connectLeftDown && !connectRightDown) {
            	return icons[30];
            } else if (connectUp && connectDown && !connectLeft && connectRight && !connectRightUp && !connectRightDown) {
            	return icons[29];
                
            } else if (connectUp && !connectDown && !connectLeft && connectRight && !connectRightUp) {
            	return icons[44];
            } else if (connectUp && !connectDown && connectLeft && !connectRight && !connectLeftUp) {
            	return icons[43];
            } else if (!connectUp && connectDown && connectLeft && !connectRight && !connectLeftDown) {
            	return icons[42];
            } else if (!connectUp && connectDown && !connectLeft && connectRight && !connectRightDown) {
            	return icons[41];
            } else if (connectUp && !connectDown && connectLeft && connectRight && !connectLeftUp) {
            	return icons[40];  
            } else if (connectUp && connectDown && connectLeft && !connectRight && !connectLeftDown) {
            	return icons[39];
            } else if (!connectUp && connectDown && connectLeft && connectRight && !connectRightDown) {
            	return icons[38];
            } else if (connectUp && connectDown && !connectLeft && connectRight && !connectRightUp) {
            	return icons[37];
            } else if (connectUp && !connectDown && connectLeft && connectRight && !connectRightUp) {
            	return icons[36];
            } else if (connectUp && connectDown && connectLeft && !connectRight && !connectLeftUp) {
            	return icons[35];
            } else if (!connectUp && connectDown && connectLeft && connectRight && !connectLeftDown) {
            	return icons[34];
            } else if (connectUp && connectDown && !connectLeft && connectRight && !connectRightDown) {
            	return icons[33];
            } else if (connectUp && connectDown && connectLeft && connectRight && !connectLeftUp && !connectLeftDown && !connectRightUp && !connectRightDown) {
            	return icons[28];
            } else if (connectUp && connectDown && connectLeft && connectRight && connectLeftUp && !connectLeftDown && !connectRightUp && !connectRightDown) {
            	return icons[27];
            } else if (connectUp && connectDown && connectLeft && connectRight && !connectLeftUp && connectLeftDown && !connectRightUp && !connectRightDown) {
            	return icons[26];
            } else if (connectUp && connectDown && connectLeft && connectRight && !connectLeftUp && !connectLeftDown && !connectRightUp && connectRightDown) {
            	return icons[25];
            } else if (connectUp && connectDown && connectLeft && connectRight && !connectLeftUp && !connectLeftDown && connectRightUp && !connectRightDown) {
            	return icons[24];
            } else if (connectUp && connectDown && connectLeft && connectRight && !connectLeftUp && !connectLeftDown && connectRightUp && connectRightDown) {
            	return icons[23];
            } else if (connectUp && connectDown && connectLeft && connectRight && connectLeftUp && !connectLeftDown && connectRightUp && !connectRightDown) {
            	return icons[22];
            } else if (connectUp && connectDown && connectLeft && connectRight && !connectLeftUp && !connectLeftDown && connectRightUp && connectRightDown) {
            	return icons[21];
            } else if (connectUp && connectDown && connectLeft && connectRight && !connectLeftUp && connectLeftDown && !connectRightUp && connectRightDown) {
            	return icons[20];
            } else if (connectUp && connectDown && connectLeft && connectRight && !connectLeftUp && connectLeftDown && connectRightUp && connectRightDown) {
            	return icons[19];
            } else if (connectUp && connectDown && connectLeft && connectRight && connectLeftUp && !connectLeftDown && connectRightUp && connectRightDown) {
            	return icons[18];
            } else if (connectUp && connectDown && connectLeft && connectRight && connectLeftUp && connectLeftDown && connectRightUp && !connectRightDown) {
            	return icons[17];
            } else if (connectUp && connectDown && connectLeft && connectRight && connectLeftUp && connectLeftDown && !connectRightUp && connectRightDown) {
            	return icons[16];
            } else if (connectUp && connectDown && connectLeft && connectRight && connectLeftUp && connectLeftDown && connectRightUp && connectRightDown) {
            	return icons[15];
            } else if (connectUp && connectDown && !connectLeft && connectRight) {
            	return icons[14];
            } else if (connectUp && connectDown && connectLeft && !connectRight) {
            	return icons[13];
            } else if (connectUp && !connectDown && connectLeft && connectRight) {
            	return icons[12];
            } else if (!connectUp && connectDown && connectLeft && connectRight) {
            	return icons[11];
            } else if (connectUp && !connectDown && !connectLeft && connectRight) {
            	return icons[10];
            } else if (connectUp && !connectDown && connectLeft && !connectRight) {
            	return icons[9];
            } else if (!connectUp && connectDown && !connectLeft && connectRight) {
            	return icons[8];
            } else if (!connectUp && connectDown && connectLeft && !connectRight) {
            	return icons[7];
            } else if (connectUp && connectDown && !connectLeft && !connectRight) {
            	return icons[6];
            } else if (!connectUp && !connectDown && connectLeft && connectRight) {
            	return icons[5];
            } else if (!connectUp && !connectDown && connectLeft && !connectRight) {
            	return icons[4];
            } else if (!connectUp && !connectDown && !connectLeft && connectRight) {
            	return icons[3];
            } else if (!connectUp && connectDown && !connectLeft && !connectRight) {
            	return icons[2];
            } else if (connectUp && !connectDown && !connectLeft && !connectRight) {
            	return icons[1];
            } else if (!connectUp && !connectDown && !connectLeft && !connectRight) {
            	return icons[0];
            }
        }
        return icons[15];
    }

}
