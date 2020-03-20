package com.silvaniastudios.roads.blocks.tileentities.tarmaccutter;

import java.util.List;

import javax.annotation.Nullable;

import com.silvaniastudios.roads.blocks.tileentities.RoadTEBlock;
import com.silvaniastudios.roads.blocks.tileentities.RoadTileEntity;
import com.silvaniastudios.roads.items.TarmacCutterBlade;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TarmacCutterBlock extends RoadTEBlock {
	
	public static final PropertyEnum<EnumBladeType> BLADE = PropertyEnum.create("blade", EnumBladeType.class);
	
	public TarmacCutterBlock(String name, boolean electric) {
		super(name, electric, 4);
		this.setDefaultState(this.blockState.getBaseState()
				.withProperty(ROTATION, RoadTEBlock.EnumRotation.north)
				.withProperty(BLADE, EnumBladeType.none)
				.withProperty(FURNACE_ACTIVE, false));
	}
	
	@Override
	public TileEntity createTileEntity(World worldIn, IBlockState state) {
		if (electric) {
			return new TarmacCutterElectricEntity();
		}
		return new TarmacCutterEntity();
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("roads.gui.tarmac_cutter.tooltip_1"));
		tooltip.add(I18n.format("roads.gui.tarmac_cutter.tooltip_2"));
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {ROTATION, BLADE, FURNACE_ACTIVE, BASE_PLATE});
	}
	
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    	return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5625D, 1.0D);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
    	return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5625D, 1.0D);
    }
    
    @SuppressWarnings("deprecation")
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		TileEntity te = world.getTileEntity(pos);
		if (te instanceof RoadTileEntity) {
			RoadTileEntity tileEntity = (RoadTileEntity) te;
			EnumBladeType bladeType = getBladeType(world, pos);
			if (tileEntity.fuel_remaining > 0) {
				return state.withProperty(FURNACE_ACTIVE, true).withProperty(BLADE, bladeType).withProperty(BASE_PLATE, hasBasePlate(world, pos));
			} else {
				return state.withProperty(FURNACE_ACTIVE, false).withProperty(BLADE, bladeType).withProperty(BASE_PLATE, hasBasePlate(world, pos));
			}
		}
		return super.getActualState(state, world, pos);
	}
    
    public EnumBladeType getBladeType(IBlockAccess world, BlockPos pos) {
    	TileEntity tile = world.getTileEntity(pos);
    	if (tile != null && tile instanceof TarmacCutterEntity) {
    		TarmacCutterEntity te = (TarmacCutterEntity) tile;
    		
    		ItemStack item = te.inventory.getStackInSlot(TarmacCutterContainer.BLADE);
    		if (item.getItem() instanceof TarmacCutterBlade) {
    			TarmacCutterBlade blade = (TarmacCutterBlade) item.getItem();
    			return EnumBladeType.byName(blade.getType());
    		}
    	}
    	return EnumBladeType.none;
    }
    
    public static enum EnumBladeType implements IStringSerializable {
    	none("none"),
    	iron("iron"),
		gold("gold"),
    	diamond("diamond");
    	
		private final String name;
		
		private static final EnumBladeType[] META_LOOKUP = new EnumBladeType[values().length];
		
		private EnumBladeType(String name) {
			this.name = name;
		}

		@Override
		public String getName() {
			return this.name;
		}
		
		public static EnumBladeType byName(String name) {
			if (name != null) {
				for (int i = 0; i < META_LOOKUP.length; i++) {
					if (byMeta(i) != null) {
						if (byMeta(i).name.equalsIgnoreCase(name)) {
							return byMeta(i);
						}
					}
				}
			}
			
	        return EnumBladeType.none;
	    }
		
		public static EnumBladeType byMeta(int meta) {
			if (meta == 0) { return EnumBladeType.none; }
			if (meta == 1) { return EnumBladeType.iron; }
			if (meta == 2) { return EnumBladeType.gold; }
			
	        return EnumBladeType.diamond;
	    }
    }
}
