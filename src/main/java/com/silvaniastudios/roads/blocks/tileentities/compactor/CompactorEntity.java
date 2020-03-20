package com.silvaniastudios.roads.blocks.tileentities.compactor;

import javax.annotation.Nonnull;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.tileentities.RoadTileEntity;
import com.silvaniastudios.roads.blocks.tileentities.paintoven.PaintOvenContainer;
import com.silvaniastudios.roads.blocks.tileentities.recipes.CompactorRecipes;
import com.silvaniastudios.roads.blocks.tileentities.recipes.RecipeRegistry;
import com.silvaniastudios.roads.items.ItemFragment;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class CompactorEntity extends RoadTileEntity implements ITickable, ICapabilityProvider {
	
	public int timerCount = 0;
	public int road_size = 15;
	
	public CompactorEntity() {}
	
	public Container createContainer(EntityPlayer player) {
		return new CompactorContainer(player.inventory, this, false);
	}
	
	public ItemStackHandler inventory = new ItemStackHandler(3) {
		@Override
		public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
			if (slot == CompactorContainer.FRAGMENTS && stack.getItem() instanceof ItemFragment) {
				return true;
			}
			return false;
		}
	};
	
	public CompactorStackHandler interactable_inv = new CompactorStackHandler(inventory, hasCapability(CapabilityEnergy.ENERGY, null));
	
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
			if (!inventory.getStackInSlot(PaintOvenContainer.FUEL).isEmpty()) {
				fuel_remaining = TileEntityFurnace.getItemBurnTime(inventory.getStackInSlot(4));
				last_fuel_cap = fuel_remaining;
				if (inventory.getStackInSlot(PaintOvenContainer.FUEL).getItem() == Items.LAVA_BUCKET) {
					inventory.setStackInSlot(PaintOvenContainer.FUEL, new ItemStack(Items.BUCKET));
				} else {
					inventory.extractItem(PaintOvenContainer.FUEL, 1, false);
				}
				sendUpdates();
			} else {
				timerCount = 0;
			}
		}
		
		if (timerCount < RoadsConfig.machine.compactorTickRate && fuel_remaining > 0) {
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
	
	public void process() {
		if (!world.isRemote) {
			FurenikusRoads.debug(2, "Compactor at" + formatPosition(pos) + "processing");
			ItemStack in = inventory.getStackInSlot(CompactorContainer.FRAGMENTS);
			if (in.getCount() > road_size) {
				ItemStack out = inventory.getStackInSlot(CompactorContainer.ROADS);
				if (out == ItemStack.EMPTY || out.getCount() < out.getMaxStackSize()) {
					ItemStack recipeOut = getRecipeResult();
					if (recipeOut.getCount() > 0) {
						if (out == ItemStack.EMPTY || (out.getItem() == recipeOut.getItem() && out.getCount() < out.getMaxStackSize())) {
							inventory.extractItem(CompactorContainer.FRAGMENTS, road_size+1, false);
							inventory.insertItem(CompactorContainer.ROADS, recipeOut, false);
							sendUpdates();
						}
					}
				}
			}
		}
	}
	
	public boolean shouldTick() {
		ItemStack in = inventory.getStackInSlot(CompactorContainer.FRAGMENTS);
		if (in.getCount() > road_size) {
			ItemStack out = inventory.getStackInSlot(CompactorContainer.ROADS);
			if (out.getCount() == 0 || (out.getItem() == getRecipeResult().getItem() && out.getCount() < out.getMaxStackSize())) {
				return true;
			}
		}
		return false;
	}
	
	public ItemStack getRecipeResult() {
		for (int i = 0; i < RecipeRegistry.compactorRecipes.size(); i++) {
			CompactorRecipes recipe = RecipeRegistry.compactorRecipes.get(i);
			ItemStack out = recipe.getCraftingResult(inventory, road_size);
			if (out.getCount() > 0 && inventory.insertItem(CompactorContainer.ROADS, out, true).getCount() == 0) {
				return out;
			}
		}
		return ItemStack.EMPTY;
	}
	
	@Override
	public void readNBT(NBTTagCompound nbt) {
		fuel_remaining = nbt.getInteger("fuel");
		last_fuel_cap = nbt.getInteger("fuel_last_used");
		road_size = nbt.getInteger("road_size");
		
		if (nbt.hasKey("items")) {
			inventory.deserializeNBT((NBTTagCompound) nbt.getTag("items"));
		}
	}
	
	@Override
	public NBTTagCompound writeNBT(NBTTagCompound nbt) {
		nbt.setInteger("fuel", fuel_remaining);
		nbt.setInteger("fuel_last_used", last_fuel_cap);
		nbt.setInteger("road_size", road_size);

		nbt.setTag("items", inventory.serializeNBT());
		return nbt;
	}
}
