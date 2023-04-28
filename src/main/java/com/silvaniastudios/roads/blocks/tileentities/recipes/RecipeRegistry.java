package com.silvaniastudios.roads.blocks.tileentities.recipes;

import java.util.ArrayList;
import java.util.Map;

import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.fluids.FRFluids;
import com.silvaniastudios.roads.items.FRItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Loader;

public class RecipeRegistry {
	
	public static ArrayList<CompactorRecipes> compactorRecipes = new ArrayList<CompactorRecipes>();
	public static ArrayList<RoadFactoryRecipes> roadFactoryRecipes = new ArrayList<RoadFactoryRecipes>();
	public static ArrayList<CrusherRecipes> crusherRecipes = new ArrayList<CrusherRecipes>();
	public static ArrayList<TarDistillerRecipes> tarDistillerRecipes = new ArrayList<TarDistillerRecipes>();
	public static ArrayList<FabricatorRecipes> fabricatorRecipes = new ArrayList<FabricatorRecipes>();
	
	public static ArrayList<FluidStack> tar = new ArrayList<FluidStack>();
	
	public static void init() {
		for (Map.Entry<String, Fluid> entry : FluidRegistry.getRegisteredFluids().entrySet()) {
			String k = entry.getKey();
			Fluid v = entry.getValue();
			
			if (RoadsConfig.general.printFluidListOnStartup) { System.out.println("Fluid " + v.getLocalizedName(new FluidStack(v, 1)) + ", registered as " + k); }
			
			String[] tarAlts = RoadsConfig.general.tarAlternatives;
			for (int i = 0; i < tarAlts.length; i++) {
				if (k.equalsIgnoreCase(tarAlts[i])) {
					tar.add(new FluidStack(v, 100));
				}
			}
		}
		
		registerCompactorRecipes();
		registerTarDistillerRecipes();
		registerRoadFactoryRecipes();
		registerTarmacCutterRecipes();
		registerCrusherRecipes();
		registerFabricatorRecipes();
		
		if (Loader.isModLoaded("thermalexpansion")) {
			ThermalExpansionRecipes.registerThermalExpansionRecipes();
		}
	}
	
	public static void registerCompactorRecipes() {
		compactorRecipes.add(new CompactorRecipes(new ItemStack(FRItems.tarmac_fragment_standard, 1), new ItemStack(FRBlocks.road_block_standard, 1)));
		compactorRecipes.add(new CompactorRecipes(new ItemStack(FRItems.tarmac_fragment_concrete_1, 1), new ItemStack(FRBlocks.road_block_concrete_1, 1)));
		compactorRecipes.add(new CompactorRecipes(new ItemStack(FRItems.tarmac_fragment_concrete_2, 1), new ItemStack(FRBlocks.road_block_concrete_2, 1)));
		compactorRecipes.add(new CompactorRecipes(new ItemStack(FRItems.tarmac_fragment_light, 1), new ItemStack(FRBlocks.road_block_light, 1)));
		compactorRecipes.add(new CompactorRecipes(new ItemStack(FRItems.tarmac_fragment_fine, 1), new ItemStack(FRBlocks.road_block_fine, 1)));
		compactorRecipes.add(new CompactorRecipes(new ItemStack(FRItems.tarmac_fragment_dark, 1), new ItemStack(FRBlocks.road_block_dark, 1)));
		compactorRecipes.add(new CompactorRecipes(new ItemStack(FRItems.tarmac_fragment_pale, 1), new ItemStack(FRBlocks.road_block_pale, 1)));
		compactorRecipes.add(new CompactorRecipes(new ItemStack(FRItems.tarmac_fragment_red, 1), new ItemStack(FRBlocks.road_block_red, 1)));
		compactorRecipes.add(new CompactorRecipes(new ItemStack(FRItems.tarmac_fragment_blue, 1), new ItemStack(FRBlocks.road_block_blue, 1)));
		compactorRecipes.add(new CompactorRecipes(new ItemStack(FRItems.tarmac_fragment_white, 1), new ItemStack(FRBlocks.road_block_white, 1)));
		compactorRecipes.add(new CompactorRecipes(new ItemStack(FRItems.tarmac_fragment_yellow, 1), new ItemStack(FRBlocks.road_block_yellow, 1)));
		compactorRecipes.add(new CompactorRecipes(new ItemStack(FRItems.tarmac_fragment_green, 1), new ItemStack(FRBlocks.road_block_green, 1)));
		compactorRecipes.add(new CompactorRecipes(new ItemStack(FRItems.tarmac_fragment_muddy, 1), new ItemStack(FRBlocks.road_block_muddy_dried, 1)));
		
		compactorRecipes.add(new CompactorRecipes(new ItemStack(FRItems.tarmac_fragment_stone, 1), new ItemStack(FRBlocks.road_block_stone, 1)));
		compactorRecipes.add(new CompactorRecipes(new ItemStack(FRItems.tarmac_fragment_grass, 1), new ItemStack(FRBlocks.road_block_grass, 1)));
		compactorRecipes.add(new CompactorRecipes(new ItemStack(FRItems.tarmac_fragment_dirt, 1), new ItemStack(FRBlocks.road_block_dirt, 1)));
		compactorRecipes.add(new CompactorRecipes(new ItemStack(FRItems.tarmac_fragment_gravel, 1), new ItemStack(FRBlocks.road_block_gravel, 1)));
		compactorRecipes.add(new CompactorRecipes(new ItemStack(FRItems.tarmac_fragment_sand, 1), new ItemStack(FRBlocks.road_block_sand, 1)));
		
		compactorRecipes.add(new CompactorRecipes(new ItemStack(FRItems.sidewalk_fragment_standard, 1), new ItemStack(FRBlocks.sidewalk, 1)));
		compactorRecipes.add(new CompactorRecipes(new ItemStack(FRItems.sidewalk_fragment_clean, 1), new ItemStack(FRBlocks.sidewalk_clean, 1)));
		compactorRecipes.add(new CompactorRecipes(new ItemStack(FRItems.sidewalk_fragment_dark, 1), new ItemStack(FRBlocks.sidewalk_dark, 1)));
		compactorRecipes.add(new CompactorRecipes(new ItemStack(FRItems.sidewalk_fragment_tan, 1), new ItemStack(FRBlocks.sidewalk_tan, 1)));
	}
	
