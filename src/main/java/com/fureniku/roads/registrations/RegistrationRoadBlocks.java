package com.fureniku.roads.registrations;

import com.fureniku.metropolis.RegistrationBase;
import com.fureniku.metropolis.RegistrationGroup;
import com.fureniku.metropolis.blocks.BlockSet;
import com.fureniku.metropolis.utils.CreativeTabSet;
import com.fureniku.roads.blocks.RoadBlockSet;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.GrassColor;
import net.neoforged.bus.api.IEventBus;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class RegistrationRoadBlocks extends RegistrationGroup {

    private final String ROAD_BLOCK_STANDARD = "road_block_standard";
    private final String ROAD_BLOCK_CONCRETE_1 = "road_block_concrete_1";
    private final String ROAD_BLOCK_CONCRETE_2 = "road_block_concrete_2";
    private final String ROAD_BLOCK_FINE = "road_block_fine";
    private final String ROAD_BLOCK_DARK = "road_block_dark";
    private final String ROAD_BLOCK_PALE = "road_block_pale";
    private final String ROAD_BLOCK_LIGHT = "road_block_light";
    private final String ROAD_BLOCK_MUDDY = "road_block_muddy";
    private final String ROAD_BLOCK_MUDDY_DRIED = "road_block_muddy_dried";
    private final String ROAD_BLOCK_WHITE = "road_block_white";
    private final String ROAD_BLOCK_YELLOW = "road_block_yellow";
    private final String ROAD_BLOCK_RED = "road_block_red";
    private final String ROAD_BLOCK_BLUE = "road_block_blue";
    private final String ROAD_BLOCK_GREEN = "road_block_green";

    private final String ROAD_BLOCK_GRASS = "grass_road";
    private final String ROAD_BLOCK_STONE = "stone_road";
    private final String ROAD_BLOCK_DIRT = "dirt_road";
    private final String ROAD_BLOCK_GRAVEL = "gravel_road";
    private final String ROAD_BLOCK_SAND = "sand_road";

    private final String SIDEWALK_STANDARD = "sidewalk";
    private final String SIDEWALK_CLEAN = "sidewalk_clean";
    private final String SIDEWALK_DARK = "sidewalk_dark";
    private final String SIDEWALK_TAN = "sidewalk_tan";

    private ArrayList<BlockSet> _roadBlockSets = new ArrayList<>();
    private CreativeTabSet _roadTab;

    //region Suppliers
    private BiFunction<BlockAndTintGetter, BlockPos, Integer> _grassBlockCol = (tint, pos) ->
            tint != null && pos != null ?
                    BiomeColors.getAverageGrassColor(tint, pos) :
                    GrassColor.getDefaultColor();
    private Supplier<Integer> _grassItemCol = () -> GrassColor.getDefaultColor();
    //endregion

    public RegistrationRoadBlocks(RegistrationBase registrationBase) {
        super(registrationBase);
    }

    @Override
    public void init(IEventBus modEventBus) {
        _roadBlockSets.add(new RoadBlockSet(ROAD_BLOCK_STANDARD));
        _roadBlockSets.add(new RoadBlockSet(ROAD_BLOCK_CONCRETE_1));
        _roadBlockSets.add(new RoadBlockSet(ROAD_BLOCK_CONCRETE_2));
        _roadBlockSets.add(new RoadBlockSet(ROAD_BLOCK_FINE));
        _roadBlockSets.add(new RoadBlockSet(ROAD_BLOCK_DARK));
        _roadBlockSets.add(new RoadBlockSet(ROAD_BLOCK_PALE));
        _roadBlockSets.add(new RoadBlockSet(ROAD_BLOCK_LIGHT));
        _roadBlockSets.add(new RoadBlockSet(ROAD_BLOCK_MUDDY));
        _roadBlockSets.add(new RoadBlockSet(ROAD_BLOCK_MUDDY_DRIED));
        _roadBlockSets.add(new RoadBlockSet(ROAD_BLOCK_WHITE));
        _roadBlockSets.add(new RoadBlockSet(ROAD_BLOCK_YELLOW));
        _roadBlockSets.add(new RoadBlockSet(ROAD_BLOCK_RED));
        _roadBlockSets.add(new RoadBlockSet(ROAD_BLOCK_BLUE));
        _roadBlockSets.add(new RoadBlockSet(ROAD_BLOCK_GREEN));

        _roadBlockSets.add(new RoadBlockSet(ROAD_BLOCK_GRASS, new ResourceLocation("minecraft", "block/grass_block_top")).addColorTints(modEventBus, _grassBlockCol, _grassItemCol));
        _roadBlockSets.add(new RoadBlockSet(ROAD_BLOCK_STONE, new ResourceLocation("minecraft", "block/stone")));
        _roadBlockSets.add(new RoadBlockSet(ROAD_BLOCK_DIRT, new ResourceLocation("minecraft", "block/dirt")));
        _roadBlockSets.add(new RoadBlockSet(ROAD_BLOCK_GRAVEL, new ResourceLocation("minecraft", "block/gravel")));
        _roadBlockSets.add(new RoadBlockSet(ROAD_BLOCK_SAND, new ResourceLocation("minecraft", "block/sand")));

        _roadBlockSets.add(new RoadBlockSet(SIDEWALK_STANDARD));
        _roadBlockSets.add(new RoadBlockSet(SIDEWALK_CLEAN));
        _roadBlockSets.add(new RoadBlockSet(SIDEWALK_DARK));
        _roadBlockSets.add(new RoadBlockSet(SIDEWALK_TAN));

        _roadTab = new CreativeTabSet(registration.getCreativeTabDeferredRegister(),"tab_roads", _roadBlockSets.get(0).getRegistryItem(16));
    }

    @Override
    public void generateCreativeTabs() {
        for (int i = 0; i < _roadBlockSets.size(); i++) {
            _roadBlockSets.get(i).registerToCreativeTab(_roadTab);
        }
    }

    @Override
    public ArrayList<CreativeTabSet> getCreativeTabs() {
        ArrayList<CreativeTabSet> tabList = new ArrayList<CreativeTabSet>();
        tabList.add(_roadTab);
        return tabList;
    }
}
