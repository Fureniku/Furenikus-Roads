package co.uk.silvania.roads.tileentities.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityRoadSlope3Entity extends TileEntity {
	
	@SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
            return AxisAlignedBB.getAABBPool().getAABB(this.xCoord - 10, this.yCoord - 10, this.zCoord - 10, this.xCoord + 10, this.yCoord + 10, this.zCoord + 10);
    }
}
