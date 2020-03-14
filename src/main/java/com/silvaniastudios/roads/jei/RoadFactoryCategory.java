package com.silvaniastudios.roads.jei;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.tileentities.roadfactory.RoadFactoryContainer;
import com.silvaniastudios.roads.blocks.tileentities.roadfactory.RoadFactoryEntity;

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

public class RoadFactoryCategory implements IRecipeCategory<RoadFactoryWrapper> {
	
	private final IDrawable background;
	private final IDrawable electric_bar;
	private final IDrawable icon;
	private final IDrawableAnimated progressBar;
	
	private final IDrawable tankCover;
	
	public static final int width = 162;
	public static final int height = 112;
	
	private boolean electric = false;
	private String uid;
	
	public RoadFactoryCategory(IGuiHelper guiHelper, String uid, boolean electric) {
		ResourceLocation guiTexture = new ResourceLocation(FurenikusRoads.MODID + ":textures/gui/jei_texture_2.png");
		background = guiHelper.createDrawable(guiTexture, 0, 0, width, height);
		electric_bar = guiHelper.createDrawable(guiTexture, 164, 0, 18, 112);
		icon = electric ? guiHelper.createDrawableIngredient(new ItemStack(FRBlocks.road_factory_electric)) : guiHelper.createDrawableIngredient(new ItemStack(FRBlocks.road_factory));
		int progressTick = electric ? RoadsConfig.machine.electricRoadFactoryTickRate : RoadsConfig.machine.roadFactoryTickRate;
		progressBar = guiHelper.createAnimatedDrawable(guiHelper.createDrawable(guiTexture, 0, 252, 160, 4), progressTick, IDrawableAnimated.StartDirection.LEFT, false);
		this.electric = electric;
		this.uid = uid;
		
		tankCover = guiHelper.createDrawable(guiTexture, 236, 0, 20, 100);
	}

	@Override
	public String getUid() {
		return uid;
	}

	@Override
	public String getTitle() {
		if (electric) {
			return I18n.format("roads.gui.electric_road_factory.name");
		}
		return I18n.format("roads.gui.road_factory.name");
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
		progressBar.draw(minecraft, 1, 107);
	}
	
	@Nullable
	@Override
	public IDrawable getIcon() {
		return icon;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, RoadFactoryWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup itemStack = recipeLayout.getItemStacks();
		IGuiFluidStackGroup fluidStack = recipeLayout.getFluidStacks();
		
		itemStack.init(RoadFactoryContainer.INPUT_1, true, 26, 12);
		itemStack.init(RoadFactoryContainer.MODIFIER, true, 74, 35);
		itemStack.init(RoadFactoryContainer.OUTPUT_1, false, 99, 12);
		fluidStack.init(12, true, 1, 1, 20, 100, RoadFactoryEntity.TANK_CAP, true, tankCover);
		
		itemStack.set(RoadFactoryContainer.INPUT_1, ingredients.getInputs(VanillaTypes.ITEM).get(0));
		itemStack.set(RoadFactoryContainer.MODIFIER, ingredients.getInputs(VanillaTypes.ITEM).get(1));
		fluidStack.set(12, ingredients.getInputs(VanillaTypes.FLUID).get(0));
		itemStack.set(RoadFactoryContainer.OUTPUT_1, ingredients.getOutputs(VanillaTypes.ITEM).get(0));
	}
}
