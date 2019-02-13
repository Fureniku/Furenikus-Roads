package com.silvaniastudios.roads;

import net.minecraftforge.common.config.Config;

@Config(modid = FurenikusRoads.MODID, name = "Fureniku's Roads")
@Config.LangKey("furenikusroads.config.title_furenikusroads")
public class RoadsConfig {
	
	@Config.Name("General Settings")
	public static General general = new General();

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
}
