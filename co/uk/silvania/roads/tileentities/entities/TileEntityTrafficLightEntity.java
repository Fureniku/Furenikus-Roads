package co.uk.silvania.roads.tileentities.entities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTrafficLightEntity extends TileEntity {

	public boolean isPowered = false;
	
    public void checkRedstonePower() {
        isPowered = worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
    }
    
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList nbttaglist = nbt.getTagList("hasPower");
		//this.isPowered = nbt.getString("hasPower");
		System.out.println("Reading NBT from Tile Entity. Current value: " + nbt.getString("hasPower"));
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		if (this.isPowered) {
			nbt.setString("hasPower", "powered");
		} else
		nbt.setString("hasPower", "not");
		System.out.println("Stored NBT to Tile Entity. Current value: " + nbt.getString("hasPower"));
	}
	
    public Packet getDescriptionPacket() {
    	NBTTagCompound nbtTag = new NBTTagCompound();
    	this.writeToNBT(nbtTag);
    	return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, nbtTag);
    }

    public void onDataPacket(INetworkManager net, Packet132TileEntityData packet) {
    	readFromNBT(packet.data);
    }

}
