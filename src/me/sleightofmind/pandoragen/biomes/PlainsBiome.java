package me.sleightofmind.pandoragen.biomes;

import java.util.Random;

import me.sleightofmind.pandoragen.Util;

import org.bukkit.Material;
import org.bukkit.util.noise.PerlinNoiseGenerator;

public class PlainsBiome implements Biome{
	
	private static final String name = "Plains";
	private int id;
	
	public PlainsBiome(int id) {
		this.id = id;
	}

	@Override @SuppressWarnings("deprecation")
	public void generateColumn(int blockx, int blockz, int height, int dominance, Random rand, short[][] blockdata) {
		for(int y = 0; y < height; y++){
			int blkid = 0;
			if(y == 0) blkid = Material.BEDROCK.getId();
			else if(y == height) blkid = Material.GRASS.getId();
			else if(y >= height - 3) blkid = Material.DIRT.getId();
			else blkid = Material.STONE.getId();	
			Util.setBlock(blockdata, blockx & 0xF, y, blockz & 0xF, (short) blkid);
		}		
	}

	@Override
	public int generateHeightmap(int blockx, int blockz) {
		return (int) ((PerlinNoiseGenerator.getNoise(blockx/200.0, blockz/200.0, 4, 2, 0.3) * 25) + 70);
	}

	@Override
	public String getName() {
		return name;
	}

	public int getID() {
		return id;
	}

}
