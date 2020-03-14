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
		
		@Config.Comment("How much paint is consumed when you place one block")
		public int costToPaint = 50;
		
		@Config.Comment("Show additional tooltips to help in GUIs (showing names of slots etc) \nPretty much essential if you don't have JEI installed.")
		public boolean guiGuide = true;
		
		@Config.Comment("Whether snow can settle on roads. It will be correctly offset and sit properly on the roads if enabled.")
		public boolean snowOnRoads = true;
		
		@Config.Comment("Add any fluid names here that you want to function as tar in the road factory.")
		public String[] tarAlternatives = new String[]{"tar"};
		
		@Config.Comment("Print a full list of all fluids in your game on startup, useful for adding tar variants.")
		public boolean printFluidListOnStartup = false;
	}
	
	public static class Machines {
		@Config.Comment("How often the Crusher processes. Default is 1.5 seconds")
		public int crusherTickRate = 30;
		@Config.Comment("How often the Crusher processes. Default is 2 seconds")
		public int compactorTickRate = 40;
		@Config.Comment("How often the Paint Filler processes. Default is 1.25 seconds")
		public int fillerTickRate = 25;
		@Config.Comment("How often the Road Factory processes. Default is 2.5 seconds")
		public int roadFactoryTickRate = 50;
		@Config.Comment("How often the Tar Distiller processes. Default is 1.25 seconds")
		public int tarDistillerTickRate = 25;
		@Config.Comment("How often the Tarmac Cutter processes. Default is 2.5 seconds")
		public int tarmacCutterTickRate = 50;
		@Config.Comment("How often the Paint Oven processes. Default is 2.5 seconds")
		public int paintOvenTickRate = 50;
		@Config.Comment("How often the Fabricator processes. Default is 2 seconds")
		public int fabricatorTickRate = 40;
		
		@Config.Comment("How often the Electric Crusher processes. Default is 1.5 seconds")
		public int electricCrusherTickRate = 30;
		@Config.Comment("How often the Electric Crusher processes. Default is 2 seconds")
		public int electricCompactorTickRate = 40;
		@Config.Comment("How often the Electric Paint Filler processes. Default is 1.25 seconds")
		public int electricFillerTickRate = 25;
		@Config.Comment("How often the Electric Road Factory processes. Default is 2.5 seconds")
		public int electricRoadFactoryTickRate = 50;
		@Config.Comment("How often the Electric Tar Distiller processes. Default is 1.25 seconds")
		public int electricTarDistillerTickRate = 25;
		@Config.Comment("How often the Electric Tarmac Cutter processes. Default is 2.5 seconds")
		public int electricTarmacCutterTickRate = 50;
		@Config.Comment("How often the Electric Paint Oven processes. Default is 2.5 seconds")
		public int electricPaintOvenTickRate = 50;
		@Config.Comment("How often the Electric Fabricator processes. Default is 2 seconds")
		public int electricFabricatorTickRate = 40;
		
		@Config.Comment("How much energy to consume per action.")
		public int electricRoadFactoryEnergyConsumption = 1000;
		@Config.Comment("How much energy to consume per action.")
		public int electricTarDistillerEnergyConsumption = 1000;
		@Config.Comment("How much energy to consume per action.")
		public int electricTarmacCutterEnergyConsumption = 1000;
		@Config.Comment("How much energy to consume per action.")
		public int electricCrusherEnergyConsumption = 1000;
		@Config.Comment("How much energy to consume per action.")
		public int electricCompactorEnergyConsumption = 1000;
		@Config.Comment("How much energy to consume per action.")
		public int electricPaintFillerEnergyConsumption = 1000;
		@Config.Comment("How much energy to consume per action.")
		public int electricPaintOvenEnergyConsumption = 1000;
		@Config.Comment("How much energy to consume per action.")
		public int electricFabricatorEnergyConsumption = 1000;
		
		@Config.Comment("How much energy the machine can hold")
		public int electricRoadFactoryEnergyStorage = 50000;
		@Config.Comment("How much energy the machine can hold")
		public int electricTarDistillerEnergyStorage = 50000;
		@Config.Comment("How much energy the machine can hold")
		public int electricTarmacCutterEnergyStorage = 50000;
		@Config.Comment("How much energy the machine can hold")
		public int electricCrusherEnergyStorage = 50000;
		@Config.Comment("How much energy the machine can hold")
		public int electricCompactorEnergyStorage = 50000;
		@Config.Comment("How much energy the machine can hold")
		public int electricPaintFillerEnergyStorage = 50000;
		@Config.Comment("How much energy the machine can hold")
		public int electricPaintOvenEnergyStorage = 50000;
		@Config.Comment("How much energy the machine can hold")
		public int electricFabricatorEnergyStorage = 50000;
		
		@Config.Comment("How much energy can transfer in per tick.")
		public int electricRoadFactoryEnergyTransferRate = 100;
		@Config.Comment("How much energy can transfer in per tick.")
		public int electricTarDistillerEnergyTransferRate = 100;
		@Config.Comment("How much energy can transfer in per tick.")
		public int electricTarmacCutterEnergyTransferRate = 100;
		@Config.Comment("How much energy can transfer in per tick.")
		public int electricCrusherEnergyTransferRate = 100;
		@Config.Comment("How much energy can transfer in per tick.")
		public int electricCompactorEnergyTransferRate = 100;
		@Config.Comment("How much energy can transfer in per tick.")
		public int electricPaintFillerEnergyTransferRate = 100;
		@Config.Comment("How much energy can transfer in per tick.")
		public int electricPaintOvenEnergyTransferRate = 100;
		@Config.Comment("How much energy can transfer in per tick.")
		public int electricFabricatorEnergyTransferRate = 100;
		
		@Config.Comment("How much tar to move per tick, in mB")
		public int tarTransferRate = 50;
		
		@Config.Comment("How many mB worth of paint one dye gives in the Paint Filler")
		public int fillerPaintPerDye = 250;
		
		@Config.Comment("How many mB worth of paint one dye gives in the Paint Oven")
		public int ovenPaintPerDye = 1000;
	}
}
