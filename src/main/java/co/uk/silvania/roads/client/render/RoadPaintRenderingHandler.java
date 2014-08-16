package co.uk.silvania.roads.client.render;

import co.uk.silvania.roads.blocks.LineBlock;
import co.uk.silvania.roads.blocks.RoadBlock;
import co.uk.silvania.roads.client.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RoadPaintRenderingHandler implements ISimpleBlockRenderingHandler {
	Tessellator tess;
	
	@Override
	public void renderInventoryBlock(Block block, int meta, int modelId, RenderBlocks renderer) {
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		renderer.blockAccess.getHeight();
        tess = Tessellator.instance;
        tess.setBrightness(block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z));
        
        float f = 0.8F;
        int c = block.colorMultiplier(renderer.blockAccess, x, y, z);
        float f1 = (float)(c >> 16 & 255) / 255.0F;
        float f2 = (float)(c >> 8 & 255) / 255.0F;
        float f3 = (float)(c & 255) / 255.0F;

        tess.setColorOpaque_F(f * f1, f * f2, f * f3);
        IIcon icon;
        LineBlock lineBlock = (LineBlock) block;

        int meta = renderer.blockAccess.getBlockMetadata(x, y, z);
        icon = lineBlock.getIcon(world, x, y, z, meta);
        



        double u0 = (double)icon.getMinU();
        double u1 = (double)icon.getMaxU();
        
        double v0 = (double)icon.getMinV();
        double v1 = (double)icon.getMaxV();

        
        //Initial height values. Quad Height method simply gets the height from meta via quick calculation (more reliable than checking bounding box size, for some reason)
        //Value is compass.
        double a  = quadHeight(world.getBlockMetadata(x, (y-1), z)); //Current block
        double n  = quadHeight(world.getBlockMetadata(x  , (y-1), z-1));
        double ne = quadHeight(world.getBlockMetadata(x+1, (y-1), z-1));
        double e  = quadHeight(world.getBlockMetadata(x+1, (y-1), z  ));
        double se = quadHeight(world.getBlockMetadata(x+1, (y-1), z+1));
        double s  = quadHeight(world.getBlockMetadata(x  , (y-1), z+1));
        double sw = quadHeight(world.getBlockMetadata(x-1, (y-1), z+1));
        double w  = quadHeight(world.getBlockMetadata(x-1, (y-1), z  ));
        double nw = quadHeight(world.getBlockMetadata(x-1, (y-1), z-1));
        
        //If the block is max height, it should check the blocks ABOVE to continue a ramp to the next level. As blocks always go up, it makes
        //no sense to check things on it's own level, because it'll never go up there. Value is short for "Y Height"
        int yH = 0;
        if (world.getBlockMetadata(x, (y-1), z) == 15) {
        	yH = 1;
        }
        
        //Check if each of the 8 points to connect are actually a roadblock.
        if (world.getBlock(x,   (y-1)+yH, z-1) instanceof RoadBlock) { n  = quadHeight(world.getBlockMetadata(x  , (y-1)+yH, z-1));} else { n  = a;}
        if (world.getBlock(x+1, (y-1)+yH, z-1) instanceof RoadBlock) { ne = quadHeight(world.getBlockMetadata(x+1, (y-1)+yH, z-1));} else { ne = a;}
        if (world.getBlock(x+1, (y-1)+yH, z)   instanceof RoadBlock) { e  = quadHeight(world.getBlockMetadata(x+1, (y-1)+yH, z  ));} else { e  = a;}
        if (world.getBlock(x+1, (y-1)+yH, z+1) instanceof RoadBlock) { se = quadHeight(world.getBlockMetadata(x+1, (y-1)+yH, z+1));} else { se = a;}
        if (world.getBlock(x,   (y-1)+yH, z+1) instanceof RoadBlock) { s  = quadHeight(world.getBlockMetadata(x  , (y-1)+yH, z+1));} else { s  = a;}
        if (world.getBlock(x-1, (y-1)+yH, z+1) instanceof RoadBlock) { sw = quadHeight(world.getBlockMetadata(x-1, (y-1)+yH, z+1));} else { sw = a;}
        if (world.getBlock(x-1, (y-1)+yH, z)   instanceof RoadBlock) { w  = quadHeight(world.getBlockMetadata(x-1, (y-1)+yH, z  ));} else { w  = a;}
        if (world.getBlock(x-1, (y-1)+yH, z-1) instanceof RoadBlock) { nw = quadHeight(world.getBlockMetadata(x-1, (y-1)+yH, z-1));} else { nw = a;}
        
        //Create a boolean as to whether there's a valid connection on each of the 8 sides.
        //Check if each side is HIGHER than the current block. We only go up, never down.
        boolean nB  = n  > a;
        boolean neB = ne > a;
        boolean eB  = e  > a;
        boolean seB = se > a;
        boolean sB  = s  > a;
        boolean swB = sw > a;
        boolean wB  = w  > a;
        boolean nwB = nw > a;

        double neQ = 0;
        double seQ = 0;
        double nwQ = 0;
        double swQ = 0;
        
        //We prioritise corners. If a corner isn't valid, assume north/south first else east/west (It can only be one or the other if corner is invalid)
        //If no matches, then set to own height (Do that first really)
        if (!nB && !nwB && !neB) { n = a;}
        if (!eB && !neB && !seB) { e = a;}
        if (!sB && !swB && !seB) { s = a;}
        if (!wB && !nwB && !swB) { w = a;}
        
        if (nB && eB) { neQ = ne; } else if (nB) { neQ = n;} else if (eB) { neQ = e;} else if (neB) { neQ = ne; } else { neQ = a;}
        if (nB && wB) { nwQ = nw; } else if (nB) { nwQ = n;} else if (wB) { nwQ = w;} else if (nwB) { nwQ = nw; } else { nwQ = a;}
        if (sB && eB) { seQ = se; } else if (sB) { seQ = s;} else if (eB) { seQ = e;} else if (seB) { seQ = se; } else { seQ = a;}
        if (sB && wB) { swQ = sw; } else if (sB) { swQ = s;} else if (wB) { swQ = w;} else if (swB) { swQ = sw; } else { swQ = a;}
        
        //If all four sides are equal (same height) colour should be flat. Else, slightly darker.
        int col = 255;
        if (!(neQ == nwQ && neQ == seQ && neQ == swQ)) {
        	col = 240;
        }
        
        System.out.println("SWB: " + swB + ", SB: " + sB + ", WB: " + wB);
        System.out.println("NE: " + neQ + ", SE: " + seQ + ", SW: " + swQ + ", NW: " + nwQ);
        
        //Now, we actually render each face.
        //Each face needs the colour setting, and then four vertex.
        //Top Side
        tess.setColorOpaque(col, col, col);
        tess.addVertexWithUV(x,   (y-1)+nwQ, z,   u0, v0); //NW
        tess.addVertexWithUV(x,   (y-1)+swQ, z+1, u0, v1); //SW
        tess.addVertexWithUV(x+1, (y-1)+seQ, z+1, u1, v1); //SE
        tess.addVertexWithUV(x+1, (y-1)+neQ, z,   u1, v0); //NE
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return false;
	}

	@Override
	public int getRenderId() {
		return ClientProxy.roadBlockRenderID;
	}
	
	public double quadHeight(int meta) {
		double m = meta + 1.0;
		return ((1.0 / 16.0) * m) + 0.01;
	}
}