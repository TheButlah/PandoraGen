package me.sleightofmind.pandoragen.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.sleightofmind.pandoragen.PandoraGen;
import me.sleightofmind.pandoragen.Util;
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
				int dominancesum = 0;
				//Use the default biome by default
				Biome mostdominantbiome = PandoraGen.biomes.get(0);
				//if a value of zero is used, there is a potential for the terrain to be entirely flat in the absence of other biomes' influence
				int highestdominance = 1;
				for (Biome b : PandoraGen.biomes) {
					int dominance = PandoraGen.wman.getDominanceByAtmosphere(b, temperature, humidity);
					if (dominance == 0) continue;
					if (dominance > highestdominance) {
						highestdominance = dominance;
						mostdominantbiome = b;
					}
					dominancesum += dominance;
					//by adding the heightmaps together and then dividing by their dominancesum (which happens in the next few lines)
					//we essentially cause the program to calculate a weighted average of the different heightmaps
					height += b.generateHeightmap(Util.getCoordinate(chunkx, x), Util.getCoordinate(chunkz, z))*dominance;
				}
				//scale the dominance of the most dominant biome to 0-255
				highestdominance = (int) Math.ceil(highestdominance / (double) dominancesum)*255;
				//scale the height back down
				height = (int) Math.ceil(height / (double) dominancesum);
				short[] column = mostdominantbiome.generateColumn(Util.getCoordinate(chunkx, x), Util.getCoordinate(chunkz, z), height, highestdominance, random);
				
				for (int y=0; y<height; y++) {
					Util.setBlock(blockdata, x, y, z, column[y]);
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
