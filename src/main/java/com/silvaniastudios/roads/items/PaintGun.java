package com.silvaniastudios.roads.items;

import java.util.List;

import javax.annotation.Nullable;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.RoadsConfig;
import com.silvaniastudios.roads.blocks.RoadBlock;
import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;
import com.silvaniastudios.roads.blocks.tileentities.paintfiller.PaintFillerEntity;
import com.silvaniastudios.roads.registries.PaintCategoryList;
import com.silvaniastudios.roads.registries.PaintGunItemRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PaintGun extends RoadItemBase {

	public PaintBlockBase selection;
	public int selMeta;

	public int white_paint = 0;
	public int yellow_paint = 0;
	public int red_paint = 0;

	public PaintGun(String name) {
		super(name, 1);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.NONE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("Place paint in the world!");
		tooltip.add("Shift right click to open UI.");
		if (stack.hasTagCompound()) {
			if (GuiScreen.isShiftKeyDown()) {
				NBTTagCompound nbt = stack.getTagCompound();
				int selectId = nbt.getInteger("selectedId");
				int pageId = nbt.getInteger("pageId");

				selection = getBlockFromSelection(selectId, pageId);
				selMeta = nbt.getInteger("selMeta");

				tooltip.add("Selection: " + new ItemStack(selection, 1, selMeta).getDisplayName());
				tooltip.add("Colour: " + nbt.getString("colour"));
				tooltip.add(" ");
				tooltip.add("White level: " + nbt.getInteger("white_paint"));
				tooltip.add("Yellow level: " + nbt.getInteger("yellow_paint"));
				tooltip.add("Red level: " + nbt.getInteger("red_paint"));
			} else {
				tooltip.add("Press Shift to see more information");
			}
		}
	}

	public static PaintBlockBase getBlockFromSelection(int sel, int page) {
		if (PaintGunItemRegistry.categoryList.size() >= page) {
			PaintCategoryList cat = PaintGunItemRegistry.categoryList.get(page);
			if (cat.size() >= sel) {
				return cat.getPaint(sel);
			} else {
				return cat.getPaint(0);
			}
		}
		return PaintGunItemRegistry.categoryList.get(0).getPaint(0);
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this));

			ItemStack stackFilled = new ItemStack(this);
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setInteger("selectedId", 1);
			nbt.setInteger("pageId", 1);
			nbt.setString("colour", "white");
			nbt.setInteger("white_paint", PaintFillerEntity.GUN_TANK_CAP);
			nbt.setInteger("yellow_paint", PaintFillerEntity.GUN_TANK_CAP);
			nbt.setInteger("red_paint", PaintFillerEntity.GUN_TANK_CAP);

			stackFilled.setTagCompound(nbt);

			items.add(stackFilled);
		}
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);

		if (stack.getItem() != this) {
			return EnumActionResult.FAIL;
		}

		NBTTagCompound nbt;

		if (!stack.hasTagCompound()) {
			nbt = new NBTTagCompound();
		} else {
			nbt = stack.getTagCompound();

			white_paint = nbt.getInteger("white_paint");
			yellow_paint = nbt.getInteger("yellow_paint");
			red_paint = nbt.getInteger("red_paint");
		}

		String colourId = nbt.getString("colour");

		//Legacy-proof code, ensure safe switching from int to string based colour information:
		if (colourId.equals("0")) { colourId = "white"; }
		if (colourId.equals("1")) { colourId = "yellow"; }
		if (colourId.equals("2")) { colourId = "red"; }
		nbt.setString("colour", colourId);

		boolean creative = player.isCreative();
		int cost = RoadsConfig.general.costToPaint;

		if (colourId.equals("yellow") && (yellow_paint >= cost || creative)) {
			if (selectBlockToPlace(nbt, world, pos, facing, hitX, hitY, hitZ, player, hand, colourId)) {
				if (!creative) { yellow_paint = yellow_paint - cost; }
			}
		}
		if (colourId.equals("red") && (red_paint >= cost || creative)) {
			if (selectBlockToPlace(nbt, world, pos, facing, hitX, hitY, hitZ, player, hand, colourId)) {
				if (!creative) { red_paint = red_paint - cost; }
			}
		}
		if (!colourId.equals("yellow") && !colourId.equals("red") && (white_paint >= cost || creative)) { //All other colours will use white paint
			if (selectBlockToPlace(nbt, world, pos, facing, hitX, hitY, hitZ, player, hand, colourId)) {
				if (!creative) { white_paint = white_paint - cost; }
			}
		}

		nbt.setInteger("white_paint", white_paint);
		nbt.setInteger("yellow_paint", yellow_paint);
		nbt.setInteger("red_paint", red_paint);

		return EnumActionResult.PASS;
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
		RayTraceResult rtr = this.rayTrace(worldIn, player, true);

		if (rtr != null) {
			if (rtr.typeOfHit == RayTraceResult.Type.BLOCK) {
				BlockPos hitPos = rtr.getBlockPos();
				Block block = worldIn.getBlockState(hitPos).getBlock();

				//Check if it's somewhere we'll probably be trying to put paint. If it is, we won't open the GUI to avoid frustrations.
				if (block instanceof PaintBlockBase || block instanceof RoadBlock) {
					return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(handIn));
				}
			}
		} else {
			//They're not aiming at any block, so they're not trying to place paint. Might as well give them the GUI.
			FurenikusRoads.proxy.openGui(0);
			return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(handIn));
		}

		if (player.isSneaking()) {
			FurenikusRoads.proxy.openGui(0);
		}

		return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(handIn));
	}

	public boolean selectBlockToPlace(NBTTagCompound nbt, World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, EntityPlayer player, EnumHand hand, String col) {
		/*if (RoadsConfig.general.debugLevel >= 1) {
			System.out.println("PaintGun#selectBlockToPlace called. Trying to find where it's called from...");
			StackTraceElement[] ste = Thread.currentThread().getStackTrace();
			for (int i = 0; i < ste.length; i++) {
				System.out.println("Class: " + ste[i].getClassName() + ", method: " + ste[i].getMethodName() + " line: " + ste[i].getLineNumber());
			}
		}*/


		int selection = nbt.getInteger("selectedId");
		int pageId = nbt.getInteger("pageId");

		BlockPos placePos = pos.offset(facing);

		if (!world.getBlockState(placePos).getBlock().isReplaceable(world, placePos)) { return false; }
		if (!(world.getBlockState(placePos.offset(EnumFacing.DOWN)).getBlock() instanceof PaintBlockBase) && player.isSneaking()) { return false; }

		PaintBlockBase block = PaintGunItemRegistry.getSelectedPaint(pageId, selection);
		int meta = PaintGunItemRegistry.getSelectedPaintMeta(pageId, selection);

		if (PaintGunItemRegistry.getCategory(pageId) != null && PaintGunItemRegistry.getCategory(pageId).getCategoryName().equalsIgnoreCase(PaintGunItemRegistry.TEXT)) {
			if (nbt.getBoolean("isLarge")) {
				meta = 4;
			}
		}
		
		if (block != null) {
			if (!col.equals("white")) { block = PaintGunItemRegistry.getAlternativeColour(block, col); }

			int offsetCount = 0;

			if (player.isSneaking()) {
				while (world.getBlockState(placePos).getBlock() instanceof PaintBlockBase && offsetCount < 3) {
					placePos = placePos.offset(EnumFacing.UP);
					offsetCount++;
				}
			}

			if (world.getBlockState(placePos.offset(EnumFacing.DOWN)).getBlock() instanceof BlockSnow) {
				return placeBlock(world, placePos.offset(EnumFacing.DOWN), block.getStateForPlacement(world, placePos, facing, hitX, hitY, hitZ, meta, player, hand));
			}

			return placeBlock(world, placePos, block.getStateForPlacement(world, placePos, facing, hitX, hitY, hitZ, meta, player, hand));
		}

		return false;
	}

	public boolean placeBlock(World world, BlockPos pos, IBlockState state) {
		boolean b = world.setBlockState(pos, state);
		world.notifyBlockUpdate(pos, state, state, 2); //People were getting desync issues, so try forcing a resync after placing paint.
		return b;
	}
}