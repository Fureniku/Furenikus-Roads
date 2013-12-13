package co.uk.silvania.roads.tileentities.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import co.uk.silvania.roads.Roads;
import co.uk.silvania.roads.client.RenderIds;
import co.uk.silvania.roads.tileentities.entities.TileEntityTrafficLightEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityTrafficLightBlock extends BlockContainer {
	
	public static int newMeta;
	
	String powerState = "";

    public TileEntityTrafficLightBlock(int id) {
        super(id, Material.iron);
        this.setHardness(1.0F);
        this.setCreativeTab(Roads.tabRoads);
        this.setLightOpacity(0);
        this.setBlockBounds(0.35F, 0.0F, 0.35F, 0.65F, 0.9F, 0.65F);
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, int blockId) {
    	TileEntityTrafficLightEntity tileEntity = (TileEntityTrafficLightEntity)world.getBlockTileEntity(x, y, z);
    	int meta;
    	meta = world.getBlockMetadata(x, y, z);
		if(tileEntity != null) {
			if (world.isBlockIndirectlyGettingPowered(x, y, z)) {
				tileEntity.isPowered = true;
				System.out.println("It's getting powered!");
				//These printlns report correctly. This one is shown when power is activated to the block
			} else {
				tileEntity.isPowered = false;
				System.out.println("It's not getting powered.");
				//And this one comes on when the power is removed in any way. So, the physical power isn't the issue...
			}
		}
    }
    
    
    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityTrafficLightEntity();
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }
    
    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack itemStack) {
	    int blockSet = world.getBlockMetadata(x, y, z) / 4;
	    int direction = MathHelper.floor_double((double)(entityliving.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
	    int newMeta = (blockSet * 4) + direction;
	    world.setBlockMetadataWithNotify(x, y, z, newMeta, 0);
    }

    @SideOnly(Side.CLIENT)
    private Icon red;
    @SideOnly(Side.CLIENT)
    private Icon green;
    @SideOnly(Side.CLIENT)
    private Icon redamber;
    @SideOnly(Side.CLIENT)
    private Icon amber;

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        red = iconRegister.registerIcon("Roads:trafficLight0");
        green = iconRegister.registerIcon("Roads:trafficLight4");
        redamber = iconRegister.registerIcon("Roads:trafficLight8");
        amber = iconRegister.registerIcon("Roads:trafficLight12");
    }

    @SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int meta) {
		if (meta == 0) {
			return red;	
        }
		if (meta == 4) {
        	return green;
        }
		if (meta == 8) {
        	return redamber;
        }
		if (meta == 12) {
        	return amber;
        }
		return amber;	
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs creativeTabs, List list) {
        list.add(new ItemStack(par1, 1, 0));
        list.add(new ItemStack(par1, 1, 4));
        list.add(new ItemStack(par1, 1, 8));
        list.add(new ItemStack(par1, 1, 12));
    }
}