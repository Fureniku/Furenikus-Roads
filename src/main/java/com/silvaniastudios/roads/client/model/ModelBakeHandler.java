package com.silvaniastudios.roads.client.model;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.decorative.CatsEyeBlock;
import com.silvaniastudios.roads.blocks.decorative.CatsEyeBlockFourWay;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModelBakeHandler {
	
	public static final ModelBakeHandler instance = new ModelBakeHandler();
	
	private ModelBakeHandler() {};
	
	@SubscribeEvent
	public void onModelBakeEvent(ModelBakeEvent event) {
		bakePaintGunModel(event);
		bakeCatsEyeModels(event);
		bakeCatsEyeDoubleSidedModels(event);
		bakeCrusherFurnace(event);
		bakePaintFillerFurnace(event);
		bakeRoadFactoryFurnace(event);
		bakeTarDistillerFurnace(event);
		bakeTarmacCutterFurnace(event);
	}

	private void bakePaintGunModel(ModelBakeEvent event) {
		Object model = event.getModelRegistry().getObject(PaintGunModel.modelResourceLocation);
		
		if (model instanceof IBakedModel) {
			IBakedModel existingModel = (IBakedModel) model;
			PaintGunModel customModel = new PaintGunModel(existingModel);
			event.getModelRegistry().putObject(PaintGunModel.modelResourceLocation, customModel);
		}
	}
	
	private void bakeDiagonalRoads(ModelBakeEvent event) {
		String[] facing = new String[] {"north", "east", "south", "west"};
		
		for (int i = 0; i < facing.length; i++) {
			ModelResourceLocation mrl = new ModelResourceLocation(FurenikusRoads.MODID + ":road_block_diagonal_1_1", "facing=" + facing[i]);
			Object model = event.getModelRegistry().getObject(mrl);
			
			if (model instanceof IBakedModel) {
				IBakedModel existingModel = (IBakedModel) model;
				CrusherBakedModel customModel = new CrusherBakedModel(existingModel, facing[i]);
				event.getModelRegistry().putObject(mrl, customModel);
			}
		}
	}
	
	private void bakeCatsEyeDoubleSidedModels(ModelBakeEvent event) {
		String[] catsEyeList = new String[] {"cats_eye_red_green", "cats_eye_white_red", "cats_eye_white_yellow", "cats_eye_white_green", "cats_eye_yellow_red"};
		
		for (int i = 0; i < catsEyeList.length; i++) {
			String l = "red";
			String r = "green";
			
			if (i == 1 || i == 2 || i == 3) { l = "white"; }
			if (i == 1) { r = "red"; }
			if (i == 2) { r = "yellow"; }
			if (i == 3) { r = "green"; }
			if (i == 4) { l = "yellow"; r = "red"; }
			
			for (int j = 0; j < CatsEyeBlockFourWay.EnumCatsEye.values().length; j++) {
				ModelResourceLocation mrl = new ModelResourceLocation(FurenikusRoads.MODID + ":" + catsEyeList[i], "eye_type=" + CatsEyeBlockFourWay.EnumCatsEye.byMetadata(j));
				Object cats_eye = event.getModelRegistry().getObject(mrl);
				
				if (cats_eye instanceof IBakedModel) {
					IBakedModel existingModel = (IBakedModel) cats_eye;
					CatsEyeTwoSidedModel customModel = new CatsEyeTwoSidedModel(existingModel, l, r, CatsEyeBlockFourWay.EnumCatsEye.byMetadata(j));
					event.getModelRegistry().putObject(mrl, customModel);
				}

				ModelResourceLocation mrl_double = new ModelResourceLocation(FurenikusRoads.MODID + ":" + catsEyeList[i] + "_double", "eye_type=" + CatsEyeBlockFourWay.EnumCatsEye.byMetadata(j));
				Object cats_eye_double = event.getModelRegistry().getObject(mrl_double);
				
				if (cats_eye_double instanceof IBakedModel) {
					IBakedModel existingModel = (IBakedModel) cats_eye_double;
					CatsEyeTwoSidedDoubleModel customModel = new CatsEyeTwoSidedDoubleModel(existingModel, l, r, CatsEyeBlockFourWay.EnumCatsEye.byMetadata(j));
					event.getModelRegistry().putObject(mrl_double, customModel);
				}
			}
		}
	}
	
	private void bakeCatsEyeModels(ModelBakeEvent event) {
		String[] catsEyeList = new String[] {"cats_eye_white", "cats_eye_yellow", "cats_eye_red", "cats_eye_green", "cats_eye_blue"};
		String[] colourList = new String[] {"white", "yellow", "red", "green", "blue"};
		
		for (int i = 0; i < catsEyeList.length; i++) {
			for (int j = 0; j < CatsEyeBlock.EnumCatsEye.values().length; j++) {
				ModelResourceLocation mrl = new ModelResourceLocation(FurenikusRoads.MODID + ":" + catsEyeList[i], "eye_type=" + CatsEyeBlock.EnumCatsEye.byMetadata(j));
				Object cats_eye = event.getModelRegistry().getObject(mrl);
				
				if (cats_eye instanceof IBakedModel) {
					IBakedModel existingModel = (IBakedModel) cats_eye;
					CatsEyeModel customModel = new CatsEyeModel(existingModel, colourList[i], CatsEyeBlock.EnumCatsEye.byMetadata(j));
					event.getModelRegistry().putObject(mrl, customModel);
				}

				ModelResourceLocation mrl_double = new ModelResourceLocation(FurenikusRoads.MODID + ":" + catsEyeList[i] + "_double", "eye_type=" + CatsEyeBlock.EnumCatsEye.byMetadata(j));
				Object cats_eye_double = event.getModelRegistry().getObject(mrl_double);
				
				if (cats_eye_double instanceof IBakedModel) {
					IBakedModel existingModel = (IBakedModel) cats_eye_double;
					CatsEyeDoubleModel customModel = new CatsEyeDoubleModel(existingModel, colourList[i], CatsEyeBlock.EnumCatsEye.byMetadata(j));
					event.getModelRegistry().putObject(mrl_double, customModel);
				}
			}
		}
	}
	
	private void bakeCrusherFurnace(ModelBakeEvent event) {
		String[] rotations = new String[] {"north", "east", "south", "west"};
		
		for (int i = 0; i < rotations.length; i++) {
			ModelResourceLocation mrl = new ModelResourceLocation(FurenikusRoads.MODID + ":crusher", "furnace_active=true,rotation=" + rotations[i]);
			Object model = event.getModelRegistry().getObject(mrl);
			
			if (model instanceof IBakedModel) {
				IBakedModel existingModel = (IBakedModel) model;
				CrusherBakedModel customModel = new CrusherBakedModel(existingModel, rotations[i]);
				event.getModelRegistry().putObject(mrl, customModel);
			}
		}
	}
	
	private void bakePaintFillerFurnace(ModelBakeEvent event) {
		String[] rotations = new String[] {"north", "east", "south", "west"};
		
		for (int i = 0; i < rotations.length; i++) {
			ModelResourceLocation mrl = new ModelResourceLocation(FurenikusRoads.MODID + ":paint_filler", "furnace_active=true,gun_loaded=false,rotation=" + rotations[i]);
			Object model = event.getModelRegistry().getObject(mrl);
			
			ModelResourceLocation mrl_gunloaded = new ModelResourceLocation(FurenikusRoads.MODID + ":paint_filler", "furnace_active=true,gun_loaded=true,rotation=" + rotations[i]);
			Object model_gunloaded = event.getModelRegistry().getObject(mrl_gunloaded);
			
			if (model instanceof IBakedModel) {
				IBakedModel existingModel = (IBakedModel) model;
				PaintFillerBakedModel customModel = new PaintFillerBakedModel(existingModel, rotations[i], false);
				if (((IBakedModel) model).getParticleTexture() != null) {
					event.getModelRegistry().putObject(mrl, customModel);
				}
			}
			
			if (model_gunloaded instanceof IBakedModel) {
				IBakedModel existingModel = (IBakedModel) model_gunloaded;
				PaintFillerBakedModel customModel = new PaintFillerBakedModel(existingModel, rotations[i], true);
				if (((IBakedModel) model).getParticleTexture() != null) {
					event.getModelRegistry().putObject(mrl_gunloaded, customModel);
				}
			}
		}
	}
	
	private void bakeRoadFactoryFurnace(ModelBakeEvent event) {
		String[] rotations = new String[] {"north", "east", "south", "west"};
		
		for (int i = 0; i < rotations.length; i++) {
			ModelResourceLocation mrl = new ModelResourceLocation(FurenikusRoads.MODID + ":road_factory", "connected=false,furnace_active=true,rotation=" + rotations[i]);
			ModelResourceLocation mrl_connect = new ModelResourceLocation(FurenikusRoads.MODID + ":road_factory", "connected=true,furnace_active=true,rotation=" + rotations[i]);
			Object model = event.getModelRegistry().getObject(mrl);
			Object model_connect = event.getModelRegistry().getObject(mrl_connect);
			
			if (model instanceof IBakedModel) {
				IBakedModel existingModel = (IBakedModel) model;
				RoadFactoryBakedModel customModel = new RoadFactoryBakedModel(existingModel, rotations[i]);
				event.getModelRegistry().putObject(mrl, customModel);
			}
			if (model_connect instanceof IBakedModel) {
				IBakedModel existingModel = (IBakedModel) model_connect;
				RoadFactoryBakedModel customModel = new RoadFactoryBakedModel(existingModel, rotations[i]);
				event.getModelRegistry().putObject(mrl_connect, customModel);
			}
		}
	}
	
	private void bakeTarDistillerFurnace(ModelBakeEvent event) {
		String[] rotations = new String[] {"north", "east", "south", "west"};
		
		for (int i = 0; i < rotations.length; i++) {
			ModelResourceLocation mrl = new ModelResourceLocation(FurenikusRoads.MODID + ":tar_distiller", "connected=false,furnace_active=true,rotation=" + rotations[i]);
			ModelResourceLocation mrl_connect = new ModelResourceLocation(FurenikusRoads.MODID + ":tar_distiller", "connected=true,furnace_active=true,rotation=" + rotations[i]);
			Object model = event.getModelRegistry().getObject(mrl);
			Object model_connect = event.getModelRegistry().getObject(mrl_connect);
			
			if (model instanceof IBakedModel) {
				IBakedModel existingModel = (IBakedModel) model;
				TarDistillerBakedModel customModel = new TarDistillerBakedModel(existingModel, rotations[i]);
				event.getModelRegistry().putObject(mrl, customModel);
			}
			if (model_connect instanceof IBakedModel) {
				IBakedModel existingModel = (IBakedModel) model_connect;
				TarDistillerBakedModel customModel = new TarDistillerBakedModel(existingModel, rotations[i]);
				event.getModelRegistry().putObject(mrl_connect, customModel);
			}
		}
	}
	
	private void bakeTarmacCutterFurnace(ModelBakeEvent event) {
		String[] rotations = new String[] {"north", "east", "south", "west"};
		
		for (int i = 0; i < rotations.length; i++) {
			ModelResourceLocation mrl = new ModelResourceLocation(FurenikusRoads.MODID + ":tarmac_cutter", "furnace_active=true,rotation=" + rotations[i]);
			Object model = event.getModelRegistry().getObject(mrl);
			
			if (model instanceof IBakedModel) {
				IBakedModel existingModel = (IBakedModel) model;
				TarmacCutterBakedModel customModel = new TarmacCutterBakedModel(existingModel, rotations[i]);
				event.getModelRegistry().putObject(mrl, customModel);
			}
		}
	}
}
