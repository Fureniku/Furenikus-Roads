package com.silvaniastudios.roads.jei;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.tileentities.crusher.CrusherContainer;

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

public class CrusherCategory implements IRecipeCategory<CrusherWrapper> {
	
	private final IDrawable background;
	private final IDrawable electric_bar;
	private final IDrawable icon;
	private final IDrawableAnimated progressBar;
	
	public static final int width = 162;
	public static final int height = 52;
	
	private boolean electric = false;
	private String uid;
	
	public CrusherCategory(IGuiHelper guiHelper, String uid, boolean electric) {
		ResourceLocation guiTexture = new ResourceLocation(FurenikusRoads.MODID + ":textures/gui/jei_texture_1.png");
		background = guiHelper.createDrawable(guiTexture, 0, 0, width, height);
		electric_bar = guiHelper.createDrawable(guiTexture, 164, 0, 18, 52);
		icon = electric ? guiHelper.createDrawableIngredient(new ItemStack(FRBlocks.crusher_electric)) : guiHelper.createDrawableIngredient(new ItemStack(FRBlocks.crusher));
		int progressTick = electric ? RoadsConfig.machine.electricCrusherTickRate : RoadsConfig.machine.crusherTickRate;
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
			return I18n.format("roads.gui.electric_crusher.name");
		}
		return I18n.format("roads.gui.crusher.name");
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
		progressBar.draw(minecraft, 1, 47);
	}
	
	@Nullable
	@Override
	public IDrawable getIcon() {
		return icon;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, CrusherWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiStack = recipeLayout.getItemStacks();
		
		guiStack.init(CrusherContainer.INPUT_1, true, 0, 24);
		guiStack.init(CrusherContainer.OUTPUT_1, false, 90, 24);
		
		guiStack.set(CrusherContainer.INPUT_1, ingredients.getInputs(VanillaTypes.ITEM).get(0));
		guiStack.set(CrusherContainer.OUTPUT_1, ingredients.getOutputs(VanillaTypes.ITEM).get(0));
	}
}
