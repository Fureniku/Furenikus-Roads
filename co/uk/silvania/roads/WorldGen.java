package co.uk.silvania.roads;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.dimensionId){
		case 0:
			generateSurface(world, random, chunkX * 16, chunkZ *16);
		}
		
		//world.setBlock(chunkX*16 + random.nextInt(16), 100, chunkZ*16 + random.nextInt(16), Roads.limeStoneBlock);
	}

	private void generateSurface(World world, Random random, int i, int j) {
		// TODO Auto-generated method stub
		
	}
	
		
}
