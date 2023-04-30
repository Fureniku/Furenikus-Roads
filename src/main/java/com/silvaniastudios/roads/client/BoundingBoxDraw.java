package com.silvaniastudios.roads.client;

import java.util.ArrayList;

import com.silvaniastudios.roads.blocks.diagonal.RoadBlockDiagonal;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BoundingBoxDraw {

	@SubscribeEvent
	public static void drawBlockHighlight(DrawBlockHighlightEvent event) {
		BlockPos pos = event.getTarget().getBlockPos();
		EntityPlayer player = event.getPlayer();
		World world = player.world;

		if (event.getTarget().typeOfHit == RayTraceResult.Type.BLOCK) {
			IBlockState state = world.getBlockState(pos);
			if (state.getBlock() instanceof RoadBlockDiagonal) {
				RoadBlockDiagonal block = (RoadBlockDiagonal) state.getBlock();

				double d3 = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double) event.getPartialTicks();
				double d4 = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double) event.getPartialTicks();
				double d5 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double) event.getPartialTicks();

				AxisAlignedBB box = new AxisAlignedBB(0,0,0,1,1,1);
				drawBoundingBox(pos, block.getLeftVecs(world, state, pos),  block.getRightVecs(world, state, pos), 0, 0, 0, 0.4f, d3, d4, d5);
				
				box.grow(0.0020000000949949026D).offset(d3, d4, d5);
			}
		}
	}
	
	public static void drawBoundingBox(BlockPos pos, Vec3d[] vecsLeft, Vec3d[] vecsRight, float r, float g, float b, float a, double pX, double pY, double pZ) {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder buffer = tessellator.getBuffer();
		buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
		
		draw(vecsLeft, buffer, r, g, b, a, pos, pX, pY, pZ);
		draw(vecsRight, buffer, r, g, b, a, pos, pX, pY, pZ);
		
		tessellator.draw();
	}
	
	public static void draw(Vec3d[] vecs, BufferBuilder buffer, float r, float g, float b, float a, BlockPos pos, double pX, double pY, double pZ) {
		for (int i = 0; i < vecs.length; i++) {
			Vec3d vec = offsetVector(offsetVector(vecs[i], pos), -pX, -pY, -pZ);
			
			double x = vec.x;
			double y = vec.y;
			double z = vec.z;

			if (i == 0) {
				buffer.pos(x, y, z).color(r, g, b, 0.0f).endVertex();
			}
			
			buffer.pos(x, y, z).color(r, g, b, a).endVertex();
			
			if (i == vecs.length) {
				buffer.pos(x, y, z).color(r, g, b, 0.0f).endVertex();
			}
		}
	}

	static Vec3d offsetVector(Vec3d vec, BlockPos pos) {
		return offsetVector(vec, pos.getX(), pos.getY(), pos.getZ());
	}
	
	static Vec3d offsetVector(Vec3d vec, double x, double y, double z) {
		return new Vec3d(vec.x + x, vec.y + y, vec.z + z);
	}
}
