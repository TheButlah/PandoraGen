package me.sleightofmind.pandoragen.biomes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.sleightofmind.pandoragen.populator.Populator;

public abstract class Biome {
	
	protected List<Populator> populators = new ArrayList<Populator>();
	private final String name = "Biome";
	private final int id;
	
	public Biome(int id) {
		this.id = id;
		//populators.add();
		
	}
	
	/**
	 * Generates a column of blocks up to columnheight. Makeup of column defined by each unique biome
	 * @param dominance The dominance from 0 to 255 of this biome over the column specified.
	 * NOTE: This parameter should not be used to change the height of the column in any way, but instead change the block makeup (like on borders of biomes)
	 */
	public abstract short[] generateColumn(int xcoord, int zcoord, int columnheight, int dominance, Random rand);
	
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
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Biome)) return false;
		Biome other = (Biome) obj;
		if (other.getName().equals(this.getName())) return true;
		return false;
	}
	
}