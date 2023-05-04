package com.silvaniastudios.roads.client;

import java.util.ArrayList;

import com.silvaniastudios.roads.blocks.decorative.CurbBlock;
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

			double d3 = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double) event.getPartialTicks();
			double d4 = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double) event.getPartialTicks();
			double d5 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double) event.getPartialTicks();

			if (state.getBlock() instanceof RoadBlockDiagonal) {
				RoadBlockDiagonal block = (RoadBlockDiagonal) state.getBlock();

				AxisAlignedBB box = new AxisAlignedBB(0,0,0,1,1,1);
				drawHalfBox(pos, block.getLeftVecs(world, state, pos),  block.getRightVecs(world, state, pos), 0, 0, 0, 0.4f, d3, d4, d5);
				
				box.grow(0.0020000000949949026D).offset(d3, d4, d5);
			}
			if (state.getBlock() instanceof CurbBlock) {
				CurbBlock block = (CurbBlock) state.getBlock();

				drawNormalBox(pos, block.getVecs(world, state, pos), 0, 0, 0, 0.4f, d3, d4, d5);
			}
		}
	}

	public static void drawNormalBox(BlockPos pos, Vec3d[] vecs, float r, float g, float b, float a, double pX, double pY, double pZ) {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder buffer = tessellator.getBuffer();
		buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);

		draw(vecs, buffer, r, g, b, a, pos, pX, pY, pZ);

		tessellator.draw();
	}
	
	public static void drawHalfBox(BlockPos pos, Vec3d[] vecsLeft, Vec3d[] vecsRight, float r, float g, float b, float a, double pX, double pY, double pZ) {
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

	//idk why its being dumb and needing different offsets depending on the rotation but oh well
	public static Vec3d[] getRotatedVecs(Vec3d[] vecsIn, int rot, Vec3d offset) {
		Vec3d vecs[] = new Vec3d[vecsIn.length];

		for (int i = 0; i < vecsIn.length; i++) {
			Vec3d offsetVec = new Vec3d(vecsIn[i].x + 0.5, vecsIn[i].y + 0.5, vecsIn[i].z + 0.5).rotateYaw((float) Math.toRadians(rot));
			vecs[i] = new Vec3d(offsetVec.x - 0.5 + offset.x, offsetVec.y - 0.5, offsetVec.z - 0.5 + offset.z);
		}

		return vecs;
	}
}
