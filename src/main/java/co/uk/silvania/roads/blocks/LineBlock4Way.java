package co.uk.silvania.roads.blocks;

import java.util.List;

import co.uk.silvania.roads.FlenixRoads;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LineBlock4Way extends LineBlock {
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack item) {
		int meta = world.getBlockMetadata(x, y, z);
		int direction = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360F) + 2.5D) & 3;
		
		int newMeta = meta + direction;
		world.setBlockMetadataWithNotify(x, y, z, newMeta, 0);
	}
	
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister icon) {
		icons = new IIcon[28];
		
		for (int i = 0; i < icons.length; i++) {
			icons[i] = icon.registerIcon(FlenixRoads.modid + ":" + (this.getUnlocalizedName().substring(5)) + i);
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

    public boolean shouldConnectToBlock(Block block) {
        return block == (Block) this;
    }
    
    public IIcon getConnectedBlockTexture(IBlockAccess block, int x, int y, int z, int side, IIcon[] icons) {
        boolean connectEast = false, connectWest = false, connectNorth = false, connectSouth = false;
        int westMeta = -1, eastMeta = -1, northMeta = -1, southMeta = -1;
        int meta = block.getBlockMetadata(x, y, z);
            //West
        	if (shouldConnectToBlock(block.getBlock(x - 1, y, z))) {
                connectWest = true;
                westMeta = block.getBlockMetadata(x-1, y, z);
            }
        	
        	//East
            if (shouldConnectToBlock(block.getBlock(x + 1, y, z))) {
                connectEast = true;
                eastMeta = block.getBlockMetadata(x+1, y, z);
            }
            
            //North
            if (shouldConnectToBlock(block.getBlock(x, y, z - 1))) {
                connectNorth = true;
                northMeta = block.getBlockMetadata(x, y, z-1);
            }

            //South
            if (shouldConnectToBlock(block.getBlock(x, y, z + 1))) {
                connectSouth = true;
                southMeta = block.getBlockMetadata(x, y, z+1);
            }
            
            if (westMeta == 4 || westMeta == 8)  { westMeta = 0; }
            if (westMeta == 5 || westMeta == 9)  { westMeta = 1; }
            if (westMeta == 6 || westMeta == 10) { westMeta = 2; }
            if (westMeta == 7 || westMeta == 11) { westMeta = 3; }
            
            if (eastMeta == 4 || eastMeta == 8)  { eastMeta = 0; }
            if (eastMeta == 5 || eastMeta == 9)  { eastMeta = 1; }
            if (eastMeta == 6 || eastMeta == 10) { eastMeta = 2; }
            if (eastMeta == 7 || eastMeta == 11) { eastMeta = 3; }
            
            if (northMeta == 4 || northMeta == 8)  { northMeta = 0; }
            if (northMeta == 5 || northMeta == 9)  { northMeta = 1; }
            if (northMeta == 6 || northMeta == 10) { northMeta = 2; }
            if (northMeta == 7 || northMeta == 11) { northMeta = 3; }
            
            if (southMeta == 4 || southMeta == 8)  { southMeta = 0; }
            if (southMeta == 5 || southMeta == 9)  { southMeta = 1; }
            if (southMeta == 6 || southMeta == 10) { southMeta = 2; }
            if (southMeta == 7 || southMeta == 11) { southMeta = 3; }
            
            if (southMeta == 0 && eastMeta == 1) {
            	return icons[4];
            } else if (westMeta == 1 && southMeta == 2) {
            	return icons[5];
            } else if (northMeta == 2 && westMeta == 3) {
            	return icons[6];
            } else if (eastMeta == 3 && northMeta == 0) {
            	return icons[7];
            } else if (eastMeta == 3 && southMeta == 2) {
            	return icons[8];
            } else if (westMeta == 3 && southMeta == 0) {
            	return icons[9];
            } else if (westMeta == 1 && northMeta == 0) {
            	return icons[10];
            } else if (eastMeta == 1 && northMeta == 2) {
            	return icons[11];
            } else if (westMeta == 1 && southMeta == 0) {
            	return icons[12];
            } else if (westMeta == 1 && northMeta == 2) {
            	return icons[13];
            } else if (eastMeta == 3 && northMeta == 2) {
            	return icons[14];
            } else if (eastMeta == 3 && southMeta == 0) {
            	return icons[15];
            } else if (westMeta == 3 && northMeta == 0) {
            	return icons[16];
            } else if (eastMeta == 1 && northMeta == 0) {
            	return icons[17];
            } else if (eastMeta == 1 && southMeta == 2) {
            	return icons[18];
            } else if (westMeta == 3 && southMeta == 2) {
            	return icons[19];
            }
            
            if (connectNorth && connectSouth) {
            	if (meta != 2) {
            		return icons[0];
            	} else {
            		return icons[2];
            	}
            } else if (connectEast && connectWest) {
            	if (meta != 3) {
            		return icons[1];
            	} else {
            		return icons[3];
            	}
            }

        if (meta <= 3) {
        	return icons[meta];
	    } else {
	      	return icons[meta + 16];
	    }
    }
    
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 4));
		list.add(new ItemStack(item, 1, 8));
	}
}
