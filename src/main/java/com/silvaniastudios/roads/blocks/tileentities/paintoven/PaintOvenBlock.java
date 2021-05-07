package com.silvaniastudios.roads.blocks.tileentities.paintoven;

import java.util.List;

import javax.annotation.Nullable;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.tileentities.RoadTEBlock;
import com.silvaniastudios.roads.blocks.tileentities.RoadTileEntity;

import net.minecraft.block.properties.IProperty;
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
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PaintOvenBlock extends RoadTEBlock {

	public PaintOvenBlock(String name, boolean electric) {
		super(name, electric, 11);
		this.setDefaultState(this.blockState.getBaseState()
				.withProperty(ROTATION, RoadTEBlock.EnumRotation.north)
				.withProperty(FURNACE_ACTIVE, false)
				.withProperty(BASE_PLATE, true));
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("roads.gui.paint_oven.tooltip_1"));
		tooltip.add(I18n.format("roads.gui.paint_oven.tooltip_2"));
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack held = player.getHeldItem(EnumHand.MAIN_HAND);
		if (held.getItem() == Items.BUCKET) {
			if (world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof PaintOvenEntity) {
				PaintOvenEntity te = (PaintOvenEntity) world.getTileEntity(pos);
				if (te.paint.getFluidAmount() >= 1000) {
					if (!player.isCreative()) {
						if (held.getCount() > 1) {
							held.setCount(held.getCount() - 1);
						} else {
							player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
						}
					}
					player.addItemStackToInventory(FluidUtil.getFilledBucket(te.paint.getFluid()));
					te.paint.drain(1000, true);
					te.sendUpdates();
					
					return true;
				}
			}
		}
		
		if (held.getItem() == Items.WATER_BUCKET) {
			if (world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof PaintOvenEntity) {
				PaintOvenEntity te = (PaintOvenEntity) world.getTileEntity(pos);
				if (te.water.getFluidAmount() + 1000 <= PaintOvenEntity.FILLER_TANK_CAP ) {
					if (!player.isCreative()) {
						if (held.getCount() > 1) {
							held.setCount(held.getCount() - 1);
						} else {
							player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
						}
						player.addItemStackToInventory(new ItemStack(Items.BUCKET));
					}
					if (te.water.getFluidAmount() > 0) {
						te.water.fill(new FluidStack(te.water.getFluid().getFluid(), 1000), true);
					} else {
						te.water.setFluid(new FluidStack(FluidRegistry.WATER, 1000));
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
			return new PaintOvenElectricEntity();
		}
		return new PaintOvenEntity();
	}
	
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		return state.withProperty(FURNACE_ACTIVE, isFurnaceEnabled(state, world, pos)).withProperty(BASE_PLATE, hasBasePlate(world, pos));
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {ROTATION, FURNACE_ACTIVE, BASE_PLATE});
	}
	
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		int meta = getMetaFromState(state);

		if (meta == 0) { return new AxisAlignedBB(0.125D,   0.0D, 0.0D,     0.875D,   1.0D, 0.84375D); }
		if (meta == 1) { return new AxisAlignedBB(0.15625D, 0.0D, 0.125D,   1.0D,     1.0D, 0.875D);   }
		if (meta == 2) { return new AxisAlignedBB(0.125D,   0.0D, 0.15625D, 0.875D,   1.0D, 1.0D);     }
		if (meta == 3) { return new AxisAlignedBB(0.0D,     0.0D, 0.125D,   0.84375D, 1.0D, 0.875D);   }
		
		
    	return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
    	return getBoundingBox(state, world, pos);
    }
	
	@Override
	public void openGui(World world, BlockPos pos, EntityPlayer player) {
		TileEntity te = world.getTileEntity(pos);
		if (!world.isRemote) {
			if (te != null && te instanceof RoadTileEntity) {
				player.openGui(FurenikusRoads.instance, (electric ? 12 : 11), world, pos.getX(), pos.getY(), pos.getZ());
			}
		}
	}
}
