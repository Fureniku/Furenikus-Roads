package com.silvaniastudios.roads.blocks.tileentities.roadfactory;

import java.util.List;

import javax.annotation.Nullable;

import com.silvaniastudios.roads.blocks.tileentities.RoadTEBlock;
import com.silvaniastudios.roads.blocks.tileentities.paintoven.PaintOvenEntity;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;

public class RoadFactoryBlock extends RoadTEBlock {
	
	public static final PropertyBool FLUID_LEFT = PropertyBool.create("fluid_left");
	public static final PropertyBool FLUID_UP = PropertyBool.create("fluid_up");

	public RoadFactoryBlock(String name, boolean electric) {
		super(name, electric, 3);
		this.setDefaultState(this.blockState.getBaseState()
				.withProperty(ROTATION, RoadTEBlock.EnumRotation.north)
				.withProperty(FLUID_LEFT, false)
				.withProperty(FLUID_UP, false)
				.withProperty(FURNACE_ACTIVE, false));
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("roads.gui.road_factory.tooltip_1"));
		tooltip.add(I18n.format("roads.gui.road_factory.tooltip_2"));
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack held = player.getHeldItem(EnumHand.MAIN_HAND);
		
		if (world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof RoadFactoryEntity) {
			RoadFactoryEntity te = (RoadFactoryEntity) world.getTileEntity(pos);
			if (held.getItem() == Items.BUCKET) {

				if (te.tarFluid.getFluidAmount() >= 1000) {
					if (!player.isCreative()) {
						if (held.getCount() > 1) {
							held.setCount(held.getCount() - 1);
						} else {
							player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
						}
					}
					player.addItemStackToInventory(FluidUtil.getFilledBucket(te.tarFluid.getFluid()));
					te.tarFluid.drain(1000, true);
					te.sendUpdates();
					return true;
				}
			}
		
			if (held.getItem().getUnlocalizedName().compareTo("item.forge.bucketFilled") == 0) {
				FluidStack fluidStack = FluidUtil.getFluidContained(held);
				if (te.tarFluid.getFluidAmount() + 1000 <= PaintOvenEntity.FILLER_TANK_CAP ) {
					if ((te.tarFluid.getFluidAmount() > 0 && te.tarFluid.getFluid().getFluid() == fluidStack.getFluid()) || te.tarFluid.getFluidAmount() == 0) {
						if (!player.isCreative()) {
							if (held.getCount() > 1) {
								held.setCount(held.getCount() - 1);
							} else {
								player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
							}
							player.addItemStackToInventory(new ItemStack(Items.BUCKET));
						}
						if (te.tarFluid.getFluidAmount() > 0) {
							te.tarFluid.fill(fluidStack, true);
						} else {
							te.tarFluid.setFluid(fluidStack);
						}
					}
					te.sendUpdates();
					
					return true;
				}
			}
		}
		
		return super.onBlockActivated(world, pos, state, player, hand, facing, hitX, hitY, hitZ);
	}
	
	@Override
	public TileEntity createTileEntity(World worldIn, IBlockState state) {
		if (electric) {
			return new RoadFactoryElectricEntity();
		}
		return new RoadFactoryEntity();
	}
	
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {		
		EnumFacing left = EnumFacing.WEST;
		EnumFacing right = EnumFacing.EAST;
		
		if (getMetaFromState(state) == 1) { left = EnumFacing.NORTH; right = EnumFacing.SOUTH; }
		if (getMetaFromState(state) == 2) { left = EnumFacing.EAST;  right = EnumFacing.WEST;  }
		if (getMetaFromState(state) == 3) { left = EnumFacing.SOUTH; right = EnumFacing.NORTH; }
		
		boolean fluid_left = fluidLeft(world, pos, left, right);
		boolean fluid_up = fluidTop(world, pos);

		
		return state.withProperty(FLUID_LEFT, fluid_left).withProperty(FLUID_UP, fluid_up).withProperty(FURNACE_ACTIVE, isFurnaceEnabled(state, world, pos)).withProperty(BASE_PLATE, hasBasePlate(world, pos));
	}
	
	private boolean fluidLeft(IBlockAccess world, BlockPos pos, EnumFacing left, EnumFacing right) {
		if (world.getTileEntity(pos.offset(left)) != null) {
			if (world.getTileEntity(pos.offset(left)).hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, right)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean fluidTop(IBlockAccess world, BlockPos pos) {
		if (world.getTileEntity(pos.offset(EnumFacing.UP)) != null) {
			if (world.getTileEntity(pos.offset(EnumFacing.UP)).hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.DOWN) ||
					world.getTileEntity(pos.offset(EnumFacing.UP)).hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		EnumFacing left  = EnumFacing.WEST;
		EnumFacing right = EnumFacing.EAST;
		int meta = getMetaFromState(state);
		
		if (meta == 1) {
			left = EnumFacing.NORTH;
			right = EnumFacing.SOUTH;
		}
		
		if (meta == 2) {
			left = EnumFacing.EAST;
			right = EnumFacing.WEST;
		}
		
		if (meta == 3) {
			left = EnumFacing.SOUTH;
			right = EnumFacing.NORTH;
		}
		
		boolean fluid_left = fluidLeft(world, pos, left, right);
		boolean fluid_top = fluidTop(world, pos);
		
		double top = fluid_top ? 1.0D : 0.84375D;
		
		if (meta == 0) {
			double l = fluid_left  ? 0.0D : 0.0625D;
			return new AxisAlignedBB(l, 0.0D, 0.0625D, 0.9375D, top, 0.9375D);
		}
		
		if (meta == 1) {
			double l = fluid_left  ? 0.0D : 0.0625D;
			return new AxisAlignedBB(0.0625D, 0.0D, l, 0.9375D, top, 0.9375D);
		}
		
		if (meta == 2) {
			double r = fluid_left  ? 1.0D : 0.9375D;
			return new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, r, top, 0.9375D);
		}
		
		if (meta == 3) {
			double r = fluid_left  ? 1.0D : 0.9375D;
			return new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, top, r);
		}
		
    	return new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, top, 0.9375D);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
    	return getBoundingBox(state, world, pos);
    }
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {FLUID_LEFT, FLUID_UP, ROTATION, FURNACE_ACTIVE, BASE_PLATE});
	}
}
