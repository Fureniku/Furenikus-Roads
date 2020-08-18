package com.silvaniastudios.roads.blocks.tileentities.fabricator;

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
import net.minecraftforge.items.SlotItemHandler;

public class FabricatorContainer extends Container {
	
	public static final int IN_1 = 0;
	public static final int IN_2 = 1;
	public static final int IN_3 = 2;
	public static final int IN_4 = 3;
	public static final int IN_5 = 4;
	public static final int IN_6 = 5;
	public static final int OUTPUT = 6;
	public static final int FUEL = 7;
	
	private boolean isElectric = false;
	private int energy;
	private int tick;
	private int fuel;
	private int fuelCap;
	private int recipeId;
	
	public FabricatorEntity tileEntity;
	
	
	public FabricatorContainer(InventoryPlayer invPlayer, FabricatorEntity tileEntity, boolean isElectric) {
		this.tileEntity = tileEntity;
		
		IItemHandler itemHandler = this.tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		addSlotToContainer(new SlotItemHandler(itemHandler, IN_1,  8, 20));
		addSlotToContainer(new SlotItemHandler(itemHandler, IN_2, 26, 20));
		addSlotToContainer(new SlotItemHandler(itemHandler, IN_3, 44, 20));
		addSlotToContainer(new SlotItemHandler(itemHandler, IN_4,  8, 38));
		addSlotToContainer(new SlotItemHandler(itemHandler, IN_5, 26, 38));
		addSlotToContainer(new SlotItemHandler(itemHandler, IN_6, 44, 38));
		addSlotToContainer(new SlotOutput(itemHandler, OUTPUT, 130, 29));
		if (!isElectric) { addSlotToContainer(new SlotFuel(itemHandler, FUEL, 152, 29)); }
		
		this.isElectric = isElectric;
		addPlayerSlots(invPlayer);
	}

	private void addPlayerSlots(IInventory playerInventory) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                int x = 8 + j * 18;
                int y = i * 18 + 70;
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, x, y));
            }
        }

        for (int i = 0; i < 9; i++) {
            int x = 8 + i * 18;
            int y = 58 + 70;
            this.addSlotToContainer(new Slot(playerInventory, i, x, y));
        }
    }
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < this.listeners.size(); ++i) {
			IContainerListener listener = this.listeners.get(i);
			if (listener instanceof EntityPlayer) {
				if (this.isElectric) {
					FabricatorElectricEntity fee = (FabricatorElectricEntity) tileEntity;
		        	if (this.energy != fee.energy.getEnergyStored()) {
		        		FurenikusRoads.PACKET_CHANNEL.sendTo(new ClientGuiUpdatePacket(0, fee.energy.getEnergyStored()), (EntityPlayerMP) listener); 
		        	}
		        	this.energy = fee.energy.getEnergyStored();
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
	        	if (this.recipeId != tileEntity.recipeId) {
	        		listener.sendWindowProperty(this, 4, tileEntity.recipeId);
	        	}
			}
		}
		
		this.tick = tileEntity.timerCount;
		this.fuel = tileEntity.fuel_remaining;
		this.fuelCap = tileEntity.last_fuel_cap;
		this.recipeId = tileEntity.recipeId;
	}
	
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		FurenikusRoads.debug(1, "Fabricator syncing ID: " + id + ", data: " + data);
		if (id == 10) {
			tileEntity.timerCount = data;
		}
		if (id == 11) {
			tileEntity.fuel_remaining = data;
		}
		if (id == 12) {
			tileEntity.last_fuel_cap = data;
		}
		if (id == 4) {
			tileEntity.recipeId = data;
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
