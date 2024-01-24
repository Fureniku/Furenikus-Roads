package com.fureniku.roads.registrations;

import com.fureniku.metropolis.RegistrationBase;
import com.fureniku.metropolis.RegistrationGroup;
import com.fureniku.metropolis.blocks.decorative.builders.MetroBlockDecorativeBuilder;
import com.fureniku.metropolis.blocks.decorative.helpers.ConnectHorizontalHelper;
import com.fureniku.metropolis.blocks.decorative.helpers.OffsetHelper;
import com.fureniku.metropolis.blocks.decorative.helpers.RotationHelper;
import com.fureniku.metropolis.blocks.decorative.helpers.ToggleHelper;
import com.fureniku.metropolis.enums.BlockConnectionType;
import com.fureniku.metropolis.enums.BlockOffsetDirection;
import com.fureniku.metropolis.enums.ToggleType;
import com.fureniku.metropolis.utils.CreativeTabSet;
import com.fureniku.metropolis.utils.ShapeUtils;
import com.fureniku.roads.FurenikusRoads;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.ArrayList;

public class RegistrationDecorative extends RegistrationGroup {

    //Add to individual faces for emissive textures. No more custom render bullshit like 1.12!
    //Put as a comment here just for my reference until I'm actually making stuff that uses it.
    //"neoforge_data": {"block_light": 15,"sky_light": 15,"ambient_occlusion": false}

    //region Block names
    //Concrete
    public final String BOLLARD_CONCRETE_PILLAR_1 = "bollard_concrete_pillar_1";
    public final String BOLLARD_CONCRETE_PILLAR_2 = "bollard_concrete_pillar_2";
    public final String BOLLARD_CONCRETE_PILLAR_DOUBLE_1 = "bollard_concrete_pillar_double_1";
    public final String BOLLARD_CONCRETE_PILLAR_DOUBLE_2 = "bollard_concrete_pillar_double_2";
    public final String BOLLARD_CONCRETE_ROUND_1 = "bollard_concrete_round_1";
    public final String BOLLARD_CONCRETE_ROUND_2 = "bollard_concrete_round_2";
    public final String BOLLARD_CONCRETE_ROUND_STUBBY_1 = "bollard_concrete_round_stubby_1";
    public final String BOLLARD_CONCRETE_ROUND_STUBBY_2 = "bollard_concrete_round_stubby_2";
    public final String BOLLARD_CONCRETE_SQUARE_1 = "bollard_concrete_square_1";
    public final String BOLLARD_CONCRETE_SQUARE_2 = "bollard_concrete_square_2";
    public final String BOLLARD_CONCRETE_SQUARE_STUBBY_1 = "bollard_concrete_square_stubby_1";
    public final String BOLLARD_CONCRETE_SQUARE_STUBBY_2 = "bollard_concrete_square_stubby_2";
    public final String BOLLARD_CONCRETE_SQUARE_STUBBY_RING_1 = "bollard_concrete_square_stubby_ring_1";
    public final String BOLLARD_CONCRETE_SQUARE_STUBBY_RING_2 = "bollard_concrete_square_stubby_ring_2";

    //Standard
    public final String BOLLARD_ROUND_RINGED_BLACK = "bollard_round_ringed_black";
    public final String BOLLARD_ROUND_RINGED_DARK_METAL = "bollard_round_ringed_dark_metal";
    public final String BOLLARD_ROUND_RINGED_LIGHT_METAL = "bollard_round_ringed_light_metal";
    public final String BOLLARD_ROUND_RINGED_YELLOW = "bollard_round_ringed_yellow";
    public final String BOLLARD_ROUND_RINGED_WHITE = "bollard_round_ringed_white";
    public final String BOLLARD_BLACK = "bollard_black";
    public final String BOLLARD_METAL_DARK = "bollard_metal_dark";
    public final String BOLLARD_METAL_LIGHT = "bollard_metal_light";
    public final String BOLLARD_YELLOW = "bollard_yellow";
    public final String BOLLARD_WHITE = "bollard_white";
    public final String BOLLARD_THIN_BLACK = "bollard_thin_black";
    public final String BOLLARD_THIN_METAL_DARK = "bollard_thin_metal_dark";
    public final String BOLLARD_THIN_METAL_LIGHT = "bollard_thin_metal_light";
    public final String BOLLARD_THIN_YELLOW = "bollard_thin_yellow";
    public final String BOLLARD_THIN_WHITE = "bollard_thin_white";
    public final String BOLLARD_TOPPED_BLACK = "bollard_topped_black";
    public final String BOLLARD_TOPPED_METAL_DARK = "bollard_topped_metal_dark";
    public final String BOLLARD_TOPPED_METAL_LIGHT = "bollard_topped_metal_light";
    public final String BOLLARD_TOPPED_YELLOW = "bollard_topped_yellow";
    public final String BOLLARD_TOPPED_WHITE = "bollard_topped_white";
    public final String BOLLARD_TOPPED_RINGED_BLACK = "bollard_topped_ringed_black";
    public final String BOLLARD_TOPPED_RINGED_METAL_DARK = "bollard_topped_ringed_metal_dark";
    public final String BOLLARD_TOPPED_RINGED_METAL_LIGHT = "bollard_topped_ringed_metal_light";
    public final String BOLLARD_TOPPED_RINGED_YELLOW = "bollard_topped_ringed_yellow";
    public final String BOLLARD_TOPPED_RINGED_WHITE = "bollard_topped_ringed_white";

