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

public class RBWhiteCorner extends Block {

        public RBWhiteCorner (int id) {
            super(id, Material.rock);
            this.setHardness(1.0F);
    		this.setStepSound(Block.soundStoneFootstep);
    		this.setCreativeTab(Roads.tabRoads);
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.825F, 1.0F);
        }
           
    	@SideOnly(Side.CLIENT)
    	private Icon sides;
    	@SideOnly(Side.CLIENT)
    	private Icon top;

    	public void registerIcons(IconRegister iconRegister) {
            this.top = iconRegister.registerIcon("Roads:TarmacWhiteCorner");
            this.sides = iconRegister.registerIcon("Roads:TarmacPlain");
    	}
    	
        public int getRenderType() {
            return 16;
            }

        public boolean isOpaqueCube() {
        	return false;
            }
            
        public boolean renderAsNormalBlock() {
        	return false;
            }

        @SideOnly(Side.CLIENT)
        public Icon getIcon(int side, int meta) {
            int k = meta;
            if (k == 0 && (side == 1)) {
             	return top;
         	} else if (k == 1 && (side == 1)) {
               	return top;
           	} else if (k == 2 && (side == 1)) {
               	return top;
            } else if (k == 3 && (side == 1)) {
              	return top;
            } else if (k == 4 && (side == 1)) {
               	return top;
            } else if (k == 5 && (side == 1)) {
               	return top;
            }
       		return sides;
        }

        public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving entity, ItemStack item) {
            int l = determineOrientation(par1World, par2, par3, par4, entity);
            par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
        }

        public static int determineOrientation(World world, int par1, int par2, int par3, EntityLiving entity) {
            int l = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
            return l == 0 ? 3 : (l == 1 ? 4 : (l == 2 ? 2 : (l == 3 ? 5 : 0)));
        }
    }