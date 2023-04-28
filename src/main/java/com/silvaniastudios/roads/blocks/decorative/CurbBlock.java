package com.silvaniastudios.roads.blocks.decorative;

import javax.annotation.Nullable;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.BlockBase;
import com.silvaniastudios.roads.blocks.BlockFakeLight;
import com.silvaniastudios.roads.blocks.enums.IConnectable;
import com.silvaniastudios.roads.blocks.enums.IMetaBlockName;
import com.silvaniastudios.roads.blocks.enums.IPostConnectable;
import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;
import com.silvaniastudios.roads.items.FRItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CurbBlock extends BlockBase implements IMetaBlockName {
	
	public static final PropertyEnum<EnumConnectSidewalk> CONNECT = PropertyEnum.create("connect", EnumConnectSidewalk.class);
	public static final PropertyEnum<EnumColourSidewalk> COLOUR = PropertyEnum.create("colour", EnumColourSidewalk.class);
	public static final PropertyDirection FACING =  PropertyDirection.create("zz_facing", EnumFacing.Plane.HORIZONTAL);
	

	public CurbBlock(String name, Material mat) {
		super(name, mat);
		this.setHardness(2.0F);
		this.setDefaultState(this.blockState.getBaseState()
				.withProperty(FACING, EnumFacing.NORTH)
				.withProperty(CONNECT, EnumConnectSidewalk.NONE)
				.withProperty(COLOUR, EnumColourSidewalk.STANDARD));
		this.setCreativeTab(FurenikusRoads.tab_road_parts);
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		int facingId = 0;
		
		if (placer.getHorizontalFacing() == EnumFacing.SOUTH) { facingId = 1; }
		if (placer.getHorizontalFacing() == EnumFacing.WEST)  { facingId = 2; }
		if (placer.getHorizontalFacing() == EnumFacing.EAST)  { facingId = 3; }
		
		return this.getStateFromMeta(meta + facingId);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
		items.add(new ItemStack(this, 1, 0));
		items.add(new ItemStack(this, 1, 4));
		items.add(new ItemStack(this, 1, 8));
		items.add(new ItemStack(this, 1, 12));
    }
	
	@Override
	public int getMetaFromState(IBlockState state) {
		EnumFacing facing = (EnumFacing)state.getValue(FACING);
		int rot = 0;
		if (facing == EnumFacing.SOUTH) { rot = 1; }
		if (facing == EnumFacing.WEST)  { rot = 2; }
		if (facing == EnumFacing.EAST)  { rot = 3; }
		
		EnumColourSidewalk colour = (EnumColourSidewalk) state.getValue(COLOUR);
		int col = 0;
		if (colour == EnumColourSidewalk.WHITE)  { col = 4; }
		if (colour == EnumColourSidewalk.YELLOW) { col = 8; }
		if (colour == EnumColourSidewalk.RED)    { col = 12; }
		
		return rot + col;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		if (meta == 0) { return this.getDefaultState().withProperty(FACING, EnumFacing.NORTH).withProperty(COLOUR, EnumColourSidewalk.STANDARD); }
		if (meta == 1) { return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH).withProperty(COLOUR, EnumColourSidewalk.STANDARD); }
		if (meta == 2) { return this.getDefaultState().withProperty(FACING, EnumFacing.WEST ).withProperty(COLOUR, EnumColourSidewalk.STANDARD); }
		if (meta == 3) { return this.getDefaultState().withProperty(FACING, EnumFacing.EAST ).withProperty(COLOUR, EnumColourSidewalk.STANDARD); }
		
		if (meta == 4) { return this.getDefaultState().withProperty(FACING, EnumFacing.NORTH).withProperty(COLOUR, EnumColourSidewalk.WHITE); }
		if (meta == 5) { return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH).withProperty(COLOUR, EnumColourSidewalk.WHITE); }
		if (meta == 6) { return this.getDefaultState().withProperty(FACING, EnumFacing.WEST ).withProperty(COLOUR, EnumColourSidewalk.WHITE); }
		if (meta == 7) { return this.getDefaultState().withProperty(FACING, EnumFacing.EAST ).withProperty(COLOUR, EnumColourSidewalk.WHITE); }
		
		if (meta == 8)  { return this.getDefaultState().withProperty(FACING, EnumFacing.NORTH).withProperty(COLOUR, EnumColourSidewalk.YELLOW); }
		if (meta == 9)  { return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH).withProperty(COLOUR, EnumColourSidewalk.YELLOW); }
		if (meta == 10) { return this.getDefaultState().withProperty(FACING, EnumFacing.WEST ).withProperty(COLOUR, EnumColourSidewalk.YELLOW); }
		if (meta == 11) { return this.getDefaultState().withProperty(FACING, EnumFacing.EAST ).withProperty(COLOUR, EnumColourSidewalk.YELLOW); }
		
		if (meta == 12) { return this.getDefaultState().withProperty(FACING, EnumFacing.NORTH).withProperty(COLOUR, EnumColourSidewalk.RED); }
		if (meta == 13) { return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH).withProperty(COLOUR, EnumColourSidewalk.RED); }
		if (meta == 14) { return this.getDefaultState().withProperty(FACING, EnumFacing.WEST ).withProperty(COLOUR, EnumColourSidewalk.RED); }
		if (meta == 15) { return this.getDefaultState().withProperty(FACING, EnumFacing.EAST ).withProperty(COLOUR, EnumColourSidewalk.RED); }
		
		return this.getDefaultState().withProperty(FACING, EnumFacing.NORTH).withProperty(COLOUR, EnumColourSidewalk.STANDARD);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {FACING, CONNECT, COLOUR});
	}

	
	private EnumConnectSidewalk canLineConnectTo(IBlockAccess world, BlockPos pos, boolean leftSide) {
		IBlockState state = world.getBlockState(pos);
		int meta = getMetaFromState(state);
		
		BlockPos posNorth = pos.offset(EnumFacing.NORTH);
		BlockPos posEast  = pos.offset(EnumFacing.EAST);
		BlockPos posSouth = pos.offset(EnumFacing.SOUTH);
		BlockPos posWest  = pos.offset(EnumFacing.WEST);
		
		boolean blockNorth = (world.getBlockState(posNorth).getBlock() instanceof CurbBlock);
		boolean blockEast  = (world.getBlockState(posEast) .getBlock() instanceof CurbBlock);
		boolean blockSouth = (world.getBlockState(posSouth).getBlock() instanceof CurbBlock);
		boolean blockWest  = (world.getBlockState(posWest) .getBlock() instanceof CurbBlock);
		
		boolean blockNorthWest = (world.getBlockState(posNorth.offset(EnumFacing.WEST)).getBlock() instanceof CurbBlock);
		boolean blockNorthEast = (world.getBlockState(posNorth.offset(EnumFacing.EAST)).getBlock() instanceof CurbBlock);
		boolean blockSouthWest = (world.getBlockState(posSouth.offset(EnumFacing.WEST)).getBlock() instanceof CurbBlock);
		boolean blockSouthEast = (world.getBlockState(posSouth.offset(EnumFacing.EAST)).getBlock() instanceof CurbBlock);
		
		//north
		if (meta == 0 || meta == 4 || meta == 8 || meta == 12) {
			if (blockSouth) {
				int metaOffset = getMetaFromState(world.getBlockState(posSouth));
				if (metaOffset == 3 || metaOffset == 7 || metaOffset == 11 || metaOffset == 15) {
					return EnumConnectSidewalk.RIGHT;
				}
				if (metaOffset == 2 || metaOffset == 6 || metaOffset == 10 || metaOffset == 14) {
					return EnumConnectSidewalk.LEFT;
				}
			}
			if (blockSouthWest) {
				int metaOffset = getMetaFromState(world.getBlockState(posSouth.offset(EnumFacing.WEST)));
				if (metaOffset == meta) { return EnumConnectSidewalk.LEFT_B; }
			}
			if (blockSouthEast) {
				int metaOffset = getMetaFromState(world.getBlockState(posSouth.offset(EnumFacing.EAST)));
				if (metaOffset == meta) { return EnumConnectSidewalk.RIGHT_B; }
			}
		}
		
		//east
		if (meta == 3 || meta == 7 || meta == 11 || meta == 15) {
			if (blockWest) {
				int metaOffset  = getMetaFromState(world.getBlockState(posWest));
				if (metaOffset == 0 || metaOffset == 4 || metaOffset == 8 || metaOffset == 12) {
					return EnumConnectSidewalk.LEFT;
				}
				if (metaOffset == 1 || metaOffset == 5 || metaOffset == 9 || metaOffset == 13) {
					return EnumConnectSidewalk.RIGHT;
				}
			}
			if (blockNorthWest) {
				int metaOffset = getMetaFromState(world.getBlockState(posNorth.offset(EnumFacing.WEST)));
				if (metaOffset == meta) { return EnumConnectSidewalk.LEFT_B; }
			}
			if (blockSouthWest) {
				int metaOffset = getMetaFromState(world.getBlockState(posSouth.offset(EnumFacing.WEST)));
				if (metaOffset == meta) { return EnumConnectSidewalk.RIGHT_B; }
			}
		}
		
		//south
		if (meta == 1 || meta == 5 || meta == 9 || meta == 13) {
			if (blockNorth) { 
				int metaOffset = getMetaFromState(world.getBlockState(posNorth));
				if (metaOffset == 3 || metaOffset == 7 || metaOffset == 11 || metaOffset == 15) {
					return EnumConnectSidewalk.LEFT;
				}
				if (metaOffset == 2 || metaOffset == 6 || metaOffset == 10 || metaOffset == 14) {
					return EnumConnectSidewalk.RIGHT;
				}
			}
			if (blockNorthEast) {
				int metaOffset = getMetaFromState(world.getBlockState(posNorth.offset(EnumFacing.EAST)));
				if (metaOffset == meta) { return EnumConnectSidewalk.LEFT_B; }
			}
			if (blockNorthWest) {
				int metaOffset = getMetaFromState(world.getBlockState(posNorth.offset(EnumFacing.WEST)));
				if (metaOffset == meta) { return EnumConnectSidewalk.RIGHT_B; }
			}
		}
		
		//west
		if (meta == 2 || meta == 6 || meta == 10 || meta == 14) {
			if (blockEast) {
				int metaOffset  = getMetaFromState(world.getBlockState(posEast));
				if (metaOffset == 0 || metaOffset == 4 || metaOffset == 8 || metaOffset == 12) {
					return EnumConnectSidewalk.RIGHT;
				}
				if (metaOffset == 1 || metaOffset == 5 || metaOffset == 9 || metaOffset == 13) {
					return EnumConnectSidewalk.LEFT;
				}
			}
			if (blockSouthEast) {
				int metaOffset = getMetaFromState(world.getBlockState(posSouth.offset(EnumFacing.EAST)));
				if (metaOffset == meta) { return EnumConnectSidewalk.LEFT_B; }
			}
			if (blockNorthEast) {
				int metaOffset = getMetaFromState(world.getBlockState(posNorth.offset(EnumFacing.EAST)));
				if (metaOffset == meta) { return EnumConnectSidewalk.RIGHT_B; }
			}
		}

		return EnumConnectSidewalk.NONE;
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		Item item = player.getHeldItem(hand).getItem();
		
		if (item.equals(FRItems.pneumatic_drill) && player.isSneaking()) {
			world.setBlockToAir(pos);
			return true;
		}
		return true;
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(CONNECT, canLineConnectTo(worldIn, pos, true));
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory1"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 4, new ModelResourceLocation(getRegistryName(), "inventory2"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 8, new ModelResourceLocation(getRegistryName(), "inventory3"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 12, new ModelResourceLocation(getRegistryName(), "inventory4"));
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }
	
	@Deprecated
	@Override
    public boolean isBlockNormalCube(IBlockState state) {
        return false;
    }
	
	@Override
	public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
        return false;
    }
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}
	
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    	return new AxisAlignedBB(0.0D, 0.0D - findBelowBlock(source, pos), 0.0D, 1.0D, 0.125D - findBelowBlock(source, pos), 1.0D);
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return NULL_AABB;
    }
    
    @Override
	public String getSpecialName(ItemStack stack) {
		return stack.getItemDamage() + "";
	}
    
    @Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (world.getBlockState(pos.offset(EnumFacing.DOWN)).getBlock() instanceof BlockAir) {
			if (RoadsConfig.general.breakPaintOnBlockBreak) {
				world.setBlockToAir(pos);
			}
		}
	}
    
    @Override
    public Vec3d getOffset(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        double offsetY = 1.0 - getBlockConnectedHeight(state, worldIn, pos);
        return new Vec3d(0, -offsetY+0.75, 0);
    }
    
    @SuppressWarnings("deprecation")
	private double getBlockConnectedHeight(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
    	int meta = getMetaFromState(state);
    	EnumFacing checkDir = state.getValue(FACING);

		BlockPos downOffset = pos.offset(EnumFacing.DOWN);
		IBlockState downState = worldIn.getBlockState(downOffset);
		int countDown = 0;
		//while block below is paint or air, keep searching
		while ((downState.getBlock() instanceof PaintBlockBase || downState.getBlock() == Blocks.AIR) && countDown < 2) {
			downOffset = downOffset.offset(EnumFacing.DOWN);
			downState = worldIn.getBlockState(downOffset);
			countDown++;
		}
		if (downState.getBlock() instanceof BlockBase || downState.getBlock().getBlockFaceShape(worldIn, downState, downOffset, EnumFacing.UP).equals(BlockFaceShape.SOLID)) {
			BlockPos sideOffset = downOffset.offset(checkDir);
			IBlockState endBlock = worldIn.getBlockState(sideOffset);
			if (endBlock.getBlock() != Blocks.AIR) {
				IBlockState endState = worldIn.getBlockState(sideOffset.offset(EnumFacing.UP));
				if (endState.getBlock() instanceof BlockBase &&
						!(endState.getBlock() instanceof PaintBlockBase) &&
						!(endState.getBlock() instanceof IConnectable) &&
						!(endState.getBlock() instanceof IPostConnectable) &&
						!(endState.getBlock() instanceof BlockFakeLight)) {
					return endState.getBoundingBox(worldIn, sideOffset.offset(EnumFacing.UP)).maxY-countDown;
				}
				return endBlock.getBoundingBox(worldIn, sideOffset).maxY-countDown-1;
			}
		}
        
        return downState.getBoundingBox(worldIn, downOffset).maxY-countDown-1;
    }
    
    private double findBelowBlock(IBlockAccess worldIn, BlockPos pos) {
    	BlockPos downOffset = pos.offset(EnumFacing.DOWN);
		IBlockState downState = worldIn.getBlockState(downOffset);
		int countDown = 0;
		
		while ((downState.getBlock() instanceof PaintBlockBase || downState.getBlock() == Blocks.AIR) && countDown < 2) {
			downOffset = downOffset.offset(EnumFacing.DOWN);
			downState = worldIn.getBlockState(downOffset);
			countDown++;
		}

		return (1.0 - downState.getBoundingBox(worldIn, downOffset).maxY) + countDown + 1;
    }
    
    
    
    
    public static enum EnumConnectSidewalk implements IStringSerializable {
    	NONE(0, "none"),
    	LEFT(1, "left"),
    	RIGHT(2, "right"),
    	LEFT_B(3, "left_b"),
    	RIGHT_B(4, "right_b");
    	
    	private static final EnumConnectSidewalk[] META_LOOKUP = new EnumConnectSidewalk[values().length];
    	private final int meta;
    	private final String name;
    	
    	private EnumConnectSidewalk(int meta, String name) {
    		this.meta = meta;
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
    	
    	public int getMetadata() {
            return this.meta;
        }
    	
    	public static EnumConnectSidewalk byMetadata(int meta) {
            if (meta < 0 || meta >= META_LOOKUP.length) {
                meta = 0;
            }
            
            return META_LOOKUP[meta];
        }
    	
    	static {
            for (EnumConnectSidewalk type: values()) {
                META_LOOKUP[type.getMetadata()] = type;
            }
        }
    }
    
    public static enum EnumColourSidewalk implements IStringSerializable {
    	STANDARD(0, "standard"),
    	WHITE(1, "white"),
    	YELLOW(2, "yellow"),
    	RED(3, "red");
    	
    	
    	private static final EnumColourSidewalk[] META_LOOKUP = new EnumColourSidewalk[values().length];
    	private final int meta;
    	private final String name;
    	
    	private EnumColourSidewalk(int meta, String name) {
    		this.meta = meta;
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
    	
    	public int getMetadata() {
            return this.meta;
        }
    	
    	public static EnumColourSidewalk byMetadata(int meta) {
            if (meta < 0 || meta >= META_LOOKUP.length) {
                meta = 0;
            }
            
            return META_LOOKUP[meta];
        }
    	
    	static {
            for (EnumColourSidewalk type: values()) {
                META_LOOKUP[type.getMetadata()] = type;
            }
        }
    }
}
