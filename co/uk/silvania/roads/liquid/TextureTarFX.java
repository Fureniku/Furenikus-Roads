//Stub- nothing in here working yet.
package co.uk.silvania.roads.liquid;

import co.uk.silvania.roads.Roads;
import net.minecraft.client.renderer.texturefx.TextureWaterFX;

public class TextureTarFX extends TextureWaterFX {
	
	public TextureTarFX() {
		super();
		this.iconIndex = Roads.instance.roadsTarFlowing.blockIndexInTexture;
	}

}
