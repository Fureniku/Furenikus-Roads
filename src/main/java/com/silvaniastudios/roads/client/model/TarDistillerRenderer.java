package com.silvaniastudios.roads.client.model;

import com.silvaniastudios.roads.blocks.tileentities.distiller.TarDistillerContainer;
import com.silvaniastudios.roads.blocks.tileentities.distiller.TarDistillerEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.client.model.animation.FastTESR;
import net.minecraftforge.fluids.FluidStack;

public class TarDistillerRenderer extends FastTESR<TarDistillerEntity> {
	
	private static float p = 1/16F; //one "pixel"

	@Override
	public void renderTileEntityFast(TarDistillerEntity te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {
		FluidStack fluidIn = te.fluidInput.getFluid();
		FluidStack fluidTar = te.fluidOutput1.getFluid();
		FluidStack fluidOut2 = te.fluidOutput2.getFluid();
		
		int meta = te.getBlockType().getMetaFromState(te.getState());
		
		if (fluidIn != null && fluidIn.amount > 0) {
			if (fluidIn.amount < TarDistillerEntity.TANK_CAP - 1000 && !te.inventory.getStackInSlot(TarDistillerContainer.FLUID_IN).isEmpty()) {
				renderTankFluid(buffer, x, y, z, 0, fluidIn, meta, true);
			} else {
				renderTankFluid(buffer, x, y, z, 0, fluidIn, meta, false);
			}
		}
		
		if (fluidTar != null && fluidTar.amount > 0) {
			if (fluidTar.amount < TarDistillerEntity.TANK_CAP - 1000 && !te.inventory.getStackInSlot(TarDistillerContainer.INPUT).isEmpty()) {
				renderTankFluid(buffer, x, y, z, 1, fluidTar, meta, true);
			} else {
				renderTankFluid(buffer, x, y, z, 1, fluidTar, meta, false);
			}
		}
		
		if (fluidOut2 != null && fluidOut2.amount > 0) {
			renderTankFluid(buffer, x, y, z, 2, fluidOut2, meta, false);
		}
	}
	
	private static void renderTankFluid(final BufferBuilder buffer, double x, double y, double z, int tankId, FluidStack fluid, int meta, boolean isFilling) {
		float fill = 0F;
		if (fluid != null) {
			TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(fluid.getFluid().getStill(fluid).toString());
			if (tankId == 0) {
				if (meta == 0) { RenderHelper.renderTankFluid(buffer, x, y, z,  1.5F, 0.25F, 6.75F, 2.5F, 11.75F, 2.5F, fluid.amount, TarDistillerEntity.TANK_CAP, sprite); }
				if (meta == 1) { RenderHelper.renderTankFluid(buffer, x, y, z, 6.75F, 0.25F,  1.5F, 2.5F, 11.75F, 2.5F, fluid.amount, TarDistillerEntity.TANK_CAP, sprite); }
				if (meta == 2) { RenderHelper.renderTankFluid(buffer, x, y, z,   12F, 0.25F, 6.75F, 2.5F, 11.75F, 2.5F, fluid.amount, TarDistillerEntity.TANK_CAP, sprite); }
				if (meta == 3) { RenderHelper.renderTankFluid(buffer, x, y, z, 6.75F, 0.25F,   12F, 2.5F, 11.75F, 2.5F, fluid.amount, TarDistillerEntity.TANK_CAP, sprite); }
				
				if (isFilling) {
					if (meta == 0) { RenderHelper.renderTankFluid(buffer, x, y, z,  2.5F, 0.25F, 7.75F, 0.5F, 12F, 0.5F, fluid.amount, TarDistillerEntity.TANK_CAP, sprite); }
					if (meta == 1) { RenderHelper.renderTankFluid(buffer, x, y, z, 7.75F, 0.25F,  2.5F, 0.5F, 12F, 0.5F, fluid.amount, TarDistillerEntity.TANK_CAP, sprite); }
					if (meta == 2) { RenderHelper.renderTankFluid(buffer, x, y, z,   13F, 0.25F, 7.75F, 0.5F, 12F, 0.5F, fluid.amount, TarDistillerEntity.TANK_CAP, sprite); }
					if (meta == 3) { RenderHelper.renderTankFluid(buffer, x, y, z, 7.75F, 0.25F,   13F, 0.5F, 12F, 0.5F, fluid.amount, TarDistillerEntity.TANK_CAP, sprite); }
				}
				
				fill = 0.00171875F * RenderHelper.getPercentage(fluid.amount, TarDistillerEntity.TANK_CAP);
				
				float xF = (float) x;
				float yF = (float) y;
				float zF = (float) z;
				
				if (meta == 0) {
					RenderHelper.renderFlatQuad(buffer, sprite, xF +  3.5F*p, yF + 9F*p, zF + 14.51F*p, xF +  3.5F*p, yF + (9F*p) + fill, zF + 14.51F*p, xF +    3F*p, yF + (9F*p) + fill, zF + 14.51F*p, xF +    3F*p, yF + 9F*p, zF + 14.51F*p); //South side
					RenderHelper.renderFlatQuad(buffer, sprite, xF + 8.75F*p, yF + 9F*p, zF +  1.49F*p, xF + 8.75F*p, yF + (9F*p) + fill, zF +  1.49F*p, xF + 9.25F*p, yF + (9F*p) + fill, zF +  1.49F*p, xF + 9.25F*p, yF + 9F*p, zF +  1.49F*p); //North side
				}
				
				if (meta == 1) {
					RenderHelper.renderFlatQuad(buffer, sprite, xF +  1.49F*p, yF + 9F*p, zF +  3.5F*p, xF +  1.49F*p, yF + (9F*p) + fill, zF +  3.5F*p, xF +  1.49F*p, yF + (9F*p) + fill, zF +    3F*p, xF +  1.49F*p, yF + 9F*p, zF +    3F*p); //West side
					RenderHelper.renderFlatQuad(buffer, sprite, xF + 14.51F*p, yF + 9F*p, zF + 8.75F*p, xF + 14.51F*p, yF + (9F*p) + fill, zF + 8.75F*p, xF + 14.51F*p, yF + (9F*p) + fill, zF + 9.25F*p, xF + 14.51F*p, yF + 9F*p, zF + 9.25F*p); //East Side
				}
				
				if (meta == 2) {
					RenderHelper.renderFlatQuad(buffer, sprite, xF + 12.5F*p, yF + 9F*p, zF +  1.49F*p, xF + 12.5F*p, yF + (9F*p) + fill, zF +  1.49F*p, xF +   13F*p, yF + (9F*p) + fill, zF + 1.49F*p, xF +    13F*p, yF + 9F*p, zF +  1.49F*p); //North Side
					RenderHelper.renderFlatQuad(buffer, sprite, xF + 6.75F*p, yF + 9F*p, zF + 14.51F*p, xF + 6.75F*p, yF + (9F*p) + fill, zF + 14.51F*p, xF + 7.25F*p, yF + (9F*p) + fill, zF + 14.51F*p, xF + 7.25F*p, yF + 9F*p, zF + 14.51F*p); //South side
				}
				
				if (meta == 3) {
					RenderHelper.renderFlatQuad(buffer, sprite, xF + 14.51F*p, yF + 9F*p, zF + 12.5F*p, xF + 14.51F*p, yF + (9F*p) + fill, zF + 12.5F*p, xF + 14.51F*p, yF + (9F*p) + fill, zF +   13F*p, xF + 14.51F*p, yF + 9F*p, zF +   13F*p); //East side
					RenderHelper.renderFlatQuad(buffer, sprite, xF +  1.49F*p, yF + 9F*p, zF + 6.75F*p, xF +  1.49F*p, yF + (9F*p) + fill, zF + 6.75F*p, xF +  1.49F*p, yF + (9F*p) + fill, zF + 7.25F*p, xF +  1.49F*p, yF + 9F*p, zF + 7.25F*p); //West side
				}
			}
		
			if (tankId == 1) {
				if (meta == 0) { RenderHelper.renderTankFluid(buffer, x, y, z, 11.75F, 0.25F,   1.5F, 2.5F, 11.75F, 2.5F, fluid.amount, TarDistillerEntity.TANK_CAP, sprite); }
				if (meta == 1) { RenderHelper.renderTankFluid(buffer, x, y, z,    12F, 0.25F, 11.75F, 2.5F, 11.75F, 2.5F, fluid.amount, TarDistillerEntity.TANK_CAP, sprite); }
				if (meta == 2) { RenderHelper.renderTankFluid(buffer, x, y, z,  1.75F, 0.25F,    12F, 2.5F, 11.75F, 2.5F, fluid.amount, TarDistillerEntity.TANK_CAP, sprite); }
				if (meta == 3) { RenderHelper.renderTankFluid(buffer, x, y, z,   1.5F, 0.25F,  1.75F, 2.5F, 11.75F, 2.5F, fluid.amount, TarDistillerEntity.TANK_CAP, sprite); }
				
				if (isFilling) {
					if (meta == 0) { RenderHelper.renderTankFluid(buffer, x, y, z, 12.75F, 0.25F,   2.5F, 0.5F, 12F, 0.5F, fluid.amount, TarDistillerEntity.TANK_CAP, sprite); }
					if (meta == 1) { RenderHelper.renderTankFluid(buffer, x, y, z,  12.5F, 0.25F, 12.25F, 0.5F, 12F, 0.5F, fluid.amount, TarDistillerEntity.TANK_CAP, sprite); }
					if (meta == 2) { RenderHelper.renderTankFluid(buffer, x, y, z,  2.75F, 0.25F,    13F, 0.5F, 12F, 0.5F, fluid.amount, TarDistillerEntity.TANK_CAP, sprite); }
					if (meta == 3) { RenderHelper.renderTankFluid(buffer, x, y, z,   2.5F, 0.25F,  2.75F, 0.5F, 12F, 0.5F, fluid.amount, TarDistillerEntity.TANK_CAP, sprite); }
				}
				
				fill = 0.00171875F * RenderHelper.getPercentage(fluid.amount, TarDistillerEntity.TANK_CAP);
				
				float xF = (float) x;
				float yF = (float) y;
				float zF = (float) z;
				
				if (meta == 0) {
					RenderHelper.renderFlatQuad(buffer, sprite, xF +    8F*p, yF + 9F*p, zF + 14.51F*p, xF +    8F*p, yF + (9F*p) + fill, zF + 14.51F*p, xF + 7.5F*p, yF + (9F*p) + fill, zF + 14.51F*p, xF +  7.5F*p, yF + 9F*p, zF + 14.51F*p); //South side
					RenderHelper.renderFlatQuad(buffer, sprite, xF + 4.25F*p, yF + 9F*p, zF +  1.49F*p, xF + 4.25F*p, yF + (9F*p) + fill, zF + 1.49F*p, xF + 4.75F*p, yF + (9F*p) + fill, zF +  1.49F*p, xF + 4.75F*p, yF + 9F*p, zF +  1.49F*p); //North side
				}
				
				if (meta == 1) {
					RenderHelper.renderFlatQuad(buffer, sprite, xF +  1.49F*p, yF + 9F*p, zF +    8F*p, xF +  1.49F*p, yF + (9F*p) + fill, zF +    8F*p, xF +  1.49F*p, yF + (9F*p) + fill, zF +  7.5F*p, xF +  1.49F*p, yF + 9F*p, zF +  7.5F*p); //West Side
					RenderHelper.renderFlatQuad(buffer, sprite, xF + 14.51F*p, yF + 9F*p, zF + 4.25F*p, xF + 14.51F*p, yF + (9F*p) + fill, zF + 4.25F*p, xF + 14.51F*p, yF + (9F*p) + fill, zF + 4.75F*p, xF + 14.51F*p, yF + 9F*p, zF + 4.75F*p); //East Side
				}
				
				if (meta == 2) {
					RenderHelper.renderFlatQuad(buffer, sprite, xF +     8F*p, yF + 9F*p, zF +  1.49F*p, xF +     8F*p, yF + (9F*p) + fill, zF +  1.49F*p, xF +   8.5F*p, yF + (9F*p) + fill, zF +  1.49F*p, xF +   8.5F*p, yF + 9F*p, zF +  1.49F*p); //North Side
					RenderHelper.renderFlatQuad(buffer, sprite, xF + 11.25F*p, yF + 9F*p, zF + 14.51F*p, xF + 11.25F*p, yF + (9F*p) + fill, zF + 14.51F*p, xF + 11.75F*p, yF + (9F*p) + fill, zF + 14.51F*p, xF + 11.75F*p, yF + 9F*p, zF + 14.51F*p); //South side
				}
				
				if (meta == 3) {
					RenderHelper.renderFlatQuad(buffer, sprite, xF + 14.51F*p, yF + 9F*p, zF +     8F*p, xF + 14.51F*p, yF + (9F*p) + fill, zF +     8F*p, xF + 14.51F*p, yF + (9F*p) + fill, zF +  8.5F*p, xF + 14.51F*p, yF + 9F*p, zF +  8.5F*p); //East side
					RenderHelper.renderFlatQuad(buffer, sprite, xF +  1.49F*p, yF + 9F*p, zF + 11.25F*p, xF +  1.49F*p, yF + (9F*p) + fill, zF + 11.25F*p, xF +  1.49F*p, yF + (9F*p) + fill, zF + 11.75F*p, xF + 1.49F*p, yF + 9F*p, zF + 11.75F*p); //West side
				}
			}
			if (tankId == 2) {
				if (meta == 0) { RenderHelper.renderTankFluid(buffer, x, y, z, 11.75F, 0.25F,    12F, 2.5F, 11.75F, 2.5F, fluid.amount, TarDistillerEntity.TANK_CAP, sprite); }
				if (meta == 1) { RenderHelper.renderTankFluid(buffer, x, y, z,   1.5F, 0.25F, 11.75F, 2.5F, 11.75F, 2.5F, fluid.amount, TarDistillerEntity.TANK_CAP, sprite); }
				if (meta == 2) { RenderHelper.renderTankFluid(buffer, x, y, z,  1.75F, 0.25F,   1.5F, 2.5F, 11.75F, 2.5F, fluid.amount, TarDistillerEntity.TANK_CAP, sprite); }
				if (meta == 3) { RenderHelper.renderTankFluid(buffer, x, y, z,    12F, 0.25F,  1.75F, 2.5F, 11.75F, 2.5F, fluid.amount, TarDistillerEntity.TANK_CAP, sprite); }
				
				if (isFilling) {
					if (meta == 0) { RenderHelper.renderTankFluid(buffer, x, y, z, 12.5F, 0.25F ,   13F, 0.5F, 12F, 0.5F, fluid.amount, TarDistillerEntity.TANK_CAP, sprite); }
					if (meta == 1) { RenderHelper.renderTankFluid(buffer, x, y, z,  2.5F, 0.25F, 12.75F, 0.5F, 12F, 0.5F, fluid.amount, TarDistillerEntity.TANK_CAP, sprite); }
					if (meta == 2) { RenderHelper.renderTankFluid(buffer, x, y, z, 2.75F, 0.25F,   2.5F, 0.5F, 12F, 0.5F, fluid.amount, TarDistillerEntity.TANK_CAP, sprite); }
					if (meta == 3) { RenderHelper.renderTankFluid(buffer, x, y, z,   13F, 0.25F,  2.75F, 0.5F, 12F, 0.5F, fluid.amount, TarDistillerEntity.TANK_CAP, sprite); }
				}
				
				fill = 0.00171875F * RenderHelper.getPercentage(fluid.amount, TarDistillerEntity.TANK_CAP);
				
				float xF = (float) x;
				float yF = (float) y;
				float zF = (float) z;
				
				if (meta == 0) {
					RenderHelper.renderFlatQuad(buffer, sprite, xF + 8.75F*p, yF + 9F*p, zF + 14.51F*p, xF + 8.75F*p, yF + (9F*p) + fill, zF + 14.51F*p, xF + 9.25F*p, yF + (9F*p) + fill, zF + 14.51F*p, xF + 9.25F*p, yF + 9F*p, zF + 14.51F*p); //South side
					RenderHelper.renderFlatQuad(buffer, sprite, xF +    3F*p, yF + 9F*p, zF +  1.49F*p, xF +    3F*p, yF + (9F*p) + fill, zF +  1.49F*p, xF +  3.5F*p, yF + (9F*p) + fill, zF +  1.49F*p, xF +  3.5F*p, yF + 9F*p, zF +  1.49F*p); //North side
				}
				if (meta == 1) {
					RenderHelper.renderFlatQuad(buffer, sprite, xF +  1.49F*p, yF + 9F*p, zF + 9.25F*p, xF +  1.49F*p, yF + (9F*p) + fill, zF + 9.25F*p, xF +  1.49F*p, yF + (9F*p) + fill, zF + 8.75F*p, xF +  1.49F*p, yF + 9F*p, zF + 8.75F*p); //West Side
					RenderHelper.renderFlatQuad(buffer, sprite, xF + 14.51F*p, yF + 9F*p, zF +    3F*p, xF + 14.51F*p, yF + (9F*p) + fill, zF +    3F*p, xF + 14.51F*p, yF + (9F*p) + fill, zF +  3.5F*p, xF + 14.51F*p, yF + 9F*p, zF +  3.5F*p); //East Side
				}
				
				if (meta == 2) {
					RenderHelper.renderFlatQuad(buffer, sprite, xF + 6.75F*p, yF + 9F*p, zF +  1.49F*p, xF + 6.75F*p, yF + (9F*p) + fill, zF +  1.49F*p, xF + 7.25F*p, yF + (9F*p) + fill, zF +  1.49F*p, xF + 7.25F*p, yF + 9F*p, zF +  1.49F*p); //North Side
					RenderHelper.renderFlatQuad(buffer, sprite, xF + 12.5F*p, yF + 9F*p, zF + 14.51F*p, xF + 12.5F*p, yF + (9F*p) + fill, zF + 14.51F*p, xF +   13F*p, yF + (9F*p) + fill, zF + 14.51F*p, xF +   13F*p, yF + 9F*p, zF + 14.51F*p); //South side
				}
				
				if (meta == 3) {
					RenderHelper.renderFlatQuad(buffer, sprite, xF + 14.51F*p, yF + 9F*p, zF + 6.75F*p, xF + 14.51F*p, yF + (9F*p) + fill, zF + 6.75F*p, xF + 14.51F*p, yF + (9F*p) + fill, zF + 7.25F*p, xF + 14.51F*p, yF + 9F*p, zF + 7.25F*p); //East side
					RenderHelper.renderFlatQuad(buffer, sprite, xF +  1.49F*p, yF + 9F*p, zF + 12.5F*p, xF +  1.49F*p, yF + (9F*p) + fill, zF + 12.5F*p, xF +  1.49F*p, yF + (9F*p) + fill, zF +   13F*p, xF +  1.49F*p, yF + 9F*p, zF +   13F*p); //West side
				}
			}
		}
	}
}
