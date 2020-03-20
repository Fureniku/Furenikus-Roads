package com.silvaniastudios.roads.blocks.tileentities.paintfiller;

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

public class PaintFillerElectricEntity extends PaintFillerEntity implements ITickable, ICapabilityProvider {
	
	public boolean isProcessing = false;
	int consumedEnergy = 0;
	
	public PaintFillerElectricEntity() {}
	
	public FREnergyStorage energy = new FREnergyStorage(RoadsConfig.machine.electricPaintFillerEnergyStorage, RoadsConfig.machine.electricPaintFillerEnergyTransferRate) {};
	
	public Container createContainer(EntityPlayer player) {
		return new PaintFillerContainer(player.inventory, this, true);
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
		if (energy.getEnergyStored() > RoadsConfig.machine.electricPaintFillerEnergyConsumption) {
			//energy per tick
			int ept = RoadsConfig.machine.electricPaintFillerEnergyConsumption / RoadsConfig.machine.electricFillerTickRate;
			if (timerCount < RoadsConfig.machine.electricFillerTickRate) {
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
				if (consumedEnergy < RoadsConfig.machine.electricPaintFillerEnergyConsumption) {
					energy.extractEnergy(RoadsConfig.machine.electricPaintFillerEnergyConsumption - consumedEnergy, false);
				}
				process();
				timerCount = 0;
			}
		}
		
		if (!world.isRemote) {
			if (lastWhite != white_paint.getFluidAmount() || lastYellow != yellow_paint.getFluidAmount() || lastRed != red_paint.getFluidAmount()) {
				lastWhite = white_paint.getFluidAmount();
				lastYellow = yellow_paint.getFluidAmount();
				lastRed = red_paint.getFluidAmount();
				
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
