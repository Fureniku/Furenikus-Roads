package com.silvaniastudios.roads.jei;

import java.util.ArrayList;

import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;
import com.silvaniastudios.roads.blocks.tileentities.recipes.CompactorRecipes;
import com.silvaniastudios.roads.blocks.tileentities.recipes.CrusherRecipes;
import com.silvaniastudios.roads.blocks.tileentities.recipes.FabricatorRecipes;
import com.silvaniastudios.roads.blocks.tileentities.recipes.RecipeRegistry;
import com.silvaniastudios.roads.blocks.tileentities.recipes.RoadFactoryRecipes;
import com.silvaniastudios.roads.blocks.tileentities.recipes.TarDistillerRecipes;
import com.silvaniastudios.roads.fluids.FRFluids;
import com.silvaniastudios.roads.items.FRItems;
import com.silvaniastudios.roads.items.PaintGunItemRegistry;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

@JEIPlugin
public class FRJEIPlugin implements IModPlugin {
	
	@Override
	public void register(IModRegistry registry) {
		registry.addRecipes(roadFactoryRecipes(), "fr_road_factory");
		registry.addRecipes(tarDistillerRecipes(), "fr_tar_distiller");
		registry.addRecipes(tarmacCutterRecipes(), "fr_tarmac_cutter");
		registry.addRecipes(crusherRecipes(), "fr_crusher");
		registry.addRecipes(paintOvenRecipes(), "fr_paint_oven");
		registry.addRecipes(compactorRecipes(), "fr_compactor");
		registry.addRecipes(fabricatorRecipes(), "fr_fabricator");
		
		registry.addRecipes(roadFactoryRecipes(), "fr_electric_road_factory");
		registry.addRecipes(tarDistillerRecipes(), "fr_electric_tar_distiller");
		registry.addRecipes(tarmacCutterRecipes(), "fr_electric_tarmac_cutter");
		registry.addRecipes(crusherRecipes(), "fr_electric_crusher");
		registry.addRecipes(paintOvenRecipes(), "fr_electric_paint_oven");
		registry.addRecipes(compactorRecipes(), "fr_electric_compactor");
		registry.addRecipes(fabricatorRecipes(), "fr_electric_fabricator");
		
		blacklistedIngredients(registry.getJeiHelpers().getIngredientBlacklist());
	}
	
	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(new RoadFactoryCategory(registry.getJeiHelpers().getGuiHelper(), "fr_road_factory", false));
		registry.addRecipeCategories(new TarDistillerCategory(registry.getJeiHelpers().getGuiHelper(), "fr_tar_distiller", false));
		registry.addRecipeCategories(new TarmacCutterCategory(registry.getJeiHelpers().getGuiHelper(), "fr_tarmac_cutter", false));
		registry.addRecipeCategories(new CrusherCategory(registry.getJeiHelpers().getGuiHelper(), "fr_crusher", false));
		registry.addRecipeCategories(new PaintOvenCategory(registry.getJeiHelpers().getGuiHelper(), "fr_paint_oven", false));
		registry.addRecipeCategories(new CompactorCategory(registry.getJeiHelpers().getGuiHelper(), "fr_compactor", false));
		registry.addRecipeCategories(new FabricatorCategory(registry.getJeiHelpers().getGuiHelper(), "fr_fabricator", false));
		
