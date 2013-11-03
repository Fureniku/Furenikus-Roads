package co.uk.silvania.roads.tileentities.entities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTrafficLightEntity extends TileEntity {

	public boolean isPowered = false;
	
    public void checkRedstonePower() {
        isPowered = worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
    }
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setBoolean("isPowered", isPowered);
		System.out.println("Stored NBT to Tile Entity. Current value: " + isPowered);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.isPowered = nbt.getBoolean("isPowered");
		System.out.println("Reading NBT from Tile Entity. Current value: " + isPowered);
	}

}
