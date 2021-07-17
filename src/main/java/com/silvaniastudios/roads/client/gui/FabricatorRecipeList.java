package com.silvaniastudios.roads.client.gui;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.tileentities.fabricator.FabricatorEntity;
import com.silvaniastudios.roads.blocks.tileentities.recipes.FabricatorRecipes;
import com.silvaniastudios.roads.blocks.tileentities.recipes.RecipeRegistry;
import com.silvaniastudios.roads.network.FabricatorUpdatePacket;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class FabricatorRecipeList extends GuiScrollingList_Mod {

	private FontRenderer fontRenderer;
	private Minecraft client;
	private GuiScreen gui;
	private FabricatorEntity te;
	
	
	private static final ResourceLocation TEXTURE_WIDGET = new ResourceLocation(FurenikusRoads.MODID, "textures/gui/fabricator_list.png");

	public FabricatorRecipeList(int width, int height, int top, int left, int entryHeight, int screenWidth, int screenHeight, FontRenderer font, Minecraft mc, GuiScreen gui, FabricatorEntity te, ArrayList<String> tooltipList) {
		super(mc, width, height, top, height+top, left, entryHeight, screenWidth, screenHeight, tooltipList);
		this.fontRenderer = font;
		this.client = mc;
		this.gui = gui;
		this.te = te;
	}

	@Override
	protected int getSize() {
		return RecipeRegistry.fabricatorRecipes.size();
	}

	@Override
	protected void elementClicked(int index, boolean doubleClick) {
		int recipeId = RecipeRegistry.fabricatorRecipes.get(index).getId(); //will *almost* always be the same as index, but not 100% certainty.
		FurenikusRoads.PACKET_CHANNEL.sendToServer(new FabricatorUpdatePacket(recipeId, te.getPos().getX(), te.getPos().getY(), te.getPos().getZ()));
	}
	
	@Override protected boolean isSelected(int index) { return false; }
	@Override protected void drawBackground() {}
	
	@Override
	protected void drawSlot(int slotIdx, int entryRight, int slotTop, int slotBuffer, Tessellator tess) {
		GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_FOG);
        GL11.glColor4f(1,1,1,1);
        
		int left = entryRight - this.listWidth + 7;
		
		FabricatorRecipes recipe = RecipeRegistry.fabricatorRecipes.get(slotIdx);
		
		ItemStack in1 = recipe.getInput1();
		ItemStack in2 = recipe.getInput2();
		ItemStack in3 = recipe.getInput3();
		ItemStack in4 = recipe.getInput4();
		ItemStack in5 = recipe.getInput5();
		ItemStack in6 = recipe.getInput6();
		
		ItemStack output = RecipeRegistry.fabricatorRecipes.get(slotIdx).getOutput();

		

		this.client.getTextureManager().bindTexture(TEXTURE_WIDGET);
		gui.drawTexturedModalRect(left, slotTop, 0, 0, 140, 28);
		gui.drawTexturedModalRect(left + 140, slotTop, 62, 0, 194, 28);
			
		fontRenderer.drawString("" + output.getDisplayName(), left + 25, slotTop + 10, 0x404040);
		
		drawItemStack(fontRenderer, output, left + 6, slotTop + 6);
		
		if (in1 != ItemStack.EMPTY) { drawItemStack(fontRenderer, in1, left + 222, slotTop + 6); }
		if (in2 != ItemStack.EMPTY) { drawItemStack(fontRenderer, in2, left + 240, slotTop + 6); }
		if (in3 != ItemStack.EMPTY) { drawItemStack(fontRenderer, in3, left + 258, slotTop + 6); }
		if (in4 != ItemStack.EMPTY) { drawItemStack(fontRenderer, in4, left + 276, slotTop + 6); }
		if (in5 != ItemStack.EMPTY) { drawItemStack(fontRenderer, in5, left + 294, slotTop + 6); }
		if (in6 != ItemStack.EMPTY) { drawItemStack(fontRenderer, in6, left + 312, slotTop + 6); }
		
		if (mouseX >= left + 1 && mouseX <= entryRight && mouseY >= slotTop && mouseY <= slotTop + 27) {
			createTooltip(slotIdx, recipe);
		}
	}
	
	protected void createTooltip(int slotId, FabricatorRecipes recipe) {
		if (!recipe.getOutput().isEmpty()) {

			tooltipList.add(recipe.getOutput().getCount() + "x " + recipe.getOutput().getDisplayName());
			
			if (recipe.getInput1() != ItemStack.EMPTY) { tooltipList.add("Slot 1: " + recipe.getInput1().getCount() + "x " + recipe.getInput1().getDisplayName()); }
			if (recipe.getInput2() != ItemStack.EMPTY) { tooltipList.add("Slot 2: " + recipe.getInput2().getCount() + "x " + recipe.getInput2().getDisplayName()); }
			if (recipe.getInput3() != ItemStack.EMPTY) { tooltipList.add("Slot 3: " + recipe.getInput3().getCount() + "x " + recipe.getInput3().getDisplayName()); }
			if (recipe.getInput4() != ItemStack.EMPTY) { tooltipList.add("Slot 4: " + recipe.getInput4().getCount() + "x " + recipe.getInput4().getDisplayName()); }
			if (recipe.getInput5() != ItemStack.EMPTY) { tooltipList.add("Slot 5: " + recipe.getInput5().getCount() + "x " + recipe.getInput5().getDisplayName()); }
			if (recipe.getInput6() != ItemStack.EMPTY) { tooltipList.add("Slot 6: " + recipe.getInput6().getCount() + "x " + recipe.getInput6().getDisplayName()); }
		}
	}
}
