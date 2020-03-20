package com.silvaniastudios.roads.items;

import com.silvaniastudios.roads.FurenikusRoads;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.registries.IForgeRegistry;

public class FRItems {
	
	public static PneumaticDrill pneumatic_drill = (PneumaticDrill) new PneumaticDrill("pneumatic_drill", ToolMaterial.IRON).setCreativeTab(FurenikusRoads.tab_tools);
	public static TarmacRammer tarmac_rammer = (TarmacRammer) new TarmacRammer("tarmac_rammer", ToolMaterial.IRON).setCreativeTab(FurenikusRoads.tab_tools);
	public static RoadItemBase paint_scraper = (RoadItemBase) new RoadItemBase("paint_scraper", 1).setCreativeTab(FurenikusRoads.tab_tools);
	public static PaintGun paint_gun = (PaintGun) new PaintGun("paint_gun").setCreativeTab(FurenikusRoads.tab_tools);
	public static ItemWrench wrench = (ItemWrench) new ItemWrench("wrench").setCreativeTab(FurenikusRoads.tab_tools);
	
	public static RoadItemBase pneumatic_drill_bit = (RoadItemBase) new RoadItemBase("pneumatic_drill_bit", 64).setCreativeTab(FurenikusRoads.tab_tools);
	public static RoadItemBase machine_frame = (RoadItemBase) new RoadItemBase("machine_frame", 64).setCreativeTab(FurenikusRoads.tab_tools);
	public static RoadItemBase machine_frame_electric = (RoadItemBase) new RoadItemBase("machine_frame_electric", 64).setCreativeTab(FurenikusRoads.tab_tools);
	public static RoadItemBase tool_handle = (RoadItemBase) new RoadItemBase("tool_handle", 64).setCreativeTab(FurenikusRoads.tab_tools);
	public static RoadItemBase large_glass_tank = (RoadItemBase) new RoadItemBase("large_glass_tank", 64).setCreativeTab(FurenikusRoads.tab_tools);
	public static RoadItemBase small_glass_tank = (RoadItemBase) new RoadItemBase("small_glass_tank", 64).setCreativeTab(FurenikusRoads.tab_tools);
	public static RoadItemBase paint_gun_barrel = (RoadItemBase) new RoadItemBase("paint_gun_barrel", 64).setCreativeTab(FurenikusRoads.tab_tools);
	public static RoadItemBase paint_gun_display = (RoadItemBase) new RoadItemBase("paint_gun_display", 64).setCreativeTab(FurenikusRoads.tab_tools);
	
	public static RoadItemBase coal_coke = (RoadItemBase) new RoadItemBase("coal_coke", 64).setCreativeTab(FurenikusRoads.tab_tools);
	public static RoadItemBase cement_dust = (RoadItemBase) new RoadItemBase("cement_dust", 64).setCreativeTab(FurenikusRoads.tab_tools);
	public static RoadItemBase limestone_dust = (RoadItemBase) new RoadItemBase("limestone_dust", 64).setCreativeTab(FurenikusRoads.tab_tools);
	public static RoadItemBase clinker_mix = (RoadItemBase) new RoadItemBase("clinker_mix", 64).setCreativeTab(FurenikusRoads.tab_tools);
	public static RoadItemBase diamond_nugget = (RoadItemBase) new RoadItemBase("diamond_nugget", 64).setCreativeTab(FurenikusRoads.tab_tools);
	
	public static TarmacCutterBlade tarmac_cutter_blade_1_iron = (TarmacCutterBlade) new TarmacCutterBlade("tarmac_cutter_blade_1_iron", 1024, 1, "iron").setCreativeTab(FurenikusRoads.tab_tools);
	public static TarmacCutterBlade tarmac_cutter_blade_1_gold = (TarmacCutterBlade) new TarmacCutterBlade("tarmac_cutter_blade_1_gold", 512, 1, "gold").setCreativeTab(FurenikusRoads.tab_tools);
	public static TarmacCutterBlade tarmac_cutter_blade_1_diamond = (TarmacCutterBlade) new TarmacCutterBlade("tarmac_cutter_blade_1_diamond", 4096, 1, "diamond").setCreativeTab(FurenikusRoads.tab_tools);
	public static TarmacCutterBlade tarmac_cutter_blade_2_iron = (TarmacCutterBlade) new TarmacCutterBlade("tarmac_cutter_blade_2_iron", 1024, 2, "iron").setCreativeTab(FurenikusRoads.tab_tools);
	public static TarmacCutterBlade tarmac_cutter_blade_2_gold = (TarmacCutterBlade) new TarmacCutterBlade("tarmac_cutter_blade_2_gold", 512, 2, "gold").setCreativeTab(FurenikusRoads.tab_tools);
	public static TarmacCutterBlade tarmac_cutter_blade_2_diamond = (TarmacCutterBlade) new TarmacCutterBlade("tarmac_cutter_blade_2_diamond", 4096, 2, "diamond").setCreativeTab(FurenikusRoads.tab_tools);
	public static TarmacCutterBlade tarmac_cutter_blade_4_iron = (TarmacCutterBlade) new TarmacCutterBlade("tarmac_cutter_blade_4_iron", 1024, 4, "iron").setCreativeTab(FurenikusRoads.tab_tools);
	public static TarmacCutterBlade tarmac_cutter_blade_4_gold = (TarmacCutterBlade) new TarmacCutterBlade("tarmac_cutter_blade_4_gold", 512, 4, "gold").setCreativeTab(FurenikusRoads.tab_tools);
	public static TarmacCutterBlade tarmac_cutter_blade_4_diamond = (TarmacCutterBlade) new TarmacCutterBlade("tarmac_cutter_blade_4_diamond", 4096, 4, "diamond").setCreativeTab(FurenikusRoads.tab_tools);
	public static TarmacCutterBlade tarmac_cutter_blade_8_iron = (TarmacCutterBlade) new TarmacCutterBlade("tarmac_cutter_blade_8_iron", 1024, 8, "iron").setCreativeTab(FurenikusRoads.tab_tools);
	public static TarmacCutterBlade tarmac_cutter_blade_8_gold = (TarmacCutterBlade) new TarmacCutterBlade("tarmac_cutter_blade_8_gold", 512, 8, "gold").setCreativeTab(FurenikusRoads.tab_tools);
	public static TarmacCutterBlade tarmac_cutter_blade_8_diamond = (TarmacCutterBlade) new TarmacCutterBlade("tarmac_cutter_blade_8_diamond", 4096, 8, "diamond").setCreativeTab(FurenikusRoads.tab_tools);
	
