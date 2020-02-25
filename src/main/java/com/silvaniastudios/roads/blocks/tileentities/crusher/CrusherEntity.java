package com.silvaniastudios.roads.blocks.tileentities.crusher;

import javax.annotation.Nonnull;

import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.RoadBlock;
import com.silvaniastudios.roads.blocks.tileentities.RoadTileEntity;
import com.silvaniastudios.roads.items.FRItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class CrusherEntity extends RoadTileEntity implements ITickable, ICapabilityProvider {
	
	public int timerCount = 0;
	
	public CrusherEntity() {

	}
	
	public ItemStackHandler inventory = new ItemStackHandler(3) {
		
		@Override
		public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
			return true;
		}
		
		@Override
		protected void onContentsChanged(int slot) {
			CrusherEntity.this.markDirty();
		}
	};
	
	public CrusherStackHandler interactable_inv = new CrusherStackHandler(inventory);
	
	public Container createContainer(EntityPlayer player) {
		return new CrusherContainer(player.inventory, this, false);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return getCapability(capability, facing) != null;
        }
		
		return super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (facing != null) {
				return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(interactable_inv);
			} else {
				return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory);
			}
		}
		return super.getCapability(capability, facing);
	}
	
	@Override
	public void update() {
		if (fuel_remaining > 0) {
			fuel_remaining--;
		} else if (fuel_remaining <= 0) {
			if (!inventory.getStackInSlot(2).isEmpty()) {
				fuel_remaining = TileEntityFurnace.getItemBurnTime(inventory.getStackInSlot(2));
				last_fuel_cap = fuel_remaining;
				if (inventory.getStackInSlot(2).getItem() == Items.LAVA_BUCKET) {
					inventory.setStackInSlot(2, new ItemStack(Items.BUCKET));
				} else {
					inventory.extractItem(2, 1, false);
				}
				sendUpdates();
			} else {
				timerCount = 0;
				return;
			}
		}
		
		if (timerCount < RoadsConfig.machine.crusherTickRate) {
			if (shouldTick()) {
				timerCount++;
			} else {
				timerCount = 0;
			}
		} else {
			process();
			timerCount = 0;
		}
	}
	
	public boolean shouldTick() {
		ItemStack itemIn = inventory.getStackInSlot(0);
		if (!itemIn.isEmpty()) {
			ItemStack itemOut = getRecipes(itemIn);
			if (!itemOut.isEmpty()) {
				if (inventory.getStackInSlot(1).isEmpty()) {
					return true;
				} else if (inventory.getStackInSlot(1).getItem() == itemOut.getItem() && (inventory.getStackInSlot(1).getCount() + itemOut.getCount()) <= itemOut.getMaxStackSize()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void process() {		
		if (!world.isRemote) {
			ItemStack itemIn = inventory.getStackInSlot(0);
			
			if (!itemIn.isEmpty()) {
				ItemStack itemOut = getRecipes(itemIn);
				if (!itemOut.isEmpty()) {
					if (inventory.getStackInSlot(1).isEmpty()) {
						inventory.setStackInSlot(1, itemOut);
						itemIn.setCount(itemIn.getCount()-1);
					} else if (inventory.getStackInSlot(1).getItem() == itemOut.getItem() && (inventory.getStackInSlot(1).getCount() + itemOut.getCount()) <= itemOut.getMaxStackSize()) {
						inventory.getStackInSlot(1).setCount(inventory.getStackInSlot(1).getCount() + itemOut.getCount());
						itemIn.setCount(itemIn.getCount()-1);
					}
				}
			}
		}
	}
	
	protected ItemStack getRecipes(ItemStack itemIn) {
		if (itemIn.getItem() instanceof ItemBlock) {
			ItemBlock ib = (ItemBlock) itemIn.getItem();
			
			if (ib.getBlock() == Blocks.STONE) { return new ItemStack(Blocks.COBBLESTONE, 1); }
			if (ib.getBlock() == Blocks.COBBLESTONE) { return new ItemStack(Blocks.GRAVEL); } 
			if (ib.getBlock() == Blocks.GRAVEL) { return new ItemStack(FRBlocks.generic_blocks, 1, 0); } //crushed rock
			
			
			if (ib.getBlock() == FRBlocks.generic_blocks) {
				if (itemIn.getItemDamage() == 0) { return new ItemStack(Blocks.SAND, 1); } //crushed rock
				if (itemIn.getItemDamage() == 1) { return new ItemStack(FRItems.cement_dust, 1); } //clinker
				if (itemIn.getItemDamage() == 2) { return new ItemStack(FRItems.cement_dust, 1); } //cement
				if (itemIn.getItemDamage() == 3) { return new ItemStack(FRItems.limestone_dust, 4); } //limestone
			}
			
			if (ib.getBlock() instanceof RoadBlock) {
				RoadBlock block = (RoadBlock) ib.getBlock();
				
				return new ItemStack(block.getFragmentItem(block), itemIn.getItemDamage() + 1);
			}
		} else {
			if (itemIn.getItem() == FRItems.clinker_mix) {
				return new ItemStack(FRItems.cement_dust, 1);
			}
		}
		
		return ItemStack.EMPTY;
	}
	
	@Override
	public void readNBT(NBTTagCompound nbt) {
		if (nbt.hasKey("items")) {
			inventory.deserializeNBT((NBTTagCompound) nbt.getTag("items"));
		}
		
		fuel_remaining = nbt.getInteger("fuel");
		last_fuel_cap = nbt.getInteger("fuel_last_used");
	}
	
	@Override
	public NBTTagCompound writeNBT(NBTTagCompound nbt) {
		nbt.setTag("items", inventory.serializeNBT());
		
		nbt.setInteger("fuel", fuel_remaining);
		nbt.setInteger("fuel_last_used", last_fuel_cap);
		return nbt;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		return writeNBT(nbt);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		readNBT(nbt);
	}
}