	public static void registerTarDistillerRecipes() {
		tarDistillerRecipes.add(new TarDistillerRecipes(null, new ItemStack(Items.COAL, 1, 0),  new FluidStack(FRFluids.tar, 1000), null, new ItemStack(FRItems.coal_coke), ItemStack.EMPTY));
		tarDistillerRecipes.add(new TarDistillerRecipes(null, new ItemStack(Items.COAL, 1, 1),  new FluidStack(FRFluids.tar,  750), null, new ItemStack(FRItems.coal_coke), ItemStack.EMPTY));
		tarDistillerRecipes.add(new TarDistillerRecipes(null, new ItemStack(Blocks.COAL_BLOCK), new FluidStack(FRFluids.tar, 9000), null, new ItemStack(FRItems.coal_coke, 9), ItemStack.EMPTY));
		
		tarDistillerRecipes.add(new TarDistillerRecipes(new FluidStack(FluidRegistry.WATER, 100), new ItemStack(Blocks.LOG, 1, 0), new FluidStack(FRFluids.tar, 100), null, ItemStack.EMPTY, ItemStack.EMPTY));
		tarDistillerRecipes.add(new TarDistillerRecipes(new FluidStack(FluidRegistry.WATER, 100), new ItemStack(Blocks.LOG, 1, 1), new FluidStack(FRFluids.tar, 100), null, ItemStack.EMPTY, ItemStack.EMPTY));
		tarDistillerRecipes.add(new TarDistillerRecipes(new FluidStack(FluidRegistry.WATER, 100), new ItemStack(Blocks.LOG, 1, 2), new FluidStack(FRFluids.tar, 100), null, ItemStack.EMPTY, ItemStack.EMPTY));
		tarDistillerRecipes.add(new TarDistillerRecipes(new FluidStack(FluidRegistry.WATER, 100), new ItemStack(Blocks.LOG, 1, 3), new FluidStack(FRFluids.tar, 100), null, ItemStack.EMPTY, ItemStack.EMPTY));
		tarDistillerRecipes.add(new TarDistillerRecipes(new FluidStack(FluidRegistry.WATER, 100), new ItemStack(Blocks.LOG2, 1, 0), new FluidStack(FRFluids.tar, 100), null, ItemStack.EMPTY, ItemStack.EMPTY));
		tarDistillerRecipes.add(new TarDistillerRecipes(new FluidStack(FluidRegistry.WATER, 100), new ItemStack(Blocks.LOG2, 1, 1), new FluidStack(FRFluids.tar, 100), null, ItemStack.EMPTY, ItemStack.EMPTY));
		
		tarDistillerRecipes.add(new TarDistillerRecipes(new FluidStack(FluidRegistry.WATER, 15), new ItemStack(Blocks.PLANKS, 1, 0), new FluidStack(FRFluids.tar, 15), null, ItemStack.EMPTY, ItemStack.EMPTY));
		tarDistillerRecipes.add(new TarDistillerRecipes(new FluidStack(FluidRegistry.WATER, 15), new ItemStack(Blocks.PLANKS, 1, 1), new FluidStack(FRFluids.tar, 15), null, ItemStack.EMPTY, ItemStack.EMPTY));
		tarDistillerRecipes.add(new TarDistillerRecipes(new FluidStack(FluidRegistry.WATER, 15), new ItemStack(Blocks.PLANKS, 1, 2), new FluidStack(FRFluids.tar, 15), null, ItemStack.EMPTY, ItemStack.EMPTY));
		tarDistillerRecipes.add(new TarDistillerRecipes(new FluidStack(FluidRegistry.WATER, 15), new ItemStack(Blocks.PLANKS, 1, 3), new FluidStack(FRFluids.tar, 15), null, ItemStack.EMPTY, ItemStack.EMPTY));
		tarDistillerRecipes.add(new TarDistillerRecipes(new FluidStack(FluidRegistry.WATER, 15), new ItemStack(Blocks.PLANKS, 1, 4), new FluidStack(FRFluids.tar, 15), null, ItemStack.EMPTY, ItemStack.EMPTY));
		tarDistillerRecipes.add(new TarDistillerRecipes(new FluidStack(FluidRegistry.WATER, 15), new ItemStack(Blocks.PLANKS, 1, 5), new FluidStack(FRFluids.tar, 15), null, ItemStack.EMPTY, ItemStack.EMPTY));
		
		if (Loader.isModLoaded("thermalfoundation")) {
			ThermalFoundationRecipes.tfRecipes();
		}
	}
	
