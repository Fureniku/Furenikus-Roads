package com.silvaniastudios.roads.items;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;
import com.silvaniastudios.roads.FRSounds;
import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.blocks.RoadBlock;
import com.silvaniastudios.roads.blocks.decorative.CurbBlock;
import com.silvaniastudios.roads.blocks.enums.EnumMeta;
import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PneumaticDrill extends ItemTool {
	
	protected String name;
	
	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(FRBlocks.road_block_standard, FRBlocks.road_block_concrete_1, FRBlocks.road_block_concrete_2, FRBlocks.road_block_light, FRBlocks.road_block_fine,
			FRBlocks.road_block_dark, FRBlocks.road_block_pale, FRBlocks.road_block_red, FRBlocks.road_block_blue, FRBlocks.road_block_white, FRBlocks.road_block_yellow, FRBlocks.road_block_green, FRBlocks.road_block_muddy,
			FRBlocks.road_block_muddy_dried, FRBlocks.road_block_stone, FRBlocks.road_block_grass, FRBlocks.road_block_dirt, FRBlocks.road_block_gravel, FRBlocks.road_block_sand);

	protected PneumaticDrill(String name, ToolMaterial materialIn) {
		super(materialIn, EFFECTIVE_ON);
		this.setHarvestLevel("pneumatic_drill", 0);
		this.name = name;
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.maxStackSize = 1;
		this.setMaxDamage(4096);
		this.setCreativeTab(FurenikusRoads.tab_tools);
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("item.tooltip.pneumatic_drill"));
	}

	public void registerItemModel() {
		FurenikusRoads.proxy.registerItemRenderer(this, 0, name);
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.NONE;
    }
	
	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if (stack.getItem() != this) {
			return EnumActionResult.FAIL;
		}
		
		if (player.isSneaking()) {
			BlockPos posAbove = pos.offset(EnumFacing.UP);
			for (int i = 0; i < 4; i++) {
				IBlockState stateAbove = world.getBlockState(posAbove);
				if (stateAbove.getBlock() instanceof CurbBlock) {
					world.setBlockToAir(posAbove);
					world.playSound(player, pos, FRSounds.pneumatic_drill, SoundCategory.BLOCKS, 1.0F, 1.0F);
					return EnumActionResult.PASS;
				}
				posAbove = posAbove.offset(EnumFacing.UP);
			}
		}
		
		if (world.getBlockState(pos).getBlock() instanceof RoadBlock) {
			RoadBlock roadBlock = (RoadBlock) world.getBlockState(pos).getBlock();
			int meta = roadBlock.getMetaFromState(world.getBlockState(pos));

			if (meta > 0) {
				world.setBlockState(pos, world.getBlockState(pos).withProperty(RoadBlock.ENUM_HEIGHT, EnumMeta.byMetadata(meta-1)));
				if (!player.isCreative()) {
					player.addItemStackToInventory(new ItemStack(roadBlock.getFragmentItem()));
				}
				world.playSound(player, pos, FRSounds.pneumatic_drill, SoundCategory.BLOCKS, 1.0F, 1.0F);
				return EnumActionResult.PASS;
			}
		}
		
		if (world.getBlockState(pos).getBlock() instanceof PaintBlockBase) {
			BlockPos posBelow = pos.offset(EnumFacing.DOWN);
			for (int i = 0; i < 4; i++) {
				IBlockState stateBelow = world.getBlockState(posBelow);
				if (stateBelow.getBlock() instanceof RoadBlock) {
					RoadBlock roadBlock = (RoadBlock) world.getBlockState(posBelow).getBlock();
					int meta = roadBlock.getMetaFromState(world.getBlockState(posBelow));
					
					if (meta > 0) {
						world.setBlockState(posBelow, world.getBlockState(posBelow).withProperty(RoadBlock.ENUM_HEIGHT, EnumMeta.byMetadata(meta-1)));
						if (!player.isCreative()) {
							player.addItemStackToInventory(new ItemStack(roadBlock.getFragmentItem()));
						}
						world.playSound(player, pos, FRSounds.pneumatic_drill, SoundCategory.BLOCKS, 1.0F, 1.0F);
						return EnumActionResult.PASS;
					}
				}
				posBelow = posBelow.offset(EnumFacing.DOWN);
			}
		}
		
		return EnumActionResult.PASS;
	}
}