		registry.addRecipeCategories(new RoadFactoryCategory(registry.getJeiHelpers().getGuiHelper(), "fr_electric_road_factory", true));
		registry.addRecipeCategories(new TarDistillerCategory(registry.getJeiHelpers().getGuiHelper(), "fr_electric_tar_distiller", true));
		registry.addRecipeCategories(new TarmacCutterCategory(registry.getJeiHelpers().getGuiHelper(), "fr_electric_tarmac_cutter", true));
		registry.addRecipeCategories(new CrusherCategory(registry.getJeiHelpers().getGuiHelper(), "fr_electric_crusher", true));
		registry.addRecipeCategories(new PaintOvenCategory(registry.getJeiHelpers().getGuiHelper(), "fr_electric_paint_oven", true));
		registry.addRecipeCategories(new CompactorCategory(registry.getJeiHelpers().getGuiHelper(), "fr_electric_compactor", true));
		registry.addRecipeCategories(new FabricatorCategory(registry.getJeiHelpers().getGuiHelper(), "fr_electric_fabricator", true));
	}
	
	private void blacklistedIngredients(IIngredientBlacklist blacklist) {
		for (int i = 0; i < PaintGunItemRegistry.lines.size(); i++) {
			PaintBlockBase white = PaintGunItemRegistry.lines.get(i);
			int meta = PaintGunItemRegistry.linesMeta.get(i);
			removePaint(white, meta, blacklist);
		}
		
		for (int i = 0; i < PaintGunItemRegistry.icons.size(); i++) {
			PaintBlockBase white = PaintGunItemRegistry.icons.get(i);
			int meta = PaintGunItemRegistry.iconsMeta.get(i);
			removePaint(white, meta, blacklist);
		}
		
		for (int i = 0; i < PaintGunItemRegistry.letters.size(); i++) {
			PaintBlockBase white = PaintGunItemRegistry.letters.get(i);
			int meta = PaintGunItemRegistry.lettersMeta.get(i);
			removePaint(white, meta, blacklist);
		}
		
		for (int i = 0; i < PaintGunItemRegistry.text.size(); i++) {
			PaintBlockBase white = PaintGunItemRegistry.text.get(i);
			int meta = PaintGunItemRegistry.textMeta.get(i);
			removePaint(white, meta, blacklist);
		}
		
		for (int i = 0; i < PaintGunItemRegistry.junction.size(); i++) {
			PaintBlockBase white = PaintGunItemRegistry.junction.get(i);
			int meta = PaintGunItemRegistry.junctionMeta.get(i);
			removePaint(white, meta, blacklist);
		}
		
		for (int i = 0; i < PaintGunItemRegistry.other.size(); i++) {
			PaintBlockBase white = PaintGunItemRegistry.other.get(i);
			int meta = PaintGunItemRegistry.otherMeta.get(i);
			removePaint(white, meta, blacklist);
		}
		
		//extras which don't show in the gun anyway so we cant steal them from the gun lists
		removePaint(FRBlocks.white_chevron_mid, 4, blacklist);
		removePaint(FRBlocks.white_chevron_mid, 12, blacklist);
		removePaint(FRBlocks.white_chevron_mid_left, 4, blacklist);
		removePaint(FRBlocks.white_chevron_mid_left, 12, blacklist);
		removePaint(FRBlocks.white_chevron_mid_right, 4, blacklist);
		removePaint(FRBlocks.white_chevron_mid_right, 12, blacklist);
		
		removePaint(FRBlocks.white_chevron_left_b, 0, blacklist);
		removePaint(FRBlocks.white_chevron_left_b_thin, 0, blacklist);
		removePaint(FRBlocks.white_chevron_right_b, 0, blacklist);
		removePaint(FRBlocks.white_chevron_right_b_thin, 0, blacklist);
		
		blacklist.addIngredientToBlacklist(new ItemStack(FRBlocks.road_snow));
	}
	
	private void removePaint(PaintBlockBase paint, int meta, IIngredientBlacklist blacklist) {
		PaintBlockBase yellow = PaintGunItemRegistry.getYellow(paint);
		PaintBlockBase red = PaintGunItemRegistry.getRed(paint);
		blacklist.addIngredientToBlacklist(new ItemStack(paint, 1, meta));
		blacklist.addIngredientToBlacklist(new ItemStack(yellow, 1, meta));
		blacklist.addIngredientToBlacklist(new ItemStack(red, 1, meta));
	}
	
	private ArrayList<PaintOvenWrapper> paintOvenRecipes() {
		ArrayList<PaintOvenWrapper> recipes = new ArrayList<PaintOvenWrapper>();
		
		recipes.add(new PaintOvenWrapper(new ItemStack(Items.DYE, 1, 15), new FluidStack(FluidRegistry.WATER, 1000), new FluidStack(FRFluids.white_paint, 1000)));
		recipes.add(new PaintOvenWrapper(new ItemStack(Items.DYE, 1, 11), new FluidStack(FluidRegistry.WATER, 1000), new FluidStack(FRFluids.yellow_paint, 1000)));
		recipes.add(new PaintOvenWrapper(new ItemStack(Items.DYE, 1, 1), new FluidStack(FluidRegistry.WATER, 1000), new FluidStack(FRFluids.red_paint, 1000)));
		
		return recipes;
	}
	
	private ArrayList<CompactorWrapper> compactorRecipes() {
		ArrayList<CompactorWrapper> recipes = new ArrayList<CompactorWrapper>();

		for (int i = 0; i < RecipeRegistry.compactorRecipes.size(); i++) {
			CompactorRecipes cr = RecipeRegistry.compactorRecipes.get(i);
			for (int j = 0; j < 16; j++) {
				recipes.add(new CompactorWrapper(new ItemStack(cr.getInputStack().getItem(), j+1), new ItemStack(cr.getOutputStack().getItem(), 1, j)));
			}
		}
		
		return recipes;
	}
	
	private ArrayList<FabricatorWrapper> fabricatorRecipes() {
		ArrayList<FabricatorWrapper> recipes = new ArrayList<FabricatorWrapper>();
		
		for (int i = 0; i < RecipeRegistry.fabricatorRecipes.size(); i++) {
			FabricatorRecipes fr = RecipeRegistry.fabricatorRecipes.get(i);
			recipes.add(new FabricatorWrapper(fr.getInput1(), fr.getInput2(), fr.getInput3(), fr.getInput4(), fr.getInput5(), fr.getInput6(), fr.getOutput()));
		}
		
		return recipes;
	}
	
	private ArrayList<RoadFactoryWrapper> roadFactoryRecipes() {
		ArrayList<RoadFactoryWrapper> recipes = new ArrayList<RoadFactoryWrapper>();
		
		for (int i = 0; i < RecipeRegistry.roadFactoryRecipes.size(); i++) {
			RoadFactoryRecipes rfr = RecipeRegistry.roadFactoryRecipes.get(i);
			recipes.add(new RoadFactoryWrapper(rfr.getInputStack(), rfr.getModifier(), rfr.getFluidInputStack(), rfr.getOutputStack()));
		}

		return recipes;
	}
	
	private ArrayList<TarDistillerWrapper> tarDistillerRecipes() {
		ArrayList<TarDistillerWrapper> recipes = new ArrayList<TarDistillerWrapper>();
		
		for (int i = 0; i < RecipeRegistry.tarDistillerRecipes.size(); i++) {
			TarDistillerRecipes tdr = RecipeRegistry.tarDistillerRecipes.get(i);
			recipes.add(new TarDistillerWrapper(tdr.getInputStack(), tdr.getFluidInputStack(), tdr.getOutput1Stack(), tdr.getOutput2Stack(), tdr.getFluidOutput1Stack(), tdr.getFluidOutput2Stack()));
		}
		
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
		
		for (int i = 0; i < RecipeRegistry.crusherRecipes.size(); i++) {
			CrusherRecipes cr = RecipeRegistry.crusherRecipes.get(i);
			recipes.add(new CrusherWrapper(cr.getInputStack(), cr.getOutputStack()));
		}

		return recipes;
	}
}