package co.uk.silvania.roads.tileentities.renderers;

import java.io.DataInputStream;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

import co.uk.silvania.roads.NBTConfig;
import co.uk.silvania.roads.Roads;
import co.uk.silvania.roads.client.models.RoadSlopeModel;
import co.uk.silvania.roads.client.models.TrafficLightModel;
import co.uk.silvania.roads.tileentities.blocks.TileEntityTrafficLightBlock;
import co.uk.silvania.roads.tileentities.entities.TileEntityTrafficLightEntity;


public class TileEntityTrafficLightRenderer extends TileEntitySpecialRenderer {

    private TrafficLightModel model;

    public TileEntityTrafficLightRenderer() {
        model = new TrafficLightModel();
    }
    
    public void readPacketData (DataInputStream dis) {
    	
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
        int rotation = 180;
        switch (te.getBlockMetadata() % 4) {
            case 0:
                rotation = 0;
                break;
            case 3:
                rotation = 90;
                break;
            case 2:
                rotation = 180;
                break;
            case 1:
                rotation = 270;
                break;
        }

        GL11.glPushMatrix();
        int i = te.getBlockMetadata();
        
        if (te instanceof TileEntityTrafficLightEntity) {
        	World world = Minecraft.getMinecraft().theWorld;
        	TileEntityTrafficLightEntity trafficLight = (TileEntityTrafficLightEntity) te;
        	NBTTagCompound nbt = NBTConfig.getTagCompoundInFile(NBTConfig.getWorldConfig(world));
        	trafficLight.readFromNBT(nbt);
        	System.out.println("Reading NBT value render-side. Right now, it's " + nbt.getString("hasPower"));
        	if (nbt.getString("hasPower").equals("powered")) {
        		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Roads.modid, "textures/entities/TrafficLightGreen.png"));
        	} else {
        		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Roads.modid, "textures/entities/TrafficLightRed.png"));
        	}
        }
            
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(1.0F, -1F, -1F);
        model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    	//}
    }
}