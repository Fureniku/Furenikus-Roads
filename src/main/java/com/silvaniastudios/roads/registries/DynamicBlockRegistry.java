package com.silvaniastudios.roads.registries;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.PaintColour;
import com.silvaniastudios.roads.blocks.paint.LargeTextPaintBlock;
import com.silvaniastudios.roads.blocks.paint.customs.*;
import com.silvaniastudios.roads.blocks.paint.properties.PaintGrid;

public class DynamicBlockRegistry {
	
	public static ArrayList<CustomPaintBlock> customPaints = new ArrayList<>();

	private static ArrayList<File> jsonList = new ArrayList<File>();
	
	public static void register() {
		FurenikusRoads.debug(0, "Starting to load custom paint files");
		jsonList = getJsonFiles("./mods/RoadPaints/", jsonList);
		
		if (jsonList.size() > 0) {
			FurenikusRoads.debug(0, "Found " + jsonList.size() + " custom paint files. Loading...");
			int success = 0;

			for (int a = 0; a < FRBlocks.col.size(); a++) {
				success += registerColour(FRBlocks.col.get(a));
			}
			
			FurenikusRoads.debug(0, "Custom paint files loading complete, " + success + "/" + jsonList.size()*FRBlocks.col.size() + " (with " + FRBlocks.col.size() + " internal colour variants) files loaded successfully.");
		} else {
			FurenikusRoads.debug(0, "No custom paint files found; skipping custom paint loading.");
		}
		System.out.println("Final: " + customPaints.size());
	}

	public static int registerColour(PaintColour col) {
		int success = 0;
		for (int i = 0; i < jsonList.size(); i++) {
			CustomPaintBlock block = importedBlock(jsonList.get(i), col);
			if (block != null) {
				customPaints.add(block);
				success++;
			} else {
				FurenikusRoads.debug(0, jsonList.get(i).getName() + " failed to load. Skipping...");
			}
		}

		return success;
	}
	
	private static ArrayList<File> getJsonFiles(String dir, ArrayList<File> jsonList) {
		File directory = new File(dir);
		
		if (!directory.exists()) {
			FurenikusRoads.debug(0, "Custom paint directory is missing; creating...");
			directory.mkdir();
		}
		
		File[] files = directory.listFiles();
		
		for (File file : files) {
			if (file.isFile()) {
				jsonList.add(file);
			} else if (file.isDirectory()) {
				getJsonFiles(file.getAbsolutePath(), jsonList);
			}
		}
		
		return jsonList;
	}
	
	private static CustomPaintBlock importedBlock(File file, PaintColour col) {
		JsonParser parser = new JsonParser();
		CustomPaintBlock block = null;
		
		try {
			JsonObject json = (JsonObject) parser.parse(new FileReader(file));
			
			String name = json.has("blockName") ? json.get("blockName").getAsString() : file.getName();
			String localName = json.has("localName") ? json.get("localName").getAsString() : "NAME_READ_FAILED";
			String category = json.has("category") ? json.get("category").getAsString() : "line";
			String type = json.has("type") ? json.get("type").getAsString() : "1x1";
			boolean horizontal = json.has("horizontal") ? json.get("horizontal").getAsBoolean() : true;
			JsonArray grid = json.has("grid") ? json.get("grid").getAsJsonArray() : null;
			JsonArray grid_b = json.has("grid_b") ? json.get("grid_b").getAsJsonArray() : null;
			JsonArray grid_c = json.has("grid_c") ? json.get("grid_c").getAsJsonArray() : null;
			JsonArray grid_d = json.has("grid_d") ? json.get("grid_d").getAsJsonArray() : null;
			//type
			
			for (int i = 0; i < FRBlocks.col.size(); i++) {
				if (name.toLowerCase().contains(FRBlocks.col.get(i).getName())) {
					FurenikusRoads.debug(0, "Paint JSON file " + name + " contains illegal word. Please don't use \"" + FRBlocks.col.get(i).getName() + "\" anywhere in your paint name.");
				}
			}
			
			if (grid != null) {
				PaintGrid gridArray_a = new PaintGrid(getGridArray(grid, name));
				PaintGrid gridArray_b = grid_b != null ? new PaintGrid(getGridArray(grid_b, name)) : PaintGrid.EMPTY;
				PaintGrid gridArray_c = grid_c != null ? new PaintGrid(getGridArray(grid_c, name)) : PaintGrid.EMPTY;
				PaintGrid gridArray_d = grid_d != null ? new PaintGrid(getGridArray(grid_d, name)) : PaintGrid.EMPTY;


				CustomPaintBlock.EnumPaintType paintType = CustomPaintBlock.EnumPaintType.getFromString(type);

				switch (paintType) {
					case ICON_1x1:
						block = new CustomPaintBlock(name + "_" + col.getName(), localName, paintType, new PaintGrid[] {gridArray_a}, category, new int[] {0}, col);
						break;
					case WALL_ICON_1x1:
						block = new CustomPaintWallBlock(name + "_" + col.getName(), localName, new PaintGrid[] {gridArray_a}, category, new int[] {0}, col);
						break;
					case MULTI_2x1:
						block = new Custom1x2PaintBlock(name + "_" + col.getName(), localName, new PaintGrid[]{gridArray_a, gridArray_b}, category, col, horizontal);
						break;
					case MULTI_3x1:
						block = new Custom1x3PaintBlock(name + "_" + col.getName(), localName, new PaintGrid[]{gridArray_a, gridArray_b, gridArray_c}, category, col, horizontal);
						break;
					case MULTI_4x1:
						block = new Custom1x4PaintBlock(name + "_" + col.getName(), localName, new PaintGrid[]{gridArray_a, gridArray_b, gridArray_c, gridArray_d}, category, col, horizontal);
						break;
					case LARGE_TEXT:
						block = new LargeTextPaintBlock(name + "_" + col.getName(), localName, new PaintGrid[]{gridArray_a, gridArray_b, gridArray_c, gridArray_d}, category, col);
						break;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return block;
	}

	private static boolean[][] getGridArray(JsonArray grid, String name) {
		if (!(grid.size() == 16 || grid.size() == 32 || grid.size() == 64)) {
			FurenikusRoads.debug(0, "Paint JSON file " + name + " has malformed Y axis grid. Make sure there are exactly 16, 32 or 64 entries; it has " + grid.size());
			return null;
		}

		boolean[][] gridArray = new boolean[grid.size()][grid.size()];

		for (int i = 0; i < grid.size(); i++) {
			JsonArray gridRow = grid.get(i).getAsJsonArray();
			if (gridRow.size() != grid.size()) {
				FurenikusRoads.debug(0, "Paint JSON file " + name + " has malformed X axis grid. Make sure there are exactly " + grid.size() + " entries; it has " + gridRow.size());
				return null;
			}
			for (int j = 0; j < gridRow.size(); j++) {
				char c = gridRow.get(j).getAsCharacter();
				if (c == ' ' || c == '0') {
					gridArray[j][i] = false;
				} else {
					gridArray[j][i] = true;
				}
			}
		}
		return gridArray;
	}
}
