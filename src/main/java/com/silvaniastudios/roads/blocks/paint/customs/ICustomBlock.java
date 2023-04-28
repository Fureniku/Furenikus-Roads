package com.silvaniastudios.roads.blocks.paint.customs;

import com.silvaniastudios.roads.blocks.paint.properties.PaintGrid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface ICustomBlock {

    @SideOnly(Side.CLIENT)
    PaintGrid getGrid(int id);
}
