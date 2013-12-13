package co.uk.silvania.roads.entity;

import co.uk.silvania.roads.Roads;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBasicCar extends Item {

	public ItemBasicCar(int id) {
		super(id);
		this.setCreativeTab(Roads.tabRoads);
	}
	
	//TODO resource stuff
	
	@Override
	public boolean onItemUseFirst(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			EntityBasicCar car = new EntityBasicCar(world);
						
			car.setPosition(x, y + 3, z);
			//car.setRed();
			world.spawnEntityInWorld(car);
			
			return true;
		} else {
			return false;
		}
	}

}
