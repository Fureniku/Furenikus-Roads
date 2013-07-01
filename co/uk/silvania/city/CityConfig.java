package co.uk.silvania.city;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CityConfig {
	
    private String value;
	
	public static int escalatorID;
	public static int travellatorID;
	public static int atmID;
	
	public static int coin1ID;
	public static int coin2ID;
	public static int coin5ID;
	public static int coin10ID;
	public static int coin25ID;
	public static int coin50ID;
	public static int coin100ID;
	public static int note100ID;
	public static int note200ID;
	public static int note500ID;
	public static int note1000ID;
	public static int note2000ID;
	public static int note5000ID;
	public static int note10000ID;
	public static int prePaidCardID;
	public static int debitCardID;
	
	public static String currencyLarge;
	public static String currencySmall;
	public static String currencyLargePlural;
	public static String currencySmallPlural;
	
	
	public static void loadConfig(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
		config.load();
		
		escalatorID = config.getBlock("Escalator", 1400).getInt();
		travellatorID = config.getBlock("Travellator", 1401).getInt();
		atmID = config.getBlock("ATM", 1402).getInt();
		
		coin1ID = config.getItem("Coin (1)", 18000).getInt();
		coin2ID = config.getItem("Coin (2)", 18001).getInt();
		coin5ID = config.getItem("Coin (5)", 18002).getInt();
		coin10ID = config.getItem("Coin (10)", 18003).getInt();
		coin25ID = config.getItem("Coin (25)", 18004).getInt();
		coin50ID = config.getItem("Coin (50)", 18005).getInt();
		coin100ID = config.getItem("Coin (100)", 18006).getInt();
		note100ID = config.getItem("Note (100)", 18007).getInt();
		note200ID = config.getItem("Note (200)", 18008).getInt();
		note500ID = config.getItem("Note (500)", 18009).getInt();
		note1000ID = config.getItem("Note (1,000)", 18010).getInt();
		note2000ID = config.getItem("Note (2,000)", 18011).getInt();
		note5000ID = config.getItem("Note (5,000)", 18012).getInt();
		note10000ID = config.getItem("Note (10,000)", 18013).getInt();
		prePaidCardID = config.getItem("Pre-Paid Card", 18014).getInt();
		debitCardID = config.getItem("Debit Card", 18015).getInt();
		
		currencySmall = config.get("Currency Small", Configuration.CATEGORY_GENERAL, "Cent").getString();
		currencyLarge = config.get("Currency Large", Configuration.CATEGORY_GENERAL, "Dollar").getString();
		currencySmallPlural = config.get("Currency Small (Plural)", Configuration.CATEGORY_GENERAL, "Cents").getString();
		currencyLargePlural = config.get("Currency Large (Plural)", Configuration.CATEGORY_GENERAL, "Dollars").getString();
		
		
		config.save();
	}

}
