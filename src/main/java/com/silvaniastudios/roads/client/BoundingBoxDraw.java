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
				ArrayList<AxisAlignedBB> boxes = block.getBoxList(pos, world, state);

				double d3 = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double) event.getPartialTicks();
				double d4 = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double) event.getPartialTicks();
				double d5 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double) event.getPartialTicks();

				/*for (int i = 0; i < boxes.size(); i++) {
					event.getContext();

					drawSelectionBoundingBox(boxes.get(i).offset(pos).offset(-d3, -d4, -d5), 0, 0, 0, 0.4f);
				}*/

				AxisAlignedBB box = new AxisAlignedBB(0,0,0,1,1,1);

				//drawSelectionBoundingBox(box.offset(pos).offset(-d3, -d4, -d5), 0, 0, 0, 0.4f);
				
				drawBoundingBox(pos, block.getLeftVecs(world, state, pos),  block.getRightVecs(world, state, pos), 0, 0, 0, 0.4f, d3, d4, d5);
				
				
				box.grow(0.0020000000949949026D).offset(d3, d4, d5);
			}
		}
	}

	public static void drawSelectionBoundingBox(AxisAlignedBB box, float red, float green, float blue, float alpha) {
		draw(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, red, green, blue, alpha);
	}

	public static void draw(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha) {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
		drawBoundingBox(bufferbuilder, minX, minY, minZ, maxX, maxY, maxZ, red, green, blue, alpha);
		tessellator.draw();
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
	
	public static void drawBoundingBox(BufferBuilder buffer, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha) {
		
		buffer.pos(minX, minY, minZ).color(red, green, blue, 0.0F).endVertex();  //1
		buffer.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex(); //2
		buffer.pos(maxX, minY, minZ).color(red, green, blue, alpha).endVertex(); //3
		buffer.pos(minX, minY, maxZ).color(red, green, blue, alpha).endVertex(); //4
		
		
		
		
		
		
		
		
		
		buffer.pos(minX, minY, minZ).color(red, green, blue, 0.0F).endVertex();  //1
		buffer.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex(); //2
		buffer.pos(maxX, minY, minZ).color(red, green, blue, alpha).endVertex(); //3
		buffer.pos(minX, minY, maxZ).color(red, green, blue, alpha).endVertex(); //4
		buffer.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex(); //5

		buffer.pos(minX, maxY, minZ).color(red, green, blue, alpha).endVertex(); //6
		buffer.pos(maxX, maxY, minZ).color(red, green, blue, alpha).endVertex(); //7
		buffer.pos(minX, maxY, maxZ).color(red, green, blue, alpha).endVertex(); //8
		buffer.pos(minX, maxY, minZ).color(red, green, blue, alpha).endVertex(); //9
		buffer.pos(minX, maxY, maxZ).color(red, green, blue, alpha).endVertex(); //10
		buffer.pos(minX, minY, maxZ).color(red, green, blue, alpha).endVertex(); //11
		
		buffer.pos(maxX, minY, minZ).color(red, green, blue, alpha).endVertex(); //12
		buffer.pos(maxX, maxY, minZ).color(red, green, blue, alpha).endVertex(); //13
		buffer.pos(minX, maxY, minZ).color(red, green, blue, alpha).endVertex(); //14
		buffer.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex(); //15
		buffer.pos(minX, minY, minZ).color(red, green, blue, 0.0F).endVertex();  //16
	}

	void bu(BufferBuilder buffer, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha) {
		buffer.pos(minX, minY, minZ).color(red, green, blue, 0.0F).endVertex(); //1
		buffer.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex(); //2

		buffer.pos(maxX, minY, minZ).color(red, green, blue, alpha).endVertex(); //3
		buffer.pos(maxX, minY, maxZ).color(red, green, blue, alpha).endVertex(); //4
		buffer.pos(minX, minY, maxZ).color(red, green, blue, alpha).endVertex(); //5
		buffer.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex(); //6

		buffer.pos(minX, maxY, minZ).color(red, green, blue, alpha).endVertex(); //7
		buffer.pos(maxX, maxY, minZ).color(red, green, blue, alpha).endVertex(); //8
		buffer.pos(maxX, maxY, maxZ).color(red, green, blue, alpha).endVertex(); //9
		buffer.pos(minX, maxY, maxZ).color(red, green, blue, alpha).endVertex(); //10

		buffer.pos(minX, maxY, minZ).color(red, green, blue, alpha).endVertex(); //11
		buffer.pos(minX, maxY, maxZ).color(red, green, blue, 0.0F).endVertex();  //12
		buffer.pos(minX, minY, maxZ).color(red, green, blue, alpha).endVertex(); //13
		buffer.pos(maxX, maxY, maxZ).color(red, green, blue, 0.0F).endVertex();  //14

		buffer.pos(maxX, minY, maxZ).color(red, green, blue, alpha).endVertex(); //15
		buffer.pos(maxX, maxY, minZ).color(red, green, blue, 0.0F).endVertex();  //16
		buffer.pos(maxX, minY, minZ).color(red, green, blue, alpha).endVertex(); //17
		buffer.pos(maxX, minY, minZ).color(red, green, blue, 0.0F).endVertex();  //18
	}
	
	static Vec3d offsetVector(Vec3d vec, BlockPos pos) {
		return offsetVector(vec, pos.getX(), pos.getY(), pos.getZ());
	}
	
	static Vec3d offsetVector(Vec3d vec, double x, double y, double z) {
		return new Vec3d(vec.x + x, vec.y + y, vec.z + z);
	}

}
