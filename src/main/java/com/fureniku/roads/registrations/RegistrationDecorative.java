package com.fureniku.roads.registrations;

import com.fureniku.metropolis.RegistrationBase;
import com.fureniku.metropolis.RegistrationGroup;
import com.fureniku.metropolis.datagen.TextureSet;
import com.fureniku.metropolis.enums.DecorativeBuilderType;
import com.fureniku.metropolis.enums.ToggleType;
import com.fureniku.metropolis.utils.CreativeTabSet;
import com.fureniku.roads.FurenikusRoads;
import com.fureniku.roads.blocks.DecorativeRoadObjectBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;

import java.util.ArrayList;

public class RegistrationDecorative extends RegistrationGroup {

    int bollardCount = 1;
    public final String BOLLARD_SLANT_SQUARE_1 = "bollard_slant_square_1";
    public final String BOLLARD_SLANT_SQUARE_2 = "bollard_slant_square_2";
    public final String BOLLARD_SLANT_SQUARE_3 = "bollard_slant_square_3";
    public final String BOLLARD_SLANT_ROUND_1 = "bollard_slant_round_1";
    public final String BOLLARD_SLANT_ROUND_2 = "bollard_slant_round_2";
    public final String BOLLARD_SLANT_ROUND_3 = "bollard_slant_round_3";

    public final String BOLLARD_THICK_LEFT = "bollard_thick_left";
    public final String BOLLARD_THICK_RIGHT = "bollard_thick_right";
    public final String BOLLARD_THIN_LEFT = "bollard_thin_left";
    public final String BOLLARD_THIN_RIGHT = "bollard_thin_right";

    public final String BOLLARD_FOLDING_1 = "bollard_folding_1";

    public final String BOLLARD_RETRACTING_1 = "bollard_retracting_1";
    public final String BOLLARD_RETRACTING_2 = "bollard_retracting_2";
    public final String BOLLARD_RETRACTING_3 = "bollard_retracting_3";
    public final String BOLLARD_RETRACTING_4 = "bollard_retracting_4";
    public final String BOLLARD_RETRACTING_5 = "bollard_retracting_5";
    public final String BOLLARD_RETRACTING_6 = "bollard_retracting_6";
    public final String BOLLARD_RETRACTING_7 = "bollard_retracting_7";
    public final String BOLLARD_RETRACTING_8 = "bollard_retracting_8";

    private CreativeTabSet _decorativeTab;

    private BlockBehaviour.Properties _props = BlockBehaviour.Properties.of().strength(1.0f).sound(SoundType.METAL);
    private BlockBehaviour.Properties _propsLit = BlockBehaviour.Properties.of().strength(1.0f).sound(SoundType.METAL).lightLevel(p_50874_ -> 15).noOcclusion();

    public RegistrationDecorative(RegistrationBase registrationBase) {
        super(registrationBase);
    }

