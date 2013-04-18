package co.uk.silvania.roads;

import net.minecraft.block.material.Material;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;
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

@Mod(modid="FlenixRoads", name="FlenixRoads", version="0.0.1")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class Roads { 
	
		public static CreativeTabs tabRoads = new CreativeTabs("tabRoads") {
				public ItemStack getIconItemStack() {
						return new ItemStack(Roads.cementDustItem, 1, 0);
					}
			};
	
		//public final static Block blockTest = new BlockTest(550, 1, Material.rock)
				//.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				//.setBlockName("blockTest").setCreativeTab(tabRoads);
		
		public final static Block roadBlockSWS2 = new RBSideWhiteStripe(812, 0)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockSWS2").setCreativeTab(tabRoads);
			
		public final static Block roadBlock = new RoadBlock(800, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlock").setCreativeTab(tabRoads);
		
		public final static Block roadBlockDYS = new RBDoubleYellowStripe(801, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockDYS").setCreativeTab(tabRoads);
		
		public final static Block macadamBlock = new MacadamBlock(802, 4, Material.rock)
				.setHardness(0.7F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("macadamBlock").setCreativeTab(tabRoads);

		public final static Block cementBlock = new CementBlock(803, 5, Material.rock)
				.setHardness(1.5F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("cementBlock").setCreativeTab(tabRoads);
		
		public final static Block roadBlockSYS = new RBSingleYellowStripe(804, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockSYS").setCreativeTab(tabRoads);
		
		public final static Block roadBlockSWS = new RBSingleWhiteSide(805, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockSWS").setCreativeTab(tabRoads);
		
		public final static Block roadBlockSWC = new RBSingleWhiteCenter(806, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockSWC").setCreativeTab(tabRoads);
		
		public final static Block roadBlockSYC = new RBSingleYellowCenter(807, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockSYC").setCreativeTab(tabRoads);
		
		public final static Block roadBlockDYC = new RBDoubleYellowCenter(808, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockDYC").setCreativeTab(tabRoads);
		
		public final static Block roadBlockWhiteCross = new RBWhiteCross(809, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockWhiteCross").setCreativeTab(tabRoads);
		
		public final static Block roadBlockYellowCross = new RBYellowCross(810, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockYellowCross").setCreativeTab(tabRoads);
		
		public final static Block roadBlockWhiteFull = new RBWhiteFull(811, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockWhiteFull").setCreativeTab(tabRoads);
		
		public final static Block roadBlockWC = new RBWhiteCorner(813, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockWC").setCreativeTab(tabRoads);
		
		public final static Block roadBlockFYS = new RBFarYellowStripe(814, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockFYS").setCreativeTab(tabRoads);
		
		public final static Block roadBlockYC = new RBYellowCorner(815, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockYC").setCreativeTab(tabRoads);
		
		public final static Block roadBlockYAC = new RBYellowAltCorner(816, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockYAC").setCreativeTab(tabRoads);
		
		public final static Block roadBlockWAC = new RBWhiteAltCorner(817, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockWAC").setCreativeTab(tabRoads);
		
		public final static Block roadBlockWSL = new RBWhiteStripedLine(818, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockWSL").setCreativeTab(tabRoads);
		
		public final static Block roadBlockWSSL = new RBWhiteSideStripedLine(819, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockWSSL").setCreativeTab(tabRoads);
		
		public final static Block roadBlockYSSL = new RBYellowSideStripedLine(820, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockYSSL").setCreativeTab(tabRoads);
		
		public final static Block roadBlockJO = new RBJunctOut(821, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockJO").setCreativeTab(tabRoads);
		
		public final static Block roadBlockJI = new RBJunctIn(822, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockJI").setCreativeTab(tabRoads);
		
		public final static Block roadBlockJOL = new RBJunctOutLine(823, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockJOL").setCreativeTab(tabRoads);
		
		public final static Block roadBlockAL = new RBArrowLine(824, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockAL").setCreativeTab(tabRoads);
		
		public final static Block roadBlockAUS = new RBArrowStraight(825, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockAUS").setCreativeTab(tabRoads);
		
		public final static Block roadBlockAUL = new RBArrowLeft(826, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockAUL").setCreativeTab(tabRoads);
		
		public final static Block roadBlockAUR = new RBArrowRight(827, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockAUR").setCreativeTab(tabRoads);
		
		public final static Block roadBlockWDS = new RBWhiteDiagonalStripe(828, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockWDS").setCreativeTab(tabRoads);
		
		public final static Block roadBlockYDS = new RBYellowDiagonalStripe(829, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockYDS").setCreativeTab(tabRoads);
		
		public final static Block roadBlockWSS = new RBWhiteSmallSquare(830, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockWSS").setCreativeTab(tabRoads);
		
		public final static Block roadBlockYSS = new RBYellowSmallSquare(831, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockYSS").setCreativeTab(tabRoads);
		
		public final static Block roadBlockSL = new RBSL(832, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockSL").setCreativeTab(tabRoads);
		
		public final static Block roadBlockOW = new RBOW(833, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockOW").setCreativeTab(tabRoads);
		
		public final static Block roadBlockST = new RBST(834, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockST").setCreativeTab(tabRoads);
		
		public final static Block roadBlockOP = new RBOP(835, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockOP").setCreativeTab(tabRoads);
		
		/*public final static Block stillTarBlock = new StillTarBlock(850)
				.setBlockName("stillTarBlock").setCreativeTab(tabRoads);
		
		public final static Block flowingTarBlock = new FlowingTarBlock(851)
				.setBlockName("flowingTarBlock").setCreativeTab(tabRoads);*/
		
		public final static Item cementItem = new CementItem(16700)
				.setMaxStackSize(64).setIconIndex(0)
				.setCreativeTab(tabRoads).setItemName("cementDust");
		
		public final static Item cementDustItem = new CementDustItem(16701)
				.setMaxStackSize(64).setIconIndex(1)
				.setCreativeTab(tabRoads).setItemName("cementDustItem");
		
		public final static Item tarBucketItem = new TarBucketItem(16702)
				.setMaxStackSize(1).setIconIndex(2)
				.setCreativeTab(tabRoads).setItemName("tarBucketItem");

        // The instance of your mod that Forge uses.
        @Instance("FlenixRoads")
        public static Roads instance;

        // Says where the client and server proxy code is loaded.
        @SidedProxy(clientSide="co.uk.silvania.roads.client.ClientProxy", serverSide="co.uk.silvania.roads.CommonProxy")
        public static CommonProxy proxy;
        
        public static Block TarrStill;
        public static Block TarrFlowing;
        
        @SideOnly(Side.CLIENT)
    	public static int tarModel;

        @PreInit
        public void preInit(FMLPreInitializationEvent event) {
        		//Does the file exist? If it doesn't this will create it.
                Configuration config = new Configuration(event.getSuggestedConfigurationFile());
                
                //This will load the config file...
                config.load();
                //...And this will save it.
                config.save();
                
                //config.getBlock(NameOfProperty, defaultID).getInt();
                int roadBlockID = config.getBlock("roadBlock", 500).getInt();
                
                Property roadBlockProperty = config.get(Configuration.CATEGORY_GENERAL, "SomeConfigString", "nothing");
                roadBlockProperty.comment = "This is a string. Change it, bitch!";
                String roadBlockString = roadBlockProperty.value;
        }
        
        @Init
        public void load(FMLInitializationEvent event) {
                proxy.registerRenderThings();
                
                LanguageRegistry.addName(roadBlockSWS2, "Tarmac (Side White Stripe)");
                MinecraftForge.setBlockHarvestLevel(roadBlockSWS2, "pickaxe", 0);
                GameRegistry.registerBlock(roadBlockSWS2, "roadBlockSWS2");
                              
                //Register the blocks (Name, Tool/Level, register it)
                LanguageRegistry.addName(roadBlock, "Tarmac (Unpainted)");
                MinecraftForge.setBlockHarvestLevel(roadBlock, "pickaxe", 0);
                GameRegistry.registerBlock(roadBlock, "roadBlock");
                
                LanguageRegistry.addName(roadBlockDYS, "Tarmac (Double Yellow Stripe)");
                MinecraftForge.setBlockHarvestLevel(roadBlockDYS, "pickaxe", 0);
                GameRegistry.registerBlock(roadBlockDYS, "roadBlockDYS");
                
                LanguageRegistry.addName(cementBlock, "Cement");
                MinecraftForge.setBlockHarvestLevel(cementBlock, "pickaxe", 2);
                GameRegistry.registerBlock(cementBlock, "cementBlock");
                
                LanguageRegistry.addName(macadamBlock, "Macadam");
                MinecraftForge.setBlockHarvestLevel(macadamBlock, "pickaxe", 1);
                GameRegistry.registerBlock(macadamBlock, "macadamBlock");
                              
                LanguageRegistry.addName(roadBlockSYS, "Tarmac (Single Yellow Stripe)");
                MinecraftForge.setBlockHarvestLevel(roadBlockSYS, "pickaxe", 1);
                GameRegistry.registerBlock(roadBlockSYS, "roadBlockSYS");
                                		
                LanguageRegistry.addName(roadBlockSWS, "Tarmac (Single White Stripe)");
                MinecraftForge.setBlockHarvestLevel(roadBlockSWS, "pickaxe", 1);
                GameRegistry.registerBlock(roadBlockSWS, "roadBlockSWS");
                        
                LanguageRegistry.addName(roadBlockSWC, "Tarmac (Single White Stripe Center)");
                MinecraftForge.setBlockHarvestLevel(roadBlockSWC, "pickaxe", 1);
                GameRegistry.registerBlock(roadBlockSWC, "roadBlockSWC");
                        		
                LanguageRegistry.addName(roadBlockSYC, "Tarmac (Single Yellow Stripe Center)");
                MinecraftForge.setBlockHarvestLevel(roadBlockSYC, "pickaxe", 1);
                GameRegistry.registerBlock(roadBlockSYC, "roadBlockSYC");
                                        		
                LanguageRegistry.addName(roadBlockDYC, "Tarmac (Double Yellow Stripe Center)");
                MinecraftForge.setBlockHarvestLevel(roadBlockDYC, "pickaxe", 1);
                GameRegistry.registerBlock(roadBlockDYC, "roadBlockDYC");
                        
                LanguageRegistry.addName(roadBlockWhiteCross, "Tarmac (White Cross)");
                MinecraftForge.setBlockHarvestLevel(roadBlockWhiteCross, "pickaxe", 1);
                GameRegistry.registerBlock(roadBlockWhiteCross, "roadBlockWhiteCross");
                        		
                LanguageRegistry.addName(roadBlockYellowCross, "Tarmac (Yellow Cross)");
                MinecraftForge.setBlockHarvestLevel(roadBlockYellowCross, "pickaxe", 1);
                GameRegistry.registerBlock(roadBlockYellowCross, "roadBlockYellowCross");
                                        		
                LanguageRegistry.addName(roadBlockWhiteFull, "Tarmac (White Top)");
                MinecraftForge.setBlockHarvestLevel(roadBlockWhiteFull, "pickaxe", 1);
                GameRegistry.registerBlock(roadBlockWhiteFull, "roadBlockWhiteFull");
                
                LanguageRegistry.addName(roadBlockWC, "Tarmac (White Corner)");
                MinecraftForge.setBlockHarvestLevel(roadBlockWC, "pickaxe", 1);
                GameRegistry.registerBlock(roadBlockWC, "roadBlockWC");
                
                LanguageRegistry.addName(roadBlockFYS, "Tarmac (Side Yellow Stripe)");
                MinecraftForge.setBlockHarvestLevel(roadBlockFYS, "pickaxe", 1);
                GameRegistry.registerBlock(roadBlockFYS, "roadBlockFYS");
                
                LanguageRegistry.addName(roadBlockYC, "Tarmac (Yellow Corner)");
                MinecraftForge.setBlockHarvestLevel(roadBlockYC, "pickaxe", 1);
                GameRegistry.registerBlock(roadBlockYC, "roadBlockYC");
                
                LanguageRegistry.addName(roadBlockYAC, "Tarmac (Yellow Alt. Corner)");
                MinecraftForge.setBlockHarvestLevel(roadBlockYAC, "pickaxe", 1);
                GameRegistry.registerBlock(roadBlockYAC, "roadBlockYAC");
                
                LanguageRegistry.addName(roadBlockWAC, "Tarmac (White Alt. Corner)");
                MinecraftForge.setBlockHarvestLevel(roadBlockWAC, "pickaxe", 1);
                GameRegistry.registerBlock(roadBlockWAC, "roadBlockWAC");
                
                LanguageRegistry.addName(roadBlockWSL, "Tarmac (White Striped Line)");
                MinecraftForge.setBlockHarvestLevel(roadBlockWSL, "pickaxe", 1);
                GameRegistry.registerBlock(roadBlockWSL, "roadBlockWSL");
                
                LanguageRegistry.addName(roadBlockWSSL, "Tarmac (White Side Striped Line)");
                MinecraftForge.setBlockHarvestLevel(roadBlockWSSL, "pickaxe", 1);
                GameRegistry.registerBlock(roadBlockWSSL, "roadBlockWSSL");
                
                LanguageRegistry.addName(roadBlockYSSL, "Tarmac (Yellow Side Striped Line)");
                MinecraftForge.setBlockHarvestLevel(roadBlockYSSL, "pickaxe", 1);
                GameRegistry.registerBlock(roadBlockYSSL, "roadBlockYSSL");
                
                LanguageRegistry.addName(roadBlockJO, "Tarmac Junction (Out)");
                MinecraftForge.setBlockHarvestLevel(roadBlockJO, "pickaxe", 1);
                GameRegistry.registerBlock(roadBlockJO, "roadBlockJO");
                
                LanguageRegistry.addName(roadBlockJI, "Tarmac Junction (In)");
                MinecraftForge.setBlockHarvestLevel(roadBlockJI, "pickaxe", 1);
                GameRegistry.registerBlock(roadBlockJI, "roadBlockJI");
                
                LanguageRegistry.addName(roadBlockJOL, "Tarmac Junction (Out, Line)");
                MinecraftForge.setBlockHarvestLevel(roadBlockJOL, "pickaxe", 1);
                GameRegistry.registerBlock(roadBlockJOL, "roadBlockJOL");
                
                LanguageRegistry.addName(roadBlockAL, "Tarmac Arrow (Line)");
                MinecraftForge.setBlockHarvestLevel(roadBlockAL, "pickaxe", 1);
                GameRegistry.registerBlock(roadBlockAL, "roadBlockAL");
                
                LanguageRegistry.addName(roadBlockAUS, "Tarmac Arrow (Straight)");
                MinecraftForge.setBlockHarvestLevel(roadBlockAUS, "pickaxe", 1);
                GameRegistry.registerBlock(roadBlockAUS, "roadBlockAUS");
                
                LanguageRegistry.addName(roadBlockAUL, "Tarmac Arrow (Left)");
                MinecraftForge.setBlockHarvestLevel(roadBlockAUL, "pickaxe", 1);
                GameRegistry.registerBlock(roadBlockAUL, "roadBlockAUL");
                
                LanguageRegistry.addName(roadBlockAUR, "Tarmac Arrow (Right)");
                MinecraftForge.setBlockHarvestLevel(roadBlockAUR, "pickaxe", 1);
                GameRegistry.registerBlock(roadBlockAUR, "roadBlockAUR");
                
                LanguageRegistry.addName(roadBlockWDS, "Tarmac (White Diagonal Stripe)");
                MinecraftForge.setBlockHarvestLevel(roadBlockWDS, "pickaxe", 1);
                GameRegistry.registerBlock(roadBlockWDS, "roadBlockWDS");
                
                LanguageRegistry.addName(roadBlockYDS, "Tarmac (Yellow Diagonal Stripe)");
                MinecraftForge.setBlockHarvestLevel(roadBlockYDS, "pickaxe", 1);
                GameRegistry.registerBlock(roadBlockYDS, "roadBlockYDS");
                
                LanguageRegistry.addName(roadBlockWSS, "Tarmac (White Small Square)");
                MinecraftForge.setBlockHarvestLevel(roadBlockWSS, "pickaxe", 1);
                GameRegistry.registerBlock(roadBlockWSS, "roadBlockWSS");
                
                LanguageRegistry.addName(roadBlockYSS, "Tarmac (Yellow Small Square)");
                MinecraftForge.setBlockHarvestLevel(roadBlockYSS, "pickaxe", 1);
                GameRegistry.registerBlock(roadBlockYSS, "roadBlockYSS");
                
                LanguageRegistry.addName(roadBlockSL, "Tarmac (Slow 1)");
                MinecraftForge.setBlockHarvestLevel(roadBlockSL, "pickaxe", 1);
                GameRegistry.registerBlock(roadBlockSL, "roadBlockSL");
                
                LanguageRegistry.addName(roadBlockOW, "Tarmac (Slow 2)");
                MinecraftForge.setBlockHarvestLevel(roadBlockOW, "pickaxe", 1);
                GameRegistry.registerBlock(roadBlockOW, "roadBlockOW");
                
                LanguageRegistry.addName(roadBlockST, "Tarmac (Stop 1)");
                MinecraftForge.setBlockHarvestLevel(roadBlockST, "pickaxe", 1);
                GameRegistry.registerBlock(roadBlockST, "roadBlockST");
                
                LanguageRegistry.addName(roadBlockOP, "Tarmac (Stop 2)");
                MinecraftForge.setBlockHarvestLevel(roadBlockOP, "pickaxe", 1);
                GameRegistry.registerBlock(roadBlockOP, "roadBlockOP");
                
                //Register the items (name, register it)
                LanguageRegistry.addName(cementItem, "Cement");
                GameRegistry.registerItem(cementItem, "cementItem");
                
                LanguageRegistry.addName(cementDustItem, "Cement Dust");
                GameRegistry.registerItem(cementDustItem, "cementDustItem");
                
                LanguageRegistry.addName(tarBucketItem, "Bucket of Tar");
                GameRegistry.registerItem(tarBucketItem, "tarBucketItem");
                
                /*//Register the liquids
                LanguageRegistry.addName(stillTarBlock, "Liquid Tar (Still)");
                GameRegistry.registerBlock(stillTarBlock, "stillTarBlock");
                
                LanguageRegistry.addName(flowingTarBlock, "Liquid Tar (Flowing)");
                GameRegistry.registerBlock(flowingTarBlock, "flowingTarBlock");
                */
                
                //Set the name for the creative tab
                LanguageRegistry.instance().addStringLocalization("itemGroup.tabRoads", "en_US", "Roads");
                
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
                
                //Shaped Recipes
                GameRegistry.addRecipe(new ItemStack(Roads.cementItem, 4), " c ", " w ", "sss",
                		'w', waterBucketStack, 's', sandStack, 'c', cementDustStack);
                
                GameRegistry.addRecipe(new ItemStack(Roads.macadamBlock, 8), "xxx", "xyx", "xxx",
                		'x', cobbleStoneStack, 'y', cementItemStack);
                
                //Shapeless Recipes
                GameRegistry.addShapelessRecipe(new ItemStack(Roads.roadBlock), macadamStack, tarBucketStack);
                
                //And some smelting!
                GameRegistry.addSmelting(Item.clay.itemID, new ItemStack(Roads.cementDustItem), 0.1f);
                GameRegistry.addSmelting(Roads.cementItem.itemID, new ItemStack(Roads.cementBlock), 0.2f);
                                
        }
        
        @PostInit
        public void postInit(FMLPostInitializationEvent event) {
                // Stub Method
        		}
		};