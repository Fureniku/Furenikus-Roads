package com.silvaniastudios.roads.blocks.tileentities.paintoven;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.tileentities.SlotFuel;
import com.silvaniastudios.roads.blocks.tileentities.paintfiller.SlotDye;
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

public class PaintOvenContainer extends Container {
	
	public static final int DYE = 0;
	public static final int FUEL = 1;
	
	private boolean isElectric = false;
	private int energy;
	private int water;
	private int paint;
	private int tick;
	private int fuel;
	private int fuelCap;
	
	public PaintOvenEntity tileEntity;
	
	
	public PaintOvenContainer(InventoryPlayer invPlayer, PaintOvenEntity tileEntity, boolean isElectric) {
		this.tileEntity = tileEntity;
		
		IItemHandler itemHandler = this.tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		addSlotToContainer(new SlotDye(itemHandler, DYE, 80, 25, "dyeAny"));
		if (!isElectric) { addSlotToContainer(new SlotFuel(itemHandler, FUEL, 174, 32)); }
		
		this.isElectric = isElectric;
		addPlayerSlots(invPlayer);
	}
	
	private void addPlayerSlots(IInventory playerInventory) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                int x = 8 + j * 18;
                int y = i * 18 + 74;
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, x, y));
            }
        }

        for (int i = 0; i < 9; i++) {
            int x = 8 + i * 18;
            int y = 58 + 74;
            this.addSlotToContainer(new Slot(playerInventory, i, x, y));
        }
    }
	
	@Override
	public void detectAndSendChanges() {
		PaintOvenElectricEntity poee = null;
		
		if (this.isElectric) {
			poee = (PaintOvenElectricEntity) tileEntity;
		}
		
		super.detectAndSendChanges();

		for (int i = 0; i < this.listeners.size(); ++i) {
			IContainerListener listener = this.listeners.get(i);
			if (poee != null) {
	        	if (this.energy != poee.energy.getEnergyStored()) {
	        		FurenikusRoads.PACKET_CHANNEL.sendTo(new ClientGuiUpdatePacket(0, poee.energy.getEnergyStored()), (EntityPlayerMP) listener); 
	        	}
			}
			
			if (this.paint != tileEntity.paint.getFluidAmount()) {
				FurenikusRoads.PACKET_CHANNEL.sendTo(new ClientGuiUpdatePacket(1, tileEntity.paint.getFluidAmount()), (EntityPlayerMP) listener); 
        	}
			if (this.water != tileEntity.water.getFluidAmount()) {
				FurenikusRoads.PACKET_CHANNEL.sendTo(new ClientGuiUpdatePacket(2, tileEntity.water.getFluidAmount()), (EntityPlayerMP) listener); 
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
		
		if (poee != null) { this.energy = poee.energy.getEnergyStored(); }
		this.paint = tileEntity.paint.getFluidAmount();
		this.water = tileEntity.water.getFluidAmount();
		this.tick = tileEntity.timerCount;
		this.fuel = tileEntity.fuel_remaining;
		this.fuelCap = tileEntity.last_fuel_cap;
	}
	
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		FurenikusRoads.debug(1, "Paint Oven syncing ID: " + id + ", data: " + data);
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

}
