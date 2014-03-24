package me.sleightofmind.pandoragen.biomes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.sleightofmind.pandoragen.populator.Populator;

public abstract class Biome {
	
	protected List<Populator> populators = new ArrayList<Populator>();
	protected static final String name = null;
	protected final int id;
	
	public Biome(int id) {
		this.id = id;
	}
	
	/**
	 * Generates a column of blocks
	 * @param dominance The dominance from 0 to 255 of this biome over the column specified
	 */
	public abstract short[] generateColumn(int blockx, int blockz, int columnheight, int dominance, Random rand);
	
	/**
	 * Generates the height of a column given that there are no competing biomes
	 */
	public abstract int generateHeightmap(int blockx, int blockz);
	
	public List<Populator> getPopulators(){
		return populators;
	}
	
	public String getName() {
		return name;
	}
	
	public int getID() {
		return id;
	}
	
}