package co.uk.silvania.roads.item;

import co.uk.silvania.roads.CommonProxy;
import co.uk.silvania.roads.Roads;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TarBucketItem extends ItemBucket {
	
    public TarBucketItem(int id) {
        super(id, Roads.roadsTarFlowing.blockID);
        setCreativeTab(Roads.tabRoads);

    }
    public ItemStack fillCustomBucket(World w, int i, int j, int k) {
    		return null;
    }
    
    public String getTextureFile() {
    	return CommonProxy.ITEMS_PNG;
    }

}
