package com.fureniku.roads;

import com.fureniku.metropolis.RegistrationBase;
import com.fureniku.metropolis.utils.CreativeTabSet;
import com.fureniku.roads.blocks.DecorativeBlock;
import com.fureniku.roads.blocks.RoadBlockSet;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.stream.Stream;

public class RegistrationRoads extends RegistrationBase {

    //region Blocknames
    private final String CRUSHED_ROCK = "crushed_rock";
    private final String CLINKER = "clinker";
    private final String CEMENT = "cement";
    private final String LIMESTONE = "limestone";
    //endregion

    private CreativeTabSet _roadTab;
    private CreativeTabSet _generalTab;

    private RoadBlockSet _grassBlockSet; //Edge case - needs colouring.

    private ArrayList<RoadBlockSet> _roadBlockSets = new ArrayList<>();

    public RegistrationRoads(String modid, IEventBus modEventBus) {
        super(modid, modEventBus);
    }

    @Override
    public void init(IEventBus modEventBus) {
        _grassBlockSet = new RoadBlockSet("grass_road", new ResourceLocation("minecraft", "block/grass_block_top"));

        _roadBlockSets.add(new RoadBlockSet("road_block_standard"));
        _roadBlockSets.add(new RoadBlockSet("road_block_concrete_1"));
        _roadBlockSets.add(new RoadBlockSet("road_block_concrete_2"));
        _roadBlockSets.add(new RoadBlockSet("road_block_fine"));
        _roadBlockSets.add(new RoadBlockSet("road_block_dark"));
        _roadBlockSets.add(new RoadBlockSet("road_block_pale"));
        _roadBlockSets.add(new RoadBlockSet("road_block_light"));
        _roadBlockSets.add(new RoadBlockSet("road_block_muddy"));
        _roadBlockSets.add(new RoadBlockSet("road_block_muddy_dried"));
        _roadBlockSets.add(new RoadBlockSet("road_block_white"));
        _roadBlockSets.add(new RoadBlockSet("road_block_yellow"));
        _roadBlockSets.add(new RoadBlockSet("road_block_red"));
        _roadBlockSets.add(new RoadBlockSet("road_block_blue"));
        _roadBlockSets.add(new RoadBlockSet("road_block_green"));

        _roadBlockSets.add(_grassBlockSet);
        _roadBlockSets.add(new RoadBlockSet("stone_road", new ResourceLocation("minecraft", "block/stone")));
        _roadBlockSets.add(new RoadBlockSet("dirt_road", new ResourceLocation("minecraft", "block/dirt")));
        _roadBlockSets.add(new RoadBlockSet("gravel_road", new ResourceLocation("minecraft", "block/gravel")));
        _roadBlockSets.add(new RoadBlockSet("sand_road", new ResourceLocation("minecraft", "block/sand")));

        _roadBlockSets.add(new RoadBlockSet("sidewalk"));
        _roadBlockSets.add(new RoadBlockSet("sidewalk_clean"));
        _roadBlockSets.add(new RoadBlockSet("sidewalk_dark"));
        _roadBlockSets.add(new RoadBlockSet("sidewalk_tan"));

        registerBlockSet(CRUSHED_ROCK, DecorativeBlock::new);
        registerBlockSet(CLINKER, DecorativeBlock::new);
        registerBlockSet(CEMENT, DecorativeBlock::new);
        registerBlockSet(LIMESTONE, DecorativeBlock::new);

        _roadTab = new CreativeTabSet(creativeTabs,"road_tab", _roadBlockSets.get(0).getRegistryItem(16));
        _generalTab = new CreativeTabSet(creativeTabs,"general_tab", getItem(CRUSHED_ROCK));

        modEventBus.addListener(this::registerBlockColors);
        modEventBus.addListener(this::registerItemColors);
    }

    @Override
    public void generateCreativeTabs() {
        buildRoadTab();
        buildGeneralTab();
    }

    private void buildRoadTab() {
        for (int i = 0; i < _roadBlockSets.size(); i++) {
            _roadBlockSets.get(i).registerToCreativeTab(_roadTab);
        }
    }

    private void buildGeneralTab() {
        _generalTab.addItem(getItem(CRUSHED_ROCK).get().getDefaultInstance());
        _generalTab.addItem(getItem(CLINKER).get().getDefaultInstance());
        _generalTab.addItem(getItem(CEMENT).get().getDefaultInstance());
        _generalTab.addItem(getItem(LIMESTONE).get().getDefaultInstance());
    }

    @Override
    protected ArrayList<CreativeTabSet> getCreativeTabs() {
        ArrayList<CreativeTabSet> tabList = new ArrayList<CreativeTabSet>();
        tabList.add(_roadTab);
        tabList.add(_generalTab);
        return tabList;
    }

    @SubscribeEvent
    public void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((state, tint, pos, tintIndex) -> tint != null && pos != null ? BiomeColors.getAverageGrassColor(tint, pos) : GrassColor.getDefaultColor(),
                Stream.of(_grassBlockSet.getRegistryBlocks()).map(RegistryObject::get).toArray(Block[]::new));
    }

    @SubscribeEvent
    public void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> GrassColor.getDefaultColor(),
                Stream.of(_grassBlockSet.getRegistryItems()).map(RegistryObject::get).toArray(Item[]::new));
    }
}