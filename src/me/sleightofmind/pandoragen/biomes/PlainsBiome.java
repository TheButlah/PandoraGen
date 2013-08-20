package me.sleightofmind.pandoragen.biomes;

import java.util.Random;

public class PlainsBiome extends Biome{

	@Override
	public short[][] generateColumn(int blockx, int blockz, int height, int dominance, Random rand, short[][] blockdata) {
		return null;
	}

	@Override
	public int generateHeightmap(int blockx, int blockz) {
		return 0;
	}

}
