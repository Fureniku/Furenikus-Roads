package com.silvaniastudios.roads;

import net.minecraftforge.common.config.Config;

@Config(modid = FurenikusRoads.MODID, name = "FlenixRoads")
@Config.LangKey("flenixroads.config.title_flenixroads")
public class RoadsConfig {
	
	@Config.Name("General Settings")
	public static General general = new General();
	
	@Config.Name("Developer Things")
	public static Dev dev = new Dev();
	
	@Config.Name("Modules")
	public static Modules modules = new Modules();

	public static class General {
		@Config.Comment("If the block under paint is broken, should the paint vanish? \n"
				+ "(NOTE: 'False' might cause immersion-breaking things, but prevent frustration!)")
		public boolean breakPaintOnBlockBreak = false;
	}
	
	public static class Modules {
		@Config.Comment("Core road blocks. You probably want these. \n"
				+ "19 IDs used.")
		public boolean road_blocks = true;
		
		@Config.Comment("Basic white lines (straight lines, side lines and so on) \n"
				+ "")
		public boolean white_basic_paints = true;
		
		@Config.Comment("White icons \n"
				+ "")
		public boolean white_icons = true;
		
		@Config.Comment("White texts (for example, \"Bus\", \"Stop\", and so on, and individual letters/numbers. \n"
				+ "38 IDs")
		public boolean white_text  = true;
		
		@Config.Comment("White junction dividers \n"
				+ "")
		public boolean white_junction = true;
		
		@Config.Comment("Anything too small to be categorized")
		public boolean white_other = true;
		
		
		@Config.Comment("Yellow texts (for example, \"Bus\", \"Stop\", and so on, and individual letters/numbers. \n"
				+ "38 IDs")
		public boolean yellow_text  = true;
		
		@Config.Comment("Anything too small to be categorized")
		public boolean yellow_other = true;
		
		@Config.Comment("Red texts (for example, \"Bus\", \"Stop\", and so on, and individual letters/numbers. \n"
				+ "38 IDs")
		public boolean red_text  = true;
		
		@Config.Comment("Anything too small to be categorized")
		public boolean red_other = true;
		
		public boolean euro_marking_pack = false;
		public boolean asia_marking_pack = false;
		public boolean oceana_marking_pack = false;
	}
	
	public static class Dev {
		
	}
}
