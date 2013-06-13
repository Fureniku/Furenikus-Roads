package co.uk.silvania.roads.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class RBPaintGagBlock extends BlockContainer{
        
        public RBPaintGagBlock(int id){
        super(id, Material.rock);
        //register a texture for block for textureloader not to crash the game. This image can be even empty, 
        //cause we are not going to render the gag.
        setUnlocalizedName("yourmod:something");
        }
        //This block is called when block is broken and destroys the primary block.
        @Override
        public void breakBlock(World world, int i, int j, int k, int par5, int par6){
                //Reading the gag's tile entity.
                RBPaintGagBlockTE tileEntity = (RBPaintGagBlockTE)world.getBlockTileEntity(i, j, k);
                //If not make this check, the game may crash if there's no tile entity at i, j, k.
                if (tileEntity != null){
                        //Actually destroys primary block.
                        world.destroyBlock(tileEntity.primary_x, tileEntity.primary_y, tileEntity.primary_z, false);
                        //Forces removing tile entity from primary block coordinates,
                        //cause sometimes minecraft forgets to do that.
                        world.removeBlockTileEntity(tileEntity.primary_x, tileEntity.primary_y, tileEntity.primary_z);
                }
                //Same as above, but for the gag block tile entity.
                world.removeBlockTileEntity(i, j, k);
        }
        //This method checks if primary block exists. 
        @Override
        public void onNeighborBlockChange(World world, int i, int j, int k, int par5){
                RBPaintGagBlockTE tileEntity = (RBPaintGagBlockTE)world.getBlockTileEntity(i, j, k);
                if (tileEntity != null){
                        //No need to check if block's Id matches the Id of our primary block, 
                        //because if a player want to change a block, he needs to brake it first, 
                        //and in this case block will be set to Air (Id = 0)
                        if(world.getBlockId(tileEntity.primary_x, tileEntity.primary_y, 
                                        tileEntity.primary_z) < 1){
                                world.destroyBlock(i, j, k, false);
                                world.removeBlockTileEntity(i, j, k);
                        }
                }
        }
        //This makes our gag invisible.
        @Override
        public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l){
                return false;
        }
        //This tells minecraft to render surrounding blocks.
        @Override
        public boolean isOpaqueCube(){
                return false;
        }
        @Override
        public TileEntity createNewTileEntity(World world) {
                return new RBPaintGagBlockTE();
        }
}