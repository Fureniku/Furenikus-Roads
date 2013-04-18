package co.uk.silvania.roads;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TarBucketItem extends Item {
	
    public TarBucketItem(int id) {
        super(id);

    }
    public ItemStack fillCustomBucket(World w, int i, int j, int k) {
    		return null;
    }
    
    public String getTextureFile() {
    	return CommonProxy.ITEMS_PNG;
    }

}
