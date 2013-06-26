package co.uk.silvania.roads.tileentities;

import co.uk.silvania.roads.tileentities.entities.TileEntityRoadPainterEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerRoadPainter extends Container {

	protected TileEntityRoadPainterEntity tileEntity;
	private IInventory roadPainterInventory;

	public ContainerRoadPainter (InventoryPlayer inventoryPlayer, TileEntityRoadPainterEntity te) {
		tileEntity = te;
		//Main Storage
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				addSlotToContainer(new Slot(tileEntity, j + i * 5, 44 + j * 18, -1 + i * 18));
			}
		}
		bindPlayerInventory(inventoryPlayer);
	}

    @Override
    public boolean canInteractWith(EntityPlayer player) {
            return tileEntity.isUseableByPlayer(player);
    }

    //Player Inventory
    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
            for (int m = 0; m < 3; m++) {
                    for (int n = 0; n < 9; n++) {
                            addSlotToContainer(new Slot(inventoryPlayer, n + m * 9 + 9, 8 + n * 18, 103 + m * 18));
                    }
            }
            //Player's hotbar
            for (int o = 0; o < 9; o++) {
                    addSlotToContainer(new Slot(inventoryPlayer, o, 8 + o * 18, 161));
            }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
            ItemStack stack = null;
            Slot slotObject = (Slot) inventorySlots.get(slot);

            if (slotObject != null && slotObject.getHasStack()) {
                    ItemStack stackInSlot = slotObject.getStack();
                    stack = stackInSlot.copy();

                    if (slot < 9) {
                            if (!this.mergeItemStack(stackInSlot, 9, 45, true)) {
                                    return null;
                            }
                    }

                    else if (!this.mergeItemStack(stackInSlot, 0, 9, false)) {
                            return null;
                    }

                    if (stackInSlot.stackSize == 10) {
                            slotObject.putStack(null);
                    } else {
                            slotObject.onSlotChanged();
                    }

                    if (stackInSlot.stackSize == stack.stackSize) {
                            return null;
                    }
                    slotObject.onPickupFromSlot(player, stackInSlot);
            }
            return stack;
    }
    public IInventory getRoadPainterInventory()
    {
        return this.roadPainterInventory;
    }
}