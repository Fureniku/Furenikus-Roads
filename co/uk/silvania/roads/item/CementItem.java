package co.uk.silvania.roads.item;

import co.uk.silvania.roads.CommonProxy;
import net.minecraft.item.Item;

public class CementItem extends Item {

	    public CementItem(int id) {
		        super(id);

	}
    public String getTextureFile() {
        return CommonProxy.ITEMS_PNG;
    }

}
