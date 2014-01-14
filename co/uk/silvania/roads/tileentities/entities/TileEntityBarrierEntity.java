package co.uk.silvania.roads.tileentities.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityBarrierEntity extends TileEntity {

	@SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
            return AxisAlignedBB.getAABBPool().getAABB(this.xCoord - 2, this.yCoord - 2, this.zCoord - 2, this.xCoord + 2, this.yCoord + 2, this.zCoord + 2);
    }
}
