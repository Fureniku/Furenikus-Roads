package co.uk.silvania.roads;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import co.uk.silvania.roads.entity.EntityBasicCar;
import co.uk.silvania.roads.tileentities.renderers.TileEntityTrafficLightRenderer;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;


public class PacketHandler implements IPacketHandler {
	
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		System.out.println("Packet recieved!");
		
		if (packet.channel.equals("FRRedstone")) {
			DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
			System.out.println("Redstone-related packet");
			boolean tlightPowered = false;
			try { 
				tlightPowered = dis.readBoolean();
				System.out.println("Current state: " + tlightPowered + ", set to " + dis.readBoolean() + " by packet.");
			} catch (IOException e) {
				System.out.println("[FlenixRoads] Packet is somehow damanged...");
			}
			finally {}
			System.out.println("Tell the traffic light render to update now. State: " + tlightPowered);
			this.handleTrafficLightUpdate(dis);
			
		} else {
			ByteArrayDataInput reader = ByteStreams.newDataInput(packet.data);
			
			int entityId = reader.readInt();
			
			EntityPlayer entityPlayer = (EntityPlayer)player;
			Entity entity = entityPlayer.worldObj.getEntityByID(entityId);
			if (entity != null && entity instanceof EntityBasicCar && entity.riddenByEntity == entityPlayer) {
				((EntityBasicCar)entity).doDrop();
			}
		}
	}
	
	private void handleTrafficLightUpdate(DataInputStream dis) {
		try {
			TileEntityTrafficLightRenderer.powered = dis.readBoolean();
		} catch (IOException e) {
			System.out.println("[FlenixRoads] Traffic Light render update is broken!");
			return;
		}
		finally {}
	}

	public static void sendCarMovePacket(EntityBasicCar car) {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream dataStream = new DataOutputStream(byteStream);
		
		try {
			dataStream.writeInt(car.entityId);
			
			PacketDispatcher.sendPacketToServer(PacketDispatcher.getPacket("FRoadsPackets", byteStream.toByteArray()));
		} catch (IOException ex) {
			System.err.append("Car packet no like!");
		}
	}

}
