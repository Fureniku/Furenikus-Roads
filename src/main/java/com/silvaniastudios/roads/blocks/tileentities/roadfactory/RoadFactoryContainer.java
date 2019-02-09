package com.silvaniastudios.roads.blocks.tileentities.roadfactory;

import com.silvaniastudios.roads.blocks.tileentities.SlotFluid;
import com.silvaniastudios.roads.blocks.tileentities.SlotFuel;
import com.silvaniastudios.roads.blocks.tileentities.SlotOutput;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
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
	
	public RoadFactoryContainer(InventoryPlayer invPlayer, RoadFactoryEntity tileEntity) {
		this.tileEntity = tileEntity;
		IItemHandler itemHandler = this.tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		addSlotToContainer(new SlotRoadIn(itemHandler, INPUT_1, 40, 20));
		addSlotToContainer(new SlotRoadIn(itemHandler, INPUT_2, 62, 20));
		addSlotToContainer(new SlotRoadIn(itemHandler, INPUT_3, 40, 42));
		addSlotToContainer(new SlotRoadIn(itemHandler, INPUT_4, 62, 42));
		addSlotToContainer(new SlotOutput(itemHandler, OUTPUT_1, 124, 20));
		addSlotToContainer(new SlotOutput(itemHandler, OUTPUT_2, 146, 20));
		addSlotToContainer(new SlotOutput(itemHandler, OUTPUT_3, 124, 42));
		addSlotToContainer(new SlotOutput(itemHandler, OUTPUT_4, 146, 42));
		addSlotToContainer(new SlotFuel(itemHandler, FUEL, 93, 92));
		addSlotToContainer(new SlotFluid(itemHandler, FLUID_IN, 34, 70));
		addSlotToContainer(new SlotFluid(itemHandler, FLUID_IN_BUCKET, 34, 92));
		
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
