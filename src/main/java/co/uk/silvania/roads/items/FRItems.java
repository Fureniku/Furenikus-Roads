package co.uk.silvania.roads.items;

import net.minecraft.item.Item;

public class FRItems {
	
	public static Item pneumaticDrill;
	public static Item tarmacCutter;
	public static Item impactWrench;
	public static Item tarmacFragments;
	
	public static void init() {
		tools();
	}
	
	public static void tools() {
		pneumaticDrill = new PneumaticDrill().setUnlocalizedName("pneumaticDrill");
		tarmacCutter = new TarmacCutter().setUnlocalizedName("tarmacCutter");
		impactWrench = new ImpactWrench().setUnlocalizedName("impactWrench");
		tarmacFragments = new GenericItems().setUnlocalizedName("tarmacFragments");
	}

}
