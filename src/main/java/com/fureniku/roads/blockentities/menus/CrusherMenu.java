package com.fureniku.roads.blockentities.menus;

import com.fureniku.metropolis.menus.MetroMenu;
import com.fureniku.roads.registrations.RoadMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;

public class CrusherMenu extends MetroMenu {

    //client constructor
    public CrusherMenu(int containerId, Inventory inventory, FriendlyByteBuf extraData) {
        this(containerId, inventory, ContainerLevelAccess.NULL);
    }

    //server constructor
    public CrusherMenu(int containerId, Inventory inventory, ContainerLevelAccess levelAccess) {
        super(RoadMenuTypes.CRUSHER_MENU, containerId);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int p_38942_) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return false;
    }
}
