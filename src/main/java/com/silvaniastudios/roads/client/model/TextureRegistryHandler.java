package com.silvaniastudios.roads.client.model;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.diagonal.ShapeLibrary;
import com.silvaniastudios.roads.blocks.paint.customs.CustomPaintBlock;
import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;
import com.silvaniastudios.roads.blocks.paint.PaintBlockCustomRenderBase;
import com.silvaniastudios.roads.registries.DynamicBlockRegistry;
import com.silvaniastudios.roads.registries.PaintCategoryList;
import com.silvaniastudios.roads.registries.PaintGunItemRegistry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

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
		ArrayList<PaintBlockBase> paints = FRBlocks.paintBlockList;
		ArrayList<String> registry = new ArrayList<>();

		for (int i = 0; i < paints.size(); i++) {
			if (paints.get(i) instanceof PaintBlockCustomRenderBase) {
				PaintBlockCustomRenderBase paint = (PaintBlockCustomRenderBase) paints.get(i);
				if (paint instanceof CustomPaintBlock && FurenikusRoads.genInternalTextures) {
					FurenikusRoads.debug(0, "Generating internal texture set is enabled. If you're seeing this spammed, and this isn't a dev build, please tell me coz I forgot to turn it off!!");
					CustomPaintBlock customPaint = (CustomPaintBlock) paint;
					if (customPaint.isInternal() && customPaint.getUnlocalizedName().contains("white")) {
						for (int j = 0; j < customPaint.getCoreMetas().length; j++) {
							try {
								ShapeLibrary.getImageFromGrid(getTextureNameFromUnloc(customPaint.getUnlocalizedName()), customPaint.getGrid(j), customPaint.getCoreMetas()[j]);
							} catch (IOException e) {
								throw new RuntimeException(e);
							}
						}

					}
				}
				if (paint.getUnlocalizedName().contains("white")) {
					for (int j = 0; j < paint.getCoreMetas().length; j++) {
						registry.add(getTextureNameFromUnloc(paint.getUnlocalizedName()) + "_" + paint.getCoreMetas()[j]);
					}
				}
			}
		}

		for (int i = 0; i < registry.size(); i++) {
			ResourceLocation rl = new ResourceLocation(FurenikusRoads.MODID + ":paints/" + registry.get(i));
			event.getMap().registerSprite(rl);
		}
	}

	/**
	 * Only for use on white paints. Doesn't check/verify the colours.
	 * @param unlocalizedName the unlocalized name of the block
	 * @return the same string, without the tile.furenikusroads. bit and without white_
	 */
	public static String getTextureNameFromUnloc(String unlocalizedName) {
		return unlocalizedName.replace("white_", "").replace("tile.furenikusroads.", "");
	}

	public static ArrayList<TextureAtlasSprite> customSprites = new ArrayList<>();
	public static ArrayList<ResourceLocation> customLocations = new ArrayList<>();
}
