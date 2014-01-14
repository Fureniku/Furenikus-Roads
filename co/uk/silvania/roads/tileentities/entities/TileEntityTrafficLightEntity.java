package co.uk.silvania.roads.tileentities.entities;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.PacketDispatcher;

public class TileEntityTrafficLightEntity extends TileEntity {

	public boolean isPowered = false;
	
    public void checkRedstonePower() {
        isPowered = worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
    }
    
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		isPowered = nbt.getBoolean("isPowered");
		//this.isPowered = nbt.getString("hasPower");
		System.out.println("Reading NBT from Tile Entity. Current value: " + nbt.getBoolean("isPowered") + ", with the boolean set as " + isPowered);
		this.sendRedstonePacketToClient();
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setBoolean("isPowered", isPowered);
		System.out.println("Stored NBT to Tile Entity. Current value: " + nbt.getBoolean("isPowered") + ", with the boolean set as " + isPowered);
		this.sendRedstonePacketToClient();
	}
	
    public Packet getDescriptionPacket() {
    	NBTTagCompound nbtTag = new NBTTagCompound();
    	this.writeToNBT(nbtTag);
    	return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, nbtTag);
    }

    public void onDataPacket(INetworkManager net, Packet132TileEntityData packet) {
    	readFromNBT(packet.data);
    }
    
    public void sendRedstonePacketToClient() {
    	if (!worldObj.isRemote) {
    		ByteArrayOutputStream bos = new ByteArrayOutputStream(1);
    		DataOutputStream dos = new DataOutputStream(bos);
    		try {
    			dos.writeBoolean(isPowered);
    			System.out.println("Despatching packet with boolean set to " + isPowered);
    		} catch (Exception e) {
    			System.out.println("[FlenixRoads] Failed to send Traffic Light packet");
    		}
    		
    		Packet250CustomPayload packet = new Packet250CustomPayload();
    		packet.channel = "FRRedstone";
    		packet.data = bos.toByteArray();
    		packet.length = bos.size();
    		PacketDispatcher.sendPacketToAllPlayers(packet);
    	}
    }

}
