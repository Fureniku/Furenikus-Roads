package co.uk.silvania.roads;

import co.uk.silvania.roads.block.ItemGeneralBlocks;
import co.uk.silvania.roads.roadblocks.itemblocks.*;
import co.uk.silvania.roads.tileentities.entities.TileEntityTrafficLightEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class CommonProxy {
        
	public boolean banCheck() {
		return false;
	}
	
    public void registerRenderThings() {}
    
    public void registerBlocks() {
    	GameRegistry.registerBlock(Roads.tarBlock, "tarBlock");
    	
        GameRegistry.registerBlock(Roads.limeStoneBlock, "limeStoneBlock");
        GameRegistry.registerBlock(Roads.catsEye, "catsEye");
        GameRegistry.registerBlock(Roads.catsEyeSide, "catsEyeSide");
        GameRegistry.registerBlock(Roads.generalBlocks, ItemGeneralBlocks.class, "FlenixRoads" + (Roads.generalBlocks.getUnlocalizedName().substring(5)));
        
        GameRegistry.registerBlock(Roads.roadBlockArrows, ItemRoadBlockArrows.class, "FlenixRoads" + (Roads.roadBlockArrows.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(Roads.roadBlockCorners, ItemRoadBlockCorners.class, "FlenixRoads" + (Roads.roadBlockCorners.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(Roads.roadBlockDoubleYellow, ItemRoadBlockDoubleYellow.class, "FlenixRoads" + (Roads.roadBlockDoubleYellow.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(Roads.roadBlockCornerB, ItemRoadBlockCornerB.class, "FlenixRoads" + (Roads.roadBlockCornerB.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(Roads.roadBlockCornerC, ItemRoadBlockCornerC.class, "FlenixRoads" + (Roads.roadBlockCornerC.getUnlocalizedName().substring(5)));
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
        //GameRegistry.registerBlock(Roads.roadBlockForkE, ItemRoadBlockForkE.class, "FlenixRoads" + (Roads.roadBlockForkE.getUnlocalizedName().substring(5)));
        //GameRegistry.registerBlock(Roads.roadBlockForkF, ItemRoadBlockForkF.class, "FlenixRoads" + (Roads.roadBlockForkF.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(Roads.roadBlockForkG, ItemRoadBlockForkG.class, "FlenixRoads" + (Roads.roadBlockForkG.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(Roads.roadBlockCenterCorner, ItemRoadBlockCenterCorner.class, "FlenixRoads" + (Roads.roadBlockCenterCorner.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(Roads.roadBlockLettersABCD, ItemRoadBlockLetters1.class, "FlenixRoads" + (Roads.roadBlockLettersABCD.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(Roads.roadBlockLettersEFGH, ItemRoadBlockLetters2.class, "FlenixRoads" + (Roads.roadBlockLettersEFGH.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(Roads.roadBlockLettersIJKL, ItemRoadBlockLetters3.class, "FlenixRoads" + (Roads.roadBlockLettersIJKL.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(Roads.roadBlockLettersMNOP, ItemRoadBlockLetters4.class, "FlenixRoads" + (Roads.roadBlockLettersMNOP.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(Roads.roadBlockLettersQRST, ItemRoadBlockLetters5.class, "FlenixRoads" + (Roads.roadBlockLettersQRST.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(Roads.roadBlockLettersUVWX, ItemRoadBlockLetters6.class, "FlenixRoads" + (Roads.roadBlockLettersUVWX.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(Roads.roadBlockLettersYZ01, ItemRoadBlockLetters7.class, "FlenixRoads" + (Roads.roadBlockLettersYZ01.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(Roads.roadBlockLetters2345, ItemRoadBlockLetters8.class, "FlenixRoads" + (Roads.roadBlockLetters2345.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(Roads.roadBlockLetters6789, ItemRoadBlockLetters9.class, "FlenixRoads" + (Roads.roadBlockLetters6789.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(Roads.roadBlockLettersMisc, ItemRoadBlockLetters10.class, "FlenixRoads" + (Roads.roadBlockLettersMisc.getUnlocalizedName().substring(5)));
        
        GameRegistry.registerBlock(Roads.sidewalkBlockGrey, ItemSidewalkBlockGrey.class, "FlenixRoads" + (Roads.sidewalkBlockGrey.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(Roads.sidewalkBlockLight, ItemSidewalkBlockLight.class, "FlenixRoads" + (Roads.sidewalkBlockLight.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(Roads.sidewalkBlockTile, ItemSidewalkBlockTile.class, "FlenixRoads" + (Roads.sidewalkBlockTile.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(Roads.sidewalkBlockTri, ItemSidewalkBlockTri.class, "FlenixRoads" + (Roads.sidewalkBlockTri.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(Roads.sidewalkBlockSides, ItemSidewalkBlockSides.class, "FlenixRoads" + (Roads.sidewalkBlockSides.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(Roads.kerbBlock, "kerbBlock");
        
        GameRegistry.registerBlock(Roads.roadRamp1, ItemRoadRamp1.class, "FlenixRoads" + (Roads.roadRamp1.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(Roads.roadRamp2, ItemRoadRamp2.class, "FlenixRoads" + (Roads.roadRamp2.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(Roads.roadRamp3, ItemRoadRamp3.class, "FlenixRoads" + (Roads.roadRamp3.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(Roads.roadRamp4, ItemRoadRamp4.class, "FlenixRoads" + (Roads.roadRamp4.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(Roads.roadRamp5, ItemRoadRamp5.class, "FlenixRoads" + (Roads.roadRamp5.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(Roads.roadRamp6, ItemRoadRamp6.class, "FlenixRoads" + (Roads.roadRamp6.getUnlocalizedName().substring(5)));
        
        //GameRegistry.registerBlock(Roads.roadRampy5, "roadRampy5");
        
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
        GameRegistry.registerBlock(Roads.blockGag4, "blockGag4");
        GameRegistry.registerBlock(Roads.blockGag5, "blockGag5");
        
        GameRegistry.registerBlock(Roads.roadPainter, "roadPainter");
        GameRegistry.registerBlock(Roads.lightBollard, "lightBollard");
        GameRegistry.registerBlock(Roads.streetLamp1, "streetLamp1");
        GameRegistry.registerBlock(Roads.streetLamp2, "streetLamp2");
        GameRegistry.registerBlock(Roads.barrierPole, "barrierPole");
        GameRegistry.registerBlock(Roads.barrierBlock, "barrierBlock");
        GameRegistry.registerBlock(Roads.barrierCorner, "barrierCorner");
        //GameRegistry.registerBlock(Roads.roadSign, "roadSign");
        GameRegistry.registerBlock(Roads.roadBarrier, "roadBarrier");
        //GameRegistry.registerBlock(Roads.roadBarrierUp, "roadBarrierUp");
        
        GameRegistry.registerItem(Roads.cementItem, "cementItem");
        GameRegistry.registerItem(Roads.cementDustItem, "cementDustItem");
        GameRegistry.registerItem(Roads.limeStonePowderItem, "limeStonePowderItem");
        GameRegistry.registerItem(Roads.limeClayPowderItem, "limeClayPowderItem");
        GameRegistry.registerItem(Roads.tarBucketItem, "tarBucketItem");
        GameRegistry.registerItem(Roads.whitePaintBlob, "whitePaintBlob");
        GameRegistry.registerItem(Roads.yellowPaintBlob, "yellowPaintBlob");
        GameRegistry.registerItem(Roads.whitePaintCan, "whitePaintCan");
        GameRegistry.registerItem(Roads.yellowPaintCan, "yellowPaintCan");
        GameRegistry.registerItem(Roads.blankSign, "blankSign");
        //GameRegistry.registerItem(Roads.carItem, "spawnerWand");
        
        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("tar", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(Roads.tarBucketItem), new ItemStack(Item.bucketEmpty));
                
        MinecraftForge.setBlockHarvestLevel(Roads.roadBlockArrows, "pickaxe", 1);
        MinecraftForge.setBlockHarvestLevel(Roads.roadBlockDoubleYellow, "pickaxe", 1);
    }
    
    public void addNames() {
    	LanguageRegistry.addName(Roads.limeStoneBlock, "Limestone");
        LanguageRegistry.addName(Roads.catsEye, "Cat's Eye");
        LanguageRegistry.addName(Roads.catsEyeSide, "Cat's Eye (Side)");
        
    	LanguageRegistry.addName(Roads.tarBlock, "Tar");
        
        LanguageRegistry.addName(new ItemStack(Roads.generalBlocks, 1, 0), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.generalBlocks, 1, 1), "Cement");
        LanguageRegistry.addName(new ItemStack(Roads.generalBlocks, 1, 2), "Macadam");
        LanguageRegistry.addName(new ItemStack(Roads.generalBlocks, 1, 3), "Hardened Tar");
        LanguageRegistry.addName(new ItemStack(Roads.generalBlocks, 1, 4), "Concrete");
        LanguageRegistry.addName(new ItemStack(Roads.generalBlocks, 1, 5), "Concrete, Old");
        LanguageRegistry.addName(new ItemStack(Roads.generalBlocks, 1, 6), "Sidewalk Light Square");
        LanguageRegistry.addName(new ItemStack(Roads.generalBlocks, 1, 7), "Sidewalk Grey Square");
        LanguageRegistry.addName(new ItemStack(Roads.generalBlocks, 1, 8), "Fuel Station Roof 1");
        LanguageRegistry.addName(new ItemStack(Roads.generalBlocks, 1, 9), "Fuel Station Roof 2");
        LanguageRegistry.addName(new ItemStack(Roads.generalBlocks, 1, 10), "Fuel Station Roof 3");
        LanguageRegistry.addName(new ItemStack(Roads.generalBlocks, 1, 11), "Fuel Station Roof 4");
        LanguageRegistry.addName(new ItemStack(Roads.generalBlocks, 1, 12), "Fuel Station Roof Plain");
        LanguageRegistry.addName(new ItemStack(Roads.generalBlocks, 1, 13), "Desert Sand");
        LanguageRegistry.addName(new ItemStack(Roads.generalBlocks, 1, 14), "Dark Brick 1");
        LanguageRegistry.addName(new ItemStack(Roads.generalBlocks, 1, 15), "Dark Brick 2");
        
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockArrows, 1, 0), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockArrows, 1, 4), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockArrows, 1, 8), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockArrows, 1, 12), "Tarmac");
        
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockCorners, 1, 0), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockCorners, 1, 4), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockCorners, 1, 8), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockCorners, 1, 12), "Tarmac");
        
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockDoubleYellow, 1, 0), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockDoubleYellow, 1, 4), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockDoubleYellow, 1, 8), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockDoubleYellow, 1, 12), "Tarmac");
        
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockCornerB, 1, 0), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockCornerB, 1, 4), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockCornerB, 1, 8), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockCornerB, 1, 12), "Tarmac");
        
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockCornerC, 1, 0), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockCornerC, 1, 4), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockCornerC, 1, 8), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockCornerC, 1, 12), "Tarmac");
        
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockJunctionIn, 1, 0), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockJunctionIn, 1, 4), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockJunctionIn, 1, 8), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockJunctionIn, 1, 12), "Tarmac");
         
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockJunctionOut, 1, 0), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockJunctionOut, 1, 4), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockJunctionOut, 1, 8), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockJunctionOut, 1, 12), "Tarmac");
        
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockMiscSingles, 1, 0), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockMiscSingles, 1, 1), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockMiscSingles, 1, 2), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockMiscSingles, 1, 3), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockMiscSingles, 1, 4), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockMiscSingles, 1, 5), "Dirt Track Road");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockMiscSingles, 1, 6), "Dirt Track Road");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockMiscSingles, 1, 7), "Lowered Grass");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockMiscSingles, 1, 8), "Lowered Stone");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockMiscSingles, 1, 9), "Lowered Cobblestone");
   		LanguageRegistry.addName(new ItemStack(Roads.roadBlockMiscSingles, 1, 10), "Lowered Sand");
   		LanguageRegistry.addName(new ItemStack(Roads.roadBlockMiscSingles, 1, 11), "Lowered Gravel");
   		LanguageRegistry.addName(new ItemStack(Roads.roadBlockMiscSingles, 1, 12), "Lowered Stone Brick");
   		LanguageRegistry.addName(new ItemStack(Roads.roadBlockMiscSingles, 1, 13), "Lowered Planks");
   		LanguageRegistry.addName(new ItemStack(Roads.roadBlockMiscSingles, 1, 14), "Lowered Concrete");
   		LanguageRegistry.addName(new ItemStack(Roads.roadBlockMiscSingles, 1, 15), "Lowered Black & Purple Thingy");
                		
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockSimpleLines, 1, 0), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockSimpleLines, 1, 4), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockSimpleLines, 1, 8), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockSimpleLines, 1, 12), "Tarmac");
        		
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockSideWhiteStripes, 1, 0), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockSideWhiteStripes, 1, 4), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockSideWhiteStripes, 1, 8), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockSideWhiteStripes, 1, 12), "Tarmac");
                                        		
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockStripes, 1, 0), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockStripes, 1, 4), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockStripes, 1, 8), "Tarmac");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockStripes, 1, 12), "Tarmac");
        
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
        
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockDirt, 1, 0), "Dirt Track Road");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockDirt, 1, 4), "Dirt Track Road");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockDirt, 1, 8), "Dirt Track Road");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockDirt, 1, 12), "Dirt Track Road");
        
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockDirtCorner, 1, 0), "Dirt Track Road");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockDirtCorner, 1, 4), "Dirt Track Road");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockDirtCorner, 1, 8), "Dirt Track Road");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockDirtCorner, 1, 12), "Dirt Track Road");
        
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockFork, 1, 0), "Tarmac, Fork");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockFork, 1, 4), "Tarmac, Fork");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockFork, 1, 8), "Tarmac, Fork");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockFork, 1, 12), "Tarmac, Fork");
        
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkB, 1, 0), "Tarmac, Fork"); 
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkB, 1, 4), "Tarmac, Fork"); 
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkB, 1, 8), "Tarmac, Fork"); 
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkB, 1, 12), "Tarmac, Fork"); 
        
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkC, 1, 0), "Tarmac, Fork");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkC, 1, 4), "Tarmac, Fork");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkC, 1, 8), "Tarmac, Fork");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkC, 1, 12), "Tarmac, Fork");
        
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkD, 1, 0), "Tarmac, Fork");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkD, 1, 4), "Tarmac, Fork");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkD, 1, 8), "Tarmac, Fork");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkD, 1, 12), "Tarmac, Fork");
        
        /*LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkE, 1, 0), "Tarmac, Fork");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkE, 1, 4), "Tarmac, Fork");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkE, 1, 8), "Tarmac, Fork");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkE, 1, 12), "Tarmac, Fork");
        
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkF, 1, 0), "Tarmac, Fork");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkF, 1, 4), "Tarmac, Fork");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkF, 1, 8), "Tarmac, Fork");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkF, 1, 12), "Tarmac, Fork");*/
        
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkG, 1, 0), "Tarmac, Fork");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkG, 1, 4), "Tarmac, Fork");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkG, 1, 8), "Tarmac, Fork");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockForkG, 1, 12), "Tarmac, Fork");
        
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLettersABCD, 1, 0), "Tarmac, Lettered");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLettersABCD, 1, 4), "Tarmac, Lettered");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLettersABCD, 1, 8), "Tarmac, Lettered");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLettersABCD, 1, 12), "Tarmac, Lettered");
        
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLettersEFGH, 1, 0), "Tarmac, Lettered");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLettersEFGH, 1, 4), "Tarmac, Lettered");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLettersEFGH, 1, 8), "Tarmac, Lettered");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLettersEFGH, 1, 12), "Tarmac, Lettered");
        
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLettersIJKL, 1, 0), "Tarmac, Lettered");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLettersIJKL, 1, 4), "Tarmac, Lettered");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLettersIJKL, 1, 8), "Tarmac, Lettered");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLettersIJKL, 1, 12), "Tarmac, Lettered");
        
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLettersMNOP, 1, 0), "Tarmac, Lettered");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLettersMNOP, 1, 4), "Tarmac, Lettered");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLettersMNOP, 1, 8), "Tarmac, Lettered");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLettersMNOP, 1, 12), "Tarmac, Lettered");
        
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLettersQRST, 1, 0), "Tarmac, Lettered");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLettersQRST, 1, 4), "Tarmac, Lettered");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLettersQRST, 1, 8), "Tarmac, Lettered");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLettersQRST, 1, 12), "Tarmac, Lettered");
        
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLettersUVWX, 1, 0), "Tarmac, Lettered");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLettersUVWX, 1, 4), "Tarmac, Lettered");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLettersUVWX, 1, 8), "Tarmac, Lettered");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLettersUVWX, 1, 12), "Tarmac, Lettered");
        
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLettersYZ01, 1, 0), "Tarmac, Lettered");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLettersYZ01, 1, 4), "Tarmac, Lettered");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLettersYZ01, 1, 8), "Tarmac, Lettered");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLettersYZ01, 1, 12), "Tarmac, Lettered");
        
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLetters2345, 1, 0), "Tarmac, Lettered");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLetters2345, 1, 4), "Tarmac, Lettered");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLetters2345, 1, 8), "Tarmac, Lettered");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLetters2345, 1, 12), "Tarmac, Lettered");
        
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLetters6789, 1, 0), "Tarmac, Lettered");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLetters6789, 1, 4), "Tarmac, Lettered");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLetters6789, 1, 8), "Tarmac, Lettered");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLetters6789, 1, 12), "Tarmac, Lettered");
        
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLettersMisc, 1, 0), "Tarmac, Lettered");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLettersMisc, 1, 4), "Tarmac, Lettered");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLettersMisc, 1, 8), "Tarmac, Lettered");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockLettersMisc, 1, 12), "Tarmac, Lettered");
        
        LanguageRegistry.addName(new ItemStack(Roads.roadRamp1, 1, 0), "Tarmac Ramp (4)");
        LanguageRegistry.addName(new ItemStack(Roads.roadRamp1, 1, 4), "Tarmac Ramp (4)");
        LanguageRegistry.addName(new ItemStack(Roads.roadRamp1, 1, 8), "Tarmac Ramp (4)");
        LanguageRegistry.addName(new ItemStack(Roads.roadRamp1, 1, 12), "Tarmac Ramp (4)");
        								
        LanguageRegistry.addName(new ItemStack(Roads.roadRamp2, 1, 0), "Tarmac Ramp (4)");
        LanguageRegistry.addName(new ItemStack(Roads.roadRamp2, 1, 4), "Tarmac Ramp (4)");
        LanguageRegistry.addName(new ItemStack(Roads.roadRamp2, 1, 8), "Tarmac Ramp (4)");
        LanguageRegistry.addName(new ItemStack(Roads.roadRamp2, 1, 12), "Tarmac Ramp (4)");
        
        LanguageRegistry.addName(new ItemStack(Roads.roadRamp3, 1, 0), "Tarmac Ramp (2)");
        LanguageRegistry.addName(new ItemStack(Roads.roadRamp3, 1, 4), "Tarmac Ramp (2)");
        LanguageRegistry.addName(new ItemStack(Roads.roadRamp3, 1, 8), "Tarmac Ramp (2)");
        LanguageRegistry.addName(new ItemStack(Roads.roadRamp3, 1, 12), "Tarmac Ramp (2)");
        
        LanguageRegistry.addName(new ItemStack(Roads.roadRamp4, 1, 0), "Tarmac Ramp (2)");
        LanguageRegistry.addName(new ItemStack(Roads.roadRamp4, 1, 4), "Tarmac Ramp (2)");
        LanguageRegistry.addName(new ItemStack(Roads.roadRamp4, 1, 8), "Tarmac Ramp (2)");
        LanguageRegistry.addName(new ItemStack(Roads.roadRamp4, 1, 12), "Tarmac Ramp (2)");
        
        LanguageRegistry.addName(new ItemStack(Roads.roadRamp5, 1, 0), "Tarmac Ramp (1)");
        LanguageRegistry.addName(new ItemStack(Roads.roadRamp5, 1, 4), "Tarmac Ramp (1)");
        LanguageRegistry.addName(new ItemStack(Roads.roadRamp5, 1, 8), "Tarmac Ramp (1)");
        LanguageRegistry.addName(new ItemStack(Roads.roadRamp5, 1, 12), "Tarmac Ramp (1)");
        
        LanguageRegistry.addName(new ItemStack(Roads.roadRamp6, 1, 0), "Tarmac Ramp (1)");
        LanguageRegistry.addName(new ItemStack(Roads.roadRamp6, 1, 4), "Tarmac Ramp (1)");
        LanguageRegistry.addName(new ItemStack(Roads.roadRamp6, 1, 8), "Tarmac Ramp (1)");
        LanguageRegistry.addName(new ItemStack(Roads.roadRamp6, 1, 12), "Tarmac Ramp (1)");
        
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockCenterCorner, 1, 0), "Tarmac, Central Corner");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockCenterCorner, 1, 4), "Tarmac, Central Corner");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockCenterCorner, 1, 8), "Tarmac, Central Corner");
        LanguageRegistry.addName(new ItemStack(Roads.roadBlockCenterCorner, 1, 12), "Tarmac");
        
        
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
   		LanguageRegistry.addName(Roads.blankSign, "Blank Sign");
   		
   		//LanguageRegistry.addName(Roads.carItem, "Car");
    }
    
    public void addRecipes() {
    }

	public void registerRenderers() {
	}
}