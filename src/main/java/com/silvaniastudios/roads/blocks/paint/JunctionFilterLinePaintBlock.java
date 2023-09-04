package com.silvaniastudios.roads.blocks.paint;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.PaintColour;
import com.silvaniastudios.roads.blocks.enums.EnumFourLengthConnectable;

import com.silvaniastudios.roads.blocks.enums.IMetaBlockName;
import com.silvaniastudios.roads.blocks.paint.uniques.ChevronIconPaintBlock;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

public class JunctionFilterLinePaintBlock extends PaintBlockBase implements IMetaBlockName {
	
	public static final PropertyEnum<EnumFourLengthConnectable> TYPE_ROT = PropertyEnum.create("position_rotation", EnumFourLengthConnectable.class);
	public static final PropertyEnum<EnumChevronConnections> CONNECT = PropertyEnum.create("connect", EnumChevronConnections.class);
	
	boolean leftSide = false;

	public JunctionFilterLinePaintBlock(String name, boolean leftSide, String category, int[] coreMetas, boolean dynamic, PaintColour colour) {
		super(name, category, coreMetas, dynamic, colour);
		this.leftSide = leftSide;
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE_ROT, EnumFourLengthConnectable.NORTH_TOP).withProperty(CONNECT, EnumChevronConnections.NONE));
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
		
		int northMeta = -1;
		int eastMeta  = -1;
		int southMeta = -1;
		int westMeta  = -1;
		
		if (northBlock.getBlock() instanceof JunctionFilterLinePaintBlock) { northMeta = getMetaFromState(northBlock); }
		if ( eastBlock.getBlock() instanceof JunctionFilterLinePaintBlock) {  eastMeta = getMetaFromState(eastBlock);  }
		if (southBlock.getBlock() instanceof JunctionFilterLinePaintBlock) { southMeta = getMetaFromState(southBlock); }
		if ( westBlock.getBlock() instanceof JunctionFilterLinePaintBlock) {  westMeta = getMetaFromState(westBlock);  }
		
		if (placerFacing.equals(EnumFacing.NORTH)) {
			placeMeta = 3;
			
			//block to right - "Flip fifth". Places the fifth required block to complete the system by rotating 180 degrees.
			if (leftSide) {
				if (eastBlock.getBlock() instanceof JunctionFilterLinePaintBlock && eastMeta == 0) {
					return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(8));
				}
			} else if (westBlock.getBlock() instanceof JunctionFilterLinePaintBlock && westMeta == 0) {
				return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(8));
			}
			//block behind
			if (southBlock.getBlock() instanceof JunctionFilterLinePaintBlock) {
				if (southMeta == 8) { return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(3)); } //Block below is flipped fifth. Start again from lowest option.
				if (southMeta < 11 && southMeta > 7) { return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(southMeta + 1)); }
				if (southMeta < 4  && southMeta > 0) { return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(southMeta - 1)); }
			}
			//block in front
			if (northBlock.getBlock() instanceof JunctionFilterLinePaintBlock) {
				if (northMeta == 3) { return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(8)); } //Also a flip fifth, at the bottom.
				if (northMeta < 3)  { return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(northMeta + 1)); }
				if (northMeta < 12 && northMeta > 8) { return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(northMeta - 1)); }
			}
		}
		
		if (placerFacing.equals(EnumFacing.SOUTH)) {
			placeMeta = 11;
			
			//"Flip fifth". Places the fifth required block to complete the system by rotating 180 degrees.
			if (leftSide) {
				if (westBlock.getBlock() instanceof JunctionFilterLinePaintBlock && westMeta == 8) {
					return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(0));
				}
			} else if (eastBlock.getBlock() instanceof JunctionFilterLinePaintBlock && eastMeta == 8) {
				return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(0));
			}
			//block behind
			if (northBlock.getBlock() instanceof JunctionFilterLinePaintBlock) {
				if (northMeta == 0) { return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(11)); } //Block below is flipped fifth. Start again from lowest option.
				if (northMeta < 3) { return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(northMeta + 1)); }
				if (northMeta < 12 && northMeta > 8) { return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(northMeta - 1)); }
			}
			//block in front
			if (southBlock.getBlock() instanceof JunctionFilterLinePaintBlock) {
				if (southMeta == 11) { return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(0)); } //Also a flip fifth, at the bottom.
				if (southMeta < 11 && southMeta > 7) { return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(southMeta + 1)); }
				if (southMeta < 4  && southMeta > 0) { return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(southMeta - 1)); }
			}
		}

		if (placerFacing.equals(EnumFacing.EAST)) {
			placeMeta = 7;
			
			//"Flip fifth". Places the fifth required block to complete the system by rotating 180 degrees.
			if (leftSide) {
				if (southBlock.getBlock() instanceof JunctionFilterLinePaintBlock && southMeta == 4) {
					return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(12));
				}
			} else if (northBlock.getBlock() instanceof JunctionFilterLinePaintBlock && northMeta == 4) {
				return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(12));
			}
			//block behind
			if (westBlock.getBlock() instanceof JunctionFilterLinePaintBlock) {
				if (westMeta == 12) { return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(7)); } //Also a flip fifth, at the bottom.
				if (westMeta > 11 && westMeta < 15) { return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(westMeta + 1)); }
				if (westMeta > 4  && westMeta < 8)  { return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(westMeta - 1)); }
			}
			//block in front
			if (eastBlock.getBlock() instanceof JunctionFilterLinePaintBlock) {
				if (eastMeta == 7) { return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(12)); } //Block below is flipped fifth. Start again from lowest option.
				if (eastMeta > 3 && eastMeta < 7) { return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(eastMeta + 1)); }
				if (eastMeta > 12) { return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(eastMeta - 1)); }
			}
		}
		
		if (placerFacing.equals(EnumFacing.WEST)) {
			placeMeta = 15;
			
			//"Flip fifth". Places the fifth required block to complete the system by rotating 180 degrees.
			if (leftSide) {
				if (northBlock.getBlock() instanceof JunctionFilterLinePaintBlock && northMeta == 12) {
					return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(4));
				}
			} else if (southBlock.getBlock() instanceof JunctionFilterLinePaintBlock && southMeta == 12) {
				return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(4));
			}
			//block behind
			if (eastBlock.getBlock() instanceof JunctionFilterLinePaintBlock) {
				if (eastMeta == 4) { return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(15)); } //Also a flip fifth, at the bottom.
				if (eastMeta > 3  && eastMeta < 7) { return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(eastMeta + 1)); }
				if (eastMeta > 12) { return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(eastMeta - 1)); }
			}
			//block in front
			if (westBlock.getBlock() instanceof JunctionFilterLinePaintBlock) {
				if (westMeta == 15) { return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(4)); } //Block below is flipped fifth. Start again from lowest option.
				if (westMeta > 11 && westMeta < 15) { return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(westMeta + 1)); }
				if (westMeta > 4  && westMeta < 8)  { return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(westMeta - 1)); }
			}
		}
		return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(placeMeta)); //TODO
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFourLengthConnectable)state.getValue(TYPE_ROT)).getIndex();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(TYPE_ROT, EnumFourLengthConnectable.byMetadata(meta));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {TYPE_ROT, CONNECT});
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(CONNECT, connectToChevron(worldIn, pos));
	}
		
		
	
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }
	
	private boolean isMidChevron(IBlockAccess world, BlockPos pos, EnumFacing facing) {
		Block block = world.getBlockState(pos.offset(facing)).getBlock();
		if (block.getUnlocalizedName().contains("_chevron_mid")) {
			return true;
		}
		
		return false;
	}
	
	private EnumChevronConnections connectToChevron(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		int meta = getMetaFromState(state);
		
		IBlockState stateNorth = world.getBlockState(pos);
		IBlockState stateEast  = world.getBlockState(pos);
		IBlockState stateSouth = world.getBlockState(pos);
		IBlockState stateWest  = world.getBlockState(pos);
		
		boolean blockNorth = world.getBlockState(pos.offset(EnumFacing.NORTH)).getBlock() instanceof ChevronPaintBlock || isMidChevron(world, pos, EnumFacing.NORTH);
		boolean blockEast  = world.getBlockState(pos.offset(EnumFacing.EAST)) .getBlock() instanceof ChevronPaintBlock || isMidChevron(world, pos, EnumFacing.EAST);
		boolean blockSouth = world.getBlockState(pos.offset(EnumFacing.SOUTH)).getBlock() instanceof ChevronPaintBlock || isMidChevron(world, pos, EnumFacing.SOUTH);
		boolean blockWest  = world.getBlockState(pos.offset(EnumFacing.WEST)) .getBlock() instanceof ChevronPaintBlock || isMidChevron(world, pos, EnumFacing.WEST);
		
		if (blockNorth) { stateNorth = world.getBlockState(pos.offset(EnumFacing.NORTH)); }
		if (blockEast)  { stateEast  = world.getBlockState(pos.offset(EnumFacing.EAST));  }
		if (blockSouth) { stateSouth = world.getBlockState(pos.offset(EnumFacing.SOUTH)); }
		if (blockWest)  { stateWest  = world.getBlockState(pos.offset(EnumFacing.WEST));  }

		boolean blockSide = false;
		IBlockState stateSide = null;
		
		//north
		if (meta < 4) {
			if (leftSide) {
				blockSide = blockEast;
				stateSide = stateEast;
			} else {
				blockSide = blockWest;
				stateSide = stateWest;
			}
		}
		//east
		if (meta > 3 && meta < 8) {
			if (leftSide) {
				blockSide = blockSouth;
				stateSide = stateSouth;
			} else {
				blockSide = blockNorth;
				stateSide = stateNorth;
			}
		}
		//south
		if (meta > 7 && meta < 12) {
			if (leftSide) {
				blockSide = blockWest;
				stateSide = stateWest;
			} else {
				blockSide = blockEast;
				stateSide = stateEast;
			}
		}
		//west
		if (meta > 11) {
			if (leftSide) {
				blockSide = blockNorth;
				stateSide = stateNorth;
			} else {
				blockSide = blockSouth;
				stateSide = stateSouth;
			}
		}
		
		if (!blockSide) {
			boolean blockAbove = world.getBlockState(pos.offset(EnumFacing.UP))  .getBlock() instanceof ChevronPaintBlock;
			boolean blockBelow = world.getBlockState(pos.offset(EnumFacing.DOWN)).getBlock() instanceof ChevronPaintBlock;
			
			if (blockAbove) {
				stateSide = world.getBlockState(pos.offset(EnumFacing.UP));
				blockSide = true;
			} else if (blockBelow) {
				stateSide = world.getBlockState(pos.offset(EnumFacing.DOWN));
				blockSide = true;
			}
		}

		if (blockSide) {
			ChevronPaintBlock.EnumJunctionConnections cnct = null;
			
			
			if (stateSide.getBlock() instanceof ChevronIconPaintBlock) {
				ChevronIconPaintBlock mipb = (ChevronIconPaintBlock) stateSide.getBlock();
				int mipbMeta = mipb.getMetaFromState(stateSide);
				
				if (mipbMeta < 4) { cnct = ChevronPaintBlock.EnumJunctionConnections.CHEVRON_B;
				} else if (mipbMeta < 8) { cnct = ChevronPaintBlock.EnumJunctionConnections.CHEVRON_A;
				} else if (mipbMeta < 12) { cnct = ChevronPaintBlock.EnumJunctionConnections.CHEVRON_THIN_B;
				} else { cnct = ChevronPaintBlock.EnumJunctionConnections.CHEVRON_THIN_A; }
			} else {
				cnct = (ChevronPaintBlock.EnumJunctionConnections) stateSide.getProperties().get(ChevronPaintBlock.TYPE);
			}
			
			if (cnct.equals(ChevronPaintBlock.EnumJunctionConnections.CHEVRON_A)) {
				if (meta == 0 || meta == 4 || meta == 8  || meta == 12) { return EnumChevronConnections.TOP_CHEVRON_A; }
				if (meta == 1 || meta == 5 || meta == 9  || meta == 13) { return EnumChevronConnections.TOP_MID_CHEVRON_A; }
				if (meta == 2 || meta == 6 || meta == 10 || meta == 14) { return EnumChevronConnections.BOTTOM_MID_CHEVRON_A; }
				if (meta == 3 || meta == 7 || meta == 11 || meta == 15) { return EnumChevronConnections.BOTTOM_CHEVRON_A; }
			}
			
			if (cnct.equals(ChevronPaintBlock.EnumJunctionConnections.CHEVRON_B)) {
				if (meta == 0 || meta == 4 || meta == 8  || meta == 12) { return EnumChevronConnections.TOP_CHEVRON_B; }
				if (meta == 1 || meta == 5 || meta == 9  || meta == 13) { return EnumChevronConnections.TOP_MID_CHEVRON_B; }
				if (meta == 2 || meta == 6 || meta == 10 || meta == 14) { return EnumChevronConnections.BOTTOM_MID_CHEVRON_B; }
				if (meta == 3 || meta == 7 || meta == 11 || meta == 15) { return EnumChevronConnections.BOTTOM_CHEVRON_B; }
			}
			
			if (cnct.equals(ChevronPaintBlock.EnumJunctionConnections.CHEVRON_THIN_A)) {
				if (meta == 0 || meta == 4 || meta == 8  || meta == 12) { return EnumChevronConnections.TOP_CHEVRON_THIN_A; }
				if (meta == 1 || meta == 5 || meta == 9  || meta == 13) { return EnumChevronConnections.TOP_MID_CHEVRON_THIN_A; }
				if (meta == 2 || meta == 6 || meta == 10 || meta == 14) { return EnumChevronConnections.BOTTOM_MID_CHEVRON_THIN_A; }
				if (meta == 3 || meta == 7 || meta == 11 || meta == 15) { return EnumChevronConnections.BOTTOM_CHEVRON_THIN_A; }
			}
			
			if (cnct.equals(ChevronPaintBlock.EnumJunctionConnections.CHEVRON_THIN_B)) {
				if (meta == 0 || meta == 4 || meta == 8  || meta == 12) { return EnumChevronConnections.TOP_CHEVRON_THIN_B; }
				if (meta == 1 || meta == 5 || meta == 9  || meta == 13) { return EnumChevronConnections.TOP_MID_CHEVRON_THIN_B; }
				if (meta == 2 || meta == 6 || meta == 10 || meta == 14) { return EnumChevronConnections.BOTTOM_MID_CHEVRON_THIN_B; }
				if (meta == 3 || meta == 7 || meta == 11 || meta == 15) { return EnumChevronConnections.BOTTOM_CHEVRON_THIN_B; }
			}
		}
		
		return EnumChevronConnections.NONE;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return stack.getItemDamage() + "";
	}
	
	public static enum EnumChevronConnections implements IStringSerializable {
		NONE(0, "none"),
		TOP_CHEVRON_A(1, "top_chevron_a"),
		TOP_CHEVRON_B(2, "top_chevron_b"),
		TOP_CHEVRON_THIN_A(3, "top_chevron_thin_a"),
		TOP_CHEVRON_THIN_B(4, "top_chevron_thin_b"),
		
		TOP_MID_CHEVRON_A(5, "top_mid_chevron_a"),
		TOP_MID_CHEVRON_B(6, "top_mid_chevron_b"),
		TOP_MID_CHEVRON_THIN_A(7, "top_mid_chevron_thin_a"),
		TOP_MID_CHEVRON_THIN_B(8, "top_mid_chevron_thin_b"),
		
		BOTTOM_MID_CHEVRON_A(9, "bottom_mid_chevron_a"),
		BOTTOM_MID_CHEVRON_B(10, "bottom_mid_chevron_b"),
		BOTTOM_MID_CHEVRON_THIN_A(11, "bottom_mid_chevron_thin_a"),
		BOTTOM_MID_CHEVRON_THIN_B(12, "bottom_mid_chevron_thin_b"),
		
		BOTTOM_CHEVRON_A(13, "bottom_chevron_a"),
		BOTTOM_CHEVRON_B(14, "bottom_chevron_b"),
		BOTTOM_CHEVRON_THIN_A(15, "bottom_chevron_thin_a"),
		BOTTOM_CHEVRON_THIN_B(16, "bottom_chevron_thin_b");
		
		private static final EnumChevronConnections[] ID_LOOKUP = new EnumChevronConnections[values().length];
		private final int id;
		private final String name;
		
		private EnumChevronConnections(int id, String name) {
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
		
		public static EnumChevronConnections byId(int id) {
	        if (id < 0 || id >= ID_LOOKUP.length) {
	        	id = 0;
	        }
	        
	        return ID_LOOKUP[id];
	    }
		
		static {
	        for (EnumChevronConnections type: values()) {
	            ID_LOOKUP[type.getId()] = type;
	        }
	    }
	}
}
