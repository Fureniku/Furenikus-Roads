package co.uk.silvania.roads;

import co.uk.silvania.roads.block.*;
import co.uk.silvania.roads.item.*;
import co.uk.silvania.roads.liquid.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
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
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid="FlenixRoads", name="FlenixRoads", version="0.0.2")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class Roads { 
	
    @Instance("FlenixRoads")
    public static Roads instance;

    @SidedProxy(clientSide="co.uk.silvania.roads.client.ClientProxy", serverSide="co.uk.silvania.roads.CommonProxy")
    public static CommonProxy proxy;
    
	public static CreativeTabs tabRoads = new CreativeTabs("tabRoads") {
		public ItemStack getIconItemStack() {
			return new ItemStack(Roads.roadBlockDYS, 1, 0);
		}
	};
			
	public static Block roadBlock;
	public static Block macadamBlock;
	public static Block cementBlock;
	public static Block limeStoneBlock;
	public static Block hardenedTarBlock;
	public static Block sidewalkBlocks;
	public static Block roadsTarStill;
	public static Block roadsTarFlowing;
	
	public static Block roadBlockSWS2;
	public static Block roadBlockDYS;
	public static Block roadBlockDYSEL;
	public static Block roadBlockDYSER;
	public static Block roadBlockDYSI;
	public static Block roadBlockDYSO;
	public static Block roadBlockSYS;
	public static Block roadBlockSWS;
	public static Block roadBlockSWC;
	public static Block roadBlockSYC;
	public static Block roadBlockDYC;
	public static Block roadBlockWhiteCross;
	public static Block roadBlockYellowCross;
	public static Block roadBlockWhiteFull;
	public static Block roadBlockYellowFull;
	public static Block roadBlockWC;
	public static Block roadBlockFYS;
	public static Block roadBlockYC;
	public static Block roadBlockYAC;
	public static Block roadBlockWAC;
	public static Block roadBlockWSL;
	public static Block roadBlockWSSL;
	public static Block roadBlockYSSL;
	public static Block roadBlockJO;
	public static Block roadBlockJI;
	public static Block roadBlockJOL;
	public static Block roadBlockJIL;
	public static Block roadBlockJOIL;
	public static Block roadBlockJIIL;
	public static Block roadBlockAL;
	public static Block roadBlockAUS;
	public static Block roadBlockAUL;
	public static Block roadBlockAUR;
	public static Block roadBlockWDS;
	public static Block roadBlockYDS;
	public static Block roadBlockWSS;
	public static Block roadBlockYSS;
	public static Block roadBlockSL;
	public static Block roadBlockOW;
	public static Block roadBlockST;
	public static Block roadBlockOP;
		
	//Items Start Here
	public static Item cementItem;
	public static Item cementDustItem;
	public static Item limeStonePowderItem;
	public static Item limeClayPowderItem;
	public static Item tarBucketItem;

	//And finally the worldgen
	public static WorldGen worldGen = new WorldGen();

    
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) {
    	RoadsConfig config = new RoadsConfig();
    	
    	RoadsConfig.loadConfig(event);

    	roadBlock = new RoadBlock(config.roadBlockID);
    	macadamBlock = new MacadamBlock(config.macadamBlockID);
    	cementBlock = new CementBlock(config.cementBlockID);
    	limeStoneBlock = new LimeStoneBlock(config.limeStoneBlockID);
    	hardenedTarBlock = new HardenedTarBlock(config.hardenedTarBlockID);
    	sidewalkBlocks = new SideWalkBlocks(config.sidewalkBlocksID);
    	roadsTarStill = new StillTarBlock(config.roadsTarStillID);
    	roadsTarFlowing = new FlowingTarBlock(config.roadsTarFlowingID);
    	
    	roadBlockSWS2 = new RBSideWhiteStripe(config.roadBlockSWS2ID);
    	roadBlockDYS = new RBDoubleYellowStripe(config.roadBlockDYSID);
    	roadBlockDYSEL = new RBDoubleYellowStripeEndLeft(config.roadBlockDYSELID);
    	roadBlockDYSER = new RBDoubleYellowStripeEndRight(config.roadBlockDYSERID);
    	roadBlockDYSI = new RBDoubleYellowStripeInnerCorner(config.roadBlockDYSIID);
    	roadBlockDYSO = new RBDoubleYellowStripeOuterCorner(config.roadBlockDYSOID);
    	roadBlockSYS = new RBSingleYellowStripe(config.roadBlockSYSID);
    	roadBlockSWS = new RBSingleWhiteSide(config.roadBlockSWSID);
    	roadBlockSWC = new RBSingleWhiteCenter(config.roadBlockSWCID);
    	roadBlockSYC = new RBSingleYellowCenter(config.roadBlockSYCID);
    	roadBlockDYC = new RBDoubleYellowCenter(config.roadBlockDYCID);
    	roadBlockWhiteCross = new RBWhiteCross(config.roadBlockWhiteCrossID);
    	roadBlockYellowCross = new RBYellowCross(config.roadBlockYellowCrossID);
    	roadBlockWhiteFull = new RBWhiteFull(config.roadBlockWhiteFullID);
    	roadBlockYellowFull = new RBYellowFull(config.roadBlockYellowFullID);
    	roadBlockWC = new RBWhiteCorner(config.roadBlockWCID);
    	roadBlockFYS = new RBFarYellowStripe(config.roadBlockFYSID);
    	roadBlockYC = new RBYellowCorner(config.roadBlockYCID);
    	roadBlockYAC = new RBYellowAltCorner(config.roadBlockYACID);
    	roadBlockWAC = new RBWhiteAltCorner(config.roadBlockWACID);
    	roadBlockWSL = new RBWhiteStripedLine(config.roadBlockWSLID);
    	roadBlockWSSL = new RBWhiteSideStripedLine(config.roadBlockWSSLID);
    	roadBlockYSSL = new RBYellowSideStripedLine(config.roadBlockYSSLID);
    	roadBlockJO = new RBJunctOut(config.roadBlockJOID);
    	roadBlockJI = new RBJunctIn(config.roadBlockJIID);
    	roadBlockJOL = new RBJunctOutLine(config.roadBlockJOLID);
    	roadBlockJIL = new RBJunctInLine(config.roadBlockJILID);
    	roadBlockJOIL = new RBJunctOutInvertedLine(config.roadBlockJOILID);
    	roadBlockJIIL = new RBJunctInInvertedLine(config.roadBlockJIILID);
    	roadBlockAL = new RBArrowLine(config.roadBlockALID);
    	roadBlockAUS = new RBArrowStraight(config.roadBlockAUSID);
    	roadBlockAUL = new RBArrowLeft(config.roadBlockAULID);
    	roadBlockAUR = new RBArrowRight(config.roadBlockAURID);
    	roadBlockWDS = new RBWhiteDiagonalStripe(config.roadBlockWDSID);
    	roadBlockYDS = new RBYellowDiagonalStripe(config.roadBlockYDSID);
    	roadBlockWSS = new RBWhiteSmallSquare(config.roadBlockWSSID);
    	roadBlockYSS = new RBYellowSmallSquare(config.roadBlockYSSID);
    	roadBlockSL = new RBSL(config.roadBlockSLID);
    	roadBlockOW = new RBOW(config.roadBlockOWID);
    	roadBlockST = new RBST(config.roadBlockSTID);
    	roadBlockOP = new RBOP(config.roadBlockOPID);
    	
    	cementItem = new CementItem(config.cementItemID);
    	cementDustItem = new CementDustItem(config.cementDustID);
    	limeStonePowderItem = new LimeStonePowderItem(config.limeStonePowderID);
    	limeClayPowderItem = new LimeClayPowderItem(config.limeClayPowderID);
    	tarBucketItem = new TarBucketItem(config.tarBucketID);
    	
        }
               
    @Init
    public void load(FMLInitializationEvent event) {
            proxy.registerRenderThings();
            
            proxy.registerBlocks();
            proxy.addNames();
            
            LiquidContainerRegistry.registerLiquid(new LiquidContainerData(new LiquidStack(Roads.roadsTarStill, LiquidContainerRegistry.BUCKET_VOLUME), new ItemStack(Roads.tarBucketItem), new ItemStack(Item.bucketEmpty)));
            LanguageRegistry.instance().addStringLocalization("itemGroup.tabRoads", "en_US", "Roads");

            //Setup the world generator
            GameRegistry.registerWorldGenerator(new WorldGen());

            //Craftin' Time!
            //First, register the blocks and items we'll use.
            ItemStack clayStack = new ItemStack(Item.clay);
            ItemStack sandStack = new ItemStack(Block.sand);
            ItemStack waterBucketStack = new ItemStack(Item.bucketWater);
            ItemStack cementDustStack = new ItemStack(Roads.cementDustItem);
            ItemStack cobbleStoneStack = new ItemStack(Block.cobblestone);
            ItemStack cementItemStack = new ItemStack(Roads.cementItem);
            ItemStack tarBucketStack = new ItemStack(Roads.tarBucketItem);
            ItemStack macadamStack = new ItemStack(Roads.macadamBlock);
            ItemStack limeStack = new ItemStack (Roads.limeStonePowderItem);

            //Shaped Recipes
            GameRegistry.addRecipe(new ItemStack(Roads.cementItem, 4), " c ", " w ", "sss", 'w', waterBucketStack, 's', sandStack, 'c', cementDustStack);
            GameRegistry.addRecipe(new ItemStack(Roads.macadamBlock, 8), "xxx", "xyx", "xxx", 'x', cobbleStoneStack, 'y', cementItemStack);
            GameRegistry.addRecipe(new ItemStack(Roads.limeStoneBlock), "ll", "ll", 'l', limeStack);

            //Shapeless Recipes
            GameRegistry.addShapelessRecipe(new ItemStack(Roads.roadBlock), macadamStack, tarBucketStack);
            GameRegistry.addShapelessRecipe(new ItemStack(Roads.limeClayPowderItem), limeStack, clayStack);

            //And some smelting!
            GameRegistry.addSmelting(Roads.limeClayPowderItem.itemID, new ItemStack(Roads.cementDustItem), 0.1f);
            GameRegistry.addSmelting(Roads.cementItem.itemID, new ItemStack(Roads.cementBlock), 0.2f);
        }


		@PostInit
        public void postInit(FMLPostInitializationEvent event) {
                // Stub Method
        		}
		};