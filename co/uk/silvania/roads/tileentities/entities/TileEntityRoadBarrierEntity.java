package co.uk.silvania.roads.tileentities.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityRoadBarrierEntity extends TileEntity {
	
	@SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
            return AxisAlignedBB.getAABBPool().getAABB(this.xCoord - 5, this.yCoord - 5, this.zCoord - 5, this.xCoord + 5, this.yCoord + 5, this.zCoord + 5);
    }

}
