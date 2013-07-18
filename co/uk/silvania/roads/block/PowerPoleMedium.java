package co.uk.silvania.roads.block;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import co.uk.silvania.roads.Roads;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Direction;
import net.minecraft.util.ReportedException;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class PowerPoleMedium extends Block {

	private Material material;
	
	private boolean wiresProvidePower = true;
    private Set blocksNeedingUpdate = new HashSet();
	
	public PowerPoleMedium(int id) {
		super(id, Material.iron);
		this.setCreativeTab(Roads.tabRoads);
        this.setBlockBounds(0.45F, 0.0F, 0.45F, 0.55F, 1.0F, 0.55F);
	}
	
    public int getRenderType() {
    	return 0;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }
    
	public boolean renderAsNormalBlock() {
		return false;
	}
	
    public void registerIcons(IconRegister iconRegister) {
    	blockIcon = iconRegister.registerIcon("Roads:PowerPole");
    }
}