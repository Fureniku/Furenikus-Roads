package co.uk.silvania.roads.client.render;

import co.uk.silvania.roads.blocks.FRBlocks;
import co.uk.silvania.roads.blocks.RoadBlock;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;

public class RoadBlockItemRenderingHandler implements IItemRenderer {

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        Tessellator tess = Tessellator.instance;
        tess.setBrightness(1);


        tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
        IIcon icon;

        int meta = item.getItemDamage();
        icon = FRBlocks.roadBlockBase1.getIcon(0, 0);

        double u0 = (double)icon.getMinU();
        double u1 = (double)icon.getMaxU();
        
        icon.getMinU();
        icon.getMaxU();
        double v0 = (double)icon.getMinV();
        double v1 = (double)icon.getMaxV();
        
        
        //Initial height values. Quad Height method simply gets the height from meta via quick calculation (more reliable than checking bounding box size, for some reason)
        //Value is compass.
        double a  = quadHeight(meta); //Current block
        int x = 0;
        int y = 0;
        int z = 0;

        
        //Now, we actually render each face.
        //Each face needs the colour setting, and then four vertex.
        //Colour is required as it's reduced for sides and bottom, to give a false effect of "shading" which is surprisingly very important.
        //Top Side
        tess.addVertexWithUV(x,   a, z,   u1, v1); //NW
        tess.addVertexWithUV(x,   a, z+1, u1, v0); //SW
        tess.addVertexWithUV(x+1, a, z+1, u0, v0); //SE
        tess.addVertexWithUV(x+1, a, z,   u0, v1); //NE
        
        //North Side
        tess.setColorOpaque(204, 204, 204);
        tess.addVertexWithUV(x+1, y+a, z, u1, v1);
        tess.addVertexWithUV(x+1, y,   z, u1, v0);
        tess.addVertexWithUV(x,   y,   z, u0, v0);
        tess.addVertexWithUV(x,   y+a, z, u0, v1);
        
        //East Side
        tess.setColorOpaque(153, 153, 155);
        tess.addVertexWithUV(x+1, y+a, z+1, u1, v1);
        tess.addVertexWithUV(x+1, y,   z+1, u1, v0);
        tess.addVertexWithUV(x+1, y,   z,   u0, v0);
        tess.addVertexWithUV(x+1, y+a, z,   u0, v1);
        
        //South Side
        tess.setColorOpaque(204, 204, 204);
        tess.addVertexWithUV(x,   y+a, z+1, u1, v1);
        tess.addVertexWithUV(x,   y,   z+1, u1, v0);
        tess.addVertexWithUV(x+1, y,   z+1, u0, v0);
        tess.addVertexWithUV(x+1, y+a, z+1, u0, v1);

        //West Side
        tess.setColorOpaque(153, 153, 155);
        tess.addVertexWithUV(x,   y+a, z,   u1, v1);
        tess.addVertexWithUV(x,   y,   z,   u1, v0);
        tess.addVertexWithUV(x,   y,   z+1, u0, v0);
        tess.addVertexWithUV(x,   y+a, z+1, u0, v1);

        //Bottom Side
        tess.setColorOpaque(127, 127, 127);
        tess.addVertexWithUV(x,   y,   z+1, u1, v1);
        tess.addVertexWithUV(x,   y,   z, u1, v0);
        tess.addVertexWithUV(x+1, y,   z, u0, v0);
        tess.addVertexWithUV(x+1, y,   z+1, u0, v1);
		
	}
	
	public double quadHeight(int meta) {
		double m = meta + 1.0;
		return ((1.0 / 16.0) * m);
	}

}
