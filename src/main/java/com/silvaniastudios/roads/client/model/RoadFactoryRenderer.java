package com.silvaniastudios.roads.client.model;

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
	
	private static float p = 1/16; //one "pixel"

	@Override
	public void renderTileEntityFast(RoadFactoryEntity te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {
		FluidStack fluid = te.tarFluid.getFluid();
		
		int meta = te.getBlockType().getMetaFromState(te.getState());
		if (fluid != null && fluid.amount > 0) {
			renderTankFluid(buffer, x, y, z, fluid, meta, te.isFilling, te);
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
	
	private static void renderTankFluid(final BufferBuilder buffer, double x, double y, double z, FluidStack fluid, int meta, boolean isFilling, RoadFactoryEntity te) {
		if (fluid != null) {
			TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(fluid.getFluid().getStill(fluid).toString());
			if (meta == 0) { RenderHelper.renderTankFluid(buffer, x, y, z, 1.5F, 0.25F,  12F, 2.5F, 11.75F, 2.5F, fluid.amount, RoadFactoryEntity.TANK_CAP, sprite); }
			if (meta == 1) { RenderHelper.renderTankFluid(buffer, x, y, z, 1.5F, 0.25F, 1.5F, 2.5F, 11.75F, 2.5F, fluid.amount, RoadFactoryEntity.TANK_CAP, sprite); }
			if (meta == 2) { RenderHelper.renderTankFluid(buffer, x, y, z,  12F, 0.25F, 1.5F, 2.5F, 11.75F, 2.5F, fluid.amount, RoadFactoryEntity.TANK_CAP, sprite); }
			if (meta == 3) { RenderHelper.renderTankFluid(buffer, x, y, z,  12F, 0.25F,  12F, 2.5F, 11.75F, 2.5F, fluid.amount, RoadFactoryEntity.TANK_CAP, sprite); }
			
			if (isFilling) {
				if (meta == 0) { RenderHelper.renderTankFluid(buffer, x, y, z, 2.5F, 0.25F,  13F, 0.5F, 12F, 0.5F, fluid.amount, RoadFactoryEntity.TANK_CAP, sprite); }
				if (meta == 1) { RenderHelper.renderTankFluid(buffer, x, y, z, 2.5F, 0.25F, 2.5F, 0.5F, 12F, 0.5F, fluid.amount, RoadFactoryEntity.TANK_CAP, sprite); }
				if (meta == 2) { RenderHelper.renderTankFluid(buffer, x, y, z,  13F, 0.25F, 2.5F, 0.5F, 12F, 0.5F, fluid.amount, RoadFactoryEntity.TANK_CAP, sprite); }
				if (meta == 3) { RenderHelper.renderTankFluid(buffer, x, y, z,  13F, 0.25F,  13F, 0.5F, 12F, 0.5F, fluid.amount, RoadFactoryEntity.TANK_CAP, sprite); }
			}
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
