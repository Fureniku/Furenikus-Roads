package com.fureniku.roads.registrations;

import com.fureniku.metropolis.RegistrationBase;
import com.fureniku.metropolis.RegistrationGroup;
import com.fureniku.metropolis.blockentity.MetroBlockEntity;
import com.fureniku.metropolis.blocks.decorative.MetroBlockDecorativeBase;
import com.fureniku.metropolis.blocks.decorative.builders.MetroBlockDecorativeBuilder;
import com.fureniku.metropolis.blocks.decorative.helpers.HelperBase;
import com.fureniku.metropolis.blocks.decorative.helpers.OffsetHelper;
import com.fureniku.metropolis.blocks.decorative.helpers.RotationHelper;
import com.fureniku.metropolis.utils.CreativeTabSet;
import com.fureniku.metropolis.utils.ShapeUtils;
import com.fureniku.metropolis.utils.SimpleUtils;
import com.fureniku.roads.FurenikusRoads;
import com.fureniku.roads.blockentities.BlockType;
import com.fureniku.roads.blockentities.CrusherBlockEntity;
import com.fureniku.roads.blockentities.FabricatorBlockEntity;
import com.fureniku.roads.blockentities.menus.CrusherMenu;
import com.fureniku.roads.blocks.entityblock.CrusherEntityBlock;
import com.fureniku.roads.blocks.entityblock.FabricatorEntityBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;

public class RegistrationMachines extends RegistrationGroup {

    private final String CRUSHER = "crusher";
    private final String FABRICATOR = "fabricator";
    private final String PAINT_FILLER = "paint_filler";
    private final String PAINT_OVEN = "paint_oven";
    private final String ROAD_FACTORY = "road_factory";
    private final String TAR_DISTILLER = "tar_distiller";
    private final String TARMAC_CUTTER = "tarmac_cutter";

    private CreativeTabSet _machineTab;
    private BlockBehaviour.Properties _props = BlockBehaviour.Properties.of().strength(2.0f).sound(SoundType.METAL);

    public RegistrationMachines(RegistrationBase registrationBase) {
        super(registrationBase);
    }

    @Override
    public void init(IEventBus iEventBus) {
        CrusherEntityBlock.ENTITY = registerEntity(CRUSHER, getHelpers(12, 14), BlockType.CRUSHER, CrusherBlockEntity::new);
        //CrusherEntityBlock.MENU = registration.registerMenuType(CRUSHER + "_menu", () -> new MenuType(CrusherMenu::new, FeatureFlags.DEFAULT_FLAGS));

        FabricatorEntityBlock.ENTITY = registerEntity(FABRICATOR, getHelpers(12, 9.5f), BlockType.FABRICATOR, FabricatorBlockEntity::new);

        _machineTab = new CreativeTabSet(registration.getCreativeTabDeferredRegister(),"tab_machines", getItem(CRUSHER));
    }

    //Most machines are very similar beyond their size
    private HelperBase[] getHelpers(float width, float height) {
        return new HelperBase[] {
                new RotationHelper(ShapeUtils.makeShape(width, height))
        };
    }

    private RegistryObject<BlockEntityType<MetroBlockEntity>> registerEntity(String name, HelperBase[] helpers, BlockType blockType, BlockEntityType.BlockEntitySupplier entity) {
        return registration.registerBlockEntityWithBlock(name, () -> new MetroBlockDecorativeBuilder(_props)
                .setModelDirectory("blocks/machine/")
                .setModelName(name)
                .setTextures(getLoc(name))
                .setHelpers(helpers)
                .buildAs(getBlockFactory(blockType, helpers)), entity);
    }

    @Override
    public void generateCreativeTabs() {
        _machineTab.addItem(getItem(CRUSHER).get().getDefaultInstance());
        _machineTab.addItem(getItem(FABRICATOR).get().getDefaultInstance());
    }

    @Override
    public ArrayList<CreativeTabSet> getCreativeTabs() {
        ArrayList<CreativeTabSet> tabList = new ArrayList<CreativeTabSet>();
        tabList.add(_machineTab);
        return tabList;
    }

    @Override
    protected ResourceLocation getLoc(String name) {
        return new ResourceLocation(FurenikusRoads.MODID, "block/machine/" + name);
    }

    private static MetroBlockDecorativeBase.MetroBlockStateFactory getBlockFactory(BlockType type, HelperBase... helpersIn) {
        switch (type) {
            case CRUSHER:
                return (props, shape, modelDir, modelName, tag, dynamicShape, textures) -> new CrusherEntityBlock(props, shape, modelDir, modelName, tag, SimpleUtils.containsType(OffsetHelper.class, helpersIn), textures) {
                    @Override
                    public ArrayList<HelperBase> getHelpers() {
                        return new ArrayList<>(Arrays.asList(helpersIn));
                    }
                };
            case FABRICATOR:
                return (props, shape, modelDir, modelName, tag, dynamicShape, textures) -> new FabricatorEntityBlock(props, shape, modelDir, modelName, tag, SimpleUtils.containsType(OffsetHelper.class, helpersIn), textures) {
                    @Override
                    public ArrayList<HelperBase> getHelpers() {
                        return new ArrayList<>(Arrays.asList(helpersIn));
                    }
                };
        }
        return null;
    }
}


