package com.silvaniastudios.roads.blocks.tileentities.fabricator;

import javax.annotation.Nonnull;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.tileentities.RoadTileEntity;
import com.silvaniastudios.roads.blocks.tileentities.recipes.FabricatorRecipes;
import com.silvaniastudios.roads.blocks.tileentities.recipes.RecipeRegistry;

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

public class FabricatorEntity extends RoadTileEntity implements ITickable, ICapabilityProvider {
	
	public int timerCount = 0;
	public int recipeId = 0;
	
	public FabricatorEntity() {}
	
	public Container createContainer(EntityPlayer player) {
		return new FabricatorContainer(player.inventory, this, false);
	}
	
	public ItemStackHandler inventory = new ItemStackHandler(8) {
		@Override
		public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
			if (slot != FabricatorContainer.OUTPUT) {
				return true;
			}
			return false;
		}
	};
	
	public FabricatorStackHandler interactable_inv = new FabricatorStackHandler(inventory, hasCapability(CapabilityEnergy.ENERGY, null));
	
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
			if (!inventory.getStackInSlot(FabricatorContainer.FUEL).isEmpty()) {
				fuel_remaining = TileEntityFurnace.getItemBurnTime(inventory.getStackInSlot(4));
				last_fuel_cap = fuel_remaining;
				if (inventory.getStackInSlot(FabricatorContainer.FUEL).getItem() == Items.LAVA_BUCKET) {
					inventory.setStackInSlot(FabricatorContainer.FUEL, new ItemStack(Items.BUCKET));
				} else {
					inventory.extractItem(FabricatorContainer.FUEL, 1, false);
				}
				sendUpdates();
			} else {
				timerCount = 0;
			}
		}
		
		if (timerCount < RoadsConfig.machine.fabricatorTickRate && fuel_remaining > 0) {
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
		for (int i = 0; i < RecipeRegistry.fabricatorRecipes.size(); i++) {
			FabricatorRecipes recipe = RecipeRegistry.fabricatorRecipes.get(i);
			ItemStack result = recipe.getCraftingResult(inventory, recipeId);
			if (result != ItemStack.EMPTY) {
				if (inventory.insertItem(FabricatorContainer.OUTPUT, result, true) == ItemStack.EMPTY) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void process() {
		FurenikusRoads.debug(2, "Fabricator at" + formatPosition(pos) + "processing");
		for (int i = 0; i < RecipeRegistry.fabricatorRecipes.size(); i++) {
			FabricatorRecipes recipe = RecipeRegistry.fabricatorRecipes.get(i);
			recipe.processRecipe(inventory, recipeId);
		}
	}
	
	@Override
	public void readNBT(NBTTagCompound nbt) {
		fuel_remaining = nbt.getInteger("fuel");
		last_fuel_cap = nbt.getInteger("fuel_last_used");
		recipeId = nbt.getInteger("recipeId");
		
		if (nbt.hasKey("items")) {
			inventory.deserializeNBT((NBTTagCompound) nbt.getTag("items"));
		}
	}
	
	@Override
	public NBTTagCompound writeNBT(NBTTagCompound nbt) {
		nbt.setInteger("fuel", fuel_remaining);
		nbt.setInteger("fuel_last_used", last_fuel_cap);
		nbt.setInteger("recipeId", recipeId);

		nbt.setTag("items", inventory.serializeNBT());
		return nbt;
	}

}
