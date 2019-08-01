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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PaintFillerBlock extends RoadTEBlock {
	
	protected String name;
	
	public static final PropertyBool GUN_LOADED = PropertyBool.create("gun_loaded");

	public PaintFillerBlock(String name) {
		super(name);
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
		return new PaintFillerEntity();
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		openGui(world, pos, player, 1);
		return true;
	}
	
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		TileEntity tileEntity = world.getTileEntity(pos);
		
		boolean connect = false;
		if (tileEntity instanceof PaintFillerEntity) {
			PaintFillerEntity te = (PaintFillerEntity) tileEntity;
			connect = hasGun(te);
		}
		return state.withProperty(GUN_LOADED, connect).withProperty(FURNACE_ACTIVE, isFurnaceEnabled(state, world, pos));
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {GUN_LOADED, ROTATION, FURNACE_ACTIVE});
	}
	
	public boolean hasGun(PaintFillerEntity tileEntity) {
		if (tileEntity.inventory.getStackInSlot(1).getItem() instanceof PaintGun) { return true; }
		if (tileEntity.has_gun) { return true; }
		return false;
	}
}
