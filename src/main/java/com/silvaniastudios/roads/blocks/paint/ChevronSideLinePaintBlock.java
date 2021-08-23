package com.silvaniastudios.roads.blocks.paint;

import com.silvaniastudios.roads.FurenikusRoads;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ChevronSideLinePaintBlock extends PaintBlockBase {
	
	public static final PropertyEnum<EnumTypeRotation> TYPE_ROT = PropertyEnum.create("type_rotation", EnumTypeRotation.class);
	public static final PropertyEnum<EnumConnections> CONNECT = PropertyEnum.create("connect", EnumConnections.class);

	public ChevronSideLinePaintBlock(String name, String category, int[] coreMetas, boolean dynamic, String colour) {
		super(name, category, coreMetas, dynamic, colour);
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE_ROT, EnumTypeRotation.DOWN_N_A).withProperty(CONNECT, EnumConnections.NONE));
		this.setCreativeTab(FurenikusRoads.tab_paint_junction);
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		EnumFacing placerFacing = placer.getHorizontalFacing();
		int placeMeta = 0;
		
		IBlockState northBlock = world.getBlockState(pos.offset(EnumFacing.NORTH));
		IBlockState eastBlock =  world.getBlockState(pos.offset(EnumFacing.EAST));
		IBlockState southBlock = world.getBlockState(pos.offset(EnumFacing.SOUTH));
		IBlockState westBlock =  world.getBlockState(pos.offset(EnumFacing.WEST));
		
		IBlockState upBlock = null;
		IBlockState leftBlock = null;
		IBlockState rightBlock = null;
		IBlockState downBlock = null;
		
		int leftMeta = -1;
		int rightMeta = -1;
		
		if (placerFacing.equals(EnumFacing.NORTH)) {
			upBlock = northBlock;
			leftBlock = westBlock;
			rightBlock = eastBlock;
			downBlock = southBlock;
		}
		
		if (placerFacing.equals(EnumFacing.EAST)) {
			placeMeta = 1;
			upBlock = eastBlock;
			leftBlock = northBlock;
			rightBlock = southBlock;
			downBlock = westBlock;
		}
		
		if (placerFacing.equals(EnumFacing.SOUTH)) {
			placeMeta = 2;
			upBlock = southBlock;
			leftBlock = eastBlock;
			rightBlock = westBlock;
			downBlock = northBlock;
		}
		
		if (placerFacing.equals(EnumFacing.WEST)) {
			placeMeta = 3;
			upBlock = westBlock;
			leftBlock = southBlock;
			rightBlock = northBlock;
			downBlock = eastBlock;
		}
		
		if (leftBlock.getBlock() instanceof ChevronSideLinePaintBlock) {
			leftMeta = leftBlock.getBlock().getMetaFromState(leftBlock);
			
			if (leftMeta < 4 || (leftMeta > 7 && leftMeta < 12)) {
				return this.getDefaultState().withProperty(TYPE_ROT, EnumTypeRotation.byMetadata(leftMeta + 4));
			} else {
				return this.getDefaultState().withProperty(TYPE_ROT, EnumTypeRotation.byMetadata(leftMeta - 4));
			}
		} else if (rightBlock.getBlock() instanceof ChevronSideLinePaintBlock) {
			rightMeta = rightBlock.getBlock().getMetaFromState(rightBlock);
			
			if (rightMeta < 4 || (rightMeta > 7 && rightMeta < 12)) {
				return this.getDefaultState().withProperty(TYPE_ROT, EnumTypeRotation.byMetadata(rightMeta + 4));
			} else {
				return this.getDefaultState().withProperty(TYPE_ROT, EnumTypeRotation.byMetadata(rightMeta - 4));
			}
		} else if   (upBlock.getBlock() instanceof ChevronSideLinePaintBlock) { return this.getDefaultState().withProperty(TYPE_ROT, EnumTypeRotation.byMetadata(upBlock.getBlock().getMetaFromState(upBlock)));
		} else if (downBlock.getBlock() instanceof ChevronSideLinePaintBlock) { return this.getDefaultState().withProperty(TYPE_ROT, EnumTypeRotation.byMetadata(downBlock.getBlock().getMetaFromState(downBlock))); }
		
		return this.getDefaultState().withProperty(TYPE_ROT, EnumTypeRotation.byMetadata(placeMeta)); //TODO
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumTypeRotation)state.getValue(TYPE_ROT)).getIndex();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(TYPE_ROT, EnumTypeRotation.byMetadata(meta));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {TYPE_ROT, CONNECT});
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(CONNECT, connectSide(worldIn, pos));
	}
	
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }
	
	private EnumConnections connectSide(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		int meta = getMetaFromState(state);
		
		boolean blockNorth = world.getBlockState(pos.offset(EnumFacing.NORTH)).getBlock() instanceof ChevronSideLinePaintBlock;
		boolean blockEast  = world.getBlockState(pos.offset(EnumFacing.EAST)) .getBlock() instanceof ChevronSideLinePaintBlock;
		boolean blockSouth = world.getBlockState(pos.offset(EnumFacing.SOUTH)).getBlock() instanceof ChevronSideLinePaintBlock;
		boolean blockWest  = world.getBlockState(pos.offset(EnumFacing.WEST)) .getBlock() instanceof ChevronSideLinePaintBlock;

		boolean blockLeft = false;
		boolean blockRight = false;
		
		//north
		if (meta == 0 || meta == 4 || meta == 8  || meta == 12) {
			if (blockEast) { blockRight = true; }
			if (blockWest) { blockLeft  = true; }
		}
		//east
		if (meta == 1 || meta == 5 || meta == 9  || meta == 13) {
			if (blockSouth) { blockRight = true; }
			if (blockNorth) { blockLeft  = true; }
		}
		//south
		if (meta == 2 || meta == 6 || meta == 10 || meta == 14) {
			if (blockWest) { blockRight = true; }
			if (blockEast) { blockLeft  = true; }
		}
		//west
		if (meta == 3 || meta == 7 || meta == 11 || meta == 15) {
			if (blockNorth) { blockRight = true; }
			if (blockSouth) { blockLeft  = true; }
		}

		if (!blockLeft &&  blockRight) { return EnumConnections.LINE_LEFT; }
		if ( blockLeft && !blockRight) { return EnumConnections.LINE_RIGHT; }
		if (!blockLeft && !blockRight) { return EnumConnections.LINE_BOTH; }
		
		return EnumConnections.NONE;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
	
	public static enum EnumConnections implements IStringSerializable {
		NONE(0, "none"),
		LINE_LEFT (1, "line_left"),
		LINE_RIGHT(2, "line_right"),
		LINE_BOTH(3, "line_both");
		
		private static final EnumConnections[] ID_LOOKUP = new EnumConnections[values().length];
		private final int id;
		private final String name;
		
		private EnumConnections(int id, String name) {
			this.id = id;
			this.name = name;
		}

		@Override
		public String getName() {
			return this.name;
		}
		
		public int getId() {
	        return this.id;
	    }
		
		public static EnumConnections byId(int id) {
	        if (id < 0 || id >= ID_LOOKUP.length) {
	        	id = 0;
	        }
	        
	        return ID_LOOKUP[id];
	    }
		
		static {
	        for (EnumConnections type: values()) {
	            ID_LOOKUP[type.getId()] = type;
	        }
	    }
	}
	
	public static enum EnumTypeRotation implements IStringSerializable {
		DOWN_N_A(0, "down_n_a"),
		DOWN_E_A(1, "down_e_a"),
		DOWN_S_A(2, "down_s_a"),
		DOWN_W_A(3, "down_w_a"),

		DOWN_N_B(4, "down_n_b"),
		DOWN_E_B(5, "down_e_b"),
		DOWN_S_B(6, "down_s_b"),
		DOWN_W_B(7, "down_w_b"),
		
		UP_N_A(8,  "up_n_a"),
		UP_E_A(9,  "up_e_a"),
		UP_S_A(10, "up_s_a"),
		UP_W_A(11, "up_w_a"),

		UP_N_B(12, "up_n_b"),
		UP_E_B(13, "up_e_b"),
		UP_S_B(14, "up_s_b"),
		UP_W_B(15, "up_w_b");
		
		private static final EnumTypeRotation[] INDEX_LOOKUP = new EnumTypeRotation[values().length];
		private final int index;
		private final String name;
		
		private EnumTypeRotation(int index, String name) {
			this.index = index;
			this.name = name;
		}

		@Override
		public String getName() {
			return this.name;
		}
		
		@Override
		public String toString() {
			return getName();
		}
		
		public int getIndex() {
	        return this.index;
	    }
		
		public static EnumTypeRotation byMetadata(int meta) {
	        if (meta < 0 || meta >= INDEX_LOOKUP.length) {
	        	meta = 0;
	        }
	        
	        return INDEX_LOOKUP[meta];
	    }
		
		static {
	        for (EnumTypeRotation type: values()) {
	        	INDEX_LOOKUP[type.getIndex()] = type;
	        }
	    }
	}
}
