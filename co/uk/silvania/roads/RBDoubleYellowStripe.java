package co.uk.silvania.roads;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class RBDoubleYellowStripe extends BlockDirectional {

        public RBDoubleYellowStripe (int id, int texture, Material material) {
                super(id, texture, material);
        }
        
        public int getBlockTextureFromSide(int side){
        	if(side == 1 || side == 2){
        		return blockIndexInTexture;
        	}else{
        		return 1;
        		      		
        	}       	
        }
        
        public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving)
        {
        	int rotation = MathHelper.floor_double((double)((entityliving.rotationYaw *4F) / 360F) + 2.5D) & 3;
        	world.setBlockMetadata(i, j, k, rotation - 0);
        }
        
        @Override
        public String getTextureFile () {
                return CommonProxy.BLOCK_PNG;
        }

}