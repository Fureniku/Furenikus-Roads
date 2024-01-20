package com.fureniku.roads.blockentities;

import com.fureniku.metropolis.blocks.decorative.MetroBlockDecorativeBase;
import com.fureniku.metropolis.blocks.decorative.helpers.HelperBase;
import com.fureniku.metropolis.blocks.decorative.helpers.OffsetHelper;
import com.fureniku.metropolis.utils.SimpleUtils;
import com.fureniku.roads.blocks.entityblock.CrusherEntityBlock;
import com.fureniku.roads.blocks.entityblock.FabricatorEntityBlock;

import java.util.ArrayList;
import java.util.Arrays;

public class BlockEntityFactoryManager {

    public static MetroBlockDecorativeBase.MetroBlockStateFactory getCrusherBlockFactory(HelperBase... helpersIn) {
        return (props, shape, modelDir, modelName, tag, dynamicShape, textures) -> new CrusherEntityBlock(props, shape, modelDir, modelName, tag, SimpleUtils.containsType(OffsetHelper.class, helpersIn), textures) {
            @Override
            public ArrayList<HelperBase> getHelpers() {
                return new ArrayList<>(Arrays.asList(helpersIn));
            }
        };
    }

    public static MetroBlockDecorativeBase.MetroBlockStateFactory getFabricatorBlockFactory(HelperBase... helpersIn) {
        return (props, shape, modelDir, modelName, tag, dynamicShape, textures) -> new FabricatorEntityBlock(props, shape, modelDir, modelName, tag, SimpleUtils.containsType(OffsetHelper.class, helpersIn), textures) {
            @Override
            public ArrayList<HelperBase> getHelpers() {
                return new ArrayList<>(Arrays.asList(helpersIn));
            }
        };
    }
}
