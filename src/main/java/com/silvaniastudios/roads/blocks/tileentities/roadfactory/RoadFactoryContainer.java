package com.silvaniastudios.roads.blocks.tileentities.roadfactory;

import com.silvaniastudios.roads.blocks.tileentities.SlotFluid;
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

public class RoadFactoryContainer extends Container {
	
	private RoadFactoryEntity tileEntity;
	
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
	
	private boolean isElectric = false;
	private int energy;
	
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
		if (this.isElectric) {
			RoadFactoryElectricEntity rfee = (RoadFactoryElectricEntity) tileEntity;
			super.detectAndSendChanges();
	
			for (int i = 0; i < this.listeners.size(); ++i) {
				IContainerListener icontainerlistener = this.listeners.get(i);
	        	if (this.energy != rfee.energy.getEnergyStored()) {
	        		icontainerlistener.sendWindowProperty(this, 0, rfee.energy.getEnergyStored());
	        	}
			}
			this.energy = rfee.energy.getEnergyStored();
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		if (this.isElectric) {
			RoadFactoryElectricEntity rfee = (RoadFactoryElectricEntity) tileEntity;
			rfee.energy.setEnergy(data);
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
