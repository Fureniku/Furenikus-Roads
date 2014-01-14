package co.uk.silvania.roads;

import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EventListener {
	
	@SideOnly(Side.CLIENT)
    @ForgeSubscribe
	public void postStitch(TextureStitchEvent.Post event) {
    	System.out.println("Registering FlenixRoads Tar Texture for tank usage");
    	Roads.tarFluid.setIcons(Roads.tarBlock.getBlockTextureFromSide(0), Roads.tarBlock.getBlockTextureFromSide(1));
    }
}
