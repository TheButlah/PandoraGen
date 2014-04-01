package me.sleightofmind.pandoragen.biomes;

import java.util.Random;

import me.sleightofmind.pandoragen.populator.ShrubberyPopulator;

import org.bukkit.Material;
import org.bukkit.util.noise.PerlinNoiseGenerator;

public class PlainsBiome extends Biome{
	
	public PlainsBiome(int id) {
		super(id);
		populators.add(new ShrubberyPopulator());
	}

	@Override @SuppressWarnings("deprecation")
	public short[] generateColumn(int blockx, int blockz, int height, int dominance, Random rand) {
		short[] column = new short[height];
		for(int y = 0; y < height; y++){
			int blkid = 0;
			if(y == 0) blkid = Material.BEDROCK.getId();
			else if(y == height) blkid = Material.GRASS.getId();
			else if(y >= height - 3) blkid = Material.DIRT.getId();
			else blkid = Material.STONE.getId();	
			column[y] = (short) blkid;
		}
		return column;
	}

	@Override
	public int generateHeightmap(int blockx, int blockz) {
		return (int) ((PerlinNoiseGenerator.getNoise(blockx/200.0, blockz/200.0, 4, 2, 0.3) * 25) + 70);
	}

}
