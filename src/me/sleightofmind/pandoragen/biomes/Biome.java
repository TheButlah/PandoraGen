package me.sleightofmind.pandoragen.biomes;

import java.util.Random;

public abstract class Biome {
	
	protected String name;
	protected static int id;
	
	public Biome(int id) {
		this.id = id;
	}
	
	/**
	 * Generates a column of blocks and sets them in the blockdata
	 * 
	 * @param dominance The dominance from 0 to 255 of this biome over the column specified
	 */
	public abstract void generateColumn(int blockx, int blockz, int height, int dominance, Random rand, short[][] blockdata);
	
	/**
	 * Generates the height of a column given that there are no competing biomes
	 */
	public abstract int generateHeightmap(int blockx, int blockz);
	
	public String getName() {
		return name;
	}
	
	public static int getID(){
		return id;
	}
	
	protected void setBlock(short[][] blockdata, int x, int y, int z, int blkid) {
        if (blockdata[y >> 4] == null) {
            blockdata[y >> 4] = new short[4096];
        }
        blockdata[y >> 4][((y & 0xF) << 8) | (z << 4) | x] = (short) blkid;
    }
	
	
}