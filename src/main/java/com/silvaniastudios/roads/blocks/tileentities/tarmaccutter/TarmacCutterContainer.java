package com.silvaniastudios.roads.blocks.tileentities.tarmaccutter;


import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.tileentities.SlotFuel;
import com.silvaniastudios.roads.blocks.tileentities.SlotOutput;
import com.silvaniastudios.roads.network.ClientGuiUpdatePacket;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
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

public class TarmacCutterContainer extends Container {
	
	public TarmacCutterEntity tileEntity;
	
	public static final int INPUT = 0;
	public static final int BLADE = 1;
	public static final int OUTPUT_1 = 2;
	public static final int OUTPUT_2 = 3;
	public static final int FUEL = 4;
	
	private boolean isElectric = false;
	private int energy;
	private int tick;
	private int fuel;
	private int fuelCap;
	
	public TarmacCutterContainer(InventoryPlayer invPlayer, TarmacCutterEntity tileEntity, boolean isElectric) {
		this.tileEntity = tileEntity;
		
		IItemHandler itemHandler = this.tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		addSlotToContainer(new SlotTarmacCutter(itemHandler, INPUT, 8, 32));
		addSlotToContainer(new SlotBlade(itemHandler,  BLADE,  53, 32));
		addSlotToContainer(new SlotOutput(itemHandler, OUTPUT_1,  98, 32));
		addSlotToContainer(new SlotOutput(itemHandler, OUTPUT_2, 116, 32));
		if (!isElectric) { addSlotToContainer(new SlotFuel(itemHandler,   FUEL, 152, 32)); }
		
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
		TarmacCutterElectricEntity tcee = null;
		if (this.isElectric) {
			tcee = (TarmacCutterElectricEntity) tileEntity;
		}
		super.detectAndSendChanges();

		for (int i = 0; i < this.listeners.size(); ++i) {
			IContainerListener listener = this.listeners.get(i);
			if (listener instanceof EntityPlayer) {
				if (tcee != null) { 
		        	if (this.energy != tcee.energy.getEnergyStored()) {
		        		FurenikusRoads.PACKET_CHANNEL.sendTo(new ClientGuiUpdatePacket(0, tcee.energy.getEnergyStored()), (EntityPlayerMP) listener); 
		        	}
				}
	        	if (this.tick != tileEntity.timerCount) {
	        		listener.sendWindowProperty(this, 10, tileEntity.timerCount);
	        	}
	        	if (this.fuel != tileEntity.fuel_remaining) {
	        		listener.sendWindowProperty(this, 11, tileEntity.fuel_remaining);
	        	}
	        	if (this.fuelCap != tileEntity.last_fuel_cap) {
	        		listener.sendWindowProperty(this, 12, tileEntity.last_fuel_cap);
	        	}
			}
		}
		if (tcee != null) { this.energy = tcee.energy.getEnergyStored(); }
		this.tick = tileEntity.timerCount;
		this.fuel = tileEntity.fuel_remaining;
		this.fuelCap = tileEntity.last_fuel_cap;
	}
	
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		FurenikusRoads.debug(1, "Tarmac Cutter syncing ID: " + id + ", data: " + data);
		if (id == 10) {
			tileEntity.timerCount = data;
		}
		if (id == 11) {
			tileEntity.fuel_remaining = data;
		}
		if (id == 12) {
			tileEntity.last_fuel_cap = data;
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
	
	@Override
	public void onContainerClosed(EntityPlayer playerIn) {
		tileEntity.sendUpdates();
		super.onContainerClosed(playerIn);
    }
}
