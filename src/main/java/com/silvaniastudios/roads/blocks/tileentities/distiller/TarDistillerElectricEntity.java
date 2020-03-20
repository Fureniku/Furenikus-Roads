package com.silvaniastudios.roads.blocks.tileentities.distiller;

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

public class TarDistillerElectricEntity extends TarDistillerEntity implements ITickable, ICapabilityProvider {
	
	public boolean isProcessing = false;
	int consumedEnergy = 0;

	public TarDistillerElectricEntity() {}
	
	public FREnergyStorage energy = new FREnergyStorage(RoadsConfig.machine.electricTarDistillerEnergyStorage, RoadsConfig.machine.electricTarDistillerEnergyTransferRate) {};
	
	public Container createContainer(EntityPlayer player) {
		return new TarDistillerContainer(player.inventory, this, true);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityEnergy.ENERGY) {
			return getCapability(capability, facing) != null;
		}
		return super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityEnergy.ENERGY) {
			return CapabilityEnergy.ENERGY.cast(energy);
		}
		
		return super.getCapability(capability, facing);
	}
	
	@Override
	public void update() {
		if (energy.getEnergyStored() > 0) {
			if (transferTar()) {
				energy.extractEnergy(50, false);
			}
		}
		if (energy.getEnergyStored() > RoadsConfig.machine.electricTarDistillerEnergyConsumption) {
			//energy per tick
			int ept = RoadsConfig.machine.electricTarDistillerEnergyConsumption / RoadsConfig.machine.electricTarDistillerTickRate;
			if (timerCount < RoadsConfig.machine.electricTarDistillerTickRate) {
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
				if (consumedEnergy < RoadsConfig.machine.electricTarDistillerEnergyConsumption) {
					energy.extractEnergy(RoadsConfig.machine.electricTarDistillerEnergyConsumption - consumedEnergy, false);
				}
				process();
				timerCount = 0;
				
				if (!world.isRemote) {
					sendUpdates();
				}
			}	
		}
		if (!world.isRemote) {
			if (lastFluidInput != fluidInput.getFluidAmount() || lastFluidOutput1 != fluidOutput1.getFluidAmount() || lastFluidOutput2 != fluidOutput2.getFluidAmount()) {
				lastFluidInput = fluidInput.getFluidAmount();
				lastFluidOutput1 = fluidOutput1.getFluidAmount();
				lastFluidOutput2 = fluidOutput2.getFluidAmount();
				sendUpdates();
			}
		}
	}

	@Override
	public void readNBT(NBTTagCompound nbt) {
		if (nbt.hasKey("energy")) {
			energy.deserializeNBT((NBTTagCompound) nbt.getTag("energy"));
		}
		super.readNBT(nbt);
	}
	
	@Override
	public NBTTagCompound writeNBT(NBTTagCompound nbt) {
		nbt.setTag("energy", energy.serializeNBT());
		return super.writeNBT(nbt);
	}
}
