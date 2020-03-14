package com.silvaniastudios.roads.blocks.tileentities.paintoven;

import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.tileentities.FREnergyStorage;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;

public class PaintOvenElectricEntity extends PaintOvenEntity implements ITickable, ICapabilityProvider {
	
	public boolean isProcessing = false;
	int consumedEnergy = 0;
	
	public PaintOvenElectricEntity() {}
	
	public FREnergyStorage energy = new FREnergyStorage(RoadsConfig.machine.electricPaintOvenEnergyStorage, RoadsConfig.machine.electricPaintOvenEnergyTransferRate) {};
	
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
		if (energy.getEnergyStored() > RoadsConfig.machine.electricPaintOvenEnergyConsumption) {
			//energy per tick
			int ept = RoadsConfig.machine.electricPaintOvenEnergyConsumption / RoadsConfig.machine.electricPaintOvenTickRate;
			if (timerCount < RoadsConfig.machine.electricPaintOvenTickRate) {
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
				if (consumedEnergy < RoadsConfig.machine.electricPaintOvenEnergyConsumption) {
					energy.extractEnergy(RoadsConfig.machine.electricPaintOvenEnergyConsumption - consumedEnergy, false);
				}
				process();
				timerCount = 0;
			}
		}
		
		if (!world.isRemote) {
			if (lastPaint != paint.getFluidAmount() || lastWater != water.getFluidAmount()) {
				lastPaint = paint.getFluidAmount();
				lastWater = water.getFluidAmount();
				
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