    //Rotatable
    public final String BOLLARD_SLANT_SQUARE_BLACK = "bollard_slant_square_black";
    public final String BOLLARD_SLANT_SQUARE_DARK_METAL = "bollard_slant_square_dark_metal";
    public final String BOLLARD_SLANT_SQUARE_LIGHT_METAL = "bollard_slant_square_light_metal";
    public final String BOLLARD_SLANT_SQUARE_YELLOW = "bollard_slant_square_yellow";
    public final String BOLLARD_SLANT_SQUARE_WHITE = "bollard_slant_square_white";
    public final String BOLLARD_SLANT_ROUND_BLACK = "bollard_slant_round_black";
    public final String BOLLARD_SLANT_ROUND_DARK_METAL = "bollard_slant_round_dark_metal";
    public final String BOLLARD_SLANT_ROUND_LIGHT_METAL = "bollard_slant_round_light_metal";
    public final String BOLLARD_SLANT_ROUND_YELLOW = "bollard_slant_round_yellow";
    public final String BOLLARD_SLANT_ROUND_WHITE = "bollard_slant_round_white";

    //Illuminated
    public final String BOLLARD_LIGHT_1_CONCRETE_1 = "bollard_light_1_concrete_1";
    public final String BOLLARD_LIGHT_1_CONCRETE_2 = "bollard_light_1_concrete_2";
    public final String BOLLARD_LIGHT_1_BLACK = "bollard_light_1_black";
    public final String BOLLARD_LIGHT_1_DARK_METAL = "bollard_light_1_dark_metal";
    public final String BOLLARD_LIGHT_1_LIGHT_METAL = "bollard_light_1_light_metal";
    public final String BOLLARD_LIGHT_1_YELLOW = "bollard_light_1_yellow";
    public final String BOLLARD_LIGHT_1_WHITE = "bollard_light_1_white";

    //Rotatable Illuminated
    public final String BOLLARD_THICK_LEFT = "bollard_thick_left";
    public final String BOLLARD_THICK_RIGHT = "bollard_thick_right";
    public final String BOLLARD_THIN_LEFT = "bollard_thin_left";
    public final String BOLLARD_THIN_RIGHT = "bollard_thin_right";

    //Reflective
    public final String BOLLARD_PORTABLE_ORANGE = "bollard_portable_orange";
    public final String BOLLARD_PORTABLE_YELLOW = "bollard_portable_yellow";

    //Foldable
    public final String BOLLARD_FOLDING_THIN_BLACK = "bollard_folding_thin_black";
    public final String BOLLARD_FOLDING_THIN_DARK_METAL = "bollard_folding_thin_dark_metal";
    public final String BOLLARD_FOLDING_THIN_LIGHT_METAL = "bollard_folding_thin_light_metal";
    public final String BOLLARD_FOLDING_THIN_YELLOW = "bollard_folding_thin_yellow";
    public final String BOLLARD_FOLDING_THIN_WHITE = "bollard_folding_thin_white";

    //Retracting
    public final String BOLLARD_RETRACTING_THICK_BLACK = "bollard_retracting_thick_black";
    public final String BOLLARD_RETRACTING_THICK_DARK_METAL = "bollard_retracting_thick_dark_metal";
    public final String BOLLARD_RETRACTING_THICK_LIGHT_METAL = "bollard_retracting_thick_light_metal";
    public final String BOLLARD_RETRACTING_THICK_YELLOW = "bollard_retracting_thick_yellow";
    public final String BOLLARD_RETRACTING_THICK_WHITE = "bollard_retracting_thick_white";
    public final String BOLLARD_RETRACTING_THIN_BLACK = "bollard_retracting_thin_black";
    public final String BOLLARD_RETRACTING_THIN_DARK_METAL = "bollard_retracting_thin_dark_metal";
    public final String BOLLARD_RETRACTING_THIN_LIGHT_METAL = "bollard_retracting_thin_light_metal";
    public final String BOLLARD_RETRACTING_THIN_YELLOW = "bollard_retracting_thin_yellow";
    public final String BOLLARD_RETRACTING_THIN_WHITE = "bollard_retracting_thin_white";
    public final String BOLLARD_RETRACTING_STUMPY_BLACK = "bollard_retracting_stumpy_black";
    public final String BOLLARD_RETRACTING_STUMPY_DARK_METAL = "bollard_retracting_stumpy_dark_metal";
    public final String BOLLARD_RETRACTING_STUMPY_LIGHT_METAL = "bollard_retracting_stumpy_light_metal";
    public final String BOLLARD_RETRACTING_STUMPY_YELLOW = "bollard_retracting_stumpy_yellow";
    public final String BOLLARD_RETRACTING_STUMPY_WHITE = "bollard_retracting_stumpy_white";
    public final String BOLLARD_RETRACTING_ROUND_BLACK = "bollard_retracting_round_black";
    public final String BOLLARD_RETRACTING_ROUND_DARK_METAL = "bollard_retracting_round_dark_metal";
    public final String BOLLARD_RETRACTING_ROUND_LIGHT_METAL = "bollard_retracting_round_light_metal";
    public final String BOLLARD_RETRACTING_ROUND_YELLOW = "bollard_retracting_round_yellow";
    public final String BOLLARD_RETRACTING_ROUND_WHITE = "bollard_retracting_round_white";

