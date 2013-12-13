//This file is for handling any NBT data I want to store, for example the state of traffic lights.
//There may well be a better way of doing this, but this is how I've learned it. Please feel free to submit a PR if you have any ideas.

package co.uk.silvania.roads;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import cpw.mods.fml.relauncher.ReflectionHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraft.world.storage.ISaveHandler;

public class NBTConfig {

    public static File getConfigFolder() {
        return new File(new File(Minecraft.getMinecraft().mcDataDir, "config"), "flenixroads");
    }
    
    public static File getWorldConfig(World world) {
        return new File(getWorldDir(world), "flenixroads");
    }
    
    public static File getWorldDir(World world) {
        try {
            ISaveHandler worldsaver = world.getSaveHandler();
            IChunkLoader loader = worldsaver.getChunkLoader(world.provider);
            File file = ReflectionHelper.<File, AnvilChunkLoader> getPrivateValue(AnvilChunkLoader.class, (AnvilChunkLoader) loader, 3);
            return file.getName().contains("DIM") ? file.getParentFile() : file;
        } catch (Exception e) {
            return null;
        }
    }
    
    public static File createAndGetNBTFile(File f) {
        try {
            CompressedStreamTools.readCompressed(new FileInputStream(f));
        } catch (Exception e) {
            NBTTagCompound cmp = new NBTTagCompound();
            try {
                CompressedStreamTools.writeCompressed(cmp, new FileOutputStream(f));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        return f;
    }
    
    private static boolean injectNBTToFile(NBTTagCompound cmp, File f) {
        try {
            CompressedStreamTools.writeCompressed(cmp, new FileOutputStream(f));
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    public static boolean saveConfig(NBTTagCompound cmp, File f) {
        return injectNBTToFile(cmp, f);
    }
    
    public static NBTTagCompound getTagCompoundInFile(File f) {
        try {
            NBTTagCompound cmp = CompressedStreamTools.readCompressed(new FileInputStream(f));
            return cmp;
        } catch (IOException e) {
            NBTTagCompound cmp = new NBTTagCompound();
            try {
                CompressedStreamTools.writeCompressed(cmp, new FileOutputStream(f));
                return getTagCompoundInFile(f);
            } catch (IOException e1) {
                return null;
            }
        }
    }
}