package com.fureniku.roads.blocks.entityblock;

import com.fureniku.metropolis.blockentity.MetroBlockEntity;
import com.fureniku.metropolis.blocks.decorative.MetroEntityBlockDecorative;
import com.fureniku.metropolis.datagen.TextureSet;
import com.fureniku.metropolis.menus.MetroMenu;
import com.fureniku.metropolis.utils.Debug;
import com.fureniku.roads.blockentities.CrusherBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

public abstract class CrusherEntityBlock extends MetroEntityBlockDecorative {

    public static RegistryObject<BlockEntityType<MetroBlockEntity>> ENTITY;
    public static RegistryObject<MetroMenu> MENU;

    public CrusherEntityBlock(Properties props, VoxelShape shape, String modelDir, String modelName, String tag, boolean dynamicShape, TextureSet... textures) {
        super(props, shape, modelDir, modelName, tag, dynamicShape, textures);
    }

    @Override
    @Nullable
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        Debug.Log("Overriding entity correctly");
        return new CrusherBlockEntity(pos, state);
    }

    @Override
    public MenuProvider getMenu(BlockState state, Level level, BlockPos pos) { //TODO
        return null;
    }

    @Override
    protected InteractionResult onRightClick(BlockState state, Level level, BlockPos pos, Player player) {
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            NetworkHooks.openScreen(serverPlayer, state.getMenuProvider(level, pos));
            return InteractionResult.CONSUME;
        }
        return InteractionResult.PASS;
    }
}