    public final String WALL_CONCRETE_CENTER_SOLID_1 = "wall_concrete_center_solid_1";
    public final String WALL_CONCRETE_CENTER_SOLID_2 = "wall_concrete_center_solid_2";

    public final String BARRIER_METAL_CENTER_SHORT = "barrier_metal_center_short";
    public final String BARRIER_METAL_CENTER_TALL = "barrier_metal_center_tall";

    public final String BARRIER_BARS_CENTER_1 = "barrier_bars_center_1";
    public final String BARRIER_BARS_CENTER_2 = "barrier_bars_center_2";
    public final String BARRIER_BARS_CENTER_3 = "barrier_bars_center_3";
    public final String BARRIER_BARS_CENTER_4 = "barrier_bars_center_4";
    public final String BARRIER_BARS_CENTER_5 = "barrier_bars_center_5";

    public final String WALL_CONCRETE_CENTER_THIN_1 = "wall_concrete_center_thin_1";
    public final String WALL_CONCRETE_CENTER_THIN_2 = "wall_concrete_center_thin_2";
    public final String WALL_CONCRETE_CENTER_THIN_3 = "wall_concrete_center_thin_3";
    public final String WALL_CONCRETE_CENTER_THIN_4 = "wall_concrete_center_thin_4";
    //endregion

    //region Textures
    private final ResourceLocation TEX_CONC_1= getLocFull("block/roads/road_block_concrete_1");
    private final ResourceLocation TEX_CONC_2 = getLocFull("block/roads/road_block_concrete_2");

    private final ResourceLocation TEX_BLACK = getLoc("bollard_black");
    private final ResourceLocation TEX_METAL_DARK = getLoc("bollard_metal_dark");
    private final ResourceLocation TEX_METAL_LIGHT = getLoc("bollard_metal_light");
    private final ResourceLocation TEX_YELLOW = getLoc("bollard_yellow");
    private final ResourceLocation TEX_WHITE = getLoc("bollard_white");
    private final ResourceLocation TEX_GOLD = getLoc("bollard_gold");
    //endregion

    private ArrayList<String> _blockNames = new ArrayList<>();
    private CreativeTabSet _decorativeTab;

    private BlockBehaviour.Properties _props = BlockBehaviour.Properties.of().strength(1.0f).sound(SoundType.METAL);
    private BlockBehaviour.Properties _propsLit = BlockBehaviour.Properties.of().strength(1.0f).sound(SoundType.METAL).lightLevel(p_50874_ -> 15).noOcclusion();

    public RegistrationDecorative(RegistrationBase registrationBase) {
        super(registrationBase);
    }

    //Generic functions to create similar block styles. The individual calls still set the texture in case there's multiple to set
    public MetroBlockDecorativeBuilder foldingRedstoneToggle(String modelName, VoxelShape shapeUp, VoxelShape shapeDown) {
        return new MetroBlockDecorativeBuilder(_props)
                .setModelDirectory("blocks/decorative/")
                .setModelName(modelName + "_up")
                .addHelper(new OffsetHelper(BlockOffsetDirection.DOWN))
                .addHelper(new ToggleHelper(true, shapeUp, shapeDown, modelName + "_down", ToggleType.REDSTONE))
                .addHelper(new RotationHelper());
    }

    public MetroBlockDecorativeBuilder bollardRedstoneToggle(String modelName, float size, float height) {
        return new MetroBlockDecorativeBuilder(_props)
                .setModelDirectory("blocks/decorative/")
                .setModelName(modelName + "_up")
                .addHelper(new OffsetHelper(BlockOffsetDirection.DOWN))
                .addHelper(new ToggleHelper(true, ShapeUtils.makeShape(size, height), ShapeUtils.makeShape(size, 1), modelName + "_down", ToggleType.REDSTONE));
    }

    public MetroBlockDecorativeBuilder rotateableBlock(BlockBehaviour.Properties props, String modelName, Vec3 shape) {
        return new MetroBlockDecorativeBuilder(props)
                .setModelDirectory("blocks/decorative/")
                .setModelName(modelName)
                .addHelper(new RotationHelper(ShapeUtils.makeShape(shape)))
                .addHelper(new OffsetHelper(BlockOffsetDirection.DOWN));
    }

    public MetroBlockDecorativeBuilder genericBlock(BlockBehaviour.Properties props, String modelName, float width, float height) {
        return new MetroBlockDecorativeBuilder(props)
                .setModelDirectory("blocks/decorative/")
                .setModelName(modelName)
                .setWidth(width)
                .setHeight(height)
                .addHelper(new OffsetHelper(BlockOffsetDirection.DOWN));
    }

    public MetroBlockDecorativeBuilder connectingBlock(BlockBehaviour.Properties props, String tag, String modelName, VoxelShape[] shapes) {
        return new MetroBlockDecorativeBuilder(props)
                .setModelDirectory("blocks/decorative/")
                .setTag(tag)
                .setModelName(modelName)
                .addHelper(new OffsetHelper(BlockOffsetDirection.DOWN))
                .addHelper(new ConnectHorizontalHelper(true, true, false, false, true, modelName + "_connection", modelName + "_item", BlockConnectionType.SAME, shapes));
    }
    public MetroBlockDecorativeBuilder connectingToggleBlock(BlockBehaviour.Properties props, String tag, String modelName, VoxelShape[] shapes, Item toggleItem) {
        return new MetroBlockDecorativeBuilder(props)
                .setModelDirectory("blocks/decorative/")
                .setTag(tag)
                .setModelName(modelName)
                .addHelper(new OffsetHelper(BlockOffsetDirection.DOWN))
                .addHelper(new ConnectHorizontalHelper(true, true, false, false, true, modelName + "_connection", modelName + "_item", BlockConnectionType.SAME, shapes))
                .addHelper(new ToggleHelper(false, Block.box(0, 0, 0, 16, 16, 16), Block.box(0, 0, 0, 16, 16, 16), modelName + "_toggled", toggleItem));
    }

