package com.silvaniastudios.roads.client.gui;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.tileentities.fabricator.FabricatorContainer;
import com.silvaniastudios.roads.blocks.tileentities.fabricator.FabricatorElectricEntity;
import com.silvaniastudios.roads.blocks.tileentities.fabricator.FabricatorEntity;
import com.silvaniastudios.roads.blocks.tileentities.recipes.FabricatorRecipes;
import com.silvaniastudios.roads.blocks.tileentities.recipes.RecipeRegistry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiFabricator extends GuiContainer {
	
	private static final ResourceLocation guiTextures = new ResourceLocation(FurenikusRoads.MODID + ":textures/gui/fabricator.png");
	private FabricatorEntity tileEntity;
	private boolean electric;
	GuiHiddenButton openSelector;
	FabricatorRecipes recipe;

	public GuiFabricator(FabricatorEntity tileEntity, FabricatorContainer container, boolean electric) {
		super(container);
		xSize = 176;
		ySize = 152;
		this.tileEntity = tileEntity;
		this.electric = electric;
	}
	
	public void initGui() {
		super.initGui();
		int guiLeft = (width - xSize) / 2;
	    int guiTop = (height - ySize) / 2;
	    
	    openSelector = new GuiHiddenButton(1, guiLeft + 78, guiTop + 20, 34, 34);

		buttonList.add(openSelector);
		
		setRecipe();
	}
	
	private void setRecipe() {
		for (int i = 0; i < RecipeRegistry.fabricatorRecipes.size(); i++) {
			FabricatorRecipes recipe = RecipeRegistry.fabricatorRecipes.get(i);
			if (recipe.getId() == tileEntity.recipeId) {
				this.recipe = recipe;
				break;
			}
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
        
        if (recipe.getId() != tileEntity.recipeId) {
        	setRecipe();
        }
    }
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		int left = (width - xSize) / 2;
		int top = (height - ySize) / 2;
		
		drawTooltip(2, left, top, mouseX, mouseY);
		if (electric) {
			fontRenderer.drawString(I18n.format("roads.gui.electric_fabricator.name"), 6, 6, 4210752);
		} else {
			fontRenderer.drawString(I18n.format("roads.gui.fabricator.name"), 6, 6, 4210752);
		}
	}
	
	private void drawTooltip(int col, int left, int top, int mouseX, int mouseY) {
		if (electric) {
			FabricatorElectricEntity fee = (FabricatorElectricEntity) tileEntity; //...fi fo fum?
			if (mouseX >= (left + 155) && mouseX <= (left + 169) && mouseY >= (top + 7) && mouseY <= (top + 49)) { this.drawHoveringText(fee.energy.getEnergyStored() + "/" + fee.energy.getMaxEnergyStored(), mouseX - left, mouseY - top + 15); }
		} else {
			if (mouseX >= (left + 153) && mouseX <= (left + 167) && mouseY >= (top + 15) && mouseY <= (top + 29)) { this.drawHoveringText(tileEntity.fuel_remaining + "/" + tileEntity.last_fuel_cap, mouseX - left, mouseY - top + 15); }
		}
		
		if (tileEntity.inventory.getStackInSlot(FabricatorContainer.IN_1).getCount() < 1 && recipe != null) {
			if (mouseX >= (left + 7) && mouseX <= (left + 24) && mouseY >= (top + 19) && mouseY <= (top + 36)) {
				if (recipe.getInput1() != ItemStack.EMPTY) { this.drawHoveringText(I18n.format("roads.gui.fabricator.requirement") + recipe.getInput1().getCount() + "x " + recipe.getInput1().getDisplayName(), mouseX - left, mouseY - top + 15); }
			}
		}
		
		if (tileEntity.inventory.getStackInSlot(FabricatorContainer.IN_2).getCount() < 1 && recipe != null) {
			if (mouseX >= (left + 25) && mouseX <= (left + 42) && mouseY >= (top + 19) && mouseY <= (top + 36)) {
				if (recipe.getInput2() != ItemStack.EMPTY) { this.drawHoveringText(I18n.format("roads.gui.fabricator.requirement") + recipe.getInput2().getCount() + "x " + recipe.getInput2().getDisplayName(), mouseX - left, mouseY - top + 15); }
			}
		}
		
		if (tileEntity.inventory.getStackInSlot(FabricatorContainer.IN_3).getCount() < 1 && recipe != null) {
			if (mouseX >= (left + 43) && mouseX <= (left + 60) && mouseY >= (top + 19) && mouseY <= (top + 36)) {
				if (recipe.getInput3() != ItemStack.EMPTY) { this.drawHoveringText(I18n.format("roads.gui.fabricator.requirement") + recipe.getInput3().getCount() + "x " + recipe.getInput3().getDisplayName(), mouseX - left, mouseY - top + 15); }
			}
		}
		
		if (tileEntity.inventory.getStackInSlot(FabricatorContainer.IN_4).getCount() < 1 && recipe != null) {
			if (mouseX >= (left + 7) && mouseX <= (left + 24) && mouseY >= (top + 37) && mouseY <= (top + 54)) {
				if (recipe.getInput4() != ItemStack.EMPTY) { this.drawHoveringText(I18n.format("roads.gui.fabricator.requirement") + recipe.getInput4().getCount() + "x " + recipe.getInput4().getDisplayName(), mouseX - left, mouseY - top + 15); }
			}
		}
		
		if (tileEntity.inventory.getStackInSlot(FabricatorContainer.IN_5).getCount() < 1 && recipe != null) {
			if (mouseX >= (left + 25) && mouseX <= (left + 42) && mouseY >= (top + 37) && mouseY <= (top + 54)) {
				if (recipe.getInput5() != ItemStack.EMPTY) { this.drawHoveringText(I18n.format("roads.gui.fabricator.requirement") + recipe.getInput5().getCount() + "x " + recipe.getInput5().getDisplayName(), mouseX - left, mouseY - top + 15); }
			}
		}
		
		if (tileEntity.inventory.getStackInSlot(FabricatorContainer.IN_6).getCount() < 1 && recipe != null) {
			if (mouseX >= (left + 43) && mouseX <= (left + 60) && mouseY >= (top + 37) && mouseY <= (top + 54)) {
				if (recipe.getInput6() != ItemStack.EMPTY) { this.drawHoveringText(I18n.format("roads.gui.fabricator.requirement") + recipe.getInput6().getCount() + "x " + recipe.getInput6().getDisplayName(), mouseX - left, mouseY - top + 15); }
			}
		}
		
		if (mouseX >= (left + 79) && mouseX <= (left + 111) && mouseY >= (top + 21) && mouseY <= (top + 53)) { this.drawHoveringText(I18n.format("roads.gui.fabricator.recipe_select"), mouseX - left, mouseY - top + 15); }
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(guiTextures);
        int left = (width - xSize) / 2;
        int top = (height - ySize) / 2;
        
        drawTexturedModalRect(left, top, 0, 0, xSize, ySize);
        if (electric) {
        	drawTexturedModalRect(left+xSize-25, top, 176, 0, 25, ySize);
        }
        
        drawFuel(left, top);
        drawProgress(left, top);
        if (recipe != null) {
        	drawItemStack(recipe.getOutput(), left + 79, top + 21);
        	
        	if (recipe.getInput1() != ItemStack.EMPTY && tileEntity.inventory.getStackInSlot(FabricatorContainer.IN_1).getCount() < 1) { drawIngredientStack(recipe.getInput1(), left + 16, top + 28); }
        	if (recipe.getInput2() != ItemStack.EMPTY && tileEntity.inventory.getStackInSlot(FabricatorContainer.IN_2).getCount() < 1) { drawIngredientStack(recipe.getInput2(), left + 34, top + 28); }
        	if (recipe.getInput3() != ItemStack.EMPTY && tileEntity.inventory.getStackInSlot(FabricatorContainer.IN_3).getCount() < 1) { drawIngredientStack(recipe.getInput3(), left + 52, top + 28); }
        	if (recipe.getInput4() != ItemStack.EMPTY && tileEntity.inventory.getStackInSlot(FabricatorContainer.IN_4).getCount() < 1) { drawIngredientStack(recipe.getInput4(), left + 16, top + 46); }
        	if (recipe.getInput5() != ItemStack.EMPTY && tileEntity.inventory.getStackInSlot(FabricatorContainer.IN_5).getCount() < 1) { drawIngredientStack(recipe.getInput5(), left + 34, top + 46); }
        	if (recipe.getInput6() != ItemStack.EMPTY && tileEntity.inventory.getStackInSlot(FabricatorContainer.IN_6).getCount() < 1) { drawIngredientStack(recipe.getInput6(), left + 52, top + 46); }
        }
	}
	
	private void drawFuel(int left, int top) {
		if (electric) {
			FabricatorElectricEntity fee = (FabricatorElectricEntity) tileEntity;
			int p = getPercentage(fee.energy.getEnergyStored(), fee.energy.getMaxEnergyStored());
			int x = Math.round(p / 2.5F);
			if (p == 100) { x = 42; }
			drawTexturedModalRect(left + 155, top + 7 + (42-x), 242, 42-x, 14, x);
		} else {
			int p = getPercentage(tileEntity.fuel_remaining, tileEntity.last_fuel_cap);
			int x = Math.round(p / 7.0F);
			drawTexturedModalRect(left + 153, top + 15 + (14-x), 229, 14-x, 13, x);
		}
	}
	
	private void drawProgress(int left, int top) {
		int p = getPercentage(tileEntity.timerCount, electric ? RoadsConfig.machine.electricCompactorTickRate : RoadsConfig.machine.compactorTickRate);
		int x = Math.round(p * 1.6F);
		drawTexturedModalRect(left + 8, top + 60, 0, 252, x, 4);
	}
	
	private int getPercentage(int num, int max) {
		float x = (float) num / (float) max;
		int y = Math.round(x*100);
		if (y > 100) { return 100; }
		return y;
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		if (button == openSelector) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiFabricatorSelector(tileEntity));
		}
	}
	
	private void drawItemStack(ItemStack stack, int x, int y) {
		//Move the itemstack forward (towards the player) to give a "stacking" look
		RenderHelper.disableStandardItemLighting();
		RenderHelper.enableGUIStandardItemLighting();
		GlStateManager.translate(0.0F, 0.0F, 32.0F);
		GlStateManager.scale(2.0, 2.0, 2.0);
        Minecraft.getMinecraft().getRenderItem().renderItemAndEffectIntoGUI(stack, x/2, y/2);
        GlStateManager.scale(0.5, 0.5, 0.5);
        GlStateManager.translate(0.0F, 0.0F, 3.0F);
        fontRenderer.drawString("" + stack.getCount(), (x+26), (y+24), 0xFFFFFF);
        GlStateManager.translate(0.0F, 0.0F, -35.0F);
        RenderHelper.enableStandardItemLighting();
    }
	
	private void drawIngredientStack(ItemStack stack, int x, int y) {
		//Move the itemstack forward (towards the player) to give a "stacking" look
		RenderHelper.disableStandardItemLighting();
		RenderHelper.enableGUIStandardItemLighting();
		GlStateManager.translate(0.0F, 0.0F, 32.0F);
		GlStateManager.scale(0.5, 0.5, 0.5);
		fontRenderer.drawString(stack.getCount() + "x", (x-19+fontRenderer.getStringWidth(stack.getCount()+"x"))*2, (y+3)*2, 0xFFFFFF);
        Minecraft.getMinecraft().getRenderItem().renderItemAndEffectIntoGUI(stack, x*2, y*2);
        GlStateManager.scale(2.0, 2.0, 2.0);
        
        GlStateManager.translate(0.0F, 0.0F, -32.0F);
        RenderHelper.enableStandardItemLighting();
    }
}
