package com.silvaniastudios.roads.jei;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.tileentities.fabricator.FabricatorContainer;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class FabricatorCategory implements IRecipeCategory<FabricatorWrapper> {
	
	private final IDrawable background;
	private final IDrawable electric_bar;
	private final IDrawable icon;
	private final IDrawableAnimated progressBar;
	
	public static final int width = 162;
	public static final int height = 62;
	
	private boolean electric = false;
	private String uid;
	
	public FabricatorCategory(IGuiHelper guiHelper, String uid, boolean electric) {
		ResourceLocation guiTexture = new ResourceLocation(FurenikusRoads.MODID + ":textures/gui/jei_texture_3.png");
		background = guiHelper.createDrawable(guiTexture, 0, 0, width, height);
		electric_bar = guiHelper.createDrawable(guiTexture, 164, 0, 18, 62);
		icon = electric ? guiHelper.createDrawableIngredient(new ItemStack(FRBlocks.fabricator_electric)) : guiHelper.createDrawableIngredient(new ItemStack(FRBlocks.fabricator));
		int progressTick = electric ? RoadsConfig.machine.electricFabricatorTickRate : RoadsConfig.machine.fabricatorTickRate;
		progressBar = guiHelper.createAnimatedDrawable(guiHelper.createDrawable(guiTexture, 0, 252, 160, 4), progressTick, IDrawableAnimated.StartDirection.LEFT, false);
		this.electric = electric;
		this.uid = uid;
	}

	@Override
	public String getUid() {
		return uid;
	}

	@Override
	public String getTitle() {
		if (electric) {
			return I18n.format("roads.gui.electric_fabricator.name");
		}
		return I18n.format("roads.gui.fabricator.name");
	}

	@Override
	public String getModName() {
		return "Fureniku's Roads";
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}
	
	@Override
	public void drawExtras(@Nonnull Minecraft minecraft) {
		if (electric) { electric_bar.draw(minecraft, width-18, 0); }
		progressBar.draw(minecraft, 1, 57);
	}
	
	@Nullable
	@Override
	public IDrawable getIcon() {
		return icon;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, FabricatorWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiStack = recipeLayout.getItemStacks();
		
		guiStack.init(FabricatorContainer.IN_1, true,  0, 16);
		guiStack.init(FabricatorContainer.IN_2, true, 18, 16);
		guiStack.init(FabricatorContainer.IN_3, true, 36, 16);
		guiStack.init(FabricatorContainer.IN_4, true,  0, 34);
		guiStack.init(FabricatorContainer.IN_5, true, 18, 34);
		guiStack.init(FabricatorContainer.IN_6, true, 36, 34);
		guiStack.init(FabricatorContainer.OUTPUT, false, 122, 25);
		
		guiStack.set(FabricatorContainer.IN_1, ingredients.getInputs(VanillaTypes.ITEM).get(0));
		guiStack.set(FabricatorContainer.IN_2, ingredients.getInputs(VanillaTypes.ITEM).get(1));
		guiStack.set(FabricatorContainer.IN_3, ingredients.getInputs(VanillaTypes.ITEM).get(2));
		guiStack.set(FabricatorContainer.IN_4, ingredients.getInputs(VanillaTypes.ITEM).get(3));
		guiStack.set(FabricatorContainer.IN_5, ingredients.getInputs(VanillaTypes.ITEM).get(4));
		guiStack.set(FabricatorContainer.IN_6, ingredients.getInputs(VanillaTypes.ITEM).get(5));
		guiStack.set(FabricatorContainer.OUTPUT, ingredients.getOutputs(VanillaTypes.ITEM).get(0));
	}
}
