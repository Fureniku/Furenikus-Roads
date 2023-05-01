package com.silvaniastudios.roads.client;

import com.silvaniastudios.roads.CommonProxy;
import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.paint.customs.CustomPaintBlock;
import com.silvaniastudios.roads.blocks.tileentities.crusher.CrusherEntity;
import com.silvaniastudios.roads.blocks.tileentities.distiller.TarDistillerEntity;
import com.silvaniastudios.roads.blocks.tileentities.paintfiller.PaintFillerEntity;
import com.silvaniastudios.roads.blocks.tileentities.paintfiller.hopper.PaintFillerHopperEntity;
import com.silvaniastudios.roads.blocks.tileentities.paintoven.PaintOvenEntity;
import com.silvaniastudios.roads.blocks.tileentities.roadfactory.RoadFactoryEntity;
import com.silvaniastudios.roads.blocks.tileentities.tarmaccutter.TarmacCutterEntity;
import com.silvaniastudios.roads.client.gui.paintgun.GuiPaintGun;
import com.silvaniastudios.roads.client.model.CrusherRenderer;
import com.silvaniastudios.roads.client.model.ModelBakeHandler;
import com.silvaniastudios.roads.client.model.PaintFillerHopperRenderer;
import com.silvaniastudios.roads.client.model.PaintFillerRenderer;
import com.silvaniastudios.roads.client.model.PaintOvenRenderer;
import com.silvaniastudios.roads.client.model.RoadFactoryRenderer;
import com.silvaniastudios.roads.client.model.TarDistillerRenderer;
import com.silvaniastudios.roads.client.model.TarmacCutterRenderer;
import com.silvaniastudios.roads.client.model.TextureRegistryHandler;
import com.silvaniastudios.roads.client.model.diagonal.loaders.Diagonal11MirrorModelLoader;
import com.silvaniastudios.roads.client.model.diagonal.loaders.Diagonal11ModelLoader;
import com.silvaniastudios.roads.client.model.diagonal.loaders.Diagonal12MirrorModelLoader;
import com.silvaniastudios.roads.client.model.diagonal.loaders.Diagonal12ModelLoader;
import com.silvaniastudios.roads.client.model.diagonal.loaders.Diagonal14MirrorModelLoader;
import com.silvaniastudios.roads.client.model.diagonal.loaders.Diagonal14ModelLoader;
import com.silvaniastudios.roads.client.model.diagonal.loaders.Diagonal18MirrorModelLoader;
import com.silvaniastudios.roads.client.model.diagonal.loaders.Diagonal18ModelLoader;
import com.silvaniastudios.roads.client.model.diagonal.loaders.Diagonal24MirrorModelLoader;
import com.silvaniastudios.roads.client.model.diagonal.loaders.Diagonal24ModelLoader;
import com.silvaniastudios.roads.client.model.diagonal.loaders.Diagonal28MirrorModelLoader;
import com.silvaniastudios.roads.client.model.diagonal.loaders.Diagonal28ModelLoader;
import com.silvaniastudios.roads.client.model.diagonal.loaders.Diagonal38MirrorModelLoader;
import com.silvaniastudios.roads.client.model.diagonal.loaders.Diagonal38ModelLoader;
import com.silvaniastudios.roads.client.model.diagonal.loaders.Diagonal48MirrorModelLoader;
import com.silvaniastudios.roads.client.model.diagonal.loaders.Diagonal48ModelLoader;
import com.silvaniastudios.roads.client.model.paint.loaders.customs.CustomMetaPaintModel;
import com.silvaniastudios.roads.client.model.paint.loaders.customs.CustomPaint2x1Model;
import com.silvaniastudios.roads.client.model.paint.loaders.customs.CustomPaintModel;
import com.silvaniastudios.roads.client.model.paint.loaders.CustomPaintModelLoader;
import com.silvaniastudios.roads.client.model.paint.loaders.PaintLoaderBase;
import com.silvaniastudios.roads.client.model.paint.loaders.customs.CustomWallPaintModel;
import com.silvaniastudios.roads.client.model.paint.loaders.lines.*;

