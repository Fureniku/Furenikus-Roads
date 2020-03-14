package com.silvaniastudios.roads;

import com.silvaniastudios.roads.blocks.tileentities.compactor.CompactorContainer;
import com.silvaniastudios.roads.blocks.tileentities.compactor.CompactorElectricEntity;
import com.silvaniastudios.roads.blocks.tileentities.compactor.CompactorEntity;
import com.silvaniastudios.roads.blocks.tileentities.crusher.CrusherContainer;
import com.silvaniastudios.roads.blocks.tileentities.crusher.CrusherElectricEntity;
import com.silvaniastudios.roads.blocks.tileentities.crusher.CrusherEntity;
import com.silvaniastudios.roads.blocks.tileentities.distiller.TarDistillerContainer;
import com.silvaniastudios.roads.blocks.tileentities.distiller.TarDistillerElectricEntity;
import com.silvaniastudios.roads.blocks.tileentities.distiller.TarDistillerEntity;
import com.silvaniastudios.roads.blocks.tileentities.fabricator.FabricatorContainer;
import com.silvaniastudios.roads.blocks.tileentities.fabricator.FabricatorElectricEntity;
import com.silvaniastudios.roads.blocks.tileentities.fabricator.FabricatorEntity;
import com.silvaniastudios.roads.blocks.tileentities.paintfiller.PaintFillerContainer;
import com.silvaniastudios.roads.blocks.tileentities.paintfiller.PaintFillerElectricEntity;
import com.silvaniastudios.roads.blocks.tileentities.paintfiller.PaintFillerEntity;
import com.silvaniastudios.roads.blocks.tileentities.paintoven.PaintOvenContainer;
import com.silvaniastudios.roads.blocks.tileentities.paintoven.PaintOvenElectricEntity;
import com.silvaniastudios.roads.blocks.tileentities.paintoven.PaintOvenEntity;
import com.silvaniastudios.roads.blocks.tileentities.roadfactory.RoadFactoryContainer;
import com.silvaniastudios.roads.blocks.tileentities.roadfactory.RoadFactoryElectricEntity;
import com.silvaniastudios.roads.blocks.tileentities.roadfactory.RoadFactoryEntity;
import com.silvaniastudios.roads.blocks.tileentities.tarmaccutter.TarmacCutterContainer;
import com.silvaniastudios.roads.blocks.tileentities.tarmaccutter.TarmacCutterElectricEntity;
import com.silvaniastudios.roads.blocks.tileentities.tarmaccutter.TarmacCutterEntity;
import com.silvaniastudios.roads.client.gui.GuiCompactor;
import com.silvaniastudios.roads.client.gui.GuiCrusher;
import com.silvaniastudios.roads.client.gui.GuiFabricator;
import com.silvaniastudios.roads.client.gui.GuiPaintFiller;
import com.silvaniastudios.roads.client.gui.GuiPaintOven;
import com.silvaniastudios.roads.client.gui.GuiRoadFactory;
import com.silvaniastudios.roads.client.gui.GuiTarDistiller;
import com.silvaniastudios.roads.client.gui.GuiTarmacCutter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
		if (te != null) {
			if (ID == 1) {
				return new PaintFillerContainer(player.inventory, (PaintFillerEntity) te, false);
			}
			if (ID == 2) {
				return new TarDistillerContainer(player.inventory, (TarDistillerEntity) te, false);
			}
			if (ID == 3) {
				return new RoadFactoryContainer(player.inventory, (RoadFactoryEntity) te, false);
			}
			if (ID == 4) {
				return new TarmacCutterContainer(player.inventory, (TarmacCutterEntity) te, false);
			}
			if (ID == 5) {
				return new CrusherContainer(player.inventory, (CrusherEntity) te, false);
			}
			
			
			if (ID == 6) {
				return new PaintFillerContainer(player.inventory, (PaintFillerElectricEntity) te, true);
			}
			if (ID == 7) {
				return new TarDistillerContainer(player.inventory, (TarDistillerElectricEntity) te, true);
			}
			if (ID == 8) {
				return new RoadFactoryContainer(player.inventory, (RoadFactoryElectricEntity) te, true);
			}
			if (ID == 9) {
				return new TarmacCutterContainer(player.inventory, (TarmacCutterElectricEntity) te, true);
			}
			if (ID == 10) {
				return new CrusherContainer(player.inventory, (CrusherElectricEntity) te, true);
			}
			
			if (ID == 11) {
				return new PaintOvenContainer(player.inventory, (PaintOvenEntity) te, false);
			}
			if (ID == 12) { 
				return new PaintOvenContainer(player.inventory, (PaintOvenElectricEntity) te, true);
			}
			
			if (ID == 13) {
				return new CompactorContainer(player.inventory, (CompactorEntity) te, false);
			}
			if (ID == 14) { 
				return new CompactorContainer(player.inventory, (CompactorElectricEntity) te, true);
			}
			
			if (ID == 15) {
				return new FabricatorContainer(player.inventory, (FabricatorEntity) te, false);
			}
			if (ID == 16) { 
				return new FabricatorContainer(player.inventory, (FabricatorElectricEntity) te, true);
			}
			
			System.out.println("You forgot to register GUI ID " + ID + " server-side, idiot.");
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
		if (te != null) {
			if (ID == 1) {
				PaintFillerEntity entity = (PaintFillerEntity) te;
				return new GuiPaintFiller(entity, new PaintFillerContainer(player.inventory, entity, false), false);
			}
			if (ID == 2) {
				TarDistillerEntity entity = (TarDistillerEntity) te;
				return new GuiTarDistiller(entity, new TarDistillerContainer(player.inventory, entity, false), false);
			}
			if (ID == 3) {
				RoadFactoryEntity entity = (RoadFactoryEntity) te;
				return new GuiRoadFactory(entity, new RoadFactoryContainer(player.inventory, entity, false), false);
			}
			if (ID == 4) {
				TarmacCutterEntity entity = (TarmacCutterEntity) te;
				return new GuiTarmacCutter(entity, new TarmacCutterContainer(player.inventory, entity, false), false);
			}
			if (ID == 5) {
				CrusherEntity entity = (CrusherEntity) te;
				return new GuiCrusher(entity, new CrusherContainer(player.inventory, entity, false), false);
			}
			
			if (ID == 6) {
				PaintFillerElectricEntity entity = (PaintFillerElectricEntity) te;
				return new GuiPaintFiller(entity, new PaintFillerContainer(player.inventory, entity, true), true);
			}
			if (ID == 7) {
				TarDistillerElectricEntity entity = (TarDistillerElectricEntity) te;
				return new GuiTarDistiller(entity, new TarDistillerContainer(player.inventory, entity, true), true);
			}
			if (ID == 8) {
				RoadFactoryElectricEntity entity = (RoadFactoryElectricEntity) te;
				return new GuiRoadFactory(entity, new RoadFactoryContainer(player.inventory, entity, true), true);
			}
			if (ID == 9) {
				TarmacCutterElectricEntity entity = (TarmacCutterElectricEntity) te;
				return new GuiTarmacCutter(entity, new TarmacCutterContainer(player.inventory, entity, true), true);
			}
			if (ID == 10) {
				CrusherElectricEntity entity = (CrusherElectricEntity) te;
				return new GuiCrusher(entity, new CrusherContainer(player.inventory, entity, true), true);
			}
			
			if (ID == 11) {
				PaintOvenEntity entity = (PaintOvenEntity) te;
				return new GuiPaintOven(entity, new PaintOvenContainer(player.inventory, entity, false), false);
			}
			if (ID == 12) {
				PaintOvenElectricEntity entity = (PaintOvenElectricEntity) te;
				return new GuiPaintOven(entity, new PaintOvenContainer(player.inventory, entity, true), true);
			}
			
			if (ID == 13) {
				CompactorEntity entity = (CompactorEntity) te;
				return new GuiCompactor(entity, new CompactorContainer(player.inventory, entity, false), false);
			}
			if (ID == 14) {
				CompactorElectricEntity entity = (CompactorElectricEntity) te;
				return new GuiCompactor(entity, new CompactorContainer(player.inventory, entity, true), true);
			}
			
			if (ID == 15) {
				FabricatorEntity entity = (FabricatorEntity) te;
				return new GuiFabricator(entity, new FabricatorContainer(player.inventory, entity, false), false);
			}
			if (ID == 16) {
				FabricatorElectricEntity entity = (FabricatorElectricEntity) te;
				return new GuiFabricator(entity, new FabricatorContainer(player.inventory, entity, true), true);
			}
			
			System.out.println("You forgot to register GUI ID " + ID + " client-side, idiot.");
		}
		return null;
	}

}
