package me.sleightofmind.pandoragen.biomes;

import java.util.Random;

import me.sleightofmind.pandoragen.Util;

import org.bukkit.Material;

public class DefaultBiome implements Biome {

	private static final String name = "Default";
	private int id;
	
	public DefaultBiome(int id) {
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
		return 64;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getID() {
		return id;
	}

}
