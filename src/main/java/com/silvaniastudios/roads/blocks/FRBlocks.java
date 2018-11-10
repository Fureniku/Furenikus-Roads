package com.silvaniastudios.roads.blocks;

import java.util.ArrayList;

import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.items.RoadItemBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class FRBlocks {
	
	public static RoadsConfig.Modules cfg = new RoadsConfig.Modules();
	
	public static ArrayList<RoadBlock> roadBlockList = new ArrayList<RoadBlock>();
	
	
	public static ArrayList<LinePaintBlock> straightLineWhiteList = new ArrayList<LinePaintBlock>();
	public static ArrayList<SideLinePaintBlock> sideLineWhiteList = new ArrayList<SideLinePaintBlock>();
	public static ArrayList<FarSideLinePaintBlock> farSideLineWhiteList = new ArrayList<FarSideLinePaintBlock>();
	public static ArrayList<SimpleLinePaintBlock> simpleLineWhiteList = new ArrayList<SimpleLinePaintBlock>();
	public static ArrayList<IconPaintBlock> iconWhiteList = new ArrayList<IconPaintBlock>();
	public static ArrayList<Connected1x2PaintBlock> tallIconWhiteList = new ArrayList<Connected1x2PaintBlock>();
	
	/*public static ArrayList<ChevronMidPaintBlock> junctionLinesMidWhiteList = new ArrayList<ChevronMidPaintBlock>();
	public static ArrayList<ChevronSidePaintBlock> junctionLinesSideWhiteList = new ArrayList<ChevronSidePaintBlock>();
	public static ArrayList<Connected1x4PaintBlock> junctionLinesWhiteList = new ArrayList<Connected1x4PaintBlock>();*/
	
	public static ArrayList<ChevronPaintBlock> chevronWhiteList = new ArrayList<ChevronPaintBlock>();
	public static ArrayList<JunctionFilterLinePaintBlock> junctionLinesWhiteList = new ArrayList<JunctionFilterLinePaintBlock>();
	
	public static ArrayList<Connected3x1PaintBlock> textWhiteList = new ArrayList<Connected3x1PaintBlock>();
	public static ArrayList<LetterPaintBlock> letterWhiteList = new ArrayList<LetterPaintBlock>();
	
	
	public static ArrayList<LinePaintBlock> straightLineYellowList = new ArrayList<LinePaintBlock>();
	public static ArrayList<SideLinePaintBlock> sideLineYellowList = new ArrayList<SideLinePaintBlock>();
	public static ArrayList<FarSideLinePaintBlock> farSideLineYellowList = new ArrayList<FarSideLinePaintBlock>();
	public static ArrayList<SimpleLinePaintBlock> simpleLineYellowList = new ArrayList<SimpleLinePaintBlock>();
	public static ArrayList<IconPaintBlock> iconYellowList = new ArrayList<IconPaintBlock>();
	
	
	public static ArrayList<ChevronMidPaintBlock> junctionLinesMidYellowList = new ArrayList<ChevronMidPaintBlock>();
	public static ArrayList<ChevronSidePaintBlock> junctionLinesSideYellowList = new ArrayList<ChevronSidePaintBlock>();
	public static ArrayList<Connected1x4PaintBlock> junctionLinesYellowList = new ArrayList<Connected1x4PaintBlock>();
	
	public static ArrayList<Connected3x1PaintBlock> textYellowList = new ArrayList<Connected3x1PaintBlock>();
	public static ArrayList<LetterPaintBlock> letterYellowList = new ArrayList<LetterPaintBlock>();
	
	
	public static ArrayList<LinePaintBlock> straightLineRedList = new ArrayList<LinePaintBlock>();
	public static ArrayList<SideLinePaintBlock> sideLineRedList = new ArrayList<SideLinePaintBlock>();
	public static ArrayList<FarSideLinePaintBlock> farSideLineRedList = new ArrayList<FarSideLinePaintBlock>();
	public static ArrayList<SimpleLinePaintBlock> simpleLineRedList = new ArrayList<SimpleLinePaintBlock>();
	public static ArrayList<IconPaintBlock> iconRedList = new ArrayList<IconPaintBlock>();
	
	
	public static ArrayList<ChevronMidPaintBlock> junctionLinesMidRedList = new ArrayList<ChevronMidPaintBlock>();
	public static ArrayList<ChevronSidePaintBlock> junctionLinesSideRedList = new ArrayList<ChevronSidePaintBlock>();
	public static ArrayList<Connected1x4PaintBlock> junctionLinesRedList = new ArrayList<Connected1x4PaintBlock>();
	
	public static ArrayList<Connected3x1PaintBlock> textRedList = new ArrayList<Connected3x1PaintBlock>();
	public static ArrayList<LetterPaintBlock> letterRedList = new ArrayList<LetterPaintBlock>();
	
	//Tarmac variants
	public static RoadBlock road_block_standard = new RoadBlock("road_block_standard", Material.ROCK);
	public static RoadBlock road_block_concrete_1 = new RoadBlock("road_block_concrete_1", Material.ROCK);
	public static RoadBlock road_block_concrete_2 = new RoadBlock("road_block_concrete_2", Material.ROCK);
	public static RoadBlock road_block_light = new RoadBlock("road_block_light", Material.ROCK);
	public static RoadBlock road_block_fine = new RoadBlock("road_block_fine", Material.ROCK);
	public static RoadBlock road_block_dark = new RoadBlock("road_block_dark", Material.ROCK);
	public static RoadBlock road_block_pale = new RoadBlock("road_block_pale", Material.ROCK);
	public static RoadBlock road_block_red = new RoadBlock("road_block_red", Material.ROCK);
	public static RoadBlock road_block_blue = new RoadBlock("road_block_blue", Material.ROCK);
	public static RoadBlock road_block_white = new RoadBlock("road_block_white", Material.ROCK);
	public static RoadBlock road_block_yellow = new RoadBlock("road_block_yellow", Material.ROCK);
	public static RoadBlock road_block_green = new RoadBlock("road_block_green", Material.ROCK);
	public static RoadBlock road_block_muddy = new RoadBlock("road_block_muddy", Material.GROUND);
	public static RoadBlock road_block_muddy_dried = new RoadBlock("road_block_muddy_dried", Material.GROUND);
	
	public static RoadBlock road_block_stone = new RoadBlock("road_block_stone", Material.ROCK);
	public static RoadBlock road_block_grass = new RoadBlock("road_block_grass", Material.GRASS);
	public static RoadBlock road_block_dirt = new RoadBlock("road_block_dirt", Material.GROUND);
	public static RoadBlock road_block_gravel = new RoadBlock("road_block_gravel", Material.SAND);
	public static RoadBlock road_block_sand = new RoadBlock("road_block_sand", Material.SAND);

	public static SidewalkBlock kerb_standard = new SidewalkBlock("kerb_standard", Material.ROCK, false);
	public static SidewalkBlock kerb_connect = new SidewalkBlock("kerb_connect", Material.ROCK, true);
	//kerb one-sided w/ connections
		
	public static StreetBlock street_block = new StreetBlock("street_block");
	//tactile bump under-side tile
	
	public static TactileCrossingBumps tactile_crossing_bumps = new TactileCrossingBumps("tactile_crossing_bumps");
	//pedestrian crossing metal markers
	
	public static LinePaintBlock line_white_straight_full = new LinePaintBlock("line_white_straight_full");
	public static LinePaintBlock line_white_straight_thick = new LinePaintBlock("line_white_straight_thick");
	public static LinePaintBlock line_white_straight_double = new LinePaintBlock("line_white_straight_double");
	public static SideLinePaintBlock line_white_side_double = new SideLinePaintBlock("line_white_side_double");
	public static SideLinePaintBlock line_white_side_single = new SideLinePaintBlock("line_white_side_single");
	public static SideLinePaintBlock line_white_side_single_thick = new SideLinePaintBlock("line_white_side_single_thick");
	public static FarSideLinePaintBlock line_white_far_side = new FarSideLinePaintBlock("line_white_far_side");
	public static FarSideLinePaintBlock line_white_far_side_thick = new FarSideLinePaintBlock("line_white_far_side_thick");
	public static HatchBoxPaintBlock hatch_box_white = new HatchBoxPaintBlock("hatch_box_white");
	public static Connected1x4PaintBlock line_white_crossing_diagonal = new Connected1x4PaintBlock("line_white_crossing_diagonal", false, false);
	public static CrossingPaintBlock white_crossing_paint = new CrossingPaintBlock("white_crossing_paint");
	public static SimpleLinePaintBlock line_white_middle_half_double = new SimpleLinePaintBlock("line_white_middle_half_double");
	public static SimpleLinePaintBlock line_white_middle_dash_double = new SimpleLinePaintBlock("line_white_middle_dash_double");
	public static SimpleLinePaintBlock line_white_middle_short = new SimpleLinePaintBlock("line_white_middle_short");
	public static SimpleLinePaintBlock line_white_filter_lane = new SimpleLinePaintBlock("line_white_filter_lane");
	public static SimpleLinePaintBlock line_white_side_short = new SimpleLinePaintBlock("line_white_side_short");
	public static ArrowPaintBlock white_arrow = new ArrowPaintBlock("white_arrow");
	public static ArrowLinePaintBlock white_arrow_line = new ArrowLinePaintBlock("white_arrow_line");
	public static ArrowDiagonalPaintBlock white_arrow_diagonal = new ArrowDiagonalPaintBlock("white_arrow_diagonal"); //TODO
	public static IconPaintBlock white_wheelchair_icon = new IconPaintBlock("white_wheelchair_icon");
	public static JunctionPaintBlock white_junction_a = new JunctionPaintBlock("white_junction_a");
	public static JunctionPaintBlock white_junction_b = new JunctionPaintBlock("white_junction_b");
	public static IconPaintBlock white_chevron = new IconPaintBlock("white_chevron");
	
	public static Connected1x2PaintBlock white_pedestrian = new Connected1x2PaintBlock("white_pedestrian");
	public static Connected1x2PaintBlock white_merge_arrow = new Connected1x2PaintBlock("white_merge_arrow");
	public static Connected1x2PaintBlock white_give_way = new Connected1x2PaintBlock("white_give_way");
	
	/*public static Connected1x4PaintBlock white_junction_filter_mid = new Connected1x4PaintBlock("white_junction_filter_mid", false, false);
	public static Connected1x4PaintBlock white_junction_filter_mid_thin = new Connected1x4PaintBlock("white_junction_filter_mid_thin", false, false);
	public static ChevronMidPaintBlock white_junction_chevron_mid = new ChevronMidPaintBlock("white_junction_chevron_mid", true);
	public static ChevronMidPaintBlock white_junction_chevron_mid_left = new ChevronMidPaintBlock("white_junction_chevron_mid_left", true);
	public static ChevronMidPaintBlock white_junction_chevron_mid_right = new ChevronMidPaintBlock("white_junction_chevron_mid_right", true);
	public static ChevronMidPaintBlock white_junction_chevron_mid_thin = new ChevronMidPaintBlock("white_junction_chevron_mid_thin", true);
	public static ChevronMidPaintBlock white_junction_chevron_mid_left_thin = new ChevronMidPaintBlock("white_junction_chevron_mid_left_thin", true);
	public static ChevronMidPaintBlock white_junction_chevron_mid_right_thin = new ChevronMidPaintBlock("white_junction_chevron_mid_right_thin", true);
	public static ChevronMidPaintBlock white_junction_left_line_left = new ChevronMidPaintBlock("white_junction_left_line_left", true);
	public static ChevronMidPaintBlock white_junction_left_line_right = new ChevronMidPaintBlock("white_junction_left_line_right", true);
	public static ChevronMidPaintBlock white_junction_right_line_left = new ChevronMidPaintBlock("white_junction_right_line_left", true);
	public static ChevronMidPaintBlock white_junction_right_line_right = new ChevronMidPaintBlock("white_junction_right_line_right", true);
	public static ChevronMidPaintBlock white_junction_both_line = new ChevronMidPaintBlock("white_junction_both_line", true);
	
	public static ChevronSidePaintBlock white_junction_chevron = new ChevronSidePaintBlock("white_junction_chevron");
	public static ChevronSidePaintBlock white_junction_chevron_low = new ChevronSidePaintBlock("white_junction_chevron_low");
	public static ChevronSidePaintBlock white_junction_chevron_high = new ChevronSidePaintBlock("white_junction_chevron_high");
	public static ChevronSidePaintBlock white_junction_chevron_thin = new ChevronSidePaintBlock("white_junction_chevron_thin");
	public static ChevronSidePaintBlock white_junction_chevron_low_thin = new ChevronSidePaintBlock("white_junction_chevron_low_thin");
	public static ChevronSidePaintBlock white_junction_chevron_high_thin = new ChevronSidePaintBlock("white_junction_chevron_high_thin");*/
	
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
	
	public static MultiIconPaintBlock white_junction_side_line_connection = new MultiIconPaintBlock("white_junction_side_line_connection");
	public static MultiIconPaintBlock white_junction_side_line_connection_thin = new MultiIconPaintBlock("white_junction_side_line_connection_thin");
	
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
	public static SideLinePaintBlock line_yellow_side_double = new SideLinePaintBlock("line_yellow_side_double");
	public static SideLinePaintBlock line_yellow_side_single = new SideLinePaintBlock("line_yellow_side_single");
	public static FarSideLinePaintBlock line_yellow_far_side = new FarSideLinePaintBlock("line_yellow_far_side");
	
	
	public static HatchBoxPaintBlock hatch_box_yellow = new HatchBoxPaintBlock("hatch_box_yellow");
	public static Connected1x4PaintBlock line_yellow_crossing_diagonal = new Connected1x4PaintBlock("line_yellow_crossing_diagonal", false, false);
	public static CrossingPaintBlock yellow_crossing_paint = new CrossingPaintBlock("yellow_crossing_paint");
	public static IconPaintBlock line_yellow_middle_half_double = new IconPaintBlock("line_yellow_middle_half_double");
	public static IconPaintBlock line_yellow_middle_dash_double = new IconPaintBlock("line_yellow_middle_dash_double");
	public static IconPaintBlock line_yellow_middle_short = new IconPaintBlock("line_yellow_middle_short");
	public static IconPaintBlock line_yellow_filter_lane = new IconPaintBlock("line_yellow_filter_lane");
	public static IconPaintBlock line_yellow_side_short = new IconPaintBlock("line_yellow_side_short");
	
	
	
	
	public static LinePaintBlock line_red_straight_full = new LinePaintBlock("line_red_straight_full");
	public static LinePaintBlock line_red_straight_thick = new LinePaintBlock("line_red_straight_thick");
	public static LinePaintBlock line_red_straight_double = new LinePaintBlock("line_red_straight_double");
	public static SideLinePaintBlock line_red_side_double = new SideLinePaintBlock("line_red_side_double");
	public static SideLinePaintBlock line_red_side_single = new SideLinePaintBlock("line_red_side_single");
	public static FarSideLinePaintBlock line_red_far_side = new FarSideLinePaintBlock("line_red_far_side");
	
	
	public static HatchBoxPaintBlock hatch_box_red = new HatchBoxPaintBlock("hatch_box_red");
	public static Connected1x4PaintBlock line_red_crossing_diagonal = new Connected1x4PaintBlock("line_red_crossing_diagonal", false, false);
	public static CrossingPaintBlock red_crossing_paint = new CrossingPaintBlock("red_crossing_paint");
	public static IconPaintBlock line_red_middle_half_double = new IconPaintBlock("line_red_middle_half_double");
	public static IconPaintBlock line_red_middle_dash_double = new IconPaintBlock("line_red_middle_dash_double");
	public static IconPaintBlock line_red_middle_short = new IconPaintBlock("line_red_middle_short");
	public static IconPaintBlock line_red_filter_lane = new IconPaintBlock("line_red_filter_lane");
	public static IconPaintBlock line_red_side_short = new IconPaintBlock("line_red_side_short");
	
	
	
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

	public static void register(IForgeRegistry<Block> registry) {
		if (cfg.road_blocks) {
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
		}
		
		if (cfg.white_basic_paints) {
			straightLineWhiteList.add(line_white_straight_full);
			straightLineWhiteList.add(line_white_straight_thick);
			straightLineWhiteList.add(line_white_straight_double);
			
			sideLineWhiteList.add(line_white_side_double);
			sideLineWhiteList.add(line_white_side_single);
			sideLineWhiteList.add(line_white_side_single_thick);
			
			farSideLineWhiteList.add(line_white_far_side);
			farSideLineWhiteList.add(line_white_far_side_thick);
	
			simpleLineWhiteList.add(line_white_middle_half_double);
			simpleLineWhiteList.add(line_white_middle_dash_double);
			simpleLineWhiteList.add(line_white_middle_short);
			simpleLineWhiteList.add(line_white_filter_lane);
			simpleLineWhiteList.add(line_white_side_short);
		}
		
		if (cfg.white_icons) {
			iconWhiteList.add(white_wheelchair_icon);
			iconWhiteList.add(white_chevron);
			tallIconWhiteList.add(white_pedestrian);
			tallIconWhiteList.add(white_merge_arrow);
			tallIconWhiteList.add(white_give_way);
		}
		
		if (cfg.white_junction) {
			junctionLinesWhiteList.add(white_junction_filter_left);
			junctionLinesWhiteList.add(white_junction_filter_left_thin);
			junctionLinesWhiteList.add(white_junction_filter_left_empty);
			
			junctionLinesWhiteList.add(white_junction_filter_right);
			junctionLinesWhiteList.add(white_junction_filter_right_thin);
			junctionLinesWhiteList.add(white_junction_filter_right_empty);
			/*junctionLinesWhiteList.add(white_junction_filter_right);
			junctionLinesWhiteList.add(white_junction_filter_left_thin);
			junctionLinesWhiteList.add(white_junction_filter_right_thin);
			junctionLinesWhiteList.add(white_junction_filter_chevron_left_a);
			junctionLinesWhiteList.add(white_junction_filter_chevron_left_b);
			junctionLinesWhiteList.add(white_junction_filter_chevron_left_thin_a);
			junctionLinesWhiteList.add(white_junction_filter_chevron_left_thin_b);
			junctionLinesWhiteList.add(white_junction_filter_chevron_right_a);
			junctionLinesWhiteList.add(white_junction_filter_chevron_right_b);
			junctionLinesWhiteList.add(white_junction_filter_chevron_right_thin_a);
			junctionLinesWhiteList.add(white_junction_filter_chevron_right_thin_b);
			
			junctionLinesWhiteList.add(white_junction_filter_mid);
			junctionLinesWhiteList.add(white_junction_filter_mid_thin);
			
			junctionLinesMidWhiteList.add(white_junction_chevron_mid);
			junctionLinesMidWhiteList.add(white_junction_chevron_mid_left);
			junctionLinesMidWhiteList.add(white_junction_chevron_mid_right);
			junctionLinesMidWhiteList.add(white_junction_chevron_mid_thin);
			junctionLinesMidWhiteList.add(white_junction_chevron_mid_left_thin);
			junctionLinesMidWhiteList.add(white_junction_chevron_mid_right_thin);
			junctionLinesMidWhiteList.add(white_junction_left_line_left);
			junctionLinesMidWhiteList.add(white_junction_left_line_right);
			junctionLinesMidWhiteList.add(white_junction_right_line_left);
			junctionLinesMidWhiteList.add(white_junction_right_line_right);
			junctionLinesMidWhiteList.add(white_junction_both_line);
			
			junctionLinesSideWhiteList.add(white_junction_chevron);
			junctionLinesSideWhiteList.add(white_junction_chevron_low);
			junctionLinesSideWhiteList.add(white_junction_chevron_high);
			junctionLinesSideWhiteList.add(white_junction_chevron_thin);
			junctionLinesSideWhiteList.add(white_junction_chevron_low_thin);
			junctionLinesSideWhiteList.add(white_junction_chevron_high_thin);*/
			
			chevronWhiteList.add(white_chevron_left_a);
			chevronWhiteList.add(white_chevron_left_b);
			chevronWhiteList.add(white_chevron_left_a_thin);
			chevronWhiteList.add(white_chevron_left_b_thin);
			
			chevronWhiteList.add(white_chevron_right_a);
			chevronWhiteList.add(white_chevron_right_b);
			chevronWhiteList.add(white_chevron_right_a_thin);
			chevronWhiteList.add(white_chevron_right_b_thin);
			
			registry.register(white_junction_side_line_connection);
			registry.register(white_junction_side_line_connection_thin);
			
			registry.register(white_chevron_side_line);
		}
		
		if (cfg.white_text) {
			textWhiteList.add(white_slow);
			textWhiteList.add(white_stop);
			textWhiteList.add(white_bike);
			textWhiteList.add(white_bus);
			textWhiteList.add(white_taxi);
			textWhiteList.add(white_lane);
			textWhiteList.add(white_keep);
			textWhiteList.add(white_clear);
			textWhiteList.add(white_turn);
			textWhiteList.add(white_left);
			textWhiteList.add(white_right);
			textWhiteList.add(white_only);
			textWhiteList.add(white_no);
			textWhiteList.add(white_entry);
			textWhiteList.add(white_bike_icon);
			textWhiteList.add(white_town);
			textWhiteList.add(white_city);
			textWhiteList.add(white_ctre);
			
			letterWhiteList.add(paint_letter_white_ab);
			letterWhiteList.add(paint_letter_white_cd);
			letterWhiteList.add(paint_letter_white_ef);
			letterWhiteList.add(paint_letter_white_gh);
			letterWhiteList.add(paint_letter_white_ij);
			letterWhiteList.add(paint_letter_white_kl);
			letterWhiteList.add(paint_letter_white_mn);
			letterWhiteList.add(paint_letter_white_op);
			letterWhiteList.add(paint_letter_white_qr);
			letterWhiteList.add(paint_letter_white_st);
			letterWhiteList.add(paint_letter_white_uv);
			letterWhiteList.add(paint_letter_white_wx);
			letterWhiteList.add(paint_letter_white_yz);
			letterWhiteList.add(paint_letter_white_01);
			letterWhiteList.add(paint_letter_white_23);
			letterWhiteList.add(paint_letter_white_45);
			letterWhiteList.add(paint_letter_white_67);
			letterWhiteList.add(paint_letter_white_89);
			letterWhiteList.add(paint_letter_white_punct_question_exclamation);
			letterWhiteList.add(paint_letter_white_punct_hash_slash);
		}
		
		if (cfg.white_other) {
			registry.registerAll(
				hatch_box_white,
				line_white_crossing_diagonal,
				white_crossing_paint,
				white_arrow,
				white_arrow_line,
				white_arrow_diagonal,
				white_junction_a,
				white_junction_b
			);
		}
		
		
		if (cfg.yellow_text) {
			/*textYellowList.add(yellow_slow);
			textYellowList.add(yellow_stop);
			textYellowList.add(yellow_bike);
			textYellowList.add(yellow_bus);
			textYellowList.add(yellow_taxi);
			textYellowList.add(yellow_lane);
			textYellowList.add(yellow_keep);
			textYellowList.add(yellow_clear);
			textYellowList.add(yellow_turn);
			textYellowList.add(yellow_left);
			textYellowList.add(yellow_right);
			textYellowList.add(yellow_only);
			textYellowList.add(yellow_no);
			textYellowList.add(yellow_entry);
			textYellowList.add(yellow_bike_icon);*/
			
			letterYellowList.add(paint_letter_yellow_ab);
			letterYellowList.add(paint_letter_yellow_cd);
			letterYellowList.add(paint_letter_yellow_ef);
			letterYellowList.add(paint_letter_yellow_gh);
			letterYellowList.add(paint_letter_yellow_ij);
			letterYellowList.add(paint_letter_yellow_kl);
			letterYellowList.add(paint_letter_yellow_mn);
			letterYellowList.add(paint_letter_yellow_op);
			letterYellowList.add(paint_letter_yellow_qr);
			letterYellowList.add(paint_letter_yellow_st);
			letterYellowList.add(paint_letter_yellow_uv);
			letterYellowList.add(paint_letter_yellow_wx);
			letterYellowList.add(paint_letter_yellow_yz);
			letterYellowList.add(paint_letter_yellow_01);
			letterYellowList.add(paint_letter_yellow_23);
			letterYellowList.add(paint_letter_yellow_45);
			letterYellowList.add(paint_letter_yellow_67);
			letterYellowList.add(paint_letter_yellow_89);
			letterYellowList.add(paint_letter_yellow_punct_question_exclamation);
			letterYellowList.add(paint_letter_yellow_punct_hash_slash);
		}
		
		if (cfg.red_text) {
			/*textList.add(red_slow);
			textRedList.add(red_stop);
			textRedList.add(red_bike);
			textRedList.add(red_bus);
			textRedList.add(red_taxi);
			textRedList.add(red_lane);
			textRedList.add(red_keep);
			textRedList.add(red_clear);
			textRedList.add(red_turn);
			textRedList.add(red_left);
			textRedList.add(red_right);
			textRedList.add(red_only);
			textRedList.add(red_no);
			textRedList.add(red_entry);
			textRedList.add(red_bike_icon);*/
			
			letterRedList.add(paint_letter_red_ab);
			letterRedList.add(paint_letter_red_cd);
			letterRedList.add(paint_letter_red_ef);
			letterRedList.add(paint_letter_red_gh);
			letterRedList.add(paint_letter_red_ij);
			letterRedList.add(paint_letter_red_kl);
			letterRedList.add(paint_letter_red_mn);
			letterRedList.add(paint_letter_red_op);
			letterRedList.add(paint_letter_red_qr);
			letterRedList.add(paint_letter_red_st);
			letterRedList.add(paint_letter_red_uv);
			letterRedList.add(paint_letter_red_wx);
			letterRedList.add(paint_letter_red_yz);
			letterRedList.add(paint_letter_red_01);
			letterRedList.add(paint_letter_red_23);
			letterRedList.add(paint_letter_red_45);
			letterRedList.add(paint_letter_red_67);
			letterRedList.add(paint_letter_red_89);
			letterRedList.add(paint_letter_red_punct_question_exclamation);
			letterRedList.add(paint_letter_red_punct_hash_slash);
		}
		
		//Anything not covered by the config.
		registry.registerAll(			
			kerb_standard,
			kerb_connect,
			
			street_block,
			
			tactile_crossing_bumps,
			
			
			line_yellow_straight_full,
			line_yellow_straight_thick,
			line_yellow_straight_double,
			line_yellow_side_double,
			line_yellow_side_single,
			
			line_yellow_far_side,
			
			hatch_box_yellow,
			line_yellow_crossing_diagonal,
			yellow_crossing_paint,
			line_yellow_middle_half_double,
			line_yellow_middle_dash_double,
			line_yellow_middle_short,
			line_yellow_filter_lane,
			line_yellow_side_short,
			
			
			
			line_red_straight_thick,
			line_red_straight_full,
			line_red_straight_double,
			line_red_side_double,
			line_red_side_single,
			
			line_red_far_side,
			
			hatch_box_red,
			line_red_crossing_diagonal,
			red_crossing_paint,
			line_red_middle_half_double,
			line_red_middle_dash_double,
			line_red_middle_short,
			line_red_filter_lane,
			line_red_side_short
		);
		
		for (int i = 0; i < roadBlockList.size(); i++)  { registry.register(roadBlockList.get(i)); }
		
		for (int i = 0; i < straightLineWhiteList.size(); i++)  { registry.register(straightLineWhiteList.get(i)); }
		for (int i = 0; i < sideLineWhiteList.size(); i++)  { registry.register(sideLineWhiteList.get(i)); }
		for (int i = 0; i < farSideLineWhiteList.size(); i++)  { registry.register(farSideLineWhiteList.get(i)); }
		for (int i = 0; i < simpleLineWhiteList.size(); i++)  { registry.register(simpleLineWhiteList.get(i)); }
		for (int i = 0; i < iconWhiteList.size(); i++)  { registry.register(iconWhiteList.get(i)); }
		for (int i = 0; i < tallIconWhiteList.size(); i++)  { registry.register(tallIconWhiteList.get(i)); }
		
		for (int i = 0; i < junctionLinesWhiteList.size(); i++)  { registry.register(junctionLinesWhiteList.get(i)); }
		for (int i = 0; i < chevronWhiteList.size(); i++)  { registry.register(chevronWhiteList.get(i)); }
		
		for (int i = 0; i < textWhiteList.size(); i++)  { registry.register(textWhiteList.get(i)); }
		for (int i = 0; i < letterWhiteList.size(); i++)  { registry.register(letterWhiteList.get(i)); }
		

		for (int i = 0; i < textYellowList.size(); i++)  { registry.register(textYellowList.get(i)); }
		for (int i = 0; i < textRedList.size(); i++)  { registry.register(textRedList.get(i)); }
	}
	
	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		for (int i = 0; i < roadBlockList.size(); i++)  { registry.register(new RoadItemBlock(roadBlockList.get(i)).setRegistryName(roadBlockList.get(i).getRegistryName())); }
		
		for (int i = 0; i < straightLineWhiteList.size(); i++)  { registry.register(new RoadItemBlock(straightLineWhiteList.get(i)).setRegistryName(straightLineWhiteList.get(i).getRegistryName())); }
		for (int i = 0; i < sideLineWhiteList.size(); i++)  { registry.register(new RoadItemBlock(sideLineWhiteList.get(i)).setRegistryName(sideLineWhiteList.get(i).getRegistryName())); }
		for (int i = 0; i < farSideLineWhiteList.size(); i++)  { registry.register(new RoadItemBlock(farSideLineWhiteList.get(i)).setRegistryName(farSideLineWhiteList.get(i).getRegistryName())); }
		for (int i = 0; i < simpleLineWhiteList.size(); i++)  { registry.register(new RoadItemBlock(simpleLineWhiteList.get(i)).setRegistryName(simpleLineWhiteList.get(i).getRegistryName())); }
		for (int i = 0; i < iconWhiteList.size(); i++)  { registry.register(new RoadItemBlock(iconWhiteList.get(i)).setRegistryName(iconWhiteList.get(i).getRegistryName())); }
		for (int i = 0; i < tallIconWhiteList.size(); i++)  { registry.register(new RoadItemBlock(tallIconWhiteList.get(i)).setRegistryName(tallIconWhiteList.get(i).getRegistryName())); }
		
		for (int i = 0; i < junctionLinesWhiteList.size(); i++)  { registry.register(new RoadItemBlock(junctionLinesWhiteList.get(i)).setRegistryName(junctionLinesWhiteList.get(i).getRegistryName())); }
		for (int i = 0; i < chevronWhiteList.size(); i++)  { registry.register(new RoadItemBlock(chevronWhiteList.get(i)).setRegistryName(chevronWhiteList.get(i).getRegistryName())); }
		
		for (int i = 0; i < textWhiteList.size(); i++)  { registry.register(new RoadItemBlock(textWhiteList.get(i)).setRegistryName(textWhiteList.get(i).getRegistryName())); }
		for (int i = 0; i < letterWhiteList.size(); i++)  { registry.register(new RoadItemBlock(letterWhiteList.get(i)).setRegistryName(letterWhiteList.get(i).getRegistryName())); }
		
		
		for (int i = 0; i < textYellowList.size(); i++)  { registry.register(new RoadItemBlock(textYellowList.get(i)).setRegistryName(textYellowList.get(i).getRegistryName())); }
		for (int i = 0; i < textRedList.size(); i++)  { registry.register(new RoadItemBlock(textRedList.get(i)).setRegistryName(textRedList.get(i).getRegistryName())); }
		
		registry.register(new RoadItemBlock(kerb_standard).setRegistryName(kerb_standard.getRegistryName()));
		registry.register(new RoadItemBlock(kerb_connect).setRegistryName(kerb_connect.getRegistryName()));
		
		registry.register(new RoadItemBlock(street_block).setRegistryName(street_block.getRegistryName()));
		
		registry.register(new RoadItemBlock(tactile_crossing_bumps).setRegistryName(tactile_crossing_bumps.getRegistryName()));
		
		if (cfg.white_junction) {
			registry.register(new RoadItemBlock(white_junction_side_line_connection).setRegistryName(white_junction_side_line_connection.getRegistryName()));
			registry.register(new RoadItemBlock(white_junction_side_line_connection_thin).setRegistryName(white_junction_side_line_connection_thin.getRegistryName()));
			registry.register(new RoadItemBlock(white_chevron_side_line).setRegistryName(white_chevron_side_line.getRegistryName()));
		}

		if (cfg.white_other) {
			registry.register(new RoadItemBlock(hatch_box_white).setRegistryName(hatch_box_white.getRegistryName()));
			registry.register(new RoadItemBlock(line_white_crossing_diagonal).setRegistryName(line_white_crossing_diagonal.getRegistryName()));
			registry.register(new RoadItemBlock(white_crossing_paint).setRegistryName(white_crossing_paint.getRegistryName()));
			registry.register(new RoadItemBlock(white_arrow).setRegistryName(white_arrow.getRegistryName()));
			registry.register(new RoadItemBlock(white_arrow_line).setRegistryName(white_arrow_line.getRegistryName()));
			registry.register(new RoadItemBlock(white_arrow_diagonal).setRegistryName(white_arrow_diagonal.getRegistryName()));
			registry.register(new RoadItemBlock(white_junction_a).setRegistryName(white_junction_a.getRegistryName()));
			registry.register(new RoadItemBlock(white_junction_b).setRegistryName(white_junction_b.getRegistryName()));
		}
		
		
		registry.register(new RoadItemBlock(line_yellow_straight_full).setRegistryName(line_yellow_straight_full.getRegistryName()));
		registry.register(new RoadItemBlock(line_yellow_straight_thick).setRegistryName(line_yellow_straight_thick.getRegistryName()));
		registry.register(new RoadItemBlock(line_yellow_straight_double).setRegistryName(line_yellow_straight_double.getRegistryName()));
		registry.register(new RoadItemBlock(line_yellow_side_double).setRegistryName(line_yellow_side_double.getRegistryName()));
		registry.register(new RoadItemBlock(line_yellow_side_single).setRegistryName(line_yellow_side_single.getRegistryName()));
		//registry.register(new RoadItemBlock(line_yellow_side_single_thick).setRegistryName(line_yellow_side_single_thick.getRegistryName()));
		registry.register(new RoadItemBlock(line_yellow_far_side).setRegistryName(line_yellow_far_side.getRegistryName()));
		//registry.register(new RoadItemBlock(line_yellow_far_side_thick).setRegistryName(line_yellow_far_side_thick.getRegistryName()));
		registry.register(new RoadItemBlock(hatch_box_yellow).setRegistryName(hatch_box_yellow.getRegistryName()));
		registry.register(new RoadItemBlock(line_yellow_crossing_diagonal).setRegistryName(line_yellow_crossing_diagonal.getRegistryName()));
		registry.register(new RoadItemBlock(yellow_crossing_paint).setRegistryName(yellow_crossing_paint.getRegistryName()));
		registry.register(new RoadItemBlock(line_yellow_middle_half_double).setRegistryName(line_yellow_middle_half_double.getRegistryName()));
		registry.register(new RoadItemBlock(line_yellow_middle_dash_double).setRegistryName(line_yellow_middle_dash_double.getRegistryName()));
		registry.register(new RoadItemBlock(line_yellow_middle_short).setRegistryName(line_yellow_middle_short.getRegistryName()));
		registry.register(new RoadItemBlock(line_yellow_filter_lane).setRegistryName(line_yellow_filter_lane.getRegistryName()));
		registry.register(new RoadItemBlock(line_yellow_side_short).setRegistryName(line_yellow_side_short.getRegistryName()));
		
		
		
		registry.register(new RoadItemBlock(line_red_straight_full).setRegistryName(line_red_straight_full.getRegistryName()));
		registry.register(new RoadItemBlock(line_red_straight_thick).setRegistryName(line_red_straight_thick.getRegistryName()));
		registry.register(new RoadItemBlock(line_red_straight_double).setRegistryName(line_red_straight_double.getRegistryName()));
		registry.register(new RoadItemBlock(line_red_side_double).setRegistryName(line_red_side_double.getRegistryName()));
		registry.register(new RoadItemBlock(line_red_side_single).setRegistryName(line_red_side_single.getRegistryName()));
		//registry.register(new RoadItemBlock(line_red_side_single_thick).setRegistryName(line_red_side_single_thick.getRegistryName()));
		registry.register(new RoadItemBlock(line_red_far_side).setRegistryName(line_red_far_side.getRegistryName()));
		//registry.register(new RoadItemBlock(line_red_far_side_thick).setRegistryName(line_red_far_side_thick.getRegistryName()));
		registry.register(new RoadItemBlock(hatch_box_red).setRegistryName(hatch_box_red.getRegistryName()));
		registry.register(new RoadItemBlock(line_red_crossing_diagonal).setRegistryName(line_red_crossing_diagonal.getRegistryName()));
		registry.register(new RoadItemBlock(red_crossing_paint).setRegistryName(red_crossing_paint.getRegistryName()));
		registry.register(new RoadItemBlock(line_red_middle_half_double).setRegistryName(line_red_middle_half_double.getRegistryName()));
		registry.register(new RoadItemBlock(line_red_middle_dash_double).setRegistryName(line_red_middle_dash_double.getRegistryName()));
		registry.register(new RoadItemBlock(line_red_middle_short).setRegistryName(line_red_middle_short.getRegistryName()));
		registry.register(new RoadItemBlock(line_red_filter_lane).setRegistryName(line_red_filter_lane.getRegistryName()));
		registry.register(new RoadItemBlock(line_red_side_short).setRegistryName(line_red_side_short.getRegistryName()));
	}
	
	public static void registerModels() {
		for (int i = 0; i < roadBlockList.size(); i++)  { roadBlockList.get(i).initModel(); }
		
		for (int i = 0; i < straightLineWhiteList.size(); i++)  { straightLineWhiteList.get(i).initModel(); }
		for (int i = 0; i < sideLineWhiteList.size(); i++)  { sideLineWhiteList.get(i).initModel(); }
		for (int i = 0; i < farSideLineWhiteList.size(); i++)  { farSideLineWhiteList.get(i).initModel(); }
		for (int i = 0; i < simpleLineWhiteList.size(); i++)  { simpleLineWhiteList.get(i).initModel(); }
		for (int i = 0; i < iconWhiteList.size(); i++)  { iconWhiteList.get(i).initModel(); }
		for (int i = 0; i < tallIconWhiteList.size(); i++)  { tallIconWhiteList.get(i).initModel(); }

		for (int i = 0; i < junctionLinesWhiteList.size(); i++)  { junctionLinesWhiteList.get(i).initModel(); }
		for (int i = 0; i < chevronWhiteList.size(); i++)  { chevronWhiteList.get(i).initModel(); }
		
		for (int i = 0; i < textWhiteList.size(); i++)  { textWhiteList.get(i).initModel(); }
		for (int i = 0; i < letterWhiteList.size(); i++)  { letterWhiteList.get(i).initModel(); }
		
		for (int i = 0; i < textYellowList.size(); i++)  { textYellowList.get(i).initModel(); }
		for (int i = 0; i < textRedList.size(); i++)  { textRedList.get(i).initModel(); }
		
		kerb_standard.initModel();
		kerb_connect.initModel();
		
		street_block.initModel();
		
		tactile_crossing_bumps.initModel();
		
		if (cfg.white_junction) {
			white_junction_side_line_connection.initModel();
			white_junction_side_line_connection_thin.initModel();
			white_chevron_side_line.initModel();
		}

		if (cfg.white_other) {
			hatch_box_white.initModel();
			line_white_crossing_diagonal.initModel();
			white_crossing_paint.initModel();
			white_arrow.initModel();
			white_arrow_line.initModel();
			white_arrow_diagonal.initModel();
			white_junction_a.initModel();
			white_junction_b.initModel();
		}
		
		line_yellow_straight_full.initModel();
		line_yellow_straight_thick.initModel();
		line_yellow_straight_double.initModel();
		line_yellow_side_double.initModel();
		line_yellow_side_single.initModel();
		//line_yellow_side_single_thick.initModel();
		line_yellow_far_side.initModel();
		//line_yellow_far_side_thick.initModel();
		hatch_box_yellow.initModel();
		line_yellow_crossing_diagonal.initModel();
		yellow_crossing_paint.initModel();
		line_yellow_middle_half_double.initModel();
		line_yellow_middle_dash_double.initModel();
		line_yellow_middle_short.initModel();
		line_yellow_filter_lane.initModel();
		line_yellow_side_short.initModel();
		
		
		
		line_red_straight_full.initModel();
		line_red_straight_thick.initModel();
		line_red_straight_double.initModel();
		line_red_side_double.initModel();
		line_red_side_single.initModel();
		//line_red_side_single_thick.initModel();
		line_red_far_side.initModel();
		//line_red_far_side_thick.initModel();
		hatch_box_red.initModel();
		line_red_crossing_diagonal.initModel();
		red_crossing_paint.initModel();
		line_red_middle_half_double.initModel();
		line_red_middle_dash_double.initModel();
		line_red_middle_short.initModel();
		line_red_filter_lane.initModel();
		line_red_side_short.initModel();
	}
}
