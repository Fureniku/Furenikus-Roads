package co.uk.silvania.city;

import co.uk.silvania.city.items.*;
import co.uk.silvania.city.tileentities.*;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid="RoadsCity", name="RoadsCity", version="0.1.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class RoadsCity { 
	
    @Instance("RoadsCity")
    public static RoadsCity instance;
    public static GuiHandler roadsGuiHandler = new GuiHandler();

    @SidedProxy(clientSide="co.uk.silvania.city.client.ClientProxy", serverSide="co.uk.silvania.city.CommonProxy")
    public static CommonProxy proxy;
    
	public static CreativeTabs tabCity = new CreativeTabs("tabCity") {
		public ItemStack getIconItemStack() {
			return new ItemStack(Block.cake, 1, 0);
		}
	};
	
	public static CreativeTabs tabEcon = new CreativeTabs("tabEcon") {
		public ItemStack getIconItemStack() {
			return new ItemStack(RoadsCity.coin100, 1, 0);
		}
	};
			
	public static Block escalator;
	public static Block atmBlock;
	public static Block travellator;
	
	public static Item coin1;
	public static Item coin2;
	public static Item coin5;
	public static Item coin10;
	public static Item coin25;
	public static Item coin50;
	public static Item coin100;
	public static Item note100;
	public static Item note200;
	public static Item note500;
	public static Item note1000;
	public static Item note2000;
	public static Item note5000;
	public static Item note10000;
	public static Item prePaidCard;
	public static Item debitCard;

	//And finally the worldgen
	//public static WorldGen worldGen = new WorldGen();

    
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) {
    	CityConfig config = new CityConfig();
    	NetworkRegistry.instance().registerGuiHandler(this, roadsGuiHandler);
    	
    	CityConfig.loadConfig(event); 

    	escalator = new TileEntityEscalatorBlock(config.escalatorID).setUnlocalizedName("escalator");
    	travellator = new TileEntityTravellatorBlock(config.travellatorID).setUnlocalizedName("travellator");
    	atmBlock = new TileEntityATMBlock(config.atmID).setUnlocalizedName("atmBlock");
    	
    	coin1 = new ItemCoin1(config.coin1ID).setUnlocalizedName("coin1");
    	coin2 = new ItemCoin2(config.coin2ID).setUnlocalizedName("coin2");
    	coin5 = new ItemCoin5(config.coin5ID).setUnlocalizedName("coin5");
    	coin10 = new ItemCoin10(config.coin10ID).setUnlocalizedName("coin10");
    	coin25 = new ItemCoin25(config.coin25ID).setUnlocalizedName("coin25");
    	coin50 = new ItemCoin50(config.coin50ID).setUnlocalizedName("coin50");
    	coin100 = new ItemCoin100(config.coin100ID).setUnlocalizedName("coin100");
    	note100 = new ItemNote1(config.note100ID).setUnlocalizedName("note100");
    	note200 = new ItemNote2(config.note200ID).setUnlocalizedName("note500");
    	note500 = new ItemNote5(config.note500ID).setUnlocalizedName("note1000");
    	note1000 = new ItemNote10(config.note1000ID).setUnlocalizedName("note2000");
    	note2000 = new ItemNote20(config.note2000ID).setUnlocalizedName("note5000");
    	note5000 = new ItemNote50(config.note5000ID).setUnlocalizedName("note10000");
    	note10000 = new ItemNote100(config.note10000ID).setUnlocalizedName("note10000");
    	prePaidCard = new PrePaidCard(config.prePaidCardID).setUnlocalizedName("prePaidCard");
    	debitCard = new DebitCard(config.debitCardID).setUnlocalizedName("debitCard");
        }
               
    @Init
    public void load(FMLInitializationEvent event) {
            proxy.registerRenderThings();
            
            proxy.registerBlocks();
            proxy.addNames();
            
            GameRegistry.registerTileEntity(TileEntityEscalatorEntity.class, "tileEntityEscalator");
            GameRegistry.registerTileEntity(TileEntityATMEntity.class, "tileEntityATM");
            GameRegistry.registerTileEntity(TileEntityTravellatorEntity.class, "tileEntityTravellator");
            
            //LiquidContainerRegistry.registerLiquid(new LiquidContainerData(new LiquidStack(RoadsCity.roadsTarStill, LiquidContainerRegistry.BUCKET_VOLUME), new ItemStack(RoadsCity.tarBucketItem), new ItemStack(Item.bucketEmpty)));
            LanguageRegistry.instance().addStringLocalization("itemGroup.tabCity", "en_US", "Cities: Blocks");
            LanguageRegistry.instance().addStringLocalization("itemGroup.tabEcon", "en_US", "Cities: Economy");
        }


		@PostInit
        public void postInit(FMLPostInitializationEvent event) {
                // Stub Method
        		}
		};