package com.silvaniastudios.roads.registries;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.paint.properties.PaintGrid;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.*;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class CustomPaintModelRegistry {

    static ArrayList<File> jsonList = new ArrayList<>();

    public PaintGrid wheelchair_icon = importedGrid("wheelchair_icon");
    public PaintGrid chevron_icon = importedGrid("chevron_icon");

    public PaintGrid pedestrian_low = importedGrid("pedestrian_low");
    public PaintGrid pedestrian_high = importedGrid("pedestrian_high");
    public PaintGrid merge_arrow_low = importedGrid("merge_arrow_low");
    public PaintGrid merge_arrow_high = importedGrid("merge_arrow_high");
    public PaintGrid give_way_low = importedGrid("give_way_low");
    public PaintGrid give_way_high = importedGrid("give_way_high");

    public PaintGrid junction_in_a = importedGrid("junction_in_a");
    public PaintGrid junction_in_b = importedGrid("junction_in_b");
    public PaintGrid junction_out_a = importedGrid("junction_out_a");
    public PaintGrid junction_out_b = importedGrid("junction_out_b");
    public PaintGrid junction_mid_a = importedGrid("junction_mid_a");
    public PaintGrid junction_mid_b = importedGrid("junction_mid_b");

    public PaintGrid chevron_mid_1 = importedGrid("chevron_mid_1");
    public PaintGrid chevron_mid_2 = importedGrid("chevron_mid_2");
    public PaintGrid chevron_mid_thin_1 = importedGrid("chevron_mid_thin_1");
    public PaintGrid chevron_mid_thin_2 = importedGrid("chevron_mid_thin_2");

    public PaintGrid chevron_mid_left_1 = importedGrid("chevron_mid_left_1");
    public PaintGrid chevron_mid_left_2 = importedGrid("chevron_mid_left_2");
    public PaintGrid chevron_mid_thin_left_1 = importedGrid("chevron_mid_thin_left_1");
    public PaintGrid chevron_mid_thin_left_2 = importedGrid("chevron_mid_thin_left_2");

    public PaintGrid chevron_mid_right_1 = importedGrid("chevron_mid_right_1");
    public PaintGrid chevron_mid_right_2 = importedGrid("chevron_mid_right_2");
    public PaintGrid chevron_mid_thin_right_1 = importedGrid("chevron_mid_thin_right_1");
    public PaintGrid chevron_mid_thin_right_2 = importedGrid("chevron_mid_thin_right_2");

    public PaintGrid crossing_diagonal_1 = importedGrid("crossing_diagonal_1");
    public PaintGrid crossing_diagonal_2 = importedGrid("crossing_diagonal_2");
    public PaintGrid crossing_diagonal_3 = importedGrid("crossing_diagonal_3");
    public PaintGrid crossing_diagonal_4 = importedGrid("crossing_diagonal_4");

    public PaintGrid empty = importedGrid("empty");
    public PaintGrid junction_mid_1 = importedGrid("junction_mid_1");
    public PaintGrid junction_mid_2 = importedGrid("junction_mid_2");
    public PaintGrid junction_mid_3 = importedGrid("junction_mid_3");
    public PaintGrid junction_mid_thin_1 = importedGrid("junction_mid_thin_1");
    public PaintGrid junction_mid_thin_2 = importedGrid("junction_mid_thin_2");
    public PaintGrid junction_mid_thin_3 = importedGrid("junction_mid_thin_3");
    public PaintGrid junction_mid_chevron_2 = importedGrid("junction_mid_chevron_2");
    public PaintGrid junction_mid_chevron_3 = importedGrid("junction_mid_chevron_3");
    public PaintGrid junction_mid_chevron_4 = importedGrid("junction_mid_chevron_4");
    public PaintGrid junction_mid_chevron_thin_3 = importedGrid("junction_mid_chevron_thin_3");

    public PaintGrid letter_a = importedGrid("letter_a");
    public PaintGrid letter_b = importedGrid("letter_b");
    public PaintGrid letter_c = importedGrid("letter_c");
    public PaintGrid letter_d = importedGrid("letter_d");
    public PaintGrid letter_e = importedGrid("letter_e");
    public PaintGrid letter_f = importedGrid("letter_f");
    public PaintGrid letter_g = importedGrid("letter_g");
    public PaintGrid letter_h = importedGrid("letter_h");
    public PaintGrid letter_i = importedGrid("letter_i");
    public PaintGrid letter_j = importedGrid("letter_j");
    public PaintGrid letter_k = importedGrid("letter_k");
    public PaintGrid letter_l = importedGrid("letter_l");
    public PaintGrid letter_m = importedGrid("letter_m");
    public PaintGrid letter_n = importedGrid("letter_n");
    public PaintGrid letter_o = importedGrid("letter_o");
    public PaintGrid letter_p = importedGrid("letter_p");
    public PaintGrid letter_q = importedGrid("letter_q");
    public PaintGrid letter_r = importedGrid("letter_r");
    public PaintGrid letter_s = importedGrid("letter_s");
    public PaintGrid letter_t = importedGrid("letter_t");
    public PaintGrid letter_u = importedGrid("letter_u");
    public PaintGrid letter_v = importedGrid("letter_v");
    public PaintGrid letter_w = importedGrid("letter_w");
    public PaintGrid letter_x = importedGrid("letter_x");
    public PaintGrid letter_y = importedGrid("letter_y");
    public PaintGrid letter_z = importedGrid("letter_z");

    public PaintGrid number_0 = importedGrid("number_0");
    public PaintGrid number_1 = importedGrid("number_1");
    public PaintGrid number_2 = importedGrid("number_2");
    public PaintGrid number_3 = importedGrid("number_3");
    public PaintGrid number_4 = importedGrid("number_4");
    public PaintGrid number_5 = importedGrid("number_5");
    public PaintGrid number_6 = importedGrid("number_6");
    public PaintGrid number_7 = importedGrid("number_7");
    public PaintGrid number_8 = importedGrid("number_8");
    public PaintGrid number_9 = importedGrid("number_9");

    public PaintGrid punct_question = importedGrid("punct_question");
    public PaintGrid punct_exclamation = importedGrid("punct_exclaim");
    public PaintGrid punct_hash = importedGrid("punct_hash");
    public PaintGrid punct_slash = importedGrid("punct_slash");

    public PaintGrid text_slow_small  = importedGrid("text_slow_small");
    public PaintGrid text_stop_small  = importedGrid("text_stop_small");
    public PaintGrid text_bike_small  = importedGrid("text_bike_small");
    public PaintGrid text_bus_small   = importedGrid("text_bus_small");
    public PaintGrid text_taxi_small  = importedGrid("text_taxi_small");
    public PaintGrid text_lane_small  = importedGrid("text_lane_small");
    public PaintGrid text_keep_small  = importedGrid("text_keep_small");
    public PaintGrid text_clear_small = importedGrid("text_clear_small");
    public PaintGrid text_turn_small  = importedGrid("text_turn_small");
    public PaintGrid text_left_small  = importedGrid("text_left_small");
    public PaintGrid text_right_small = importedGrid("text_right_small");
    public PaintGrid text_only_small  = importedGrid("text_only_small");
    public PaintGrid text_no_small    = importedGrid("text_no_small");
    public PaintGrid text_entry_small = importedGrid("text_entry_small");
    public PaintGrid text_bike_icon_small = importedGrid("text_bike_icon_small");
    public PaintGrid text_town_small = importedGrid("text_town_small");
    public PaintGrid text_city_small = importedGrid("text_city_small");
    public PaintGrid text_ctre_small = importedGrid("text_ctre_small");

    public PaintGrid text_slow_l  = importedGrid("text_slow_l");
    public PaintGrid text_stop_l  = importedGrid("text_stop_l");
    public PaintGrid text_bike_l  = importedGrid("text_bike_l");
    public PaintGrid text_bus_l   = importedGrid("text_bus_l");
    public PaintGrid text_taxi_l  = importedGrid("text_taxi_l");
    public PaintGrid text_lane_l  = importedGrid("text_lane_l");
    public PaintGrid text_keep_l  = importedGrid("text_keep_l");
    public PaintGrid text_clear_l = importedGrid("text_clear_l");
    public PaintGrid text_turn_l  = importedGrid("text_turn_l");
    public PaintGrid text_left_l  = importedGrid("text_left_l");
    public PaintGrid text_right_l = importedGrid("text_right_l");
    public PaintGrid text_only_l  = importedGrid("text_only_l");
    public PaintGrid text_no_l    = importedGrid("text_no_l");
    public PaintGrid text_entry_l = importedGrid("text_entry_l");
    public PaintGrid text_bike_icon_l = importedGrid("text_bike_icon_l");
    public PaintGrid text_town_l = importedGrid("text_town_l");
    public PaintGrid text_city_l = importedGrid("text_city_l");
    public PaintGrid text_ctre_l = importedGrid("text_ctre_l");

    public PaintGrid text_slow_m  = importedGrid("text_slow_m");
    public PaintGrid text_stop_m  = importedGrid("text_stop_m");
    public PaintGrid text_bike_m  = importedGrid("text_bike_m");
    public PaintGrid text_bus_m   = importedGrid("text_bus_m");
    public PaintGrid text_taxi_m  = importedGrid("text_taxi_m");
    public PaintGrid text_lane_m  = importedGrid("text_lane_m");
    public PaintGrid text_keep_m  = importedGrid("text_keep_m");
    public PaintGrid text_clear_m = importedGrid("text_clear_m");
    public PaintGrid text_turn_m  = importedGrid("text_turn_m");
    public PaintGrid text_left_m  = importedGrid("text_left_m");
    public PaintGrid text_right_m = importedGrid("text_right_m");
    public PaintGrid text_only_m  = importedGrid("text_only_m");
    public PaintGrid text_no_m    = importedGrid("text_no_m");
    public PaintGrid text_entry_m = importedGrid("text_entry_m");
    public PaintGrid text_bike_icon_m = importedGrid("text_bike_icon_m");
    public PaintGrid text_town_m = importedGrid("text_town_m");
    public PaintGrid text_city_m = importedGrid("text_city_m");
    public PaintGrid text_ctre_m = importedGrid("text_ctre_m");

    public PaintGrid text_slow_r  = importedGrid("text_slow_r");
    public PaintGrid text_stop_r  = importedGrid("text_stop_r");
    public PaintGrid text_bike_r  = importedGrid("text_bike_r");
    public PaintGrid text_bus_r   = importedGrid("text_bus_r");
    public PaintGrid text_taxi_r  = importedGrid("text_taxi_r");
    public PaintGrid text_lane_r  = importedGrid("text_lane_r");
    public PaintGrid text_keep_r  = importedGrid("text_keep_r");
    public PaintGrid text_clear_r = importedGrid("text_clear_r");
    public PaintGrid text_turn_r  = importedGrid("text_turn_r");
    public PaintGrid text_left_r  = importedGrid("text_left_r");
    public PaintGrid text_right_r = importedGrid("text_right_r");
    public PaintGrid text_only_r  = importedGrid("text_only_r");
    public PaintGrid text_no_r    = importedGrid("text_no_r");
    public PaintGrid text_entry_r = importedGrid("text_entry_r");
    public PaintGrid text_bike_icon_r = importedGrid("text_bike_icon_r");
    public PaintGrid text_town_r = importedGrid("text_town_r");
    public PaintGrid text_city_r = importedGrid("text_city_r");
    public PaintGrid text_ctre_r = importedGrid("text_ctre_r");

    public PaintGrid junction_side_line_connection_a = importedGrid("junction_side_line_connection_a");
    public PaintGrid junction_side_line_connection_b = importedGrid("junction_side_line_connection_b");
    public PaintGrid junction_side_line_connection_c = importedGrid("junction_side_line_connection_c");
    public PaintGrid junction_side_line_connection_d = importedGrid("junction_side_line_connection_d");

    public PaintGrid junction_side_line_connection_thin_a = importedGrid("junction_side_line_connection_thin_a");
    public PaintGrid junction_side_line_connection_thin_b = importedGrid("junction_side_line_connection_thin_b");
    public PaintGrid junction_side_line_connection_thin_c = importedGrid("junction_side_line_connection_thin_c");
    public PaintGrid junction_side_line_connection_thin_d = importedGrid("junction_side_line_connection_thin_d");

    public PaintGrid junction_side_line_connection_thick_thick_a = importedGrid("junction_side_line_connection_thick_thick_a");
    public PaintGrid junction_side_line_connection_thick_thick_b = importedGrid("junction_side_line_connection_thick_thick_b");
    public PaintGrid junction_side_line_connection_thick_thick_c = importedGrid("junction_side_line_connection_thick_thick_c");
    public PaintGrid junction_side_line_connection_thick_thick_d = importedGrid("junction_side_line_connection_thick_thick_d");

    public PaintGrid junction_mid_line_connection_a = importedGrid("junction_mid_line_connection_a");
    public PaintGrid junction_mid_line_connection_b = importedGrid("junction_mid_line_connection_b");
    public PaintGrid junction_mid_line_connection_c = importedGrid("junction_mid_line_connection_c");
    public PaintGrid junction_mid_line_connection_d = importedGrid("junction_mid_line_connection_d");

    private static boolean compiledSystem = false;

    public CustomPaintModelRegistry() {}

    public static void register(FMLPreInitializationEvent event) {
        FurenikusRoads.debug(0, "Starting to load internal paint files");
        jsonList = getJsonFiles(event.getModMetadata().getClass().getResource("/assets/" + FurenikusRoads.MODID + "/paints").getFile(), jsonList);

        if (jsonList.size() > 0) {
            FurenikusRoads.debug(0, "Found " + jsonList.size() + " internal paint files. Loading...");
        } else {
            FurenikusRoads.debug(0, "No internal paint files found; this is a major bug! There will be missing assets in-game.");
        }
    }

    private static ArrayList<File> getJsonFiles(String dir, ArrayList<File> jsonList) {
        File directory = new File(dir);
        File[] files = directory.listFiles();

        //Above only works in dev environment; for compiled .jar we need to handle differently.
        if (files == null) {
            FurenikusRoads.debug(0, "Running in production environment; switching to jarfile extraction for internal paint JSONs");
            return getCompiledJsonFiles(jsonList);
        }

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

    private static ArrayList<File> getCompiledJsonFiles(ArrayList<File> foundFiles) {
        compiledSystem = true;
        try {
            URL jarUrl = FurenikusRoads.class.getProtectionDomain().getCodeSource().getLocation();
            JarURLConnection jarConnection = (JarURLConnection)jarUrl.openConnection();
            JarFile jar = jarConnection.getJarFile();
            Enumeration<JarEntry> entries = jar.entries();

            int entryCount = 0;

            while (entries.hasMoreElements()) {
                entryCount++;
                JarEntry entry = entries.nextElement();

                if (!entry.isDirectory() && entry.getName().startsWith("assets/" + FurenikusRoads.MODID + "/paints")) {
                    File file = new File(entry.getName());
                    if (file.getName().endsWith(".json")) {
                        foundFiles.add(file);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return foundFiles;
    }

    private File getFileForPaint(String name) {
        for (int i = 0; i < jsonList.size(); i++) {
            if (jsonList.get(i).getName().endsWith(name + ".json")) {
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
                JsonObject json = null;
                if (CustomPaintModelRegistry.compiledSystem) {
                    json = (JsonObject) parser.parse(readFileInJar(file.getName()));
                } else {
                    json = (JsonObject) parser.parse(new FileReader(file));
                }

                JsonArray grid = json.has("grid") ? json.get("grid").getAsJsonArray() : null;

                if (grid != null) {
                    if (!(grid.size() == 16 || grid.size() == 32 || grid.size() == 64)) {
                        JsonArray gridRow = grid.get(0).getAsJsonArray();
                        FurenikusRoads.debug(0, "#INTERNAL# Paint JSON file " + name + " has malformed Y axis grid. Make sure there are exactly 16, 32 or 64 entries; it has " + grid.size() + " with X: " + gridRow.size());
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
        } else {
            FurenikusRoads.debug(0, "Internal paint file for " + name + " was null! This will crash when placing!!");
        }
        return null;
    }

    private static String readFileInJar(String fileName) throws IOException {
        StringBuilder sb = new StringBuilder();
        fileName = "assets/furenikusroads/paints/" + fileName;
        URL jarUrl = FurenikusRoads.class.getProtectionDomain().getCodeSource().getLocation();
        JarURLConnection jarConnection = (JarURLConnection)jarUrl.openConnection();
        JarFile jar = jarConnection.getJarFile();
        JarEntry entry = jar.getJarEntry(fileName);

        if (entry != null) {
            InputStream inputStream = jar.getInputStream(entry);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            inputStream.close();
        }
        jar.close();
        return sb.toString();
    }
}
