package com.silvaniastudios.roads.jei;

import java.util.ArrayList;

import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.fluids.FRFluids;
import com.silvaniastudios.roads.items.FRItems;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

@JEIPlugin
public class FRJEIPlugin implements IModPlugin {
	
	@Override
	public void register(IModRegistry registry) {
		registry.addRecipes(roadFactoryRecipes(), "fr_road_factory");
		registry.addRecipes(tarDistillerRecipes(), "fr_tar_distiller");
		registry.addRecipes(tarmacCutterRecipes(), "fr_tarmac_cutter");
		registry.addRecipes(crusherRecipes(), "fr_crusher");
		
		registry.addRecipes(roadFactoryRecipes(), "fr_electric_road_factory");
		registry.addRecipes(tarDistillerRecipes(), "fr_electric_tar_distiller");
		registry.addRecipes(tarmacCutterRecipes(), "fr_electric_tarmac_cutter");
		registry.addRecipes(crusherRecipes(), "fr_electric_crusher");
	}
	
	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(new RoadFactoryCategory(registry.getJeiHelpers().getGuiHelper(), "fr_road_factory", false));
		registry.addRecipeCategories(new TarDistillerCategory(registry.getJeiHelpers().getGuiHelper(), "fr_tar_distiller", false));
		registry.addRecipeCategories(new TarmacCutterCategory(registry.getJeiHelpers().getGuiHelper(), "fr_tarmac_cutter", false));
		registry.addRecipeCategories(new CrusherCategory(registry.getJeiHelpers().getGuiHelper(), "fr_crusher", false));
		
