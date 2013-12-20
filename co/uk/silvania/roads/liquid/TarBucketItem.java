package co.uk.silvania.roads.liquid;

import co.uk.silvania.roads.CommonProxy;
import co.uk.silvania.roads.Roads;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TarBucketItem extends ItemBucket {
	
    public TarBucketItem(int id) {
        super(id, Roads.roadsTarFlowing.blockID);
        this.setCreativeTab(Roads.tabRoads);
        this.setMaxStackSize(1);
    }
    
    public ItemStack fillCustomBucket(World w, int i, int j, int k) {
    		return null;
    }
    
    public void registerIcons(IconRegister iconRegister) {
    	itemIcon = iconRegister.registerIcon(Roads.modid + ":TarBucket");
    }
}
