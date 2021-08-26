package com.silvaniastudios.roads.blocks.paint;

import java.util.ArrayList;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.PaintColour;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ChevronPaintBlock extends PaintBlockBase {

	public static final PropertyEnum<EnumType> META_ID = PropertyEnum.create("rotation", EnumType.class);
	public static final PropertyEnum<EnumJunctionConnections> TYPE = PropertyEnum.create("connect", EnumJunctionConnections.class);
	public static final PropertyBool EDGE = PropertyBool.create("edge");
	public static final PropertyBool HIDE = PropertyBool.create("hide");
	
	
	
	public ChevronPaintBlock(String name, EnumJunctionConnections type, boolean aType, String category, int[] coreMetas, boolean dynamic, PaintColour colour) {
		super(name, category, coreMetas, dynamic, colour);
		this.setDefaultState(this.blockState.getBaseState().withProperty(META_ID, EnumType.NORTH).withProperty(TYPE, type).withProperty(EDGE, false).withProperty(HIDE, false));
		if (!aType) { this.setCreativeTab(null); } else { this.setCreativeTab(FurenikusRoads.tab_paint_junction); }
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {		
		IBlockState stateNorth = world.getBlockState(pos.offset(EnumFacing.NORTH));		
		IBlockState stateEast = world.getBlockState(pos.offset(EnumFacing.EAST));
		IBlockState stateSouth = world.getBlockState(pos.offset(EnumFacing.SOUTH));
		IBlockState stateWest = world.getBlockState(pos.offset(EnumFacing.WEST));
		
		IBlockState stateLeft = null;
		IBlockState stateRight = null;
		IBlockState stateUp = null;
		IBlockState stateDown = null;
		
		int rot = 0;
		
		if (placer.getHorizontalFacing().equals(EnumFacing.NORTH)) {
			stateLeft = stateWest;
			stateRight = stateEast;
			stateUp = stateNorth;
			stateDown = stateSouth;
			rot = 0;
		}
		
		if (placer.getHorizontalFacing().equals(EnumFacing.EAST))  {
			stateLeft  = stateNorth;
			stateRight = stateSouth;
			stateUp = stateEast;
			stateDown = stateWest;
			rot = 1;
		}
		
		if (placer.getHorizontalFacing().equals(EnumFacing.SOUTH)) {
			stateLeft  = stateEast;
			stateRight = stateWest;
			stateUp = stateSouth;
			stateDown = stateNorth;
			rot = 2;
		}
		
		if (placer.getHorizontalFacing().equals(EnumFacing.WEST))  {
			stateLeft  = stateSouth;
			stateRight = stateNorth;
			stateUp = stateWest;
			stateDown = stateEast;
			rot = 3;
		}
		
		ArrayList<ChevronPaintBlock> chevronALeft = new ArrayList<ChevronPaintBlock>();
		ArrayList<ChevronPaintBlock> chevronARight = new ArrayList<ChevronPaintBlock>();
		ArrayList<ChevronPaintBlock> chevronBLeft = new ArrayList<ChevronPaintBlock>();
		ArrayList<ChevronPaintBlock> chevronBRight = new ArrayList<ChevronPaintBlock>();
		
		for (int i = 0; i < FRBlocks.col.length; i++) { 
			chevronALeft.add((ChevronPaintBlock) Block.REGISTRY.getObject(new ResourceLocation(FurenikusRoads.MODID, FRBlocks.col[i].getName() + "_chevron_left_a")));
			chevronALeft.add((ChevronPaintBlock) Block.REGISTRY.getObject(new ResourceLocation(FurenikusRoads.MODID, FRBlocks.col[i].getName() + "_chevron_left_a_thin")));
			chevronARight.add((ChevronPaintBlock) Block.REGISTRY.getObject(new ResourceLocation(FurenikusRoads.MODID, FRBlocks.col[i].getName() + "_chevron_right_a")));
			chevronARight.add((ChevronPaintBlock) Block.REGISTRY.getObject(new ResourceLocation(FurenikusRoads.MODID, FRBlocks.col[i].getName() + "_chevron_right_a_thin")));
			
			chevronBLeft.add((ChevronPaintBlock) Block.REGISTRY.getObject(new ResourceLocation(FurenikusRoads.MODID, FRBlocks.col[i].getName() + "_chevron_left_b")));
			chevronBLeft.add((ChevronPaintBlock) Block.REGISTRY.getObject(new ResourceLocation(FurenikusRoads.MODID, FRBlocks.col[i].getName() + "_chevron_left_b_thin")));
			chevronBRight.add((ChevronPaintBlock) Block.REGISTRY.getObject(new ResourceLocation(FurenikusRoads.MODID, FRBlocks.col[i].getName() + "_chevron_right_b")));
			chevronBRight.add((ChevronPaintBlock) Block.REGISTRY.getObject(new ResourceLocation(FurenikusRoads.MODID, FRBlocks.col[i].getName() + "_chevron_right_b_thin")));
		}
		
		for (int i = 0; i < chevronALeft.size(); i++) {
			//If we are placing left, and left is null, and right is right, then place left to match right. (ok read this comment like 2 years later and wtf fureniku this makes no sense)
			if (chevronALeft.contains(this) || chevronBLeft.contains(this)) {
				if (!(stateLeft.getBlock() instanceof ChevronPaintBlock)) {
					if (chevronARight.contains(stateRight.getBlock())) {
						return chevronALeft.get(chevronARight.indexOf(stateRight.getBlock())).getDefaultState().withProperty(META_ID, EnumType.byId(rot));
					} else if (chevronBRight.contains(stateRight.getBlock())) {
						return chevronBLeft.get(chevronBRight.indexOf(stateRight.getBlock())).getDefaultState().withProperty(META_ID, EnumType.byId(rot));
					}
				}
			}
			//Like above but right instead
			if (chevronARight.contains(this) || chevronBRight.contains(this)) {
				if (!(stateRight.getBlock() instanceof ChevronPaintBlock)) {
					if (chevronALeft.contains(stateLeft.getBlock())) {
						return chevronARight.get(chevronALeft.indexOf(stateLeft.getBlock())).getDefaultState().withProperty(META_ID, EnumType.byId(rot));
					} else if (chevronBLeft.contains(stateLeft.getBlock())) {
						return chevronBRight.get(chevronBLeft.indexOf(stateLeft.getBlock())).getDefaultState().withProperty(META_ID, EnumType.byId(rot));
					}
				}
			}
			//If those aren't the case, then connect as normal to surrounding blocks
			if (this.equals(chevronALeft.get(i)) || this.equals(chevronBLeft.get(i))) {
				if (stateLeft.getBlock().equals(chevronALeft.get(i)) || stateRight.getBlock().equals(chevronALeft.get(i))) {
					return chevronBLeft.get(i).getDefaultState().withProperty(META_ID, EnumType.byId(rot));
				}
				if (stateLeft.getBlock().equals(chevronBLeft.get(i)) || stateRight.getBlock().equals(chevronBLeft.get(i))) {
					return chevronALeft.get(i).getDefaultState().withProperty(META_ID, EnumType.byId(rot));
				}
				
				if (stateUp.getBlock().equals(chevronALeft.get(i)) || stateDown.getBlock().equals(chevronALeft.get(i))) {
					return chevronALeft.get(i).getDefaultState().withProperty(META_ID, EnumType.byId(rot));
				}
				if (stateUp.getBlock().equals(chevronBLeft.get(i)) || stateDown.getBlock().equals(chevronBLeft.get(i))) {
					return chevronBLeft.get(i).getDefaultState().withProperty(META_ID, EnumType.byId(rot));
				}
			}
			
			if (this.equals(chevronARight.get(i)) || this.equals(chevronBRight.get(i))) {
				if (stateLeft.getBlock().equals(chevronARight.get(i)) || stateRight.getBlock().equals(chevronARight.get(i))) {
					return chevronBRight.get(i).getDefaultState().withProperty(META_ID, EnumType.byId(rot));
				}
				if (stateLeft.getBlock().equals(chevronBRight.get(i)) || stateRight.getBlock().equals(chevronBRight.get(i))) {
					return chevronARight.get(i).getDefaultState().withProperty(META_ID, EnumType.byId(rot));
				}
				
				if (stateUp.getBlock().equals(chevronARight.get(i)) || stateDown.getBlock().equals(chevronARight.get(i))) {
					return chevronARight.get(i).getDefaultState().withProperty(META_ID, EnumType.byId(rot));
				}
				if (stateUp.getBlock().equals(chevronBRight.get(i)) || stateDown.getBlock().equals(chevronBRight.get(i))) {
					return chevronBRight.get(i).getDefaultState().withProperty(META_ID, EnumType.byId(rot));
				}
			}
		}
		
		return this.getDefaultState().withProperty(META_ID, EnumType.byId(rot));
	}
	
	public int placeMeta(int meta, IBlockState state) {
		if (state.getBlock() instanceof ChevronPaintBlock) {
			int sideMeta = state.getBlock().getMetaFromState(state);
			if (sideMeta > 3) {
				return meta;
			} else {
				return meta + 4;
			}
		}
		return meta;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(CreativeTabs creativeTabs, NonNullList<ItemStack> list) {
		list.add(new ItemStack(this, 1, 0));
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumType)state.getValue(META_ID)).getId();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(META_ID, EnumType.byId(meta));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {META_ID, TYPE, EDGE, HIDE});
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(EDGE, connectToLine(state, worldIn, pos)).withProperty(HIDE, shouldHide(state, worldIn, pos));
	}
	
	private boolean shouldHide(IBlockState state, IBlockAccess world, BlockPos pos) {
		if (world.getBlockState(pos.offset(EnumFacing.UP)).getBlock() instanceof JunctionFilterLinePaintBlock
				|| world.getBlockState(pos.offset(EnumFacing.DOWN)).getBlock() instanceof JunctionFilterLinePaintBlock) {
			return true;
		}
		return false;
	}
	
	private boolean connectToLine(IBlockState state, IBlockAccess world, BlockPos pos) {
		int meta = getMetaFromState(state);
		if (meta == 0) { return isBottomSection(world.getBlockState( pos.west())) || isBottomSection(world.getBlockState( pos.east())); }
		if (meta == 1) { return isBottomSection(world.getBlockState(pos.north())) || isBottomSection(world.getBlockState(pos.south())); }
		if (meta == 2) { return isBottomSection(world.getBlockState( pos.east())) || isBottomSection(world.getBlockState( pos.west())); }
		if (meta == 3) { return isBottomSection(world.getBlockState(pos.south())) || isBottomSection(world.getBlockState(pos.north())); }
		
		return false;
	}
	
	private boolean isBottomSection(IBlockState state) {
		if (state.getBlock() instanceof JunctionFilterLinePaintBlock) {
			JunctionFilterLinePaintBlock block = (JunctionFilterLinePaintBlock) state.getBlock();
			int meta = block.getMetaFromState(state);
			if (meta == 3 || meta == 7 || meta == 11 || meta == 15) { return true; }
		}
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
	
	@Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }
	
	public static enum EnumType implements IStringSerializable {
		//up = left side low, right side up. down = left side up, right side down.
		NORTH(0, "north"),
		EAST (1, "east"),
		SOUTH(2, "south"),
		WEST (3, "west");
		
		private static final EnumType[] ID_LOOKUP = new EnumType[values().length];
		private final int id;
		private final String name;
		
		private EnumType(int id, String name) {
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
		
		public static EnumType byId(int id) {
	        if (id < 0 || id >= ID_LOOKUP.length) {
	        	id = 0;
	        }
	        
	        return ID_LOOKUP[id];
	    }
		
		static {
	        for (EnumType type: values()) {
	            ID_LOOKUP[type.getId()] = type;
	        }
	    }
	}
	
	public static enum EnumJunctionConnections implements IStringSerializable {
		NONE(0, "none"),
		CHEVRON_A(1, "chevron_a"),
		CHEVRON_B(2, "chevron_b"),
		CHEVRON_THIN_A(3, "chevron_thin_a"),
		CHEVRON_THIN_B(4, "chevron_thin_b");
		
		private static final EnumJunctionConnections[] ID_LOOKUP = new EnumJunctionConnections[values().length];
		private final int id;
		private final String name;
		
		private EnumJunctionConnections(int id, String name) {
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
		
		public static EnumJunctionConnections byId(int id) {
	        if (id < 0 || id >= ID_LOOKUP.length) {
	        	id = 0;
	        }
	        
	        return ID_LOOKUP[id];
	    }
		
		static {
	        for (EnumJunctionConnections type: values()) {
	            ID_LOOKUP[type.getId()] = type;
	        }
	    }
	}
}