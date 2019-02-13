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

public class CrusherBlock extends RoadTEBlock {
	
	public static final PropertyBool FURNACE_ACTIVE = PropertyBool.create("furnace_active");

	public CrusherBlock(String name) {
		super(name);
		this.setDefaultState(this.blockState.getBaseState()
				.withProperty(ROTATION, PaintFillerBlock.EnumRotation.north)
				.withProperty(FURNACE_ACTIVE, false));
	}
	
	@Override
	public TileEntity createTileEntity(World worldIn, IBlockState state) {
		return new CrusherEntity();
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("Crushes blocks to make different blocks!");
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		openGui(world, pos, player, 5);
		return true;
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {ROTATION, FURNACE_ACTIVE});
	}
	
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		TileEntity te = world.getTileEntity(pos);
		if (te instanceof RoadTileEntity) {
			RoadTileEntity tileEntity = (RoadTileEntity) te;
			if (tileEntity.fuel_remaining > 0) {
				return state.withProperty(FURNACE_ACTIVE, true);
			} else {
				return state.withProperty(FURNACE_ACTIVE, false);
			}
		}
		return state;
	}
}
