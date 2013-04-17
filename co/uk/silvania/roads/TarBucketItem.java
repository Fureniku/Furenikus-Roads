package co.uk.silvania.roads;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TarBucketItem extends ItemBucket {
	
    public TarBucketItem(int id, int blockId) {
        super(id, blockId);

    }
    public ItemStack fillCustomBucket(World w, int i, int j, int k) {
    		return null;
    }
    
    public String getTextureFile() {
    	return CommonProxy.ITEMS_PNG;
    }

}