	public static void registerRoadFactoryRecipes() {
		for (int i = 0; i < tar.size(); i++) {
			roadFactoryRecipes.add(new RoadFactoryRecipes(tar.get(i), new ItemStack(Blocks.STONE, 8, 0), ItemStack.EMPTY, new ItemStack(FRBlocks.road_block_stone, 8, 15)));
			roadFactoryRecipes.add(new RoadFactoryRecipes(tar.get(i), new ItemStack(Blocks.STONE, 8, 1), ItemStack.EMPTY, new ItemStack(FRBlocks.road_block_pale, 8, 15)));
			roadFactoryRecipes.add(new RoadFactoryRecipes(tar.get(i), new ItemStack(Blocks.STONE, 8, 3), ItemStack.EMPTY, new ItemStack(FRBlocks.road_block_light, 8, 15)));
			roadFactoryRecipes.add(new RoadFactoryRecipes(tar.get(i), new ItemStack(Blocks.STONE, 8, 5), ItemStack.EMPTY, new ItemStack(FRBlocks.road_block_dark, 8, 15)));
			roadFactoryRecipes.add(new RoadFactoryRecipes(tar.get(i), new ItemStack(Blocks.STONE, 8, 6), ItemStack.EMPTY, new ItemStack(FRBlocks.road_block_fine, 8, 15)));
			
			roadFactoryRecipes.add(new RoadFactoryRecipes(tar.get(i), new ItemStack(FRBlocks.generic_blocks, 8, 0), ItemStack.EMPTY, new ItemStack(FRBlocks.road_block_standard, 8, 15)));
			roadFactoryRecipes.add(new RoadFactoryRecipes(tar.get(i), new ItemStack(Blocks.GRASS, 8, 0), ItemStack.EMPTY, new ItemStack(FRBlocks.road_block_grass, 8, 15)));
			roadFactoryRecipes.add(new RoadFactoryRecipes(tar.get(i), new ItemStack(Blocks.DIRT, 8, 0), ItemStack.EMPTY, new ItemStack(FRBlocks.road_block_dirt, 8, 15)));
			roadFactoryRecipes.add(new RoadFactoryRecipes(tar.get(i), new ItemStack(Blocks.GRAVEL, 8, 0), ItemStack.EMPTY, new ItemStack(FRBlocks.road_block_gravel, 8, 15)));
			roadFactoryRecipes.add(new RoadFactoryRecipes(tar.get(i), new ItemStack(Blocks.SAND, 8, 0), ItemStack.EMPTY, new ItemStack(FRBlocks.road_block_sand, 8, 15)));
			
			roadFactoryRecipes.add(new RoadFactoryRecipes(null, new ItemStack(FRBlocks.road_block_standard, 8, 15), new ItemStack(Items.DYE, 1, 15), new ItemStack(FRBlocks.road_block_white, 8, 15)));
			roadFactoryRecipes.add(new RoadFactoryRecipes(null, new ItemStack(FRBlocks.road_block_standard, 8, 15), new ItemStack(Items.DYE, 1, 11), new ItemStack(FRBlocks.road_block_yellow, 8, 15)));
			roadFactoryRecipes.add(new RoadFactoryRecipes(null, new ItemStack(FRBlocks.road_block_standard, 8, 15), new ItemStack(Items.DYE, 1, 1), new ItemStack(FRBlocks.road_block_red, 8, 15)));
			roadFactoryRecipes.add(new RoadFactoryRecipes(null, new ItemStack(FRBlocks.road_block_standard, 8, 15), new ItemStack(Items.DYE, 1, 2), new ItemStack(FRBlocks.road_block_green, 8, 15)));
			roadFactoryRecipes.add(new RoadFactoryRecipes(null, new ItemStack(FRBlocks.road_block_standard, 8, 15), new ItemStack(Items.DYE, 1, 4), new ItemStack(FRBlocks.road_block_blue, 8, 15)));
			
			roadFactoryRecipes.add(new RoadFactoryRecipes(tar.get(i), new ItemStack(FRBlocks.generic_blocks, 8, 0), new ItemStack(Items.DYE, 1, 15), new ItemStack(FRBlocks.road_block_white, 8, 15)));
			roadFactoryRecipes.add(new RoadFactoryRecipes(tar.get(i), new ItemStack(FRBlocks.generic_blocks, 8, 0), new ItemStack(Items.DYE, 1, 11), new ItemStack(FRBlocks.road_block_yellow, 8, 15)));
			roadFactoryRecipes.add(new RoadFactoryRecipes(tar.get(i), new ItemStack(FRBlocks.generic_blocks, 8, 0), new ItemStack(Items.DYE, 1, 1), new ItemStack(FRBlocks.road_block_red, 8, 15)));
			roadFactoryRecipes.add(new RoadFactoryRecipes(tar.get(i), new ItemStack(FRBlocks.generic_blocks, 8, 0), new ItemStack(Items.DYE, 1, 2), new ItemStack(FRBlocks.road_block_green, 8, 15)));
			roadFactoryRecipes.add(new RoadFactoryRecipes(tar.get(i), new ItemStack(FRBlocks.generic_blocks, 8, 0), new ItemStack(Items.DYE, 1, 4), new ItemStack(FRBlocks.road_block_blue, 8, 15)));
		}
	}
	
	public static void registerTarmacCutterRecipes() {
		
	}
	
	public static void registerCrusherRecipes() {
		crusherRecipes.add(new CrusherRecipes(new ItemStack(Blocks.STONE, 1, 0), new ItemStack(Blocks.COBBLESTONE, 1)));
		crusherRecipes.add(new CrusherRecipes(new ItemStack(Blocks.COBBLESTONE, 1, 0), new ItemStack(Blocks.GRAVEL, 1)));
		crusherRecipes.add(new CrusherRecipes(new ItemStack(Blocks.GRAVEL, 1, 0), new ItemStack(FRBlocks.generic_blocks, 1, 0)));
		
		crusherRecipes.add(new CrusherRecipes(new ItemStack(FRBlocks.generic_blocks, 1, 0), new ItemStack(Blocks.SAND, 1)));
		crusherRecipes.add(new CrusherRecipes(new ItemStack(FRBlocks.generic_blocks, 1, 1), new ItemStack(FRItems.cement_dust, 1)));
		crusherRecipes.add(new CrusherRecipes(new ItemStack(FRBlocks.generic_blocks, 1, 2), new ItemStack(FRItems.cement_dust, 1)));
		crusherRecipes.add(new CrusherRecipes(new ItemStack(FRBlocks.generic_blocks, 1, 3), new ItemStack(FRItems.limestone_dust, 4)));
		
		crusherRecipes.add(new CrusherRecipes(new ItemStack(FRItems.clinker_mix, 1), new ItemStack(FRItems.cement_dust, 1)));
		
		for (int i = 0; i < 16; i++) {
			crusherRecipes.add(new CrusherRecipes(new ItemStack(FRBlocks.road_block_standard, 1, i), new ItemStack(FRBlocks.road_block_standard.getFragmentItem(), i+1)));
			crusherRecipes.add(new CrusherRecipes(new ItemStack(FRBlocks.road_block_concrete_1, 1, i), new ItemStack(FRBlocks.road_block_concrete_1.getFragmentItem(), i+1)));
			crusherRecipes.add(new CrusherRecipes(new ItemStack(FRBlocks.road_block_concrete_2, 1, i), new ItemStack(FRBlocks.road_block_concrete_2.getFragmentItem(), i+1)));
			crusherRecipes.add(new CrusherRecipes(new ItemStack(FRBlocks.road_block_light, 1, i), new ItemStack(FRBlocks.road_block_light.getFragmentItem(), i+1)));
			crusherRecipes.add(new CrusherRecipes(new ItemStack(FRBlocks.road_block_fine, 1, i), new ItemStack(FRBlocks.road_block_fine.getFragmentItem(), i+1)));
			crusherRecipes.add(new CrusherRecipes(new ItemStack(FRBlocks.road_block_dark, 1, i), new ItemStack(FRBlocks.road_block_dark.getFragmentItem(), i+1)));
			crusherRecipes.add(new CrusherRecipes(new ItemStack(FRBlocks.road_block_pale, 1, i), new ItemStack(FRBlocks.road_block_pale.getFragmentItem(), i+1)));
			crusherRecipes.add(new CrusherRecipes(new ItemStack(FRBlocks.road_block_red, 1, i), new ItemStack(FRBlocks.road_block_red.getFragmentItem(), i+1)));
			crusherRecipes.add(new CrusherRecipes(new ItemStack(FRBlocks.road_block_blue, 1, i), new ItemStack(FRBlocks.road_block_blue.getFragmentItem(), i+1)));
			crusherRecipes.add(new CrusherRecipes(new ItemStack(FRBlocks.road_block_white, 1, i), new ItemStack(FRBlocks.road_block_white.getFragmentItem(), i+1)));
			crusherRecipes.add(new CrusherRecipes(new ItemStack(FRBlocks.road_block_yellow, 1, i), new ItemStack(FRBlocks.road_block_yellow.getFragmentItem(), i+1)));
			crusherRecipes.add(new CrusherRecipes(new ItemStack(FRBlocks.road_block_green, 1, i), new ItemStack(FRBlocks.road_block_green.getFragmentItem(), i+1)));
			crusherRecipes.add(new CrusherRecipes(new ItemStack(FRBlocks.road_block_muddy, 1, i), new ItemStack(FRBlocks.road_block_muddy.getFragmentItem(), i+1)));
			crusherRecipes.add(new CrusherRecipes(new ItemStack(FRBlocks.road_block_muddy_dried, 1, i), new ItemStack(FRBlocks.road_block_muddy_dried.getFragmentItem(), i+1)));
			
			crusherRecipes.add(new CrusherRecipes(new ItemStack(FRBlocks.road_block_stone, 1, i), new ItemStack(FRBlocks.road_block_stone.getFragmentItem(), i+1)));
			crusherRecipes.add(new CrusherRecipes(new ItemStack(FRBlocks.road_block_grass, 1, i), new ItemStack(FRBlocks.road_block_grass.getFragmentItem(), i+1)));
			crusherRecipes.add(new CrusherRecipes(new ItemStack(FRBlocks.road_block_dirt, 1, i), new ItemStack(FRBlocks.road_block_dirt.getFragmentItem(), i+1)));
			crusherRecipes.add(new CrusherRecipes(new ItemStack(FRBlocks.road_block_gravel, 1, i), new ItemStack(FRBlocks.road_block_gravel.getFragmentItem(), i+1)));
			crusherRecipes.add(new CrusherRecipes(new ItemStack(FRBlocks.road_block_sand, 1, i), new ItemStack(FRBlocks.road_block_sand.getFragmentItem(), i+1)));
		}
	}
	
