package com.silvaniastudios.roads.blocks.tileentities.compactor;

import com.silvaniastudios.roads.blocks.tileentities.SlotFuel;
import com.silvaniastudios.roads.blocks.tileentities.SlotOutput;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class CompactorContainer extends Container {
	
	public static final int FRAGMENTS = 0;
	public static final int ROADS = 1;
	public static final int FUEL = 2;
	
	private boolean isElectric = false;
	private int energy;
	private int tick;
	private int fuel;
	private int fuelCap;
	private int road_size;
	
	public CompactorEntity tileEntity;
	
	
	public CompactorContainer(InventoryPlayer invPlayer, CompactorEntity tileEntity, boolean isElectric) {
		this.tileEntity = tileEntity;
		
		IItemHandler itemHandler = this.tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		addSlotToContainer(new SlotFragment(itemHandler, FRAGMENTS, 8, 32));
		addSlotToContainer(new SlotOutput(itemHandler, ROADS, 130, 32));
		
		if (!isElectric) { addSlotToContainer(new SlotFuel(itemHandler, FUEL, 152, 32)); }
		
		this.isElectric = isElectric;
		addPlayerSlots(invPlayer);
	}

	private void addPlayerSlots(IInventory playerInventory) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                int x = 8 + j * 18;
                int y = i * 18 + 64;
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, x, y));
            }
        }

        for (int i = 0; i < 9; i++) {
            int x = 8 + i * 18;
            int y = 58 + 64;
            this.addSlotToContainer(new Slot(playerInventory, i, x, y));
        }
    }
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < this.listeners.size(); ++i) {
			IContainerListener icontainerlistener = this.listeners.get(i);
			if (this.isElectric) {
				CompactorElectricEntity cee = (CompactorElectricEntity) tileEntity;
	        	if (this.energy != cee.energy.getEnergyStored()) {
	        		icontainerlistener.sendWindowProperty(this, 0, cee.energy.getEnergyStored());
	        	}
	        	this.energy = cee.energy.getEnergyStored();
			}
        	if (this.tick != tileEntity.timerCount) {
        		icontainerlistener.sendWindowProperty(this, 1, tileEntity.timerCount);
        	}
        	if (this.fuel != tileEntity.fuel_remaining) {
        		icontainerlistener.sendWindowProperty(this, 2, tileEntity.fuel_remaining);
        	}
        	if (this.fuelCap != tileEntity.last_fuel_cap) {
        		icontainerlistener.sendWindowProperty(this, 3, tileEntity.last_fuel_cap);
        	}
        	if (this.road_size != tileEntity.road_size) {
        		icontainerlistener.sendWindowProperty(this, 4, tileEntity.road_size);
        	}
		}
		
		this.tick = tileEntity.timerCount;
		this.fuel = tileEntity.fuel_remaining;
		this.fuelCap = tileEntity.last_fuel_cap;
		this.road_size = tileEntity.road_size;
	}
	
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		if (this.isElectric && id == 0) {
			CompactorElectricEntity cee = (CompactorElectricEntity) tileEntity;
			cee.energy.setEnergy(data);
		}
		if (id == 1) {
			tileEntity.timerCount = data;
		}
		if (id == 2) {
			tileEntity.fuel_remaining = data;
		}
		if (id == 3) {
			tileEntity.last_fuel_cap = data;
		}
		if (id == 4) {
			tileEntity.road_size = data;
		}
    }
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return tileEntity.canInteractWith(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotId) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(slotId);
		
		if (slot != null && slot.getHasStack()) {
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			
			if (slotId < tileEntity.inventory.getSlots()) {
				if (!this.mergeItemStack(stack1, tileEntity.inventory.getSlots(), this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(stack1, 0, tileEntity.inventory.getSlots(), false)) {
				return ItemStack.EMPTY;
			}
			
			if (stack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}
		return stack;
	}
}
