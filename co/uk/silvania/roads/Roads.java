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
						return new ItemStack(Item.eyeOfEnder, 1, 0);
					}
			};
	
		public final static Block roadBlock = new RoadBlock(500, 1, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlock").setCreativeTab(tabRoads);
		
		public final static Block roadBlockDYS = new RBDoubleYellowStripe(501, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockDYS").setCreativeTab(tabRoads);
		
		public final static Block roadBlockSYS = new RBSingleYellowStripe(504, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockSYS").setCreativeTab(tabRoads);
		
		public final static Block roadBlockSWS = new RBSingleWhiteSide(505, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockSWS").setCreativeTab(tabRoads);
		
		public final static Block roadBlockSWC = new RBSingleWhiteCenter(506, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockSWC").setCreativeTab(tabRoads);
		
		public final static Block roadBlockSYC = new RBSingleYellowCenter(507, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockSYC").setCreativeTab(tabRoads);
		
		public final static Block roadBlockDYC = new RBDoubleYellowCenter(508, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockDYC").setCreativeTab(tabRoads);
		
		public final static Block roadBlockWhiteCross = new RBWhiteCross(509, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockWhiteCross").setCreativeTab(tabRoads);
		
		public final static Block roadBlockYellowCross = new RBYellowCross(510, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockYellowCross").setCreativeTab(tabRoads);
		
		public final static Block roadBlockWhiteFull = new RBWhiteFull(511, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlockWhiteFull").setCreativeTab(tabRoads);
		
		
		public final static Block macadamBlock = new MacadamBlock(502, 4, Material.rock)
				.setHardness(0.7F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("macadamBlock").setCreativeTab(tabRoads);
		
		public final static Block cementBlock = new CementBlock(503, 5, Material.rock)
				.setHardness(1.5F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("cementBlock").setCreativeTab(tabRoads);
		
		public final static Block stillTarBlock = new StillTarBlock(520)
				.setBlockName("stillTarBlock").setCreativeTab(tabRoads);
		
		public final static Block flowingTarBlock = new FlowingTarBlock(521)
				.setBlockName("flowingTarBlock").setCreativeTab(tabRoads);
		
		public final static Item cementItem = new CementItem(20000)
				.setMaxStackSize(64).setIconIndex(0)
				.setCreativeTab(tabRoads).setItemName("cementDust");
		
		public final static Item cementDustItem = new CementDustItem(20001)
				.setMaxStackSize(64).setIconIndex(1)
				.setCreativeTab(tabRoads).setItemName("cementDustItem");
		
		public final static Item tarBucketItem = new TarBucketItem(20002, 521)
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
                int roadBlockID = config.getBlock("roadBlock", 200).getInt();
                
                Property roadBlockProperty = config.get(Configuration.CATEGORY_GENERAL, "SomeConfigString", "nothing");
                roadBlockProperty.comment = "This is a string. Change it, bitch!";
                String roadBlockString = roadBlockProperty.value;
        }
        
        @Init
        public void load(FMLInitializationEvent event) {
                proxy.registerRenderThings();
                
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
                
                //Register the items (name, register it)
                LanguageRegistry.addName(cementItem, "Cement");
                GameRegistry.registerItem(cementItem, "cementItem");
                
                LanguageRegistry.addName(cementDustItem, "Cement Dust");
                GameRegistry.registerItem(cementDustItem, "cementDustItem");
                
                LanguageRegistry.addName(tarBucketItem, "Bucket of Tar");
                GameRegistry.registerItem(tarBucketItem, "tarBucketItem");
                
                //Register the liquids
                LanguageRegistry.addName(stillTarBlock, "Liquid Tar (Still)");
                GameRegistry.registerBlock(stillTarBlock, "stillTarBlock");
                
                LanguageRegistry.addName(flowingTarBlock, "Liquid Tar (Flowing)");
                GameRegistry.registerBlock(flowingTarBlock, "flowingTarBlock");
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