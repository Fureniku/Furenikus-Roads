package com.silvaniastudios.roads.client.model;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;
import com.silvaniastudios.roads.registries.PaintCategoryList;
import com.silvaniastudios.roads.registries.PaintGunItemRegistry;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TextureRegistryHandler {
	
	public static final TextureRegistryHandler instance = new TextureRegistryHandler();
	
	private TextureRegistryHandler() {};
	
	@SubscribeEvent
	public void stitcherEventPre(TextureStitchEvent.Pre event) {
		ResourceLocation white_paint = new ResourceLocation(FurenikusRoads.MODID + ":items/paint_white");
		ResourceLocation yellow_paint = new ResourceLocation(FurenikusRoads.MODID + ":items/paint_yellow");
		ResourceLocation red_paint = new ResourceLocation(FurenikusRoads.MODID + ":items/paint_red");
		
		ResourceLocation block_white_paint = new ResourceLocation(FurenikusRoads.MODID + ":blocks/paint_white");
		ResourceLocation block_yellow_paint = new ResourceLocation(FurenikusRoads.MODID + ":blocks/paint_yellow");
		ResourceLocation block_red_paint = new ResourceLocation(FurenikusRoads.MODID + ":blocks/paint_red");
		
		ResourceLocation tar_flowing = new ResourceLocation(FurenikusRoads.MODID + ":fluids/tar_flowing");
		ResourceLocation tar_still = new ResourceLocation(FurenikusRoads.MODID + ":fluids/tar_still");
		ResourceLocation white_paint_flowing = new ResourceLocation(FurenikusRoads.MODID + ":fluids/white_paint_flowing");
		ResourceLocation white_paint_still = new ResourceLocation(FurenikusRoads.MODID + ":fluids/white_paint_still");
		ResourceLocation yellow_paint_flowing = new ResourceLocation(FurenikusRoads.MODID + ":fluids/yellow_paint_flowing");
		ResourceLocation yellow_paint_still = new ResourceLocation(FurenikusRoads.MODID + ":fluids/yellow_paint_still");
		ResourceLocation red_paint_flowing = new ResourceLocation(FurenikusRoads.MODID + ":fluids/red_paint_flowing");
		ResourceLocation red_paint_still = new ResourceLocation(FurenikusRoads.MODID + ":fluids/red_paint_still");
		
		ResourceLocation cats_eye_white  = new ResourceLocation(FurenikusRoads.MODID + ":blocks/cats_eye_white");
		ResourceLocation cats_eye_yellow = new ResourceLocation(FurenikusRoads.MODID + ":blocks/cats_eye_yellow");
		ResourceLocation cats_eye_red    = new ResourceLocation(FurenikusRoads.MODID + ":blocks/cats_eye_red");
		ResourceLocation cats_eye_green  = new ResourceLocation(FurenikusRoads.MODID + ":blocks/cats_eye_green");
		
		ResourceLocation machine_vent_on = new ResourceLocation(FurenikusRoads.MODID + ":blocks/machine_vent_back_on");
		ResourceLocation paint_filler_display = new ResourceLocation(FurenikusRoads.MODID + ":blocks/paint_filler_machine_display");
		
		ResourceLocation sprite_white_paint = new ResourceLocation(FurenikusRoads.MODID + ":fluids/white_paint_flowing");
		ResourceLocation sprite_yellow_paint = new ResourceLocation(FurenikusRoads.MODID + ":fluids/yellow_paint_flowing");
		ResourceLocation sprite_red_paint = new ResourceLocation(FurenikusRoads.MODID + ":fluids/red_paint_flowing");
		
		ResourceLocation sprite_glass = new ResourceLocation(FurenikusRoads.MODID + ":blocks/hopper_tank");
		ResourceLocation sprite_glass_top = new ResourceLocation(FurenikusRoads.MODID + ":blocks/hopper_tank_top");
		
		ResourceLocation sprite_light_white = new ResourceLocation(FurenikusRoads.MODID + ":blocks/hopper_type_white");
		ResourceLocation sprite_light_yellow = new ResourceLocation(FurenikusRoads.MODID + ":blocks/hopper_type_yellow");
		ResourceLocation sprite_light_red = new ResourceLocation(FurenikusRoads.MODID + ":blocks/hopper_type_red");
		ResourceLocation sprite_light_item = new ResourceLocation(FurenikusRoads.MODID + ":blocks/hopper_type_item");
		ResourceLocation sprite_light_none = new ResourceLocation(FurenikusRoads.MODID + ":blocks/hopper_type_none");
		
		
		
		event.getMap().registerSprite(white_paint);
		event.getMap().registerSprite(yellow_paint);
		event.getMap().registerSprite(red_paint);
		
		event.getMap().registerSprite(block_white_paint);
		event.getMap().registerSprite(block_yellow_paint);
		event.getMap().registerSprite(block_red_paint);
		
		event.getMap().registerSprite(tar_flowing);
		event.getMap().registerSprite(tar_still);
		event.getMap().registerSprite(white_paint_flowing);
		event.getMap().registerSprite(white_paint_still);
		event.getMap().registerSprite(yellow_paint_flowing);
		event.getMap().registerSprite(yellow_paint_still);
		event.getMap().registerSprite(red_paint_flowing);
		event.getMap().registerSprite(red_paint_still);
		
		event.getMap().registerSprite(cats_eye_white);
		event.getMap().registerSprite(cats_eye_yellow);
		event.getMap().registerSprite(cats_eye_red);
		event.getMap().registerSprite(cats_eye_green);
		
		event.getMap().registerSprite(machine_vent_on);
		event.getMap().registerSprite(paint_filler_display);
		
		event.getMap().registerSprite(sprite_white_paint);
		event.getMap().registerSprite(sprite_yellow_paint);
		event.getMap().registerSprite(sprite_red_paint);
		
		event.getMap().registerSprite(sprite_glass);
		event.getMap().registerSprite(sprite_glass_top);
		
		event.getMap().registerSprite(sprite_light_white);
		event.getMap().registerSprite(sprite_light_yellow);
		event.getMap().registerSprite(sprite_light_red);
		event.getMap().registerSprite(sprite_light_item);
		event.getMap().registerSprite(sprite_light_none);
		
		for (int i = 0; i < PaintGunItemRegistry.categoryList.size(); i++) {
			PaintCategoryList category = PaintGunItemRegistry.categoryList.get(i);
			for (int j = 0; j < category.size(); j++) {
				PaintBlockBase block = category.getPaint(j);
				String name = block.getUnlocalizedName().substring(20); //tile.furenikusroads: ...
				if (category.getMeta(j) > 0) { name = name + "_" + category.getMeta(j); }
				ResourceLocation sprite = new ResourceLocation(FurenikusRoads.MODID + ":items/paint_gun_display/" + name);
				event.getMap().registerSprite(sprite);
			}
		}
	}

	@SubscribeEvent
	public void paintTextures(TextureStitchEvent.Pre event) {
		ResourceLocation line_straight_full_0 = new ResourceLocation(FurenikusRoads.MODID + ":paints/line_straight_full_0");
		ResourceLocation line_straight_full_2 = new ResourceLocation(FurenikusRoads.MODID + ":paints/line_straight_full_2");
		
		ResourceLocation line_straight_thick_0 = new ResourceLocation(FurenikusRoads.MODID + ":paints/line_straight_thick_0");
		ResourceLocation line_straight_thick_2 = new ResourceLocation(FurenikusRoads.MODID + ":paints/line_straight_thick_2");
		
		ResourceLocation line_straight_double_0 = new ResourceLocation(FurenikusRoads.MODID + ":paints/line_straight_double_0");
		ResourceLocation line_straight_double_2 = new ResourceLocation(FurenikusRoads.MODID + ":paints/line_straight_double_2");
		
		ResourceLocation line_straight_double_thick_0 = new ResourceLocation(FurenikusRoads.MODID + ":paints/line_straight_double_thick_0");
		ResourceLocation line_straight_double_thick_2 = new ResourceLocation(FurenikusRoads.MODID + ":paints/line_straight_double_thick_2");
		
		ResourceLocation line_side_double = new ResourceLocation(FurenikusRoads.MODID + ":paints/line_side_double_0");
		ResourceLocation line_side_double_thick = new ResourceLocation(FurenikusRoads.MODID + ":paints/line_side_double_thick_0");
		ResourceLocation line_side_single = new ResourceLocation(FurenikusRoads.MODID + ":paints/line_side_single_0");
		ResourceLocation line_side_single_thick = new ResourceLocation(FurenikusRoads.MODID + ":paints/line_side_single_thick_0");
		
		ResourceLocation line_far_side = new ResourceLocation(FurenikusRoads.MODID + ":paints/line_far_side_0");
		ResourceLocation line_far_side_thick = new ResourceLocation(FurenikusRoads.MODID + ":paints/line_far_side_thick_0");
		
		event.getMap().registerSprite(line_straight_full_0);
		event.getMap().registerSprite(line_straight_full_2);
		
		event.getMap().registerSprite(line_straight_thick_0);
		event.getMap().registerSprite(line_straight_thick_2);
		
		event.getMap().registerSprite(line_straight_double_0);
		event.getMap().registerSprite(line_straight_double_2);
		
		event.getMap().registerSprite(line_straight_double_thick_0);
		event.getMap().registerSprite(line_straight_double_thick_2);
		
		event.getMap().registerSprite(line_side_double);
		event.getMap().registerSprite(line_side_double_thick);
		event.getMap().registerSprite(line_side_single);
		event.getMap().registerSprite(line_side_single_thick);
		
		event.getMap().registerSprite(line_far_side);
		event.getMap().registerSprite(line_far_side_thick);
	}
}
