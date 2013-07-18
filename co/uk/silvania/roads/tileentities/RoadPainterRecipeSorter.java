package co.uk.silvania.roads.tileentities;

import java.util.Comparator;

import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;

class RoadPainterRecipeSorter implements Comparator {
	final RoadPainterCraftingManager craftingManager;

	RoadPainterRecipeSorter(RoadPainterCraftingManager craftingManager) {
		this.craftingManager = craftingManager;
	}

	public int compareRecipes(IRecipe par1IRecipe, IRecipe par2IRecipe) {
		return par1IRecipe instanceof ShapelessRecipes && par2IRecipe instanceof ShapedRecipes ? 1 : (par2IRecipe instanceof ShapelessRecipes && par1IRecipe instanceof ShapedRecipes ? -1 : (par2IRecipe.getRecipeSize() < par1IRecipe.getRecipeSize() ? -1 : (par2IRecipe.getRecipeSize() > par1IRecipe.getRecipeSize() ? 1 : 0)));
	}

	public int compare(Object par1Obj, Object par2Obj) {
		return this.compareRecipes((IRecipe)par1Obj, (IRecipe)par2Obj);
	}
}