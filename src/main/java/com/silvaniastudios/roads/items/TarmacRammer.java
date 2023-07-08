package com.silvaniastudios.roads.items;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;
import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.RoadBlock;
import com.silvaniastudios.roads.blocks.enums.EnumMeta;
import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TarmacRammer extends ItemTool {
	
	protected String name;
	
	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet();

	protected TarmacRammer(String name, ToolMaterial materialIn) {
		super(materialIn, EFFECTIVE_ON);
		this.setHarvestLevel("pneumatic_drill", 0);
		this.name = name;
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.maxStackSize = 1;
		this.setCreativeTab(FurenikusRoads.tab_tools);
	}

	public void registerItemModel() {
		FurenikusRoads.proxy.registerItemRenderer(this, 0, name);
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("item.tooltip.tarmac_rammer_1"));
		tooltip.add(I18n.format("item.tooltip.tarmac_rammer_2"));
	}

	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);

		if (stack.getItem() != this) {
			return EnumActionResult.FAIL;
		}
		
		if (world.getBlockState(pos).getBlock() instanceof RoadBlock) {
			RoadBlock roadBlock = (RoadBlock) world.getBlockState(pos).getBlock();
			int meta = roadBlock.getMetaFromState(world.getBlockState(pos));

			if (meta < 15) {
				if (!player.isCreative()) {
					for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
						if (player.inventory.getStackInSlot(i).getItem().equals(roadBlock.getFragmentItem())) {
							world.setBlockState(pos, world.getBlockState(pos).withProperty(RoadBlock.ENUM_HEIGHT, EnumMeta.byMetadata(meta+1)));
							player.inventory.decrStackSize(i, 1);
							return EnumActionResult.PASS;
						}
					}
				} else {
					world.setBlockState(pos, world.getBlockState(pos).withProperty(RoadBlock.ENUM_HEIGHT, EnumMeta.byMetadata(meta+1)));
					return EnumActionResult.PASS;
				}
			}
		}
		
		if (world.getBlockState(pos).getBlock() instanceof PaintBlockBase) {
			BlockPos posBelow = pos.offset(EnumFacing.DOWN);
			for (int i = 0; i < 4; i++) {
				IBlockState stateBelow = world.getBlockState(posBelow);
				if (stateBelow.getBlock() instanceof RoadBlock) {
					RoadBlock roadBlock = (RoadBlock) stateBelow.getBlock();
					int meta = roadBlock.getMetaFromState(stateBelow);
					if (meta < 15) {
						if (!player.isCreative()) {
							for (int j = 0; j < player.inventory.getSizeInventory(); j++) {
								if (player.inventory.getStackInSlot(j).getItem().equals(roadBlock.getFragmentItem())) {
									world.setBlockState(posBelow, stateBelow.withProperty(RoadBlock.ENUM_HEIGHT, EnumMeta.byMetadata(meta+1)));
									player.inventory.decrStackSize(j, 1);
									return EnumActionResult.PASS;
								}
							}
						} else {
							world.setBlockState(posBelow, stateBelow.withProperty(RoadBlock.ENUM_HEIGHT, EnumMeta.byMetadata(meta+1)));
							return EnumActionResult.PASS;
						}
					}
				}
				posBelow = posBelow.offset(EnumFacing.DOWN);
			}
		}
		
		return EnumActionResult.PASS;
	}
}
