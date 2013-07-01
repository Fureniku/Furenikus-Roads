package co.uk.silvania.city;

import co.uk.silvania.roads.Roads;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class CommonProxy {
	
    public void registerRenderThings() {
    }
    
    public void registerBlocks() {
        GameRegistry.registerBlock(RoadsCity.escalator, "escalator");
        GameRegistry.registerBlock(RoadsCity.atmBlock, "atmBlock");
        GameRegistry.registerBlock(RoadsCity.travellator, "travellator");
        
        GameRegistry.registerItem(RoadsCity.coin1,"coin1");
        GameRegistry.registerItem(RoadsCity.coin2,"coin2");
        GameRegistry.registerItem(RoadsCity.coin5,"coin5");
        GameRegistry.registerItem(RoadsCity.coin10,"coin10");
        GameRegistry.registerItem(RoadsCity.coin25,"coin25");
        GameRegistry.registerItem(RoadsCity.coin50,"coin50");
        GameRegistry.registerItem(RoadsCity.coin100,"coin100");
        GameRegistry.registerItem(RoadsCity.note100,"note100");
        GameRegistry.registerItem(RoadsCity.note500,"note500");
        GameRegistry.registerItem(RoadsCity.note1000,"note1000");
        GameRegistry.registerItem(RoadsCity.note2000,"note2000");
        GameRegistry.registerItem(RoadsCity.note5000,"note5000");
        GameRegistry.registerItem(RoadsCity.note10000,"note10000");
        GameRegistry.registerItem(RoadsCity.prePaidCard,"prePaidCard");
        GameRegistry.registerItem(RoadsCity.debitCard,"debitCard");
    }
    
    public void addNames() {
        LanguageRegistry.addName(RoadsCity.escalator, "Escalator");
        LanguageRegistry.addName(RoadsCity.travellator, "Travellator");
        LanguageRegistry.addName(RoadsCity.atmBlock, "ATM");
        
        LanguageRegistry.addName(RoadsCity.coin1, "1 " + CityConfig.currencySmall);
        LanguageRegistry.addName(RoadsCity.coin2, "2 " + CityConfig.currencySmallPlural);
        LanguageRegistry.addName(RoadsCity.coin5, "5 " + CityConfig.currencySmallPlural);
        LanguageRegistry.addName(RoadsCity.coin10, "10 " + CityConfig.currencySmallPlural);
        LanguageRegistry.addName(RoadsCity.coin25, "25 " + CityConfig.currencySmallPlural);
        LanguageRegistry.addName(RoadsCity.coin50, "50 " + CityConfig.currencySmallPlural);
        LanguageRegistry.addName(RoadsCity.coin100, "1 " + CityConfig.currencyLarge + " Coin");
        LanguageRegistry.addName(RoadsCity.note100, "1 " + CityConfig.currencyLarge);
        LanguageRegistry.addName(RoadsCity.note500, "5 " + CityConfig.currencyLargePlural);
        LanguageRegistry.addName(RoadsCity.note1000, "10 " + CityConfig.currencyLargePlural);
        LanguageRegistry.addName(RoadsCity.note2000, "20 " + CityConfig.currencyLargePlural);
        LanguageRegistry.addName(RoadsCity.note5000, "50 " + CityConfig.currencyLargePlural);
        LanguageRegistry.addName(RoadsCity.note10000, "100 " + CityConfig.currencyLargePlural);
        LanguageRegistry.addName(RoadsCity.prePaidCard, "Pre-Paid Card");
        LanguageRegistry.addName(RoadsCity.debitCard, "Debit Card");
        
    	
    }
    
    public void addRecipes() {
    	
    }

}
