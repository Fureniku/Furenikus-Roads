package com.silvaniastudios.roads.blocks;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.paint.properties.PaintGrid;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class CustomPaintModelRegistry {

    static ArrayList<File> jsonList = new ArrayList<>();

    public PaintGrid wheelchair_icon;
    public PaintGrid chevron_icon;

    public PaintGrid letter_a;
    public PaintGrid letter_b;
    public PaintGrid letter_c;
    public PaintGrid letter_d;
    public PaintGrid letter_e;
    public PaintGrid letter_f;
    public PaintGrid letter_g;
    public PaintGrid letter_h;
    public PaintGrid letter_i;
    public PaintGrid letter_j;
    public PaintGrid letter_k;
    public PaintGrid letter_l;
    public PaintGrid letter_m;
    public PaintGrid letter_n;
    public PaintGrid letter_o;
    public PaintGrid letter_p;
    public PaintGrid letter_q;
    public PaintGrid letter_r;
    public PaintGrid letter_s;
    public PaintGrid letter_t;
    public PaintGrid letter_u;
    public PaintGrid letter_v;
    public PaintGrid letter_w;
    public PaintGrid letter_x;
    public PaintGrid letter_y;
    public PaintGrid letter_z;

    public PaintGrid number_0;
    public PaintGrid number_1;
    public PaintGrid number_2;
    public PaintGrid number_3;
    public PaintGrid number_4;
    public PaintGrid number_5;
    public PaintGrid number_6;
    public PaintGrid number_7;
    public PaintGrid number_8;
    public PaintGrid number_9;

    public PaintGrid punct_question;
    public PaintGrid punct_exclamation;
    public PaintGrid punct_hash;
    public PaintGrid punct_slash;

    public PaintGrid junction_side_line_connection_a;
    public PaintGrid junction_side_line_connection_b;
    public PaintGrid junction_side_line_connection_c;
    public PaintGrid junction_side_line_connection_d;

    public PaintGrid junction_side_line_connection_thin_a;
    public PaintGrid junction_side_line_connection_thin_b;
    public PaintGrid junction_side_line_connection_thin_c;
    public PaintGrid junction_side_line_connection_thin_d;

    public PaintGrid junction_side_line_connection_thick_thick_a;
    public PaintGrid junction_side_line_connection_thick_thick_b;
    public PaintGrid junction_side_line_connection_thick_thick_c;
    public PaintGrid junction_side_line_connection_thick_thick_d;

    public PaintGrid junction_mid_line_connection_a;
    public PaintGrid junction_mid_line_connection_b;
    public PaintGrid junction_mid_line_connection_c;
    public PaintGrid junction_mid_line_connection_d;

    public CustomPaintModelRegistry() {
        wheelchair_icon = importedGrid("wheelchair_icon");
        chevron_icon = importedGrid("chevron_icon");

        letter_a = importedGrid("letter_a");
        letter_b = importedGrid("letter_b");
        letter_c = importedGrid("letter_c");
        letter_d = importedGrid("letter_d");
        letter_e = importedGrid("letter_e");
        letter_f = importedGrid("letter_f");
        letter_g = importedGrid("letter_g");
        letter_h = importedGrid("letter_h");
        letter_i = importedGrid("letter_i");
        letter_j = importedGrid("letter_j");
        letter_k = importedGrid("letter_k");
        letter_l = importedGrid("letter_l");
        letter_m = importedGrid("letter_m");
        letter_n = importedGrid("letter_n");
        letter_o = importedGrid("letter_o");
        letter_p = importedGrid("letter_p");
        letter_q = importedGrid("letter_q");
        letter_r = importedGrid("letter_r");
        letter_s = importedGrid("letter_s");
        letter_t = importedGrid("letter_t");
        letter_u = importedGrid("letter_u");
        letter_v = importedGrid("letter_v");
        letter_w = importedGrid("letter_w");
        letter_x = importedGrid("letter_x");
        letter_y = importedGrid("letter_y");
        letter_z = importedGrid("letter_z");

        number_0 = importedGrid("number_0");
        number_1 = importedGrid("number_1");
        number_2 = importedGrid("number_2");
        number_3 = importedGrid("number_3");
        number_4 = importedGrid("number_4");
        number_5 = importedGrid("number_5");
        number_6 = importedGrid("number_6");
        number_7 = importedGrid("number_7");
        number_8 = importedGrid("number_8");
        number_9 = importedGrid("number_9");

        punct_question = importedGrid("punct_question");
        punct_exclamation = importedGrid("punct_exclaim");
        punct_hash = importedGrid("punct_hash");
        punct_slash = importedGrid("punct_slash");


        junction_side_line_connection_a = importedGrid("junction_side_line_connection_a");
        junction_side_line_connection_b = importedGrid("junction_side_line_connection_b");
        junction_side_line_connection_c = importedGrid("junction_side_line_connection_c");
        junction_side_line_connection_d = importedGrid("junction_side_line_connection_d");

        junction_side_line_connection_thin_a = importedGrid("junction_side_line_connection_thin_a");
        junction_side_line_connection_thin_b = importedGrid("junction_side_line_connection_thin_b");
        junction_side_line_connection_thin_c = importedGrid("junction_side_line_connection_thin_c");
        junction_side_line_connection_thin_d = importedGrid("junction_side_line_connection_thin_d");

        junction_side_line_connection_thick_thick_a = importedGrid("junction_side_line_connection_thick_thick_a");
        junction_side_line_connection_thick_thick_b = importedGrid("junction_side_line_connection_thick_thick_b");
        junction_side_line_connection_thick_thick_c = importedGrid("junction_side_line_connection_thick_thick_c");
        junction_side_line_connection_thick_thick_d = importedGrid("junction_side_line_connection_thick_thick_d");

        junction_mid_line_connection_a = importedGrid("junction_mid_line_connection_a");
        junction_mid_line_connection_b = importedGrid("junction_mid_line_connection_b");
        junction_mid_line_connection_c = importedGrid("junction_mid_line_connection_c");
        junction_mid_line_connection_d = importedGrid("junction_mid_line_connection_d");
    }

    public static void register(FMLPreInitializationEvent event) {
        FurenikusRoads.debug(0, "Starting to load internal paint files");
        jsonList = getJsonFiles(event.getModMetadata().getClass().getResource("/assets/" + FurenikusRoads.MODID + "/paints").getFile().toString(), jsonList);

        if (jsonList.size() > 0) {
            FurenikusRoads.debug(0, "Found " + jsonList.size() + " internal paint files. Loading...");
        } else {
            FurenikusRoads.debug(0, "No internal paint files found; this is a major bug! There will be missing assets in-game.");
        }
    }

    private static ArrayList<File> getJsonFiles(String dir, ArrayList<File> jsonList) {
        File directory = new File(dir);

        File[] files = directory.listFiles();

        if (files.length > 0) {
            for (File file : files) {
                if (file != null) {
                    if (file.isFile()) {
                        jsonList.add(file);
                    } else if (file.isDirectory()) {
                        getJsonFiles(file.getAbsolutePath(), jsonList);
                    }
                }
            }
        }

        return jsonList;
    }

    private File getFileForPaint(String name) {
        for (int i = 0; i < jsonList.size(); i++) {
            if (jsonList.get(i).getName().equals(name + ".json")) {
                return jsonList.get(i);
            }
        }
        return null;
    }

    private PaintGrid importedGrid(String name) {
        File file = getFileForPaint(name);
        if (file != null) {
            JsonParser parser = new JsonParser();

            try {
                JsonObject json = (JsonObject) parser.parse(new FileReader(file));
                JsonArray grid = json.has("grid") ? json.get("grid").getAsJsonArray() : null;

                if (grid != null) {
                    if (!(grid.size() == 16 || grid.size() == 32 || grid.size() == 64)) {
                        FurenikusRoads.debug(0, "#INTERNAL# Paint JSON file " + name + " has malformed Y axis grid. Make sure there are exactly 16, 32 or 64 entries; it has " + grid.size());
                        return null;
                    }
                    boolean[][] gridArray = new boolean[grid.size()][grid.size()];

                    for (int i = 0; i < grid.size(); i++) {
                        JsonArray gridRow = grid.get(i).getAsJsonArray();
                        if (gridRow.size() != grid.size()) {
                            FurenikusRoads.debug(0, "#INTERNAL# Paint JSON file " + name + " has malformed X axis grid. Make sure there are exactly " + grid.size() + " entries; it has " + gridRow.size());
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

                    return new PaintGrid(gridArray);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
