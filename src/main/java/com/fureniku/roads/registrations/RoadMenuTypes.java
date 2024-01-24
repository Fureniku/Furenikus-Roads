package com.fureniku.roads.registrations;

import com.fureniku.roads.blockentities.menus.CrusherMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;

public class RoadMenuTypes {

    public static final MenuType<CrusherMenu> CRUSHER_MENU = IForgeMenuType.create((windowId, inv, data) -> new CrusherMenu(windowId, inv, data));
}
