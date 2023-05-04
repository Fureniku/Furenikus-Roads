package com.silvaniastudios.roads.client;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.diagonal.ShapeLibrary;
import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;
import com.silvaniastudios.roads.blocks.paint.PaintBlockCustomRenderBase;
import com.silvaniastudios.roads.registries.PaintCategoryList;
import com.silvaniastudios.roads.registries.PaintGunItemRegistry;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
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
		ResourceLocation cats_eye_blue  = new ResourceLocation(FurenikusRoads.MODID + ":blocks/cats_eye_blue");

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

		//Non-custom rendering paints which need icons registering
		ResourceLocation junction_filter_left = new ResourceLocation(FurenikusRoads.MODID + ":paints/junction_filter_left_0");
		ResourceLocation junction_filter_left_empty = new ResourceLocation(FurenikusRoads.MODID + ":paints/junction_filter_left_empty_0");
		ResourceLocation junction_filter_left_thin = new ResourceLocation(FurenikusRoads.MODID + ":paints/junction_filter_left_thin_0");
		ResourceLocation junction_filter_right = new ResourceLocation(FurenikusRoads.MODID + ":paints/junction_filter_right_0");
		ResourceLocation junction_filter_right_empty = new ResourceLocation(FurenikusRoads.MODID + ":paints/junction_filter_right_empty_0");
		ResourceLocation junction_filter_right_thin = new ResourceLocation(FurenikusRoads.MODID + ":paints/junction_filter_right_thin_0");

		ResourceLocation chevron_left_a = new ResourceLocation(FurenikusRoads.MODID + ":paints/chevron_left_a_0");
		ResourceLocation chevron_left_a_thin = new ResourceLocation(FurenikusRoads.MODID + ":paints/chevron_left_a_thin_0");
		ResourceLocation chevron_right_a = new ResourceLocation(FurenikusRoads.MODID + ":paints/chevron_right_a_0");
		ResourceLocation chevron_right_a_thin = new ResourceLocation(FurenikusRoads.MODID + ":paints/chevron_right_a_thin_0");
		ResourceLocation chevron_side_line = new ResourceLocation(FurenikusRoads.MODID + ":paints/chevron_side_line_0");

		ResourceLocation hatch_box = new ResourceLocation(FurenikusRoads.MODID + ":paints/hatch_box_0");
		ResourceLocation crossing_paint = new ResourceLocation(FurenikusRoads.MODID + ":paints/crossing_paint_0");
		ResourceLocation arrow_0 = new ResourceLocation(FurenikusRoads.MODID + ":paints/arrow_0");
		ResourceLocation arrow_4 = new ResourceLocation(FurenikusRoads.MODID + ":paints/arrow_4");
		ResourceLocation arrow_line = new ResourceLocation(FurenikusRoads.MODID + ":paints/arrow_line_0");
		ResourceLocation arrow_diagonal_0 = new ResourceLocation(FurenikusRoads.MODID + ":paints/arrow_diagonal_0");
		ResourceLocation arrow_diagonal_4 = new ResourceLocation(FurenikusRoads.MODID + ":paints/arrow_diagonal_4");

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
		event.getMap().registerSprite(cats_eye_blue);

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

		event.getMap().registerSprite(junction_filter_left);
		event.getMap().registerSprite(junction_filter_left_empty);
		event.getMap().registerSprite(junction_filter_left_thin);
		event.getMap().registerSprite(junction_filter_right);
		event.getMap().registerSprite(junction_filter_right_empty);
		event.getMap().registerSprite(junction_filter_right_thin);

		event.getMap().registerSprite(chevron_left_a);
		event.getMap().registerSprite(chevron_left_a_thin);
		event.getMap().registerSprite(chevron_right_a);
		event.getMap().registerSprite(chevron_right_a_thin);
		event.getMap().registerSprite(chevron_side_line);

		event.getMap().registerSprite(hatch_box);
		event.getMap().registerSprite(crossing_paint);
		event.getMap().registerSprite(arrow_0);
		event.getMap().registerSprite(arrow_4);
		event.getMap().registerSprite(arrow_line);
		event.getMap().registerSprite(arrow_diagonal_0);
		event.getMap().registerSprite(arrow_diagonal_4);

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

		if (FurenikusRoads.genInternalTextures) {
			FurenikusRoads.debug(0, "Generating internal texture set is enabled. If you're seeing this spammed, and this isn't a dev build, please tell me coz I forgot to turn it off!!");
			for (int j = 0; j < FRBlocks.genTexturesList.size(); j++) {
				try {
					ShapeLibrary.getImageFromGrid(""+j, FRBlocks.genTexturesList.get(j), 0);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}

		for (int i = 0; i < paints.size(); i++) {
			if (paints.get(i) instanceof PaintBlockCustomRenderBase) {
				PaintBlockCustomRenderBase paint = (PaintBlockCustomRenderBase) paints.get(i);
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

	public static void generateJSONFromTextures() {
		FurenikusRoads.debug(0, "Generating JSONs from textures!");
		ArrayList<File> imageList = new ArrayList<>();
		String directory = "./conversions/";
		imageList = getImagesToConvert(directory, imageList);
		FurenikusRoads.debug(0, "Found " + imageList.size() + " textures to process.");

		for (int i = 0; i < imageList.size(); i++) {
			generatePixelArray(directory, getImage(imageList.get(i)), imageList.get(i).getName());
		}

		FurenikusRoads.debug(0, "Generation complete.");
	}

	private static ArrayList<File> getImagesToConvert(String dir, ArrayList<File> imageList) {
		File directory = new File(dir);

		if (!directory.exists()) {
			directory.mkdir();
		}

		File[] files = directory.listFiles();

		for (File file : files) {
			if (file.isFile() && file.getName().endsWith(".png")) {
				imageList.add(file);
			} else if (file.isDirectory()) {
				getImagesToConvert(file.getAbsolutePath(), imageList);
			}
		}

		return imageList;
	}

	private static BufferedImage getImage(File file) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return img;
	}

	//ChatGPT made this. Thanks ChatGPT.
	public static int[][] generatePixelArray(String dir, BufferedImage image, String name) {
		int width = image.getWidth();
		int height = image.getHeight();
		int[][] pixelArray = new int[width][height];

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int pixel = image.getRGB(x, y);
				int alpha = (pixel >> 24) & 0xff;
				if (alpha <= 63) { // transparent pixel
					pixelArray[y][x] = 0;
				} else { // opaque pixel
					pixelArray[y][x] = 1;
				}
			}
		}

		FurenikusRoads.debug(0, "Generating text file for image size " + width + ", " + height);
		try {
			int rowCount = 0;
			int colCount = 0;
			FileWriter writer = new FileWriter(dir + name.replace(".png", ".json"));
			writer.write("{\n");
			writer.write("  \"grid\":[\n");
			for (int[] row : pixelArray) {
				writer.write("    [");
				rowCount++;
				for (int pixel : row) {
					colCount++;
					if (colCount < row.length) {
						writer.write(pixel + ",");
					} else {
						writer.write(""+pixel);
					}
				}
				colCount = 0;
				if (rowCount < pixelArray.length) {
					writer.write("],\n");
				} else {
					writer.write("]\n");
				}
			}
			writer.write("  ]\n");
			writer.write("}");
			writer.close();
		} catch (IOException e) {
			System.err.println("Error writing pixel array to file: " + e.getMessage());
		}

		return pixelArray;
	}

	/**
	 * Only for use on white paints. Doesn't check/verify the colours.
	 * @param unlocalizedName the unlocalized name of the block
	 * @return the same string, without the tile.furenikusroads. bit and without white_
	 */
	public static String getTextureNameFromUnloc(String unlocalizedName) {
		return unlocalizedName.replace("white_", "").replace("tile.furenikusroads.", "");
	}
}
