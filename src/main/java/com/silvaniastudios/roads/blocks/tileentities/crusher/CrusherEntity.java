package com.silvaniastudios.roads.blocks.tileentities.crusher;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableMap;
import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.RoadBlock;
import com.silvaniastudios.roads.blocks.tileentities.RoadTileEntity;
import com.silvaniastudios.roads.items.FRItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.animation.TimeValues;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.model.animation.CapabilityAnimation;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class CrusherEntity extends RoadTileEntity implements ITickable, ICapabilityProvider {
	
	public int timerCount = 0;
	
	private final IAnimationStateMachine asm;
	private final TimeValues.VariableValue move;
	
	public CrusherEntity() {
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT) { 
			move = new TimeValues.VariableValue(0);
			asm = ModelLoaderRegistry.loadASM(new ResourceLocation(FurenikusRoads.MODID, "asms/block/crusher.json"), ImmutableMap.of("move", move));
		} else {
			move = null;
			asm = null;
		}
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
	
	public Container createContainer(EntityPlayer player) {
		return new CrusherContainer(player.inventory, this);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
		if (capability == CapabilityAnimation.ANIMATION_CAPABILITY) { 
			return true;
		}
		
		return super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory);
		}
		
		if (capability == CapabilityAnimation.ANIMATION_CAPABILITY) {
			return CapabilityAnimation.ANIMATION_CAPABILITY.cast(asm);
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
				inventory.extractItem(2, 1, false);
				sendUpdates();
			} else {
				timerCount = 0;
				return;
			}
		}
		
		if (world.isRemote) {
			move.setValue(timerCount);
		}
		
		if (timerCount < RoadsConfig.general.crusherTickRate) {
			if (shouldTick()) {
				timerCount++;
			} else {
				timerCount = 0;
			}
		} else {
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
	
	protected ItemStack getRecipes(ItemStack itemIn) {
		if (itemIn.getItem() instanceof ItemBlock) {
			ItemBlock ib = (ItemBlock) itemIn.getItem();
			
			if (ib.getBlock() == Blocks.STONE) { return new ItemStack(Blocks.COBBLESTONE, 1); }
			if (ib.getBlock() == Blocks.COBBLESTONE) { return new ItemStack(FRBlocks.generic_blocks, 1, 0); } //crushed rock
			if (ib.getBlock() == Blocks.GRAVEL) { return new ItemStack(Blocks.SAND, 1); }
			
			
			if (ib.getBlock() == FRBlocks.generic_blocks) {
				if (itemIn.getItemDamage() == 0) { return new ItemStack(Blocks.GRAVEL, 1); } //crushed rock
				if (itemIn.getItemDamage() == 1) { return new ItemStack(FRItems.cement_dust, 4); } //clinker
				if (itemIn.getItemDamage() == 2) { return new ItemStack(FRItems.cement_dust, 4); } //cement
				if (itemIn.getItemDamage() == 3) { return new ItemStack(FRItems.limestone_dust, 4); } //limestone
			}
			
			if (ib.getBlock() instanceof RoadBlock) {
				RoadBlock block = (RoadBlock) ib.getBlock();
				
				return new ItemStack(block.getFragmentItem(block), itemIn.getItemDamage() + 1);
			}
		}
		
		return ItemStack.EMPTY;
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
