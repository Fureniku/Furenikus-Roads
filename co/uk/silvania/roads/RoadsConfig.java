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
	
	public static int roadBlockSWS2ID;
	public static int roadBlockDYSIID;
	public static int roadBlockDYSOID;
	public static int roadBlockSYSID;
	public static int roadBlockSWSID;
	public static int roadBlockSWCID;
	public static int roadBlockSYCID;
	public static int roadBlockWhiteCrossID;
	public static int roadBlockYellowCrossID;
	public static int roadBlockWhiteFullID;
	public static int roadBlockYellowFullID;
	public static int roadBlockWCID;
	public static int roadBlockFYSID;
	public static int roadBlockYCID;
	public static int roadBlockYACID;
	public static int roadBlockWACID;
	public static int roadBlockWSLID;
	public static int roadBlockWSSLID;
	public static int roadBlockYSSLID;
	public static int roadBlockJOID;
	public static int roadBlockJIID;
	public static int roadBlockJOLID;
	public static int roadBlockJILID;
	public static int roadBlockJOILID;
	public static int roadBlockJIILID;
	
	
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
	
	public static int sidewalkBlockGreyID;
	public static int sidewalkBlockLightID;
	public static int sidewalkBlockTileID;
	
	public static int roadRamp1ID;
	public static int roadRamp2ID;
	
	public static int powerPoleID;
	public static int powerPoleSmallID;
	
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
	
	public static int cementItemID;
	public static int cementDustID;
	public static int limeStonePowderID;
	public static int limeClayPowderID;
	public static int tarBucketID;
	
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
		
		sidewalkBlockGreyID = config.getBlock("Sidewalk Grey", 827).getInt();
		sidewalkBlockLightID = config.getBlock("Sidewalk Light", 828).getInt();
		sidewalkBlockTileID = config.getBlock("Sidewalk Tiled", 829).getInt();
		
		roadRamp1ID = config.getBlock("Road Ramp 1", 830).getInt();
		roadRamp2ID = config.getBlock("Road Ramp 2", 831).getInt();
		
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
		roadSignID = config.getBlock("Road Sign", 842).getInt();
		roadBarrierID = config.getBlock("Road Barrier", 843).getInt();

		//Item IDs
		cementItemID = config.getItem("Cement", 16700).getInt();
		cementDustID = config.getItem("Cement Dust", 16701).getInt();
		limeStonePowderID = config.getItem("Limestone Dust", 16704).getInt();
		limeClayPowderID = config.getItem("Lime/Clay Dust", 16703).getInt();
		tarBucketID = config.getItem("Bucket of Tar", 16702).getInt();

		config.save();
	}
}
