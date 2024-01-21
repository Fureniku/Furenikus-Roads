package com.fureniku.roads.blockentities.menus;

import com.fureniku.metropolis.menus.MetroMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class CrusherMenu extends MetroMenu {

    //client constructor
    public CrusherMenu(@Nullable MenuType<?> menuType, int containerId) {
        super(menuType, containerId);
    }

    //server constructor
/*    public CrusherMenu(int containerId, Inventory playerInv, ContainerLevelAccess access) {
        super(MenuType.)
    }*/

    @Override
    public ItemStack quickMoveStack(Player player, int p_38942_) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return false;
    }
}
