package com.silvaniastudios.roads.jei;

import java.util.ArrayList;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.tileentities.tarmaccutter.TarmacCutterContainer;
import com.silvaniastudios.roads.items.FRItems;

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

public class TarmacCutterCategory implements IRecipeCategory<TarmacCutterWrapper> {
	
	private final IDrawable background;
	private final IDrawable electric_bar;
	private final IDrawable icon;
	private final IDrawableAnimated progressBar;
	
	public static final int width = 162;
	public static final int height = 52;
	
	private String uid;
	
	private boolean electric = false;
	
	public TarmacCutterCategory(IGuiHelper guiHelper, String uid, boolean electric) {
		ResourceLocation guiTexture = new ResourceLocation(FurenikusRoads.MODID + ":textures/gui/jei_texture_1.png");
		background = guiHelper.createDrawable(guiTexture, 0, 54, width, height);
		electric_bar = guiHelper.createDrawable(guiTexture, 164, 0, 18, 52);
		icon = electric ? guiHelper.createDrawableIngredient(new ItemStack(FRBlocks.tarmac_cutter_electric)) : guiHelper.createDrawableIngredient(new ItemStack(FRBlocks.tarmac_cutter));
		int progressTick = electric ? RoadsConfig.machine.electricTarmacCutterTickRate : RoadsConfig.machine.tarmacCutterTickRate;
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
			return I18n.format("roads.gui.electric_tarmac_cutter.name");
		}
		return I18n.format("roads.gui.tarmac_cutter.name");
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
	public void setRecipe(IRecipeLayout recipeLayout, TarmacCutterWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiStack = recipeLayout.getItemStacks();
		
		ArrayList<ItemStack> blade_1 = new ArrayList<ItemStack>();
		ArrayList<ItemStack> blade_2 = new ArrayList<ItemStack>();
		ArrayList<ItemStack> blade_4 = new ArrayList<ItemStack>();
		ArrayList<ItemStack> blade_8 = new ArrayList<ItemStack>();
		
		blade_1.add(new ItemStack(FRItems.tarmac_cutter_blade_1_diamond));
		blade_1.add(new ItemStack(FRItems.tarmac_cutter_blade_1_gold));
		blade_1.add(new ItemStack(FRItems.tarmac_cutter_blade_1_iron));
		blade_2.add(new ItemStack(FRItems.tarmac_cutter_blade_2_diamond));
		blade_2.add(new ItemStack(FRItems.tarmac_cutter_blade_2_gold));
		blade_2.add(new ItemStack(FRItems.tarmac_cutter_blade_2_iron));
		blade_4.add(new ItemStack(FRItems.tarmac_cutter_blade_4_diamond));
		blade_4.add(new ItemStack(FRItems.tarmac_cutter_blade_4_gold));
		blade_4.add(new ItemStack(FRItems.tarmac_cutter_blade_4_iron));
		blade_8.add(new ItemStack(FRItems.tarmac_cutter_blade_8_diamond));
		blade_8.add(new ItemStack(FRItems.tarmac_cutter_blade_8_gold));
		blade_8.add(new ItemStack(FRItems.tarmac_cutter_blade_8_iron));
		
		guiStack.init(TarmacCutterContainer.INPUT, true, 0, 24);
		guiStack.init(TarmacCutterContainer.BLADE, true, 45, 24);
		guiStack.init(TarmacCutterContainer.OUTPUT_1, false, 90, 24);
		guiStack.init(TarmacCutterContainer.OUTPUT_2, false, 108, 24);
		
		ItemStack fragments = ingredients.getOutputs(VanillaTypes.ITEM).get(1).get(0);
		
		guiStack.set(TarmacCutterContainer.INPUT, ingredients.getInputs(VanillaTypes.ITEM).get(0));
		
		if (fragments.getCount() == 1) {
			guiStack.set(TarmacCutterContainer.BLADE, blade_1);
		} else if (fragments.getCount() == 2) {
			guiStack.set(TarmacCutterContainer.BLADE, blade_2);
		} else if (fragments.getCount() == 4) {
			guiStack.set(TarmacCutterContainer.BLADE, blade_4);
		} else {
			guiStack.set(TarmacCutterContainer.BLADE, blade_8);
		}
		
		guiStack.set(TarmacCutterContainer.OUTPUT_1, ingredients.getOutputs(VanillaTypes.ITEM).get(0));
		guiStack.set(TarmacCutterContainer.OUTPUT_2, fragments);
	}

}
