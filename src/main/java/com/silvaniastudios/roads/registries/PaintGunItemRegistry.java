package com.silvaniastudios.roads.registries;

import java.util.ArrayList;

import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class PaintGunItemRegistry {
	
	public static final String LINES = "lines";
	public static final String ICONS = "icons";
	public static final String LETTERS = "letters";
	public static final String TEXT = "text";
	public static final String JUNCTIONS = "junctions";
	
	public static ArrayList<PaintCategoryList> categoryList = new ArrayList<PaintCategoryList>();
	
	public static void init() {
		PaintCategoryList lines = new PaintCategoryList(LINES);
		PaintCategoryList icons = new PaintCategoryList(ICONS);
		PaintCategoryList letters = new PaintCategoryList(LETTERS);
		PaintCategoryList text = new PaintCategoryList(TEXT);
		PaintCategoryList junctions = new PaintCategoryList(JUNCTIONS);
		
		categoryList.add(lines);
		categoryList.add(icons);
		categoryList.add(letters);
		categoryList.add(text);
		categoryList.add(junctions);
	}

	public static PaintBlockBase getAlternativeColour(PaintBlockBase paintWhite, String col) {
		ResourceLocation loc = new ResourceLocation(paintWhite.getRegistryName().toString().replace("white", col));
		if (ForgeRegistries.BLOCKS.containsKey(loc)) {
			return (PaintBlockBase) ForgeRegistries.BLOCKS.getValue(loc);
		}
		return paintWhite;
	}
	
	public static PaintBlockBase getSelectedPaint(int catId, int selectId) {
		if (catId <= categoryList.size()) {
			PaintCategoryList cat = categoryList.get(catId);
			if (selectId <= cat.size()) {
				return cat.getPaint(selectId);
			}
		}
		
		return null;
	}
	
	public static int getSelectedPaintMeta(int catId, int selectId) {
		if (catId <= categoryList.size()) {
			PaintCategoryList cat = categoryList.get(catId);
			if (selectId <= cat.size()) {
				return cat.getMeta(selectId);
			}
		}
		
		return 0;
	}
	
	public static PaintCategoryList getCategory(int catId) {
		if (catId <= categoryList.size()) {
			return categoryList.get(catId);
		}
		return null;
	}
	
	public static void registerPaint(PaintBlockBase block) {
		registerPaint(block, 0, 0);
	}
	
	public static void registerPaint(PaintBlockBase block, int meta, int index) {
		String cat = block.getCategory();
		
		
		for (int i = 0; i < categoryList.size(); i++) {
			if (categoryList.get(i).getCategoryName().equalsIgnoreCase(cat)) {
				categoryList.get(i).add(block, meta, index);
				return;
			}
		}
		
		//Category doesn't exist yet; create.
		PaintCategoryList list = new PaintCategoryList(cat);
		list.add(block, meta, index);
		categoryList.add(list);
		System.out.println("Creating category " + list.name + " for block " + block.getLocalizedName() + " and registering.");
		
	}
}