    @Override
    public void init(IEventBus modEventBus) {
        //region Bollards
        //Concrete
        _blockNames.add(registerBlockSet(BOLLARD_CONCRETE_PILLAR_1,             () -> genericBlock(_props, "bollard_concrete_pillar", 4, 16).setTextures(TEX_CONC_1).build()));
        _blockNames.add(registerBlockSet(BOLLARD_CONCRETE_PILLAR_2,             () -> genericBlock(_props, "bollard_concrete_pillar", 4, 16).setTextures(TEX_CONC_2).build()));
        _blockNames.add(registerBlockSet(BOLLARD_CONCRETE_PILLAR_DOUBLE_1,      () -> genericBlock(_props, "bollard_concrete_pillar_double", 5, 16).setTextures(TEX_CONC_1).build()));
        _blockNames.add(registerBlockSet(BOLLARD_CONCRETE_PILLAR_DOUBLE_2,      () -> genericBlock(_props, "bollard_concrete_pillar_double", 5, 16).setTextures(TEX_CONC_2).build()));
        _blockNames.add(registerBlockSet(BOLLARD_CONCRETE_ROUND_1,              () -> genericBlock(_props, "bollard_concrete_round", 4, 16).setTextures(TEX_CONC_1).build()));
        _blockNames.add(registerBlockSet(BOLLARD_CONCRETE_ROUND_2,              () -> genericBlock(_props, "bollard_concrete_round", 4, 16).setTextures(TEX_CONC_2).build()));
        _blockNames.add(registerBlockSet(BOLLARD_CONCRETE_ROUND_STUBBY_1,       () -> genericBlock(_props, "bollard_concrete_round_stubby", 4, 9).setTextures(TEX_CONC_1).build()));
        _blockNames.add(registerBlockSet(BOLLARD_CONCRETE_ROUND_STUBBY_2,       () -> genericBlock(_props, "bollard_concrete_round_stubby", 4, 9).setTextures(TEX_CONC_2).build()));
        _blockNames.add(registerBlockSet(BOLLARD_CONCRETE_SQUARE_1,             () -> genericBlock(_props, "bollard_concrete_square", 4, 16).setTextures(TEX_CONC_1).build()));
        _blockNames.add(registerBlockSet(BOLLARD_CONCRETE_SQUARE_2,             () -> genericBlock(_props, "bollard_concrete_square", 4, 16).setTextures(TEX_CONC_2).build()));
        _blockNames.add(registerBlockSet(BOLLARD_CONCRETE_SQUARE_STUBBY_1,      () -> genericBlock(_props, "bollard_concrete_square_stubby", 4, 9).setTextures(TEX_CONC_1).build()));
        _blockNames.add(registerBlockSet(BOLLARD_CONCRETE_SQUARE_STUBBY_2,      () -> genericBlock(_props, "bollard_concrete_square_stubby", 4, 9).setTextures(TEX_CONC_2).build()));
        _blockNames.add(registerBlockSet(BOLLARD_CONCRETE_SQUARE_STUBBY_RING_1, () -> genericBlock(_props, "bollard_concrete_square_stubby_ring", 4, 10).setTextures(TEX_CONC_1).build()));
        _blockNames.add(registerBlockSet(BOLLARD_CONCRETE_SQUARE_STUBBY_RING_2, () -> genericBlock(_props, "bollard_concrete_square_stubby_ring", 4, 10).setTextures(TEX_CONC_2).build()));

        //Standard
        _blockNames.add(registerBlockSet(BOLLARD_ROUND_RINGED_BLACK,        () -> genericBlock(_props, "bollard_round_ringed", 4, 16).setTextures(texture("texture", TEX_BLACK), texture("accent", TEX_GOLD)).build()));
        _blockNames.add(registerBlockSet(BOLLARD_ROUND_RINGED_DARK_METAL,   () -> genericBlock(_props, "bollard_round_ringed", 4, 16).setTextures(texture("texture", TEX_METAL_DARK), texture("accent", TEX_GOLD)).build()));
        _blockNames.add(registerBlockSet(BOLLARD_ROUND_RINGED_LIGHT_METAL,  () -> genericBlock(_props, "bollard_round_ringed", 4, 16).setTextures(texture("texture", TEX_METAL_LIGHT), texture("accent", TEX_GOLD)).build()));
        _blockNames.add(registerBlockSet(BOLLARD_ROUND_RINGED_YELLOW,       () -> genericBlock(_props, "bollard_round_ringed", 4, 16).setTextures(texture("texture", TEX_YELLOW), texture("accent", TEX_YELLOW)).build()));
        _blockNames.add(registerBlockSet(BOLLARD_ROUND_RINGED_WHITE,        () -> genericBlock(_props, "bollard_round_ringed", 4, 16).setTextures(texture("texture", TEX_WHITE), texture("accent", TEX_GOLD)).build()));
        _blockNames.add(registerBlockSet(BOLLARD_BLACK,                     () -> genericBlock(_props, "bollard", 3, 16).setTextures(TEX_BLACK).build()));
        _blockNames.add(registerBlockSet(BOLLARD_METAL_DARK,                () -> genericBlock(_props, "bollard", 3, 16).setTextures(TEX_METAL_DARK).build()));
        _blockNames.add(registerBlockSet(BOLLARD_METAL_LIGHT,               () -> genericBlock(_props, "bollard", 3, 16).setTextures(TEX_METAL_LIGHT).build()));
        _blockNames.add(registerBlockSet(BOLLARD_YELLOW,                    () -> genericBlock(_props, "bollard", 3, 16).setTextures(TEX_YELLOW).build()));
        _blockNames.add(registerBlockSet(BOLLARD_WHITE,                     () -> genericBlock(_props, "bollard", 3, 16).setTextures(TEX_WHITE).build()));
        _blockNames.add(registerBlockSet(BOLLARD_THIN_BLACK,                () -> genericBlock(_props, "bollard_thin", 2, 16).setTextures(TEX_BLACK).build()));
        _blockNames.add(registerBlockSet(BOLLARD_THIN_METAL_DARK,           () -> genericBlock(_props, "bollard_thin", 2, 16).setTextures(TEX_METAL_DARK).build()));
        _blockNames.add(registerBlockSet(BOLLARD_THIN_METAL_LIGHT,          () -> genericBlock(_props, "bollard_thin", 2, 16).setTextures(TEX_METAL_LIGHT).build()));
        _blockNames.add(registerBlockSet(BOLLARD_THIN_YELLOW,               () -> genericBlock(_props, "bollard_thin", 2, 16).setTextures(TEX_YELLOW).build()));
        _blockNames.add(registerBlockSet(BOLLARD_THIN_WHITE,                () -> genericBlock(_props, "bollard_thin", 2, 16).setTextures(TEX_WHITE).build()));
        _blockNames.add(registerBlockSet(BOLLARD_TOPPED_BLACK,              () -> genericBlock(_props, "bollard_topped", 3, 16).setTextures(TEX_BLACK).build()));
        _blockNames.add(registerBlockSet(BOLLARD_TOPPED_METAL_DARK,         () -> genericBlock(_props, "bollard_topped", 3, 16).setTextures(TEX_METAL_DARK).build()));
        _blockNames.add(registerBlockSet(BOLLARD_TOPPED_METAL_LIGHT,        () -> genericBlock(_props, "bollard_topped", 3, 16).setTextures(TEX_METAL_LIGHT).build()));
        _blockNames.add(registerBlockSet(BOLLARD_TOPPED_YELLOW,             () -> genericBlock(_props, "bollard_topped", 3, 16).setTextures(TEX_YELLOW).build()));
        _blockNames.add(registerBlockSet(BOLLARD_TOPPED_WHITE,              () -> genericBlock(_props, "bollard_topped", 3, 16).setTextures(TEX_WHITE).build()));
        _blockNames.add(registerBlockSet(BOLLARD_TOPPED_RINGED_BLACK,       () -> genericBlock(_props, "bollard_topped_ringed", 3, 16).setTextures(texture("texture", TEX_BLACK), texture("ring", TEX_GOLD)).build()));
        _blockNames.add(registerBlockSet(BOLLARD_TOPPED_RINGED_METAL_DARK,  () -> genericBlock(_props, "bollard_topped_ringed", 3, 16).setTextures(texture("texture", TEX_METAL_DARK), texture("ring", TEX_GOLD)).build()));
        _blockNames.add(registerBlockSet(BOLLARD_TOPPED_RINGED_METAL_LIGHT, () -> genericBlock(_props, "bollard_topped_ringed", 3, 16).setTextures(texture("texture", TEX_METAL_LIGHT), texture("ring", TEX_GOLD)).build()));
        _blockNames.add(registerBlockSet(BOLLARD_TOPPED_RINGED_YELLOW,      () -> genericBlock(_props, "bollard_topped_ringed", 3, 16).setTextures(texture("texture", TEX_YELLOW), texture("ring", TEX_YELLOW)).build()));
        _blockNames.add(registerBlockSet(BOLLARD_TOPPED_RINGED_WHITE,       () -> genericBlock(_props, "bollard_topped_ringed", 3, 16).setTextures(texture("texture", TEX_WHITE), texture("ring", TEX_GOLD)).build()));

        //Rotatable
        Vec3 smallFullHeight = new Vec3(2, 16, 2);
        _blockNames.add(registerBlockSet(BOLLARD_SLANT_SQUARE_BLACK, () -> rotateableBlock(_props, "bollard_slant_square", smallFullHeight).setTextures(TEX_BLACK).build()));
        _blockNames.add(registerBlockSet(BOLLARD_SLANT_SQUARE_DARK_METAL, () -> rotateableBlock(_props, "bollard_slant_square", smallFullHeight).setTextures(TEX_METAL_DARK).build()));
        _blockNames.add(registerBlockSet(BOLLARD_SLANT_SQUARE_LIGHT_METAL, () -> rotateableBlock(_props, "bollard_slant_square", smallFullHeight).setTextures(TEX_METAL_LIGHT).build()));
        _blockNames.add(registerBlockSet(BOLLARD_SLANT_SQUARE_YELLOW, () -> rotateableBlock(_props, "bollard_slant_square", smallFullHeight).setTextures(TEX_YELLOW).build()));
        _blockNames.add(registerBlockSet(BOLLARD_SLANT_SQUARE_WHITE, () -> rotateableBlock(_props, "bollard_slant_square", smallFullHeight).setTextures(TEX_WHITE).build()));
        _blockNames.add(registerBlockSet(BOLLARD_SLANT_ROUND_BLACK, () -> rotateableBlock(_props, "bollard_slant_round", smallFullHeight).setTextures(TEX_BLACK).build()));
        _blockNames.add(registerBlockSet(BOLLARD_SLANT_ROUND_DARK_METAL, () -> rotateableBlock(_props, "bollard_slant_round", smallFullHeight).setTextures(TEX_METAL_DARK).build()));
        _blockNames.add(registerBlockSet(BOLLARD_SLANT_ROUND_LIGHT_METAL, () -> rotateableBlock(_props, "bollard_slant_round", smallFullHeight).setTextures(TEX_METAL_LIGHT).build()));
        _blockNames.add(registerBlockSet(BOLLARD_SLANT_ROUND_YELLOW, () -> rotateableBlock(_props, "bollard_slant_round", smallFullHeight).setTextures(TEX_YELLOW).build()));
        _blockNames.add(registerBlockSet(BOLLARD_SLANT_ROUND_WHITE, () -> rotateableBlock(_props, "bollard_slant_round", smallFullHeight).setTextures(TEX_WHITE).build()));

        //Illuminated
        _blockNames.add(registerBlockSet(BOLLARD_LIGHT_1_CONCRETE_1,  () -> genericBlock(_propsLit, "bollard_light_1", 3, 16).setTextures(TEX_CONC_1).build()));
        _blockNames.add(registerBlockSet(BOLLARD_LIGHT_1_CONCRETE_2,  () -> genericBlock(_propsLit, "bollard_light_1", 3, 16).setTextures(TEX_CONC_2).build()));
        _blockNames.add(registerBlockSet(BOLLARD_LIGHT_1_BLACK,       () -> genericBlock(_propsLit, "bollard_light_1", 3, 16).setTextures(TEX_BLACK).build()));
        _blockNames.add(registerBlockSet(BOLLARD_LIGHT_1_DARK_METAL,  () -> genericBlock(_propsLit, "bollard_light_1", 3, 16).setTextures(TEX_METAL_DARK).build()));
        _blockNames.add(registerBlockSet(BOLLARD_LIGHT_1_LIGHT_METAL, () -> genericBlock(_propsLit, "bollard_light_1", 3, 16).setTextures(TEX_METAL_LIGHT).build()));
        _blockNames.add(registerBlockSet(BOLLARD_LIGHT_1_YELLOW,      () -> genericBlock(_propsLit, "bollard_light_1", 3, 16).setTextures(TEX_YELLOW).build()));
        _blockNames.add(registerBlockSet(BOLLARD_LIGHT_1_WHITE,       () -> genericBlock(_propsLit, "bollard_light_1", 3, 16).setTextures(TEX_WHITE).build()));

        //Rotatable Illuminated
        _blockNames.add(registerBlockSet(BOLLARD_THICK_LEFT, () -> rotateableBlock(_propsLit, "bollard_traffic_1", new Vec3(6, 16, 6)).setTextures(getLoc("traffic_bollard_thick_left")).build()));
        _blockNames.add(registerBlockSet(BOLLARD_THICK_RIGHT, () -> rotateableBlock(_propsLit, "bollard_traffic_1", new Vec3(6, 16, 6)).setTextures(getLoc("traffic_bollard_thick_right")).build()));
        _blockNames.add(registerBlockSet(BOLLARD_THIN_LEFT, () -> rotateableBlock(_propsLit, "bollard_traffic_2", new Vec3(6, 16, 2)).setTextures(getLoc("traffic_bollard_thin_left")).build()));
        _blockNames.add(registerBlockSet(BOLLARD_THIN_RIGHT, () -> rotateableBlock(_propsLit, "bollard_traffic_2", new Vec3(6, 16, 2)).setTextures(getLoc("traffic_bollard_thin_right")).build()));

        //Reflective
        _blockNames.add(registerBlockSet(BOLLARD_PORTABLE_ORANGE, () -> genericBlock(_props, "bollard_portable", 2, 16).setTextures(texture("texture", "bollard_orange"), texture("reflection", "reflective_surface")).build()));
        _blockNames.add(registerBlockSet(BOLLARD_PORTABLE_YELLOW, () -> genericBlock(_props, "bollard_portable", 2, 16).setTextures(texture("texture", TEX_YELLOW), texture("reflection", "reflective_surface")).build()));

        //Foldable
        _blockNames.add(registerBlockSet(BOLLARD_FOLDING_THIN_BLACK, () ->  foldingRedstoneToggle("bollard_folding_1", ShapeUtils.makeShape(2, 16), Block.box(7, 0, 0, 9, 2, 9)).setTextures(TEX_BLACK).build()));
        _blockNames.add(registerBlockSet(BOLLARD_FOLDING_THIN_DARK_METAL, () ->  foldingRedstoneToggle("bollard_folding_1", ShapeUtils.makeShape(2, 16), Block.box(7, 0, 0, 9, 2, 9)).setTextures(TEX_METAL_DARK).build()));
        _blockNames.add(registerBlockSet(BOLLARD_FOLDING_THIN_LIGHT_METAL, () ->  foldingRedstoneToggle("bollard_folding_1", ShapeUtils.makeShape(2, 16), Block.box(7, 0, 0, 9, 2, 9)).setTextures(TEX_METAL_LIGHT).build()));
        _blockNames.add(registerBlockSet(BOLLARD_FOLDING_THIN_YELLOW, () ->  foldingRedstoneToggle("bollard_folding_1", ShapeUtils.makeShape(2, 16), Block.box(7, 0, 0, 9, 2, 9)).setTextures(TEX_YELLOW).build()));
        _blockNames.add(registerBlockSet(BOLLARD_FOLDING_THIN_WHITE, () ->  foldingRedstoneToggle("bollard_folding_1", ShapeUtils.makeShape(2, 16), Block.box(7, 0, 0, 9, 2, 9)).setTextures(TEX_WHITE).build()));

        //Retracting
        _blockNames.add(registerBlockSet(BOLLARD_RETRACTING_THICK_BLACK, () -> bollardRedstoneToggle("bollard_retract_thick", 4, 16).setTextures(TEX_BLACK).build()));
        _blockNames.add(registerBlockSet(BOLLARD_RETRACTING_THICK_DARK_METAL, () -> bollardRedstoneToggle("bollard_retract_thick", 4, 16).setTextures(TEX_METAL_DARK).build()));
        _blockNames.add(registerBlockSet(BOLLARD_RETRACTING_THICK_LIGHT_METAL, () -> bollardRedstoneToggle("bollard_retract_thick", 4, 16).setTextures(TEX_METAL_LIGHT).build()));
        _blockNames.add(registerBlockSet(BOLLARD_RETRACTING_THICK_YELLOW, () -> bollardRedstoneToggle("bollard_retract_thick", 4, 16).setTextures(TEX_YELLOW).build()));
        _blockNames.add(registerBlockSet(BOLLARD_RETRACTING_THICK_WHITE, () -> bollardRedstoneToggle("bollard_retract_thick", 4, 16).setTextures(TEX_WHITE).build()));

        _blockNames.add(registerBlockSet(BOLLARD_RETRACTING_THIN_BLACK, () -> bollardRedstoneToggle("bollard_retract_thin", 3, 16).setTextures(TEX_BLACK).build()));
        _blockNames.add(registerBlockSet(BOLLARD_RETRACTING_THIN_DARK_METAL, () -> bollardRedstoneToggle("bollard_retract_thin", 3, 16).setTextures(TEX_METAL_DARK).build()));
        _blockNames.add(registerBlockSet(BOLLARD_RETRACTING_THIN_LIGHT_METAL, () -> bollardRedstoneToggle("bollard_retract_thin", 3, 16).setTextures(TEX_METAL_LIGHT).build()));
        _blockNames.add(registerBlockSet(BOLLARD_RETRACTING_THIN_YELLOW, () -> bollardRedstoneToggle("bollard_retract_thin", 3, 16).setTextures(TEX_YELLOW).build()));
        _blockNames.add(registerBlockSet(BOLLARD_RETRACTING_THIN_WHITE, () -> bollardRedstoneToggle("bollard_retract_thin", 3, 16).setTextures(TEX_WHITE).build()));

        _blockNames.add(registerBlockSet(BOLLARD_RETRACTING_STUMPY_BLACK, () -> bollardRedstoneToggle("bollard_retract_stumpy", 5.5f, 13).setTextures(TEX_BLACK).build()));
        _blockNames.add(registerBlockSet(BOLLARD_RETRACTING_STUMPY_DARK_METAL, () -> bollardRedstoneToggle("bollard_retract_stumpy", 5.5f, 13).setTextures(TEX_METAL_DARK).build()));
        _blockNames.add(registerBlockSet(BOLLARD_RETRACTING_STUMPY_LIGHT_METAL, () -> bollardRedstoneToggle("bollard_retract_stumpy", 5.5f, 13).setTextures(TEX_METAL_LIGHT).build()));
        _blockNames.add(registerBlockSet(BOLLARD_RETRACTING_STUMPY_YELLOW, () -> bollardRedstoneToggle("bollard_retract_stumpy", 5.5f, 13).setTextures(TEX_YELLOW).build()));
        _blockNames.add(registerBlockSet(BOLLARD_RETRACTING_STUMPY_WHITE, () -> bollardRedstoneToggle("bollard_retract_stumpy", 5.5f, 13).setTextures(TEX_WHITE).build()));

        _blockNames.add(registerBlockSet(BOLLARD_RETRACTING_ROUND_BLACK, () -> bollardRedstoneToggle("bollard_retract_round_ringed", 5, 16).setTextures(TEX_BLACK).build()));
        _blockNames.add(registerBlockSet(BOLLARD_RETRACTING_ROUND_DARK_METAL, () -> bollardRedstoneToggle("bollard_retract_round_ringed", 5, 16).setTextures(TEX_METAL_DARK).build()));
        _blockNames.add(registerBlockSet(BOLLARD_RETRACTING_ROUND_LIGHT_METAL, () -> bollardRedstoneToggle("bollard_retract_round_ringed", 5, 16).setTextures(TEX_METAL_LIGHT).build()));
        _blockNames.add(registerBlockSet(BOLLARD_RETRACTING_ROUND_YELLOW, () -> bollardRedstoneToggle("bollard_retract_round_ringed", 5, 16).setTextures(TEX_YELLOW).build()));
        _blockNames.add(registerBlockSet(BOLLARD_RETRACTING_ROUND_WHITE, () -> bollardRedstoneToggle("bollard_retract_round_ringed", 5, 16).setTextures(TEX_WHITE).build()));
        //endregion

        VoxelShape[] wall_shape = ShapeUtils.combineMultiShapes(ShapeUtils.makeShapes(4f, 4f), ShapeUtils.makeShapes(2.5f, 4f, 16f));
        VoxelShape[] barrier_shape = ShapeUtils.makeShapes(2f, 16f);


        _blockNames.add(registerBlockSet(WALL_CONCRETE_CENTER_SOLID_1, () -> connectingBlock(_props, "roads_wall", "barrier_concrete_middle", wall_shape).setTextures(TEX_CONC_1).build()));
        _blockNames.add(registerBlockSet(WALL_CONCRETE_CENTER_SOLID_2, () -> connectingBlock(_props, "roads_wall", "barrier_concrete_middle", wall_shape).setTextures(TEX_CONC_2).build()));

        //TODO connection + toggle together dont work yet
        /*_blockNames.add(registerBlockSet(BARRIER_METAL_CENTER_SHORT, () -> standard_barrier
                .setNoToggledConnectionNames("barrier_metal_center_short", "barrier_metal_center_short_connection", "barrier_metal_center_post")
                .setTextures(texture("texture", getLoc("barrier_metal")), texture("accent", getLoc("barrier_metal_dark"))).setCenterFourSided(true, false).setIndependentModelsPerSide().build()));
        _blockNames.add(registerBlockSet(BARRIER_METAL_CENTER_TALL, () -> standard_barrier
                .setNoToggledConnectionNames("barrier_metal_center_tall", "barrier_metal_center_tall_connection", "barrier_metal_center_post")
                .setTextures(texture("texture", getLoc("barrier_metal")), texture("accent", getLoc("barrier_metal_dark"))).setCenterFourSided(true, false).setIndependentModelsPerSide().build()));*/

        _blockNames.add(registerBlockSet(WALL_CONCRETE_CENTER_THIN_1, () -> connectingToggleBlock(_props, "roads_barrier_mid", "barrier_wall_mid", barrier_shape, Items.DIAMOND).setTextures(TEX_CONC_1).build()));
        _blockNames.add(registerBlockSet(WALL_CONCRETE_CENTER_THIN_2, () -> connectingToggleBlock(_props, "roads_barrier_mid", "barrier_wall_mid", barrier_shape, Items.DIAMOND).setTextures(TEX_CONC_2).build()));
        _blockNames.add(registerBlockSet(WALL_CONCRETE_CENTER_THIN_3, () -> connectingToggleBlock(_props, "roads_barrier_mid", "barrier_wall_mid", barrier_shape, Items.DIAMOND).setTextures(TEX_CONC_2).build()));
        _blockNames.add(registerBlockSet(WALL_CONCRETE_CENTER_THIN_4, () -> connectingToggleBlock(_props, "roads_barrier_mid", "barrier_wall_mid", barrier_shape, Items.DIAMOND).setTextures(TEX_CONC_2).build()));

        _blockNames.add(registerBlockSet(BARRIER_BARS_CENTER_1, () -> connectingToggleBlock(_props, "roads_barrier_mid", "barrier_wall_mid", barrier_shape, Items.DIAMOND).setTextures(TEX_CONC_2).build()));
        _blockNames.add(registerBlockSet(BARRIER_BARS_CENTER_2, () -> connectingToggleBlock(_props, "roads_barrier_mid", "barrier_wall_mid", barrier_shape, Items.DIAMOND).setTextures(TEX_CONC_2).build()));
        _blockNames.add(registerBlockSet(BARRIER_BARS_CENTER_3, () -> connectingToggleBlock(_props, "roads_barrier_mid", "barrier_wall_mid", barrier_shape, Items.DIAMOND).setTextures(TEX_CONC_2).build()));
        _blockNames.add(registerBlockSet(BARRIER_BARS_CENTER_4, () -> connectingToggleBlock(_props, "roads_barrier_mid", "barrier_wall_mid", barrier_shape, Items.DIAMOND).setTextures(TEX_CONC_2).build()));
        _blockNames.add(registerBlockSet(BARRIER_BARS_CENTER_5, () -> connectingToggleBlock(_props, "roads_barrier_mid", "barrier_wall_mid", barrier_shape, Items.DIAMOND).setTextures(TEX_CONC_2).build()));

        _decorativeTab = new CreativeTabSet(registration.getCreativeTabDeferredRegister(),"tab_decorative", getItem(BOLLARD_ROUND_RINGED_DARK_METAL));
    }

    @Override
    public void generateCreativeTabs() {
        for (int i = 0; i < _blockNames.size(); i++) {
            _decorativeTab.addItem(getItem(_blockNames.get(i)).get().getDefaultInstance());
        }
    }

    @Override
    public ArrayList<CreativeTabSet> getCreativeTabs() {
        ArrayList<CreativeTabSet> tabList = new ArrayList<>();
        tabList.add(_decorativeTab);
        return tabList;
    }

    @Override
    protected ResourceLocation getLoc(String name) {
        return new ResourceLocation(FurenikusRoads.MODID, "block/decorative/" + name);
    }
}
