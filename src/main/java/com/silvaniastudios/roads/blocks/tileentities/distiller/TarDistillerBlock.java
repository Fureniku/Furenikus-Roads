package com.silvaniastudios.roads.blocks.tileentities.distiller;

import java.util.List;

import javax.annotation.Nullable;

import com.silvaniastudios.roads.blocks.tileentities.RoadTEBlock;
import com.silvaniastudios.roads.blocks.tileentities.roadfactory.RoadFactoryBlock;

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

public class TarDistillerBlock extends RoadTEBlock {
	
	public static final PropertyBool CONNECTED = PropertyBool.create("connected");
	public static final PropertyBool FURNACE_ACTIVE = PropertyBool.create("furnace_active");

	public TarDistillerBlock(String name) {
		super(name);
		this.setDefaultState(this.blockState.getBaseState()
				.withProperty(ROTATION, RoadTEBlock.EnumRotation.north)
				.withProperty(CONNECTED, false)
				.withProperty(FURNACE_ACTIVE, false));
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("Creates tar from input items.");
		tooltip.add("Currently only takes coal. Fuel with lava.");
	}
	
	@Override
	public TileEntity createTileEntity(World worldIn, IBlockState state) {
		return new TarDistillerEntity();
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		openGui(world, pos, player, 2);
		return true;
	}
	
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		EnumFacing getRight = EnumFacing.EAST;
		if (getMetaFromState(state) == 0) { getRight = EnumFacing.EAST;  }
		if (getMetaFromState(state) == 1) { getRight = EnumFacing.SOUTH; }
		if (getMetaFromState(state) == 2) { getRight = EnumFacing.WEST;  }
		if (getMetaFromState(state) == 3) { getRight = EnumFacing.NORTH; }
		
		IBlockState blockRight = world.getBlockState(pos.offset(getRight));
		boolean connect = blockRight.getBlock() instanceof RoadFactoryBlock;
		return state.withProperty(CONNECTED, connect).withProperty(FURNACE_ACTIVE, isFurnaceEnabled(state, world, pos));
	}
	

	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {CONNECTED, ROTATION, FURNACE_ACTIVE});
	}
}
