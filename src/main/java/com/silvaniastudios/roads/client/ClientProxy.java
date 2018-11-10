package com.silvaniastudios.roads.client;

import com.silvaniastudios.roads.CommonProxy;
import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.FRBlocks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(FurenikusRoads.MODID + ":" + id, "inventory"));
	}

	@Override
	public void init() {
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new ModBlockColours(), FRBlocks.road_block_grass);
	}
}