	public static ItemFragment tarmac_fragment_standard = (ItemFragment) new ItemFragment("tarmac_fragment_standard").setCreativeTab(FurenikusRoads.tab_roads);
	public static ItemFragment tarmac_fragment_concrete_1 = (ItemFragment) new ItemFragment("tarmac_fragment_concrete_1").setCreativeTab(FurenikusRoads.tab_roads);
	public static ItemFragment tarmac_fragment_concrete_2 = (ItemFragment) new ItemFragment("tarmac_fragment_concrete_2").setCreativeTab(FurenikusRoads.tab_roads);
	public static ItemFragment tarmac_fragment_light = (ItemFragment) new ItemFragment("tarmac_fragment_light").setCreativeTab(FurenikusRoads.tab_roads);
	public static ItemFragment tarmac_fragment_fine = (ItemFragment) new ItemFragment("tarmac_fragment_fine").setCreativeTab(FurenikusRoads.tab_roads);
	public static ItemFragment tarmac_fragment_dark = (ItemFragment) new ItemFragment("tarmac_fragment_dark").setCreativeTab(FurenikusRoads.tab_roads);
	public static ItemFragment tarmac_fragment_pale = (ItemFragment) new ItemFragment("tarmac_fragment_pale").setCreativeTab(FurenikusRoads.tab_roads);
	public static ItemFragment tarmac_fragment_red = (ItemFragment) new ItemFragment("tarmac_fragment_red").setCreativeTab(FurenikusRoads.tab_roads);
	public static ItemFragment tarmac_fragment_blue = (ItemFragment) new ItemFragment("tarmac_fragment_blue").setCreativeTab(FurenikusRoads.tab_roads);
	public static ItemFragment tarmac_fragment_white = (ItemFragment) new ItemFragment("tarmac_fragment_white").setCreativeTab(FurenikusRoads.tab_roads);
	public static ItemFragment tarmac_fragment_yellow = (ItemFragment) new ItemFragment("tarmac_fragment_yellow").setCreativeTab(FurenikusRoads.tab_roads);
	public static ItemFragment tarmac_fragment_green = (ItemFragment) new ItemFragment("tarmac_fragment_green").setCreativeTab(FurenikusRoads.tab_roads);
	public static ItemFragment tarmac_fragment_muddy = (ItemFragment) new ItemFragment("tarmac_fragment_muddy").setCreativeTab(FurenikusRoads.tab_roads);
	public static ItemFragment tarmac_fragment_stone = (ItemFragment) new ItemFragment("tarmac_fragment_stone").setCreativeTab(FurenikusRoads.tab_roads);
	public static ItemFragment tarmac_fragment_grass = (ItemFragment) new ItemFragment("tarmac_fragment_grass").setCreativeTab(FurenikusRoads.tab_roads);
	public static ItemFragment tarmac_fragment_dirt = (ItemFragment) new ItemFragment("tarmac_fragment_dirt").setCreativeTab(FurenikusRoads.tab_roads);
	public static ItemFragment tarmac_fragment_gravel = (ItemFragment) new ItemFragment("tarmac_fragment_gravel").setCreativeTab(FurenikusRoads.tab_roads);
	public static ItemFragment tarmac_fragment_sand = (ItemFragment) new ItemFragment("tarmac_fragment_sand").setCreativeTab(FurenikusRoads.tab_roads);
	
