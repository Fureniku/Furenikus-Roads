package com.fureniku.roads.blockentities;

import com.fureniku.metropolis.blockentity.MetroBlockEntity;
import com.fureniku.roads.blockentities.menus.CrusherMenu;
import com.fureniku.roads.blocks.entityblock.CrusherEntityBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class CrusherBlockEntity extends MetroBlockEntity implements MenuProvider {

    public CrusherBlockEntity(BlockPos pos, BlockState state) {
        super(CrusherEntityBlock.ENTITY.get(), pos, state);
    }

    @Override
    public Component getDisplayName() {
        //return Component.translatable("blah.blah.blah");
        return Component.literal("Crusher (localise me)");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player p_39956_) {
        return new CrusherMenu(id, inventory);
    }
}
