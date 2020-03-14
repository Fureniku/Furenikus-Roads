package com.silvaniastudios.roads.blocks.tileentities.paintfiller;

import java.util.List;

import javax.annotation.Nullable;

import com.silvaniastudios.roads.blocks.tileentities.RoadTEBlock;
import com.silvaniastudios.roads.items.PaintGun;

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

public class PaintFillerBlock extends RoadTEBlock {
	
	protected String name;
	
	public static final PropertyBool GUN_LOADED = PropertyBool.create("gun_loaded");

	public PaintFillerBlock(String name, boolean electric) {
		super(name, electric, 1);
		this.setDefaultState(this.blockState.getBaseState()
				.withProperty(GUN_LOADED, false)
				.withProperty(ROTATION, RoadTEBlock.EnumRotation.north)
				.withProperty(FURNACE_ACTIVE, false));
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("roads.gui.paint_filler.tooltip_1"));
		tooltip.add(I18n.format("roads.gui.paint_filler.tooltip_2"));
	}
	
	@Override
	public TileEntity createTileEntity(World worldIn, IBlockState state) {
		if (electric) {
			return new PaintFillerElectricEntity();
		}
		return new PaintFillerEntity();
	}
	
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		TileEntity tileEntity = world.getTileEntity(pos);
		
		boolean hasGun = false;
		if (tileEntity instanceof PaintFillerEntity) {
			PaintFillerEntity te = (PaintFillerEntity) tileEntity;
			hasGun = hasGun(te);
		}
		return state.withProperty(GUN_LOADED, hasGun).withProperty(FURNACE_ACTIVE, isFurnaceEnabled(state, world, pos)).withProperty(BASE_PLATE, hasBasePlate(world, pos));
	}
	
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		int meta = getMetaFromState(state);

		if (meta == 0) { return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.9375D, 1.0D, 0.9375D); }
		if (meta == 1) { return new AxisAlignedBB(0.0625D, 0.0D, 0.0D, 1.0D, 1.0D, 0.9375D); }
		if (meta == 2) { return new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 1.0D, 1.0D, 1.0D); }
		if (meta == 3) { return new AxisAlignedBB(0.0D, 0.0D, 0.0625D, 0.9375D, 1.0D, 1.0D); }
		
		
    	return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
    	return getBoundingBox(state, world, pos);
    }
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {GUN_LOADED, ROTATION, FURNACE_ACTIVE, BASE_PLATE});
	}
	
	public boolean hasGun(PaintFillerEntity tileEntity) {
		if (tileEntity.inventory.getStackInSlot(1).getItem() instanceof PaintGun) { return true; }
		if (tileEntity.has_gun) { return true; }
		return false;
	}
}
