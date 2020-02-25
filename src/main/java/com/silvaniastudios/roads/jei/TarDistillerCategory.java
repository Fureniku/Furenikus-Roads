package com.silvaniastudios.roads.jei;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.tileentities.distiller.TarDistillerContainer;
import com.silvaniastudios.roads.blocks.tileentities.distiller.TarDistillerEntity;

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
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class TarDistillerCategory implements IRecipeCategory<TarDistillerWrapper> {
	
	private final IDrawable background;
	private final IDrawable electric_bar;
	private final IDrawable icon;
	private final IDrawableAnimated progressBar;
	
	private final IDrawable tankCover;
	
	public static final int width = 184;
	public static final int height = 112;
	
	private boolean electric = false;
	private String uid;
	
	public TarDistillerCategory(IGuiHelper guiHelper, String uid, boolean electric) {
		ResourceLocation guiTexture = new ResourceLocation(FurenikusRoads.MODID + ":textures/gui/jei_texture_2.png");
		background = guiHelper.createDrawable(guiTexture, 0, 114, width, height);
		electric_bar = guiHelper.createDrawable(guiTexture, 186, 114, 18, 112);
		icon = electric ? guiHelper.createDrawableIngredient(new ItemStack(FRBlocks.tar_distiller_electric)) : guiHelper.createDrawableIngredient(new ItemStack(FRBlocks.tar_distiller));
		int progressTick = electric ? RoadsConfig.machine.electricTarDistillerTickRate : RoadsConfig.machine.tarDistillerTickRate;
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
			return I18n.format("roads.gui.electric_tar_distiller.name");
		}
		return I18n.format("roads.gui.tar_distiller.name");
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
	public void setRecipe(IRecipeLayout recipeLayout, TarDistillerWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup itemStack = recipeLayout.getItemStacks();
		IGuiFluidStackGroup fluidStack = recipeLayout.getFluidStacks();
		
		itemStack.init(TarDistillerContainer.INPUT, true, 26, 22);
		itemStack.init(TarDistillerContainer.OUTPUT_1, false, 92, 0);
		itemStack.init(TarDistillerContainer.OUTPUT_2, false, 92, 22);
		if (!electric) { itemStack.init(TarDistillerContainer.FUEL, true, 166, 22); }
		fluidStack.init(10, true, 1, 1, 20, 100, TarDistillerEntity.TANK_CAP, true, tankCover);
		fluidStack.init(11, false, 115, 1, 20, 100, TarDistillerEntity.TANK_CAP, true, tankCover);
		fluidStack.init(12, false, 141, 1, 20, 100, TarDistillerEntity.TANK_CAP, true, tankCover);
		
		itemStack.set(TarDistillerContainer.INPUT, ingredients.getInputs(VanillaTypes.ITEM).get(0));
		if (!electric) { itemStack.set(TarDistillerContainer.FUEL, new ItemStack(Items.LAVA_BUCKET)); }
		itemStack.set(TarDistillerContainer.OUTPUT_1, ingredients.getOutputs(VanillaTypes.ITEM).get(0));
		itemStack.set(TarDistillerContainer.OUTPUT_2, ingredients.getOutputs(VanillaTypes.ITEM).get(1));
		
		int in = ingredients.getInputs(VanillaTypes.FLUID).size();
		int out = ingredients.getOutputs(VanillaTypes.FLUID).size();
		
		if (in > 0) { fluidStack.set(10, ingredients.getInputs(VanillaTypes.FLUID).get(0)); }
		if (out > 0) { fluidStack.set(11, ingredients.getOutputs(VanillaTypes.FLUID).get(0)); }
		if (out > 1) { fluidStack.set(12, ingredients.getOutputs(VanillaTypes.FLUID).get(1)); }
		
	}
}
