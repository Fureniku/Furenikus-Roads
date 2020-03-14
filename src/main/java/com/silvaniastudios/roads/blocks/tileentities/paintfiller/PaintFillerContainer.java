package com.silvaniastudios.roads.blocks.tileentities.paintfiller;

import com.silvaniastudios.roads.blocks.tileentities.SlotFuel;

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

public class PaintFillerContainer extends Container {
	
	private PaintFillerEntity tileEntity;
	
	private boolean isElectric = false;
	private int energy;
	private int paint_white;
	private int paint_yellow;
	private int paint_red;
	
	public static final int WHITE_DYE = 0;
	public static final int YELLOW_DYE = 2;
	public static final int RED_DYE = 3;
	public static final int GUN = 1;
	public static final int FUEL = 4;
	
	public PaintFillerContainer(InventoryPlayer invPlayer, PaintFillerEntity tileEntity, boolean isElectric) {
		this.tileEntity = tileEntity;
		
		IItemHandler itemHandler = this.tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		addSlotToContainer(new SlotDye(itemHandler, 0, 74, 36, "dyeWhite"));
		addSlotToContainer(new SlotPaintGun(itemHandler, 1, 21, 36));
		addSlotToContainer(new SlotDye(itemHandler, 2, 74, 64, "dyeYellow"));
		addSlotToContainer(new SlotDye(itemHandler, 3, 74, 92, "dyeRed"));
		if (!isElectric) { addSlotToContainer(new SlotFuel(itemHandler, 4, 174, 32)); }
		
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
		PaintFillerElectricEntity pfee = null;
		
		if (this.isElectric) {
			pfee = (PaintFillerElectricEntity) tileEntity;
		}
		super.detectAndSendChanges();

		for (int i = 0; i < this.listeners.size(); ++i) {
			IContainerListener icontainerlistener = this.listeners.get(i);
			if (this.isElectric) {
	        	if (this.energy != pfee.energy.getEnergyStored()) {
	        		icontainerlistener.sendWindowProperty(this, 0, pfee.energy.getEnergyStored());
	        	}
			}
			
			if (this.paint_white != tileEntity.white_paint.getFluidAmount()) {
        		icontainerlistener.sendWindowProperty(this, 1, tileEntity.white_paint.getFluidAmount());
        	}
        	if (this.paint_yellow != tileEntity.yellow_paint.getFluidAmount()) {
        		icontainerlistener.sendWindowProperty(this, 2, tileEntity.yellow_paint.getFluidAmount());
        	}
        	if (this.paint_red != tileEntity.red_paint.getFluidAmount()) {
        		icontainerlistener.sendWindowProperty(this, 3, tileEntity.red_paint.getFluidAmount());
        	}
		}
		if (this.isElectric) { this.energy = pfee.energy.getEnergyStored(); }
		this.paint_white = tileEntity.white_paint.getFluidAmount();
		this.paint_yellow = tileEntity.yellow_paint.getFluidAmount();
		this.paint_red = tileEntity.red_paint.getFluidAmount();
	}
	
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		if (id == 0 && this.isElectric) {
			PaintFillerElectricEntity pfee = (PaintFillerElectricEntity) tileEntity;
			pfee.energy.setEnergy(data);
		}
		
		if (id == 1 && tileEntity.white_paint.getFluid() != null) {
			tileEntity.white_paint.getFluid().amount = data;
		}
		if (id == 2 && tileEntity.yellow_paint.getFluid() != null) {
			tileEntity.yellow_paint.getFluid().amount = data;
		}
		if (id == 3 && tileEntity.red_paint.getFluid() != null) {
			tileEntity.red_paint.getFluid().amount = data;
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
