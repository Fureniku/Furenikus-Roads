package com.silvaniastudios.roads.blocks.tileentities;

import com.google.common.collect.ImmutableList;
import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.items.ItemWrench;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class RoadTEBlock extends Block {

	protected String name;
	public static final PropertyEnum<EnumRotation> ROTATION = PropertyEnum.create("rotation", EnumRotation.class);
	public static final PropertyBool FURNACE_ACTIVE = PropertyBool.create("furnace_active");
	public static final PropertyBool BASE_PLATE = PropertyBool.create("base_plate");
	private int guiId = 0;
	protected boolean electric;
	
	public RoadTEBlock(String name, boolean electric, int guiId) {
		super(Material.IRON);
		this.name = name;
		setUnlocalizedName(FurenikusRoads.MODID + "." + name);
		setRegistryName(name);
		this.setCreativeTab(FurenikusRoads.tab_tools);
		this.setHardness(2.5F);
		this.setHarvestLevel("pickaxe", 1);
		this.guiId = guiId;
		this.electric = electric;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		if (placer.getHorizontalFacing() == EnumFacing.NORTH) { return this.getDefaultState().withProperty(ROTATION, EnumRotation.north); }
		if (placer.getHorizontalFacing() == EnumFacing.EAST)  { return this.getDefaultState().withProperty(ROTATION, EnumRotation.east); }
		if (placer.getHorizontalFacing() == EnumFacing.SOUTH) { return this.getDefaultState().withProperty(ROTATION, EnumRotation.south); }
		if (placer.getHorizontalFacing() == EnumFacing.WEST)  { return this.getDefaultState().withProperty(ROTATION, EnumRotation.west); }
		return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer);
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntity te = world.getTileEntity(pos);
		if (te != null) {
			IItemHandler cap = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
			
			for (int i = 0; i < cap.getSlots(); i++) {
				ItemStack stack = cap.getStackInSlot(i);
				if (stack != ItemStack.EMPTY) {
					InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);
				}
			}
		}
		super.breakBlock(world, pos, state);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (player.getHeldItem(hand).getItem() instanceof ItemWrench) {
			ItemWrench wrench = (ItemWrench) player.getHeldItem(hand).getItem();
			int mode = wrench.getMode(player.getHeldItem(hand));
			if (mode == 0) {
				if (state.getValue(ROTATION).equals(RoadTEBlock.EnumRotation.north)) {
					world.setBlockState(pos, state.withProperty(ROTATION, EnumRotation.east));
				}
				if (state.getValue(ROTATION).equals(RoadTEBlock.EnumRotation.east)) {
					world.setBlockState(pos, state.withProperty(ROTATION, EnumRotation.south));
				}
				if (state.getValue(ROTATION).equals(RoadTEBlock.EnumRotation.south)) {
					world.setBlockState(pos, state.withProperty(ROTATION, EnumRotation.west));
				}
				if (state.getValue(ROTATION).equals(RoadTEBlock.EnumRotation.west)) {
					world.setBlockState(pos, state.withProperty(ROTATION, EnumRotation.north));
				}
			}
			
			if (mode == 1) {
				if (world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof RoadTileEntity) {
					RoadTileEntity te = (RoadTileEntity) world.getTileEntity(pos);
					te.hasBasePlate = !te.hasBasePlate;
					te.sendUpdates();
				}
			}
		} else {
			openGui(world, pos, player);
		}
		return true;
	}
	
	public void openGui(World world, BlockPos pos, EntityPlayer player) {
		TileEntity te = world.getTileEntity(pos);
		if (!world.isRemote) {
			if (te != null && te instanceof RoadTileEntity) {
				player.openGui(FurenikusRoads.instance, guiId + (electric ? 5 : 0), world, pos.getX(), pos.getY(), pos.getZ());
			}
		}
	}
	
	public boolean isElectric() {
		return electric;
	}
	
	public boolean hasBasePlate(IBlockAccess world, BlockPos pos) {
		if (world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof RoadTileEntity) {
			RoadTileEntity te = (RoadTileEntity) world.getTileEntity(pos);
			
			return te.hasBasePlate;
		}
		
		return true;
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
    }
	
	public int getMetaFromState(IBlockState state) {
		if (state.getValue(ROTATION).equals(RoadTEBlock.EnumRotation.north)) {
			return 0;
		} else if (state.getValue(ROTATION).equals(RoadTEBlock.EnumRotation.east)) {
			return 1;
		} else if (state.getValue(ROTATION).equals(RoadTEBlock.EnumRotation.south)) {
			return 2;
		} else if (state.getValue(ROTATION).equals(RoadTEBlock.EnumRotation.west)) {
			return 3;
		}
		
		return 0;
	}
	
	public IBlockState getStateFromMeta(int meta) {
		if (meta == 0) { return this.getDefaultState().withProperty(ROTATION, RoadTEBlock.EnumRotation.north); }
		if (meta == 1) { return this.getDefaultState().withProperty(ROTATION, RoadTEBlock.EnumRotation.east);  }
		if (meta == 2) { return this.getDefaultState().withProperty(ROTATION, RoadTEBlock.EnumRotation.south); }
		return this.getDefaultState().withProperty(ROTATION, RoadTEBlock.EnumRotation.west); 	
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
    
	public static enum EnumRotation implements IStringSerializable {
    	north("north"),
    	east("east"),
		south("south"),
    	west("west");
    	
		private final String name;
		
		private EnumRotation(String name) {
			this.name = name;
		}

		@Override
		public String getName() {
			return this.name;
		}
		
		public static EnumRotation byMeta(int meta) {
			if (meta == 0) { return EnumRotation.north; }
			if (meta == 1) { return EnumRotation.east; }
			if (meta == 2) { return EnumRotation.south; }
			
	        return EnumRotation.west;
	    }
    }
	
	public boolean isFurnaceEnabled(IBlockState state, IBlockAccess world, BlockPos pos) {
		TileEntity te = world.getTileEntity(pos);
		if (te instanceof RoadTileEntity) {
			RoadTileEntity tileEntity = (RoadTileEntity) te;
			if (tileEntity.fuel_remaining > 0) {
				return true;
			}
		}
		return false;
	}
}
