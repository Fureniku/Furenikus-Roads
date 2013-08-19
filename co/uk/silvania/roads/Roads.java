package co.uk.silvania.roads;

import co.uk.silvania.roads.block.*;
import co.uk.silvania.roads.block.tess.RoadRamp5;
import co.uk.silvania.roads.item.*;
import co.uk.silvania.roads.liquid.*;
import co.uk.silvania.roads.roadblocks.*;
import co.uk.silvania.roads.tileentities.*;
import co.uk.silvania.roads.tileentities.blocks.*;
import co.uk.silvania.roads.tileentities.entities.*;
import co.uk.silvania.roads.tileentities.itemrenderers.TrafficLightItemRenderer;
import net.minecraft.block.material.Material;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSign;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
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

@Mod(modid="FlenixRoads", name="FlenixRoads", version="0.5.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class Roads { 
	
    @Instance("FlenixRoads")
    public static Roads instance;
    public static GuiHandler roadsGuiHandler = new GuiHandler();

    @SidedProxy(clientSide="co.uk.silvania.roads.client.ClientProxy", serverSide="co.uk.silvania.roads.CommonProxy")
    public static CommonProxy proxy;
    
	public static CreativeTabs tabRoads = new CreativeTabs("tabRoads") {
		public ItemStack getIconItemStack() {
			return new ItemStack(Roads.roadBlockDoubleYellow, 1, 0);
		}
	};

	public static Block limeStoneBlock;
	public static Block roadsTarStill;
	public static Block roadsTarFlowing;
	public static Block catsEye;
	public static Block catsEyeSide;
	public static Block generalBlocks;

	public static Block roadBlockArrows;
	public static Block roadBlockCorners;
	public static Block roadBlockDoubleYellow;
	public static Block roadBlockCornerB;
	public static Block roadBlockJunctionIn;
	public static Block roadBlockJunctionOut;
	public static Block roadBlockMiscSingles;
	public static Block roadBlockSimpleLines;
	public static Block roadBlockSideWhiteStripes;
	public static Block roadBlockStripes;
	public static Block roadBlockDirt;
	public static Block roadBlockDirtCorner;
	public static Block roadBlockFork;
	public static Block roadBlockForkB;
	public static Block roadBlockForkC;
	public static Block roadBlockForkD;
	public static Block roadBlockForkE;
	public static Block roadBlockForkF;
	public static Block roadBlockForkG;
	
	public static Block roadRamp1;
	public static Block roadRamp2;
	public static Block roadRamp3;
	public static Block roadRamp4;
	public static Block roadRamp5;
	public static Block roadRamp6;
	
	public static Block sidewalkBlockGrey;
	public static Block sidewalkBlockLight;
	public static Block sidewalkBlockTile;
	public static Block sidewalkBlockTri;
	public static Block sidewalkBlockSides;
	public static Block kerbBlock;
	
	public static Block powerPole;
	public static Block powerPoleSmall;
	public static Block powerPoleLarge;
	public static Block powerPoleOn;
	public static Block powerPoleSmallOn;
	public static Block powerPoleLargeOn;
		
	public static Block blockGag1;
	public static Block blockGag2;
	public static Block blockGag3;
	public static Block blockGag4;
	public static Block blockGag5;
	
	public static Block roadPainter;
	public static Block trafficLight;
	public static Block lightBollard;
	public static Block streetLamp1;
	public static Block streetLamp2;
	public static Block streetSign;
	public static Block barrierPole;
	public static Block barrierBlock;
	public static Block barrierCorner;
	//public static Block roadSign;
	public static Block roadBarrier;
	public static Block roadBarrierUp;
	
	//Items Start Here
	public static Item cementItem;
	public static Item cementDustItem;
	public static Item limeStonePowderItem;
	public static Item limeClayPowderItem;
	public static Item tarBucketItem;
	public static Item whitePaintBlob;
	public static Item yellowPaintBlob;
	public static Item whitePaintCan;
	public static Item yellowPaintCan;
	public static Item blankSign;

	//And finally the worldgen
	public static WorldGen worldGen = new WorldGen();

    
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	RoadsConfig config = new RoadsConfig();
    	NetworkRegistry.instance().registerGuiHandler(this, roadsGuiHandler);
    	
    	RoadsConfig.loadConfig(event); 
    	
    	//if (PlayerHandler.getBannedPlayers().contains(player.username)) player.kick();

    	limeStoneBlock = new LimeStoneBlock(config.limeStoneBlockID).setUnlocalizedName("limeStoneBlock");
    	catsEye = new CatsEye(config.catsEyeID).setUnlocalizedName("catsEye");
    	catsEyeSide = new CatsEyeSide(config.catsEyeSideID).setUnlocalizedName("catsEyeSide");
    	roadsTarStill = new StillTarBlock(config.roadsTarStillID).setUnlocalizedName("roadsTarStill");
    	roadsTarFlowing = new FlowingTarBlock(config.roadsTarFlowingID).setUnlocalizedName("roadsTarFlowing");
    	generalBlocks = new GeneralBlocks(config.generalBlocksID).setUnlocalizedName("generalBlocks");

    	roadBlockArrows = new RoadBlockArrows(config.roadBlockArrowsID).setUnlocalizedName("roadBlockArrows");
    	roadBlockDoubleYellow = new RoadBlockDoubleYellow(config.roadBlockDoubleYellowID).setUnlocalizedName("roadBlockDoubleYellow");
    	roadBlockCorners = new RoadBlockCorners(config.roadBlockCornersID).setUnlocalizedName("roadBlockCorners");
    	roadBlockCornerB = new RoadBlockCornerB(config.roadBlockCornerBID).setUnlocalizedName("roadBlockCornerB");
    	roadBlockJunctionIn = new RoadBlockJunctionIn(config.roadBlockJunctionInID).setUnlocalizedName("roadBlockJunctionIn");
    	roadBlockJunctionOut = new RoadBlockJunctionOut(config.roadBlockJunctionOutID).setUnlocalizedName("roadBlockJunctionOut");
    	roadBlockMiscSingles = new RoadBlockMiscSingles(config.roadBlockMiscSinglesID).setUnlocalizedName("roadBlockMiscSingles");
    	roadBlockSimpleLines = new RoadBlockSimpleLines(config.roadBlockSimpleLinesID).setUnlocalizedName("roadBlockSimpleLines");
    	roadBlockSideWhiteStripes = new RoadBlockSideWhiteStripes(config.roadBlockSideWhiteStripesID).setUnlocalizedName("roadBlockSideWhiteStripes");
    	roadBlockStripes = new RoadBlockStripes(config.roadBlockStripesID).setUnlocalizedName("roadBlockStripes");
    	roadBlockDirt = new RoadBlockDirt(config.roadBlockDirtID).setUnlocalizedName("roadBlockDirt");
    	roadBlockDirtCorner = new RoadBlockDirtCorner(config.roadBlockDirtCornerID).setUnlocalizedName("roadBlockDirtCorner");
    	roadBlockFork = new RoadBlockFork(config.roadBlockForkID).setUnlocalizedName("roadBlockFork");
    	roadBlockForkB = new RoadBlockForkB(config.roadBlockForkBID).setUnlocalizedName("roadBlockForkB");
    	roadBlockForkC = new RoadBlockForkC(config.roadBlockForkCID).setUnlocalizedName("roadBlockForkC");
    	roadBlockForkD = new RoadBlockForkD(config.roadBlockForkDID).setUnlocalizedName("roadBlockForkD");
    	roadBlockForkE = new RoadBlockForkE(config.roadBlockForkEID).setUnlocalizedName("roadBlockForkE");
    	roadBlockForkF = new RoadBlockForkE(config.roadBlockForkFID).setUnlocalizedName("roadBlockForkF");
    	roadBlockForkG = new RoadBlockForkE(config.roadBlockForkGID).setUnlocalizedName("roadBlockForkG");
    	
    	sidewalkBlockGrey = new SidewalkBlockGrey(config.sidewalkBlockGreyID).setUnlocalizedName("sidewalkBlockGrey");
    	sidewalkBlockLight = new SidewalkBlockLight(config.sidewalkBlockLightID).setUnlocalizedName("sidewalkBlockLight");
    	sidewalkBlockTile = new SidewalkBlockTile(config.sidewalkBlockTileID).setUnlocalizedName("sidewalkBlockTile");
    	sidewalkBlockTri = new SidewalkBlockTri(config.sidewalkBlockTriID).setUnlocalizedName("sidewalkBlockTri");
    	sidewalkBlockSides = new SidewalkBlockSides(config.sidewalkBlockSidesID).setUnlocalizedName("sidewalkBlockSides");
    	kerbBlock = new KerbBlock(config.kerbBlockID).setUnlocalizedName("kerbBlock");
    	
    	roadRamp1 = new TileEntityRamp1(config.roadRamp1ID).setUnlocalizedName("roadRamp1");
    	roadRamp2 = new TileEntityRamp2(config.roadRamp2ID).setUnlocalizedName("roadRamp2");
    	roadRamp3 = new TileEntityRamp3(config.roadRamp3ID).setUnlocalizedName("roadRamp3");
    	roadRamp4 = new TileEntityRamp4(config.roadRamp4ID).setUnlocalizedName("roadRamp4");
    	roadRamp5 = new TileEntityRamp5(config.roadRamp5ID).setUnlocalizedName("roadRamp5");
    	roadRamp6 = new TileEntityRamp6(config.roadRamp6ID).setUnlocalizedName("roadRamp6");
    	
    	
    	powerPole = new PowerPoleMedium(config.powerPoleID, false).setUnlocalizedName("powerPole");
    	powerPoleSmall = new PowerPoleSmall(config.powerPoleSmallID).setUnlocalizedName("powerPoleSmall");
    	powerPoleLarge = new PowerPoleLarge(config.powerPoleLargeID, false).setUnlocalizedName("powerPoleLarge");
    	
    	powerPoleOn = new PowerPoleMedium(config.powerPoleOnID, true).setUnlocalizedName("powerPoleOn");
    	powerPoleSmallOn = new PowerPoleSmall(config.powerPoleSmallOnID).setUnlocalizedName("powerPoleSmallOn");
    	powerPoleLargeOn = new PowerPoleLarge(config.powerPoleLargeOnID, true).setUnlocalizedName("powerPoleLargeOn");
    	
    	blockGag1 = new BlockGag1(config.blockGag1ID).setUnlocalizedName("blockGag1");
    	blockGag2 = new BlockGag2(config.blockGag2ID).setUnlocalizedName("blockGag2");
    	blockGag3 = new BlockGag3(config.blockGag3ID).setUnlocalizedName("blockGag3");
    	blockGag4 = new BlockGag4(config.blockGag4ID).setUnlocalizedName("blockGag4");
    	blockGag5 = new BlockGag5(config.blockGag5ID).setUnlocalizedName("blockGag5");
    	
    	
    	roadPainter = new TileEntityRoadPainterBlock(config.roadPainterID).setUnlocalizedName("roadPainter");
    	trafficLight = new TileEntityTrafficLightBlock(config.trafficLightID).setUnlocalizedName("trafficLight");
    	lightBollard = new TileEntityLightBollardBlock(config.lightBollardID).setUnlocalizedName("lightBollard");
    	streetLamp1 = new TileEntityStreetLamp1Block(config.streetLamp1ID, false).setUnlocalizedName("streetLamp1");
    	streetLamp2 = new TileEntityStreetLamp2Block(config.streetLamp2ID, false).setUnlocalizedName("streetLamp2");
    	streetSign = new TileEntityStreetSignBlock(config.streetSignID).setUnlocalizedName("streetSign");
    	barrierPole = new TileEntityBarrierPoleBlock(config.barrierPoleID).setUnlocalizedName("barrierPole");
    	barrierBlock = new TileEntityBarrierBlock(config.barrierBlockID).setUnlocalizedName("barrierBlock");
    	barrierCorner = new TileEntityBarrierCornerBlock(config.barrierCornerID).setUnlocalizedName("barrierCorner");
    	//roadSign = new TileEntityRoadSignBlock(config.roadSignID, TileEntityRoadSignEntity.class, true).setUnlocalizedName("roadSign");
    	roadBarrier = new TileEntityRoadBarrierBlock(config.roadBarrierID, false).setUnlocalizedName("roadBarrier");
    	roadBarrierUp = new TileEntityRoadBarrierBlock(config.roadBarrierUpID, true).setUnlocalizedName("roadBarrierUp");

    	
    	cementItem = new CementItem(config.cementItemID).setUnlocalizedName("cementItem");
    	cementDustItem = new CementDustItem(config.cementDustID).setUnlocalizedName("cementDustItem");
    	limeStonePowderItem = new LimeStonePowderItem(config.limeStonePowderID).setUnlocalizedName("limeStonePowderItem");
    	limeClayPowderItem = new LimeClayPowderItem(config.limeClayPowderID).setUnlocalizedName("limeClayPowderItem");
    	tarBucketItem = new TarBucketItem(config.tarBucketID).setUnlocalizedName("tarBucketItem");
    	whitePaintBlob = new WhitePaintBlob(config.whitePaintBlobID).setUnlocalizedName("whitePaintBlob");
    	yellowPaintBlob = new YellowPaintBlob(config.yellowPaintBlobID).setUnlocalizedName("yellowPaintBlob");
    	whitePaintCan = new WhitePaintCan(config.whitePaintCanID).setUnlocalizedName("whitePaintCan");
    	yellowPaintCan = new YellowPaintCan(config.yellowPaintCanID).setUnlocalizedName("yellowPaintCan");
    	blankSign = new BlankSign(config.blankSignID).setUnlocalizedName("blankSign");
    	
    	//MinecraftForgeClient.registerItemRenderer(trafficLight.blockID, new TrafficLightItemRenderer());
    	MinecraftForge.EVENT_BUS.register(new TarBucketHandler());
        }
    
    public static Block roadRampy5 = new RoadRamp5(249).setUnlocalizedName("roadRampy5");
               
    @EventHandler
    public void load(FMLInitializationEvent event) {
            proxy.registerRenderThings();
            proxy.registerRenderers();
            
            proxy.registerBlocks();
            proxy.addNames();
            
            GameRegistry.registerTileEntity(TileEntityTrafficLightEntity.class, "tileEntityTrafficLight");
            GameRegistry.registerTileEntity(TileEntityLightBollardEntity.class, "tileEntityLightBollard");
            GameRegistry.registerTileEntity(TileEntityRoadPainterEntity.class, "tileEntityRoadPainter");
            GameRegistry.registerTileEntity(TileEntityStreetLamp1Entity.class, "tileEntityStreetLamp1");
            GameRegistry.registerTileEntity(TileEntityStreetLamp2Entity.class, "tileEntityStreetLamp2");
            GameRegistry.registerTileEntity(TileEntityStreetSignEntity.class, "tileEntityStreetSign");
            GameRegistry.registerTileEntity(TileEntityBarrierPoleEntity.class, "tileEntityBarrierPole");
            GameRegistry.registerTileEntity(TileEntityBarrierEntity.class, "tileEntityBarrier");
            GameRegistry.registerTileEntity(TileEntityBarrierCornerEntity.class, "tileEntityBarrierCorner");
            GameRegistry.registerTileEntity(TileEntityRoadSignEntity.class, "tileEntityRoadSign");
            GameRegistry.registerTileEntity(TileEntityRoadSlope1Entity.class, "tileEntityRoadSlope1");
            GameRegistry.registerTileEntity(TileEntityRoadSlope2Entity.class, "tileEntityRoadSlope2");
            GameRegistry.registerTileEntity(TileEntityRoadSlope3Entity.class, "tileEntityRoadSlope3");
            GameRegistry.registerTileEntity(TileEntityRoadSlope4Entity.class, "tileEntityRoadSlope4");
            GameRegistry.registerTileEntity(TileEntityRoadSlope5Entity.class, "tileEntityRoadSlope5");
            GameRegistry.registerTileEntity(TileEntityRoadSlope6Entity.class, "tileEntityRoadSlope6");
            GameRegistry.registerTileEntity(TileEntityRoadBarrierEntity.class, "tileEntityRoadBarrier");
            GameRegistry.registerTileEntity(TileEntityRoadBarrierUpEntity.class, "tileEntityRoadBarrierUp");
            
            //FluidRegistry.registerLiquid(new FluidStack(Roads.roadsTarStill, FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(Roads.tarBucketItem), new ItemStack(Item.bucketEmpty));
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
            ItemStack macadamStack = new ItemStack(Roads.generalBlocks, 1, 2);
            ItemStack limeStack = new ItemStack(Roads.limeStonePowderItem);
            ItemStack yellowDye = new ItemStack(Item.dyePowder, 1, 11);
            ItemStack whiteDye = new ItemStack(Item.dyePowder, 1, 15);
            ItemStack bucket = new ItemStack(Item.bucketEmpty);
            ItemStack workBench = new ItemStack(Block.workbench);
            ItemStack ironIngot = new ItemStack(Item.ingotIron);
            ItemStack furnace = new ItemStack(Block.furnaceIdle);
            ItemStack roadBlock = new ItemStack(Roads.roadBlockMiscSingles, 1, 0);
            ItemStack smallPole = new ItemStack(Roads.powerPoleSmall);
            ItemStack medPole = new ItemStack(Roads.powerPole);
            ItemStack largePole = new ItemStack(Roads.powerPoleLarge);
            ItemStack dryDirt = new ItemStack(Roads.roadBlockMiscSingles, 1, 6);
            ItemStack dirtBlock = new ItemStack(Block.dirt);
            ItemStack redDye = new ItemStack(Item.dyePowder, 1, 1);
            ItemStack greenDye = new ItemStack(Item.dyePowder, 1, 2);
            ItemStack glowstoneDust = new ItemStack(Item.glowstone);
            ItemStack streetLight = new ItemStack(Roads.streetLamp1);
            ItemStack roadBarrier = new ItemStack(Roads.barrierBlock, 1, 0);
            ItemStack redstoneDust = new ItemStack(Item.redstone);
            ItemStack redstoneLamp = new ItemStack(Block.redstoneLampIdle);
            ItemStack blueDye = new ItemStack(Item.dyePowder, 1, 4);
            ItemStack blackDye = new ItemStack(Item.dyePowder, 1, 0);
            ItemStack greyDye = new ItemStack(Item.dyePowder, 1, 8);
            ItemStack blankSign = new ItemStack(Roads.blankSign);
            ItemStack slab = new ItemStack(Block.stoneSingleSlab);
            ItemStack concrete = new ItemStack(Roads.generalBlocks, 1, 4);
            ItemStack concreteOld = new ItemStack(Roads.generalBlocks, 1, 5);
            ItemStack cementBlock = new ItemStack(Roads.generalBlocks, 1, 1);
            ItemStack kerbBlock = new ItemStack(Roads.kerbBlock);
            ItemStack sidewalkGrey = new ItemStack(Roads.sidewalkBlockGrey, 1, 0);
            ItemStack sidewalkLight = new ItemStack(Roads.sidewalkBlockLight, 1, 0);
            ItemStack sidewalkTile = new ItemStack(Roads.sidewalkBlockTile, 1, 0);
            
            //Shaped Recipes
            GameRegistry.addRecipe(new ItemStack(Roads.cementItem, 4), " c ", " w ", "sss", 'w', waterBucketStack, 's', sandStack, 'c', cementDustStack);
            GameRegistry.addRecipe(new ItemStack(Roads.generalBlocks, 8, 2), "xxx", "xyx", "xxx", 'x', cobbleStoneStack, 'y', cementItemStack);
            GameRegistry.addRecipe(new ItemStack(Roads.limeStoneBlock), "ll", "ll", 'l', limeStack);
            GameRegistry.addRecipe(new ItemStack(Roads.yellowPaintCan), "yyy", "yby", "yyy", 'y', yellowDye, 'b', bucket);
            GameRegistry.addRecipe(new ItemStack(Roads.whitePaintCan), "www", "wbw", "www", 'w', yellowDye, 'b', bucket);
            GameRegistry.addRecipe(new ItemStack(Roads.roadPainter), "ifi", "fwf", "iii", 'w', workBench, 'i', ironIngot, 'f', furnace);
            GameRegistry.addRecipe(new ItemStack(Roads.powerPoleSmall, 3, 0), "iri", "iri", "iri", 'i', ironIngot, 'r', redstoneDust);
            GameRegistry.addRecipe(medPole, "s", "s", "s", 's', smallPole);
            GameRegistry.addRecipe(largePole, "m", "m", "m", 'm', medPole);
            GameRegistry.addRecipe(new ItemStack(Roads.roadBlockMiscSingles, 3, 6), "ddd", 'd', dirtBlock);
            GameRegistry.addRecipe(new ItemStack(Roads.roadBlockMiscSingles, 8, 5), "ddd", "dwd", "ddd", 'd', dryDirt, 'w', waterBucketStack);
            GameRegistry.addRecipe(new ItemStack(Roads.roadRamp1, 1, 0), "rrr", 'r', roadBlock);
            GameRegistry.addRecipe(new ItemStack(Roads.trafficLight), "iri", "dyd", "igi", 'i', ironIngot, 'r', redDye, 'd', glowstoneDust, 'y', yellowDye, 'g', greenDye);
            GameRegistry.addRecipe(new ItemStack(Roads.lightBollard), " b ", "wlw", "yly", 'l', redstoneLamp, 'b', blueDye, 'w', whiteDye, 'y', yellowDye);
            GameRegistry.addRecipe(streetLight, "iii", "grg", "iii", 'i', ironIngot, 'g', glowstoneDust, 'r', redstoneDust);
            GameRegistry.addRecipe(new ItemStack(Roads.streetSign, 1, 0), "bbb", " s ", "   ", 's', blankSign, 'b', blackDye);
            GameRegistry.addRecipe(new ItemStack(Roads.streetSign, 1, 4), " b ", "bsb", " b ", 's', blankSign, 'b', blackDye);
            GameRegistry.addRecipe(new ItemStack(Roads.streetSign, 1, 8), "b b", "bsb", " b ", 's', blankSign, 'b', blackDye);
            GameRegistry.addRecipe(new ItemStack(Roads.streetSign, 1, 12), "bbb", " s ", "bbb", 's', blankSign, 'b', blackDye);
            GameRegistry.addRecipe(new ItemStack(Roads.barrierBlock), "   ", "iii", "   ", 'i', ironIngot);
            GameRegistry.addRecipe(new ItemStack(Roads.barrierPole), "   ", "iii", " i ", 'i', ironIngot);
            GameRegistry.addRecipe(new ItemStack(Roads.barrierCorner), " b", "b ", 'b', roadBarrier);
            GameRegistry.addRecipe(new ItemStack(Roads.generalBlocks, 1, 6), " k ", "kbk", " k ", 'k', kerbBlock, 'b', sidewalkGrey);
            GameRegistry.addRecipe(new ItemStack(Roads.generalBlocks, 1, 7), " k ", "kbk", " k ", 'k', kerbBlock, 'b', sidewalkLight);
            GameRegistry.addRecipe(new ItemStack(Roads.sidewalkBlockSides, 1, 0), "kbk", 'k', kerbBlock, 'b', sidewalkGrey);
            GameRegistry.addRecipe(new ItemStack(Roads.sidewalkBlockSides, 1, 4), "kbk", 'k', kerbBlock, 'b', sidewalkLight);
            GameRegistry.addRecipe(new ItemStack(Roads.sidewalkBlockSides, 1, 8), "k k", " b ", 'k', kerbBlock, 'b', sidewalkGrey);
            GameRegistry.addRecipe(new ItemStack(Roads.sidewalkBlockSides, 1, 12), "k k", " b ", 'k', kerbBlock, 'b', sidewalkLight);
            GameRegistry.addRecipe(new ItemStack(Roads.sidewalkBlockTri, 1, 0), " k ", "kbk", 'k', kerbBlock, 'b', sidewalkGrey);
            GameRegistry.addRecipe(new ItemStack(Roads.sidewalkBlockTri, 1, 4), " k ", "kbk", 'k', kerbBlock, 'b', sidewalkLight);
            GameRegistry.addRecipe(new ItemStack(Roads.sidewalkBlockTri, 1, 8), " k ", "kbk", 'k', kerbBlock, 'b', sidewalkTile);
            GameRegistry.addRecipe(new ItemStack(Roads.sidewalkBlockGrey, 1, 4), "k", "b", 'k', kerbBlock, 'b', sidewalkGrey);
            GameRegistry.addRecipe(new ItemStack(Roads.sidewalkBlockGrey, 1, 8), " k", "b ", 'k', kerbBlock, 'b', sidewalkGrey);
            GameRegistry.addRecipe(new ItemStack(Roads.sidewalkBlockGrey, 1, 12), "kk", "kb", 'k', kerbBlock, 'b', sidewalkGrey);
            GameRegistry.addRecipe(new ItemStack(Roads.sidewalkBlockLight, 1, 4), "k", "b", 'k', kerbBlock, 'b', sidewalkLight);
            GameRegistry.addRecipe(new ItemStack(Roads.sidewalkBlockLight, 1, 8), " k", "b ", 'k', kerbBlock, 'b', sidewalkLight);
            GameRegistry.addRecipe(new ItemStack(Roads.sidewalkBlockLight, 1, 12), "kk", "kb", 'k', kerbBlock, 'b', sidewalkLight);
            GameRegistry.addRecipe(new ItemStack(Roads.sidewalkBlockTile, 1, 4), "k", "b", 'k', kerbBlock, 'b', sidewalkTile);
            GameRegistry.addRecipe(new ItemStack(Roads.sidewalkBlockTile, 1, 8), " k", "b ", 'k', kerbBlock, 'b', sidewalkTile);
            GameRegistry.addRecipe(new ItemStack(Roads.sidewalkBlockTile, 1, 12), "kk", "kb", 'k', kerbBlock, 'b', sidewalkTile);
            
            //Shapeless Recipes
            GameRegistry.addShapelessRecipe(new ItemStack(Roads.roadBlockMiscSingles, 8, 0), macadamStack, macadamStack, macadamStack, macadamStack, macadamStack, macadamStack, macadamStack, macadamStack, tarBucketStack);
            GameRegistry.addShapelessRecipe(new ItemStack(Roads.limeClayPowderItem), limeStack, clayStack);
            GameRegistry.addShapelessRecipe(new ItemStack(Roads.yellowPaintBlob, 6, 0), yellowDye);
            GameRegistry.addShapelessRecipe(new ItemStack(Roads.whitePaintBlob, 6, 0), whiteDye);
            GameRegistry.addShapelessRecipe(new ItemStack(Roads.generalBlocks, 1, 0), roadBlock, roadBlock);
            GameRegistry.addShapelessRecipe(new ItemStack(Roads.streetLamp2), streetLight, streetLight);
            GameRegistry.addShapelessRecipe(new ItemStack(Roads.kerbBlock), slab);
            GameRegistry.addShapelessRecipe(new ItemStack(Roads.sidewalkBlockGrey), greyDye, concrete);
            GameRegistry.addShapelessRecipe(new ItemStack(Roads.sidewalkBlockLight), whiteDye, concrete);
            GameRegistry.addShapelessRecipe(new ItemStack(Roads.sidewalkBlockTile), whiteDye, blackDye, concrete);
            GameRegistry.addShapelessRecipe(new ItemStack(Roads.sidewalkBlockGrey), greyDye, concreteOld);
            GameRegistry.addShapelessRecipe(new ItemStack(Roads.sidewalkBlockLight), whiteDye, concreteOld);
            GameRegistry.addShapelessRecipe(new ItemStack(Roads.sidewalkBlockTile), whiteDye, blackDye, concreteOld);
            GameRegistry.addShapelessRecipe(new ItemStack(Roads.generalBlocks, 1, 5), concrete, waterBucketStack);
            GameRegistry.addShapelessRecipe(new ItemStack(Roads.generalBlocks, 1, 4), cementBlock, sandStack, waterBucketStack, cobbleStoneStack);
            
            //And some smelting!
            GameRegistry.addSmelting(Roads.limeClayPowderItem.itemID, new ItemStack(Roads.cementDustItem), 0.1F);
            GameRegistry.addSmelting(Roads.cementItem.itemID, new ItemStack(Roads.generalBlocks, 1, 1), 0.2F);
            GameRegistry.addSmelting(Roads.tarBucketItem.itemID, new ItemStack(Roads.generalBlocks, 1, 3), 0.2F);
        }


		@EventHandler
        public void postInit(FMLPostInitializationEvent event) {
			
			int roadsRenderID = RenderingRegistry.getNextAvailableRenderId();
                // Stub Method
        		}
		};