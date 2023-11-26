package com.fureniku.roads.registrations;

import com.fureniku.metropolis.RegistrationBase;
import com.fureniku.metropolis.RegistrationGroup;
import com.fureniku.metropolis.datagen.TextureSet;
import com.fureniku.metropolis.utils.CreativeTabSet;
import com.fureniku.roads.FurenikusRoads;
import com.fureniku.roads.blocks.BollardBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.ArrayList;

public class RegistrationDecorative extends RegistrationGroup {

    int bollardCount = 1;

    private CreativeTabSet _decorativeTab;

    private BlockBehaviour.Properties _props = BlockBehaviour.Properties.of().strength(1.0f).sound(SoundType.METAL);

    public RegistrationDecorative(RegistrationBase registrationBase) {
        super(registrationBase);
    }

    @Override
    public void init(IEventBus modEventBus) {
        registerBlockSet("bollard_" + (bollardCount++), () -> new BollardBlock(_props, 16, 4, "bollard_round_top", new ResourceLocation(FurenikusRoads.MODID, "block/roads/road_block_concrete_1")));
        registerBlockSet("bollard_" + (bollardCount++), () -> new BollardBlock(_props, 16, 4, "bollard_round_top", new ResourceLocation(FurenikusRoads.MODID, "block/roads/road_block_concrete_2")));
        registerBlockSet("bollard_" + (bollardCount++), () -> new BollardBlock(_props, 14, 3, "bollard_smooth", getLoc("bollard_smooth_metal")));
        registerBlockSet("bollard_" + (bollardCount++), () -> new BollardBlock(_props, 14, 3, "bollard_smooth", texture("texture", "bollard_striped"), texture("top", "bollard_black")));
        registerBlockSet("bollard_" + (bollardCount++), () -> new BollardBlock(_props, 14, 3, "bollard_smooth", texture("texture", "bollard_black_ringed"), texture("top", "bollard_black")));
        registerBlockSet("bollard_" + (bollardCount++), () -> new BollardBlock(_props, 14, 3, "bollard_smooth", texture("texture", "bollard_ringed"), texture("top", "bollard_smooth_metal")));
        registerBlockSet("bollard_" + (bollardCount++), () -> new BollardBlock(_props, 14, 3, "bollard_smooth", getLoc("bollard_yellow")));
        registerBlockSet("bollard_" + (bollardCount++), () -> new BollardBlock(_props, 14, 2, "bollard_smooth_thin", getLoc("bollard_smooth_metal")));
        registerBlockSet("bollard_" + (bollardCount++), () -> new BollardBlock(_props, 14, 2, "bollard_smooth_thin", texture("texture", "bollard_striped"), texture("top", "bollard_black")));
        registerBlockSet("bollard_" + (bollardCount++), () -> new BollardBlock(_props, 14, 2, "bollard_smooth_thin", texture("texture", "bollard_black_ringed"), texture("top", "bollard_black")));
        registerBlockSet("bollard_" + (bollardCount++), () -> new BollardBlock(_props, 14, 2, "bollard_smooth_thin", texture("texture", "bollard_ringed"), texture("top", "bollard_smooth_metal")));
        registerBlockSet("bollard_" + (bollardCount++), () -> new BollardBlock(_props, 14, 2, "bollard_smooth_thin", getLoc("bollard_yellow")));
        registerBlockSet("bollard_" + (bollardCount++), () -> new BollardBlock(_props, 14, 2, "bollard_topped", getLoc("bollard_black")));
        registerBlockSet("bollard_" + (bollardCount++), () -> new BollardBlock(_props, 14, 2, "bollard_topped", getLoc("bollard_smooth_metal")));
        registerBlockSet("bollard_" + (bollardCount++), () -> new BollardBlock(_props, 14, 2, "bollard_topped_ringed", getLoc("bollard_black")));
        registerBlockSet("bollard_" + (bollardCount++), () -> new BollardBlock(_props, 14, 2, "bollard_topped_ringed", getLoc("bollard_smooth_metal")));
        registerBlockSet("bollard_" + (bollardCount++), () -> new BollardBlock(_props.lightLevel(p_50874_ -> 15).noOcclusion(), 16, 3, "bollard_smooth_light", getLoc("bollard_black")));
        registerBlockSet("bollard_" + (bollardCount++), () -> new BollardBlock(_props.lightLevel(p_50874_ -> 15).noOcclusion(), 16, 3, "bollard_smooth_light", getLoc("bollard_smooth_metal")));
        registerBlockSet("bollard_" + (bollardCount++), () -> new BollardBlock(_props, 16, 2, "bollard_portable", getLoc("bollard_small_portable")));
        registerBlockSet("bollard_" + (bollardCount++), () -> new BollardBlock(_props, 16, 2, "bollard_portable", getLoc("bollard_small_portable_yellow")));

        _decorativeTab = new CreativeTabSet(registration.getCreativeTabDeferredRegister(),"tab_decorative", getItem("bollard_1"));
    }

    @Override
    public void generateCreativeTabs() {
        for (int i = 0; i < bollardCount-1; i++) {
            _decorativeTab.addItem(getItem("bollard_" + (i+1)).get().getDefaultInstance());
        }
    }

    @Override
    public ArrayList<CreativeTabSet> getCreativeTabs() {
        ArrayList<CreativeTabSet> tabList = new ArrayList<>();
        tabList.add(_decorativeTab);
        return tabList;
    }

    private ResourceLocation getLoc(String name) {
        return new ResourceLocation(FurenikusRoads.MODID, "block/decorative/" + name);
    }

    private TextureSet texture(String name, String loc) {
        return new TextureSet(name, getLoc(loc));
    }
}
