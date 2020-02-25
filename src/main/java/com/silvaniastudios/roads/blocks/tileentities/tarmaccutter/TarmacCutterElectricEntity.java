package com.silvaniastudios.roads.blocks.tileentities.tarmaccutter;

import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.RoadBlock;
import com.silvaniastudios.roads.blocks.tileentities.FREnergyStorage;
import com.silvaniastudios.roads.items.TarmacCutterBlade;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;

public class TarmacCutterElectricEntity extends TarmacCutterEntity implements ITickable, ICapabilityProvider {
	
	public boolean isProcessing = false;
	int consumedEnergy = 0;
	
	public TarmacCutterElectricEntity() {}
	
	public FREnergyStorage energy = new FREnergyStorage(RoadsConfig.machine.electricTarmacCutterEnergyStorage, RoadsConfig.machine.electricTarmacCutterEnergyTransferRate) {};

	public Container createContainer(EntityPlayer player) {
		return new TarmacCutterContainer(player.inventory, this, true);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityEnergy.ENERGY) {
			return getCapability(capability, facing) != null;
		}
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
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
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (facing != null) {
				return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(interactable_inv);
			} else {
				return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory);
			}
		}
		
		return super.getCapability(capability, facing);
	}
	
	@Override
	public void update() {
		if (energy.getEnergyStored() > RoadsConfig.machine.electricTarmacCutterEnergyConsumption) {
			//energy per tick
			int ept = RoadsConfig.machine.electricTarmacCutterEnergyConsumption / RoadsConfig.machine.electricTarmacCutterTickRate;
			if (timerCount < RoadsConfig.machine.electricTarmacCutterTickRate) {
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
				if (consumedEnergy < RoadsConfig.machine.electricTarmacCutterEnergyConsumption) {
					energy.extractEnergy(RoadsConfig.machine.electricTarmacCutterEnergyConsumption - consumedEnergy, false);
				}
				consumedEnergy = 0;
				
				ItemStack in = inventory.getStackInSlot(TarmacCutterContainer.INPUT);
				ItemStack blade = inventory.getStackInSlot(TarmacCutterContainer.BLADE);
				ItemStack blockOut = inventory.getStackInSlot(TarmacCutterContainer.OUTPUT_1);
				ItemStack fragOut = inventory.getStackInSlot(TarmacCutterContainer.OUTPUT_2); //If you didn't read this var name in an angry call of duty man voice... you're lying you did.
				
				if (blade.getItem() instanceof TarmacCutterBlade) {
					if (blade.getItemDamage() < blade.getMaxDamage()) {
						int cutSize = getCutSize(blade);
						
						if (in.getItemDamage() >= cutSize && in.getItem() instanceof ItemBlock) {
							ItemBlock ib = (ItemBlock) in.getItem();
							
							if (ib.getBlock() instanceof RoadBlock) {
								RoadBlock block = (RoadBlock) ib.getBlock();
								if (blockOut.isEmpty() || (blockOut.getItem() == in.getItem() && blockOut.getItemDamage() == in.getItemDamage() - cutSize)) {
									if (fragOut.isEmpty() || (fragOut.getItem() == block.getFragmentItem(block) && fragOut.getCount() <= fragOut.getMaxStackSize() - cutSize)) {
										in.setCount(in.getCount() - 1);
										
										if (blockOut.isEmpty()) {
											inventory.setStackInSlot(2, new ItemStack(in.getItem(), 1, in.getItemDamage()-cutSize));
										} else {
											blockOut.setCount(blockOut.getCount() + 1);
										}
										
										if (fragOut.isEmpty()) { 
											ItemStack fragment = new ItemStack(block.getFragmentItem(block), cutSize);
											inventory.setStackInSlot(3, fragment);
										} else {
											fragOut.setCount(fragOut.getCount() + cutSize);
										}
										
										blade.setItemDamage(blade.getItemDamage() + 1);
									}
								}
							}
						}
					} else {
						inventory.setStackInSlot(TarmacCutterContainer.BLADE, ItemStack.EMPTY);
					}
				}
				
				if (!world.isRemote) {
					sendUpdates();
				}
				timerCount = 0;
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
	}
	
	@Override
	public NBTTagCompound writeNBT(NBTTagCompound nbt) {
		nbt.setTag("items", inventory.serializeNBT());
		nbt.setTag("energy", energy.serializeNBT());
		return nbt;
	}
}
