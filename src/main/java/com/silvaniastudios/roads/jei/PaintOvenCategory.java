package com.silvaniastudios.roads.jei;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.tileentities.paintoven.PaintOvenContainer;
import com.silvaniastudios.roads.blocks.tileentities.paintoven.PaintOvenEntity;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class PaintOvenCategory implements IRecipeCategory<PaintOvenWrapper> {
	
	private final IDrawable background;
	private final IDrawable electric_bar;
	private final IDrawable icon;
	private final IDrawableAnimated progressBar;
	
	private final IDrawable tankCover;
	
	public static final int width = 184;
	public static final int height = 62;
	
	private boolean electric = false;
	private String uid;
	
	public PaintOvenCategory(IGuiHelper guiHelper, String uid, boolean electric) {
		ResourceLocation guiTexture = new ResourceLocation(FurenikusRoads.MODID + ":textures/gui/jei_texture_1.png");
		background = guiHelper.createDrawable(guiTexture, 0, 162, width, height);
		electric_bar = guiHelper.createDrawable(guiTexture, 186, 162, 18, 42);
		icon = electric ? guiHelper.createDrawableIngredient(new ItemStack(FRBlocks.paint_oven_electric)) : guiHelper.createDrawableIngredient(new ItemStack(FRBlocks.paint_oven));
		int progressTick = electric ? RoadsConfig.machine.electricPaintOvenTickRate : RoadsConfig.machine.paintOvenTickRate;
		progressBar = guiHelper.createAnimatedDrawable(guiHelper.createDrawable(guiTexture, 0, 252, 160, 4), progressTick, IDrawableAnimated.StartDirection.LEFT, false);
		this.electric = electric;
		this.uid = uid;
		
		tankCover = guiHelper.createDrawable(guiTexture, 196, 0, 60, 50);
	}

	@Override
	public String getUid() {
		return uid;
	}

	@Override
	public String getTitle() {
		if (electric) {
			return I18n.format("roads.gui.electric_paint_oven.name");
		}
		return I18n.format("roads.gui.paint_oven.name");
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
	public void setRecipe(IRecipeLayout recipeLayout, PaintOvenWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiStack = recipeLayout.getItemStacks();
		IGuiFluidStackGroup fluidStack = recipeLayout.getFluidStacks();
		
		guiStack.init(PaintOvenContainer.DYE, true, 72, 17);
		fluidStack.init(3, true, 1, 1, 60, 50, PaintOvenEntity.FILLER_TANK_CAP, true, tankCover);
		fluidStack.init(4, false, 101, 1, 60, 50, PaintOvenEntity.FILLER_TANK_CAP, true, tankCover);
		
		guiStack.set(PaintOvenContainer.DYE, ingredients.getInputs(VanillaTypes.ITEM).get(0));
		fluidStack.set(3, ingredients.getInputs(VanillaTypes.FLUID).get(0));
		fluidStack.set(4, ingredients.getOutputs(VanillaTypes.FLUID).get(0));
	}
}
