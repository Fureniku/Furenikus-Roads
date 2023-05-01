package com.silvaniastudios.roads.network;

import com.silvaniastudios.roads.items.PaintGun;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PaintGunUpdatePacket implements IMessage {
	
	public PaintGunUpdatePacket(){}
	
	private int selection;
	private String selectedColour;
	private int pageId;
	private boolean isLarge;
	
	public PaintGunUpdatePacket(int selection, String selectedColour, int pageId, boolean isLarge) {
		this.selection = selection;
		this.selectedColour = selectedColour;
		this.pageId = pageId;
		this.isLarge = isLarge;
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(selection);
		ByteBufUtils.writeUTF8String(buf, selectedColour);
		buf.writeInt(pageId);
		buf.writeBoolean(isLarge);
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		selection = buf.readInt();
		selectedColour = ByteBufUtils.readUTF8String(buf);
		pageId = buf.readInt();
		isLarge = buf.readBoolean();
	}
	
	public static class Handler implements IMessageHandler<PaintGunUpdatePacket, IMessage> {

		@Override
		public IMessage onMessage(PaintGunUpdatePacket message, MessageContext ctx) {
			EntityPlayerMP player = ctx.getServerHandler().player;
			
			int selection = message.selection;
			String selectedColour = message.selectedColour;
			int pageId = message.pageId;
			boolean isLarge = message.isLarge;
			
			player.getServerWorld().addScheduledTask(() -> {
				ItemStack item = player.getHeldItem(EnumHand.MAIN_HAND);
				if (item.getItem() instanceof PaintGun) {
					NBTTagCompound nbt;
					
					if (item.hasTagCompound()) {
						nbt = item.getTagCompound();
					} else {
						nbt = new NBTTagCompound();
					}
					
					nbt.setInteger("selectedId", selection);
					nbt.setString("colour", selectedColour);
					nbt.setInteger("pageId", pageId);
					nbt.setBoolean("isLarge", isLarge);
					
					item.setTagCompound(nbt);
				}
			});
			return null;
		}
	}
}
