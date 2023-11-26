package com.fureniku.roads;

import com.fureniku.metropolis.RegistrationBase;
import com.fureniku.metropolis.utils.CreativeTabSet;
import com.fureniku.roads.registrations.RegistrationDecorative;
import com.fureniku.roads.registrations.RegistrationGeneral;
import com.fureniku.roads.registrations.RegistrationRoadBlocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.ArrayList;

public class RegistrationRoads extends RegistrationBase {

    private RegistrationRoadBlocks _registrationRoads = new RegistrationRoadBlocks(this);
    private RegistrationGeneral _registrationGeneral = new RegistrationGeneral(this);
    private RegistrationDecorative _registrationDecorative = new RegistrationDecorative(this);

    public RegistrationRoads(String modid, IEventBus modEventBus) {
        super(modid, modEventBus);
    }

    @Override
    public void init(IEventBus modEventBus) {
        _registrationRoads.init(modEventBus);
        _registrationGeneral.init(modEventBus);
        _registrationDecorative.init(modEventBus);
    }

    @Override
    public void generateCreativeTabs() {
        _registrationRoads.generateCreativeTabs();
        _registrationGeneral.generateCreativeTabs();
        _registrationDecorative.generateCreativeTabs();
    }

    @Override
    protected ArrayList<CreativeTabSet> getCreativeTabs() {
        ArrayList<CreativeTabSet> tabList = new ArrayList<CreativeTabSet>();
        tabList.addAll(_registrationRoads.getCreativeTabs());
        tabList.addAll(_registrationGeneral.getCreativeTabs());
        tabList.addAll(_registrationDecorative.getCreativeTabs());
        return tabList;
    }

    @Override
    protected void commonSetup(FMLCommonSetupEvent event) {}

    @Override
    protected void clientSetup(FMLClientSetupEvent event) {}
}