package me.thebutlah.pandoragen.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.util.noise.PerlinNoiseGenerator;

public class PandoraChunkGenerator extends ChunkGenerator{

	
	public static boolean runonce = true;
	
	
	@Override
	public byte[][] generateBlockSections(World world, Random random, int chunkx, int chunkz, ChunkGenerator.BiomeGrid biomes) {
		byte[][] result = new byte[world.getMaxHeight() >> 4][];
		if(runonce){
			System.out.println("Ran once, adding populators.!");
			world.getPopulators().add(new OrePopulator());
			runonce = false;
		}
		
		for (int x=0; x<16; x++) {
			for (int z=0; z<16; z++) {
				
				int height = (int) ((PerlinNoiseGenerator.getNoise(getCoordinate(chunkx, x)/200.0, getCoordinate(chunkz, z)/200.0, 4, 2, 0.3) * 30) + 80);
				for(int y = 0; y < world.getMaxHeight(); y++){
					int blockid = 0;
					if(height - 4 < y && y < height){
						blockid = Material.DIRT.getId();
					}else if(y==height){
						blockid = Material.GRASS.getId();
					}else if(y < height){
						blockid = Material.STONE.getId();
					}
					this.setBlock(result, x, y, z, (byte) blockid);
				}
			}
		}
		
		return result;
	}
	
	private void setBlock(byte[][] result, int x, int y, int z, byte blkid) {
        if (result[y >> 4] == null) {
            result[y >> 4] = new byte[4096];
        }
        result[y >> 4][((y & 0xF) << 8) | (z << 4) | x] = blkid;
    }
	
	@Override
    public Location getFixedSpawnLocation(World world, Random random) {
		int x = random.nextInt(200) - 100;
		int z = random.nextInt(200) - 100;
		int y = world.getHighestBlockYAt(x, z);
		return new Location(world, x, y, z);
    }
	
	public List<BlockPopulator> getDefaultPopulators(World w){
		List<BlockPopulator> pops = new ArrayList<BlockPopulator>();
		pops.add(new OrePopulator());
		return pops;
	}
	
	public int getCoordinate(int chunkx, int x){
		return (chunkx << 4) + x;
	}
}
