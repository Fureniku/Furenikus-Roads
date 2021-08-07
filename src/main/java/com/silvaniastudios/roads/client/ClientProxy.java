package com.silvaniastudios.roads.client;

import com.silvaniastudios.roads.CommonProxy;
import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.FRBlocks;
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
import com.silvaniastudios.roads.client.model.paint.loaders.CustomPaintModelLoader;
import com.silvaniastudios.roads.client.model.paint.loaders.LinePaintModelLoader;

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
		
		//paints
		ModelLoaderRegistry.registerLoader(new CustomPaintModelLoader());
		
		ModelLoaderRegistry.registerLoader(new LinePaintModelLoader());
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
