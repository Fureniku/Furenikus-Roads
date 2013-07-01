package co.uk.silvania.city;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CityConfig {
	
    private String value;
	
	public static int escalatorID;
	public static int atmID;
	
	public static int coin1ID;
	public static int coin2ID;
	public static int coin5ID;
	public static int coin10ID;
	public static int coin25ID;
	public static int coin50ID;
	public static int coin100ID;
	public static int note100ID;
	public static int note500ID;
	public static int note1000ID;
	public static int note2000ID;
	public static int note5000ID;
	public static int note10000ID;
	public static int prePaidCardID;
	public static int debitCardID;
	
	public static String currencyLarge;
	public static String currencySmall;
	
	
	public static void loadConfig(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
		config.load();
		
		escalatorID = config.getBlock("Escalator", 1400).getInt();
		atmID = config.getBlock("ATM", 1401).getInt();
		
		coin1ID = config.getItem("Coin (1)", 18000).getInt();
		coin2ID = config.getItem("Coin (2)", 18001).getInt();
		coin5ID = config.getItem("Coin (5)", 18002).getInt();
		coin10ID = config.getItem("Coin (10)", 18003).getInt();
		coin25ID = config.getItem("Coin (25)", 18004).getInt();
		coin50ID = config.getItem("Coin (50)", 18005).getInt();
		coin100ID = config.getItem("Coin (100)", 18006).getInt();
		note100ID = config.getItem("Note (100)", 18007).getInt();
		note500ID = config.getItem("Note (500)", 18008).getInt();
		note1000ID = config.getItem("Note (1,000)", 18009).getInt();
		note2000ID = config.getItem("Note (2,000)", 18010).getInt();
		note5000ID = config.getItem("Note (5,000)", 18011).getInt();
		note10000ID = config.getItem("Note (10,000)", 18012).getInt();
		prePaidCardID = config.getItem("Pre-Paid Card", 18013).getInt();
		debitCardID = config.getItem("Debit Card", 18014).getInt();
		
		//String currencySmall = config.get("Currency Small", Configuration.CATEGORY_GENERAL, "Cents");
		
		
		config.save();
	}

}