	public static ItemFragment sidewalk_fragment_standard = (ItemFragment) new ItemFragment("sidewalk_fragment_standard").setCreativeTab(FurenikusRoads.tab_sidewalks);
	public static ItemFragment sidewalk_fragment_clean = (ItemFragment) new ItemFragment("sidewalk_fragment_clean").setCreativeTab(FurenikusRoads.tab_sidewalks);
	public static ItemFragment sidewalk_fragment_dark = (ItemFragment) new ItemFragment("sidewalk_fragment_dark").setCreativeTab(FurenikusRoads.tab_sidewalks);
	public static ItemFragment sidewalk_fragment_tan = (ItemFragment) new ItemFragment("sidewalk_fragment_tan").setCreativeTab(FurenikusRoads.tab_sidewalks);
	
	public static void register(IForgeRegistry<Item> registry) {
		registry.registerAll(
				pneumatic_drill,
				tarmac_rammer,
				paint_scraper,
				paint_gun,
				wrench,
				
				pneumatic_drill_bit,
				machine_frame,
				machine_frame_electric,
				tool_handle,
				large_glass_tank,
				small_glass_tank,
				paint_gun_barrel,
				paint_gun_display,
				
				coal_coke,
				cement_dust,
				limestone_dust,
				clinker_mix,
				diamond_nugget,
				
				tarmac_cutter_blade_1_iron,
				tarmac_cutter_blade_1_gold,
				tarmac_cutter_blade_1_diamond,
				tarmac_cutter_blade_2_iron,
				tarmac_cutter_blade_2_gold,
				tarmac_cutter_blade_2_diamond,
				tarmac_cutter_blade_4_iron,
				tarmac_cutter_blade_4_gold,
				tarmac_cutter_blade_4_diamond,
				tarmac_cutter_blade_8_iron,
				tarmac_cutter_blade_8_gold,
				tarmac_cutter_blade_8_diamond,
				
				tarmac_fragment_standard,
				tarmac_fragment_concrete_1,
				tarmac_fragment_concrete_2,
				tarmac_fragment_light,
				tarmac_fragment_fine,
				tarmac_fragment_dark,
				tarmac_fragment_pale,
				tarmac_fragment_red,
				tarmac_fragment_blue,
				tarmac_fragment_white,
				tarmac_fragment_yellow,
				tarmac_fragment_green,
				tarmac_fragment_muddy,
				tarmac_fragment_stone,
				tarmac_fragment_grass,
				tarmac_fragment_dirt,
				tarmac_fragment_gravel,
				tarmac_fragment_sand,
				
				sidewalk_fragment_standard,
				sidewalk_fragment_clean,
				sidewalk_fragment_dark
		);
	}
	
	public static void registerModels() {
		pneumatic_drill.registerItemModel();
		tarmac_rammer.registerItemModel();
		paint_scraper.registerItemModel();
		paint_gun.registerItemModel();
		wrench.registerItemModel();
		
		pneumatic_drill_bit.registerItemModel();
		machine_frame.registerItemModel();
		machine_frame_electric.registerItemModel();
		tool_handle.registerItemModel();
		large_glass_tank.registerItemModel();
		small_glass_tank.registerItemModel();
		paint_gun_barrel.registerItemModel();
		paint_gun_display.registerItemModel();
		
		coal_coke.registerItemModel();
		cement_dust.registerItemModel();
		limestone_dust.registerItemModel();
		clinker_mix.registerItemModel();
		diamond_nugget.registerItemModel();
		
		tarmac_cutter_blade_1_iron.registerItemModel();
		tarmac_cutter_blade_1_gold.registerItemModel();
		tarmac_cutter_blade_1_diamond.registerItemModel();
		tarmac_cutter_blade_2_iron.registerItemModel();
		tarmac_cutter_blade_2_gold.registerItemModel();
		tarmac_cutter_blade_2_diamond.registerItemModel();
		tarmac_cutter_blade_4_iron.registerItemModel();
		tarmac_cutter_blade_4_gold.registerItemModel();
		tarmac_cutter_blade_4_diamond.registerItemModel();
		tarmac_cutter_blade_8_iron.registerItemModel();
		tarmac_cutter_blade_8_gold.registerItemModel();
		tarmac_cutter_blade_8_diamond.registerItemModel();
		
		tarmac_fragment_standard.registerItemModel();
		tarmac_fragment_concrete_1.registerItemModel();
		tarmac_fragment_concrete_2.registerItemModel();
		tarmac_fragment_light.registerItemModel();
		tarmac_fragment_fine.registerItemModel();
		tarmac_fragment_dark.registerItemModel();
		tarmac_fragment_pale.registerItemModel();
		tarmac_fragment_red.registerItemModel();
		tarmac_fragment_blue.registerItemModel();
		tarmac_fragment_white.registerItemModel();
		tarmac_fragment_yellow.registerItemModel();
		tarmac_fragment_green.registerItemModel();
		tarmac_fragment_muddy.registerItemModel();
		tarmac_fragment_stone.registerItemModel();
		tarmac_fragment_grass.registerItemModel();
		tarmac_fragment_dirt.registerItemModel();
		tarmac_fragment_gravel.registerItemModel();
		tarmac_fragment_sand.registerItemModel();
		
		sidewalk_fragment_standard.registerItemModel();
		sidewalk_fragment_clean.registerItemModel();
		sidewalk_fragment_dark.registerItemModel();
	}
}
