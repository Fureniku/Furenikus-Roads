package co.uk.silvania.roads;

import co.uk.silvania.roads.block.ItemSideWalkBlocks;
import co.uk.silvania.roads.tileentities.entities.TileEntityTrafficLightEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class CommonProxy {
        
        public void registerRenderThings() {
        }
        
        public void registerBlocks() {
            GameRegistry.registerBlock(Roads.roadBlock, "roadBlock");
            GameRegistry.registerBlock(Roads.cementBlock, "cementBlock");
            GameRegistry.registerBlock(Roads.macadamBlock, "macadamBlock");
            GameRegistry.registerBlock(Roads.limeStoneBlock, "limeStoneBlock");
            GameRegistry.registerBlock(Roads.hardenedTarBlock, "hardenedTarBlock");
            GameRegistry.registerBlock(Roads.roadsTarStill, "roadsTarStill");
            GameRegistry.registerBlock(Roads.roadsTarFlowing, "roadsTarFlowing");
            GameRegistry.registerBlock(Roads.sidewalkKerbed, "sidewalkKerbed");
            GameRegistry.registerBlock(Roads.sidewalkKerbed1, "sidewalkKerbed1");
            GameRegistry.registerBlock(Roads.sidewalkKerbed2, "sidewalkKerbed2");
            GameRegistry.registerBlock(Roads.sidewalkKerbedInnerCorner, "sidewalkKerbedInnerCorner");
            GameRegistry.registerBlock(Roads.sidewalkKerbed1InnerCorner, "sidewalkKerbed1InnerCorner");
            GameRegistry.registerBlock(Roads.sidewalkKerbed2InnerCorner, "sidewalkKerbed2InnerCorner");
            GameRegistry.registerBlock(Roads.sidewalkKerbedOuterCorner, "sidewalkKerbedOuterCorner");
            GameRegistry.registerBlock(Roads.sidewalkKerbed1OuterCorner, "sidewalkKerbed1OuterCorner");
            GameRegistry.registerBlock(Roads.sidewalkKerbed2OuterCorner, "sidewalkKerbed2OuterCorner");
            GameRegistry.registerBlock(Roads.catsEye, "catsEye");
            GameRegistry.registerBlock(Roads.catsEyeSide, "catsEyeSide");
            GameRegistry.registerBlock(Roads.sidewalkBlocks, ItemSideWalkBlocks.class, "Silvania" + (Roads.sidewalkBlocks.getUnlocalizedName().substring(5)));
            
            GameRegistry.registerBlock(Roads.roadBlockSWS2, "roadBlockSWS2");
            GameRegistry.registerBlock(Roads.roadBlockDYSEL, "roadBlockDYSE");
            GameRegistry.registerBlock(Roads.roadBlockDYSER, "roadBlockDYSER");
            GameRegistry.registerBlock(Roads.roadBlockDYSI, "roadBlockDYSI");
            GameRegistry.registerBlock(Roads.roadBlockDYSO, "roadBlockDYSO");
            GameRegistry.registerBlock(Roads.roadBlockDYS, "roadBlockDYS");
            GameRegistry.registerBlock(Roads.roadBlockSYS, "roadBlockSYS");
            GameRegistry.registerBlock(Roads.roadBlockSWS, "roadBlockSWS");
            GameRegistry.registerBlock(Roads.roadBlockSWC, "roadBlockSWC");
            GameRegistry.registerBlock(Roads.roadBlockSYC, "roadBlockSYC");
            GameRegistry.registerBlock(Roads.roadBlockDYC, "roadBlockDYC");
            GameRegistry.registerBlock(Roads.roadBlockWhiteCross, "roadBlockWhiteCross");
            GameRegistry.registerBlock(Roads.roadBlockYellowCross, "roadBlockYellowCross");
            GameRegistry.registerBlock(Roads.roadBlockWhiteFull, "roadBlockWhiteFull");
            GameRegistry.registerBlock(Roads.roadBlockYellowFull, "roadBlockYellowFull");
            GameRegistry.registerBlock(Roads.roadBlockWC, "roadBlockWC");
            GameRegistry.registerBlock(Roads.roadBlockFYS, "roadBlockFYS");
            GameRegistry.registerBlock(Roads.roadBlockYC, "roadBlockYC");
            GameRegistry.registerBlock(Roads.roadBlockYAC, "roadBlockYAC");
            GameRegistry.registerBlock(Roads.roadBlockWAC, "roadBlockWAC");
            GameRegistry.registerBlock(Roads.roadBlockWSL, "roadBlockWSL");
            GameRegistry.registerBlock(Roads.roadBlockWSSL, "roadBlockWSSL");
            GameRegistry.registerBlock(Roads.roadBlockYSSL, "roadBlockYSSL");
            GameRegistry.registerBlock(Roads.roadBlockJO, "roadBlockJO");
            GameRegistry.registerBlock(Roads.roadBlockJI, "roadBlockJI");
            GameRegistry.registerBlock(Roads.roadBlockJOL, "roadBlockJOL");
            GameRegistry.registerBlock(Roads.roadBlockJIL, "roadBlockJIL");
            GameRegistry.registerBlock(Roads.roadBlockJOIL, "roadBlockJOIL");
            GameRegistry.registerBlock(Roads.roadBlockJIIL, "roadBlockJIIL");
            GameRegistry.registerBlock(Roads.roadBlockAL, "roadBlockAL");
            GameRegistry.registerBlock(Roads.roadBlockAUS, "roadBlockAUS");
            GameRegistry.registerBlock(Roads.roadBlockAUL, "roadBlockAUL");
            GameRegistry.registerBlock(Roads.roadBlockAUR, "roadBlockAUR");
            GameRegistry.registerBlock(Roads.roadBlockWDS, "roadBlockWDS");
            GameRegistry.registerBlock(Roads.roadBlockYDS, "roadBlockYDS");
            GameRegistry.registerBlock(Roads.roadBlockWSS, "roadBlockWSS");
            GameRegistry.registerBlock(Roads.roadBlockYSS, "roadBlockYSS");
            GameRegistry.registerBlock(Roads.roadBlockSL, "roadBlockSL");
            GameRegistry.registerBlock(Roads.roadBlockOW, "roadBlockOW");
            GameRegistry.registerBlock(Roads.roadBlockST, "roadBlockST");
            GameRegistry.registerBlock(Roads.roadBlockOP, "roadBlockOP");
            GameRegistry.registerBlock(Roads.roadBlockStop, "roadBlockStop");
            GameRegistry.registerBlock(Roads.roadBlockJCUK, "roadBlockJCUK");
            GameRegistry.registerBlock(Roads.roadBlockJCUSA, "roadBlockJCUSA");
            
            GameRegistry.registerBlock(Roads.powerPole, "powerPole");
            GameRegistry.registerBlock(Roads.roadPainter, "roadPainter");
            GameRegistry.registerBlock(Roads.trafficLight, "trafficLight");
            GameRegistry.registerBlock(Roads.trafficHanging, "trafficHanging");
            GameRegistry.registerBlock(Roads.lightBollard, "lightBollard");
            GameRegistry.registerBlock(Roads.streetLamp1, "streetLamp1");
            GameRegistry.registerBlock(Roads.streetLamp2, "streetLamp2");
            GameRegistry.registerBlock(Roads.streetSign, "streetSign");
            GameRegistry.registerBlock(Roads.barrierPole, "barrierPole");
            GameRegistry.registerBlock(Roads.barrierBlock, "barrierBlock");
            GameRegistry.registerBlock(Roads.barrierCorner, "barrierCorner");
            GameRegistry.registerBlock(Roads.roadSlope1, "roadSlope1");
            GameRegistry.registerBlock(Roads.roadSlope2, "roadSlope2");
            GameRegistry.registerBlock(Roads.roadSlope3, "roadSlope3");
            GameRegistry.registerBlock(Roads.roadSlope4, "roadSlope4");
            GameRegistry.registerBlock(Roads.roadSlope5, "roadSlope5");
            GameRegistry.registerBlock(Roads.roadSlope6, "roadSlope6");
            GameRegistry.registerBlock(Roads.roadSlope7, "roadSlope7");
            GameRegistry.registerBlock(Roads.roadSlope8, "roadSlopeHalf");
            GameRegistry.registerBlock(Roads.roadSign, "roadSign");
            
            GameRegistry.registerItem(Roads.cementItem, "cementItem");
            GameRegistry.registerItem(Roads.cementDustItem, "cementDustItem");
            GameRegistry.registerItem(Roads.limeStonePowderItem, "limeStonePowderItem");
            GameRegistry.registerItem(Roads.limeClayPowderItem, "limeClayPowderItem");
            GameRegistry.registerItem(Roads.tarBucketItem, "tarBucketItem");
            
            
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlock, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.cementBlock, "pickaxe", 2);
            MinecraftForge.setBlockHarvestLevel(Roads.macadamBlock, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.hardenedTarBlock, "pickaxe", 4);
            MinecraftForge.setBlockHarvestLevel(Roads.sidewalkBlocks, "pickaxe", 4); 
            MinecraftForge.setBlockHarvestLevel(Roads.sidewalkKerbed, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.sidewalkKerbed1, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.sidewalkKerbed2, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.sidewalkKerbedInnerCorner, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.sidewalkKerbed1InnerCorner, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.sidewalkKerbed2InnerCorner, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.sidewalkKerbedOuterCorner, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.sidewalkKerbed1OuterCorner, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.sidewalkKerbed2OuterCorner, "pickaxe", 1);
            
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockSWS2, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockDYSEL, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockDYSER, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockDYSI, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockDYSO, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockDYS, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockSYS, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockSWS, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockSWC, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockSYC, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockDYC, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockWhiteCross, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockYellowCross, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockWhiteFull, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockYellowFull, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockWC, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockFYS, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockYC, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockYAC, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockWAC, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockWSL, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockWSSL, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockYSSL, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockJO, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockJI, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockJOL, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockJIL, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockJOIL, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockJIIL, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockAL, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockAUS, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockAUL, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockAUR, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockWDS, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockYDS, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockWSS, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockYSS, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockSL, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockOW, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockST, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockOP, "pickaxe", 1);    
        }
        
        public void addNames() {
        	LanguageRegistry.addName(Roads.limeStoneBlock, "Limestone");
            LanguageRegistry.addName(Roads.hardenedTarBlock, "Hardened Tar");
            LanguageRegistry.addName(Roads.roadBlock, "Tarmac (Unpainted)");
            LanguageRegistry.addName(Roads.cementBlock, "Cement");
            LanguageRegistry.addName(Roads.macadamBlock, "Macadam");
            LanguageRegistry.addName(Roads.roadsTarFlowing, "Tar");
            LanguageRegistry.addName(Roads.sidewalkKerbed, "Sidewalk (Kerb)");
            LanguageRegistry.addName(Roads.sidewalkKerbed1, "Sidewalk (Kerb)");
            LanguageRegistry.addName(Roads.sidewalkKerbed2, "Sidewalk (Kerb)");
            LanguageRegistry.addName(Roads.sidewalkKerbedInnerCorner, "Sidewalk (Kerb, Inner Corner)");
            LanguageRegistry.addName(Roads.sidewalkKerbed1InnerCorner, "Sidewalk (Kerb, Inner Corner)");
            LanguageRegistry.addName(Roads.sidewalkKerbed2InnerCorner, "Sidewalk (Kerb, Inner Corner)");
            LanguageRegistry.addName(Roads.sidewalkKerbedOuterCorner, "Sidewalk (Kerb, Outer Corner)");
            LanguageRegistry.addName(Roads.sidewalkKerbed1OuterCorner, "Sidewalk (Kerb, Outer Corner)");
            LanguageRegistry.addName(Roads.sidewalkKerbed2OuterCorner, "Sidewalk (Kerb, Outer Corner)");
            LanguageRegistry.addName(Roads.catsEye, "Cat's Eye");
            LanguageRegistry.addName(Roads.catsEyeSide, "Cat's Eye (Side)");
            LanguageRegistry.addName(new ItemStack(Roads.sidewalkBlocks, 1, 0), "Sidewalk 1");
            LanguageRegistry.addName(new ItemStack(Roads.sidewalkBlocks, 1, 1), "Sidewalk 2");
            LanguageRegistry.addName(new ItemStack(Roads.sidewalkBlocks, 1, 2), "Sidewalk 3");
            LanguageRegistry.addName(new ItemStack(Roads.sidewalkBlocks, 1, 3), "Sidewalk 4");

            LanguageRegistry.addName(Roads.roadBlockDYS, "Tarmac (Double Yellow Stripe)");
            LanguageRegistry.addName(Roads.roadBlockSYS, "Tarmac (Single Yellow Stripe)");
            LanguageRegistry.addName(Roads.roadBlockSWS, "Tarmac (Single White Stripe)");
            LanguageRegistry.addName(Roads.roadBlockSWC, "Tarmac (Single White Stripe Center)");
            LanguageRegistry.addName(Roads.roadBlockSYC, "Tarmac (Single Yellow Stripe Center)");
            LanguageRegistry.addName(Roads.roadBlockDYC, "Tarmac (Double Yellow Stripe Center)");
            LanguageRegistry.addName(Roads.roadBlockSWS2, "Tarmac (Side White Stripe)");
            LanguageRegistry.addName(Roads.roadBlockDYSEL, "Tarmac (Double Yellow Stripe End)");
            LanguageRegistry.addName(Roads.roadBlockDYSER, "Tarmac (Double Yellow Stripe End)");
            LanguageRegistry.addName(Roads.roadBlockDYSI, "Tarmac (Double Yellow Stripe Inner Corner)");
            LanguageRegistry.addName(Roads.roadBlockDYSO, "Tarmac (Double Yellow Stripe Outer Corner)");
            LanguageRegistry.addName(Roads.roadBlockWhiteCross, "Tarmac (White Cross)");
            LanguageRegistry.addName(Roads.roadBlockYellowCross, "Tarmac (Yellow Cross)");
            LanguageRegistry.addName(Roads.roadBlockWhiteFull, "Tarmac (White Top)");
            LanguageRegistry.addName(Roads.roadBlockYellowFull, "Tarmac (Yellow Top)");
            LanguageRegistry.addName(Roads.roadBlockWC, "Tarmac (White Corner)");
            LanguageRegistry.addName(Roads.roadBlockFYS, "Tarmac (Side Yellow Stripe)");
            LanguageRegistry.addName(Roads.roadBlockYC, "Tarmac (Yellow Corner)");
            LanguageRegistry.addName(Roads.roadBlockYAC, "Tarmac (Yellow Alt. Corner)");
            LanguageRegistry.addName(Roads.roadBlockWAC, "Tarmac (White Alt. Corner)");
            LanguageRegistry.addName(Roads.roadBlockWSL, "Tarmac (White Striped Line)");
            LanguageRegistry.addName(Roads.roadBlockWSSL, "Tarmac (White Side Striped Line)");
            LanguageRegistry.addName(Roads.roadBlockYSSL, "Tarmac (Yellow Side Striped Line)");
            LanguageRegistry.addName(Roads.roadBlockJO, "Tarmac Junction (Out)");
            LanguageRegistry.addName(Roads.roadBlockJI, "Tarmac Junction (In)");
            LanguageRegistry.addName(Roads.roadBlockJOL, "Tarmac Junction (Out, Line)");
            LanguageRegistry.addName(Roads.roadBlockJIL, "Tarmac Junction (In, Line)");
            LanguageRegistry.addName(Roads.roadBlockJOIL, "Tarmac Junction (Out, Inverted Line)");
            LanguageRegistry.addName(Roads.roadBlockJIIL, "Tarmac Junction (In, Inverted Line)");
            LanguageRegistry.addName(Roads.roadBlockAL, "Tarmac Arrow (Line)");
            LanguageRegistry.addName(Roads.roadBlockAUS, "Tarmac Arrow (Straight)");
            LanguageRegistry.addName(Roads.roadBlockAUL, "Tarmac Arrow (Left)");
            LanguageRegistry.addName(Roads.roadBlockAUR, "Tarmac Arrow (Right)");
            LanguageRegistry.addName(Roads.roadBlockWDS, "Tarmac (White Diagonal Stripe)");
            LanguageRegistry.addName(Roads.roadBlockYDS, "Tarmac (Yellow Diagonal Stripe)");
            LanguageRegistry.addName(Roads.roadBlockWSS, "Tarmac (White Small Square)");
            LanguageRegistry.addName(Roads.roadBlockYSS, "Tarmac (Yellow Small Square)");
            LanguageRegistry.addName(Roads.roadBlockST, "Tarmac (Stop 1)");
            LanguageRegistry.addName(Roads.roadBlockOP, "Tarmac (Stop 2)");
            LanguageRegistry.addName(Roads.roadBlockSL, "Tarmac (Slow 1)");
            LanguageRegistry.addName(Roads.roadBlockOW, "Tarmac (Slow 2)");
            LanguageRegistry.addName(Roads.roadBlockStop, "Stop Paint");
            LanguageRegistry.addName(Roads.roadBlockJCUK, "Junction Center (Leftie)");
            LanguageRegistry.addName(Roads.roadBlockJCUSA, "Junction Center (Rightie)");
                        
            LanguageRegistry.addName(Roads.powerPole, "Redstone Post");
            LanguageRegistry.addName(Roads.roadPainter, "Road Painter");
            LanguageRegistry.addName(Roads.trafficLight, "Traffic Light");
            LanguageRegistry.addName(Roads.trafficHanging, "Traffic Light (Hanging)");
            LanguageRegistry.addName(Roads.lightBollard, "Light Bollard");
            LanguageRegistry.addName(Roads.streetLamp1, "Street Lamp (Single)");
            LanguageRegistry.addName(Roads.streetLamp2, "Street Lamp (Double)");
            LanguageRegistry.addName(Roads.streetSign, "Street Sign");
            LanguageRegistry.addName(Roads.barrierPole, "Barrier (Post)");
            LanguageRegistry.addName(Roads.barrierBlock, "Barrier");
            LanguageRegistry.addName(Roads.barrierCorner, "Barrier (Corner)");
            LanguageRegistry.addName(Roads.roadSlope1, "Road Slope Plain");
            LanguageRegistry.addName(Roads.roadSlope2, "Road Slope Double Yellow Stripe");
            LanguageRegistry.addName(Roads.roadSlope3, "Road Slope Single White Stripe");
            LanguageRegistry.addName(Roads.roadSlope4, "Road Slope Side White Stripe");
            LanguageRegistry.addName(Roads.roadSlope5, "Road Slope Small White Stripe");
            LanguageRegistry.addName(Roads.roadSlope6, "Road Slope Kerb (Right)");
            LanguageRegistry.addName(Roads.roadSlope7, "Road Slope Kerb (Left)");
            LanguageRegistry.addName(Roads.roadSlope8, "Road Slope Half");
            LanguageRegistry.addName(Roads.roadSign, "Street Sign");
            
            LanguageRegistry.addName(Roads.cementItem, "Cement");
            LanguageRegistry.addName(Roads.cementDustItem, "Cement Dust");
            LanguageRegistry.addName(Roads.limeStonePowderItem, "Limestone Dust");
            LanguageRegistry.addName(Roads.limeClayPowderItem, "Lime & Clay Dust Mix");
            LanguageRegistry.addName(Roads.tarBucketItem, "Bucket of Tar");
        }
        
        public void addRecipes() {
        	
        }
}