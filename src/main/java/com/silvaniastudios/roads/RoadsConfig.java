package com.silvaniastudios.roads;

import net.minecraftforge.common.config.Config;

@Config(modid = FurenikusRoads.MODID, name = "Fureniku's Roads")
@Config.LangKey("furenikusroads.config.title_furenikusroads")
public class RoadsConfig {
	
	@Config.Name("General Settings")
	public static General general = new General();
	
	@Config.Name("Machine Settings")
	public static Machines machine = new Machines();

	public static class General {
		@Config.Comment("If the block under paint is broken, should the paint vanish? \n"
				+ "(NOTE: 'False' might cause immersion-breaking things, but prevent frustration!)")
		public boolean breakPaintOnBlockBreak = true;
		
		@Config.Comment("How many blocks worth of paint one dye gives in the Paint Filler")
		public int paintPerDye = 1000;
		
		@Config.Comment("How much paint is consumed when you place one block")
		public int costToPaint = 50;
		
		@Config.Comment("Show additional tooltips to help in GUIs (showing names of slots etc) \nPretty much essential if you don't have JEI installed.")
		public boolean guiGuide = true;
		
		@Config.Comment("Whether snow can settle on roads. It will be correctly offset and sit properly on the roads if enabled.")
		public boolean snowOnRoads = true;
	}
	
	public static class Machines {
		@Config.Comment("How often the Crusher processes. Default is 1.5 seconds")
		public int crusherTickRate = 30;
		@Config.Comment("How often the Paint Filler processes. Default is 1.25 seconds")
		public int fillerTickRate = 25;
		@Config.Comment("How often the Road Factory processes. Default is 1.25 seconds")
		public int roadFactoryTickRate = 25;
		@Config.Comment("How often the Tar Distiller processes. Default is 1.25 seconds")
		public int tarDistillerTickRate = 25;
		@Config.Comment("How often the Tarmac Cutter processes. Default is 2.5 seconds")
		public int tarmacCutterTickRate = 50;
		
		@Config.Comment("How often the Electric Crusher processes. Default is 1.5 seconds")
		public int electricCrusherTickRate = 30;
		@Config.Comment("How often the Electric Paint Filler processes. Default is 1.25 seconds")
		public int electricFillerTickRate = 25;
		@Config.Comment("How often the Electric Road Factory processes. Default is 1.25 seconds")
		public int electricRoadFactoryTickRate = 25;
		@Config.Comment("How often the Electric Tar Distiller processes. Default is 1.25 seconds")
		public int electricTarDistillerTickRate = 25;
		@Config.Comment("How often the Electric Tarmac Cutter processes. Default is 2.5 seconds")
		public int electricTarmacCutterTickRate = 50;
		
		@Config.Comment("How much energy to consume per action.")
		public int electricRoadFactoryEnergyConsumption = 1000;
		@Config.Comment("How much energy to consume per action.")
		public int electricTarDistillerEnergyConsumption = 1000;
		@Config.Comment("How much energy to consume per action.")
		public int electricTarmacCutterEnergyConsumption = 1000;
		@Config.Comment("How much energy to consume per action.")
		public int electricCrusherEnergyConsumption = 1000;
		@Config.Comment("How much energy to consume per action.")
		public int electricPaintFillerEnergyConsumption = 1000;
		
		@Config.Comment("How much energy the machine can hold")
		public int electricRoadFactoryEnergyStorage = 50000;
		@Config.Comment("How much energy the machine can hold")
		public int electricTarDistillerEnergyStorage = 50000;
		@Config.Comment("How much energy the machine can hold")
		public int electricTarmacCutterEnergyStorage = 50000;
		@Config.Comment("How much energy the machine can hold")
		public int electricCrusherEnergyStorage = 50000;
		@Config.Comment("How much energy the machine can hold")
		public int electricPaintFillerEnergyStorage = 50000;
		
		@Config.Comment("How much energy can transfer in per tick.")
		public int electricRoadFactoryEnergyTransferRate = 100;
		@Config.Comment("How much energy can transfer in per tick.")
		public int electricTarDistillerEnergyTransferRate = 100;
		@Config.Comment("How much energy can transfer in per tick.")
		public int electricTarmacCutterEnergyTransferRate = 100;
		@Config.Comment("How much energy can transfer in per tick.")
		public int electricCrusherEnergyTransferRate = 100;
		@Config.Comment("How much energy can transfer in per tick.")
		public int electricPaintFillerEnergyTransferRate = 100;
	}
}
