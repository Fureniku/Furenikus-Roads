package com.silvaniastudios.roads.blocks.tileentities;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class RoadTileEntity extends TileEntity {
	
	public int fuel_remaining = 0;
	public int last_fuel_cap = 0;
	
	boolean hasBasePlate;
	
	public RoadTileEntity() {}

	public boolean canInteractWith(EntityPlayer playerIn) {
        return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
    }

	public IBlockState getState() { 
		return world.getBlockState(pos);
	}
	
	public boolean isLoaded() {
		return this.hasWorld() && this.hasPosition() ? this.getWorld().isBlockLoaded(this.getPos()) : false;
	}
	
	public boolean hasPosition() {
		return this.pos != null && this.pos != BlockPos.ORIGIN;
	}
	
	public void sendUpdates() {
		this.markDirty();
		
		if (this.isLoaded()) {
			final IBlockState state = this.getState();
			this.getWorld().notifyBlockUpdate(this.pos, state, state, 3);
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setBoolean("base_plate", hasBasePlate);
		return writeNBT(nbt);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		hasBasePlate = nbt.getBoolean("base_plate");
		readNBT(nbt);
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}
	
	public void readNBT(NBTTagCompound nbt) {
	}
	
	public NBTTagCompound writeNBT(NBTTagCompound nbt) {
		return nbt;
	}
	
	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return oldState.getBlock() != newSate.getBlock();
    }
	
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(getPos(), 0, this.getUpdateTag());
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
		this.readNBT(pkt.getNbtCompound());
		this.getWorld().notifyBlockUpdate(this.pos, this.getState(), this.getState(), 3);
	}
	
	public boolean isDye(ItemStack stack, String name) {
		if (!stack.isEmpty()) {
			for (int id : OreDictionary.getOreIDs(stack)) {
				if (OreDictionary.getOreName(id).equals(name)) {
	                return true;
				}
			}
		}
		return false;
	}
	
	public String formatPosition(BlockPos pos) {
		return " X: " + pos.getX() + ", Y: " + pos.getY() + ", Z: " + pos.getZ() + " ";
	}
}
