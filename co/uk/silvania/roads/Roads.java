package co.uk.silvania.roads;

import co.uk.silvania.roads.block.*;
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

@Mod(modid="FlenixRoads", name="FlenixRoads", version="0.4.2")
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
	
	public static Block roadRamp1;
	public static Block roadRamp2;
	
	public static Block sidewalkBlockGrey;
	public static Block sidewalkBlockLight;
	public static Block sidewalkBlockTile;
	
	public static Block powerPole;
	public static Block powerPoleSmall;
	public static Block powerPoleLarge;
	
	public static Block blockGag1;
	public static Block blockGag2;
	public static Block blockGag3;
	
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

	//And finally the worldgen
	public static WorldGen worldGen = new WorldGen();

    
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	RoadsConfig config = new RoadsConfig();
    	NetworkRegistry.instance().registerGuiHandler(this, roadsGuiHandler);
    	
    	RoadsConfig.loadConfig(event); 

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
    	sidewalkBlockGrey = new SidewalkBlockGrey(config.sidewalkBlockGreyID).setUnlocalizedName("sidewalkBlockGrey");
    	sidewalkBlockLight = new SidewalkBlockLight(config.sidewalkBlockLightID).setUnlocalizedName("sidewalkBlockLight");
    	sidewalkBlockTile = new SidewalkBlockTile(config.sidewalkBlockTileID).setUnlocalizedName("sidewalkBlockTile");
    	roadBlockDirt = new RoadBlockDirt(config.roadBlockDirtID).setUnlocalizedName("roadBlockDirt");
    	roadBlockDirtCorner = new RoadBlockDirtCorner(config.roadBlockDirtCornerID).setUnlocalizedName("roadBlockDirtCorner");
    	
    	roadRamp1 = new TileEntityRamp1(config.roadRamp1ID).setUnlocalizedName("roadRamp1");
    	roadRamp2 = new TileEntityRamp2(config.roadRamp2ID).setUnlocalizedName("roadRamp2");
    	
    	powerPole = new PowerPoleMedium(config.powerPoleID).setUnlocalizedName("powerPole");
    	powerPoleSmall = new PowerPoleSmall(config.powerPoleSmallID).setUnlocalizedName("powerPoleSmall");
    	powerPoleLarge = new PowerPoleLarge(config.powerPoleLargeID).setUnlocalizedName("powerPoleLarge");
    	
    	blockGag1 = new BlockGag1(config.blockGag1ID).setUnlocalizedName("blockGag1");
    	blockGag2 = new BlockGag2(config.blockGag2ID).setUnlocalizedName("blockGag2");
    	blockGag3 = new BlockGag3(config.blockGag3ID).setUnlocalizedName("blockGag3");
    	
    	
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
    	roadBarrier = new TileEntityRoadBarrierBlock(config.roadBarrierID).setUnlocalizedName("roadBarrier");

    	
    	cementItem = new CementItem(config.cementItemID).setUnlocalizedName("cementItem");
    	cementDustItem = new CementDustItem(config.cementDustID).setUnlocalizedName("cementDustItem");
    	limeStonePowderItem = new LimeStonePowderItem(config.limeStonePowderID).setUnlocalizedName("limeStonePowderItem");
    	limeClayPowderItem = new LimeClayPowderItem(config.limeClayPowderID).setUnlocalizedName("limeClayPowderItem");
    	tarBucketItem = new TarBucketItem(config.tarBucketID).setUnlocalizedName("tarBucketItem");
    	whitePaintBlob = new WhitePaintBlob(config.whitePaintBlobID).setUnlocalizedName("whitePaintBlob");
    	yellowPaintBlob = new YellowPaintBlob(config.yellowPaintBlobID).setUnlocalizedName("yellowPaintBlob");
    	whitePaintCan = new WhitePaintCan(config.whitePaintCanID).setUnlocalizedName("whitePaintCan");
    	yellowPaintCan = new YellowPaintCan(config.yellowPaintCanID).setUnlocalizedName("yellowPaintCan");
    	
    	//MinecraftForgeClient.registerItemRenderer(trafficLight.blockID, new TrafficLightItemRenderer());
    	
        }
               
    @EventHandler
    public void load(FMLInitializationEvent event) {
            proxy.registerRenderThings();
            
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
            //ItemStack macadamStack = new ItemStack(Roads.macadamBlock);
            ItemStack limeStack = new ItemStack (Roads.limeStonePowderItem);

            //Shaped Recipes
            GameRegistry.addRecipe(new ItemStack(Roads.cementItem, 4), " c ", " w ", "sss", 'w', waterBucketStack, 's', sandStack, 'c', cementDustStack);
            //GameRegistry.addRecipe(new ItemStack(Roads.macadamBlock, 8), "xxx", "xyx", "xxx", 'x', cobbleStoneStack, 'y', cementItemStack);
            GameRegistry.addRecipe(new ItemStack(Roads.limeStoneBlock), "ll", "ll", 'l', limeStack);

            //Shapeless Recipes
            //GameRegistry.addShapelessRecipe(new ItemStack(Roads.roadBlock), macadamStack, tarBucketStack);
            GameRegistry.addShapelessRecipe(new ItemStack(Roads.limeClayPowderItem), limeStack, clayStack);

            //And some smelting!
            GameRegistry.addSmelting(Roads.limeClayPowderItem.itemID, new ItemStack(Roads.cementDustItem), 0.1f);
            //GameRegistry.addSmelting(Roads.cementItem.itemID, new ItemStack(Roads.cementBlock), 0.2f);
            
            //RenderingRegistry.registerBlockHandler(new TileEntityTrafficLightRenderer());
        }


		@EventHandler
        public void postInit(FMLPostInitializationEvent event) {
			
			int roadsRenderID = RenderingRegistry.getNextAvailableRenderId();
                // Stub Method
        		}
		};