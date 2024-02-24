package com.fureniku.roads.blockentities.menus;

import com.fureniku.metropolis.menus.MetroMenu;
import com.fureniku.metropolis.utils.Debug;
import com.fureniku.roads.blocks.entityblock.CrusherEntityBlock;
import com.fureniku.roads.registrations.RoadMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class CrusherMenu extends MetroMenu {

    //client constructor
    public CrusherMenu(int containerId, Inventory playerInv) {
        this(containerId, playerInv, new ItemStackHandler(3));
    }

    //server constructor
    public CrusherMenu(int containerId, Inventory playerInv, IItemHandler dataInventory) {
        super(CrusherEntityBlock.MENU_TYPE.get(), containerId);
        this.addSlot(new SlotItemHandler(dataInventory,0, 10, 10));
    }

    public CrusherMenu(int containerId, Inventory inventory, FriendlyByteBuf friendlyByteBuf) {
        super(CrusherEntityBlock.MENU_TYPE.get(), containerId);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int p_38942_) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