		registry.addRecipeCategories(new RoadFactoryCategory(registry.getJeiHelpers().getGuiHelper(), "fr_electric_road_factory", true));
		registry.addRecipeCategories(new TarDistillerCategory(registry.getJeiHelpers().getGuiHelper(), "fr_electric_tar_distiller", true));
		registry.addRecipeCategories(new TarmacCutterCategory(registry.getJeiHelpers().getGuiHelper(), "fr_electric_tarmac_cutter", true));
		registry.addRecipeCategories(new CrusherCategory(registry.getJeiHelpers().getGuiHelper(), "fr_electric_crusher", true));
	}
	
	private ArrayList<RoadFactoryWrapper> roadFactoryRecipes() {
		ArrayList<RoadFactoryWrapper> recipes = new ArrayList<RoadFactoryWrapper>();
		
		recipes.add(new RoadFactoryWrapper(new ItemStack(FRBlocks.generic_blocks, 8, 0), new FluidStack(FRFluids.tar, 1000), new ItemStack(FRBlocks.road_block_standard, 8, 15)));
		recipes.add(new RoadFactoryWrapper(new ItemStack(Blocks.STONE, 8, 0), new FluidStack(FRFluids.tar, 1000), new ItemStack(FRBlocks.road_block_stone, 8, 15)));
		recipes.add(new RoadFactoryWrapper(new ItemStack(Blocks.STONE, 8, 1), new FluidStack(FRFluids.tar, 1000), new ItemStack(FRBlocks.road_block_pale, 8, 15)));
		recipes.add(new RoadFactoryWrapper(new ItemStack(Blocks.STONE, 8, 3), new FluidStack(FRFluids.tar, 1000), new ItemStack(FRBlocks.road_block_light, 8, 15)));
		recipes.add(new RoadFactoryWrapper(new ItemStack(Blocks.STONE, 8, 5), new FluidStack(FRFluids.tar, 1000), new ItemStack(FRBlocks.road_block_dark, 8, 15)));
		recipes.add(new RoadFactoryWrapper(new ItemStack(Blocks.STONE, 8, 6), new FluidStack(FRFluids.tar, 1000), new ItemStack(FRBlocks.road_block_fine, 8, 15)));
		
		recipes.add(new RoadFactoryWrapper(new ItemStack(Blocks.GRASS,  8, 0), new FluidStack(FRFluids.tar, 1000), new ItemStack(FRBlocks.road_block_grass, 8, 15)));
		recipes.add(new RoadFactoryWrapper(new ItemStack(Blocks.DIRT,   8, 0), new FluidStack(FRFluids.tar, 1000), new ItemStack(FRBlocks.road_block_dirt, 8, 15)));
		recipes.add(new RoadFactoryWrapper(new ItemStack(Blocks.GRAVEL, 8, 0), new FluidStack(FRFluids.tar, 1000), new ItemStack(FRBlocks.road_block_gravel, 8, 15)));
		recipes.add(new RoadFactoryWrapper(new ItemStack(Blocks.SAND,   8, 0), new FluidStack(FRFluids.tar, 1000), new ItemStack(FRBlocks.road_block_sand, 8, 15)));

		return recipes;
	}
	
	private ArrayList<TarDistillerWrapper> tarDistillerRecipes() {
		ArrayList<TarDistillerWrapper> recipes = new ArrayList<TarDistillerWrapper>();
		
		recipes.add(new TarDistillerWrapper(new ItemStack(Items.COAL, 1, 0), null, new ItemStack(FRItems.coal_coke, 1, 0), ItemStack.EMPTY, new FluidStack(FRFluids.tar, 1000), null));
		recipes.add(new TarDistillerWrapper(new ItemStack(Items.COAL, 1, 1), null, new ItemStack(FRItems.coal_coke, 1, 0), ItemStack.EMPTY, new FluidStack(FRFluids.tar, 750), null));
		recipes.add(new TarDistillerWrapper(new ItemStack(Blocks.COAL_BLOCK, 1, 0), null, new ItemStack(FRItems.coal_coke, 9, 0), ItemStack.EMPTY, new FluidStack(FRFluids.tar, 9000), null));
		return recipes;
	}
	
	private ArrayList<TarmacCutterWrapper> tarmacCutterRecipes() {
		ArrayList<TarmacCutterWrapper> recipes = new ArrayList<TarmacCutterWrapper>();
		
		for (int i = 0; i < 15; i++) {
			addCutterRecipes(recipes, i, 1);
		}
		
		for (int i = 0; i < 14; i++) {
			addCutterRecipes(recipes, i, 2);
		}
		
		for (int i = 0; i < 12; i++) {
			addCutterRecipes(recipes, i, 4);
		}
		
		for (int i = 0; i < 8; i++) {
			addCutterRecipes(recipes, i, 8);
		}
		
		return recipes;
	}
	
	private void addCutterRecipes(ArrayList<TarmacCutterWrapper> recipes, int i, int bladeSize) {
		recipes.add(new TarmacCutterWrapper(new ItemStack(FRBlocks.road_block_standard, 1, i+bladeSize), new ItemStack(FRBlocks.road_block_standard, 1, i), new ItemStack(FRItems.tarmac_fragment_standard, bladeSize)));
		recipes.add(new TarmacCutterWrapper(new ItemStack(FRBlocks.road_block_concrete_1, 1, i+bladeSize), new ItemStack(FRBlocks.road_block_concrete_1, 1, i), new ItemStack(FRItems.tarmac_fragment_concrete_1, bladeSize)));
		recipes.add(new TarmacCutterWrapper(new ItemStack(FRBlocks.road_block_concrete_2, 1, i+bladeSize), new ItemStack(FRBlocks.road_block_concrete_2, 1, i), new ItemStack(FRItems.tarmac_fragment_concrete_2, bladeSize)));
		recipes.add(new TarmacCutterWrapper(new ItemStack(FRBlocks.road_block_light, 1, i+bladeSize), new ItemStack(FRBlocks.road_block_light, 1, i), new ItemStack(FRItems.tarmac_fragment_light, bladeSize)));
		recipes.add(new TarmacCutterWrapper(new ItemStack(FRBlocks.road_block_fine, 1, i+bladeSize), new ItemStack(FRBlocks.road_block_fine, 1, i), new ItemStack(FRItems.tarmac_fragment_fine, bladeSize)));
		recipes.add(new TarmacCutterWrapper(new ItemStack(FRBlocks.road_block_dark, 1, i+bladeSize), new ItemStack(FRBlocks.road_block_dark, 1, i), new ItemStack(FRItems.tarmac_fragment_dark, bladeSize)));
		recipes.add(new TarmacCutterWrapper(new ItemStack(FRBlocks.road_block_pale, 1, i+bladeSize), new ItemStack(FRBlocks.road_block_pale, 1, i), new ItemStack(FRItems.tarmac_fragment_pale, bladeSize)));
		recipes.add(new TarmacCutterWrapper(new ItemStack(FRBlocks.road_block_red, 1, i+bladeSize), new ItemStack(FRBlocks.road_block_red, 1, i), new ItemStack(FRItems.tarmac_fragment_red, bladeSize)));
		recipes.add(new TarmacCutterWrapper(new ItemStack(FRBlocks.road_block_blue, 1, i+bladeSize), new ItemStack(FRBlocks.road_block_blue, 1, i), new ItemStack(FRItems.tarmac_fragment_blue, bladeSize)));
		recipes.add(new TarmacCutterWrapper(new ItemStack(FRBlocks.road_block_white, 1, i+bladeSize), new ItemStack(FRBlocks.road_block_white, 1, i), new ItemStack(FRItems.tarmac_fragment_white, bladeSize)));
		recipes.add(new TarmacCutterWrapper(new ItemStack(FRBlocks.road_block_yellow, 1, i+bladeSize), new ItemStack(FRBlocks.road_block_yellow, 1, i), new ItemStack(FRItems.tarmac_fragment_yellow, bladeSize)));
		recipes.add(new TarmacCutterWrapper(new ItemStack(FRBlocks.road_block_green, 1, i+bladeSize), new ItemStack(FRBlocks.road_block_green, 1, i), new ItemStack(FRItems.tarmac_fragment_green, bladeSize)));
		recipes.add(new TarmacCutterWrapper(new ItemStack(FRBlocks.road_block_muddy, 1, i+bladeSize), new ItemStack(FRBlocks.road_block_muddy, 1, i), new ItemStack(FRItems.tarmac_fragment_muddy, bladeSize)));
		recipes.add(new TarmacCutterWrapper(new ItemStack(FRBlocks.road_block_muddy_dried, 1, i+bladeSize), new ItemStack(FRBlocks.road_block_muddy_dried, 1, i), new ItemStack(FRItems.tarmac_fragment_muddy, bladeSize)));
		
		recipes.add(new TarmacCutterWrapper(new ItemStack(FRBlocks.road_block_stone, 1, i+bladeSize), new ItemStack(FRBlocks.road_block_stone, 1, i), new ItemStack(FRItems.tarmac_fragment_stone, bladeSize)));
		recipes.add(new TarmacCutterWrapper(new ItemStack(FRBlocks.road_block_grass, 1, i+bladeSize), new ItemStack(FRBlocks.road_block_grass, 1, i), new ItemStack(FRItems.tarmac_fragment_grass, bladeSize)));
		recipes.add(new TarmacCutterWrapper(new ItemStack(FRBlocks.road_block_dirt, 1, i+bladeSize), new ItemStack(FRBlocks.road_block_dirt, 1, i), new ItemStack(FRItems.tarmac_fragment_dirt, bladeSize)));
		recipes.add(new TarmacCutterWrapper(new ItemStack(FRBlocks.road_block_gravel, 1, i+bladeSize), new ItemStack(FRBlocks.road_block_gravel, 1, i), new ItemStack(FRItems.tarmac_fragment_gravel, bladeSize)));
		recipes.add(new TarmacCutterWrapper(new ItemStack(FRBlocks.road_block_sand, 1, i+bladeSize), new ItemStack(FRBlocks.road_block_sand, 1, i), new ItemStack(FRItems.tarmac_fragment_sand, bladeSize)));
	}
	
	private ArrayList<CrusherWrapper> crusherRecipes() {
		ArrayList<CrusherWrapper> recipes = new ArrayList<CrusherWrapper>();
		
		recipes.add(new CrusherWrapper(new ItemStack(Blocks.STONE, 1, 0), new ItemStack(Blocks.COBBLESTONE, 1, 0)));
		recipes.add(new CrusherWrapper(new ItemStack(Blocks.COBBLESTONE, 1, 0), new ItemStack(Blocks.GRAVEL, 1, 0)));
		recipes.add(new CrusherWrapper(new ItemStack(Blocks.GRAVEL, 1, 0), new ItemStack(FRBlocks.generic_blocks, 1, 0)));
		
		recipes.add(new CrusherWrapper(new ItemStack(FRBlocks.generic_blocks, 1, 0), new ItemStack(Blocks.SAND, 1, 0)));
		recipes.add(new CrusherWrapper(new ItemStack(FRBlocks.generic_blocks, 1, 1), new ItemStack(FRItems.cement_dust, 1, 0)));
		recipes.add(new CrusherWrapper(new ItemStack(FRBlocks.generic_blocks, 1, 2), new ItemStack(FRItems.cement_dust, 1, 0)));
		recipes.add(new CrusherWrapper(new ItemStack(FRBlocks.generic_blocks, 1, 3), new ItemStack(FRItems.limestone_dust, 1, 0)));
		
		return recipes;
	}
}