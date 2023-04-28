package com.silvaniastudios.roads.blocks.paint;

import java.util.Arrays;
import java.util.Random;

import javax.annotation.Nullable;

import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.BlockBase;
import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.NonPaintRoadTopBlock;
import com.silvaniastudios.roads.blocks.PaintColour;
import com.silvaniastudios.roads.blocks.decorative.CurbBlock;
import com.silvaniastudios.roads.blocks.diagonal.RoadBlockDiagonal;
import com.silvaniastudios.roads.items.FRItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PaintBlockBase extends BlockBase {
	
	private String categoryName;
	private int[] coreMetas;
	private boolean[] dynamic;
	private PaintColour colour;
	
	public PaintBlockBase(String name, String catName, int[] coreMetas, boolean[] dynamic, PaintColour colour) {
		super(name, Material.CLOTH);
		this.categoryName = catName;
		this.coreMetas = coreMetas;
		this.setHardness(2.0F);
		this.dynamic = dynamic;
		this.colour = colour;
	}
	
	public PaintBlockBase(String name, String catName, int[] coreMetas, boolean dynamic, PaintColour colour) {
		this(name, catName, coreMetas, null, colour);
		if (coreMetas != null) {
			this.dynamic = new boolean[coreMetas.length];
			if (dynamic) {
				Arrays.fill(this.dynamic, true);
			}
		}
	}
	
	public PaintColour getColour() {
		return this.colour;
	}
	
	public String getCategory() {
		return categoryName;
	}
	
	public int[] getCoreMetas() {
		return coreMetas;
	}
	
	public boolean canConnect(int id) {
		return dynamic[id];
	}
	
	//Searches through the unlocalized name, removes reference of colour and returns a name of the icon which should be uniform across all colours.
	//Used for the item and paint gun icons
	public String getIconName() {
		for (int i = 0; i < FRBlocks.col.length; i++) {
			if (this.name.contains(FRBlocks.col[i].getName() + "_")) {
				return this.name.replace(FRBlocks.col[i].getName() + "_", "");
			} else if (this.name.contains("_" + FRBlocks.col[i].getName())) { //for if the name ends with the colour for any reason which it never should but y'know
				return this.name.replace("_" + FRBlocks.col[i].getName(), "");
			}
		}
		
		return "";
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
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
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ItemStack.EMPTY.getItem();
	}
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	//Get pick block from the provided core meta options.
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		int closest = 0;
		for (int i = 0; i < getCoreMetas().length; i++) {
			if (getCoreMetas()[i] <= getMetaFromState(state) && getCoreMetas()[i] > closest) {
				closest = getCoreMetas()[i];
			}
		}
		return new ItemStack(this, 1, closest);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
		for (int i = 0; i < getCoreMetas().length; i++) {
			items.add(new ItemStack(this, 1, getCoreMetas()[i]));
		}
	}
	
	//@SuppressWarnings("deprecation")
	//TODO this has always been buggy anyway, reimplement later.
	/*public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		ItemStack item = player.getHeldItem(EnumHand.MAIN_HAND);
		if (item.getItem() instanceof PaintGun) {
			if (getItem(world, pos, state).getItem() instanceof ItemBlock && item.hasTagCompound()) {
				Block block = ((ItemBlock) getItem(world, pos, state).getItem()).getBlock();
				if (block instanceof PaintBlockBase) {
					PaintBlockBase whiteBlock = PaintGunItemRegistry.getWhite((PaintBlockBase) block);
					NBTTagCompound nbt = item.getTagCompound();
					int pageId = nbt.getInteger("pageId");
					int id = nbt.getInteger("selectedId");
					int selMeta = nbt.getInteger("selMeta");
					int selectedColour = nbt.getInteger("colour");
					boolean isLarge = nbt.getBoolean("isLarge");
					
					int blockMeta = getMetaFromState(state);
					
					int lineId = PaintGunItemRegistry.findLineId(whiteBlock, blockMeta);
					int iconId = PaintGunItemRegistry.findIconId(whiteBlock, blockMeta);
					int letterId = PaintGunItemRegistry.findLetterId(whiteBlock, blockMeta);
					int textId = PaintGunItemRegistry.findTextId(whiteBlock, blockMeta);
					int junctionId = PaintGunItemRegistry.findJunctionId(whiteBlock, blockMeta);
					
					if (lineId <= PaintGunItemRegistry.lines.size() && lineId >= 0) {
						pageId = 1;
						id = lineId;
						selMeta = PaintGunItemRegistry.linesMeta.get(lineId);
					}
					
					if (iconId <= PaintGunItemRegistry.icons.size() && iconId >= 0) {
						pageId = 2;
						id = iconId;
						selMeta = PaintGunItemRegistry.iconsMeta.get(iconId);
					}
					
					if (letterId <= PaintGunItemRegistry.letters.size() && letterId >= 0) {
						pageId = 3;
						id = letterId;
						selMeta = PaintGunItemRegistry.lettersMeta.get(letterId);
					}
					
					if (textId <= PaintGunItemRegistry.text.size() && textId >= 0) {
						pageId = 4;
						id = textId;
						selMeta = PaintGunItemRegistry.textMeta.get(textId);
						if (selMeta == 4) { isLarge = true; }
					}
					
					if (junctionId <= PaintGunItemRegistry.junction.size() && junctionId >= 0) {
						pageId = 5;
						id = junctionId;
						selMeta = PaintGunItemRegistry.junctionMeta.get(junctionId);
					}
					
					//This is all client-side only because reasons, so gotta send a packety boi
					FurenikusRoads.PACKET_CHANNEL.sendToServer(new PaintGunUpdatePacket(id, selMeta, selectedColour, pageId, isLarge));
					return ItemStack.EMPTY;
				}
			}
		}
		return getItem(world, pos, state);
	}*/
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		Item item = player.getHeldItem(hand).getItem();
		
		if (item.equals(FRItems.paint_scraper)) {
			world.setBlockToAir(pos);
		}
		
		return true;
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
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    	return new AxisAlignedBB(0.0D, -1+getBlockBelowHeight(source, pos), 0.0D, 1.0D, -1+getBlockBelowHeight(source, pos)+0.0625, 1.0D);
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return NULL_AABB;
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
        
        if (underBlock instanceof RoadBlockDiagonal) {
        	RoadBlockDiagonal rbd = (RoadBlockDiagonal) underBlock;
        	
        	return rbd.getBlockHeight(worldIn, rbd.getRoad(worldIn, pos.offset(EnumFacing.DOWN)), rbd.getRoadPos(worldIn, pos.offset(EnumFacing.DOWN)));
        }
        
        if (underBlock instanceof PaintBlockBase || underBlock instanceof NonPaintRoadTopBlock || underBlock instanceof CurbBlock) {
        	extraOffset = 0.062;
        }
        
        return underBlock.getBoundingBox(underState, worldIn, pos.offset(EnumFacing.DOWN)).maxY - extraOffset;
    }
}
