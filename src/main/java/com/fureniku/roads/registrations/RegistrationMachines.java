package com.fureniku.roads.registrations;

import com.fureniku.metropolis.RegistrationBase;
import com.fureniku.metropolis.RegistrationGroup;
import com.fureniku.metropolis.blockentity.MetroBlockEntity;
import com.fureniku.metropolis.blocks.decorative.builders.MetroBlockDecorativeBuilder;
import com.fureniku.metropolis.blocks.decorative.helpers.HelperBase;
import com.fureniku.metropolis.blocks.decorative.helpers.RotationHelper;
import com.fureniku.metropolis.utils.CreativeTabSet;
import com.fureniku.metropolis.utils.Debug;
import com.fureniku.metropolis.utils.ShapeUtils;
import com.fureniku.roads.FurenikusRoads;
import com.fureniku.roads.blockentities.BlockEntityFactoryManager;
import com.fureniku.roads.blockentities.CrusherBlockEntity;
import com.fureniku.roads.blockentities.FabricatorBlockEntity;
import com.fureniku.roads.blocks.entityblock.CrusherEntityBlock;
import com.fureniku.roads.blocks.entityblock.FabricatorEntityBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.RegistryObject;

import java.util.ArrayList;

public class RegistrationMachines extends RegistrationGroup {

    private final String CRUSHER = "crusher";
    private final String FABRICATOR = "fabricator";
    private final String PAINT_FILLER = "paint_filler";
    private final String PAINT_OVEN = "paint_oven";
    private final String ROAD_FACTORY = "road_factory";
    private final String TAR_DISTILLER = "tar_distiller";
    private final String TARMAC_CUTTER = "tarmac_cutter";

    private RegistryObject<BlockEntityType<MetroBlockEntity>> CRUSHER_ENTITY;
    private RegistryObject<BlockEntityType<MetroBlockEntity>> FABRICATOR_ENTITY;

    private CreativeTabSet _machineTab;

    private BlockBehaviour.Properties _props = BlockBehaviour.Properties.of().strength(1.0f).sound(SoundType.METAL);


    public RegistrationMachines(RegistrationBase registrationBase) {
        super(registrationBase);
    }

    @Override
    public void init(IEventBus iEventBus) {
        Debug.Log("Initialize machines");

        RotationHelper crusherRotationHelper = new RotationHelper(ShapeUtils.makeShape(12, 14));
        RotationHelper fabricatorRotationHelper = new RotationHelper(ShapeUtils.makeShape(12, 9.5f));

        CRUSHER_ENTITY = registration.registerBlockEntityWithBlock(CRUSHER, () -> new MetroBlockDecorativeBuilder<CrusherEntityBlock>(_props)
                .setModelDirectory("blocks/machine/")
                .setModelName("crusher")
                .setTextures(getLoc("crusher"))
                .setHelpers(crusherRotationHelper).buildAs(BlockEntityFactoryManager.getCrusherBlockFactory(crusherRotationHelper)), CrusherBlockEntity::new);

        FABRICATOR_ENTITY = registration.registerBlockEntityWithBlock(FABRICATOR, () -> new MetroBlockDecorativeBuilder<FabricatorEntityBlock>(_props)
                .setModelDirectory("blocks/machine/")
                .setModelName("fabricator")
                .setTextures(getLoc("fabricator"))
                .setHelpers(fabricatorRotationHelper).buildAs(BlockEntityFactoryManager.getFabricatorBlockFactory(fabricatorRotationHelper)), FabricatorBlockEntity::new);

        _machineTab = new CreativeTabSet(registration.getCreativeTabDeferredRegister(),"tab_machines", getItem(CRUSHER));
    }

    public RegistryObject<BlockEntityType<MetroBlockEntity>> getCrusherEntity() {
        return CRUSHER_ENTITY;
    }

    public RegistryObject<BlockEntityType<MetroBlockEntity>> getFabricatorEntity() {
        return FABRICATOR_ENTITY;
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
}
