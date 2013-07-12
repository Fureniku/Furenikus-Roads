package co.uk.silvania.roads.block;

import co.uk.silvania.roads.CommonProxy;
import co.uk.silvania.roads.Roads;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class RBYellowFull extends RoadBlockBase {

        public RBYellowFull (int id) {
            super(id);
        }
           
    	@SideOnly(Side.CLIENT)
    	private Icon sides;
    	@SideOnly(Side.CLIENT)
    	private Icon top;

    	public void registerIcons(IconRegister iconRegister) {
            this.top = iconRegister.registerIcon("Roads:TarmacYellowFull");
            this.sides = iconRegister.registerIcon("Roads:TarmacPlain");
    	}
    	
        @SideOnly(Side.CLIENT)
        public Icon getIcon(int side, int meta) {
            int k = meta;
            if (side == 1) {
             	return top;
            } else {
            	return sides;
            }
        }
    }