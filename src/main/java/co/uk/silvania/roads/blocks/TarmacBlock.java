package co.uk.silvania.roads.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import co.uk.silvania.roads.items.FRItems;

public class TarmacBlock extends RoadBlock {
	
	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
        if (!world.isRemote) {
        	int meta = world.getBlockMetadata(x, y, z);
			if (meta < 16 && meta > 0) {
				System.out.println("Meta: " + meta);
				if (player.getHeldItem().getItem() == FRItems.tarmacCutter) {
					world.setBlockMetadataWithNotify(x, y, z, meta - 1, 3);
					player.inventory.addItemStackToInventory(new ItemStack(FRItems.tarmacFragments));
				}
			}
        }		
	}
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (!world.isRemote) {
			int meta = world.getBlockMetadata(x, y, z);
			if (meta >= 0 && meta < 15) {
	        	if (player.getHeldItem().getItem() == FRItems.impactWrench) {
	        		if (player.inventory.hasItem(FRItems.tarmacFragments)) {
	        			//Raise block by 1/16th
	        			world.setBlockMetadataWithNotify(x, y, z, meta + 1, 3);
	        			player.inventory.consumeInventoryItem(FRItems.tarmacFragments);
	        	        //Update client-side inventory
	        	        EntityPlayerMP plyr = (EntityPlayerMP) player;
	        			plyr.sendContainerToPlayer(plyr.inventoryContainer);
	        		}
	        	} else if (meta == 15) {
	        		if (player.getHeldItem().getItem() == FRItems.impactWrench) {
	        			if (player.inventory.hasItem(FRItems.tarmacFragments)) {
	        				//Create a new lowest-level tarmac block on Y+1
	        				world.setBlock(x, y+1, z, this, 0, 3);
	        				player.inventory.consumeInventoryItem(FRItems.tarmacFragments);
	        		        EntityPlayerMP plyr = (EntityPlayerMP) player;
	        				plyr.sendContainerToPlayer(plyr.inventoryContainer);
	        			}
	        		}
	        	}
	        }
        }
		return false;
    }

}
