package com.fureniku.roads.registrations;

import com.fureniku.metropolis.RegistrationBase;
import com.fureniku.metropolis.RegistrationGroup;
import com.fureniku.metropolis.utils.CreativeTabSet;
import com.fureniku.roads.blocks.DecorativeBlock;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.ArrayList;

public class RegistrationGeneral extends RegistrationGroup {

    private final String CRUSHED_ROCK = "crushed_rock";
    private final String CLINKER = "clinker";
    private final String CEMENT = "cement";
    private final String LIMESTONE = "limestone";

    private CreativeTabSet _generalTab;

    public RegistrationGeneral(RegistrationBase registrationBase) {
        super(registrationBase);
    }

    @Override
    public void init(IEventBus iEventBus) {
        registerBlockSet(CRUSHED_ROCK, DecorativeBlock::new);
        registerBlockSet(CLINKER, DecorativeBlock::new);
        registerBlockSet(CEMENT, DecorativeBlock::new);
        registerBlockSet(LIMESTONE, DecorativeBlock::new);

        _generalTab = new CreativeTabSet(registration.getCreativeTabDeferredRegister(),"tab_general", getItem(CRUSHED_ROCK));
    }

    @Override
    public void generateCreativeTabs() {
        _generalTab.addItem(getItem(CRUSHED_ROCK).get().getDefaultInstance());
        _generalTab.addItem(getItem(CLINKER).get().getDefaultInstance());
        _generalTab.addItem(getItem(CEMENT).get().getDefaultInstance());
        _generalTab.addItem(getItem(LIMESTONE).get().getDefaultInstance());
    }

    @Override
    public ArrayList<CreativeTabSet> getCreativeTabs() {
        ArrayList<CreativeTabSet> tabList = new ArrayList<CreativeTabSet>();
        tabList.add(_generalTab);
        return tabList;
    }
}
