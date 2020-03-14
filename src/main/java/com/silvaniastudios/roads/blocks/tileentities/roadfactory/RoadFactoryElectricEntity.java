package com.silvaniastudios.roads.blocks.tileentities.roadfactory;

import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.tileentities.FREnergyStorage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;

public class RoadFactoryElectricEntity extends RoadFactoryEntity implements ITickable, ICapabilityProvider {
	
	public boolean isProcessing = false;
	int consumedEnergy = 0;

	public RoadFactoryElectricEntity() {}
	
	public FREnergyStorage energy = new FREnergyStorage(RoadsConfig.machine.electricRoadFactoryEnergyStorage, RoadsConfig.machine.electricRoadFactoryEnergyTransferRate) {};
	
	public Container createContainer(EntityPlayer player) {
		return new RoadFactoryContainer(player.inventory, this, true);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityEnergy.ENERGY) {
			return true;
		}
		return super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityEnergy.ENERGY) {
			if (facing != null) {
				return CapabilityEnergy.ENERGY.cast(energy);
			}
		}
		return super.getCapability(capability, facing);
	}
	
	@Override
	public void update() {
		//Used for rendering. Only do it on client.
		renderUpdate();
		
		if (energy.getEnergyStored() > RoadsConfig.machine.electricRoadFactoryEnergyConsumption) {
			//energy per tick
			int ept = RoadsConfig.machine.electricRoadFactoryEnergyConsumption / RoadsConfig.machine.electricRoadFactoryTickRate;
			if (timerCount < RoadsConfig.machine.electricRoadFactoryTickRate) {
				if (shouldTick()) {
					isProcessing = true;
					timerCount++;
					energy.extractEnergy(ept, false);
					consumedEnergy += ept;
				} else {
					isProcessing = false;
					timerCount = 0;
				}
			} else {
				//Round up energy usage to config value. eg 1000 usage, at 30 ticks, would only use 990 energy. This uses the other 10.
				if (consumedEnergy < RoadsConfig.machine.electricRoadFactoryEnergyConsumption) {
					energy.extractEnergy(RoadsConfig.machine.electricRoadFactoryEnergyConsumption - consumedEnergy, false);
				}
				process();
				timerCount = 0;
			}
		}
		
		if (!world.isRemote) {
			if (lastTar != tarFluid.getFluidAmount()) {
				lastTar = tarFluid.getFluidAmount();
				sendUpdates();
			}
		}
	}
	
	@Override
	public void readNBT(NBTTagCompound nbt) {
		if (nbt.hasKey("items")) {
			inventory.deserializeNBT((NBTTagCompound) nbt.getTag("items"));
		}
		if (nbt.hasKey("energy")) {
			energy.deserializeNBT((NBTTagCompound) nbt.getTag("energy"));
		}
		
		tarFluid.readFromNBT(nbt);
	}
	
	@Override
	public NBTTagCompound writeNBT(NBTTagCompound nbt) {
		nbt.setTag("items", inventory.serializeNBT());
		nbt.setTag("energy", energy.serializeNBT());
		
		tarFluid.writeToNBT(nbt);
		return nbt;
	}
}
