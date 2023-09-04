package com.silvaniastudios.roads.blocks;

import java.awt.Color;
import java.util.ArrayList;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.decorative.BarrierBlock;
import com.silvaniastudios.roads.blocks.decorative.BarrierConcreteEdgeBlock;
import com.silvaniastudios.roads.blocks.decorative.BarrierEdgeBlock;
import com.silvaniastudios.roads.blocks.decorative.BarrierEndBlock;
import com.silvaniastudios.roads.blocks.decorative.BarrierLowEdgeBlock;
import com.silvaniastudios.roads.blocks.decorative.BarsBarrierBlock;
import com.silvaniastudios.roads.blocks.decorative.BollardBlock;
import com.silvaniastudios.roads.blocks.decorative.CatsEyeBlock;
import com.silvaniastudios.roads.blocks.decorative.CatsEyeBlockFourWay;
import com.silvaniastudios.roads.blocks.decorative.ConcreteBarrierBlock;
import com.silvaniastudios.roads.blocks.decorative.CurbBlock;
import com.silvaniastudios.roads.blocks.decorative.FoldingBollardBlock;
import com.silvaniastudios.roads.blocks.decorative.MetalPost;
import com.silvaniastudios.roads.blocks.decorative.RetractableBollardBlock;
import com.silvaniastudios.roads.blocks.decorative.SpeedBumpBlock;
import com.silvaniastudios.roads.blocks.decorative.StandardBollardBlock;
import com.silvaniastudios.roads.blocks.decorative.StreetLight;
import com.silvaniastudios.roads.blocks.decorative.WheelStopBlock;
import com.silvaniastudios.roads.blocks.diagonal.RoadBlockDiagonal;
import com.silvaniastudios.roads.blocks.paint.*;
import com.silvaniastudios.roads.blocks.paint.customs.*;
import com.silvaniastudios.roads.blocks.paint.properties.PaintGrid;
import com.silvaniastudios.roads.blocks.paint.uniques.ChevronIconPaintBlock;
import com.silvaniastudios.roads.blocks.paint.uniques.HatchBoxPaintBlock;
import com.silvaniastudios.roads.blocks.tileentities.compactor.CompactorBlock;
import com.silvaniastudios.roads.blocks.tileentities.compactor.CompactorElectricEntity;
import com.silvaniastudios.roads.blocks.tileentities.compactor.CompactorEntity;
import com.silvaniastudios.roads.blocks.tileentities.crusher.CrusherBlock;
import com.silvaniastudios.roads.blocks.tileentities.crusher.CrusherElectricEntity;
import com.silvaniastudios.roads.blocks.tileentities.crusher.CrusherEntity;
import com.silvaniastudios.roads.blocks.tileentities.distiller.TarDistillerBlock;
import com.silvaniastudios.roads.blocks.tileentities.distiller.TarDistillerElectricEntity;
import com.silvaniastudios.roads.blocks.tileentities.distiller.TarDistillerEntity;
import com.silvaniastudios.roads.blocks.tileentities.fabricator.FabricatorBlock;
import com.silvaniastudios.roads.blocks.tileentities.fabricator.FabricatorElectricEntity;
import com.silvaniastudios.roads.blocks.tileentities.fabricator.FabricatorEntity;
import com.silvaniastudios.roads.blocks.tileentities.paintfiller.PaintFillerBlock;
import com.silvaniastudios.roads.blocks.tileentities.paintfiller.PaintFillerElectricEntity;
import com.silvaniastudios.roads.blocks.tileentities.paintfiller.PaintFillerEntity;
import com.silvaniastudios.roads.blocks.tileentities.paintfiller.hopper.PaintFillerHopperBlock;
import com.silvaniastudios.roads.blocks.tileentities.paintfiller.hopper.PaintFillerHopperEntity;
import com.silvaniastudios.roads.blocks.tileentities.paintoven.PaintOvenBlock;
import com.silvaniastudios.roads.blocks.tileentities.paintoven.PaintOvenElectricEntity;
import com.silvaniastudios.roads.blocks.tileentities.paintoven.PaintOvenEntity;
import com.silvaniastudios.roads.blocks.tileentities.roadfactory.RoadFactoryBlock;
import com.silvaniastudios.roads.blocks.tileentities.roadfactory.RoadFactoryElectricEntity;
import com.silvaniastudios.roads.blocks.tileentities.roadfactory.RoadFactoryEntity;
import com.silvaniastudios.roads.blocks.tileentities.tarmaccutter.TarmacCutterBlock;
import com.silvaniastudios.roads.blocks.tileentities.tarmaccutter.TarmacCutterElectricEntity;
import com.silvaniastudios.roads.blocks.tileentities.tarmaccutter.TarmacCutterEntity;
import com.silvaniastudios.roads.fluids.FRFluids;
import com.silvaniastudios.roads.items.FRItems;
import com.silvaniastudios.roads.items.RoadItemBlock;
import com.silvaniastudios.roads.registries.CustomPaintModelRegistry;
import com.silvaniastudios.roads.registries.DynamicBlockRegistry;
import com.silvaniastudios.roads.registries.PaintGunItemRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public class FRBlocks {
	
	public static ArrayList<PaintColour> col = new ArrayList<>();

	static {
		col.add(new PaintColour("white", 0, 0, TextFormatting.WHITE));
		col.add(new PaintColour("yellow", Color.YELLOW.getRGB(), 1, TextFormatting.YELLOW));
		col.add(new PaintColour("red", Color.RED.getRGB(), 2, TextFormatting.RED));
	}

	private static int colourCount = 3;

	public static PaintColour addNewColour(String name, int colour, TextFormatting formatting) {
		FurenikusRoads.debug(0, "Adding colour " + name);
		PaintColour paintColour = new PaintColour(name, colour, colourCount, formatting);
		col.add(paintColour);
		colourCount++;
		return paintColour;
	}

	public static ArrayList<RoadBlock> roadBlockList = new ArrayList<RoadBlock>();
	public static ArrayList<RoadBlockDiagonal> roadBlockDiagonalList = new ArrayList<RoadBlockDiagonal>();
	public static ArrayList<PaintBlockBase> paintBlockList = new ArrayList<PaintBlockBase>();
	public static ArrayList<BlockBase> catsEyeList = new ArrayList<BlockBase>();
	public static ArrayList<BlockBase> genericList = new ArrayList<BlockBase>();

	public static ArrayList<PaintGrid> genTexturesList = new ArrayList<PaintGrid>(); //For internal/temporary use only, generating textures from grids.

	//Tarmac variants
	public static RoadBlock road_block_standard = new RoadBlock("road_block_standard", Material.ROCK, FRItems.tarmac_fragment_standard);
	public static RoadBlock road_block_concrete_1 = new RoadBlock("road_block_concrete_1", Material.ROCK, FRItems.tarmac_fragment_concrete_1);
	public static RoadBlock road_block_concrete_2 = new RoadBlock("road_block_concrete_2", Material.ROCK, FRItems.tarmac_fragment_concrete_2);
	public static RoadBlock road_block_light = new RoadBlock("road_block_light", Material.ROCK, FRItems.tarmac_fragment_light);
	public static RoadBlock road_block_fine = new RoadBlock("road_block_fine", Material.ROCK, FRItems.tarmac_fragment_fine);
	public static RoadBlock road_block_dark = new RoadBlock("road_block_dark", Material.ROCK, FRItems.tarmac_fragment_dark);
	public static RoadBlock road_block_pale = new RoadBlock("road_block_pale", Material.ROCK, FRItems.tarmac_fragment_pale);
	public static RoadBlock road_block_red = new RoadBlock("road_block_red", Material.ROCK, FRItems.tarmac_fragment_red);
	public static RoadBlock road_block_blue = new RoadBlock("road_block_blue", Material.ROCK, FRItems.tarmac_fragment_blue);
	public static RoadBlock road_block_white = new RoadBlock("road_block_white", Material.ROCK, FRItems.tarmac_fragment_white);
	public static RoadBlock road_block_yellow = new RoadBlock("road_block_yellow", Material.ROCK, FRItems.tarmac_fragment_yellow);
	public static RoadBlock road_block_green = new RoadBlock("road_block_green", Material.ROCK, FRItems.tarmac_fragment_green);
	public static RoadBlock road_block_muddy = new RoadBlock("road_block_muddy", Material.GROUND, FRItems.tarmac_fragment_muddy);
	public static RoadBlock road_block_muddy_dried = new RoadBlock("road_block_muddy_dried", Material.GROUND, FRItems.tarmac_fragment_muddy);

	public static RoadBlock road_block_stone = new RoadBlock("road_block_stone", Material.ROCK, FRItems.tarmac_fragment_stone);
	public static RoadBlock road_block_grass = new RoadBlock("road_block_grass", Material.GRASS, FRItems.tarmac_fragment_grass);
	public static RoadBlock road_block_dirt = new RoadBlock("road_block_dirt", Material.GROUND, FRItems.tarmac_fragment_dirt);
	public static RoadBlock road_block_gravel = new RoadBlock("road_block_gravel", Material.SAND, FRItems.tarmac_fragment_gravel);
	public static RoadBlock road_block_sand = new RoadBlock("road_block_sand", Material.SAND, FRItems.tarmac_fragment_sand);

	public static RoadBlockDiagonal road_block_diagonal_1_1 = new RoadBlockDiagonal("road_block_diagonal_1_1", false, "diagonal_11", 0, 1);
	public static RoadBlockDiagonal road_block_diagonal_1_2 = new RoadBlockDiagonal("road_block_diagonal_1_2", false, "diagonal_12", 0, 0.5f);
	public static RoadBlockDiagonal road_block_diagonal_1_4 = new RoadBlockDiagonal("road_block_diagonal_1_4", false, "diagonal_14", 0, 0.25f);
	public static RoadBlockDiagonal road_block_diagonal_2_4 = new RoadBlockDiagonal("road_block_diagonal_2_4", false, "diagonal_24", 0.25f, 0.5f);
	public static RoadBlockDiagonal road_block_diagonal_1_8 = new RoadBlockDiagonal("road_block_diagonal_1_8", false, "diagonal_18", 0, 0.125f);
	public static RoadBlockDiagonal road_block_diagonal_2_8 = new RoadBlockDiagonal("road_block_diagonal_2_8", false, "diagonal_28", 0.125f, 0.25f);
	public static RoadBlockDiagonal road_block_diagonal_3_8 = new RoadBlockDiagonal("road_block_diagonal_3_8", false, "diagonal_38", 0.25f, 0.375f);
	public static RoadBlockDiagonal road_block_diagonal_4_8 = new RoadBlockDiagonal("road_block_diagonal_4_8", false, "diagonal_48", 0.375f, 0.5f);

	public static RoadBlockDiagonal road_block_diagonal_1_1_mirror = new RoadBlockDiagonal("road_block_diagonal_1_1_mirror", true, "diagonal_11_mirror", 0, 1);
	public static RoadBlockDiagonal road_block_diagonal_1_2_mirror = new RoadBlockDiagonal("road_block_diagonal_1_2_mirror", true, "diagonal_12_mirror", 0, 0.5f);
	public static RoadBlockDiagonal road_block_diagonal_1_4_mirror = new RoadBlockDiagonal("road_block_diagonal_1_4_mirror", true, "diagonal_14_mirror", 0, 0.25f);
	public static RoadBlockDiagonal road_block_diagonal_2_4_mirror = new RoadBlockDiagonal("road_block_diagonal_2_4_mirror", true, "diagonal_24_mirror", 0.25f, 0.5f);
	public static RoadBlockDiagonal road_block_diagonal_1_8_mirror = new RoadBlockDiagonal("road_block_diagonal_1_8_mirror", true, "diagonal_18_mirror", 0, 0.125f);
	public static RoadBlockDiagonal road_block_diagonal_2_8_mirror = new RoadBlockDiagonal("road_block_diagonal_2_8_mirror", true, "diagonal_28_mirror", 0.125f, 0.25f);
	public static RoadBlockDiagonal road_block_diagonal_3_8_mirror = new RoadBlockDiagonal("road_block_diagonal_3_8_mirror", true, "diagonal_38_mirror", 0.25f, 0.375f);
	public static RoadBlockDiagonal road_block_diagonal_4_8_mirror = new RoadBlockDiagonal("road_block_diagonal_4_8_mirror", true, "diagonal_48_mirror", 0.375f, 0.5f);

	public static CurbBlock kerb_standard = new CurbBlock("kerb_standard", Material.ROCK);

	public static PaintFillerBlock paint_filler = new PaintFillerBlock("paint_filler", false);
	public static TarDistillerBlock tar_distiller = new TarDistillerBlock("tar_distiller", false);
	public static RoadFactoryBlock road_factory = new RoadFactoryBlock("road_factory", false);
	public static TarmacCutterBlock tarmac_cutter = new TarmacCutterBlock("tarmac_cutter", false);
	public static CrusherBlock crusher = new CrusherBlock("crusher", false);
	public static PaintOvenBlock paint_oven = new PaintOvenBlock("paint_oven", false);
	public static CompactorBlock compactor = new CompactorBlock("compactor", false);
	public static FabricatorBlock fabricator = new FabricatorBlock("fabricator", false);

	public static PaintFillerBlock paint_filler_electric = new PaintFillerBlock("paint_filler_electric", true);
	public static TarDistillerBlock tar_distiller_electric = new TarDistillerBlock("tar_distiller_electric", true);
	public static RoadFactoryBlock road_factory_electric = new RoadFactoryBlock("road_factory_electric", true);
	public static TarmacCutterBlock tarmac_cutter_electric = new TarmacCutterBlock("tarmac_cutter_electric", true);
	public static CrusherBlock crusher_electric = new CrusherBlock("crusher_electric", true);
	public static PaintOvenBlock paint_oven_electric = new PaintOvenBlock("paint_oven_electric", true);
	public static CompactorBlock compactor_electric = new CompactorBlock("compactor_electric", true);
	public static FabricatorBlock fabricator_electric = new FabricatorBlock("fabricator_electric", true);

	public static PaintFillerHopperBlock paint_filler_hopper = new PaintFillerHopperBlock("paint_filler_hopper");

	public static BlockFluidClassic tar_fluid = (BlockFluidClassic) new BlockFluidClassic(FRFluids.tar, Material.WATER).setUnlocalizedName(FurenikusRoads.MODID + ".tar_fluid").setRegistryName("tar_fluid");
	public static BlockFluidClassic paint_white_fluid = (BlockFluidClassic) new BlockFluidClassic(FRFluids.white_paint, Material.WATER).setUnlocalizedName(FurenikusRoads.MODID + ".paint_white_fluid").setRegistryName("paint_white_fluid");
	public static BlockFluidClassic paint_yellow_fluid = (BlockFluidClassic) new BlockFluidClassic(FRFluids.yellow_paint, Material.WATER).setUnlocalizedName(FurenikusRoads.MODID + ".paint_yellow_fluid").setRegistryName("paint_yellow_fluid");
	public static BlockFluidClassic paint_red_fluid = (BlockFluidClassic) new BlockFluidClassic(FRFluids.red_paint, Material.WATER).setUnlocalizedName(FurenikusRoads.MODID + ".paint_red_fluid").setRegistryName("paint_red_fluid");

	public static StreetBlock street_block_a = (StreetBlock) new StreetBlock("street_block_a", 16).setCreativeTab(FurenikusRoads.tab_sidewalks);
	public static StreetBlock street_block_b = (StreetBlock) new StreetBlock("street_block_b", 16).setCreativeTab(FurenikusRoads.tab_sidewalks);
	public static StreetBlock generic_blocks = (StreetBlock) new StreetBlock("generic_blocks", 4).setCreativeTab(FurenikusRoads.tab_sidewalks);

	public static StreetRoadBlock sidewalk = new StreetRoadBlock("sidewalk", Material.ROCK, FRItems.sidewalk_fragment_standard);
	public static StreetRoadBlock sidewalk_clean = new StreetRoadBlock("sidewalk_clean", Material.ROCK, FRItems.sidewalk_fragment_clean);
	public static StreetRoadBlock sidewalk_dark = new StreetRoadBlock("sidewalk_dark", Material.ROCK, FRItems.sidewalk_fragment_dark);
	public static StreetRoadBlock sidewalk_tan = new StreetRoadBlock("sidewalk_tan", Material.ROCK, FRItems.sidewalk_fragment_tan);
	//tactile bump under-side tile

	public static NonPaintRoadTopBlock tactile_crossing_bumps = new NonPaintRoadTopBlock("tactile_crossing_bumps");
	public static NonPaintRoadTopBlock manhole_cover_round = new NonPaintRoadTopBlock("manhole_cover_round");
	public static NonPaintRoadTopBlock manhole_cover_square = new NonPaintRoadTopBlock("manhole_cover_square");
	public static NonPaintRoadTopBlock drain_cover_1 = new NonPaintRoadTopBlock("drain_cover_1");
	public static NonPaintRoadTopBlock drain_cover_2 = new NonPaintRoadTopBlock("drain_cover_2");

	public static BarrierBlock barrier_standard_mid = new BarrierBlock("barrier_standard_mid");
	public static BarrierBlock barrier_tall_mid = new BarrierBlock("barrier_tall_mid");
	public static ConcreteBarrierBlock barrier_concrete_1_mid = new ConcreteBarrierBlock("barrier_concrete_1_mid");
	public static ConcreteBarrierBlock barrier_concrete_2_mid = new ConcreteBarrierBlock("barrier_concrete_2_mid");
	public static BarsBarrierBlock barrier_bars_mid = new BarsBarrierBlock("barrier_bars_mid");
	public static BarsBarrierBlock barrier_bars_mid_2 = new BarsBarrierBlock("barrier_bars_mid_2");
	public static BarsBarrierBlock barrier_bars_mid_3 = new BarsBarrierBlock("barrier_bars_mid_3");
	public static BarsBarrierBlock barrier_bars_mid_concrete_1 = new BarsBarrierBlock("barrier_bars_mid_concrete_1");
	public static BarsBarrierBlock barrier_bars_mid_concrete_2 = new BarsBarrierBlock("barrier_bars_mid_concrete_2");
	public static BarsBarrierBlock barrier_wall_mid_concrete_1 = new BarsBarrierBlock("barrier_wall_mid_concrete_1");
	public static BarsBarrierBlock barrier_wall_mid_concrete_2 = new BarsBarrierBlock("barrier_wall_mid_concrete_2");
	public static BarsBarrierBlock barrier_wall_pole_mid_concrete_1 = new BarsBarrierBlock("barrier_wall_pole_mid_concrete_1");
	public static BarsBarrierBlock barrier_wall_pole_mid_concrete_2 = new BarsBarrierBlock("barrier_wall_pole_mid_concrete_2");
	public static BarsBarrierBlock barrier_low_mid = new BarsBarrierBlock("barrier_low_mid", 0.5F);
	public static BarrierEndBlock barrier_end = new BarrierEndBlock("barrier_end");

	public static BarrierEdgeBlock barrier_bars_edge = new BarrierEdgeBlock("barrier_bars_edge", false);
	public static BarrierEdgeBlock barrier_bars_edge_2 = new BarrierEdgeBlock("barrier_bars_edge_2", false);
	public static BarrierEdgeBlock barrier_bars_edge_3 = new BarrierEdgeBlock("barrier_bars_edge_3", false);
	public static BarrierEdgeBlock barrier_wall_edge_concrete_1 = new BarrierEdgeBlock("barrier_wall_edge_concrete_1", false);
	public static BarrierEdgeBlock barrier_wall_edge_concrete_2 = new BarrierEdgeBlock("barrier_wall_edge_concrete_2", false);
	public static BarrierEdgeBlock barrier_bars_edge_concrete_1 = new BarrierEdgeBlock("barrier_bars_edge_concrete_1", false);
	public static BarrierEdgeBlock barrier_bars_edge_concrete_2 = new BarrierEdgeBlock("barrier_bars_edge_concrete_2", false);
	public static BarrierEdgeBlock barrier_standard_edge = new BarrierEdgeBlock("barrier_standard_edge", false);
	public static BarrierEdgeBlock barrier_tall_edge = new BarrierEdgeBlock("barrier_tall_edge", false);
	public static BarrierConcreteEdgeBlock barrier_concrete_edge_1 = new BarrierConcreteEdgeBlock("barrier_concrete_edge_1");
	public static BarrierConcreteEdgeBlock barrier_concrete_edge_2 = new BarrierConcreteEdgeBlock("barrier_concrete_edge_2");
	public static BarrierEdgeBlock barrier_wall_pole_edge_concrete_1 = new BarrierEdgeBlock("barrier_wall_pole_edge_concrete_1", false);
	public static BarrierEdgeBlock barrier_wall_pole_edge_concrete_2 = new BarrierEdgeBlock("barrier_wall_pole_edge_concrete_2", false);
	public static BarrierLowEdgeBlock barrier_low_edge = new BarrierLowEdgeBlock("barrier_low_edge", false);

	public static BarrierEdgeBlock barrier_bars_edge_double = new BarrierEdgeBlock("barrier_bars_edge_double", true);
	public static BarrierEdgeBlock barrier_bars_edge_double_2 = new BarrierEdgeBlock("barrier_bars_edge_double_2", true);
	public static BarrierEdgeBlock barrier_bars_edge_double_3 = new BarrierEdgeBlock("barrier_bars_edge_double_3", true);
	public static BarrierEdgeBlock barrier_wall_edge_concrete_1_double = new BarrierEdgeBlock("barrier_wall_edge_concrete_1_double", true);
	public static BarrierEdgeBlock barrier_wall_edge_concrete_2_double = new BarrierEdgeBlock("barrier_wall_edge_concrete_2_double", true);
	public static BarrierEdgeBlock barrier_bars_edge_concrete_1_double = new BarrierEdgeBlock("barrier_bars_edge_concrete_1_double", true);
	public static BarrierEdgeBlock barrier_bars_edge_concrete_2_double = new BarrierEdgeBlock("barrier_bars_edge_concrete_2_double", true);
	public static BarrierEdgeBlock barrier_wall_pole_edge_concrete_1_double = new BarrierEdgeBlock("barrier_wall_pole_edge_concrete_1_double", true);
	public static BarrierEdgeBlock barrier_wall_pole_edge_concrete_2_double = new BarrierEdgeBlock("barrier_wall_pole_edge_concrete_2_double", true);
	public static BarrierEdgeBlock barrier_standard_edge_double = new BarrierEdgeBlock("barrier_standard_edge_double", true);
	public static BarrierEdgeBlock barrier_tall_edge_double = new BarrierEdgeBlock("barrier_tall_edge_double", true);


	public static BollardBlock bollard_1 = new BollardBlock("bollard_1");
	public static StandardBollardBlock bollard_2 = new StandardBollardBlock("bollard_2");
	public static RetractableBollardBlock bollard_3 = new RetractableBollardBlock("bollard_3");
	public static FoldingBollardBlock bollard_folding_smooth_metal = new FoldingBollardBlock("bollard_folding_smooth_metal");
	public static FoldingBollardBlock bollard_folding_black = new FoldingBollardBlock("bollard_folding_black");
	public static FoldingBollardBlock bollard_folding_yellow = new FoldingBollardBlock("bollard_folding_yellow");

	public static WheelStopBlock wheel_stop = new WheelStopBlock("wheel_stop");
	public static SpeedBumpBlock speed_bump = new SpeedBumpBlock("speed_bump");

	public static CatsEyeBlock cats_eye_white  = new CatsEyeBlock("cats_eye_white", false);
	public static CatsEyeBlock cats_eye_yellow = new CatsEyeBlock("cats_eye_yellow", false);
	public static CatsEyeBlock cats_eye_red    = new CatsEyeBlock("cats_eye_red", false);
	public static CatsEyeBlock cats_eye_green  = new CatsEyeBlock("cats_eye_green", false);
	public static CatsEyeBlock cats_eye_blue  = new CatsEyeBlock("cats_eye_blue", false);
	public static CatsEyeBlockFourWay cats_eye_red_green = new CatsEyeBlockFourWay("cats_eye_red_green", false);
	public static CatsEyeBlockFourWay cats_eye_white_red = new CatsEyeBlockFourWay("cats_eye_white_red", false);
	public static CatsEyeBlockFourWay cats_eye_white_yellow = new CatsEyeBlockFourWay("cats_eye_white_yellow", false);
	public static CatsEyeBlockFourWay cats_eye_white_green = new CatsEyeBlockFourWay("cats_eye_white_green", false);
	public static CatsEyeBlockFourWay cats_eye_yellow_red = new CatsEyeBlockFourWay("cats_eye_yellow_red", false);

	public static CatsEyeBlock cats_eye_white_double  = new CatsEyeBlock("cats_eye_white_double", true);
	public static CatsEyeBlock cats_eye_yellow_double = new CatsEyeBlock("cats_eye_yellow_double", true);
	public static CatsEyeBlock cats_eye_red_double    = new CatsEyeBlock("cats_eye_red_double", true);
	public static CatsEyeBlock cats_eye_green_double  = new CatsEyeBlock("cats_eye_green_double", true);
	public static CatsEyeBlock cats_eye_blue_double  = new CatsEyeBlock("cats_eye_blue_double", true);
	public static CatsEyeBlockFourWay cats_eye_red_green_double = new CatsEyeBlockFourWay("cats_eye_red_green_double", true);
	public static CatsEyeBlockFourWay cats_eye_white_red_double = new CatsEyeBlockFourWay("cats_eye_white_red_double", true);
	public static CatsEyeBlockFourWay cats_eye_white_yellow_double = new CatsEyeBlockFourWay("cats_eye_white_yellow_double", true);
	public static CatsEyeBlockFourWay cats_eye_white_green_double = new CatsEyeBlockFourWay("cats_eye_white_green_double", true);
	public static CatsEyeBlockFourWay cats_eye_yellow_red_double = new CatsEyeBlockFourWay("cats_eye_yellow_red_double", true);

	public static MetalPost post_small_vertical = new MetalPost("post_small_vertical", false, 0.125);
	public static MetalPost post_small_horizontal = new MetalPost("post_small_horizontal", true, 0.125);
	public static MetalPost post_medium_vertical = new MetalPost("post_medium_vertical", false, 0.25);
	public static MetalPost post_medium_horizontal = new MetalPost("post_medium_horizontal", true, 0.25);
	public static MetalPost post_large_vertical = new MetalPost("post_large_vertical", false, 0.375);
	public static MetalPost post_large_horizontal = new MetalPost("post_large_horizontal", true, 0.375);

	public static MetalPost post_small_vertical_2 = new MetalPost("post_small_vertical_2", false, 0.125);
	public static MetalPost post_medium_vertical_2 = new MetalPost("post_medium_vertical_2", false, 0.25);
	public static MetalPost post_large_vertical_2 = new MetalPost("post_large_vertical_2", false, 0.375);

	public static StreetLight street_light_1 = new StreetLight("street_light_1", 10, 3, 2);
	public static StreetLight street_light_2 = new StreetLight("street_light_2", 16, 5, 2);
	public static StreetLight street_light_3 = new StreetLight("street_light_3", 10, 2.5, 2);
	public static StreetLight street_light_4 = new StreetLight("street_light_4", 16, 2.5, 2);
	public static StreetLight street_light_5 = new StreetLight("street_light_5", 16, 3, 5);
	public static StreetLight street_light_6 = new StreetLight("street_light_6", 16, 3, 5);

	public static BlockFakeLight fake_light_source = new BlockFakeLight("fake_light_source");

	public static BlockRoadSnow road_snow = new BlockRoadSnow("road_snow");

	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(PaintFillerEntity.class, new ResourceLocation(FurenikusRoads.MODID, "paint_filler_entity"));
		GameRegistry.registerTileEntity(TarDistillerEntity.class, new ResourceLocation(FurenikusRoads.MODID, "tar_distiller_entity"));
		GameRegistry.registerTileEntity(RoadFactoryEntity.class, new ResourceLocation(FurenikusRoads.MODID, "road_factory_entity"));
		GameRegistry.registerTileEntity(TarmacCutterEntity.class, new ResourceLocation(FurenikusRoads.MODID, "tarmac_cutter_entity"));
		GameRegistry.registerTileEntity(CrusherEntity.class, new ResourceLocation(FurenikusRoads.MODID, "crusher_entity"));
		GameRegistry.registerTileEntity(PaintOvenEntity.class, new ResourceLocation(FurenikusRoads.MODID, "paint_oven_entity"));
		GameRegistry.registerTileEntity(CompactorEntity.class, new ResourceLocation(FurenikusRoads.MODID, "compactor_entity"));
		GameRegistry.registerTileEntity(FabricatorEntity.class, new ResourceLocation(FurenikusRoads.MODID, "fabricator_entity"));

		GameRegistry.registerTileEntity(PaintFillerElectricEntity.class, new ResourceLocation(FurenikusRoads.MODID, "paint_filler_electric_entity"));
		GameRegistry.registerTileEntity(TarDistillerElectricEntity.class, new ResourceLocation(FurenikusRoads.MODID, "tar_distiller_electric_entity"));
		GameRegistry.registerTileEntity(RoadFactoryElectricEntity.class, new ResourceLocation(FurenikusRoads.MODID, "road_factory_electric_entity"));
		GameRegistry.registerTileEntity(TarmacCutterElectricEntity.class, new ResourceLocation(FurenikusRoads.MODID, "tarmac_cutter_electric_entity"));
		GameRegistry.registerTileEntity(CrusherElectricEntity.class, new ResourceLocation(FurenikusRoads.MODID, "crusher_electric_entity"));
		GameRegistry.registerTileEntity(PaintOvenElectricEntity.class, new ResourceLocation(FurenikusRoads.MODID, "paint_oven_electric_entity"));
		GameRegistry.registerTileEntity(CompactorElectricEntity.class, new ResourceLocation(FurenikusRoads.MODID, "compactor_electric_entity"));
		GameRegistry.registerTileEntity(FabricatorElectricEntity.class, new ResourceLocation(FurenikusRoads.MODID, "fabricator_electric_entity"));

		GameRegistry.registerTileEntity(PaintFillerHopperEntity.class, new ResourceLocation(FurenikusRoads.MODID, "paint_filler_hopper_entity"));
	}

	public static void register(IForgeRegistry<Block> registry) {
		CustomPaintModelRegistry paintRegistry = new CustomPaintModelRegistry();

		roadBlockList.add(road_block_standard);
		roadBlockList.add(road_block_concrete_1);
		roadBlockList.add(road_block_concrete_2);
		roadBlockList.add(road_block_light);
		roadBlockList.add(road_block_fine);
		roadBlockList.add(road_block_dark);
		roadBlockList.add(road_block_pale);
		roadBlockList.add(road_block_red);
		roadBlockList.add(road_block_blue);
		roadBlockList.add(road_block_white);
		roadBlockList.add(road_block_yellow);
		roadBlockList.add(road_block_green);
		roadBlockList.add(road_block_muddy);
		roadBlockList.add(road_block_muddy_dried);

		roadBlockList.add(road_block_stone);
		roadBlockList.add(road_block_grass);
		roadBlockList.add(road_block_dirt);
		roadBlockList.add(road_block_gravel);
		roadBlockList.add(road_block_sand);

		roadBlockDiagonalList.add(road_block_diagonal_1_1);
		roadBlockDiagonalList.add(road_block_diagonal_1_2);
		roadBlockDiagonalList.add(road_block_diagonal_1_4);
		roadBlockDiagonalList.add(road_block_diagonal_2_4);
		roadBlockDiagonalList.add(road_block_diagonal_1_8);
		roadBlockDiagonalList.add(road_block_diagonal_2_8);
		roadBlockDiagonalList.add(road_block_diagonal_3_8);
		roadBlockDiagonalList.add(road_block_diagonal_4_8);

		roadBlockDiagonalList.add(road_block_diagonal_1_1_mirror);
		roadBlockDiagonalList.add(road_block_diagonal_1_2_mirror);
		roadBlockDiagonalList.add(road_block_diagonal_1_4_mirror);
		roadBlockDiagonalList.add(road_block_diagonal_2_4_mirror);
		roadBlockDiagonalList.add(road_block_diagonal_1_8_mirror);
		roadBlockDiagonalList.add(road_block_diagonal_2_8_mirror);
		roadBlockDiagonalList.add(road_block_diagonal_3_8_mirror);
		roadBlockDiagonalList.add(road_block_diagonal_4_8_mirror);

		roadBlockList.add(sidewalk);
		roadBlockList.add(sidewalk_clean);
		roadBlockList.add(sidewalk_dark);
		roadBlockList.add(sidewalk_tan);

		for (int i = 0; i < col.size(); i++) {
			paintBlockList.add(new LinePaintBlock("line_" + col.get(i).getName() + "_straight_full", PaintGunItemRegistry.LINES, col.get(i)));
			paintBlockList.add(new LinePaintBlock("line_" + col.get(i).getName() + "_straight_thick", PaintGunItemRegistry.LINES, col.get(i)));
			paintBlockList.add(new LinePaintBlock("line_" + col.get(i).getName() + "_straight_double", PaintGunItemRegistry.LINES, col.get(i)));
			paintBlockList.add(new LinePaintBlock("line_" + col.get(i).getName() + "_straight_double_thick", PaintGunItemRegistry.LINES, col.get(i)));
			
			paintBlockList.add(new SideLinePaintBlock("line_" + col.get(i).getName() + "_side_double", PaintGunItemRegistry.LINES, col.get(i)));
			paintBlockList.add(new SideLinePaintBlock("line_" + col.get(i).getName() + "_side_double_thick", PaintGunItemRegistry.LINES, col.get(i)));
			paintBlockList.add(new SideLinePaintBlock("line_" + col.get(i).getName() + "_side_single", PaintGunItemRegistry.LINES, col.get(i)));
			paintBlockList.add(new SideLinePaintBlock("line_" + col.get(i).getName() + "_side_single_thick", PaintGunItemRegistry.LINES, col.get(i)));
			
			paintBlockList.add(new SideLinePaintBlock("line_" + col.get(i).getName() + "_far_side", PaintGunItemRegistry.LINES, col.get(i)));
			paintBlockList.add(new SideLinePaintBlock("line_" + col.get(i).getName() + "_far_side_thick", PaintGunItemRegistry.LINES, col.get(i)));
			
			paintBlockList.add(new SimpleLinePaintBlock("line_" + col.get(i).getName() + "_middle_half_double", PaintGunItemRegistry.LINES, col.get(i)));
			paintBlockList.add(new SimpleLinePaintBlock("line_" + col.get(i).getName() + "_middle_dash_double", PaintGunItemRegistry.LINES,col.get(i)));
			paintBlockList.add(new SimpleLinePaintBlock("line_" + col.get(i).getName() + "_middle_short", PaintGunItemRegistry.LINES, col.get(i)));
			paintBlockList.add(new SimpleLinePaintBlock("line_" + col.get(i).getName() + "_filter_lane", PaintGunItemRegistry.LINES,  col.get(i)));
			paintBlockList.add(new SimpleLinePaintBlock("line_" + col.get(i).getName() + "_side_short", PaintGunItemRegistry.LINES, col.get(i)));
			paintBlockList.add(new SimpleLinePaintBlock("line_" + col.get(i).getName() + "_thin_crossing", PaintGunItemRegistry.LINES,  col.get(i)));

			paintBlockList.add(new CustomPaintBlock(col.get(i).getName() + "_wheelchair_icon", CustomPaintBlock.EnumPaintType.ICON_1x1, new PaintGrid[] {paintRegistry.wheelchair_icon}, PaintGunItemRegistry.ICONS, new int[] {0}, col.get(i), FurenikusRoads.tab_paint_icons));
			paintBlockList.add(new CustomPaintBlock(col.get(i).getName() + "_chevron", CustomPaintBlock.EnumPaintType.ICON_1x1, new PaintGrid[] {paintRegistry.chevron_icon}, PaintGunItemRegistry.ICONS, new int[] {0}, col.get(i), FurenikusRoads.tab_paint_icons));
			
			paintBlockList.add(new Custom1x2PaintBlock(col.get(i).getName() + "_pedestrian", new PaintGrid[] {paintRegistry.pedestrian_low, paintRegistry.pedestrian_high}, PaintGunItemRegistry.ICONS, col.get(i)));
			paintBlockList.add(new Custom1x2PaintBlock(col.get(i).getName() + "_merge_arrow", new PaintGrid[] {paintRegistry.merge_arrow_low, paintRegistry.merge_arrow_high}, PaintGunItemRegistry.ICONS, col.get(i)));
			paintBlockList.add(new Custom1x2PaintBlock(col.get(i).getName() + "_give_way", new PaintGrid[] {paintRegistry.give_way_low, paintRegistry.give_way_high}, PaintGunItemRegistry.ICONS, col.get(i)));

			//TODO Skipping
			paintBlockList.add(new JunctionFilterLinePaintBlock(col.get(i).getName() + "_junction_filter_left", true, PaintGunItemRegistry.JUNCTIONS, new int[] {0}, true, col.get(i)));
			paintBlockList.add(new JunctionFilterLinePaintBlock(col.get(i).getName() + "_junction_filter_left_thin", true, PaintGunItemRegistry.JUNCTIONS, new int[] {0}, true, col.get(i)));
			paintBlockList.add(new JunctionFilterLinePaintBlock(col.get(i).getName() + "_junction_filter_left_empty", true, PaintGunItemRegistry.JUNCTIONS, new int[] {0}, true, col.get(i)));
			paintBlockList.add(new JunctionFilterLinePaintBlock(col.get(i).getName() + "_junction_filter_right", false, PaintGunItemRegistry.JUNCTIONS, new int[] {0}, true, col.get(i)));
			paintBlockList.add(new JunctionFilterLinePaintBlock(col.get(i).getName() + "_junction_filter_right_thin", false, PaintGunItemRegistry.JUNCTIONS, new int[] {0}, true, col.get(i)));
			paintBlockList.add(new JunctionFilterLinePaintBlock(col.get(i).getName() + "_junction_filter_right_empty", false, PaintGunItemRegistry.JUNCTIONS, new int[] {0}, true, col.get(i)));

			paintBlockList.add(new Custom1x4PaintBlock(col.get(i).getName() + "_junction_fork_mid", new PaintGrid[]{paintRegistry.junction_mid_1, paintRegistry.junction_mid_2, paintRegistry.junction_mid_3, paintRegistry.empty}, PaintGunItemRegistry.JUNCTIONS, col.get(i), false));
			paintBlockList.add(new Custom1x4PaintBlock(col.get(i).getName() + "_junction_fork_mid_thin", new PaintGrid[]{paintRegistry.junction_mid_thin_1, paintRegistry.junction_mid_thin_2, paintRegistry.junction_mid_thin_3, paintRegistry.empty}, PaintGunItemRegistry.JUNCTIONS, col.get(i), false));
			paintBlockList.add(new Custom1x4PaintBlock(col.get(i).getName() + "_junction_fork_chevron_mid", new PaintGrid[]{paintRegistry.junction_mid_1, paintRegistry.junction_mid_chevron_2, paintRegistry.junction_mid_chevron_3, paintRegistry.junction_mid_chevron_4}, PaintGunItemRegistry.JUNCTIONS, col.get(i), false));
			paintBlockList.add(new Custom1x4PaintBlock(col.get(i).getName() + "_junction_fork_chevron_mid_thin", new PaintGrid[]{paintRegistry.junction_mid_thin_1, paintRegistry.junction_mid_thin_2, paintRegistry.junction_mid_chevron_thin_3, paintRegistry.chevron_mid_thin_1}, PaintGunItemRegistry.JUNCTIONS, col.get(i), false));

			//TODO Skipping
			paintBlockList.add(new ChevronPaintBlock(col.get(i).getName() + "_chevron_left_a", ChevronPaintBlock.EnumJunctionConnections.CHEVRON_A, true, PaintGunItemRegistry.JUNCTIONS, new int[] {0}, true, col.get(i)));
			paintBlockList.add(new ChevronPaintBlock(col.get(i).getName() + "_chevron_left_b", ChevronPaintBlock.EnumJunctionConnections.CHEVRON_B, false, PaintGunItemRegistry.JUNCTIONS, null, true, col.get(i)));
			paintBlockList.add(new ChevronPaintBlock(col.get(i).getName() + "_chevron_left_a_thin", ChevronPaintBlock.EnumJunctionConnections.CHEVRON_THIN_A, true, PaintGunItemRegistry.JUNCTIONS, new int[] {0}, true, col.get(i)));
			paintBlockList.add(new ChevronPaintBlock(col.get(i).getName() + "_chevron_left_b_thin", ChevronPaintBlock.EnumJunctionConnections.CHEVRON_THIN_B, false, PaintGunItemRegistry.JUNCTIONS, null, true, col.get(i)));
			paintBlockList.add(new ChevronPaintBlock(col.get(i).getName() + "_chevron_right_a", ChevronPaintBlock.EnumJunctionConnections.CHEVRON_A, true, PaintGunItemRegistry.JUNCTIONS, new int[] {0}, true, col.get(i)));
			paintBlockList.add(new ChevronPaintBlock(col.get(i).getName() + "_chevron_right_b", ChevronPaintBlock.EnumJunctionConnections.CHEVRON_B, false, PaintGunItemRegistry.JUNCTIONS, null, true, col.get(i)));
			paintBlockList.add(new ChevronPaintBlock(col.get(i).getName() + "_chevron_right_a_thin", ChevronPaintBlock.EnumJunctionConnections.CHEVRON_THIN_A, true, PaintGunItemRegistry.JUNCTIONS, new int[] {0}, true, col.get(i)));
			paintBlockList.add(new ChevronPaintBlock(col.get(i).getName() + "_chevron_right_b_thin", ChevronPaintBlock.EnumJunctionConnections.CHEVRON_THIN_B, false, PaintGunItemRegistry.JUNCTIONS, null, true, col.get(i)));
			
			paintBlockList.add(new CustomMetaPaintBlock(col.get(i).getName() + "_junction_side_line_connection", CustomPaintBlock.EnumPaintType.ICON_1x1, new PaintGrid[] {paintRegistry.junction_side_line_connection_a, paintRegistry.junction_side_line_connection_b, paintRegistry.junction_side_line_connection_c, paintRegistry.junction_side_line_connection_d}, PaintGunItemRegistry.JUNCTIONS, new int[] {0, 4, 8, 12}, col.get(i), FurenikusRoads.tab_paint_junction));
			paintBlockList.add(new CustomMetaPaintBlock(col.get(i).getName() + "_junction_side_line_connection_thin", CustomPaintBlock.EnumPaintType.ICON_1x1, new PaintGrid[] {paintRegistry.junction_side_line_connection_thin_a, paintRegistry.junction_side_line_connection_thin_b, paintRegistry.junction_side_line_connection_thin_c, paintRegistry.junction_side_line_connection_thin_d}, PaintGunItemRegistry.JUNCTIONS, new int[] {0, 4, 8, 12}, col.get(i), FurenikusRoads.tab_paint_junction));
			paintBlockList.add(new CustomMetaPaintBlock(col.get(i).getName() + "_junction_side_line_connection_thick_thick", CustomPaintBlock.EnumPaintType.ICON_1x1, new PaintGrid[] {paintRegistry.junction_side_line_connection_thick_thick_a, paintRegistry.junction_side_line_connection_thick_thick_b, paintRegistry.junction_side_line_connection_thick_thick_c, paintRegistry.junction_side_line_connection_thick_thick_d}, PaintGunItemRegistry.JUNCTIONS, new int[] {0, 4, 8, 12}, col.get(i), FurenikusRoads.tab_paint_junction));
			paintBlockList.add(new CustomMetaPaintBlock(col.get(i).getName() + "_junction_mid_line_connection", CustomPaintBlock.EnumPaintType.ICON_1x1, new PaintGrid[] {paintRegistry.junction_mid_line_connection_a, paintRegistry.junction_mid_line_connection_b, paintRegistry.junction_mid_line_connection_c, paintRegistry.junction_mid_line_connection_d}, PaintGunItemRegistry.JUNCTIONS, new int[] {0, 4, 8, 12}, col.get(i), FurenikusRoads.tab_paint_junction));
			paintBlockList.add(new CustomMetaPaintBlock(col.get(i).getName() + "_junction_a", CustomPaintBlock.EnumPaintType.ICON_1x1, new PaintGrid[] {paintRegistry.junction_in_a, paintRegistry.junction_out_b, paintRegistry.junction_mid_a}, PaintGunItemRegistry.ICONS, new int[] {0, 4, 8}, col.get(i), FurenikusRoads.tab_paint_junction));
			paintBlockList.add(new CustomMetaPaintBlock(col.get(i).getName() + "_junction_b", CustomPaintBlock.EnumPaintType.ICON_1x1, new PaintGrid[] {paintRegistry.junction_in_b, paintRegistry.junction_out_a, paintRegistry.junction_mid_b}, PaintGunItemRegistry.ICONS, new int[] {0, 4, 8}, col.get(i), FurenikusRoads.tab_paint_junction));

			paintBlockList.add(new ChevronIconPaintBlock(col.get(i).getName() + "_chevron_mid", new PaintGrid[] {paintRegistry.chevron_mid_1, paintRegistry.chevron_mid_2, paintRegistry.chevron_mid_thin_1, paintRegistry.chevron_mid_thin_2}, PaintGunItemRegistry.JUNCTIONS, col.get(i)));
			paintBlockList.add(new ChevronIconPaintBlock(col.get(i).getName() + "_chevron_mid_left", new PaintGrid[] {paintRegistry.chevron_mid_left_1, paintRegistry.chevron_mid_left_2, paintRegistry.chevron_mid_thin_left_1, paintRegistry.chevron_mid_thin_left_2}, PaintGunItemRegistry.JUNCTIONS, col.get(i)));
			paintBlockList.add(new ChevronIconPaintBlock(col.get(i).getName() + "_chevron_mid_right", new PaintGrid[] {paintRegistry.chevron_mid_right_1, paintRegistry.chevron_mid_right_2, paintRegistry.chevron_mid_thin_right_1, paintRegistry.chevron_mid_thin_right_2}, PaintGunItemRegistry.JUNCTIONS, col.get(i)));

			//TODO skipping
			paintBlockList.add(new ChevronSideLinePaintBlock(col.get(i).getName() + "_chevron_side_line", PaintGunItemRegistry.JUNCTIONS, new int[] {0}, false, col.get(i)));

			//TODO
			paintBlockList.add(new LargeTextPaintBlock(col.get(i).getName() + "_slow",      new PaintGrid[]{paintRegistry.text_slow_small, paintRegistry.text_slow_l, paintRegistry.text_slow_m, paintRegistry.text_slow_r}, PaintGunItemRegistry.TEXT, col.get(i)));
			paintBlockList.add(new LargeTextPaintBlock(col.get(i).getName() + "_stop",      new PaintGrid[]{paintRegistry.text_stop_small, paintRegistry.text_stop_l, paintRegistry.text_stop_m, paintRegistry.text_stop_r}, PaintGunItemRegistry.TEXT, col.get(i)));
			paintBlockList.add(new LargeTextPaintBlock(col.get(i).getName() + "_bike",      new PaintGrid[]{paintRegistry.text_bike_small, paintRegistry.text_bike_l, paintRegistry.text_bike_m, paintRegistry.text_bike_r}, PaintGunItemRegistry.TEXT, col.get(i)));
			paintBlockList.add(new LargeTextPaintBlock(col.get(i).getName() + "_bus",       new PaintGrid[]{paintRegistry.text_bus_small, paintRegistry.text_bus_l, paintRegistry.text_bus_m, paintRegistry.text_bus_r}, PaintGunItemRegistry.TEXT, col.get(i)));
			paintBlockList.add(new LargeTextPaintBlock(col.get(i).getName() + "_taxi",      new PaintGrid[]{paintRegistry.text_taxi_small, paintRegistry.text_taxi_l, paintRegistry.text_taxi_m, paintRegistry.text_taxi_r}, PaintGunItemRegistry.TEXT, col.get(i)));
			paintBlockList.add(new LargeTextPaintBlock(col.get(i).getName() + "_lane",      new PaintGrid[]{paintRegistry.text_lane_small, paintRegistry.text_lane_l, paintRegistry.text_lane_m, paintRegistry.text_lane_r}, PaintGunItemRegistry.TEXT, col.get(i)));
			paintBlockList.add(new LargeTextPaintBlock(col.get(i).getName() + "_keep",      new PaintGrid[]{paintRegistry.text_keep_small, paintRegistry.text_keep_l, paintRegistry.text_keep_m, paintRegistry.text_keep_r}, PaintGunItemRegistry.TEXT, col.get(i)));
			paintBlockList.add(new LargeTextPaintBlock(col.get(i).getName() + "_clear",     new PaintGrid[]{paintRegistry.text_clear_small, paintRegistry.text_clear_l, paintRegistry.text_clear_m, paintRegistry.text_clear_r}, PaintGunItemRegistry.TEXT, col.get(i)));
			paintBlockList.add(new LargeTextPaintBlock(col.get(i).getName() + "_turn",      new PaintGrid[]{paintRegistry.text_turn_small, paintRegistry.text_turn_l, paintRegistry.text_turn_m, paintRegistry.text_turn_r}, PaintGunItemRegistry.TEXT, col.get(i)));
			paintBlockList.add(new LargeTextPaintBlock(col.get(i).getName() + "_left",      new PaintGrid[]{paintRegistry.text_left_small, paintRegistry.text_left_l, paintRegistry.text_left_m, paintRegistry.text_left_r}, PaintGunItemRegistry.TEXT, col.get(i)));
			paintBlockList.add(new LargeTextPaintBlock(col.get(i).getName() + "_right",     new PaintGrid[]{paintRegistry.text_right_small, paintRegistry.text_right_l, paintRegistry.text_right_m, paintRegistry.text_right_r}, PaintGunItemRegistry.TEXT, col.get(i)));
			paintBlockList.add(new LargeTextPaintBlock(col.get(i).getName() + "_only",      new PaintGrid[]{paintRegistry.text_only_small, paintRegistry.text_only_l, paintRegistry.text_only_m, paintRegistry.text_only_r}, PaintGunItemRegistry.TEXT, col.get(i)));
			paintBlockList.add(new LargeTextPaintBlock(col.get(i).getName() + "_no",        new PaintGrid[]{paintRegistry.text_no_small, paintRegistry.text_no_l, paintRegistry.text_no_m, paintRegistry.text_no_r}, PaintGunItemRegistry.TEXT, col.get(i)));
			paintBlockList.add(new LargeTextPaintBlock(col.get(i).getName() + "_entry",     new PaintGrid[]{paintRegistry.text_entry_small, paintRegistry.text_entry_l, paintRegistry.text_entry_m, paintRegistry.text_entry_r}, PaintGunItemRegistry.TEXT, col.get(i)));
			paintBlockList.add(new LargeTextPaintBlock(col.get(i).getName() + "_bike_icon", new PaintGrid[]{paintRegistry.text_bike_icon_small, paintRegistry.text_bike_icon_l, paintRegistry.text_bike_icon_m, paintRegistry.text_bike_icon_r}, PaintGunItemRegistry.TEXT, col.get(i)));
			paintBlockList.add(new LargeTextPaintBlock(col.get(i).getName() + "_town",      new PaintGrid[]{paintRegistry.text_town_small, paintRegistry.text_town_l, paintRegistry.text_town_m, paintRegistry.text_town_r}, PaintGunItemRegistry.TEXT, col.get(i)));
			paintBlockList.add(new LargeTextPaintBlock(col.get(i).getName() + "_city",      new PaintGrid[]{paintRegistry.text_city_small, paintRegistry.text_city_l, paintRegistry.text_city_m, paintRegistry.text_city_r}, PaintGunItemRegistry.TEXT, col.get(i)));
			paintBlockList.add(new LargeTextPaintBlock(col.get(i).getName() + "_ctre",      new PaintGrid[]{paintRegistry.text_ctre_small, paintRegistry.text_ctre_l, paintRegistry.text_ctre_m, paintRegistry.text_ctre_r}, PaintGunItemRegistry.TEXT, col.get(i)));

			paintBlockList.add(new CustomPaintWallBlock("paint_letter_" + col.get(i).getName() + "_ab", new PaintGrid[]{paintRegistry.letter_a, paintRegistry.letter_b}, PaintGunItemRegistry.LETTERS, new int[] {0, 8}, col.get(i), FurenikusRoads.tab_paint_letters));
			paintBlockList.add(new CustomPaintWallBlock("paint_letter_" + col.get(i).getName() + "_cd", new PaintGrid[]{paintRegistry.letter_c, paintRegistry.letter_d}, PaintGunItemRegistry.LETTERS, new int[] {0, 8}, col.get(i), FurenikusRoads.tab_paint_letters));
			paintBlockList.add(new CustomPaintWallBlock("paint_letter_" + col.get(i).getName() + "_ef", new PaintGrid[]{paintRegistry.letter_e, paintRegistry.letter_f}, PaintGunItemRegistry.LETTERS, new int[] {0, 8}, col.get(i), FurenikusRoads.tab_paint_letters));
			paintBlockList.add(new CustomPaintWallBlock("paint_letter_" + col.get(i).getName() + "_gh", new PaintGrid[]{paintRegistry.letter_g, paintRegistry.letter_h}, PaintGunItemRegistry.LETTERS, new int[] {0, 8}, col.get(i), FurenikusRoads.tab_paint_letters));
			paintBlockList.add(new CustomPaintWallBlock("paint_letter_" + col.get(i).getName() + "_ij", new PaintGrid[]{paintRegistry.letter_i, paintRegistry.letter_j}, PaintGunItemRegistry.LETTERS, new int[] {0, 8}, col.get(i), FurenikusRoads.tab_paint_letters));
			paintBlockList.add(new CustomPaintWallBlock("paint_letter_" + col.get(i).getName() + "_kl", new PaintGrid[]{paintRegistry.letter_k, paintRegistry.letter_l}, PaintGunItemRegistry.LETTERS, new int[] {0, 8}, col.get(i), FurenikusRoads.tab_paint_letters));
			paintBlockList.add(new CustomPaintWallBlock("paint_letter_" + col.get(i).getName() + "_mn", new PaintGrid[]{paintRegistry.letter_m, paintRegistry.letter_n}, PaintGunItemRegistry.LETTERS, new int[] {0, 8}, col.get(i), FurenikusRoads.tab_paint_letters));
			paintBlockList.add(new CustomPaintWallBlock("paint_letter_" + col.get(i).getName() + "_op", new PaintGrid[]{paintRegistry.letter_o, paintRegistry.letter_p}, PaintGunItemRegistry.LETTERS, new int[] {0, 8}, col.get(i), FurenikusRoads.tab_paint_letters));
			paintBlockList.add(new CustomPaintWallBlock("paint_letter_" + col.get(i).getName() + "_qr", new PaintGrid[]{paintRegistry.letter_q, paintRegistry.letter_r}, PaintGunItemRegistry.LETTERS, new int[] {0, 8}, col.get(i), FurenikusRoads.tab_paint_letters));
			paintBlockList.add(new CustomPaintWallBlock("paint_letter_" + col.get(i).getName() + "_st", new PaintGrid[]{paintRegistry.letter_s, paintRegistry.letter_t}, PaintGunItemRegistry.LETTERS, new int[] {0, 8}, col.get(i), FurenikusRoads.tab_paint_letters));
			paintBlockList.add(new CustomPaintWallBlock("paint_letter_" + col.get(i).getName() + "_uv", new PaintGrid[]{paintRegistry.letter_u, paintRegistry.letter_v}, PaintGunItemRegistry.LETTERS, new int[] {0, 8}, col.get(i), FurenikusRoads.tab_paint_letters));
			paintBlockList.add(new CustomPaintWallBlock("paint_letter_" + col.get(i).getName() + "_wx", new PaintGrid[]{paintRegistry.letter_w, paintRegistry.letter_x}, PaintGunItemRegistry.LETTERS, new int[] {0, 8}, col.get(i), FurenikusRoads.tab_paint_letters));
			paintBlockList.add(new CustomPaintWallBlock("paint_letter_" + col.get(i).getName() + "_yz", new PaintGrid[]{paintRegistry.letter_y, paintRegistry.letter_z}, PaintGunItemRegistry.LETTERS, new int[] {0, 8}, col.get(i), FurenikusRoads.tab_paint_letters));

			paintBlockList.add(new CustomPaintWallBlock("paint_letter_" + col.get(i).getName() + "_01", new PaintGrid[]{paintRegistry.number_0, paintRegistry.number_1}, PaintGunItemRegistry.LETTERS, new int[] {0, 8}, col.get(i), FurenikusRoads.tab_paint_letters));
			paintBlockList.add(new CustomPaintWallBlock("paint_letter_" + col.get(i).getName() + "_23", new PaintGrid[]{paintRegistry.number_2, paintRegistry.number_3}, PaintGunItemRegistry.LETTERS, new int[] {0, 8}, col.get(i), FurenikusRoads.tab_paint_letters));
			paintBlockList.add(new CustomPaintWallBlock("paint_letter_" + col.get(i).getName() + "_45", new PaintGrid[]{paintRegistry.number_4, paintRegistry.number_5}, PaintGunItemRegistry.LETTERS, new int[] {0, 8}, col.get(i), FurenikusRoads.tab_paint_letters));
			paintBlockList.add(new CustomPaintWallBlock("paint_letter_" + col.get(i).getName() + "_67", new PaintGrid[]{paintRegistry.number_6, paintRegistry.number_7}, PaintGunItemRegistry.LETTERS, new int[] {0, 8}, col.get(i), FurenikusRoads.tab_paint_letters));
			paintBlockList.add(new CustomPaintWallBlock("paint_letter_" + col.get(i).getName() + "_89", new PaintGrid[]{paintRegistry.number_8, paintRegistry.number_9}, PaintGunItemRegistry.LETTERS, new int[] {0, 8}, col.get(i), FurenikusRoads.tab_paint_letters));

			paintBlockList.add(new CustomPaintWallBlock("paint_letter_" + col.get(i).getName() + "_punct_question_exclamation", new PaintGrid[]{paintRegistry.punct_question, paintRegistry.punct_exclamation}, PaintGunItemRegistry.LETTERS, new int[] {0, 8}, col.get(i), FurenikusRoads.tab_paint_letters));
			paintBlockList.add(new CustomPaintWallBlock("paint_letter_" + col.get(i).getName() + "_punct_hash_slash", new PaintGrid[]{paintRegistry.punct_hash, paintRegistry.punct_slash}, PaintGunItemRegistry.LETTERS, new int[] {0, 8}, col.get(i), FurenikusRoads.tab_paint_letters));
			
			paintBlockList.add(new HatchBoxPaintBlock("hatch_box_" + col.get(i).getName(), PaintGunItemRegistry.ICONS, new int[] {0}, true, col.get(i)));
			paintBlockList.add(new Custom1x4PaintBlock("line_" + col.get(i).getName() + "_crossing_diagonal", new PaintGrid[]{paintRegistry.crossing_diagonal_1, paintRegistry.crossing_diagonal_2, paintRegistry.crossing_diagonal_3, paintRegistry.crossing_diagonal_4}, PaintGunItemRegistry.ICONS, col.get(i), true));
			paintBlockList.add(new CrossingPaintBlock(col.get(i).getName() + "_crossing_paint", PaintGunItemRegistry.ICONS, new int[] {0}, true, col.get(i)));
			
			paintBlockList.add(new ArrowPaintBlock(col.get(i).getName() + "_arrow", PaintGunItemRegistry.ICONS, new int[] {0, 4}, true, col.get(i)));
			paintBlockList.add(new ArrowLinePaintBlock(col.get(i).getName() + "_arrow_line", PaintGunItemRegistry.ICONS, new int[] {0}, true, col.get(i)));
			paintBlockList.add(new ArrowDiagonalPaintBlock(col.get(i).getName() + "_arrow_diagonal", PaintGunItemRegistry.ICONS, new int[] {0, 4}, true, col.get(i)));
			

		}

		catsEyeList.add(cats_eye_white);
		catsEyeList.add(cats_eye_yellow);
		catsEyeList.add(cats_eye_red);
		catsEyeList.add(cats_eye_green);
		catsEyeList.add(cats_eye_blue);
		catsEyeList.add(cats_eye_red_green);
		catsEyeList.add(cats_eye_white_red);
		catsEyeList.add(cats_eye_white_yellow);
		catsEyeList.add(cats_eye_white_green);
		catsEyeList.add(cats_eye_yellow_red);

		catsEyeList.add(cats_eye_white_double);
		catsEyeList.add(cats_eye_yellow_double);
		catsEyeList.add(cats_eye_red_double);
		catsEyeList.add(cats_eye_green_double);
		catsEyeList.add(cats_eye_blue_double);
		catsEyeList.add(cats_eye_red_green_double);
		catsEyeList.add(cats_eye_white_red_double);
		catsEyeList.add(cats_eye_white_yellow_double);
		catsEyeList.add(cats_eye_white_green_double);
		catsEyeList.add(cats_eye_yellow_red_double);

		genericList.add(post_small_vertical);
		genericList.add(post_small_horizontal);
		genericList.add(post_medium_vertical);
		genericList.add(post_medium_horizontal);
		genericList.add(post_large_vertical);
		genericList.add(post_large_horizontal);

		genericList.add(post_small_vertical_2);
		genericList.add(post_medium_vertical_2);
		genericList.add(post_large_vertical_2);

		genericList.add(street_light_1);
		genericList.add(street_light_2);
		genericList.add(street_light_3);
		genericList.add(street_light_4);
		genericList.add(street_light_5);
		genericList.add(street_light_6);
		genericList.add(barrier_end);

		genericList.add(barrier_standard_mid);
		genericList.add(barrier_tall_mid);
		genericList.add(barrier_concrete_1_mid);
		genericList.add(barrier_concrete_2_mid);

		genericList.add(barrier_bars_mid);
		genericList.add(barrier_bars_mid_2);
		genericList.add(barrier_bars_mid_3);
		genericList.add(barrier_wall_mid_concrete_1);
		genericList.add(barrier_wall_mid_concrete_2);
		genericList.add(barrier_bars_mid_concrete_1);
		genericList.add(barrier_bars_mid_concrete_2);
		genericList.add(barrier_wall_pole_mid_concrete_1);
		genericList.add(barrier_wall_pole_mid_concrete_2);
		genericList.add(barrier_low_mid);

		genericList.add(barrier_standard_edge);
		genericList.add(barrier_tall_edge);
		genericList.add(barrier_concrete_edge_1);
		genericList.add(barrier_concrete_edge_2);

		genericList.add(barrier_bars_edge);
		genericList.add(barrier_bars_edge_2);
		genericList.add(barrier_bars_edge_3);
		genericList.add(barrier_wall_edge_concrete_1);
		genericList.add(barrier_wall_edge_concrete_2);
		genericList.add(barrier_bars_edge_concrete_1);
		genericList.add(barrier_bars_edge_concrete_2);
		genericList.add(barrier_wall_pole_edge_concrete_1);
		genericList.add(barrier_wall_pole_edge_concrete_2);
		genericList.add(barrier_low_edge);

		genericList.add(barrier_bars_edge_double);
		genericList.add(barrier_bars_edge_double_2);
		genericList.add(barrier_bars_edge_double_3);
		genericList.add(barrier_wall_edge_concrete_1_double);
		genericList.add(barrier_wall_edge_concrete_2_double);
		genericList.add(barrier_bars_edge_concrete_1_double);
		genericList.add(barrier_bars_edge_concrete_2_double);
		genericList.add(barrier_wall_pole_edge_concrete_1_double);
		genericList.add(barrier_wall_pole_edge_concrete_2_double);
		genericList.add(barrier_standard_edge_double);
		genericList.add(barrier_tall_edge_double);

		genericList.add(bollard_1);
		genericList.add(bollard_2);
		genericList.add(bollard_3);
		genericList.add(bollard_folding_smooth_metal);
		genericList.add(bollard_folding_black);
		genericList.add(bollard_folding_yellow);

		genericList.add(wheel_stop);
		genericList.add(speed_bump);

		genericList.add(fake_light_source);

		registry.registerAll(
				paint_filler,
				tar_distiller,
				road_factory,
				tarmac_cutter,
				crusher,
				paint_oven,
				compactor,
				fabricator,

				paint_filler_electric,
				tar_distiller_electric,
				road_factory_electric,
				tarmac_cutter_electric,
				crusher_electric,
				paint_oven_electric,
				compactor_electric,
				fabricator_electric,

				paint_filler_hopper,

				tar_fluid,
				paint_white_fluid,
				paint_yellow_fluid,
				paint_red_fluid,

				kerb_standard,

				street_block_a,
				street_block_b,
				generic_blocks,

				tactile_crossing_bumps,
				manhole_cover_round,
				manhole_cover_square,
				drain_cover_1,
				drain_cover_2,
				road_snow
				);

		for (int i = 0; i < roadBlockList.size(); i++)  { registry.register(roadBlockList.get(i)); }
		for (int i = 0; i < roadBlockDiagonalList.size(); i++)  { registry.register(roadBlockDiagonalList.get(i)); }
		for (int i = 0; i < paintBlockList.size(); i++)  { registry.register(paintBlockList.get(i)); }
		for (int i = 0; i < genericList.size(); i++)  { registry.register(genericList.get(i)); }
		for (int i = 0; i < catsEyeList.size(); i++)  { registry.register(catsEyeList.get(i)); }
		for (int i = 0; i < DynamicBlockRegistry.customPaints.size(); i++) { registry.register(DynamicBlockRegistry.customPaints.get(i)); }
	}

	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		for (int i = 0; i < roadBlockList.size(); i++)  { registry.register(new RoadItemBlock(roadBlockList.get(i)).setRegistryName(roadBlockList.get(i).getRegistryName())); }
		for (int i = 0; i < roadBlockDiagonalList.size(); i++)  { registry.register(new RoadItemBlock(roadBlockDiagonalList.get(i)).setRegistryName(roadBlockDiagonalList.get(i).getRegistryName())); }
		for (int i = 0; i < paintBlockList.size(); i++)  { registry.register(new RoadItemBlock(paintBlockList.get(i)).setRegistryName(paintBlockList.get(i).getRegistryName())); }
		for (int i = 0; i < genericList.size(); i++)  { registry.register(new RoadItemBlock(genericList.get(i)).setRegistryName(genericList.get(i).getRegistryName())); }
		for (int i = 0; i < catsEyeList.size(); i++)  { registry.register(new RoadItemBlock(catsEyeList.get(i)).setRegistryName(catsEyeList.get(i).getRegistryName())); }
		for (int i = 0; i < DynamicBlockRegistry.customPaints.size(); i++) { registry.register(new RoadItemBlock(DynamicBlockRegistry.customPaints.get(i)).setRegistryName(DynamicBlockRegistry.customPaints.get(i).getRegistryName()));}
		
		registry.register(new RoadItemBlock(paint_filler).setRegistryName(paint_filler.getRegistryName()));
		registry.register(new RoadItemBlock(tar_distiller).setRegistryName(tar_distiller.getRegistryName()));
		registry.register(new RoadItemBlock(road_factory).setRegistryName(road_factory.getRegistryName()));
		registry.register(new RoadItemBlock(tarmac_cutter).setRegistryName(tarmac_cutter.getRegistryName()));
		registry.register(new RoadItemBlock(crusher).setRegistryName(crusher.getRegistryName()));
		registry.register(new RoadItemBlock(paint_oven).setRegistryName(paint_oven.getRegistryName()));
		registry.register(new RoadItemBlock(compactor).setRegistryName(compactor.getRegistryName()));
		registry.register(new RoadItemBlock(fabricator).setRegistryName(fabricator.getRegistryName()));

		registry.register(new RoadItemBlock(paint_filler_electric).setRegistryName(paint_filler_electric.getRegistryName()));
		registry.register(new RoadItemBlock(tar_distiller_electric).setRegistryName(tar_distiller_electric.getRegistryName()));
		registry.register(new RoadItemBlock(road_factory_electric).setRegistryName(road_factory_electric.getRegistryName()));
		registry.register(new RoadItemBlock(tarmac_cutter_electric).setRegistryName(tarmac_cutter_electric.getRegistryName()));
		registry.register(new RoadItemBlock(crusher_electric).setRegistryName(crusher_electric.getRegistryName()));
		registry.register(new RoadItemBlock(paint_oven_electric).setRegistryName(paint_oven_electric.getRegistryName()));
		registry.register(new RoadItemBlock(compactor_electric).setRegistryName(compactor_electric.getRegistryName()));
		registry.register(new RoadItemBlock(fabricator_electric).setRegistryName(fabricator_electric.getRegistryName()));

		registry.register(new RoadItemBlock(paint_filler_hopper).setRegistryName(paint_filler_hopper.getRegistryName()));

		registry.register(new RoadItemBlock(tar_fluid).setRegistryName(tar_fluid.getRegistryName()));
		registry.register(new RoadItemBlock(paint_white_fluid).setRegistryName(paint_white_fluid.getRegistryName()));
		registry.register(new RoadItemBlock(paint_yellow_fluid).setRegistryName(paint_yellow_fluid.getRegistryName()));
		registry.register(new RoadItemBlock(paint_red_fluid).setRegistryName(paint_red_fluid.getRegistryName()));

		registry.register(new RoadItemBlock(kerb_standard).setRegistryName(kerb_standard.getRegistryName()));

		registry.register(new RoadItemBlock(street_block_a).setRegistryName(street_block_a.getRegistryName()));
		registry.register(new RoadItemBlock(street_block_b).setRegistryName(street_block_b.getRegistryName()));
		registry.register(new RoadItemBlock(generic_blocks).setRegistryName(generic_blocks.getRegistryName()));

		registry.register(new RoadItemBlock(tactile_crossing_bumps).setRegistryName(tactile_crossing_bumps.getRegistryName()));
		registry.register(new RoadItemBlock(manhole_cover_round).setRegistryName(manhole_cover_round.getRegistryName()));
		registry.register(new RoadItemBlock(manhole_cover_square).setRegistryName(manhole_cover_square.getRegistryName()));
		registry.register(new RoadItemBlock(drain_cover_1).setRegistryName(drain_cover_1.getRegistryName()));
		registry.register(new RoadItemBlock(drain_cover_2).setRegistryName(drain_cover_2.getRegistryName()));
		registry.register(new RoadItemBlock(road_snow).setRegistryName(road_snow.getRegistryName()));
	}

	public static void registerClientItemModels() {
		for (int i = 0; i < roadBlockDiagonalList.size(); i++)  { 
			roadBlockDiagonalList.get(i).initItemModel();
		}

		for (int i = 0; i < paintBlockList.size(); i++) {
			if (paintBlockList.get(i) instanceof PaintBlockCustomRenderBase) {
				PaintBlockCustomRenderBase block = (PaintBlockCustomRenderBase) paintBlockList.get(i);
				block.initItemModel();
			}
		}

		for (int i = 0; i < DynamicBlockRegistry.customPaints.size(); i++) {
			CustomPaintBlock block = DynamicBlockRegistry.customPaints.get(i);
			block.initItemModel();
		}
	}

	public static void registerModels() {
		for (int i = 0; i < roadBlockList.size(); i++)  { roadBlockList.get(i).initModel(); }
		for (int i = 0; i < roadBlockDiagonalList.size(); i++)  { roadBlockDiagonalList.get(i).initModel(); }
		for (int i = 0; i < paintBlockList.size(); i++)  { paintBlockList.get(i).initModel(); }
		for (int i = 0; i < genericList.size(); i++)  { genericList.get(i).initModel(); }
		for (int i = 0; i < catsEyeList.size(); i++)  { catsEyeList.get(i).initModel(); }
		for (int i = 0; i < DynamicBlockRegistry.customPaints.size(); i++) { DynamicBlockRegistry.customPaints.get(i).initModel(); }

		paint_filler.initModel();
		tar_distiller.initModel();
		road_factory.initModel();
		tarmac_cutter.initModel();
		crusher.initModel();
		paint_oven.initModel();
		compactor.initModel();
		fabricator.initModel();

		paint_filler_electric.initModel();
		tar_distiller_electric.initModel();
		road_factory_electric.initModel();
		tarmac_cutter_electric.initModel();
		crusher_electric.initModel();
		paint_oven_electric.initModel();
		compactor_electric.initModel();
		fabricator_electric.initModel();

		paint_filler_hopper.initModel();

		registerBlockModel(tar_fluid);
		registerBlockModel(paint_white_fluid);
		registerBlockModel(paint_yellow_fluid);
		registerBlockModel(paint_red_fluid);

		kerb_standard.initModel();

		street_block_a.initModel();
		street_block_b.initModel();
		generic_blocks.initModel();

		tactile_crossing_bumps.initModel();
		manhole_cover_round.initModel();
		manhole_cover_square.initModel();
		drain_cover_1.initModel();
		drain_cover_2.initModel();
		road_snow.initModel();
	}

	@SideOnly(Side.CLIENT)
	public static void registerBlockModel(Block block) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}

	//We only register white variants. Others can be taken from the white.
	public static void registerPaintGunEntries() {
		System.out.println("#############################");
		System.out.println("#############################");
		System.out.println("#############################");
		System.out.println("Registering paint gun entries. Will check " + paintBlockList.size() + " entries.");
		System.out.println("#############################");
		System.out.println("#############################");
		System.out.println("#############################");
		int count = 0;
		for (int i = 0; i < paintBlockList.size(); i++) {
			PaintBlockBase block = paintBlockList.get(i);
			
			if (block.getUnlocalizedName().contains("white")) {
				if (block.getCoreMetas() != null) {
					for (int j = 0; j < block.getCoreMetas().length; j++) {
						PaintGunItemRegistry.registerPaint(block, block.getCoreMetas()[j], j);
						count++;
					}
				}
			}
		}
		
		for (int i = 0; i < DynamicBlockRegistry.customPaints.size(); i++) {
			PaintBlockBase block = DynamicBlockRegistry.customPaints.get(i);
			
			if (block.getUnlocalizedName().contains("white")) {
				for (int j = 0; j < block.getCoreMetas().length; j++) {
					PaintGunItemRegistry.registerPaint(block, block.getCoreMetas()[j], j);
					count++;
				}
				count++;
			}
		}
		
		FurenikusRoads.logger.info("REGISTRATION COMPLETE! " + count + "Paints registered");
	}
}
