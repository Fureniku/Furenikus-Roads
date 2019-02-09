package com.silvaniastudios.roads.client.model;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.tileentities.roadfactory.RoadFactoryContainer;
import com.silvaniastudios.roads.blocks.tileentities.roadfactory.RoadFactoryEntity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.animation.FastTESR;
import net.minecraftforge.fluids.FluidStack;

public class RoadFactoryRenderer extends FastTESR<RoadFactoryEntity> {
	
	private static float p = 1/16F; //one "pixel"

	@Override
	public void renderTileEntityFast(RoadFactoryEntity te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {
		FluidStack fluid = te.tarFluid.getFluid();
		
		int meta = te.getBlockType().getMetaFromState(te.getState());
		
		if (fluid != null && fluid.amount > 0) {
			renderTankFluid(buffer, x, y, z, fluid.amount, meta, te.isFilling, te);
		}
		
		ItemStack out1 = te.inventory.getStackInSlot(RoadFactoryContainer.OUTPUT_1);
		ItemStack out2 = te.inventory.getStackInSlot(RoadFactoryContainer.OUTPUT_2);
		ItemStack out3 = te.inventory.getStackInSlot(RoadFactoryContainer.OUTPUT_3);
		ItemStack out4 = te.inventory.getStackInSlot(RoadFactoryContainer.OUTPUT_4);

		if (!out1.isEmpty()) { renderBlock(buffer, x, y, z, meta, out1, 0); }
		if (!out2.isEmpty()) { renderBlock(buffer, x, y, z, meta, out2, 1); }
		if (!out3.isEmpty()) { renderBlock(buffer, x, y, z, meta, out3, 2); }
		if (!out4.isEmpty()) { renderBlock(buffer, x, y, z, meta, out4, 3); }
	}
	
	private static void renderTankFluid(final BufferBuilder buffer, double x, double y, double z, int tankFill, int meta, boolean isFilling, RoadFactoryEntity te) {
		float fill = 0.00734375F * RenderHelper.getPercentage(tankFill, RoadFactoryEntity.TANK_CAP);

		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(FurenikusRoads.MODID + ":fluids/tar_flowing");
		if (meta == 0) { RenderHelper.renderCube(buffer, x, y, z, 1.5F*p, 0.25F*p,  12F*p, 2.5F*p, fill, 2.5F*p, sprite); }
		if (meta == 1) { RenderHelper.renderCube(buffer, x, y, z, 1.5F*p, 0.25F*p, 1.5F*p, 2.5F*p, fill, 2.5F*p, sprite); }
		if (meta == 2) { RenderHelper.renderCube(buffer, x, y, z,  12F*p, 0.25F*p, 1.5F*p, 2.5F*p, fill, 2.5F*p, sprite); }
		if (meta == 3) { RenderHelper.renderCube(buffer, x, y, z,  12F*p, 0.25F*p,  12F*p, 2.5F*p, fill, 2.5F*p, sprite); }
		
		if (isFilling) {
			if (meta == 0) { RenderHelper.renderCube(buffer, x, y, z, 2.5F*p, 0.25F*p,  13F*p, 0.5F*p, 12F*p, 0.5F*p, sprite); }
			if (meta == 1) { RenderHelper.renderCube(buffer, x, y, z, 2.5F*p, 0.25F*p, 2.5F*p, 0.5F*p, 12F*p, 0.5F*p, sprite); }
			if (meta == 2) { RenderHelper.renderCube(buffer, x, y, z,  13F*p, 0.25F*p, 2.5F*p, 0.5F*p, 12F*p, 0.5F*p, sprite); }
			if (meta == 3) { RenderHelper.renderCube(buffer, x, y, z,  13F*p, 0.25F*p,  13F*p, 0.5F*p, 12F*p, 0.5F*p, sprite); }
		}
	}
	

	
	@SuppressWarnings("deprecation")
	private static void renderBlock(final BufferBuilder buffer, double x, double y, double z, int meta, ItemStack stack, int slot) {
		if (stack.getItem() instanceof ItemBlock) {
			float offset = (3.25F*p) * slot;
			
			ItemBlock ib = (ItemBlock) stack.getItem();
			IBlockState state = ib.getBlock().getStateFromMeta(stack.getItemDamage());
			IBakedModel model = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getModelForState(state);
			TextureAtlasSprite sprite = model.getParticleTexture();
			
			if (meta == 0) { RenderHelper.renderCube(buffer, x, y, z, 12.125F*p,          4.25F*p,  2.375F*p + offset, 1.5F*p, 1.5F*p, 1.5F*p, sprite); }
			if (meta == 1) { RenderHelper.renderCube(buffer, x, y, z, 12.125F*p - offset, 4.25F*p, 12.125F*p,          1.5F*p, 1.5F*p, 1.5F*p, sprite); }
			if (meta == 2) { RenderHelper.renderCube(buffer, x, y, z,  2.375F*p,          4.25F*p, 12.125F*p - offset, 1.5F*p, 1.5F*p, 1.5F*p, sprite); }
			if (meta == 3) { RenderHelper.renderCube(buffer, x, y, z,  2.375F*p + offset, 4.25F*p,  2.375F*p,          1.5F*p, 1.5F*p, 1.5F*p, sprite); }
		}
	}

}
