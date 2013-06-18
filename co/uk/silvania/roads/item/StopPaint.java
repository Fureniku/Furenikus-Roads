/*package co.uk.silvania.roads.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class StopPaint extends Item {

	public StopPaint(int par1) {
		super(par1);
		// TODO Auto-generated constructor stub
	}
	
	//Called when a player right-clicks with this item in his hand
    @Override
    public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float xOffset, float yOffset, float zOffSet){
        //Prevents itemstack from decreasing when in creative mod
        if (!player.capabilities.isCreativeMode){
            --item.stackSize;
        }
        //Prevents from making changes in inactive world
        if (!world.isRemote){
                //Increases y coordinate, so our block will be placed on top of the block you clicked, just as it should be
                y++;
                //Takes the player sight direction
                int dir = MathHelper.floor_double((double)((player.rotationYaw * 4F) / 360F) + 0.5D) & 3;
                //This array will store information about the coordinates where we want to place our gags relatively to the primary block.
                //To add blocks, just add some more {rel_x, rel_y, rel_z} into the array.
                //Exactly this array will add three gag block from the side of the primary block.
                public int[][] gagShift = {{-1, 0, -1}, {-1, 0, 0}, {-1, 0, 1}};
                int[] shift;
                //This cycle will prevent us from placing block instead of other ones, more commonly, it checks if the places where we want
                //to place gags are empty.
                boolean canPlace = true;
                for(int i = 0; i < gagShift.length; i++){
                        shift = rotXZByDir(gagShift[i][0], y, gagShift[i][1], dir);
                        if(!world.isAirBlock(x + shift[0], y + shift[1], z + shift[2])){
                                canPlace = false;
                        }
                }
                //If the check was successful
                if(canPlace){
                        //0x02 flag will update neighboring flags, we have to use it, or we'll have some glitches.
                        world.setBlock(x, y, z, yourMod.yourBlock.blockID, dir, 0x02);
                        //This code is placing gags one after another into the coordinates we've set.
                        for(int i = 0; i < gagShift.length; i++){
                                shift = rotXZByDir(gagShift[i][0], y, gagShift[i][1], dir);
                                world.setBlock(x + shift[0], y + shift[1], z + shift[2], yourMod.gagblock.blockID, dir, 0x02);
                                //For gags to know, where primary block is placed.
                                TileEntityGag tileGag = (TileEntityGag)world.getBlockTileEntity(
                                                x + shift[0], y, z + shift[1]);
                                tileGag.primary_x = x;
                                tileGag.primary_y = y;
                                tileGag.primary_z = z;
                        }
                }
                        return true;
                }
        return false;
    }
        //This function rotates the relative coordinates accordingly to the given direction
        public int[] rotXZByDir(int x, int y, int z, int dir){
                if (dir == 0){
                        return new int[]{x, y, z};
                }else if(dir == 1){
                        return new int[]{-z, y, x};
                }else if(dir == 2){
                        return new int[]{-x, y, -z};
                }else{
                        return new int[]{z, y, -x};
                }
        }

}*/
