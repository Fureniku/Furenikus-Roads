package com.silvaniastudios.roads.blocks.paint;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.PaintColour;
import com.silvaniastudios.roads.blocks.enums.IMetaBlockName;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PaintBlockCustomRenderBase extends PaintBlockBase implements IMetaBlockName {
	
	int[] subBlocks = { 0 };
	
	public PaintBlockCustomRenderBase(String name, String catName, int[] coreMetas, boolean dynamic, PaintColour colour) {
		super(name, catName, coreMetas, dynamic, colour);
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return stack.getItemDamage() + "";
	}
	
    @SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
    	for (int i = 0; i < subBlocks.length; i++) {
    		items.add(new ItemStack(this, 1, subBlocks[i]));
    	}
    }
	
    @SideOnly(Side.CLIENT)
	public void initModel() {
		StateMapperBase b = new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				StateMapperBase b = new DefaultStateMapper();
				return new ModelResourceLocation(state.getBlock().getRegistryName(), b.getPropertyString(state.getProperties()));
			}
		};

		ModelLoader.setCustomStateMapper(this, b);
	}

	@SideOnly(Side.CLIENT)
	public void initItemModel() {
		Item itemBlock = Item.REGISTRY.getObject(new ResourceLocation(FurenikusRoads.MODID, this.name));
		ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(getRegistryName(), "inventory");
		for (int i = 0; i < subBlocks.length; i++) {
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemBlock, subBlocks[i], itemModelResourceLocation);
    	}
	}
}
