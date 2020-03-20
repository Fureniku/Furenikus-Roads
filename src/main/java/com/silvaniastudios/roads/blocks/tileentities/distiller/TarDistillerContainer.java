package com.silvaniastudios.roads.blocks.tileentities.distiller;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.tileentities.SlotBucket;
import com.silvaniastudios.roads.blocks.tileentities.SlotFluid;
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
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class TarDistillerContainer extends Container {
	
	public TarDistillerEntity tileEntity;
	
	public static final int INPUT = 0;
	public static final int OUTPUT_1 = 1;
	public static final int OUTPUT_2 = 2;
	public static final int FUEL = 3;
	public static final int FLUID_IN = 4;
	public static final int FLUID_OUT_1 = 5;
	public static final int FLUID_OUT_2 = 6;
	public static final int FLUID_IN_BUCKET = 7;
	public static final int FLUID_OUT_1_BUCKET = 8;
	public static final int FLUID_OUT_2_BUCKET = 9;
	
	private boolean isElectric = false;
	private int energy;
	private int fluid_in;
	private int fluid_out_1;
	private int fluid_out_2;
	private int tick;
	private int fuel;
	private int fuelCap;
	
	public TarDistillerContainer(InventoryPlayer invPlayer, TarDistillerEntity tileEntity, boolean isElectric) {
		this.tileEntity = tileEntity;
		
		IItemHandler itemHandler = this.tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		addSlotToContainer(new SlotDistillerInput(itemHandler, INPUT, 34, 30, tileEntity));
		addSlotToContainer(new SlotOutput(itemHandler, OUTPUT_1, 100, 8));
		addSlotToContainer(new SlotOutput(itemHandler, OUTPUT_2, 100, 30));
		if (!isElectric) { addSlotToContainer(new SlotDistillerFuel(itemHandler, FUEL, 174, 30)); }
		
		addSlotToContainer(new SlotFluid(itemHandler, FLUID_IN, 34, 70));
		addSlotToContainer(new SlotFluid(itemHandler, FLUID_OUT_1, 100, 92));
		addSlotToContainer(new SlotFluid(itemHandler, FLUID_OUT_2, 174, 92));
		
		addSlotToContainer(new SlotBucket(itemHandler, FLUID_IN_BUCKET, 34, 92));
		addSlotToContainer(new SlotBucket(itemHandler, FLUID_OUT_1_BUCKET, 100, 70));
		addSlotToContainer(new SlotBucket(itemHandler, FLUID_OUT_2_BUCKET, 174, 70));
		
		this.isElectric = isElectric;
		
		addPlayerSlots(invPlayer);
	}
	
	private void addPlayerSlots(IInventory playerInventory) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                int x = 8 + j * 18;
                int y = i * 18 + 126;
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, x, y));
            }
        }

        for (int i = 0; i < 9; i++) {
            int x = 8 + i * 18;
            int y = 58 + 126;
            this.addSlotToContainer(new Slot(playerInventory, i, x, y));
        }
    }
	
	@Override
	public void detectAndSendChanges() {
		TarDistillerElectricEntity tdee = null;
		if (this.isElectric) {
			tdee = (TarDistillerElectricEntity) tileEntity;
		}
		super.detectAndSendChanges();

		for (int i = 0; i < this.listeners.size(); ++i) {
			IContainerListener listener = this.listeners.get(i);
			if (listener instanceof EntityPlayer) {
				if (tdee != null) {
					if (this.energy != tdee.energy.getEnergyStored()) {
						FurenikusRoads.PACKET_CHANNEL.sendTo(new ClientGuiUpdatePacket(0, tdee.energy.getEnergyStored()), (EntityPlayerMP) listener); 
		        	}
				}
	        	if (this.fluid_in != tileEntity.fluidInput.getFluidAmount()) {
	        		FurenikusRoads.PACKET_CHANNEL.sendTo(new ClientGuiUpdatePacket(1, tileEntity.fluidInput.getFluidAmount()), (EntityPlayerMP) listener); 
	        	}
	        	if (this.fluid_out_1 != tileEntity.fluidOutput1.getFluidAmount()) {
	        		FurenikusRoads.PACKET_CHANNEL.sendTo(new ClientGuiUpdatePacket(2, tileEntity.fluidOutput1.getFluidAmount()), (EntityPlayerMP) listener); 
	        	}
	        	if (this.fluid_out_2 != tileEntity.fluidOutput2.getFluidAmount()) {
	        		FurenikusRoads.PACKET_CHANNEL.sendTo(new ClientGuiUpdatePacket(3, tileEntity.fluidOutput2.getFluidAmount()), (EntityPlayerMP) listener); 
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
		if (this.isElectric) { this.energy = tdee.energy.getEnergyStored(); }
		this.fluid_in = tileEntity.fluidInput.getFluidAmount();
		this.fluid_out_1 = tileEntity.fluidOutput1.getFluidAmount();
		this.fluid_out_2 = tileEntity.fluidOutput2.getFluidAmount();
		this.tick = tileEntity.timerCount;
		this.fuel = tileEntity.fuel_remaining;
		this.fuelCap = tileEntity.last_fuel_cap;
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
