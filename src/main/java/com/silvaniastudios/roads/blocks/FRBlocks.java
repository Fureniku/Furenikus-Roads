package com.silvaniastudios.roads.blocks;

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
import com.silvaniastudios.roads.blocks.paint.ArrowDiagonalPaintBlock;
import com.silvaniastudios.roads.blocks.paint.ArrowLinePaintBlock;
import com.silvaniastudios.roads.blocks.paint.ArrowPaintBlock;
import com.silvaniastudios.roads.blocks.paint.ChevronPaintBlock;
import com.silvaniastudios.roads.blocks.paint.ChevronSideLinePaintBlock;
import com.silvaniastudios.roads.blocks.paint.Connected1x2PaintBlock;
import com.silvaniastudios.roads.blocks.paint.Connected1x4PaintBlock;
import com.silvaniastudios.roads.blocks.paint.Connected3x1PaintBlock;
import com.silvaniastudios.roads.blocks.paint.CrossingPaintBlock;
import com.silvaniastudios.roads.blocks.paint.FarSideLinePaintBlock;
import com.silvaniastudios.roads.blocks.paint.HatchBoxPaintBlock;
import com.silvaniastudios.roads.blocks.paint.IconPaintBlock;
import com.silvaniastudios.roads.blocks.paint.JunctionFilterLinePaintBlock;
import com.silvaniastudios.roads.blocks.paint.JunctionPaintBlock;
import com.silvaniastudios.roads.blocks.paint.LetterPaintBlock;
import com.silvaniastudios.roads.blocks.paint.LinePaintBlock;
import com.silvaniastudios.roads.blocks.paint.MultiIconPaintBlock;
import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;
import com.silvaniastudios.roads.blocks.paint.SideLinePaintBlock;
import com.silvaniastudios.roads.blocks.paint.SimpleLinePaintBlock;
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
import com.silvaniastudios.roads.items.PaintGunItemRegistry;
import com.silvaniastudios.roads.items.RoadItemBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public class FRBlocks {

	public static ArrayList<RoadBlock> roadBlockList = new ArrayList<RoadBlock>();
	public static ArrayList<RoadBlockDiagonal> roadBlockDiagonalList = new ArrayList<RoadBlockDiagonal>();
	public static ArrayList<PaintBlockBase> paintBlockList = new ArrayList<PaintBlockBase>();
	public static ArrayList<BlockBase> catsEyeList = new ArrayList<BlockBase>();
	public static ArrayList<BlockBase> genericList = new ArrayList<BlockBase>();

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
	//pedestrian crossing metal markers

	public static BlockFakeLight fake_light_source = new BlockFakeLight("fake_light_source");

	public static LinePaintBlock line_white_straight_full = new LinePaintBlock("line_white_straight_full");
	public static LinePaintBlock line_white_straight_thick = new LinePaintBlock("line_white_straight_thick");
	public static LinePaintBlock line_white_straight_double = new LinePaintBlock("line_white_straight_double");
	public static LinePaintBlock line_white_straight_double_thick = new LinePaintBlock("line_white_straight_double_thick");
	public static SideLinePaintBlock line_white_side_double = new SideLinePaintBlock("line_white_side_double");
	public static SideLinePaintBlock line_white_side_double_thick = new SideLinePaintBlock("line_white_side_double_thick");
	public static SideLinePaintBlock line_white_side_single = new SideLinePaintBlock("line_white_side_single");
	public static SideLinePaintBlock line_white_side_single_thick = new SideLinePaintBlock("line_white_side_single_thick");
	public static FarSideLinePaintBlock line_white_far_side = new FarSideLinePaintBlock("line_white_far_side");
	public static FarSideLinePaintBlock line_white_far_side_thick = new FarSideLinePaintBlock("line_white_far_side_thick");
	public static HatchBoxPaintBlock hatch_box_white = new HatchBoxPaintBlock("hatch_box_white");
	public static Connected1x4PaintBlock line_white_crossing_diagonal = new Connected1x4PaintBlock("line_white_crossing_diagonal", true);
	public static CrossingPaintBlock white_crossing_paint = new CrossingPaintBlock("white_crossing_paint");
	public static SimpleLinePaintBlock line_white_middle_half_double = new SimpleLinePaintBlock("line_white_middle_half_double");
	public static SimpleLinePaintBlock line_white_middle_dash_double = new SimpleLinePaintBlock("line_white_middle_dash_double");
	public static SimpleLinePaintBlock line_white_middle_short = new SimpleLinePaintBlock("line_white_middle_short");
	public static SimpleLinePaintBlock line_white_filter_lane = new SimpleLinePaintBlock("line_white_filter_lane");
	public static SimpleLinePaintBlock line_white_side_short = new SimpleLinePaintBlock("line_white_side_short");
	public static ArrowPaintBlock white_arrow = new ArrowPaintBlock("white_arrow");
	public static ArrowLinePaintBlock white_arrow_line = new ArrowLinePaintBlock("white_arrow_line");
	public static ArrowDiagonalPaintBlock white_arrow_diagonal = new ArrowDiagonalPaintBlock("white_arrow_diagonal");
	public static IconPaintBlock white_wheelchair_icon = new IconPaintBlock("white_wheelchair_icon");
	public static JunctionPaintBlock white_junction_a = new JunctionPaintBlock("white_junction_a");
	public static JunctionPaintBlock white_junction_b = new JunctionPaintBlock("white_junction_b");
	public static IconPaintBlock white_chevron = new IconPaintBlock("white_chevron");
	public static SimpleLinePaintBlock line_white_thin_crossing = new SimpleLinePaintBlock("line_white_thin_crossing");

	public static Connected1x2PaintBlock white_pedestrian = new Connected1x2PaintBlock("white_pedestrian");
	public static Connected1x2PaintBlock white_merge_arrow = new Connected1x2PaintBlock("white_merge_arrow");
	public static Connected1x2PaintBlock white_give_way = new Connected1x2PaintBlock("white_give_way");

	public static JunctionFilterLinePaintBlock white_junction_filter_left = new JunctionFilterLinePaintBlock("white_junction_filter_left", true);
	public static JunctionFilterLinePaintBlock white_junction_filter_left_thin  = new JunctionFilterLinePaintBlock("white_junction_filter_left_thin", true);
	public static JunctionFilterLinePaintBlock white_junction_filter_left_empty = new JunctionFilterLinePaintBlock("white_junction_filter_left_empty", true);
	public static JunctionFilterLinePaintBlock white_junction_filter_right = new JunctionFilterLinePaintBlock("white_junction_filter_right", false);
	public static JunctionFilterLinePaintBlock white_junction_filter_right_thin  = new JunctionFilterLinePaintBlock("white_junction_filter_right_thin", false);
	public static JunctionFilterLinePaintBlock white_junction_filter_right_empty = new JunctionFilterLinePaintBlock("white_junction_filter_right_empty", false);

	public static ChevronSideLinePaintBlock white_chevron_side_line = new ChevronSideLinePaintBlock("white_chevron_side_line");

	public static ChevronPaintBlock white_chevron_left_a = new ChevronPaintBlock("white_chevron_left_a", ChevronPaintBlock.EnumJunctionConnections.CHEVRON_A, true);
	public static ChevronPaintBlock white_chevron_left_b = new ChevronPaintBlock("white_chevron_left_b", ChevronPaintBlock.EnumJunctionConnections.CHEVRON_B, false);
	public static ChevronPaintBlock white_chevron_left_a_thin = new ChevronPaintBlock("white_chevron_left_a_thin", ChevronPaintBlock.EnumJunctionConnections.CHEVRON_THIN_A, true);
	public static ChevronPaintBlock white_chevron_left_b_thin = new ChevronPaintBlock("white_chevron_left_b_thin", ChevronPaintBlock.EnumJunctionConnections.CHEVRON_THIN_B, false);

	public static ChevronPaintBlock white_chevron_right_a = new ChevronPaintBlock("white_chevron_right_a", ChevronPaintBlock.EnumJunctionConnections.CHEVRON_A, true);
	public static ChevronPaintBlock white_chevron_right_b = new ChevronPaintBlock("white_chevron_right_b", ChevronPaintBlock.EnumJunctionConnections.CHEVRON_B, false);
	public static ChevronPaintBlock white_chevron_right_a_thin = new ChevronPaintBlock("white_chevron_right_a_thin", ChevronPaintBlock.EnumJunctionConnections.CHEVRON_THIN_A, true);
	public static ChevronPaintBlock white_chevron_right_b_thin = new ChevronPaintBlock("white_chevron_right_b_thin", ChevronPaintBlock.EnumJunctionConnections.CHEVRON_THIN_B, false);

	public static MultiIconPaintBlock white_chevron_mid = new MultiIconPaintBlock("white_chevron_mid", true);
	public static Connected1x4PaintBlock white_junction_fork_mid = new Connected1x4PaintBlock("white_junction_fork_mid", false);
	public static Connected1x4PaintBlock white_junction_fork_mid_thin = new Connected1x4PaintBlock("white_junction_fork_mid_thin", false);
	public static Connected1x4PaintBlock white_junction_fork_chevron_mid = new Connected1x4PaintBlock("white_junction_fork_chevron_mid", false);
	public static Connected1x4PaintBlock white_junction_fork_chevron_mid_thin = new Connected1x4PaintBlock("white_junction_fork_chevron_mid_thin", false);

	public static MultiIconPaintBlock white_chevron_mid_left = new MultiIconPaintBlock("white_chevron_mid_left", true);
	public static MultiIconPaintBlock white_chevron_mid_right = new MultiIconPaintBlock("white_chevron_mid_right", true);

	public static MultiIconPaintBlock white_junction_side_line_connection = new MultiIconPaintBlock("white_junction_side_line_connection", true);
	public static MultiIconPaintBlock white_junction_side_line_connection_thin = new MultiIconPaintBlock("white_junction_side_line_connection_thin", true);
	public static MultiIconPaintBlock white_junction_side_line_connection_thick_thick = new MultiIconPaintBlock("white_junction_side_line_connection_thick_thick", true);
	public static MultiIconPaintBlock white_junction_mid_line_connection = new MultiIconPaintBlock("white_junction_mid_line_connection", true);

	public static Connected3x1PaintBlock white_slow  = new Connected3x1PaintBlock("white_slow");
	public static Connected3x1PaintBlock white_stop  = new Connected3x1PaintBlock("white_stop");
	public static Connected3x1PaintBlock white_bike  = new Connected3x1PaintBlock("white_bike");
	public static Connected3x1PaintBlock white_bus   = new Connected3x1PaintBlock("white_bus");
	public static Connected3x1PaintBlock white_taxi  = new Connected3x1PaintBlock("white_taxi");
	public static Connected3x1PaintBlock white_lane  = new Connected3x1PaintBlock("white_lane");
	public static Connected3x1PaintBlock white_keep  = new Connected3x1PaintBlock("white_keep");
	public static Connected3x1PaintBlock white_clear = new Connected3x1PaintBlock("white_clear");
	public static Connected3x1PaintBlock white_turn  = new Connected3x1PaintBlock("white_turn");
	public static Connected3x1PaintBlock white_left  = new Connected3x1PaintBlock("white_left");
	public static Connected3x1PaintBlock white_right = new Connected3x1PaintBlock("white_right");
	public static Connected3x1PaintBlock white_only  = new Connected3x1PaintBlock("white_only");
	public static Connected3x1PaintBlock white_no    = new Connected3x1PaintBlock("white_no");
	public static Connected3x1PaintBlock white_entry = new Connected3x1PaintBlock("white_entry");
	public static Connected3x1PaintBlock white_bike_icon = new Connected3x1PaintBlock("white_bike_icon");
	public static Connected3x1PaintBlock white_town = new Connected3x1PaintBlock("white_town");
	public static Connected3x1PaintBlock white_city = new Connected3x1PaintBlock("white_city");
	public static Connected3x1PaintBlock white_ctre = new Connected3x1PaintBlock("white_ctre");

	public static LetterPaintBlock paint_letter_white_ab = new LetterPaintBlock("paint_letter_white_ab");
	public static LetterPaintBlock paint_letter_white_cd = new LetterPaintBlock("paint_letter_white_cd");
	public static LetterPaintBlock paint_letter_white_ef = new LetterPaintBlock("paint_letter_white_ef");
	public static LetterPaintBlock paint_letter_white_gh = new LetterPaintBlock("paint_letter_white_gh");
	public static LetterPaintBlock paint_letter_white_ij = new LetterPaintBlock("paint_letter_white_ij");
	public static LetterPaintBlock paint_letter_white_kl = new LetterPaintBlock("paint_letter_white_kl");
	public static LetterPaintBlock paint_letter_white_mn = new LetterPaintBlock("paint_letter_white_mn");
	public static LetterPaintBlock paint_letter_white_op = new LetterPaintBlock("paint_letter_white_op");
	public static LetterPaintBlock paint_letter_white_qr = new LetterPaintBlock("paint_letter_white_qr");
	public static LetterPaintBlock paint_letter_white_st = new LetterPaintBlock("paint_letter_white_st");
	public static LetterPaintBlock paint_letter_white_uv = new LetterPaintBlock("paint_letter_white_uv");
	public static LetterPaintBlock paint_letter_white_wx = new LetterPaintBlock("paint_letter_white_wx");
	public static LetterPaintBlock paint_letter_white_yz = new LetterPaintBlock("paint_letter_white_yz");
	public static LetterPaintBlock paint_letter_white_01 = new LetterPaintBlock("paint_letter_white_01");
	public static LetterPaintBlock paint_letter_white_23 = new LetterPaintBlock("paint_letter_white_23");
	public static LetterPaintBlock paint_letter_white_45 = new LetterPaintBlock("paint_letter_white_45");
	public static LetterPaintBlock paint_letter_white_67 = new LetterPaintBlock("paint_letter_white_67");
	public static LetterPaintBlock paint_letter_white_89 = new LetterPaintBlock("paint_letter_white_89");
	public static LetterPaintBlock paint_letter_white_punct_question_exclamation = new LetterPaintBlock("paint_letter_white_punct_question_exclamation");
	public static LetterPaintBlock paint_letter_white_punct_hash_slash = new LetterPaintBlock("paint_letter_white_punct_hash_slash");



	public static LinePaintBlock line_yellow_straight_full = new LinePaintBlock("line_yellow_straight_full");
	public static LinePaintBlock line_yellow_straight_thick = new LinePaintBlock("line_yellow_straight_thick");
	public static LinePaintBlock line_yellow_straight_double = new LinePaintBlock("line_yellow_straight_double");
	public static LinePaintBlock line_yellow_straight_double_thick = new LinePaintBlock("line_yellow_straight_double_thick");
	public static SideLinePaintBlock line_yellow_side_double = new SideLinePaintBlock("line_yellow_side_double");
	public static SideLinePaintBlock line_yellow_side_double_thick = new SideLinePaintBlock("line_yellow_side_double_thick");
	public static SideLinePaintBlock line_yellow_side_single = new SideLinePaintBlock("line_yellow_side_single");
	public static SideLinePaintBlock line_yellow_side_single_thick = new SideLinePaintBlock("line_yellow_side_single_thick");
	public static FarSideLinePaintBlock line_yellow_far_side = new FarSideLinePaintBlock("line_yellow_far_side");
	public static FarSideLinePaintBlock line_yellow_far_side_thick = new FarSideLinePaintBlock("line_yellow_far_side_thick");
	public static HatchBoxPaintBlock hatch_box_yellow = new HatchBoxPaintBlock("hatch_box_yellow");
	public static Connected1x4PaintBlock line_yellow_crossing_diagonal = new Connected1x4PaintBlock("line_yellow_crossing_diagonal", true);
	public static CrossingPaintBlock yellow_crossing_paint = new CrossingPaintBlock("yellow_crossing_paint");
	public static SimpleLinePaintBlock line_yellow_middle_half_double = new SimpleLinePaintBlock("line_yellow_middle_half_double");
	public static SimpleLinePaintBlock line_yellow_middle_dash_double = new SimpleLinePaintBlock("line_yellow_middle_dash_double");
	public static SimpleLinePaintBlock line_yellow_middle_short = new SimpleLinePaintBlock("line_yellow_middle_short");
	public static SimpleLinePaintBlock line_yellow_filter_lane = new SimpleLinePaintBlock("line_yellow_filter_lane");
	public static SimpleLinePaintBlock line_yellow_side_short = new SimpleLinePaintBlock("line_yellow_side_short");
	public static ArrowPaintBlock yellow_arrow = new ArrowPaintBlock("yellow_arrow");
	public static ArrowLinePaintBlock yellow_arrow_line = new ArrowLinePaintBlock("yellow_arrow_line");
	public static ArrowDiagonalPaintBlock yellow_arrow_diagonal = new ArrowDiagonalPaintBlock("yellow_arrow_diagonal");
	public static IconPaintBlock yellow_wheelchair_icon = new IconPaintBlock("yellow_wheelchair_icon");
	public static JunctionPaintBlock yellow_junction_a = new JunctionPaintBlock("yellow_junction_a");
	public static JunctionPaintBlock yellow_junction_b = new JunctionPaintBlock("yellow_junction_b");
	public static IconPaintBlock yellow_chevron = new IconPaintBlock("yellow_chevron");
	public static SimpleLinePaintBlock line_yellow_thin_crossing = new SimpleLinePaintBlock("line_yellow_thin_crossing");

	public static Connected1x2PaintBlock yellow_pedestrian = new Connected1x2PaintBlock("yellow_pedestrian");
	public static Connected1x2PaintBlock yellow_merge_arrow = new Connected1x2PaintBlock("yellow_merge_arrow");
	public static Connected1x2PaintBlock yellow_give_way = new Connected1x2PaintBlock("yellow_give_way");

	public static JunctionFilterLinePaintBlock yellow_junction_filter_left = new JunctionFilterLinePaintBlock("yellow_junction_filter_left", true);
	public static JunctionFilterLinePaintBlock yellow_junction_filter_left_thin  = new JunctionFilterLinePaintBlock("yellow_junction_filter_left_thin", true);
	public static JunctionFilterLinePaintBlock yellow_junction_filter_left_empty = new JunctionFilterLinePaintBlock("yellow_junction_filter_left_empty", true);
	public static JunctionFilterLinePaintBlock yellow_junction_filter_right = new JunctionFilterLinePaintBlock("yellow_junction_filter_right", false);
	public static JunctionFilterLinePaintBlock yellow_junction_filter_right_thin  = new JunctionFilterLinePaintBlock("yellow_junction_filter_right_thin", false);
	public static JunctionFilterLinePaintBlock yellow_junction_filter_right_empty = new JunctionFilterLinePaintBlock("yellow_junction_filter_right_empty", false);

	public static ChevronSideLinePaintBlock yellow_chevron_side_line = new ChevronSideLinePaintBlock("yellow_chevron_side_line");

	public static ChevronPaintBlock yellow_chevron_left_a = new ChevronPaintBlock("yellow_chevron_left_a", ChevronPaintBlock.EnumJunctionConnections.CHEVRON_A, true);
	public static ChevronPaintBlock yellow_chevron_left_b = new ChevronPaintBlock("yellow_chevron_left_b", ChevronPaintBlock.EnumJunctionConnections.CHEVRON_B, false);
	public static ChevronPaintBlock yellow_chevron_left_a_thin = new ChevronPaintBlock("yellow_chevron_left_a_thin", ChevronPaintBlock.EnumJunctionConnections.CHEVRON_THIN_A, true);
	public static ChevronPaintBlock yellow_chevron_left_b_thin = new ChevronPaintBlock("yellow_chevron_left_b_thin", ChevronPaintBlock.EnumJunctionConnections.CHEVRON_THIN_B, false);

	public static ChevronPaintBlock yellow_chevron_right_a = new ChevronPaintBlock("yellow_chevron_right_a", ChevronPaintBlock.EnumJunctionConnections.CHEVRON_A, true);
	public static ChevronPaintBlock yellow_chevron_right_b = new ChevronPaintBlock("yellow_chevron_right_b", ChevronPaintBlock.EnumJunctionConnections.CHEVRON_B, false);
	public static ChevronPaintBlock yellow_chevron_right_a_thin = new ChevronPaintBlock("yellow_chevron_right_a_thin", ChevronPaintBlock.EnumJunctionConnections.CHEVRON_THIN_A, true);
	public static ChevronPaintBlock yellow_chevron_right_b_thin = new ChevronPaintBlock("yellow_chevron_right_b_thin", ChevronPaintBlock.EnumJunctionConnections.CHEVRON_THIN_B, false);

	public static MultiIconPaintBlock yellow_chevron_mid = new MultiIconPaintBlock("yellow_chevron_mid", true);
	public static Connected1x4PaintBlock yellow_junction_fork_mid = new Connected1x4PaintBlock("yellow_junction_fork_mid", false);
	public static Connected1x4PaintBlock yellow_junction_fork_mid_thin = new Connected1x4PaintBlock("yellow_junction_fork_mid_thin", false);
	public static Connected1x4PaintBlock yellow_junction_fork_chevron_mid = new Connected1x4PaintBlock("yellow_junction_fork_chevron_mid", false);
	public static Connected1x4PaintBlock yellow_junction_fork_chevron_mid_thin = new Connected1x4PaintBlock("yellow_junction_fork_chevron_mid_thin", false);

	public static MultiIconPaintBlock yellow_chevron_mid_left = new MultiIconPaintBlock("yellow_chevron_mid_left", true);
	public static MultiIconPaintBlock yellow_chevron_mid_right = new MultiIconPaintBlock("yellow_chevron_mid_right", true);

	public static MultiIconPaintBlock yellow_junction_side_line_connection = new MultiIconPaintBlock("yellow_junction_side_line_connection", true);
	public static MultiIconPaintBlock yellow_junction_side_line_connection_thin = new MultiIconPaintBlock("yellow_junction_side_line_connection_thin", true);
	public static MultiIconPaintBlock yellow_junction_side_line_connection_thick_thick = new MultiIconPaintBlock("yellow_junction_side_line_connection_thick_thick", true);
	public static MultiIconPaintBlock yellow_junction_mid_line_connection = new MultiIconPaintBlock("yellow_junction_mid_line_connection", true);

	public static Connected3x1PaintBlock yellow_slow  = new Connected3x1PaintBlock("yellow_slow");
	public static Connected3x1PaintBlock yellow_stop  = new Connected3x1PaintBlock("yellow_stop");
	public static Connected3x1PaintBlock yellow_bike  = new Connected3x1PaintBlock("yellow_bike");
	public static Connected3x1PaintBlock yellow_bus   = new Connected3x1PaintBlock("yellow_bus");
	public static Connected3x1PaintBlock yellow_taxi  = new Connected3x1PaintBlock("yellow_taxi");
	public static Connected3x1PaintBlock yellow_lane  = new Connected3x1PaintBlock("yellow_lane");
	public static Connected3x1PaintBlock yellow_keep  = new Connected3x1PaintBlock("yellow_keep");
	public static Connected3x1PaintBlock yellow_clear = new Connected3x1PaintBlock("yellow_clear");
	public static Connected3x1PaintBlock yellow_turn  = new Connected3x1PaintBlock("yellow_turn");
	public static Connected3x1PaintBlock yellow_left  = new Connected3x1PaintBlock("yellow_left");
	public static Connected3x1PaintBlock yellow_right = new Connected3x1PaintBlock("yellow_right");
	public static Connected3x1PaintBlock yellow_only  = new Connected3x1PaintBlock("yellow_only");
	public static Connected3x1PaintBlock yellow_no    = new Connected3x1PaintBlock("yellow_no");
	public static Connected3x1PaintBlock yellow_entry = new Connected3x1PaintBlock("yellow_entry");
	public static Connected3x1PaintBlock yellow_bike_icon = new Connected3x1PaintBlock("yellow_bike_icon");
	public static Connected3x1PaintBlock yellow_town = new Connected3x1PaintBlock("yellow_town");
	public static Connected3x1PaintBlock yellow_city = new Connected3x1PaintBlock("yellow_city");
	public static Connected3x1PaintBlock yellow_ctre = new Connected3x1PaintBlock("yellow_ctre");

	public static LetterPaintBlock paint_letter_yellow_ab = new LetterPaintBlock("paint_letter_yellow_ab");
	public static LetterPaintBlock paint_letter_yellow_cd = new LetterPaintBlock("paint_letter_yellow_cd");
	public static LetterPaintBlock paint_letter_yellow_ef = new LetterPaintBlock("paint_letter_yellow_ef");
	public static LetterPaintBlock paint_letter_yellow_gh = new LetterPaintBlock("paint_letter_yellow_gh");
	public static LetterPaintBlock paint_letter_yellow_ij = new LetterPaintBlock("paint_letter_yellow_ij");
	public static LetterPaintBlock paint_letter_yellow_kl = new LetterPaintBlock("paint_letter_yellow_kl");
	public static LetterPaintBlock paint_letter_yellow_mn = new LetterPaintBlock("paint_letter_yellow_mn");
	public static LetterPaintBlock paint_letter_yellow_op = new LetterPaintBlock("paint_letter_yellow_op");
	public static LetterPaintBlock paint_letter_yellow_qr = new LetterPaintBlock("paint_letter_yellow_qr");
	public static LetterPaintBlock paint_letter_yellow_st = new LetterPaintBlock("paint_letter_yellow_st");
	public static LetterPaintBlock paint_letter_yellow_uv = new LetterPaintBlock("paint_letter_yellow_uv");
	public static LetterPaintBlock paint_letter_yellow_wx = new LetterPaintBlock("paint_letter_yellow_wx");
	public static LetterPaintBlock paint_letter_yellow_yz = new LetterPaintBlock("paint_letter_yellow_yz");
	public static LetterPaintBlock paint_letter_yellow_01 = new LetterPaintBlock("paint_letter_yellow_01");
	public static LetterPaintBlock paint_letter_yellow_23 = new LetterPaintBlock("paint_letter_yellow_23");
	public static LetterPaintBlock paint_letter_yellow_45 = new LetterPaintBlock("paint_letter_yellow_45");
	public static LetterPaintBlock paint_letter_yellow_67 = new LetterPaintBlock("paint_letter_yellow_67");
	public static LetterPaintBlock paint_letter_yellow_89 = new LetterPaintBlock("paint_letter_yellow_89");
	public static LetterPaintBlock paint_letter_yellow_punct_question_exclamation = new LetterPaintBlock("paint_letter_yellow_punct_question_exclamation");
	public static LetterPaintBlock paint_letter_yellow_punct_hash_slash = new LetterPaintBlock("paint_letter_yellow_punct_hash_slash");



	public static LinePaintBlock line_red_straight_full = new LinePaintBlock("line_red_straight_full");
	public static LinePaintBlock line_red_straight_thick = new LinePaintBlock("line_red_straight_thick");
	public static LinePaintBlock line_red_straight_double = new LinePaintBlock("line_red_straight_double");
	public static LinePaintBlock line_red_straight_double_thick = new LinePaintBlock("line_red_straight_double_thick");
	public static SideLinePaintBlock line_red_side_double = new SideLinePaintBlock("line_red_side_double");
	public static SideLinePaintBlock line_red_side_double_thick = new SideLinePaintBlock("line_red_side_double_thick");
	public static SideLinePaintBlock line_red_side_single = new SideLinePaintBlock("line_red_side_single");
	public static SideLinePaintBlock line_red_side_single_thick = new SideLinePaintBlock("line_red_side_single_thick");
	public static FarSideLinePaintBlock line_red_far_side = new FarSideLinePaintBlock("line_red_far_side");
	public static FarSideLinePaintBlock line_red_far_side_thick = new FarSideLinePaintBlock("line_red_far_side_thick");
	public static HatchBoxPaintBlock hatch_box_red = new HatchBoxPaintBlock("hatch_box_red");
	public static Connected1x4PaintBlock line_red_crossing_diagonal = new Connected1x4PaintBlock("line_red_crossing_diagonal", true);
	public static CrossingPaintBlock red_crossing_paint = new CrossingPaintBlock("red_crossing_paint");
	public static SimpleLinePaintBlock line_red_middle_half_double = new SimpleLinePaintBlock("line_red_middle_half_double");
	public static SimpleLinePaintBlock line_red_middle_dash_double = new SimpleLinePaintBlock("line_red_middle_dash_double");
	public static SimpleLinePaintBlock line_red_middle_short = new SimpleLinePaintBlock("line_red_middle_short");
	public static SimpleLinePaintBlock line_red_filter_lane = new SimpleLinePaintBlock("line_red_filter_lane");
	public static SimpleLinePaintBlock line_red_side_short = new SimpleLinePaintBlock("line_red_side_short");
	public static ArrowPaintBlock red_arrow = new ArrowPaintBlock("red_arrow");
	public static ArrowLinePaintBlock red_arrow_line = new ArrowLinePaintBlock("red_arrow_line");
	public static ArrowDiagonalPaintBlock red_arrow_diagonal = new ArrowDiagonalPaintBlock("red_arrow_diagonal");
	public static IconPaintBlock red_wheelchair_icon = new IconPaintBlock("red_wheelchair_icon");
	public static JunctionPaintBlock red_junction_a = new JunctionPaintBlock("red_junction_a");
	public static JunctionPaintBlock red_junction_b = new JunctionPaintBlock("red_junction_b");
	public static IconPaintBlock red_chevron = new IconPaintBlock("red_chevron");

	public static Connected1x2PaintBlock red_pedestrian = new Connected1x2PaintBlock("red_pedestrian");
	public static Connected1x2PaintBlock red_merge_arrow = new Connected1x2PaintBlock("red_merge_arrow");
	public static Connected1x2PaintBlock red_give_way = new Connected1x2PaintBlock("red_give_way");

	public static JunctionFilterLinePaintBlock red_junction_filter_left = new JunctionFilterLinePaintBlock("red_junction_filter_left", true);
	public static JunctionFilterLinePaintBlock red_junction_filter_left_thin  = new JunctionFilterLinePaintBlock("red_junction_filter_left_thin", true);
	public static JunctionFilterLinePaintBlock red_junction_filter_left_empty = new JunctionFilterLinePaintBlock("red_junction_filter_left_empty", true);
	public static JunctionFilterLinePaintBlock red_junction_filter_right = new JunctionFilterLinePaintBlock("red_junction_filter_right", false);
	public static JunctionFilterLinePaintBlock red_junction_filter_right_thin  = new JunctionFilterLinePaintBlock("red_junction_filter_right_thin", false);
	public static JunctionFilterLinePaintBlock red_junction_filter_right_empty = new JunctionFilterLinePaintBlock("red_junction_filter_right_empty", false);

	public static ChevronSideLinePaintBlock red_chevron_side_line = new ChevronSideLinePaintBlock("red_chevron_side_line");
	public static SimpleLinePaintBlock line_red_thin_crossing = new SimpleLinePaintBlock("line_red_thin_crossing");

	public static ChevronPaintBlock red_chevron_left_a = new ChevronPaintBlock("red_chevron_left_a", ChevronPaintBlock.EnumJunctionConnections.CHEVRON_A, true);
	public static ChevronPaintBlock red_chevron_left_b = new ChevronPaintBlock("red_chevron_left_b", ChevronPaintBlock.EnumJunctionConnections.CHEVRON_B, false);
	public static ChevronPaintBlock red_chevron_left_a_thin = new ChevronPaintBlock("red_chevron_left_a_thin", ChevronPaintBlock.EnumJunctionConnections.CHEVRON_THIN_A, true);
	public static ChevronPaintBlock red_chevron_left_b_thin = new ChevronPaintBlock("red_chevron_left_b_thin", ChevronPaintBlock.EnumJunctionConnections.CHEVRON_THIN_B, false);

	public static ChevronPaintBlock red_chevron_right_a = new ChevronPaintBlock("red_chevron_right_a", ChevronPaintBlock.EnumJunctionConnections.CHEVRON_A, true);
	public static ChevronPaintBlock red_chevron_right_b = new ChevronPaintBlock("red_chevron_right_b", ChevronPaintBlock.EnumJunctionConnections.CHEVRON_B, false);
	public static ChevronPaintBlock red_chevron_right_a_thin = new ChevronPaintBlock("red_chevron_right_a_thin", ChevronPaintBlock.EnumJunctionConnections.CHEVRON_THIN_A, true);
	public static ChevronPaintBlock red_chevron_right_b_thin = new ChevronPaintBlock("red_chevron_right_b_thin", ChevronPaintBlock.EnumJunctionConnections.CHEVRON_THIN_B, false);

	public static MultiIconPaintBlock red_chevron_mid = new MultiIconPaintBlock("red_chevron_mid", true);
	public static Connected1x4PaintBlock red_junction_fork_mid = new Connected1x4PaintBlock("red_junction_fork_mid", false);
	public static Connected1x4PaintBlock red_junction_fork_mid_thin = new Connected1x4PaintBlock("red_junction_fork_mid_thin", false);
	public static Connected1x4PaintBlock red_junction_fork_chevron_mid = new Connected1x4PaintBlock("red_junction_fork_chevron_mid", false);
	public static Connected1x4PaintBlock red_junction_fork_chevron_mid_thin = new Connected1x4PaintBlock("red_junction_fork_chevron_mid_thin", false);

	public static MultiIconPaintBlock red_chevron_mid_left = new MultiIconPaintBlock("red_chevron_mid_left", true);
	public static MultiIconPaintBlock red_chevron_mid_right = new MultiIconPaintBlock("red_chevron_mid_right", true);

	public static MultiIconPaintBlock red_junction_side_line_connection = new MultiIconPaintBlock("red_junction_side_line_connection", true);
	public static MultiIconPaintBlock red_junction_side_line_connection_thin = new MultiIconPaintBlock("red_junction_side_line_connection_thin", true);
	public static MultiIconPaintBlock red_junction_side_line_connection_thick_thick = new MultiIconPaintBlock("red_junction_side_line_connection_thick_thick", true);
	public static MultiIconPaintBlock red_junction_mid_line_connection = new MultiIconPaintBlock("red_junction_mid_line_connection", true);

	public static Connected3x1PaintBlock red_slow  = new Connected3x1PaintBlock("red_slow");
	public static Connected3x1PaintBlock red_stop  = new Connected3x1PaintBlock("red_stop");
	public static Connected3x1PaintBlock red_bike  = new Connected3x1PaintBlock("red_bike");
	public static Connected3x1PaintBlock red_bus   = new Connected3x1PaintBlock("red_bus");
	public static Connected3x1PaintBlock red_taxi  = new Connected3x1PaintBlock("red_taxi");
	public static Connected3x1PaintBlock red_lane  = new Connected3x1PaintBlock("red_lane");
	public static Connected3x1PaintBlock red_keep  = new Connected3x1PaintBlock("red_keep");
	public static Connected3x1PaintBlock red_clear = new Connected3x1PaintBlock("red_clear");
	public static Connected3x1PaintBlock red_turn  = new Connected3x1PaintBlock("red_turn");
	public static Connected3x1PaintBlock red_left  = new Connected3x1PaintBlock("red_left");
	public static Connected3x1PaintBlock red_right = new Connected3x1PaintBlock("red_right");
	public static Connected3x1PaintBlock red_only  = new Connected3x1PaintBlock("red_only");
	public static Connected3x1PaintBlock red_no    = new Connected3x1PaintBlock("red_no");
	public static Connected3x1PaintBlock red_entry = new Connected3x1PaintBlock("red_entry");
	public static Connected3x1PaintBlock red_bike_icon = new Connected3x1PaintBlock("red_bike_icon");
	public static Connected3x1PaintBlock red_town = new Connected3x1PaintBlock("red_town");
	public static Connected3x1PaintBlock red_city = new Connected3x1PaintBlock("red_city");
	public static Connected3x1PaintBlock red_ctre = new Connected3x1PaintBlock("red_ctre");

	public static LetterPaintBlock paint_letter_red_ab = new LetterPaintBlock("paint_letter_red_ab");
	public static LetterPaintBlock paint_letter_red_cd = new LetterPaintBlock("paint_letter_red_cd");
	public static LetterPaintBlock paint_letter_red_ef = new LetterPaintBlock("paint_letter_red_ef");
	public static LetterPaintBlock paint_letter_red_gh = new LetterPaintBlock("paint_letter_red_gh");
	public static LetterPaintBlock paint_letter_red_ij = new LetterPaintBlock("paint_letter_red_ij");
	public static LetterPaintBlock paint_letter_red_kl = new LetterPaintBlock("paint_letter_red_kl");
	public static LetterPaintBlock paint_letter_red_mn = new LetterPaintBlock("paint_letter_red_mn");
	public static LetterPaintBlock paint_letter_red_op = new LetterPaintBlock("paint_letter_red_op");
	public static LetterPaintBlock paint_letter_red_qr = new LetterPaintBlock("paint_letter_red_qr");
	public static LetterPaintBlock paint_letter_red_st = new LetterPaintBlock("paint_letter_red_st");
	public static LetterPaintBlock paint_letter_red_uv = new LetterPaintBlock("paint_letter_red_uv");
	public static LetterPaintBlock paint_letter_red_wx = new LetterPaintBlock("paint_letter_red_wx");
	public static LetterPaintBlock paint_letter_red_yz = new LetterPaintBlock("paint_letter_red_yz");
	public static LetterPaintBlock paint_letter_red_01 = new LetterPaintBlock("paint_letter_red_01");
	public static LetterPaintBlock paint_letter_red_23 = new LetterPaintBlock("paint_letter_red_23");
	public static LetterPaintBlock paint_letter_red_45 = new LetterPaintBlock("paint_letter_red_45");
	public static LetterPaintBlock paint_letter_red_67 = new LetterPaintBlock("paint_letter_red_67");
	public static LetterPaintBlock paint_letter_red_89 = new LetterPaintBlock("paint_letter_red_89");
	public static LetterPaintBlock paint_letter_red_punct_question_exclamation = new LetterPaintBlock("paint_letter_red_punct_question_exclamation");
	public static LetterPaintBlock paint_letter_red_punct_hash_slash = new LetterPaintBlock("paint_letter_red_punct_hash_slash");

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

		paintBlockList.add(line_white_straight_full);
		paintBlockList.add(line_white_straight_thick);
		paintBlockList.add(line_white_straight_double);
		paintBlockList.add(line_white_straight_double_thick);
		paintBlockList.add(line_yellow_straight_full);
		paintBlockList.add(line_yellow_straight_thick);
		paintBlockList.add(line_yellow_straight_double);
		paintBlockList.add(line_yellow_straight_double_thick);
		paintBlockList.add(line_red_straight_full);
		paintBlockList.add(line_red_straight_thick);
		paintBlockList.add(line_red_straight_double);
		paintBlockList.add(line_red_straight_double_thick);

		paintBlockList.add(line_white_side_double);
		paintBlockList.add(line_white_side_double_thick);
		paintBlockList.add(line_white_side_single);
		paintBlockList.add(line_white_side_single_thick);
		paintBlockList.add(line_yellow_side_double);
		paintBlockList.add(line_yellow_side_double_thick);
		paintBlockList.add(line_yellow_side_single);
		paintBlockList.add(line_yellow_side_single_thick);
		paintBlockList.add(line_red_side_double);
		paintBlockList.add(line_red_side_double_thick);
		paintBlockList.add(line_red_side_single);
		paintBlockList.add(line_red_side_single_thick);

		paintBlockList.add(line_white_far_side);
		paintBlockList.add(line_white_far_side_thick);
		paintBlockList.add(line_yellow_far_side);
		paintBlockList.add(line_yellow_far_side_thick);
		paintBlockList.add(line_red_far_side);
		paintBlockList.add(line_red_far_side_thick);

		paintBlockList.add(line_white_middle_half_double);
		paintBlockList.add(line_white_middle_dash_double);
		paintBlockList.add(line_white_middle_short);
		paintBlockList.add(line_white_filter_lane);
		paintBlockList.add(line_white_side_short);
		paintBlockList.add(line_white_thin_crossing);

		paintBlockList.add(line_yellow_middle_half_double);
		paintBlockList.add(line_yellow_middle_dash_double);
		paintBlockList.add(line_yellow_middle_short);
		paintBlockList.add(line_yellow_filter_lane);
		paintBlockList.add(line_yellow_side_short);
		paintBlockList.add(line_yellow_thin_crossing);

		paintBlockList.add(line_red_middle_half_double);
		paintBlockList.add(line_red_middle_dash_double);
		paintBlockList.add(line_red_middle_short);
		paintBlockList.add(line_red_filter_lane);
		paintBlockList.add(line_red_side_short);
		paintBlockList.add(line_red_thin_crossing);

		paintBlockList.add(white_wheelchair_icon);
		paintBlockList.add(white_chevron);
		paintBlockList.add(white_pedestrian);
		paintBlockList.add(white_merge_arrow);
		paintBlockList.add(white_give_way);

		paintBlockList.add(yellow_wheelchair_icon);
		paintBlockList.add(yellow_chevron);
		paintBlockList.add(yellow_pedestrian);
		paintBlockList.add(yellow_merge_arrow);
		paintBlockList.add(yellow_give_way);

		paintBlockList.add(red_wheelchair_icon);
		paintBlockList.add(red_chevron);
		paintBlockList.add(red_pedestrian);
		paintBlockList.add(red_merge_arrow);
		paintBlockList.add(red_give_way);

		paintBlockList.add(white_junction_filter_left);
		paintBlockList.add(white_junction_filter_left_thin);
		paintBlockList.add(white_junction_filter_left_empty);

		paintBlockList.add(white_junction_filter_right);
		paintBlockList.add(white_junction_filter_right_thin);
		paintBlockList.add(white_junction_filter_right_empty);

		paintBlockList.add(white_junction_fork_mid);
		paintBlockList.add(white_junction_fork_mid_thin);
		paintBlockList.add(white_junction_fork_chevron_mid);
		paintBlockList.add(white_junction_fork_chevron_mid_thin);

		paintBlockList.add(white_chevron_left_a);
		paintBlockList.add(white_chevron_left_b);
		paintBlockList.add(white_chevron_left_a_thin);
		paintBlockList.add(white_chevron_left_b_thin);

		paintBlockList.add(white_chevron_right_a);
		paintBlockList.add(white_chevron_right_b);
		paintBlockList.add(white_chevron_right_a_thin);
		paintBlockList.add(white_chevron_right_b_thin);

		paintBlockList.add(white_junction_side_line_connection);
		paintBlockList.add(white_junction_side_line_connection_thin);
		paintBlockList.add(white_junction_side_line_connection_thick_thick);
		paintBlockList.add(white_junction_mid_line_connection);

		paintBlockList.add(white_chevron_mid);
		paintBlockList.add(white_chevron_mid_left);
		paintBlockList.add(white_chevron_mid_right);

		paintBlockList.add(white_chevron_side_line);


		paintBlockList.add(yellow_junction_filter_left);
		paintBlockList.add(yellow_junction_filter_left_thin);
		paintBlockList.add(yellow_junction_filter_left_empty);

		paintBlockList.add(yellow_junction_filter_right);
		paintBlockList.add(yellow_junction_filter_right_thin);
		paintBlockList.add(yellow_junction_filter_right_empty);

		paintBlockList.add(yellow_junction_fork_mid);
		paintBlockList.add(yellow_junction_fork_mid_thin);
		paintBlockList.add(yellow_junction_fork_chevron_mid);
		paintBlockList.add(yellow_junction_fork_chevron_mid_thin);

		paintBlockList.add(yellow_chevron_left_a);
		paintBlockList.add(yellow_chevron_left_b);
		paintBlockList.add(yellow_chevron_left_a_thin);
		paintBlockList.add(yellow_chevron_left_b_thin);

		paintBlockList.add(yellow_chevron_right_a);
		paintBlockList.add(yellow_chevron_right_b);
		paintBlockList.add(yellow_chevron_right_a_thin);
		paintBlockList.add(yellow_chevron_right_b_thin);

		paintBlockList.add(yellow_junction_side_line_connection);
		paintBlockList.add(yellow_junction_side_line_connection_thin);
		paintBlockList.add(yellow_junction_side_line_connection_thick_thick);
		paintBlockList.add(yellow_junction_mid_line_connection);

		paintBlockList.add(yellow_chevron_mid);
		paintBlockList.add(yellow_chevron_mid_left);
		paintBlockList.add(yellow_chevron_mid_right);

		paintBlockList.add(yellow_chevron_side_line);


		paintBlockList.add(red_junction_filter_left);
		paintBlockList.add(red_junction_filter_left_thin);
		paintBlockList.add(red_junction_filter_left_empty);

		paintBlockList.add(red_junction_filter_right);
		paintBlockList.add(red_junction_filter_right_thin);
		paintBlockList.add(red_junction_filter_right_empty);

		paintBlockList.add(red_junction_fork_mid);
		paintBlockList.add(red_junction_fork_mid_thin);
		paintBlockList.add(red_junction_fork_chevron_mid);
		paintBlockList.add(red_junction_fork_chevron_mid_thin);

		paintBlockList.add(red_chevron_left_a);
		paintBlockList.add(red_chevron_left_b);
		paintBlockList.add(red_chevron_left_a_thin);
		paintBlockList.add(red_chevron_left_b_thin);

		paintBlockList.add(red_chevron_right_a);
		paintBlockList.add(red_chevron_right_b);
		paintBlockList.add(red_chevron_right_a_thin);
		paintBlockList.add(red_chevron_right_b_thin);

		paintBlockList.add(red_junction_side_line_connection);
		paintBlockList.add(red_junction_side_line_connection_thin);
		paintBlockList.add(red_junction_side_line_connection_thick_thick);
		paintBlockList.add(red_junction_mid_line_connection);

		paintBlockList.add(red_chevron_mid);
		paintBlockList.add(red_chevron_mid_left);
		paintBlockList.add(red_chevron_mid_right);

		paintBlockList.add(red_chevron_side_line);


		paintBlockList.add(white_slow);
		paintBlockList.add(white_stop);
		paintBlockList.add(white_bike);
		paintBlockList.add(white_bus);
		paintBlockList.add(white_taxi);
		paintBlockList.add(white_lane);
		paintBlockList.add(white_keep);
		paintBlockList.add(white_clear);
		paintBlockList.add(white_turn);
		paintBlockList.add(white_left);
		paintBlockList.add(white_right);
		paintBlockList.add(white_only);
		paintBlockList.add(white_no);
		paintBlockList.add(white_entry);
		paintBlockList.add(white_bike_icon);
		paintBlockList.add(white_town);
		paintBlockList.add(white_city);
		paintBlockList.add(white_ctre);

		paintBlockList.add(yellow_slow);
		paintBlockList.add(yellow_stop);
		paintBlockList.add(yellow_bike);
		paintBlockList.add(yellow_bus);
		paintBlockList.add(yellow_taxi);
		paintBlockList.add(yellow_lane);
		paintBlockList.add(yellow_keep);
		paintBlockList.add(yellow_clear);
		paintBlockList.add(yellow_turn);
		paintBlockList.add(yellow_left);
		paintBlockList.add(yellow_right);
		paintBlockList.add(yellow_only);
		paintBlockList.add(yellow_no);
		paintBlockList.add(yellow_entry);
		paintBlockList.add(yellow_bike_icon);
		paintBlockList.add(yellow_town);
		paintBlockList.add(yellow_city);
		paintBlockList.add(yellow_ctre);

		paintBlockList.add(red_slow);
		paintBlockList.add(red_stop);
		paintBlockList.add(red_bike);
		paintBlockList.add(red_bus);
		paintBlockList.add(red_taxi);
		paintBlockList.add(red_lane);
		paintBlockList.add(red_keep);
		paintBlockList.add(red_clear);
		paintBlockList.add(red_turn);
		paintBlockList.add(red_left);
		paintBlockList.add(red_right);
		paintBlockList.add(red_only);
		paintBlockList.add(red_no);
		paintBlockList.add(red_entry);
		paintBlockList.add(red_bike_icon);
		paintBlockList.add(red_town);
		paintBlockList.add(red_city);
		paintBlockList.add(red_ctre);

		paintBlockList.add(paint_letter_white_ab);
		paintBlockList.add(paint_letter_white_cd);
		paintBlockList.add(paint_letter_white_ef);
		paintBlockList.add(paint_letter_white_gh);
		paintBlockList.add(paint_letter_white_ij);
		paintBlockList.add(paint_letter_white_kl);
		paintBlockList.add(paint_letter_white_mn);
		paintBlockList.add(paint_letter_white_op);
		paintBlockList.add(paint_letter_white_qr);
		paintBlockList.add(paint_letter_white_st);
		paintBlockList.add(paint_letter_white_uv);
		paintBlockList.add(paint_letter_white_wx);
		paintBlockList.add(paint_letter_white_yz);
		paintBlockList.add(paint_letter_white_01);
		paintBlockList.add(paint_letter_white_23);
		paintBlockList.add(paint_letter_white_45);
		paintBlockList.add(paint_letter_white_67);
		paintBlockList.add(paint_letter_white_89);
		paintBlockList.add(paint_letter_white_punct_question_exclamation);
		paintBlockList.add(paint_letter_white_punct_hash_slash);

		paintBlockList.add(paint_letter_yellow_ab);
		paintBlockList.add(paint_letter_yellow_cd);
		paintBlockList.add(paint_letter_yellow_ef);
		paintBlockList.add(paint_letter_yellow_gh);
		paintBlockList.add(paint_letter_yellow_ij);
		paintBlockList.add(paint_letter_yellow_kl);
		paintBlockList.add(paint_letter_yellow_mn);
		paintBlockList.add(paint_letter_yellow_op);
		paintBlockList.add(paint_letter_yellow_qr);
		paintBlockList.add(paint_letter_yellow_st);
		paintBlockList.add(paint_letter_yellow_uv);
		paintBlockList.add(paint_letter_yellow_wx);
		paintBlockList.add(paint_letter_yellow_yz);
		paintBlockList.add(paint_letter_yellow_01);
		paintBlockList.add(paint_letter_yellow_23);
		paintBlockList.add(paint_letter_yellow_45);
		paintBlockList.add(paint_letter_yellow_67);
		paintBlockList.add(paint_letter_yellow_89);
		paintBlockList.add(paint_letter_yellow_punct_question_exclamation);
		paintBlockList.add(paint_letter_yellow_punct_hash_slash);

		paintBlockList.add(paint_letter_red_ab);
		paintBlockList.add(paint_letter_red_cd);
		paintBlockList.add(paint_letter_red_ef);
		paintBlockList.add(paint_letter_red_gh);
		paintBlockList.add(paint_letter_red_ij);
		paintBlockList.add(paint_letter_red_kl);
		paintBlockList.add(paint_letter_red_mn);
		paintBlockList.add(paint_letter_red_op);
		paintBlockList.add(paint_letter_red_qr);
		paintBlockList.add(paint_letter_red_st);
		paintBlockList.add(paint_letter_red_uv);
		paintBlockList.add(paint_letter_red_wx);
		paintBlockList.add(paint_letter_red_yz);
		paintBlockList.add(paint_letter_red_01);
		paintBlockList.add(paint_letter_red_23);
		paintBlockList.add(paint_letter_red_45);
		paintBlockList.add(paint_letter_red_67);
		paintBlockList.add(paint_letter_red_89);
		paintBlockList.add(paint_letter_red_punct_question_exclamation);
		paintBlockList.add(paint_letter_red_punct_hash_slash);

		paintBlockList.add(hatch_box_white);
		paintBlockList.add(line_white_crossing_diagonal);
		paintBlockList.add(white_crossing_paint);
		paintBlockList.add(white_arrow);
		paintBlockList.add(white_arrow_line);
		paintBlockList.add(white_arrow_diagonal);
		paintBlockList.add(white_junction_a);
		paintBlockList.add(white_junction_b);

		paintBlockList.add(hatch_box_yellow);
		paintBlockList.add(line_yellow_crossing_diagonal);
		paintBlockList.add(yellow_crossing_paint);
		paintBlockList.add(yellow_arrow);
		paintBlockList.add(yellow_arrow_line);
		paintBlockList.add(yellow_arrow_diagonal);
		paintBlockList.add(yellow_junction_a);
		paintBlockList.add(yellow_junction_b);

		paintBlockList.add(hatch_box_red);
		paintBlockList.add(line_red_crossing_diagonal);
		paintBlockList.add(red_crossing_paint);
		paintBlockList.add(red_arrow);
		paintBlockList.add(red_arrow_line);
		paintBlockList.add(red_arrow_diagonal);
		paintBlockList.add(red_junction_a);
		paintBlockList.add(red_junction_b);

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
	}

	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		for (int i = 0; i < roadBlockList.size(); i++)  { registry.register(new RoadItemBlock(roadBlockList.get(i)).setRegistryName(roadBlockList.get(i).getRegistryName())); }
		for (int i = 0; i < roadBlockDiagonalList.size(); i++)  { registry.register(new RoadItemBlock(roadBlockDiagonalList.get(i)).setRegistryName(roadBlockDiagonalList.get(i).getRegistryName())); }
		for (int i = 0; i < paintBlockList.size(); i++)  { registry.register(new RoadItemBlock(paintBlockList.get(i)).setRegistryName(paintBlockList.get(i).getRegistryName())); }
		for (int i = 0; i < genericList.size(); i++)  { registry.register(new RoadItemBlock(genericList.get(i)).setRegistryName(genericList.get(i).getRegistryName())); }
		for (int i = 0; i < catsEyeList.size(); i++)  { registry.register(new RoadItemBlock(catsEyeList.get(i)).setRegistryName(catsEyeList.get(i).getRegistryName())); }

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
	}

	public static void registerModels() {
		for (int i = 0; i < roadBlockList.size(); i++)  { roadBlockList.get(i).initModel(); }
		for (int i = 0; i < roadBlockDiagonalList.size(); i++)  { roadBlockDiagonalList.get(i).initModel(); }
		for (int i = 0; i < paintBlockList.size(); i++)  { paintBlockList.get(i).initModel(); }
		for (int i = 0; i < genericList.size(); i++)  { genericList.get(i).initModel(); }
		for (int i = 0; i < catsEyeList.size(); i++)  { catsEyeList.get(i).initModel(); }

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

	//We only register white variants. Yellow and red can be taken from the white.
	public static void registerPaintGunEntries() {
		PaintGunItemRegistry.registerLine(line_white_straight_full);
		PaintGunItemRegistry.registerLine(line_white_straight_full, 2);
		PaintGunItemRegistry.registerLine(line_white_straight_thick);
		PaintGunItemRegistry.registerLine(line_white_straight_thick, 2);
		PaintGunItemRegistry.registerLine(line_white_straight_double);
		PaintGunItemRegistry.registerLine(line_white_straight_double, 2);
		PaintGunItemRegistry.registerLine(line_white_straight_double_thick);
		PaintGunItemRegistry.registerLine(line_white_straight_double_thick, 2);

		PaintGunItemRegistry.registerLine(line_white_side_double);
		PaintGunItemRegistry.registerLine(line_white_side_double_thick);
		PaintGunItemRegistry.registerLine(line_white_side_single);
		PaintGunItemRegistry.registerLine(line_white_side_single_thick);

		PaintGunItemRegistry.registerLine(line_white_far_side);
		PaintGunItemRegistry.registerLine(line_white_far_side_thick);

		PaintGunItemRegistry.registerLine(line_white_middle_half_double);
		PaintGunItemRegistry.registerLine(line_white_middle_dash_double);
		PaintGunItemRegistry.registerLine(line_white_middle_short);
		PaintGunItemRegistry.registerLine(line_white_filter_lane);
		PaintGunItemRegistry.registerLine(line_white_side_short);

		//-----------------------------------------------------

		PaintGunItemRegistry.registerIcons(white_wheelchair_icon);
		PaintGunItemRegistry.registerIcons(white_chevron);
		PaintGunItemRegistry.registerIcons(white_pedestrian);
		PaintGunItemRegistry.registerIcons(white_merge_arrow);
		PaintGunItemRegistry.registerIcons(white_give_way);

		PaintGunItemRegistry.registerIcons(white_arrow_line);
		PaintGunItemRegistry.registerIcons(white_arrow);
		PaintGunItemRegistry.registerIcons(white_arrow, 4);
		PaintGunItemRegistry.registerIcons(white_arrow_diagonal, 4);
		PaintGunItemRegistry.registerIcons(white_arrow_diagonal);

		PaintGunItemRegistry.registerIcons(white_crossing_paint);
		PaintGunItemRegistry.registerIcons(line_white_crossing_diagonal);

		PaintGunItemRegistry.registerIcons(white_junction_a, 4);
		PaintGunItemRegistry.registerIcons(white_junction_a, 8);
		PaintGunItemRegistry.registerIcons(white_junction_a, 0);
		PaintGunItemRegistry.registerIcons(white_junction_b, 0);
		PaintGunItemRegistry.registerIcons(white_junction_b, 8);
		PaintGunItemRegistry.registerIcons(white_junction_b, 4);

		PaintGunItemRegistry.registerIcons(hatch_box_white);

		PaintGunItemRegistry.registerIcons(line_white_thin_crossing);

		//-----------------------------------------------------

		PaintGunItemRegistry.registerLetters(paint_letter_white_ab);
		PaintGunItemRegistry.registerLetters(paint_letter_white_cd);
		PaintGunItemRegistry.registerLetters(paint_letter_white_ef);
		PaintGunItemRegistry.registerLetters(paint_letter_white_gh);
		PaintGunItemRegistry.registerLetters(paint_letter_white_ij);
		PaintGunItemRegistry.registerLetters(paint_letter_white_kl);
		PaintGunItemRegistry.registerLetters(paint_letter_white_mn);
		PaintGunItemRegistry.registerLetters(paint_letter_white_op);
		PaintGunItemRegistry.registerLetters(paint_letter_white_qr);
		PaintGunItemRegistry.registerLetters(paint_letter_white_st);
		PaintGunItemRegistry.registerLetters(paint_letter_white_uv);
		PaintGunItemRegistry.registerLetters(paint_letter_white_wx);
		PaintGunItemRegistry.registerLetters(paint_letter_white_yz);
		PaintGunItemRegistry.registerLetters(paint_letter_white_01);
		PaintGunItemRegistry.registerLetters(paint_letter_white_23);
		PaintGunItemRegistry.registerLetters(paint_letter_white_45);
		PaintGunItemRegistry.registerLetters(paint_letter_white_67);
		PaintGunItemRegistry.registerLetters(paint_letter_white_89);
		PaintGunItemRegistry.registerLetters(paint_letter_white_punct_question_exclamation);
		PaintGunItemRegistry.registerLetters(paint_letter_white_punct_hash_slash);

		//-----------------------------------------------------

		PaintGunItemRegistry.registerText(white_slow);
		PaintGunItemRegistry.registerText(white_stop);
		PaintGunItemRegistry.registerText(white_bike);
		PaintGunItemRegistry.registerText(white_bus);
		PaintGunItemRegistry.registerText(white_taxi);
		PaintGunItemRegistry.registerText(white_lane);
		PaintGunItemRegistry.registerText(white_keep);
		PaintGunItemRegistry.registerText(white_clear);
		PaintGunItemRegistry.registerText(white_turn);
		PaintGunItemRegistry.registerText(white_left);
		PaintGunItemRegistry.registerText(white_right);
		PaintGunItemRegistry.registerText(white_only);
		PaintGunItemRegistry.registerText(white_no);
		PaintGunItemRegistry.registerText(white_entry);
		PaintGunItemRegistry.registerText(white_town);
		PaintGunItemRegistry.registerText(white_city);
		PaintGunItemRegistry.registerText(white_ctre);
		PaintGunItemRegistry.registerText(white_bike_icon);

		PaintGunItemRegistry.registerText(white_slow, 4);
		PaintGunItemRegistry.registerText(white_stop, 4);
		PaintGunItemRegistry.registerText(white_bike, 4);
		PaintGunItemRegistry.registerText(white_bus, 4);
		PaintGunItemRegistry.registerText(white_taxi, 4);
		PaintGunItemRegistry.registerText(white_lane, 4);
		PaintGunItemRegistry.registerText(white_keep, 4);
		PaintGunItemRegistry.registerText(white_clear, 4);
		PaintGunItemRegistry.registerText(white_turn, 4);
		PaintGunItemRegistry.registerText(white_left, 4);
		PaintGunItemRegistry.registerText(white_right, 4);
		PaintGunItemRegistry.registerText(white_only, 4);
		PaintGunItemRegistry.registerText(white_no, 4);
		PaintGunItemRegistry.registerText(white_entry, 4);
		PaintGunItemRegistry.registerText(white_town, 4);
		PaintGunItemRegistry.registerText(white_city, 4);
		PaintGunItemRegistry.registerText(white_ctre, 4);
		PaintGunItemRegistry.registerText(white_bike_icon, 4);

		//-----------------------------------------------------

		PaintGunItemRegistry.registerJunction(white_chevron_left_a);
		PaintGunItemRegistry.registerJunction(white_chevron_mid);
		PaintGunItemRegistry.registerJunction(white_chevron_right_a);
		PaintGunItemRegistry.registerJunction(white_chevron_left_a_thin);
		PaintGunItemRegistry.registerJunction(white_chevron_mid, 8);
		PaintGunItemRegistry.registerJunction(white_chevron_right_a_thin);

		PaintGunItemRegistry.registerJunction(white_junction_filter_left);
		PaintGunItemRegistry.registerJunction(white_junction_filter_right);
		PaintGunItemRegistry.registerJunction(white_junction_filter_left_thin);
		PaintGunItemRegistry.registerJunction(white_junction_filter_right_thin);
		PaintGunItemRegistry.registerJunction(white_junction_filter_left_empty);
		PaintGunItemRegistry.registerJunction(white_junction_filter_right_empty);

		PaintGunItemRegistry.registerJunction(white_chevron_mid_right);
		PaintGunItemRegistry.registerJunction(white_chevron_mid_left);
		PaintGunItemRegistry.registerJunction(white_chevron_mid_right, 8);
		PaintGunItemRegistry.registerJunction(white_chevron_mid_left, 8);

		PaintGunItemRegistry.registerJunction(white_chevron_side_line);

		PaintGunItemRegistry.registerJunction(white_junction_fork_mid);
		PaintGunItemRegistry.registerJunction(white_junction_fork_mid_thin);
		PaintGunItemRegistry.registerJunction(white_junction_fork_chevron_mid);
		PaintGunItemRegistry.registerJunction(white_junction_fork_chevron_mid_thin);

		PaintGunItemRegistry.registerJunction(white_junction_side_line_connection, 12);
		PaintGunItemRegistry.registerJunction(white_junction_side_line_connection, 8);
		PaintGunItemRegistry.registerJunction(white_junction_side_line_connection, 0);
		PaintGunItemRegistry.registerJunction(white_junction_side_line_connection, 4);

		PaintGunItemRegistry.registerJunction(white_junction_side_line_connection_thin, 4);
		PaintGunItemRegistry.registerJunction(white_junction_side_line_connection_thin, 12);
		PaintGunItemRegistry.registerJunction(white_junction_side_line_connection_thin, 0);
		PaintGunItemRegistry.registerJunction(white_junction_side_line_connection_thin, 8);

		PaintGunItemRegistry.registerJunction(white_junction_side_line_connection_thick_thick, 0);
		PaintGunItemRegistry.registerJunction(white_junction_side_line_connection_thick_thick, 4);
		PaintGunItemRegistry.registerJunction(white_junction_side_line_connection_thick_thick, 8);
		PaintGunItemRegistry.registerJunction(white_junction_side_line_connection_thick_thick, 12);

		PaintGunItemRegistry.registerJunction(white_junction_mid_line_connection, 0);
		PaintGunItemRegistry.registerJunction(white_junction_mid_line_connection, 4);
		PaintGunItemRegistry.registerJunction(white_junction_mid_line_connection, 8);
		PaintGunItemRegistry.registerJunction(white_junction_mid_line_connection, 12);

		FurenikusRoads.logger.info("REGISTRATION COMPLETE! " + PaintGunItemRegistry.lines.size() + " lines, " + PaintGunItemRegistry.icons.size() + " icons, " + PaintGunItemRegistry.letters.size() + " letters, " + PaintGunItemRegistry.text.size() + " texts, " + PaintGunItemRegistry.junction.size() + " junctions.");
	}
}
