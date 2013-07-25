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
            GameRegistry.registerBlock(Roads.roadBlockDirt, ItemRoadBlockDirt.class, "FlenixRoads" + (Roads.roadBlockDirt.getUnlocalizedName().substring(5)));
            GameRegistry.registerBlock(Roads.roadBlockDirtCorner, ItemRoadBlockDirtCorner.class, "FlenixRoads" + (Roads.roadBlockDirtCorner.getUnlocalizedName().substring(5)));
            GameRegistry.registerBlock(Roads.roadBlockFork, ItemRoadBlockFork.class, "FlenixRoads" + (Roads.roadBlockFork.getUnlocalizedName().substring(5)));
            GameRegistry.registerBlock(Roads.roadBlockForkB, ItemRoadBlockForkB.class, "FlenixRoads" + (Roads.roadBlockForkB.getUnlocalizedName().substring(5)));
            GameRegistry.registerBlock(Roads.roadBlockForkC, ItemRoadBlockForkC.class, "FlenixRoads" + (Roads.roadBlockForkC.getUnlocalizedName().substring(5)));
            GameRegistry.registerBlock(Roads.roadBlockForkD, ItemRoadBlockForkD.class, "FlenixRoads" + (Roads.roadBlockForkD.getUnlocalizedName().substring(5)));
            GameRegistry.registerBlock(Roads.roadBlockForkE, ItemRoadBlockForkE.class, "FlenixRoads" + (Roads.roadBlockForkE.getUnlocalizedName().substring(5)));
            GameRegistry.registerBlock(Roads.roadBlockForkF, ItemRoadBlockForkF.class, "FlenixRoads" + (Roads.roadBlockForkF.getUnlocalizedName().substring(5)));
            GameRegistry.registerBlock(Roads.roadBlockForkG, ItemRoadBlockForkG.class, "FlenixRoads" + (Roads.roadBlockForkG.getUnlocalizedName().substring(5)));
            
            GameRegistry.registerBlock(Roads.sidewalkBlockGrey, ItemSidewalkBlockGrey.class, "FlenixRoads" + (Roads.sidewalkBlockGrey.getUnlocalizedName().substring(5)));
            GameRegistry.registerBlock(Roads.sidewalkBlockLight, ItemSidewalkBlockLight.class, "FlenixRoads" + (Roads.sidewalkBlockLight.getUnlocalizedName().substring(5)));
            GameRegistry.registerBlock(Roads.sidewalkBlockTile, ItemSidewalkBlockTile.class, "FlenixRoads" + (Roads.sidewalkBlockTile.getUnlocalizedName().substring(5)));
            GameRegistry.registerBlock(Roads.sidewalkBlockTri, ItemSidewalkBlockTri.class, "FlenixRoads" + (Roads.sidewalkBlockTri.getUnlocalizedName().substring(5)));
            GameRegistry.registerBlock(Roads.sidewalkBlockSides, ItemSidewalkBlockSides.class, "FlenixRoads" + (Roads.sidewalkBlockSides.getUnlocalizedName().substring(5)));
            
            GameRegistry.registerBlock(Roads.roadRamp1, ItemRoadRamp1.class, "FleixRoads" + (Roads.roadRamp1.getUnlocalizedName().substring(5)));
            GameRegistry.registerBlock(Roads.roadRamp2, ItemRoadRamp2.class, "FleixRoads" + (Roads.roadRamp2.getUnlocalizedName().substring(5)));
            GameRegistry.registerBlock(Roads.streetSign, ItemStreetSign.class, "FlenixRoads" + (Roads.streetSign.getUnlocalizedName().substring(5)));
            GameRegistry.registerBlock(Roads.trafficLight, ItemTrafficLight.class, "FlenixRoads" + (Roads.trafficLight.getUnlocalizedName().substring(5)));
            
            GameRegistry.registerBlock(Roads.powerPole, "powerPole");
            GameRegistry.registerBlock(Roads.powerPoleSmall, "powerPoleSmall");
            GameRegistry.registerBlock(Roads.powerPoleLarge, "powerPoleLarge");
            GameRegistry.registerBlock(Roads.powerPoleOn, "powerPoleOn");
            GameRegistry.registerBlock(Roads.powerPoleSmallOn, "powerPoleSmallOn");
            GameRegistry.registerBlock(Roads.powerPoleLargeOn, "powerPoleLargeOn");
            
            GameRegistry.registerBlock(Roads.blockGag1, "blockGag1");
            GameRegistry.registerBlock(Roads.blockGag2, "blockGag2");
            GameRegistry.registerBlock(Roads.blockGag3, "blockGag3");
            
            GameRegistry.registerBlock(Roads.roadPainter, "roadPainter");
            GameRegistry.registerBlock(Roads.lightBollard, "lightBollard");
            GameRegistry.registerBlock(Roads.streetLamp1, "streetLamp1");
            GameRegistry.registerBlock(Roads.streetLamp2, "streetLamp2");
            GameRegistry.registerBlock(Roads.barrierPole, "barrierPole");
            GameRegistry.registerBlock(Roads.barrierBlock, "barrierBlock");
            GameRegistry.registerBlock(Roads.barrierCorner, "barrierCorner");
            //GameRegistry.registerBlock(Roads.roadSign, "roadSign");
            GameRegistry.registerBlock(Roads.roadBarrier, "roadBarrier");
            
            GameRegistry.registerItem(Roads.cementItem, "cementItem");
            GameRegistry.registerItem(Roads.cementDustItem, "cementDustItem");
            GameRegistry.registerItem(Roads.limeStonePowderItem, "limeStonePowderItem");
            GameRegistry.registerItem(Roads.limeClayPowderItem, "limeClayPowderItem");
            GameRegistry.registerItem(Roads.tarBucketItem, "tarBucketItem");
            GameRegistry.registerItem(Roads.whitePaintBlob, "whitePaintBlob");
            GameRegistry.registerItem(Roads.yellowPaintBlob, "yellowPaintBlob");
            GameRegistry.registerItem(Roads.whitePaintCan, "whitePaintCan");
            GameRegistry.registerItem(Roads.yellowPaintCan, "yellowPaintCan");

            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockArrows, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(Roads.roadBlockDoubleYellow, "pickaxe", 1);
        }
        
        public void addNames() {
        	LanguageRegistry.addName(Roads.limeStoneBlock, "Limestone");
            LanguageRegistry.addName(Roads.roadsTarFlowing, "Tar");
            LanguageRegistry.addName(Roads.catsEye, "Cat's Eye");
            LanguageRegistry.addName(Roads.catsEyeSide, "Cat's Eye (Side)");
            
            LanguageRegistry.addName(new ItemStack(Roads.generalBlocks, 1, 0), "Tarmac");
            LanguageRegistry.addName(new ItemStack(Roads.generalBlocks, 1, 1), "Cement");
            LanguageRegistry.addName(new ItemStack(Roads.generalBlocks, 1, 2), "Macadam");
            LanguageRegistry.addName(new ItemStack(Roads.generalBlocks, 1, 3), "Hardened Tar");
            LanguageRegistry.addName(new ItemStack(Roads.generalBlocks, 1, 4), "Concrete");
            
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockArrows, 1, 0), "Tarmac (Arrow, Straight)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockArrows, 1, 4), "Tarmac (Arrow, Left)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockArrows, 1, 8), "Tarmac (Arrow, Right)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockArrows, 1, 12), "Tarmac (Arrow, Line)");
            
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockCorners, 1, 0), "Tarmac (Double Yellow Corner Large)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockCorners, 1, 4), "Tarmac (Double Yellow Corner Small)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockCorners, 1, 8), "Tarmac (Inverted Diagonal White Stripe)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockCorners, 1, 12), "Tarmac (Inverted Diagonal Yellow Stripe)");
            
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockDoubleYellow, 1, 0), "Tarmac (Double Yellow Stripes)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockDoubleYellow, 1, 4), "Tarmac (Double Yellow End, Left)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockDoubleYellow, 1, 8), "Tarmac (Double Yellow End, Right");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockDoubleYellow, 1, 12), "Tarmac (Double Yellow Center)");
            
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockCornerB, 1, 0), "Tarmac (White Corner)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockCornerB, 1, 4), "Tarmac (White Alternate Corner)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockCornerB, 1, 8), "Tarmac (Yellow Corner)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockCornerB, 1, 12), "Tarmac (Yellow Alternate Corner)");
            
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockJunctionIn, 1, 0), "Tarmac (Junction In)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockJunctionIn, 1, 4), "Tarmac (Junction In, Line)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockJunctionIn, 1, 8), "Tarmac (Junction In, Line Inverted)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockJunctionIn, 1, 12), "Tarmac (Junction Center Left)");
             
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockJunctionOut, 1, 0), "Tarmac (Junction Out)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockJunctionOut, 1, 4), "Tarmac (Junction Out, Line)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockJunctionOut, 1, 8), "Tarmac (Junction Out, Line Inverted)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockJunctionOut, 1, 12), "Tarmac (Junction Center Right)");
            
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockMiscSingles, 1, 0), "Tarmac (Plain)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockMiscSingles, 1, 1), "Tarmac (White Topped)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockMiscSingles, 1, 2), "Tarmac (Yellow Topped)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockMiscSingles, 1, 3), "Tarmac (White Cross)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockMiscSingles, 1, 4), "Tarmac (Yellow Cross)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockMiscSingles, 1, 5), "Dirt Track Road");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockMiscSingles, 1, 6), "Dirt Track Road, Dry");
                    		
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockSimpleLines, 1, 0), "Tarmac (White Center)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockSimpleLines, 1, 4), "Tarmac (Yellow Center)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockSimpleLines, 1, 8), "Tarmac (White Diagonal)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockSimpleLines, 1, 12), "Tarmac (Yellow Diagonal)");
            		
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockSideWhiteStripes, 1, 0), "Tarmac (White Small Stripe, Side)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockSideWhiteStripes, 1, 4), "Tarmac (White Small Stripe)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockSideWhiteStripes, 1, 8), "Tarmac (Yellow Small Stripe, Side)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockSideWhiteStripes, 1, 12), "Tarmac (Yellow Small Stripe)");
                                            		
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockStripes, 1, 0), "Tarmac (White Side)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockStripes, 1, 4), "Tarmac (White Far Side)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockStripes, 1, 8), "Tarmac (Yellow Side)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockStripes, 1, 12), "Tarmac (Yellow Far Side)");
            
            LanguageRegistry.addName(new ItemStack(Roads.sidewalkBlockGrey, 1, 0), "Sidewalk Grey");
            LanguageRegistry.addName(new ItemStack(Roads.sidewalkBlockGrey, 1, 4), "Sidewalk Grey (Kerbed)");
            LanguageRegistry.addName(new ItemStack(Roads.sidewalkBlockGrey, 1, 8), "Sidewalk Grey (Kerbed, Inner Corner)");
            LanguageRegistry.addName(new ItemStack(Roads.sidewalkBlockGrey, 1, 12), "Sidewalk Grey (Kerbed, Outer Corner)");
            								
            LanguageRegistry.addName(new ItemStack(Roads.sidewalkBlockLight, 1, 0), "Sidewalk Light");
            LanguageRegistry.addName(new ItemStack(Roads.sidewalkBlockLight, 1, 4), "Sidewalk Light (Kerbed)");
            LanguageRegistry.addName(new ItemStack(Roads.sidewalkBlockLight, 1, 8), "Sidewalk Light (Kerbed, Inner Corner)");
            LanguageRegistry.addName(new ItemStack(Roads.sidewalkBlockLight, 1, 12), "Sidewalk Light (Kerbed, Outer Corner)");
            																
            LanguageRegistry.addName(new ItemStack(Roads.sidewalkBlockTile, 1, 0), "Sidewalk Tile");
            LanguageRegistry.addName(new ItemStack(Roads.sidewalkBlockTile, 1, 4), "Sidewalk Tile (Kerbed)");
            LanguageRegistry.addName(new ItemStack(Roads.sidewalkBlockTile, 1, 8), "Sidewalk Tile (Kerbed, Inner Corner)");
            LanguageRegistry.addName(new ItemStack(Roads.sidewalkBlockTile, 1, 12), "Sidewalk Tile (Kerbed, Outer Corner)");
            
            LanguageRegistry.addName(new ItemStack(Roads.sidewalkBlockSides, 1, 0), "Sidewalk Sides, Grey");
            LanguageRegistry.addName(new ItemStack(Roads.sidewalkBlockSides, 1, 4), "Sidewalk Sides, Light");
            LanguageRegistry.addName(new ItemStack(Roads.sidewalkBlockSides, 1, 8), "Sidewalk Small Sides, Grey");
            LanguageRegistry.addName(new ItemStack(Roads.sidewalkBlockSides, 1, 12), "Sidewalk Small Sides, Light");
            
            LanguageRegistry.addName(new ItemStack(Roads.sidewalkBlockTri, 1, 0), "Sidewalk Tri-side, Grey");
            LanguageRegistry.addName(new ItemStack(Roads.sidewalkBlockTri, 1, 4), "Sidewalk Tri-Side, Light");
            LanguageRegistry.addName(new ItemStack(Roads.sidewalkBlockTri, 1, 8), "Sidewalk Tri-Side, Tile");
            
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockDirt, 1, 0), "Dirt, Tyre Treads");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockDirt, 1, 4), "Dirt, Line");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockDirt, 1, 8), "Dirt, Worn");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockDirt, 1, 12), "Dirt, Grassy");
            
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockDirtCorner, 1, 0), "Dirt, Tyre Treads (Corner)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockDirtCorner, 1, 4), "Dirt, Line (Corner)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockDirtCorner, 1, 8), "Dirt, Worn (Corner)");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockDirtCorner, 1, 12), "Dirt, Grassy (Corner)");
            
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockFork, 1, 0), "Road Fork, Base");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockFork, 1, 4), "Road Fork, Base 2");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockFork, 1, 8), "Road Fork, Base Left");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockFork, 1, 12), "Road Fork, Base Right");
            
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkB, 1, 0), "Road Fork, Left 1");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkB, 1, 4), "Road Fork, Left 2");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkB, 1, 8), "Road Fork, Left 3");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkB, 1, 12), "Road Fork, Left 4");
            
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkC, 1, 0), "Road Fork, Right 1");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkC, 1, 4), "Road Fork, Right 2");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkC, 1, 8), "Road Fork, Right 3");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkC, 1, 12), "Road Fork, Right 4");
            
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkD, 1, 0), "Road Fork, Center 1");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkD, 1, 4), "Road Fork, Left Plain");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkD, 1, 8), "Road Fork, Center 2");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkD, 1, 12), "Road Fork, Right Plain");
            
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkE, 1, 0), "Road Fork, Left Inverted 1");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkE, 1, 4), "Road Fork, Left Inverted 2");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkE, 1, 8), "Road Fork, Left Inverted 3");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkE, 1, 12), "Road Fork, Left Inverted 4");
            
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkF, 1, 0), "Road Fork, Right Inverted 1");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkF, 1, 4), "Road Fork, Right Inverted 2");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkF, 1, 8), "Road Fork, Right Inverted 3");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkF, 1, 12), "Road Fork, Right Inverted 4");
            
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkG, 1, 0), "Road Fork, Straight 1");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkG, 1, 4), "Road Fork, Straight 2");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkG, 1, 8), "Road Fork, Straight 3");
            LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkG, 1, 12), "Road Fork, Straight 4");
            
            LanguageRegistry.addName(new ItemStack(Roads.roadRamp1, 1, 0), "Tarmac Ramp (Plain)");
            LanguageRegistry.addName(new ItemStack(Roads.roadRamp1, 1, 4), "Tarmac Ramp ()");
            LanguageRegistry.addName(new ItemStack(Roads.roadRamp1, 1, 8), "Tarmac Ramp (White Stripe)");
            LanguageRegistry.addName(new ItemStack(Roads.roadRamp1, 1, 12), "Tarmac Ramp ()");
            								
            LanguageRegistry.addName(new ItemStack(Roads.roadRamp2, 1, 0), "Tarmac Ramp (Double Yellow Left)");
            LanguageRegistry.addName(new ItemStack(Roads.roadRamp2, 1, 4), "Tarmac Ramp (Double Yellow Right)");
            LanguageRegistry.addName(new ItemStack(Roads.roadRamp2, 1, 8), "Tarmac Ramp (White Stripe Left)");
            LanguageRegistry.addName(new ItemStack(Roads.roadRamp2, 1, 12), "Tarmac Ramp (White Stripe Right)");
            
            LanguageRegistry.addName(new ItemStack(Roads.trafficLight, 1, 0), "Traffic Light (Red)");
            LanguageRegistry.addName(new ItemStack(Roads.trafficLight, 1, 4), "Traffic Light (Green)");
            LanguageRegistry.addName(new ItemStack(Roads.trafficLight, 1, 8), "Traffic Light (Red/Amber)");
            LanguageRegistry.addName(new ItemStack(Roads.trafficLight, 1, 12), "Traffic Light (Amber)");
            
            LanguageRegistry.addName(new ItemStack(Roads.streetSign, 1, 0), "Street Sign (30)");
            LanguageRegistry.addName(new ItemStack(Roads.streetSign, 1, 4), "Street Sign (40)");
            LanguageRegistry.addName(new ItemStack(Roads.streetSign, 1, 8), "Street Sign (50)");
            LanguageRegistry.addName(new ItemStack(Roads.streetSign, 1, 12), "Street Sign (60)");
            		
            LanguageRegistry.addName(Roads.powerPole, "Street Pole (Medium)");
            LanguageRegistry.addName(Roads.powerPoleSmall, "Street Pole (Small)");
            LanguageRegistry.addName(Roads.powerPoleLarge, "Street Pole (Large)");
            LanguageRegistry.addName(Roads.powerPoleOn, "Street Pole (Activated, Medium)");
            LanguageRegistry.addName(Roads.powerPoleSmallOn, "Street Pole (Activated, Small)");
            LanguageRegistry.addName(Roads.powerPoleLargeOn, "Street Pole (Activated, Large)");
            
            LanguageRegistry.addName(Roads.roadPainter, "Road Painter");
            LanguageRegistry.addName(Roads.trafficLight, "Traffic Light");
            LanguageRegistry.addName(Roads.lightBollard, "Light Bollard");
            LanguageRegistry.addName(Roads.streetLamp1, "Street Lamp (Single)");
            LanguageRegistry.addName(Roads.streetLamp2, "Street Lamp (Double)");
            LanguageRegistry.addName(Roads.streetSign, "Street Sign");
            LanguageRegistry.addName(Roads.barrierPole, "Barrier (Post)");
            LanguageRegistry.addName(Roads.barrierBlock, "Barrier");
            LanguageRegistry.addName(Roads.barrierCorner, "Barrier (Corner)");
            //LanguageRegistry.addName(Roads.roadSign, "Street Sign");
            LanguageRegistry.addName(Roads.roadBarrier, "Road Barrier");
            
            LanguageRegistry.addName(Roads.cementItem, "Cement");
            LanguageRegistry.addName(Roads.cementDustItem, "Cement Dust");
            LanguageRegistry.addName(Roads.limeStonePowderItem, "Limestone Dust");
            LanguageRegistry.addName(Roads.limeClayPowderItem, "Lime & Clay Dust Mix");
            LanguageRegistry.addName(Roads.tarBucketItem, "Bucket of Tar");
            LanguageRegistry.addName(Roads.whitePaintBlob, "Paint Blob");
            LanguageRegistry.addName(Roads.yellowPaintBlob, "Paint Blob");
            LanguageRegistry.addName(Roads.whitePaintCan, "Paint Can");
       		LanguageRegistry.addName(Roads.yellowPaintCan, "Paint Can");
        }
        
        public void addRecipes() {
        	
        }
}