import com.silvaniastudios.roads.registries.DynamicBlockRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(FurenikusRoads.MODID + ":" + id, "inventory"));
	}

	@Override
	public void preInit() {
		MinecraftForge.EVENT_BUS.register(ModelBakeHandler.instance);
		MinecraftForge.EVENT_BUS.register(TextureRegistryHandler.instance);

		ModelLoaderRegistry.registerLoader(new Diagonal11ModelLoader());
		ModelLoaderRegistry.registerLoader(new Diagonal12ModelLoader());
		ModelLoaderRegistry.registerLoader(new Diagonal14ModelLoader());
		ModelLoaderRegistry.registerLoader(new Diagonal24ModelLoader());
		ModelLoaderRegistry.registerLoader(new Diagonal18ModelLoader());
		ModelLoaderRegistry.registerLoader(new Diagonal28ModelLoader());
		ModelLoaderRegistry.registerLoader(new Diagonal38ModelLoader());
		ModelLoaderRegistry.registerLoader(new Diagonal48ModelLoader());

		ModelLoaderRegistry.registerLoader(new Diagonal11MirrorModelLoader());
		ModelLoaderRegistry.registerLoader(new Diagonal12MirrorModelLoader());
		ModelLoaderRegistry.registerLoader(new Diagonal14MirrorModelLoader());
		ModelLoaderRegistry.registerLoader(new Diagonal24MirrorModelLoader());
		ModelLoaderRegistry.registerLoader(new Diagonal18MirrorModelLoader());
		ModelLoaderRegistry.registerLoader(new Diagonal28MirrorModelLoader());
		ModelLoaderRegistry.registerLoader(new Diagonal38MirrorModelLoader());
		ModelLoaderRegistry.registerLoader(new Diagonal48MirrorModelLoader());
		
		//custom paints
		for (int i = 0; i < DynamicBlockRegistry.customPaints.size(); i++) {
			CustomPaintBlock block = DynamicBlockRegistry.customPaints.get(i);
			String name = block.getRegistryName().toString().replace("furenikusroads:", "");

			switch (block.getPaintType()) {
				case ICON_1x1:
					ModelLoaderRegistry.registerLoader(new CustomPaintModelLoader(name, new CustomPaintModel()));
					break;
				case WALL_ICON_1x1:
					ModelLoaderRegistry.registerLoader(new CustomPaintModelLoader(name, new CustomWallPaintModel()));
					break;
				case MULTI_2x1: //TODO and below
					break;
				case MULTI_3x1:
					break;
				case MULTI_4x1:
					break;
				case LARGE_TEXT:
					break;
			}
		}

		//other paints
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new PaintLineModel(), "line_", "_straight_full"));//LineStraightFullModelLoader());
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new PaintLineDoubleModel(), "line_", "_straight_double"));//LineStraightDoubleModelLoader());
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new PaintLineThickModel(), "line_", "_straight_thick"));//LineStraightThickModelLoader());
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new PaintLineDoubleThickModel(), "line_", "_straight_double_thick"));//LineStraightDoubleThickModelLoader());
		
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new PaintLineSideDoubleModel(), "line_", "_side_double"));//new LineSideDoubleModelLoader());
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new PaintLineSideDoubleThickModel(), "line_", "_side_double_thick"));//new LineSideDoubleThickModelLoader());
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new PaintLineSideSingleModel(), "line_", "_side_single"));//new LineSideSingleModelLoader());
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new PaintLineSideSingleThickModel(), "line_", "_side_single_thick"));//new LineSideSingleThickModelLoader());
		
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new PaintLineFarSideModel(), "line_", "_far_side"));//new LineFarSideModelLoader());
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new PaintLineFarSideThickModel(), "line_", "_far_side_thick"));//new LineFarSideThickModelLoader());

		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new PaintLineMiddleHalfDoubleModel(), "line_", "_middle_half_double")); //line_middle_half_double
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new PaintLineMiddleDashDoubleModel(), "line_", "_middle_dash_double")); //line_middle_dash_double
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new PaintLineMiddleShortModel(), "line_", "_middle_short")); //line_middle_short
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new PaintLineFilterLaneModel(), "line_", "_filter_lane")); //line_filter_lane
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new PaintLineSideShortModel(), "line_", "_side_short")); //line_side_short
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new PaintLineThinCrossingModel(), "line_", "_thin_crossing")); //line_thin_crossing

		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomPaintModel(), "", "_wheelchair_icon"));
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomPaintModel(), "", "_chevron"));

		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomPaint2x1Model(), "", "_pedestrian"));
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomPaint2x1Model(), "", "_merge_arrow"));
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomPaint2x1Model(), "", "_give_way"));

		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomMetaPaintModel(), "", "_chevron_mid"));
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomMetaPaintModel(), "", "_chevron_mid_left"));
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomMetaPaintModel(), "", "_chevron_mid_right"));

		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomMetaPaintModel(), "", "_junction_side_line_connection"));
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomMetaPaintModel(), "", "_junction_side_line_connection_thin"));
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomMetaPaintModel(), "", "_junction_side_line_connection_thick_thick"));
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomMetaPaintModel(), "", "_junction_mid_line_connection"));
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomMetaPaintModel(), "", "_junction_a"));
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomMetaPaintModel(), "", "_junction_b"));

		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomWallPaintModel(), "paint_letter_", "_ab"));
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomWallPaintModel(), "paint_letter_", "_cd"));
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomWallPaintModel(), "paint_letter_", "_ef"));
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomWallPaintModel(), "paint_letter_", "_gh"));
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomWallPaintModel(), "paint_letter_", "_ij"));
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomWallPaintModel(), "paint_letter_", "_kl"));
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomWallPaintModel(), "paint_letter_", "_mn"));
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomWallPaintModel(), "paint_letter_", "_op"));
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomWallPaintModel(), "paint_letter_", "_qr"));
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomWallPaintModel(), "paint_letter_", "_st"));
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomWallPaintModel(), "paint_letter_", "_uv"));
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomWallPaintModel(), "paint_letter_", "_wx"));
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomWallPaintModel(), "paint_letter_", "_yz"));

		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomWallPaintModel(), "paint_letter_", "_01"));
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomWallPaintModel(), "paint_letter_", "_23"));
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomWallPaintModel(), "paint_letter_", "_45"));
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomWallPaintModel(), "paint_letter_", "_67"));
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomWallPaintModel(), "paint_letter_", "_89"));
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomWallPaintModel(), "paint_letter_", "_punct_question_exclamation"));
		ModelLoaderRegistry.registerLoader(new PaintLoaderBase(new CustomWallPaintModel(), "paint_letter_", "_punct_hash_slash"));
	}

	@Override
	public void init() {
		super.init();
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new ModBlockColours(), FRBlocks.road_block_grass);
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ModItemColours(), FRBlocks.road_block_grass);
		
		MinecraftForge.EVENT_BUS.register(BoundingBoxDraw.class);

		if (!RoadsConfig.general.performanceMode) {
			ClientRegistry.bindTileEntitySpecialRenderer(PaintFillerEntity.class, new PaintFillerRenderer());
			ClientRegistry.bindTileEntitySpecialRenderer(TarDistillerEntity.class, new TarDistillerRenderer());
			ClientRegistry.bindTileEntitySpecialRenderer(RoadFactoryEntity.class, new RoadFactoryRenderer());
			ClientRegistry.bindTileEntitySpecialRenderer(CrusherEntity.class, new CrusherRenderer());
			ClientRegistry.bindTileEntitySpecialRenderer(TarmacCutterEntity.class, new TarmacCutterRenderer());

			
			ClientRegistry.bindTileEntitySpecialRenderer(PaintOvenEntity.class, new PaintOvenRenderer());
		}
		ClientRegistry.bindTileEntitySpecialRenderer(PaintFillerHopperEntity.class, new PaintFillerHopperRenderer()); //Has actual utilisation in the renderer so needs to always be enabled
	}

	public void postInit() {
		super.postInit();
		FRBlocks.registerClientItemModels();
	}

	@Override
	public void openGui(int guiId) {
		if (guiId == 0) { Minecraft.getMinecraft().displayGuiScreen(new GuiPaintGun()); }
	}
}
