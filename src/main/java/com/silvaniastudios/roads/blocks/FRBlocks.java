package com.silvaniastudios.roads.blocks;

import com.silvaniastudios.roads.FlenixRoads;
import com.silvaniastudios.roads.items.RoadItemBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public class FRBlocks {
	
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
		
	public static StreetBlock street_block = new StreetBlock("street_block");
	
	public static LinePaintBlock line_white_straight_full = new LinePaintBlock("line_white_straight_full");
	public static LinePaintBlock line_yellow_straight_full = new LinePaintBlock("line_yellow_straight_full");
	public static LinePaintBlock line_red_straight_full = new LinePaintBlock("line_red_straight_full");
	public static LinePaintBlock line_white_straight_thick = new LinePaintBlock("line_white_straight_thick");
	public static LinePaintBlock line_yellow_straight_thick = new LinePaintBlock("line_yellow_straight_thick");
	public static LinePaintBlock line_red_straight_thick = new LinePaintBlock("line_red_straight_thick");
	
	public static LinePaintBlock line_white_straight_double = new LinePaintBlock("line_white_straight_double");
	public static LinePaintBlock line_yellow_straight_double = new LinePaintBlock("line_yellow_straight_double");
	public static LinePaintBlock line_red_straight_double = new LinePaintBlock("line_red_straight_double");
	
	public static SideLinePaintBlock line_white_side_double = new SideLinePaintBlock("line_white_side_double");
	public static SideLinePaintBlock line_yellow_side_double = new SideLinePaintBlock("line_yellow_side_double");
	public static SideLinePaintBlock line_red_side_double = new SideLinePaintBlock("line_red_side_double");
	public static SideLinePaintBlock line_white_side_single = new SideLinePaintBlock("line_white_side_single");
	public static SideLinePaintBlock line_yellow_side_single = new SideLinePaintBlock("line_yellow_side_single");
	public static SideLinePaintBlock line_red_side_single = new SideLinePaintBlock("line_red_side_single");
	
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
	
	public static IconPaintBlock line_white_middle_half_double = new IconPaintBlock("line_white_middle_half_double");
	public static IconPaintBlock line_yellow_middle_half_double = new IconPaintBlock("line_yellow_middle_half_double");
	public static IconPaintBlock line_red_middle_half_double = new IconPaintBlock("line_red_middle_half_double");
	public static IconPaintBlock line_white_middle_dash_double = new IconPaintBlock("line_white_middle_dash_double");
	public static IconPaintBlock line_yellow_middle_dash_double = new IconPaintBlock("line_yellow_middle_dash_double");
	public static IconPaintBlock line_red_middle_dash_double = new IconPaintBlock("line_red_middle_dash_double");
	
	public static IconPaintBlock line_white_middle_short = new IconPaintBlock("line_white_middle_short");
	public static IconPaintBlock line_yellow_middle_short = new IconPaintBlock("line_yellow_middle_short");
	public static IconPaintBlock line_red_middle_short = new IconPaintBlock("line_red_middle_short");
	
	public static LetterPaintBlock[] letterBlockWhiteArray = new LetterPaintBlock[] {paint_letter_white_ab, paint_letter_white_cd, paint_letter_white_ef, 
			paint_letter_white_gh, paint_letter_white_ij, paint_letter_white_kl, paint_letter_white_mn, paint_letter_white_op, paint_letter_white_qr,
			paint_letter_white_st, paint_letter_white_uv, paint_letter_white_wx, paint_letter_white_yz, paint_letter_white_01, paint_letter_white_23, 
			paint_letter_white_45, paint_letter_white_67, paint_letter_white_89, paint_letter_white_punct_question_exclamation, paint_letter_white_punct_hash_slash};
	
	public static LetterPaintBlock[] letterBlockYellowArray = new LetterPaintBlock[] {paint_letter_yellow_ab, paint_letter_yellow_cd, paint_letter_yellow_ef, 
			paint_letter_yellow_gh, paint_letter_yellow_ij, paint_letter_yellow_kl, paint_letter_yellow_mn, paint_letter_yellow_op, paint_letter_yellow_qr,
			paint_letter_yellow_st, paint_letter_yellow_uv, paint_letter_yellow_wx, paint_letter_yellow_yz, paint_letter_yellow_01, paint_letter_yellow_23, 
			paint_letter_yellow_45, paint_letter_yellow_67, paint_letter_yellow_89, paint_letter_yellow_punct_question_exclamation, paint_letter_yellow_punct_hash_slash};
	
	public static LetterPaintBlock[] letterBlockRedArray = new LetterPaintBlock[] {paint_letter_red_ab, paint_letter_red_cd, paint_letter_red_ef, 
			paint_letter_red_gh, paint_letter_red_ij, paint_letter_red_kl, paint_letter_red_mn, paint_letter_red_op, paint_letter_red_qr,
			paint_letter_red_st, paint_letter_red_uv, paint_letter_red_wx, paint_letter_red_yz, paint_letter_red_01, paint_letter_red_23, 
			paint_letter_red_45, paint_letter_red_67, paint_letter_red_89, paint_letter_red_punct_question_exclamation, paint_letter_red_punct_hash_slash};
	
	public static void register(IForgeRegistry<Block> registry) {
		registry.registerAll(
			road_block_standard,
			road_block_concrete_1,
			road_block_concrete_2,
			road_block_light,
			road_block_fine,
			road_block_dark,
			road_block_pale,
			road_block_red,
			road_block_blue,
			road_block_white,
			road_block_yellow,
			road_block_green,
			road_block_muddy,
			road_block_muddy_dried,
			
			road_block_stone,
			road_block_grass,
			road_block_dirt,
			road_block_gravel,
			road_block_sand,
			
			kerb_standard,
			kerb_connect,
			
			street_block,
			
			line_white_straight_full,
			line_yellow_straight_full,
			line_red_straight_full,
			line_white_straight_thick,
			line_yellow_straight_thick,
			line_red_straight_thick,
			
			line_white_straight_double,
			
			line_white_side_double,
			line_yellow_side_double,
			line_red_side_double,
			line_white_side_single,
			line_yellow_side_single,
			line_red_side_single,
			
			line_white_middle_half_double,
			line_yellow_middle_half_double,
			line_red_middle_half_double,
			line_white_middle_dash_double,
			line_yellow_middle_dash_double,
			line_red_middle_dash_double,
			
			line_white_middle_short,
			line_yellow_middle_short,
			line_red_middle_short
		);
		
		for (int i = 0; i < letterBlockWhiteArray.length; i++)  { registry.register(letterBlockWhiteArray[i]); }
		for (int i = 0; i < letterBlockYellowArray.length; i++) { registry.register(letterBlockYellowArray[i]); }
		for (int i = 0; i < letterBlockRedArray.length; i++)    { registry.register(letterBlockRedArray[i]); }
	}
	
	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		registry.register(new RoadItemBlock(road_block_standard).setRegistryName(road_block_standard.getRegistryName()));
		registry.register(new RoadItemBlock(road_block_concrete_1).setRegistryName(road_block_concrete_1.getRegistryName()));
		registry.register(new RoadItemBlock(road_block_concrete_2).setRegistryName(road_block_concrete_2.getRegistryName()));
		registry.register(new RoadItemBlock(road_block_light).setRegistryName(road_block_light.getRegistryName()));
		registry.register(new RoadItemBlock(road_block_fine).setRegistryName(road_block_fine.getRegistryName()));
		registry.register(new RoadItemBlock(road_block_dark).setRegistryName(road_block_dark.getRegistryName()));
		registry.register(new RoadItemBlock(road_block_pale).setRegistryName(road_block_pale.getRegistryName()));
		registry.register(new RoadItemBlock(road_block_red).setRegistryName(road_block_red.getRegistryName()));
		registry.register(new RoadItemBlock(road_block_blue).setRegistryName(road_block_blue.getRegistryName()));
		registry.register(new RoadItemBlock(road_block_white).setRegistryName(road_block_white.getRegistryName()));
		registry.register(new RoadItemBlock(road_block_yellow).setRegistryName(road_block_yellow.getRegistryName()));
		registry.register(new RoadItemBlock(road_block_green).setRegistryName(road_block_green.getRegistryName()));
		registry.register(new RoadItemBlock(road_block_muddy).setRegistryName(road_block_muddy.getRegistryName()));
		registry.register(new RoadItemBlock(road_block_muddy_dried).setRegistryName(road_block_muddy_dried.getRegistryName()));
		
		registry.register(new RoadItemBlock(road_block_stone).setRegistryName(road_block_stone.getRegistryName()));
		registry.register(new RoadItemBlock(road_block_grass).setRegistryName(road_block_grass.getRegistryName()));
		registry.register(new RoadItemBlock(road_block_dirt).setRegistryName(road_block_dirt.getRegistryName()));
		registry.register(new RoadItemBlock(road_block_gravel).setRegistryName(road_block_gravel.getRegistryName()));
		registry.register(new RoadItemBlock(road_block_sand).setRegistryName(road_block_sand.getRegistryName()));
		
		registry.register(new RoadItemBlock(kerb_standard).setRegistryName(kerb_standard.getRegistryName()));
		registry.register(new RoadItemBlock(kerb_connect).setRegistryName(kerb_connect.getRegistryName()));
		
		registry.register(new RoadItemBlock(street_block).setRegistryName(street_block.getRegistryName()));
		
		registry.register(new RoadItemBlock(line_white_straight_full).setRegistryName(line_white_straight_full.getRegistryName()));
		registry.register(new RoadItemBlock(line_yellow_straight_full).setRegistryName(line_yellow_straight_full.getRegistryName()));
		registry.register(new RoadItemBlock(line_red_straight_full).setRegistryName(line_red_straight_full.getRegistryName()));
		registry.register(new RoadItemBlock(line_white_straight_thick).setRegistryName(line_white_straight_thick.getRegistryName()));
		registry.register(new RoadItemBlock(line_yellow_straight_thick).setRegistryName(line_yellow_straight_thick.getRegistryName()));
		registry.register(new RoadItemBlock(line_red_straight_thick).setRegistryName(line_red_straight_thick.getRegistryName()));
		
		registry.register(new RoadItemBlock(line_white_straight_double).setRegistryName(line_white_straight_double.getRegistryName()));
		
		registry.register(new RoadItemBlock(line_white_side_double).setRegistryName(line_white_side_double.getRegistryName()));
		registry.register(new RoadItemBlock(line_yellow_side_double).setRegistryName(line_yellow_side_double.getRegistryName()));
		registry.register(new RoadItemBlock(line_red_side_double).setRegistryName(line_red_side_double.getRegistryName()));
		registry.register(new RoadItemBlock(line_white_side_single).setRegistryName(line_white_side_single.getRegistryName()));
		registry.register(new RoadItemBlock(line_yellow_side_single).setRegistryName(line_yellow_side_single.getRegistryName()));
		registry.register(new RoadItemBlock(line_red_side_single).setRegistryName(line_red_side_single.getRegistryName()));
		
		registry.register(new RoadItemBlock(line_white_middle_half_double).setRegistryName(line_white_middle_half_double.getRegistryName()));
		registry.register(new RoadItemBlock(line_yellow_middle_half_double).setRegistryName(line_yellow_middle_half_double.getRegistryName()));
		registry.register(new RoadItemBlock(line_red_middle_half_double).setRegistryName(line_red_middle_half_double.getRegistryName()));
		registry.register(new RoadItemBlock(line_white_middle_dash_double).setRegistryName(line_white_middle_dash_double.getRegistryName()));
		registry.register(new RoadItemBlock(line_yellow_middle_dash_double).setRegistryName(line_yellow_middle_dash_double.getRegistryName()));
		registry.register(new RoadItemBlock(line_red_middle_dash_double).setRegistryName(line_red_middle_dash_double.getRegistryName()));
		
		registry.register(new RoadItemBlock(line_white_middle_short).setRegistryName(line_white_middle_short.getRegistryName()));
		registry.register(new RoadItemBlock(line_yellow_middle_short).setRegistryName(line_yellow_middle_short.getRegistryName()));
		registry.register(new RoadItemBlock(line_red_middle_short).setRegistryName(line_red_middle_short.getRegistryName()));
		
		for (int i = 0; i < letterBlockWhiteArray.length; i++)  { registry.register(new RoadItemBlock(letterBlockWhiteArray[i]).setRegistryName(letterBlockWhiteArray[i].getRegistryName())); }
		for (int i = 0; i < letterBlockYellowArray.length; i++) { registry.register(new RoadItemBlock(letterBlockYellowArray[i]).setRegistryName(letterBlockYellowArray[i].getRegistryName())); } 
		for (int i = 0; i < letterBlockRedArray.length; i++)    { registry.register(new RoadItemBlock(letterBlockRedArray[i]).setRegistryName(letterBlockRedArray[i].getRegistryName()));	}
	}
	
	public static void registerModels() {
		road_block_standard.initModel();
		road_block_concrete_1.initModel();
		road_block_concrete_2.initModel();
		road_block_light.initModel();
		road_block_fine.initModel();
		road_block_dark.initModel();
		road_block_pale.initModel();
		road_block_red.initModel();
		road_block_blue.initModel();
		road_block_white.initModel();
		road_block_yellow.initModel();
		road_block_green.initModel();
		road_block_muddy.initModel();
		road_block_muddy_dried.initModel();
		
		road_block_stone.initModel();
		road_block_grass.initModel();
		road_block_dirt.initModel();
		road_block_gravel.initModel();
		road_block_sand.initModel();
		
		kerb_standard.initModel();
		kerb_connect.initModel();
		
		street_block.initModel();
		
		line_white_straight_full.initModel();
		line_yellow_straight_full.initModel();
		line_red_straight_full.initModel();
		line_white_straight_thick.initModel();
		line_yellow_straight_thick.initModel();
		line_red_straight_thick.initModel();
		
		line_white_straight_double.initModel();
		
		line_white_side_double.initModel();
		line_yellow_side_double.initModel();
		line_red_side_double.initModel();
		line_white_side_single.initModel();
		line_yellow_side_single.initModel();
		line_red_side_single.initModel();
		
		line_white_middle_half_double.initModel();
		line_yellow_middle_half_double.initModel();
		line_red_middle_half_double.initModel();
		line_white_middle_dash_double.initModel();
		line_yellow_middle_dash_double.initModel();
		line_red_middle_dash_double.initModel();
		
		line_white_middle_short.initModel();
		line_yellow_middle_short.initModel();
		line_red_middle_short.initModel();
		
		for (int i = 0; i < letterBlockWhiteArray.length; i++)  { letterBlockWhiteArray[i].initModel(); }
		for (int i = 0; i < letterBlockYellowArray.length; i++) { letterBlockYellowArray[i].initModel(); }
		for (int i = 0; i < letterBlockRedArray.length; i++)    { letterBlockRedArray[i].initModel(); }
	}
	
	@SideOnly(Side.CLIENT)
	public static void initModel(Block block) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(FlenixRoads.MODID + ":" + block.getUnlocalizedName().substring(5), "inventory"));
	}
}
