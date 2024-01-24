package com.fureniku.roads;

import com.fureniku.metropolis.RegistrationBase;
import com.fureniku.metropolis.datagen.MetroBlockStateProvider;
import com.fureniku.metropolis.utils.CreativeTabSet;
import com.fureniku.roads.registrations.RegistrationDecorative;
import com.fureniku.roads.registrations.RegistrationGeneral;
import com.fureniku.roads.registrations.RegistrationMachines;
import com.fureniku.roads.registrations.RegistrationRoadBlocks;
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
    protected void clientSetup(FMLClientSetupEvent event) {}

    @Override
    protected void modelSetup(ModelEvent.RegisterGeometryLoaders registerGeometryLoaders) {}

    @Override
    protected void modifyBakingResult(ModelEvent.ModifyBakingResult modifyBakingResult) {}

    @Override
    protected void bakingComplete(ModelEvent.BakingCompleted bakingCompleted) {}

    @Override
    protected void dataGen(GatherDataEvent event, DataGenerator gen, PackOutput packOutput, ExistingFileHelper efh) {
        gen.addProvider(event.includeClient(), new MetroBlockStateProvider(packOutput, FurenikusRoads.MODID, efh, FurenikusRoads.INSTANCE.registration));
    }
}