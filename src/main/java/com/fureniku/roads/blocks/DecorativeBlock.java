package com.fureniku.roads.blocks;

import com.fureniku.metropolis.blocks.MetroBlockBase;
import net.minecraft.world.level.block.SoundType;

public class DecorativeBlock extends MetroBlockBase {

    public DecorativeBlock() {
        super(Properties.of()
                .strength(1.0f)
                .sound(SoundType.STONE));
    }
}
