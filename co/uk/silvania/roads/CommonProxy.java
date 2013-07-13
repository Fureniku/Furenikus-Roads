package co.uk.silvania.roads;

import co.uk.silvania.roads.block.ItemGeneralBlocks;
import co.uk.silvania.roads.roadblocks.itemblocks.*;
import co.uk.silvania.roads.tileentities.entities.TileEntityTrafficLightEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class CommonProxy {
        
        public void registerRenderThings() {
        }
        
        public void registerBlocks() {
            GameRegistry.registerBlock(Roads.limeStoneBlock, "limeStoneBlock");
            GameRegistry.registerBlock(Roads.roadsTarStill, "roadsTarStill");
            GameRegistry.registerBlock(Roads.roadsTarFlowing, "roadsTarFlowing");
            GameRegistry.registerBlock(Roads.catsEye, "catsEye");
            GameRegistry.registerBlock(Roads.catsEyeSide, "catsEyeSide");
            GameRegistry.registerBlock(Roads.generalBlocks, ItemGeneralBlocks.class, "FlenixRoads" + (Roads.generalBlocks.getUnlocalizedName().substring(5)));
            
            GameRegistry.registerBlock(Roads.roadBlockArrows, ItemRoadBlockArrows.class, "FlenixRoads" + (Roads.roadBlockArrows.getUnlocalizedName().substring(5)));
            GameRegistry.registerBlock(Roads.roadBlockCorners, ItemRoadBlockCorners.class, "FlenixRoads" + (Roads.roadBlockCorners.getUnlocalizedName().substring(5)));
            GameRegistry.registerBlock(Roads.roadBlockDoubleYellow, ItemRoadBlockDoubleYellow.class, "FlenixRoads" + (Roads.roadBlockDoubleYellow.getUnlocalizedName().substring(5)));
            GameRegistry.registerBlock(Roads.roadBlockCornerB, ItemRoadBlockCornerB.class, "FlenixRoads" + (Roads.roadBlockCornerB.getUnlocalizedName().substring(5)));
            GameRegistry.registerBlock(Roads.roadBlockJunctionIn, ItemRoadBlockJunctionIn.class, "FlenixRoads" + (Roads.roadBlockJunctionIn.getUnlocalizedName().substring(5)));
            GameRegistry.registerBlock(Roads.roadBlockJunctionOut, ItemRoadBlockJunctionOut.class, "FlenixRoads" + (Roads.roadBlockJunctionOut.getUnlocalizedName().substring(5)));
            GameRegistry.registerBlock(Roads.roadBlockMiscSingles, ItemRoadBlockMiscSingles.class, "FlenixRoads" + (Roads.roadBlockMiscSingles.getUnlocalizedName().substring(5)));
            GameRegistry.registerBlock(Roads.roadBlockSimpleLines, ItemRoadBlockSimpleLines.class, "FlenixRoads" + (Roads.roadBlockSimpleLines.getUnlocalizedName().substring(5)));
            GameRegistry.registerBlock(Roads.roadBlockSideWhiteStripes, ItemRoadBlockSideWhiteStripes.class, "FlenixRoads" + (Roads.roadBlockSideWhiteStripes.getUnlocalizedName().substring(5)));
            GameRegistry.registerBlock(Roads.roadBlockStripes, ItemRoadBlockStripes.class, "FlenixRoads" + (Roads.roadBlockStripes.getUnlocalizedName().substring(5)));
            
            GameRegistry.registerBlock(Roads.sidewalkBlockGrey, ItemSidewalkBlockGrey.class, "FlenixRoads" + (Roads.sidewalkBlockGrey.getUnlocalizedName().substring(5)));
            GameRegistry.registerBlock(Roads.sidewalkBlockLight, ItemSidewalkBlockLight.class, "FlenixRoads" + (Roads.sidewalkBlockLight.getUnlocalizedName().substring(5)));
            GameRegistry.registerBlock(Roads.sidewalkBlockTile, ItemSidewalkBlockTile.class, "FlenixRoads" + (Roads.sidewalkBlockTile.getUnlocalizedName().substring(5)));
            
            GameRegistry.registerBlock(Roads.roadRamp1, ItemRoadRamp1.class, "FleixRoads" + (Roads.roadRamp1.getUnlocalizedName().substring(5)));
            GameRegistry.registerBlock(Roads.roadRamp2, ItemRoadRamp2.class, "FleixRoads" + (Roads.roadRamp2.getUnlocalizedName().substring(5)));
            
            GameRegistry.registerBlock(Roads.powerPole, "powerPole");
            GameRegistry.registerBlock(Roads.powerPoleSmall, "powerPoleSmall");
            
            GameRegistry.registerBlock(Roads.roadPainter, "roadPainter");
            GameRegistry.registerBlock(Roads.trafficLight, "trafficLight");
            GameRegistry.registerBlock(Roads.lightBollard, "lightBollard");
            GameRegistry.registerBlock(Roads.streetLamp1, "streetLamp1");
            GameRegistry.registerBlock(Roads.streetLamp2, "streetLamp2");
            GameRegistry.registerBlock(Roads.streetSign, "streetSign");
            GameRegistry.registerBlock(Roads.barrierPole, "barrierPole");
            GameRegistry.registerBlock(Roads.barrierBlock, "barrierBlock");
            GameRegistry.registerBlock(Roads.barrierCorner, "barrierCorner");
            GameRegistry.registerBlock(Roads.roadSign, "roadSign");
            GameRegistry.registerBlock(Roads.roadBarrier, "roadBarrier");
            
            GameRegistry.registerItem(Roads.cementItem, "cementItem");
            GameRegistry.registerItem(Roads.cementDustItem, "cementDustItem");
            GameRegistry.registerItem(Roads.limeStonePowderItem, "limeStonePowderItem");
            GameRegistry.registerItem(Roads.limeClayPowderItem, "limeClayPowderItem");
            GameRegistry.registerItem(Roads.tarBucketItem, "tarBucketItem");

            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockArrows, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockDoubleYellow, "pickaxe", 1);
        }
        
        public void addNames() {
        	LanguageRegistry.addName(Roads.limeStoneBlock, "Limestone");
            LanguageRegistry.addName(Roads.roadsTarFlowing, "Tar");
            LanguageRegistry.addName(Roads.catsEye, "Cat's Eye");
            LanguageRegistry.addName(Roads.catsEyeSide, "Cat's Eye (Side)");
            
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockArrows, 1, 0), "Tarmac (Arrow, Straight)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockArrows, 1, 4), "Tarmac (Arrow, Left)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockArrows, 1, 8), "Tarmac (Arrow, Right)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockArrows, 1, 12), "Tarmac (Arrow, Line)");
            
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockArrows, 1, 0), "Tarmac (Double Yellow Stripes)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockArrows, 1, 4), "Tarmac (Double Yellow End, Left)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockArrows, 1, 8), "Tarmac (Double Yellow End, Right");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockArrows, 1, 12), "Tarmac (Double Yellow Center)");
                        
            LanguageRegistry.addName(Roads.powerPole, "Redstone Post");
            LanguageRegistry.addName(Roads.roadPainter, "Road Painter");
            LanguageRegistry.addName(Roads.trafficLight, "Traffic Light");
            LanguageRegistry.addName(Roads.lightBollard, "Light Bollard");
            LanguageRegistry.addName(Roads.streetLamp1, "Street Lamp (Single)");
            LanguageRegistry.addName(Roads.streetLamp2, "Street Lamp (Double)");
            LanguageRegistry.addName(Roads.streetSign, "Street Sign");
            LanguageRegistry.addName(Roads.barrierPole, "Barrier (Post)");
            LanguageRegistry.addName(Roads.barrierBlock, "Barrier");
            LanguageRegistry.addName(Roads.barrierCorner, "Barrier (Corner)");
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