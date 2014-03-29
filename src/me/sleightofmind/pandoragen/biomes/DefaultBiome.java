package me.sleightofmind.pandoragen.biomes;

import java.util.Random;

import org.bukkit.Material;

public class DefaultBiome extends Biome {
	
	private static final String name = "asdf";
	
	public DefaultBiome(int id) {
		super(id);
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
		return 64;
	}
	
	{
		name.charAt(0);
	}
}
