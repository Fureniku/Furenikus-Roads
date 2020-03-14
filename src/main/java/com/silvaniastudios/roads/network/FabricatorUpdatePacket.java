package com.silvaniastudios.roads.network;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.tileentities.fabricator.FabricatorEntity;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class FabricatorUpdatePacket implements IMessage {
	
	public FabricatorUpdatePacket(){}
	
	private int recipeId;
	private int posX;
	private int posY;
	private int posZ;
	
	public FabricatorUpdatePacket(int recipeId, int xPos, int yPos, int zPos) {
		this.recipeId = recipeId;
		this.posX = xPos;
		this.posY = yPos;
		this.posZ = zPos;
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(recipeId);
		buf.writeInt(posX);
		buf.writeInt(posY);
		buf.writeInt(posZ);
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		recipeId = buf.readInt();
		posX = buf.readInt();
		posY = buf.readInt();
		posZ = buf.readInt();
	}
	
	public static class Handler implements IMessageHandler<FabricatorUpdatePacket, IMessage> {

		@Override
		public IMessage onMessage(FabricatorUpdatePacket message, MessageContext ctx) {
			EntityPlayerMP player = ctx.getServerHandler().player;
			World world = player.world;

			int recipeId = message.recipeId;
			int posX = message.posX;
			int posY = message.posY;
			int posZ = message.posZ;
			
			BlockPos pos = new BlockPos(posX, posY, posZ);
			
			if (world.getTileEntity(pos) != null && player.getPosition().distanceSq(posX, posY, posZ) < 100) {
				if (world.getTileEntity(pos) instanceof FabricatorEntity) {
					FabricatorEntity entity = (FabricatorEntity) world.getTileEntity(pos);
					entity.recipeId = recipeId;
					entity.sendUpdates();
					player.openGui(FurenikusRoads.instance, entity.hasCapability(CapabilityEnergy.ENERGY, null) ? 16 : 15, world, pos.getX(), pos.getY(), pos.getZ());
				}
			}
			return null;
		}
	}
}
