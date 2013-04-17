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

@Mod(modid="Roads", name="Roads", version="0.0.1")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class Roads { 
	
		public static CreativeTabs tabRoads = new CreativeTabs("tabRoads") {
				public ItemStack getIconItemStack() {
						return new ItemStack(Item.eyeOfEnder, 1, 0);
					}
			};
	
		public final static Block roadBlock = new RoadBlock(500, 0, Material.rock)
				.setHardness(1.0F).setStepSound(Block.soundStoneFootstep)
				.setBlockName("roadBlock").setCreativeTab(tabRoads);

        // The instance of your mod that Forge uses.
        @Instance("Roads")
        public static Roads instance;

        // Says where the client and server proxy code is loaded.
        @SidedProxy(clientSide="co.uk.silvania.roads.client.ClientProxy", serverSide="co.uk.silvania.roads.CommonProxy")
        public static CommonProxy proxy;

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
                proxy.registerRenderers();
                
                //Register the block's name
                LanguageRegistry.addName(roadBlock, "Tarmac (Unpainted)");
                MinecraftForge.setBlockHarvestLevel(roadBlock, "pickaxe", 0);
                GameRegistry.registerBlock(roadBlock, "roadBlock");
                //Set the name for the creative tab
                LanguageRegistry.instance().addStringLocalization("itemGroup.tabRoads", "en_US", "Roads");
                                
        }
        
        @PostInit
        public void postInit(FMLPostInitializationEvent event) {
                // Stub Method
        		}
		};