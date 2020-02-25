package com.silvaniastudios.roads.blocks.tileentities.crusher;

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
import net.minecraftforge.items.SlotItemHandler;

public class CrusherContainer extends Container {
	
	private CrusherEntity tileEntity;
	
	public static final int INPUT_1 = 0;
	public static final int OUTPUT_1 = 1;
	public static final int FUEL = 2;
	
	private boolean isElectric = false;
	private int energy;
	
	public CrusherContainer(InventoryPlayer invPlayer, CrusherEntity tileEntity, boolean isElectric) {
		this.tileEntity = tileEntity;
		
		IItemHandler itemHandler = this.tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		addSlotToContainer(new SlotItemHandler(itemHandler, INPUT_1, 8, 32));
		addSlotToContainer(new SlotOutput(itemHandler, OUTPUT_1,  98, 32));
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
		if (this.isElectric) {
			CrusherElectricEntity cee = (CrusherElectricEntity) tileEntity;
			super.detectAndSendChanges();
	
			for (int i = 0; i < this.listeners.size(); ++i) {
				IContainerListener icontainerlistener = this.listeners.get(i);
	        	if (this.energy != cee.energy.getEnergyStored()) {
	        		icontainerlistener.sendWindowProperty(this, 0, cee.energy.getEnergyStored());
	        	}
			}
			this.energy = cee.energy.getEnergyStored();
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		if (this.isElectric) {
			CrusherElectricEntity cee = (CrusherElectricEntity) tileEntity;
			cee.energy.setEnergy(data);
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
