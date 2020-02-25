package com.silvaniastudios.roads.blocks.tileentities.distiller;

import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.tileentities.FREnergyStorage;
import com.silvaniastudios.roads.blocks.tileentities.roadfactory.RoadFactoryBlock;
import com.silvaniastudios.roads.blocks.tileentities.roadfactory.RoadFactoryEntity;
import com.silvaniastudios.roads.fluids.FRFluids;
import com.silvaniastudios.roads.items.FRItems;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

public class TarDistillerElectricEntity extends TarDistillerEntity implements ITickable, ICapabilityProvider {
	
	public boolean isProcessing = false;
	int consumedEnergy = 0;

	public TarDistillerElectricEntity() {}
	
	public FREnergyStorage energy = new FREnergyStorage(RoadsConfig.machine.electricTarDistillerEnergyStorage, RoadsConfig.machine.electricTarDistillerEnergyTransferRate) {};
	
	public Container createContainer(EntityPlayer player) {
		return new TarDistillerContainer(player.inventory, this, true);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityEnergy.ENERGY) {
			return getCapability(capability, facing) != null;
		}
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			return true;
		}
		return super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityEnergy.ENERGY) {
			if (facing != null) {
				return CapabilityEnergy.ENERGY.cast(energy);
			}
		}
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (facing != null) {
				return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(interactable_inv);
			} else {
				return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory);
			}
		}
		
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			EnumFacing sideLeft = EnumFacing.WEST;
			EnumFacing sideRight = EnumFacing.EAST;
			EnumFacing sideBack = EnumFacing.SOUTH;
			
			IBlockState state = world.getBlockState(pos);
			if (state.getBlock() instanceof TarDistillerBlock) {
				TarDistillerBlock block = (TarDistillerBlock) state.getBlock();
				int meta = block.getMetaFromState(state);
				
				if (meta == 1) {
					sideLeft = EnumFacing.NORTH;
					sideRight = EnumFacing.SOUTH;
					sideBack = EnumFacing.EAST;
				}
				if (meta == 2) {
					sideLeft = EnumFacing.EAST;
					sideRight = EnumFacing.WEST;
					sideBack = EnumFacing.NORTH;
				}
				if (meta == 3) {
					sideLeft = EnumFacing.SOUTH;
					sideRight = EnumFacing.NORTH;
					sideBack = EnumFacing.WEST;
				}
				
				if (facing == sideLeft)  { CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(fluidInput); }
				if (facing == sideRight) { CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(fluidOutput1); }
				if (facing == sideBack)  { CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(fluidOutput2); }
			}
		}
		
		return super.getCapability(capability, facing);
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public void update() {
		if (fuel_remaining > 0) {
			fuel_remaining--;
		} else if (fuel_remaining <= 0) {
			if (inventory.getStackInSlot(TarDistillerContainer.FUEL).getItem() == Items.LAVA_BUCKET) {
				fuel_remaining = 20000;
				inventory.setStackInSlot(TarDistillerContainer.FUEL, new ItemStack(Items.BUCKET));
				sendUpdates();
			} else {
				timerCount = 0;
				return;
			}
		}
		
		if (timerCount < RoadsConfig.machine.tarDistillerTickRate) {
			if (shouldTick()) {
				timerCount++;
			} else {
				timerCount = 0;
			}
		} else {
			if (!world.isRemote) {
				boolean hasChanges = false;
				ItemStack input = inventory.getStackInSlot(TarDistillerContainer.INPUT);
				ItemStack output_1 = inventory.getStackInSlot(TarDistillerContainer.OUTPUT_1);
				
				if (validInput(input, null)) {
					ItemStack itemOut1 = output1(input, null);
					FluidStack fluidOut1 = fluidOut1(input, null);
					
					if (output_1.isEmpty() || output_1.getCount() + itemOut1.getCount() <= inventory.getSlotLimit(TarDistillerContainer.OUTPUT_1)) {
						boolean process = false;
						if (fluidOutput1.getFluid() == null) {
							fluidOutput1.setFluid(fluidOut1);
							process = true;
						} else if ((fluidOutput1.getFluidAmount() <= TANK_CAP - fluidOut1.amount && (fluidOutput1.getFluid().getFluid().equals(FRFluids.tar)) || fluidOutput1.getFluidAmount() <= 0)) {
							fluidOutput1.fill(fluidOut1, true);
							process = true;
						}
						
						if (process) {
							inventory.extractItem(TarDistillerContainer.INPUT, 1, false);
							
							if (output_1.isEmpty()) { 
								inventory.setStackInSlot(TarDistillerContainer.OUTPUT_1, itemOut1);
							} else {
								output_1.setCount(output_1.getCount()+itemOut1.getCount());
							}
							hasChanges = true;
						}
					}
				}
				
				ItemStack fluid_in = inventory.getStackInSlot(TarDistillerContainer.FLUID_IN);
				
				if (fluid_in.getUnlocalizedName().compareTo("item.forge.bucketFilled") == 0) {
					/*FluidStack fluidStack = FluidUtil.getFluidContained(fluid_in);
					if (fluidInput.getFluidAmount() <= TANK_CAP - 1000) {
						fluidInput.fill(fluidStack, true);
					}*/
					//When we support other fluids, this is where we'll import tham.
				}
				
				ItemStack fluid_out_1 = inventory.getStackInSlot(TarDistillerContainer.FLUID_OUT_1);
				ItemStack fluid_out_1_bucket = inventory.getStackInSlot(TarDistillerContainer.FLUID_OUT_1_BUCKET);
				//Should always be tar. Should.
				if (fluid_out_1_bucket.getItem() == Items.BUCKET && fluidOutput1 != null && fluid_out_1.isEmpty()) {
					if (fluidOutput1.getFluidAmount() >= 1000) {
						Fluid fluid = fluidOutput1.getFluid().getFluid();
						ItemStack bucket = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, fluid);

						fluidOutput1.drain(new FluidStack(fluid, 1000), true);
						inventory.setStackInSlot(TarDistillerContainer.FLUID_OUT_1, bucket);
						fluid_out_1_bucket.setCount(fluid_out_1_bucket.getCount() - 1);
						hasChanges = true;
					}
				}
				
				ItemStack fluid_out_2 = inventory.getStackInSlot(TarDistillerContainer.FLUID_OUT_2);
				ItemStack fluid_out_2_bucket = inventory.getStackInSlot(TarDistillerContainer.FLUID_OUT_1_BUCKET);
				//Could be anything tbh (although currently its always nothing).
				if (fluid_out_2_bucket.getItem() == Items.BUCKET && fluidOutput2 != null && fluid_out_2.isEmpty()) {
					if (fluidOutput2.getFluidAmount() >= 1000) {
						Fluid fluid = fluidOutput2.getFluid().getFluid();
						ItemStack bucket = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, fluid);
					
						fluidOutput2.drain(new FluidStack(fluid, 1000), true);
						inventory.setStackInSlot(TarDistillerContainer.FLUID_OUT_2, bucket);
						fluid_out_2_bucket.setCount(fluid_out_2_bucket.getCount() - 1);
						hasChanges = true;
					}
				}
				
				Block blockHere = world.getBlockState(pos).getBlock();
				
				if (blockHere != null) {
					EnumFacing getRight = EnumFacing.EAST;
					if (blockHere.getMetaFromState(getState()) == 0) { getRight = EnumFacing.EAST;  }
					if (blockHere.getMetaFromState(getState()) == 1) { getRight = EnumFacing.SOUTH; }
					if (blockHere.getMetaFromState(getState()) == 2) { getRight = EnumFacing.WEST;  }
					if (blockHere.getMetaFromState(getState()) == 3) { getRight = EnumFacing.NORTH; }
					
					IBlockState blockRight = world.getBlockState(pos.offset(getRight));
					if (blockRight.getBlock() instanceof RoadFactoryBlock) {
						if (world.getTileEntity(pos.offset(getRight)) != null) {
							RoadFactoryEntity rfEntity = (RoadFactoryEntity) world.getTileEntity(pos.offset(getRight));
							if ((rfEntity.tarFluid.getFluidAmount() + 1000 <= rfEntity.tarFluid.getCapacity()) && fluidOutput1.getFluidAmount() >= 1000) {
								FluidStack fluid = fluidOutput1.drain(new FluidStack(FRFluids.tar, 1000), true);
								rfEntity.tarFluid.fill(fluid, true);
								rfEntity.sendUpdates();
								hasChanges = true;
							}
						}
					}
				}
				if (hasChanges) { sendUpdates(); }	
			}
			timerCount = 0;
		}
	}
	
	public boolean validInput(ItemStack input, FluidStack fluidIn) {
		Item item = input.getItem();
		if (item == Items.COAL) {
			return true;
		}
		if (item instanceof ItemBlock) {
			ItemBlock ib = (ItemBlock) item;
			if (ib.getBlock() == Blocks.COAL_BLOCK) {
				return true;
			}
		}
		return false;
	}
	
	public ItemStack output1(ItemStack itemIn, FluidStack fluidIn) {
		Item item = itemIn.getItem();
		if (item == Items.COAL) {
			return new ItemStack(FRItems.coal_coke);
		}
		if (item instanceof ItemBlock) {
			ItemBlock ib = (ItemBlock) item;
			if (ib.getBlock() == Blocks.COAL_BLOCK) {
				return new ItemStack(FRItems.coal_coke, 9);
			}
		}
		return ItemStack.EMPTY;
	}
	
	public ItemStack output2(ItemStack itemIn, FluidStack fluidIn) {
		return ItemStack.EMPTY;
	}
	
	public FluidStack fluidOut1(ItemStack itemIn, FluidStack fluidIn) {
		Item item = itemIn.getItem();
		if (item == Items.COAL) {
			if (itemIn.getItemDamage() == 0) {
				return new FluidStack(FRFluids.tar, 1000);
			}
			return new FluidStack(FRFluids.tar, 750);
		}
		if (item instanceof ItemBlock) {
			ItemBlock ib = (ItemBlock) item;
			if (ib.getBlock() == Blocks.COAL_BLOCK) {
				return new FluidStack(FRFluids.tar, 9000);
			}
		}
		return null;
	}

	public FluidStack fluidOut2(ItemStack itemIn, FluidStack fluidIn) {
		return null;
	}
	
	
	
	//Simulate everything but dont actually process. This is to check if it "can" do stuff.
	public boolean shouldTick() {
		ItemStack input = inventory.getStackInSlot(TarDistillerContainer.INPUT);
		
		if (validInput(input, null)) {
			ItemStack stack = inventory.getStackInSlot(TarDistillerContainer.OUTPUT_1);
			if (stack.isEmpty() || stack.getCount() < inventory.getSlotLimit(TarDistillerContainer.OUTPUT_1)) {
				return true;
			}
		}
		
		ItemStack fluid_in = inventory.getStackInSlot(TarDistillerContainer.FLUID_IN);
		
		if (fluid_in.getUnlocalizedName().compareTo("item.forge.bucketFilled") == 0) {
			//FluidStack fluidStack = FluidUtil.getFluidContained(fluid_in); check its valid
			if (fluidInput.getFluidAmount() <= TANK_CAP - 1000) {
				return true;
			}
		}
		
		ItemStack fluid_out_1 = inventory.getStackInSlot(TarDistillerContainer.FLUID_OUT_1);
		ItemStack fluid_out_1_bucket = inventory.getStackInSlot(TarDistillerContainer.FLUID_OUT_1_BUCKET);
		if (fluid_out_1_bucket.getItem() == Items.BUCKET && fluidOutput1 != null && fluid_out_1.isEmpty()) {
			if (fluidOutput1.getFluidAmount() >= 1000) {
				return true;
			}
		}
		
		ItemStack fluid_out_2 = inventory.getStackInSlot(TarDistillerContainer.FLUID_OUT_2);
		ItemStack fluid_out_2_bucket = inventory.getStackInSlot(TarDistillerContainer.FLUID_OUT_1_BUCKET);
		if (fluid_out_2_bucket.getItem() == Items.BUCKET && fluidOutput2 != null && fluid_out_2.isEmpty()) {
			if (fluidOutput2.getFluidAmount() >= 1000) {
				return true;
			}
		}
		
		Block blockHere = world.getBlockState(pos).getBlock();
		
		if (blockHere != null) {
			EnumFacing getRight = EnumFacing.EAST;
			if (blockHere.getMetaFromState(getState()) == 0) { getRight = EnumFacing.EAST;  }
			if (blockHere.getMetaFromState(getState()) == 1) { getRight = EnumFacing.SOUTH; }
			if (blockHere.getMetaFromState(getState()) == 2) { getRight = EnumFacing.WEST;  }
			if (blockHere.getMetaFromState(getState()) == 3) { getRight = EnumFacing.NORTH; }
			
			IBlockState blockRight = world.getBlockState(pos.offset(getRight));
			if (blockRight.getBlock() instanceof RoadFactoryBlock) {
				if (world.getTileEntity(pos.offset(getRight)) != null) {
					RoadFactoryEntity rfEntity = (RoadFactoryEntity) world.getTileEntity(pos.offset(getRight));
					if ((rfEntity.tarFluid.getFluidAmount() + 1000 <= rfEntity.tarFluid.getCapacity()) && fluidOutput1.getFluidAmount() >= 1000) {
						return true;
					}
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
		if (nbt.hasKey("energy")) {
			energy.deserializeNBT((NBTTagCompound) nbt.getTag("energy"));
		}
		
		fluidInput.readFromNBT(nbt);
		fluidOutput1.readFromNBT(nbt);
		fluidOutput2.readFromNBT(nbt);
		
		int i1 = nbt.getInteger("input_level");
		int o1 = nbt.getInteger("output1_level");
		int o2 = nbt.getInteger("output2_level");
		
		if (i1 > TANK_CAP) { i1 = TANK_CAP; }
		if (o1 > TANK_CAP) { o1 = TANK_CAP; }
		if (o2 > TANK_CAP) { o2 = TANK_CAP; }
		
		Fluid fluid_input = FluidRegistry.getFluid(nbt.getString("fluid_input"));
		Fluid fluid_output1 = FluidRegistry.getFluid(nbt.getString("fluid_output1"));
		Fluid fluid_output2 = FluidRegistry.getFluid(nbt.getString("fluid_output2"));

		if (fluid_input   != null) { fluidInput.setFluid(new FluidStack(fluid_input, i1)); }
		if (fluid_output1 != null) { fluidOutput1.setFluid(new FluidStack(fluid_output1, o1)); }
		if (fluid_output2 != null) { fluidOutput1.setFluid(new FluidStack(fluid_output2, o2)); }
		
		fuel_remaining = nbt.getInteger("fuel");
	}
	
	@Override
	public NBTTagCompound writeNBT(NBTTagCompound nbt) {
		nbt.setTag("items", inventory.serializeNBT());
		nbt.setTag("energy", energy.serializeNBT());
		
		nbt.setInteger("input_level", fluidInput.getFluidAmount());
		nbt.setInteger("output1_level", fluidOutput1.getFluidAmount());
		nbt.setInteger("output2_level", fluidOutput2.getFluidAmount());
		
		if (fluidInput.getFluid()   != null) { nbt.setString("fluid_input", fluidInput.getFluid().getFluid().getName()); }
		if (fluidOutput1.getFluid() != null) { 
			nbt.setString("fluid_output1", fluidOutput1.getFluid().getFluid().getName());
		}
		if (fluidOutput2.getFluid() != null) { nbt.setString("fluid_output2", fluidOutput2.getFluid().getFluid().getName()); }
		
		FluidStack.loadFluidStackFromNBT(nbt);
		
		nbt.setInteger("fuel", fuel_remaining);
		return nbt;
	}
}
