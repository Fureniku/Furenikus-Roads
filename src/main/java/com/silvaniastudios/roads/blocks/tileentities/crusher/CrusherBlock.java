package com.silvaniastudios.roads.blocks.tileentities.crusher;

import java.util.List;

import javax.annotation.Nullable;

import com.silvaniastudios.roads.blocks.tileentities.RoadTEBlock;
import com.silvaniastudios.roads.blocks.tileentities.RoadTileEntity;
import com.silvaniastudios.roads.blocks.tileentities.paintfiller.PaintFillerBlock;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CrusherBlock extends RoadTEBlock {
	
	public static final PropertyBool FURNACE_ACTIVE = PropertyBool.create("furnace_active");

	public CrusherBlock(String name, boolean electric) {
		super(name, electric, 5);
		this.setDefaultState(this.blockState.getBaseState()
				.withProperty(ROTATION, PaintFillerBlock.EnumRotation.north)
				.withProperty(FURNACE_ACTIVE, false));
	}
	
	@Override
	public TileEntity createTileEntity(World worldIn, IBlockState state) {
		if (electric) {
			return new CrusherElectricEntity();
		}
		return new CrusherEntity();
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("roads.gui.crusher.tooltip_1"));
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {ROTATION, FURNACE_ACTIVE, BASE_PLATE});
	}
	
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		TileEntity te = world.getTileEntity(pos);
		if (te instanceof RoadTileEntity) {
			RoadTileEntity tileEntity = (RoadTileEntity) te;
			if (tileEntity.fuel_remaining > 0) {
				return state.withProperty(FURNACE_ACTIVE, true).withProperty(BASE_PLATE, hasBasePlate(world, pos));
			} else {
				return state.withProperty(FURNACE_ACTIVE, false).withProperty(BASE_PLATE, hasBasePlate(world, pos));
			}
		}
		return state;
	}
	
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    	return new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 0.90625D, 0.875D);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
    	return getBoundingBox(state, world, pos);
    }
}
