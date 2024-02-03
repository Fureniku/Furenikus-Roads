package com.fureniku.roads;

import com.fureniku.metropolis.RegistrationBase;
import com.fureniku.metropolis.datagen.MetroBlockStateProvider;
import com.fureniku.metropolis.utils.CreativeTabSet;
import com.fureniku.metropolis.utils.Debug;
import com.fureniku.roads.blocks.entityblock.CrusherEntityBlock;
import com.fureniku.roads.client.screens.CrusherScreen;
import com.fureniku.roads.registrations.*;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;


import java.util.ArrayList;

public class RegistrationRoads extends RegistrationBase {

    public final RegistrationRoadBlocks _registrationRoads = new RegistrationRoadBlocks(this);
    public final RegistrationGeneral _registrationGeneral = new RegistrationGeneral(this);
    public final RegistrationDecorative _registrationDecorative = new RegistrationDecorative(this);
    public final RegistrationMachines _registrationMachines = new RegistrationMachines(this);

    public RegistrationRoads(String modid, IEventBus modEventBus) {
        super(modid, modEventBus);
    }

    @Override
    public void init(IEventBus modEventBus) {
        _registrationRoads.init(modEventBus);
        _registrationGeneral.init(modEventBus);
        _registrationDecorative.init(modEventBus);
        _registrationMachines.init(modEventBus);
    }

    @Override
    public void generateCreativeTabs() {
        _registrationRoads.generateCreativeTabs();
        _registrationGeneral.generateCreativeTabs();
        _registrationDecorative.generateCreativeTabs();
        _registrationMachines.generateCreativeTabs();
    }

    @Override
    protected ArrayList<CreativeTabSet> getCreativeTabs() {
        ArrayList<CreativeTabSet> tabList = new ArrayList<CreativeTabSet>();
        tabList.addAll(_registrationRoads.getCreativeTabs());
        tabList.addAll(_registrationGeneral.getCreativeTabs());
        tabList.addAll(_registrationDecorative.getCreativeTabs());
        tabList.addAll(_registrationMachines.getCreativeTabs());
        return tabList;
    }

    @Override
    protected void commonSetup(FMLCommonSetupEvent event) {}

    @Override
    protected void clientSetup(FMLClientSetupEvent event) {
        Debug.Log("### registered screen");
        MenuScreens.register(CrusherEntityBlock.MENU_TYPE.get(), CrusherScreen::new);
        if (RoadMenuTypes.CRUSHER_MENU == null) {
            Debug.Log("Menu is null");
        } else {
            Debug.Log("Menu is not null");
        }
    }

    @Override
    protected void dataGen(GatherDataEvent event, DataGenerator gen, PackOutput packOutput, ExistingFileHelper efh) {
        gen.addProvider(event.includeClient(), new MetroBlockStateProvider(packOutput, FurenikusRoads.MODID, efh, FurenikusRoads.INSTANCE.registration));
    }
}