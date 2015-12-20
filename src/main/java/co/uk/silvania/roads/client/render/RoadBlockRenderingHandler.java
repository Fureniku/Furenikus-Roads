package co.uk.silvania.roads.client.render;

import org.lwjgl.opengl.GL11;

import co.uk.silvania.roads.blocks.FRBlocks;
import co.uk.silvania.roads.blocks.LineBlock;
import co.uk.silvania.roads.blocks.RoadBlock;
import co.uk.silvania.roads.client.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RoadBlockRenderingHandler implements ISimpleBlockRenderingHandler {
	Tessellator tess;
	
	@Override
	public void renderInventoryBlock(Block block, int meta, int modelId, RenderBlocks renderer) {
		Tessellator tess = Tessellator.instance;

		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        //tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
        IIcon icon = block.getIcon(0, meta);

        double u0 = (double)icon.getMinU();
        double u1 = (double)icon.getMaxU();

        double v0 = (double)icon.getMinV();
        double v1 = (double)icon.getMaxV();
        
        final float FACE_XZ_NORMAL = 0.8944F;
        final float FACE_Y_NORMAL  = 0.4472F;
        
        
        //The height of the block. Drawn from metadata, which is more reliable than trying to pull it from the bounding box.
        double a  = quadHeight(meta); //Current block
        
        //Now, we actually render each face.
        //Each face needs the colour setting, and then four vertex.
        //Colour is required as it's reduced for sides and bottom, to give a false effect of "shading" which is surprisingly very important.
        //Top Side
        tess.startDrawingQuads();
        tess.setNormal(0.0F, 1.0F, 0.0F);
        tess.addVertexWithUV(0, a, 0, u1, v1); //NW
        tess.addVertexWithUV(0, a, 1, u1, v0); //SW
        tess.addVertexWithUV(1, a, 1, u0, v0); //SE
        tess.addVertexWithUV(1, a, 0, u0, v1); //NE
        tess.draw();
        
        //North Side
        tess.startDrawingQuads();
        tess.setNormal(0.0F, FACE_Y_NORMAL, -FACE_XZ_NORMAL);
        tess.addVertexWithUV(1, a, 0, u1, v1);
        tess.addVertexWithUV(1, 0, 0, u1, v0);
        tess.addVertexWithUV(0, 0, 0, u0, v0);
        tess.addVertexWithUV(0, a, 0, u0, v1);
        tess.draw();
        
        //East Side
        tess.startDrawingQuads();
        tess.setNormal(FACE_XZ_NORMAL, FACE_Y_NORMAL, 0.0F);
        tess.addVertexWithUV(1, a, 1, u1, v1);
        tess.addVertexWithUV(1, 0, 1, u1, v0);
        tess.addVertexWithUV(1, 0, 0, u0, v0);
        tess.addVertexWithUV(1, a, 0, u0, v1);
        tess.draw();
        
        //South Side
        tess.startDrawingQuads();
        tess.setNormal(0.0F, FACE_Y_NORMAL, FACE_XZ_NORMAL);
        tess.addVertexWithUV(0, a, 1, u1, v1);
        tess.addVertexWithUV(0, 0, 1, u1, v0);
        tess.addVertexWithUV(1, 0, 1, u0, v0);
        tess.addVertexWithUV(1, a, 1, u0, v1);
        tess.draw();

        //West Side
        tess.startDrawingQuads();
        tess.setNormal(-FACE_XZ_NORMAL, FACE_Y_NORMAL, 0.0F);
        tess.addVertexWithUV(0, a, 0, u1, v1);
        tess.addVertexWithUV(0, 0, 0, u1, v0);
        tess.addVertexWithUV(0, 0, 1, u0, v0);
        tess.addVertexWithUV(0, a, 1, u0, v1);
        tess.draw();

        //Bottom Side
        tess.startDrawingQuads();
        tess.setNormal(0.0F, -1.0F, 0.0F);
        tess.addVertexWithUV(0, 0, 1, u1, v1);
        tess.addVertexWithUV(0, 0, 0, u1, v0);
        tess.addVertexWithUV(1, 0, 0, u0, v0);
        tess.addVertexWithUV(1, 0, 1, u0, v1);
        tess.draw();
        
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		renderer.blockAccess.getHeight();
        tess = Tessellator.instance;
        tess.setBrightness(block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z));
        
        float f = 0.8F;
        int c = block.colorMultiplier(world, x, y, z);
        float f1 = (float)(c >> 16 & 255) / 255.0F;
        float f2 = (float)(c >> 8 & 255) / 255.0F;
        float f3 = (float)(c & 255) / 255.0F;

        tess.setColorOpaque_F(f * f1, f * f2, f * f3);
        
        int meta = renderer.blockAccess.getBlockMetadata(x, y, z);
        IIcon icon = block.getIcon(0, meta);

        double u0 = (double)icon.getMinU();
        double u1 = (double)icon.getMaxU();
        double v0 = (double)icon.getMinV();
        double v1 = (double)icon.getMaxV();
        
        //Initial height values. Quad Height method simply gets the height from meta via quick calculation (more reliable than checking bounding box size, for some reason)
        //Value is compass.
        double a  = quadHeight(world.getBlockMetadata(x, y, z)); //Current block
        double n  = quadHeight(world.getBlockMetadata(x,   y, z-1));
        double ne = quadHeight(world.getBlockMetadata(x+1, y, z-1));
        double e  = quadHeight(world.getBlockMetadata(x+1, y, z  ));
        double se = quadHeight(world.getBlockMetadata(x+1, y, z+1));
        double s  = quadHeight(world.getBlockMetadata(x,   y, z+1));
        double sw = quadHeight(world.getBlockMetadata(x-1, y, z+1));
        double w  = quadHeight(world.getBlockMetadata(x-1, y, z  ));
        double nw = quadHeight(world.getBlockMetadata(x-1, y, z-1));

        //If the block is max height, it should check the blocks ABOVE to continue a ramp to the next level. As blocks always go up, it makes
        //no sense to check things on it's own level, because it'll never go up there.
        double nt  = 0;
        double net = 0;
        double et  = 0;
        double set = 0;
        double st  = 0;
        double swt = 0;
        double wt  = 0;
        double nwt = 0;
        
        double offset = 1-quadHeight(world.getBlockMetadata(x, y, z));
        //This currently only checks the block next to it, and if there's a block there then it adds full.
        //should check block ABOVE the block next to it, and add the correct amount.
        //double not boolean, y+1, meta to get value. boom.
        if (world.getBlock(x,   y+1, z-1) instanceof RoadBlock) { nt  = quadHeight(world.getBlockMetadata(x,   y+1, z-1)) + offset;}
        if (world.getBlock(x+1, y+1, z-1) instanceof RoadBlock) { net = quadHeight(world.getBlockMetadata(x+1, y+1, z-1)) + offset;}
        if (world.getBlock(x+1, y+1, z)   instanceof RoadBlock) { et  = quadHeight(world.getBlockMetadata(x+1, y+1, z  )) + offset;}
        if (world.getBlock(x+1, y+1, z+1) instanceof RoadBlock) { set = quadHeight(world.getBlockMetadata(x+1, y+1, z+1)) + offset;}
        if (world.getBlock(x,   y+1, z+1) instanceof RoadBlock) { st  = quadHeight(world.getBlockMetadata(x,   y+1, z+1)) + offset;}
        if (world.getBlock(x-1, y+1, z+1) instanceof RoadBlock) { swt = quadHeight(world.getBlockMetadata(x-1, y+1, z+1)) + offset;}
        if (world.getBlock(x-1, y+1, z)   instanceof RoadBlock) { wt  = quadHeight(world.getBlockMetadata(x-1, y+1, z  )) + offset;}
        if (world.getBlock(x-1, y+1, z-1) instanceof RoadBlock) { nwt = quadHeight(world.getBlockMetadata(x-1, y+1, z-1)) + offset;}
                
        double nQh = 0;
        double neQh = 0;
        double eQh = 0;
        double seQh = 0;
        double sQh = 0;
        double swQh = 0;
        double wQh = 0;
        double nwQh = 0;
        
        if (world.getBlock(x,   y, z-1) instanceof RoadBlock) { nQh  = quadHeight(world.getBlockMetadata(x,   y, z-1)); } else if (!(world.isAirBlock(x,   y, z-1))) { nQh  = 1;}
        if (world.getBlock(x+1, y, z-1) instanceof RoadBlock) { neQh = quadHeight(world.getBlockMetadata(x+1, y, z-1)); } else if (!(world.isAirBlock(x+1, y, z-1))) { neQh = 1;}
        if (world.getBlock(x+1, y, z)   instanceof RoadBlock) { eQh  = quadHeight(world.getBlockMetadata(x+1, y, z));   } else if (!(world.isAirBlock(x+1, y, z)))   { eQh  = 1;}
        if (world.getBlock(x+1, y, z+1) instanceof RoadBlock) { seQh = quadHeight(world.getBlockMetadata(x+1, y, z+1)); } else if (!(world.isAirBlock(x+1, y, z+1))) { seQh = 1;}
        if (world.getBlock(x,   y, z+1) instanceof RoadBlock) { sQh  = quadHeight(world.getBlockMetadata(x,   y, z+1)); } else if (!(world.isAirBlock(x,   y, z+1))) { sQh  = 1;}
        if (world.getBlock(x-1, y, z+1) instanceof RoadBlock) { swQh = quadHeight(world.getBlockMetadata(x-1, y, z+1)); } else if (!(world.isAirBlock(x-1, y, z+1))) { swQh = 1;}
        if (world.getBlock(x-1, y, z)   instanceof RoadBlock) { wQh  = quadHeight(world.getBlockMetadata(x-1, y, z));   } else if (!(world.isAirBlock(x-1, y, z)))   { wQh  = 1;}
        if (world.getBlock(x-1, y, z-1) instanceof RoadBlock) { nwQh = quadHeight(world.getBlockMetadata(x-1, y, z-1)); } else if (!(world.isAirBlock(x-1, y, z-1))) { nwQh = 1;}
        
        if ((world.getBlock(x,   y, z-1) instanceof RoadBlock) || (nt > 0))  { n  = nQh  + nt;}  else { n  = a;} //TODO this one!
        if ((world.getBlock(x+1, y, z-1) instanceof RoadBlock) || (net > 0)) { ne = neQh + net;} else { ne = a;}
        if ((world.getBlock(x+1, y, z)   instanceof RoadBlock) || (et > 0))  { e  = eQh  + et;}  else { e  = a;}
        if ((world.getBlock(x+1, y, z+1) instanceof RoadBlock) || (set > 0)) { se = seQh + set;} else { se = a;}
        if ((world.getBlock(x,   y, z+1) instanceof RoadBlock) || (st > 0))  { s  = sQh  + st;}  else { s  = a;}
        if ((world.getBlock(x-1, y, z+1) instanceof RoadBlock) || (swt > 0)) { sw = swQh + swt;} else { sw = a;}
        if ((world.getBlock(x-1, y, z)   instanceof RoadBlock) || (wt > 0))  { w  = wQh  + wt;}  else { w  = a;}
        if ((world.getBlock(x-1, y, z-1) instanceof RoadBlock) || (nwt > 0)) { nw = nwQh + nwt;} else { nw = a;}
        
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
        
        //Change direct compass to match a corner if no direct line is found. Useful for side height calculations.
        /*if (!nB && nwB) { n = nw;}
        if (!nB && neB) { n = ne;}
        if (!eB && neB) { e = ne;}
        if (!eB && seB) { e = se;}
        if (!sB && swB) { s = sw;}
        if (!sB && seB) { s = se;}
        if (!wB && neB) { w = ne;}
        if (!wB && seB) { w = se;}*/
        
        //If all four sides are equal (same height) colour should be flat. Else, slightly darker.
        int col = 255;
        if (!(neQ == nwQ && neQ == seQ && neQ == swQ)) {
        	col = 240;
        }
        
        //If block above extends LineBlock, take it's texture and render it.
        Block blockAbove = world.getBlock(x, y+1, z);
    	if (blockAbove instanceof LineBlock) {
        	IIcon iconAbove = blockAbove.getIcon(world, x, y, z, meta);

            double u0_1 = (double)iconAbove.getMinU();
            double u1_1 = (double)iconAbove.getMaxU();
            double v0_1 = (double)iconAbove.getMinV();
            double v1_1 = (double)iconAbove.getMaxV();
            
            tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
            tess.addVertexWithUV(x,   y+nwQ+0.001, z,   u0_1, v0_1); //NW
            tess.addVertexWithUV(x,   y+swQ+0.001, z+1, u0_1, v1_1); //SW
            tess.addVertexWithUV(x+1, y+seQ+0.001, z+1, u1_1, v1_1); //SE
            tess.addVertexWithUV(x+1, y+neQ+0.001, z,   u1_1, v0_1); //NE
            tess.draw();
            tess.startDrawingQuads();
        }
        
        //Now, we actually render each face.
        //Each face needs the colour setting, and then four vertex.
        //Colour is required as it's reduced for sides and bottom, to give a false effect of "shading" which is surprisingly very important.
        
        
        
        
        
        //Top Side
        tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        tess.addVertexWithUV(x,   y+nwQ, z,   u1, v1); //NW
        tess.addVertexWithUV(x,   y+swQ, z+1, u1, v0); //SW
        tess.addVertexWithUV(x+1, y+seQ, z+1, u0, v0); //SE
        tess.addVertexWithUV(x+1, y+neQ, z,   u0, v1); //NE
        tess.draw();
        
        //North Side
        tess.startDrawingQuads();
        tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
        tess.addVertexWithUV(x+1, y+e, z, u1, v1);
        tess.addVertexWithUV(x+1, y,   z, u1, v0);
        tess.addVertexWithUV(x,   y,   z, u0, v0);
        tess.addVertexWithUV(x,   y+w, z, u0, v1);
        tess.draw();
        
        //East Side
        tess.startDrawingQuads();
        tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
        tess.addVertexWithUV(x+1, y+s, z+1, u1, v1);
        tess.addVertexWithUV(x+1, y,   z+1, u1, v0);
        tess.addVertexWithUV(x+1, y,   z,   u0, v0);
        tess.addVertexWithUV(x+1, y+n, z,   u0, v1);
        tess.draw();
        
        //South Side
        tess.startDrawingQuads();
        tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
        tess.addVertexWithUV(x,   y+w, z+1, u1, v1);
        tess.addVertexWithUV(x,   y,   z+1, u1, v0);
        tess.addVertexWithUV(x+1, y,   z+1, u0, v0);
        tess.addVertexWithUV(x+1, y+e, z+1, u0, v1);
        tess.draw();
        
        //West Side
        tess.startDrawingQuads();
        tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
        tess.addVertexWithUV(x,   y+n, z,   u1, v1);
        tess.addVertexWithUV(x,   y,   z,   u1, v0);
        tess.addVertexWithUV(x,   y,   z+1, u0, v0);
        tess.addVertexWithUV(x,   y+s, z+1, u0, v1);
        tess.draw();
        
        //Bottom Side
        tess.startDrawingQuads();
        tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
        tess.addVertexWithUV(x,   y,   z+1, u1, v1);
        tess.addVertexWithUV(x,   y,   z, u1, v0);
        tess.addVertexWithUV(x+1, y,   z, u0, v0);
        tess.addVertexWithUV(x+1, y,   z+1, u0, v1);
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return ClientProxy.roadBlockRenderID;
	}
	
	public double quadHeight(int meta) {
		double m = meta + 1.0;
		return ((1.0 / 16.0) * m);
	}
}