    @Override
    public void init(IEventBus modEventBus) {
        //Add to individual faces for emissive textures. No more custom render bullshit like 1.12!
        //Put as a comment here just for my reference until I'm actually making stuff that uses it.
        //"neoforge_data": {"block_light": 15,"sky_light": 15,"ambient_occlusion": false}
        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 4, 16).setModelName("bollard_concrete_pillar").setTextures(getLocFull("block/roads/road_block_concrete_1")).build());
        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 4, 16).setModelName("bollard_concrete_pillar").setTextures(getLocFull("block/roads/road_block_concrete_2")).build());
        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 5, 16).setModelName("bollard_concrete_pillar_double").setTextures(getLocFull("block/roads/road_block_concrete_1")).build());
        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 5, 16).setModelName("bollard_concrete_pillar_double").setTextures(getLocFull("block/roads/road_block_concrete_2")).build());
        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 4, 16).setModelName("bollard_concrete_round").setTextures(getLocFull("block/roads/road_block_concrete_1")).build());
        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 4, 16).setModelName("bollard_concrete_round").setTextures(getLocFull("block/roads/road_block_concrete_2")).build());
        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 4, 9).setModelName("bollard_concrete_round_stubby").setTextures(getLocFull("block/roads/road_block_concrete_1")).build());
        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 4, 9).setModelName("bollard_concrete_round_stubby").setTextures(getLocFull("block/roads/road_block_concrete_2")).build());
        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 4, 16).setModelName("bollard_concrete_square").setTextures(getLocFull("block/roads/road_block_concrete_1")).build());
        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 4, 16).setModelName("bollard_concrete_square").setTextures(getLocFull("block/roads/road_block_concrete_2")).build());
        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 4, 9).setModelName("bollard_concrete_square_stubby").setTextures(getLocFull("block/roads/road_block_concrete_1")).build());
        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 4, 9).setModelName("bollard_concrete_square_stubby").setTextures(getLocFull("block/roads/road_block_concrete_2")).build());
        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 4, 10).setModelName("bollard_concrete_square_stubby_ring").setTextures(getLocFull("block/roads/road_block_concrete_1")).build());
        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 4, 10).setModelName("bollard_concrete_square_stubby_ring").setTextures(getLocFull("block/roads/road_block_concrete_2")).build());

        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 4, 16).setModelName("bollard_round_ringed").setTextures(getLoc("bollard_black")).build());
        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 4, 16).setModelName("bollard_round_ringed").setTextures(getLoc("bollard_smooth_metal")).build());

        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 3, 16).setModelName("bollard_smooth").setTextures(texture("texture", "bollard_ringed_metal"), texture("top", "bollard_smooth_metal")).build());
        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 3, 16).setModelName("bollard_smooth").setTextures(texture("texture", "bollard_ringed_black"), texture("top", "bollard_black")).build());
        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 3, 16).setModelName("bollard_smooth").setTextures(texture("texture", "bollard_striped_metal"), texture("top", "bollard_black")).build());
        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 3, 16).setModelName("bollard_smooth").setTextures(texture("texture", "bollard_striped_black"), texture("top", "bollard_black")).build());
        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 3, 16).setModelName("bollard_smooth").setTextures(texture( "texture", "bollard_smooth_metal"), texture("top", "bollard_smooth_metal")).build());
        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 3, 16).setModelName("bollard_smooth").setTextures(texture("texture", "bollard_yellow"), texture("top", "bollard_yellow")).build());
        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 3, 16).setModelName("bollard_smooth").setTextures(texture("texture", "bollard_black"), texture("top", "bollard_black")).build());

        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 2, 16).setModelName("bollard_smooth_thin").setTextures(texture("texture", "bollard_ringed_metal"), texture("top", "bollard_smooth_metal")).build());
        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 2, 16).setModelName("bollard_smooth_thin").setTextures(texture("texture", "bollard_ringed_black"), texture("top", "bollard_black")).build());
        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 2, 16).setModelName("bollard_smooth_thin").setTextures(texture("texture", "bollard_striped_metal"), texture("top", "bollard_black")).build());
        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 2, 16).setModelName("bollard_smooth_thin").setTextures(texture("texture", "bollard_striped_black"), texture("top", "bollard_black")).build());
        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 2, 16).setModelName("bollard_smooth_thin").setTextures(texture("texture", "bollard_smooth_metal"), texture("top", "bollard_smooth_metal")).build());
        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 2, 16).setModelName("bollard_smooth_thin").setTextures(texture("texture", "bollard_yellow"), texture("top", "bollard_yellow")).build());
        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 2, 16).setModelName("bollard_smooth_thin").setTextures(texture("texture", "bollard_black"), texture("top", "bollard_black")).build());

        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 3, 16).setModelName("bollard_topped").setTextures(getLoc("bollard_smooth_metal")).build());
        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 3, 16).setModelName("bollard_topped").setTextures(getLoc("bollard_black")).build());
        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 3, 16).setModelName("bollard_topped_ringed").setTextures(getLoc("bollard_smooth_metal")).build());
        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 3, 16).setModelName("bollard_topped_ringed").setTextures(getLoc("bollard_black")).build());

        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_propsLit, 3, 16).setModelName("bollard_smooth_light").setTextures(getLoc("bollard_black")).build());
        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_propsLit, 3, 16).setModelName("bollard_smooth_light").setTextures(getLoc("bollard_smooth_metal")).build());
        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 2, 16).setModelName("bollard_portable").setTextures(getLoc("bollard_small_portable")).build());
        registerBlockSet("bollard_" + (bollardCount++), () -> new DecorativeRoadObjectBuilder(_props, 2, 16).setModelName("bollard_portable").setTextures(getLoc("bollard_small_portable_yellow")).build());

        registerBlockSet(BOLLARD_SLANT_SQUARE_1, () -> new DecorativeRoadObjectBuilder(_props, DecorativeBuilderType.DECORATIVE_ROTATABLE).setWidth(2).setModelName("bollard_slant_square").setTextures(getLoc("bollard_smooth_metal")).build());
        registerBlockSet(BOLLARD_SLANT_SQUARE_2, () -> new DecorativeRoadObjectBuilder(_props, DecorativeBuilderType.DECORATIVE_ROTATABLE).setWidth(2).setModelName("bollard_slant_square").setTextures(getLoc("bollard_black")).build());
        registerBlockSet(BOLLARD_SLANT_SQUARE_3, () -> new DecorativeRoadObjectBuilder(_props, DecorativeBuilderType.DECORATIVE_ROTATABLE).setWidth(2).setModelName("bollard_slant_square").setTextures(getLoc("bollard_yellow")).build());
        registerBlockSet(BOLLARD_SLANT_ROUND_1, () -> new DecorativeRoadObjectBuilder(_props, DecorativeBuilderType.DECORATIVE_ROTATABLE).setWidth(2).setModelName("bollard_slant_round").setTextures(getLoc("bollard_smooth_metal")).build());
        registerBlockSet(BOLLARD_SLANT_ROUND_2, () -> new DecorativeRoadObjectBuilder(_props, DecorativeBuilderType.DECORATIVE_ROTATABLE).setWidth(2).setModelName("bollard_slant_round").setTextures(getLoc("bollard_black")).build());
        registerBlockSet(BOLLARD_SLANT_ROUND_3, () -> new DecorativeRoadObjectBuilder(_props, DecorativeBuilderType.DECORATIVE_ROTATABLE).setWidth(2).setModelName("bollard_slant_round").setTextures(getLoc("bollard_yellow")).build());


        registerBlockSet(BOLLARD_THICK_LEFT, () -> new DecorativeRoadObjectBuilder(_propsLit, DecorativeBuilderType.DECORATIVE_ROTATABLE).setWidth(6).setModelName("bollard_traffic_1").setTextures(getLoc("traffic_bollard_thick_left")).build());
        registerBlockSet(BOLLARD_THICK_RIGHT, () -> new DecorativeRoadObjectBuilder(_propsLit, DecorativeBuilderType.DECORATIVE_ROTATABLE).setWidth(6).setModelName("bollard_traffic_1").setTextures(getLoc("traffic_bollard_thick_right")).build());
        registerBlockSet(BOLLARD_THIN_LEFT, () -> new DecorativeRoadObjectBuilder(_propsLit, DecorativeBuilderType.DECORATIVE_ROTATABLE).setShape(6, 16, 2).setModelName("bollard_traffic_2").setTextures(getLoc("traffic_bollard_thin_left")).build());
        registerBlockSet(BOLLARD_THIN_RIGHT, () -> new DecorativeRoadObjectBuilder(_propsLit, DecorativeBuilderType.DECORATIVE_ROTATABLE).setShape(6, 16, 2).setModelName("bollard_traffic_2").setTextures(getLoc("traffic_bollard_thin_right")).build());

        registerBlockSet(BOLLARD_FOLDING_1, () ->  new DecorativeRoadObjectBuilder(_props, DecorativeBuilderType.DECORATIVE_ROTATABLE_TOGGLE).setShape(2, 16).setToggledShape(Block.box(7, 0, 0, 9, 2, 9))
                .setModelName("bollard_folding_1_upright").setToggleModelName("bollard_folding_1_down").setTextures(getLoc("bollard_smooth_metal")).setToggleType(ToggleType.REDSTONE).build());

        registerBlockSet(BOLLARD_RETRACTING_1, () ->  new DecorativeRoadObjectBuilder(_props, DecorativeBuilderType.DECORATIVE_TOGGLE).setShape(4, 16).setToggledShape(5, 1)
                .setModelName("bollard_retract_thick_up").setToggleModelName("bollard_retract_thick_down").setTextures(getLoc("bollard_smooth_metal")).setToggleType(ToggleType.REDSTONE).build());
        registerBlockSet(BOLLARD_RETRACTING_2, () ->  new DecorativeRoadObjectBuilder(_props, DecorativeBuilderType.DECORATIVE_TOGGLE).setShape(4, 16).setToggledShape(5, 1)
                .setModelName("bollard_retract_thick_up").setToggleModelName("bollard_retract_thick_down").setTextures(getLoc("bollard_black")).setToggleType(ToggleType.REDSTONE).build());
        registerBlockSet(BOLLARD_RETRACTING_3, () ->  new DecorativeRoadObjectBuilder(_props, DecorativeBuilderType.DECORATIVE_TOGGLE).setShape(3, 16).setToggledShape(5, 1)
                .setModelName("bollard_retract_thin_up").setToggleModelName("bollard_retract_thin_down").setTextures(getLoc("bollard_smooth_metal")).setToggleType(ToggleType.REDSTONE).build());
        registerBlockSet(BOLLARD_RETRACTING_4, () ->  new DecorativeRoadObjectBuilder(_props, DecorativeBuilderType.DECORATIVE_TOGGLE).setShape(3, 16).setToggledShape(5, 1)
                .setModelName("bollard_retract_thin_up").setToggleModelName("bollard_retract_thin_down").setTextures(getLoc("bollard_black")).setToggleType(ToggleType.REDSTONE).build());
        registerBlockSet(BOLLARD_RETRACTING_5, () ->  new DecorativeRoadObjectBuilder(_props, DecorativeBuilderType.DECORATIVE_TOGGLE).setShape(5.5f, 13).setToggledShape(5, 1)
                .setModelName("bollard_retract_stumpy_up").setToggleModelName("bollard_retract_stumpy_down").setTextures(getLoc("bollard_smooth_metal")).setToggleType(ToggleType.REDSTONE).build());
        registerBlockSet(BOLLARD_RETRACTING_6, () ->  new DecorativeRoadObjectBuilder(_props, DecorativeBuilderType.DECORATIVE_TOGGLE).setShape(5.5f, 13).setToggledShape(5, 1)
                .setModelName("bollard_retract_stumpy_up").setToggleModelName("bollard_retract_stumpy_down").setTextures(getLoc("bollard_black")).setToggleType(ToggleType.REDSTONE).build());
        registerBlockSet(BOLLARD_RETRACTING_7, () ->  new DecorativeRoadObjectBuilder(_props, DecorativeBuilderType.DECORATIVE_TOGGLE).setShape(5, 16).setToggledShape(5, 1)
                .setModelName("bollard_retract_round_ringed_up").setToggleModelName("bollard_retract_round_ringed_down").setTextures(getLoc("bollard_smooth_metal")).setToggleType(ToggleType.REDSTONE).build());
        registerBlockSet(BOLLARD_RETRACTING_8, () ->  new DecorativeRoadObjectBuilder(_props, DecorativeBuilderType.DECORATIVE_TOGGLE).setShape(5, 16).setToggledShape(5, 1)
                .setModelName("bollard_retract_round_ringed_up").setToggleModelName("bollard_retract_round_ringed_down").setTextures(getLoc("bollard_black")).setToggleType(ToggleType.REDSTONE).build());

        _decorativeTab = new CreativeTabSet(registration.getCreativeTabDeferredRegister(),"tab_decorative", getItem("bollard_1"));
    }

    @Override
    public void generateCreativeTabs() {
        for (int i = 0; i < bollardCount-1; i++) {
            _decorativeTab.addItem(getItem("bollard_" + (i+1)).get().getDefaultInstance());
        }
        _decorativeTab.addItem(getItem(BOLLARD_SLANT_SQUARE_1).get().getDefaultInstance());
        _decorativeTab.addItem(getItem(BOLLARD_SLANT_SQUARE_2).get().getDefaultInstance());
        _decorativeTab.addItem(getItem(BOLLARD_SLANT_SQUARE_3).get().getDefaultInstance());
        _decorativeTab.addItem(getItem(BOLLARD_SLANT_ROUND_1).get().getDefaultInstance());
        _decorativeTab.addItem(getItem(BOLLARD_SLANT_ROUND_2).get().getDefaultInstance());
        _decorativeTab.addItem(getItem(BOLLARD_SLANT_ROUND_3).get().getDefaultInstance());

        _decorativeTab.addItem(getItem(BOLLARD_THICK_LEFT).get().getDefaultInstance());
        _decorativeTab.addItem(getItem(BOLLARD_THICK_RIGHT).get().getDefaultInstance());
        _decorativeTab.addItem(getItem(BOLLARD_THIN_LEFT).get().getDefaultInstance());
        _decorativeTab.addItem(getItem(BOLLARD_THIN_RIGHT).get().getDefaultInstance());

        _decorativeTab.addItem(getItem(BOLLARD_FOLDING_1).get().getDefaultInstance());

        _decorativeTab.addItem(getItem(BOLLARD_RETRACTING_1).get().getDefaultInstance());
        _decorativeTab.addItem(getItem(BOLLARD_RETRACTING_2).get().getDefaultInstance());
        _decorativeTab.addItem(getItem(BOLLARD_RETRACTING_3).get().getDefaultInstance());
        _decorativeTab.addItem(getItem(BOLLARD_RETRACTING_4).get().getDefaultInstance());
        _decorativeTab.addItem(getItem(BOLLARD_RETRACTING_5).get().getDefaultInstance());
        _decorativeTab.addItem(getItem(BOLLARD_RETRACTING_6).get().getDefaultInstance());
        _decorativeTab.addItem(getItem(BOLLARD_RETRACTING_7).get().getDefaultInstance());
        _decorativeTab.addItem(getItem(BOLLARD_RETRACTING_8).get().getDefaultInstance());
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

    private ResourceLocation getLocFull(String name) {
        return new ResourceLocation(FurenikusRoads.MODID, name);
    }

    private TextureSet texture(String name, String loc) {
        return new TextureSet(name, getLoc(loc));
    }
}
