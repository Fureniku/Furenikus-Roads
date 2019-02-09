package com.silvaniastudios.roads;

import com.silvaniastudios.roads.blocks.FRBlocks;
import com.silvaniastudios.roads.items.FRItems;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

@Mod.EventBusSubscriber
public class CommonProxy {
	
	@Instance
	public static FurenikusRoads instance;
	
	public void registerItemRenderer(Item item, int meta, String id) {}
	
	public void openGui(int guiId) {}
	
	public void preInit() {}
	
	public void init() {
		oreDictRegistry();
		registerFurnaceRecipes();
	}
	
	public static void oreDictRegistry() {
		OreDictionary.registerOre("tarmac", new ItemStack(FRBlocks.road_block_standard, 1, 15));
		OreDictionary.registerOre("tarmac", new ItemStack(FRBlocks.road_block_light, 1, 15));
		OreDictionary.registerOre("tarmac", new ItemStack(FRBlocks.road_block_fine, 1, 15));
		OreDictionary.registerOre("tarmac", new ItemStack(FRBlocks.road_block_dark, 1, 15));
		OreDictionary.registerOre("tarmac", new ItemStack(FRBlocks.road_block_red, 1, 15));
		OreDictionary.registerOre("tarmac", new ItemStack(FRBlocks.road_block_blue, 1, 15));
		OreDictionary.registerOre("tarmac", new ItemStack(FRBlocks.road_block_white, 1, 15));
		OreDictionary.registerOre("tarmac", new ItemStack(FRBlocks.road_block_yellow, 1, 15));
		OreDictionary.registerOre("tarmac", new ItemStack(FRBlocks.road_block_green, 1, 15));
		
		OreDictionary.registerOre("limestone", new ItemStack(FRBlocks.generic_blocks, 1, 3));
		
		OreDictionary.registerOre("nuggetDiamond", new ItemStack(FRItems.diamond_nugget));
	}
	
	public static void registerFurnaceRecipes() {
		GameRegistry.addSmelting(FRItems.clinker_mix, new ItemStack(FRBlocks.generic_blocks, 1, 1), 0.0F);
	}
}
