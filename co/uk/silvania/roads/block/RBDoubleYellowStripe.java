package co.uk.silvania.roads.block;

import co.uk.silvania.roads.CommonProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class RBDoubleYellowStripe extends BlockDirectional {

        public RBDoubleYellowStripe (int id, int texture, Material material) {
                super(id, texture, material);
        }
        
        public int getBlockTextureFromSide(int side){
        	if(side == 1){
        		return blockIndexInTexture;
        	}else{
        		return 1;
        		      		
        	}       	
        }
        
        @SideOnly(Side.CLIENT)
        
        public int getBlockTextureFromSideAndMetadata(int par1, int par2)
        {
            int var3 = par2 & 12;
            int var4 = par2 & 3;
            return var3 == 0 && (par1 == 1 || par1 == 0) ? 21 : (var3 == 4 && (par1 == 5 || par1 == 4) ? 21 : (var3 == 8 && (par1 == 2 || par1 == 3) ? 21 : (var4 == 1 ? 116 : (var4 == 2 ? 117 : (var4 == 3 ? 153 : 20)))));
        }

        /*public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving)
        {
            int var6 = determineOrientation(par1World, par2, par3, par4, (EntityPlayer)par5EntityLiving);
            par1World.setBlockMetadataWithNotify(par2, par3, par4, var6);

            if (!par1World.isRemote)
            {
                this.updatePistonState(par1World, par2, par3, par4);
            }
        }*/
        
        
        
        
        /*public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving)
        {
        	int rotation = MathHelper.floor_double((double)((entityliving.rotationYaw *4F) / 360F) + 2.5D) & 3;
        	world.setBlockMetadata(i, j, k, rotation - 0);
        }*/
        
        @Override
        public String getTextureFile () {
                return CommonProxy.BLOCK_PNG;
        }

}