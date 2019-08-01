package com.silvaniastudios.roads.blocks;

import javax.annotation.Nullable;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.enums.EnumMeta;
import com.silvaniastudios.roads.blocks.enums.IConnectable;
import com.silvaniastudios.roads.blocks.enums.IMetaBlockName;
import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BarrierEndBlock extends BlockBase implements IConnectable, IMetaBlockName {
	
	public static final PropertyEnum<EnumMeta> META_ID = PropertyEnum.create("meta", EnumMeta.class);
	
	public BarrierEndBlock(String name) {
		super(name, Material.IRON);
		this.setHardness(2.0F);
		setDefaultState(this.blockState.getBaseState().withProperty(META_ID, EnumMeta.id0));
		this.setCreativeTab(FurenikusRoads.tab_road_parts);
		this.setHardness(1.5F);
		this.setHarvestLevel("pickaxe", 1);
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return stack.getItemDamage() + "";
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		int rot = 0;
		if (placer.getHorizontalFacing() == EnumFacing.EAST)  { rot = 1; }
		if (placer.getHorizontalFacing() == EnumFacing.SOUTH) { rot = 2; }
		if (placer.getHorizontalFacing() == EnumFacing.WEST)  { rot = 3; }
		
		return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta+rot, placer);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(CreativeTabs creativeTabs, NonNullList<ItemStack> list) {
		list.add(new ItemStack(this, 1, 0));
		//list.add(new ItemStack(this, 1, 4));
		//list.add(new ItemStack(this, 1, 8));
		//list.add(new ItemStack(this, 1, 12));
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
    @Override
    public int damageDropped(IBlockState state) {
    	int meta = getMetaFromState(state);
    	if (meta < 4) { return 0; }
    	if (meta < 8) { return 4; } 
    	if (meta < 12) { return 8; }
    	if (meta < 16) { return 12; }
    	return 0;
    }
    
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {META_ID});
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(META_ID, EnumMeta.byMetadata(meta));
    }
	
	@Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumMeta)state.getValue(META_ID)).getMetadata();
    }
	
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
    	return getBox(state, world, pos);
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
        return getBox(state, world, pos);
    }
    
    private AxisAlignedBB getBox(IBlockState state, IBlockAccess world, BlockPos pos) {
    	int meta = getMetaFromState(state);
    	double p = 1.0/16.0;
    	if (meta == 0) { return new AxisAlignedBB(p* 5, -1+getBlockBelowHeight(world, pos),    0, p*11, -1+getBlockBelowHeight(world, pos)+1.0, p* 2); }
    	if (meta == 1) { return new AxisAlignedBB(p*14, -1+getBlockBelowHeight(world, pos), p* 5,    1, -1+getBlockBelowHeight(world, pos)+1.0, p*11); }
    	if (meta == 2) { return new AxisAlignedBB(p* 5, -1+getBlockBelowHeight(world, pos), p*14, p*11, -1+getBlockBelowHeight(world, pos)+1.0, 1); }
    	if (meta == 3) { return new AxisAlignedBB(0,    -1+getBlockBelowHeight(world, pos), p* 5, p* 2, -1+getBlockBelowHeight(world, pos)+1.0, p*11); }
    	
    	return new AxisAlignedBB(0, -1+getBlockBelowHeight(world, pos), 0, 1, -1+getBlockBelowHeight(world, pos)+1.0, 1);
    }
    
    @Override
    public Block.EnumOffsetType getOffsetType()
    {
        return Block.EnumOffsetType.XYZ;
    }
    
    @Override
    public Vec3d getOffset(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        double offset = 1.0 - getBlockBelowHeight(worldIn, pos);
        return new Vec3d(0, -offset, 0);
    }
    
    @SuppressWarnings("deprecation")
	public double getBlockBelowHeight(IBlockAccess worldIn, BlockPos pos) {
    	IBlockState underState = worldIn.getBlockState(pos.offset(EnumFacing.DOWN));
        Block underBlock = underState.getBlock();
        double extraOffset = 0.0;
        
        if (underBlock instanceof PaintBlockBase || underBlock instanceof NonPaintRoadTopBlock || underBlock instanceof CurbBlock) {
        	extraOffset = 0.062;
        }
        
        return underBlock.getBoundingBox(underState, worldIn, pos.offset(EnumFacing.DOWN)).maxY - extraOffset;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void initModel() {
    	ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    	//ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 4, new ModelResourceLocation(getRegistryName(), "inventory2"));
    	//ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 8, new ModelResourceLocation(getRegistryName(), "inventory3"));
    	//ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 12, new ModelResourceLocation(getRegistryName(), "inventory4"));
    }
}
