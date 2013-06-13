package co.uk.silvania.roads;

import net.minecraft.block.Block;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class RoadsConfig {

	//Blocks
	public static int roadBlockID;
	public static int macadamBlockID;
	public static int cementBlockID;
	public static int limeStoneBlockID;
	public static int hardenedTarBlockID;
	public static int sidewalkBlocksID;
	public static int sidewalkKerbedID;
	public static int sidewalkKerbed1ID;
	public static int sidewalkKerbed2ID;
	public static int sidewalkKerbedInnerCornerID;
	public static int sidewalkKerbed1InnerCornerID;
	public static int sidewalkKerbed2InnerCornerID;
	public static int sidewalkKerbedOuterCornerID;
	public static int sidewalkKerbed1OuterCornerID;
	public static int sidewalkKerbed2OuterCornerID;
	public static int catsEyeID;
	public static int catsEyeSideID;
	public static int roadsTarStillID;
	public static int roadsTarFlowingID;
	
	public static int roadBlockSWS2ID;
	public static int roadBlockDYSID;
	public static int roadBlockDYSELID;
	public static int roadBlockDYSERID;
	public static int roadBlockDYSIID;
	public static int roadBlockDYSOID;
	public static int roadBlockSYSID;
	public static int roadBlockSWSID;
	public static int roadBlockSWCID;
	public static int roadBlockSYCID;
	public static int roadBlockDYCID;
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
	public static int roadBlockALID;
	public static int roadBlockAUSID;
	public static int roadBlockAULID;
	public static int roadBlockAURID;
	public static int roadBlockWDSID;
	public static int roadBlockYDSID;
	public static int roadBlockWSSID;
	public static int roadBlockYSSID;
	public static int roadBlockSLID;
	public static int roadBlockOWID;
	public static int roadBlockSTID;
	public static int roadBlockOPID;
	public static int roadBlockStopID;
	public static int roadBlockPaintGagID;
	public static int roadBlockJCUKID;
	public static int roadBlockJCUSAID;
	
	public static int powerPoleID;
	public static int roadPainterID;
	public static int trafficLightID;
	public static int trafficHangingID;
	public static int lightBollardID;
	public static int streetLamp1ID;
	public static int streetLamp2ID;
	public static int streetSignID;
	public static int barrierPoleID;
	public static int barrierBlockID;
	
	public static int cementItemID;
	public static int cementDustID;
	public static int limeStonePowderID;
	public static int limeClayPowderID;
	public static int tarBucketID;
	
	public static void loadConfig(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
		config.load();
		
		//Block IDs
		roadBlockID = config.getBlock("Base Road Block", 800).getInt();
		macadamBlockID = config.getBlock("Macadam", 801).getInt();
		cementBlockID = config.getBlock("Cement Block", 802).getInt();
		limeStoneBlockID = config.getBlock("Limestone", 803).getInt();
		hardenedTarBlockID = config.getBlock("Hardened Tar", 804).getInt();
		sidewalkBlocksID = config.getBlock("Sidewalk Blocks", 805).getInt();
		sidewalkKerbedID = config.getBlock("Sidewalk Kerb", 806).getInt();
		sidewalkKerbed1ID = config.getBlock("Sidewalk Kerb 1", 807).getInt();
		sidewalkKerbed2ID = config.getBlock("Sidewalk Kerb 2", 808).getInt();
		sidewalkKerbedInnerCornerID = config.getBlock("Sidewalk Kerb Inner Corner", 809).getInt();
		sidewalkKerbed1InnerCornerID = config.getBlock("Sidewalk Kerb Inner Corner 1", 810).getInt();
		sidewalkKerbed2InnerCornerID = config.getBlock("Sidewalk Kerb Inner Corner 2", 811).getInt();
		sidewalkKerbedOuterCornerID = config.getBlock("Sidewalk Kerb Outer Corner", 812).getInt();
		sidewalkKerbed1OuterCornerID = config.getBlock("Sidewalk Kerb Outer Corner 1", 813).getInt();
		sidewalkKerbed2OuterCornerID = config.getBlock("Sidewalk Kerb Outer Corner 2", 814).getInt();
		catsEyeID = config.getBlock("Cat's Eye", 815).getInt();
		catsEyeSideID = config.getBlock("Cat's Eye Side", 816).getInt();
		roadsTarStillID = config.getBlock("Tar Still", 819).getInt();
		roadsTarFlowingID = config.getBlock("Tar Flowing", 818).getInt();
		
		//Road Block IDs
		roadBlockSWS2ID = config.getBlock("Side White Stripe", 820).getInt();
		roadBlockDYSID = config.getBlock("Double Yellow Stripe", 821).getInt();
		roadBlockDYSELID = config.getBlock("Double Yellow Stripe End Left", 822).getInt();
		roadBlockDYSERID = config.getBlock("Double Yellow Stripe End Right", 823).getInt();
		roadBlockDYSIID = config.getBlock("Double Yellow Stripe Inner Corner", 824).getInt();
		roadBlockDYSOID = config.getBlock("Double Yellow Stripe Outer Corner", 825).getInt();
		roadBlockSYSID = config.getBlock("Single Yellow Stripe", 826).getInt();
		roadBlockSWSID = config.getBlock("Single White Side Stripe", 827).getInt();
		roadBlockSWCID = config.getBlock("Single White Center Stripe", 828).getInt();
		roadBlockSYCID = config.getBlock("Single Yellow Center Stripe", 829).getInt();
		roadBlockDYCID = config.getBlock("Double Yellow Center Stripe", 830).getInt();
		roadBlockWhiteCrossID = config.getBlock("White Cross", 831).getInt();
		roadBlockYellowCrossID = config.getBlock("Yellow Cross", 832).getInt();
		roadBlockWhiteFullID = config.getBlock("White Full", 833).getInt();
		roadBlockYellowFullID = config.getBlock("Yellow Full", 834).getInt();
		roadBlockWCID = config.getBlock("White Corner", 835).getInt();
		roadBlockFYSID = config.getBlock("Far Side Yellow Stripe", 836).getInt();
		roadBlockYCID = config.getBlock("Yellow Corner", 837).getInt();
		roadBlockYACID = config.getBlock("Yellow Alternate Corner", 838).getInt();
		roadBlockWACID = config.getBlock("White Alternate Corner", 839).getInt();
		roadBlockWSLID = config.getBlock("White Striped Line", 840).getInt();
		roadBlockWSSLID = config.getBlock("White Side Striped Line", 841).getInt();
		roadBlockYSSLID = config.getBlock("Yellow Side Striped Line", 842).getInt();
		roadBlockJOID = config.getBlock("Junction Out", 843).getInt();
		roadBlockJIID = config.getBlock("Junction In", 844).getInt();
		roadBlockJOLID = config.getBlock("Junction Out Line", 845).getInt();
		roadBlockJILID = config.getBlock("Junction In Line", 846).getInt();
		roadBlockJOILID = config.getBlock("Junction Out Inverted Line", 847).getInt();
		roadBlockJIILID = config.getBlock("Junction In Inverted Line", 848).getInt();
		roadBlockALID = config.getBlock("Arrow Line", 849).getInt();
		roadBlockAUSID = config.getBlock("Arrow Straight", 850).getInt();
		roadBlockAULID = config.getBlock("Arrow Left", 851).getInt();
		roadBlockAURID = config.getBlock("Arrow Right", 852).getInt();
		roadBlockWDSID = config.getBlock("White Diagonal Stripe", 853).getInt();
		roadBlockYDSID = config.getBlock("Yellow Diagonal Stripe", 854).getInt();
		roadBlockWSSID = config.getBlock("White Small Square", 855).getInt();
		roadBlockYSSID = config.getBlock("Yellow Small Square", 856).getInt();
		roadBlockSLID = config.getBlock("Slow 1", 857).getInt();
		roadBlockOWID = config.getBlock("Slow 2", 858).getInt();
		roadBlockSTID = config.getBlock("Stop 1", 859).getInt();
		roadBlockOPID = config.getBlock("Stop 2", 860).getInt();
		roadBlockStopID = config.getBlock("Stop Paint", 861).getInt();
		roadBlockPaintGagID = config.getBlock("Paint Gag (REQUIRED)", 862).getInt();
		roadBlockJCUKID = config.getBlock("Junction Center UK", 863).getInt();
		roadBlockJCUSAID = config.getBlock("Junction Center USA", 864).getInt();
		
		powerPoleID = config.getBlock("Power Pole", 870).getInt();
		roadPainterID = config.getBlock("Road Painter", 871).getInt();
		trafficLightID = config.getBlock("Traffic Light", 872).getInt();
		trafficHangingID = config.getBlock("Traffic Light Hanging", 873).getInt();
		lightBollardID = config.getBlock("Light Bollard", 874).getInt();
		streetLamp1ID = config.getBlock("Street Lamp 1", 875).getInt();
		streetLamp2ID = config.getBlock("Street Lamp 2", 876).getInt();
		streetSignID = config.getBlock("Street Sign", 877).getInt();
		barrierPoleID = config.getBlock("Barrier (Post)", 878).getInt();
		barrierBlockID = config.getBlock("Barrier", 879).getInt();


		//Item IDs
		cementItemID = config.getItem("Cement", 16700).getInt();
		cementDustID = config.getItem("Cement Dust", 16701).getInt();
		limeStonePowderID = config.getItem("Limestone Dust", 16704).getInt();
		limeClayPowderID = config.getItem("Lime/Clay Dust", 16703).getInt();
		tarBucketID = config.getItem("Bucket of Tar", 16702).getInt();

		config.save();
	}
}
