package com.silvaniastudios.roads.blocks.tileentities.roadfactory;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.tileentities.SlotFluid;
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

public class RoadFactoryContainer extends Container {
	
	public RoadFactoryEntity tileEntity;
	
	public static final int INPUT_1 = 0;
	public static final int INPUT_2 = 1;
	public static final int INPUT_3 = 2;
	public static final int INPUT_4 = 3;
	public static final int OUTPUT_1 = 4;
	public static final int OUTPUT_2 = 5;
	public static final int OUTPUT_3 = 6;
	public static final int OUTPUT_4 = 7;
	public static final int FUEL = 8;
	public static final int FLUID_IN = 9;
	public static final int FLUID_IN_BUCKET = 10;
	public static final int MODIFIER = 11;
	
	private boolean isElectric = false;
	private int energy;
	private int tar;
	private int tick;
	private int fuel;
	private int fuelCap;
	
	public RoadFactoryContainer(InventoryPlayer invPlayer, RoadFactoryEntity tileEntity, boolean isElectric) {
		this.tileEntity = tileEntity;
		IItemHandler itemHandler = this.tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		addSlotToContainer(new SlotRoadIn(itemHandler, INPUT_1, 34, 20));
		addSlotToContainer(new SlotRoadIn(itemHandler, INPUT_2, 57, 20));
		addSlotToContainer(new SlotRoadIn(itemHandler, INPUT_3, 34, 43));
		addSlotToContainer(new SlotRoadIn(itemHandler, INPUT_4, 57, 43));
		addSlotToContainer(new SlotOutput(itemHandler, OUTPUT_1, 107, 20));
		addSlotToContainer(new SlotOutput(itemHandler, OUTPUT_2, 130, 20));
		addSlotToContainer(new SlotOutput(itemHandler, OUTPUT_3, 107, 43));
		addSlotToContainer(new SlotOutput(itemHandler, OUTPUT_4, 130, 43));
		if (!isElectric) { addSlotToContainer(new SlotFuel(itemHandler, FUEL, 152, 43)); }
		addSlotToContainer(new SlotFluid(itemHandler, FLUID_IN, 34, 70));
		addSlotToContainer(new SlotFluid(itemHandler, FLUID_IN_BUCKET, 34, 92));
		if (itemHandler.getSlots() >= 12) { addSlotToContainer(new SlotItemHandler(itemHandler, MODIFIER, 82, 43)); }
		
		this.isElectric = isElectric;
		addPlayerSlots(invPlayer);
	}
	
	private void addPlayerSlots(IInventory playerInventory) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                int x = 8 + j * 18;
                int y = i * 18 + 124;
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, x, y));
            }
        }

        for (int i = 0; i < 9; i++) {
            int x = 8 + i * 18;
            int y = 58 + 124;
            this.addSlotToContainer(new Slot(playerInventory, i, x, y));
        }
    }
	
	@Override
	public void detectAndSendChanges() {
		RoadFactoryElectricEntity rfee = null;
		if (this.isElectric) {
			rfee = (RoadFactoryElectricEntity) tileEntity;
		}
		super.detectAndSendChanges();

		for (int i = 0; i < this.listeners.size(); ++i) {
			IContainerListener listener = this.listeners.get(i);
			if (this.isElectric) {
	        	if (this.energy != rfee.energy.getEnergyStored()) {
	        		FurenikusRoads.PACKET_CHANNEL.sendTo(new ClientGuiUpdatePacket(0, rfee.energy.getEnergyStored()), (EntityPlayerMP) listener); 
	        	}
			}
        	if (this.tar != tileEntity.tarFluid.getFluidAmount()) {
        		FurenikusRoads.PACKET_CHANNEL.sendTo(new ClientGuiUpdatePacket(1, tileEntity.tarFluid.getFluidAmount()), (EntityPlayerMP) listener); 
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
		this.tar = tileEntity.tarFluid.getFluidAmount();
		this.tick = tileEntity.timerCount;
		this.fuel = tileEntity.fuel_remaining;
		this.fuelCap = tileEntity.last_fuel_cap;
		if (rfee != null) { this.energy = rfee.energy.getEnergyStored(); }
	}
	
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		FurenikusRoads.debug(1, "Road Factory syncing ID: " + id + ", data: " + data);
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
