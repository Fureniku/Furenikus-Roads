package com.silvaniastudios.roads.client.model;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.decorative.CatsEyeBlock;
import com.silvaniastudios.roads.blocks.decorative.CatsEyeBlockFourWay;
import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;
import com.silvaniastudios.roads.registries.PaintCategoryList;
import com.silvaniastudios.roads.registries.PaintGunItemRegistry;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
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
	
	@SubscribeEvent
	public void stitcherEventPre(TextureStitchEvent.Pre event) {
		ResourceLocation white_paint = new ResourceLocation(FurenikusRoads.MODID + ":items/paint_white");
		ResourceLocation yellow_paint = new ResourceLocation(FurenikusRoads.MODID + ":items/paint_yellow");
		ResourceLocation red_paint = new ResourceLocation(FurenikusRoads.MODID + ":items/paint_red");
		
		ResourceLocation block_white_paint = new ResourceLocation(FurenikusRoads.MODID + ":blocks/paint_white");
		ResourceLocation block_yellow_paint = new ResourceLocation(FurenikusRoads.MODID + ":blocks/paint_yellow");
		ResourceLocation block_red_paint = new ResourceLocation(FurenikusRoads.MODID + ":blocks/paint_red");
		
		ResourceLocation tar_flowing = new ResourceLocation(FurenikusRoads.MODID + ":fluids/tar_flowing");
		ResourceLocation tar_still = new ResourceLocation(FurenikusRoads.MODID + ":fluids/tar_still");
		ResourceLocation white_paint_flowing = new ResourceLocation(FurenikusRoads.MODID + ":fluids/white_paint_flowing");
		ResourceLocation white_paint_still = new ResourceLocation(FurenikusRoads.MODID + ":fluids/white_paint_still");
		ResourceLocation yellow_paint_flowing = new ResourceLocation(FurenikusRoads.MODID + ":fluids/yellow_paint_flowing");
		ResourceLocation yellow_paint_still = new ResourceLocation(FurenikusRoads.MODID + ":fluids/yellow_paint_still");
		ResourceLocation red_paint_flowing = new ResourceLocation(FurenikusRoads.MODID + ":fluids/red_paint_flowing");
		ResourceLocation red_paint_still = new ResourceLocation(FurenikusRoads.MODID + ":fluids/red_paint_still");
		
		ResourceLocation cats_eye_white  = new ResourceLocation(FurenikusRoads.MODID + ":blocks/cats_eye_white");
		ResourceLocation cats_eye_yellow = new ResourceLocation(FurenikusRoads.MODID + ":blocks/cats_eye_yellow");
		ResourceLocation cats_eye_red    = new ResourceLocation(FurenikusRoads.MODID + ":blocks/cats_eye_red");
		ResourceLocation cats_eye_green  = new ResourceLocation(FurenikusRoads.MODID + ":blocks/cats_eye_green");
		
		ResourceLocation machine_vent_on = new ResourceLocation(FurenikusRoads.MODID + ":blocks/machine_vent_back_on");
		ResourceLocation paint_filler_display = new ResourceLocation(FurenikusRoads.MODID + ":blocks/paint_filler_machine_display");
		
		ResourceLocation sprite_white_paint = new ResourceLocation(FurenikusRoads.MODID + ":fluids/white_paint_flowing");
		ResourceLocation sprite_yellow_paint = new ResourceLocation(FurenikusRoads.MODID + ":fluids/yellow_paint_flowing");
		ResourceLocation sprite_red_paint = new ResourceLocation(FurenikusRoads.MODID + ":fluids/red_paint_flowing");
		
		ResourceLocation sprite_glass = new ResourceLocation(FurenikusRoads.MODID + ":blocks/hopper_tank");
		ResourceLocation sprite_glass_top = new ResourceLocation(FurenikusRoads.MODID + ":blocks/hopper_tank_top");
		
		ResourceLocation sprite_light_white = new ResourceLocation(FurenikusRoads.MODID + ":blocks/hopper_type_white");
		ResourceLocation sprite_light_yellow = new ResourceLocation(FurenikusRoads.MODID + ":blocks/hopper_type_yellow");
		ResourceLocation sprite_light_red = new ResourceLocation(FurenikusRoads.MODID + ":blocks/hopper_type_red");
		ResourceLocation sprite_light_item = new ResourceLocation(FurenikusRoads.MODID + ":blocks/hopper_type_item");
		ResourceLocation sprite_light_none = new ResourceLocation(FurenikusRoads.MODID + ":blocks/hopper_type_none");
		
		ResourceLocation line_straight_full_0 = new ResourceLocation(FurenikusRoads.MODID + ":paints/line_straight_full_0");
		ResourceLocation line_straight_full_1 = new ResourceLocation(FurenikusRoads.MODID + ":paints/line_straight_full_1");
		ResourceLocation line_straight_full_2 = new ResourceLocation(FurenikusRoads.MODID + ":paints/line_straight_full_2");
		
		ResourceLocation line_straight_thick_0 = new ResourceLocation(FurenikusRoads.MODID + ":paints/line_straight_thick_0");
		ResourceLocation line_straight_thick_1 = new ResourceLocation(FurenikusRoads.MODID + ":paints/line_straight_thick_1");
		ResourceLocation line_straight_thick_2 = new ResourceLocation(FurenikusRoads.MODID + ":paints/line_straight_thick_2");
		
		ResourceLocation line_straight_double_0 = new ResourceLocation(FurenikusRoads.MODID + ":paints/line_straight_double_0");
		ResourceLocation line_straight_double_1 = new ResourceLocation(FurenikusRoads.MODID + ":paints/line_straight_double_1");
		ResourceLocation line_straight_double_2 = new ResourceLocation(FurenikusRoads.MODID + ":paints/line_straight_double_2");
		
		ResourceLocation line_straight_double_thick_0 = new ResourceLocation(FurenikusRoads.MODID + ":paints/line_straight_double_thick_0");
		ResourceLocation line_straight_double_thick_1 = new ResourceLocation(FurenikusRoads.MODID + ":paints/line_straight_double_thick_1");
		ResourceLocation line_straight_double_thick_2 = new ResourceLocation(FurenikusRoads.MODID + ":paints/line_straight_double_thick_2");
		
		event.getMap().registerSprite(line_straight_full_0);
		event.getMap().registerSprite(line_straight_full_1);
		event.getMap().registerSprite(line_straight_full_2);
		
		event.getMap().registerSprite(line_straight_thick_0);
		event.getMap().registerSprite(line_straight_thick_1);
		event.getMap().registerSprite(line_straight_thick_2);
		
		event.getMap().registerSprite(line_straight_double_0);
		event.getMap().registerSprite(line_straight_double_1);
		event.getMap().registerSprite(line_straight_double_2);
		
		event.getMap().registerSprite(line_straight_double_thick_0);
		event.getMap().registerSprite(line_straight_double_thick_1);
		event.getMap().registerSprite(line_straight_double_thick_2);
		
		event.getMap().registerSprite(white_paint);
		event.getMap().registerSprite(yellow_paint);
		event.getMap().registerSprite(red_paint);
		
		event.getMap().registerSprite(block_white_paint);
		event.getMap().registerSprite(block_yellow_paint);
		event.getMap().registerSprite(block_red_paint);
		
		event.getMap().registerSprite(tar_flowing);
		event.getMap().registerSprite(tar_still);
		event.getMap().registerSprite(white_paint_flowing);
		event.getMap().registerSprite(white_paint_still);
		event.getMap().registerSprite(yellow_paint_flowing);
		event.getMap().registerSprite(yellow_paint_still);
		event.getMap().registerSprite(red_paint_flowing);
		event.getMap().registerSprite(red_paint_still);
		
		event.getMap().registerSprite(cats_eye_white);
		event.getMap().registerSprite(cats_eye_yellow);
		event.getMap().registerSprite(cats_eye_red);
		event.getMap().registerSprite(cats_eye_green);
		
		event.getMap().registerSprite(machine_vent_on);
		event.getMap().registerSprite(paint_filler_display);
		
		event.getMap().registerSprite(sprite_white_paint);
		event.getMap().registerSprite(sprite_yellow_paint);
		event.getMap().registerSprite(sprite_red_paint);
		
		event.getMap().registerSprite(sprite_glass);
		event.getMap().registerSprite(sprite_glass_top);
		
		event.getMap().registerSprite(sprite_light_white);
		event.getMap().registerSprite(sprite_light_yellow);
		event.getMap().registerSprite(sprite_light_red);
		event.getMap().registerSprite(sprite_light_item);
		event.getMap().registerSprite(sprite_light_none);
		
		for (int i = 0; i < PaintGunItemRegistry.categoryList.size(); i++) {
			PaintCategoryList category = PaintGunItemRegistry.categoryList.get(i);
			for (int j = 0; j < category.size(); j++) {
				PaintBlockBase block = category.getPaint(j);
				String name = block.getUnlocalizedName().substring(20); //tile.furenikusroads: ...
				if (category.getMeta(j) > 0) { name = name + "_" + category.getMeta(j); }
				ResourceLocation sprite = new ResourceLocation(FurenikusRoads.MODID + ":items/paint_gun_display/" + name);
				event.getMap().registerSprite(sprite);
			}
		}
	}
}
