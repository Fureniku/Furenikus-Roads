package com.silvaniastudios.roads;

import net.minecraftforge.common.config.Config;

@Config(modid = FurenikusRoads.MODID, name = "Fureniku's Roads")
@Config.LangKey("furenikusroads.config.title_furenikusroads")
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
		public boolean breakPaintOnBlockBreak = true;
		@Config.Comment("How many blocks worth of paint one dye gives in the Paint Filler")
		public int paintPerDye = 1000;
		@Config.Comment("How often the Paint Filler processes. Default is 1.25 seconds - higher values may cause lag.")
		public int fillerTickRate = 25;
		@Config.Comment("How often the Road Factory processes. Default is 1.25 seconds - higher values may cause lag.")
		public int roadFactoryTickRate = 25;
		@Config.Comment("How often the Tar Distiller processes. Default is 1.25 seconds - higher values may cause lag.")
		public int tarDistillerTickRate = 25;
		@Config.Comment("How often the Tarmac Cutter processes. Default is 2.5 seconds - higher values may cause lag.")
		public int tarmacCutterTickRate = 50;
		@Config.Comment("How often the Tarmac Cutter processes. Default is 0.5 seconds - higher values may cause lag.")
		public int crusherTickRate = 10;
		
		@Config.Comment("How much paint is consumed when you place one block")
		public int costToPaint = 50;
	}
	
	public static class Modules {
		@Config.Comment("Core road blocks. You probably want these. \n"
				+ "19 IDs used.")
		public boolean road_blocks = true;
		
		@Config.Comment("Basic lines (straight lines, side lines and so on) \n"
				+ "")
		public boolean basic_paints = true;
		
		@Config.Comment("Icons \n"
				+ "")
		public boolean icons = true;
		
		@Config.Comment("Texts (for example, \"Bus\", \"Stop\", and so on, and individual letters/numbers. \n"
				+ "38 IDs")
		public boolean text  = true;
		
		@Config.Comment("Junction dividers \n"
				+ "")
		public boolean junction = true;
		
		@Config.Comment("Anything too small to be categorized")
		public boolean other = true;

		
		public boolean euro_marking_pack = false;
		public boolean asia_marking_pack = false;
		public boolean oceana_marking_pack = false;
	}
	
	public static class Dev {
		
	}
}
