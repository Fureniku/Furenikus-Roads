package com.silvaniastudios.roads.network;

import com.silvaniastudios.roads.blocks.tileentities.compactor.CompactorContainer;
import com.silvaniastudios.roads.blocks.tileentities.compactor.CompactorElectricEntity;
import com.silvaniastudios.roads.blocks.tileentities.crusher.CrusherContainer;
import com.silvaniastudios.roads.blocks.tileentities.crusher.CrusherElectricEntity;
import com.silvaniastudios.roads.blocks.tileentities.distiller.TarDistillerContainer;
import com.silvaniastudios.roads.blocks.tileentities.distiller.TarDistillerElectricEntity;
import com.silvaniastudios.roads.blocks.tileentities.distiller.TarDistillerEntity;
import com.silvaniastudios.roads.blocks.tileentities.fabricator.FabricatorContainer;
import com.silvaniastudios.roads.blocks.tileentities.fabricator.FabricatorElectricEntity;
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

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientGuiUpdatePacket implements IMessage {
	
	public ClientGuiUpdatePacket(){}
	
	private int id;
	private int amount;
	
	public ClientGuiUpdatePacket(int id, int amount) {
		this.id = id;
		this.amount = amount;
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(id);
		buf.writeInt(amount);
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		id = buf.readInt();
		amount = buf.readInt();
	}
	
	public static class Handler implements IMessageHandler<ClientGuiUpdatePacket, IMessage> {

		@Override
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(ClientGuiUpdatePacket message, MessageContext ctx) {
			EntityPlayer player = Minecraft.getMinecraft().player;

			int id = message.id;
			int amount = message.amount;
			
			if (player.openContainer instanceof TarDistillerContainer) {
				TarDistillerContainer ctr = (TarDistillerContainer) player.openContainer;
				TarDistillerEntity te = ctr.tileEntity;
				
				if (te instanceof TarDistillerElectricEntity && id == 0) {
					TarDistillerElectricEntity tdee = (TarDistillerElectricEntity) te;
					tdee.energy.setEnergy(amount);
				}
				
				if (id == 1 && te.fluidInput.getFluid() != null) {
					te.fluidInput.getFluid().amount = amount;
				}
				if (id == 2 && te.fluidOutput1.getFluid() != null) {
					te.fluidOutput1.getFluid().amount = amount;
				}
				if (id == 3 && te.fluidOutput2.getFluid() != null) {
					te.fluidOutput2.getFluid().amount = amount;
				}
			}
			
			if (player.openContainer instanceof CompactorContainer) {
				CompactorContainer ctr = (CompactorContainer) player.openContainer;
				
				if (ctr.tileEntity instanceof CompactorElectricEntity && id == 0) {
					CompactorElectricEntity tdee = (CompactorElectricEntity) ctr.tileEntity;
					tdee.energy.setEnergy(amount);
				}
			}
			
			if (player.openContainer instanceof CrusherContainer) {
				CrusherContainer ctr = (CrusherContainer) player.openContainer;
				
				if (ctr.tileEntity instanceof CrusherElectricEntity && id == 0) {
					CrusherElectricEntity tdee = (CrusherElectricEntity) ctr.tileEntity;
					tdee.energy.setEnergy(amount);
				}
			}
			
			if (player.openContainer instanceof FabricatorContainer) {
				FabricatorContainer ctr = (FabricatorContainer) player.openContainer;
				
				if (ctr.tileEntity instanceof FabricatorElectricEntity && id == 0) {
					FabricatorElectricEntity tdee = (FabricatorElectricEntity) ctr.tileEntity;
					tdee.energy.setEnergy(amount);
				}
			}
			
			//paint filler - energy white yellow red
			if (player.openContainer instanceof PaintFillerContainer) {
				PaintFillerContainer ctr = (PaintFillerContainer) player.openContainer;
				PaintFillerEntity te = ctr.tileEntity;
				
				if (te instanceof PaintFillerElectricEntity && id == 0) {
					PaintFillerElectricEntity tdee = (PaintFillerElectricEntity) te;
					tdee.energy.setEnergy(amount);
				}
				
				if (id == 1 && te.white_paint.getFluid() != null) {
					te.white_paint.getFluid().amount = amount;
				}
				if (id == 2 && te.yellow_paint.getFluid() != null) {
					te.yellow_paint.getFluid().amount = amount;
				}
				if (id == 3 && te.red_paint.getFluid() != null) {
					te.red_paint.getFluid().amount = amount;
				}
			}
			
			//paint oven - energy paint water
			if (player.openContainer instanceof PaintOvenContainer) {
				PaintOvenContainer ctr = (PaintOvenContainer) player.openContainer;
				PaintOvenEntity te = ctr.tileEntity;
				
				if (te instanceof PaintOvenElectricEntity && id == 0) {
					PaintOvenElectricEntity tdee = (PaintOvenElectricEntity) te;
					tdee.energy.setEnergy(amount);
				}
				
				if (id == 1 && te.paint.getFluid() != null) {
					te.paint.getFluid().amount = amount;
				}
				if (id == 2 && te.water.getFluid() != null) {
					te.water.getFluid().amount = amount;
				}
			}
			
			//road factory - energy tarfluid
			if (player.openContainer instanceof RoadFactoryContainer) {
				RoadFactoryContainer ctr = (RoadFactoryContainer) player.openContainer;
				RoadFactoryEntity te = ctr.tileEntity;
				
				if (te instanceof RoadFactoryElectricEntity && id == 0) {
					RoadFactoryElectricEntity tdee = (RoadFactoryElectricEntity) te;
					tdee.energy.setEnergy(amount);
				}
				
				if (id == 1 && te.tarFluid.getFluid() != null) {
					te.tarFluid.getFluid().amount = amount;
				}
			}
			
			//tarmac cutter
			if (player.openContainer instanceof TarmacCutterContainer) {
				TarmacCutterContainer ctr = (TarmacCutterContainer) player.openContainer;
				
				if (ctr.tileEntity instanceof TarmacCutterElectricEntity && id == 0) {
					TarmacCutterElectricEntity tdee = (TarmacCutterElectricEntity) ctr.tileEntity;
					tdee.energy.setEnergy(amount);
				}
			}
			return null;
		}
	}
}
