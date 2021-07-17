package com.silvaniastudios.roads.blocks.tileentities.paintfiller.hopper;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.tileentities.RoadTEBlock;
import com.silvaniastudios.roads.blocks.tileentities.paintfiller.PaintFillerBlock;
import com.silvaniastudios.roads.items.ItemWrench;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PaintFillerHopperBlock extends Block {
	
	protected String name;
	
	public static final PropertyBool UP = PropertyBool.create("up");
	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool EAST = PropertyBool.create("east");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool WEST = PropertyBool.create("west");
	
	public static final PropertyEnum<RoadTEBlock.EnumRotation> ROTATION = PropertyEnum.create("rotation", RoadTEBlock.EnumRotation.class);
	
	public static final PropertyBool IS_OUTPUT = PropertyBool.create("is_output"); //refers to hopper -> filler. Things -> hopper is always an inversion of this.
	
	
	public PaintFillerHopperBlock(String name) {
		super(Material.IRON);
		this.name = name;
		setUnlocalizedName(FurenikusRoads.MODID + "." + name);
		setRegistryName(name);
		this.setCreativeTab(FurenikusRoads.tab_tools);
		this.setHardness(2.5F);
		this.setHarvestLevel("pickaxe", 1);
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("roads.gui.paint_filler_hopper.tooltip_1"));
	}
	
	@Override
	public TileEntity createTileEntity(World worldIn, IBlockState state) {
		return new PaintFillerHopperEntity();
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
    }
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (player.getHeldItem(hand).getItem() instanceof ItemWrench) {
			ItemWrench wrench = (ItemWrench) player.getHeldItem(hand).getItem();
			TileEntity tileEntity = world.getTileEntity(pos);
			
			if (tileEntity instanceof PaintFillerHopperEntity) {
				PaintFillerHopperEntity te = (PaintFillerHopperEntity) tileEntity;
				if (wrench.getMode(player.getHeldItem(hand)) == 1) {
					te.toggleOutputMode(player);
				} else {
					te.updateSide(facing, player);
				}
			}
		}
		return true;
	}
	
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		return state.withProperty(ROTATION, getRotation(world, pos))
				.withProperty(IS_OUTPUT, getOutput(world, pos))
				.withProperty(UP,    getIO(world, pos, EnumFacing.UP))
				.withProperty(NORTH, getIO(world, pos, EnumFacing.NORTH))
				.withProperty(EAST,  getIO(world, pos, EnumFacing.EAST))
				.withProperty(SOUTH, getIO(world, pos, EnumFacing.SOUTH))
				.withProperty(WEST,  getIO(world, pos, EnumFacing.WEST));
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {UP, NORTH, EAST, SOUTH, WEST, ROTATION, IS_OUTPUT});
	}
	
	public int getMetaFromState(IBlockState state) {
		return 0;
	}
	
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState(); 	
	}
	
	public boolean getOutput(IBlockAccess world, BlockPos pos) {
		TileEntity tileEntity = world.getTileEntity(pos);
		if (tileEntity instanceof PaintFillerHopperEntity) {
			PaintFillerHopperEntity te = (PaintFillerHopperEntity) tileEntity;
			return te.getOutput();
		}
		return true;
	}
	
	public boolean getIO(IBlockAccess world, BlockPos pos, EnumFacing dir) {
		TileEntity tileEntity = world.getTileEntity(pos);
		if (tileEntity instanceof PaintFillerHopperEntity) {
			PaintFillerHopperEntity te = (PaintFillerHopperEntity) tileEntity;
			
			return te.getIOFromSide(dir) != EnumIO.none;
		}
		return false;
	}
	
	public RoadTEBlock.EnumRotation getRotation(IBlockAccess world, BlockPos pos) {
		if (world.getBlockState(pos.offset(EnumFacing.DOWN)).getBlock() instanceof PaintFillerBlock) {
			return world.getBlockState(pos.offset(EnumFacing.DOWN)).getValue(RoadTEBlock.ROTATION);
		}
		return RoadTEBlock.EnumRotation.north;
	}
	
	public boolean isElectric(IBlockAccess world, BlockPos pos) {
		if (world.getBlockState(pos.offset(EnumFacing.DOWN)).getBlock() instanceof PaintFillerBlock) {
			PaintFillerBlock block = (PaintFillerBlock) world.getBlockState(pos.offset(EnumFacing.DOWN)).getBlock();
			return block.isElectric();
		}
		return false;
	}
	
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		boolean north = getIO(world, pos, EnumFacing.NORTH);
		boolean east  = getIO(world, pos, EnumFacing.EAST);
		boolean south = getIO(world, pos, EnumFacing.SOUTH);
		boolean west  = getIO(world, pos, EnumFacing.WEST);
		boolean up    = getIO(world, pos, EnumFacing.UP);
		
		double n = north ? 0.0D : 0.1875D;
		double e = east  ? 1.0D : 0.8125D;
		double s = south ? 1.0D : 0.8125D;
		double w = west  ? 0.0D : 0.1875D;
		double u = up    ? 1.0D : 0.75D;
		
    	return new AxisAlignedBB(w, 0.0D, n, e, u, s);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
    	return getBoundingBox(state, world, pos);
    }
	
	public void registerItemModel(Item itemBlock) {
		FurenikusRoads.proxy.registerItemRenderer(itemBlock, 0, name);
	}
	
	public Item createItemBlock() {
		return new ItemBlock(this).setRegistryName(getRegistryName());
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
	
	@Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }
	
	@SideOnly(Side.CLIENT)
	public void initModel() {
		StateMapperBase b = new DefaultStateMapper();
		BlockStateContainer bsc = this.getBlockState();
		ImmutableList<IBlockState> values = bsc.getValidStates();
		
		for(IBlockState state : values) {
			ModelResourceLocation mrl = new ModelResourceLocation(state.getBlock().getRegistryName(), b.getPropertyString(state.getProperties()));
			
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(state.getBlock()), this.getMetaFromState(state), mrl);
		}
	}
	
	@Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
    
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
    
    public static enum EnumIO implements IStringSerializable {
    	none("none"),
    	white("white"),
		yellow("yellow"),
    	red("red"),
    	item("item");
    	
		private final String name;
		
		private EnumIO(String name) {
			this.name = name;
		}

		@Override
		public String getName() {
			return this.name;
		}
		
		public static EnumIO byId(int byId) {
			if (byId == 1) { return EnumIO.white; }
			if (byId == 2) { return EnumIO.yellow; }
			if (byId == 3) { return EnumIO.red; }
			if (byId == 4) { return EnumIO.item; }
			
	        return EnumIO.none;
	    }
    }
}
