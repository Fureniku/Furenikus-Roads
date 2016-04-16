package co.uk.silvania.roads.blocks;

import co.uk.silvania.roads.FlenixRoads;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class IconBlockWide extends IconBlock {
	
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		icons = new IIcon[96];
		int j = 16;
		int k = 48;
		for (int i = 0; i < icons.length; i++) {
			icons[i] = iconRegister.registerIcon(FlenixRoads.modid + ":" + (this.getUnlocalizedName().substring(5)) + "_" + i);
		}
	}
	
	@SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
    	return icons[meta];
    }
	
	@Override
	public IIcon getIcon(IBlockAccess block, int x, int y, int z, int side) {
		return getConnectedBlockTexture(block, x, y+1, z, side, icons);
	}

	public boolean shouldConnectToBlock (IBlockAccess block, int x, int y, int z, Block ctBlock, int ctMeta) {
    	//Check block AND meta matches.
        return ctBlock == this && ctMeta == block.getBlockMetadata(x, y, z);
    }
	
    public IIcon getConnectedBlockTexture (IBlockAccess block, int x, int y, int z, int side, IIcon[] icons) {
    	boolean connectEast2 = false, connectWest2 = false, connectNorth2 = false, connectSouth2 = false;
    	boolean connectEast1 = false, connectWest1 = false, connectNorth1 = false, connectSouth1 = false;
    	boolean connectEast = false, connectWest = false, connectNorth = false, connectSouth = false;
        boolean connectEastWest = false, connectNorthSouth = false;
        int meta = block.getBlockMetadata(x, y, z);
        
        boolean iconNorth = meta == 0 || meta == 4 || meta == 8  || meta == 12;
        boolean iconEast  = meta == 1 || meta == 5 || meta == 9  || meta == 13;
        boolean iconSouth = meta == 2 || meta == 6 || meta == 10 || meta == 14;
        boolean iconWest  = meta == 3 || meta == 7 || meta == 11 || meta == 15;

    	if (shouldConnectToBlock(block, x, y, z, block.getBlock(x - 1, y, z), block.getBlockMetadata(x - 1, y, z)) &&
    			shouldConnectToBlock(block, x, y, z, block.getBlock(x - 2, y, z), block.getBlockMetadata(x - 2, y, z))) {
            connectWest2 = true;
        }
    	
    	if (shouldConnectToBlock(block, x, y, z, block.getBlock(x + 1, y, z), block.getBlockMetadata(x + 1, y, z)) &&
    			shouldConnectToBlock(block, x, y, z, block.getBlock(x + 2, y, z), block.getBlockMetadata(x + 2, y, z))) {
            connectEast2 = true;
        }
    	
    	if (shouldConnectToBlock(block, x, y, z, block.getBlock(x, y, z - 1), block.getBlockMetadata(x, y, z - 1)) && 
    			shouldConnectToBlock(block, x, y, z, block.getBlock(x, y, z - 2), block.getBlockMetadata(x, y, z - 2))) {
    		connectNorth2 = true;
        }

        if (shouldConnectToBlock(block, x, y, z, block.getBlock(x, y, z + 1), block.getBlockMetadata(x, y, z + 1)) && 
        		shouldConnectToBlock(block, x, y, z, block.getBlock(x, y, z + 2), block.getBlockMetadata(x, y, z + 2))) {
            connectSouth2 = true;
        }
    	
    	
        if (shouldConnectToBlock(block, x, y, z, block.getBlock(x - 1, y, z), block.getBlockMetadata(x - 1, y, z)) && !connectWest2) { connectWest = true; }
        if (shouldConnectToBlock(block, x, y, z, block.getBlock(x + 1, y, z), block.getBlockMetadata(x + 1, y, z)) && !connectEast2) { connectEast = true; }
        if (shouldConnectToBlock(block, x, y, z, block.getBlock(x, y, z - 1), block.getBlockMetadata(x, y, z - 1)) && !connectNorth2) { connectNorth = true; }
        if (shouldConnectToBlock(block, x, y, z, block.getBlock(x, y, z + 1), block.getBlockMetadata(x, y, z + 1)) && !connectSouth2) { connectSouth = true; }
        
        if (shouldConnectToBlock(block, x, y, z, block.getBlock(x - 1, y, z), block.getBlockMetadata(x - 1, y, z)) &&
    			shouldConnectToBlock(block, x, y, z, block.getBlock(x + 1, y, z), block.getBlockMetadata(x + 1, y, z))) {
            connectEastWest = true;
        }
    	
    	
    	if (shouldConnectToBlock(block, x, y, z, block.getBlock(x, y, z - 1), block.getBlockMetadata(x, y, z - 1)) && 
    			shouldConnectToBlock(block, x, y, z, block.getBlock(x, y, z + 1), block.getBlockMetadata(x, y, z + 1))) {
            connectNorthSouth = true;
        }
    	
    	//META: 0 = north, 1 = east, 2 = south, 3 = west
    	
    	if (connectEastWest && !connectWest2 && !connectEast2) {
    		if (meta >= 0 && meta <= 3) {
	    		if (iconNorth) { return icons[49]; }
	    		if (iconSouth) { return icons[55]; }
    		}
    		if (meta >= 4 && meta <= 7) {
	    		if (iconNorth) { return icons[61]; }
	    		if (iconSouth) { return icons[67]; }
    		}
    	}
    	
    	if (connectWest2 && !connectEast) {
    		if (meta >= 0 && meta <= 3) {
				if (iconNorth) { return icons[50]; }
		    	if (iconSouth) { return icons[54]; }
    		}
    		if (meta >= 4 && meta <= 7) {
    			if (iconNorth) { return icons[62]; }
		    	if (iconSouth) { return icons[66]; }
    		}
    	}
    	
    	if (connectEast2 && !connectWest) {
    		if (meta >= 0 && meta <= 3) {
	    		if (iconNorth) { return icons[48]; }
		    	if (iconSouth) { return icons[56]; }
    		}
    		if (meta >= 4 && meta <= 7) {
    			if (iconNorth) { return icons[60]; }
		    	if (iconSouth) { return icons[68]; }
    		}
    	}
    	


    	if (connectNorthSouth && !connectNorth2 && !connectSouth2) {
    		if (meta >= 0 && meta <= 3) {
		    	if (iconEast) { return icons[52]; }
		    	if (iconWest) { return icons[58]; }
    		}
    		if (meta >= 4 && meta <= 7) {
    			if (iconEast) { return icons[64]; }
		    	if (iconWest) { return icons[70]; }
    		}
    	}
    	
    	if (connectNorth2 && !connectSouth) {
    		if (meta >= 0 && meta <= 3) {
	    		if (iconEast) { return icons[53]; }
		    	if (iconWest) { return icons[57]; }
    		}
    		if (meta >= 4 && meta <= 7) {
    			if (iconEast) { return icons[65]; }
		    	if (iconWest) { return icons[69]; }
    		}
    	}
    	
    	if (connectSouth2 && !connectNorth) {
    		if (meta >= 0 && meta <= 3) {
	    		if (iconEast) { return icons[51]; }
		    	if (iconWest) { return icons[59]; }
    		}
    		if (meta >= 4 && meta <= 7) {
    			if (iconEast) { return icons[63]; }
		    	if (iconWest) { return icons[71]; }
    		}
    	}
    	
    	if (!connectEast && connectWest) {
    		if (meta >= 0 && meta <= 3) {
	    		if (iconNorth) { return icons[17]; }
	    		if (iconSouth) { return icons[20]; }
    		}
    		if (meta >= 4 && meta <= 7) {
	    		if (iconNorth) { return icons[25]; }
	    		if (iconSouth) { return icons[28]; }
    		}
    	}
    	
    	if (connectEast && !connectWest) {
    		if (meta >= 0 && meta <= 3) {
	    		if (iconNorth) { return icons[16]; }
	    		if (iconSouth) { return icons[21]; }
    		}
    		if (meta >= 4 && meta <= 7) {
	    		if (iconNorth) { return icons[24]; }
	    		if (iconSouth) { return icons[29]; }
    		}
    	}
    	
    	if (!connectNorth && connectSouth) {
    		if (meta >= 0 && meta <= 3) {
	    		if (iconEast) { return icons[18]; }
	    		if (iconWest) { return icons[23]; }
    		}
    		if (meta >= 4 && meta <= 7) {
	    		if (iconEast) { return icons[26]; }
	    		if (iconWest) { return icons[31]; }
    		}
    	}
    	
    	if (connectNorth && !connectSouth) {
    		if (meta >= 0 && meta <= 3) {
	    		if (iconEast) { return icons[19]; }
	    		if (iconWest) { return icons[22]; }
    		}
    		if (meta >= 4 && meta <= 7) {
	    		if (iconEast) { return icons[27]; }
	    		if (iconWest) { return icons[30]; }
    		}
    	}

        System.out.println("connect! north south east west " + connectNorth + connectSouth + connectEast + connectWest + ", side: " + side);
        return icons[meta];
    }
}
