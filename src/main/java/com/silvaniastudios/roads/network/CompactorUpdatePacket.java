package com.silvaniastudios.roads.network;

import com.silvaniastudios.roads.blocks.tileentities.compactor.CompactorContainer;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CompactorUpdatePacket implements IMessage {
	
	public CompactorUpdatePacket(){}
	
	private int change_amt;
	
	public CompactorUpdatePacket(int change_amt) {
		this.change_amt = change_amt;
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(change_amt);
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		change_amt = buf.readInt();
	}
	
	public static class Handler implements IMessageHandler<CompactorUpdatePacket, IMessage> {

		@Override
		public IMessage onMessage(CompactorUpdatePacket message, MessageContext ctx) {
			EntityPlayerMP player = ctx.getServerHandler().player;

			int change_amt = message.change_amt;
			
			if (player.openContainer instanceof CompactorContainer) {
				CompactorContainer ctr = (CompactorContainer) player.openContainer;
				ctr.tileEntity.road_size += change_amt;
				if (ctr.tileEntity.road_size > 15) { ctr.tileEntity.road_size = 15; }
				if (ctr.tileEntity.road_size <  0) { ctr.tileEntity.road_size =  0; }
			}
			return null;
		}
	}
}
