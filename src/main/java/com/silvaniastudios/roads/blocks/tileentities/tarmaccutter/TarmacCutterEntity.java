package com.silvaniastudios.roads.blocks.tileentities.tarmaccutter;

import javax.annotation.Nonnull;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.RoadBlock;
import com.silvaniastudios.roads.blocks.tileentities.RoadTileEntity;
import com.silvaniastudios.roads.items.FRItems;
import com.silvaniastudios.roads.items.TarmacCutterBlade;

import net.minecraft.entity.player.EntityPlayer;
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
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TarmacCutterEntity extends RoadTileEntity implements ITickable, ICapabilityProvider {
	
	public int timerCount = 0;
	
	public TarmacCutterEntity() {}
	
	public ItemStackHandler inventory = new ItemStackHandler(5) {
		
		@Override
		public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
			return true;
		}
		
		@Override
		protected void onContentsChanged(int slot) {
			TarmacCutterEntity.this.markDirty();
		}
	};
	
	public TarmacCutterStackHandler interactable_inv = new TarmacCutterStackHandler(inventory, hasCapability(CapabilityEnergy.ENERGY, null));
	
	public Container createContainer(EntityPlayer player) {
		return new TarmacCutterContainer(player.inventory, this, false);
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
			if (!inventory.getStackInSlot(TarmacCutterContainer.FUEL).isEmpty()) {
				fuel_remaining = TileEntityFurnace.getItemBurnTime(inventory.getStackInSlot(TarmacCutterContainer.FUEL));
				last_fuel_cap = fuel_remaining;
				if (inventory.getStackInSlot(4).getItem() == Items.LAVA_BUCKET) {
					inventory.setStackInSlot(4, new ItemStack(Items.BUCKET));
				} else {
					inventory.extractItem(4, 1, false);
				}
				sendUpdates();
			} else {
				timerCount = 0;
				return;
			}
		}
		
		if (timerCount < RoadsConfig.machine.tarmacCutterTickRate) {
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
		FurenikusRoads.debug(2, "Tarmac Cutter at" + formatPosition(pos) + "processing");
		if (!world.isRemote) {
			ItemStack in = inventory.getStackInSlot(TarmacCutterContainer.INPUT);
			ItemStack blade = inventory.getStackInSlot(TarmacCutterContainer.BLADE);
			ItemStack blockOut = inventory.getStackInSlot(TarmacCutterContainer.OUTPUT_1);
			ItemStack fragOut = inventory.getStackInSlot(TarmacCutterContainer.OUTPUT_2); //If you didn't read this var name in an angry call of duty man voice... you're lying you did.
			
			if (blade.getItem() instanceof TarmacCutterBlade) {
				if (blade.getItemDamage() < blade.getMaxDamage()) {
					int cutSize = getCutSize(blade);
					
					if (in.getItemDamage() >= cutSize && in.getItem() instanceof ItemBlock) {
						ItemBlock ib = (ItemBlock) in.getItem();
						
						if (ib.getBlock() instanceof RoadBlock) {
							RoadBlock block = (RoadBlock) ib.getBlock();
							if (blockOut.isEmpty() || (blockOut.getItem() == in.getItem() && blockOut.getItemDamage() == in.getItemDamage() - cutSize)) {
								if (fragOut.isEmpty() || (fragOut.getItem() == block.getFragmentItem() && fragOut.getCount() <= fragOut.getMaxStackSize() - cutSize)) {
									in.setCount(in.getCount() - 1);
									
									if (blockOut.isEmpty()) {
										inventory.setStackInSlot(2, new ItemStack(in.getItem(), 1, in.getItemDamage()-cutSize));
									} else {
										blockOut.setCount(blockOut.getCount() + 1);
									}
									
									if (fragOut.isEmpty()) { 
										ItemStack fragment = new ItemStack(block.getFragmentItem(), cutSize);
										inventory.setStackInSlot(3, fragment);
									} else {
										fragOut.setCount(fragOut.getCount() + cutSize);
									}
									
									blade.setItemDamage(blade.getItemDamage() + 1);
									sendUpdates();
								}
							}
						}
					}
				} else {
					inventory.setStackInSlot(TarmacCutterContainer.BLADE, ItemStack.EMPTY);
				}
			}
		}
	}
	
	public boolean shouldTick() {
		ItemStack in = inventory.getStackInSlot(0);
		ItemStack blade = inventory.getStackInSlot(1);
		ItemStack blockOut = inventory.getStackInSlot(2);
		ItemStack fragOut = inventory.getStackInSlot(3);
		
		if (blade.getItem() instanceof TarmacCutterBlade && blade.getItemDamage() < blade.getMaxDamage()) {
			int cutSize = getCutSize(blade);
			
			if (in.getItemDamage() >= cutSize && in.getItem() instanceof ItemBlock) {
				ItemBlock ib = (ItemBlock) in.getItem();
				
				if (ib.getBlock() instanceof RoadBlock) {
					RoadBlock block = (RoadBlock) ib.getBlock();
					if (blockOut.isEmpty() || (blockOut.getItem() == in.getItem() && blockOut.getItemDamage() == in.getItemDamage() - cutSize)) {
						if (fragOut.isEmpty() || (fragOut.getItem() == block.getFragmentItem() && fragOut.getCount() <= fragOut.getMaxStackSize() - cutSize)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public int getCutSize(ItemStack stack) {
		if (stack.getItem() instanceof TarmacCutterBlade) {
			TarmacCutterBlade blade = (TarmacCutterBlade) stack.getItem();
			return blade.size;
		}
		if (stack.getItem() == FRItems.tarmac_cutter_blade_1_iron || stack.getItem() == FRItems.tarmac_cutter_blade_1_gold || stack.getItem() == FRItems.tarmac_cutter_blade_1_diamond) { return 1; }
		if (stack.getItem() == FRItems.tarmac_cutter_blade_2_iron || stack.getItem() == FRItems.tarmac_cutter_blade_2_gold || stack.getItem() == FRItems.tarmac_cutter_blade_2_diamond) { return 2; }
		if (stack.getItem() == FRItems.tarmac_cutter_blade_4_iron || stack.getItem() == FRItems.tarmac_cutter_blade_4_gold || stack.getItem() == FRItems.tarmac_cutter_blade_4_diamond) { return 4; }
		if (stack.getItem() == FRItems.tarmac_cutter_blade_8_iron || stack.getItem() == FRItems.tarmac_cutter_blade_8_gold || stack.getItem() == FRItems.tarmac_cutter_blade_8_diamond) { return 8; }
		return 0;
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
