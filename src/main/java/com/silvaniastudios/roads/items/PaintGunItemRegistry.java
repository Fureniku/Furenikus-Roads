package com.silvaniastudios.roads.items;

import java.util.ArrayList;

import com.silvaniastudios.roads.FurenikusRoads;
import com.silvaniastudios.roads.blocks.paint.PaintBlockBase;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class PaintGunItemRegistry {
	
	public static ArrayList<PaintBlockBase> lines = new ArrayList<PaintBlockBase>();
	public static ArrayList<PaintBlockBase> icons = new ArrayList<PaintBlockBase>();
	public static ArrayList<PaintBlockBase> letters = new ArrayList<PaintBlockBase>();
	public static ArrayList<PaintBlockBase> text = new ArrayList<PaintBlockBase>();
	public static ArrayList<PaintBlockBase> junction = new ArrayList<PaintBlockBase>();
	public static ArrayList<PaintBlockBase> other = new ArrayList<PaintBlockBase>();
	
	public static ArrayList<Integer> linesMeta = new ArrayList<Integer>();
	public static ArrayList<Integer> iconsMeta = new ArrayList<Integer>();
	public static ArrayList<Integer> lettersMeta = new ArrayList<Integer>();
	public static ArrayList<Integer> textMeta = new ArrayList<Integer>();
	public static ArrayList<Integer> junctionMeta = new ArrayList<Integer>();
	public static ArrayList<Integer> otherMeta = new ArrayList<Integer>();
	
	public PaintGunItemRegistry() {}

	public static void registerLine(PaintBlockBase block) {
		lines.add(block);
		linesMeta.add(0);
	}
	
	public static void registerLine(PaintBlockBase block, int meta) {
		lines.add(block);
		linesMeta.add(meta);
	}
	
	public static void registerIcons(PaintBlockBase block) {
		icons.add(block);
		iconsMeta.add(0);
	}
	
	public static void registerIcons(PaintBlockBase block, int meta) {
		icons.add(block);
		iconsMeta.add(meta);
	}
	
	public static void registerLetters(PaintBlockBase block) {
		letters.add(block);
		lettersMeta.add(0);
		
		letters.add(block);
		lettersMeta.add(8);
	}
	
	public static void registerText(PaintBlockBase block) {
		text.add(block);
		textMeta.add(0);
	}
	
	public static void registerText(PaintBlockBase block, int meta) {
		text.add(block);
		textMeta.add(meta);
	}
	
	public static void registerJunction(PaintBlockBase block) {
		junction.add(block);
		junctionMeta.add(0);
	}
	
	public static void registerJunction(PaintBlockBase block, int meta) {
		junction.add(block);
		junctionMeta.add(meta);
	}
	
	public static void registerOther(PaintBlockBase block) {
		other.add(block);
		otherMeta.add(0);
	}
	
	public static void registerOther(PaintBlockBase block, int meta) {
		other.add(block);
		otherMeta.add(meta);
	}
	
	public static PaintBlockBase getBlockFromIdAndPage(int id, int page) {
		if (id >= 0) {
			if (page == 1 && id < lines.size()) { return lines.get(id); }
			if (page == 2 && id < icons.size()) { return icons.get(id); }
			if (page == 3 && id < letters.size()) { return letters.get(id); }
			if (page == 4 && id < text.size()/2) { return text.get(id); }
			if (page == 5 && id < junction.size()) { return junction.get(id); }
		}
		return null;
	}
	
	public static PaintBlockBase getWhite(PaintBlockBase block) {
		String unloc = block.getUnlocalizedName().substring(20);
		if (unloc.contains("yellow")) { unloc = unloc.replaceAll("yellow", "white"); }
		if (unloc.contains("red"))    { unloc = unloc.replaceAll("red",    "white"); }
		PaintBlockBase newBlock = (PaintBlockBase) ForgeRegistries.BLOCKS.getValue(new ResourceLocation(FurenikusRoads.MODID, unloc));
		
		if (newBlock != null) { return newBlock; }
		return block;
	}
	
	public static PaintBlockBase getYellow(PaintBlockBase block) {
		String unloc = block.getUnlocalizedName().substring(20);
		if (unloc.contains("white")) { unloc = unloc.replaceAll("white", "yellow"); }
		if (unloc.contains("red"))   { unloc = unloc.replaceAll("red",   "yellow"); }
		PaintBlockBase newBlock = (PaintBlockBase) ForgeRegistries.BLOCKS.getValue(new ResourceLocation(FurenikusRoads.MODID, unloc));
		
		if (newBlock != null) { return newBlock; }
		return block;
	}
	
	public static PaintBlockBase getRed(PaintBlockBase block) {
		String unloc = block.getUnlocalizedName().substring(20);
		if (unloc.contains("white"))  { unloc = unloc.replaceAll("white",  "red"); }
		if (unloc.contains("yellow")) { unloc = unloc.replaceAll("yellow", "red"); }
		PaintBlockBase newBlock = (PaintBlockBase) ForgeRegistries.BLOCKS.getValue(new ResourceLocation(FurenikusRoads.MODID, unloc));
		
		if (newBlock != null) { return newBlock; }
		return block;
	}
	
	public static int findLineId(PaintBlockBase block, int meta) {
		for (int i = 0; i < PaintGunItemRegistry.lines.size(); i++) {
			if (PaintGunItemRegistry.lines.get(i).equals(block)) {
				int count = 0;
				while (i+count < lines.size() && lines.get(i+count).equals(block)) {
					count++;
				}
				for (int j = 0; j < count; j++) {
					if (PaintGunItemRegistry.linesMeta.get(i+j) > meta) {
						return i+j-1;
					}
				}
				return i;
			}
		}
		
		return -1;
	}
	
	public static int findIconId(PaintBlockBase block, int meta) {
		for (int i = 0; i < PaintGunItemRegistry.icons.size(); i++) {
			if (PaintGunItemRegistry.icons.get(i).equals(block)) {
				int count = 0;
				while (i+count < icons.size() && icons.get(i+count).equals(block)) {
					count++;
				}
				for (int j = 0; j < count; j++) {
					if (PaintGunItemRegistry.iconsMeta.get(i+j) > meta) {
						return i+j-1;
					}
				}
				return i;
			}
		}
		
		return -1;
	}
	
	public static int findLetterId(PaintBlockBase block, int meta) {
		for (int i = 0; i < PaintGunItemRegistry.letters.size(); i++) {
			if (PaintGunItemRegistry.letters.get(i).equals(block)) {
				int count = 0;
				while (i+count < letters.size() && letters.get(i+count).equals(block)) {
					count++;
				}
				for (int j = 0; j < count; j++) {
					if (PaintGunItemRegistry.lettersMeta.get(i+j) > meta) {
						return i+j-1;
					}
				}
				return i;
			}
		}
		
		return -1;
	}
	
	public static int findTextId(PaintBlockBase block, int meta) {
		for (int i = 0; i < PaintGunItemRegistry.text.size(); i++) {
			if (PaintGunItemRegistry.text.get(i).equals(block)) {
				int count = 0;
				while (i+count < text.size() && text.get(i+count).equals(block)) {
					count++;
				}
				for (int j = 0; j < count; j++) {
					if (PaintGunItemRegistry.textMeta.get(i+j) > meta) {
						return i+j-1;
					}
				}
				return i;
			}
		}
		
		return -1;
	}
	
	public static int findJunctionId(PaintBlockBase block, int meta) {
		for (int i = 0; i < PaintGunItemRegistry.junction.size(); i++) {
			if (PaintGunItemRegistry.junction.get(i).equals(block)) {
				int count = 0;
				while (i+count < junction.size() && junction.get(i+count).equals(block)) {
					count++;
				}
				for (int j = 0; j < count; j++) {
					if (PaintGunItemRegistry.junctionMeta.get(i+j) > meta) {
						return i+j-1;
					}
				}
				return i;
			}
		}
		
		return -1;
	}
}
