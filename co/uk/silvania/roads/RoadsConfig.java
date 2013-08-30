package co.uk.silvania.roads;

import net.minecraft.block.Block;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class RoadsConfig {

	//Blocks

	public static int limeStoneBlockID;
	public static int catsEyeID;
	public static int catsEyeSideID;
	public static int roadsTarStillID;
	public static int roadsTarFlowingID;
	public static int generalBlocksID;

	public static int roadBlockArrowsID;
	public static int roadBlockCornersID;
	public static int roadBlockDoubleYellowID;
	public static int roadBlockCornerBID;
	public static int roadBlockJunctionInID;
	public static int roadBlockJunctionOutID;
	public static int roadBlockMiscSinglesID;
	public static int roadBlockSimpleLinesID;
	public static int roadBlockSideWhiteStripesID;
	public static int roadBlockStripesID;
	public static int roadBlockDirtID;
	public static int roadBlockDirtCornerID;
	public static int roadBlockForkID;
	public static int roadBlockForkBID;
	public static int roadBlockForkCID;
	public static int roadBlockForkDID;
	public static int roadBlockForkEID;
	public static int roadBlockForkFID;
	public static int roadBlockForkGID;
	public static int roadBlockCenterCornerID;
	
	public static int sidewalkBlockGreyID;
	public static int sidewalkBlockLightID;
	public static int sidewalkBlockTileID;
	public static int sidewalkBlockTriID;
	public static int sidewalkBlockSidesID;
	public static int kerbBlockID;
	
	public static int roadRamp1ID;
	public static int roadRamp2ID;
	public static int roadRamp3ID;
	public static int roadRamp4ID;
	public static int roadRamp5ID;
	public static int roadRamp6ID;
	
	public static int powerPoleID;
	public static int powerPoleSmallID;
	public static int powerPoleLargeID;
	public static int powerPoleOnID;
	public static int powerPoleSmallOnID;
	public static int powerPoleLargeOnID;
	
	public static int blockGag1ID;
	public static int blockGag2ID;
	public static int blockGag3ID;
	public static int blockGag4ID;
	public static int blockGag5ID;
	
	public static int roadPainterID;
	public static int trafficLightID;
	public static int trafficHangingID;
	public static int lightBollardID;
	public static int streetLamp1ID;
	public static int streetLamp2ID;
	public static int streetSignID;
	public static int barrierPoleID;
	public static int barrierBlockID;
	public static int barrierCornerID;
	public static int roadSlope1ID;
	public static int roadSlope2ID;
	public static int roadSlope3ID;
	public static int roadSlope4ID;
	public static int roadSlope5ID;
	public static int roadSlope6ID;
	public static int roadSlope7ID;	
	public static int roadSlope8ID;
	public static int roadSignID;
	public static int roadBarrierID;
	public static int roadBarrierUpID;
	
	public static int cementItemID;
	public static int cementDustID;
	public static int limeStonePowderID;
	public static int limeClayPowderID;
	public static int tarBucketID;
	public static int whitePaintBlobID;
	public static int yellowPaintBlobID;
	public static int whitePaintCanID;
	public static int yellowPaintCanID;
	public static int blankSignID;
	
	public static void loadConfig(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
		config.load();
		
		//Block IDs
		generalBlocksID = config.getBlock("General Blocks", 800).getInt();
		limeStoneBlockID = config.getBlock("Limestone", 803).getInt();
		catsEyeID = config.getBlock("Cat's Eye", 804).getInt();
		catsEyeSideID = config.getBlock("Cat's Eye Side", 805).getInt();
		roadsTarStillID = config.getBlock("Tar Still", 802).getInt();
		roadsTarFlowingID = config.getBlock("Tar Flowing", 801).getInt();
		powerPoleID = config.getBlock("Power Pole", 807).getInt();
		powerPoleSmallID = config.getBlock("Power Pole Small", 806).getInt();
		powerPoleLargeID = config.getBlock("Power Pole Large", 808).getInt();
		
		powerPoleOnID = config.getBlock("Power Pole (On)", 825).getInt();
		powerPoleSmallOnID = config.getBlock("Power Pole Small (On)", 824).getInt();
		powerPoleLargeOnID = config.getBlock("Power Pole Large (On)", 826).getInt();
		
		//Road Block IDs
		roadBlockArrowsID = config.getBlock("Arrows", 812).getInt();
		roadBlockDoubleYellowID = config.getBlock("Double Yellows", 810).getInt();
		roadBlockCornersID = config.getBlock("Corners", 811).getInt();
		roadBlockCornerBID = config.getBlock("Diagonal Inverted", 813).getInt();
		roadBlockJunctionInID = config.getBlock("Junction In", 814).getInt();
		roadBlockJunctionOutID = config.getBlock("Junction Out", 815).getInt();
		roadBlockMiscSinglesID = config.getBlock("Misc Singles", 816).getInt();
		roadBlockSimpleLinesID = config.getBlock("Simple Lines", 817).getInt();
		roadBlockSideWhiteStripesID = config.getBlock("Small White Stripes", 818).getInt();
		roadBlockStripesID = config.getBlock("Stripes", 819).getInt();
		roadBlockDirtID = config.getBlock("Dirt", 820).getInt();
		roadBlockDirtCornerID = config.getBlock("Dirt, Corners", 821).getInt();
		roadBlockForkID = config.getBlock("Fork", 847).getInt();
		roadBlockForkBID = config.getBlock("Fork B", 848).getInt();
		roadBlockForkCID = config.getBlock("Fork C", 849).getInt();
		roadBlockForkDID = config.getBlock("Fork D", 850).getInt();
		roadBlockForkEID = config.getBlock("Fork E", 851).getInt();
		roadBlockForkFID = config.getBlock("Fork F", 852).getInt();
		roadBlockForkGID = config.getBlock("Fork G", 853).getInt();
		roadBlockCenterCornerID = config.getBlock("Center Corners", 862).getInt();
		
		sidewalkBlockGreyID = config.getBlock("Sidewalk Grey", 827).getInt();
		sidewalkBlockLightID = config.getBlock("Sidewalk Light", 828).getInt();
		sidewalkBlockTileID = config.getBlock("Sidewalk Tiled", 829).getInt();
		sidewalkBlockTriID = config.getBlock("Sidewalk Tri-point", 823).getInt();
		sidewalkBlockSidesID = config.getBlock("Sidewalk Sides", 822).getInt();
		kerbBlockID = config.getBlock("Kerb Block", 830).getInt();
		
		roadRamp1ID = config.getBlock("Road Ramp 1", 854).getInt();
		roadRamp2ID = config.getBlock("Road Ramp 2", 855).getInt();
		roadRamp3ID = config.getBlock("Road Ramp 3", 856).getInt();
		roadRamp4ID = config.getBlock("Road Ramp 4", 857).getInt();
		roadRamp5ID = config.getBlock("Road Ramp 5", 858).getInt();
		roadRamp6ID = config.getBlock("Road Ramp 6", 859).getInt();
		
		blockGag1ID = config.getBlock("IMPORTANT: Do not remove! BG1", 844).getInt();
		blockGag2ID = config.getBlock("IMPORTANT: Do not remove! BG2", 845).getInt();
		blockGag3ID = config.getBlock("IMPORTANT: Do not remove! BG3", 846).getInt();
		blockGag4ID = config.getBlock("IMPORTANT: Do not remove! BG4", 860).getInt();
		blockGag5ID = config.getBlock("IMPORTANT: Do not remove! BG5", 861).getInt();
		
		roadPainterID = config.getBlock("Road Painter", 832).getInt();
		trafficLightID = config.getBlock("Traffic Light", 833).getInt();
		trafficHangingID = config.getBlock("Traffic Light Hanging", 834).getInt();
		lightBollardID = config.getBlock("Light Bollard", 835).getInt();
		streetLamp1ID = config.getBlock("Street Lamp 1", 836).getInt();
		streetLamp2ID = config.getBlock("Street Lamp 2", 837).getInt();
		streetSignID = config.getBlock("Street Sign", 838).getInt();
		barrierPoleID = config.getBlock("Barrier (Post)", 839).getInt();
		barrierBlockID = config.getBlock("Barrier", 840).getInt();
		barrierCornerID = config.getBlock("Barrier (Corner)", 841).getInt();
		roadSignID = config.getBlock("Road Sign", 831).getInt();
		roadBarrierID = config.getBlock("Road Barrier", 843).getInt();
		roadBarrierUpID = config.getBlock("Road Barrier Up", 842).getInt();

		//Item IDs
		cementItemID = config.getItem("Cement", 16700).getInt();
		cementDustID = config.getItem("Cement Dust", 16701).getInt();
		limeStonePowderID = config.getItem("Limestone Dust", 16704).getInt();
		limeClayPowderID = config.getItem("Lime/Clay Dust", 16703).getInt();
		tarBucketID = config.getItem("Bucket of Tar", 16702).getInt();
		whitePaintBlobID = config.getItem("White Paint Blob", 16707).getInt();
		yellowPaintBlobID = config.getItem("Yellow Paint Blob", 16708).getInt();
		whitePaintCanID = config.getItem("White Paint Can", 16705).getInt();
		yellowPaintCanID = config.getItem("Yellow Paint Can", 16706).getInt();
		blankSignID = config.getItem("Blank Sign", 16709).getInt();

		config.save();
	}
}
