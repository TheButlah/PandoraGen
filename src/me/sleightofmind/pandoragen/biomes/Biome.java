package me.sleightofmind.pandoragen.biomes;

import java.util.Random;

public interface Biome {
	
	/**
	 * Generates a column of blocks and sets them in the blockdata
	 * 
	 * @param dominance The dominance from 0 to 255 of this biome over the column specified
	 */
	public abstract void generateColumn(int blockx, int blockz, int columnheight, int dominance, Random rand, short[][] blockdata);
	
	/**
	 * Generates the height of a column given that there are no competing biomes
	 */
	public abstract int generateHeightmap(int blockx, int blockz);
	
	public String getName();
	
	public int getID();
	
}