	public static void registerFabricatorRecipes() {
		ItemStack empty = ItemStack.EMPTY;
		ItemStack iron_ingot = new ItemStack(Items.IRON_INGOT);
		ItemStack iron_ingot_2 = new ItemStack(Items.IRON_INGOT, 2);
		ItemStack concrete_1 = new ItemStack(FRBlocks.road_block_concrete_1, 1, 15);
		ItemStack concrete_2 = new ItemStack(FRBlocks.road_block_concrete_2, 1, 15);
		ItemStack iron_bars = new ItemStack(Blocks.IRON_BARS);
		ItemStack black_dye = new ItemStack(Items.DYE, 1, 0);
		ItemStack yellow_dye = new ItemStack(Items.DYE, 1, 11);
		ItemStack piston = new ItemStack(Blocks.PISTON, 1, 0);
		ItemStack redstone = new ItemStack(Items.REDSTONE, 1, 0);
		
		int i = 0;
		
		fabricatorRecipes.add(new FabricatorRecipes(i++, iron_ingot, empty, empty, empty, empty, empty, new ItemStack(FRBlocks.post_small_vertical, 2, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, iron_ingot, empty, empty, empty, empty, empty, new ItemStack(FRBlocks.post_small_vertical_2, 2, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, iron_ingot, empty, empty, empty, empty, empty, new ItemStack(FRBlocks.post_small_horizontal, 2, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, iron_ingot_2, empty, empty, empty, empty, empty, new ItemStack(FRBlocks.post_medium_vertical, 2, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, iron_ingot_2, empty, empty, empty, empty, empty, new ItemStack(FRBlocks.post_medium_vertical_2, 2, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, iron_ingot_2, empty, empty, empty, empty, empty, new ItemStack(FRBlocks.post_medium_horizontal, 2, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Items.IRON_INGOT, 3), empty, empty, empty, empty, empty, new ItemStack(FRBlocks.post_large_vertical, 2, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Items.IRON_INGOT, 3), empty, empty, empty, empty, empty, new ItemStack(FRBlocks.post_large_vertical_2, 2, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Items.IRON_INGOT, 3), empty, empty, empty, empty, empty, new ItemStack(FRBlocks.post_large_horizontal, 2, 0)));
		
		fabricatorRecipes.add(new FabricatorRecipes(i++, iron_ingot_2, new ItemStack(Items.GLOWSTONE_DUST, 1), new ItemStack(Blocks.GLASS_PANE, 1), empty, empty, empty, new ItemStack(FRBlocks.street_light_1, 2, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, iron_ingot_2, new ItemStack(Items.GLOWSTONE_DUST, 1), new ItemStack(Blocks.GLASS_PANE, 1), empty, empty, empty, new ItemStack(FRBlocks.street_light_2, 2, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, iron_ingot_2, new ItemStack(Items.GLOWSTONE_DUST, 1), new ItemStack(Blocks.GLASS_PANE, 1), empty, empty, empty, new ItemStack(FRBlocks.street_light_3, 2, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, iron_ingot_2, new ItemStack(Items.GLOWSTONE_DUST, 1), new ItemStack(Blocks.GLASS_PANE, 1), empty, empty, empty, new ItemStack(FRBlocks.street_light_4, 2, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, iron_ingot_2, new ItemStack(Items.GLOWSTONE_DUST, 1), new ItemStack(Blocks.GLASS_PANE, 1), empty, empty, empty, new ItemStack(FRBlocks.street_light_5, 2, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, iron_ingot_2, new ItemStack(Items.GLOWSTONE_DUST, 1), new ItemStack(Blocks.GLASS_PANE, 1), empty, empty, empty, new ItemStack(FRBlocks.street_light_6, 2, 0)));
		
		//Barriers
		fabricatorRecipes.add(new FabricatorRecipes(i++, black_dye, iron_ingot, yellow_dye, empty, iron_ingot, empty, new ItemStack(FRBlocks.barrier_end, 4, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, iron_ingot, iron_ingot, iron_ingot, empty, empty, empty, new ItemStack(FRBlocks.barrier_standard_mid, 4, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, iron_ingot, iron_ingot, iron_ingot, iron_ingot, iron_ingot, iron_ingot, new ItemStack(FRBlocks.barrier_tall_mid, 4, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, empty, concrete_1, empty, empty, concrete_1, empty, new ItemStack(FRBlocks.barrier_concrete_1_mid, 4, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, empty, concrete_2, empty, empty, concrete_2, empty, new ItemStack(FRBlocks.barrier_concrete_2_mid, 4, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, empty, iron_bars, empty, empty, new ItemStack(Blocks.IRON_BARS), empty, new ItemStack(FRBlocks.barrier_bars_mid, 2, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, empty, iron_bars, empty, empty, new ItemStack(Blocks.IRON_BARS), empty, new ItemStack(FRBlocks.barrier_bars_mid_2, 2, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, empty, iron_bars, empty, empty, new ItemStack(Blocks.IRON_BARS), empty, new ItemStack(FRBlocks.barrier_bars_mid_3, 2, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, concrete_1, concrete_1, concrete_1, empty, empty, empty, new ItemStack(FRBlocks.barrier_wall_mid_concrete_1, 6, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, concrete_2, concrete_2, concrete_2, empty, empty, empty, new ItemStack(FRBlocks.barrier_wall_mid_concrete_2, 6, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, iron_bars, concrete_1, iron_bars, empty, empty, empty, new ItemStack(FRBlocks.barrier_bars_mid_concrete_1, 6, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, iron_bars, concrete_2, iron_bars, empty, empty, empty, new ItemStack(FRBlocks.barrier_bars_mid_concrete_2, 6, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, empty, iron_bars, empty, concrete_1, concrete_1, concrete_1, new ItemStack(FRBlocks.barrier_wall_pole_mid_concrete_1, 8, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, empty, iron_bars, empty, concrete_2, concrete_2, concrete_2, new ItemStack(FRBlocks.barrier_wall_pole_mid_concrete_2, 8, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, empty, empty, empty, iron_ingot, iron_ingot, iron_ingot, new ItemStack(FRBlocks.barrier_low_mid, 6, 0)));
		//Edge barriers
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.barrier_standard_mid, 1, 0), empty, empty, empty, empty, empty, new ItemStack(FRBlocks.barrier_standard_edge, 1, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.barrier_tall_mid, 1, 0), empty, empty, empty, empty, empty, new ItemStack(FRBlocks.barrier_tall_edge, 1, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.barrier_concrete_1_mid, 1, 0), empty, empty, empty, empty, empty, new ItemStack(FRBlocks.barrier_concrete_edge_1, 1, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.barrier_concrete_2_mid, 1, 0), empty, empty, empty, empty, empty, new ItemStack(FRBlocks.barrier_concrete_edge_2, 1, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.barrier_bars_mid, 1, 0), empty, empty, empty, empty, empty, new ItemStack(FRBlocks.barrier_bars_edge, 1, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.barrier_bars_mid_2, 1, 0), empty, empty, empty, empty, empty, new ItemStack(FRBlocks.barrier_bars_edge_2, 1, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.barrier_bars_mid_3, 1, 0), empty, empty, empty, empty, empty, new ItemStack(FRBlocks.barrier_bars_edge_3, 1, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.barrier_wall_mid_concrete_1, 1, 0), empty, empty, empty, empty, empty, new ItemStack(FRBlocks.barrier_wall_edge_concrete_1, 1, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.barrier_wall_mid_concrete_2, 1, 0), empty, empty, empty, empty, empty, new ItemStack(FRBlocks.barrier_wall_edge_concrete_2, 1, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.barrier_bars_mid_concrete_1, 1, 0), empty, empty, empty, empty, empty, new ItemStack(FRBlocks.barrier_bars_edge_concrete_1, 1, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.barrier_bars_mid_concrete_2, 1, 0), empty, empty, empty, empty, empty, new ItemStack(FRBlocks.barrier_bars_edge_concrete_2, 1, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.barrier_wall_pole_mid_concrete_1, 1, 0), empty, empty, empty, empty, empty, new ItemStack(FRBlocks.barrier_wall_pole_edge_concrete_1, 1, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.barrier_wall_pole_mid_concrete_2, 1, 0), empty, empty, empty, empty, empty, new ItemStack(FRBlocks.barrier_wall_pole_edge_concrete_2, 1, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.barrier_low_mid, 1, 0), empty, empty, empty, empty, empty, new ItemStack(FRBlocks.barrier_low_edge, 1, 0)));
		//Double
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.barrier_standard_edge, 1, 0), new ItemStack(FRBlocks.barrier_standard_edge, 1, 0), empty, empty, empty, empty, new ItemStack(FRBlocks.barrier_standard_edge_double, 1, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.barrier_tall_edge, 1, 0), new ItemStack(FRBlocks.barrier_tall_edge, 1, 0), empty, empty, empty, empty, new ItemStack(FRBlocks.barrier_tall_edge_double, 1, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.barrier_bars_edge, 1, 0), new ItemStack(FRBlocks.barrier_bars_edge, 1, 0), empty, empty, empty, empty, new ItemStack(FRBlocks.barrier_bars_edge_double, 1, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.barrier_bars_edge_2, 1, 0), new ItemStack(FRBlocks.barrier_bars_edge_3, 1, 0), empty, empty, empty, empty, new ItemStack(FRBlocks.barrier_bars_edge_double_2, 1, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.barrier_bars_edge_2, 1, 0), new ItemStack(FRBlocks.barrier_bars_edge_3, 1, 0), empty, empty, empty, empty, new ItemStack(FRBlocks.barrier_bars_edge_double_3, 1, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.barrier_wall_edge_concrete_1, 1, 0), new ItemStack(FRBlocks.barrier_wall_edge_concrete_1, 1, 0), empty, empty, empty, empty, new ItemStack(FRBlocks.barrier_wall_edge_concrete_1_double, 1, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.barrier_wall_edge_concrete_2, 1, 0), new ItemStack(FRBlocks.barrier_wall_edge_concrete_2, 1, 0), empty, empty, empty, empty, new ItemStack(FRBlocks.barrier_wall_edge_concrete_2_double, 1, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.barrier_bars_edge_concrete_1, 1, 0), new ItemStack(FRBlocks.barrier_bars_edge_concrete_1, 1, 0), empty, empty, empty, empty, new ItemStack(FRBlocks.barrier_bars_edge_concrete_1_double, 1, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.barrier_bars_edge_concrete_2, 1, 0), new ItemStack(FRBlocks.barrier_bars_edge_concrete_2, 1, 0), empty, empty, empty, empty, new ItemStack(FRBlocks.barrier_bars_edge_concrete_2_double, 1, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.barrier_wall_pole_edge_concrete_1, 1, 0), new ItemStack(FRBlocks.barrier_wall_pole_edge_concrete_1, 1, 0), empty, empty, empty, empty, new ItemStack(FRBlocks.barrier_wall_pole_edge_concrete_1_double, 1, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.barrier_wall_pole_edge_concrete_2, 1, 0), new ItemStack(FRBlocks.barrier_wall_pole_edge_concrete_2, 1, 0), empty, empty, empty, empty, new ItemStack(FRBlocks.barrier_wall_pole_edge_concrete_2_double, 1, 0)));
		
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 0), new ItemStack(Blocks.REDSTONE_LAMP), new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 0), new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 11), new ItemStack(Blocks.STONE_PRESSURE_PLATE), new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 4), new ItemStack(FRBlocks.bollard_1, 4, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 0), new ItemStack(Blocks.REDSTONE_LAMP), new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 0), new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 11), new ItemStack(Blocks.STONE_PRESSURE_PLATE), new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 4), new ItemStack(FRBlocks.bollard_1, 4, 2)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 0), new ItemStack(Blocks.REDSTONE_LAMP), new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 0), new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 11), new ItemStack(Blocks.STONE_PRESSURE_PLATE), new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 4), new ItemStack(FRBlocks.bollard_1, 8, 4)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 0), new ItemStack(Blocks.REDSTONE_LAMP), new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 0), new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 11), new ItemStack(Blocks.STONE_PRESSURE_PLATE), new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 4), new ItemStack(FRBlocks.bollard_1, 8, 6)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, empty, concrete_1, empty, empty, concrete_1, empty, new ItemStack(FRBlocks.bollard_2, 8, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, empty, concrete_2, empty, empty, concrete_2, empty, new ItemStack(FRBlocks.bollard_2, 8, 1)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, empty, iron_ingot_2, empty, empty, iron_ingot_2, empty, new ItemStack(FRBlocks.bollard_2, 4, 2)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, black_dye, iron_ingot_2, yellow_dye, black_dye, iron_ingot_2, yellow_dye, new ItemStack(FRBlocks.bollard_2, 4, 3)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, black_dye, iron_ingot_2, new ItemStack(Items.DYE, 1, 15), empty, new ItemStack(Items.IRON_INGOT, 2), empty, new ItemStack(FRBlocks.bollard_2, 4, 4)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, yellow_dye, iron_ingot_2, yellow_dye, empty, iron_ingot_2, empty, new ItemStack(FRBlocks.bollard_2, 4, 5)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, empty, iron_ingot, empty, empty, iron_ingot, empty, new ItemStack(FRBlocks.bollard_2, 4, 6)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, black_dye, iron_ingot, yellow_dye, black_dye, iron_ingot, yellow_dye, new ItemStack(FRBlocks.bollard_2, 4, 7)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, black_dye, iron_ingot, new ItemStack(Items.DYE, 1, 15), empty, iron_ingot, empty, new ItemStack(FRBlocks.bollard_2, 4, 8)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, yellow_dye, iron_ingot, yellow_dye, empty, iron_ingot, empty, new ItemStack(FRBlocks.bollard_2, 4, 9)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Items.IRON_NUGGET), iron_ingot, new ItemStack(Items.IRON_NUGGET), black_dye, iron_ingot, black_dye, new ItemStack(FRBlocks.bollard_2, 4, 10)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Items.GOLD_NUGGET), new ItemStack(FRBlocks.bollard_2, 4, 10), new ItemStack(Items.GOLD_NUGGET), empty, empty, empty, new ItemStack(FRBlocks.bollard_2, 4, 11)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Items.GLOWSTONE_DUST), iron_ingot_2, new ItemStack(Items.GLOWSTONE_DUST), empty, new ItemStack(Items.IRON_INGOT, 2), empty, new ItemStack(FRBlocks.bollard_2, 4, 12)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Items.GLOWSTONE_DUST), iron_ingot_2, new ItemStack(Items.GLOWSTONE_DUST), black_dye, new ItemStack(Items.IRON_INGOT, 2), black_dye, new ItemStack(FRBlocks.bollard_2, 4, 13)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Items.DYE, 1, 14), iron_ingot, new ItemStack(Items.DYE, 1, 14), empty, iron_ingot, empty, new ItemStack(FRBlocks.bollard_2, 4, 14)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, yellow_dye, iron_ingot, yellow_dye, empty, iron_ingot, empty, new ItemStack(FRBlocks.bollard_2, 4, 15)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, empty, iron_ingot_2, empty, empty, piston, empty, new ItemStack(FRBlocks.bollard_3, 2, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, empty, iron_ingot_2, empty, empty, piston, empty, new ItemStack(FRBlocks.bollard_3, 2, 2)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, empty, new ItemStack(Items.IRON_INGOT, 3), empty, empty, piston, empty, new ItemStack(FRBlocks.bollard_3, 2, 4)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, empty, new ItemStack(Items.IRON_INGOT, 3), empty, empty, piston, empty, new ItemStack(FRBlocks.bollard_3, 2, 6)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, empty, iron_ingot_2, empty, empty, piston, empty, new ItemStack(FRBlocks.bollard_3, 2, 8)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, empty, iron_ingot_2, empty, empty, piston, empty, new ItemStack(FRBlocks.bollard_3, 2, 10)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, empty, iron_ingot, empty, empty, piston, empty, new ItemStack(FRBlocks.bollard_3, 2, 12)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, empty, iron_ingot, empty, empty, piston, empty, new ItemStack(FRBlocks.bollard_3, 2, 14)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, yellow_dye, iron_ingot, yellow_dye, empty, redstone, empty, new ItemStack(FRBlocks.bollard_folding_yellow, 1, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, yellow_dye, iron_ingot_2, yellow_dye, empty, redstone, empty, new ItemStack(FRBlocks.bollard_folding_yellow, 1, 8)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, empty, iron_ingot, empty, empty, redstone, empty, new ItemStack(FRBlocks.bollard_folding_smooth_metal, 1, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, empty, iron_ingot_2, empty, empty, redstone, empty, new ItemStack(FRBlocks.bollard_folding_smooth_metal, 1, 8)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, black_dye, iron_ingot, black_dye, empty, redstone, empty, new ItemStack(FRBlocks.bollard_folding_black, 1, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, black_dye, iron_ingot_2, black_dye, empty, redstone, empty, new ItemStack(FRBlocks.bollard_folding_black, 1, 8)));
		
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Blocks.STONE, 1, 0), black_dye, new ItemStack(Blocks.STONE, 1, 0), empty, empty, empty, new ItemStack(FRBlocks.speed_bump, 8, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Blocks.STONE, 1, 0), yellow_dye, new ItemStack(Blocks.STONE, 1, 0), empty, empty, empty, new ItemStack(FRBlocks.speed_bump, 8, 2)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, concrete_1, empty, concrete_1, empty, empty, empty, new ItemStack(FRBlocks.speed_bump, 8, 4)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, concrete_2, empty, concrete_2, empty, empty, empty, new ItemStack(FRBlocks.speed_bump, 8, 6)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.road_block_standard, 1, 15), empty, new ItemStack(FRBlocks.road_block_standard, 1, 15), empty, empty, empty, new ItemStack(FRBlocks.speed_bump, 8, 8)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.road_block_white, 1, 15), empty, new ItemStack(FRBlocks.road_block_white, 1, 15), empty, empty, empty, new ItemStack(FRBlocks.speed_bump, 8, 10)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.road_block_yellow, 1, 15), empty, new ItemStack(FRBlocks.road_block_yellow, 1, 15), empty, empty, empty, new ItemStack(FRBlocks.speed_bump, 8, 12)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.road_block_red, 1, 15), empty, new ItemStack(FRBlocks.road_block_red, 1, 15), empty, empty, empty, new ItemStack(FRBlocks.speed_bump, 8, 14)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, empty, empty, empty, empty, concrete_1, empty, new ItemStack(FRBlocks.wheel_stop, 4, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, empty, empty, empty, empty, concrete_2, empty, new ItemStack(FRBlocks.wheel_stop, 4, 4)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, empty, yellow_dye, empty, empty, new ItemStack(Blocks.STONE, 1, 0), empty, new ItemStack(FRBlocks.wheel_stop, 4, 8)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, empty, black_dye, empty, empty, new ItemStack(Blocks.STONE, 1, 0), empty, new ItemStack(FRBlocks.wheel_stop, 4, 12)));
		
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Items.DYE, 1, 15), new ItemStack(Blocks.GLASS_PANE), new ItemStack(Items.DYE, 1, 15), iron_ingot, new ItemStack(Items.GLOWSTONE_DUST), iron_ingot, new ItemStack(FRBlocks.cats_eye_white, 2)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Items.DYE, 1, 11), new ItemStack(Blocks.GLASS_PANE), new ItemStack(Items.DYE, 1, 11), iron_ingot, new ItemStack(Items.GLOWSTONE_DUST), iron_ingot, new ItemStack(FRBlocks.cats_eye_yellow, 2)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Items.DYE, 1,  1), new ItemStack(Blocks.GLASS_PANE), new ItemStack(Items.DYE, 1,  1), iron_ingot, new ItemStack(Items.GLOWSTONE_DUST), iron_ingot, new ItemStack(FRBlocks.cats_eye_red, 2)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Items.DYE, 1,  2), new ItemStack(Blocks.GLASS_PANE), new ItemStack(Items.DYE, 1,  2), iron_ingot, new ItemStack(Items.GLOWSTONE_DUST), iron_ingot, new ItemStack(FRBlocks.cats_eye_green, 2)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Items.DYE, 1,  4), new ItemStack(Blocks.GLASS_PANE), new ItemStack(Items.DYE, 1,  4), iron_ingot, new ItemStack(Items.GLOWSTONE_DUST), iron_ingot, new ItemStack(FRBlocks.cats_eye_blue, 2)));
		
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Items.DYE, 1,  1), new ItemStack(Blocks.GLASS_PANE), new ItemStack(Items.DYE, 1,  2), iron_ingot, new ItemStack(Items.GLOWSTONE_DUST), iron_ingot, new ItemStack(FRBlocks.cats_eye_red_green, 2)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Items.DYE, 1, 15), new ItemStack(Blocks.GLASS_PANE), new ItemStack(Items.DYE, 1,  1), iron_ingot, new ItemStack(Items.GLOWSTONE_DUST), iron_ingot, new ItemStack(FRBlocks.cats_eye_white_red, 2)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Items.DYE, 1, 15), new ItemStack(Blocks.GLASS_PANE), new ItemStack(Items.DYE, 1, 11), iron_ingot, new ItemStack(Items.GLOWSTONE_DUST), iron_ingot, new ItemStack(FRBlocks.cats_eye_white_yellow, 2)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Items.DYE, 1, 15), new ItemStack(Blocks.GLASS_PANE), new ItemStack(Items.DYE, 1,  2), iron_ingot, new ItemStack(Items.GLOWSTONE_DUST), iron_ingot, new ItemStack(FRBlocks.cats_eye_white_green, 2)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Items.DYE, 1, 11), new ItemStack(Blocks.GLASS_PANE), new ItemStack(Items.DYE, 1,  1), iron_ingot, new ItemStack(Items.GLOWSTONE_DUST), iron_ingot, new ItemStack(FRBlocks.cats_eye_yellow_red, 2)));
		
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Items.DYE, 1,  1), new ItemStack(Blocks.GLASS_PANE), new ItemStack(Items.DYE, 1,  2), iron_ingot, new ItemStack(Items.GLOWSTONE_DUST), iron_ingot, new ItemStack(FRBlocks.cats_eye_red_green_double)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.cats_eye_red_green), empty, new ItemStack(FRBlocks.cats_eye_red_green), empty, empty, empty, new ItemStack(FRBlocks.cats_eye_red_green_double)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Items.DYE, 1, 15), new ItemStack(Blocks.GLASS_PANE), new ItemStack(Items.DYE, 1,  1), iron_ingot, new ItemStack(Items.GLOWSTONE_DUST), iron_ingot, new ItemStack(FRBlocks.cats_eye_white_red_double)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.cats_eye_white_red), empty, new ItemStack(FRBlocks.cats_eye_white_red), empty, empty, empty, new ItemStack(FRBlocks.cats_eye_white_red_double)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Items.DYE, 1, 15), new ItemStack(Blocks.GLASS_PANE), new ItemStack(Items.DYE, 1, 11), iron_ingot, new ItemStack(Items.GLOWSTONE_DUST), iron_ingot, new ItemStack(FRBlocks.cats_eye_white_yellow_double)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.cats_eye_white_yellow), empty, new ItemStack(FRBlocks.cats_eye_white_yellow), empty, empty, empty, new ItemStack(FRBlocks.cats_eye_white_yellow_double)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Items.DYE, 1, 15), new ItemStack(Blocks.GLASS_PANE), new ItemStack(Items.DYE, 1,  2), iron_ingot, new ItemStack(Items.GLOWSTONE_DUST), iron_ingot, new ItemStack(FRBlocks.cats_eye_white_green_double)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.cats_eye_white_green), empty, new ItemStack(FRBlocks.cats_eye_white_green), empty, empty, empty, new ItemStack(FRBlocks.cats_eye_white_green_double)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Items.DYE, 1, 11), new ItemStack(Blocks.GLASS_PANE), new ItemStack(Items.DYE, 1,  1), iron_ingot, new ItemStack(Items.GLOWSTONE_DUST), iron_ingot, new ItemStack(FRBlocks.cats_eye_yellow_red_double)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.cats_eye_yellow_red), empty, new ItemStack(FRBlocks.cats_eye_yellow_red), empty, empty, empty, new ItemStack(FRBlocks.cats_eye_yellow_red_double)));
		
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Items.DYE, 1, 15), new ItemStack(Blocks.GLASS_PANE), new ItemStack(Items.DYE, 1, 15), iron_ingot, new ItemStack(Items.GLOWSTONE_DUST), iron_ingot, new ItemStack(FRBlocks.cats_eye_white_double)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.cats_eye_white), empty, new ItemStack(FRBlocks.cats_eye_white), empty, empty, empty, new ItemStack(FRBlocks.cats_eye_white_double)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Items.DYE, 1, 11), new ItemStack(Blocks.GLASS_PANE), new ItemStack(Items.DYE, 1, 11), iron_ingot, new ItemStack(Items.GLOWSTONE_DUST), iron_ingot, new ItemStack(FRBlocks.cats_eye_yellow_double)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.cats_eye_yellow), empty, new ItemStack(FRBlocks.cats_eye_yellow), empty, empty, empty, new ItemStack(FRBlocks.cats_eye_yellow_double)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Items.DYE, 1,  1), new ItemStack(Blocks.GLASS_PANE), new ItemStack(Items.DYE, 1,  1), iron_ingot, new ItemStack(Items.GLOWSTONE_DUST), iron_ingot, new ItemStack(FRBlocks.cats_eye_red_double)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.cats_eye_red), empty, new ItemStack(FRBlocks.cats_eye_red), empty, empty, empty, new ItemStack(FRBlocks.cats_eye_red_double)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Items.DYE, 1,  2), new ItemStack(Blocks.GLASS_PANE), new ItemStack(Items.DYE, 1,  2), iron_ingot, new ItemStack(Items.GLOWSTONE_DUST), iron_ingot, new ItemStack(FRBlocks.cats_eye_green_double)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.cats_eye_green), empty, new ItemStack(FRBlocks.cats_eye_green), empty, empty, empty, new ItemStack(FRBlocks.cats_eye_green_double)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Items.DYE, 1,  4), new ItemStack(Blocks.GLASS_PANE), new ItemStack(Items.DYE, 1,  4), iron_ingot, new ItemStack(Items.GLOWSTONE_DUST), iron_ingot, new ItemStack(FRBlocks.cats_eye_blue_double)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(FRBlocks.cats_eye_blue), empty, new ItemStack(FRBlocks.cats_eye_blue), empty, empty, empty, new ItemStack(FRBlocks.cats_eye_blue_double)));
		
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Blocks.STONE), empty, new ItemStack(Blocks.STONE), new ItemStack(Blocks.STONE), empty, new ItemStack(Blocks.STONE), new ItemStack(FRBlocks.kerb_standard, 8, 0)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Blocks.STONE), new ItemStack(Items.DYE, 1, 15), new ItemStack(Blocks.STONE), new ItemStack(Blocks.STONE), new ItemStack(Items.DYE, 1, 15), new ItemStack(Blocks.STONE), new ItemStack(FRBlocks.kerb_standard, 8, 4)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Blocks.STONE), new ItemStack(Items.DYE, 1, 11), new ItemStack(Blocks.STONE), new ItemStack(Blocks.STONE), new ItemStack(Items.DYE, 1, 11), new ItemStack(Blocks.STONE), new ItemStack(FRBlocks.kerb_standard, 8, 8)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Blocks.STONE), new ItemStack(Items.DYE, 1,  1), new ItemStack(Blocks.STONE), new ItemStack(Blocks.STONE), new ItemStack(Items.DYE, 1,  1), new ItemStack(Blocks.STONE), new ItemStack(FRBlocks.kerb_standard, 8, 12)));
		
		fabricatorRecipes.add(new FabricatorRecipes(i++, yellow_dye, yellow_dye, yellow_dye, new ItemStack(Blocks.STONE_PRESSURE_PLATE), new ItemStack(Blocks.STONE_PRESSURE_PLATE), new ItemStack(Blocks.STONE_PRESSURE_PLATE), new ItemStack(FRBlocks.tactile_crossing_bumps, 16)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, iron_ingot, new ItemStack(FRItems.tool_handle), iron_ingot, empty, empty, empty, new ItemStack(FRBlocks.manhole_cover_round, 4)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, iron_ingot, new ItemStack(FRItems.tool_handle), iron_ingot, empty, empty, empty, new ItemStack(FRBlocks.manhole_cover_square, 4)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, iron_ingot, new ItemStack(FRItems.tool_handle), iron_ingot, empty, empty, empty, new ItemStack(FRBlocks.drain_cover_1, 4)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, iron_ingot, new ItemStack(FRItems.tool_handle), iron_ingot, empty, empty, empty, new ItemStack(FRBlocks.drain_cover_2, 4)));

		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Blocks.STONE, 8), empty, empty, empty, empty, empty, new ItemStack(FRBlocks.road_block_diagonal_1_1, 8)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Blocks.STONE, 8), empty, empty, empty, empty, empty, new ItemStack(FRBlocks.road_block_diagonal_1_2, 8)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Blocks.STONE, 8), empty, empty, empty, empty, empty, new ItemStack(FRBlocks.road_block_diagonal_1_4, 8)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Blocks.STONE, 8), empty, empty, empty, empty, empty, new ItemStack(FRBlocks.road_block_diagonal_2_4, 8)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Blocks.STONE, 8), empty, empty, empty, empty, empty, new ItemStack(FRBlocks.road_block_diagonal_1_8, 8)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Blocks.STONE, 8), empty, empty, empty, empty, empty, new ItemStack(FRBlocks.road_block_diagonal_2_8, 8)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Blocks.STONE, 8), empty, empty, empty, empty, empty, new ItemStack(FRBlocks.road_block_diagonal_3_8, 8)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Blocks.STONE, 8), empty, empty, empty, empty, empty, new ItemStack(FRBlocks.road_block_diagonal_4_8, 8)));

		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Blocks.STONE, 8), empty, empty, empty, empty, empty, new ItemStack(FRBlocks.road_block_diagonal_1_1_mirror, 8)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Blocks.STONE, 8), empty, empty, empty, empty, empty, new ItemStack(FRBlocks.road_block_diagonal_1_2_mirror, 8)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Blocks.STONE, 8), empty, empty, empty, empty, empty, new ItemStack(FRBlocks.road_block_diagonal_1_4_mirror, 8)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Blocks.STONE, 8), empty, empty, empty, empty, empty, new ItemStack(FRBlocks.road_block_diagonal_2_4_mirror, 8)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Blocks.STONE, 8), empty, empty, empty, empty, empty, new ItemStack(FRBlocks.road_block_diagonal_1_8_mirror, 8)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Blocks.STONE, 8), empty, empty, empty, empty, empty, new ItemStack(FRBlocks.road_block_diagonal_2_8_mirror, 8)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Blocks.STONE, 8), empty, empty, empty, empty, empty, new ItemStack(FRBlocks.road_block_diagonal_3_8_mirror, 8)));
		fabricatorRecipes.add(new FabricatorRecipes(i++, new ItemStack(Blocks.STONE, 8), empty, empty, empty, empty, empty, new ItemStack(FRBlocks.road_block_diagonal_4_8_mirror, 8)));

		//fabricatorRecipes.add(new FabricatorRecipes(i++, empty, empty, empty, empty, empty, empty, empty));
	}
}
