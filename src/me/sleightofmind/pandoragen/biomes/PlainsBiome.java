package me.sleightofmind.pandoragen.biomes;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.util.noise.PerlinNoiseGenerator;

public class PlainsBiome extends Biome{
	
	public PlainsBiome(int id) {
		super(id);
	}

	@Override
	public void generateColumn(int blockx, int blockz, int height, int dominance, Random rand, short[][] blockdata) {
		for(int y = 0; y <= height; y++){
			int id = 0;
			if(y == 0) id = Material.BEDROCK.getId();
			else if(y >= height - 3) id = Material.DIRT.getId();
			else if(y == height) id = Material.GRASS.getId();
			else id = Material.STONE.getId();
			
			setBlock(blockdata, blockx, height, blockz, id);
		}
	}

	@Override
	public int generateHeightmap(int blockx, int blockz) {
		return (int) ((PerlinNoiseGenerator.getNoise(blockx/200.0, blockz/200.0, 4, 2, 0.3) * 25) + 70);
	}

}
