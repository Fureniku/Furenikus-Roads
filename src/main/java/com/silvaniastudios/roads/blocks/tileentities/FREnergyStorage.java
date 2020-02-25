package com.silvaniastudios.roads.blocks.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.EnergyStorage;

public class FREnergyStorage extends EnergyStorage implements INBTSerializable<NBTTagCompound> {

	public FREnergyStorage(int capacity, int maxReceive) {
		super(capacity, maxReceive);
	}
	
	public void setEnergy(int energy) {
		this.energy = energy;
	}

	@Override
	public NBTTagCompound serializeNBT() {
		final NBTTagCompound nbt = new NBTTagCompound();
		
		nbt.setInteger("energy", energy);
		return nbt;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		if (nbt.hasKey("energy")) {
			energy = nbt.getInteger("energy");
		}
	}
}
