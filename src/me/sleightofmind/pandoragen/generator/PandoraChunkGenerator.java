package me.sleightofmind.pandoragen.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.sleightofmind.pandoragen.PandoraGen;
import me.sleightofmind.pandoragen.Util;
import me.sleightofmind.pandoragen.WhittakerManager;
import me.sleightofmind.pandoragen.biomes.Biome;
import me.sleightofmind.pandoragen.populator.OrePopulator;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.BlockPopulator;

public class PandoraChunkGenerator extends ChunkGenerator{	
	
	@Override
	public short[][] generateExtBlockSections(World world, Random random, int chunkx, int chunkz, ChunkGenerator.BiomeGrid biomes) {
		short[][] blockdata = new short[world.getMaxHeight() >> 4][];
		
		for (int x=0; x<16; x++) {
			for (int z=0; z<16; z++) {
				int humidity = PandoraGen.wman.getHumidity(x, z);
				int temperature = PandoraGen.wman.getTemperature(x, z);
				int height = 0;
				for (Biome b : PandoraGen.biomes) {
					int dominance = PandoraGen.wman.getDominanceByAtmosphere(b.getID(), temperature, humidity);
					if (dominance <= 0) continue;
					//height += (b.generateHeightmap(Util.getCoordinate(chunkx, x), Util.getCoordinate(chunkz, z)));
					
				}
			}
		}
		
		return blockdata;
	}
	
	@Override
    public Location getFixedSpawnLocation(World world, Random random) {
		int x = random.nextInt(200) - 100;
		int z = random.nextInt(200) - 100;
		int y = world.getHighestBlockYAt(x, z);
		return new Location(world, x, y, z);
    }
	
	@Override
	public List<BlockPopulator> getDefaultPopulators(World w){
		List<BlockPopulator> pops = new ArrayList<BlockPopulator>();
		pops.add(new OrePopulator());
		return pops;
	}
	
	public int getCoordinate(int chunkx, int x){
		return (chunkx << 4) + x;
	}
}
