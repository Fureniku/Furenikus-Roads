package co.uk.silvania.roads.block;

import co.uk.silvania.roads.CommonProxy;
import co.uk.silvania.roads.Roads;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class RBDoubleYellowStripeEndLeft extends RoadBlockBase {
    public RBDoubleYellowStripeEndLeft(int id) {
        super(id);
    }
    
	@SideOnly(Side.CLIENT)
	private Icon sides;
	@SideOnly(Side.CLIENT)
	private Icon top;
       
	public void registerIcons(IconRegister iconRegister) {
        this.top = iconRegister.registerIcon("Roads:TarmacDoubleYellowStripeEndLeft");
        this.sides = iconRegister.registerIcon("Roads:TarmacPlain");
	}
	
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta) {
        int k = meta;
        if (k == 0 || k == 1 || k ==  1 || k == 3 || k == 4 || k == 5 && (side == 1)) {
         	return top;
        }
   		return sides;
    }
}